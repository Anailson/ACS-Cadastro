<?php

class ReasonsVisitPersistence
{
    public static function insert(AcsDataBase $db, ReasonsVisitModel $reasonsVisit)
    {
        $query = array(ReasonsVisitModel::REASONS_VISIT => self::insertReasonsVisit(),
            ActiveSearch::ACTIVE_SEARCH => self::insertActiveSearch(),
            Following::FOLLOWING => self::insertFollowing(),
            AnotherReasons::ANOTHER_REASONS => self::insertAnotherReasons());
        return $reasonsVisit->save($db, $query);
    }

    private static function insertReasonsVisit()
    {
        return "INSERT INTO TB_REASONS_VISIT (RESULT, ID_ACTIVE_SEARCH, ID_FOLLOWING, ID_ANOTHER_REASONS) 
                VALUES (:RESULT, :ID_ACTIVE_SEARCH, :ID_FOLLOWING, :ID_ANOTHER_REASONS)";
    }

    private static function insertActiveSearch()
    {
        return "INSERT INTO TB_ACTIVE_SEARCH (APPOINTMENT, EXAM, VACCINE, CONDITIONS) 
                VALUES (:APPOINTMENT, :EXAM, :VACCINE, :CONDITIONS)";
    }

    private static function insertFollowing()
    {
        return "INSERT INTO TB_FOLLOWING (PREGNANT, PUERPERA, NEW_BORN, CHILD, MALNUTRITION, REHABILITATION, HYPERTENSION,
                    DIABETES, ASTHMA, COPD_EMPHYSEMA, CANCER, CHRONIC_DISEASE, LEPROSY, TUBERCULOSIS, RESPIRATORY, SMOKER, 
                    HOME_BEDDING, VULNERABILITY, BOLSA_FAMILIA, MENTAL_HEALTH, ALCOHOL, DRUGS) 
                VALUES (:PREGNANT, :PUERPERA, :NEW_BORN, :CHILD, :MALNUTRITION, :REHABILITATION, :HYPERTENSION,
                    :DIABETES, :ASTHMA, :COPD_EMPHYSEMA, :CANCER, :CHRONIC_DISEASE, :LEPROSY, :TUBERCULOSIS, :RESPIRATORY, :SMOKER, 
                    :HOME_BEDDING, :VULNERABILITY, :BOLSA_FAMILIA, :MENTAL_HEALTH, :ALCOHOL, :DRUGS)";
    }

    private static function insertAnotherReasons()
    {
        return "INSERT INTO TB_ANOTHER_REASONS(RECORD_UPDATE, PERIODIC_VISIT, INTERMENT, CONTROL_ENVIRONMENTS, 
                  COLLECTIVE_ACTIVITIES, GUIDANCE, OTHERS)
               VALUES (:RECORD_UPDATE, :PERIODIC_VISIT, :INTERMENT, :CONTROL_ENVIRONMENTS, 
                  :COLLECTIVE_ACTIVITIES, :GUIDANCE, :OTHERS)";
    }
}