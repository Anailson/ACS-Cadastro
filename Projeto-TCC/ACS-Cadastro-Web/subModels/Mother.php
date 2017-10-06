<?php

class Mother
{
    const MOTHER = "MOTHER";
    const KNOWN = "KNOWN";
    const NAME = "NAME";

    private $known, $name;

    function __construct($known, $name)
    {
        $this->known = $known;
        $this->name = $name;
    }

    public static function getFromArray($array)
    {
        return new Mother($array[self::KNOWN], $array[self::NAME]);
    }

    public function getValuesToDB()
    {
        return array(":" . self::KNOWN => $this->known, ":" . self::NAME => $this->name);
    }
}