<?php

class Interment
{
    const INTERMENT = "INTERMENT";
    const DESCRIPTION = "DESCRIPTION";

    private $interment, $description;

    function __construct($interment, $description)
    {
        $this->interment = $interment;
        $this->description = $description;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::INTERMENT => $this->interment,
            ":" . self::DESCRIPTION => $this->description);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new Interment($array[self::INTERMENT], $array[self::DESCRIPTION]);
    }
}