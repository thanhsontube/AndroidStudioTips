package nt.son.androidstudiotips.location_map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseActivity;

public class HereMapActivity extends BaseActivity implements HereMapFragment.OnFragmentInteractionListener {

    private static final String TAG = "HereMapActivity";
    private SupportMapFragment supportMapFragment;
    private GoogleMap googleMap;
    //10.779786,106.698994
    public static double LAT = 10.779786;
    public static double LGN = 106.698994;
    public static LatLng POSITION = new LatLng(LAT, LGN);
    TextView txt;

    //10.777077, 106.697648


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_here_map);
        txt = (TextView) findViewById(R.id.txt_address);

        supportMapFragment = (SupportMapFragment) getSafeFragmentManager().findFragmentById(R.id.map);
        getMAP();


        FragmentTransaction ft = getSafeFragmentManager().beginTransaction();
        HereMapFragment f = HereMapFragment.newInstance("", "");
        ft.add(R.id.map_ll_map, f, "map");
        ft.commitAllowingStateLoss();
    }

    private void transition () {
//        TransitionManager
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
                animateMapTo(POSITION, 16f, true);
                break;
            case R.id.action_add_4:
                addMarker(googleMap.getCameraPosition().target);
                new ReverseGeoCodingTask(this, txt, googleMap.getCameraPosition().target).execute();
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

    private GoogleMap getMAP() {
        if (!isSafe()) {
            return null;
        }

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
                return null;
//                return getView(marker);
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


        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                Log.d(TAG, ">>>" + "setOnMapLoadedCallback");
            }
        });

        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

            }
        });

        return googleMap;


    }

    private View getView(Marker marker) {
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

    private void addMarker(LatLng position) {
        MarkerOptions markerOptions = new MarkerOptions().position(position)
                .title("target")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .snippet("Target here")
                .alpha(1f);
        Marker marker = googleMap.addMarker(markerOptions);
        marker.setDraggable(false);
    }

    private void add136NKKN() {
        MarkerOptions opts = new MarkerOptions().position(new LatLng(10.777077, 106.697648))
                .title("Home")
                .snippet("136 Nan Ky Khoi Nghia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(1f);

        Marker marker = googleMap.addMarker(opts);
        marker.setDraggable(true);
    }

    private synchronized void animateMapTo(LatLng pin, Float zoomLevel, boolean isAnimation) {
        GoogleMap googleMap = getMAP();
        if (!isSafe() || googleMap == null) {
            return;
        }
        final float camareZoomLevel;

        if (zoomLevel == null) {
            camareZoomLevel = googleMap.getCameraPosition().zoom;
        } else {
            camareZoomLevel = zoomLevel;
        }

        CameraPosition cameraPosition = new CameraPosition.Builder().target(pin)
                .tilt(45)
                .zoom(camareZoomLevel)
                .bearing(0)
                .build();

        googleMap.stopAnimation();
        if (isAnimation) {
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        } else {
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

    }

    class ReverseGeoCodingTask extends AsyncTask<Void, Void, String> {

        TextView txt;
        LatLng latLng;
        Context context;
        public ReverseGeoCodingTask (Context context, TextView txt, LatLng latLng) {
            this.txt = txt;
            this.latLng = latLng;
            this.context = context;
        }
        @Override
        protected String doInBackground(Void... params) {
            Geocoder geocoder = new Geocoder(context);
            double latitude = latLng.latitude;
            double longitude = latLng.longitude;

            List<Address> addresses = null;
            String addressText="";

            try {
                addresses = geocoder.getFromLocation(latitude, longitude,1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(addresses != null && addresses.size() > 0 ){
                Address address = addresses.get(0);

                addressText = String.format("%s, %s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getLocality(),
                        address.getCountryName());
            }

            return addressText;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txt.setText(s);
        }
    }
}
