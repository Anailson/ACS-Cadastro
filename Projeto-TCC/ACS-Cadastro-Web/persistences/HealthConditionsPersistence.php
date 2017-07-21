<?php

if (!@include "subModels/Pregnant.class.php") {
    include "../subModels/Pregnant.class.php";
}
if (!@include "subModels/Diseases.class.php") {
    include "../subModels/Diseases.class.php";
}
if (!@include "subModels/HeartDisease.class.php") {
    include "../subModels/HeartDisease.class.php";
}
if (!@include "subModels/KidneyDisease.class.php") {
    include "../subModels/KidneyDisease.class.php";
}
if (!@include "subModels/RespiratoryDisease.class.php") {
    include "../subModels/RespiratoryDisease.class.php";
}
if (!@include "subModels/Interment.class.php") {
    include "../subModels/Interment.class.php";
}
if (!@include "subModels/Plant.class.php") {
    include "../subModels/Plant.class.php";
}

class HealthConditionsPersistence
{
    static function insert(AcsDataBase $db, array $healthConditions)
    {
        $values = array(':WEIGHT' => $healthConditions['WEIGHT']);
        $values[':ID_PREGNANT'] = self::insertPregnant($db, Pregnant::getFromArray($healthConditions['PREGNANT']));
        $values[':ID_DISEASES'] = self::insertDiseases($db, Diseases::getFromArray($healthConditions['DISEASES']));
        $values[':ID_HEART_DISEASE'] = self::insertHearthDisease($db, HeartDisease::getFromArray($healthConditions['HEART_DISEASE']));
        $values[':ID_KIDNEY_DISEASE'] = self::insertKidneyDisease($db, KidneyDisease::getFromArray($healthConditions['KIDNEY_DISEASE']));
        $values[':ID_RESPIRATORY_DISEASE'] = self::insertRespiratoryDisease($db, RespiratoryDisease::getFromArray($healthConditions['RESPIRATORY_DISEASE']));
        $values[':ID_INTERMENT'] = self::insertInterment($db, Interment::getFromArray($healthConditions['INTERMENT']));
        $values[':ID_PLANT'] = self::insertPlant($db, Plant::getFromArray($healthConditions['PLANT']));

        if(in_array(-1, $values)){
            return false;
        }
        $query = "INSERT INTO TB_HEALTH_CONDITIONS (WEIGHT, ID_PREGNANT, ID_DISEASES, ID_HEART_DISEASE, 
                        ID_KIDNEY_DISEASE, ID_RESPIRATORY_DISEASE, ID_INTERMENT, ID_PLANT) 
                  VALUES (:WEIGHT, :ID_PREGNANT, :ID_DISEASES, :ID_HEART_DISEASE, 
                        :ID_KIDNEY_DISEASE, :ID_RESPIRATORY_DISEASE, :ID_INTERMENT, :ID_PLANT)";
        return $db->insert($query, $values);
    }

    private static function insertPregnant(AcsDataBase $db, Pregnant $pregnant)
    {
        $query = "INSERT INTO TB_PREGNANT (PREGNANT, DESCRIPTION)
                  VALUES (:PREGNANT, :DESCRIPTION)";
        return $pregnant->save($db, $query);
    }

    private static function insertDiseases(AcsDataBase $db, Diseases $diseases)
    {
        $query = "INSERT INTO TB_DISEASES (SMOKER, ALCOHOL, DRUGS, HYPERTENSION, DIABETES,AVC, HEART_ATTACK, LEPROSY, TUBERCULOSIS, 
                        CANCER, IN_BED, DOMICILED, OTHER_PRACTICES, MENTAL_HEALTH)
                  VALUES (:SMOKER, :ALCOHOL, :DRUGS, :HYPERTENSION, :DIABETES, :AVC, :HEART_ATTACK, :LEPROSY, :TUBERCULOSIS, 
                        :CANCER, :IN_BED, :DOMICILED, :OTHER_PRACTICES, :MENTAL_HEALTH)";
        return $diseases->save($db, $query);
    }

    private static function insertHearthDisease(AcsDataBase $db, HeartDisease $heartDisease)
    {
        $query = "INSERT INTO TB_HEART_DISEASE (HEART_DISEASE, INSUFFICIENCY, ANOTHER, DONT_KNOWN)
                  VALUES (:HEART_DISEASE, :INSUFFICIENCY, :ANOTHER, :DONT_KNOWN)";
        return $heartDisease->save($db, $query);
    }

    private static function insertKidneyDisease(AcsDataBase $db, KidneyDisease $kidneyDisease)
    {
        $query = "INSERT INTO TB_KIDNEY_DISEASE (KIDNEY_DISEASE, INSUFFICIENCY, ANOTHER, DONT_KNOWN) 
                  VALUES (:KIDNEY_DISEASE, :INSUFFICIENCY, :ANOTHER, :DONT_KNOWN)";
        return $kidneyDisease->save($db, $query);
    }

    private static function insertRespiratoryDisease(AcsDataBase $db, RespiratoryDisease $respiratoryDisease)
    {
        $query = "INSERT INTO TB_RESPIRATORY_DISEASE (RESPIRATORY_DISEASE, ASTHMA, EMPHYSEMA, ANOTHER, DONT_KNOWN) 
                  VALUES (:RESPIRATORY_DISEASE, :ASTHMA, :EMPHYSEMA, :ANOTHER, :DONT_KNOWN)";
        return $respiratoryDisease->save($db, $query);
    }

    private static function insertInterment(AcsDataBase $db, Interment $interment)
    {
        $query = "INSERT INTO TB_INTERMENT (INTERMENT, DESCRIPTION)
                  VALUES (:INTERMENT, :DESCRIPTION)";
        return $interment->save($db, $query);
    }

    private static function insertPlant(AcsDataBase $db, Plant $plant)
    {
        $query = "INSERT INTO TB_PLANT (PLANT, DESCRIPTION)
                  VALUES (:PLANT, :DESCRIPTION)";
        return $plant->save($db, $query);
    }
}