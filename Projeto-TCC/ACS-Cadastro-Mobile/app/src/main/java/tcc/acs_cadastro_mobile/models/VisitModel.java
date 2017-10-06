package tcc.acs_cadastro_mobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.interfaces.ISearcher;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;
import tcc.acs_cadastro_mobile.persistence.VisitPersistence;

public class VisitModel extends RealmObject implements Serializable, ISearcher {

    public static final String RECORD = "record";
    public static final String NUM_SUS = "numSus";

    @PrimaryKey
    private long record;
    private long numSus;
    private RecordVisitModel details;
    private ReasonsVisitModel reasons;
    private int status;

    public VisitModel() {
        this(new RecordVisitModel(), new ReasonsVisitModel());
    }

    public VisitModel(RecordVisitModel details, ReasonsVisitModel reasons) {
        this.record = details.getRecord();
        this.numSus = details.getNumSus();
        this.details = details;
        this.reasons = reasons;
    }

    public String getName() {
        return getDetails().getName();
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public boolean recordContainsKey(int key){
        return getRecord() > AcsRecordPersistence.DEFAULT_INT
                && containsKey(String.valueOf(getRecord()), String.valueOf(key));
    }

    public boolean numSusContainsKey(int key){
        return getNumSus() > AcsRecordPersistence.DEFAULT_INT
                && containsKey(String.valueOf(getNumSus()), String.valueOf(key));
    }

    public boolean nameContainsKey(String key){
        return containsKey(getName(), key);
    }


    private boolean containsKey(String value, String key){
        return value.trim().toUpperCase().contains(key.toUpperCase());
    }
}
