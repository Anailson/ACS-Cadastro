<?php

class RecordDataPersistence
{

    static function insert($recordDetails, $anthropometric, $kidAndPregnant)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);;
        $idDetails = self::insertRecordDetails($db, $recordDetails);
        $idAnthropometric = self::insertAnthropometric($db, $anthropometric);
        $idKidAndPregnant = self::insertKidAndPregnant($db, $kidAndPregnant);

        $query = "INSERT INTO TB_RECORD_DATA (ID_RECORD_DETAILS, ID_ANTROPOMETRIC, ID_KID_PREGNANT)
                  VALUES(:ID_RECORD_DETAILS, :ID_ANTROPOMETRIC, :ID_KID_PREGNANT)";
        $array = array(":ID_RECORD_DETAILS" => $idDetails,
                        ":ID_ANTHROPOMETRIC" => $idAnthropometric,
                        ":ID_KID_PREGNANT" => $idKidAndPregnant);
        $id = $db->insert($query, $array);

        if(!$id){
            echo "Erro ao inserir o registro";
            die();
        }

        return $id;
    }

    private static function insertRecordDetails($db, $recordDetails)
    {
        //$numSus = $recordDetails[':NUM_SUS'];
        //$idCitizen = AgentsPersistence::get($numSus);
        $idCitizen = 200;
        $recordDetails[":ID_CITIZEN"] = $idCitizen  ;
        unset($recordDetails[':NUM_SUS']);

        $query = "INSERT INTO  TB_RECORD_DETAILS (RECORD, PLACE_CARE, TYPE_CARE, SHIFT, ID_CITIZEN)
                  VALUES (:RECORD, :PLACE_CARE, :TYPE_CARE, :SHIFT, :ID_CITIZEN)";

        $id = $db->insert($query, $recordDetails);

        if(!$id){
            echo "Erro ao inseriro registro";
            die();
        }
        return $id;
    }

    private static function insertAnthropometric($db, $anthropometric)
    {
        $query = "INSERT INTO TB_ANTROPOMETRIC (WEIGHT, HEIGHT, VACCINATES)
                  VALUES (:WEIGHT, :HEIGHT, :VACCINATES)";

        var_dump($anthropometric); echo "<br><br>";

        $id = $db->insert($query, $anthropometric);
        if(!$id){
            echo "Erro ao inseriro registro";
            die();
        }
        return $id;
    }

    private static function insertKidAndPregnant($db, $kidAndPregnant)
    {
        $query = "INSERT INTO TB_KID_PREGNANT (BREAST_FEEDING, DUM, PLANNED_PREGNANCY, WEEKS, PREVIOUS, CHILD_BIRTH, HOME_CARE)
                  VALUES (:BREAST_FEEDING, :DUM, :PLANNED_PREGNANCY, :WEEKS, :PREVIOUS, :CHILD_BIRTH, :HOME_CARE)";

        var_dump($kidAndPregnant); echo "<br><br>";

        $id = $db->insert($query, $kidAndPregnant);
        if(!$id){
            echo "Erro ao inseriro registro";
            die();
        }
        return $id;
    }
}