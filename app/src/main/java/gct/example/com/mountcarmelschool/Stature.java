package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.main_wise_data.MainWisedata;
import gct.example.com.mountcarmelschool.main_wise_data.STATURE;
import gct.example.com.mountcarmelschool.main_wise_data.WORTHY;
import gct.example.com.mountcarmelschool.wise.StatureAdapter;
import gct.example.com.mountcarmelschool.wise.WiseModel;



public class Stature extends AppCompatActivity {
    ImageView imageStatureback;
    TextView textStaurenumber;
    RequestQueue requestQueue;
    Context context;
    String e_mail;



    public static String url_data ;
    private List<WiseModel> listitems;
    private RecyclerView recyclerviewStature;
    private StatureAdapter adapter, adaptersearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stature);
        textStaurenumber = (TextView) findViewById(R.id.textStaurenumber);
        context = Stature.this;

        imageStatureback = (ImageView) findViewById(R.id.imageStatureback);



        imageStatureback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  finish();
                onBackPressed();
            }
        });




        url_data = "http://infoes.in/sunil/mcsd/user/wisebyid?e_mail="+Main2Activity.email+"&type=S";
        recyclerviewStature = (RecyclerView) findViewById(R.id.recyclerviewStature);
        recyclerviewStature.setHasFixedSize(true);
        recyclerviewStature.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();

        getRemark(url_data);


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerviewStature.setLayoutManager(layoutManager);

    }
    private void getRemark(String url_data) {
        listitems.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e("url_data",s);

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    textStaurenumber.setText(jsonObject.getString("total"));
                    JSONArray jsonArray = jsonObject.getJSONArray("response");

                    for(int i =0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);

                        WiseModel item = new WiseModel(
                                object.getString("remarks"),
                                object.getString("teacher_name"), object.getString("date")

                        );
                        listitems.add(item);

                    }

                    adapter = new StatureAdapter(listitems,Stature.this);
                    recyclerviewStature.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("url_data",volleyError.getMessage());

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getStringReq(String url, final String e_mail) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
                        Log.d("response: ", response);
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
        MainWisedata data = new Gson().fromJson(response, MainWisedata.class);
        gct.example.com.mountcarmelschool.main_wise_data.Response response1 = data.getResponse();
        for (STATURE stature : response1.getSTATURE()) {
         //   Toast.makeText(context, "" + stature.getTotal(), Toast.LENGTH_SHORT).show();
            textStaurenumber.setText(stature.getTotal());
        }
    }
}