<?php

class Responsible
{
    const RESPONSIBLE = "RESPONSIBLE";
    const NUM_SUS = "NUM_SUS";
    const BIRTH_DATE = "BIRTH_DATE";

    private $responsible, $numSus, $birthDate;

    function __construct($responsible, $numSus, $birthDate)
    {
        $this->responsible = $responsible;
        $this->numSus = $numSus;
        $this->birthDate = $birthDate;
    }

    public function getValuesToDB()
    {
        return array(":" . self::RESPONSIBLE => $this->responsible,
            ":" . self::NUM_SUS => $this->numSus,
            ":" . self::BIRTH_DATE => $this->birthDate);
    }

    public static function getFromArray(array $array)
    {
        return new Responsible($array[self::RESPONSIBLE], $array[self::NUM_SUS], $array[self::BIRTH_DATE]);
    }
}