package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.CommunicableDisease;
import tcc.acs_cadastro_mobile.subModels.ConditionDiseases;
import tcc.acs_cadastro_mobile.subModels.TrackingDiseases;

public class ConditionsModel extends RealmObject implements Serializable {

    private ConditionDiseases condition;
    private CommunicableDisease communicable;
    private TrackingDiseases tracking;
    private RealmList<RealmInt> anothers;

    public ConditionsModel() {
        this(new ConditionDiseases(), new CommunicableDisease(), new TrackingDiseases(), new RealmList<RealmInt>());
    }

    public ConditionsModel(ConditionDiseases condition, CommunicableDisease communicable,
                           TrackingDiseases tracking, RealmList<RealmInt> anothers) {
        this.condition = condition;
        this.communicable = communicable;
        this.tracking = tracking;
        this.anothers = anothers;
    }

    public ConditionDiseases getCondition() {
        return condition;
    }

    public boolean[] getConditions(){
        return condition.getValues();
    }

    public boolean[] getCommunicables(){
        return communicable.getValues();
    }

    public boolean[] getTrackings(){
        return tracking.getValues();
    }

    public void setCondition(ConditionDiseases condition) {
        this.condition = condition;
    }

    public CommunicableDisease getCommunicable() {
        return communicable;
    }

    public void setCommunicable(CommunicableDisease communicable) {
        this.communicable = communicable;
    }

    public TrackingDiseases getTracking() {
        return tracking;
    }

    public void setTracking(TrackingDiseases tracking) {
        this.tracking = tracking;
    }

    public RealmList<RealmInt> getAnothers() {
        return anothers;
    }

    public void setAnothers(RealmList<RealmInt> anothers) {
        this.anothers = anothers;
    }
}
