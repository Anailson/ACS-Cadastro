package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.ActiveSearch;
import tcc.acs_cadastro_mobile.subModels.AnotherReasons;
import tcc.acs_cadastro_mobile.subModels.Following;

public class ReasonsVisitModel extends RealmObject implements Serializable{

    private ActiveSearch active;
    private Following following;
    private AnotherReasons another;

    public ReasonsVisitModel() {
        this(new ActiveSearch(), new Following(), new AnotherReasons());
    }

    public ReasonsVisitModel(ActiveSearch active, Following following, AnotherReasons another) {
        this.active = active;
        this.following = following;
        this.another = another;
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
}
