package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.StreetSituationModel;
import tcc.acs_cadastro_mobile.subModels.AnotherInstitution;
import tcc.acs_cadastro_mobile.subModels.FamilyVisit;
import tcc.acs_cadastro_mobile.subModels.Feeding;
import tcc.acs_cadastro_mobile.subModels.Hygiene;
import tcc.acs_cadastro_mobile.subModels.StreetSituation;


public class StreetSituationPersistence {

    private StreetSituationPersistence() {}

    public static StreetSituationModel get(StreetSituation street, boolean benefit,
                       boolean family, Feeding feeding, AnotherInstitution anotherInstitution,
                       FamilyVisit familyVisit, Hygiene hygiene) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        StreetSituationModel object = realm.copyToRealm(new StreetSituationModel(street, benefit, family, feeding, anotherInstitution,
                familyVisit, hygiene));
        realm.commitTransaction();
        return object;
    }

    public static StreetSituation getStreetSituation(boolean isStreetSituation, String fields) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        StreetSituation object = realm.copyToRealm(new StreetSituation(isStreetSituation, fields));
        realm.commitTransaction();
        return object;
    }

    public static Feeding getFeeding(String fields, boolean[] fields1) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Feeding object = realm.copyToRealm(new Feeding(fields, fields1));
        realm.commitTransaction();
        return object;
    }

    public static AnotherInstitution getAnotherInstitution(boolean isAnotherInstitution, String fields) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        AnotherInstitution object = realm.copyToRealm(new AnotherInstitution(isAnotherInstitution, fields));
        realm.commitTransaction();
        return object;
    }

    public static FamilyVisit getFamilyVisit(boolean isFamilyVisit, String fields) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        FamilyVisit object = realm.copyToRealm(new FamilyVisit(isFamilyVisit, fields));
        realm.commitTransaction();
        return object;
    }

    public static Hygiene getHygiene(boolean isHygiene, boolean[] fields) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Hygiene object = realm.copyToRealm(new Hygiene(isHygiene, fields));
        realm.commitTransaction();
        return object;
    }
}
