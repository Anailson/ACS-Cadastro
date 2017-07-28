<?php
if (!@include_once "persistences/AcsDataBase.php") {
    include_once "../persistences/AcsDataBase.php";
}
if (!@include "models/CitizenModel.class.php") {
    include "../models/CitizenModel.class.php";
}
//if (!@include "persistences/PersonalDataPersistence.php") {include "../persistences/PersonalDataPersistence.php";}
//if (!@include_once "models/CitizenModel.class.php") {include_once "../models/CitizenModel.class.php";}

class CitizenPersistence
{

    public static function getId($numSus)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT CZ.CITIZEN_ID
              FROM TB_CITIZEN AS CZ 
              INNER JOIN TB_PERSONAL_DATA AS PD ON CZ.ID_PERSONAL_DATA = PD.PERSONAL_DATA_ID
              INNER JOIN TB_PARTICULAR AS P ON PD.ID_PARTICULAR = P.PARTICULAR_ID 
              WHERE P.NUM_SUS = :NUM_SUS
              LIMIT 1";
        return $db->select($query, array(":NUM_SUS" => $numSus));
    }

    public static function getParticularData($numSus)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT P.NUM_SUS, P.NUM_NIS, P.NAME, P.SOCIAL_NAME, P.BIRTH_DATE
              FROM TB_CITIZEN AS CZ 
              INNER JOIN TB_PERSONAL_DATA AS PD ON CZ.ID_PERSONAL_DATA = PD.PERSONAL_DATA_ID
              INNER JOIN TB_PARTICULAR AS P ON PD.ID_PARTICULAR = P.PARTICULAR_ID 
              WHERE P.NUM_SUS = :NUM_SUS
              LIMIT 1";
        return $db->select($query, array(":NUM_SUS" => $numSus));
    }

    public static function getAllParticularInfo()
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT P.NUM_SUS, P.NAME, P.BIRTH_DATE,
                        C.PHONE, C.EMAIL
                FROM TB_CITIZEN AS CZ 
                INNER JOIN TB_PERSONAL_DATA AS PD ON CZ.ID_PERSONAL_DATA = PD.PERSONAL_DATA_ID
                INNER JOIN TB_PARTICULAR AS P ON PD.ID_PARTICULAR = P.PARTICULAR_ID
                INNER JOIN TB_CONTACT AS C ON PD.ID_CONTACT = C.CONTACT_ID";
        return $db->select($query);
    }

    public static function getAll()
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT C.ID_PERSONAL_DATA, C.ID_SOCIAL_DEMOGRAPHIC, C.ID_HEALTH_CONDITIONS, C.ID_STREET_SITUATION
                  FROM TB_CITIZEN AS C";
        $ids = $db->select($query);

        $citizenValues = array();
        foreach ($ids as $id) {

            $personalValues = PersonalDataPersistence::getById($db, $id["ID_" . PersonalDataModel::PERSONAL_DATA]);
            $socialValues = SocialDemographicPersistence::getById($db, $id["ID_" . SocialDemographicModel::SOCIAL_DEMOGRAPHIC]);
            $healthValues = null;
            $streetValues = null;

            array_push($citizenValues, array(PersonalDataModel::PERSONAL_DATA => $personalValues,
                SocialDemographicModel::SOCIAL_DEMOGRAPHIC => $socialValues,
                HealthConditionsModel::HEALTH_CONDITIONS => $healthValues,
                StreetSituationModel::STREET_SITUATION => $streetValues));

        }
        return $citizenValues;
    }
    static function insert(CitizenModel $citizen)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_CITIZEN (ID_PERSONAL_DATA, ID_SOCIAL_DEMOGRAPHIC, ID_HEALTH_CONDITIONS, ID_STREET_SITUATION) 
                  VALUES(:ID_PERSONAL_DATA, :ID_SOCIAL_DEMOGRAPHIC, :ID_HEALTH_CONDITIONS, :ID_STREET_SITUATION)";

        return $citizen->save($db, $query);
    }

    static function insertAll()
    {

    }
}