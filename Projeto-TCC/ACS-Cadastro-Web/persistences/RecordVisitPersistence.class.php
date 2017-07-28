<?php


class RecordVisitPersistence
{
    public static function insert(AcsDataBase $db, RecordVisitModel $recordVisit)
    {
        $query = array(RecordVisitModel::RECORD_VISIT => self::insertRecordVisit(),
            RecordDetails::RECORD_DETAILS => self::insertRecordDetails());
        return $recordVisit->save($db, $query);
    }

    private static function insertRecordVisit()
    {
        return "INSERT INTO TB_RECORD_VISIT (IS_SHARED, ID_RECORD_DETAILS) VALUES (:IS_SHARED, :ID_RECORD_DETAILS)";
    }

    private static function insertRecordDetails()
    {
        return "INSERT INTO TB_RECORD_DETAILS (RECORD, SHIFT, PLACE_CARE, TYPE_CARE, ID_CITIZEN) 
            VALUES (:RECORD, :SHIFT, :PLACE_CARE, :TYPE_CARE,  :ID_CITIZEN)";
    }
}