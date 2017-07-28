<?php

if (!@include "models/HousingConditionsModel.class.php") {
    include "../models/HousingConditionsModel.class.php";
}
if (!@include "models/AddressDataModel.class.php") {
    include "../models/AddressDataModel.class.php";
}
if (!@include "persistences/ResidencePersistence.class.php") {
    include "../persistences/ResidencePersistence.class.php";
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
    const LON = "LONG";

    private $lat, $long, $housingConditions, $addressData;

    public function __construct($lat, $long, HousingConditionsModel $housingConditions, AddressDataModel $addressData)
    {
        $this->lat = $lat;
        $this->long = $long;
        $this->housingConditions = $housingConditions;
        $this->addressData = $addressData;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":ID_" . HousingConditionsModel::HOUSING_CONDITIONS => HousingPersistence::insert($db, $this->housingConditions),
            ":ID_" . AddressDataModel::ADDRESS_DATA => AddressDataPersistence::insert($db, $this->addressData));
        if(in_array(false, $params)){
            return false;
        }
        $params[self::LAT] = $this->lat;
        $params[self::LON] = $this->long;
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new ResidenceModel($array[self::LAT], $array[self::LON],
            HousingConditionsModel::getFromArray($array[HousingConditionsModel::HOUSING_CONDITIONS]),
            AddressDataModel::getFromArray($array[AddressDataModel::ADDRESS_DATA]));
    }
}