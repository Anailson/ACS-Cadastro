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
    const CID_CIAP = "CID_CIAP";

    private $conditions, $communicable, $tracking;

    public function __construct(ConditionsDiseases $conditions, CommunicableDiseases $communicable, TrackingDiseases $tracking)
    {
        $this->conditions = $conditions;
        $this->communicable = $communicable;
        $this->tracking = $tracking;
    }

    public function getValuesToDB()
    {
        $values[ConditionsDiseases::CONDITIONS_DISEASES] = $this->conditions->getValuesToDB();
        $values[CommunicableDiseases::COMMUNICABLE_DISEASES] = $this->communicable->getValuesToDB();
        $values[TrackingDiseases::TRACKING_DISEASES] = $this->tracking->getValuesToDB();
        return $values;
    }



    public static function getFromArray(array $array)
    {
        return new ConditionsModel(ConditionsDiseases::getFromArray($array[ConditionsDiseases::CONDITIONS_DISEASES]),
            CommunicableDiseases::getFromArray($array[CommunicableDiseases::COMMUNICABLE_DISEASES]),
            TrackingDiseases::getFromArray($array[TrackingDiseases::TRACKING_DISEASES]));
    }

}