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

    public static StreetSituationModel getInstance(StreetSituation street, boolean benefit,
                           boolean family, Feeding feeding, AnotherInstitution anotherInstitution,
                           FamilyVisit familyVisit, Hygiene hygiene) {
        Realm realm = Realm.getDefaultInstance();
        return StreetSituationModel.newInstance(realm, street, benefit, family, feeding, anotherInstitution,
                familyVisit, hygiene);
    }

    public static StreetSituation getStreetSituation(boolean isStreetSituation, String fields) {
        Realm realm = Realm.getDefaultInstance();
        return StreetSituationModel.getStreetSituation(realm, isStreetSituation, fields);
    }

    public static Feeding getFeeding(String fields, boolean[] fields1) {
        Realm realm = Realm.getDefaultInstance();
        return StreetSituationModel.getFeeding(realm, fields, fields1);
    }

    public static AnotherInstitution getAnotherInstitution(boolean isAnotherInstitution, String fields) {
        Realm realm = Realm.getDefaultInstance();
        return StreetSituationModel.getAnotherInstitution(realm, isAnotherInstitution, fields);
    }

    public static FamilyVisit getFamilyVisit(boolean isFamilyVisit, String fields) {
        Realm realm = Realm.getDefaultInstance();
        return StreetSituationModel.getFamilyVisit(realm, isFamilyVisit, fields);
    }

    public static Hygiene getHygiene(boolean isHygiene, boolean[] fields) {
        Realm realm = Realm.getDefaultInstance();
        return StreetSituationModel.getHygiene(realm, isHygiene, fields);
    }
}
