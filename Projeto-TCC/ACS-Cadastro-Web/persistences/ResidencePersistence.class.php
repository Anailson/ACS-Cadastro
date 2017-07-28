<?php

class ResidencePersistence
{
    public static function insert(ResidenceModel $residence)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_RESIDENCE (LAT, LON, ID_HOUSING_CONDITIONS, ID_ADDRESS_DATA) 
              VALUES (:LAT, :LON, :ID_HOUSING_CONDITIONS, :ID_ADDRESS_DATA)";
        return $residence->save($db, $query);
    }
}