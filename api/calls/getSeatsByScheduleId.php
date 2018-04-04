<?php

include "../mainUtil.php";

use Database\Database;

$fields = array("scheduleId");
$seats = array();

date_default_timezone_set('Europe/Amsterdam');

if(checkFields($fields) && checkValidApiKey()) {

    $scheduleId = getField($fields[0]);
    $results = getSeats($scheduleId);



    foreach($results as $result){
        $updateDate = new DateTime($result["UpdateDate"]);
        $diff = $updateDate->diff(new DateTime());

        if($diff->i >= 15 && $result["Taken"] == 1) {
            $statement = Database::getConnection()->prepare("Update Taken Set Taken = 0 WHERE SeatId = ? AND MovieScheduleId = ?");
            $statement->bindValue(1, $result["Id"], Database::PARAM_STR);
            $statement->bindValue(2, $scheduleId, Database::PARAM_STR);

            try
            {
                $statement->execute();
            }
            catch (\Exception $e)
            {

            }
            $results = getSeats($scheduleId);
        }
        $MovieSchedule = array();

        $movieSchedule["theaterId"] = $result["TheaterId"];
        $movieSchedule["seatId"] = $result["Id"];
        $movieSchedule["seatNumber"] = $result["SeatNumber"];
        $movieSchedule["rowNumber"] = $result["RowNumber"];
        $movieSchedule["taken"] = $result["Taken"];
        $movieSchedule["lastUpdated"] = $result["UpdateDate"];

        $seats["results"][] = $movieSchedule;
    }



}

function getSeats($scheduleId) {
    $statement = Database::getConnection()->prepare("SELECT m.TheaterId, s.Id, s.SeatNumber, s.RowNumber, t.Taken, t.UpdateDate FROM MovieSchedule m JOIN Seat s ON m.TheaterId = s.TheaterId JOIN Taken t ON t.SeatId = s.Id && m.id = t.MovieScheduleId WHERE m.Id = ?");
    $statement->bindValue(1, $scheduleId, Database::PARAM_STR);

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

echo json_encode($seats);
