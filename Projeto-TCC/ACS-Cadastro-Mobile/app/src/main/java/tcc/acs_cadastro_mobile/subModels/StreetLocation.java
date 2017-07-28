package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import java.io.Serializable;

public class StreetLocation extends RealmObject implements Serializable {
    private String type, name, complement;
    private int number;

    public StreetLocation() {
        this("", "", 0, "");
    }

    public StreetLocation(String type, String name, int number, String complement) {
        this.type = type;
        this.name = name;
        this.complement = complement;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    //String type, String name, int number, String complement
    public JSONObject asJson() {
        JSONObject json = new JSONObject();
        try{
            json.put(Constants.Residence.TYPE.name(), type);
            json.put(Constants.Residence.NAME.name(), name);
            json.put(Constants.Residence.NUMBER.name(), number);
            json.put(Constants.Residence.COMPLEMENT.name(), complement);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}

