package com.psu.seedcampaign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class North_Knowlede extends Activity {

    GridView grid;
    String[] web = {
            "กระโถนพระฤๅษี",
            "กลาย"
           /* "Instagram",
            "Facebook",
            "Flickr",
            /* "Pinterest",
            "Quora",
            "Twitter",
            "Vimeo",
            "WordPress",
            "Youtube",
           "Stumbleupon",
            "SoundCloud",
            "Reddit",
            "Blogger"*/
    } ;
    int[] imageId = {
            R.drawable.a6,
            R.drawable.a4
            /*R.drawable.a3,
            R.drawable.a4,
            R.drawable.a5,
          /*  R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon,
            R.drawable.icon*/
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_north__knowlede);

        CustomGrid adapter = new CustomGrid(North_Knowlede.this,web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent IntentMain = new Intent(North_Knowlede.this, Discription.class);

                startActivity(IntentMain);
            }
        });
        /*imageId = (GridView)findViewById(R.id.Tetx);
        imageId.setOnClickListener(this);
        Intent intent = new Intent(this, Discription.class);
        startActivity(intent);*/

    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.north__knowlede, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
