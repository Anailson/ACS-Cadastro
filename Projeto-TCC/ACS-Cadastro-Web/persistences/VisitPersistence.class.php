<?php

if (!@include "models/VisitModel.class.php") {
    include "../models/VisitModel.class.php";
}
if (!@include_once "persistences/AcsDataBase.php") {
    include_once "../persistences/AcsDataBase.php";
}

class VisitPersistence
{
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

    public static function getAll($numSus = false)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT 
                    RD.IS_SHARED,
                    RS.RESULT,
                    AE.APPOINTMENT, AE.EXAM, AE.VACCINE, AE.CONDITIONS,
                    FW.PREGNANT, FW.PUERPERA, FW.NEW_BORN, FW.CHILD, FW.MALNUTRITION, FW.REHABILITATION, FW.HYPERTENSION, FW.DIABETES,
                    FW.ASTHMA, FW.COPD_EMPHYSEMA, FW.CANCER, FW.CHRONIC_DISEASE, FW.LEPROSY, FW.TUBERCULOSIS, FW.RESPIRATORY, 
                    FW.SMOKER, FW.HOME_BEDDING, FW.VULNERABILITY, FW.BOLSA_FAMILIA, FW.MENTAL_HEALTH, FW.ALCOHOL, FW.DRUGS,
                    AR.RECORD_UPDATE, AR.PERIODIC_VISIT, AR.INTERMENT, AR.CONTROL_ENVIRONMENTS, AR.COLLECTIVE_ACTIVITIES, AR.GUIDANCE, AR.OTHERS,
                    D.RECORD, D.PLACE_CARE, D.TYPE_CARE, D.SHIFT, P.NUM_SUS
                FROM tb_visit AS VT
                INNER JOIN tb_record_visit AS RD ON RD.RECORD_VISIT_ID = VT.ID_RECORD_VISIT
                INNER JOIN tb_record_details AS D ON D.RECORD_DETAILS_ID = RD.ID_RECORD_DETAILS
                
                INNER JOIN tb_citizen AS CT ON CT.CITIZEN_ID = D.ID_CITIZEN
                INNER JOIN tb_personal_data AS PD ON PD.PERSONAL_DATA_ID = CT.ID_PERSONAL_DATA
                INNER JOIN tb_particular AS P ON P.PARTICULAR_ID = PD.ID_PARTICULAR
                INNER JOIN tb_reasons_visit AS RS ON RS.REASONS_VISIT_ID =  VT.ID_REASONS_VISIT
                INNER JOIN tb_active_search AS AE ON AE.ACTIVE_SEARCH_ID = RS.ID_ACTIVE_SEARCH
                INNER JOIN tb_following AS FW ON FW.FOLLOWING_ID =  RS.ID_FOLLOWING
                INNER JOIN tb_another_reasons AS AR ON AR.ANOTHER_REASONS_ID = RS.ID_ANOTHER_REASONS";

        if ($numSus) {
            $query .= " WHERE VT.ID_AGENT = (SELECT AG.ID FROM tb_agent AS AG WHERE AG.NUM_SUS = :NUM_SUS)";
            $values = $db->select($query, array(":NUM_SUS" => $numSus));
        } else {
            $values = $db->select($query);
        }

        $visits = array();
        foreach ($values as $value) {
            $visits[] = array(RecordVisitModel::RECORD_VISIT => self::getRecordVisit($value),
                ReasonsVisitModel::REASONS_VISIT => self::getReasonsVisit($value));
        }
        return $visits;
    }

    public static function insert(VisitModel $visit)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_VISIT (ID_RECORD_VISIT, ID_REASONS_VISIT) VALUES (:ID_RECORD_VISIT, :ID_REASONS_VISIT)";
        return $visit->save($db, $query);
    }


    private static function getRecordVisit(array $value)
    {
        $recordDetails = array(RecordDetails::RECORD => $value[RecordDetails::RECORD], RecordDetails::PLACE_CARE => $value[RecordDetails::PLACE_CARE],
            RecordDetails::TYPE_CARE => $value[RecordDetails::TYPE_CARE], RecordDetails::SHIFT => $value[RecordDetails::SHIFT],
            RecordDetails::NUM_SUS => $value[RecordDetails::NUM_SUS]);
        return array(RecordVisitModel::IS_SHARED => $value[RecordVisitModel::IS_SHARED], RecordDetails::RECORD_DETAILS => $recordDetails);
    }

    private static function getReasonsVisit(array $value)
    {

        $activeSearch = array(ActiveSearch::APPOINTMENT => $value[ActiveSearch::APPOINTMENT], ActiveSearch::EXAM => $value[ActiveSearch::EXAM],
            ActiveSearch::VACCINE => $value[ActiveSearch::VACCINE], ActiveSearch::CONDITIONS => $value[ActiveSearch::CONDITIONS]);
        $following = array(Following::PREGNANT => $value[Following::PREGNANT], Following::PUERPERA => $value[Following::PUERPERA],
            Following::NEW_BORN => $value[Following::NEW_BORN], Following::CHILD => $value[Following::CHILD],
            Following::MALNUTRITION => $value[Following::MALNUTRITION], Following::REHABILITATION => $value[Following::REHABILITATION],
            Following::HYPERTENSION => $value[Following::HYPERTENSION], Following::DIABETES => $value[Following::DIABETES],
            Following::ASTHMA => $value[Following::ASTHMA], Following::COPD_EMPHYSEMA => $value[Following::COPD_EMPHYSEMA],
            Following::CANCER => $value[Following::CANCER], Following::CHRONIC_DISEASE => $value[Following::CHRONIC_DISEASE],
            Following::LEPROSY => $value[Following::LEPROSY], Following::TUBERCULOSIS => $value[Following::TUBERCULOSIS],
            Following::RESPIRATORY => $value[Following::RESPIRATORY], Following::SMOKER => $value[Following::SMOKER],
            Following::HOME_BEDDING => $value[Following::HOME_BEDDING], Following::VULNERABILITY => $value[Following::VULNERABILITY],
            Following::BOLSA_FAMILIA => $value[Following::BOLSA_FAMILIA], Following::MENTAL_HEALTH => $value[Following::MENTAL_HEALTH],
            Following::ALCOHOL => $value[Following::ALCOHOL], Following::DRUGS => $value[Following::DRUGS]);
        $anotherReason = array(AnotherReasons::RECORD_UPDATE => $value[AnotherReasons::RECORD_UPDATE], AnotherReasons::PERIODIC_VISIT => $value[AnotherReasons::PERIODIC_VISIT],
            AnotherReasons::INTERMENT => $value[AnotherReasons::INTERMENT], AnotherReasons::CONTROL_ENVIRONMENTS => $value[AnotherReasons::CONTROL_ENVIRONMENTS],
            AnotherReasons::OTHERS => $value[AnotherReasons::OTHERS], AnotherReasons::GUIDANCE => $value[AnotherReasons::GUIDANCE],
            AnotherReasons::COLLECTIVE_ACTIVITIES => $value[AnotherReasons::COLLECTIVE_ACTIVITIES]);
        

        return array(ReasonsVisitModel::RESULT => $value[ReasonsVisitModel::RESULT], ActiveSearch::ACTIVE_SEARCH =>$activeSearch,
            Following::FOLLOWING => $following, AnotherReasons::ANOTHER_REASONS => $anotherReason);
    }
}