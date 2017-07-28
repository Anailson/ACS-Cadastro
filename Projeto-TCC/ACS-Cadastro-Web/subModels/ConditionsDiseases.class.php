<?php

class ConditionsDiseases
{
    const CONDITIONS_DISEASES = "CONDITIONS_DISEASES";
    const ASTHMA = "ASTHMA";
    const MALNUTRITION = "MALNUTRITION";
    const DIABETES = "DIABETES";
    const DPOC = "DPOC";
    const HYPERTENSION = "HYPERTENSION";
    const OBESITY = "OBESITY";
    const PRENATAL = "PRENATAL";
    const CHILD_CARE = "CHILD_CARE";
    const PUERPERIUM = "PUERPERIUM";
    const SEXUAL_HEALTH = "SEXUAL_HEALTH";
    const SMOKING = "SMOKING";
    const ALCOHOL = "ALCOHOL";
    const DRUGS = "DRUGS";
    const MENTAL_HEALTH = "MENTAL_HEALTH";
    const REHABILITATION = "REHABILITATION";

    private $asthma, $malnutrition, $diabetes, $dpoc, $hypertension, $obesity, $prenatal, $childCare, $puerperium,
        $sexualHealth, $smoking, $alcohol, $drugs, $mentalHealth, $rehabilitation;

    public function __construct($asthma, $malnutrition, $diabetes, $dpoc, $hypertension, $obesity, $prenatal, $childCare,
                                $puerperium, $sexualHealth, $smoking, $alcohol, $drugs, $mentalHealth, $rehabilitation)
    {
        $this->asthma = $asthma;
        $this->malnutrition = $malnutrition;
        $this->diabetes = $diabetes;
        $this->dpoc = $dpoc;
        $this->hypertension = $hypertension;
        $this->obesity = $obesity;
        $this->prenatal = $prenatal;
        $this->childCare = $childCare;
        $this->puerperium = $puerperium;
        $this->sexualHealth = $sexualHealth;
        $this->smoking = $smoking;
        $this->alcohol = $alcohol;
        $this->drugs = $drugs;
        $this->mentalHealth = $mentalHealth;
        $this->rehabilitation = $rehabilitation;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::ASTHMA => $this->asthma,
            ":" . self::MALNUTRITION => $this->malnutrition,
            ":" . self::DIABETES => $this->diabetes,
            ":" . self::DPOC => $this->dpoc,
            ":" . self::HYPERTENSION => $this->hypertension,
            ":" . self::OBESITY => $this->obesity,
            ":" . self::PRENATAL => $this->prenatal,
            ":" . self::CHILD_CARE => $this->childCare,
            ":" . self::PUERPERIUM => $this->puerperium,
            ":" . self::SEXUAL_HEALTH => $this->sexualHealth,
            ":" . self::SMOKING => $this->smoking,
            ":" . self::ALCOHOL => $this->alcohol,
            ":" . self::DRUGS => $this->drugs,
            ":" . self::MENTAL_HEALTH => $this->mentalHealth,
            ":" . self::REHABILITATION => $this->rehabilitation);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new ConditionsDiseases($array[self::ASTHMA], $array[self::MALNUTRITION], $array[self::DIABETES], $array[self::DPOC],
            $array[self::HYPERTENSION], $array[self::OBESITY], $array[self::PRENATAL], $array[self::CHILD_CARE],
            $array[self::PUERPERIUM], $array[self::SEXUAL_HEALTH], $array[self::SMOKING], $array[self::ALCOHOL],
            $array[self::DRUGS], $array[self::MENTAL_HEALTH], $array[self::REHABILITATION]);
    }
}