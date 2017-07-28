<?php
if(!@include "subModels/HousingSituation.class.php") {
    include "../subModels/HousingSituation.class.php";
}
if(!@include "subModels/House.class.php"){
    include "../subModels/House.class.php";
}
if(!@include "subModels/WaterAndSanitation.class.php"){
    include "../subModels/WaterAndSanitation.class.php";
}
if(!@include "subModels/Pet.class.php"){
    include "../subModels/Pet.class.php";
}

class HousingConditionsModel
{
    const HOUSING_CONDITIONS = "HOUSING_CONDITIONS";
    const ELECTRICITY = "ELECTRICITY";

    private $housing, $house, $electricity, $waterSanitation, $pet;

    public function __construct(HousingSituation $housing, House $house, $electricity, WaterAndSanitation $waterSanitation, Pet $pet)
    {
        $this->housing = $housing;
        $this->house = $house;
        $this->electricity = $electricity;
        $this->waterSanitation = $waterSanitation;
        $this->pet = $pet;
    }

    public function save(AcsDataBase $db, array $query)
    {
        $params = array(":ID_" . HousingSituation::HOUSING_SITUATION => $this->housing->save($db, $query[HousingSituation::HOUSING_SITUATION]),
            ":ID_" . House::HOUSE => $this->house->save($db, $query[House::HOUSE]),
            ":ID_" . WaterAndSanitation::WATER_SANITATION => $this->waterSanitation->save($db, $query[WaterAndSanitation::WATER_SANITATION]),
            ":ID_" . Pet::PET => $this->pet->save($db, $query[Pet::PET]));
        if(in_array(false, $params)){
            return false;
        }
        $params[":" . self::ELECTRICITY] = $this->electricity;
        return $db->insert($query[self::HOUSING_CONDITIONS], $params);
    }

    public static function getFromArray(array $array)
    {
        return new HousingConditionsModel(HousingSituation::getFromArray($array[HousingSituation::HOUSING_SITUATION]),
            House::getFromArray($array[House::HOUSE]), $array[self::ELECTRICITY],
            WaterAndSanitation::getFromArray($array[WaterAndSanitation::WATER_SANITATION]), Pet::getFromArray($array[Pet::PET]));
    }
}