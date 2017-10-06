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

    public function getValuesToDB()
    {
        $values[EducationEmployment::EDUCATION_EMPLOYMENT] = $this->educationEmployment->getValuesToDB();
        $values[HealthGroup::HEALTH_GROUP] = $this->healthGroup->getValuesToDB();
        $values[CommunityTraditional::COMMUNITY_TRADITIONAL] = $this->community->getValuesToDB();
        $values[SexualOrientation::SEXUAL_ORIENTATION] = $this->orientation->getValuesToDB();
        $values[Deficiency::DEFICIENCY] = $this->deficiency->getValuesToDB();
        $values[self::KINSHIP] = $this->kinship;
        $values[self::KIDS_09] = $this->kids09;
        return $values;
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