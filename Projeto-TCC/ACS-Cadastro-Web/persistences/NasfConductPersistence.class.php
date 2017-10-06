<?php

class NasfConductPersistence
{
    public static function getById(AcsDataBase $db, $id)
    {
        $query = "SELECT 
                FROM 
                INNER JOIN tb_nasf AS NF ON NF.NASF_ID = NC.ID_NASF
                INNER JOIN tb_conduct AS CT ON CT.CONDUCT_ID = NC.NASF_CONDUCT_ID
                INNER JOIN tb_forwarding AS FR ON FR.FORWARDING_ID = NC.ID_FORWARDING
                WHERE NC.NASF_CONDUCT_ID = :NASF_CONDUCT_ID";
        return $db->select($query, array(":NASF_CONDUCT_ID" => $id));
    }

    public static function insert(AcsDataBase $db, NasfConductModel $nasfConduct)
    {
        $values = $nasfConduct->getValuesToBD();
        $ids[":ID_" . Conduct::CONDUCT] = $db->insert(self::insertConduct(), $values[Conduct::CONDUCT]);
        $ids[":ID_" . Forwarding::FORWARDING] = $db->insert(self::insertForwarding(), $values[Forwarding::FORWARDING]);
        $ids[":ID_" . Nasf::NASF] = $db->insert(self::insertNasf(), $values[Nasf::NASF]);
        if(in_array(false, $ids)){
            return false;
        }
        $ids[":" . NasfConductModel::NASF_CONDUCT] = $values[NasfConductModel::NASF_CONDUCT];
        return $db->insert(self::insertNasfConduct(), $ids);
    }

    private static function insertNasf()
    {
        return "INSERT INTO TB_NASF(EVALUATION, PROCEDURES, PRESCRIPTION) 
                VALUES (:EVALUATION, :PROCEDURES, :PRESCRIPTION)";
    }

    private static function insertNasfConduct()
    {
        return "INSERT INTO TB_NASF_CONDUCT(NASF_CONDUCT, ID_NASF, ID_CONDUCT, ID_FORWARDING) 
                VALUES (:NASF_CONDUCT, :ID_NASF, :ID_CONDUCT, :ID_FORWARDING)";
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