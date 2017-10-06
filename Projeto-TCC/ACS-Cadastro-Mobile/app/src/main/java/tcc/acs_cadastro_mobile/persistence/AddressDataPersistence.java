package tcc.acs_cadastro_mobile.persistence;


import io.realm.Realm;
import tcc.acs_cadastro_mobile.models.AddressDataModel;
import tcc.acs_cadastro_mobile.subModels.CityLocation;
import tcc.acs_cadastro_mobile.subModels.Phones;
import tcc.acs_cadastro_mobile.subModels.StreetLocation;

public class AddressDataPersistence {

    private AddressDataPersistence() {}

    public static AddressDataModel get(StreetLocation street, CityLocation city, Phones phones) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        AddressDataModel object = realm.copyToRealm(new AddressDataModel(street, city, phones));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static StreetLocation get(String place, String name, int number, String complement) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        StreetLocation object = realm.copyToRealm(new StreetLocation(place, name, number, complement));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static CityLocation get(String neighborhood, String uf, String city, long cep) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CityLocation object = realm.copyToRealm(new CityLocation(neighborhood, uf, city, cep));
        realm.commitTransaction();
        realm.close();
        return object;
    }

    public static Phones get(String home, String reference) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Phones object = realm.copyToRealm(new Phones(home, reference));
        realm.commitTransaction();
        realm.close();
        return object;
    }
}
