package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

public class SocialDemographicModel implements Serializable {

    private String[] kinship, education, employment, kids09, sexualOrientation, communityTraditional;
    private String occupation;
    private boolean school, caregiver, communityGroup, healthPlan;//, communityTraditionalYN, sexualOrientationYN, deficiencyYN;
    private boolean[] deficiency;

    public SocialDemographicModel() {
        kinship = education = employment = kids09 = communityTraditional = sexualOrientation
                = new String[]{"" + CitizenModel.INT_DEFAULT_VALUE, CitizenModel.STRING_DEFAULT_VALUE};
        occupation = CitizenModel.STRING_DEFAULT_VALUE;
        school = caregiver = communityGroup = healthPlan = false;
        deficiency = new boolean[]{false, false, false, false, false, false};
    }

    public SocialDemographicModel(String[] kinship, String occupation, boolean school, String[] education,
                                  String[] employment, String[] kids09, boolean caregiver, boolean communityGroup,
                                  boolean healthPlan, String[] communityTraditional, String[] sexualOrientation,
                                  boolean[] deficiency) {
        this.kinship = kinship;
        this.occupation = occupation;
        this.school = school;
        this.education = education;
        this.employment = employment;
        this.kids09 = kids09;
        this.caregiver = caregiver;
        this.communityGroup = communityGroup;
        this.healthPlan = healthPlan;
        this.communityTraditional = communityTraditional;
        this.sexualOrientation = sexualOrientation;
        this.deficiency = deficiency;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String[] getKinship() {
        return kinship;
    }

    public String getOccupation() {
        return occupation;
    }

    public boolean isSchool() {
        return school;
    }

    public String[] getEducation() {
        return education;
    }

    public String[] getEmployment() {
        return employment;
    }

    public String[] getKids() {
        return kids09;
    }

    public boolean isCaregiver() {
        return caregiver;
    }

    public boolean isCommunityGroup() {
        return communityGroup;
    }

    public boolean isHealthPlan() {
        return healthPlan;
    }

    public boolean isCommunityTraditional() {
        return !communityTraditional[CitizenModel.INDEX].equals(CitizenModel.INT_DEFAULT_VALUE + "");
    }

    public String[] getCommunityTraditional() {
        return communityTraditional;
    }

    public boolean isSexualOrientation() {
        return !sexualOrientation[CitizenModel.INDEX].equals(CitizenModel.INT_DEFAULT_VALUE + "");
    }

    public String[] getSexualOrientation() {
        return sexualOrientation;
    }

    public boolean isDeficiency() {
        if (deficiency.length != 6) {
            throw new IllegalStateException("Array 'deficiency' must be 6 index. It currently has " + deficiency.length);
        }
        return deficiency[0];
    }

    public boolean[] getDeficiency() {
        return deficiency;
    }
}
