<?php
if (!@include "persistences/AcsDataBase.php") {
    include "../persistences/AcsDataBase.php";
};

class AgentsPersistence
{
    const NAME = "NAME";
    const NUM_SUS = "NUM_SUS";
    const AREA = "AREA";
    const EQUIP = "EQUIP";

    static function getAll()
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT A.NAME, A.NUM_SUS, A.AREA, A.EQUIP FROM TB_AGENT AS A";
        return $db->select($query);
    }

    static function get($numSus)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT A.NAME, A.NUM_SUS, A.AREA, A.EQUIP FROM TB_AGENT AS A WHERE A.NUM_SUS = :NUM_SUS";
        $array = $db->select($query, array(self::NUM_SUS => $numSus));

        if (sizeof($array) > 0) {
            return $array[0];
        }
        return false;
    }

    static function insert($array)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_AGENT (" . self::NAME . "," . self::NUM_SUS . "," . self::AREA . "," . self::EQUIP . ")
                VALUES (:" . self::NAME . ",:" . self::NUM_SUS . ",:" . self::AREA . ",:" . self::EQUIP . ")";
        return $db->insert($query, $array);
    }
}