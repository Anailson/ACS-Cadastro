<?php
if (!@include "subModels/Particular.php") {
    include "../subModels/Particular.php";
}
if (!@include "subModels/Mother.php") {
    include "../subModels/Mother.php";
}
if (!@include "subModels/Responsible.php") {
    include "../subModels/Responsible.php";
}
if (!@include "subModels/GenderAndRace.php") {
    include "../subModels/GenderAndRace.php";
}
if (!@include "subModels/Nationality.php") {
    include "../subModels/Nationality.php";
}
if (!@include "subModels/Contact.php") {
    include "../subModels/Contact.php";
}

class PersonalDataPersistence
{
    static function insert(AcsDataBase $db, array $personalData)
    {
        $values = array();
        $values[":ID_PARTICULAR"] = self::insertParticular($db, Particular::getFromArray($personalData['PARTICULAR']));
        $values[":ID_MOTHER"] = self::insertMother($db, Mother::getFromArray($personalData['MOTHER']));
        $values[":ID_RESPONSIBLE"] = self::insertResponsible($db, Responsible::getFromArray($personalData['RESPONSIBLE']));
        $values[":ID_GENDER_RACE"] = self::insertGenderAndRace($db, GenderAndRace::getFromArray($personalData['GENDER_RACE']));
        $values[":ID_NATIONALITY"] = self::insertNationality($db, Nationality::getFromArray($personalData['NATIONALITY']));
        $values[":ID_CONTACT"] = self::insertContact($db, Contact::getFromArray($personalData['CONTACT']));

        if(in_array(0, $values)){
            return false;
        }
        $query = "INSERT INTO TB_PERSONAL_DATA(ID_PARTICULAR, ID_MOTHER, ID_RESPONSIBLE, ID_GENDER_RACE, ID_NATIONALITY, ID_CONTACT) 
                      VALUES (:ID_PARTICULAR, :ID_MOTHER, :ID_RESPONSIBLE, :ID_GENDER_RACE, :ID_NATIONALITY, :ID_CONTACT)";
        return $db->insert($query, $values);
    }

    private static function insertParticular(AcsDataBase $db, Particular $particular)
    {
        $query = "INSERT INTO TB_PARTICULAR (NUM_SUS, NUM_NIS, NAME, SOCIAL_NAME, BIRTH_DATE)
                  VALUES(:NUM_SUS, :NUM_NIS, :NAME, :SOCIAL_NAME, :BIRTH_DATE)";
        return $particular->save($db, $query);
    }

    private static function insertMother(AcsDataBase $db, Mother $mother)
    {
        $query = "INSERT INTO TB_MOTHER (KNOWN, NAME) VALUES(:KNOWN, :NAME)";
        return $mother->save($db, $query);
    }

    private static function insertResponsible($db, Responsible $responsible)
    {
        $query = "INSERT INTO TB_RESPONSIBLE(RESPONSIBLE, NUM_SUS, BIRTH_DATE) 
                  VALUES(:RESPONSIBLE, :NUM_SUS, :BIRTH_DATE)";
        return $responsible->save($db, $query);
    }

    private static function insertGenderAndRace($db, GenderAndRace $genderAndRace)
    {
        $query = "INSERT INTO TB_GENDER_RACE(GENDER, RACE) VALUES(:GENDER, :RACE)";
        return $genderAndRace->save($db, $query);
    }

    private static function insertNationality($db, Nationality $nationality)
    {
        $query = "INSERT INTO TB_NATIONALITY(NATIONALITY, NATION_BIRTH, UF, CITY) 
                  VALUES(:NATIONALITY, :NATION_BIRTH, :UF, :CITY)";
        return $nationality->save($db, $query);
    }

    private static function insertContact($db, Contact $contact)
    {
        $query = "INSERT INTO TB_CONTACT(PHONE, EMAIL) 
                  VALUES(:PHONE, :EMAIL)";
        return $contact->save($db, $query);
    }
}