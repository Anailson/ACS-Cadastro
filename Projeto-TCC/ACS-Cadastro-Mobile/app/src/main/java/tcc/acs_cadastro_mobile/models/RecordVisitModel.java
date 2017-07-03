package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;


public class RecordVisitModel extends RealmObject implements Serializable {

    private boolean isShared;
    private RecordDetails details;

    public RecordVisitModel() {
        this(new RecordDetails(), false);
    }

    public RecordVisitModel(RecordDetails details, boolean isShared) {
        this.details = details;
        this.isShared = isShared;
    }

    public long getRecord(){
        return getDetails().getRecord();
    }

    public long getNumSus(){
        return getDetails().getNumSus();
    }

    public String getName(){
        return getDetails().getName();
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        isShared = shared;
    }

    public RecordDetails getDetails() {
        return details;
    }

    public void setDetails(RecordDetails details) {
        this.details = details;
    }
}
