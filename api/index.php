<?php
//require_once "databaseConnection.php";

include "mainUtil.php";

if(isset($_POST["type"])) {
    $type = $_POST["type"];

    switch ($type) {
        case 'get':
            break;
        case 'set':
            break;
        case 'update':
            break;
        default:
            break;
    }
}