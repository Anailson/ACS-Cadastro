package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;

public class CitizenModel extends RealmObject implements Serializable, ISearcher {

    public static final String STRING_DEFAULT_VALUE = "No value";
    public static final String NAME = "name";
    public static final String NUM_SUS = "numSus";
    public static final String STATUS = "status";

    @PrimaryKey
    private long numSus;
    private String name;
    private PersonalDataModel personalData;
    private SocialDemographicModel socialDemographicData;
    private HealthConditionsModel healthConditions;
    private StreetSituationModel streetSituation;
    private int status;

    public CitizenModel() {
        this(new PersonalDataModel(), new SocialDemographicModel(), new HealthConditionsModel(),
                new StreetSituationModel());
    }

    public CitizenModel(PersonalDataModel personalData, SocialDemographicModel socialDemographicData,
                        HealthConditionsModel healthConditions, StreetSituationModel streetSituation) {

        this.numSus = personalData.getNumSus();
        this.name = personalData.getName();
        this.personalData = personalData;
        this.socialDemographicData = socialDemographicData;
        this.healthConditions = healthConditions;
        this.streetSituation = streetSituation;
        setStatus(AcsRecordPersistence.INSERT);
    }

    public long getNumSus() {
        return numSus > AcsRecordPersistence.DEFAULT_INT ? numSus : AcsRecordPersistence.DEFAULT_INT;
    }

    public void setNumSus(long numSus) {
        this.numSus = numSus;
    }

    public int getStatus() {
        if (status != AcsRecordPersistence.OK
                && status != AcsRecordPersistence.INSERT
                && status != AcsRecordPersistence.UPDATE) {
            throw new IllegalArgumentException("Status invalid");
        }
        return status;
    }

    public String getStatusValue(){
        switch (getStatus()){
            case AcsRecordPersistence.OK: return AcsRecordPersistence.Status.OK.name();
            case AcsRecordPersistence.INSERT: return AcsRecordPersistence.Status.INSERT.name();
            case AcsRecordPersistence.UPDATE: return AcsRecordPersistence.Status.UPDATE.name();
        }
        return null;
    }

    public void setStatus(int status) {

        if (status != AcsRecordPersistence.INSERT
                && status != AcsRecordPersistence.UPDATE
                && status != AcsRecordPersistence.OK) {
            throw new IllegalArgumentException("Use AcsRecordPersistence constants (INSERT, UPDATE, OK).");
        }
        this.status = status;
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

    public boolean susContainsKey(int key) {
        return personalData.getNumSus() > AcsRecordPersistence.DEFAULT_INT
                && containsKey(String.valueOf(personalData.getNumSus()), String.valueOf(key));
    }

    private boolean containsKey(String value, String key) {
        return value.toUpperCase().contains(key.toUpperCase().trim());
    }

    public String getPhone() {
        return personalData.getPhone();
    }

    public String getBirthDate() {
        return personalData.getBirth();
    }

    public String getGender() {
        return getPersonalData().getGender();
    }

    @Override
    public String toString() {
        return "Nome: " + getName() + ", SUS: " + getNumSus();
    }

}