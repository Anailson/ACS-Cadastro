<?php
if (!@include "controllers/AgentsController.php") {include "../controllers/AgentsController.php";}
if (!@include "controllers/CitizenController.class.php") {include "../controllers/CitizenController.class.php";}

$method = $_SERVER['REQUEST_METHOD'];

if ($method === AcsDataBase::GET) {

    if (isset($_GET['f']) && function_exists($_GET['f']) && $_GET['f'] == "get") {

        $value = isset($_GET['d']) ? $_GET['d'] : false;
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
    if ($option) {
        insertAll($json);
    } else {
        insert($json);
    }
}

function get($json)
{
    echo $json;
}

function getAll($numSus = false)
{
    $citizens = CitizenPersistence::getAll($numSus);
    if($citizens){
        echo json_encode($citizens);
    }
}

function insert($json)
{
    $array = json_decode($json, true);
    $citizen = CitizenModel::getFromArray($array);
    $numSus = $array["AGENT"]['NUM_SUS'];
    echo json_encode(array("id" => CitizenPersistence::insert($numSus, $citizen)));
}

function insertAll($json)
{
    $values = json_decode($json, true);
    $agent = $values[0];
    unset($values[0]);

    $i = 0;
    $indexes = array();
    foreach ($values as $value) {
        $citizen = CitizenModel::getFromArray($value);
        $id = CitizenPersistence::insert($agent['NUM_SUS'], $citizen);
        if($id > 0){
            $indexes[] = array ("index" => $i);
        }
        $i++;
    }
    echo json_encode($indexes);
}