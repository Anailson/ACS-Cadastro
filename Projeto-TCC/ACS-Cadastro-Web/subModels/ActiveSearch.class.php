<?php

class ActiveSearch
{
    const ACTIVE_SEARCH = "ACTIVE_SEARCH";
    const APPOINTMENT = "APPOINTMENT";
    const EXAM = "EXAM";
    const VACCINE = "VACCINE";
    const CONDITIONS = "CONDITIONS";

    private $appointment, $exam, $vaccine, $conditions;

    public function __construct($appointment, $exam, $vaccine, $conditions)
    {
        $this->appointment = $appointment;
        $this->exam = $exam;
        $this->vaccine = $vaccine;
        $this->conditions = $conditions;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::APPOINTMENT => $this->appointment,
            ":" . self::EXAM => $this->exam,
            ":" . self::VACCINE => $this->vaccine,
            ":" . self::CONDITIONS => $this->conditions);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new ActiveSearch($array[self::APPOINTMENT], $array[self::EXAM], $array[self::VACCINE], $array[self::CONDITIONS]);
    }

}