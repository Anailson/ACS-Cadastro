package tcc.acs_cadastro_mobile.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tcc.acs_cadastro_mobile.persistence.CitizenPersistence;

public class CitizenModel extends RealmObject {

    public static final String STRING_DEFAULT_VALUE = "No value";
    public static final String NAME = "name";
    public static final String NUM_SUS = "numSus";

    @PrimaryKey
    private long numSus;
    private String name;
    private PersonalDataModel personalData;
    private SocialDemographicModel socialDemographicData;
    private HealthConditionsModel healthConditions;
    private StreetSituationModel streetSituation;


    public static CitizenModel newInstance(Realm realm, PersonalDataModel personalData,
                                           SocialDemographicModel socialDemographic,
                                           HealthConditionsModel healthConditions,
                                           StreetSituationModel streetSituation){

        long numSus = CitizenPersistence.getMinorNumSus(personalData.getNumSus());
        realm.beginTransaction();
        CitizenModel object = realm.createObject(CitizenModel.class, numSus);
        object.setName(personalData.getName());
        object.setPersonalData(personalData);
        object.setSocialDemographicData(socialDemographic);
        object.setHealthConditions(healthConditions);
        object.setStreetSituation(streetSituation);
        realm.commitTransaction();
        return object;
    }

    public long getNumSus() {
        return numSus <= 0 ? 0 : numSus;
    }

    public void setNumSus(long numSus) {
        this.numSus = numSus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonalDataModel getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataModel personalData) {
        this.personalData = personalData;
    }

    public SocialDemographicModel getSocialDemographicData() {
        return socialDemographicData;
    }

    public void setSocialDemographicData(SocialDemographicModel socialDemographicData) {
        this.socialDemographicData = socialDemographicData;
    }

    public HealthConditionsModel getHealthConditions() {
        return healthConditions;
    }

    public void setHealthConditions(HealthConditionsModel healthConditions) {
        this.healthConditions = healthConditions;
    }

    public StreetSituationModel getStreetSituation() {
        return streetSituation;
    }

    public void setStreetSituation(StreetSituationModel streetSituation) {
        this.streetSituation = streetSituation;
    }

    public boolean nameContainsKey(String key) {
        return containsKey(personalData.getName(), key);
    }

    public boolean susContainsKey(String key) {
        return containsKey(String.valueOf(personalData.getNumSus()), key);
    }

    private boolean containsKey(String value, String key) {
        return value.toUpperCase().contains(key.toUpperCase().trim());
    }

    public long getPhone() {
        return getNumSus();
    }

    public String getBirthDate() {
        return personalData.getBirth();
    }

    @Override
    public String toString() {
        return "Nome: " + getName() + ", SUS: " + getNumSus();
    }
}
