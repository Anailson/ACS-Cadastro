<?php

class Pregnant
{
    const PREGNANT = "PREGNANT";
    const DESCRIPTION = "DESCRIPTION";

    private $pregnant, $description;

    function __construct($pregnant, $description)
    {
        $this->pregnant = $pregnant;
        $this->description = $description;
    }

    public function getValuesToDB()
    {
        return array(":" . self::PREGNANT => $this->pregnant, ':' . self::DESCRIPTION => $this->description);
    }

    public static function getFromArray(array $array)
    {
        return new Pregnant($array[self::PREGNANT], $array[self::DESCRIPTION]);
    }
}