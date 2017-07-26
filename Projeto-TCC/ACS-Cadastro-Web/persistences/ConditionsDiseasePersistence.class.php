<?php

class ConditionsDiseasePersistence
{

    public static function insert(AcsDataBase $db, ConditionsDiseaseModel $conditionsDisease)
    {
        $query = array(Conditions::CONDITIONS => self::queryConditions(),
            Communicable::COMMUNICABLE => self::queryCommunicable(),
            Tracking::TRACKING => self::queryTracking(),
            ConditionsDiseaseModel::CONDITIONS_DISEASE => self::insertConditionsDisease());
        return $conditionsDisease->save($db, $query);
    }

    private static function insertConditionsDisease()
    {
        return "INSERT INTO TB_CONDITIONS_DISEASE_ID(ID_CONDITIONS, ID_COMMUNICABLE, ID_TRACKING) 
                VALUES (ID_CONDITIONS, ID_COMMUNICABLE, ID_TRACKING)";
    }

    private static function queryConditions()
    {
        return "INSERT INTO TB_CONDITIONS(ASTHMA, MALNUTRITION, DIABETES, DPOC, HYPERTENSION, OBESITY, PRENATAL, CHILD_CARE, 
                PUERPERIUM, SEXUAL_HEALTH, SMOKING, ALCOHOL, DRUGS, MENTAL_HEALTH, REHABILITATION) 
        VALUES (:ASTHMA, :MALNUTRITION, :DIABETES, :DPOC, :HYPERTENSION, :OBESITY, :PRENATAL, :CHILD_CARE, 
                :PUERPERIUM, :SEXUAL_HEALTH, :SMOKING, :ALCOHOL, :DRUGS, :MENTAL_HEALTH, :REHABILITATION)";
    }


    private static function queryCommunicable()
    {
        return "INSERT INTO TB_COMMUNICABLE(TUBERCULOSIS, LEPROSY, DENGUE, DST) 
                VALUES (:TUBERCULOSIS, :LEPROSY, :DENGUE, :DST)";
    }

    private static function queryTracking()
    {
        return "INSERT INTO TB_TRACKING(CERVICAL_CANCER, BREAST_CANCER, CARDIOVASCULA) 
              VALUES (:CERVICAL_CANCER, :BREAST_CANCER, :CARDIOVASCULA)";
    }
}