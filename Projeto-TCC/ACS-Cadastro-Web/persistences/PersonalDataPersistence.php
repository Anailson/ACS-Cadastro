<?php

class PersonalDataPersistence
{
    static function insert(AcsDataBase $db, PersonalDataModel $personalData)
    {

        $query = array(PersonalDataModel::PERSONAL_DATA => self::queryPersonal(),
            Particular::PARTICULAR => self::queryParticular(),
            Mother::MOTHER => self::queryMother(),
            Responsible::RESPONSIBLE => self::queryResponsible(),
            GenderAndRace::GENDER_RACE => self::queryGenderAndRace(),
            Nationality::NATIONALITY => self::queryNationality(),
            Contact::CONTACT => self::queryContact());
        return $personalData->save($db, $query);
    }

    private static function queryPersonal()
    {
        return "INSERT INTO TB_PERSONAL_DATA(ID_PARTICULAR, ID_MOTHER, ID_RESPONSIBLE, ID_GENDER_RACE, ID_NATIONALITY, ID_CONTACT) 
                      VALUES (:ID_PARTICULAR, :ID_MOTHER, :ID_RESPONSIBLE, :ID_GENDER_RACE, :ID_NATIONALITY, :ID_CONTACT)";
    }

    private static function queryParticular()
    {
        return "INSERT INTO TB_PARTICULAR (NUM_SUS, NUM_NIS, NAME, SOCIAL_NAME, BIRTH_DATE)
                  VALUES(:NUM_SUS, :NUM_NIS, :NAME, :SOCIAL_NAME, :BIRTH_DATE)";
    }

    private static function queryMother()
    {
        return "INSERT INTO TB_MOTHER (KNOWN, NAME) VALUES(:KNOWN, :NAME)";
    }

    private static function queryResponsible()
    {
        return "INSERT INTO TB_RESPONSIBLE(RESPONSIBLE, NUM_SUS, BIRTH_DATE) 
                  VALUES(:RESPONSIBLE, :NUM_SUS, :BIRTH_DATE)";
    }

    private static function queryGenderAndRace()
    {
        return "INSERT INTO TB_GENDER_RACE(GENDER, RACE) VALUES(:GENDER, :RACE)";
    }

    private static function queryNationality()
    {
        return "INSERT INTO TB_NATIONALITY(NATIONALITY, NATION_BIRTH, UF, CITY) 
                  VALUES(:NATIONALITY, :NATION_BIRTH, :UF, :CITY)";
    }

    private static function queryContact()
    {
        return "INSERT INTO TB_CONTACT(PHONE, EMAIL) VALUES(:PHONE, :EMAIL)";
    }
}