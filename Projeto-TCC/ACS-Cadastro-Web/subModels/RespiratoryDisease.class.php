<?php

class RespiratoryDisease
{

    const RESPIRATORY_DISEASE = "RESPIRATORY_DISEASE";
    const ASTHMA = "ASTHMA";
    const EMPHYSEMA = "EMPHYSEMA";
    const ANOTHER = "ANOTHER";
    const DONT_KNOWN = "DONT_KNOWN";

    private $respiratoryDisease, $asthma, $emphysema,  $another, $dontKnown;

    public function __construct($respiratoryDisease, $asthma, $emphysema, $another, $dontKnown)
    {
        $this->respiratoryDisease = $respiratoryDisease;
        $this->asthma = $asthma;
        $this->emphysema = $emphysema;
        $this->another = $another;
        $this->dontKnown = $dontKnown;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::RESPIRATORY_DISEASE => $this->respiratoryDisease,
            ":" . self::ASTHMA => $this->asthma,
            ":" . self::EMPHYSEMA => $this->emphysema,
            ":" . self::ANOTHER => $this->another,
            ":" . self::DONT_KNOWN => $this->dontKnown);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new RespiratoryDisease($array[self::RESPIRATORY_DISEASE], $array[self::ASTHMA], $array[self::EMPHYSEMA],
            $array[self::ANOTHER], $array[self::DONT_KNOWN]);
    }

}