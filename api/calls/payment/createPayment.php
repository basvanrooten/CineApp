<?php

include "../../mainUtil.php";
require "Mollie/API/Autoloader.php";

$fields = array("amount", "description");
$movieSchedules = array();

$paymentData = [];
$test = true;

if(checkFields($fields) && checkValidApiKey()) {
    $mollie = new Mollie_API_Client();
    if($test) {
        $mollie->setApiKey("test_952f8yVN3jWnyuv2RmSMHVFF3pUm4C");
    } else {
        $mollie->setApiKey("live_yhHjdHPdWr7y9QDn5bVa8E2caz4NqA");
    }

    $amount = getField($fields[0]);
    $description = getField($fields[1]);
    $redirectUrl = "http://api.gaikvanavondlam.nl/redirecturl/";
    $webhookUrl =  "http://api.gaikvanavondlam.nl/webhook/";

    try {
        $payment = $mollie->payments->create(array(
            "amount" => floatval($amount),
            "description" => $description,
            "redirectUrl" => $redirectUrl
        ));
        $paymentData["payment"]["id"] = $payment->id;
        $paymentData["payment"]["amount"] = floatval($amount);
        $paymentData["payment"]["description"] = $description;
        $paymentData["payment"]["redirectUrl"] = $redirectUrl;
        $paymentData["payment"]["paymentUrl"] = $payment->getPaymentUrl();
        $paymentData["payment"]["webhookUrl"] = $webhookUrl;
    } catch (Mollie_API_Exception $e)
    {
        $paymentData = "error";
    }


}

echo json_encode($paymentData);
