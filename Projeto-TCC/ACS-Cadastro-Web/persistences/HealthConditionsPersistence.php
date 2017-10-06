<?php

class HealthConditionsPersistence
{
    public static function getById(AcsDataBase $db, $id)
    {
        $query = "SELECT HC.WEIGHT,
                P.PREGNANT, P.DESCRIPTION AS PREGNANT_DESCRIPTION,
                D.SMOKER, D.ALCOHOL, D.DRUGS, D.DIABETES, D.AVC, D.HEART_ATTACK, D.LEPROSY, D.TUBERCULOSIS, D.CANCER, D.IN_BED,
                D.DOMICILED, D.OTHER_PRACTICES, D.MENTAL_HEALTH, D.HYPERTENSION,
                H.HEART_DISEASE, H.INSUFFICIENCY AS HEART_DISEASE_INSUFFICIENCY, H.ANOTHER AS HEART_DISEASE_ANOTHER, 
                H.DONT_KNOWN AS HEART_DISEASE_DONT_KNOWN,
                K.KIDNEY_DISEASE, K.INSUFFICIENCY AS KIDNEY_DISEASE_INSUFFICIENCY, K.ANOTHER AS KIDNEY_DISEASE_ANOTHER, 
                K.DONT_KNOWN AS KIDNEY_DISEASE_DONT_KNOWN,
                R.RESPIRATORY_DISEASE, R.ASTHMA, R.EMPHYSEMA, R.ANOTHER AS RESPIRATORY_DISEASE_ANOTHER, 
                R.DONT_KNOWN AS RESPIRATORY_DISEASE_DONT_KNOWN,
                I.INTERMENT, I.DESCRIPTION AS INTERMENT_DESCRIPTION,
                T.PLANT, T.DESCRIPTION AS PLANT_DESCRIPTION
            FROM tb_health_conditions AS HC
            INNER JOIN tb_pregnant AS P ON P.PREGNANT_ID = HC.ID_PREGNANT
            INNER JOIN tb_diseases AS D ON D.DISEASES_ID = HC.ID_DISEASES
            INNER JOIN tb_heart_disease AS H ON H.HEART_DISEASE_ID = HC.ID_HEART_DISEASE
            INNER JOIN tb_kidney_disease AS K ON K.KIDNEY_DISEASE_ID = HC.ID_KIDNEY_DISEASE
            INNER JOIN tb_respiratory_disease AS R ON R.RESPIRATORY_DISEASE_ID = HC.ID_RESPIRATORY_DISEASE
            INNER JOIN tb_interment AS I ON I.INTERMENT_ID = HC.ID_INTERMENT
            INNER JOIN tb_plant AS T ON T.PLANT_ID = HC.ID_PLANT
            WHERE HC.HEALTH_CONDITIONS_ID = :HEALTH_CONDITIONS_ID";
        return $db->select($query, array(":HEALTH_CONDITIONS_ID" => $id));
    }

    static function insert(AcsDataBase $db, HealthConditionsModel $healthConditions)
    {
        $values = $healthConditions->getValuesToDB();

        $ids[":ID_" . Pregnant::PREGNANT] = $db->insert(self::queryPregnant(), $values[Pregnant::PREGNANT]);
        $ids[":ID_" . Diseases::DISEASES] = $db->insert(self::queryDiseases(), $values[Diseases::DISEASES]);
        $ids[":ID_" . HeartDisease::HEART_DISEASE] = $db->insert(self::queryHearthDisease(), $values[HeartDisease::HEART_DISEASE]);
        $ids[":ID_" . KidneyDisease::KIDNEY_DISEASE] = $db->insert(self::queryKidneyDisease(), $values[KidneyDisease::KIDNEY_DISEASE]);
        $ids[":ID_" . RespiratoryDisease::RESPIRATORY_DISEASE] = $db->insert(self::queryRespiratoryDisease(), $values[RespiratoryDisease::RESPIRATORY_DISEASE]);
        $ids[":ID_" . Interment::INTERMENT] = $db->insert(self::queryInterment(), $values[Interment::INTERMENT]);
        $ids[":ID_" . Plant::PLANT] = $db->insert(self::queryPlant(), $values[Plant::PLANT]);

        if(in_array(false, $ids)){
            return false;
        }
        $ids[":" . HealthConditionsModel::WEIGHT] = $values[HealthConditionsModel::WEIGHT];
        return $db->insert(self::queryHealthConditions(), $ids);
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