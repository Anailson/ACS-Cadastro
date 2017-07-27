<?php

class NasfConductPersistence
{
    
    public static function insert(AcsDataBase $db, NasfConductModel $nasfConduct)
    {
        $query = array(Nasf::NASF => self::insertNasf(),
            Conduct::CONDUCT=>self::insertConduct(),
            Forwarding::FORWARDING => self::insertForwarding(),
            NasfConductModel::NASF_CONDUCT => self::insertNasfConduct());
        return $nasfConduct->save($db, $query);
    }

    private static function insertNasfConduct()
    {
        return "INSERT INTO TB_NASF_CONDUCT(NASF_CONDUCT, ID_NASF, ID_CONDUCT, ID_FORWARDING) 
                VALUES (:NASF_CONDUCT, :ID_NASF, :ID_CONDUCT, :ID_FORWARDING)";
    }

    private static function insertNasf()
    {
        return "INSERT INTO TB_NASF(EVALUATION, PROCEDURES, PRESCRIPTION) 
                VALUES (:EVALUATION, :PROCEDURES, :PRESCRIPTION)";
    }

    private static function insertConduct()
    {
        return "INSERT INTO TB_CONDUCT(SCHEDULE_APPOINTMENT, SCHEDULE_CARE, GROUPS_SCHEDULE, NASF_SCHEDULE, RELEASES) 
                VALUES (:SCHEDULE_APPOINTMENT, :SCHEDULE_CARE, :GROUPS_SCHEDULE, :NASF_SCHEDULE, :RELEASES)";
    }

    private static function insertForwarding()
    {
        return "INSERT INTO TB_FORWARDING(IN_DAY, SPECIALIZED_SERVICE, CAPS, HOSPITALIZATION, URGENCY, HOME_CARE_SERVICE, INTER_SECTORAL) 
                VALUES (:IN_DAY, :SPECIALIZED_SERVICE, :CAPS, :HOSPITALIZATION, :URGENCY, :HOME_CARE_SERVICE, :INTER_SECTORAL)";
    }

}