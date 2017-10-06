<?php
if (!@include_once "persistences/OccurrencesMapPersistence.class.php") {
    include_once "../persistences/OccurrencesMapPersistence.class.php";
}

class OccurrencesMapController
{
    function __construct()
    {

    }

    public function getHealthConditionsLocation($parameter)
    {
        $locations = OccurrencesMapPersistence::getHealthConditionsLocation($parameter);
        $residences = array();

        foreach ($locations as $location) {

            $residenceId = $location[ResidenceModel::RESIDENCE . "_ID"];
            if (!$this->valueDuplicate($residences, "ID", $residenceId)) {
                $residences[$residenceId] = array(
                    "ID" => $residenceId,
                    ResidenceModel::LAT => $location[ResidenceModel::LAT],
                    ResidenceModel::LNG => $location[ResidenceModel::LNG],
                    StreetSituation::STREET => $this->getStreetDetails($location),
                    CitizenModel::CITIZEN => array());
            }

            $citizenId = $location[CitizenModel::CITIZEN . "_ID"];
            $citizens = $residences[$residenceId][CitizenModel::CITIZEN];
            if (!$this->valueDuplicate($citizens, "ID", $citizenId)) {
                $citizens[$citizenId] = array("ID" => $citizenId,
                    Particular::NAME => $this->getCitizenDetails($location),
                    "OCCURRENCES" => array());
            }

            $occurrences = $citizens[$citizenId]["OCCURRENCES"];
            $occurrence = $location["DISEASE"];
            if (!in_array($occurrence, $occurrences)) {
                $occurrences[] = $occurrence;
            }
            $citizens[$citizenId]["OCCURRENCES"] = $occurrences;
            $residences[$residenceId][CitizenModel::CITIZEN] = $citizens;
        }

        $json = array();
        foreach ($residences as $residence) {

            $count = 0;
            $details = "<b>" . $residence[StreetSituation::STREET] . "</b><br><br>";
            $citizens = $residence[CitizenModel::CITIZEN];
            foreach ($citizens as $citizen) {

                $details .= "<b>" . $citizen[Particular::NAME] . ":</b>";
                $occurrences = $citizen["OCCURRENCES"];
                foreach ($occurrences as $occurrence) {
                    $details .= " " . $occurrence . ",";
                    $count++;
                }
                $details = substr($details, 0, strlen($details) - 1) . "<br><br>";
            }

            $json[] = array("lat" => $residence[ResidenceModel::LAT],
                "lng" => $residence[ResidenceModel::LNG],
                "details" => $details,
                "count" => $count);
        }
        return json_encode($json);
    }

    function getAllOccurrences()
    {
        $return = OccurrencesMapPersistence::getAllLocations();
        var_dump($return);
    }

    private function getStreetDetails(array $location)
    {
        return $this->getIfExists($location, StreetLocation::TYPE)
            . " " . $this->getIfExists($location, StreetLocation::STREET_LOCATION . "_" . StreetLocation::NAME)
            . " " . $this->getIfExists($location, StreetLocation::NUMBER)
            . ", " . $this->getIfExists($location, CityLocation::NEIGHBORHOOD)
            . ". " . $this->getIfExists($location, CityLocation::CITY)
            . " - " . $this->getIfExists($location, CityLocation::UF);
    }

    private function getCitizenDetails(array $location)
    {
        return $this->getIfExists($location, Particular::PARTICULAR . "_" . Particular::NAME);
    }

    private function valueDuplicate(array $array, $key, $value)
    {
        foreach ($array as $arr) {
            if ($arr[$key] === $value) {
                return true;
            }
        }
        return false;
    }

    private function getIfExists(array $array, $key)
    {
        return array_key_exists($key, $array) ? $array[$key] : false;
    }


}