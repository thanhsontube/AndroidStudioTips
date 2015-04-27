package nt.son.androidstudiotips.location_map;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseActivity;

public class HereMapActivity extends BaseActivity implements HereMapFragment.OnFragmentInteractionListener {

    private SupportMapFragment supportMapFragment;
    private GoogleMap googleMap;
    //10.779786,106.698994
    public static double LAT = 10.779786;
    public static double LGN = 106.698994;
    public static LatLng POSITION = new LatLng(LAT,LGN);

    //10.777077, 106.697648



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_here_map);

        supportMapFragment = (SupportMapFragment) getSafeFragmentManager().findFragmentById(R.id.map);
        getMAP();


        FragmentTransaction ft = getSafeFragmentManager().beginTransaction();
        HereMapFragment f = HereMapFragment.newInstance("", "");
        ft.add(R.id.map_ll_map, f, "map");
        ft.commitAllowingStateLoss();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_here_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_add_1:
                addMarkers();
                break;
            case R.id.action_add_2:
                add136NKKN();
                break;
            case R.id.action_add_3:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCamera();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void updateCamera() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        LatLng latLng;
        if (location != null) {
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
        } else {
            latLng = new LatLng(LAT, LGN);
        }
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16f);
        googleMap.moveCamera(cameraUpdate);
    }

    private void getMAP () {
        googleMap = supportMapFragment.getMap();

        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });

        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }
        });

        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return getView(marker);
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                marker.hideInfoWindow();

            }
        });



    }

    private View getView (Marker marker) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_map, null);
        return view;
    }

    private void addMarkers() {

        MarkerOptions markerOpt = new MarkerOptions().position(POSITION)
                .title("Notre Dame Cathedral")
                .icon(BitmapDescriptorFactory.defaultMarker())
                .snippet("Snippet")
                .alpha(0.6f);
        Marker marker = googleMap.addMarker(markerOpt);
        marker.setDraggable(true);
    }

    private void add136NKKN () {
        MarkerOptions opts = new MarkerOptions().position(new LatLng(10.777077, 106.697648))
                .title("Home")
                .snippet("136 Nan Ky Khoi Nghia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(1f);

        Marker marker = googleMap.addMarker(opts);
        marker.setDraggable(true);
    }
}
