package tcc.acs_cadastro_mobile.subModels;

import io.realm.RealmObject;

import java.io.Serializable;

public class TrackingDiseases extends RealmObject implements Serializable {

    private boolean cervicalCancer, breastCancer, cardiovascular;

    public TrackingDiseases() {
        this(new boolean[3]);
    }

    public TrackingDiseases(boolean[] tracking) {
        this(tracking[0], tracking[1], tracking[2]);

        if(tracking.length != 3){
            throw new IllegalArgumentException("Length of conditions array must be 3 instead " + tracking.length);
        }
    }

    public TrackingDiseases(boolean cervicalCancer, boolean breastCancer, boolean cardiovascular) {
        this.cervicalCancer = cervicalCancer;
        this.breastCancer = breastCancer;
        this.cardiovascular = cardiovascular;
    }

    public boolean[] getValues() {
        return new boolean[]{cervicalCancer, breastCancer, cardiovascular};
    }

    public boolean isCervicalCancer() {
        return cervicalCancer;
    }

    public void setCervicalCancer(boolean cervicalCancer) {
        this.cervicalCancer = cervicalCancer;
    }

    public boolean isBreastCancer() {
        return breastCancer;
    }

    public void setBreastCancer(boolean breastCancer) {
        this.breastCancer = breastCancer;
    }

    public boolean isCardiovascular() {
        return cardiovascular;
    }

    public void setCardiovascular(boolean cardiovascular) {
        this.cardiovascular = cardiovascular;
    }
}
