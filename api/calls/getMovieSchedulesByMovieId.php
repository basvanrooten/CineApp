<?php

include "../mainUtil.php";

use Database\Database;

$fields = array("movieId");
$movieSchedules = array();

if(checkFields($fields) && checkValidApiKey()) {

    $movieId = getField($fields[0]);

    $statement = Database::getConnection()->prepare("SELECT * FROM `MovieSchedule` WHERE MovieId = ?");
    $statement->bindValue(1, $movieId, Database::PARAM_STR);

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
        $movieSchedule["theaterId"] = $result["TheaterId"];

        $movieSchedules[] = $movieSchedule;
    }



}

echo json_encode($movieSchedules);
