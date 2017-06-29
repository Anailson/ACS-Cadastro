package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;


public class Conduct extends RealmObject implements Serializable {

    private boolean scheduledAppointment, scheduledCare, groupScheduling, nasfScheduling, relsease;

    public Conduct() {
        this(new boolean[5]);
    }

    public Conduct(boolean[] conduct) {
        this(conduct[0], conduct[1], conduct[2], conduct[3], conduct[4]);
        if(conduct.length != 5){
            throw new IllegalArgumentException("Length of conditions array must be 5 instead " + conduct.length);
        }
    }

    public Conduct(boolean scheduledAppointment, boolean scheduledCare, boolean groupScheduling,
                   boolean nasfScheduling, boolean relsease) {
        this.scheduledAppointment = scheduledAppointment;
        this.scheduledCare = scheduledCare;
        this.groupScheduling = groupScheduling;
        this.nasfScheduling = nasfScheduling;
        this.relsease = relsease;
    }

    public boolean[] getValues() {
        return new boolean[]{scheduledAppointment, scheduledCare, groupScheduling, nasfScheduling, relsease};
    }

    public boolean isScheduledAppointment() {
        return scheduledAppointment;
    }

    public void setScheduledAppointment(boolean scheduledAppointment) {
        this.scheduledAppointment = scheduledAppointment;
    }

    public boolean isScheduledCare() {
        return scheduledCare;
    }

    public void setScheduledCare(boolean scheduledCare) {
        this.scheduledCare = scheduledCare;
    }

    public boolean isGroupScheduling() {
        return groupScheduling;
    }

    public void setGroupScheduling(boolean groupScheduling) {
        this.groupScheduling = groupScheduling;
    }

    public boolean isNasfScheduling() {
        return nasfScheduling;
    }

    public void setNasfScheduling(boolean nasfScheduling) {
        this.nasfScheduling = nasfScheduling;
    }

    public boolean isRelsease() {
        return relsease;
    }

    public void setRelsease(boolean relsease) {
        this.relsease = relsease;
    }
}
