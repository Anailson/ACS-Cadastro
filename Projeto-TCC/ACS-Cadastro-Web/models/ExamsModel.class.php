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

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":ID_" . RequestExams::REQUEST_EXAMS => $this->request->save($db, $query[RequestExams::REQUEST_EXAMS]),
            ":ID_" . EvaluatedExams::EVALUATED_EXAMS => $this->evaluated->save($db, $query[EvaluatedExams::EVALUATED_EXAMS]));
        if(in_array(false, $params)){
            return false;
        }
        $params[":" . self::PIC] = $this->pic;
        return $db->insert($query[self::EXAMS], $params);
    }

    public static function getFromArray(array $array)
    {
        return new ExamsModel($array[self::PIC],
            RequestExams::getFromArray($array[RequestExams::REQUEST_EXAMS]),
            EvaluatedExams::getFromArray($array[EvaluatedExams::EVALUATED_EXAMS]));
    }

}