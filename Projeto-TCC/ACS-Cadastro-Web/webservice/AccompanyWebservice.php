<?php
if(!@include "persistences/AccompanyPersistence.php") {
    include "../persistences/AccompanyPersistence.php";
}

$method = $_SERVER['REQUEST_METHOD'];

if ($method === AcsDataBase::POST) {

    $option = isset($_GET['o']) ? $_GET['o'] : false;
    $json = $_POST['JSON'];
    if ($option) {
        insertAll($json);
    } else {
        insert($json);
    }
}

function insert($json)
{
    $arrayIds = AccompanyPersistence::insert($json);
    var_dump($arrayIds);
    echo $arrayIds["ID_ACCOMPANY"];
}

function insertAll($array)
{

}