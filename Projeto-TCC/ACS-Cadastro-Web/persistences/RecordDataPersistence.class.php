<?php

class RecordDataPersistence
{

    static function insert(AcsDataBase $db, RecordDataModel $recordData)
    {
        $query = array(RecordDataModel::RECORD_DATA => self::queryRecordData(),
            RecordDetails::RECORD_DETAILS => self::queryRecordDetails(),
            Anthropometric::ANTHROPOMETRIC => self::queryAnthropometric(),
            KidAndPregnant::KID_PREGNANT => self::queryKidAndPregnant());
        return $recordData->save($db, $query);
    }

    private static function queryRecordData()
    {
        return "INSERT INTO TB_RECORD_DATA (ID_RECORD_DETAILS, ID_ANTHROPOMETRIC, ID_KID_PREGNANT)
                  VALUES(:ID_RECORD_DETAILS, :ID_ANTHROPOMETRIC, :ID_KID_PREGNANT)";
    }

    private static function queryRecordDetails()
    {
        return "INSERT INTO  TB_RECORD_DETAILS (RECORD, PLACE_CARE, TYPE_CARE, SHIFT, ID_CITIZEN)
                  VALUES (:RECORD, :PLACE_CARE, :TYPE_CARE, :SHIFT, :ID_CITIZEN)";
    }

    private static function queryAnthropometric()
    {
        return "INSERT INTO TB_ANTROPOMETRIC (WEIGHT, HEIGHT, VACCINATES)
                  VALUES (:WEIGHT, :HEIGHT, :VACCINATES)";
    }

    private static function queryKidAndPregnant()
    {
        return "INSERT INTO TB_KID_PREGNANT (BREAST_FEEDING, DUM, PLANNED_PREGNANCY, WEEKS, PREVIOUS, CHILD_BIRTH, HOME_CARE)
                  VALUES (:BREAST_FEEDING, :DUM, :PLANNED_PREGNANCY, :WEEKS, :PREVIOUS, :CHILD_BIRTH, :HOME_CARE)";
    }
}