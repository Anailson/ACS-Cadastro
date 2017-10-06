<?php

class RecordDataPersistence
{
    public static function getById(AcsDataBase $db, $id)
    {
        $query = "SELECT 
                FROM TB_RECORD_DATA AS RD
                
                WHERE  = :RECORD_DATA_ID";

        return $db->select($query, array(":RECORD_DATA_ID" => $id));
    }

    static function insert(AcsDataBase $db, RecordDataModel $recordData)
    {
        $values = $recordData->getValuesToDB();
        $ids[":ID_" . RecordDetails::RECORD_DETAILS] = $db->insert(self::queryRecordDetails(), $values[RecordDetails::RECORD_DETAILS]);
        $ids[":ID_" . Anthropometric::ANTHROPOMETRIC] = $db->insert(self::queryAnthropometric(), $values[Anthropometric::ANTHROPOMETRIC]);
        $ids[":ID_" . KidAndPregnant::KID_PREGNANT] = $db->insert(self::queryKidAndPregnant(), $values[KidAndPregnant::KID_PREGNANT]);
        if(in_array(false, $ids)){
            return false;
        }
        return $db->insert(self::queryRecordData(), $ids);
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