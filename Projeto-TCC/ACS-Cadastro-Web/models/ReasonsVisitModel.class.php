<?php
if (!@include "subModels/ActiveSearch.class.php") {
    include "../subModels/ActiveSearch.class.php";
}
if (!@include "subModels/AnotherReasons.class.php") {
    include "../subModels/AnotherReasons.class.php";
}
if (!@include "subModels/Following.class.php") {
    include "../subModels/Following.class.php";
}

class ReasonsVisitModel
{
    const REASONS_VISIT = "REASONS_VISIT";
    const RESULT = "RESULT";

    private $result, $activeSearch, $following, $anotherReasons;

    public function __construct($result, ActiveSearch $activeSearch, Following $following, AnotherReasons $anotherReasons)
    {
        $this->result = $result;
        $this->activeSearch = $activeSearch;
        $this->following = $following;
        $this->anotherReasons = $anotherReasons;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":ID_" . ActiveSearch::ACTIVE_SEARCH => $this->activeSearch->save($db, $query[ActiveSearch::ACTIVE_SEARCH]),
            ":ID_" . Following::FOLLOWING => $this->following->save($db, $query[Following::FOLLOWING]),
            ":ID_" . AnotherReasons::ANOTHER_REASONS => $this->anotherReasons->save($db, $query[AnotherReasons::ANOTHER_REASONS]));
        if(in_array(false, $params)){
            return false;
        }
        $params[":" . self::RESULT] = $this->result;
        return $db->insert($query[self::REASONS_VISIT], $params);
    }

    public static function getFromArray(array $array)
    {
        return new ReasonsVisitModel($array[self::RESULT], ActiveSearch::getFromArray($array[ActiveSearch::ACTIVE_SEARCH]),
            Following::getFromArray($array[Following::FOLLOWING]), AnotherReasons::getFromArray($array[AnotherReasons::ANOTHER_REASONS]));
    }
}