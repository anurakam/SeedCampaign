package com.psu.seedcampaign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by อนุรักษ์ on 2/2/2558.
 */
public class Land extends Activity implements View.OnClickListener {
    private ImageButton North, NorthEast, Central, East, South;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.land);
        North = (ImageButton) findViewById(R.id.NorthImgBtn);
        NorthEast = (ImageButton) findViewById(R.id.NorthEastImgBtn);
        Central = (ImageButton) findViewById(R.id.CentralImgBtn);
        East = (ImageButton) findViewById(R.id.EastImgBtn);
        South = (ImageButton) findViewById(R.id.SouthImgBtn);

        North.setOnClickListener(this);
        NorthEast.setOnClickListener(this);
        Central.setOnClickListener(this);
        East.setOnClickListener(this);
        South.setOnClickListener(this);


    }
    public void onClick(View v) {
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
    }
}

