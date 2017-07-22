<?php

class StreetSituationPersistence
{

    public static function insert(AcsDataBase $db, StreetSituationModel $streetSituation)
    {
        $query = array(
            StreetSituationModel::STREET_SITUATION => self::queryStreetSituation(),
            StreetSituation::STREET => self::queryStreet(),
            Feeding::FEEDING => self::queryFeeding(),
            AnotherInstitution::ANOTHER_INSTITUTION => self::queryAnotherInstitution(),
            FamilyVisit::FAMILY_VISIT => self::queryFamilyVisit(),
            Hygiene::HYGIENE => self::queryHygiene());
        return $streetSituation->save($db, $query);
    }

    private static function queryStreetSituation()
    {
        return "INSERT INTO TB_STREET_SITUATION(BENEFIT, FAMILY, ID_STREET, ID_FEEDING, 
                    ID_ANOTHER_INSTITUTION, ID_FAMILY_VISIT, ID_HYGIENE) 
                VALUES (:BENEFIT, :FAMILY, :ID_STREET, :ID_FEEDING, :ID_ANOTHER_INSTITUTION, :ID_FAMILY_VISIT, :ID_HYGIENE)";
    }

    private static function queryStreet()
    {
        return "INSERT INTO TB_STREET(STREET, DESCRIPTION) VALUES (:STREET, :DESCRIPTION)";
    }

    private static function queryFeeding()
    {
        return "INSERT INTO TB_FEEDING(FOOD_PER_DAY, RESTAURANT, RESTAURANT_DONATION, RELIGIOUS_GROUP, POPULAR, ANOTHER) 
            VALUES (:FOOD_PER_DAY, :RESTAURANT, :RESTAURANT_DONATION, :RELIGIOUS_GROUP, :POPULAR, :ANOTHER)";
    }

    private static function queryAnotherInstitution()
    {
        return "INSERT INTO TB_ANOTHER_INSTITUATION(ANOTHER_INSTITUTION, DESCRIPTION) VALUES (:ANOTHER_INSTITUTION, :DESCRIPTION)";
    }

    private static function queryFamilyVisit()
    {
        return "INSERT INTO TB_FAMILY_VISIT(FAMILY_VISIT, DESCRIPTION) VALUES (:FAMILY_VISIT, :DESCRIPTION)";
    }

    private static function queryHygiene()
    {
        return "INSERT INTO TB_HYGIENE(HYGIENE, BATH, SANITARY, ORAL, ANOTHER) 
              VALUES (:HYGIENE, :BATH, :SANITARY, :ORAL, :ANOTHER)";
    }

}