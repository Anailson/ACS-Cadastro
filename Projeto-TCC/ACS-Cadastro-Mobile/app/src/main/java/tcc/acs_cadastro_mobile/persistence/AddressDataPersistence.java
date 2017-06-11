package tcc.acs_cadastro_mobile.persistence;


import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.subModels.CityLocation;
import tcc.acs_cadastro_mobile.subModels.Phones;
import tcc.acs_cadastro_mobile.subModels.StreetLocation;

public class AddressDataPersistence {

    private AddressDataPersistence() {}

    public static AddressDataModel getInstance(StreetLocation street, CityLocation city, Phones phones) {
        Realm realm = Realm.getDefaultInstance();
        return AddressDataModel.newInstance(realm, street, city, phones);
    }

    public static StreetLocation getStreetLocation(String place, String name, int number, String complement) {
        Realm realm = Realm.getDefaultInstance();
        return AddressDataModel.getStreetLocation(realm, place, name, number, complement);
    }

    public static CityLocation getCityLocation(String neighborhood, String uf, String city, long cep) {
        Realm realm = Realm.getDefaultInstance();
        return AddressDataModel.getCityLocation(realm, neighborhood, city, uf, cep);
    }

    public static Phones getPhones(String home, String reference) {
        Realm realm = Realm.getDefaultInstance();
        return AddressDataModel.getPhones(realm, home,  reference);
    }
}
