<?php

class GenderAndRace
{
    const GENDER = "GENDER";
    const RACE = "RACE";
    const GENDER_RACE = "GENDER_RACE";

    private $gender, $race;

    function __construct($gender, $race)
    {
        $this->gender = $gender;
        $this->race = $race;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::GENDER => $this->gender, ":" . self::RACE => $this->race);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new GenderAndRace($array[self::GENDER], $array[self::RACE]);
    }

}