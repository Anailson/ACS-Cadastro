<?php

if (!@include "models/RecordDataModel.class.php") {
    include "../models/RecordDataModel.class.php";
}
if (!@include "models/ConditionsDiseaseModel.class.php") {
    include "../models/ConditionsModel.class.php";
}
if (!@include "models/ExamsModel.class.php") {
    include "../models/ExamsModel.class.php";
}
if (!@include "models/NasfConductModel.class.php") {
    include "../models/NasfConductModel.class.php";
}
if (!@include "persistences/RecordDataPersistence.class.php") {
    include "../persistences/RecordDataPersistence.class.php";
}
if (!@include "persistences/ConditionsPersistence.class.php") {
    include "../persistences/ConditionsPersistence.class.php";
}
if (!@include "persistences/ExamsPersistence.class.php") {
    include "../persistences/ExamsPersistence.class.php";
}
if (!@include "persistences/NasfConductPersistence.class.php") {
    include "../persistences/NasfConductPersistence.class.php";
}

class AccompanyModel
{
    const ACCOMPANY = "ACCOMPANY";

    private $recordData, $conditions, $exams, $nasfConduct;

    public function __construct(RecordDataModel $recordData, ConditionsModel $conditionsDisease, ExamsModel $examsMode,
                                NasfConductModel $nasfConduct)
    {
        $this->recordData = $recordData;
        $this->conditions = $conditionsDisease;
        $this->exams = $examsMode;
        $this->nasfConduct = $nasfConduct;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":ID_" . RecordDataModel::RECORD_DATA => RecordDataPersistence::insert($db, $this->recordData),
            ":ID_" . ConditionsModel::CONDITIONS => ConditionsPersistence::insert($db, $this->conditions),
            ":ID_" . ExamsModel::EXAMS => ExamsPersistence::insert($db, $this->exams),
            ":ID_" . NasfConductModel::NASF_CONDUCT => NasfConductPersistence::insert($db, $this->nasfConduct)
        );

        return $db->insert($query, $params);
    }

    public static function getFromArray(array $array)
    {
        return new AccompanyModel(RecordDataModel::getFromArray($array[RecordDataModel::RECORD_DATA]),
            ConditionsModel::getFromArray($array[ConditionsModel::CONDITIONS]),
            ExamsModel::getFromArray($array[ExamsModel::EXAMS]),
            NasfConductModel::getFromArray($array[NasfConductModel::NASF_CONDUCT]));
    }

}