<?php

class EvaluatedExams
{
    const EVALUATED_EXAMS = "EVALUATED_EXAMS";
    const TOTAL_CHOLESTEROL = "TOTAL_CHOLESTEROL";
    const CREATINE = "CREATINE";
    const EAS_EQU = "EAS_EQU";
    const ELECTROCARDIOGRAM = "ELECTROCARDIOGRAM";
    const HEMOGLOBIN = "HEMOGLOBIN";
    const SPIROMETRY = "SPIROMETRY";
    const SPUTUM = "SPUTUM";
    const GLYCEMIA = "GLYCEMIA";
    const HDL = "HDL";
    const GLYCEMIC_HEMOGLIBIN = "GLYCEMIC_HEMOGLIBIN";
    const BLOOD_COUNT = "BLOOD_COUNT";
    const LDL = "LDL";
    const EYES = "EYES";
    const SYPHILIS = "SYPHILIS";
    const DENGUE = "DENGUE";
    const HIV = "HIV";
    const HUMAN_ANTIGLOBULIN = "HUMAN_ANTIGLOBULIN";
    const EAR_TEST = "EAR_TEST";
    const PREGNANCY_TEST = "PREGNANCY_TEST";
    const EYE_TEST = "EYE_TEST";
    const FOOT_TEST = "FOOT_TEST";
    const ULTRASONOGRAPHY = "ULTRASONOGRAPHY";
    const UROCULTURE = "UROCULTURE";

    private $totalCholesterol, $creatine, $easEqu, $electrocardiogram, $hemoglobin, $spirometry, $sputum, $glycemia,
        $hdl, $glycemicHemoglobin, $bloodCount, $ldl, $eyes, $syphilis, $dengue, $hiv, $humanAntiglobulin, $earTest,
        $pregnancyTest, $eyeTest, $footTest, $ultrasonography, $uroculture;

    public function __construct($totalCholesterol, $creatine, $easEqu, $electrocardiogram, $hemoglobin, $spirometry,
                                $sputum, $glycemia, $hdl, $glycemicHemoglobin, $bloodCount, $ldl, $eyes, $syphilis, $dengue, $hiv,
                                $humanAntiglobulin, $earTest, $pregnancyTest, $eyeTest, $footTest, $ultrasonography, $uroculture)
    {
        $this->totalCholesterol = $totalCholesterol;
        $this->creatine = $creatine;
        $this->easEqu = $easEqu;
        $this->electrocardiogram = $electrocardiogram;
        $this->hemoglobin = $hemoglobin;
        $this->spirometry = $spirometry;
        $this->sputum = $sputum;
        $this->glycemia = $glycemia;
        $this->hdl = $hdl;
        $this->glycemicHemoglobin = $glycemicHemoglobin;
        $this->bloodCount = $bloodCount;
        $this->ldl = $ldl;
        $this->eyes = $eyes;
        $this->syphilis = $syphilis;
        $this->dengue = $dengue;
        $this->hiv = $hiv;
        $this->humanAntiglobulin = $humanAntiglobulin;
        $this->earTest = $earTest;
        $this->pregnancyTest = $pregnancyTest;
        $this->eyeTest = $eyeTest;
        $this->footTest = $footTest;
        $this->ultrasonography = $ultrasonography;
        $this->uroculture = $uroculture;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":" . self::TOTAL_CHOLESTEROL => $this->totalCholesterol,
            ":" . self::CREATINE => $this->creatine,
            ":" . self::EAS_EQU => $this->easEqu,
            ":" . self::ELECTROCARDIOGRAM => $this->electrocardiogram,
            ":" . self::HEMOGLOBIN => $this->hemoglobin,
            ":" . self::SPIROMETRY => $this->spirometry,
            ":" . self::SPUTUM => $this->sputum,
            ":" . self::GLYCEMIA => $this->glycemia,
            ":" . self::HDL => $this->hdl,
            ":" . self::GLYCEMIC_HEMOGLIBIN => $this->glycemicHemoglobin,
            ":" . self::BLOOD_COUNT => $this->bloodCount,
            ":" . self::LDL => $this->ldl,
            ":" . self::EYES => $this->eyes,
            ":" . self::SYPHILIS => $this->syphilis,
            ":" . self::DENGUE => $this->dengue,
            ":" . self::HIV => $this->hiv,
            ":" . self::HUMAN_ANTIGLOBULIN => $this->humanAntiglobulin,
            ":" . self::EAR_TEST => $this->earTest,
            ":" . self::PREGNANCY_TEST => $this->pregnancyTest,
            ":" . self::EYE_TEST => $this->eyeTest,
            ":" . self::FOOT_TEST => $this->footTest,
            ":" . self::ULTRASONOGRAPHY => $this->ultrasonography,
            ":" . self::UROCULTURE => $this->uroculture);
        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new EvaluatedExams($array[self::TOTAL_CHOLESTEROL], $array[self::CREATINE], $array[self::EAS_EQU],
            $array[self::ELECTROCARDIOGRAM], $array[self::HEMOGLOBIN], $array[self::SPIROMETRY], $array[self::SPUTUM],
            $array[self::GLYCEMIA], $array[self::HDL], $array[self::GLYCEMIC_HEMOGLIBIN], $array[self::BLOOD_COUNT],
            $array[self::LDL],$array[self::EYES], $array[self::SYPHILIS], $array[self::DENGUE], $array[self::HIV],
            $array[self::HUMAN_ANTIGLOBULIN],$array[self::EAR_TEST], $array[self::PREGNANCY_TEST], $array[self::EYE_TEST],
            $array[self::FOOT_TEST], $array[self::ULTRASONOGRAPHY], $array[self::UROCULTURE]);
    }
}