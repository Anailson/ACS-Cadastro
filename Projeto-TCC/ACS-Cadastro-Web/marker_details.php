<?php
include_once "models/CitizenModel.class.php";
include_once "models/ResidenceModel.class.php";
include_once "models/AccompanyModel.class.php";
include_once "models/VisitModel.class.php";

if(!@include_once "../controllers/OccurrencesMapController.class.php"){
    include_once "controllers/OccurrencesMapController.class.php";
}

if ($_SERVER['REQUEST_METHOD'] == "GET") {
    if (isset($_GET['f']) && $_GET['f'] == "map") {

        $filter = isset($_GET['l']) ? $_GET['l'] : false;
        $parameter = isset($_GET['p']) ? $_GET['p'] : false;
        getOccurrences($filter, $parameter);
    }
}

function getOccurrences($filter, $parameter)
{
    $json = array();
    $controller = new OccurrencesMapController();

    switch ($filter) {
        case HealthConditionsModel::HEALTH_CONDITIONS:
            $json = $controller->getHealthConditionsLocation($parameter);
            break;
        case SocialDemographicModel::SOCIAL_DEMOGRAPHIC;
            $locations = OccurrencesMapPersistence::getSocialDemographicLocation($parameter);
            break;
        case StreetSituationModel::STREET_SITUATION;
            $locations = OccurrencesMapPersistence::getStreetSituationLocation($parameter);
            break;
        case ConditionsModel::CONDITIONS;
            $locations = OccurrencesMapPersistence::getConditionsLocation($parameter);
            break;
        case ExamsModel::EXAMS;
            $locations = OccurrencesMapPersistence::getExamsLocation($parameter);
            break;
        default:
            $locations = OccurrencesMapPersistence::getAllLocations();
            break;
    }
/*
    foreach ($locations as $location) {

        $id = $location[ResidenceModel::RESIDENCE . "_ID"];
        if(!in_array($id, $ids)){

            $ids[] = $id;
            $details = getIfExists($location, StreetLocation::TYPE);
            $details .= " " . getIfExists($location, StreetLocation::STREET_LOCATION . "_" . StreetLocation::NAME);
            $details .= " " . getIfExists($location, StreetLocation::NUMBER);
            $details .= ", " . getIfExists($location, CityLocation::NEIGHBORHOOD);
            $details .= ". " . getIfExists($location, CityLocation::CITY);
            $details .= " - " . getIfExists($location, CityLocation::UF) . "<br>";
            //$details .= "Cidadão(ões): " . getIfExists($location, Particular::PARTICULAR . "_" . Particular::NAME);

            $json[] = array('lat' => $location[ResidenceModel::LAT],
                'lng' => $location[ResidenceModel::LNG],
                'details' => $details,
                'count' => 1);
        } else {
            $index = array_search($id, $ids);
            //$json[$index]['details'] .= ", " . getIfExists($location, Particular::PARTICULAR . "_" . Particular::NAME);
            $json[$index]['count']++;
        }
    }*/

    echo $json;
}

function getIfExists(array $array, $key){
    return array_key_exists($key, $array) ? $array[$key] : "";
}

function duplicateResidence($id, array $ids)
{
    if(in_array($id, $ids)){
        array_search($id, $ids);
    }
    return false;
}