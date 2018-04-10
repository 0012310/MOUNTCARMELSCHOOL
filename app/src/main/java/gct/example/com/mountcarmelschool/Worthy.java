package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.android.volley.VolleyLog;
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
import gct.example.com.mountcarmelschool.main_wise_data.WORTHY;
import gct.example.com.mountcarmelschool.model_parent.Father;
import gct.example.com.mountcarmelschool.model_parent.ParentDetailsData;
import gct.example.com.mountcarmelschool.wise.WiseAdapter;
import gct.example.com.mountcarmelschool.wise.WiseModel;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import static gct.example.com.mountcarmelschool.R.layout.activity_worthy;

public class Worthy extends AppCompatActivity {

    ImageView imageWorthyback;
    RequestQueue requestQueue;
    Context context;
    String e_mail;
    TextView textworthynumber;

    public static String url_data ;
    private List<WiseModel> listitems;
    private RecyclerView recyclerView;
    private WiseAdapter adapter, adaptersearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worthy);
        imageWorthyback = (ImageView) findViewById(R.id.imageWorthyback);
        imageWorthybackonclick();
        context = Worthy.this;
        textworthynumber= (TextView) findViewById(R.id.textworthynumber);




        url_data = "http://infoes.in/sunil/mcsd/user/wisebyid?e_mail="+Main2Activity.email+"&type=W";
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();
        getRemark(url_data);



        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

    }

    private void getRemark(String url_data) {
        listitems.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.e("url_data",s);

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    textworthynumber.setText(jsonObject.getString("total"));


                 //   textworthynumber.setText("''total''");
                    JSONArray jsonArray = jsonObject.getJSONArray("response");

                    for(int i =0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);

                        WiseModel item = new WiseModel(
                                object.getString("remarks") ,  object.getString("teacher_name"),object.getString("date"));

                        listitems.add(item);

                    }

                    adapter = new WiseAdapter(listitems,Worthy.this);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {


                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




    private void imageWorthybackonclick() {
        imageWorthyback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();


            }
        });
    }
}