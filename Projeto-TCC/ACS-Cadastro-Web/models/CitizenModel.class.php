<?php

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