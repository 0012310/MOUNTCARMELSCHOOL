package gct.example.com.mountcarmelschool;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;
import gct.example.com.mountcarmelschool.classes.LoginDataForList;
import gct.example.com.mountcarmelschool.model.login_data.InvalidUserData;
import gct.example.com.mountcarmelschool.model.login_data.LoginData;
import gct.example.com.mountcarmelschool.model_rahul.SessionManager;

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    Button buttonSinghIn;
    EditText username, password;
    private ProgressDialog mprogressDialog;
    String URL_LOGIN = "http://infoes.in/sunil/mcsd/user/login";
    Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private String sec="";


    private ImageView profile_pic;
    private TextView name, email_id, token;
    private Button btn_logout;
    private com.google.android.gms.common.internal.zzaf btn_login;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 101;
    LinearLayout profile_section;
    SessionManager sessionManager;

    public static String id, email, name_url, type, schoolcode, tokengoogle, timestamp, img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = Login.this;
        sessionManager = new SessionManager(getApplicationContext());
        username = (EditText) findViewById(R.id.edt_username);
        buttonSinghIn = (Button) findViewById(R.id.buttonSinghIn);
        profile_section = (LinearLayout) findViewById(R.id.profile_section);
        name = (TextView) findViewById(R.id.name);
        email_id = (TextView) findViewById(R.id.email_id);
        token = (TextView) findViewById(R.id.token);

        btn_login = (com.google.android.gms.common.internal.zzaf) findViewById(R.id.btn_login);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        profile_pic = (ImageView) findViewById(R.id.profile_pic);
        btn_logout.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        profile_section.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        Boolean logIntent = getIntent().getBooleanExtra("LOGOUT", false);
        if (logIntent) {
            Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
            setLogout();

        }
        String email = username.getText().toString();
        //getStringReq();

    }


    private void getStringReq(String URL, final String email) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("response", response);
                try {
                    getLoginData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "error: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("email", email);
                return params;
            }
        };
        queue.add(strReq);
    }

    private void getLoginData(String response) throws JSONException {
        LoginData data = new Gson().fromJson(response, LoginData.class);
        if (data.getStatus().equals("true")) {
            for (gct.example.com.mountcarmelschool.model.login_data.Response response1 : data.getResponse()) {
                LoginDataForList loginDataForList = new LoginDataForList();
                loginDataForList.setEmail(response1.getEmail());
                loginDataForList.setId(response1.getId());
                loginDataForList.setType(response1.getType());
                loginDataForList.setSchoolcode(response1.getSchoolcode());
                loginDataForList.setTokengoogle(response1.getTokengoogle());
                loginDataForList.setTimestamp(response1.getTimestamp());
                loginDataForList.setName(response1.getName());
                loginDataForList.setImg(response1.getImg());
                loginDataForList.setSection(response1.getSection());
                sec = response1.getSection();
                //Log.d("email_data",response1.getEmail()+"\n"+response1.getId()+"\n"+response1.getType()+"\n"+response1.getSchoolcode()+"\n"+response1.getTokengoogle()+"\n"+response1.getTimestamp()+"\n"+response1.getName()+"\n"+response1.getImg());
                Log.d("sec_data",response1.getSection());
                if (response1.getType() != null && response1.getEmail() != null) {
                    goTOMain2(response1.getEmail(), loginDataForList.getName(), loginDataForList.getType(), loginDataForList.getImg());
                    //
                }

                Intent i = new Intent(Login.this, MultiSelectSportsActivity.class);
                if (null != sec) {
                    i.putExtra("sec",sec);
                    //startActivity(i);
                }
            }


        } else if (data.getStatus().equals("false")) {


            //  startActivity(new Intent(this, Login.class));

        }
    }

    private void goTOMain2(String e_mail, String name_url, String type, String img) {
        CommonMethods.setPreference(context, "e_mail", e_mail);
        Intent i = new Intent(Login.this, Main2Activity.class);
        i.putExtra("e_mail", e_mail);
        i.putExtra("url_name", name_url);
        i.putExtra("types", type);
        i.putExtra("img", img);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login:
                setLogin();
                break;
            case R.id.btn_logout:
                setLogout();
                break;

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    void setLogout() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void setLogin() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    String email1;

    private void handelResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String name1 = account.getDisplayName();
            email1 = account.getEmail();

            String name2 = account.getId();
            name.setText(name1);
            email_id.setText(email1);
            token.setText(name2);
            sessionManager.createLoginSession(name2, email1);

            LocalSharedPreferences.saveUserName(context, name1);
            LocalSharedPreferences.saveUserEmail(context, email1);

            CommonMethods.setPreference(context, "email_token", email1);
            Intent i = new Intent(getApplicationContext(), Main2Activity.class);
            i.putExtra("e_mail", email1);
            Log.d("email33", "" + email1);
            startActivity(i);

            updateUI(true);
            //getStringReq(URL_LOGIN,email1);
            HashMap<String, String> user = sessionManager.getUserDetails();
            // name
            String name = user.get(SessionManager.KEY_NAME);

            // email
            String email = user.get(SessionManager.KEY_EMAIL);
            //username.setText(email1);
            if (null != email1) {
                // getStringReq(URL_LOGIN, email);
            }
        } else {
            updateUI(false);
        }
    }

    private void updateUI(boolean isLogin) {
        if (isLogin) {
            profile_section.setVisibility(View.GONE);
            btn_login.setVisibility(View.VISIBLE);
        } else {
            profile_section.setVisibility(View.GONE);
            btn_login.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handelResult(result);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}