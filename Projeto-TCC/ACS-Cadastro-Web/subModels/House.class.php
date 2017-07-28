<?php

class House
{
    const HOUSE = "HOUSE";
    const TYPE = "TYPE";
    const RESIDENTS = "RESIDENTS";
    const ROOMS = "ROOMS";
    const ACCESS = "ACCESS";
    const CONSTRUCTION = "CONSTRUCTION";
    const CONSTRUCTION_TYPE = "CONSTRUCTION_TYPE";

    private $type, $nResident, $nRoom, $access, $construction, $constructionType;

    public function __construct($type, $nResident, $nRoom, $access, $construction, $constructionType)
    {
        $this->type = $type;
        $this->nResident = $nResident;
        $this->nRoom = $nRoom;
        $this->access = $access;
        $this->construction = $construction;
        $this->constructionType = $constructionType;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::TYPE => $this->type,
            ":" . self::RESIDENTS => $this->nResident,
            ":" . self::ROOMS => $this->nRoom,
            ":" . self::ACCESS => $this->access,
            ":" . self::CONSTRUCTION => $this->construction,
            ":" . self::CONSTRUCTION_TYPE => $this->constructionType);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new House($array[self::TYPE], $array[self::RESIDENTS], $array[self::ROOMS], $array[self::ACCESS],
            $array[self::CONSTRUCTION], $array[self::CONSTRUCTION_TYPE]);
    }
}