<?php

class StreetSituation
{
    const STREET = "STREET";
    const DESCRIPTION = "DESCRIPTION";

    private $streetSituation, $description;

    public function __construct($streetSituation, $description)
    {
        $this->streetSituation = $streetSituation;
        $this->description = $description;
    }

    public function getValuesToDB()
    {
        return array(":" . self::STREET => $this->streetSituation, ":" . self::DESCRIPTION => $this->description);
    }

    public static function getFromArray(array $array)
    {
        return new StreetSituation($array[self::STREET], $array[self::DESCRIPTION]);
    }

}