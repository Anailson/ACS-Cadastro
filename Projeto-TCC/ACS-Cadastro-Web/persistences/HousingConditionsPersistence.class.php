<?php

class HousingPersistence
{

    public static function insert(AcsDataBase $db, HousingConditionsModel $housingConditions)
    {
        $query = array(HousingConditionsModel::HOUSING_CONDITIONS => self::insertHousingConditions(),
            HousingSituation::HOUSING_SITUATION => self::insertHousingSituation(),
            House::HOUSE => self::insertHouse(),
            WaterAndSanitation::WATER_SANITATION => self::insertWaterSanitation(),
            Pet::PET => self::insertPet());
        return $housingConditions->save($db, $query);
    }

    private static function insertHousingConditions()
    {
        return "INSERT INTO TB_HOUSING_CONDITIONS(ELECTRICITY, ID_HOUSING_SITUATION, ID_HOUSE, ID_WATER_SANITATION, ID_PET) 
                VALUES (:ELECTRICITY, :ID_HOUSING_SITUATION, :ID_HOUSE, :ID_WATER_SANITATION, :ID_PET)";
    }

    private static function insertHousingSituation()
    {
        return "INSERT INTO TB_HOUSING_SITUATION(SITUATION, LOCATION, OWNERSHIP) 
                VALUES (:SITUATION, :LOCATION, :OWNERSHIP)";
    }

    private static function insertHouse()
    {
        return "INSERT INTO TB_HOUSE (TYPE, RESIDENTS, ROOMS, ACCESS, CONSTRUCTION, CONSTRUCTION_TYPE)
                VALUES (:TYPE, :RESIDENTS, :ROOMS, :ACCESS, :CONSTRUCTION, :CONSTRUCTION_TYPE)";
    }

    private static function insertWaterSanitation()
    {
        return "INSERT INTO TB_WATER_SANITATION (WATER_SUPPLY, WATER_TREATMENT, BATHROOM)
                VALUES (:WATER_SUPPLY, :WATER_TREATMENT, :BATHROOM)";
    }

    private static function insertPet()
    {
        return "INSERT INTO TB_PET (HAS_PET, CAT, DOG, BIRD, CREATION, ANOTHER, PETS)
                VALUES (:HAS_PET, :CAT, :DOG, :BIRD, :CREATION, :ANOTHER, :PETS)";
    }
}