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
// for temp testing I disabled the api key check
//    if (isset($_GET['key']) && !empty($_GET['key']) && $_GET['key'] == getApiKey()){
//        return true;
//    } else {
//        return false;
//    }
    return true;
}


function checkFields($fields){
    foreach ($fields as $field){
        if(!isset($_GET[$field]) || empty($_GET[$field])){
            return false;
        }
    }
    return true;
}

function getField($field){
    return $_GET[$field];
}