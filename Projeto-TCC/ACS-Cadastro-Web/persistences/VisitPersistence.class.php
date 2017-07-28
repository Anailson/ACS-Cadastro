<?php

if (!@include_once "persistences/AcsDataBase.php") {
    include_once "../persistences/AcsDataBase.php";
}
if (!@include "models/VisitModel.class.php") {
    include "../models/VisitModel.class.php";
}

class VisitPersistence
{
    public static function insert(VisitModel $visit)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_VISIT (ID_RECORD_VISIT, ID_REASONS_VISIT) VALUES (:ID_RECORD_VISIT, :ID_REASONS_VISIT)";
        return $visit->save($db, $query);
    }
}