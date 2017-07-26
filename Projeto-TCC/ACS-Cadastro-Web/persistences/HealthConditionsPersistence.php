<?php

class HealthConditionsPersistence
{
    public static function getById(AcsDataBase $db, $id)
    {
        return null;
    }

    static function insert(AcsDataBase $db, HealthConditionsModel $healthConditions)
    {
        $query[HealthConditionsModel::HEALTH_CONDITIONS] = self::queryHealthConditions();
        $query[Pregnant::PREGNANT] = self::queryPregnant();
        $query[Diseases::DISEASES] = self::queryDiseases();
        $query[HeartDisease::HEART_DISEASE] = self::queryHearthDisease();
        $query[KidneyDisease::KIDNEY_DISEASE] = self::queryKidneyDisease();
        $query[RespiratoryDisease::RESPIRATORY_DISEASE] = self::queryRespiratoryDisease();
        $query[Interment::INTERMENT] = self::queryInterment();
        $query[Plant::PLANT] = self::queryPlant();

        return $healthConditions->save($db, $query);
    }
    private static function queryHealthConditions()
    {
        return "INSERT INTO TB_HEALTH_CONDITIONS (WEIGHT, ID_PREGNANT, ID_DISEASES, ID_HEART_DISEASE, 
                        ID_KIDNEY_DISEASE, ID_RESPIRATORY_DISEASE, ID_INTERMENT, ID_PLANT) 
                  VALUES (:WEIGHT, :ID_PREGNANT, :ID_DISEASES, :ID_HEART_DISEASE, 
                        :ID_KIDNEY_DISEASE, :ID_RESPIRATORY_DISEASE, :ID_INTERMENT, :ID_PLANT)";
    }

    private static function queryPregnant()
    {
        return "INSERT INTO TB_PREGNANT (PREGNANT, DESCRIPTION)
                  VALUES (:PREGNANT, :DESCRIPTION)";
    }

    private static function queryDiseases()
    {
        return "INSERT INTO TB_DISEASES (SMOKER, ALCOHOL, DRUGS, HYPERTENSION, DIABETES,AVC, HEART_ATTACK, LEPROSY, TUBERCULOSIS, 
                        CANCER, IN_BED, DOMICILED, OTHER_PRACTICES, MENTAL_HEALTH)
                  VALUES (:SMOKER, :ALCOHOL, :DRUGS, :HYPERTENSION, :DIABETES, :AVC, :HEART_ATTACK, :LEPROSY, :TUBERCULOSIS, 
                        :CANCER, :IN_BED, :DOMICILED, :OTHER_PRACTICES, :MENTAL_HEALTH)";
    }

    private static function queryHearthDisease()
    {
        return "INSERT INTO TB_HEART_DISEASE (HEART_DISEASE, INSUFFICIENCY, ANOTHER, DONT_KNOWN)
                  VALUES (:HEART_DISEASE, :INSUFFICIENCY, :ANOTHER, :DONT_KNOWN)";
    }

    private static function queryKidneyDisease()
    {
        return "INSERT INTO TB_KIDNEY_DISEASE (KIDNEY_DISEASE, INSUFFICIENCY, ANOTHER, DONT_KNOWN) 
                  VALUES (:KIDNEY_DISEASE, :INSUFFICIENCY, :ANOTHER, :DONT_KNOWN)";
    }

    private static function queryRespiratoryDisease()
    {
        return "INSERT INTO TB_RESPIRATORY_DISEASE (RESPIRATORY_DISEASE, ASTHMA, EMPHYSEMA, ANOTHER, DONT_KNOWN) 
                  VALUES (:RESPIRATORY_DISEASE, :ASTHMA, :EMPHYSEMA, :ANOTHER, :DONT_KNOWN)";
    }

    private static function queryInterment()
    {
        return "INSERT INTO TB_INTERMENT (INTERMENT, DESCRIPTION)
                  VALUES (:INTERMENT, :DESCRIPTION)";
    }

    private static function queryPlant()
    {
        return "INSERT INTO TB_PLANT (PLANT, DESCRIPTION)
                  VALUES (:PLANT, :DESCRIPTION)";
    }
}