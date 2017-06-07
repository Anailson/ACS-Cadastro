package tcc.acs_cadastro_mobile.models;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmObject;

public class CitizenModel extends RealmObject{

    public static final String STRING_DEFAULT_VALUE = "No value";
    public static final String NAME = "name";
    public static final String NUM_SUS = "numSus";

    private PersonalDataModel personalData;
    private SocialDemographicModel socialDemographicData;
    private HealthConditionsModel healthConditions;
    private StreetSituationModel streetSituation;

    public static CitizenModel newInstance(Realm realm, PersonalDataModel personalData,
                                           SocialDemographicModel socialDemographic, HealthConditionsModel healthConditions,
                                           StreetSituationModel streetSituation) {
        realm.beginTransaction();
        CitizenModel object = realm.createObject(CitizenModel.class);
        object.setPersonalData(personalData);
        object.setSocialDemographicData(socialDemographic);
        object.setHealthConditions(healthConditions);
        object.setStreetSituation(streetSituation);
        realm.commitTransaction();
        return object;
    }

    public PersonalDataModel getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataModel personalData) {
        this.personalData = personalData;
    }

    public SocialDemographicModel getSocialDemographicData() {return socialDemographicData;}

    public void setSocialDemographicData(SocialDemographicModel socialDemographicData) {
        this.socialDemographicData = socialDemographicData;
    }

    public HealthConditionsModel getHealthConditions() {return healthConditions;}

    public void setHealthConditions(HealthConditionsModel healthConditions) {
        this.healthConditions = healthConditions;
    }

    public StreetSituationModel getStreetSituation() {return streetSituation;}

    public void setStreetSituation(StreetSituationModel streetSituation) {
        this.streetSituation = streetSituation;
    }

    public boolean nameContainsKey(String key){return containsKey(personalData.getName(), key);}

    public boolean susContainsKey(String key){return containsKey(String.valueOf(personalData.getNumSus()), key);}

    private boolean containsKey(String value, String key){return value.toUpperCase().contains(key.toUpperCase().trim());}

    public String getName() {return personalData.getName();}

    public String getAddress(){
        return "Rua " + getName() + ", " + new Random().nextInt(1000);
    }

    public long getNumSus(){
        return personalData.getNumSus();
    }

    public long getPhone(){
        return personalData.getNumSus();
    }

    public String getBirthDate(){
        return personalData.getBirth();
    }

    @Override
    public String toString() {
        return "Nome: " + getName() + ", SUS: " + getNumSus();
    }
}
