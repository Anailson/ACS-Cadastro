<?php

class ConditionsPersistence
{

    public static function insert(AcsDataBase $db, ConditionsModel $conditionsDisease)
    {
        $query = array(ConditionsDiseases::CONDITIONS_DISEASES => self::queryConditions(),
            CommunicableDiseases::COMMUNICABLE_DISEASES => self::queryCommunicable(),
            TrackingDiseases::TRACKING_DISEASES => self::queryTracking(),
            ConditionsModel::CONDITIONS => self::insertConditionsDisease());
        return $conditionsDisease->save($db, $query);
    }

    private static function insertConditionsDisease()
    {
        return "INSERT INTO TB_CONDITIONS(ID_CONDITIONS, ID_COMMUNICABLE, ID_TRACKING) 
                VALUES (:ID_CONDITIONS_DISEASES, :ID_COMMUNICABLE_DISEASES, :ID_TRACKING_DISEASES)";
    }

    private static function queryConditions()
    {
        return "INSERT INTO TB_CONDITIONS_DISEASE(ASTHMA, MALNUTRITION, DIABETES, DPOC, HYPERTENSION, OBESITY, PRENATAL, CHILD_CARE, 
                PUERPERIUM, SEXUAL_HEALTH, SMOKING, ALCOHOL, DRUGS, MENTAL_HEALTH, REHABILITATION) 
        VALUES (:ASTHMA, :MALNUTRITION, :DIABETES, :DPOC, :HYPERTENSION, :OBESITY, :PRENATAL, :CHILD_CARE, 
                :PUERPERIUM, :SEXUAL_HEALTH, :SMOKING, :ALCOHOL, :DRUGS, :MENTAL_HEALTH, :REHABILITATION)";
    }


    private static function queryCommunicable()
    {
        return "INSERT INTO TB_COMMUNICABLE_DISEASES(TUBERCULOSIS, LEPROSY, DENGUE, DST) 
                VALUES (:TUBERCULOSIS, :LEPROSY, :DENGUE, :DST)";
    }

    private static function queryTracking()
    {
        return "INSERT INTO TB_TRACKING_DISEASES (CERVICAL_CANCER, BREAST_CANCER, CARDIOVASCULAR) 
              VALUES (:CERVICAL_CANCER, :BREAST_CANCER, :CARDIOVASCULAR)";
    }
}