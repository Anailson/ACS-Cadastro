<?php

class VisitPersistence
{
    public static function insert(VisitModel $visit)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_VISIT (ID_RECORD_VISIT, ID_REASONS_VISIT) VALUES (:ID_RECORD_VISIT, :ID_REASONS_VISIT)";
        return $visit->save($db, $query);
    }
}