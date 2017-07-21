<?php

class HeartDisease
{
    const HEART_DISEASE = "HEART_DISEASE";
    const INSUFFICIENCY = "INSUFFICIENCY";
    const ANOTHER = "ANOTHER";
    const DONT_KNOWN = "DONT_KNOWN";

    private $hearthDisease, $insufficiency, $another, $dontKnown;

    public function __construct($hearthDisease, $insufficiency, $another, $dontKnown)
    {
        $this->hearthDisease = $hearthDisease;
        $this->insufficiency = $insufficiency;
        $this->another = $another;
        $this->dontKnown = $dontKnown;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::HEART_DISEASE => $this->hearthDisease,
            ":" . self::INSUFFICIENCY => $this->insufficiency,
            ":" . self::ANOTHER => $this->another,
            ":" . self::DONT_KNOWN => $this->dontKnown);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new HeartDisease($array[self::HEART_DISEASE], $array[self::INSUFFICIENCY], $array[self::ANOTHER], $array[self::DONT_KNOWN]);
    }
}