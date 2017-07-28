package tcc.acs_cadastro_mobile.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.persistence.AcsRecordPersistence;
import tcc.acs_cadastro_mobile.subModels.ActiveSearch;
import tcc.acs_cadastro_mobile.subModels.AnotherReasons;
import tcc.acs_cadastro_mobile.subModels.Following;

public class ReasonsVisitModel extends RealmObject implements Serializable{

    private ActiveSearch active;
    private Following following;
    private AnotherReasons another;
    private String result;

    public ReasonsVisitModel() {
        this(new ActiveSearch(), new Following(), new AnotherReasons(), AcsRecordPersistence.DEFAULT_STR);
    }

    public ReasonsVisitModel(ActiveSearch active, Following following, AnotherReasons another, String result) {
        this.active = active;
        this.following = following;
        this.another = another;
        this.result = result;
    }

    public boolean[] getActiveSearchs(){
        return getActive().getValues();
    }

    public boolean[] getFollowings(){
        return getFollowing().getValues();
    }

    public boolean[] getAnotherReasons(){
        return getAnother().getValues();
    }

    public ActiveSearch getActive() {
        return active;
    }

    public void setActive(ActiveSearch active) {
        this.active = active;
    }

    public Following getFollowing() {
        return following;
    }

    public void setFollowing(Following following) {
        this.following = following;
    }

    public AnotherReasons getAnother() {
        return another;
    }

    public void setAnother(AnotherReasons another) {
        this.another = another;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public JSONObject asJson(){
        JSONObject json = new JSONObject();
        try {
            json.put(Constants.Visit.RESULT.name(),result);
            json.put(Constants.Visit.ACTIVE_SEARCH.name(), active.asJson());
            json.put(Constants.Visit.FOLLOWING.name(), following.asJson());
            json.put(Constants.Visit.ANOTHER_REASONS.name(), another.asJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
