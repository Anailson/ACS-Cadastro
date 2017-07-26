<?php

if (!@include "persistences/CitizenPersistence.php") {
    include "../persistences/CitizenPersistence.php";
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
    $citizen = CitizenModel::getFromArray(json_decode($json, true));
    echo json_encode(array("id" => CitizenPersistence::insert($citizen)));
}

function insertAll($array)
{
    $array = json_decode($array, true);
    $rows = 0;
    foreach ($array as $value){
        $citizen = CitizenModel::getFromArray($value);
        if (($id = CitizenPersistence::insert($citizen)) > 0){
            $rows++;
        }
    }
    echo json_encode(array("rows" => $rows));
}