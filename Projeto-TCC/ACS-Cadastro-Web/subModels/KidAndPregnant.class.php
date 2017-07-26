<?php

class KidAndPregnant
{
    const KID_PREGNANT = "KID_PREGNANT";
    const BREAST_FEEDING = "BREAST_FEEDING";
    const DUM = "DUM";
    const PLANNED_PREGNANCY = "PLANNED_PREGNANCY";
    const WEEKS = "WEEKS";
    const PREVIOUS = "PREVIOUS";
    const CHILD_BIRTH = "CHILD_BIRTH";
    const HOME_CARE = "HOME_CARE";

    private $breastFeeding, $dum, $plannedPregnancy, $weeks, $previous, $childBirth, $homeCare;

    public function __construct($breastFeeding, $dum, $plannedPregnancy, $weeks, $previous, $childBirth, $homeCare)
    {
        $this->breastFeeding = $breastFeeding;
        $this->dum = $dum;
        $this->plannedPregnancy = $plannedPregnancy;
        $this->weeks = $weeks;
        $this->previous = $previous;
        $this->childBirth = $childBirth;
        $this->homeCare = $homeCare;
    }

    public function save(AcsDataBase $db, $query){
        $params = array(":" . self::BREAST_FEEDING => $this->breastFeeding,
            ":" . self::DUM => $this->dum,
            ":" . self::PLANNED_PREGNANCY => $this->plannedPregnancy,
            ":" . self::WEEKS => $this->weeks,
            ":" . self::PREVIOUS => $this->previous,
            ":" . self::CHILD_BIRTH => $this->childBirth,
            ":" . self::HOME_CARE => $this->homeCare);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new KidAndPregnant($array[self::BREAST_FEEDING], $array[self::DUM], $array[self::PLANNED_PREGNANCY],
            $array[self::WEEKS], $array[self::PREVIOUS], $array[self::CHILD_BIRTH], $array[self::HOME_CARE]);
    }

}