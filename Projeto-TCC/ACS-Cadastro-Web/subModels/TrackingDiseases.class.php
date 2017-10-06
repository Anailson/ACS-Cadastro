<?php

class TrackingDiseases
{
    const TRACKING_DISEASES = "TRACKING_DISEASES";
    const CERVICAL_CANCER = "CERVICAL_CANCER";
    const BREAST_CANCER = "BREAST_CANCER";
    const CARDIOVASCULAR = "CARDIOVASCULAR";

    private $cervicalCancer, $breastCancer, $cardiovascular;

    public function __construct($cervicalCancer, $breastCancer, $cardiovascular)
    {
        $this->cervicalCancer = $cervicalCancer;
        $this->breastCancer = $breastCancer;
        $this->cardiovascular = $cardiovascular;
    }

    public function getValuesToDB()
    {
        return array(":" . self::CERVICAL_CANCER => $this->cervicalCancer,
            ":" . self::BREAST_CANCER => $this->breastCancer,
            ":" . self::CARDIOVASCULAR => $this->cardiovascular);
    }

    public static function getFromArray(array $array)
    {
        return new TrackingDiseases($array[self::CERVICAL_CANCER], $array[self::BREAST_CANCER], $array[self::CARDIOVASCULAR]);
    }

}