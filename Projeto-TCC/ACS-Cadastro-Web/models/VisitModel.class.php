<?php
if (!@include "models/RecordVisitModel.class.php") {
    include "../models/RecordVisitModel.class.php";
}
if (!@include "models/ReasonsVisitModel.class.php") {
    include "../models/ReasonsVisitModel.class.php";
}
if(!@include "persistences/RecordDataPersistence.class.php") {
    include "../persistences/RecordDataPersistence.class.php";
}
if(!@include "persistences/ReasonsVisitPersistence.class.php") {
    include "../persistences/ReasonsVisitPersistence.class.php";
}

class VisitModel
{
    private $recordVisit, $reasonsVisit;

    public function __construct(RecordVisitModel $recordVisit, ReasonsVisitModel $reasonsVisit)
    {
        $this->recordVisit = $recordVisit;
        $this->reasonsVisit = $reasonsVisit;
    }

    public function save(AcsDataBase $db, $query)
    {
        $params = array(":ID_" . RecordVisitModel::RECORD_VISIT =>  RecordVisitPersistence::insert($db, $this->recordVisit),
            ":ID_" . ReasonsVisitModel::REASONS_VISIT => ReasonsVisitPersistence::insert($db, $this->reasonsVisit));
        if (in_array(false, $params)) {
            return false;
        }
        return $db->insert($query, $params);
    }
}