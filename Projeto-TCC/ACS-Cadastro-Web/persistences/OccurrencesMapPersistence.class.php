<?php
if (!@include_once "persistences/AcsDataBase.php") {
    include_once "../persistences/AcsDataBase.php";
}

class OccurrencesMapPersistence
{
    public static function getHealthConditionsLocation($parameter = false)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT RS.RESIDENCE_ID, CO.DISEASE, CT.CITIZEN_ID,
                    PT.NUM_SUS, PT.NAME AS PARTICULAR_NAME, 
                    SL.TYPE, SL.NAME AS STREET_LOCATION_NAME, SL.NUMBER, 
                    CL.NEIGHBORHOOD, CL.CITY, CL.UF, 
                    RS.LAT, RS.LNG
                FROM tb_citizen_has_occurrence CO
                INNER JOIN tb_citizen CT ON CT.CITIZEN_ID = CO.ID_CITIZEN
                INNER JOIN tb_personal_data PD ON PD.PERSONAL_DATA_ID = CT.ID_PERSONAL_DATA
                INNER JOIN tb_particular PT ON PT.PARTICULAR_ID = PD.ID_PARTICULAR
                INNER JOIN tb_responsible_citizen RC ON RC.ID_CITIZEN = CO.ID_CITIZEN
                INNER JOIN tb_responsible RP ON RP.RESPONSIBLE_ID = RC.ID_RESPONSIBLE
                INNER JOIN tb_responsible_residence RR ON RR.ID_RESPONSIBLE = RP.RESPONSIBLE_ID
                INNER JOIN tb_residence RS ON RS.RESIDENCE_ID = RR.ID_RESIDENCE
                INNER JOIN tb_address_data AD ON AD.ADDRESS_DATA_ID = RS.ID_ADDRESS_DATA
                INNER JOIN tb_street_location SL ON SL.STREET_LOCATION_ID = AD.ID_STREET_LOCATION
                INNER JOIN tb_city_location CL ON CL.CITY_LOCATION_ID = AD.ID_CITY_LOCATION";
        if($parameter){
            $query .= " WHERE CO.DISEASE = :DISEASE";
            return $db->select($query, array(":DISEASE" => $parameter));
        }
        return $db->select($query);
    }

    public static function getSocialDemographicLocation($parameter = false)
    {
        return array();
    }

    public static function getStreetSituationLocation($parameter = false)
    {
        return array();
    }

    public static function getConditionsLocation($parameter = false)
    {
        return array();
    }

    public static function getExamsLocation($parameter = false)
    {
        return array();
    }

    public static function getAllLocations()
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT RS.RESIDENCE_ID, RS.LAT, RS.LNG FROM TB_RESIDENCE AS RS";
        return $db->select($query);
    }
}