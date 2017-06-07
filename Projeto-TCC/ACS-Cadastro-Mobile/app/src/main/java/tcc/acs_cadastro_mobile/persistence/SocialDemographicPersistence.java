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

    public static SocialDemographicModel getInstance(String kinship, EducationEmployment educationEmployment,
                     HealthAndGroup healthAndGroup, String kids09, CommunityTraditional communityTraditional,
                     SexualOrientation sexualOrientation, Deficiency deficiency){
        return SocialDemographicModel.newInstance(Realm.getDefaultInstance(), kinship, educationEmployment,
                healthAndGroup, kids09, communityTraditional, sexualOrientation, deficiency);
    }

    public static EducationEmployment getEducationEmployment(boolean school, String occupation, String education, String employment){

        Realm realm = Realm.getDefaultInstance();
        return SocialDemographicModel.getEducationEmployment(realm, occupation, education, employment);
    }

    public static HealthAndGroup getHealthAndGroup(boolean caregiver, boolean communityGroup, boolean healthPlan){
        Realm realm = Realm.getDefaultInstance();
        return SocialDemographicModel.getHealthAndGroup(realm, caregiver, communityGroup, healthPlan);
    }

    public static CommunityTraditional getCommunityTraditional(boolean isCommunityTraditional, String value){
        Realm realm = Realm.getDefaultInstance();
        return SocialDemographicModel.getCommunityTraditional(realm, isCommunityTraditional, value);
    }

    public static SexualOrientation getSexualOrientation(boolean isOrientation, String value){
        Realm realm = Realm.getDefaultInstance();
        return SocialDemographicModel.getSexualOrientation(realm, isOrientation, value);
    }

    public static Deficiency getDeficiency(boolean isDeficiency, boolean[] deficiency){
        Realm realm = Realm.getDefaultInstance();
        return SocialDemographicModel.getDeficiency(realm, isDeficiency, deficiency);
    }
}
