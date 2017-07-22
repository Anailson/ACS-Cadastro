<?php

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

    public function save(AcsDataBase $db, array $query)
    {
        $ids[":ID_" . Particular::PARTICULAR] = $this->particular->save($db, $query[Particular::PARTICULAR]);
        $ids[":ID_" . Mother::MOTHER] = $this->mother->save($db, $query[Mother::MOTHER]);
        $ids[":ID_" . Responsible::RESPONSIBLE] = $this->responsible->save($db, $query[Responsible::RESPONSIBLE]);
        $ids[":ID_" . GenderAndRace::GENDER_RACE] = $this->genderAndRace->save($db, $query[GenderAndRace::GENDER_RACE]);
        $ids[":ID_" . Nationality::NATIONALITY] = $this->nationality->save($db, $query[Nationality::NATIONALITY]);
        $ids[":ID_" . Contact::CONTACT] = $this->contact->save($db, $query[Contact::CONTACT]);

        if(in_array(false, $ids)){
            return false;
        }
        return $db->insert($query[self::PERSONAL_DATA], $ids);
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