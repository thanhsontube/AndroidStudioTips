package nt.son.androidstudiotips.location_map;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Sonnt on 4/24/15.
 */
public class LocationUtils implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
LocationListener, com.google.android.gms.location.LocationListener{
    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    //

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    //

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    //

}
