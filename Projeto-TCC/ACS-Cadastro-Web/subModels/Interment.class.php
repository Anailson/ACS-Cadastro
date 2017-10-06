<?php

class Interment
{
    const INTERMENT = "INTERMENT";
    const DESCRIPTION = "DESCRIPTION";

    private $interment, $description;

    function __construct($interment, $description)
    {
        $this->interment = $interment;
        $this->description = $description;
    }

    public function getValuesToDB()
    {
        return array(":" . self::INTERMENT => $this->interment,
            ":" . self::DESCRIPTION => $this->description);
    }

    public static function getFromArray(array $array)
    {
        return new Interment($array[self::INTERMENT], $array[self::DESCRIPTION]);
    }
}