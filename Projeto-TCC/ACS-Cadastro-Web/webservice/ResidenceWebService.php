<?php

if (!@include "persistences/ResidencePersistence.class.php") {
    include "../persistences/ResidencePersistence.class.php";
}

$method = $_SERVER['REQUEST_METHOD'];
if ($method === AcsDataBase::GET) {
    if (isset($_GET['f']) && function_exists($_GET['f']) && $_GET['f'] == "get") {

        $value = isset($_GET['d']) ? $_GET['d'] : 0;
        if ($value) {
            get($value);
        } else {
            getAll();
        }
    }
} else if ($method === AcsDataBase::POST) {

    $option = isset($_GET['o']) ? $_GET['o'] : false;
    $json = $_POST['JSON'];
    if($option){
        insertAll($json);
    } else {
        insert($json);
    }
}

function insert($json)
{
    //TODO set latitude/longitude using residence of address
    $residence = ResidenceModel::getFromArray(json_decode($json, true));
    echo json_encode(array("id" => ResidencePersistence::insert($residence)));
}
