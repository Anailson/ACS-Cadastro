<?php
if (!@include "persistences/AcsDataBase.php") {
    include "../persistences/AcsDataBase.php";
};
if (!@include "persistences/PersonalDataPersistence.php") {
    include "../persistences/PersonalDataPersistence.php";
};
if (!@include "persistences/SocialDemographicPersistence.php") {
    include "../persistences/SocialDemographicPersistence.php";
};
if (!@include "persistences/HealthConditionsPersistence.php") {
    include "../persistences/HealthConditionsPersistence.php";
};


class CitizenPersistence
{
    static function insert($json)
    {
        $citizen = json_decode($json, true);
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $db->beginTransaction();

        $personalData = $citizen['PERSONAL_DATA'];
        $socialDemographic = $citizen['SOCIAL_DEMOGRAPHIC'];
        $healthConditions = $citizen['HEALTH_CONDITIONS'];
        $streetSituation = $citizen['STREET_SITUATION'];

        $values = array();
        //$values[':PERSONAL_DATA'] = PersonalDataPersistence::insert($db, $personalData);
        $values[':SOCIAL_DEMOGRAPHIC'] = SocialDemographic::insert($db, $socialDemographic);

        echo "<b>" . var_dump($values) ."</b><br>";
        if (in_array(0, $values) || in_array(-1, $values)){
            $db->rollback();
            return -1;
        }
        return $values;
    }
}