<?php

class CityLocation
{
    const CITY_LOCATION = "CITY_LOCATION";
    const NEIGHBORHOOD = "NEIGHBORHOOD";
    const UF = "UF";
    const CITY = "CITY";
    const CEP = "CEP";

    private $neighborhood, $uf, $city, $cep;

    public function __construct($neighborhood, $uf, $city, $cep)
    {
        $this->neighborhood = $neighborhood;
        $this->uf = $uf;
        $this->city = $city;
        $this->cep = $cep;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::NEIGHBORHOOD => $this->neighborhood,
            ":" . self::UF => $this->uf,
            ":" . self::CITY => $this->city,
            ":" . self::CEP => $this->cep);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new CityLocation($array[self::NEIGHBORHOOD], $array[self::UF], $array[self::CITY], $array[self::CEP]);
    }
}