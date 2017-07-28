<?php

if(!@include "subModels/StreetLocation.class.php") {
    include "../subModels/StreetLocation.class.php";
}
if(!@include "subModels/CityLocation.class.php"){
    include "../subModels/CityLocation.class.php";
}
if(!@include "subModels/Phones.class.php"){
    include "../subModels/Phones.class.php";
}
class AddressDataModel
{
    const ADDRESS_DATA = "ADDRESS_DATA";
    private $streetLocation, $cityLocation, $phones;

    public function __construct(StreetLocation $streetLocation, CityLocation $cityLocation, Phones $phones)
    {
        $this->streetLocation = $streetLocation;
        $this->cityLocation = $cityLocation;
        $this->phones = $phones;
    }

    public function save(AcsDataBase $db, array $query)
    {
        $params = array(":ID_" . StreetLocation::STREET_LOCATION => $this->streetLocation->save($db, $query[StreetLocation::STREET_LOCATION ]),
            ":ID_" . CityLocation::CITY_LOCATION => $this->cityLocation->save($db, $query[CityLocation::CITY_LOCATION]),
            ":ID_" . Phones::PHONES => $this->phones->save($db, $query[Phones::PHONES]));
        if(in_array(false, $params)){
            return false;
        }
        return $db->insert($query[self::ADDRESS_DATA], $params);
    }

    public static function getFromArray(array $array)
    {
        return new AddressDataModel(StreetLocation::getFromArray($array[StreetLocation::STREET_LOCATION]),
            CityLocation::getFromArray($array[CityLocation::CITY_LOCATION]), Phones::getFromArray($array[Phones::PHONES]));
    }
}