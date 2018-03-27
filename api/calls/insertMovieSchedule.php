<?php

include "../mainUtil.php";

use Database\Database;

$fields = array("startDate", "movieId", "theaterId");
$id = array();

if(checkFields($fields) && checkValidApiKey()) {

    $startDate = getField($fields[0]);
    $movieId = getField($fields[1]);
    $theaterId = getField($fields[2]);

    $statement = Database::getConnection()->prepare("INSERT INTO `MovieSchedule` (StartDate, MovieId, TheaterId) VALUES (?, ?, ?);");
    $statement->bindValue(1, $startDate, Database::PARAM_STR);
    $statement->bindValue(2, $movieId, Database::PARAM_STR);
    $statement->bindValue(3, $theaterId, Database::PARAM_STR);

    try
    {
        $statement->execute();
    }
    catch (\Exception $e)
    {
        //Return just the empty array.
        //Do a die(); method so the code stops running and doesn't crash, because the statement->fetchAll() will throw another exception.
        die(json_encode($id));
    }

    $id["id"] = Database::getConnection()->lastInsertId();

}

echo json_encode($id);
