<?php

include "../mainUtil.php";

use Database\Database;

$fields = array("scheduleId");
$seats = array();

if(checkFields($fields) && checkValidApiKey()) {

    $scheduleId = getField($fields[0]);

    $statement = Database::getConnection()->prepare("SELECT m.TheaterId, s.Id, s.SeatNumber, s.RowNumber, t.Taken FROM MovieSchedule m JOIN Seat s ON m.TheaterId = s.TheaterId JOIN Taken t ON t.SeatId = s.Id && m.id = t.MovieScheduleId WHERE m.Id = ?");
    $statement->bindValue(1, $scheduleId, Database::PARAM_STR);

    try
    {
        $statement->execute();
    }
    catch (\Exception $e)
    {
        //Return just the empty array.
        //Do a die(); method so the code stops running and doesn't crash, because the statement->fetchAll() will throw another exception.
        die(json_encode($seats));
    }

    $results = $statement->fetchAll();

    foreach($results as $result){
        $MovieSchedule = array();

        $movieSchedule["theaterId"] = $result["TheaterId"];
        $movieSchedule["seatId"] = $result["Id"];
        $movieSchedule["seatNumber"] = $result["SeatNumber"];
        $movieSchedule["rowNumber"] = $result["RowNumber"];
        $movieSchedule["taken"] = $result["Taken"];

        $seats["results"][] = $movieSchedule;
    }



}

echo json_encode($seats);
