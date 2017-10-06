<?php
if (!@include_once "models/AccompanyModel.class.php") {
    include_once "../models/AccompanyModel.class.php";
}
if (!@include_once "models/RecordDataModel.class.php") {
    include_once "../models/RecordDataModel.class.php";
}
if (!@include_once "persistences/AcsDataBase.php") {
    include_once "../persistences/AcsDataBase.php";
}
if (!@include_once "persistences/AgentsPersistence.php") {
    include_once "../persistences/AgentsPersistence.php";
}

class AccompanyPersistence
{
    static function get($record)
    {

    }
    public static function getAll($numSus = false)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT  CN.CONDITIONS_DISEASE_ID AS 'CONDITIONS_ID', P.NUM_SUS, 
                RT.RECORD, RT.PLACE_CARE, RT.TYPE_CARE, RT.SHIFT,
                AN.WEIGHT, AN.HEIGHT, AN.VACCINATES,
                KP.BREAST_FEEDING, KP.DUM, KP.PLANNED_PREGNANCY, KP.WEEKS, KP.PREVIOUS, KP.CHILD_BIRTH, KP.HOME_CARE,
                CD.ASTHMA, CD.MALNUTRITION, CD.DIABETES, CD.DIABETES, CD.DPOC, CD.HYPERTENSION, CD.OBESITY, 
                CD.PRENATAL, CD.CHILD_CARE, CD.PUERPERIUM, CD.SEXUAL_HEALTH, 
                CD.SMOKING, CD.ALCOHOL, CD.DRUGS, CD.MENTAL_HEALTH, CD.REHABILITATION,
                CM.TUBERCULOSIS, CM.LEPROSY, CM.DENGUE, CM.DST, 
                TD.CERVICAL_CANCER, TD.BREAST_CANCER, TD.CARDIOVASCULAR,
                EX.PIC, 
                RE.TOTAL_CHOLESTEROL 'RE_TOTAL_CHOLESTEROL', RE.CREATINE 'RE_CREATINE', RE.EAS_EQU 'RE_EAS_EQU', 
                RE.ELECTROCARDIOGRAM 'RE_ELECTROCARDIOGRAM', RE.HEMOGLOBIN 'RE_HEMOGLOBIN', RE.SPIROMETRY 'RE_SPIROMETRY', 
                RE.SPUTUM 'RE_SPUTUM', RE.GLYCEMIA 'RE_GLYCEMIA', RE.HDL 'RE_HDL', RE.GLYCEMIC_HEMOGLIBIN 'RE_GLYCEMIC_HEMOGLIBIN', 
                RE.BLOOD_COUNT 'RE_BLOOD_COUNT', RE.LDL 'RE_LDL', RE.EYES 'RE_EYES', RE.SYPHILIS 'RE_SYPHILIS', 
                RE.DENGUE 'RE_DENGUE',RE.HUMAN_ANTIGLOBULIN 'RE_HUMAN_ANTIGLOBULIN', RE.HIV 'RE_HIV', RE.EAR_TEST 'RE_EAR_TEST', 
                RE.PREGNANCY_TEST 'RE_PREGNANCY_TEST', RE.EYE_TEST 'RE_EYE_TEST', RE.FOOT_TEST 'RE_FOOT_TEST', 
                RE.ULTRASONOGRAPHY 'RE_ULTRASONOGRAPHY', RE.UROCULTURE 'RE_UROCULTURE',
                EE.TOTAL_CHOLESTEROL 'EE_TOTAL_CHOLESTEROL', EE.CREATINE 'EE_CREATINE', EE.EAS_EQU 'EE_EAS_EQU', 
                EE.ELECTROCARDIOGRAM 'EE_ELECTROCARDIOGRAM', EE.HEMOGLOBIN 'EE_HEMOGLOBIN', EE.SPIROMETRY 'EE_SPIROMETRY', 
                EE.SPUTUM 'EE_SPUTUM', EE.GLYCEMIA 'EE_GLYCEMIA', EE.HDL 'EE_HDL', EE.GLYCEMIC_HEMOGLIBIN 'EE_GLYCEMIC_HEMOGLIBIN', 
                EE.BLOOD_COUNT 'EE_BLOOD_COUNT', EE.LDL 'EE_LDL', EE.EYES 'EE_EYES', EE.SYPHILIS 'EE_SYPHILIS', 
                EE.DENGUE 'EE_DENGUE', EE.HUMAN_ANTIGLOBULIN 'EE_HUMAN_ANTIGLOBULIN', EE.HIV 'EE_HIV', EE.EAR_TEST 'EE_EAR_TEST', 
                EE.PREGNANCY_TEST 'EE_PREGNANCY_TEST', EE.EYE_TEST 'EE_EYE_TEST', EE.FOOT_TEST 'EE_FOOT_TEST', 
                EE.ULTRASONOGRAPHY 'EE_ULTRASONOGRAPHY', EE.UROCULTURE 'EE_UROCULTURE',
                NC.NASF_CONDUCT, 
                NF.EVALUATION, NF.PROCEDURES, NF.PROCEDURES, NF.PRESCRIPTION,
                CT.SCHEDULE_APPOINTMENT, CT.SCHEDULE_CARE, CT.GROUPS_SCHEDULE, CT.NASF_SCHEDULE, CT.RELEASES,
                FR.IN_DAY, FR.SPECIALIZED_SERVICE, FR.CAPS, FR.HOSPITALIZATION, FR.URGENCY, FR.HOME_CARE_SERVICE, FR.INTER_SECTORAL
            FROM TB_ACCOMPANY AS AC
            INNER JOIN tb_record_data 			AS RD ON RD.RECORD_DATA_ID = AC.ID_RECORD_DATA
            INNER JOIN tb_record_details 		AS RT ON RT.RECORD_DETAILS_ID = RD.ID_RECORD_DETAILS
            INNER JOIN tb_antropometric			AS AN ON AN.ANTROPOMETRIC_ID = RD.ID_ANTHROPOMETRIC
            INNER JOIN tb_kid_pregnant 			AS KP ON KP.KID_PREGNANT_ID = RD.ID_KID_PREGNANT
            INNER JOIN TB_CITIZEN 				AS CZ ON CZ.CITIZEN_ID = RT.ID_CITIZEN
            INNER JOIN tb_personal_data 		AS PD ON PD.PERSONAL_DATA_ID = CZ.ID_PERSONAL_DATA 
            INNER JOIN tb_particular 			AS P ON P.PARTICULAR_ID = PD.ID_PARTICULAR
            INNER JOIN tb_conditions 			AS CN ON CN.CONDITIONS_DISEASE_ID = AC.ID_CONDITIONS
            INNER JOIN tb_conditions_disease 	AS CD ON CD.CONDITIONS_ID = CN.ID_CONDITIONS
            INNER JOIN tb_communicable_diseases AS CM ON CM.COMMUNICABLE_ID = CN.ID_COMMUNICABLE
            INNER JOIN tb_tracking_diseases 	AS TD ON TD.TRACKING_ID = CN.ID_TRACKING
            INNER JOIN tb_exams 				AS EX ON EX.EXAMS_ID = AC.ID_EXAMS
            INNER JOIN tb_request_exams 		AS RE ON RE.REQUEST_EXAMS_ID = EX.ID_REQUEST_EXAMS
            INNER JOIN tb_evaluated_exams 		AS EE ON EE.EVALUATED_EXAMS_ID = EX.ID_EVALUATED_EXAMS
            INNER JOIN tb_nasf_conduct 			AS NC ON NC.NASF_CONDUCT_ID = AC.ID_NASF_CONDUCT 
            INNER JOIN tb_nasf 					AS NF ON NF.NASF_ID = NC.ID_NASF
            INNER JOIN tb_conduct 				AS CT ON CT.CONDUCT_ID = NC.NASF_CONDUCT_ID
            INNER JOIN tb_forwarding 			AS FR ON FR.FORWARDING_ID = NC.ID_FORWARDING";
            //";
        if($numSus){
            $query .= " WHERE AC.ID_AGENT = (SELECT AG.ID FROM TB_AGENT AS AG WHERE AG.NUM_SUS = :NUM_SUS)";
            $values = $db->select($query, array(":NUM_SUS" => $numSus));
        }else {
            $values = $db->select($query);
        }

        $accompanies = array();
        foreach ($values as $value) {
            $recordData = self::getRecordData($value);
            $conditionsCidCiap = self::getAnotherCidCiap($db, $value[ConditionsModel::CONDITIONS . "_ID"]);
            $conditions = self::getConditions($conditionsCidCiap, $value);
            $exams = self::getExams($value);
            $nasfConduct = self::getNasfConduct($value);

            $accompanies[] = array(RecordDataModel::RECORD_DATA => $recordData,
                ConditionsModel::CONDITIONS => $conditions,
                ExamsModel::EXAMS => $exams,
                NasfConductModel::NASF_CONDUCT => $nasfConduct);

        }
        return $accompanies;
    }

    public static function getAllSimpleInfoAccompany()
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "SELECT R.RECORD, P.NUM_SUS, P.NUM_NIS, P.NAME, P.SOCIAL_NAME, P.BIRTH_DATE
                FROM TB_ACCOMPANY AS ACC 
                INNER JOIN tb_record_data as RD on RD.RECORD_DATA_ID = ACC.ID_RECORD_DATA
                INNER JOIN tb_record_details AS R on R.RECORD_DETAILS_ID = RD.RECORD_DATA_ID
                INNER JOIN tb_citizen as CZ on CZ.CITIZEN_ID = R.ID_CITIZEN
                INNER JOIN tb_personal_data as PD on PD.PERSONAL_DATA_ID = CZ.ID_PERSONAL_DATA
                INNER JOIN tb_particular as P on p.PARTICULAR_ID = PD.PERSONAL_DATA_ID";
        return $db->select($query);
    }

    static function insert($numSus, AccompanyModel $accompany)
    {
        $db = new AcsDataBase(AcsDataBase::DB_NAME);
        $query = "INSERT INTO TB_ACCOMPANY(ID_RECORD_DATA, ID_CONDITIONS, ID_EXAMS, ID_NASF_CONDUCT, ID_AGENT) 
                  VALUES (:ID_RECORD_DATA, :ID_CONDITIONS, :ID_EXAMS, :ID_NASF_CONDUCT, :ID_AGENT)";
        $ids = $accompany->save($db, $query);

        if (in_array(false, $ids)) {
            return false;
        }
        $agent = AgentsPersistence::get($numSus);
        $ids[":ID_AGENT"] = $agent['ID'];
        return $db->insert($query, $ids);
    }

    static function update($record)
    {

    }

    static function delete($record)
    {

    }

    private function getRecordData(array $values)
    {
        $recordDetails = array(RecordDetails::RECORD => $values[RecordDetails::RECORD], RecordDetails::PLACE_CARE => $values[RecordDetails::PLACE_CARE],
            RecordDetails::TYPE_CARE => $values[RecordDetails::TYPE_CARE], RecordDetails::SHIFT => $values[RecordDetails::SHIFT],
            RecordDetails::NUM_SUS => $values[RecordDetails::NUM_SUS]);
        $anthropometric = array(Anthropometric::WEIGHT => $values[Anthropometric::WEIGHT], Anthropometric::VACCINATES => $values[Anthropometric::VACCINATES],
            Anthropometric::HEIGHT => $values[Anthropometric::HEIGHT]);
        $kidsPregnant = array(KidAndPregnant::WEEKS => $values[KidAndPregnant::WEEKS], KidAndPregnant::PREVIOUS => $values[KidAndPregnant::PREVIOUS],
            KidAndPregnant::PLANNED_PREGNANCY => $values[KidAndPregnant::PLANNED_PREGNANCY], KidAndPregnant::HOME_CARE => $values[KidAndPregnant::HOME_CARE],
            KidAndPregnant::DUM => $values[KidAndPregnant::DUM], KidAndPregnant::CHILD_BIRTH => $values[KidAndPregnant::CHILD_BIRTH],
            KidAndPregnant::BREAST_FEEDING => $values[KidAndPregnant::BREAST_FEEDING]);
        return array(RecordDetails::RECORD_DETAILS => $recordDetails, Anthropometric::ANTHROPOMETRIC => $anthropometric, KidAndPregnant::KID_PREGNANT => $kidsPregnant);
    }

    private function getAnotherCidCiap(AcsDataBase $db, $idCondition = 0)
    {
        if($idCondition != 0){
            $query = "SELECT CC.CODE, CC.DESCRIPTION
                FROM tb_conditions_has_cid_ciap AS CD
                INNER JOIN tb_cid_ciap AS CC ON CC.CID_CIAP_ID = CD.ID_CID_CIAP
                WHERE CD.ID_CONDITIONS = :ID_CONDITIONS";
            return $db->select($query, array(":ID_" . ConditionsModel::CONDITIONS => $idCondition));
        }
        return null;
    }

    private function getConditions(array $anotherCidCiap, array $values)
    {
        $conditionDisease = array(ConditionsDiseases::SMOKING => $values[ConditionsDiseases::SMOKING], ConditionsDiseases::SEXUAL_HEALTH => $values[ConditionsDiseases::SEXUAL_HEALTH],
            ConditionsDiseases::REHABILITATION => $values[ConditionsDiseases::REHABILITATION], ConditionsDiseases::PUERPERIUM => $values[ConditionsDiseases::PUERPERIUM],
            ConditionsDiseases::PRENATAL => $values[ConditionsDiseases::PRENATAL], ConditionsDiseases::OBESITY => $values[ConditionsDiseases::OBESITY],
            ConditionsDiseases::MENTAL_HEALTH => $values[ConditionsDiseases::MENTAL_HEALTH], ConditionsDiseases::MALNUTRITION => $values[ConditionsDiseases::MALNUTRITION],
            ConditionsDiseases::HYPERTENSION => $values[ConditionsDiseases::HYPERTENSION], ConditionsDiseases::ASTHMA => $values[ConditionsDiseases::ASTHMA],
            ConditionsDiseases::ALCOHOL => $values[ConditionsDiseases::ALCOHOL], ConditionsDiseases::DIABETES => $values[ConditionsDiseases::DIABETES],
            ConditionsDiseases::DPOC => $values[ConditionsDiseases::DPOC],
            ConditionsDiseases::CHILD_CARE => $values[ConditionsDiseases::CHILD_CARE], ConditionsDiseases::DRUGS => $values[ConditionsDiseases::DRUGS]);
        $communicableDisease = array(CommunicableDiseases::TUBERCULOSIS => $values[CommunicableDiseases::TUBERCULOSIS], CommunicableDiseases::LEPROSY => $values[CommunicableDiseases::LEPROSY],
            CommunicableDiseases::DST => $values[CommunicableDiseases::DST], CommunicableDiseases::DENGUE => $values[CommunicableDiseases::DENGUE]);
        $trackingDisease = array(TrackingDiseases::CERVICAL_CANCER => $values[TrackingDiseases::CERVICAL_CANCER], TrackingDiseases::CARDIOVASCULAR => $values[TrackingDiseases::CARDIOVASCULAR],
            TrackingDiseases::BREAST_CANCER => $values[TrackingDiseases::BREAST_CANCER]);

        $condition = array(ConditionsDiseases::CONDITIONS_DISEASES => $conditionDisease, CommunicableDiseases::COMMUNICABLE_DISEASES => $communicableDisease,
            TrackingDiseases::TRACKING_DISEASES => $trackingDisease);
        if($anotherCidCiap){
            $condition[ConditionsModel::CID_CIAP] = $anotherCidCiap;
        }
        return $condition;
    }

    private function getExams(array $values)
    {
        return array(ExamsModel::PIC => $values[ExamsModel::PIC], RequestExams::REQUEST_EXAMS => self::getExamsValues("RE_", $values),
            EvaluatedExams::EVALUATED_EXAMS => self::getExamsValues("EE_", $values));

    }

    private function getExamsValues($prefix, array $values)
    {
        return array(RequestExams::TOTAL_CHOLESTEROL => $values[$prefix . RequestExams::TOTAL_CHOLESTEROL], RequestExams::CREATINE => $values[$prefix . RequestExams::CREATINE],
            RequestExams::EAS_EQU => $values[$prefix . RequestExams::EAS_EQU], RequestExams::ELECTROCARDIOGRAM => $values[$prefix . RequestExams::ELECTROCARDIOGRAM],
            RequestExams::HEMOGLOBIN => $values[$prefix . RequestExams::HEMOGLOBIN], RequestExams::SPIROMETRY => $values[$prefix . RequestExams::SPIROMETRY],
            RequestExams::SPUTUM => $values[$prefix . RequestExams::SPUTUM], RequestExams::GLYCEMIA => $values[$prefix . RequestExams::GLYCEMIA],
            RequestExams::HDL => $values[$prefix . RequestExams::HDL], RequestExams::GLYCEMIC_HEMOGLIBIN => $values[$prefix . RequestExams::GLYCEMIC_HEMOGLIBIN],
            RequestExams::BLOOD_COUNT => $values[$prefix . RequestExams::BLOOD_COUNT], RequestExams::LDL => $values[$prefix . RequestExams::LDL],
            RequestExams::EYES => $values[$prefix . RequestExams::EYES], RequestExams::SYPHILIS => $values[$prefix . RequestExams::SYPHILIS],
            RequestExams::DENGUE => $values[$prefix . RequestExams::DENGUE], RequestExams::HIV => $values[$prefix . RequestExams::HIV],
            RequestExams::HUMAN_ANTIGLOBULIN => $values[$prefix . RequestExams::HUMAN_ANTIGLOBULIN],
            RequestExams::EAR_TEST => $values[$prefix . RequestExams::EAR_TEST], RequestExams::PREGNANCY_TEST => $values[$prefix . RequestExams::PREGNANCY_TEST],
            RequestExams::EYE_TEST => $values[$prefix . RequestExams::EYE_TEST], RequestExams::FOOT_TEST => $values[$prefix . RequestExams::FOOT_TEST],
            RequestExams::ULTRASONOGRAPHY => $values[$prefix . RequestExams::ULTRASONOGRAPHY], RequestExams::UROCULTURE => $values[$prefix . RequestExams::UROCULTURE]);
    }

    private function getNasfConduct(array $values)
    {
        $nasf = array(Nasf::EVALUATION => $values[Nasf::EVALUATION], Nasf::PRESCRIPTION=> $values[Nasf::PRESCRIPTION], Nasf::PROCEDURES=> $values[Nasf::PROCEDURES]);
        $conduct = array(Conduct::GROUPS_SCHEDULE => $values[Conduct::GROUPS_SCHEDULE], Conduct::NASF_SCHEDULE=> $values[Conduct::NASF_SCHEDULE],
            Conduct::RELEASES=> $values[Conduct::RELEASES],Conduct::SCHEDULE_APPOINTMENT=> $values[Conduct::SCHEDULE_APPOINTMENT],
            Conduct::SCHEDULE_CARE=> $values[Conduct::SCHEDULE_CARE]);
        $forwarding = array(Forwarding::CAPS => $values[Forwarding::CAPS], Forwarding::URGENCY => $values[Forwarding::URGENCY],
            Forwarding::SPECIALIZED_SERVICE=> $values[Forwarding::SPECIALIZED_SERVICE], Forwarding::IN_DAY => $values[Forwarding::IN_DAY],
            Forwarding::INTER_SECTORAL=> $values[Forwarding::INTER_SECTORAL], Forwarding::HOSPITALIZATION=> $values[Forwarding::HOSPITALIZATION],
            Forwarding::HOME_CARE_SERVICE=> $values[Forwarding::HOME_CARE_SERVICE]);

        return array(NasfConductModel::NASF_CONDUCT => $values[NasfConductModel::NASF_CONDUCT], Nasf::NASF => $nasf, Conduct::CONDUCT => $conduct, Forwarding::FORWARDING => $forwarding);
    }

}

/*
*/