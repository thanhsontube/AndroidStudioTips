package nt.son.androidstudiotips.location_map;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import nt.son.androidstudiotips.R;
import nt.son.androidstudiotips.base.BaseActivity;

public class HereMapActivity extends BaseActivity implements HereMapFragment.OnFragmentInteractionListener{

    private SupportMapFragment supportMapFragment;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_here_map);

        supportMapFragment = (SupportMapFragment) getSafeFragmentManager().findFragmentById(R.id.map);
        googleMap = supportMapFragment.getMap();

        googleMap.setMyLocationEnabled(true);

        FragmentTransaction ft = getSafeFragmentManager().beginTransaction();
        HereMapFragment f = HereMapFragment.newInstance("","");
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
           latLng  = new LatLng(location.getLatitude(), location.getLongitude());
        } else {
            latLng = new LatLng(37.4218,  -122.0840);
        }
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 4f);
        googleMap.moveCamera(cameraUpdate);
    }
}
