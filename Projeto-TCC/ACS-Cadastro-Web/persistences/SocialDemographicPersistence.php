<?php

if (!@include "subModels/EducationEmployment.php") {
    include "../subModels/EducationEmployment.php";
}
if (!@include "subModels/HealthGroup.php") {
    include "../subModels/HealthGroup.php";
}
if (!@include "subModels/CommunityTraditional.php") {
    include "../subModels/CommunityTraditional.php";
}
if (!@include "subModels/SexualOrientation.php") {
    include "../subModels/SexualOrientation.php";
}
if (!@include "subModels/Deficiency.php") {
    include "../subModels/Deficiency.php";
}

class SocialDemographic
{
    static function insert(AcsDataBase $db, $socialDemographic)
    {
        $values = array();
        $values[':KINSHIP'] = $socialDemographic['KINSHIP'];
        $values[':KIDS_09'] = $socialDemographic['KIDS_09'];
        $values[":ID_DEFICIENCY"] = self::insertDeficiency($db, Deficiency::getFromArray($socialDemographic['DEFICIENCY']));
        $values[":ID_EDUCATION_EMPLOYMENT"] = self::insertEducationEmployment($db, EducationEmployment::getFromArray($socialDemographic['EDUCATION_EMPLOYMENT']));
        $values[":ID_HEALTH_GROUP"] = self::insertHealthGroup($db, HealthGroup::getFromArray($socialDemographic['HEALTH_GROUP']));
        $values[":ID_COMMUNITY_TRADITIONAL"] = self::insertCommunityTraditional($db, CommunityTraditional::getFromArray($socialDemographic['COMMUNITY_TRADITIONAL']));
        $values[":ID_SEXUAL_ORIENTATION"] = self::insertSexualOrientation($db, SexualOrientation::getFromArray($socialDemographic['SEXUAL_ORIENTATION']));

        if (in_array(-1, $values)) {
            return false;
        }

        $query = "INSERT INTO TB_SOCIAL_DEMOGRAPHIC (KINSHIP, KIDS_09, ID_DEFICIENCY, ID_SEXUAL_ORIENTATION, 
                  ID_COMMUNITY_TRADITIONAL, ID_HEALTH_GROUP, ID_EDUCATION_EMPLOYMENT) 
                VALUES(:KINSHIP, :KIDS_09, :ID_DEFICIENCY, :ID_SEXUAL_ORIENTATION, 
                  :ID_COMMUNITY_TRADITIONAL, :ID_HEALTH_GROUP, :ID_EDUCATION_EMPLOYMENT)";
        return $db->insert($query, $values);
    }

    private static function insertEducationEmployment(AcsDataBase $db, EducationEmployment $educationEmployment)
    {
        $query = "INSERT INTO TB_EDUCATION_EMPLOYMENT(SCHOOL, OCCUPATION, EDUCATION, EMPLOYMENT)
                  VALUES(:SCHOOL, :OCCUPATION, :EDUCATION, :EMPLOYMENT)";
        return $educationEmployment->save($db, $query);
    }

    private static function insertHealthGroup(AcsDataBase $db, HealthGroup $healthGroup)
    {
        $query = "INSERT INTO TB_HEALTH_GROUP (CAREGIVER, COMMUNITY_GROUP, HEALTH_PLAN) 
                  VALUES (:CAREGIVER, :COMMUNITY_GROUP, :HEALTH_PLAN)";
        return $healthGroup->save($db, $query);
    }

    private static function insertCommunityTraditional(AcsDataBase $db, CommunityTraditional $community)
    {
        $query = "INSERT INTO TB_COMMUNITY_TRADITIONAL(COMMUNITY_TRADITIONAL, DESCRIPTION) 
                  VALUES (:COMMUNITY_TRADITIONAL, :DESCRIPTION)";
        return $community->save($db, $query);
    }

    private static function insertSexualOrientation(AcsDataBase $db, SexualOrientation $sexual)
    {
        $query = "INSERT INTO TB_SEXUAL_ORIENTATION(SEXUAL_ORIENTATION, DESCRIPTION) 
                  VALUES (:SEXUAL_ORIENTATION, :DESCRIPTION)";
        return $sexual->save($db, $query);
    }

    private static function insertDeficiency(AcsDataBase $db, Deficiency $deficiency)
    {
        $query = "INSERT INTO TB_DEFICIENCY(DEFICIENCY, HEARING, VISUAL, INTELLECTUAL, PHYSICAL, ANOTHER) 
                  VALUES (:DEFICIENCY, :HEARING, :VISUAL, :INTELLECTUAL, :PHYSICAL, :ANOTHER)";
        return $deficiency->save($db, $query);
    }
}