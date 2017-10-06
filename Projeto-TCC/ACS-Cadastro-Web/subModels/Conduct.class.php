<?php

class Conduct
{
    const CONDUCT = "CONDUCT";
    const SCHEDULE_APPOINTMENT = "SCHEDULE_APPOINTMENT";
    const SCHEDULE_CARE = "SCHEDULE_CARE";
    const GROUPS_SCHEDULE = "GROUPS_SCHEDULE";
    const NASF_SCHEDULE = "NASF_SCHEDULE";
    const RELEASES = "RELEASES";

    private $appointment, $care, $groups, $nasf, $release;

    public function __construct($appointment, $care, $groups, $nasf, $release)
    {
        $this->appointment = $appointment;
        $this->care = $care;
        $this->groups = $groups;
        $this->nasf = $nasf;
        $this->release = $release;
    }

    public function getValuesToBD()
    {
        return array(self::SCHEDULE_APPOINTMENT => $this->appointment,
            self::SCHEDULE_CARE => $this->care,
            self::GROUPS_SCHEDULE => $this->groups,
            self::NASF_SCHEDULE => $this->nasf,
            self::RELEASES => $this->release);
    }

    public static function getFromArray(array $array)
    {
        return new Conduct($array[self::SCHEDULE_APPOINTMENT], $array[self::SCHEDULE_CARE], $array[self::GROUPS_SCHEDULE],
            $array[self::NASF_SCHEDULE], $array[self::RELEASES]);
    }
}