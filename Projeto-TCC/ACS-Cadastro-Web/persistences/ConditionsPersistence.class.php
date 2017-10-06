<?php

class ConditionsPersistence
{
    public static function getById(AcsDataBase $db, $id)
    {
        $query = "SELECT CD.ASTHMA, CD.MALNUTRITION, CD.DIABETES, CD.DIABETES, CD.DPOC, CD.HYPERTENSION, CD.OBESITY, 
                        CD.PRENATAL, CD.CHILD_CARE, CD.PUERPERIUM, CD.SEXUAL_HEALTH, 
                        CD.SMOKING, CD.ALCOHOL, CD.DRUGS, CD.MENTAL_HEALTH, CD.REHABILITATION,
                        CM.TUBERCULOSIS, CM.LEPROSY, CM.DENGUE, CM.DST, 
                        TD.CERVICAL_CANCER, TD.BREAST_CANCER, TD.CARDIOVASCULAR
                    FROM tb_conditions AS CN
                    
                    WHERE  = :CONDITIONS_DISEASE_ID ";
        return $db->select($query, array(":CONDITIONS_DISEASE_ID" => $id ));
    }

    public static function insert(AcsDataBase $db, ConditionsModel $conditionsDisease)
    {
        $values = $conditionsDisease->getValuesToDB();
        $ids[":ID_" . CommunicableDiseases::COMMUNICABLE_DISEASES] = $db->insert(self::queryCommunicable(), $values[CommunicableDiseases::COMMUNICABLE_DISEASES]);
        $ids[":ID_" . TrackingDiseases::TRACKING_DISEASES] = $db->insert(self::queryTracking(), $values[TrackingDiseases::TRACKING_DISEASES]);
        $ids[":ID_" . ConditionsDiseases::CONDITIONS_DISEASES] = $db->insert(self::insertConditionsDisease(), $values[ConditionsDiseases::CONDITIONS_DISEASES]);
        if(in_array(false, $ids)){
            return false;
        }
        return $db->insert(self::queryConditions(), $ids);
    }

    private static function insertConditionsDisease()
    {
        return "INSERT INTO TB_CONDITIONS_DISEASE(ASTHMA, MALNUTRITION, DIABETES, DPOC, HYPERTENSION, OBESITY, PRENATAL, CHILD_CARE, 
                PUERPERIUM, SEXUAL_HEALTH, SMOKING, ALCOHOL, DRUGS, MENTAL_HEALTH, REHABILITATION) 
        VALUES (:ASTHMA, :MALNUTRITION, :DIABETES, :DPOC, :HYPERTENSION, :OBESITY, :PRENATAL, :CHILD_CARE, 
                :PUERPERIUM, :SEXUAL_HEALTH, :SMOKING, :ALCOHOL, :DRUGS, :MENTAL_HEALTH, :REHABILITATION)";
    }

    private static function queryConditions()
    {
        return "INSERT INTO TB_CONDITIONS(ID_CONDITIONS, ID_COMMUNICABLE, ID_TRACKING) 
                VALUES (:ID_CONDITIONS_DISEASES, :ID_COMMUNICABLE_DISEASES, :ID_TRACKING_DISEASES)";
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