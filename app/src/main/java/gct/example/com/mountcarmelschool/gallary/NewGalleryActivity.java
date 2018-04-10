package gct.example.com.mountcarmelschool.gallary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

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

public class NewGalleryActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<NewGallaryModel> listitem;
    NewGallaryAdapter adapter ;
    String Urls;
    ImageView imagenewGallerback ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_y_new_galler);
        imagenewGallerback= (ImageView) findViewById(R.id.imagenewGallerback);
        imagenewGallerback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        gridView = (GridView) findViewById(R.id.gridview);
        listitem=new ArrayList<>();

        Urls ="http://infoes.in/sunil/mcsd/user/getallgallary";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    JSONObject  jsonObject = new JSONObject(s);
                    JSONArray jsonArray =jsonObject.getJSONArray("eventlist");
                    for(int i=0;i<jsonArray.length();i++){
                       JSONObject  object = (JSONObject) jsonArray.get(i);

                        NewGallaryModel newGallaryModel = new NewGallaryModel(
                                object.getString("Month"),
                                object.getString("Image"),
                                object.getString("Month_no")

                        );
                        listitem.add(newGallaryModel);
                        adapter = new NewGallaryAdapter(NewGalleryActivity.this,listitem);
                        gridView.setAdapter(adapter);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);



    }
}
