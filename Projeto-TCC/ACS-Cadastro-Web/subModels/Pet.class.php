<?php

class Pet
{
    const PET = "PET";
    const HAS_PET = "HAS_PET";
    const CAT = "CAT";
    const DOG = "DOG";
    const BIRD = "BIRD";
    const CREATION = "CREATION";
    const ANOTHER = "ANOTHER";
    CONST PETS = "PETS";

    private $hasPet, $cat, $dog, $bird, $creation, $another, $nPets;

    public function __construct($hasPet, $cat, $dog, $bird, $creation, $another, $nPets)
    {
        $this->hasPet = $hasPet;
        $this->cat = $cat;
        $this->dog = $dog;
        $this->bird = $bird;
        $this->creation = $creation;
        $this->another = $another;
        $this->nPets = $nPets;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::HAS_PET => $this->hasPet,
            ":" . self::CAT => $this->cat,
            ":" . self::DOG => $this->dog,
            ":" . self::BIRD => $this->bird,
            ":" . self::CREATION => $this->creation,
            ":" . self::ANOTHER => $this->another,
            ":" . self::PETS => $this->nPets);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new Pet($array[self::HAS_PET], $array[self::CAT], $array[self::DOG],
            $array[self::BIRD], $array[self::CREATION], $array[self::ANOTHER], $array[self::PETS]);
    }
}