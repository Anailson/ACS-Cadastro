package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;

public class HealthAndGroup extends RealmObject implements Serializable {

    private boolean caregiver, communityGroup, healthPlan;

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
}