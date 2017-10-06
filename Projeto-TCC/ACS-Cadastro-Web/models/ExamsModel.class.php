<?php
if (!@include "subModels/RequestExams.class.php") {
    include "../subModels/RequestExams.class.php";
}
if (!@include "subModels/EvaluatedExams.class.php") {
    include "../subModels/EvaluatedExams.class.php";
}

class ExamsModel
{
    const EXAMS = "EXAMS";
    const PIC = "PIC";

    private $pic, $request, $evaluated;

    public function __construct($pic, RequestExams $request, EvaluatedExams $evaluated)
    {
        $this->pic = $pic;
        $this->request = $request;
        $this->evaluated = $evaluated;
    }

    public function getValuesToDB()
    {
        $values[RequestExams::REQUEST_EXAMS] = $this->request->getValuesToDB();
        $values[EvaluatedExams::EVALUATED_EXAMS] = $this->evaluated->getValuesToDB();
        $values[self::PIC] = $this->pic;
        return $values;
    }

    public static function getFromArray(array $array)
    {
        return new ExamsModel($array[self::PIC],
            RequestExams::getFromArray($array[RequestExams::REQUEST_EXAMS]),
            EvaluatedExams::getFromArray($array[EvaluatedExams::EVALUATED_EXAMS]));
    }

}