package tcc.acs_cadastro_mobile.gps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ManagerConection {

    private ManagerConection(){}

    public static  boolean hasConnection(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
