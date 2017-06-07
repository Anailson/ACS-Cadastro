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

    public static HealthConditionsModel getInstance(Pregnant pregnant, String weight, Diseases diseases,
                    HeartDisease heartDisease, KidneyDisease kidneyDisease, RespiratoryDisease respiratoryDisease,
                    Interment interment, Plant plants) {
        Realm realm = Realm.getDefaultInstance();
        return HealthConditionsModel.newInstance(realm, pregnant, weight, diseases, heartDisease,
                kidneyDisease, respiratoryDisease, interment, plants);
    }

    public static Pregnant getPregnant(boolean isPregnant, String maternity) {
        Realm realm = Realm.getDefaultInstance();
        return HealthConditionsModel.getPregnant(realm, isPregnant, maternity);
    }

    public static Diseases getDiseases(boolean smoker, boolean alcohol, boolean drugs, boolean hypertension,
                    boolean diabetis, boolean avc, boolean heartAttack, boolean leprosy,
                    boolean tuberculosis, boolean cancer, boolean inBed, boolean domiciled,
                    boolean otherPractices, boolean mentalHealth) {
        Realm realm = Realm.getDefaultInstance();
        boolean [] diseases = new boolean[]{smoker, alcohol, drugs,  hypertension,
                diabetis, avc, heartAttack, leprosy, tuberculosis, cancer, inBed, domiciled,
                otherPractices, mentalHealth};
        return HealthConditionsModel.getDiseases(realm, diseases);
    }

    public static HeartDisease getHeartDisease(boolean isHeartDisease, boolean[] diseases) {
        Realm realm = Realm.getDefaultInstance();
        return HealthConditionsModel.getHeartDisease(realm, isHeartDisease, diseases);
    }

    public static KidneyDisease getKidneyDisease(boolean kidneyDisease, boolean[] diseases) {
        Realm realm = Realm.getDefaultInstance();
        return HealthConditionsModel.getKidneyDisease(realm, kidneyDisease, diseases);
    }

    public static RespiratoryDisease getRespiratoryDisease(boolean isRespiratoryDisease, boolean[] diseases) {
        Realm realm = Realm.getDefaultInstance();
        return HealthConditionsModel.getRespiratoryDisease(realm, isRespiratoryDisease, diseases);
    }

    public static Interment getInterment(boolean isInterment, String interment) {
        Realm realm = Realm.getDefaultInstance();
        return HealthConditionsModel.getInterment(realm, isInterment, interment);
    }

    public static Plant getPlant(boolean isPlant, String value) {
        Realm realm = Realm.getDefaultInstance();
        return HealthConditionsModel.getPlant(realm, isPlant, value);
    }
}
