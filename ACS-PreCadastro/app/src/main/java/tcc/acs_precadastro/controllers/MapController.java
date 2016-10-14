package tcc.acs_precadastro.controllers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import tcc.acs_precadastro.R;
import tcc.acs_precadastro.interfaces.SearchLocationListener;
import tcc.acs_precadastro.utils.ManagerConection;
import tcc.acs_precadastro.utils.ManagerSnackbar;
import tcc.acs_precadastro.utils.ManagerToast;
import tcc.acs_precadastro.utils.SearchMyLocation;

/**
 * Created by anail on 12/10/2016.
 */
public class MapController {

    private View view;
    private Context context;
    private GoogleMap googleMap;
    private TextView txtLatitude, txtLongitude, txtAddress, txtCity;

    public MapController(View view){

        this.view = view;
        this.googleMap = null;
        this.context = view.getContext();
        this.txtLatitude = (TextView) view.findViewById(R.id.txtLatitude);
        this.txtLongitude = (TextView) view.findViewById(R.id.txtLongitude);
        this.txtAddress = (TextView) view.findViewById(R.id.txtAddress);
        this.txtCity = (TextView) view.findViewById(R.id.txtCity);
    }

    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public ButtonClickListener getButtonClickListener(){
        return new ButtonClickListener();
    }

    public MapClickListener getMapClickListener(){
        return new MapClickListener();
    }

    public class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            SearchMyLocation search = new SearchMyLocation(context);
            search.addMyLocationListener(new MyLocationListener());
        }
    }

    private class MapClickListener implements GoogleMap.OnMapClickListener {

        @Override
        public void onMapClick(LatLng latLng) {

            Geocoder geocoder = new Geocoder(context);
            try {

                List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                String locality = addressList.get(0).getLocality();
                String street = addressList.get(0).getAddressLine(0);

                ManagerToast.showLongToast(context, locality + ": " + street);

            } catch (IOException e) {
                e.printStackTrace();
            }

            googleMap.addMarker(new MarkerOptions().position(latLng));
            googleMap.addCircle(new CircleOptions().center(latLng).radius(200000));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    private class MyLocationListener extends AsyncTask<Location, Void, Location>
            implements SearchLocationListener{

        @Override
        public void getLocation(Location location) {
            execute(location);
        }

        @Override
        protected Location doInBackground(Location... locations) {
            return locations[0];
        }

        @Override
        protected void onPostExecute(Location location) {

            txtLatitude.setText(String.valueOf(location.getLatitude()));
            txtLongitude.setText(String.valueOf(location.getLongitude()));
            updateMap(location);

            if(ManagerConection.hasConnection(context)){

                updateForm(location);

            } else {

                ManagerToast.showLongToast(context, R.string.err_connection_offline);
            }
        }

        private void updateForm(Location location){

            Address address = getAddress(location);

            if(address != null){

                String completAddress = address.getAddressLine(0);
                String city = address.getLocality();

                txtAddress.setText(completAddress);

                if (txtCity != null) {
                    txtCity.setText(city);
                }

                ManagerToast.showLongToast(context, completAddress);

            } else {

                ManagerSnackbar.showIndefiniteSnackbar(view, R.string.err_connection_unknown, "Close", new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        ManagerSnackbar.getSnackbar().dismiss();
                    }
                });
            }
        }

        private void updateMap(Location location){

            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            googleMap.setMinZoomPreference((float) 4);
            googleMap.addMarker(new MarkerOptions().position(latLng));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }

        private Address getAddress(Location location){

            try {
                Geocoder geocoder = new Geocoder(context);
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                return addresses.get(0);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
