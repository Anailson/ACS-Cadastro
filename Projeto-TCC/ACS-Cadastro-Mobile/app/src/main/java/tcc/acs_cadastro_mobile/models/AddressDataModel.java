package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.CityLocation;
import tcc.acs_cadastro_mobile.subModels.Phones;
import tcc.acs_cadastro_mobile.subModels.StreetLocation;

public class AddressDataModel extends RealmObject implements Serializable {

    private StreetLocation street;
    private CityLocation city;
    private Phones phones;

    public AddressDataModel() {
        this(new StreetLocation(), new CityLocation(), new Phones());
    }

    public AddressDataModel(StreetLocation street, CityLocation city, Phones phones) {
        this.street = street;
        this.city = city;
        this.phones = phones;
    }

    public StreetLocation getStreet() {
        return street;
    }

    public void setStreet(StreetLocation street) {
        this.street = street;
    }

    public CityLocation getCity() {
        return city;
    }

    public void setCity(CityLocation city) {
        this.city = city;
    }

    public Phones getPhones() {
        return phones;
    }

    public void setPhones(Phones phones) {
        this.phones = phones;
    }

    public String getPlaceType() {return getStreet().getType();}

    public String getPlaceName() {return getStreet().getName();}

    public int getNumber() {return getStreet().getNumber();}

    public String getComplement() { return getStreet().getComplement();}

    public String getNeighborhood() {return getCity().getNeighborhood();}

    public String getUf() {return getCity().getUf();}

    public String getCityName() {return getCity().getName();}

    public long getCep(){
        return getCity().getCep();
    }

    public String getPhoneHome() {return getPhones().getHome();}

    public String getPhoneReference() {return getPhones().getReference();}
}
