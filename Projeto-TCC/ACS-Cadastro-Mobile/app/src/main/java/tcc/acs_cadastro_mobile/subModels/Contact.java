package tcc.acs_cadastro_mobile.subModels;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.Constants;

import static tcc.acs_cadastro_mobile.Constants.Citizen.EMAIL;
import static tcc.acs_cadastro_mobile.Constants.Citizen.PHONE;

public class Contact extends RealmObject  implements Serializable {
    private String phone, email;

    public Contact() {
        this("", "");
    }

    public Contact(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JSONObject asJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(PHONE.name(), phone);
        json.put(EMAIL.name(), email);
        return json;
    }
}
