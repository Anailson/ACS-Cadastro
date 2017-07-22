package tcc.acs_cadastro_mobile.subModels;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.models.CitizenModel;

import static tcc.acs_cadastro_mobile.Constants.Accompany.PLACE_CARE;
import static tcc.acs_cadastro_mobile.Constants.Accompany.RECORD;
import static tcc.acs_cadastro_mobile.Constants.Accompany.SHIFT;
import static tcc.acs_cadastro_mobile.Constants.Accompany.TYPE_CARE;
import static tcc.acs_cadastro_mobile.Constants.Citizen.NUM_SUS;

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

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(RECORD.name(), record);
        json.put(PLACE_CARE.name(), placeCare);
        json.put(TYPE_CARE.name(), typeCare);
        json.put(SHIFT.name(), shift);
        json.put(NUM_SUS.name(), citizen.getNumSus());
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
