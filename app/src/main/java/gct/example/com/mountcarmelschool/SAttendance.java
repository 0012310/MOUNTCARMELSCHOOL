package gct.example.com.mountcarmelschool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import gct.example.com.mountcarmelschool.adapter.SAttendanceAdapter;
import gct.example.com.mountcarmelschool.adapter.StaffBdAdapter;
import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.classes.SAttandanceForList;
import gct.example.com.mountcarmelschool.classes.StaffBdForList;
import gct.example.com.mountcarmelschool.model.login_data.InvalidUserData;
import gct.example.com.mountcarmelschool.model_assessment_data.Assessment;
import gct.example.com.mountcarmelschool.model_assessment_data.AssessmentData;
import gct.example.com.mountcarmelschool.model_attendacestudentlist.Attendancelist;
import gct.example.com.mountcarmelschool.model_attendacestudentlist.List;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static gct.example.com.mountcarmelschool.R.id.SAttendance;
import static gct.example.com.mountcarmelschool.R.id.list_item;
import static gct.example.com.mountcarmelschool.R.id.staffbirtdayrecycler;

public class SAttendance extends AppCompatActivity {

    ImageView imagesattendence;
    Context context;
    SAttendanceAdapter sAttendanceAdapter;
    RecyclerView recyclerViewSAttendance;
    CheckBox checkboxParent;
    Button btnsubmit;
    LinearLayout linearCollection;

    private ArrayList<SAttandanceForList> sAttandanceForListArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sattendance);
        context = SAttendance.this;
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        sAttandanceForListArrayList = new ArrayList<>();
        imagesattendence = (ImageView) findViewById(R.id.imagesattendence);
        imagesattendenceonclick();
        linearCollection = (LinearLayout) findViewById(R.id.linearCollection);
        recyclerViewSAttendance = (RecyclerView) findViewById(R.id.recyclerViewSAttendance);
        findViewById(R.id.btnsubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String data = ",";
                ArrayList<SAttandanceForList> stList = ((SAttendanceAdapter) sAttendanceAdapter).getStudentist();
                for (int i = 0; i < stList.size(); i++) {
                    SAttandanceForList singleStudent = stList.get(i);
                    if (singleStudent.isSelected() == true) {
                        data = data + "," + singleStudent.getAdm_no().toString();

                    }
                }
                Toast.makeText(context, "Selected Students: \n" + data, Toast.LENGTH_LONG).show();
                sendStringReq("http://infoes.in/sunil/mcsd/user/make_att", data.substring(2,data.length()));
            }
        });
        String e_mail1 = CommonMethods.getPreference(this, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/studentlist?t_mail=" + e_mail1;
        Log.d("e_mail ", "" + e_mail1);
        getStringReq(url, "" + e_mail1);

    }


    // here we are trying to get data from server

    private void getStringReq(String url, final String e_mail) {

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("user_emails", e_mail);
                        Log.d("response11: ", response);
                        getDetailsData(response);
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "" + volleyError, Toast.LENGTH_SHORT).show();
                        if (volleyError instanceof NoConnectionError) {
                            Toast.makeText(context, "No Internet Found!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Some error occurs in get Moments. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("e_mail", e_mail);
                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }

    private void getDetailsData(String response) {
        Attendancelist data = new Gson().fromJson(response, Attendancelist.class);
        if (data.getStatus().equals("true")) {
            gct.example.com.mountcarmelschool.model_attendacestudentlist.Response response1 = data.getResponse();
            for (List attendancelist : response1.getList()) {
                SAttandanceForList sAttandanceForList = new SAttandanceForList();
                sAttandanceForList.setFirstname(attendancelist.getFirstname());
                sAttandanceForList.setAdm_no(attendancelist.getAdm_no());
                sAttandanceForList.setSelected(true);
                sAttandanceForListArrayList.add(sAttandanceForList);
            }
        } else if (data.getStatus().equals("false")) {
            InvalidUserData invalidUserData = new Gson().fromJson(response, InvalidUserData.class);
            Toast.makeText(context, "" + invalidUserData.getError(), Toast.LENGTH_SHORT).show();
        }
        sAttendanceAdapter = new SAttendanceAdapter(sAttandanceForListArrayList, context);
        recyclerViewSAttendance.setLayoutManager(new GridLayoutManager(this,3));
        recyclerViewSAttendance.setAdapter(sAttendanceAdapter);

    }


    // here we are trying to send data to server

    private void sendStringReq(String url, final String adm_no) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("sendDataResult", "" + response);
                        //Toast.makeText(context, "Attendance Updated.", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "" + volleyError, Toast.LENGTH_SHORT).show();
                        if (volleyError instanceof NoConnectionError) {
                            Toast.makeText(context, "No Internet Found!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Some error occurs in get Moments. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("adm_no", adm_no);
                Log.d("adm_no_tosend",""+adm_no);
                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }

    private void imagesattendenceonclick() {
        imagesattendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}