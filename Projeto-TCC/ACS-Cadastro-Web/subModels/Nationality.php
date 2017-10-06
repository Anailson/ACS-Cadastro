<?php

class Nationality
{
    const NATIONALITY = "NATIONALITY";
    const NATION_BIRTH = "NATION_BIRTH";
    const UF = "UF";
    const CITY = "CITY";

    private $nationality, $nationBirth, $uf, $city;

    function __construct($nationality, $nationBirth, $uf, $city)
    {
        $this->nationality = $nationality;
        $this->nationBirth = $nationBirth;
        $this->uf = $uf;
        $this->city = $city;
    }

    public function getValuesToDB()
    {
        return array(":" . self::NATIONALITY => $this->nationality,
            ":" . self::NATION_BIRTH => $this->nationBirth,
            ":" . self::UF => $this->uf, ":" . self::CITY => $this->city);
    }

    public static function getFromArray(array $array)
    {
        return new Nationality($array[self::NATIONALITY], $array[self::NATION_BIRTH], $array[self::UF], $array[self::CITY]);
    }
}