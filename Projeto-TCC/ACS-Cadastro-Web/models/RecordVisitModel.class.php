<?php
if(!@include "subModels/RecordDetails.class.php") {
    include "../subModels/RecordDetails.class.php";
}

class RecordVisitModel
{
    const IS_SHARED = "IS_SHARED";
    const RECORD_VISIT = "RECORD_VISIT";

    private $isShared, $recordDetails;

    public function __construct($isShared, RecordDetails $recordDetails)
    {
        $this->isShared = $isShared;
        $this->recordDetails = $recordDetails;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":ID_" . RecordDetails::RECORD_DETAILS => $this->recordDetails->save($db, $query[RecordDetails::RECORD_DETAILS]));

        if(in_array(false, $params)){
            return false;
        }
        $params[":" . self::IS_SHARED] = $this->isShared;
        return $db->insert($query[self::RECORD_VISIT], $params);
    }

    public static function getFromArray(array $array)
    {
        $array[RecordDetails::RECORD_DETAILS][RecordDetails::PLACE_CARE] = "- - -";
        $array[RecordDetails::RECORD_DETAILS][RecordDetails::TYPE_CARE] = "- - -";
        return new RecordVisitModel($array[self::IS_SHARED],
            RecordDetails::getFromArray($array[RecordDetails::RECORD_DETAILS]));
    }
}