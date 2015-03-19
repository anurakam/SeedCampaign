package com.psu.seedcampaign;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ProgressBar;
import android.widget.Toast;
//import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by อนุรักษ์ on 2/2/2558.
 */
public class Plant extends Activity {

    private static final String TAG = Plant.class.getSimpleName();
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri; // file url to store image/video

    private ImageButton btnCapturePicture, btnPlant,btnLocation;
    String filePath = null;
    String Username = null;
    String location = null;
    private ProgressBar progressBar;
    long totalSize = 0;


    ImageButton b;

    EditText NameTree,CommonName,Amount,LocationName,Region;
    Context context;
    String U;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant);
        b=(ImageButton)findViewById(R.id.imageButtonTakePhoto);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnPlant=(ImageButton)findViewById(R.id.imageButtonPlan);
        btnLocation=(ImageButton)findViewById(R.id.imageButtonPlaces);
        NameTree =(EditText)findViewById(R.id.nameTree);
        CommonName =(EditText)findViewById(R.id.commonName);
        Amount =(EditText)findViewById(R.id.amount);
        LocationName =(EditText)findViewById(R.id.locationName);
        Region =(EditText)findViewById(R.id.region);
        context=this;


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //selectImage();
                captureImage();
                //finish();


            }
        });
        //Intent i = getIntent();
        filePath = getIntent().getStringExtra("filePath");
        if (filePath != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();

            // down sizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

            b.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 300, 300, false));
            //Toast.makeText(context, filePath, Toast.LENGTH_SHORT).show();
        }

        Username = getIntent().getStringExtra("username");

       // Toast.makeText(context, Username, Toast.LENGTH_SHORT).show();

        location = getIntent().getStringExtra("location");

        if (location != null) {
            LocationName.setText(location);
        }
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plant.this, Location.class);
                intent.putExtra("username",Username);
                intent.putExtra("filePath",filePath);
                startActivity(intent);
                finish();
            }
        });
        btnPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] separated = filePath.split("/");
                //Toast.makeText(context, separated[6], Toast.LENGTH_SHORT).show();


                int id_User;
                String username,picName,nickName,flowerName,amount,locationName,region;
                username = Username;
                picName = separated[6];
                nickName = NameTree.getText().toString();
                flowerName = CommonName.getText().toString();
                amount = Amount.getText().toString();
                locationName = LocationName.getText().toString();
                region = Region.getText().toString();

                PlantingTable plantingTable = new PlantingTable(username,picName,nickName,flowerName,amount,locationName,region);
                new AsyncPlanting().execute(plantingTable);
                new UploadFileToServer().execute();
               // finish();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds options to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void selectImage() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(Plant.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);*/
                    captureImage();



                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes

       outState.putParcelable(MediaStore.EXTRA_OUTPUT, fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        savedInstanceState.getParcelable(MediaStore.EXTRA_OUTPUT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {

                launchUploadActivity(true);
                /*File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }

                }*/
                /*if (filePath != null) {
                    // Displaying the image or video on the screen
                    previewMedia(isImage);
                }*/

                //launchUploadActivity(true);


                /*try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    b.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath + "");
                b.setImageBitmap(thumbnail);
            }
        }
    }
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                Config.IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create "
                        + Config.IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    private void launchUploadActivity(boolean isImage){
        Intent i = new Intent(Plant.this, MainActivity.class);
        i.putExtra("filePath", fileUri.getPath());
        i.putExtra("username", Username);
        i.putExtra("isImage", isImage);
        startActivity(i);
    }

    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            // setting progress bar to zero
            progressBar.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            // Making progress bar visible
            progressBar.setVisibility(View.VISIBLE);

            // updating progress bar value
            progressBar.setProgress(progress[0]);

            // updating percentage value
            //txtPercentage.setText(String.valueOf(progress[0]) + "%");
        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Config.FILE_UPLOAD_URL);

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                        new AndroidMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                File sourceFile = new File(filePath);

                // Adding file data to http body
                entity.addPart("image", new FileBody(sourceFile));

                // Extra parameters if you want to pass to server
                entity.addPart("website",
                        new StringBody("www.androidhive.info"));
                entity.addPart("email", new StringBody("abc@gmail.com"));

                totalSize = entity.getContentLength();
                httppost.setEntity(entity);

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(TAG, "Response from server: " + result);

            // showing the server response in an alert dialog
            showAlert();

            super.onPostExecute(result);
        }

    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("คุณปลูกไปแล้ว"+"  "+"ต้น"+"\n"+"รวมทั้งโครงการ"+"  "+"ต้น").setTitle("เสร็จสิ้น")
                .setCancelable(false)
                .setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // do nothing
                        b.setImageDrawable(getResources().getDrawable(R.drawable.takephoto));
                        progressBar.setVisibility(View.GONE);
                        NameTree.setText("");
                        CommonName.setText("");
                        Amount.setText("");
                        LocationName.setText("");
                        Region.setText("");
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    protected class AsyncPlanting extends
            AsyncTask<PlantingTable, Void, Void> {

        @Override
        protected Void doInBackground(PlantingTable... params) {

            RestAPI api = new RestAPI();
            try {

                api.Planting(params[0].getUserName(),
                        params[0].getPicName(), params[0].getNickName(),
                        params[0].getFlowerName(), params[0].getAmount(), params[0].getLocationName(), params[0].getRegion());

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncPlanting", e.getMessage());

            }
            return null;
        }

        /*@Override
        protected void onPostExecute(Void result) {

        }*/

    }

}