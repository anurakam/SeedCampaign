package com.psu.seedcampaign;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.psu.seedcampaign.R;

import java.io.File;

/**
 * Created by อนุรักษ์ on 2/2/2558.
 */
public class Plant extends Activity {
    static int TAKE_PHOTO_REQUEST_CODE=1;
    Uri outputFileUri;
    public void onCreate(Bundle savedInstanceState) {

        ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant);
    }
    public void btnTakePhoto(View view){
        File file=new File(Environment.getExternalStorageDirectory(),"tst.jpg");
        outputFileUri = Uri.fromFile(file);

        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,outputFileUri);
        startActivityForResult(intent,TAKE_PHOTO_REQUEST_CODE);


    }

    @Override
    protected void  onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==TAKE_PHOTO_REQUEST_CODE&&resultCode==RESULT_OK){}
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