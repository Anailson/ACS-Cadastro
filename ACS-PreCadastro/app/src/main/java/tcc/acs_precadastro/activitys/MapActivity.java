package tcc.acs_precadastro.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MapActivity extends Fragment
        implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks {

    private GoogleMap googleMap;
    private GoogleApiClient apiClient;
    private MapController controller;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.activity_map, container, false);

        apiClient = new GoogleApiClient.Builder(this.getActivity())
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API).build();

        controller = new MapController(view);

        Button btnOk = (Button) view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(controller.getButtonClickListener());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        apiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
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
        ManagerToast.showShortToast(this.getContext(), "onConnected");

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
