# Get Current Location

It's a simple app to get current address(Latitude,Longitude,address,admin area, sub admin area, locality, sub locality, postal code etc)

### Procedure to get current location

#### Add Dependencies at build.gradle(Module: app)
```java
implementation 'com.google.android.gms:play-services-location:17.0.0'
```
#### Add Permissions at AndroidManifest.xml
```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```
#### Check Permissions
```java
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
```
#### Get Location
```java
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
```

## Thanks for Read
