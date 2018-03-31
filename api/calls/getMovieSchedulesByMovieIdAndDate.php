<?php

include "../mainUtil.php";

use Database\Database;

$fields = array("startDate", "movieId");
$movieSchedules = array();

if(checkFields($fields) && checkValidApiKey()) {

    $startDate = getField($fields[0]);
    $movieId = getField($fields[1]);
    $theaters = 1;

    $results = getMovieSchedules($startDate, $movieId);
    if(count($results) == 0) {
        insertMovieSchedules($startDate, $movieId, $theaters);
        $results = getMovieSchedules($startDate, $movieId);
    }

    foreach($results as $result){
        $MovieSchedule = array();

        $movieSchedule["id"] = $result["Id"];
        $movieSchedule["startDate"] = $result["StartDate"];
        $movieSchedule["movieId"] = $result["MovieId"];

        $theater = array();
        $theater["id"] = $result["TheaterId"];
        $theater["name"] = $result["Name"];
        $movieSchedule["theater"] = $theater;
        $movieSchedule["takenPerc"] = getTakenPercentage($movieSchedule["id"], $theater["id"]);
        $movieSchedules["results"][] = $movieSchedule;
    }



}
echo json_encode($movieSchedules);

function getTakenPercentage($movieScheduleId, $theaterId) {
    $statement = Database::getConnection()->prepare("
        SELECT COUNT(*) as FreeSeats
      FROM Taken 
      WHERE MovieScheduleId = ? AND Taken = 0;
    ");
    $statement->bindValue(1, $movieScheduleId, Database::PARAM_STR);

    try
    {
        $statement->execute();
    }
    catch (\Exception $e)
    {
        return 0;
    }

    $freeSeats = $statement->fetch();
    $freeSeats = intval($freeSeats["FreeSeats"]);

    $statement = Database::getConnection()->prepare("
        SELECT COUNT(*) as NumOfSeats
        FROM Seat 
        WHERE TheaterId = ?;
    ");
    $statement->bindValue(1, $theaterId, Database::PARAM_STR);

    try
    {
        $statement->execute();
    }
    catch (\Exception $e)
    {
        return 0;
    }
    $numOfSeats = $statement->fetch();
    $numOfSeats = intval($numOfSeats["NumOfSeats"]);

    return intval($freeSeats / $numOfSeats *100);

}

function getMovieSchedules($startDate, $movieId) {
    $statement = Database::getConnection()->prepare("
        SELECT Theater.Id as 'TheaterId', Theater.Name, MovieSchedule.*
        FROM Theater
        JOIN (
            SELECT *
            FROM `MovieSchedule`
            WHERE Date(StartDate) = Date(?) AND MovieId = ?) as MovieSchedule
        ON Theater.Id = MovieSchedule.TheaterId;
    ");
    $statement->bindValue(1, $startDate, Database::PARAM_STR);
    $statement->bindValue(2, $movieId, Database::PARAM_STR);

    try
    {
        $statement->execute();
    }
    catch (\Exception $e)
    {
        return [];
    }

    return $statement->fetchAll();
}

function insertMovieSchedules($startDate, $movieId, $theaters) {
    $times = 3;
    $date = new DateTime($startDate);
    $date->setTime(9, 0);
    for($i = 0; $i < $times; $i++) {
        $theater = rand(1, $theaters);
        $statement = Database::getConnection()->prepare("
        INSERT INTO
        MovieSchedule(StartDate, MovieId, TheaterId)
        VALUES (?, ?, ?)
    ");
        $statement->bindValue(1, $date->format("Y-m-d H:i:s"), Database::PARAM_STR);
        $statement->bindValue(2, $movieId, Database::PARAM_STR);
        $statement->bindValue(3, $theater, Database::PARAM_STR);

        try
        {
          $statement->execute();
        }
        catch (\Exception $e)
        {
            die();
        }

        $scheduleId = Database::getConnection()->lastInsertId();
        $statement = Database::getConnection()->prepare("SELECT * FROM `Seat` WHERE TheaterId = ?");
        $statement->bindValue(1, $theater, Database::PARAM_STR);


        try
        {
            $statement->execute();
        }
        catch (\Exception $e)
        {
            die();
        }

        $seats = $statement->fetchAll();

        foreach ($seats as $seat) {
            $statement = Database::getConnection()->prepare("
            INSERT INTO
            Taken(SeatId, MovieScheduleId, Taken)
            VALUES (?, ?, 0)
            ");
            $statement->bindValue(1, $seat["Id"], Database::PARAM_STR);
            $statement->bindValue(2, $scheduleId, Database::PARAM_STR);

            try
            {
                $statement->execute();
            }
            catch (\Exception $e)
            {
                die();
            }
        }

        $date = $date->add(new DateInterval("PT5H"));
    }
}


