package com.psu.seedcampaign;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;

import java.util.ArrayList;

import app.akexorcist.gdaplibrary.GooglePlaceSearch;
import app.akexorcist.gdaplibrary.GooglePlaceSearch.OnPlaceResponseListener;
import app.akexorcist.gdaplibrary.PlaceType;

//import android.content.Context;
//import android.location.Location;
//import android.location.LocationManager;
//import android.util.Log;

public class Location extends Activity {

    final String ApiKey = "AIzaSyCDengTMy7_x2AY_eTG3Rsz2PefAK3YXWg"; //"AIzaSyDQ6mA6vUHD3cMNqDoblES6q3dFHzNLqs4";

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        android.location.Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();



        textStatus = (TextView)findViewById(R.id.textStatus);

        listView = (ListView)findViewById(R.id.listView);
        //textView = (TextView)findViewById(R.id.textView);
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
        //gp.getTextSearch(keyword, type, false, language);
        //gp.getRadarSearch(latitude, longitude, radius, type, language, false, keyword);
    }
}
