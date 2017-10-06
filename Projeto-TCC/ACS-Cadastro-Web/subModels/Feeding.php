<?php

class Feeding
{
    const FEEDING = "FEEDING";
    const FOOD_PER_DAY = "FOOD_PER_DAY";
    const RESTAURANT = "RESTAURANT";
    const RESTAURANT_DONATION = "RESTAURANT_DONATION";
    const RELIGIOUS_GROUP = "RELIGIOUS_GROUP";
    const POPULAR = "POPULAR";
    const ANOTHER = "ANOTHER";

    private $foodPerDay, $restaurant, $restaurantDonation, $religiousGroup, $popular, $another;

    public function __construct($foodPerDay, $restaurant, $restaurantDonation, $religiousGroup, $popular, $another)
    {
        $this->foodPerDay = $foodPerDay;
        $this->restaurant = $restaurant;
        $this->restaurantDonation = $restaurantDonation;
        $this->religiousGroup = $religiousGroup;
        $this->popular = $popular;
        $this->another = $another;
    }

    public function getValuesToDB()
    {
        return array(":" . self::FOOD_PER_DAY => $this->foodPerDay,
            ":" . self::RESTAURANT => $this->restaurant,
            ":" . self::RESTAURANT_DONATION => $this->restaurantDonation,
            ":" . self::RELIGIOUS_GROUP => $this->religiousGroup,
            ":" . self::POPULAR => $this->popular,
            ":" . self::ANOTHER => $this->another);
    }

    public static function getFromArray(array $array)
    {
        return new Feeding($array[self::FOOD_PER_DAY], $array[self::RESTAURANT], $array[self::RESTAURANT_DONATION],
            $array[self::RELIGIOUS_GROUP], $array[self::POPULAR], $array[self::ANOTHER]);
    }
}