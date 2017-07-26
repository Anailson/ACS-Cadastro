<?php

class SocialDemographicPersistence
{
    public static function getById(AcsDataBase $db, $id)
    {
        $query = "SELECT SD.KINSHIP, SD.KIDS_09,
                    D.DEFICIENCY, D.HEARING, D.VISUAL, D.INTELLECTUAL, D.PHYSICAL, D.ANOTHER,
                    S.SEXUAL_ORIENTATION, S.DESCRIPTION AS SEX_DESCRIPTION,
                    C.COMMUNITY_TRADITIONAL, C.DESCRIPTION AS COMM_DESCRIPTION,
                    H.CAREGIVER, H.COMMUNITY_GROUP, H.HEALTH_PLAN,
                    E.SCHOOL, E.OCCUPATION, E.EDUCATION, E.EMPLOYMENT
                FROM tb_social_demographic SD
                INNER JOIN tb_deficiency AS D ON SD.ID_DEFICIENCY = D.DEFICIENCY_ID
                INNER JOIN tb_sexual_orientation AS S ON SD.ID_SEXUAL_ORIENTATION = S.SEXUAL_ORIENTATION_ID
                INNER JOIN tb_community_traditional AS C ON SD.ID_COMMUNITY_TRADITIONAL = C.COMMUNITY_TRADITIONAL_ID
                INNER JOIN tb_health_group AS H ON SD.ID_HEALTH_GROUP = H.HEALTH_GROUP_ID
                INNER JOIN tb_education_employment AS E ON SD.ID_EDUCATION_EMPLOYMENT = E.EDUCATION_EMPLOYMENT_ID
                WHERE SD.SOCIAL_DEMOGRAPHIC_ID = :SOCIAL_DEMOGRAPHIC";

        return $db->select($query, array(":" . SocialDemographicModel::SOCIAL_DEMOGRAPHIC => $id));
    }
    static function insert(AcsDataBase $db, SocialDemographicModel $demographicModel)
    {
        $query = array(SocialDemographicModel::SOCIAL_DEMOGRAPHIC => self::querySocialDemographic(),
            EducationEmployment::EDUCATION_EMPLOYMENT => self::queryEducationEmployment(),
            HealthGroup::HEALTH_GROUP => self::queryHealthGroup(),
            CommunityTraditional::COMMUNITY_TRADITIONAL => self::queryCommunityTraditional(),
            SexualOrientation::SEXUAL_ORIENTATION => self::querySexualOrientation(),
            Deficiency::DEFICIENCY => self::queryDeficiency());

        return $demographicModel->save($db, $query);
    }

    private static function querySocialDemographic()
    {
        return "INSERT INTO TB_SOCIAL_DEMOGRAPHIC (KINSHIP, KIDS_09, ID_DEFICIENCY, ID_SEXUAL_ORIENTATION, 
                  ID_COMMUNITY_TRADITIONAL, ID_HEALTH_GROUP, ID_EDUCATION_EMPLOYMENT) 
                VALUES(:KINSHIP, :KIDS_09, :ID_DEFICIENCY, :ID_SEXUAL_ORIENTATION, 
                  :ID_COMMUNITY_TRADITIONAL, :ID_HEALTH_GROUP, :ID_EDUCATION_EMPLOYMENT)";
    }

    private static function queryEducationEmployment()
    {
        return "INSERT INTO TB_EDUCATION_EMPLOYMENT(SCHOOL, OCCUPATION, EDUCATION, EMPLOYMENT)
                  VALUES(:SCHOOL, :OCCUPATION, :EDUCATION, :EMPLOYMENT)";
    }

    private static function queryHealthGroup()
    {
        return "INSERT INTO TB_HEALTH_GROUP (CAREGIVER, COMMUNITY_GROUP, HEALTH_PLAN) 
                  VALUES (:CAREGIVER, :COMMUNITY_GROUP, :HEALTH_PLAN)";
    }

    private static function queryCommunityTraditional()
    {
        return "INSERT INTO TB_COMMUNITY_TRADITIONAL(COMMUNITY_TRADITIONAL, DESCRIPTION) 
                  VALUES (:COMMUNITY_TRADITIONAL, :DESCRIPTION)";
    }

    private static function querySexualOrientation()
    {
        return "INSERT INTO TB_SEXUAL_ORIENTATION(SEXUAL_ORIENTATION, DESCRIPTION) 
                  VALUES (:SEXUAL_ORIENTATION, :DESCRIPTION)";
    }

    private static function queryDeficiency()
    {
        return "INSERT INTO TB_DEFICIENCY(DEFICIENCY, HEARING, VISUAL, INTELLECTUAL, PHYSICAL, ANOTHER) 
                  VALUES (:DEFICIENCY, :HEARING, :VISUAL, :INTELLECTUAL, :PHYSICAL, :ANOTHER)";
    }
}