<?php
if(!@include "../subModels/Pregnant.class.php"){
    include "subModels/Pregnant.class.php";
}
if(!@include "../subModels/Diseases.class.php"){
    include "subModels/Diseases.class.php";
}
if(!@include "../subModels/HeartDisease.class.php"){
    include "subModels/HeartDisease.class.php";
}
if(!@include "../subModels/KidneyDisease.class.php"){
    include "subModels/KidneyDisease.class.php";
}
if(!@include "../subModels/RespiratoryDisease.class.php"){
    include "subModels/RespiratoryDisease.class.php";
}
if(!@include "../subModels/Interment.class.php"){
    include "subModels/Interment.class.php";
}
if(!@include "../subModels/Plant.class.php"){
    include "subModels/Plant.class.php";
}


class HealthConditionsModel
{

    const WEIGHT = "WEIGHT";
    const HEALTH_CONDITIONS = "HEALTH_CONDITIONS";

    private $weight, $pregnant, $diseases, $heartDisease, $kidneyDisease, $respiratoryDisease, $interment, $plant;

    public function __construct($weight, Pregnant $pregnant, Diseases $diseases, HeartDisease $heartDisease, KidneyDisease $kidneyDisease,
                                RespiratoryDisease $respiratoryDisease, Interment $Interment, Plant $plant)
    {
        $this->weight = $weight;
        $this->pregnant = $pregnant;
        $this->diseases = $diseases;
        $this->heartDisease = $heartDisease;
        $this->kidneyDisease = $kidneyDisease;
        $this->respiratoryDisease = $respiratoryDisease;
        $this->interment = $Interment;
        $this->plant = $plant;
    }

    public function getValuesToDB()
    {
        $values[Pregnant::PREGNANT] = $this->pregnant->getValuesToDB();
        $values[Diseases::DISEASES] = $this->diseases->getValuesToDB();
        $values[HeartDisease::HEART_DISEASE] = $this->heartDisease->getValuesToDB();
        $values[KidneyDisease::KIDNEY_DISEASE] = $this->kidneyDisease->getValuesToDB();
        $values[RespiratoryDisease::RESPIRATORY_DISEASE] = $this->respiratoryDisease->getValuesToDB();
        $values[Interment::INTERMENT] = $this->interment->getValuesToDB();
        $values[Plant::PLANT] = $this->plant->getValuesToDB();
        $values[self::WEIGHT] = $this->weight;
        return $values;
    }

    public static function getFromArray(array $array)
    {
        return new HealthConditionsModel($array[self::WEIGHT],
            Pregnant::getFromArray($array[Pregnant::PREGNANT]),
            Diseases::getFromArray($array[Diseases::DISEASES]),
            HeartDisease::getFromArray($array[HeartDisease::HEART_DISEASE]),
            KidneyDisease::getFromArray($array[KidneyDisease::KIDNEY_DISEASE]),
            RespiratoryDisease::getFromArray($array[RespiratoryDisease::RESPIRATORY_DISEASE]),
            Interment::getFromArray($array[Interment::INTERMENT]),
            Plant::getFromArray($array[Plant::PLANT]));
    }
}