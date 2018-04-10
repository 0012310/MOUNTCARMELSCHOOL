package gct.example.com.mountcarmelschool.model_rahul;

import android.app.ProgressDialog;
import android.content.Intent;
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
import java.util.List;

import gct.example.com.mountcarmelschool.R;

public class Gridshow extends AppCompatActivity {

    public static String url_data ;

    GridView grid;
    private List<Field> listitems;
    private FieldAdapter fieldAdapter;
    Field fieldmodel;
    private List<Field> fieldList;
    String cid;
    ImageView imagegallery1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridshow);
        Intent i=this.getIntent();
        imagegallery1= (ImageView) findViewById(R.id.imagegallery1);
        imagegallery1Onclick();
        cid=i.getExtras().getString("imageURL");
        //"u"+
        url_data =cid;
        listitems = new ArrayList<>();
        grid=(GridView)findViewById(R.id.grid);
        fieldAdapter =new FieldAdapter(getApplicationContext(),R.layout.single_view,cid);
        grid.setAdapter(fieldAdapter);
        loadRecyckerViewData();
    }

    private void imagegallery1Onclick() {
        imagegallery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void loadRecyckerViewData() {
        listitems.clear();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data.....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url_data,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject parentobj = new JSONObject(response);
                            JSONArray mainobj = parentobj.getJSONArray("image");
                            StringBuffer sb = new StringBuffer();
                            ArrayList l1 = new ArrayList();
                            fieldList = new ArrayList<>();
                            int i=0;
                            while (i<mainobj.length()){
                                JSONObject finalobject = mainobj.getJSONObject(i);
                                String caseId = finalobject.getString("imgid");
                                String CCNNo = (finalobject.getString("image"));
                                String Claim = (finalobject.getString("description"));

                                fieldmodel = new Field( caseId,CCNNo,Claim);
                                fieldAdapter.add(fieldmodel);
                                i++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
