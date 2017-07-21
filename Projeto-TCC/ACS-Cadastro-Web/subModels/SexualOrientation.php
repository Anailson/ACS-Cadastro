<?php

class SexualOrientation
{
    const SEXUAL_ORIENTATION = "SEXUAL_ORIENTATION";
    const DESCRIPTION = "DESCRIPTION";

    private $sexual, $description;

    function __construct($sexual, $description)
    {
        $this->sexual = $sexual;
        $this->description = $description;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::SEXUAL_ORIENTATION => $this->sexual,
            ":" . self::DESCRIPTION => $this->description);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new SexualOrientation($array[self::SEXUAL_ORIENTATION], $array[self::DESCRIPTION]);
    }
}