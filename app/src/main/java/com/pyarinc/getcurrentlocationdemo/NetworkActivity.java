package com.pyarinc.getcurrentlocationdemo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
/* Logic taken from swiftBoy where https://stackoverflow.com/questions/1513485/how-do-i-get-the-current-gps-location-programmatically-in-android*/

public class NetworkActivity extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationManager locationManager;
    private TextView tvLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLocation= findViewById(R.id.textview);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            Toast.makeText(NetworkActivity.this, "location came", Toast.LENGTH_SHORT).show();
                            Log.d("SuccessLocation", "onSuccess: "+location.getLatitude());
                        }
                    }
                });

        LocationListener locationListener = new MyLocationListener();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
    }


    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            Log.d("SuccessLocation", "onLocationChanged: "+loc.getLatitude());
            tvLocation.setText("Lccation Networkd::"+loc.getLatitude()+" ,"+loc.getLongitude());

        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("SuccessLocation", "onProviderDisabled: ");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("SuccessLocation", "onProviderEnabled: ");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("SuccessLocation", "onStatusChanged: ");
        }
    }

}
