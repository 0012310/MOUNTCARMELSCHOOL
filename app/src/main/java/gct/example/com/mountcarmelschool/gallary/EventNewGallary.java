package gct.example.com.mountcarmelschool.gallary;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import gct.example.com.mountcarmelschool.R;
import gct.example.com.mountcarmelschool.model_rahul.PendingModel;

public class EventNewGallary extends AppCompatActivity {

    String getUrls;
    GridView gridView;
    ArrayList<PendingModel> list;
    NewEventAdapter adapter;
    ImageView imageneweventlistback;
    TextView textFeebill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_new_gallary);
        imageneweventlistback= (ImageView) findViewById(R.id.imageneweventlistback);
        textFeebill= (TextView) findViewById(R.id.textFeebill);
        imageneweventlistback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (getIntent().getStringExtra("Monthname")!=null){
            textFeebill.setText(getIntent().getStringExtra("Monthname"));

        }
        Bundle bundle =getIntent().getExtras();
        if(bundle !=null)
        {
            getUrls = bundle.getString("api");
        }
        gridView = (GridView) findViewById(R.id.gridview);
        list = new ArrayList<>();
        LoadAPI();


    }

    private void LoadAPI() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, getUrls, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonobject = new JSONObject(s);
                    String stauts = jsonobject.getString("status");
                    if (stauts.equalsIgnoreCase("false")) {

                    }

                    JSONArray array = jsonobject.getJSONArray("eventlist");
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject o = array.getJSONObject(i);
                        PendingModel items = new PendingModel(
                                o.getString("id"),
                                o.getString("name"),
                                o.getString("date"),
                                o.getString("description"),
                                o.getString("entry_by"),
                                o.getString("timestamp"),
                                o.getString("classdetail"),
                                o.getString("section_id"),
                                o.getString("event_image")
                        );

                        list.add(items);
                    }
                    adapter = new NewEventAdapter(list, EventNewGallary.this);

                    gridView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(EventNewGallary.this);
        requestQueue.add(stringRequest);
    }
}
