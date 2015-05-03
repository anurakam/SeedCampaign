package com.psu.seedcampaign;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by อนุรักษ์ on 8/3/2558.
 */
public class ProfileUser extends Activity {

    ArrayAdapter<String> adapter;
    ListView listv;
    Context context;
    ArrayList<String> data;

    TextView tvFisrtName, tvLastName, allTree;
    String user_name,AllTree;
    ImageButton profileButton;

    private static final int PICK_IMAGE = 1;

    String selectedImagePath;
    String username,imageprofile;
    private static final String TAG = "upload";

    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
            Locale.getDefault()).format(new Date());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proflie_user);
        // Show the Up button in the action bar.
        //data = new ArrayList<String>();
        listv = (ListView) findViewById(R.id.lv_dept);
        context = this;

        /*adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);
        listv.setAdapter(adapter);*/
        //Toast.makeText(this, "Loading Please Wait..", Toast.LENGTH_SHORT).show();
       // new AsyncLoadDeptDetails().execute();


        tvFisrtName=(TextView)findViewById(R.id.tv_firstname);
        tvLastName=(TextView)findViewById(R.id.tv_lastname);

        allTree = (TextView)findViewById(R.id.alltree);
        profileButton = (ImageButton)findViewById(R.id.imageProfile);
        Intent i=getIntent();
        user_name=i.getStringExtra("username");
       // Toast.makeText(context, user_name, Toast.LENGTH_SHORT).show();

        new AsyncUserDetails().execute(user_name);
       // new AsyncUserPlanting().execute();

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                        PICK_IMAGE);

            }
        });







        GsonBuilder builder = new GsonBuilder();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://anurakam.somee.com")
                .setConverter(new GsonConverter(builder.create()))
                .build();
        ApiService retrofit = restAdapter.create(ApiService.class);
        retrofit.getHistoryByUserNameWithCallback(user_name, new Callback<List<HistoryModel>>() {


            @Override
            public void success(List<HistoryModel> historyModels, Response response) {
                List<HistoryModel> ep = historyModels;
                listv.setAdapter(new HistoryListAdapter(ep));
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ProfileUser.this,
                        "ไม่สามารถเข้าถึงข้อมูลได้",
                        Toast.LENGTH_LONG).show();
            }
        });
        retrofit.getAllTreeUserByUserNameWithCallback(user_name,new Callback<AllTreeUserModel>() {
            @Override
            public void success(AllTreeUserModel allTreeUserModel, Response response) {
                AllTreeUserModel AllTreeUser =allTreeUserModel;
                AllTree = AllTreeUser.getAllTreeUser();
                allTree.setText("จำนวนต้นไม้ที่คุณปลูกทั้งหมด : "+ AllTree + " ต้น");
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ProfileUser.this,
                        "ไม่สามารถเข้าถึงข้อมูลต้นไม้ทั้งหมดได้",
                        Toast.LENGTH_LONG).show();
            }
        });

    }



    public class HistoryListAdapter extends BaseAdapter {

        List<HistoryModel> History;

        public HistoryListAdapter(List<HistoryModel> sd) {
            History = sd;
        }

        @Override
        public int getCount() {
            return History.size();
        }

        @Override
        public Object getItem(int position) {
            return History.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            TextView nickName;
            //TextView userName;
            TextView flowerName;
            TextView amount;
            TextView locationName,region;
            //TextView userName;
            TextView position;
            ImageView image;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = getLayoutInflater();

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_single_history, parent, false);
                holder = new ViewHolder();
                //holder.userName = (TextView) convertView.findViewById(R.id.txt);
                holder.nickName = (TextView) convertView.findViewById(R.id.txt);
                holder.flowerName = (TextView) convertView.findViewById(R.id.txt1);
                holder.amount = (TextView) convertView.findViewById(R.id.txt2);
                holder.image = (ImageView) convertView.findViewById(R.id.img);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            HistoryModel bk = History.get(position);
            //holder.position.setText("" + (position + 1));
            //holder.userName.setText("" + bk.getUserName());
           // holder.locationName.setText("" + bk.getLocationName());
            holder.nickName.setText("ชื่อต้นไม้:  " + bk.getNickName());
            holder.flowerName.setText("ชื่อสามัญ:  " + bk.getFlowerName());
            holder.amount.setText("จำนวนต้นที่ปลูก:  " + bk.getAmount()+" ต้น");
            Picasso.with(ProfileUser.this).load("http://anurakam.somee.com/AndroidFileUpload/uploads/" + bk.getPicName()).resize(75, 75).into(holder.image);

            return convertView;
        }
    }








    /*protected class AsyncLoadDeptDetails extends
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

    }*/

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

   /* @Override
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
*/
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // TODO Auto-generated method stub



       if (resultCode == RESULT_OK) {
           if (requestCode == PICK_IMAGE) {
               Uri selectedImageUri = data.getData();
               if (Build.VERSION.SDK_INT < 19) {
                   selectedImagePath = getPath(selectedImageUri);
                   Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath);
                   profileButton.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
                   try {
                       sendPhoto(bitmap);
                   } catch (Exception e) {
//					// TODO Auto-generated catch block
                       e.printStackTrace();
                   }

               }
               else {
                   ParcelFileDescriptor parcelFileDescriptor;
                   try {
                       parcelFileDescriptor = getContentResolver().openFileDescriptor(selectedImageUri, "r");
                       FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                       Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                       parcelFileDescriptor.close();

                       profileButton.setImageBitmap(Bitmap.createScaledBitmap(image, 200, 200, false));

                       sendPhoto(image);

                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       // TODO Auto-generated catch block
                       e.printStackTrace();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }

               }
           }
       }
   }

    public String getPath(Uri uri) {
        if( uri == null ) {
            return null;
        }
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }
    private void sendPhoto(Bitmap bitmap) throws Exception {


        username = user_name;
        imageprofile = "Profile_"+timeStamp+ ".jpg";

        ImageProfileTable imageProfileTable = new ImageProfileTable(username,imageprofile);
        new AsyncImageProfile().execute(imageProfileTable);
        new UploadTask().execute(bitmap);

    }

    protected class AsyncImageProfile extends
            AsyncTask<ImageProfileTable, Void, Void> {

        @Override
        protected Void doInBackground(ImageProfileTable... params) {

            RestAPI api = new RestAPI();
            try {

                api.SaveImageProfile(params[0].getUserName(),
                        params[0].getImageProfile());
                Toast.makeText(ProfileUser.this, username+"  "+imageprofile, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncImageProfile", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {


        }

    }

    private class UploadTask extends AsyncTask<Bitmap, Void, Void> {

        protected Void doInBackground(Bitmap... bitmaps) {
            if (bitmaps[0] == null)
                return null;
            setProgress(0);

            Bitmap bitmap = bitmaps[0];
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // convert Bitmap to ByteArrayOutputStream
            InputStream in = new ByteArrayInputStream(stream.toByteArray()); // convert ByteArrayOutputStream to ByteArrayInputStream

            DefaultHttpClient httpclient = new DefaultHttpClient();
            try {
                HttpPost httppost = new HttpPost(
                        "http://anurakam.somee.com/AndroidFileUpload/saveprofile.php"); // server

                MultipartEntity reqEntity = new MultipartEntity();
                reqEntity.addPart("myFile",
                        "Profile_"+timeStamp+ ".jpg", in);//System.currentTimeMillis()
                httppost.setEntity(reqEntity);

                Log.i(TAG, "request " + httppost.getRequestLine());
                HttpResponse response = null;
                try {
                    response = httpclient.execute(httppost);
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    if (response != null)
                        Log.i(TAG, "response " + response.getStatusLine().toString());
                } finally {

                }
            } finally {

            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            Toast.makeText(ProfileUser.this, "อัพโหลดรูปโปรไฟล์เสร็จแล้ว"+timeStamp, Toast.LENGTH_LONG).show();
        }
    }
}
