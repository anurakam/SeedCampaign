package com.psu.seedcampaign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by อนุรักษ์ on 2/2/2558.
 */
 public class Land extends Activity {
    private ImageButton North, NorthEast, Central, East, South;
    private TextView SumPeople;
    String Count;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.land);
        SumPeople = (TextView) findViewById(R.id.peopleSum);

        /*North = (ImageButton) findViewById(R.id.NorthImgBtn);
        NorthEast = (ImageButton) findViewById(R.id.NorthEastImgBtn);
        Central = (ImageButton) findViewById(R.id.CentralImgBtn);
        East = (ImageButton) findViewById(R.id.EastImgBtn);
        South = (ImageButton) findViewById(R.id.SouthImgBtn);
        North.setOnClickListener(this);
        NorthEast.setOnClickListener(this);
        Central.setOnClickListener(this);
        East.setOnClickListener(this);
        South.setOnClickListener(this);*/

        GsonBuilder builder = new GsonBuilder();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://anurakam.somee.com/")
                .setConverter(new GsonConverter(builder.create()))
                .build();
        ApiService retrofit = restAdapter.create(ApiService.class);
        retrofit.getCountPeopleByMethodWithCallback(new Callback<CountPeopleModel>() {


            @Override
            public void success(CountPeopleModel countPeopleModel, Response response) {
                CountPeopleModel CountPeople = countPeopleModel;
                Count = CountPeople.getCountPeople();
                SumPeople.setText("ผู้เข้าร่วมโครงการ : " + Count + " คน");

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Land.this, "ไม่สามารถเข้าถึงข้อมูลได้", Toast.LENGTH_SHORT).show();
            }
        });


    }

  }


    /*public void onClick(View v) {
        switch (v.getId()) {

            case R.id.NorthImgBtn:
                Intent intent = new Intent(Land.this, North_Knowlede.class);
                startActivity(intent);
                break;

            case R.id.NorthEastImgBtn:
                intent = new Intent(Land.this, NorthEast_Knowlede.class);
                startActivity(intent);
                break;

            case R.id.CentralImgBtn:
                intent = new Intent(Land.this, Central_Knowlede.class);
                startActivity(intent);
                break;

            case R.id.EastImgBtn:
                intent = new Intent(Land.this, East_Knowlede.class);
                startActivity(intent);
                break;

            case R.id.SouthImgBtn:
                intent = new Intent(Land.this, South_Knowlede.class);
                startActivity(intent);
                break;

        }
}*/