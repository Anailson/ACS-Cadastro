package tcc.acs_cadastro_mobile.models;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;
import tcc.acs_cadastro_mobile.subModels.CityLocation;
import tcc.acs_cadastro_mobile.subModels.Phones;
import tcc.acs_cadastro_mobile.subModels.StreetLocation;

public class AddressDataModel extends RealmObject implements Serializable {

    private StreetLocation street;
    private CityLocation city;
    private Phones phones;

    public static AddressDataModel newInstance(Realm realm, StreetLocation street, CityLocation city, Phones phones) {
        realm.beginTransaction();
        AddressDataModel object = realm.createObject(AddressDataModel.class);
        object.setStreet(street);
        object.setCity(city);
        object.setPhones(phones);
        realm.commitTransaction();
        return object;
    }

    public static StreetLocation getStreetLocation(Realm realm, String type, String name, int number, String complement){
        realm.beginTransaction();
        StreetLocation object = realm.createObject(StreetLocation.class);
        object.setType(type);
        object.setName(name);
        object.setNumber(number);
        object.setComplement(complement);
        realm.commitTransaction();
        return object;
    }

    public static CityLocation getCityLocation(Realm realm, String neighborhood, String city, String uf, long cep){
        realm.beginTransaction();
        CityLocation object = realm.createObject(CityLocation.class);
        object.setNeighborhood(neighborhood);
        object.setName(city);
        object.setUf(uf);
        object.setCep(cep);
        realm.commitTransaction();
        return object;
    }

    public static Phones getPhones(Realm realm, String home, String reference){
        realm.beginTransaction();
        Phones object = realm.createObject(Phones.class);
        object.setHome(home);
        object.setReference(reference);
        realm.commitTransaction();
        return object;
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
