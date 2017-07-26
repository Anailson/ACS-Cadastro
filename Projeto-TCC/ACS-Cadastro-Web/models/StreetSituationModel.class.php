<?php
if(!@include "../subModels/StreetSituation.php") {
    include "subModels/StreetSituation.php";
}
if(!@include "../subModels/Feeding.php") {
    include "subModels/Feeding.php";
}
if(!@include "../subModels/AnotherInstitution.php") {
    include "subModels/AnotherInstitution.php";
}
if(!@include "../subModels/FamilyVisit.php") {
    include "subModels/FamilyVisit.php";
}
if(!@include "../subModels/Hygiene.php") {
    include "subModels/Hygiene.php";
}


class StreetSituationModel
{
    const BENEFIT = "BENEFIT";
    const FAMILY = "FAMILY";
    const STREET_SITUATION = "STREET_SITUATION";

    private $streetSituation, $benefit, $family, $feeding, $anotherInstitution, $familyVisit, $hygiene;

    public function __construct(StreetSituation $streetSituation, $benefit, $family, Feeding $feeding,
                                AnotherInstitution $anotherInstitution, FamilyVisit $familyVisit, Hygiene $hygiene)
    {
        $this->streetSituation = $streetSituation;
        $this->benefit = $benefit;
        $this->family = $family;
        $this->feeding = $feeding;
        $this->anotherInstitution = $anotherInstitution;
        $this->familyVisit = $familyVisit;
        $this->hygiene = $hygiene;
    }


    public function save(AcsDataBase $db, $query)
    {
        $values[":ID_" . StreetSituation::STREET] = $this->streetSituation->save($db, $query[StreetSituation::STREET]);
        $values[":ID_" . Feeding::FEEDING] = $this->feeding->save($db, $query[Feeding::FEEDING]);
        $values[":ID_" . AnotherInstitution::ANOTHER_INSTITUTION] = $this->anotherInstitution->save($db, $query[AnotherInstitution::ANOTHER_INSTITUTION]);
        $values[":ID_" . FamilyVisit::FAMILY_VISIT] = $this->familyVisit->save($db, $query[FamilyVisit::FAMILY_VISIT]);
        $values[":ID_" . Hygiene::HYGIENE] = $this->hygiene->save($db, $query[Hygiene::HYGIENE]);

        if(in_array(false, $values)){
            return false;
        }
        $values[":" . self::BENEFIT] = $this->benefit;
        $values[":" . self::FAMILY] = $this->family;
        return $db->insert($query[StreetSituationModel::STREET_SITUATION], $values);
    }

    public static function getFromArray(array $array)
    {
        return new StreetSituationModel(StreetSituation::getFromArray($array[StreetSituation::STREET]),
            $array[self::BENEFIT], $array[self::FAMILY],
            Feeding::getFromArray($array[Feeding::FEEDING]),
            AnotherInstitution::getFromArray($array[AnotherInstitution::ANOTHER_INSTITUTION]),
            FamilyVisit::getFromArray($array[FamilyVisit::FAMILY_VISIT]),
            Hygiene::getFromArray($array[Hygiene::HYGIENE]));
    }
}