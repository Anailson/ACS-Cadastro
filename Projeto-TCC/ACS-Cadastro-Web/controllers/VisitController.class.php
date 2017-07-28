<?php

class VisitController
{

    const ADD = "Nova Visita";
    const DETAILS = "Detalhes";
    const EDIT = "Editar";
    const DELETE = "Remover";

    function __construct()
    {
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