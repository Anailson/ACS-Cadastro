<?php

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

    public function save(AcsDataBase $db, array $query)
    {
        $values[":ID_" . Pregnant::PREGNANT] = $this->pregnant->save($db, $query[Pregnant::PREGNANT]);
        $values[":ID_" . Diseases::DISEASES] = $this->diseases->save($db, $query[Diseases::DISEASES]);
        $values[":ID_" . HeartDisease::HEART_DISEASE] = $this->heartDisease->save($db, $query[HeartDisease::HEART_DISEASE]);
        $values[":ID_" . KidneyDisease::KIDNEY_DISEASE] = $this->kidneyDisease->save($db, $query[KidneyDisease::KIDNEY_DISEASE]);
        $values[":ID_" . RespiratoryDisease::RESPIRATORY_DISEASE] = $this->respiratoryDisease->save($db, $query[RespiratoryDisease::RESPIRATORY_DISEASE]);
        $values[":ID_" . Interment::INTERMENT] = $this->interment->save($db, $query[Interment::INTERMENT]);
        $values[":ID_" . Plant::PLANT] = $this->plant->save($db, $query[Plant::PLANT]);
        if(in_array(false, $values)){
            return false;
        }
        $values[":" . self::WEIGHT] = $this->weight;
        return $db->insert($query[self::HEALTH_CONDITIONS], $values);
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