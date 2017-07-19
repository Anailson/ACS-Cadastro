package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.models.AccompanyModel;
import tcc.acs_cadastro_mobile.models.CitizenModel;

public class RecordDetails extends RealmObject implements Serializable {

    private long record;
    private String placeCare, typeCare, shift;
    private CitizenModel citizen;

    public RecordDetails() {
        this(-1, "", "", "", new CitizenModel());
    }

    public RecordDetails(long record, String placeCare, String typeCare, String shift, CitizenModel citizen) {
        this.record = record;
        this.placeCare = placeCare;
        this.typeCare = typeCare;
        this.shift = shift;
        this.citizen = citizen;
    }

    public JSONObject getAsJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(AccompanyModel.DB_VALUES.RECORD_DATA.name(), record);
        json.put(AccompanyModel.DB_VALUES.PLACE_CARE.name(), placeCare);
        json.put(AccompanyModel.DB_VALUES.TYPE_CARE.name(), typeCare);
        json.put(AccompanyModel.DB_VALUES.SHIFT.name(), shift);
        return json;
    }

    public String getName(){
        return getCitizen().getName();
    }

    public long getNumSus(){
        return getCitizen().getNumSus();
    }

    public String getGender(){
        return getCitizen().getPersonalData().getGender();
    }

    public String getBirthDate(){
        return getCitizen().getPersonalData().getBirth();
    }

    public long getRecord() {
        return record;
    }

    public void setRecord(long record) {
        this.record = record;
    }

    public String getPlaceCare() {
        return placeCare;
    }

    public void setPlaceCare(String placeCare) {
        this.placeCare = placeCare;
    }

    public String getTypeCare() {
        return typeCare;
    }

    public void setTypeCare(String typeCare) {
        this.typeCare = typeCare;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public CitizenModel getCitizen() {
        return citizen;
    }

    public void setCitizen(CitizenModel citizen) {
        this.citizen = citizen;
    }
}
