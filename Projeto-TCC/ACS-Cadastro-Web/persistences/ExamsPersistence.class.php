<?php

class ExamsPersistence
{
    public static function getById(AcsDataBase $db, $id)
    {
        $query = "SELECT 
                FROM tb_exams AS EX
                
                WHERE  = :EXAMS_ID";
        return $db->select($query, array(":EXAMS_ID" => $id));
    }

    public static function insert(AcsDataBase $db, ExamsModel $exams)
    {
        $values = $exams->getValuesToDB();
        $ids[":ID_" . EvaluatedExams::EVALUATED_EXAMS] = $db->insert(self::insertEvaluatedExams(), $values[EvaluatedExams::EVALUATED_EXAMS]);
        $ids[":ID_" . RequestExams::REQUEST_EXAMS] = $db->insert(self::insertRequestExams(), $values[RequestExams::REQUEST_EXAMS]);
        if(in_array(false, $ids)){
            return false;
        }
        $ids[":" . ExamsModel::PIC] = $values[ExamsModel::PIC] ;
        return $db->insert(self::insertExams(), $ids);
    }

    private static function insertExams()
    {
        return "INSERT INTO TB_EXAMS(PIC, ID_REQUEST_EXAMS, ID_EVALUATED_EXAMS) 
                VALUES (:PIC, :ID_REQUEST_EXAMS, :ID_EVALUATED_EXAMS)";
    }

    private static function insertEvaluatedExams()
    {
        return "INSERT INTO TB_EVALUATED_EXAMS(TOTAL_CHOLESTEROL, CREATINE, EAS_EQU, ELECTROCARDIOGRAM, HEMOGLOBIN, 
                  SPIROMETRY, SPUTUM, GLYCEMIA, HDL, GLYCEMIC_HEMOGLIBIN, BLOOD_COUNT, LDL, EYES, SYPHILIS, DENGUE, HIV, 
                  HUMAN_ANTIGLOBULIN, EAR_TEST, PREGNANCY_TEST, EYE_TEST, FOOT_TEST, ULTRASONOGRAPHY, UROCULTURE) 
                VALUES (:TOTAL_CHOLESTEROL, :CREATINE, :EAS_EQU, :ELECTROCARDIOGRAM, :HEMOGLOBIN, 
                  :SPIROMETRY, :SPUTUM, :GLYCEMIA, :HDL, :GLYCEMIC_HEMOGLIBIN, :BLOOD_COUNT, :LDL, :EYES, :SYPHILIS, :DENGUE, :HIV, 
                  :HUMAN_ANTIGLOBULIN, :EAR_TEST, :PREGNANCY_TEST, :EYE_TEST, :FOOT_TEST, :ULTRASONOGRAPHY, :UROCULTURE)";
    }

    private static function insertRequestExams()
    {
        return "INSERT INTO TB_REQUEST_EXAMS(TOTAL_CHOLESTEROL, CREATINE, EAS_EQU, ELECTROCARDIOGRAM, HEMOGLOBIN, 
                  SPIROMETRY, SPUTUM, GLYCEMIA, HDL, GLYCEMIC_HEMOGLIBIN, BLOOD_COUNT, LDL, EYES, SYPHILIS, DENGUE, HIV, 
                  HUMAN_ANTIGLOBULIN, EAR_TEST, PREGNANCY_TEST, EYE_TEST, FOOT_TEST, ULTRASONOGRAPHY, UROCULTURE) 
                VALUES (:TOTAL_CHOLESTEROL, :CREATINE, :EAS_EQU, :ELECTROCARDIOGRAM, :HEMOGLOBIN, 
                  :SPIROMETRY, :SPUTUM, :GLYCEMIA, :HDL, :GLYCEMIC_HEMOGLIBIN, :BLOOD_COUNT, :LDL, :EYES, :SYPHILIS, :DENGUE, :HIV, 
                  :HUMAN_ANTIGLOBULIN, :EAR_TEST, :PREGNANCY_TEST, :EYE_TEST, :FOOT_TEST, :ULTRASONOGRAPHY, :UROCULTURE)";
    }
}