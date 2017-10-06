<?php

if (!@include "persistences/ResidencePersistence.class.php") {
    include "../persistences/ResidencePersistence.class.php";
}
if (!@include_once "models/ResidenceModel.class.php") {
    include_once "../models/ResidenceModel.class.php";
}

class ResidenceController
{
    const ADD = "Nova residência";
    const DETAILS = "Detalhes";
    const EDIT = "Editar";
    const DELETE = "Remover";

    public function getAllSimpleInfoResidence()
    {
        $info = array();
        $records = ResidencePersistence::getAllSimpleInfoResidence();
        foreach ($records as $record) {
            array_push($info, array(
                CityLocation::CEP => $record[CityLocation::CEP],
                AddressDataModel::ADDRESS_DATA => $this->getCompleteAddress($record),
                Phones::PHONES => $record[Phones::HOME]));
        }
        return $info;
    }

    public function crudButtons()
    {
        return array(
            self::ADD => "<button name='add' class='btn btn-primary btn-fill' onclick='redirect(\"nova-residencia\")'><i class='fa fa-plus-circle'></i>&nbsp;" . self::ADD . "</button>",
            self::DETAILS => "<button class='btn btn-block btn-info btn-fill'><i class='fa fa-search-plus'></i></button>",
            self::EDIT => "<button class='btn btn-block btn-warning btn-fill'><i class='fa fa-pencil-square-o'></i></button>",
            self::DELETE => "<button class='btn btn-block btn-danger btn-fill'><i class='fa fa-trash-o'></i></button>");
    }

    private function getCompleteAddress(array $array)
    {
        $type = strpos($array[StreetLocation::TYPE], "-") ? "" : $array[StreetLocation::TYPE];
        return $type . " " . $array[StreetLocation::NAME] . ", " . $array[StreetLocation::NUMBER];
    }
}