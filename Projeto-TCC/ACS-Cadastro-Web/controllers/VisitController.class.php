<?php
if(!@include "persistences/VisitPersistence.class.php") {
    include "../persistences/VisitPersistence.class.php";
}
class VisitController
{

    const ADD = "Nova Visita";
    const DETAILS = "Detalhes";
    const EDIT = "Editar";
    const DELETE = "Remover";

    function __construct()
    {
    }

    public function getAllSimpleVisitInfo()
    {
        $info = array();
        $records = VisitPersistence::getAllSimpleVisitInfo();
        foreach ($records as $record){
            array_push($info, array(
                RecordDetails::RECORD => $this->filterValues($record[RecordDetails::RECORD]),
                Particular::NUM_SUS => $this->filterValues($record[Particular::NUM_SUS]),
                Particular::NUM_NIS => $this->filterValues($record[Particular::NUM_NIS]),
                Particular::NAME => $record[Particular::NAME],
                Particular::SOCIAL_NAME => $record[Particular::SOCIAL_NAME],
                Particular::BIRTH_DATE => $record[Particular::BIRTH_DATE]));
        }
        return $info;
    }

    public function crudButtons()
    {


        return array(
            self::ADD => "<button class='btn btn-primary btn-fill' onclick='redirect(\"nova-visita\")'><i class='fa fa-plus-circle'></i>&nbsp;" . self::ADD . "</button>",
            self::DETAILS => "<button name='details' id='details' class='btn btn-block btn-info btn-fill'><i class='fa fa-search-plus'></i></button>",
            //self::EDIT => "<button class='btn btn-warning btn-fill'><i class='fa fa-pencil-square-o'></i></button>",
            //self::DELETE => "<button class='btn btn-danger btn-fill'><i class='fa fa-trash-o'></i></button>",
        );
    }

    private function filterValues($value)
    {
        return intval($value) <= 0 ? "- - -" : $value;
    }
}