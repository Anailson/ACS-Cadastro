package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class ActiveSearch extends RealmObject implements Serializable{
    private boolean appointment, exam, vaccine, conditions;

    public ActiveSearch() {
        this(new boolean[4]);
    }

    public ActiveSearch(boolean[] results) {
        this(results[0], results[1], results[2], results[3]);
    }

    public ActiveSearch(boolean appointment, boolean exam, boolean vaccine, boolean conditions) {
        this.appointment = appointment;
        this.exam = exam;
        this.vaccine = vaccine;
        this.conditions = conditions;
    }


    public boolean[] getValues() {
        return new boolean[]{appointment, exam, vaccine, conditions};
    }

    public boolean isAppointment() {
        return appointment;
    }

    public void setAppointment(boolean appointment) {
        this.appointment = appointment;
    }

    public boolean isExam() {
        return exam;
    }

    public void setExam(boolean exam) {
        this.exam = exam;
    }

    public boolean isVaccine() {
        return vaccine;
    }

    public void setVaccine(boolean vaccine) {
        this.vaccine = vaccine;
    }

    public boolean isConditions() {
        return conditions;
    }

    public void setConditions(boolean conditions) {
        this.conditions = conditions;
    }

    public JSONObject asJson(){
        JSONObject json = new JSONObject();
        try {
            json.put(Constants.Visit.APPOINTMENT.name(), appointment);
            json.put(Constants.Visit.EXAM.name(), exam);
            json.put(Constants.Visit.VACCINE.name(), vaccine);
            json.put(Constants.Visit.CONDITIONS.name(), conditions);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
