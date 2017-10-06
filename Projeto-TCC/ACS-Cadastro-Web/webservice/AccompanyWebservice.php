<?php
if(!@include_once "persistences/AccompanyPersistence.php") {
    include_once "../persistences/AccompanyPersistence.php";
}

$method = $_SERVER['REQUEST_METHOD'];

if($method === AcsDataBase::GET){

    if (isset($_GET['f']) && function_exists($_GET['f']) && $_GET['f'] == "get") {

        $value = isset($_GET['d']) ? $_GET['d'] : false;
        if ($value) {
            get($value);
        } else {
            $value = isset($_GET['a']) ? $_GET['a'] : false;
            getAll($value);
        }
    }

}else if ($method === AcsDataBase::POST) {
    $option = isset($_GET['o']) ? $_GET['o'] : false;
    if($option){
        insertAll($_POST['JSON']);
    } else{
        insert($_POST['JSON']);
    }
}

function insert($json)
{
    $array = json_decode($json, true);
    $accompany = AccompanyModel::getFromArray($array);
    echo json_encode(array ("id" => AccompanyPersistence::insert($array['AGENT']['NUM_SUS'], $accompany)));
}

function get($json)
{

}

function getAll($numSus = false)
{
    $accompanies = AccompanyPersistence::getAll($numSus);
    if($accompanies){
        echo json_encode($accompanies);
    }
}

function insertAll($json)
{
    $values = json_decode($json, true);

    $agent = $values[0];
    unset($values[0]);

    $rows = 0;
    $i = 0;
    $indexes = array();
    $array = array();

    foreach ($values as $value) {
        $citizen = AccompanyModel::getFromArray($value);
        $id = AccompanyPersistence::insert($agent['NUM_SUS'], $citizen);
        ($id > 0) ? $rows++ : $indexes[] = array ("index" => $i);
        $i++;
    }
    $array["rows"] = $rows;
    $array["indexes"] = $indexes;
    echo json_encode($array);
}