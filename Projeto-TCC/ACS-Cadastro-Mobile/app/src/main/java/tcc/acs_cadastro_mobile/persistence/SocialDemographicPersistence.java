package tcc.acs_cadastro_mobile.persistence;

import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.SocialDemographicModel;
import tcc.acs_cadastro_mobile.subModels.CommunityTraditional;
import tcc.acs_cadastro_mobile.subModels.Deficiency;
import tcc.acs_cadastro_mobile.subModels.EducationEmployment;
import tcc.acs_cadastro_mobile.subModels.HealthAndGroup;
import tcc.acs_cadastro_mobile.subModels.SexualOrientation;

public class SocialDemographicPersistence {

    private SocialDemographicPersistence() {}

    public static SocialDemographicModel get(String kinship, EducationEmployment educationEmployment,
                                             HealthAndGroup healthAndGroup, String kids09, CommunityTraditional communityTraditional,
                                             SexualOrientation sexualOrientation, Deficiency deficiency){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        SocialDemographicModel object = realm.copyToRealm(new SocialDemographicModel(kinship, educationEmployment,
                healthAndGroup, kids09, communityTraditional, sexualOrientation, deficiency));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static EducationEmployment get(boolean school, String occupation, String education, String employment){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        EducationEmployment object = realm.copyToRealm(new EducationEmployment(school, occupation, education, employment));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static HealthAndGroup get(boolean caregiver, boolean communityGroup, boolean healthPlan){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        HealthAndGroup object = realm.copyToRealm(new HealthAndGroup(caregiver, communityGroup, healthPlan));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static CommunityTraditional getCommunityTraditional(boolean isCommunityTraditional, String value){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CommunityTraditional object = realm.copyToRealm(new CommunityTraditional(isCommunityTraditional, value));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static SexualOrientation getSexualOrientation(boolean isOrientation, String value){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        SexualOrientation object = realm.copyToRealm(new SexualOrientation(isOrientation, value));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static Deficiency get(boolean isDeficiency, boolean[] deficiency){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Deficiency object = realm.copyToRealm(new Deficiency(isDeficiency, deficiency));
        realm.commitTransaction();
        realm.close();
        return object;
    }
}
