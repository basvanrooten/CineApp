<?php

include "../../mainUtil.php";
require "Mollie/API/Autoloader.php";

$fields = array("id");
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

    $id = getField($fields[0]);

    $payment	= $mollie->payments->get($id);


    if($payment->isPaid()) {
        $paymentData["payment"]["status"] = "paid";
    } elseif ($payment->isCancelled()) {
        $paymentData["payment"]["status"] = "cancelled";
    } elseif ($payment->isExpired()) {
        $paymentData["payment"]["status"] = "expired";
    } elseif ($payment->isFailed()) {
        $paymentData["payment"]["status"] = "failed";
    } else {
        $paymentData["payment"]["status"] = "failed";
    }

}
echo json_encode($paymentData);