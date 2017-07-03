package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.HealthConditionsModel;
import tcc.acs_cadastro_mobile.subModels.Diseases;
import tcc.acs_cadastro_mobile.subModels.HeartDisease;
import tcc.acs_cadastro_mobile.subModels.Interment;
import tcc.acs_cadastro_mobile.subModels.KidneyDisease;
import tcc.acs_cadastro_mobile.subModels.Plant;
import tcc.acs_cadastro_mobile.subModels.Pregnant;
import tcc.acs_cadastro_mobile.subModels.RespiratoryDisease;

public class HealthConditionsPersistence {

    private HealthConditionsPersistence() {}

    public static HealthConditionsModel get(Pregnant pregnant, String weight, Diseases diseases,
                    HeartDisease heartDisease, KidneyDisease kidneyDisease, RespiratoryDisease respiratoryDisease,
                    Interment interment, Plant plants) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        HealthConditionsModel object = realm.copyToRealm(new HealthConditionsModel(pregnant, weight, diseases,
                heartDisease, kidneyDisease, respiratoryDisease, interment, plants));
        realm.commitTransaction();
        return object;
    }

    public static Pregnant getPregnant(boolean isPregnant, String maternity) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Pregnant object = realm.copyToRealm(new Pregnant(isPregnant, maternity));
        realm.commitTransaction();
        return object;
    }

    public static Diseases get(boolean smoker, boolean alcohol, boolean drugs, boolean hypertension,
                               boolean diabetis, boolean avc, boolean heartAttack, boolean leprosy,
                               boolean tuberculosis, boolean cancer, boolean inBed, boolean domiciled,
                               boolean otherPractices, boolean mentalHealth) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Diseases object = realm.copyToRealm(new Diseases(new boolean[]{smoker, alcohol, drugs, hypertension,
        diabetis, avc, heartAttack, leprosy, tuberculosis, cancer, inBed, domiciled, otherPractices, mentalHealth}));
        realm.commitTransaction();
        return object;
    }

    public static HeartDisease getHeartDisease(boolean isHeartDisease, boolean[] diseases) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        HeartDisease object = realm.copyToRealm(new HeartDisease(isHeartDisease, diseases));
        realm.commitTransaction();
        return object;
    }

    public static KidneyDisease getKidneyDisease(boolean kidneyDisease, boolean[] diseases) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        KidneyDisease object = realm.copyToRealm(new KidneyDisease(kidneyDisease, diseases));
        realm.commitTransaction();
        return object;
    }

    public static RespiratoryDisease getRespiratoryDisease(boolean isRespiratoryDisease, boolean[] diseases) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RespiratoryDisease object = realm.copyToRealm(new RespiratoryDisease(isRespiratoryDisease, diseases));
        realm.commitTransaction();
        return object;
    }

    public static Interment getInterment(boolean isInterment, String interment) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Interment object = realm.copyToRealm(new Interment(isInterment, interment));
        realm.commitTransaction();
        return object;
    }

    public static Plant getPlant(boolean isPlant, String value) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Plant object = realm.copyToRealm(new Plant(isPlant, value));
        realm.commitTransaction();
        return object;
    }
}
