package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class EducationEmployment extends RealmObject implements Serializable {

    private boolean school;
    private String occupation, education, employment;

    public EducationEmployment() {
        this(false, "", "", "");
    }

    public EducationEmployment(boolean school, String occupation, String education, String employment) {
        this.school = school;
        this.occupation = occupation;
        this.education = education;
        this.employment = employment;
    }

    public boolean isSchool() {
        return school;
    }

    public void setSchool(boolean school) {
        this.school = school;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }
}
