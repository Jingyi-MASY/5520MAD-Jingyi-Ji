package edu.neu.madcourse.numad21fa_jingyiji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Location extends AppCompatActivity implements LocationListener {
    protected double latitude, longitude;
    TextView txtLa, txtLo;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        txtLa = findViewById(R.id.textLatitude);
        txtLo = findViewById(R.id.textLongitude);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(Location.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Location.this, new String[]
                    {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        android.location.Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        txtLa.setText(String.format("%.2f", latitude));
        txtLo.setText(String.format("%.2f", longitude));

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(@NonNull android.location.Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        txtLa.setText(String.format("%.2f", latitude));
        txtLo.setText(String.format("%.2f", longitude));

        //stop update
        //locationManager.removeUpdates(this);
    }




}