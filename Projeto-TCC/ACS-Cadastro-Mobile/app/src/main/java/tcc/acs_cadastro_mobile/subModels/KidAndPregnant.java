package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;

import static tcc.acs_cadastro_mobile.Constants.Accompany.BREAST_FEEDING;
import static tcc.acs_cadastro_mobile.Constants.Accompany.CHILD_BIRTH;
import static tcc.acs_cadastro_mobile.Constants.Accompany.DUM;
import static tcc.acs_cadastro_mobile.Constants.Accompany.HOME_CARE;
import static tcc.acs_cadastro_mobile.Constants.Accompany.PLANNED_PREGNANCY;
import static tcc.acs_cadastro_mobile.Constants.Accompany.PREVIOUS;
import static tcc.acs_cadastro_mobile.Constants.Accompany.WEEKS;

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

    public JSONObject asJson() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(BREAST_FEEDING.name(), breastFeeding);
        json.put(DUM.name(), dum);
        json.put(PLANNED_PREGNANCY.name(), plannedPregnancy);
        json.put(WEEKS.name(), weeks);
        json.put(PREVIOUS.name(), previous);
        json.put(CHILD_BIRTH.name(), childBirth);
        json.put(HOME_CARE.name(), homeCare);
        return json;
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
