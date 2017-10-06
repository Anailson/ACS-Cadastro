<?php

class KidneyDisease
{
    const KIDNEY_DISEASE = "KIDNEY_DISEASE";
    const INSUFFICIENCY = "INSUFFICIENCY";
    const ANOTHER = "ANOTHER";
    const DONT_KNOWN = "DONT_KNOWN";

    private $kidneyDisease, $insufficiency, $another, $dontKnown;

    public function __construct($kidneyDisease, $insufficiency, $another, $dontKnown)
    {
        $this->kidneyDisease = $kidneyDisease;
        $this->insufficiency = $insufficiency;
        $this->another = $another;
        $this->dontKnown = $dontKnown;
    }

    public function getValuesToDB()
    {
        return array(":" . self::KIDNEY_DISEASE => $this->kidneyDisease,
            ":" . self::INSUFFICIENCY => $this->insufficiency,
            ":" . self::ANOTHER => $this->another,
            ":" . self::DONT_KNOWN => $this->dontKnown);
    }

    public static function getFromArray(array $array)
    {
        return new KidneyDisease($array[self::KIDNEY_DISEASE], $array[self::INSUFFICIENCY], $array[self::ANOTHER], $array[self::DONT_KNOWN]);
    }
}