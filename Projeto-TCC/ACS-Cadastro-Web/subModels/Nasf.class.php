<?php

class Nasf
{
    const NASF = "NASF";
    const EVALUATION = "EVALUATION";
    const PROCEDURES = "PROCEDURES";
    const PRESCRIPTION = "PRESCRIPTION";

    private $evaluation, $procedures, $prescription;

    public function __construct($evaluation, $procedures, $prescription)
    {
        $this->evaluation = $evaluation;
        $this->procedures = $procedures;
        $this->prescription = $prescription;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(self::EVALUATION => $this->evaluation,
            self::PROCEDURES => $this->procedures,
            self::PRESCRIPTION => $this->prescription);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new Nasf($array[self::EVALUATION], $array[self::PROCEDURES], $array[self::PRESCRIPTION]);
    }
}