package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;

public class Mother extends RealmObject  implements Serializable {
    private boolean known;
    private String name;

    public boolean isKnown() {return known;}

    public void setKnown(boolean known) {this.known = known;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}