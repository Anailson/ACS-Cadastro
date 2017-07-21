<?php
include "../persistences/AgentsPersistence.php";

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

    $list = isset($_GET['m']) ? $_GET['m'] : false;
    if($list) {
        $json = $_POST['JSON'];
        insert($json);
    }
}

function get($numSus)
{
    $agent = AgentsPersistence::get($numSus);
    if ($agent) {
        echo json_encode(getAgentData($agent));
    }
}

function getAll()
{
    $list = AgentsPersistence::getAll();
    $agents = array();
    foreach ($list as $l) {
        array_push($agents, getAgentData($l));

    }
    echo json_encode($agents);
}

function insert($json)
{
    $array = json_decode($json, true);
    $array = array("id" => AgentsPersistence::insert($array));
    echo json_encode($array);
}

function getAgentData($array)
{
    return array(
        AgentsPersistence::NAME => $array[AgentsPersistence::NAME],
        AgentsPersistence::NUM_SUS => $array[AgentsPersistence::NUM_SUS],
        AgentsPersistence::AREA => $array[AgentsPersistence::AREA],
        AgentsPersistence::EQUIP => $array[AgentsPersistence::EQUIP]);
}