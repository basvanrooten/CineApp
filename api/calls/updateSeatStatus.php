<?php

include "../mainUtil.php";

use Database\Database;

$fields = array("scheduleId", "seatId", "status");
$seat = array();
$results = array();

if(checkFields($fields) && checkValidApiKey()) {

    $scheduleId = getField($fields[0]);
    $seatId = getField($fields[1]);
    $status = getField($fields[2]);

    $statement = Database::getConnection()->prepare("Update Taken Set Taken = ? WHERE SeatId = ? AND MovieScheduleId = ?");
    $statement->bindValue(1, $status, Database::PARAM_STR);
    $statement->bindValue(2, $seatId, Database::PARAM_STR);
    $statement->bindValue(3, $scheduleId, Database::PARAM_STR);

    try
    {
        $statement->execute();
    }
    catch (\Exception $e)
    {
        //Return just the empty array.
        //Do a die(); method so the code stops running and doesn't crash, because the statement->fetchAll() will throw another exception.
        die();
    }

    $statement = Database::getConnection()->prepare("SELECT m.TheaterId, s.Id, s.SeatNumber, s.RowNumber, t.Taken FROM MovieSchedule m JOIN Seat s ON m.TheaterId = s.TheaterId JOIN Taken t ON t.SeatId = s.Id && m.id = t.MovieScheduleId WHERE m.Id = ? AND s.Id = ?");
    $statement->bindValue(1, $scheduleId, Database::PARAM_STR);
    $statement->bindValue(2, $seatId, Database::PARAM_STR);

    try
    {
        $statement->execute();
    }
    catch (\Exception $e)
    {
        //Return just the empty array.
        //Do a die(); method so the code stops running and doesn't crash, because the statement->fetchAll() will throw another exception.
        die();
    }

    $result = $statement->fetch();

    $seat["theaterId"] = $result["TheaterId"];
    $seat["seatId"] = $result["Id"];
    $seat["seatNumber"] = $result["SeatNumber"];
    $seat["rowNumber"] = $result["RowNumber"];
    $seat["taken"] = $result["Taken"];
    $results["results"][] = $seat;

}

echo json_encode($results);
