<?php

class Hygiene
{
    const HYGIENE = "HYGIENE";
    const BATH = "BATH";
    const SANITARY = "SANITARY";
    const ORAL = "ORAL";
    const ANOTHER = "ANOTHER";

    private $hygiene, $bath, $sanitary, $oral, $another;

    public function __construct($hygiene, $bath, $sanitary, $oral, $another)
    {
        $this->hygiene = $hygiene;
        $this->bath = $bath;
        $this->sanitary = $sanitary;
        $this->oral = $oral;
        $this->another = $another;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::HYGIENE => $this->hygiene,
            ":" . self::BATH => $this->bath,
            ":" . self::SANITARY => $this->sanitary,
            ":" . self::ORAL => $this->oral,
            ":" . self::ANOTHER => $this->another);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new Hygiene($array[self::HYGIENE], $array[self::BATH], $array[self::SANITARY], $array[self::ORAL], $array[self::ANOTHER]);
    }
}