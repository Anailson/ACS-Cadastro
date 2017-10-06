<?php
if(!@include "persistences/CitizenPersistence.php") {
    include "../persistences/CitizenPersistence.php";
}
class RecordDetails
{
    const RECORD_DETAILS = "RECORD_DETAILS";
    const RECORD = "RECORD";
    const PLACE_CARE = "PLACE_CARE";
    const TYPE_CARE = "TYPE_CARE";
    const SHIFT = "SHIFT";
    const NUM_SUS = "NUM_SUS";

    private $record, $placeCare, $typeCare, $shift, $numSusCitizen;

    public function __construct($record, $placeCare, $typeCare, $shift, $numSusCitizen)
    {
        $this->record = $record;
        $this->placeCare = $placeCare;
        $this->typeCare = $typeCare;
        $this->shift = $shift;
        $this->numSusCitizen = $numSusCitizen;
    }

    public function getValuesToDB()
    {
        $id = CitizenPersistence::getId($this->numSusCitizen)[0][0];

        return array(":" . self::RECORD => $this->record,
            ":" . self::PLACE_CARE => $this->placeCare,
            ":" . self::TYPE_CARE => $this->typeCare,
            ":" . self::SHIFT => $this->shift,
            ":ID_CITIZEN" => $id);
    }

    public static function getFromArray(array $array)
    {
        return new RecordDetails($array[self::RECORD],
            $array[self::PLACE_CARE],
            $array[self::TYPE_CARE],
            $array[self::SHIFT],
            $array[self::NUM_SUS]);
    }

}