package com.psu.seedcampaign;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by อนุรักษ์ on 3/2/2558.
 */
public class Login extends FragmentActivity {
    private ImageButton Login,Register;

    private static String TAG = Login.class.getSimpleName();
    EditText userLogin, passLogin;
    Context context;

//    private MainFragment mainFragment;

    //LoginButton facebookBtn;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        //setupActionBar();


        // Initialize  the layout components
        context=this;
        userLogin = (EditText) findViewById(R.id.userText);
        passLogin = (EditText) findViewById(R.id.passText);


       Login = (ImageButton) findViewById(R.id.loginbtn);
        Login.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String username=userLogin.getText().toString();
                String password=passLogin.getText().toString();




                // Execute the AsyncLogin class
                new AsyncLogin().execute(username,password);

                /*Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();*/
            }

        });
        Register = (ImageButton) findViewById(R.id.regisbtn);
        Register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CreateUser.class);
                startActivity(intent);
            }
        });

        /*facebookBtn = (LoginButton) findViewById(R.id.facebookLoginButton);
        facebookBtn.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
                if (user != null) {
                    Toast.makeText(Login.this,
                            "---------------",
                            Toast.LENGTH_LONG).show();;
                } else {
                    Toast.makeText(Login.this,
                            "You are not logged",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        showHashKey(this);*/


        /*if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, mainFragment)
                    .commit();
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);

        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {


        }*/
    }




    /*public static void showHashKey(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    "com.psu.seedcampaign", PackageManager.GET_SIGNATURES); //Your      package name here
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("KeyHash:", "error");
        } catch (NoSuchAlgorithmException e) {
            Log.i("KeyHash:", "error");
        }
    }*/


    protected class AsyncLogin extends AsyncTask<String, JSONObject, Boolean> {

        String userName=null;
        @Override
        protected Boolean doInBackground(String... params) {

            RestAPI api = new RestAPI();
            boolean userAuth = false;
            try {


                // Call the User Authentication Method in API
                JSONObject jsonObj = api.UserAuthentication(params[0],
                        params[1]);

                //Parse the JSON Object to boolean
                JSONParser parser = new JSONParser();
                userAuth = parser.parseUserAuth(jsonObj);
                userName=params[0];
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d("AsyncLogin", e.getMessage());

            }
            return userAuth;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            Toast.makeText(context, "กรุณารอสักครู่...",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub


            //Check user validity
            if (result) {
                Intent i = new Intent(Login.this,
                        MainActivity.class);
                i.putExtra("username",userName);
                startActivity(i);
            }
            else
            {
                Toast.makeText(context, "ชื่อผู้ใช้/รหัสผ่านของท่านไม่ถูกต้อง ",Toast.LENGTH_SHORT).show();
            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

   /* public static class MainFragment extends Fragment {
        private static final String TAG = MainFragment.class.getName();

        private UiLifecycleHelper uiHelper;

        private Session.StatusCallback callback = new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                onSessionStateChange(session, state, exception);
            }
        };

        private void onSessionStateChange(Session session, SessionState state, Exception exception) {
            if (state.isOpened()) {

                Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {

                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        Toast.makeText(getActivity(),
                                "Hello " + user.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                request.executeAsync();

            } else if (state.isClosed()) {
                Log.i(TAG, "Logged out...");
            }
        }

        @Override
        public void onResume() {
            super.onResume();

            Session session = Session.getActiveSession();
            if (session != null &&
                    (session.isOpened() || session.isClosed()) ) {
                onSessionStateChange(session, session.getState(), null);
            }

            uiHelper.onResume();
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            uiHelper.onActivityResult(requestCode, resultCode, data);
        }

        @Override
        public void onPause() {
            super.onPause();
            uiHelper.onPause();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            uiHelper.onDestroy();
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            uiHelper.onSaveInstanceState(outState);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            uiHelper = new UiLifecycleHelper(getActivity(), callback);
            uiHelper.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.login, container,
                    false);


            LoginButton authButton = (LoginButton) rootView.findViewById(R.id.facebookLoginButton);
            authButton.setFragment(this);


            return rootView;
        }
    }*/

    }




