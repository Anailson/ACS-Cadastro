<?php
if (!@include_once "persistences/AcsDataBase.php") {
    include_once "../persistences/AcsDataBase.php";
}
if (!@include_once "models/ResidenceModel.class.php") {
    include_once "../models/ResidenceModel.class.php";
}
if (!@include_once "persistences/AgentsPersistence.php") {
    include_once "../persistences/AgentsPersistence.php";
}

class ResidencePersistence
{
    public static function getAllSimpleInfoResidence()
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT S.TYPE, S.NAME, S.NUMBER, CL.CITY, CL.UF, CL.CEP, P.HOME 
                FROM tb_residence AS RD
                INNER JOIN tb_address_data AS AD ON AD.ADDRESS_DATA_ID = RD.ID_ADDRESS_DATA
                INNER JOIN tb_street_location AS S ON S.STREET_LOCATION_ID = AD.ID_STREET_LOCATION
                INNER JOIN tb_city_location AS CL ON CL.CITY_LOCATION_ID = AD.ID_CITY_LOCATION
                INNER JOIN tb_phones AS P ON P.PHONES_ID = AD.ID_PHONES";
        return $db->select($query);
    }

    public static function getOccurrences($disease = false)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT RD.LAT, RD.LNG, S.TYPE, S.NAME, S.NUMBER, CL.NEIGHBORHOOD, CL.CITY, CL.UF, CL.CEP, P.HOME 
                FROM tb_residence AS RD
                INNER JOIN tb_address_data AS AD ON AD.ADDRESS_DATA_ID = RD.ID_ADDRESS_DATA
                INNER JOIN tb_street_location AS S ON S.STREET_LOCATION_ID = AD.ID_STREET_LOCATION
                INNER JOIN tb_city_location AS CL ON CL.CITY_LOCATION_ID = AD.ID_CITY_LOCATION
                INNER JOIN tb_phones AS P ON P.PHONES_ID = AD.ID_PHONES";
        if($disease){

        }
        return $db->select($query);
    }

    public static function getAll($numSus = false)
    {

        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT RD.LAT, RD.LNG, 
                    HC.ELECTRICITY, 
                    HS.SITUATION, HS.LOCATION, HS.OWNERSHIP, 
                    HE.TYPE AS 'HE_TYPE', HE.RESIDENTS, HE.ROOMS, HE.ACCESS, HE.CONSTRUCTION, HE.CONSTRUCTION_TYPE,
                    WS.WATER_SUPPLY, WS.WATER_TREATMENT, WS.BATHROOM,
                    PT.HAS_PET, PT.CAT, PT.DOG, PT.BIRD, PT.CREATION, PT.ANOTHER, PT.PETS,
                    SL.TYPE AS 'SL_TYPE', SL.NAME, SL.NUMBER, SL.COMPLEMENT, 
                    CL.NEIGHBORHOOD, CL.UF, CL.CITY, CL.CEP,
                    PH.HOME, PH.REFERENCE
                FROM tb_residence AS RD
                INNER JOIN tb_housing_conditions AS HC ON HC.HOUSING_CONDITIONS_ID = RD.ID_HOUSING_CONDITIONS
                INNER JOIN tb_housing_situation AS HS ON HS.HOUSING_SITUATION_ID = HC.ID_HOUSING_SITUATION 
                INNER JOIN TB_HOUSE AS HE ON HE.HOUSE_ID = HC.ID_HOUSE
                INNER JOIN tb_water_sanitation AS Ws ON WS.WATER_SANITATION_ID = HC.ID_WATER_SANITATION
                INNER JOIN tb_pet AS PT ON PT.PET_ID = HC.ID_PET
                INNER JOIN tb_address_data AS AD ON AD.ADDRESS_DATA_ID =  RD.ID_ADDRESS_DATA
                INNER JOIN tb_street_location AS SL ON SL.STREET_LOCATION_ID = AD.ID_STREET_LOCATION
                INNER JOIN tb_city_location AS CL ON CL.CITY_LOCATION_ID = AD.ID_CITY_LOCATION
                INNER JOIN tb_phones AS PH ON PH.PHONES_ID = AD.ID_PHONES";

        if($numSus){

            $query .= " " . "WHERE RD.ID_AGENT = (SELECT AG.ID FROM tb_agent AS AG WHERE AG.NUM_SUS = :NUM_SUS)";
            $values = $db->select($query, array(":NUM_SUS" => $numSus));
        } else {
            $values = $db->select($query);
        }


        $residences = array();
        foreach ($values as $value) {
            $housingConditions = self::getHousingConditions($value);
            $addressData = self::getAddressData($value);
            $residences[] = array(HousingConditionsModel::HOUSING_CONDITIONS => $housingConditions, AddressDataModel::ADDRESS_DATA => $addressData);
        }
        return $residences;
    }

    public static function insert($numSus, ResidenceModel $residence)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_RESIDENCE (LAT, LNG, ID_HOUSING_CONDITIONS, ID_ADDRESS_DATA, ID_AGENT) 
              VALUES (:LAT, :LNG, :ID_HOUSING_CONDITIONS, :ID_ADDRESS_DATA, :ID_AGENT)";
        $id = AgentsPersistence::get($numSus)['ID'];
        return $residence->save($id, $db, $query);
    }

    private static function getHousingConditions(array $values)
    {
        $housingSituation = array(HousingSituation::LOCATION => $values[HousingSituation::LOCATION],
            HousingSituation::SITUATION => $values[HousingSituation::SITUATION], HousingSituation::OWNERSHIP => $values[HousingSituation::OWNERSHIP]);
        $house = array(House::ACCESS => $values[House::ACCESS], House::CONSTRUCTION => $values[House::CONSTRUCTION],
            House::CONSTRUCTION_TYPE => $values[House::CONSTRUCTION_TYPE], House::RESIDENTS => $values[House::RESIDENTS],
            House::ROOMS => $values[House::ROOMS], House::TYPE => $values["HE_" . House::TYPE]);
        $waterSanitation = array(WaterAndSanitation::WATER_SUPPLY => $values[WaterAndSanitation::WATER_SUPPLY],
            WaterAndSanitation::WATER_TREATMENT => $values[WaterAndSanitation::WATER_TREATMENT], WaterAndSanitation::BATHROOM => $values[WaterAndSanitation::BATHROOM]);
        $pet = array(Pet::CAT => $values[Pet::CAT], Pet::DOG => $values[Pet::DOG], Pet::BIRD => $values[Pet::BIRD],
            Pet::CREATION => $values[Pet::CREATION], Pet::ANOTHER => $values[Pet::ANOTHER], Pet::HAS_PET => $values[Pet::HAS_PET],
            Pet::PETS => $values[Pet::PETS]);
        return array(HousingConditionsModel::ELECTRICITY => $values[HousingConditionsModel::ELECTRICITY],
            HousingSituation::HOUSING_SITUATION => $housingSituation, House::HOUSE => $house,
            WaterAndSanitation::WATER_SANITATION => $waterSanitation, Pet::PET => $pet);
    }

    private static function getAddressData($values)
    {
        $streetLocation = array(StreetLocation::COMPLEMENT => $values[StreetLocation::COMPLEMENT], StreetLocation::NAME => $values[StreetLocation::NAME],
            StreetLocation::NUMBER => $values[StreetLocation::NUMBER], StreetLocation::TYPE => $values["SL_" . StreetLocation::TYPE]);
        $cityLocation = array(CityLocation::CEP => $values[CityLocation::CEP], CityLocation::CITY => $values[CityLocation::CITY],
            CityLocation::NEIGHBORHOOD => $values[CityLocation::NEIGHBORHOOD], CityLocation::UF => $values[CityLocation::UF]);
        $phones = array(Phones::HOME => $values[Phones::HOME], Phones::REFERENCE => $values[Phones::REFERENCE]);

        return array(StreetLocation::STREET_LOCATION => $streetLocation,
            CityLocation::CITY_LOCATION => $cityLocation, Phones::PHONES => $phones);
    }
}