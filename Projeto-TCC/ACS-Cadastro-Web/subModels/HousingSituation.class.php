<?php

class HousingSituation
{
    const HOUSING_SITUATION = "HOUSING_SITUATION";
    const SITUATION = "SITUATION";
    const LOCATION = "LOCATION";
    const OWNERSHIP = "OWNERSHIP";

    private $situation, $location, $ownership;

    public function __construct($situation, $location, $ownership)
    {
        $this->situation = $situation;
        $this->location = $location;
        $this->ownership = $ownership;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::SITUATION => $this->situation,
            ":" . self::LOCATION => $this->location,
            ":" . self::OWNERSHIP => $this->ownership);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new HousingSituation($array[self::SITUATION], $array[self::LOCATION], $array[self::OWNERSHIP]);
    }

}