<?php
include "../persistences/CitizenPersistence.php";

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

    $list = isset($_GET['o']) ? $_GET['o'] : false;
    $json = $_POST['JSON'];
    insert($json);
}

function insert($json)
{
    $array = json_decode($json, true);
    $array = array("id" => CitizenPersistence::insert($array));
    echo json_encode($array);
}

function insertAll($jsonArray)
{

}