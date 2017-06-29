package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class KidAndPregnant extends RealmObject implements Serializable {


    private String breastFeeding, dum;
    private boolean plannedPregnancy;
    private int weeks, previous, childBirth;
    private String homeCare;

    public KidAndPregnant() {
        this("", "", false, 0, 0, 0, "");
    }

    public KidAndPregnant(String breastFeeding, String dum, boolean plannedPregnancy, int weeks,
                          int previous, int childBirth, String homeCare) {
        this.breastFeeding = breastFeeding;
        this.dum = dum;
        this.plannedPregnancy = plannedPregnancy;
        this.weeks = weeks;
        this.previous = previous;
        this.childBirth = childBirth;
        this.homeCare = homeCare;
    }

    public String getBreastFeeding() {
        return breastFeeding;
    }

    public void setBreastFeeding(String breastFeeding) {
        this.breastFeeding = breastFeeding;
    }

    public String getDum() {
        return dum;
    }

    public void setDum(String dum) {
        this.dum = dum;
    }

    public boolean isPlannedPregnancy() {
        return plannedPregnancy;
    }

    public void setPlannedPregnancy(boolean plannedPregnancy) {
        this.plannedPregnancy = plannedPregnancy;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getChildBirth() {
        return childBirth;
    }

    public void setChildBirth(int childBirth) {
        this.childBirth = childBirth;
    }

    public String getHomeCare() {
        return homeCare;
    }

    public void setHomeCare(String homeCare) {
        this.homeCare = homeCare;
    }
}
