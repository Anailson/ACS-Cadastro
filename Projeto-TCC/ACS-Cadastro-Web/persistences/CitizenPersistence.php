<?php
if (!@include_once "models/CitizenModel.class.php") {
    include_once "../models/CitizenModel.class.php";
}
if (!@include_once "persistences/AcsDataBase.php") {
    include_once "../persistences/AcsDataBase.php";
}
if (!@include_once "persistences/AgentsPersistence.php") {
    include_once "../persistences/AgentsPersistence.php";
}

class CitizenPersistence
{
    public static function getId($numSus)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT CZ.CITIZEN_ID
              FROM TB_CITIZEN AS CZ 
              INNER JOIN TB_PERSONAL_DATA AS PD ON CZ.ID_PERSONAL_DATA = PD.PERSONAL_DATA_ID
              INNER JOIN TB_PARTICULAR AS P ON PD.ID_PARTICULAR = P.PARTICULAR_ID 
              WHERE P.NUM_SUS = :NUM_SUS
              LIMIT 1";
        return $db->select($query, array(":NUM_SUS" => $numSus));
    }

    public static function getParticularData($numSus)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT P.NUM_SUS, P.NUM_NIS, P.NAME, P.SOCIAL_NAME, P.BIRTH_DATE
              FROM TB_CITIZEN AS CZ 
              INNER JOIN TB_PERSONAL_DATA AS PD ON CZ.ID_PERSONAL_DATA = PD.PERSONAL_DATA_ID
              INNER JOIN TB_PARTICULAR AS P ON PD.ID_PARTICULAR = P.PARTICULAR_ID 
              WHERE P.NUM_SUS = :NUM_SUS
              LIMIT 1";
        return $db->select($query, array(":NUM_SUS" => $numSus));
    }

    public static function getAllParticularInfo()
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT P.NUM_SUS, P.NAME, P.BIRTH_DATE,
                        C.PHONE, C.EMAIL
                FROM TB_CITIZEN AS CZ 
                INNER JOIN TB_PERSONAL_DATA AS PD ON CZ.ID_PERSONAL_DATA = PD.PERSONAL_DATA_ID
                INNER JOIN TB_PARTICULAR AS P ON PD.ID_PARTICULAR = P.PARTICULAR_ID
                INNER JOIN TB_CONTACT AS C ON PD.ID_CONTACT = C.CONTACT_ID";
        return $db->select($query);
    }

    public static function getAll($numSus = false)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT C.ID_PERSONAL_DATA, C.ID_SOCIAL_DEMOGRAPHIC, C.ID_HEALTH_CONDITIONS, C.ID_STREET_SITUATION
                  FROM TB_CITIZEN AS C";

        if($numSus){
            $query .= " WHERE C.ID_AGENT = (SELECT AG.ID FROM TB_AGENT AS AG WHERE AG.NUM_SUS = :NUM_SUS)";
            $ids = $db->select($query, array(":NUM_SUS" => $numSus));
        } else {
            $ids = $db->select($query);
        }

        $citizens = array();
        foreach ($ids as $id) {

            $values = PersonalDataPersistence::getById($db, $id["ID_" . PersonalDataModel::PERSONAL_DATA])[0];
            $personalData = self::getPersonalData($values);

            $values = SocialDemographicPersistence::getById($db, $id["ID_" . SocialDemographicModel::SOCIAL_DEMOGRAPHIC])[0];
            $socialDemographic = self::getSocialDemographic($values);

            $values = HealthConditionsPersistence::getById($db, $id["ID_" . HealthConditionsModel::HEALTH_CONDITIONS])[0];
            $healthConditions = self::getHealthConditions($values);

            $values = StreetSituationPersistence::getById($db, $id["ID_" . StreetSituationModel::STREET_SITUATION])[0];
            $streetValues = self::getStreetSituation($values);

            array_push($citizens, array(PersonalDataModel::PERSONAL_DATA => $personalData,
                SocialDemographicModel::SOCIAL_DEMOGRAPHIC => $socialDemographic,
                HealthConditionsModel::HEALTH_CONDITIONS => $healthConditions,
                StreetSituationModel::STREET_SITUATION => $streetValues));
        }
        return $citizens;
    }


    public static function insert($numSus, CitizenModel $citizen)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_CITIZEN (ID_AGENT, ID_PERSONAL_DATA, ID_SOCIAL_DEMOGRAPHIC, ID_HEALTH_CONDITIONS, ID_STREET_SITUATION) 
                  VALUES(:ID_AGENT, :ID_PERSONAL_DATA, :ID_SOCIAL_DEMOGRAPHIC, :ID_HEALTH_CONDITIONS, :ID_STREET_SITUATION)";
        $ids = $citizen->save($db);
        if (in_array(false, $ids)) {
            return false;
        }
        $agent = AgentsPersistence::get($numSus);
        $ids[":ID_AGENT"] = $agent['ID'];
        return $db->insert($query, $ids);
    }

    public static function insertAll()
    {

    }


    private function getPersonalData(array $array)
    {
        $particular = array(Particular::NUM_SUS => $array[Particular::NUM_SUS],
            Particular::NUM_NIS => $array[Particular::NUM_NIS],
            Particular::NAME => $array[Particular::NAME],
            Particular::SOCIAL_NAME => $array[Particular::SOCIAL_NAME],
            Particular::BIRTH_DATE => $array[Particular::BIRTH_DATE]);
        $mother = array(Mother::KNOWN => $array[Mother::KNOWN],
            Mother::NAME => $array[Mother::MOTHER . "_" . Mother::NAME]);
        $responsible = array(Responsible::RESPONSIBLE => $array[Responsible::RESPONSIBLE],
            Responsible::NUM_SUS => $array["RESP_" . Responsible::NUM_SUS],
            Responsible::BIRTH_DATE => $array["RESP_" . Responsible::BIRTH_DATE]);
        $genderRace = array(GenderAndRace::GENDER => $array[GenderAndRace::GENDER],
            GenderAndRace::RACE => $array[GenderAndRace::RACE],);
        $nationality = array(Nationality::NATIONALITY => $array[Nationality::NATIONALITY],
            Nationality::NATION_BIRTH => $array[Nationality::NATION_BIRTH],
            Nationality::UF => $array[Nationality::UF],
            Nationality::CITY => $array[Nationality::CITY]);
        $contact = array(Contact::PHONE => $array[Contact::PHONE],
            Contact::EMAIL => $array[Contact::EMAIL]);

        return array(Particular::PARTICULAR => $particular, Mother::MOTHER => $mother,
            Responsible::RESPONSIBLE => $responsible, GenderAndRace::GENDER_RACE => $genderRace,
            Nationality::NATIONALITY => $nationality, Contact::CONTACT => $contact);
    }

    private function getSocialDemographic(array $array)
    {
        $deficiency = array(Deficiency::DEFICIENCY => $array[Deficiency::DEFICIENCY],
            Deficiency::HEARING => $array[Deficiency::HEARING],
            Deficiency::INTELLECTUAL => $array[Deficiency::INTELLECTUAL],
            Deficiency::PHYSICAL => $array[Deficiency::PHYSICAL],
            Deficiency::VISUAL => $array[Deficiency::VISUAL],
            Deficiency::ANOTHER => $array[Deficiency::ANOTHER]);
        $sexualOrientation = array(SexualOrientation::SEXUAL_ORIENTATION => $array[SexualOrientation::SEXUAL_ORIENTATION],
            SexualOrientation::DESCRIPTION => $array["SEX_" . SexualOrientation::DESCRIPTION]);
        $communityTraditional = array(CommunityTraditional::COMMUNITY_TRADITIONAL => $array[CommunityTraditional::COMMUNITY_TRADITIONAL],
            CommunityTraditional::DESCRIPTION => $array["COMM_" . CommunityTraditional::DESCRIPTION]);
        $healthGroup = array(HealthGroup::CAREGIVER => $array[HealthGroup::CAREGIVER],
            HealthGroup::COMMUNITY_GROUP => $array[HealthGroup::COMMUNITY_GROUP],
            HealthGroup::HEALTH_PLAN => $array[HealthGroup::HEALTH_PLAN]);
        $educationEmployment = array(EducationEmployment::SCHOOL => $array[EducationEmployment::SCHOOL],
            EducationEmployment::OCCUPATION => $array[EducationEmployment::OCCUPATION],
            EducationEmployment::EDUCATION => $array[EducationEmployment::EDUCATION],
            EducationEmployment::EMPLOYMENT => $array[EducationEmployment::EMPLOYMENT]);

        return array(SocialDemographicModel::KIDS_09 => $array[SocialDemographicModel::KIDS_09],
            SocialDemographicModel::KINSHIP => $array[SocialDemographicModel::KINSHIP],
            Deficiency::DEFICIENCY => $deficiency, SexualOrientation::SEXUAL_ORIENTATION => $sexualOrientation,
            CommunityTraditional::COMMUNITY_TRADITIONAL => $communityTraditional, HealthGroup::HEALTH_GROUP => $healthGroup,
            EducationEmployment::EDUCATION_EMPLOYMENT => $educationEmployment);
    }

    private static function getHealthConditions(array $values)
    {
        $pregnant = array(Pregnant::PREGNANT => $values[Pregnant::PREGNANT],
            Pregnant::DESCRIPTION => $values[Pregnant::PREGNANT . "_" . Pregnant::DESCRIPTION]);
        $diseases = array(Diseases::SMOKER => $values[Diseases::SMOKER],
            Diseases::ALCOHOL => $values[Diseases::ALCOHOL],
            Diseases::DRUGS => $values[Diseases::DRUGS],
            Diseases::DIABETES => $values[Diseases::DIABETES],
            Diseases::AVC => $values[Diseases::AVC],
            Diseases::HEART_ATTACK => $values[Diseases::HEART_ATTACK],
            Diseases::LEPROSY => $values[Diseases::LEPROSY],
            Diseases::TUBERCULOSIS => $values[Diseases::TUBERCULOSIS],
            Diseases::CANCER => $values[Diseases::CANCER],
            Diseases::IN_BED => $values[Diseases::IN_BED],
            Diseases::DOMICILED => $values[Diseases::DOMICILED],
            Diseases::OTHER_PRACTICES => $values[Diseases::OTHER_PRACTICES],
            Diseases::MENTAL_HEALTH => $values[Diseases::MENTAL_HEALTH],
            Diseases::HYPERTENSION => $values[Diseases::HYPERTENSION]);
        $heartDisease = array(HeartDisease::HEART_DISEASE => $values[HeartDisease::HEART_DISEASE],
            HeartDisease::INSUFFICIENCY => $values[HeartDisease::HEART_DISEASE . "_" . HeartDisease::INSUFFICIENCY],
            HeartDisease::DONT_KNOWN => $values[HeartDisease::HEART_DISEASE . "_" . HeartDisease::DONT_KNOWN],
            HeartDisease::ANOTHER => $values[HeartDisease::HEART_DISEASE . "_" . HeartDisease::ANOTHER]);
        $kidneyDisease = array(KidneyDisease::KIDNEY_DISEASE => $values[KidneyDisease::KIDNEY_DISEASE],
            KidneyDisease::INSUFFICIENCY => $values[KidneyDisease::KIDNEY_DISEASE . "_" . KidneyDisease::INSUFFICIENCY],
            KidneyDisease::DONT_KNOWN => $values[KidneyDisease::KIDNEY_DISEASE . "_" . KidneyDisease::DONT_KNOWN],
            KidneyDisease::ANOTHER => $values[KidneyDisease::KIDNEY_DISEASE . "_" . KidneyDisease::ANOTHER]);
        $respiratoryDisease = array(RespiratoryDisease::RESPIRATORY_DISEASE => $values[RespiratoryDisease::RESPIRATORY_DISEASE],
            RespiratoryDisease::ASTHMA => $values[RespiratoryDisease::ASTHMA],
            RespiratoryDisease::EMPHYSEMA => $values[RespiratoryDisease::EMPHYSEMA],
            RespiratoryDisease::DONT_KNOWN => $values[RespiratoryDisease::RESPIRATORY_DISEASE . "_" .RespiratoryDisease::DONT_KNOWN],
            RespiratoryDisease::ANOTHER => $values[RespiratoryDisease::RESPIRATORY_DISEASE . "_" .RespiratoryDisease::ANOTHER]);
        $interment = array(Interment::INTERMENT => $values[Interment::INTERMENT],
            Interment::DESCRIPTION => $values[Interment::INTERMENT . "_" . Interment::DESCRIPTION]);
        $plant = array(Plant::PLANT => $values[Plant::PLANT],
            Plant::DESCRIPTION => $values[Plant::PLANT . "_" . Plant::DESCRIPTION]);

        return array(HealthConditionsModel::WEIGHT => $values[HealthConditionsModel::WEIGHT],
            Pregnant::PREGNANT => $pregnant, Diseases::DISEASES => $diseases, HeartDisease::HEART_DISEASE => $heartDisease,
            KidneyDisease::KIDNEY_DISEASE => $kidneyDisease, RespiratoryDisease::RESPIRATORY_DISEASE => $respiratoryDisease,
            Interment::INTERMENT => $interment, Plant::PLANT => $plant);
    }

    private static function getStreetSituation(array $values)
    {
        $streetSituation = array(StreetSituation::STREET => $values[StreetSituation::STREET],
            StreetSituation::DESCRIPTION => $values[StreetSituation::DESCRIPTION ."_" . StreetSituation::STREET]);
        $feeding = array(Feeding::FOOD_PER_DAY => $values[Feeding::FOOD_PER_DAY],
            Feeding::RESTAURANT => $values[Feeding::RESTAURANT],
            Feeding::RESTAURANT_DONATION => $values[Feeding::RESTAURANT_DONATION],
            Feeding::RELIGIOUS_GROUP => $values[Feeding::RELIGIOUS_GROUP],
            Feeding::POPULAR => $values[Feeding::POPULAR],
            Feeding::ANOTHER => $values[Feeding::ANOTHER . "_" . Feeding::FEEDING]);
        $anotherInstitution = array(AnotherInstitution::ANOTHER_INSTITUTION => $values[AnotherInstitution::ANOTHER_INSTITUTION],
            AnotherInstitution::DESCRIPTION => $values[AnotherInstitution::DESCRIPTION . "_INSTITUTION" ]);
        $familyVisit = array(FamilyVisit::FAMILY_VISIT => $values[FamilyVisit::FAMILY_VISIT],
            FamilyVisit::DESCRIPTION => $values[FamilyVisit::DESCRIPTION . "_" . FamilyVisit::FAMILY_VISIT]);
        $hygiene = array(Hygiene::HYGIENE => $values[Hygiene::HYGIENE],
            Hygiene::BATH => $values[Hygiene::BATH],
            Hygiene::SANITARY => $values[Hygiene::SANITARY],
            Hygiene::ORAL => $values[Hygiene::ORAL],
            Hygiene::ANOTHER => $values[Hygiene::ANOTHER . "_" . Hygiene::HYGIENE]);

        return array(StreetSituationModel::BENEFIT => $values[StreetSituationModel::BENEFIT],
            StreetSituationModel::FAMILY => $values[StreetSituationModel::FAMILY],
            StreetSituation::STREET => $streetSituation,
            Feeding::FEEDING => $feeding, AnotherInstitution::ANOTHER_INSTITUTION => $anotherInstitution,
            FamilyVisit::FAMILY_VISIT => $familyVisit, Hygiene::HYGIENE => $hygiene);
    }
}