package com.psu.seedcampaign;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by อนุรักษ์ on 10/3/2558.
 */
public class PreviewImage extends Activity {


    private String filePath = null;

    private ImageView imgPreview;

    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_image);

        imgPreview = (ImageView) findViewById(R.id.imgPreview);

        btnOk = (Button) findViewById(R.id.okbtn);
        // Changing action bar background color
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.color.action_bar))));

        // Receiving the data from previous activity
        Intent i = getIntent();

        // image or video path that is captured in previous activity
        filePath = i.getStringExtra("filePath");

        // boolean flag to identify the media type, image or video
        boolean isImage = i.getBooleanExtra("isImage", true);

        if (filePath != null) {
            // Displaying the image or video on the screen
            previewMedia(isImage);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry, file path is missing!", Toast.LENGTH_LONG).show();
        }



        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // uploading the file to server
                Intent i = new Intent(PreviewImage.this, MainActivity.class);
                i.putExtra("filePath", filePath);

                startActivity(i);
            }
        });

    }

    private void previewMedia(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            imgPreview.setVisibility(View.VISIBLE);
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // down sizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

            imgPreview.setImageBitmap(bitmap);
        }
    }

}
