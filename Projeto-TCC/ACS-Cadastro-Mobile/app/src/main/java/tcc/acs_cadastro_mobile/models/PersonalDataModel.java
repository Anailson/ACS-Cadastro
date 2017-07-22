package tcc.acs_cadastro_mobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.Contact;
import tcc.acs_cadastro_mobile.subModels.GenderAndRace;
import tcc.acs_cadastro_mobile.subModels.Mother;
import tcc.acs_cadastro_mobile.subModels.Nationality;
import tcc.acs_cadastro_mobile.subModels.ParticularData;
import tcc.acs_cadastro_mobile.subModels.Responsible;

import static tcc.acs_cadastro_mobile.Constants.Citizen.CONTACT;
import static tcc.acs_cadastro_mobile.Constants.Citizen.GENDER_RACE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.MOTHER;
import static tcc.acs_cadastro_mobile.Constants.Citizen.NATIONALITY;
import static tcc.acs_cadastro_mobile.Constants.Citizen.PARTICULAR;
import static tcc.acs_cadastro_mobile.Constants.Citizen.RESPONSIBLE;

public class PersonalDataModel extends RealmObject implements Serializable {

    private ParticularData particularData;
    private Mother mother;
    private Responsible responsible;
    private GenderAndRace genderNRace;
    private Nationality nationality;
    private Contact contact;

    public PersonalDataModel() {
        this(new ParticularData(), new Mother(), new Responsible(), new GenderAndRace(), new Nationality(),
                new Contact());
    }

    public PersonalDataModel(ParticularData particularData, Mother mother, Responsible responsible,
                             GenderAndRace genderNRace, Nationality nationality, Contact contact) {
        this.particularData = particularData;
        this.mother = mother;
        this.responsible = responsible;
        this.genderNRace = genderNRace;
        this.nationality = nationality;
        this.contact = contact;
    }


    public ParticularData getParticularData() {
        return particularData;
    }

    public void setParticularData(ParticularData particularData) {
        this.particularData = particularData;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }

    public Responsible getResponsible() {
        return responsible;
    }

    public GenderAndRace getGenderNRace() {
        return genderNRace;
    }

    public void setGenderNRace(GenderAndRace genderNRace) {
        this.genderNRace = genderNRace;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public long getNumSus() {
        return getParticularData().getNumSus();
    }

    public String getName() {
        return getParticularData().getName();
    }

    public String getSocialName() {
        return getParticularData().getSocialName();
    }

    public boolean isMotherUnknown() {
        return getMother().isKnown();
    }

    public String getMotherName() {
        return getMother().getName();
    }

    public long getNumNis() {
        return getParticularData().getNumNis();
    }

    public String getBirth() {
        return getParticularData().getBirthDate();
    }

    public boolean isResponsible() {
        return getResponsible().isResponsible();
    }

    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }

    public long getRespNumSus() {
        return getResponsible().getNumSus();
    }

    public String getRespBirth() {
        return getResponsible().getBirthDate();
    }

    public String getGender() {
        return getGenderNRace().getGender();
    }

    public String getRace() {
        return getGenderNRace().getRace();
    }

    public String getNation() {
        return getNationality().getNationality();
    }

    public String getNationBirth() {
        return getNationality().getNationBirth();
    }

    public String getUf() {
        return getNationality().getUf();
    }

    public String getCity() {
        return getNationality().getCity();
    }

    public String getPhone() {
        return getContact().getPhone();
    }

    public String getEmail() {
        return getContact().getEmail();
    }

    public boolean isNationBirth() {
        return getNationBirth().equals("Brasil");
    }

    public JSONObject   asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(PARTICULAR.name(), particularData.asJson());
        json.put(MOTHER.name(), mother.asJson());
        json.put(RESPONSIBLE.name(), responsible.asJson());
        json.put(GENDER_RACE.name(), genderNRace.asJson());
        json.put(NATIONALITY.name(), nationality.asJson());
        json.put(CONTACT.name(), contact.asJson());
        return json;
    }
}