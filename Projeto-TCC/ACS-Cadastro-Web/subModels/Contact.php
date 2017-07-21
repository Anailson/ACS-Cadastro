<?php

class Contact
{
    const PHONE = "PHONE";
    const EMAIL = "EMAIL";

    private $phone, $email;

    function __construct($phone, $email)
    {
        $this->phone = $phone;
        $this->email = $email;
    }

    public function save(AcsDataBase $db, $query)
    {
        $values = array(":" . self::PHONE => $this->phone, ":" . self::EMAIL => $this->email);
        return $db->insert($query, $values);
    }

    public static function getFromArray(array $array)
    {
        return new Contact($array[self::PHONE], $array[self::EMAIL]);
    }
}