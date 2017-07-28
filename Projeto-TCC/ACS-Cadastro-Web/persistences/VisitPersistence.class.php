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

    public static function getAllSimpleVisitInfo()
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT R.RECORD, P.NUM_SUS, P.NUM_NIS, P.NAME, P.SOCIAL_NAME, P.BIRTH_DATE 
                FROM TB_VISIT AS VT 
                INNER JOIN tb_record_visit AS RV ON RV.RECORD_VISIT_ID = VT.ID_RECORD_VISIT 
                INNER JOIN tb_record_details AS R ON R.RECORD_DETAILS_ID = RV.ID_RECORD_DETAILS 
                INNER JOIN tb_citizen AS CT ON CT.CITIZEN_ID = R.ID_CITIZEN 
                INNER JOIN tb_personal_data AS PD ON PD.PERSONAL_DATA_ID = CT.ID_PERSONAL_DATA 
                INNER JOIN tb_particular AS P ON P.PARTICULAR_ID = PD.ID_PARTICULAR";
        return $db->select($query);
    }
}