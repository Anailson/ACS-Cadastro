package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;

public class AnotherReasons extends RealmObject implements Serializable {
    private boolean internment, controlEnvironments, collectiveActivities, guidance, others;

    public AnotherReasons() {
        this(new boolean[5]);
    }

    public AnotherReasons(boolean[] reasons) {
        this(reasons[0], reasons[1], reasons[2], reasons[3], reasons[4]);
    }

    public AnotherReasons(boolean internment, boolean controlEnvironments, boolean collectiveActivities, boolean guidance, boolean others) {
        this.internment = internment;
        this.controlEnvironments = controlEnvironments;
        this.collectiveActivities = collectiveActivities;
        this.guidance = guidance;
        this.others = others;
    }

    public boolean[] getValues(){
        return new boolean[]{internment, controlEnvironments, collectiveActivities, guidance, others};
    }

    public boolean isInternment() {
        return internment;
    }

    public void setInternment(boolean internment) {
        this.internment = internment;
    }

    public boolean isControlEnvironments() {
        return controlEnvironments;
    }

    public void setControlEnvironments(boolean controlEnvironments) {
        this.controlEnvironments = controlEnvironments;
    }

    public boolean isCollectiveActivities() {
        return collectiveActivities;
    }

    public void setCollectiveActivities(boolean collectiveActivities) {
        this.collectiveActivities = collectiveActivities;
    }

    public boolean isGuidance() {
        return guidance;
    }

    public void setGuidance(boolean guidance) {
        this.guidance = guidance;
    }

    public boolean isOthers() {
        return others;
    }

    public void setOthers(boolean others) {
        this.others = others;
    }
}
