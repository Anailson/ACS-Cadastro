<?php
include "models/CitizenModel.class.php";
include "persistences/CitizenPersistence.php";

class CitizenController
{
    const ADD = "Novo cidadão";
    const DETAILS = "Detalhes";
    const EDIT = "Editar";
    const DELETE = "Remover";

    function __construct()
    {
    }

    public function getAllParticularInfo()
    {
        $data = array();
        $values = CitizenPersistence::getAllParticularInfo();

        foreach ($values as $value) {

            array_push($data,
                array(Particular::NUM_SUS => self::filterNumSus($value[Particular::NUM_SUS]),
                    Particular::NAME => $value[Particular::NAME],
                    Particular::BIRTH_DATE => $value[Particular::BIRTH_DATE],
                    Contact::PHONE => $value[Contact::PHONE],
                    Contact::EMAIL => $value[Contact::EMAIL]
                ));
        }
        return $data;
    }

    private function filterNumSus($value)
    {
        return intval($value) <= 0 ? "- - -" : $value;
    }

    public function crudButtons()
    {
        return array(
            self::ADD => "<button type='button' class='btn btn-primary btn-fill'><i class='fa fa-plus-circle'></i>&nbsp;" . self::ADD . "</button>",
            self::DETAILS => "<button type='button' class='btn btn-info btn-fill'><i class='fa fa-search-plus'></i>&nbsp;" . self::DETAILS . "</button>",
            self::EDIT => "<button type='button' class='btn btn-warning btn-fill'><i class='fa fa-pencil-square-o'></i>&nbsp;" . self::EDIT . "</button>",
            self::DELETE => "<button type='button' class='btn btn-danger btn-fill'><i class='fa fa-trash-o'></i>&nbsp;" . self::DELETE . "</button>",
        );
    }
}