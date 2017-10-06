<?php
if(!@include_once "models/AgentModel.class.php"){include_once "../models/AgentModel.class.php";}
if(!@include_once "persistences/AgentsPersistence.php"){include_once "../persistences/AgentsPersistence.php";}
if(!@include_once "controllers/Controller.php"){include_once "../controllers/Controller.php";}

class AgentsController
{
    const ADD = "Novo Agente";
    const DETAILS = "details";
    const EDIT = "edit";
    const DELETE = "delete";

    function __construct()
    {
    }

    public function save($name, $numSus, $area, $equip, $access)
    {
        if(empty($name) || empty($numSus) || empty($area) || empty($equip)){
            return Controller::EMPTY_VALUES;
        }
        $agent = new AgentModel($name, $numSus, $area, $equip, $access);
        $result = AgentsPersistence::insert($agent);

        if($result){
            return Controller::OK_RESULT;
        }
        return Controller::GENERAL_ERROR;
    }

    public function get($numSus){

        if(empty($numSus)){
            return Controller::EMPTY_VALUES;
        }
        $values = AgentsPersistence::get($numSus);
        if($values){
            return $values;
        }
        return Controller::GENERAL_ERROR;
    }

    public function getAll()
    {
        $agents = array();
        $arrays = AgentsPersistence::getAll();
        foreach ($arrays as $array)
        {
            $agent = new AgentModel($array[AgentModel::NAME],
                $array[AgentModel::NUM_SUS],
                $array[AgentModel::AREA],
                $array[AgentModel::EQUIP],
                $array[AgentModel::ACCESS]);
            array_push($agents, $agent);
        }
        return $agents;
    }

    public function edit($numSus, $area, $equip, $access)
    {
        if(empty($numSus) || empty($area) || empty($equip) || $access === ""){
            return Controller::EMPTY_VALUES;
        }

        $result = AgentsPersistence::get($numSus);
        if(!$result){
            return Controller::INVALID_VALUES;
        }

        $rows = AgentsPersistence::update($result['ID'], $area, $equip, $access);
        if($rows > 0){
            return Controller::OK_RESULT;
        }
        return Controller::GENERAL_ERROR;
    }

    public function delete($numSus)
    {
        if($numSus <= 0){
            return Controller::EMPTY_VALUES;
        }
        $result = $this->get($numSus);

        if($result == Controller::EMPTY_VALUES || $result == Controller::GENERAL_ERROR || $result == Controller::INVALID_VALUES){
            return Controller::INVALID_VALUES;
        }

        $result = AgentsPersistence::delete($result['ID']);
        if($result > 0){
            return Controller::OK_RESULT;
        }
        return Controller::GENERAL_ERROR;
    }

    public function crudButtons($numSus)
    {
        return array(
            self::ADD => "<button name='add' class='btn btn-primary btn-fill' onclick='redirect(\"novo-agente\")'><i class='fa fa-plus-circle'></i>&nbsp;" . self::ADD . "</button>",
            self::DETAILS => "<button name='details' id='details' class='btn btn-block btn-info btn-fill' onclick='details($numSus)'><i class='fa fa-search-plus'></i></button>",
            self::EDIT => "<button name='edit' id='edit' class='btn btn-block btn-warning btn-fill' onclick='edit($numSus)'><i class='fa fa-pencil-square-o'></i></button>",
            self::DELETE => "<button name='delete' id='delete' class='btn btn-block btn-danger btn-fill' onclick='deleteAgent($numSus)'><i class='fa fa-trash-o'></i></button>");
    }
}