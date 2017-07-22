<?php

class AccompanyPersistence
{
    static function insert($json)
    {
        $accompanyData = json_decode($json, true);
        $recordData = $accompanyData['RECORD_DATA'];

        $recordDetails = parent::prepareValues($recordData['RECORD_DETAILS']);
        $anthropometric = parent::prepareValues($recordData['ANTHROPOMETRIC']);
        $kidAndPregnant = parent::prepareValues($recordData['KID_PREGNANT']);

        $idRecordData = RecordDataPersistence::insert($recordDetails, $anthropometric, $kidAndPregnant);
        return array("ID_RECORD_DATA" => $idRecordData);
    }

    static function update($record)
    {

    }

    static function delete($record)
    {

    }

    static function get($record)
    {

    }

    static function getAll()
    {

    }
}