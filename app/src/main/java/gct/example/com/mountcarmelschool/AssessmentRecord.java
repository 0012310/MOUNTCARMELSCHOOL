package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.main_notice_data.Notice;
import gct.example.com.mountcarmelschool.main_wise_data.EMOTION;
import gct.example.com.mountcarmelschool.main_wise_data.MainWisedata;
import gct.example.com.mountcarmelschool.model_assessment_data.Assessment;
import gct.example.com.mountcarmelschool.model_assessment_data.AssessmentData;

public class AssessmentRecord extends AppCompatActivity {
    Context context;
    ImageView imageAssingmentRecord;
 //   TextView assessmentdetails;
    String assesment_rec_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_record);
        context = AssessmentRecord.this;
   //     assessmentdetails= (TextView) findViewById(R.id.assessmentdetails);
        String e_mail1 = CommonMethods.getPreference(this, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/assessment?e_mail=" + e_mail1;
        Log.d("e_mail assessment", "" + e_mail1);
        getStringReq(url, "" + e_mail1);
        imageAssingmentRecord = (ImageView) findViewById(R.id.imageAssingmentRecord);
        imageView1BackOnClick();
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
        AssessmentData data   = new Gson().fromJson(response, AssessmentData.class);
        gct.example.com.mountcarmelschool.model_assessment_data.Response response1 = data.getResponse();
        for (Assessment assessment : response1.getAssessment()) {
            assessment_details(assessment.getAssessment_Record());
        }
    }

    private void assessment_details(String assessment_record) {
        if (assessment_record != null) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(assessment_record));
            startActivity(i);
        }
    }

    private void imageView1BackOnClick() {
        imageAssingmentRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,Main2Activity.class));
                //finish();
            }
        });
    }
}
