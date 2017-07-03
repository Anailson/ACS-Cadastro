package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;
import java.util.Formatter;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;

public class ResidenceModel extends RealmObject implements Serializable, ISearcher {

    public static final String CEP = "cep";
    public static final String STREET_NAME = "streetName";

    @PrimaryKey
    private long cep;
    private String streetName;
    private AddressDataModel addressData;
    private HousingConditionsModel housingConditions;
    private RealmList<HousingHistoricalModel> housingHistorical;


    public static ResidenceModel newInstance(Realm realm, AddressDataModel addressData,
                     HousingConditionsModel housingConditions, RealmList<HousingHistoricalModel> housingHistorical){
        realm.beginTransaction();
        ResidenceModel object = realm.createObject(ResidenceModel.class, addressData.getCep());
        object.setStreetName(addressData.getPlaceName());
        object.setAddressData(addressData);
        object.setHousingConditions(housingConditions);
        object.setHousingHistorical(housingHistorical);
        realm.commitTransaction();
        return object;
    }

    public long getCep() {
        return cep;
    }

    public void setCep(long cep) {
        this.cep = cep;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public AddressDataModel getAddressData() {
        return addressData;
    }

    public void setAddressData(AddressDataModel addressData) {
        this.addressData = addressData;
    }

    public HousingConditionsModel getHousingConditions() {
        return housingConditions;
    }

    public void setHousingConditions(HousingConditionsModel housingConditions) {
        this.housingConditions = housingConditions;
    }

    public RealmList<HousingHistoricalModel> getHousingHistorical() {
        return housingHistorical;
    }

    public void setHousingHistorical(RealmList<HousingHistoricalModel> housingHistorical) {
        this.housingHistorical = housingHistorical;
    }

    public boolean addressContainsKey(String key){
        return containsKey(getAddressData().getPlaceName(), key);
    }

    public boolean cepContainsKey(int key) {
        return getCep() > AcsRecordPersistence.DEFAULT_INT && containsKey(String.valueOf(getCep()), String.valueOf(key));
    }

    private boolean containsKey(String value, String key){
        return value.toUpperCase().contains(key.toUpperCase().trim());
    }

    public String getSimpleAddress(){
        Formatter out = new Formatter();
        out.format("%s %s, %d.", getAddressData().getPlaceType(), getStreetName(), getAddressData().getNumber());
        return out.toString();
    }

    public String getCompleteAddress(){
        String type = getAddressData().getPlaceType().contains("-") ? "" : getAddressData().getPlaceType();
        Formatter out = new Formatter();
        return out.format("%s %s, %d. %s %s-%s. CEP: %d", type, getStreetName(),
                getAddressData().getNumber(), getAddressData().getNeighborhood(), getAddressData().getCityName(),
                getAddressData().getUf(), getCep()).toString();
    }

    public String getHomePhone() {
        return getAddressData().getPhoneHome();
    }

    public String getReferencePhone() {
        return getAddressData().getPhoneReference();
    }
}
