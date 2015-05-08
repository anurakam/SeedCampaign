package com.psu.seedcampaign;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;

import java.util.ArrayList;

import app.akexorcist.gdaplibrary.GooglePlaceSearch;
import app.akexorcist.gdaplibrary.GooglePlaceSearch.OnPlaceResponseListener;
import app.akexorcist.gdaplibrary.PlaceType;

//import android.content.Context;
//import android.location.Location;
//import android.location.LocationManager;
//import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
public class Location extends Activity implements ConnectionCallbacks,
        OnConnectionFailedListener, LocationListener {

    final String ApiKey =  "AIzaSyDQ6mA6vUHD3cMNqDoblES6q3dFHzNLqs4";//"AIzaSyCDengTMy7_x2AY_eTG3Rsz2PefAK3YXWg";

    double latitude ;//=  7.89539;
    double longitude ;//= 98.3535;
    int radius = 1000;
    String type = PlaceType.FOOD;
    String language = "en";
    String keyword = "japan restaurant food";

    TextView textStatus;
    ListView listView;
    String filePath = null;
    String username = null;

    GooglePlaceSearch gp;

    private android.location.Location mLastLocation;
    private boolean mRequestingLocationUpdates = false;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 1000; // 10 meters

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();

            createLocationRequest();
        }


        //togglePeriodicLocationUpdates();

        displayLocation();

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        android.location.Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
       // longitude = location.getLongitude();
       // latitude = location.getLatitude();



        textStatus = (TextView)findViewById(R.id.textStatus);

        listView = (ListView)findViewById(R.id.listView);
        //textView = (TextView)findViewById(R.id.textView);

        //gp.getTextSearch(keyword, type, false, language);
        //gp.getRadarSearch(latitude, longitude, radius, type, language, false, keyword);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkPlayServices();

        // Resuming the periodic location updates
        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    /**
     * Method to display the location on UI
     * */
    private void displayLocation() {

        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            gp = new GooglePlaceSearch(ApiKey);
            gp.setOnPlaceResponseListener(new OnPlaceResponseListener() {
                public void onResponse(String status, ArrayList<ContentValues> arr_data,
                                       Document doc) {
                    textStatus.setText("ระบุตำแหน่ง");

                    if(status.equals(GooglePlaceSearch.STATUS_OK)) {
                        final ArrayList<String> array = new ArrayList<String>();
                        String nameLocale;
                        for(int i = 0 ; i < arr_data.size() ; i++) {
                            array.add(arr_data.get(i).getAsString(GooglePlaceSearch.PLACE_NAME)/* + "\n"
                                + "Address : " + arr_data.get(i).getAsString(GooglePlaceSearch.PLACE_ADDRESS) + "\n"
                                + "Latitude : " + arr_data.get(i).getAsString(GooglePlaceSearch.PLACE_LATITUDE) + "\n"
                                + "Longitude : " + arr_data.get(i).getAsString(GooglePlaceSearch.PLACE_LONGITUDE) + "\n"
                                + "Phone Number : " + arr_data.get(i).getAsString(GooglePlaceSearch.PLACE_PHONENUMBER)*/);

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Location.this
                                , R.layout.listview_text, array);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String[] mStringArray = new String[array.size()];
                                mStringArray = array.toArray(mStringArray);

                                // for(int i = 0; i < mStringArray.length ; i++){
                                // MainActivity mainActivity = new MainActivity();
                                //textView.setText(mStringArray[position]);
                                //finish();
                                //Log.d("string is", (mStringArray[position]));
                                //}
                                filePath = getIntent().getStringExtra("filePath");
                                username = getIntent().getStringExtra("username");

                                Intent i = new Intent(Location.this,MainActivity.class);
                                i.putExtra("location",mStringArray[position]);
                                i.putExtra("filePath",filePath);
                                i.putExtra("username",username);
                                startActivity(i);
                                finish();
                            }
                        });

                    }
                }
            });

            gp.getNearby(latitude, longitude, radius);

        } else {


        }
    }

    /**
     * Method to toggle periodic location updates
     * */
    private void togglePeriodicLocationUpdates() {
        if (!mRequestingLocationUpdates) {
            // Changing the button text


            mRequestingLocationUpdates = true;

            // Starting the location updates
            startLocationUpdates();



        } else {
            // Changing the button text


            mRequestingLocationUpdates = false;

            // Stopping the location updates
            stopLocationUpdates();


        }
    }

    /**
     * Creating google api client object
     * */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Creating location request object
     * */
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Starting the location updates
     * */
    protected void startLocationUpdates() {

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }

    /**
     * Stopping location updates
     */
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    /**
     * Google api callback methods
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {

    }

    @Override
    public void onConnected(Bundle arg0) {

        // Once connected with google api, get the location
        displayLocation();

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        // Assign the new location
        mLastLocation = location;

        Toast.makeText(getApplicationContext(), "กรุณารอสักครู่",
                Toast.LENGTH_SHORT).show();

        // Displaying the new location on UI
        displayLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        togglePeriodicLocationUpdates();
        return true;

    }
}
