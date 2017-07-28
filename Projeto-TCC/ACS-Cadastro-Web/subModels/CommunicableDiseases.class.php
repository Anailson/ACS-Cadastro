<?php

class CommunicableDiseases
{
    const COMMUNICABLE_DISEASES = "COMMUNICABLE_DISEASES";
    const TUBERCULOSIS = "TUBERCULOSIS";
    const LEPROSY = "LEPROSY";
    const DENGUE = "DENGUE";
    const DST = "DST";

    private $tuberculosis, $leprosy, $dengue, $dst;

    public function __construct($tuberculosis, $leprosy, $dengue, $dst)
    {
        $this->tuberculosis = $tuberculosis;
        $this->leprosy = $leprosy;
        $this->dengue = $dengue;
        $this->dst = $dst;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::TUBERCULOSIS => $this->tuberculosis,
            ":" . self::LEPROSY => $this->leprosy,
            ":" . self::DENGUE => $this->dengue,
            ":" . self::DST => $this->dst);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new CommunicableDiseases($array[self::TUBERCULOSIS], $array[self::LEPROSY], $array[self::DENGUE], $array[self::DST]);
    }
}