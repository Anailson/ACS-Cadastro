<?php

class FamilyVisit
{
    const FAMILY_VISIT = "FAMILY_VISIT";
    const DESCRIPTION = "DESCRIPTION";

    private $familyVisit, $description;

    function __construct($familyVisit, $description)
    {
        $this->familyVisit = $familyVisit;
        $this->description = $description;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::FAMILY_VISIT => $this->familyVisit,
            ":" . self::DESCRIPTION => $this->description);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new FamilyVisit($array[self::FAMILY_VISIT], $array[self::DESCRIPTION]);
    }
}