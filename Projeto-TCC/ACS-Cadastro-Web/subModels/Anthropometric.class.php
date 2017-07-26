<?php

class Anthropometric
{
    const ANTHROPOMETRIC = "ANTHROPOMETRIC";
    const WEIGHT = "WEIGHT";
    const HEIGHT = "HEIGHT";
    const VACCINATES = "VACCINATES";

    private $weight, $height, $vaccinates;

    public function __construct($weight, $height, $vaccinates)
    {
        $this->weight = $weight;
        $this->height = $height;
        $this->vaccinates = $vaccinates;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::WEIGHT => $this->weight,
            ":" . self::HEIGHT => $this->height,
            ":" . self::VACCINATES => $this->vaccinates);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new Anthropometric($array[self::WEIGHT], $array[self::HEIGHT], $array[self::VACCINATES]);
    }

}