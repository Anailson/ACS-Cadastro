<?php

if(!@include "subModels/EducationEmployment.php"){
    include "../subModels/EducationEmployment.php";
}
if(!@include "subModels/HealthGroup.php"){
    include "../subModels/HealthGroup.php";
}
if(!@include "subModels/CommunityTraditional.php"){
    include "../subModels/CommunityTraditional.php";
}
if(!@include "subModels/SexualOrientation.php"){
    include "../subModels/SexualOrientation.php";
}
if(!@include "subModels/Deficiency.php"){
    include "../subModels/Deficiency.php";
}

class SocialDemographicModel
{
    const KINSHIP = "KINSHIP";
    const KIDS_09 = "KIDS_09";
    const SOCIAL_DEMOGRAPHIC = "SOCIAL_DEMOGRAPHIC";

    private $kinship, $kids09, $educationEmployment, $healthGroup, $community, $orientation, $deficiency;

    public function __construct($kinship, $kids09, EducationEmployment $educationEmployment, HealthGroup $healthGroup,
                                CommunityTraditional $community, SexualOrientation $orientation, Deficiency $deficiency)
    {
        $this->kinship = $kinship;
        $this->kids09 = $kids09;
        $this->educationEmployment = $educationEmployment;
        $this->healthGroup = $healthGroup;
        $this->community = $community;
        $this->orientation = $orientation;
        $this->deficiency = $deficiency;
    }

    public function save(AcsDataBase $db, array $query)
    {
        $values[":ID_" . EducationEmployment::EDUCATION_EMPLOYMENT] = $this->educationEmployment->save($db, $query[EducationEmployment::EDUCATION_EMPLOYMENT]);
        $values[":ID_" . HealthGroup::HEALTH_GROUP] = $this->healthGroup->save($db, $query[HealthGroup::HEALTH_GROUP]);
        $values[":ID_" . CommunityTraditional::COMMUNITY_TRADITIONAL] = $this->community->save($db, $query[CommunityTraditional::COMMUNITY_TRADITIONAL]);
        $values[":ID_" . SexualOrientation::SEXUAL_ORIENTATION] = $this->orientation->save($db, $query[SexualOrientation::SEXUAL_ORIENTATION]);
        $values[":ID_" . Deficiency::DEFICIENCY] = $this->deficiency->save($db, $query[Deficiency::DEFICIENCY]);

        if(in_array(false, $values)){
            return false;
        }
        $values[":" . self::KINSHIP] = $this->kinship;
        $values[":" . self::KIDS_09] = $this->kids09;
        return $db->insert($query[self::SOCIAL_DEMOGRAPHIC], $values);
    }

    public static function getFromArray(array $array)
    {
        return new SocialDemographicModel($array[self::KINSHIP], $array[self::KIDS_09],
            EducationEmployment::getFromArray($array[EducationEmployment::EDUCATION_EMPLOYMENT]),
            HealthGroup::getFromArray($array[HealthGroup::HEALTH_GROUP]),
            CommunityTraditional::getFromArray($array[CommunityTraditional::COMMUNITY_TRADITIONAL]),
            SexualOrientation::getFromArray($array[SexualOrientation::SEXUAL_ORIENTATION]),
            Deficiency::getFromArray($array[Deficiency::DEFICIENCY]));
    }
}