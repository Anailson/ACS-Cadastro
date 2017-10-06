package tcc.acs_cadastro_mobile.httpRequest;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IHttpResponse;
import tcc.acs_cadastro_mobile.interfaces.IJsonParser;
import tcc.acs_cadastro_mobile.models.AccompanyModel;
import tcc.acs_cadastro_mobile.models.AgentModel;
import tcc.acs_cadastro_mobile.models.ConditionsModel;
import tcc.acs_cadastro_mobile.models.ExamsModel;
import tcc.acs_cadastro_mobile.models.NasfConductModel;
import tcc.acs_cadastro_mobile.models.RealmInt;
import tcc.acs_cadastro_mobile.models.RecordDataModel;
import tcc.acs_cadastro_mobile.persistence.AgentPersistence;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
import tcc.acs_cadastro_mobile.subModels.Anthropometric;
import tcc.acs_cadastro_mobile.subModels.CommunicableDisease;
import tcc.acs_cadastro_mobile.subModels.ConditionDiseases;
import tcc.acs_cadastro_mobile.subModels.Conduct;
import tcc.acs_cadastro_mobile.subModels.EvaluatedExams;
import tcc.acs_cadastro_mobile.subModels.Forwarding;
import tcc.acs_cadastro_mobile.subModels.KidAndPregnant;
import tcc.acs_cadastro_mobile.subModels.NASF;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;
import tcc.acs_cadastro_mobile.subModels.RequestExams;
import tcc.acs_cadastro_mobile.subModels.TrackingDiseases;

public class AccompanyHttpRequest implements WebServiceConnection.ConnectionResult{

    private final String PATH = "AccompanyWebService.php";
    private IHttpResponse.Responses<AccompanyModel> response;
    private WebServiceConnection connection;
    private Context context;
    private AccompanyJsonParser parser;


    public AccompanyHttpRequest(Context context) {
        this.context = context;
        this.response = new IHttpResponse.Responses<>();
        this.connection = new WebServiceConnection(context, this);
        this.parser = new AccompanyJsonParser();
    }

    public void start(){
        connection.start();
    }

    public void getAll(long value, IHttpResponse.GetAll<AccompanyModel> getAll) {
        response.getAll = getAll;
        connection.setProgressDialog(R.string.msg_get_data_title, R.string.msg_get_accompany_data_message);
        connection.getAll(PATH, value);
    }

    public void insert(AccompanyModel accompany, IHttpResponse.Insert insert) {
        response.insert = insert;
        JSONObject json = parser.parserFrom(accompany);
        AgentModel agent = AgentPersistence.get();
        try {
            json.put(Constants.Agent.AGENT.name(), agent.asJson());
            Log.e("JSON", json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void insertAll(List<AccompanyModel> accompanies, IHttpResponse.InsertAll insertAll) {
        response.insertAll = insertAll;
        response.insertAll = insertAll;
        JSONArray jsonArray = parser.parserFrom(accompanies);
        try {
            jsonArray.put(0, AgentPersistence.get().asJson());
            //connection.postAll(PATH, jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private ProgressDialog getProgressDialog(String title, String message) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    public void onSuccess(WebServiceConnection.Request request) {
        switch (request.getMethod()) {
            case GET_ALL:
                response.getAll(parser.parserFrom(request.getArray()));
        }
    }

    @Override
    public void onGeneralError(WebServiceConnection.Request request) {
    }

    @Override
    public void onConnectionError(WebServiceConnection.Request request) {
    }

    @Override
    public void onInvalidValueError(WebServiceConnection.Request request) {
    }


    private class AccompanyJsonParser implements IJsonParser<AccompanyModel> {

        @Override
        public JSONArray parserFrom(List<AccompanyModel> list) {
            JSONArray array = new JSONArray();
            for (AccompanyModel accompany : list) {
                array.put(parserFrom(accompany));
            }
            return array;
        }

        @Override
        public List<AccompanyModel> parserFrom(JSONArray array) {

            List<AccompanyModel> list = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject object = array.getJSONObject(i);
                    list.add(parserFrom(object));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }

        @Override
        public JSONObject parserFrom(AccompanyModel object) {

            JSONObject json = new JSONObject();
            try {

                json.put(Constants.Accompany.RECORD_DATA.name(), getRecordData(object.getRecordData()));
                json.put(Constants.Accompany.CONDITIONS.name(), getConditions(object.getConditions()));
                json.put(Constants.Accompany.EXAMS.name(), getExams(object.getExams()));
                json.put(Constants.Accompany.NASF_CONDUCT.name(), getNasfConduct(object.getNasfConduct()));
            } catch (JSONException e) {
                e.getMessage();
            }
            return json;
        }

        @Override
        public AccompanyModel parserFrom(JSONObject json) {

            try{
                RecordDataModel recordData = getRecordData(json.getJSONObject(Constants.Accompany.RECORD_DATA.name()));
                ConditionsModel conditions = getConditions(json.getJSONObject(Constants.Accompany.CONDITIONS.name()));
                ExamsModel exams = getExams(json.getJSONObject(Constants.Accompany.EXAMS.name()));
                NasfConductModel nasfConduct = getNasfConduct(json.getJSONObject(Constants.Accompany.NASF_CONDUCT.name()));
                return new AccompanyModel(recordData, conditions, exams, nasfConduct);

            } catch (JSONException e){
                e.printStackTrace();
            }
            return new AccompanyModel();
        }

        private JSONObject getRecordData(RecordDataModel recordData) throws JSONException {
            JSONObject json = new JSONObject();
            json.put(Constants.Accompany.RECORD_DETAILS.name(), recordData.getRecordDetails().asJson());
            json.put(Constants.Accompany.ANTHROPOMETRIC.name(), recordData.getAnthropometric().asJson());
            json.put(Constants.Accompany.KID_PREGNANT.name(), recordData.getKidsPregnant().asJson());
            return json;
        }

        private RecordDataModel getRecordData(JSONObject json) throws JSONException {

            JSONObject data = json.getJSONObject(Constants.Accompany.RECORD_DETAILS.name());
            RecordDetails recordDetails = new RecordDetails();
            recordDetails.setCitizen(CitizenPersistence.get(data.getLong(Constants.Citizen.NUM_SUS.name())));
            recordDetails.setRecord(data.getLong(Constants.Accompany.RECORD.name()));
            recordDetails.setPlaceCare(data.getString(Constants.Accompany.PLACE_CARE.name()));
            recordDetails.setTypeCare(data.getString(Constants.Accompany.TYPE_CARE.name()));
            recordDetails.setShift(data.getString(Constants.Accompany.SHIFT.name()));

            data = json.getJSONObject(Constants.Accompany.ANTHROPOMETRIC.name());
            Anthropometric anthropometric = new Anthropometric();
            anthropometric.setHeight(data.getInt(Constants.Accompany.HEIGHT.name()));
            anthropometric.setVaccinates(data.getInt(Constants.Accompany.VACCINATES.name()) == 1);
            anthropometric.setWeight(data.getInt(Constants.Accompany.WEIGHT.name()));

            data = json.getJSONObject(Constants.Accompany.KID_PREGNANT.name());
            KidAndPregnant kidsPregnant = new KidAndPregnant();
            kidsPregnant.setWeeks(data.getInt(Constants.Accompany.WEEKS.name()));
            kidsPregnant.setPrevious(data.getInt(Constants.Accompany.PREVIOUS.name()));
            kidsPregnant.setPlannedPregnancy(convertIntToBoolean(data, Constants.Accompany.PLANNED_PREGNANCY.name()));
            kidsPregnant.setHomeCare(data.getString(Constants.Accompany.HOME_CARE.name()));
            kidsPregnant.setDum(data.getString(Constants.Accompany.DUM.name()));
            kidsPregnant.setChildBirth(data.getInt(Constants.Accompany.CHILD_BIRTH.name()));
            kidsPregnant.setBreastFeeding(data.getString(Constants.Accompany.BREAST_FEEDING.name()));

            return new RecordDataModel(recordDetails, anthropometric, kidsPregnant);

        }

        private JSONObject getConditions(ConditionsModel conditions) throws JSONException {

            JSONObject json = new JSONObject();
            json.put(Constants.Accompany.CONDITIONS_DISEASES.name(), conditions.getCondition().asJson());
            json.put(Constants.Accompany.COMMUNICABLE_DISEASES.name(), conditions.getCommunicable().asJson());
            json.put(Constants.Accompany.TRACKING_DISEASES.name(), conditions.getTracking().asJson());
            return json;
        }
        private ConditionsModel getConditions(JSONObject json) throws JSONException{
            JSONObject data = json.getJSONObject(Constants.Accompany.CONDITIONS_DISEASES.name());
            ConditionDiseases condition = new ConditionDiseases();
            condition.setAsthma(convertIntToBoolean(data, Constants.Accompany.ASTHMA.name()));
            condition.setMalnutrition(convertIntToBoolean(data, Constants.Accompany.MALNUTRITION.name()));
            condition.setDiabetes(convertIntToBoolean(data, Constants.Accompany.DIABETES.name()));
            condition.setDpoc(convertIntToBoolean(data, Constants.Accompany.DPOC.name()));
            condition.setHypertension(convertIntToBoolean(data, Constants.Accompany.HYPERTENSION.name()));
            condition.setObesity(convertIntToBoolean(data, Constants.Accompany.OBESITY.name()));
            condition.setPrenatal(convertIntToBoolean(data, Constants.Accompany.PRENATAL.name()));
            condition.setChildcare(convertIntToBoolean(data, Constants.Accompany.CHILD_CARE.name()));
            condition.setPuerperium(convertIntToBoolean(data, Constants.Accompany.PUERPERIUM.name()));
            condition.setSexualHealth(convertIntToBoolean(data, Constants.Accompany.SEXUAL_HEALTH.name()));
            condition.setSmoking(convertIntToBoolean(data, Constants.Accompany.SMOKING.name()));
            condition.setAlcohol(convertIntToBoolean(data, Constants.Accompany.ALCOHOL.name()));
            condition.setDrugs(convertIntToBoolean(data, Constants.Accompany.DRUGS.name()));
            condition.setMentalHealth(convertIntToBoolean(data, Constants.Accompany.MENTAL_HEALTH.name()));
            condition.setRehabilitation(convertIntToBoolean(data, Constants.Accompany.REHABILITATION.name()));

            CommunicableDisease communicable = new CommunicableDisease();
            data = json.getJSONObject(Constants.Accompany.COMMUNICABLE_DISEASES.name());
            communicable.setTuberculosis(convertIntToBoolean(data, Constants.Accompany.TUBERCULOSIS.name()));
            communicable.setLeprosy(convertIntToBoolean(data, Constants.Accompany.LEPROSY.name()));
            communicable.setDst(convertIntToBoolean(data, Constants.Accompany.DST.name()));
            communicable.setDengue(convertIntToBoolean(data, Constants.Accompany.DENGUE.name()));

            TrackingDiseases tracking = new TrackingDiseases();
            data = json.getJSONObject(Constants.Accompany.TRACKING_DISEASES.name());
            tracking.setCervicalCancer(convertIntToBoolean(data, Constants.Accompany.CERVICAL_CANCER.name()));
            tracking.setCardiovascular(convertIntToBoolean(data, Constants.Accompany.CARDIOVASCULAR.name()));
            tracking.setBreastCancer(convertIntToBoolean(data, Constants.Accompany.BREAST_CANCER.name()));

            RealmList<RealmInt> cidCiap = new RealmList<>();
            JSONArray cidCiapArray = json.optJSONArray("CID_CIAP");
            if(cidCiapArray != null){
                for(int i = 0; i< cidCiapArray.length(); i++){
                    data = cidCiapArray.getJSONObject(i);
                    String s = data.getString("CODE");
                    Log.e("CODE", s);
                    cidCiap.add(new RealmInt(s));

                }
            }else{
                Log.e("NULL", "NULL");
            }

            return new ConditionsModel(condition, communicable, tracking, cidCiap);
        }

        private JSONObject getExams(ExamsModel exams) {

            JSONObject json = new JSONObject();
            try {
                json.put(Constants.Accompany.PIC.name(), exams.getPic());
                json.put(Constants.Accompany.REQUEST_EXAMS.name(), exams.getRequest().asJson());
                json.put(Constants.Accompany.EVALUATED_EXAMS.name(), exams.getEvaluate().asJson());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json;
        }

        private ExamsModel getExams(JSONObject json) throws JSONException{
            String pic = json.getString(Constants.Accompany.PIC.name());

            JSONObject data = json.getJSONObject(Constants.Accompany.REQUEST_EXAMS.name());
            RequestExams request = new RequestExams(getExamsValue(data));

            data = json.getJSONObject(Constants.Accompany.EVALUATED_EXAMS.name());
            EvaluatedExams evaluate = new EvaluatedExams(getExamsValue(data));

            return new ExamsModel(pic, request, evaluate, new RealmList<RealmInt>());
        }

        private boolean[] getExamsValue(JSONObject json) throws JSONException{
            boolean totalCholesterol = convertIntToBoolean(json, Constants.Accompany.TOTAL_CHOLESTEROL.name());
            boolean creatinine = convertIntToBoolean(json, Constants.Accompany.CREATINE.name());
            boolean easEqu = convertIntToBoolean(json, Constants.Accompany.EAS_EQU.name());
            boolean electrocardiogram = convertIntToBoolean(json, Constants.Accompany.ELECTROCARDIOGRAM.name());
            boolean hemoglobin = convertIntToBoolean(json, Constants.Accompany.HEMOGLOBIN.name());
            boolean spirometry = convertIntToBoolean(json, Constants.Accompany.SPIROMETRY.name());
            boolean sputum = convertIntToBoolean(json, Constants.Accompany.SPUTUM.name());
            boolean glycemia = convertIntToBoolean(json, Constants.Accompany.GLYCEMIA.name());
            boolean hdl = convertIntToBoolean(json, Constants.Accompany.HDL.name());
            boolean glycemic = convertIntToBoolean(json, Constants.Accompany.GLYCEMIC_HEMOGLIBIN.name());
            boolean bloodCount = convertIntToBoolean(json, Constants.Accompany.BLOOD_COUNT.name());
            boolean ldl = convertIntToBoolean(json, Constants.Accompany.LDL.name());
            boolean eyes = convertIntToBoolean(json, Constants.Accompany.EYES.name());
            boolean syphilis = convertIntToBoolean(json, Constants.Accompany.SYPHILIS.name());
            boolean dengue = convertIntToBoolean(json, Constants.Accompany.DENGUE.name());
            boolean hiv = convertIntToBoolean(json, Constants.Accompany.HIV.name());
            boolean humanAntiglobulin = convertIntToBoolean(json, Constants.Accompany.HUMAN_ANTIGLOBULIN.name());
            boolean earTest = convertIntToBoolean(json, Constants.Accompany.EAR_TEST.name());
            boolean testPregnancy = convertIntToBoolean(json, Constants.Accompany.PREGNANCY_TEST.name());
            boolean eyeTest = convertIntToBoolean(json, Constants.Accompany.EYE_TEST.name());
            boolean testFoot = convertIntToBoolean(json, Constants.Accompany.FOOT_TEST.name());
            boolean ultrasonography = convertIntToBoolean(json, Constants.Accompany.ULTRASONOGRAPHY.name());
            boolean uroculture = convertIntToBoolean(json, Constants.Accompany.UROCULTURE.name());

            return new boolean[]{totalCholesterol, creatinine, easEqu, electrocardiogram, hemoglobin,
                    spirometry,sputum, glycemia, hdl, glycemic, bloodCount, ldl, eyes, syphilis, dengue, hiv,
                    humanAntiglobulin, earTest, testPregnancy, eyeTest, testFoot, ultrasonography, uroculture};
        }

        private JSONObject getNasfConduct(NasfConductModel nasfConduct) throws JSONException {

            JSONObject json = new JSONObject();
            json.put(Constants.Accompany.NASF_CONDUCT.name(), nasfConduct.isObservation());
            json.put(Constants.Accompany.NASF.name(), nasfConduct.getNasf().asJson());
            json.put(Constants.Accompany.CONDUCT.name(), nasfConduct.getConduct().asJson());
            json.put(Constants.Accompany.FORWARDING.name(), nasfConduct.getForwarding().asJson());
            return json;
        }

        private NasfConductModel getNasfConduct(JSONObject json) throws JSONException{
            boolean observation = convertIntToBoolean(json, Constants.Accompany.NASF_CONDUCT.name());

            NASF nasf = new NASF();
            JSONObject data = json.getJSONObject(Constants.Accompany.NASF.name());
            nasf.setProcedures(convertIntToBoolean(data, Constants.Accompany.PROCEDURES.name()));
            nasf.setEvaluation(convertIntToBoolean(data, Constants.Accompany.EVALUATION.name()));
            nasf.setPrescription(convertIntToBoolean(data, Constants.Accompany.PRESCRIPTION.name()));

            Conduct conduct = new Conduct();
            data = json.getJSONObject(Constants.Accompany.CONDUCT.name());
            conduct.setScheduledCare(convertIntToBoolean(data, Constants.Accompany.SCHEDULE_CARE.name()));
            conduct.setScheduledAppointment(convertIntToBoolean(data, Constants.Accompany.SCHEDULE_APPOINTMENT.name()));
            conduct.setRelsease(convertIntToBoolean(data, Constants.Accompany.RELEASES.name()));
            conduct.setNasfScheduling(convertIntToBoolean(data, Constants.Accompany.NASF_SCHEDULE.name()));
            conduct.setGroupScheduling(convertIntToBoolean(data, Constants.Accompany.GROUPS_SCHEDULE.name()));

            Forwarding forwarding = new Forwarding();
            data = json.getJSONObject(Constants.Accompany.FORWARDING.name());
            forwarding.setUrgency(convertIntToBoolean(data, Constants.Accompany.URGENCY.name()));
            forwarding.setSpecializedService(convertIntToBoolean(data, Constants.Accompany.SPECIALIZED_SERVICE.name()));
            forwarding.setInTheDay(convertIntToBoolean(data, Constants.Accompany.IN_DAY.name()));
            forwarding.setIntersectoral(convertIntToBoolean(data, Constants.Accompany.INTER_SECTORAL.name()));
            forwarding.setHospitalization(convertIntToBoolean(data, Constants.Accompany.HOSPITALIZATION.name()));
            forwarding.setHomeCareService(convertIntToBoolean(data, Constants.Accompany.HOME_CARE_SERVICE.name()));
            forwarding.setCaps(convertIntToBoolean(data, Constants.Accompany.CAPS.name()));

            return new NasfConductModel(observation, nasf, conduct, forwarding);
        }
    }

    private boolean convertIntToBoolean(JSONObject json, String key) throws JSONException {
        return json.getInt(key) == 1;
    }
}
