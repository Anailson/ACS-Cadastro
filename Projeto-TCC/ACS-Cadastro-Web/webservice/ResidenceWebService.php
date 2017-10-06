<?php

if (!@include "persistences/ResidencePersistence.class.php") {
    include "../persistences/ResidencePersistence.class.php";
}

$method = $_SERVER['REQUEST_METHOD'];
if ($method === AcsDataBase::GET) {

    if (isset($_GET['f']) && function_exists($_GET['f']) && $_GET['f'] == "get") {

        $value = isset($_GET['d']) ? $_GET['d'] : false;
        if ($value) {
            get($value);
        } else  {
            $value = isset($_GET['a']) ? $_GET['a'] : false;
            getAll($value);
        }
    }

} else if ($method === AcsDataBase::POST) {

    $json = $_POST['JSON'];
    $option = isset($_GET['o']) ? $_GET['o'] : false;
    if ($option) {
        insertAll($json);
    } else {
        insert($json);
    }
}

function insert($json)
{
    $array = json_decode($json, true);

    $streetLocation = $array[AddressDataModel::ADDRESS_DATA][StreetLocation::STREET_LOCATION];
    $cityLocation = $array[AddressDataModel::ADDRESS_DATA][CityLocation::CITY_LOCATION];
    $latLng = geocode($streetLocation[StreetLocation::NAME], $streetLocation[StreetLocation::NUMBER],
        $cityLocation[CityLocation::CITY], $cityLocation[CityLocation::UF]);

    $array[ResidenceModel::LAT] = $latLng['lat'];
    $array[ResidenceModel::LNG] = $latLng['lng'];

    $residence = ResidenceModel::getFromArray($array);
    $numSus = $array["AGENT"]['NUM_SUS'];
    echo json_encode(array("id" => ResidencePersistence::insert($numSus, $residence)));
}

function get($value)
{
    echo "get" . " " . $value;
}

function getAll($numSus)
{
    $residences = ResidencePersistence::getAll($numSus);
    if ($residences) {
        echo json_encode($residences);
    }
}

function geocode($street, $number, $city, $uf)
{
    $url = "http://maps.google.com/maps/api/geocode/json?address=";
    $formattedAddress = $street . "+" . $number . "+" . $city . "+" . $uf;
    $formattedAddress = str_replace(" ", "+", $formattedAddress);

    $contents = json_decode(file_get_contents($url . $formattedAddress), true);
    if($contents['status'] == 'OK') {
        return $contents['results'][0]['geometry']['location'];
    }
    return null;
}
