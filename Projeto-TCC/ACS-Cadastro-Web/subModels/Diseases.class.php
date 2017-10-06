<?php

class Diseases
{

    const DISEASES = "DISEASES";
    const SMOKER = "SMOKER";
    const ALCOHOL = "ALCOHOL";
    const DRUGS = "DRUGS";
    const HYPERTENSION = "HYPERTENSION";
    const DIABETES = "DIABETES";
    const AVC = "AVC";
    const HEART_ATTACK = "HEART_ATTACK";
    const LEPROSY = "LEPROSY";
    const TUBERCULOSIS = "TUBERCULOSIS";
    const CANCER = "CANCER";
    const IN_BED = "IN_BED";
    const DOMICILED = "DOMICILED";
    const OTHER_PRACTICES = "OTHER_PRACTICES";
    const MENTAL_HEALTH = "MENTAL_HEALTH";

    private $smoker, $alcohol, $drugs, $hypertension, $diabetes, $avc, $heartAttack, $leprosy, $tuberculosis, $cancer,
        $inBed, $domiciled, $otherPractices, $mentalHealth;

    public function __construct($smoker, $alcohol, $drugs, $hypertension, $diabetes, $avc, $heartAttack, $leprosy, $tuberculosis,
                                $cancer, $inBed, $domiciled, $otherPractices, $mentalHealth)
    {
        $this->smoker = $smoker;
        $this->alcohol = $alcohol;
        $this->drugs = $drugs;
        $this->hypertension = $hypertension;
        $this->diabetes = $diabetes;
        $this->avc = $avc;
        $this->heartAttack = $heartAttack;
        $this->leprosy = $leprosy;
        $this->tuberculosis = $tuberculosis;
        $this->cancer = $cancer;
        $this->inBed = $inBed;
        $this->domiciled = $domiciled;
        $this->otherPractices = $otherPractices;
        $this->mentalHealth = $mentalHealth;
    }

    public function getValuesToDB()
    {
        return array(":" . self::SMOKER=>$this->smoker,
            ":" . self::ALCOHOL => $this->alcohol,
            ":" . self::DRUGS => $this->drugs,
            ":" . self::HYPERTENSION => $this->hypertension,
            ":" . self::DIABETES => $this->diabetes,
            ":" . self::AVC => $this->avc,
            ":" . self::HEART_ATTACK => $this->heartAttack,
            ":" . self::LEPROSY => $this->leprosy,
            ":" . self::TUBERCULOSIS => $this->tuberculosis,
            ":" . self::CANCER => $this->cancer,
            ":" . self::IN_BED => $this->inBed,
            ":" . self::DOMICILED => $this->domiciled,
            ":" . self::OTHER_PRACTICES => $this->otherPractices,
            ":" . self::MENTAL_HEALTH => $this->mentalHealth);
    }

    public static function getFromArray(array $array)
    {
        return new Diseases($array[self::SMOKER], $array[self::ALCOHOL], $array[self::DRUGS], $array[self::HYPERTENSION],
            $array[self::DIABETES], $array[self::AVC], $array[self::HEART_ATTACK], $array[self::LEPROSY], $array[self::TUBERCULOSIS],
            $array[self::CANCER], $array[self::IN_BED], $array[self::DOMICILED], $array[self::OTHER_PRACTICES], $array[self::MENTAL_HEALTH]);
    }

}