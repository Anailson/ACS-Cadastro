<?php

class EducationEmployment
{
    const SCHOOL = "SCHOOL";
    const OCCUPATION = "OCCUPATION";
    const EDUCATION = "EDUCATION";
    const EMPLOYMENT = "EMPLOYMENT";
    const EDUCATION_EMPLOYMENT = "EDUCATION_EMPLOYMENT";

    private $school, $occupation, $education, $employment;

    function __construct($school, $occupation, $education, $employment)
    {
        $this->school = $school;
        $this->occupation = $occupation;
        $this->education = $education;
        $this->employment = $employment;
    }

    public function save(AcsDataBase $db, $query)
    {
        $array = array(":" . self::SCHOOL => $this->school,
            ":" . self::OCCUPATION => $this->occupation,
            ":" . self::EDUCATION => $this->education,
            ":" . self::EMPLOYMENT => $this->employment);
        return $db->insert($query, $array);
    }

    public static function getFromArray(array $array)
    {
        return new EducationEmployment($array[self::SCHOOL], $array[self::OCCUPATION], $array[self::EDUCATION], $array[self::EMPLOYMENT]);
    }

}