package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;


public class RecordVisitModel extends RealmObject implements Serializable {

    private boolean isShared;
    private RecordDetails details;

    public RecordVisitModel() {
        this(false, new RecordDetails());
    }

    public RecordVisitModel(boolean isShared, RecordDetails details) {
        this.isShared = isShared;
        this.details = details;
    }

    public long getRecord(){
        return getDetails().getRecord();
    }

    public long getNumSus(){
        return getDetails().getNumSus();
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
