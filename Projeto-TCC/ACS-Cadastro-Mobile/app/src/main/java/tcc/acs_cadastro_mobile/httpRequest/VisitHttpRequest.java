package tcc.acs_cadastro_mobile.httpRequest;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.R;
import tcc.acs_cadastro_mobile.interfaces.IHttpResponse;
import tcc.acs_cadastro_mobile.interfaces.IJsonParser;
import tcc.acs_cadastro_mobile.models.ReasonsVisitModel;
import tcc.acs_cadastro_mobile.models.RecordVisitModel;
import tcc.acs_cadastro_mobile.models.VisitModel;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;
import tcc.acs_cadastro_mobile.subModels.ActiveSearch;
import tcc.acs_cadastro_mobile.subModels.AnotherReasons;
import tcc.acs_cadastro_mobile.subModels.Following;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;

public class VisitHttpRequest implements WebServiceConnection.ConnectionResult{

    private final String PATH = "VisitWebService.php";
    private IHttpResponse.Responses<VisitModel> response;
    private WebServiceConnection webService;
    private VisitJsonParser parser;

    public VisitHttpRequest(Context context) {
        response = new IHttpResponse.Responses<>();
        webService = new WebServiceConnection(context, this);
        parser = new VisitJsonParser();
    }

    public void start(){
        webService.start();
    }

    public void getAll(long param, IHttpResponse.GetAll<VisitModel> getAll){
        response.getAll = getAll;
        webService.setProgressDialog(R.string.msg_get_data_title, R.string.msg_get_visit_data_message);
        webService.getAll(PATH, param);
    }

    public void insert(VisitModel visit, IHttpResponse.Insert insert){
        response.insert = insert;
        webService.post(PATH, parser.parserFrom(visit));
    }

    public void insertAll(List<VisitModel> list, IHttpResponse.InsertAll insertAll) {
        response.insertAll = insertAll;
        webService.postAll(PATH, parser.parserFrom(list));
    }

    @Override
    public void onSuccess(WebServiceConnection.Request request) {
        switch (request.getMethod()){
            case GET_ALL: response.getAll(parser.parserFrom(request.getArray()));break;
            //case POST: response.insert(request.getValue()); break;
            //case POST_ALL: response.insertAll(); break;
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

    private class VisitJsonParser implements IJsonParser<VisitModel>{

        @Override
        public JSONObject parserFrom(VisitModel visit) {

            JSONObject json = new JSONObject();
            try {
                json.put(Constants.Visit.RECORD_VISIT.name(), getRecordVisit(visit.getDetails()));
                json.put(Constants.Visit.REASONS_VISIT.name(), getReasonsVisit(visit.getReasons()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        public VisitModel parserFrom(JSONObject json) {
            try {
                RecordVisitModel record = getRecordVisit(json.getJSONObject(Constants.Visit.RECORD_VISIT.name()));
                ReasonsVisitModel reasons = getReasonsVisit(json.getJSONObject(Constants.Visit.REASONS_VISIT.name()));
                return new VisitModel(record, reasons);
            } catch (JSONException e) {
                Log.e("JSONException", json.toString());
                Log.e("parserFrom(" + VisitModel.class.getName()+")", e.getMessage());
            }
            return new VisitModel();
        }

        @Override
        public List<VisitModel> parserFrom(JSONArray array) {

            List<VisitModel> list = new ArrayList<>();
            for(int i = 0; i< array.length(); i++){
                try {
                    list.add(parserFrom(array.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }

        @Override
        public JSONArray parserFrom(List<VisitModel> list) {

            JSONArray array = new JSONArray();
            for(VisitModel visit : list){
                array.put(parserFrom(visit));
            }
            return array;
        }

        private JSONObject getReasonsVisit(ReasonsVisitModel reasons) throws JSONException {
            JSONObject json = new JSONObject();
            json.put(Constants.Visit.RESULT.name(),reasons.getResult());
            json.put(Constants.Visit.ACTIVE_SEARCH.name(), reasons.getActive().asJson());
            json.put(Constants.Visit.FOLLOWING.name(), reasons.getFollowing().asJson());
            json.put(Constants.Visit.ANOTHER_REASONS.name(), reasons.getAnother().asJson());
            return json;
        }

        private ReasonsVisitModel getReasonsVisit(JSONObject json) throws JSONException{
            String result = json.getString(Constants.Visit.RESULT.name());
            ActiveSearch search = new ActiveSearch();
            Following following = new Following();
            AnotherReasons another = new AnotherReasons();

            JSONObject data = json.getJSONObject(Constants.Visit.ACTIVE_SEARCH.name());
            search.setAppointment(data.getInt(Constants.Visit.APPOINTMENT.name()) == 1);
            search.setConditions(data.getInt(Constants.Visit.CONDITIONS.name()) == 1);
            search.setExam(data.getInt(Constants.Visit.EXAM.name()) == 1);
            search.setVaccine(data.getInt(Constants.Visit.VACCINE.name()) == 1);

            data = json.getJSONObject(Constants.Visit.FOLLOWING.name());
            following.setPregnant(convertIntToBoolean(data, Constants.Visit.PREGNANT.name()));
            following.setPuerpera(convertIntToBoolean(data, Constants.Visit.PUERPERA.name()));
            following.setNewborn(convertIntToBoolean(data, Constants.Visit.NEW_BORN.name()));
            following.setChild(convertIntToBoolean(data, Constants.Visit.CHILD.name()));
            following.setMalnutrition(convertIntToBoolean(data, Constants.Visit.MALNUTRITION.name()));
            following.setRehabilitationDeficiency(convertIntToBoolean(data, Constants.Visit.REHABILITATION.name()));
            following.setHypertension(convertIntToBoolean(data, Constants.Visit.HYPERTENSION.name()));
            following.setDiabetes(convertIntToBoolean(data, Constants.Visit.DIABETES.name()));
            following.setAsthma(convertIntToBoolean(data, Constants.Visit.ASTHMA.name()));
            following.setCopdEmphysema(convertIntToBoolean(data, Constants.Visit.COPD_EMPHYSEMA.name()));
            following.setCancer(convertIntToBoolean(data, Constants.Visit.CANCER.name()));
            following.setChronicDiseases(convertIntToBoolean(data, Constants.Visit.CHRONIC_DISEASE.name()));
            following.setLeprosy(convertIntToBoolean(data, Constants.Visit.LEPROSY.name()));
            following.setTuberculosis(convertIntToBoolean(data, Constants.Visit.TUBERCULOSIS.name()));
            following.setRespiratory(convertIntToBoolean(data, Constants.Visit.RESPIRATORY.name()));
            following.setSmoker(convertIntToBoolean(data, Constants.Visit.SMOKER.name()));
            following.setHomeBedding(convertIntToBoolean(data, Constants.Visit.HOME_BEDDING.name()));
            following.setVulnerability(convertIntToBoolean(data, Constants.Visit.VULNERABILITY.name()));
            following.setBolsaFamília(convertIntToBoolean(data, Constants.Visit.BOLSA_FAMILIA.name()));
            following.setMentalHealth(convertIntToBoolean(data, Constants.Visit.MENTAL_HEALTH.name()));
            following.setAlcohol(convertIntToBoolean(data, Constants.Visit.ALCOHOL.name()));
            following.setDrugs(convertIntToBoolean(data, Constants.Visit.DRUGS.name()));

            data = json.getJSONObject(Constants.Visit.ANOTHER_REASONS.name());
            another.setRecordUpdate(convertIntToBoolean(data, Constants.Visit.RECORD_UPDATE.name()));
            another.setPeriodicVisit(convertIntToBoolean(data, Constants.Visit.PERIODIC_VISIT.name()));
            another.setInternment(convertIntToBoolean(data, Constants.Visit.INTERMENT.name()));
            another.setControlEnvironments(convertIntToBoolean(data, Constants.Visit.CONTROL_ENVIRONMENTS.name()));
            another.setCollectiveActivities(convertIntToBoolean(data, Constants.Visit.COLLECTIVE_ACTIVITIES.name()));
            another.setGuidance(convertIntToBoolean(data, Constants.Visit.GUIDANCE.name()));
            another.setOthers(convertIntToBoolean(data, Constants.Visit.OTHERS.name()));

            return new ReasonsVisitModel(search, following, another, result);
        }

        private JSONObject getRecordVisit(RecordVisitModel details) throws JSONException {
            JSONObject json = new JSONObject();
            json.put(Constants.Visit.IS_SHARED.name(), details.isShared());
            json.put(Constants.Visit.RECORD_DETAILS.name(), details.getDetails().asJson());
            return json;
        }

        private RecordVisitModel getRecordVisit(JSONObject json) throws JSONException{
            boolean shared = convertIntToBoolean(json, Constants.Visit.IS_SHARED.name());

            RecordDetails details = new RecordDetails();
            JSONObject data = json.getJSONObject(Constants.Visit.RECORD_DETAILS.name());
            long numSus = data.getLong(Constants.Visit.NUM_SUS.name());

            details.setRecord(data.getLong(Constants.Visit.RECORD.name()));
            details.setPlaceCare(data.getString(Constants.Accompany.PLACE_CARE.name()));
            details.setTypeCare(data.getString(Constants.Accompany.TYPE_CARE.name()));
            details.setShift(data.getString(Constants.Accompany.SHIFT.name()));
            details.setCitizen(CitizenPersistence.get(numSus));
            return new RecordVisitModel(details, shared);
        }

        private boolean convertIntToBoolean(JSONObject json, String tag) throws JSONException {
            return json.getInt(tag) == 1;
        }
    }
}
