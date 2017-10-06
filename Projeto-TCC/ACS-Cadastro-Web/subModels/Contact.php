<?php

class Contact
{
    const PHONE = "PHONE";
    const EMAIL = "EMAIL";
    const CONTACT = "CONTACT";

    private $phone, $email;

    function __construct($phone, $email)
    {
        $this->phone = $phone;
        $this->email = $email;
    }

    public function getValuesToDB()
    {
        return array(":" . self::PHONE => $this->phone, ":" . self::EMAIL => $this->email);
    }

    public static function getFromArray(array $array)
    {
        return new Contact($array[self::PHONE], $array[self::EMAIL]);
    }
}