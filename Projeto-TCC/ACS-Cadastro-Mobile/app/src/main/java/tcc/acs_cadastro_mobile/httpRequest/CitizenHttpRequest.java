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
import tcc.acs_cadastro_mobile.alerts.DefaultAlert;
import tcc.acs_cadastro_mobile.interfaces.IHttpResponse;
import tcc.acs_cadastro_mobile.interfaces.IJsonParser;
import tcc.acs_cadastro_mobile.models.AgentModel;
import tcc.acs_cadastro_mobile.models.CitizenModel;
import tcc.acs_cadastro_mobile.models.HealthConditionsModel;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;
import tcc.acs_cadastro_mobile.models.SocialDemographicModel;
import tcc.acs_cadastro_mobile.models.StreetSituationModel;
import tcc.acs_cadastro_mobile.subModels.AnotherInstitution;
import tcc.acs_cadastro_mobile.subModels.CommunityTraditional;
import tcc.acs_cadastro_mobile.subModels.Contact;
import tcc.acs_cadastro_mobile.subModels.Deficiency;
import tcc.acs_cadastro_mobile.subModels.Diseases;
import tcc.acs_cadastro_mobile.subModels.EducationEmployment;
import tcc.acs_cadastro_mobile.subModels.FamilyVisit;
import tcc.acs_cadastro_mobile.subModels.Feeding;
import tcc.acs_cadastro_mobile.subModels.GenderAndRace;
import tcc.acs_cadastro_mobile.subModels.HealthAndGroup;
import tcc.acs_cadastro_mobile.subModels.HeartDisease;
import tcc.acs_cadastro_mobile.subModels.Hygiene;
import tcc.acs_cadastro_mobile.subModels.Interment;
import tcc.acs_cadastro_mobile.subModels.KidneyDisease;
import tcc.acs_cadastro_mobile.subModels.Mother;
import tcc.acs_cadastro_mobile.subModels.Nationality;
import tcc.acs_cadastro_mobile.subModels.ParticularData;
import tcc.acs_cadastro_mobile.subModels.Plant;
import tcc.acs_cadastro_mobile.subModels.Pregnant;
import tcc.acs_cadastro_mobile.subModels.RespiratoryDisease;
import tcc.acs_cadastro_mobile.subModels.Responsible;
import tcc.acs_cadastro_mobile.subModels.SexualOrientation;
import tcc.acs_cadastro_mobile.subModels.StreetSituation;

import static tcc.acs_cadastro_mobile.Constants.Citizen.ANOTHER_INSTITUTION;
import static tcc.acs_cadastro_mobile.Constants.Citizen.BENEFIT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.COMMUNITY_TRADITIONAL;
import static tcc.acs_cadastro_mobile.Constants.Citizen.CONTACT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.DEFICIENCY;
import static tcc.acs_cadastro_mobile.Constants.Citizen.DISEASES;
import static tcc.acs_cadastro_mobile.Constants.Citizen.EDUCATION_EMPLOYMENT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.FAMILY;
import static tcc.acs_cadastro_mobile.Constants.Citizen.FAMILY_VISIT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.FEEDING;
import static tcc.acs_cadastro_mobile.Constants.Citizen.GENDER_RACE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.HEALTH_CONDITIONS;
import static tcc.acs_cadastro_mobile.Constants.Citizen.HEALTH_GROUP;
import static tcc.acs_cadastro_mobile.Constants.Citizen.HEART_DISEASE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.HYGIENE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.INTERMENT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.KIDNEY_DISEASE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.KIDS_09;
import static tcc.acs_cadastro_mobile.Constants.Citizen.KINSHIP;
import static tcc.acs_cadastro_mobile.Constants.Citizen.MOTHER;
import static tcc.acs_cadastro_mobile.Constants.Citizen.NATIONALITY;
import static tcc.acs_cadastro_mobile.Constants.Citizen.PARTICULAR;
import static tcc.acs_cadastro_mobile.Constants.Citizen.PERSONAL_DATA;
import static tcc.acs_cadastro_mobile.Constants.Citizen.PLANT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.PREGNANT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.RESPIRATORY_DISEASE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.RESPONSIBLE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.SEXUAL_ORIENTATION;
import static tcc.acs_cadastro_mobile.Constants.Citizen.SOCIAL_DEMOGRAPHIC;
import static tcc.acs_cadastro_mobile.Constants.Citizen.STREET;
import static tcc.acs_cadastro_mobile.Constants.Citizen.STREET_SITUATION;
import static tcc.acs_cadastro_mobile.Constants.Citizen.WEIGHT;

public class CitizenHttpRequest implements WebServiceConnection.ConnectionResult{

    private static final String PATH = "CitizenWebService.php";
    private Context context;
    private IHttpResponse.Responses<CitizenModel> response;
    private CitizenJsonParser parser;
    private WebServiceConnection connection;

    public CitizenHttpRequest(Context context) {
        this.context = context;
        this.response = new IHttpResponse.Responses<>();
        this.parser = new CitizenJsonParser();
        this.connection = new WebServiceConnection(context, this);
    }

    public void start() {
        connection.start();
    }

    public void get(long value, IHttpResponse.Get<CitizenModel> get) {
        response.get = get;
        connection.setProgressDialog(context.getString(R.string.msg_get_data_title), context.getString(R.string.msg_get_citizen_data_message));
        connection.get(PATH, value);
    }

    public void getAll(long value, IHttpResponse.GetAll<CitizenModel> getAll) {
        response.getAll = getAll;
        connection.setProgressDialog(context.getString(R.string.msg_get_data_title), context.getString(R.string.msg_get_citizen_data_message));
        connection.getAll(PATH, value);
    }

    public void insert(CitizenModel citizen, IHttpResponse.Insert insert) {
        response.insert = insert;
        connection.setProgressDialog(context.getString(R.string.msg_send_citizen_data_title), context.getString(R.string.msg_send_citizen_data_message));
        connection.post(PATH, parser.parserFrom(citizen));
    }

    public void insertAll(AgentModel agent, List<CitizenModel> list, IHttpResponse.InsertAll insertAll) {
        response.insertAll = insertAll;
        JSONArray array = addJsonOnFirst(agent.asJson(), parser.parserFrom(list));
        connection.setProgressDialog(context.getString(R.string.msg_send_citizen_data_title), context.getString(R.string.msg_send_citizen_data_message));
        connection.postAll(PATH, array);
    }

    private int getId(JSONObject json) {
        try {
            return json.getInt("id");
        } catch (JSONException e) {
            return -1;
        }
    }

    private int[] getIndexes(JSONArray array) {
        int[] indexes = new int[array.length()];
        for (int i = 0; i < array.length(); i++) {
            try {
                indexes[i] = array.getJSONObject(i).getInt("index");
            } catch (JSONException e) {
                e.getMessage();
            }
        }
        return indexes;
    }

    private JSONArray addJsonOnFirst(JSONObject json, JSONArray array){
        JSONArray newArray = new JSONArray();
        newArray.put(json);
        for(int i = 0; i < array.length(); i++){
            newArray.put(array.optJSONObject(i));
        }
        return newArray;
    }

    public void onSuccess (WebServiceConnection.Request request){
        switch (request.getMethod()) {
            case GET: response.get(parser.parserFrom(request.getObject())); break;
            case GET_ALL: response.getAll(parser.parserFrom(request.getArray())); break;
            case POST: response.insert(getId(request.getObject())); break;
            case POST_ALL: response.insertAll(getIndexes(request.getArray()));
        }
    }

    @Override
    public void onGeneralError (WebServiceConnection.Request request){
        new DefaultAlert(context).setMessage("Ocorreu um erro ao atualizar os dados").show();
    }

    @Override
    public void onConnectionError (WebServiceConnection.Request request){
    }

    @Override
    public void onInvalidValueError (WebServiceConnection.Request request){
    }


    private static class CitizenJsonParser implements IJsonParser<CitizenModel> {

        @Override
        public JSONObject parserFrom(CitizenModel citizen) {

            JSONObject json = new JSONObject();
            try {
                json.put(PERSONAL_DATA.name(), getPersonalData(citizen.getPersonalData()));
                json.put(SOCIAL_DEMOGRAPHIC.name(), getSocialDemographic(citizen.getSocialDemographicData()));
                json.put(HEALTH_CONDITIONS.name(), getHealthConditions(citizen.getHealthConditions()));
                json.put(STREET_SITUATION.name(), getStreetSituation(citizen.getStreetSituation()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        public CitizenModel parserFrom(JSONObject json) {

            try {
                PersonalDataModel personalDataModel = getPersonalData(json.getJSONObject(Constants.Citizen.PERSONAL_DATA.name()));
                SocialDemographicModel socialDemographicModel = getSocialDemographic(json.getJSONObject(Constants.Citizen.SOCIAL_DEMOGRAPHIC.name()));
                HealthConditionsModel healthConditionsModel = getHealthConditions(json.getJSONObject(Constants.Citizen.HEALTH_CONDITIONS.name()));
                StreetSituationModel streetSituation = getStreetSituation(json.getJSONObject(Constants.Citizen.STREET_SITUATION.name()));

                return new CitizenModel(personalDataModel, socialDemographicModel, healthConditionsModel, streetSituation);

            } catch (JSONException e) {
                Log.e("JSONException", json.toString());
                Log.e(CitizenModel.class.getName() + ".parserFrom", e.getMessage());
            }

            return new CitizenModel();
        }

        @Override
        public JSONArray parserFrom(List<CitizenModel> list) {
            JSONArray array = new JSONArray();
            for (CitizenModel citizen : list) {
                array.put(parserFrom(citizen));
            }
            return array;
        }

        @Override
        public List<CitizenModel> parserFrom(JSONArray jsonArray) {

            List<CitizenModel> citizens = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    citizens.add(parserFrom(jsonArray.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return citizens;
        }

        private JSONObject getPersonalData(PersonalDataModel personal) throws JSONException {
            JSONObject json = new JSONObject();
            json.put(PARTICULAR.name(), personal.getParticularData().asJson());
            json.put(MOTHER.name(), personal.getMother().asJson());
            json.put(RESPONSIBLE.name(), personal.getResponsible().asJson());
            json.put(GENDER_RACE.name(), personal.getGenderNRace().asJson());
            json.put(NATIONALITY.name(), personal.getNationality().asJson());
            json.put(CONTACT.name(), personal.getContact().asJson());
            return json;
        }

        private PersonalDataModel getPersonalData(JSONObject json) {

            JSONObject data;
            ParticularData particular = new ParticularData();
            Mother mother = new Mother();
            Responsible responsible = new Responsible();
            GenderAndRace genderAndRace = new GenderAndRace();
            Nationality nationality = new Nationality();
            Contact contact = new Contact();

            try {
                data = json.getJSONObject(Constants.Citizen.PARTICULAR.name());
                particular.setName(data.getString(Constants.Citizen.NAME.name()));
                particular.setSocialName(data.getString(Constants.Citizen.SOCIAL_NAME.name()));
                particular.setNumSus(data.getLong(Constants.Citizen.NUM_SUS.name()));
                particular.setNumNis(data.getLong(Constants.Citizen.NUM_NIS.name()));
                particular.setBirthDate(data.getString(Constants.Citizen.BIRTH_DATE.name()));

                data = json.getJSONObject(Constants.Citizen.MOTHER.name());
                mother.setName(data.getString(Constants.Citizen.NAME.name()));
                mother.setKnown(data.getInt(Constants.Citizen.KNOWN.name()) == 1);

                data = json.getJSONObject(Constants.Citizen.RESPONSIBLE.name());
                responsible.setResponsible(data.getInt(Constants.Citizen.RESPONSIBLE.name()) == 1);
                responsible.setNumSus(data.getLong(Constants.Citizen.NUM_SUS.name()));
                responsible.setBirthDate(data.getString(Constants.Citizen.BIRTH_DATE.name()));

                data = json.getJSONObject(Constants.Citizen.GENDER_RACE.name());
                genderAndRace.setRace(data.getString(Constants.Citizen.RACE.name()));
                genderAndRace.setGender(data.getString(Constants.Citizen.GENDER.name()));

                data = json.getJSONObject(Constants.Citizen.NATIONALITY.name());
                nationality.setCity(data.getString(Constants.Citizen.CITY.name()));
                nationality.setNationality(data.getString(Constants.Citizen.NATIONALITY.name()));
                nationality.setUf(data.getString(Constants.Citizen.UF.name()));
                nationality.setNationBirth(data.getString(Constants.Citizen.NATION_BIRTH.name()));

                data = json.getJSONObject(Constants.Citizen.CONTACT.name());
                contact.setEmail(data.getString(Constants.Citizen.EMAIL.name()));
                contact.setPhone(data.getString(Constants.Citizen.PHONE.name()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new PersonalDataModel(particular, mother, responsible, genderAndRace, nationality, contact);
        }

        private JSONObject getSocialDemographic(SocialDemographicModel socialDemographic) {

            JSONObject json = new JSONObject();
            try {
                json.put(KINSHIP.name(), socialDemographic.getKinship());
                json.put(KIDS_09.name(), socialDemographic.getKids09());
                json.put(EDUCATION_EMPLOYMENT.name(), socialDemographic.getEducationEmployment().asJson());
                json.put(HEALTH_GROUP.name(), socialDemographic.getHealthAndGroup().asJson());
                json.put(COMMUNITY_TRADITIONAL.name(), socialDemographic.getCommunityTraditional().asJson());
                json.put(SEXUAL_ORIENTATION.name(), socialDemographic.getSexualOrientation().asJson());
                json.put(DEFICIENCY.name(), socialDemographic.getDeficiency().asJson());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json;
        }

        private SocialDemographicModel getSocialDemographic(JSONObject json){
            String kinship = "";
            String kids09 = "";
            EducationEmployment educationEmployment = new EducationEmployment();
            HealthAndGroup healthAndGroup = new HealthAndGroup();
            CommunityTraditional communityTraditional = new CommunityTraditional();
            SexualOrientation sexualOrientation = new SexualOrientation();
            Deficiency deficiency = new Deficiency();

            try {
                kinship = json.getString(Constants.Citizen.KINSHIP.name());
                kids09 = json.getString(Constants.Citizen.KIDS_09.name());

                JSONObject data = json.getJSONObject(Constants.Citizen.EDUCATION_EMPLOYMENT.name());
                educationEmployment.setEmployment(data.getString(Constants.Citizen.EMPLOYMENT.name()));
                educationEmployment.setEducation(data.getString(Constants.Citizen.EDUCATION.name()));
                educationEmployment.setSchool(data.getInt(Constants.Citizen.SCHOOL.name()) == 1);
                educationEmployment.setOccupation(data.getString(Constants.Citizen.OCCUPATION.name()));

                data = json.getJSONObject(Constants.Citizen.HEALTH_GROUP.name());
                healthAndGroup.setHealthPlan(data.getInt(Constants.Citizen.HEALTH_PLAN.name()) == 1);
                healthAndGroup.setCommunityGroup(data.getInt(Constants.Citizen.COMMUNITY_GROUP.name()) == 1);
                healthAndGroup.setCaregiver(data.getInt(Constants.Citizen.CAREGIVER.name()) == 1);


                data = json.getJSONObject(Constants.Citizen.COMMUNITY_TRADITIONAL.name());
                communityTraditional.setValue(data.getString(Constants.Citizen.DESCRIPTION.name()));
                communityTraditional.setCommunityTraditional(data.getInt(Constants.Citizen.COMMUNITY_TRADITIONAL.name()) == 1);

                data = json.getJSONObject(Constants.Citizen.SEXUAL_ORIENTATION.name());
                sexualOrientation.setSexualOrientation(data.getInt(Constants.Citizen.SEXUAL_ORIENTATION.name()) == 1);
                sexualOrientation.setValue(data.getString(Constants.Citizen.DESCRIPTION.name()));

                data = json.getJSONObject(Constants.Citizen.DEFICIENCY.name());
                deficiency.setDeficiency(data.getInt(Constants.Citizen.DEFICIENCY.name()) == 1);
                deficiency.setAnother(data.getInt(Constants.Citizen.ANOTHER.name()) == 1);
                deficiency.setHearing(data.getInt(Constants.Citizen.HEARING.name()) == 1);
                deficiency.setIntellectual(data.getInt(Constants.Citizen.INTELLECTUAL.name()) == 1);
                deficiency.setVisual(data.getInt(Constants.Citizen.VISUAL.name()) == 1);
                deficiency.setPhisical(data.getInt(Constants.Citizen.PHYSICAL.name()) == 1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new SocialDemographicModel(kinship, educationEmployment, healthAndGroup, kids09, communityTraditional, sexualOrientation, deficiency);
        }

        private JSONObject getHealthConditions(HealthConditionsModel healthConditions){

            JSONObject json = new JSONObject();
            try {
                json.put(PREGNANT.name(), healthConditions.getPregnant().asJson());
                json.put(WEIGHT.name(), healthConditions.getWeight());
                json.put(DISEASES.name(), healthConditions.getDiseases().getAsJson());
                json.put(HEART_DISEASE.name(), healthConditions.getHeartDisease().getAsJson());
                json.put(KIDNEY_DISEASE.name(), healthConditions.getKidneyDisease().getAsJson());
                json.put(RESPIRATORY_DISEASE.name(), healthConditions.getRespiratoryDisease().getAsJson());
                json.put(INTERMENT.name(), healthConditions.getInterment().getAsJson());
                json.put(PLANT.name(), healthConditions.getPlant().getAsJson());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json;
        }

        private HealthConditionsModel getHealthConditions(JSONObject json){

            String weight = "";
            Pregnant pregnant = new Pregnant();
            Diseases diseases = new Diseases();
            HeartDisease heartDisease = new HeartDisease();
            KidneyDisease kidneyDisease = new KidneyDisease();
            RespiratoryDisease respiratoryDisease = new RespiratoryDisease();
            Interment interment = new Interment();
            Plant plant = new Plant();

            try {

                weight = json.getString(Constants.Citizen.WEIGHT.name());

                JSONObject data = json.getJSONObject(Constants.Citizen.PREGNANT.name());
                pregnant.setMaternity(data.getString(Constants.Citizen.DESCRIPTION.name()));
                pregnant.setPregnant(data.getInt(Constants.Citizen.PREGNANT.name()) == 1);

                data = json.getJSONObject(Constants.Citizen.DISEASES.name());
                diseases.setSmoker(data.getInt(Constants.Citizen.SMOKER.name()) == 1);
                diseases.setAlcohol(data.getInt(Constants.Citizen.ALCOHOL.name()) == 1);
                diseases.setDrugs(data.getInt(Constants.Citizen.DRUGS.name()) == 1);
                diseases.setHypertension(data.getInt(Constants.Citizen.HYPERTENSION.name()) == 1);
                diseases.setDiabetes(data.getInt(Constants.Citizen.DIABETES.name()) == 1);
                diseases.setAvc(data.getInt(Constants.Citizen.AVC.name()) == 1);
                diseases.setHeartAttack(data.getInt(Constants.Citizen.HEART_ATTACK.name()) == 1);
                diseases.setLeprosy(data.getInt(Constants.Citizen.LEPROSY.name()) == 1);
                diseases.setTuberculosis(data.getInt(Constants.Citizen.TUBERCULOSIS.name()) == 1);
                diseases.setCancer(data.getInt(Constants.Citizen.CANCER.name()) == 1);
                diseases.setInBend(data.getInt(Constants.Citizen.IN_BED.name()) == 1);
                diseases.setDomiciled(data.getInt(Constants.Citizen.DOMICILED.name()) == 1);
                diseases.setOtherPractices(data.getInt(Constants.Citizen.OTHER_PRACTICES.name()) == 1);
                diseases.setMentalHealth(data.getInt(Constants.Citizen.MENTAL_HEALTH.name()) == 1);

                data = json.getJSONObject(Constants.Citizen.HEART_DISEASE.name());
                heartDisease.setHeartDisease(data.getInt(Constants.Citizen.HEART_DISEASE.name()) == 1);
                heartDisease.setAnother(data.getInt(Constants.Citizen.ANOTHER.name()) == 1);
                heartDisease.setDontKnow(data.getInt(Constants.Citizen.DONT_KNOWN.name()) == 1);
                heartDisease.setInsuficiency(data.getInt(Constants.Citizen.INSUFFICIENCY.name()) == 1);

                data = json.getJSONObject(Constants.Citizen.KIDNEY_DISEASE.name());
                kidneyDisease.setInsuficiency(data.getInt(Constants.Citizen.INSUFFICIENCY.name()) == 1);
                kidneyDisease.setDontKnow(data.getInt(Constants.Citizen.DONT_KNOWN.name()) == 1);
                kidneyDisease.setAnother(data.getInt(Constants.Citizen.ANOTHER.name()) == 1);
                kidneyDisease.setKidneyDisease(data.getInt(Constants.Citizen.KIDNEY_DISEASE.name()) == 1);

                data = json.getJSONObject(Constants.Citizen.RESPIRATORY_DISEASE.name());
                respiratoryDisease.setEmphysema(data.getInt(Constants.Citizen.EMPHYSEMA.name()) == 1);
                respiratoryDisease.setAsthma(data.getInt(Constants.Citizen.ASTHMA.name()) == 1);
                respiratoryDisease.setRespiratoryDisease(data.getInt(Constants.Citizen.RESPIRATORY_DISEASE.name()) == 1);
                respiratoryDisease.setDontKnow(data.getInt(Constants.Citizen.DONT_KNOWN.name()) == 1);
                respiratoryDisease.setAnother(data.getInt(Constants.Citizen.ANOTHER.name()) == 1);

                data = json.getJSONObject(Constants.Citizen.INTERMENT.name());
                interment.setValue(data.getString(Constants.Citizen.DESCRIPTION.name()));
                interment.setInterment(data.getInt(Constants.Citizen.INTERMENT.name()) == 1);

                data = json.getJSONObject(Constants.Citizen.PLANT.name());
                plant.setValue(data.getString(Constants.Citizen.DESCRIPTION.name()));
                plant.setPlants(data.getInt(Constants.Citizen.PLANT.name()) == 1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new HealthConditionsModel(pregnant, weight, diseases, heartDisease, kidneyDisease, respiratoryDisease, interment, plant);
        }

        private JSONObject getStreetSituation(StreetSituationModel streetSituation){

            JSONObject json = new JSONObject();
            try {
                json.put(STREET.name(), streetSituation.getStreetSituation().asJson());
                json.put(BENEFIT.name(), streetSituation.isBenefit());
                json.put(FAMILY.name(), streetSituation.isFamily());
                json.put(FEEDING.name(), streetSituation.getFeeding().asJson());
                json.put(ANOTHER_INSTITUTION.name(), streetSituation.getAnotherInstitution().asJson());
                json.put(FAMILY_VISIT.name(), streetSituation.getFamilyVisit().asJson());
                json.put(HYGIENE.name(), streetSituation.getHygiene().asJson());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return json;
        }

        private StreetSituationModel getStreetSituation(JSONObject json){

            JSONObject data ;

            boolean benefit = false, family = false;
            StreetSituation streetSituation = new StreetSituation();
            Feeding feeding = new Feeding();
            AnotherInstitution anotherInstitution = new AnotherInstitution();
            FamilyVisit familyVisit = new FamilyVisit();
            Hygiene hygiene = new Hygiene();

            try {

                benefit = json.getInt(Constants.Citizen.BENEFIT.name()) == 1;
                family = json.getInt(Constants.Citizen.FAMILY.name()) == 1;

                data = json.getJSONObject(Constants.Citizen.STREET.name());
                streetSituation.setStreetSituation(data.getInt(Constants.Citizen.STREET.name()) == 1);
                streetSituation.setValue(data.getString(Constants.Citizen.DESCRIPTION.name()));

                data = json.getJSONObject(Constants.Citizen.FEEDING.name());
                feeding.setRestaurant(data.getInt(Constants.Citizen.RESTAURANT.name()) == 1);
                feeding.setRestaurantDonation(data.getInt(Constants.Citizen.RESTAURANT_DONATION.name()) == 1);
                feeding.setReligiousGroup(data.getInt(Constants.Citizen.RELIGIOUS_GROUP.name()) == 1);
                feeding.setPopular(data.getInt(Constants.Citizen.POPULAR.name()) == 1);
                feeding.setAnother(data.getInt(Constants.Citizen.ANOTHER.name()) == 1);
                feeding.setFoodPerDay(data.getString(Constants.Citizen.FOOD_PER_DAY.name()));

                data = json.getJSONObject(Constants.Citizen.ANOTHER_INSTITUTION.name());
                anotherInstitution.setValue(data.getString(Constants.Citizen.DESCRIPTION.name()));
                anotherInstitution.setAnotherInstitution(data.getInt(Constants.Citizen.ANOTHER_INSTITUTION.name()) == 1);

                data = json.getJSONObject(Constants.Citizen.FAMILY_VISIT.name());
                familyVisit.setFamilyVisit(data.getInt(Constants.Citizen.FAMILY_VISIT.name()) == 1);
                familyVisit.setValue(data.getString(Constants.Citizen.DESCRIPTION.name()));

                data = json.getJSONObject(Constants.Citizen.HYGIENE.name());
                hygiene.setBath(data.getInt(Constants.Citizen.BATH.name()) == 1);
                hygiene.setSanitary(data.getInt(Constants.Citizen.SANITARY.name()) == 1);
                hygiene.setOral(data.getInt(Constants.Citizen.ORAL.name()) == 1);
                hygiene.setAnother(data.getInt(Constants.Citizen.ANOTHER.name()) == 1);
                hygiene.setHygiene(data.getInt(Constants.Citizen.HYGIENE.name()) == 1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new StreetSituationModel(streetSituation, benefit, family, feeding, anotherInstitution, familyVisit, hygiene);
        }
    }
}
