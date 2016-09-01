package com.avisosms.iuri.avisasms;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by andre on 01/07/2016.
 */
public class App extends Application{
   // private static App mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        Realm.deleteRealm(config);

    }

}
