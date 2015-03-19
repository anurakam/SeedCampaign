package com.psu.seedcampaign;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by อนุรักษ์ on 8/3/2558.
 */
public class CreateUser extends Activity {

    EditText FirstName, LastName, Username, Password;
    Button btnCreateUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        // Show the Up button in the action bar.


        FirstName =(EditText) findViewById(R.id.fisrtname);
        LastName = (EditText) findViewById(R.id.lastname);
        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        btnCreateUser=(Button) findViewById(R.id.btn_createuser);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String firstname, lastname, username, password;

                firstname = FirstName.getText().toString();
                lastname = LastName.getText().toString();
                username = Username.getText().toString();
                password = Password.getText().toString();

                UserDetailsTable userDetail = new UserDetailsTable(firstname,
                        lastname, username, password);


                new AsyncCreateUser().execute(userDetail);

            }
        });

    }

    protected class AsyncCreateUser extends
            AsyncTask<UserDetailsTable, Void, Void> {

        @Override
        protected Void doInBackground(UserDetailsTable... params) {

            RestAPI api = new RestAPI();
            try {

                api.CreateNewAccount(params[0].getFirstName(),
                        params[0].getLastName(), params[0].getUserName(),
                        params[0].getPassword());

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncCreateUser", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            showAlert();

        }

    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("เสร็จสิ้น")
                .setMessage("คุณได้ทำการสมัครสมาชิกเรียบร้อยแล้ว")
                .setCancelable(false)
                .setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // do nothing
                        Intent i = new Intent(CreateUser.this, Login.class);
                        startActivity(i);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

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
