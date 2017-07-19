package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class HealthAndGroup extends RealmObject implements Serializable {

    private boolean caregiver, communityGroup, healthPlan;

    public HealthAndGroup() {
        this(false, false, false);
    }

    public HealthAndGroup(boolean caregiver, boolean communityGroup, boolean healthPlan) {
        this.caregiver = caregiver;
        this.communityGroup = communityGroup;
        this.healthPlan = healthPlan;
    }

    public boolean isCaregiver() {
        return caregiver;
    }

    public void setCaregiver(boolean caregiver) {
        this.caregiver = caregiver;
    }

    public boolean isCommunityGroup() {
        return communityGroup;
    }

    public void setCommunityGroup(boolean communityGroup) {
        this.communityGroup = communityGroup;
    }

    public boolean isHealthPlan() {
        return healthPlan;
    }

    public void setHealthPlan(boolean healthPlan) {
        this.healthPlan = healthPlan;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.CAREGIVER.name(), caregiver);
        json.put(Constants.Citizen.COMMUNITY_GROUP.name(), communityGroup);
        json.put(Constants.Citizen.HEALTH_PLAN.name(), healthPlan);
        return json;
    }
}
