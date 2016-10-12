package tcc.acs_precadastro.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import tcc.acs_precadastro.interfaces.SearchLocationListener;

/**
 * Created by anail on 10/10/2016.
 */
@SuppressWarnings("MissingPermission")
public class SearchMyLocation {

    private Context context;
    private LocationManager locManager;
    private SearchLocationListener listener;
    private boolean gpsEnabled, networkEnabled;
    private LocationListener locationListenerGps, locationListenerNetwork;

    public SearchMyLocation(Context context) {

        this.context = context;
        this.locationListenerGps = getLocationListenerGps();
        this.locationListenerNetwork = getLocationListenerNetwork();
        this.locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.gpsEnabled = this.networkEnabled = false;
    }

    public void addMyLocationListener(SearchLocationListener listener) {

        this.listener = listener;

        try {
            gpsEnabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            Log.e("GPS_PROVIDER", "GPS_PROVIDER_EXCEPTION");
        }

        try {
            networkEnabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            Log.e("NETWORK_PROVIDER", "NETWORK_PROVIDER_EXCEPTION");
        }

        if (!gpsEnabled && !networkEnabled){
            return;
        }
        if (gpsEnabled) {
            //locManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListenerGps, null);
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGps);
        }

        if (networkEnabled) {
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerNetwork);
        }

        new SearchLocation().execute();
    }

    private LocationListener getLocationListenerGps() {

        return new LocationListener() {

            public void onLocationChanged(Location location) {

                listener.getLocation(location);
                locManager.removeUpdates(this);
                locManager.removeUpdates(locationListenerNetwork);
            }

            public void onProviderDisabled(String provider) {}

            public void onProviderEnabled(String provider) {}

            public void onStatusChanged(String provider, int status, Bundle extras) {}
        };
    }

    private LocationListener getLocationListenerNetwork() {

        return new LocationListener() {

            public void onLocationChanged(Location location) {

                listener.getLocation(location);
                locManager.removeUpdates(this);
                locManager.removeUpdates(locationListenerGps);
            }

            public void onProviderDisabled(String provider) {}

            public void onProviderEnabled(String provider) {}

            public void onStatusChanged(String provider, int status, Bundle extras) {}
        };
    }

    private class SearchLocation extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            locManager.removeUpdates(locationListenerGps);
            locManager.removeUpdates(locationListenerNetwork);

            Location locationByNetwork = null, locationByGps = null;
            if (gpsEnabled) {
                locationByGps = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

            if (networkEnabled) {
                locationByNetwork = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (locationByGps != null && locationByNetwork != null) {

                if (locationByGps.getTime() > locationByNetwork.getTime()) {
                    listener.getLocation(locationByGps);

                } else {
                    listener.getLocation(locationByNetwork);
                }

            } else if (locationByGps != null) {

                listener.getLocation(locationByGps);

            } else if (locationByNetwork != null) {

                listener.getLocation(locationByNetwork);

            } else {

                listener.getLocation(null);
            }
            return null;
        }
    }
}