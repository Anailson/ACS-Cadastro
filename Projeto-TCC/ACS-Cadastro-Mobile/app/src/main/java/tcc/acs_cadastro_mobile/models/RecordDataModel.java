package tcc.acs_cadastro_mobile.models;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.Anthropometric;
import tcc.acs_cadastro_mobile.subModels.KidAndPregnant;
import tcc.acs_cadastro_mobile.subModels.RecordDetails;

import java.io.Serializable;

public class RecordDataModel extends RealmObject implements Serializable {

    private RecordDetails recordDetails;
    private Anthropometric anthropometric;
    private KidAndPregnant kidsPregnant;

    public RecordDataModel() {
        this(new RecordDetails(), new Anthropometric(), new KidAndPregnant());
    }

    public RecordDataModel(RecordDetails recordDetails, Anthropometric anthropometric, KidAndPregnant kidsPregnant) {
        this.recordDetails = recordDetails;
        this.anthropometric = anthropometric;
        this.kidsPregnant = kidsPregnant;
    }

    public long getRecord(){
        return getRecordDetails().getRecord();
    }

    public String getName(){
        return getRecordDetails().getName();
    }

    public long getNumSus(){
        return getRecordDetails().getNumSus();
    }

    public String getGender(){
        return getRecordDetails().getGender();
    }

    public String getBirthDate(){
        return getRecordDetails().getBirthDate();
    }

    public RecordDetails getRecordDetails() {
        return recordDetails;
    }

    public void setRecordDetails(RecordDetails recordDetails) {
        this.recordDetails = recordDetails;
    }

    public Anthropometric getAnthropometric() {
        return anthropometric;
    }

    public void setAnthropometric(Anthropometric anthropometric) {
        this.anthropometric = anthropometric;
    }

    public KidAndPregnant getKidsPregnant() {
        return kidsPregnant;
    }

    public void setKidsPregnant(KidAndPregnant kidsPregnant) {
        this.kidsPregnant = kidsPregnant;
    }
}
