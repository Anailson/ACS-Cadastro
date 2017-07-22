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

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::STREET => $this->streetSituation,
            ":" . self::DESCRIPTION => $this->description);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new StreetSituation($array[self::STREET], $array[self::DESCRIPTION]);
    }

}