package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.subModels.Contact;
import tcc.acs_cadastro_mobile.subModels.GenderAndRace;
import tcc.acs_cadastro_mobile.subModels.Mother;
import tcc.acs_cadastro_mobile.subModels.Nationality;
import tcc.acs_cadastro_mobile.subModels.ParticularData;
import tcc.acs_cadastro_mobile.models.PersonalDataModel;
import tcc.acs_cadastro_mobile.subModels.Responsible;

public class PersonalDataPersistence {


    public static PersonalDataModel getInstance(ParticularData particular, Mother mother, Responsible responsible,
                                GenderAndRace genderAndRace, Nationality nationality, Contact contact) {

        return PersonalDataModel.newInstance(Realm.getDefaultInstance(), particular, mother,
                responsible, genderAndRace, nationality, contact);
    }

    public static ParticularData getParticularData(long sus, String name, String socialName,
                                                   long nis, String birth){
        Realm realm = Realm.getDefaultInstance();
        return PersonalDataModel.getParticularData(realm, sus, name, socialName, nis, birth);
    }
    public static Mother getMother(boolean isKnown, String name){

        Realm realm = Realm.getDefaultInstance();
        return PersonalDataModel.getMother(realm, isKnown, name);
    }

    public static Responsible getResponsible(boolean checked, long aLong, String fields) {
        Realm realm = Realm.getDefaultInstance();
        return PersonalDataModel.getResponsible(realm, checked, aLong, fields);
    }

    public static GenderAndRace getGenderAndRace(String gender, String race) {
        Realm realm = Realm.getDefaultInstance();
        return PersonalDataModel.getGenderAndRace(realm, gender, race);
    }

    public static Nationality getNationality(String nationality, String nationBirth, String uf, String city) {
        Realm realm = Realm.getDefaultInstance();
        return PersonalDataModel.getNationality(realm, nationality, nationBirth, uf, city);
    }

    public static Contact getContact(String phone, String email) {
        Realm realm = Realm.getDefaultInstance();
        return PersonalDataModel.getContact(realm, phone, email);
    }
}
