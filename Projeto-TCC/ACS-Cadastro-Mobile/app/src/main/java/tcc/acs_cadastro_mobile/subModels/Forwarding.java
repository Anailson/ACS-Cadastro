package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class Forwarding extends RealmObject implements Serializable{

    private boolean inTheDay, specializedService, caps, hospitalization, urgency, homeCareService,
            intersectoral;
    public Forwarding() {
        this(new boolean[7]);
    }

    public Forwarding(boolean[] forwarding) {
        this(forwarding[0], forwarding[1], forwarding[2], forwarding[3], forwarding[4], forwarding[5], forwarding[6]);
        if(forwarding.length != 7){
            throw new IllegalArgumentException("Length of conditions array must be 7 instead " + forwarding.length);
        }
    }

    public Forwarding(boolean inTheDay, boolean specializedService, boolean caps, boolean hospitalization,
                      boolean urgency, boolean homeCareService, boolean intersectoral) {
        this.inTheDay = inTheDay;
        this.specializedService = specializedService;
        this.caps = caps;
        this.hospitalization = hospitalization;
        this.urgency = urgency;
        this.homeCareService = homeCareService;
        this.intersectoral = intersectoral;
    }

    public boolean[] getValues() {
        return new boolean[]{inTheDay, specializedService, caps, hospitalization, urgency, homeCareService,
                intersectoral};
    }

    public boolean isInTheDay() {
        return inTheDay;
    }

    public void setInTheDay(boolean inTheDay) {
        this.inTheDay = inTheDay;
    }

    public boolean isSpecializedService() {
        return specializedService;
    }

    public void setSpecializedService(boolean specializedService) {
        this.specializedService = specializedService;
    }

    public boolean isCaps() {
        return caps;
    }

    public void setCaps(boolean caps) {
        this.caps = caps;
    }

    public boolean isHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(boolean hospitalization) {
        this.hospitalization = hospitalization;
    }

    public boolean isUrgency() {
        return urgency;
    }

    public void setUrgency(boolean urgency) {
        this.urgency = urgency;
    }

    public boolean isHomeCareService() {
        return homeCareService;
    }

    public void setHomeCareService(boolean homeCareService) {
        this.homeCareService = homeCareService;
    }

    public boolean isIntersectoral() {
        return intersectoral;
    }

    public void setIntersectoral(boolean intersectoral) {
        this.intersectoral = intersectoral;
    }

    public JSONObject asJson() {
        JSONObject json = new JSONObject();
        //, , , , , ,
        try {
            json.put(Constants.Accompany.IN_DAY.name(), inTheDay);
            json.put(Constants.Accompany.SPECIALIZED_SERVICE.name(), specializedService);
            json.put(Constants.Accompany.CAPS.name(), caps);
            json.put(Constants.Accompany.HOSPITALIZATION.name(), hospitalization);
            json.put(Constants.Accompany.URGENCY.name(), urgency);
            json.put(Constants.Accompany.HOME_CARE_SERVICE.name(), homeCareService);
            json.put(Constants.Accompany.INTER_SECTORAL.name(), intersectoral);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
