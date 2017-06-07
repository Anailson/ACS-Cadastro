package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

public class AddressDataModel implements Serializable {

    private String placeName, complement, neighborhood, cep, phoneHome, phoneReference;
    private int number;
    private String placeType, uf, city;

    public AddressDataModel(String placeType, String placeName, int number, String complement,
                            String neighborhood, String uf, String city, String cep,
                            String phoneHome, String phoneReference) {
        this.placeType = placeType;
        this.placeName = placeName;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.city = city;
        this.cep = cep;
        this.phoneHome = phoneHome;
        this.phoneReference = phoneReference;
    }

    public String getPlaceType() {
        return placeType;
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getUf() {
        return uf;
    }

    public String getCity() {
        return city;
    }

    public String getCep() {
        return cep;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneReference() {
        return phoneReference;
    }
}
