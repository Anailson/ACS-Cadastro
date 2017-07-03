package tcc.acs_cadastro_mobile.subModels;

import java.io.Serializable;

import io.realm.RealmObject;


public class CityLocation extends RealmObject implements Serializable {

    private String neighborhood, name, uf;
    private long cep;

    public CityLocation() {
        this("", "", "", 0);
    }

    public CityLocation(String neighborhood, String uf, String name, long cep) {
        this.neighborhood = neighborhood;
        this.name = name;
        this.uf = uf;
        this.cep = cep;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public long getCep() {
        return cep;
    }

    public void setCep(long cep) {
        this.cep = cep;
    }
}
