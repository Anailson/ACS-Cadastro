<?php

class HomeController
{
    private $cssClasses;

    function __construct($htmlClasses)
    {
        $this->cssClasses = $htmlClasses;
    }
}