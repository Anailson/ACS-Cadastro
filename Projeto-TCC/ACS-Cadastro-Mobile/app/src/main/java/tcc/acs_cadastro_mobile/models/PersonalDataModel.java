package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.Contact;
import tcc.acs_cadastro_mobile.subModels.GenderAndRace;
import tcc.acs_cadastro_mobile.subModels.Mother;
import tcc.acs_cadastro_mobile.subModels.Nationality;
import tcc.acs_cadastro_mobile.subModels.ParticularData;
import tcc.acs_cadastro_mobile.subModels.Responsible;

public class PersonalDataModel extends RealmObject implements Serializable {

    private ParticularData particularData;
    private Mother mother;
    private Responsible responsible;
    private GenderAndRace genderNRace;
    private Nationality nationality;
    private Contact contact;

    public PersonalDataModel() {
        this.particularData = new ParticularData();
        this.mother = new Mother();
        this.responsible = new Responsible();
        this.genderNRace = new GenderAndRace();
        this.nationality = new Nationality();
        this.contact = new Contact();
    }

    public static PersonalDataModel newInstance(Realm realm, ParticularData particularData, Mother mother,
                        Responsible responsible, GenderAndRace genderNRace, Nationality nationality, Contact contact) {
        realm.beginTransaction();
        PersonalDataModel data = realm.createObject(PersonalDataModel.class);
        data.setParticularData(particularData);
        data.setMother(mother);
        data.setResponsible(responsible);
        data.setGenderNRace(genderNRace);
        data.setNationality(nationality);
        data.setContact(contact);
        realm.commitTransaction();
        return data;
    }

    public static ParticularData getParticularData(Realm realm, long sus, String name,
                                   String socialName, long nis, String birth) {
        realm.beginTransaction();
        ParticularData object = realm.createObject(ParticularData.class);
        object.setNumSus(sus);
        object.setName(name);
        object.setSocialName(socialName);
        object.setNumNis(nis);
        object.setBirthDate(birth);
        realm.commitTransaction();
        return object;
    }

    public static Mother getMother(Realm realm, boolean isKnown, String name){
        realm.beginTransaction();
        Mother object = realm.createObject(Mother.class);
        object.setKnown(isKnown);
        object.setName(name);
        realm.commitTransaction();
        return object;
    }

    public static Responsible getResponsible(Realm realm, boolean isResponsible, long numSus,
                                             String birthDate){
        realm.beginTransaction();
        Responsible object = realm.createObject(Responsible.class);
        object.setResponsible(isResponsible);
        object.setNumSus(numSus);
        object.setBirthDate(birthDate);
        realm.commitTransaction();
        return object;
    }

    public static GenderAndRace getGenderAndRace(Realm realm, String gender, String race){
        realm.beginTransaction();
        GenderAndRace object = realm.createObject(GenderAndRace.class);
        object.setGender(gender);
        object.setRace(race);
        realm.commitTransaction();
        return object;
    }

    public static Nationality getNationality(Realm realm,String nationality, String nationBirth,
                                             String uf, String city){
        realm.beginTransaction();
        Nationality object = realm.createObject(Nationality.class);
        object.setNationality(nationality);
        object.setNationBirth(nationBirth);
        object.setUf(uf);
        object.setCity(city);
        realm.commitTransaction();
        return object;
    }

    public static Contact getContact(Realm realm, String phone, String email){
        realm.beginTransaction();
        Contact object = realm.createObject(Contact.class);
        object.setPhone(phone);
        object.setEmail(email);
        realm.commitTransaction();
        return object;
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
}