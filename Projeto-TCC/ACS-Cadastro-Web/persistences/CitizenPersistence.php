<?php

class CitizenPersistence
{
    static function insert(CitizenModel $citizen)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_CITIZEN (ID_PERSONAL_DATA, ID_SOCIAL_DEMOGRAPHIC, ID_HEALTH_CONDITIONS, ID_STREET_SITUATION) 
                  VALUES(:ID_PERSONAL_DATA, :ID_SOCIAL_DEMOGRAPHIC, :ID_HEALTH_CONDITIONS, :ID_STREET_SITUATION)";

        return $citizen->save($db, $query);
    }
}