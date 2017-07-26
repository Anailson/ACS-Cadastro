<?php
if (!@include "persistences/AcsDataBase.php") {
    include "../persistences/AcsDataBase.php";
}

class AccompanyPersistence
{
    static function insert(AccompanyMoodel $accompay)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);


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