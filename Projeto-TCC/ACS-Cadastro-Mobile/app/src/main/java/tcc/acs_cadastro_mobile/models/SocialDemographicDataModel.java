package tcc.acs_cadastro_mobile.models;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.io.Serializable;

public class SocialDemographicDataModel implements Serializable {

    private String[] kinship, education, employment, kids09, communityTraditional, sexualOrientation;
    private String occupation;
    private boolean school, caregiver, communityGroup, healthPlan;
    private boolean[] deficiency;

    public SocialDemographicDataModel() {
        kinship = education = employment = kids09 = communityTraditional = sexualOrientation
                = new String[]{Integer.toString(0), "No value"};
        occupation = "No value";
        school = caregiver = communityGroup = healthPlan = false;
        deficiency = new boolean[]{false, false, false, false, false};
    }

    public SocialDemographicDataModel(String[] kinship, String occupation, boolean school,
                                      String[] education, String[] employment, String[] kids09, boolean caregiver,
                                      boolean communityGroup, boolean healthPlan, String[] communityTraditional,
                                      String[] sexualOrientation, boolean[] deficiency) {

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
/*
    public void fillKinship(Spinner spnKinship) {
        fillField(spnKinship, Integer.parseInt(kinship[0]));
    }

    public void fillOccupation(EditText edtOccupation) {
        fillField(edtOccupation, occupation);
    }

    public void fillSchool(RadioGroup rgrpSchool, int idYes, int idNo) {
        fillField(rgrpSchool, idYes, idNo, school);
    }

    public void fillEducation(Spinner spnEducation) {
        fillField(spnEducation, Integer.parseInt(education[0]));
    }

    public void fillEmployment(Spinner spnEmployment) {
        fillField(spnEmployment, Integer.parseInt(employment[0]));
    }

    public void fillKids09(Spinner spnKids09) {
        fillField(spnKids09, Integer.parseInt(kids09[0]));
    }

    public void fillCaregiver(RadioGroup rgrpCaregiver, int idYes, int idNo) {
        fillField(rgrpCaregiver, idYes, idNo, caregiver);
    }

    public void fillCommunityGroup(RadioGroup rgrpCommunityGroup, int idYes, int idNo) {
        fillField(rgrpCommunityGroup, idYes, idNo, communityGroup);
    }

    public void fillHealthPlan(RadioGroup rgrpHealthPlan, int idYes, int idNo) {
        fillField(rgrpHealthPlan, idYes, idNo, healthPlan);
    }

    public void fillCommunityTraditional(RadioGroup rgrpCommunityTraditional, int idYes, int idNo, EditText edtCommunityTraditional) {

        if (Boolean.parseBoolean(communityTraditional[0])) {
            fillField(rgrpCommunityTraditional, idYes, idNo, true);
            fillField(edtCommunityTraditional, communityTraditional[1]);
        }else {
            fillField(edtCommunityTraditional, "");
            fillField(rgrpCommunityTraditional, idYes, idNo, false);
        }
    }

    public void fillOrientationSexual(RadioGroup rgrpOrientationSexual, int idYes, int idNo) {
        fillField(rgrpOrientationSexual, idYes, idNo, Boolean.parseBoolean(sexualOrientation[0]));
    }

    public void fillDeficiency(CheckBox chbVisual, CheckBox chbHearing, CheckBox chbIntellectual,
                               CheckBox chbPhysical, CheckBox chbAnother) {
        chbVisual.setChecked(deficiency[0]);
        chbHearing.setChecked(deficiency[1]);
        chbIntellectual.setChecked(deficiency[2]);
        chbPhysical.setChecked(deficiency[3]);
        chbAnother.setChecked(deficiency[4]);
    }

    private void fillField(EditText editText, String value) {
        editText.setText(value);
    }

    private void fillField(Spinner spinner, int position){
        spinner.setSelection(position);
    }

    private void fillField(RadioGroup rGroup, int idYes, int idNo, boolean check) {

        if (check) {
            rGroup.check(idYes);
        } else {
            rGroup.check(idNo);
        }
    }*/
}
