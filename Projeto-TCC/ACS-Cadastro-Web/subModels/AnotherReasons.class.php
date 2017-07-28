<?php

class AnotherReasons
{
    const ANOTHER_REASONS = "ANOTHER_REASONS";
    const RECORD_UPDATE = "RECORD_UPDATE";
    const PERIODIC_VISIT = "PERIODIC_VISIT";
    const INTERMENT = "INTERMENT";
    const CONTROL_ENVIRONMENTS = "CONTROL_ENVIRONMENTS";
    const COLLECTIVE_ACTIVITIES = "COLLECTIVE_ACTIVITIES";
    const GUIDANCE = "GUIDANCE";
    const OTHERS = "OTHERS";

    private $recordUpdate, $periodicVisit, $internment, $controlEnvironments, $collectiveActivities,
        $guidance, $others;

    public function __construct($recordUpdate, $periodicVisit, $internment, $controlEnvironments, $collectiveActivities,
                                $guidance, $others)
    {
        $this->recordUpdate = $recordUpdate;
        $this->periodicVisit = $periodicVisit;
        $this->internment = $internment;
        $this->controlEnvironments = $controlEnvironments;
        $this->collectiveActivities = $collectiveActivities;
        $this->guidance = $guidance;
        $this->others = $others;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::RECORD_UPDATE => $this->recordUpdate,
            ":" . self::PERIODIC_VISIT => $this->periodicVisit,
            ":" . self::INTERMENT => $this->internment,
            ":" . self::CONTROL_ENVIRONMENTS => $this->controlEnvironments,
            ":" . self::COLLECTIVE_ACTIVITIES => $this->collectiveActivities,
            ":" . self::GUIDANCE => $this->guidance,
            ":" . self::OTHERS => $this->others);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new AnotherReasons($array[self::RECORD_UPDATE], $array[self::PERIODIC_VISIT], $array[self::INTERMENT],
            $array[self::CONTROL_ENVIRONMENTS], $array[self::COLLECTIVE_ACTIVITIES], $array[self::GUIDANCE], $array[self::OTHERS]);
    }
}