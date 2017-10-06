<?php

class PersonalDataPersistence
{
    static function getById(AcsDataBase $db, $id)
    {
        $query = "SELECT P.NUM_SUS, P.NUM_NIS, P.NAME, P.SOCIAL_NAME, P.BIRTH_DATE,
                    M.KNOWN, M.NAME AS MOTHER_NAME,
                    R.RESPONSIBLE, R.NUM_SUS AS RESP_NUM_SUS, R.BIRTH_DATE AS RESP_BIRTH_DATE,
                    G.GENDER, G.RACE,
                    N.NATIONALITY, N.NATION_BIRTH, N.UF, N.CITY,
                    C.PHONE, C.EMAIL
                FROM tb_personal_data PD
                INNER JOIN tb_particular AS P ON PD.ID_PARTICULAR = P.PARTICULAR_ID
                INNER JOIN tb_mother AS M ON PD.ID_MOTHER = M.MOTHER_ID
                INNER JOIN tb_responsible AS R ON PD.ID_RESPONSIBLE = R.RESPONSIBLE_ID
                INNER JOIN tb_gender_race AS G ON PD.ID_GENDER_RACE = G.GENDER_RACE_ID
                INNER JOIN tb_nationality AS N ON PD.ID_NATIONALITY = N.NATIONALITY_ID
                INNER JOIN tb_contact AS C ON PD.ID_CONTACT = C.CONTACT_ID
                WHERE PD.PERSONAL_DATA_ID = :PERSONAL_DATA";
        $param = array(":" . PersonalDataModel::PERSONAL_DATA => $id);
        return $db->select($query, $param);
    }

    static function insert(AcsDataBase $db, PersonalDataModel $personalData)
    {
        $values = $personalData->getValuesToDB();
        $ids[":ID_" . Particular::PARTICULAR] = $db->insert(self::queryParticular(), $values[Particular::PARTICULAR]);
        $ids[":ID_" . Mother::MOTHER] = $db->insert(self::queryMother(), $values[Mother::MOTHER]);
        $ids[":ID_" . Responsible::RESPONSIBLE] = $db->insert(self::queryResponsible(), $values[Responsible::RESPONSIBLE]);
        $ids[":ID_" . GenderAndRace::GENDER_RACE] = $db->insert(self::queryGenderAndRace(), $values[GenderAndRace::GENDER_RACE]);
        $ids[":ID_" . Nationality::NATIONALITY] = $db->insert(self::queryNationality(), $values[Nationality::NATIONALITY]);
        $ids[":ID_" . Contact::CONTACT] = $db->insert(self::queryContact(), $values[Contact::CONTACT]);

        if(in_array(false, $ids)){
            return false;
        }
        return $db->insert(self::queryPersonal(), $ids);
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