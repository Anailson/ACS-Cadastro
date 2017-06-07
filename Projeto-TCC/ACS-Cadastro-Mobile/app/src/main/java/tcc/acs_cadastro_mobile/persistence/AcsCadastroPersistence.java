package tcc.acs_cadastro_mobile.persistence;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AcsCadastroPersistence {

    public static void startDatabase(Context context){
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
        //Realm.deleteRealm(config);
    }
}
