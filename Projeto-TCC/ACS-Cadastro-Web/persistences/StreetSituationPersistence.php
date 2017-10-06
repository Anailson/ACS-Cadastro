<?php

class StreetSituationPersistence
{
    public static function getById(AcsDataBase $db, $id)
    {
        $query = "SELECT SS.BENEFIT, SS.FAMILY,
                    ST.STREET, ST.DESCRIPTION AS 'DESCRIPTION_STREET', 
                    FD.FOOD_PER_DAY, FD.RESTAURANT, FD.RESTAURANT_DONATION, FD.RELIGIOUS_GROUP, FD.POPULAR, FD.ANOTHER AS 'ANOTHER_FEEDING',
                    AI.ANOTHER_INSTITUTION, AI.DESCRIPTION AS 'DESCRIPTION_INSTITUTION',
                    FV.FAMILY_VISIT, FV.DESCRIPTION AS 'DESCRIPTION_FAMILY_VISIT',
                    HG.HYGIENE, HG.BATH, HG.SANITARY, HG.ORAL, HG.ANOTHER AS 'ANOTHER_HYGIENE'
                FROM tb_street_situation AS SS
                INNER JOIN tb_street AS ST ON ST.STREET_ID = SS.ID_STREET
                INNER JOIN tb_feeding AS FD ON FD.FEEDING_ID = SS.ID_FEEDING
                INNER JOIN tb_another_instituation AS AI ON AI.ANOTHER_INSTITUTION_ID =  SS.ID_ANOTHER_INSTITUTION
                INNER JOIN tb_family_visit AS FV ON FV.FAMILY_VISIT_ID = SS.ID_FAMILY_VISIT
                INNER JOIN tb_hygiene AS HG ON HG.HYGIENE_ID = SS.ID_HYGIENE
                WHERE SS.STREET_SITUATION_ID = :STREET_SITUATION_ID";
        return $db->select($query, array(":STREET_SITUATION_ID" => $id));
    }
    public static function insert(AcsDataBase $db, StreetSituationModel $streetSituation)
    {
        $values = $streetSituation->getValuesToDB();
        $ids[":ID_" . StreetSituation::STREET ] = $db->insert(self::queryStreet(), $values[StreetSituation::STREET] );
        $ids[":ID_" . Feeding::FEEDING ] = $db->insert(self::queryFeeding(), $values[Feeding::FEEDING]);
        $ids[":ID_" . AnotherInstitution::ANOTHER_INSTITUTION] = $db->insert(self::queryAnotherInstitution(), $values[AnotherInstitution::ANOTHER_INSTITUTION]);
        $ids[":ID_" . FamilyVisit::FAMILY_VISIT] = $db->insert(self::queryFamilyVisit(), $values[FamilyVisit::FAMILY_VISIT]);
        $ids[":ID_" . Hygiene::HYGIENE] = $db->insert(self::queryHygiene(), $values[Hygiene::HYGIENE]);
        if(in_array(false, $ids)){
            return false;
        }
        $ids[":" . StreetSituationModel::BENEFIT] = $values[StreetSituationModel::BENEFIT];
        $ids[":" . StreetSituationModel::FAMILY] = $values[StreetSituationModel::FAMILY];
        return $db->insert(self::queryStreetSituation(), $ids);
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