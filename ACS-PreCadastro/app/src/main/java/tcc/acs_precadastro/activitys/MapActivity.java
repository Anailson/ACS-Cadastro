package tcc.acs_precadastro.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import tcc.acs_precadastro.R;
import tcc.acs_precadastro.controllers.MapController;
import tcc.acs_precadastro.utils.ManagerToast;

public class MapActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks {

    private GoogleMap googleMap;
    private GoogleApiClient apiClient;
    private MapController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        apiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API).build();

        controller = new MapController(this);

        Button btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(controller.getButtonClickListener());
    }

    @Override
    protected void onStart() {
        apiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        apiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng myLocation = new LatLng(0, 0);

        controller.setGoogleMap(googleMap);
        this.googleMap = googleMap;
        this.googleMap.setMinZoomPreference((float) 4);
        this.googleMap.setOnMapClickListener(controller.getMapClickListener());
        this.googleMap.addMarker(new MarkerOptions().position(myLocation).title("Lat 0, Long 0"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        ManagerToast.showShortToast(this, "onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
