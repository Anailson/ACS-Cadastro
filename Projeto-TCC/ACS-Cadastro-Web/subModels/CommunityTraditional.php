<?php

class CommunityTraditional
{
    const COMMUNITY_TRADITIONAL = "COMMUNITY_TRADITIONAL";
    const DESCRIPTION = "DESCRIPTION";

    private $community, $description;

    function __construct($community, $description)
    {
        $this->community = $community;
        $this->description = $description;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::COMMUNITY_TRADITIONAL => $this->community,
            ":" . self::DESCRIPTION => $this->description);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new CommunityTraditional($array[self::COMMUNITY_TRADITIONAL], $array[self::DESCRIPTION]);
    }

}