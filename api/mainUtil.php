<?php
/**
 * Created by PhpStorm.
 * User: Wouter Kodde
 * Date: 13-10-2016
 * Time: 18:58
 */

include 'autoLoader.php';

function redirect($url)
{
    header('Location: '.$url);
}

function getApiKey(){
    return "F9413B2193145D4F8B14943B88B98";
}

function checkValidApiKey(){
    if (isset($_POST['key']) && !empty($_POST['key']) && $_POST['key'] == getApiKey()){
        return true;
    } else {
        return false;
    }
}


function checkFields($fields){
    foreach ($fields as $field){
        if(!isset($_POST[$field]) && !empty($_POST[$field])){
            return false;
        }
    }
    return true;
}

function getField($field){
    return $_POST[$field];
}