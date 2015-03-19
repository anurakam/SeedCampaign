package com.psu.seedcampaign;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by อนุรักษ์ on 8/3/2558.
 */
public class ProfileUser extends Activity {

    ArrayAdapter<String> adapter;
    ListView listv;
    Context context;
    ArrayList<String> data;

    TextView tvFisrtName, tvLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proflie_user);
        // Show the Up button in the action bar.
        data = new ArrayList<String>();
        listv = (ListView) findViewById(R.id.lv_dept);
        context = this;

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);
        listv.setAdapter(adapter);
        //Toast.makeText(this, "Loading Please Wait..", Toast.LENGTH_SHORT).show();
        new AsyncLoadDeptDetails().execute();


        tvFisrtName=(TextView)findViewById(R.id.tv_firstname);
        tvLastName=(TextView)findViewById(R.id.tv_lastname);

        Intent i=getIntent();
        String username=i.getStringExtra("username");
       // Toast.makeText(context, username, Toast.LENGTH_SHORT).show();

        new AsyncUserDetails().execute(username);
       // new AsyncUserPlanting().execute();
    }

    protected class AsyncLoadDeptDetails extends
            AsyncTask<Void, JSONObject, ArrayList<DeptTable>> {
        ArrayList<DeptTable> deptTable = null;

        @Override
        protected ArrayList<DeptTable> doInBackground(Void... params) {
            // TODO Auto-generated method stub


            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.GetDepartmentDetails();

                JSONParser parser = new JSONParser();

                deptTable = parser.parseDepartment(jsonObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLoadDeptDetails", e.getMessage());

            }

            return deptTable;
        }

        @Override
        protected void onPostExecute(ArrayList<DeptTable> result) {
            // TODO Auto-generated method stub

            for (int i = 0; i < result.size(); i++) {
                data.add(result.get(i).getNo() + " " + result.get(i).getName());
            }

            adapter.notifyDataSetChanged();

            Toast.makeText(context,"ดาวโหลดเสร็จสิ้น",Toast.LENGTH_SHORT).show();
        }

    }

    protected class AsyncUserDetails extends AsyncTask<String,Void,UserDetailsTable>
    {

        @Override
        protected UserDetailsTable doInBackground(String... params) {
            // TODO Auto-generated method stub
            UserDetailsTable userDetail=null;
            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.GetUserDetails(params[0]);
                JSONParser parser = new JSONParser();
                userDetail = parser.parseUserDetails(jsonObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncUserDetails", e.getMessage());

            }

            return userDetail;
        }

        @Override
        protected void onPostExecute(UserDetailsTable result) {
            // TODO Auto-generated method stub

            tvFisrtName.setText(result.getFirstName());
            tvLastName.setText(result.getLastName());

        }

    }


    /*protected class AsyncUserPlanting extends AsyncTask<Void,JSONObject, ArrayList<PlantingDetailsTable>>
    { ArrayList<PlantingDetailsTable> userPlanting = null;

        @Override
        protected ArrayList<PlantingDetailsTable> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            //PlantingDetailsTable userPlanting=null;
            RestAPI api = new RestAPI();
            try {

                JSONObject jsonObj = api.GetUserPlanting();
                JSONParser parser = new JSONParser();
                userPlanting = parser.parseUserPlanting2(jsonObj);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncUserPlanting", e.getMessage());

            }

            return userPlanting;
        }

        @Override
        protected void onPostExecute(ArrayList<PlantingDetailsTable> result) {
            // TODO Auto-generated method stub

            *//*tvFisrtName.setText(result.getFirstName());
            tvLastName.setText(result.getLastName());*//*
            for (int i = 0; i < result.size(); i++) {
                data.add(result.get(i).getNickName() + " " + result.get(i).getFlowerName()+ " " + result.get(i).getPicName()+ " " + result.get(i).getLocationName()+ " " + result.get(i).getRegion());
            }

        }

    }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}