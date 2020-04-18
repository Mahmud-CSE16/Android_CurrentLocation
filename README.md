# Get Current Location

It's a simple app to get current address(Latitude,Longitude,address,admin area, sub admin area, locality, sub locality, postal code etc)

### Procedure to get current location

#### Add Dependencies
```java
implementation 'com.google.android.gms:play-services-location:17.0.0'
```
#### Add Permissions
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
