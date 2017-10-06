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

    public static PersonalDataModel get(ParticularData particular, Mother mother, Responsible responsible,
                                        GenderAndRace genderAndRace, Nationality nationality, Contact contact) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        PersonalDataModel object = realm.copyToRealm(new PersonalDataModel(particular, mother,
                responsible, genderAndRace, nationality, contact));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static ParticularData get(long sus, String name, String socialName,
                                     long nis, String birth){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        ParticularData object = realm.copyToRealm(new ParticularData(sus, nis, name, socialName, birth));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static Mother get(boolean isKnown, String name){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Mother object = realm.copyToRealm(new Mother(isKnown, name));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static Responsible get(boolean responsible, long numSus, String birthDate) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Responsible object = realm.copyToRealm(new Responsible(responsible, numSus, birthDate));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static GenderAndRace getGenderAndRace(String gender, String race) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        GenderAndRace object = realm.copyToRealm(new GenderAndRace(gender, race));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static Nationality get(String nationality, String nationBirth, String uf, String city) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Nationality object = realm.copyToRealm(new Nationality(nationality, nationBirth, uf, city));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static Contact getContact(String phone, String email) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Contact object = realm.copyToRealm(new Contact(phone, email));
        realm.commitTransaction();
        realm.close();
        return object;
    }
}
