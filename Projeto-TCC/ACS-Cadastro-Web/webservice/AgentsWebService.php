<?php
include "../persistences/AgentsPersistence.php";

$method = $_SERVER['REQUEST_METHOD'];

if ($method == "GET") {

    if (isset($_GET['f']) && function_exists($_GET['f']) && $_GET['f'] == "get") {

        $value = isset($_GET['d']) ? $_GET['d'] : 0;
        if ($value) {
            get($value);
        }
    }
}

function get($numSus)
{

    //var_dump(AgentsPersistence::getAll());
    //echo "<br><br>";

    $agent = AgentsPersistence::get($numSus);
    if ($agent) {
        echo json_encode(getAgentData($agent));
    }
}

function getAgentData($array)
{
    return array(
        AgentsPersistence::NAME => $array[AgentsPersistence::NAME],
        AgentsPersistence::NUM_SUS => $array[AgentsPersistence::NUM_SUS],
        AgentsPersistence::AREA => $array[AgentsPersistence::AREA],
        AgentsPersistence::EQUIP => $array[AgentsPersistence::EQUIP]);
}