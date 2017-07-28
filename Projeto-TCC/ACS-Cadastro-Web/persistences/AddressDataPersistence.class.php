<?php

class AddressDataPersistence
{
    public static function insert(AcsDataBase $db, AddressDataModel $addressData)
    {
        $query = array(AddressDataModel::ADDRESS_DATA => self::insertAddressData(),
            StreetLocation::STREET_LOCATION => self::insertStreetLocation(),
            CityLocation::CITY_LOCATION => self::insertCityLocation(),
            Phones::PHONES => self::insertPhones());
        return $addressData->save($db, $query);
    }

    private static function insertAddressData()
    {
        return "INSERT INTO TB_ADDRESS_DATA(ID_STREET_LOCATION, ID_CITY_LOCATION, ID_PHONES) 
                VALUES (:ID_STREET_LOCATION, :ID_CITY_LOCATION, :ID_PHONES)";
    }

    private static function insertStreetLocation()
    {
        return "INSERT INTO TB_STREET_LOCATION(TYPE, NAME, NUMBER, COMPLEMENT) 
                VALUES (:TYPE, :NAME, :NUMBER, :COMPLEMENT)";
    }

    private static function insertCityLocation()
    {
        return "INSERT INTO TB_CITY_LOCATION(NEIGHBORHOOD, UF, CITY, CEP) 
                VALUES (:NEIGHBORHOOD, :UF, :CITY, :CEP)";
    }

    private static function insertPhones()
    {
        return "INSERT INTO TB_PHONES (HOME, REFERENCE) VALUES (:HOME, :REFERENCE)";
    }
}