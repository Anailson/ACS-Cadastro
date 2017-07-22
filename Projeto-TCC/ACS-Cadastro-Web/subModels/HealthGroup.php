<?php

class HealthGroup
{
    const CAREGIVER = "CAREGIVER";
    const COMMUNITY_GROUP = "COMMUNITY_GROUP";
    const HEALTH_PLAN = "HEALTH_PLAN";
    const HEALTH_GROUP = "HEALTH_GROUP";

    private $caregiver, $communityGroup, $healthPlan;

    function __construct($caregiver, $communityGroup, $healthPlan)
    {
        $this->caregiver = $caregiver;
        $this->communityGroup = $communityGroup;
        $this->healthPlan = $healthPlan;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::CAREGIVER => $this->caregiver,
            ":" . self::COMMUNITY_GROUP => $this->communityGroup,
            ":" . self::HEALTH_PLAN=>$this->healthPlan);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new HealthGroup($array[self::CAREGIVER], $array[self::COMMUNITY_GROUP], $array[self::HEALTH_PLAN]);
    }

}