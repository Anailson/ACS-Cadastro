<?php
if (!@include "persistences/AcsDataBase.php") {
    include "../persistences/AcsDataBase.php";
}
if (!@include "models/AccompanyModel.class.php") {
    include "../models/AccompanyModel.class.php";
}

class AccompanyPersistence
{
    public static function getAllSimpleInfoAccompany()
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT R.RECORD, P.NUM_SUS, P.NUM_NIS, P.NAME, P.SOCIAL_NAME, P.BIRTH_DATE
                FROM TB_ACCOMPANY AS ACC 
                INNER JOIN tb_record_data as RD on RD.RECORD_DATA_ID = ACC.ID_RECORD_DATA
                INNER JOIN tb_record_details AS R on R.RECORD_DETAILS_ID = RD.RECORD_DATA_ID
                INNER JOIN tb_citizen as CZ on CZ.CITIZEN_ID = R.ID_CITIZEN
                INNER JOIN tb_personal_data as PD on PD.PERSONAL_DATA_ID = CZ.ID_PERSONAL_DATA
                INNER JOIN tb_particular as P on p.PARTICULAR_ID = PD.PERSONAL_DATA_ID";
        return $db->select($query);
    }

    static function insert(AccompanyModel $accompany)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_ACCOMPANY(ID_RECORD_DATA, ID_CONDITIONS, ID_EXAMS, ID_NASF_CONDUCT) 
                  VALUES (:ID_RECORD_DATA, :ID_CONDITIONS, :ID_EXAMS, :ID_NASF_CONDUCT)";
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