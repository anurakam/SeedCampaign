package com.psu.seedcampaign;

import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class MainActivity extends TabActivity implements TabHost.OnTabChangeListener {

        TabHost tabHost;


    //-------------------------------------------

   /* private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter; */

    //-------------------------------------------------------
    String filePath = null;
    String value = null;
    String location = null;

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

            intent = new Intent().setClass(this, Plant.class);
            filePath = getIntent().getStringExtra("filePath");
            if (filePath != null) {
                // boolean isImage = getIntent().getBooleanExtra("isImage", true);
                intent.putExtra("filePath", filePath);
                // intent.putExtra("isImage", isImage);
                //this.startActivity(intent);

            }
            value= getIntent().getStringExtra("username");
            if (value != null) {
                intent.putExtra("username", value);}
             location = getIntent().getStringExtra("location");
            if (location != null) {
                intent.putExtra("location", location);}
            spec = tabHost.newTabSpec("One").setIndicator(getResources().getString(R.string.ic_launcher),
                    getResources().getDrawable(R.drawable.plan))
                    .setContent(intent);
            tabHost.addTab(spec);


            intent = new Intent().setClass(this, Land.class);


            spec = tabHost.newTabSpec("Two").setIndicator(getResources().getString(R.string.ic_launcher),
                    getResources().getDrawable(R.drawable.world))
                    .setContent(intent);
            tabHost.addTab(spec);


            intent = new Intent().setClass(this, Feed.class);
            spec = tabHost.newTabSpec("Three").setIndicator(getResources().getString(R.string.ic_launcher),
                    getResources().getDrawable(R.drawable.feed))
                    .setContent(intent);
            tabHost.addTab(spec);

            intent = new Intent().setClass(this, ProfileUser.class);

            if (value != null) {
            intent.putExtra("username", value);}
            spec = tabHost.newTabSpec("Four").setIndicator(getResources().getString(R.string.ic_launcher),
                    getResources().getDrawable(R.drawable.profile))
                    .setContent(intent);
            tabHost.addTab(spec);

            intent = new Intent().setClass(this, Knowledge.class);
            spec = tabHost.newTabSpec("Five").setIndicator(getResources().getString(R.string.ic_launcher),
                    getResources().getDrawable(R.drawable.iconkb))
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







//-------------------------------------------------------------------------------------------------------



/*            mTitle = mDrawerTitle = getTitle();

            // load slide menu items
            navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

            // nav drawer icons from resources
            navMenuIcons = getResources()
                    .obtainTypedArray(R.array.nav_drawer_icons);

            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

            navDrawerItems = new ArrayList<NavDrawerItem>();

            // adding nav drawer items to array
            // Home
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));


            // Recycle the typed array
            navMenuIcons.recycle();

            mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

            // setting the nav drawer list adapter
            adapter = new NavDrawerListAdapter(getApplicationContext(),
                    navDrawerItems);
            mDrawerList.setAdapter(adapter);

            // enabling action bar app icon and behaving it as toggle button
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);

            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                    R.drawable.ic_drawer, //nav menu toggle icon
                    R.string.app_name, // nav drawer open - description for accessibility
                    R.string.app_name // nav drawer close - description for accessibility
            ) {
                public void onDrawerClosed(View view) {
                    getActionBar().setTitle(mTitle);
                    // calling onPrepareOptionsMenu() to show action bar icons
                    invalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView) {
                    getActionBar().setTitle(mDrawerTitle);
                    // calling onPrepareOptionsMenu() to hide action bar icons
                    invalidateOptionsMenu();
                }
            };
            mDrawerLayout.setDrawerListener(mDrawerToggle);

            if (savedInstanceState == null) {
                // on first time display view for first nav item
                displayView(0);
            }*/





//-----------------------------------------------------------------------------------------------------------

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


        /*@Override
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
        }*/

    /**
     * Slide menu item click listener
     * */


    /*
     private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
*/




    /***
     * Called when invalidateOptionsMenu() is triggered
     */

    /*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
*/
    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
   /* private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
           case 0:
                fragment = new MainActivity();
                break;
           case 1:
                fragment = new FindPeopleFragment();
                break;
            case 2:
                fragment = new PhotosFragment();
                break;
            case 3:
                fragment = new CommunityFragment();
                break;
            case 4:
                fragment = new PagesFragment();
                break;
            case 5:
                fragment = new WhatsHotFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
*/
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

  /*  @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, Plant.class);
        startActivity(intent);
        return true;

    }



 }

