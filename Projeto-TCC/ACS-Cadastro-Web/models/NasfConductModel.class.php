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

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . Nasf::NASF => $this->nasf->save($db, $query[Nasf::NASF]),
            ":" . Conduct::CONDUCT => $this->conduct->save($db, $query[Conduct::CONDUCT]),
            ":" . Forwarding::FORWARDING => $this->forwarding->save($db, $query[Forwarding::FORWARDING]));
        if (in_array(false, $params)) {
            return false;
        }
        $params[":" . self::NASF_CONDUCT] = $this->nasfConduct;
        return $db->insert($query[self::NASF_CONDUCT], $params);
    }

}