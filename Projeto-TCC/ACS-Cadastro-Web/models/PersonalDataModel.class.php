<?php

if(!@include "../subModels/Particular.php") {
    include "subModels/Particular.php";
}
if(!@include "../subModels/Mother.php") {
    include "subModels/Mother.php";
}
if(!@include "../subModels/Responsible.php") {
    include "subModels/Responsible.php";
}
if(!@include "../subModels/GenderAndRace.php") {
    include "subModels/GenderAndRace.php";
}
if(!@include "../subModels/Nationality.php") {
    include "subModels/Nationality.php";
}
if(!@include "../subModels/Contact.php") {
    include "subModels/Contact.php";
}

class PersonalDataModel
{
    const PERSONAL_DATA = "PERSONAL_DATA";

    private $particular, $mother, $responsible, $genderAndRace, $nationality, $contact;

    public function __construct(Particular $particular, Mother $mother, Responsible $responsible,
                                GenderAndRace $genderAndRace, Nationality $nationality, Contact $contact)
    {
        $this->particular = $particular;
        $this->mother = $mother;
        $this->responsible = $responsible;
        $this->genderAndRace = $genderAndRace;
        $this->nationality = $nationality;
        $this->contact = $contact;
    }

    public function getValuesToDB()
    {
        $values[Particular::PARTICULAR] = $this->particular->getValuesToDB();
        $values[Mother::MOTHER] = $this->mother->getValuesToDB();
        $values[Responsible::RESPONSIBLE] = $this->responsible->getValuesToDB();
        $values[GenderAndRace::GENDER_RACE] = $this->genderAndRace->getValuesToDB();
        $values[Nationality::NATIONALITY] = $this->nationality->getValuesToDB();
        $values[Contact::CONTACT] = $this->contact->getValuesToDB();
        return $values;
    }

    public static function getByArray(array $array)
    {
        return new PersonalDataModel(Particular::getFromArray($array[Particular::PARTICULAR]),
            Mother::getFromArray($array[Mother::MOTHER]),
            Responsible::getFromArray($array[Responsible::RESPONSIBLE]),
            GenderAndRace::getFromArray($array[GenderAndRace::GENDER_RACE]),
            Nationality::getFromArray($array[Nationality::NATIONALITY]),
            Contact::getFromArray($array[Contact::CONTACT]));
    }
}