<?php
if (!@include_once "persistences/AcsDataBase.php") {
    include_once "../persistences/AcsDataBase.php";
}
if (!@include_once "models/ResidenceModel.class.php") {
    include_once "../models/ResidenceModel.class.php";
}

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