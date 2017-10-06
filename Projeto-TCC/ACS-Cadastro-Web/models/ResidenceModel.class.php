<?php

if (!@include "models/HousingConditionsModel.class.php") {
    include "../models/HousingConditionsModel.class.php";
}
if (!@include "models/AddressDataModel.class.php") {
    include "../models/AddressDataModel.class.php";
}
if (!@include "persistences/HousingConditionsPersistence.class.php") {
    include "../persistences/HousingConditionsPersistence.class.php";
}
if (!@include "persistences/AddressDataPersistence.class.php") {
    include "../persistences/AddressDataPersistence.class.php";
}

class ResidenceModel
{
    const RESIDENCE = "RESIDENCE";
    const LAT = "LAT";
    const LNG = "LNG";

    private $lat, $lng, $housingConditions, $addressData;

    public function __construct(AddressDataModel $addressData, HousingConditionsModel $housingConditions)
    {
        $this->lat = 0;
        $this->lng = 0;
        $this->housingConditions = $housingConditions;
        $this->addressData = $addressData;
    }

    public function save($id, AcsDataBase $db, $query)
    {
        $ids[":ID_" . HousingConditionsModel::HOUSING_CONDITIONS] = HousingPersistence::insert($db, $this->housingConditions);
        $ids[":ID_" . AddressDataModel::ADDRESS_DATA] = AddressDataPersistence::insert($db, $this->addressData);
        if(in_array(false, $ids)){
            return false;
        }
        $ids[":ID_AGENT"] = $id;
        $ids[":" . self::LAT] = $this->lat;
        $ids[":" . self::LNG] = $this->lng;
        return $db->insert($query, $ids);
    }

    public static function getFromArray(array $array)
    {
        $residence = new ResidenceModel(AddressDataModel::getFromArray($array[AddressDataModel::ADDRESS_DATA]),
            HousingConditionsModel::getFromArray($array[HousingConditionsModel::HOUSING_CONDITIONS]));
        $residence->lat = $array[self::LAT];
        $residence->lng = $array[self::LNG];
        return $residence;
    }
}