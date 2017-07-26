<?php

if (!@include "models/RecordDataModel.class.php") {
    include "../models/RecordDataModel.class.php";
}
if (!@include "models/ConditionsDiseaseModel.class.php") {
    include "../models/ConditionsDiseaseModel.class.php";
}
if (!@include "models/ExamsModel.class.php") {
    include "../models/ExamsModel.class.php";
}
if (!@include "models/NasfConductModel.class.php") {
    include "../models/NasfConductModel.class.php";
}
if (!include "persistences/RecordDataPersistence.class.php") {
    include "../persistences/RecordDataPersistence.class.php";
}
if (!include "persistences/ConditionsDiseasePersistence.class.php") {
    include "../persistences/ConditionsDiseasePersistence.class.php";
}
if (!include "persistences/ExamsPersistence.class.php") {
    include "../persistences/ExamsPersistence.class.php";
}
if (!include "persistences/NasfConductPersistence.class.php") {
    include "../persistences/NasfConductPersistence.class.php";
}

class AccompanyModel
{
    const ACCOMPANY = "ACCOMPANY";

    private $recordData, $conditionsDisease, $exams, $nasfCondut;

    public function __construct(RecordDataModel $recordData, ConditionsDiseaseModel $conditionsDisease, ExamsModel $examsMode,
                                NasfConductModel $nasfCondut)
    {
        $this->recordData = $recordData;
        $this->conditionsDisease = $conditionsDisease;
        $this->exams = $examsMode;
        $this->nasfCondut = $nasfCondut;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(RecordDataModel::RECORD_DATA => RecordDataPersistence::insert($db, $this->recordData),
            ConditionsDiseaseModel::CONDITIONS_DISEASE => ConditionsDiseasePersistence::insert($db, $this->conditionsDisease),
            ExamsModel::EXAMS => ExamsPersistence::insert($db, $this->exams),
            NasfConductModel::NASF_CONDUCT => NasfConductPersistence::insert($db, $this->nasfCondut));

        return $db->insert($query, $params);
    }

}