<?php
if(!@include "subModels/TrackingDiseases.class.php") {
    include "../subModels/TrackingDiseases.class.php";
}
if(!@include "subModels/CommunicableDiseases.class.php") {
    include "../subModels/CommunicableDiseases.class.php";
}
if(!@include "subModels/ConditionsDiseases.class.php") {
    include "../subModels/ConditionsDiseases.class.php";
}

class ConditionsModel
{
    const CONDITIONS = "CONDITIONS";

    private $conditions, $communicable, $tracking;

    public function __construct(ConditionsDiseases $conditions, CommunicableDiseases $communicable, TrackingDiseases $tracking)
    {
        $this->conditions = $conditions;
        $this->communicable = $communicable;
        $this->tracking = $tracking;
    }

    public function save(AcsDataBase $db, array $query)
    {
        $params = array(":ID_" . ConditionsDiseases::CONDITIONS_DISEASES => $this->conditions->save($db, $query[ConditionsDiseases::CONDITIONS_DISEASES]),
            ":ID_" . CommunicableDiseases::COMMUNICABLE_DISEASES => $this->communicable->save($db, $query[CommunicableDiseases::COMMUNICABLE_DISEASES]),
            ":ID_" . TrackingDiseases::TRACKING_DISEASES => $this->tracking->save($db, $query[TrackingDiseases::TRACKING_DISEASES]));
        if(in_array(false, $params)){
            return false;
        }
        return $db->insert($query[self::CONDITIONS], $params);
    }



    public static function getFromArray(array $array)
    {
        return new ConditionsModel(ConditionsDiseases::getFromArray($array[ConditionsDiseases::CONDITIONS_DISEASES]),
            CommunicableDiseases::getFromArray($array[CommunicableDiseases::COMMUNICABLE_DISEASES]),
            TrackingDiseases::getFromArray($array[TrackingDiseases::TRACKING_DISEASES]));
    }

}