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

    public function getValuesToDB()
    {
        return array(":" . self::SEXUAL_ORIENTATION => $this->sexual,
            ":" . self::DESCRIPTION => $this->description);
    }

    public static function getFromArray(array $array)
    {
        return new SexualOrientation($array[self::SEXUAL_ORIENTATION], $array[self::DESCRIPTION]);
    }
}