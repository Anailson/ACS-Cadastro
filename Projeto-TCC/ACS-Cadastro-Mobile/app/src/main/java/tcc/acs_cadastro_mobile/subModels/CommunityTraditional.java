package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class CommunityTraditional extends RealmObject implements Serializable {

    private boolean isCommunityTraditional;
    private String value;

    public CommunityTraditional() {
        this(false, "");
    }

    public CommunityTraditional(boolean isCommunityTraditional, String value) {
        this.isCommunityTraditional = isCommunityTraditional;
        this.value = value;
    }

    public boolean isCommunityTraditional() {
        return isCommunityTraditional;
    }

    public void setCommunityTraditional(boolean communityTraditional) {
        isCommunityTraditional = communityTraditional;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.COMMUNITY_TRADITIONAL.name(), isCommunityTraditional);
        json.put(Constants.Citizen.DESCRIPTION.name(), value);
        return json;
    }
}
