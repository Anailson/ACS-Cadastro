<?php

if (!@include "persistences/VisitPersistence.class.php") {
    include "../persistences/VisitPersistence.class.php";
}

$method = $_SERVER['REQUEST_METHOD'];
if ($method === AcsDataBase::GET) {
    if (isset($_GET['f']) && function_exists($_GET['f']) && $_GET['f'] == "get") {

        $value = isset($_GET['d']) ? $_GET['d'] : 0;
        if ($value) {
            get($value);
        } else {
            $value = isset($_GET['a']) ? $_GET['a'] : false;
            if($value){
                getAll($value);
            } else {
                getAll();
            }
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
    $citizen = VisitModel::getFromArray(json_decode($json, true));
    echo json_encode(array("id" => VisitPersistence::insert($citizen)));
}

function get($numSus)
{

}

function getAll($numSus = false)
{
    $visit = VisitPersistence::getAll($numSus);
    if($visit){
        echo json_encode($visit);
    }
}