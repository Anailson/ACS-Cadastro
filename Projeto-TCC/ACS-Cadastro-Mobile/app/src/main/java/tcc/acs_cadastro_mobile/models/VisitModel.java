package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;

public class VisitModel extends RealmObject implements Serializable, ISearcher {

    @PrimaryKey
    private long record;
    private long numSus;
    //private RecordDetails details;
    //private boolean isShared;
    private RecordVisitModel details;
    //private ActiveSearch active
    //private Following following
    //private AnotherReasons another
    private ReasonsVisitModel reasons;
    private String result;

    public VisitModel() {
        this(new RecordVisitModel(), new ReasonsVisitModel(), AcsRecordPersistence.DEFAULT_STR);
    }

    public VisitModel(RecordVisitModel details, ReasonsVisitModel reasons, String result) {
        this.record = details.getRecord();
        this.numSus = details.getNumSus();
        this.details = details;
        this.reasons = reasons;
        this.result = result;
    }

    public boolean[] getActiveSearchs(){
        return getReasons().getActive().getValues();
    }

    public boolean[] getFollowings(){
        return getReasons().getFollowing().getValues();
    }

    public boolean[] getAnotherReasons(){
        return getReasons().getAnother().getValues();
    }

    public long getRecord() {
        return record;
    }

    public void setRecord(long record) {
        this.record = record;
    }

    public long getNumSus() {
        return numSus;
    }

    public void setNumSus(long numSus) {
        this.numSus = numSus;
    }

    public RecordVisitModel getDetails() {
        return details;
    }

    public void setDetails(RecordVisitModel details) {
        this.details = details;
    }

    public ReasonsVisitModel getReasons() {
        return reasons;
    }

    public void setReasons(ReasonsVisitModel reasons) {
        this.reasons = reasons;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
