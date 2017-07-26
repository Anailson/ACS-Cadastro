<?php

class Particular
{
    const PARTICULAR = "PARTICULAR";
    const NUM_SUS = "NUM_SUS";
    const NUM_NIS = "NUM_NIS";
    const NAME = "NAME";
    const SOCIAL_NAME = "SOCIAL_NAME";
    const BIRTH_DATE = "BIRTH_DATE";

    private $numSus, $numNis, $name, $socialName, $birthDate;

    public function __construct($numSus, $numNis, $name, $socialName, $birthDate)
    {
        $this->numSus = $numSus;
        $this->numNis = $numNis;
        $this->name = $name;
        $this->socialName = $socialName;
        $this->birthDate = $birthDate;
    }
    public function getNumSus()
    {
        return $this->numSus;
    }
    public function getName()
    {
        return $this->name;
    }

    public static function getFromArray(array $array)
    {
        return new Particular($array[self::NUM_SUS], $array[self::NUM_NIS], $array[self::NAME],
            $array[self::SOCIAL_NAME], $array[self::BIRTH_DATE]);
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::NUM_SUS => $this->numSus,
            ":" . self::NUM_NIS => $this->numNis,
            ":" . self::NAME => $this->name,
            ":" . self::SOCIAL_NAME => $this->socialName,
            ":" . self::BIRTH_DATE => $this->birthDate);
        return $db->insert($query, $values);
    }
}