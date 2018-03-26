<?php

include "../mainUtil.php";
use Database\Database;

$fields = array();


if(checkFields($fields) && checkValidApiKey()) {


} else {
    echo "error!";
}