package tcc.acs_cadastro_mobile.gps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import tcc.acs_cadastro_mobile.views.CalendarActivity;

@SuppressWarnings("MissingPermission")
public class DefineLocation extends AsyncTask<Void, Void, Address> {

    private Context context;
    private LocationManager locManager;
    private ProviderLocationListener gpsListener, networkListener;
    private ProgressDialog progressDialog;

    public DefineLocation(Context context) {
        this.context = context;
        this.gpsListener = new ProviderLocationListener(LocationManager.GPS_PROVIDER);
        this.networkListener = new ProviderLocationListener(LocationManager.NETWORK_PROVIDER);
        this.locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.progressDialog = new ProgressDialog(context);
    }

    public void searchLocation() {

        gpsListener.requestUpdatePosition();
        networkListener.requestUpdatePosition();
    }

    public void execute(){
        super.execute();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog.setCancelable(true);
        progressDialog.setTitle("Atualizando as informações...");
        progressDialog.show();
    }

    @Override
    protected Address doInBackground(Void... voids) {

        locManager.removeUpdates(gpsListener);
        locManager.removeUpdates(networkListener);
        gpsListener.defineLocation(locManager);
        networkListener.defineLocation(locManager);

        Location location = gpsListener.compareBetter(networkListener.getLocation());

        try {
            if(ManagerConection.hasConnection(context)) {
                Geocoder geocoder = new Geocoder(context);
                Address address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
                //Address address = geocoder.getFromLocationName("Rua José Mesquita da Silveira 748, Itabaiana SE", 1).get(0);
                Log.e("doInBackground", address.getPostalCode() + " = " + address.getLatitude() + ", " + address.getLongitude());
                return address;
            }

        } catch (IOException e) {
            Log.e("doInBackground", "Null");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Address address) {
        super.onPostExecute(address);
        progressDialog.cancel();
        //TODO: add Activity Dialog for information...
        context.startActivity(new Intent(context, CalendarActivity.class));
    }






















    private class ProviderLocationListener implements LocationListener {

        private String provider;
        private boolean enable;
        private Location location;

        public ProviderLocationListener(String provider) {
            this.provider = provider;
        }

        public void requestUpdatePosition() {

            enable = locManager.isProviderEnabled(provider);
            Log.e("requestUpdatePosition", "provider: " + provider + "; enable: " + enable);
            if (enable) {
                locManager.requestSingleUpdate(provider, this, null);
                locManager.requestLocationUpdates(provider, 1, 1, this);
            }
        }

        public Location getLocation() {
            return location;
        }

        public void defineLocation(LocationManager manager) {
            if (enable) {
                location = manager.getLastKnownLocation(provider);
            }
        }

        public Location compareBetter(Location anotherLocation) {

            Log.e("doInBackground", "Location null: " + (location == null) + " Another null: " + (anotherLocation == null));

            if (location != null && anotherLocation != null) {

                if (anotherLocation.getTime() > location.getTime()) {
                    return anotherLocation;
                }
                return location;

            } else if (anotherLocation != null) {

                return anotherLocation;

            } else if (location != null) {

                return location;
            }
            return null;
        }

        @Override
        public void onLocationChanged(Location location) {
            this.location = location;
            locManager.removeUpdates(gpsListener);
            locManager.removeUpdates(networkListener);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    }
}