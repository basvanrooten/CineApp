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
