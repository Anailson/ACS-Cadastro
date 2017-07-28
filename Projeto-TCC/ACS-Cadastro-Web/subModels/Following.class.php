<?php

class Following
{
    const FOLLOWING = "FOLLOWING";
    const PREGNANT = "PREGNANT";
    const PUERPERA = "PUERPERA";
    const NEW_BORN = "NEW_BORN";
    const CHILD = "CHILD";
    const MALNUTRITION = "MALNUTRITION";
    const REHABILITATION = "REHABILITATION";
    const HYPERTENSION = "HYPERTENSION";
    const DIABETES = "DIABETES";
    const ASTHMA = "ASTHMA";
    const COPD_EMPHYSEMA = "COPD_EMPHYSEMA";
    const CANCER = "CANCER";
    const CHRONIC_DISEASE = "CHRONIC_DISEASE";
    const LEPROSY = "LEPROSY";
    const TUBERCULOSIS = "TUBERCULOSIS";
    const RESPIRATORY = "RESPIRATORY";
    const SMOKER = "SMOKER";
    const HOME_BEDDING = "HOME_BEDDING";
    const VULNERABILITY = "VULNERABILITY";
    const BOLSA_FAMILIA = "BOLSA_FAMILIA";
    const MENTAL_HEALTH = "MENTAL_HEALTH";
    const ALCOHOL = "ALCOHOL";
    const DRUGS = "DRUGS";

    private $pregnant, $puerpera, $newborn, $child, $malnutrition, $rehabilitationDeficiency,
            $hypertension, $diabetes, $asthma, $copdEmphysema, $cancer, $chronicDiseases, $leprosy, $tuberculosis,
            $respiratory, $smoker, $homeBedding, $vulnerability, $bolsaFamília, $mentalHealth, $alcohol, $drugs;

    public function __construct($pregnant, $puerpera, $newborn, $child, $malnutrition, $rehabilitationDeficiency,
                                $hypertension, $diabetes, $asthma, $copdEmphysema, $cancer, $chronicDiseases, $leprosy,
                                $tuberculosis, $respiratory, $smoker, $homeBedding, $vulnerability, $bolsaFamília,
                                $mentalHealth, $alcohol, $drugs)
    {
        $this->pregnant = $pregnant;
        $this->puerpera = $puerpera;
        $this->newborn = $newborn;
        $this->child = $child;
        $this->malnutrition = $malnutrition;
        $this->rehabilitationDeficiency = $rehabilitationDeficiency;
        $this->hypertension = $hypertension;
        $this->diabetes = $diabetes;
        $this->asthma = $asthma;
        $this->copdEmphysema = $copdEmphysema;
        $this->cancer = $cancer;
        $this->chronicDiseases = $chronicDiseases;
        $this->leprosy = $leprosy;
        $this->tuberculosis = $tuberculosis;
        $this->respiratory = $respiratory;
        $this->smoker = $smoker;
        $this->homeBedding = $homeBedding;
        $this->vulnerability = $vulnerability;
        $this->bolsaFamília = $bolsaFamília;
        $this->mentalHealth = $mentalHealth;
        $this->alcohol = $alcohol;
        $this->drugs = $drugs;
    }

    public function save(AcsDataBase $db, $query)

    {
        $param = array(":" . self::PREGNANT => $this->pregnant,
            ":" . self::PUERPERA => $this->puerpera,
            ":" . self::NEW_BORN => $this->newborn,
            ":" . self::CHILD => $this->child,
            ":" . self::MALNUTRITION => $this->malnutrition,
            ":" . self::REHABILITATION => $this->rehabilitationDeficiency,
            ":" . self::HYPERTENSION => $this->hypertension,
            ":" . self::DIABETES => $this->diabetes,
            ":" . self::ASTHMA => $this->asthma,
            ":" . self::COPD_EMPHYSEMA => $this->copdEmphysema,
            ":" . self::CANCER => $this->cancer,
            ":" . self::CHRONIC_DISEASE => $this->chronicDiseases,
            ":" . self::LEPROSY => $this->leprosy,
            ":" . self::TUBERCULOSIS => $this->tuberculosis,
            ":" . self::RESPIRATORY => $this->respiratory,
            ":" . self::SMOKER => $this->smoker,
            ":" . self::HOME_BEDDING => $this->homeBedding,
            ":" . self::VULNERABILITY=> $this->vulnerability,
            ":" . self::BOLSA_FAMILIA => $this->bolsaFamília,
            ":" . self::MENTAL_HEALTH => $this->mentalHealth,
            ":" . self::ALCOHOL => $this->alcohol,
            ":" . self::DRUGS => $this->drugs);
        return $db->insert($query, $param);
    }

    public static function getFromArray(array $array)
    {
        return new Following($array[self::PREGNANT], $array[self::PUERPERA], $array[self::NEW_BORN], $array[self::CHILD],
            $array[self::MALNUTRITION], $array[self::REHABILITATION], $array[self::HYPERTENSION], $array[self::DIABETES],
            $array[self::ASTHMA], $array[self::COPD_EMPHYSEMA], $array[self::CANCER], $array[self::CHRONIC_DISEASE],
            $array[self::LEPROSY], $array[self::TUBERCULOSIS], $array[self::RESPIRATORY], $array[self::SMOKER],
            $array[self::HOME_BEDDING], $array[self::VULNERABILITY], $array[self::BOLSA_FAMILIA], $array[self::MENTAL_HEALTH],
            $array[self::ALCOHOL], $array[self::DRUGS]);
    }
}