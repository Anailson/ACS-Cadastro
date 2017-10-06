package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.CommunityTraditional;
import tcc.acs_cadastro_mobile.subModels.Deficiency;
import tcc.acs_cadastro_mobile.subModels.EducationEmployment;
import tcc.acs_cadastro_mobile.subModels.HealthAndGroup;
import tcc.acs_cadastro_mobile.subModels.SexualOrientation;

public class SocialDemographicModel extends RealmObject implements Serializable {

    private String kinship, kids09;
    private EducationEmployment educationEmployment;
    private HealthAndGroup healthAndGroup;
    private CommunityTraditional communityTraditional;
    private SexualOrientation sexualOrientation;
    private Deficiency deficiency;

    public SocialDemographicModel() {
        this("", new EducationEmployment(), new HealthAndGroup(), "", new CommunityTraditional(),
                new SexualOrientation(), new Deficiency());
    }

    public SocialDemographicModel(String kinship, EducationEmployment educationEmployment,
                                  HealthAndGroup healthAndGroup, String kids09, CommunityTraditional communityTraditional,
                                  SexualOrientation sexualOrientation, Deficiency deficiency) {
        this.kinship = kinship;
        this.kids09 = kids09;
        this.educationEmployment = educationEmployment;
        this.healthAndGroup = healthAndGroup;
        this.communityTraditional = communityTraditional;
        this.sexualOrientation = sexualOrientation;
        this.deficiency = deficiency;
    }


    public String getKinship() {
        return kinship;
    }

    public void setKinship(String kinship) {
        this.kinship = kinship;
    }

    public EducationEmployment getEducationEmployment() {
        return educationEmployment;
    }

    public void setEducationEmployment(EducationEmployment educationEmployment) {
        this.educationEmployment = educationEmployment;
    }

    public String getKids09() {
        return kids09;
    }

    public void setKids09(String kids09) {
        this.kids09 = kids09;
    }

    public HealthAndGroup getHealthAndGroup() {
        return healthAndGroup;
    }

    public void setHealthAndGroup(HealthAndGroup healthAndGroup) {
        this.healthAndGroup = healthAndGroup;
    }

    public CommunityTraditional getCommunityTraditional(){
        return communityTraditional;
    }

    public void setCommunityTraditional(CommunityTraditional communityTraditional) {
        this.communityTraditional = communityTraditional;
    }

    public SexualOrientation getSexualOrientation(){
        return sexualOrientation;
    }

    public void setSexualOrientation(SexualOrientation sexualOrientation){
        this.sexualOrientation = sexualOrientation;
    }

    public Deficiency getDeficiency() {
        return deficiency;
    }

    public void setDeficiency(Deficiency deficiency){
        this.deficiency = deficiency;
    }

    public String getOccupation() {
        return getEducationEmployment().getOccupation();
    }

    public boolean isSchool() {
        return getEducationEmployment().isSchool();
    }

    public String getEducation() {
        return getEducationEmployment().getEducation();
    }

    public String getEmployment() {
        return getEducationEmployment().getEmployment();
    }

    public String getKids() {
        return kids09;
    }

    public boolean isCaregiver() {
        return getHealthAndGroup().isCaregiver();
    }

    public boolean isCommunityGroup() {
        return getHealthAndGroup().isCommunityGroup();
    }

    public boolean isHealthPlan() {
        return healthAndGroup.isHealthPlan();
    }

    public boolean isCommunityTraditional() {
        return getCommunityTraditional().isCommunityTraditional();
    }

    public String getCommunityName() {
        return getCommunityTraditional().getValue();
    }

    public boolean isSexualOrientation() {
        return getSexualOrientation().isSexualOrientation();
    }

    public String getOrientation() {
        return getSexualOrientation().getValue();
    }

    public boolean isDeficiency() {
        return getDeficiency().isDeficiency();
    }

    public boolean[] getDeficiencys(){
        return getDeficiency().getDeficiencys();
    }
}
