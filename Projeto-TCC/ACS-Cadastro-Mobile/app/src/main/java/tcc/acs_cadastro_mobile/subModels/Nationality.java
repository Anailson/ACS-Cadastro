package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

public class Nationality extends RealmObject  implements Serializable {
    private String nationality, nationBirth, uf, city;

    public Nationality() {
        this("", "", "", "");
    }

    public Nationality(String nationality, String nationBirth, String uf, String city) {
        this.nationality = nationality;
        this.nationBirth = nationBirth;
        this.uf = uf;
        this.city = city;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationBirth() {
        return nationBirth;
    }

    public void setNationBirth(String nationBirth) {
        this.nationBirth = nationBirth;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(Constants.Citizen.NATIONALITY.name(), nationality);
        json.put(Constants.Citizen.NATION_BIRTH.name(), nationBirth);
        json.put(Constants.Citizen.UF.name(), uf);
        json.put(Constants.Citizen.CITY.name(), city);
        return json;
    }

}
