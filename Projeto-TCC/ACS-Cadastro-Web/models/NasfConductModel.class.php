<?php

if (!@include "subModels/Nasf.class.php") {
    include "../subModels/Nasf.class.php";
}
if (!@include "subModels/Conduct.class.php") {
    include "../subModels/Conduct.class.php";
}
if (!@include "subModels/Forwarding.class.php") {
    include "../subModels/Forwarding.class.php";
}

class NasfConductModel
{
    const NASF_CONDUCT = "NASF_CONDUCT";

    private $nasfConduct, $nasf, $conduct, $forwarding;

    public function __construct($nasfConduct, Nasf $nasf, Conduct $conduct, Forwarding $forwarding)
    {
        $this->nasfConduct = $nasfConduct;
        $this->nasf = $nasf;
        $this->conduct = $conduct;
        $this->forwarding = $forwarding;
    }

    public static function getFromArray(array $array)
    {
        return new NasfConductModel($array[self::NASF_CONDUCT],
            Nasf::getFromArray($array[Nasf::NASF]),
            Conduct::getFromArray($array[Conduct::CONDUCT]),
            Forwarding::getFromArray($array[Forwarding::FORWARDING]));
    }

    public function getValuesToBD()
    {
        $values[Nasf::NASF] = $this->nasf->getValuesToBD();
        $values[Conduct::CONDUCT] = $this->conduct->getValuesToBD();
        $values[Forwarding::FORWARDING] = $this->forwarding->getValuesToBD();
        $values[self::NASF_CONDUCT] = $this->nasfConduct;
        return $values;
    }
}