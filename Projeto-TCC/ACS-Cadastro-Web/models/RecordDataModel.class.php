<?php

if(!@include_once "subModels/RecordDetails.class.php") {
    include_once"../subModels/RecordDetails.class.php";
}
if(!@include_once "subModels/Anthropometric.class.php") {
    include_once "../subModels/Anthropometric.class.php";
}
if(!@include_once "subModels/KidAndPregnant.class.php") {
    include_once "../subModels/KidAndPregnant.class.php";
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

    public function getValuesToDB()
    {
        $values[RecordDetails::RECORD_DETAILS] = $this->recordDetails->getValuesToDB();
        $values[Anthropometric::ANTHROPOMETRIC] = $this->anthropometric->getValuesToDB();
        $values[KidAndPregnant::KID_PREGNANT] = $this->kidsPregnant->getValuesToDB();
        return $values;
    }


    public static function getFromArray(array $array)
    {
        return new RecordDataModel(RecordDetails::getFromArray($array[RecordDetails::RECORD_DETAILS]),
            Anthropometric::getFromArray($array[Anthropometric::ANTHROPOMETRIC]),
            KidAndPregnant::getFromArray($array[KidAndPregnant::KID_PREGNANT]));
    }

}