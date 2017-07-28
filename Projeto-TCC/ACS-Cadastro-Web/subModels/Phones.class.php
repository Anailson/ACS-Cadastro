<?php

class Phones
{
    const PHONES = "PHONES";
    const HOME = "HOME";
    const REFERENCE = "REFERENCE";

    private $home, $reference;

    public function __construct($home, $reference)
    {
        $this->home = $home;
        $this->reference = $reference;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::HOME => $this->home, ":" . self::REFERENCE => $this->reference);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new Phones($array[self::HOME], $array[self::REFERENCE]);
    }
}