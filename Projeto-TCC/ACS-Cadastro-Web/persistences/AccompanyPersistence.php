<?php
if (!@include "persistences/AcsDataBase.php") {
    include "../persistences/AcsDataBase.php";
}
if (!@include "persistences/AccompanyModel.class.php") {
    include "../models/AccompanyModel.class.php";
}

class AccompanyPersistence
{
    static function insert(AccompanyModel $accompany)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO `TB_ACCOMPANY`(`ID_RECORD_DATA`, `ID_CONDITIONS_DISEASE`, `ID_EXAMS`, `ID_NASF_CONDUCT`) 
                  VALUES (`:ID_RECORD_DATA`, `:ID_CONDITIONS_DISEASE`, `:ID_EXAMS`, `:ID_NASF_CONDUCT`)";

        return $accompany->save($db, $query);
    }

    static function update($record)
    {

    }

    static function delete($record)
    {

    }

    static function get($record)
    {

    }

    static function getAll()
    {

    }
}