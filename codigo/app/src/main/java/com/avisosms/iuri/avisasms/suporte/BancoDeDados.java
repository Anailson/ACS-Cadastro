package com.avisosms.iuri.avisasms.suporte;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by iuri on 6/7/2016.
 */
public class BancoDeDados {

    public static void ConfigurarBanco(Context context)  {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);
    }

}
