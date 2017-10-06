<?php

class AgentModel
{
    const NAME = "NAME";
    const NUM_SUS = "NUM_SUS";
    const AREA = "AREA";
    const EQUIP = "EQUIP";
    const REMEMBER = "REMEMBER";
    const ADMINISTRATOR = array(0, "Administrador");
    const AGENT = array(1, "Agente de saúde");
    const ACCESS = "ACCESS";

    private $name;
    private $numSus;
    private $area;
    private $equip;
    private $access;

    function __construct($name, $numSus, $area, $equip, $access)
    {
        $this->name = $name;
        $this->numSus = $numSus;
        $this->area = $area;
        $this->equip = $equip;
        $this->access = $access;
    }

    public function getName()
    {
        return $this->name;
    }

    public function getNumSus()
    {
        return $this->numSus;
    }

    public function getArea()
    {
        return $this->area;
    }

    public function getEquip()
    {
        return $this->equip;
    }

    public function getAccess()
    {
        return $this->access;
    }

    public function getAccessAsText()
    {
        switch ($this->access){
            case 0: return self::ADMINISTRATOR[1];
            case 1: return self::AGENT[1];
            default: return "---";
        }
    }
}