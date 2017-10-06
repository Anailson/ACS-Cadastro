<?php
if (!@include "persistences/AccompanyPersistence.php") {
    include "../persistences/AccompanyPersistence.php";
}
if (!@include_once "models/AccompanyModel.class.php") {
    include_once "../models/AccompanyModel.class.php";
}
if (!@include_once "models/CitizenModel.class.php") {
    include_once "../models/CitizenModel.class.php";
}


class AccompanyController
{
    const ADD = "Novo Acompanhamento";
    const DETAILS = "Detalhes";
    const EDIT = "Editar";
    const DELETE = "Remover";

    function __construct()
    {
    }

    public function getAllSimpleInfoAccompany()
    {
        $info = array();
        $records = AccompanyPersistence::getAllSimpleInfoAccompany();

        foreach ($records as $record) {

            var_dump($record);
            array_push($info, array(
                RecordDetails::RECORD => self::filterValues($record[RecordDetails::RECORD]),
                Particular::NUM_SUS => self::filterValues($record[Particular::NUM_SUS]),
                Particular::NUM_NIS => self::filterValues($record[Particular::NUM_NIS]),
                Particular::NAME => $record[Particular::NAME],
                Particular::SOCIAL_NAME => $record[Particular::SOCIAL_NAME],
                Particular::BIRTH_DATE => $record[Particular::BIRTH_DATE]
            ));
        }
        return $info;
    }


    public function crudButtons()
    {
        return array(
            self::ADD => "<button id='add' class='btn btn-primary btn-fill' onclick='redirect(\"novo-acompanhamento\")'><i class='fa fa-plus-circle'></i>&nbsp;" . self::ADD . "</button>",
            self::DETAILS => "<button class='btn btn-info btn-fill'><i class='fa fa-search-plus'></i></button>",
            self::EDIT => "<button class='btn btn-warning btn-fill'><i class='fa fa-pencil-square-o'></i></button>",
            self::DELETE => "<button class='btn btn-danger btn-fill'><i class='fa fa-trash-o'></i></button>",
        );
    }


    private function filterValues($value)
    {
        return intval($value) <= 0 ? "- - -" : $value;
    }
}