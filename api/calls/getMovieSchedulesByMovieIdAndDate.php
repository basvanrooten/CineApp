<?php

include "../mainUtil.php";

use Database\Database;

$fields = array("startDate", "movieId");
$movieSchedules = array();

if(checkFields($fields) && checkValidApiKey()) {

    $startDate = getField($fields[0]);
    $movieId = getField($fields[1]);

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
        //Return just the empty array.
        //Do a die(); method so the code stops running and doesn't crash, because the statement->fetchAll() will throw another exception.
        die(json_encode($movieSchedules));
    }

    $results = $statement->fetchAll();

    foreach($results as $result){
        $MovieSchedule = array();

        $movieSchedule["id"] = $result["Id"];
        $movieSchedule["startDate"] = $result["StartDate"];
        $movieSchedule["movieId"] = $result["MovieId"];

        $theater = array();
        $theater["id"] = $result["TheaterId"];
        $theater["name"] = $result["Name"];
        $movieSchedule["theater"] = $theater;

        $movieSchedules["results"][] = $movieSchedule;
    }



}

echo json_encode($movieSchedules);
