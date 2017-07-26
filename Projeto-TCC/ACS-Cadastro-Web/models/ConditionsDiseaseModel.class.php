<?php
if(!@include "subModels/Tracking.class.php") {
    include "../subModels/Tracking.class.php";
}
if(!@include "subModels/Communicable.class.php") {
    include "../subModels/Communicable.class.php";
}
if(!@include "subModels/Conditions.class.php") {
    include "../subModels/Conditions.class.php";
}

class ConditionsDiseaseModel
{
    const CONDITIONS_DISEASE = "CONDITIONS_DISEASE";

    private $conditions, $communicable, $tracking;

    public function __construct(Conditions $conditions, Communicable $communicable, Tracking $tracking)
    {
        $this->conditions = $conditions;
        $this->communicable = $communicable;
        $this->tracking = $tracking;
    }

    public function save(AcsDataBase $db, array $query)
    {
        $params = array(":ID_" . Conditions::CONDITIONS => $this->conditions->save($db, $query[Conditions::CONDITIONS]),
            ":ID_" . Communicable::COMMUNICABLE => $this->communicable->save($db, $query[Communicable::COMMUNICABLE]),
            ":ID_" . Tracking::TRACKING => $this->tracking->save($db, $query[Tracking::TRACKING]));
        if(in_array(false, $params)){
            return false;
        }
        return $db->insert($query[self::CONDITIONS_DISEASE], $params);
    }
}