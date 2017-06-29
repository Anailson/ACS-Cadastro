package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;

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
}
