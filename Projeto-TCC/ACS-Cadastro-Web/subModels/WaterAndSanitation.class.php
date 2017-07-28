<?php

class WaterAndSanitation
{
    const WATER_SANITATION = "WATER_SANITATION";
    const WATER_SUPPLY = "WATER_SUPPLY";
    const WATER_TREATMENT = "WATER_TREATMENT";
    const BATHROOM = "BATHROOM";

    private $waterSupply, $waterTreatment, $bathroom;

    public function __construct($waterSupply, $waterTreatment, $bathroom)
    {
        $this->waterSupply = $waterSupply;
        $this->waterTreatment = $waterTreatment;
        $this->bathroom = $bathroom;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::WATER_SUPPLY => $this->waterSupply,
            ":" . self::WATER_TREATMENT => $this->waterTreatment,
            ":" . self::BATHROOM => $this->bathroom);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new WaterAndSanitation($array[self::WATER_SUPPLY], $array[self::WATER_TREATMENT], $array[self::BATHROOM]);
    }
}