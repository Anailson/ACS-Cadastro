package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class AnotherReasons extends RealmObject implements Serializable {
    private boolean recordUpdate, periodicVisit, internment, controlEnvironments, collectiveActivities,
            guidance, others;

    public AnotherReasons() {
        this(new boolean[7]);
    }

    public AnotherReasons(boolean[] reasons) {
        this(reasons[0], reasons[1], reasons[2], reasons[3], reasons[4], reasons[5], reasons[6]);
    }

    public AnotherReasons(boolean record, boolean periodic, boolean internment, boolean controlEnvironments,
                          boolean collectiveActivities, boolean guidance, boolean others) {
        this.recordUpdate = record;
        this.periodicVisit = periodic;
        this.internment = internment;
        this.controlEnvironments = controlEnvironments;
        this.collectiveActivities = collectiveActivities;
        this.guidance = guidance;
        this.others = others;
    }

    public boolean[] getValues() {
        return new boolean[]{recordUpdate, periodicVisit, internment, controlEnvironments,
                collectiveActivities, guidance, others};
    }

    public boolean isRecordUpdate() {
        return recordUpdate;
    }

    public void setRecordUpdate(boolean recordUpdate) {
        this.recordUpdate = recordUpdate;
    }

    public boolean isPeriodicVisit() {
        return periodicVisit;
    }

    public void setPeriodicVisit(boolean periodicVisit) {
        this.periodicVisit = periodicVisit;
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

    public JSONObject asJson(){
        JSONObject json = new JSONObject();
        try {
            json.put(Constants.Visit.RECORD_UPDATE.name(), recordUpdate);
            json.put(Constants.Visit.PERIODIC_VISIT.name(), periodicVisit);
            json.put(Constants.Visit.INTERMENT.name(), internment);
            json.put(Constants.Visit.CONTROL_ENVIRONMENTS.name(), controlEnvironments);
            json.put(Constants.Visit.COLLECTIVE_ACTIVITIES.name(), collectiveActivities);
            json.put(Constants.Visit.GUIDANCE.name(), guidance);
            json.put(Constants.Visit.OTHERS.name(), others);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
