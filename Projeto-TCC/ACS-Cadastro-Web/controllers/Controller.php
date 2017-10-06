<?php

class Controller
{
    const OK_RESULT = 0;
    const EMPTY_VALUES = -1;
    const INVALID_VALUES = -2;
    const GENERAL_ERROR = -3;

    const ADD = "add";
    const DETAILS = "details";
    const EDIT = "edit";
    const DELETE = "delete";

    private $add = "";
    private $detail = "";
    private $edit = "";
    private $delete = "";

    public function changeAddCrudText($text)
    {
        $this->setCrudText(array(self::ADD => $text, self::DETAILS => $this->detail,
            self::EDIT => $this->edit, self::DELETE => $this->delete));
    }

    public function changeDetailCrudText($text)
    {
        $this->setCrudText(array(self::ADD => $this->add, self::DETAILS => $text,
            self::EDIT => $this->edit, self::DELETE => $this->delete));
    }

    public function changeEditCrudText($text)
    {
        $this->setCrudText(array(self::ADD => $this->add, self::DETAILS => $this->detail,
            self::EDIT => $text, self::DELETE => $this->delete));
    }

    public function changeDeleteCrudText($text)
    {
        $this->setCrudText(array(self::ADD => $this->add, self::DETAILS => $this->detail,
            self::EDIT => $this->edit, self::DELETE => $text));
    }

    public function setCrudText(array $array)
    {
        $this->add = $array[self::ADD];
        $this->detail = $array[self::DETAILS];
        $this->edit = $array[self::EDIT];
        $this->delete = $array[self::DELETE];
    }
/*
    public function crudButtons($numSus)
    {
        return array(
            self::ADD => "<button name='add' class='btn btn-primary btn-fill add'><i class='fa fa-plus-circle'></i>&nbsp;" . $this->add . "</button>",
            self::DETAILS => "<button name='details' id='$numSus' class='btn btn-info btn-fill detail'><i class='fa fa-search-plus'></i>&nbsp;" . $this->detail . "</button>",
            self::EDIT => "<button name='edit' id='$numSus' class='btn btn-warning btn-fill edit'><i class='fa fa-pencil-square-o'></i>&nbsp;" . $this->edit . "</button>",
            self::DELETE => "<button name='delete' id='$numSus' class='btn btn-danger btn-fill delete'><i class='fa fa-trash-o'></i>&nbsp;" . $this->delete . "</button>");
    }
*/
}