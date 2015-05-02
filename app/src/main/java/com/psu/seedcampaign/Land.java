package com.psu.seedcampaign;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

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
    private TextView SumPeople,NorthSum,NorthEastSum,EastSum,CentralSum,SouthSum,AllTreeSum;
    String Count,SumN,SumNE,SumE,SumC,SumS,SumA;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.land);
        SumPeople = (TextView) findViewById(R.id.peopleSum);
        NorthSum = (TextView) findViewById(R.id.northSum);
        NorthEastSum  = (TextView) findViewById(R.id.north_eastSum);
        EastSum  = (TextView) findViewById(R.id.eastSum);
        CentralSum  = (TextView) findViewById(R.id.centerSum);
        SouthSum  = (TextView) findViewById(R.id.southSum);
        AllTreeSum = (TextView) findViewById(R.id.treeSum);
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
        //ApiService retrofit2 = restAdapter.create(ApiService.class);
        retrofit.getSumNorthByMethodWithCallback(new Callback<SumNorthModel>() {
            @Override
            public void success(SumNorthModel sumNorthModel, Response response) {
                SumNorthModel SumNorth = sumNorthModel;
                SumN = SumNorth.getSumNorth();

                NorthSum.setText(SumN + " ต้น");
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Land.this, "ไม่สามารถเข้าถึงข้อมูลจำนวนต้นไมภาคเหนือได้", Toast.LENGTH_SHORT).show();
            }
        }) ;
        retrofit.getSumNorthEastByMethodWithCallback(new Callback<SumNorthEastModel>() {
            @Override
            public void success(SumNorthEastModel sumNorthEastModel, Response response) {
                SumNorthEastModel SumNorthEast = sumNorthEastModel;
                SumNE = SumNorthEast.getSumNorthEast();
                NorthEastSum.setText(SumNE + " ต้น");

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Land.this, "ไม่สามารถเข้าถึงข้อมูลจำนวนต้นไมภาคตะวันออกเฉียงเหนือได้", Toast.LENGTH_SHORT).show();
            }
        });
        retrofit.getSumCentralByMethodWithCallback(new Callback<SumCentralModel>() {
            @Override
            public void success(SumCentralModel sumCentralModel, Response response) {
                SumCentralModel SumCentral = sumCentralModel;
                SumC = SumCentral.getSumCentral();
                CentralSum.setText(SumC + " ต้น");
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Land.this, "ไม่สามารถเข้าถึงข้อมูลจำนวนต้นไมภาคกลางได้", Toast.LENGTH_SHORT).show();
            }
        });
        retrofit.getSumEastByMethodWithCallback(new Callback<SumEastModel>() {
            @Override
            public void success(SumEastModel sumEastModel, Response response) {
                SumEastModel SumEast = sumEastModel;
                SumE = SumEast.getSumEast();
                EastSum.setText(SumE + " ต้น");
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Land.this, "ไม่สามารถเข้าถึงข้อมูลจำนวนต้นไมภาคตะวันออกได้", Toast.LENGTH_SHORT).show();
            }
        });
        retrofit.getSumSouthByMethodWithCallback(new Callback<SumSouthModel>() {
            @Override
            public void success(SumSouthModel sumSouthModel, Response response) {
                SumSouthModel SumSouth = sumSouthModel;
                SumS = SumSouth.getSumSouth();
                SouthSum.setText(SumS + " ต้น");
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Land.this, "ไม่สามารถเข้าถึงข้อมูลจำนวนต้นไมภาคใต้ได้", Toast.LENGTH_SHORT).show();
            }
        });
        retrofit.getSumAllTreeByMethodWithCallback(new Callback<SumAllTreeModel>() {
            @Override
            public void success(SumAllTreeModel sumAllTreeModel, Response response) {
                SumAllTreeModel SumAll = sumAllTreeModel;
                SumA = SumAll.getSumAllTre();
                AllTreeSum.setText("จำนวนต้นไม้ทั้งหมดในโครงการ : "+SumA + " ต้น");

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Land.this, "ไม่สามารถเข้าถึงข้อมูลจำนวนต้นไม้ทั้งหมดได้", Toast.LENGTH_SHORT).show();
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