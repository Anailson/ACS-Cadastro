package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;

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
}
