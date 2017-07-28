<?php

class StreetLocation
{

    const STREET_LOCATION = "STREET_LOCATION";
    const TYPE = "TYPE";
    const NAME = "NAME";
    const NUMBER = "NUMBER";
    const COMPLEMENT = "COMPLEMENT";

    private $type, $name, $number, $complement;

    public function __construct($type, $name, $number, $complement)
    {
        $this->type = $type;
        $this->name = $name;
        $this->number = $number;
        $this->complement = $complement;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::TYPE => $this->type,
            ":" . self::NAME => $this->name,
            ":" . self::NUMBER => $this->number,
            ":" . self::COMPLEMENT => $this->complement);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new StreetLocation($array[self::TYPE], $array[self::NAME], $array[self::NUMBER], $array[self::COMPLEMENT]);
    }
}