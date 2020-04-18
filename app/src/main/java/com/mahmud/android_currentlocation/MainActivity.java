package com.mahmud.android_currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //variables declaration
    TextView textView;
    FusedLocationProviderClient fusedLocationProviderClient;
    Address currentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variables initialization
        textView = findViewById(R.id.textView);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


    }

    public void onClick(View v){
        getCurrentLocation();
    }

    private void getLocation() {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                //initialize location
                Location location = task.getResult();
                if(location != null){

                    try {
                        //initialize geoCoder
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                        //initialize address list
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                        currentAddress = addresses.get(0);
                        progressDialog.cancel();

                        /*currentAddress.getLatitude();
                        currentAddress.getLongitude();
                        currentAddress.getAddressLine(0);//location name
                        currentAddress.getAdminArea();//division
                        currentAddress.getSubAdminArea();//district
                        currentAddress.getPostalCode();//postalCode
                        currentAddress.getLocale();
                        currentAddress.getLocality();//may be upozilla
                        currentAddress.getSubLocality();//union/upozilla*/


                        textView.setText(Html.fromHtml(
                                "<font color='#6200EE'><br><b>getLatitude :</b> <br></font>"+currentAddress.getLatitude()+
                                "<font color='#6200EE'><br><b>getLongitude :</b> <br></font>"+currentAddress.getLongitude()+
                                "<font color='#6200EE'><br><b>getAddressLine :</b> <br></font>"+currentAddress.getAddressLine(0)+
                                "<font color='#6200EE'><br><b>getAdminArea :</b> <br></font>"+currentAddress.getAdminArea()+
                                "<font color='#6200EE'><br><b>getSubAdminArea :</b> <br></font>"+currentAddress.getSubAdminArea()+
                                "<font color='#6200EE'><br><b>getPostalCode :</b> <br></font>"+currentAddress.getPostalCode()+
                                "<font color='#6200EE'><br><b>getLocale :</b> <br></font>"+currentAddress.getLocale()+
                                "<font color='#6200EE'><br><b>getLocality :</b> <br></font>"+currentAddress.getLocality()+
                                "<font color='#6200EE'><br><b>getSubLocality :</b> <br></font>"+currentAddress.getSubLocality()
                        ));

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("error",e.getMessage());
                        progressDialog.cancel();
                    }
                }
            }
        });
    }

    public void getCurrentLocation() {
        //check permission
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            //when permission granted
            getLocation();
        }else{
            //when permission denied
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},111);
        }
    }
}
