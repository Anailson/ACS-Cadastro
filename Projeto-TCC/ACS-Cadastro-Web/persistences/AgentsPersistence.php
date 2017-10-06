<?php
if (!@include_once "persistences/AcsDataBase.php") {
    include_once "../persistences/AcsDataBase.php";
};

class AgentsPersistence
{
    const TB_AGENT = "TB_AGENT";

    static function insert(AgentModel $agent)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);

        $query = "INSERT INTO " . self::TB_AGENT . "(" . AgentModel::NAME . "," . AgentModel::NUM_SUS . "," . AgentModel::AREA . "," . AgentModel::EQUIP . ", " . AgentModel::ACCESS . " )
                VALUES (:" . AgentModel::NAME . ",:" . AgentModel::NUM_SUS . ",:" . AgentModel::AREA . ",:" . AgentModel::EQUIP . ",:". AgentModel::ACCESS .")";
        $values = array(AgentModel::NAME => $agent->getName(),
            AgentModel::NUM_SUS => $agent->getNumSus(),
            AgentModel::AREA => $agent->getArea(),
            AgentModel::EQUIP => $agent->getEquip(),
            AgentModel::ACCESS => $agent->getAccess());
        var_dump($values);
        return $db->insert($query, $values);
    }

    static function getAll()
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT A.NAME, A.NUM_SUS, A.AREA, A.EQUIP, A.ACCESS FROM TB_AGENT AS A";
        return $db->select($query);
    }

    static function get($numSus)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT A.ID, A.NAME, A.NUM_SUS, A.AREA, A.EQUIP, A.ACCESS FROM TB_AGENT AS A WHERE A.NUM_SUS = :NUM_SUS";
        $array = $db->select($query, array(AgentModel::NUM_SUS => $numSus));

        if (sizeof($array) == 0) {
            return false;
        }
        return $array[0];
    }

    static function update($id, $area, $equip, $access)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "UPDATE TB_AGENT SET AREA={$area}, EQUIP={$equip}, ACCESS={$access} WHERE ID={$id}";
        return $db->update($query);
    }

    public static function delete($id)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "DELETE FROM TB_AGENT WHERE ID = {$id}";
        return $db->delete($query);
    }
}