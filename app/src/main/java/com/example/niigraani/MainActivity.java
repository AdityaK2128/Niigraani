package com.example.niigraani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    private GoogleMap mMap;
    private GeofencingClient geofencingClient ;
    private int FINE_LOCATION_ACCESS_REQUEST_CODE  = 10001 ;

    private Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        geofencingClient = LocationServices.getGeofencingClient(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });


    }

    public void openActivity2(){
        Intent intent = new Intent(this , MapsActivity.class);
        startActivity(intent);
    }


    private void enableUserLocation()
    {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);

        }else{
            // Asking fot the Permission
            if(ActivityCompat.shouldShowRequestPermissionRationale(this , android.Manifest.permission.ACCESS_FINE_LOCATION)){
                // We need to show the user a dialog for displaying why the permission

                ActivityCompat.requestPermissions(this , new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);

            }else{

                ActivityCompat.requestPermissions(this , new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);

            }

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        if(requestCode == FINE_LOCATION_ACCESS_REQUEST_CODE){
            if(grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // We have the permission now
                mMap.setMyLocationEnabled(true);
            }else{
                // We Do not have the permission now

            }
        }
    }
}