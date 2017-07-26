<?php

if(!@include "subModels/RecordDetails.class.php") {
    include "../subModels/RecordDetails.class.php";
}

if(!@include "subModels/Anthropometric.class.php") {
    include "../subModels/Anthropometric.class.php";
}
if(!@include "subModels/KidAndPregnant.class.php") {
    include "../subModels/KidAndPregnant.class.php";
}

class RecordDataModel
{

    const RECORD_DATA = "RECORD_DATA";
    private $recordDetails, $anthropometric, $kidsPregnant;

    public function __construct(RecordDetails $recordDetails, Anthropometric $anthropometric, KidAndPregnant $kidsPregnant)
    {
        $this->recordDetails = $recordDetails;
        $this->anthropometric = $anthropometric;
        $this->kidsPregnant = $kidsPregnant;
    }

    public function save(AcsDataBase $db, $querys)
    {
        $params = array(":ID_" . RecordDetails::RECORD_DETAILS => $this->recordDetails->save($db, $querys[RecordDetails::RECORD_DETAILS]),
            ":ID_" . Anthropometric::ANTHROPOMETRIC => $this->anthropometric->save($db, $querys[Anthropometric::ANTHROPOMETRIC]),
            ":ID_" . KidAndPregnant::KID_PREGNANT => $this->kidsPregnant->save($db, $querys[KidAndPregnant::KID_PREGNANT]));
        if(in_array(false, $params)){
            return false;
        }
        return $db->insert($querys[self::RECORD_DATA], $params);
    }

}