<?php

class Plant
{
    const PLANT = "PLANT";
    const DESCRIPTION = "DESCRIPTION";

    private $plant, $description;

    function __construct($plant, $description)
    {
        $this->plant = $plant;
        $this->description = $description;
    }

    public function getValuesToDB()
    {
        return array(":" . self::PLANT => $this->plant, ":" . self::DESCRIPTION => $this->description);
    }

    public static function getFromArray(array $array)
    {
        return new Plant($array[self::PLANT], $array[self::DESCRIPTION]);
    }
}