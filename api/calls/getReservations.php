<?php

include "../mainUtil.php";
use Database\Database;

$fields = array("userId");
$reservations = [];

if(checkFields($fields) && checkValidApiKey()) {

    $userId = getField($fields[0]);

    $statement = Database::getConnection()->prepare("SELECT * FROM reservations WHERE userId = ?;");
    $statement->bindValue(1, $userId, Database::PARAM_STR);

    try
    {
        $statement->execute();
    }
    catch (\Exception $e)
    {
        return false;
    }

    $result = $statement->fetchAll();

    for ($i = 0; $i < count($result); $i++) {
        array_push($reservations, array(
            "id" => $result[$i]["id"],
            "screeningId" => $result[$i]["screeningId"],
            "status" => $result[$i]["status"],
            "paymentId" => $result[$i]["paymentId"],
            "paymentMethod" => $result[$i]["paymentMethod"]
        ));
    }

    echo json_encode($reservations);
} else {
    echo "error!";
}