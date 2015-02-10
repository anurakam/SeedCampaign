package com.psu.seedcampaign;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MainActivity extends TabActivity implements TabHost.OnTabChangeListener {

        TabHost tabHost;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);


            /*if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }*/

            tabHost = getTabHost();


            tabHost.setOnTabChangedListener(this);

            TabHost.TabSpec spec;
            Intent intent;

            intent = new Intent().setClass(this, Land.class);
            spec = tabHost.newTabSpec("One").setIndicator(getResources().getString(R.string.ic_launcher),
                    getResources().getDrawable(R.drawable.world))
                    .setContent(intent);
            tabHost.addTab(spec);


            intent = new Intent().setClass(this, Plant.class);
            spec = tabHost.newTabSpec("Two").setIndicator(getResources().getString(R.string.ic_launcher),
                    getResources().getDrawable(R.drawable.plan))
                    .setContent(intent);
            tabHost.addTab(spec);


            intent = new Intent().setClass(this, Feed.class);
            spec = tabHost.newTabSpec("Three").setIndicator(getResources().getString(R.string.ic_launcher),
                    getResources().getDrawable(R.drawable.feed))
                    .setContent(intent);
            tabHost.addTab(spec);


            tabHost.getTabWidget().getChildAt(1);
            tabHost.getTabWidget().getChildAt(2);


            tabHost.getTabWidget().setCurrentTab(0);
            tabHost.getTabWidget().getChildAt(0);


            for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
            {
                tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#BCE954"));
            }
            tabHost.getTabWidget().setCurrentTab(1);
            tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#BCE954"));

        }

        public void onTabChanged(String tabId) {
            for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
            {
                if(i==0)
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#BCE954"));
                else if(i==1)
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#BCE954"));
                else if(i==2)
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#BCE954"));
            }
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#BCE954"));


            if(tabHost.getCurrentTab()==0)
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab());
            else if(tabHost.getCurrentTab()==1)
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab());
            else if(tabHost.getCurrentTab()==2)
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab());


        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);

            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
}
