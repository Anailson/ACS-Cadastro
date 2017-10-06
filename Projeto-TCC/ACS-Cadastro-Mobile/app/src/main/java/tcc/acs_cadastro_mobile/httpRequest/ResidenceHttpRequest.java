package tcc.acs_cadastro_mobile.httpRequest;

import android.content.Context;

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
import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.models.HousingConditionsModel;
import tcc.acs_cadastro_mobile.models.HousingHistoricalModel;
import tcc.acs_cadastro_mobile.models.ResidenceModel;
import tcc.acs_cadastro_mobile.subModels.CityLocation;
import tcc.acs_cadastro_mobile.subModels.House;
import tcc.acs_cadastro_mobile.subModels.HousingSituation;
import tcc.acs_cadastro_mobile.subModels.Pet;
import tcc.acs_cadastro_mobile.subModels.Phones;
import tcc.acs_cadastro_mobile.subModels.StreetLocation;
import tcc.acs_cadastro_mobile.subModels.WaterAndSanitation;

public class ResidenceHttpRequest implements WebServiceConnection.ConnectionResult{

    private final String PATH = "ResidenceWebService.php";
    private IHttpResponse.Responses<ResidenceModel> response;
    private WebServiceConnection connection;
    private ResidenceJsonParser parser;

    public ResidenceHttpRequest(Context context) {
        response = new IHttpResponse.Responses<>();
        connection = new WebServiceConnection(context, this);
        parser = new ResidenceJsonParser();
    }

    public void start() {
        connection.start();
    }

    public void getAll(long value, IHttpResponse.GetAll<ResidenceModel> getAll) {
        response.getAll = getAll;
        connection.setProgressDialog(R.string.msg_get_data_title, R.string.msg_get_residence_data_message);
        connection.getAll(PATH, value);
    }

    public void insert(ResidenceModel residence, IHttpResponse.Insert insert) {
        response.insert = insert;
        connection.post(PATH, parser.parserFrom(residence));
    }

    @Override
    public void onSuccess(WebServiceConnection.Request request) {
        switch (request.getMethod()){
            case GET_ALL: response.getAll(parser.parserFrom(request.getArray()));
        }
    }

    @Override
    public void onGeneralError(WebServiceConnection.Request request) {}

    @Override
    public void onConnectionError(WebServiceConnection.Request request) {}

    @Override
    public void onInvalidValueError(WebServiceConnection.Request request) {}

    private class ResidenceJsonParser implements IJsonParser<ResidenceModel> {

        @Override
        public JSONObject parserFrom(ResidenceModel residence) {

            JSONObject json = new JSONObject();
            try{
                json.put(Constants.Residence.ADDRESS_DATA.name(), getAddressData(residence.getAddressData()));
                json.put(Constants.Residence.HOUSING_CONDITIONS.name(), getHousingConditions(residence.getHousingConditions()));
            } catch (JSONException e){
                e.printStackTrace();
            }
            return json;
        }

        @Override
        public ResidenceModel parserFrom(JSONObject json) {

            try {
                AddressDataModel addressData = getAdressData(json.getJSONObject(Constants.Residence.ADDRESS_DATA.name()));
                HousingConditionsModel housingConditions = getHousingConditions(json.getJSONObject(Constants.Residence.HOUSING_CONDITIONS.name()));

                return new ResidenceModel(addressData, housingConditions, new RealmList<HousingHistoricalModel>());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResidenceModel();
        }

        @Override
        public List<ResidenceModel> parserFrom(JSONArray array) {
            List<ResidenceModel> list = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                try {
                    list.add(parserFrom(array.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }

        @Override
        public JSONArray parserFrom(List<ResidenceModel> list) {
            JSONArray array = new JSONArray();
            for (ResidenceModel residence : list) {
                array.put(parserFrom(residence));
            }
            return array;
        }

        private JSONObject getAddressData(AddressDataModel addressData){
            JSONObject json = new JSONObject();
            try{
                json.put(Constants.Residence.STREET_LOCATION.name(), addressData.getStreet().asJson());
                json.put(Constants.Residence.CITY_LOCATION.name(), addressData.getCity().asJson());
                json.put(Constants.Residence.PHONES.name(), addressData.getPhones().asJson());
            }catch (JSONException e){
                e.printStackTrace();
            }
            return json;
        }

        private AddressDataModel getAdressData(JSONObject json) throws JSONException {
            StreetLocation street = new StreetLocation();
            CityLocation city = new CityLocation();
            Phones phones = new Phones();

            JSONObject data = json.getJSONObject(Constants.Residence.STREET_LOCATION.name());
            street.setType(data.getString(Constants.Residence.TYPE.name()));
            street.setName(data.getString(Constants.Residence.NAME.name()));
            street.setNumber(data.getInt(Constants.Residence.NUMBER.name()));
            street.setComplement(data.getString(Constants.Residence.COMPLEMENT.name()));

            data = json.getJSONObject(Constants.Residence.CITY_LOCATION.name());
            city.setName(data.getString(Constants.Residence.CITY.name()));
            city.setUf(data.getString(Constants.Residence.UF.name()));
            city.setNeighborhood(data.getString(Constants.Residence.NEIGHBORHOOD.name()));
            city.setCep(data.getInt(Constants.Residence.CEP.name()));

            data = json.getJSONObject(Constants.Residence.PHONES.name());
            phones.setHome(data.getString(Constants.Residence.HOME.name()));
            phones.setReference(data.getString(Constants.Residence.REFERENCE.name()));
            return new AddressDataModel(street, city, phones);
        }

        private JSONObject getHousingConditions(HousingConditionsModel housing) throws JSONException {

            JSONObject json = new JSONObject();
            json.put(Constants.Residence.HOUSING_SITUATION.name(), housing.getHousingSituation().asJson());
            json.put(Constants.Residence.HOUSE.name(), housing.getHouse().asJson());
            json.put(Constants.Residence.ELECTRICITY.name(), housing.isElectricEnergy());
            json.put(Constants.Residence.WATER_SANITATION.name(), housing.getWaterAndSanitation().asJson());
            json.put(Constants.Residence.PET.name(), housing.getPet().asJson());
            return json;
        }

        private HousingConditionsModel getHousingConditions(JSONObject json) throws JSONException {

            boolean electricEnergy = json.getInt(Constants.Residence.ELECTRICITY.name()) == 1;

            JSONObject data = json.getJSONObject(Constants.Residence.HOUSING_SITUATION.name());
            HousingSituation housingSituation = new HousingSituation();
            housingSituation.setLocation(data.getString(Constants.Residence.LOCATION.name()));
            housingSituation.setOwnership(data.getString(Constants.Residence.OWNERSHIP.name()));
            housingSituation.setSituation(data.getString(Constants.Residence.SITUATION.name()));

            House house = new House();
            data = json.getJSONObject(Constants.Residence.HOUSE.name());
            house.setType(data.getString(Constants.Residence.TYPE.name()));
            house.setAccess(data.getString(Constants.Residence.ACCESS.name()));
            house.setnResident(data.getInt(Constants.Residence.RESIDENTS.name()));
            house.setnRoom(data.getInt(Constants.Residence.ROOMS.name()));
            house.setContruction(data.getString(Constants.Residence.CONSTRUCTION.name()));
            house.setContructionType(data.getString(Constants.Residence.CONSTRUCTION_TYPE.name()));

            WaterAndSanitation waterAndSanitation = new WaterAndSanitation();
            data = json.getJSONObject(Constants.Residence.WATER_SANITATION.name());
            waterAndSanitation.setWaterSupply(data.getString(Constants.Residence.WATER_SUPPLY.name()));
            waterAndSanitation.setWaterTreatment(data.getString(Constants.Residence.WATER_TREATMENT.name()));
            waterAndSanitation.setBathroom(data.getString(Constants.Residence.BATHROOM.name()));

            Pet pet = new Pet();
            data = json.getJSONObject(Constants.Residence.PET.name());
            pet.setHasPet(data.getInt(Constants.Residence.HAS_PET.name()) == 1);
            pet.setnPets(data.getInt(Constants.Residence.PETS.name()));
            pet.setDog(data.getInt(Constants.Residence.DOG.name()) == 1);
            pet.setCat(data.getInt(Constants.Residence.CAT.name()) == 1);
            pet.setBird(data.getInt(Constants.Residence.BIRD.name()) == 1);
            pet.setCriation(data.getInt(Constants.Residence.CREATION.name()) == 1);
            pet.setAnother(data.getInt(Constants.Residence.ANOTHER.name()) == 1);

            return new HousingConditionsModel(housingSituation, house, electricEnergy, waterAndSanitation, pet);
        }
    }
}