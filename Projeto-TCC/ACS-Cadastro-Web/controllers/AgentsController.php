<?php
include "models/AgentModel.php";
include "persistences/AgentsPersistence.php";

class AgentsController
{
    const ADD = "Novo Agente Comunitário";
    const DETAILS = "Detalhes";
    const EDIT = "Editar";
    const DELETE = "Remover";

    private $agents;

    function __construct()
    {
        $this->agents = array();
    }

    public function getAll()
    {
        $arrays = AgentsPersistence::getAll();
        foreach ($arrays as $array)
        {
            $agent = new AgentModel($array[AgentsPersistence::NAME],
                $array[AgentsPersistence::NUM_SUS],
                $array[AgentsPersistence::AREA],
                $array[AgentsPersistence::EQUIP]);
            array_push($this->agents, $agent);
        }
        return $this->agents;
    }

    public function crudButtons($numSus)
    {
        return array(
            self::ADD => "<button type='button' class='btn btn-primary btn-fill'><i class='fa fa-plus-circle'></i>&nbsp;".self::ADD."</button>",
            self::DETAILS => "<button type='button' class='btn btn-info btn-fill'><i class='fa fa-search-plus'></i>&nbsp;". self::DETAILS ."</button>",
            self::EDIT => "<button type='button' class='btn btn-warning btn-fill'><i class='fa fa-pencil-square-o'></i>&nbsp;". self::EDIT ."</button>",
            self::DELETE => "<button type='button' class='btn btn-danger btn-fill'><i class='fa fa-trash-o'></i>&nbsp;". self::DELETE ."</button>",
        );


    }
}