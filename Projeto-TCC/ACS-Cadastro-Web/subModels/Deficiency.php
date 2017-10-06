<?php

class Deficiency
{
    const DEFICIENCY = "DEFICIENCY";
    const HEARING = "HEARING";
    const VISUAL = "VISUAL";
    const INTELLECTUAL = "INTELLECTUAL";
    const PHYSICAL = "PHYSICAL";
    const ANOTHER = "ANOTHER";

    private $deficiency, $hearing, $visual, $intellectual, $physical, $another;

    function __construct($deficiency, $hearing, $visual, $intellectual, $physical, $another)
    {
        $this->deficiency = $deficiency;
        $this->hearing = $hearing;
        $this->visual = $visual;
        $this->intellectual = $intellectual;
        $this->physical = $physical;
        $this->another = $another;
    }

    public function getValuesToDB()
    {
        return array(":" . self::DEFICIENCY => $this->deficiency,
            ":" . self::HEARING=> $this->hearing,
            ":" . self::VISUAL => $this->visual,
            ":" . self::INTELLECTUAL => $this->intellectual,
            ":" . self::PHYSICAL => $this->physical,
            ":" . self::ANOTHER => $this->another);
    }

    public static function getFromArray(array $array)
    {
        return new Deficiency($array[self::DEFICIENCY], $array[self::HEARING], $array[self::VISUAL],
            $array[self::INTELLECTUAL], $array[self::PHYSICAL], $array[self::ANOTHER]);
    }
}