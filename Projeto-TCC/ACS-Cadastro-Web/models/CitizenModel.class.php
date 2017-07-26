<?php
if(!@include "models/PersonalDataModel.class.php") {
    include "../models/PersonalDataModel.class.php";
}
if(!@include "models/SocialDemographicModel.class.php"){
    include "../models/SocialDemographicModel.class.php";
}
if(!@include "models/HealthConditionsModel.class.php"){
    include "../models/HealthConditionsModel.class.php";
}
if(!@include "models/StreetSituationModel.class.php"){
    include "../models/StreetSituationModel.class.php";
}
if(!@include "persistences/PersonalDataPersistence.php"){
    include "../persistences/PersonalDataPersistence.php";
}
if(!@include "persistences/SocialDemographicPersistence.php"){
    include "../persistences/SocialDemographicPersistence.php";
}

if(!@include "persistences/HealthConditionsPersistence.php"){
    include "../persistences/HealthConditionsPersistence.php";
}

if(!@include "persistences/StreetSituationPersistence.php"){
    include "../persistences/StreetSituationPersistence.php";
}   


class CitizenModel
{
    private $personal, $socialDemographic, $healthConditions, $streetSituation;

    public function __construct(PersonalDataModel $personal, SocialDemographicModel $socialDemographic,
                                HealthConditionsModel $healthConditions, StreetSituationModel $streetSituation)
    {
        $this->personal = $personal;
        $this->socialDemographic = $socialDemographic;
        $this->healthConditions = $healthConditions;
        $this->streetSituation = $streetSituation;
    }

    public function save(AcsDataBase $db, $query)
    {
        $ids[":ID_" . PersonalDataModel::PERSONAL_DATA] = PersonalDataPersistence::insert($db, $this->personal);
        $ids[":ID_" . SocialDemographicModel::SOCIAL_DEMOGRAPHIC] = SocialDemographicPersistence::insert($db, $this->socialDemographic);
        $ids[":ID_" . HealthConditionsModel::HEALTH_CONDITIONS] = HealthConditionsPersistence::insert($db, $this->healthConditions);
        $ids[":ID_" . StreetSituationModel::STREET_SITUATION] = StreetSituationPersistence::insert($db, $this->streetSituation);

        if (in_array(false, $ids)) {
            return false;
        }
        return $db->insert($query, $ids);
    }

    public static function getFromArray(array $array)
    {
        return new CitizenModel(PersonalDataModel::getByArray($array[PersonalDataModel::PERSONAL_DATA]),
            SocialDemographicModel::getFromArray($array[SocialDemographicModel::SOCIAL_DEMOGRAPHIC]),
            HealthConditionsModel::getFromArray($array[HealthConditionsModel::HEALTH_CONDITIONS]),
            StreetSituationModel::getFromArray($array[StreetSituationModel::STREET_SITUATION]));
    }
}