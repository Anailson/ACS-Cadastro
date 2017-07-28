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
}