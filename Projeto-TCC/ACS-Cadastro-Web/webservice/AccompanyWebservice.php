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
    $accompany = AccompanyModel::getFromArray(json_decode($json, true));
    echo json_encode(array ("id" => AccompanyPersistence::insert($accompany)));
}

function insertAll($array)
{

}