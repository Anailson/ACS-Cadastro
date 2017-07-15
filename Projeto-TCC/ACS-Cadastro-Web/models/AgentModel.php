<?php

class AgentModel
{
    private $name;
    private $numSus;
    private $area;
    private $equip;

    function __construct($name, $numSus, $area, $equip)
    {
        $this->name = $name;
        $this->numSus = $numSus;
        $this->area = $area;
        $this->equip = $equip;
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
}