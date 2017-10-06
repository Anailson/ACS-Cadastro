<?php
if(!@include_once "models/CitizenModel.class.php"){include_once "../models/CitizenModel.class.php";}
if(!@include_once "persistences/CitizenPersistence.php"){include_once "../persistences/CitizenPersistence.php";}

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
            self::ADD => "<button name='add' class='btn btn-primary btn-fill' onclick='redirect(\"novo-cidadao\")'><i class='fa fa-plus-circle'></i>&nbsp;" . self::ADD . "</button>",
            self::DETAILS => "<button name='details' class='btn btn-block btn-info btn-fill' onclick='redirect(\"\")'><i class='fa fa-search-plus'></i></button>",
            self::EDIT => "<button name='edit' class='btn btn-block btn-warning btn-fill' onclick='redirect(\"\")'><i class='fa fa-pencil-square-o'></i></button>",
            self::DELETE => "<button name='delete' class='btn btn-block btn-danger btn-fill' onclick='redirect(\"\")'><i class='fa fa-trash-o'></i></button>",
        );
    }
}
