<?php

class AnotherInstitution
{
    const ANOTHER_INSTITUTION = "ANOTHER_INSTITUTION";
    const DESCRIPTION = "DESCRIPTION";

    private $anotherInstitution, $description;

    public function __construct($anotherInstitution, $description)
    {
        $this->anotherInstitution = $anotherInstitution;
        $this->description = $description;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::ANOTHER_INSTITUTION => $this->anotherInstitution,
            ":" . self::DESCRIPTION => $this->description);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new AnotherInstitution($array[self::ANOTHER_INSTITUTION], self::DESCRIPTION);
    }


}