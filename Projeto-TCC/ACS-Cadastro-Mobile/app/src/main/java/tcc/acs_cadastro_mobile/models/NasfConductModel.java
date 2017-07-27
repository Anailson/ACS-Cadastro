package tcc.acs_cadastro_mobile.models;

import android.widget.RadioGroup;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;
import tcc.acs_cadastro_mobile.subModels.Conduct;
import tcc.acs_cadastro_mobile.subModels.Forwarding;
import tcc.acs_cadastro_mobile.subModels.NASF;

import java.io.Serializable;

public class NasfConductModel extends RealmObject implements Serializable {

    private boolean observation;
    private NASF nasf;
    private Conduct conduct;
    private Forwarding forwarding;

    public NasfConductModel() {
        this(false, new NASF(), new Conduct(), new Forwarding());
    }

    public NasfConductModel(boolean observation, NASF nasf, Conduct conduct, Forwarding forwarding) {
        this.observation = observation;
        this.nasf = nasf;
        this.conduct = conduct;
        this.forwarding = forwarding;
    }

    public boolean[] getNasfs() {
        return getNasf().getValues();
    }

    public boolean[] getConducts() {
        return getConduct().getValues();
    }

    public boolean[] getForwardings() {
        return getForwarding().getValues();
    }

    public boolean isObservation(){
        return observation;
    }

    public void setObservation(boolean observation) {
        this.observation = observation;
    }

    public NASF getNasf() {
        return nasf;
    }

    public void setNasf(NASF nasf) {
        this.nasf = nasf;
    }

    public Conduct getConduct() {
        return conduct;
    }

    public void setConduct(Conduct conduct) {
        this.conduct = conduct;
    }

    public Forwarding getForwarding() {
        return forwarding;
    }

    public void setForwarding(Forwarding forwarding) {
        this.forwarding = forwarding;
    }

    public JSONObject asJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(Constants.Accompany.NASF_CONDUCT.name(), observation);
            json.put(Constants.Accompany.NASF.name(), nasf.asJson());
            json.put(Constants.Accompany.CONDUCT.name(), conduct.asJson());
            json.put(Constants.Accompany.FORWARDING.name(), forwarding.asJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
