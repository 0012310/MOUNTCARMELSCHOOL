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
import gct.example.com.mountcarmelschool.models_staff_noticdata.NoticData;
import gct.example.com.mountcarmelschool.models_staff_noticdata.Notice;

public class SNotic extends AppCompatActivity {
    ImageView imagesnoticback;
    Context context;
    TextView textatchment, textatchmenticon, texttitle;
    String e_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snotic);
        context = SNotic.this;

        String e_maill = CommonMethods.getPreference(this, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/staffNotice?e_mail=" + e_maill;
        // Toast.makeText(this, "e_mail_in_notic" + e_maill, Toast.LENGTH_SHORT).show();
        Log.d("e_mail notic", "" + e_maill);
        getStringReq(url, "" + e_maill);

        //imagesnoticback= (ImageView) findViewById(R.id.imagesnoticback);

        textatchment = (TextView) findViewById(R.id.textatchment);
        textatchmenticon = (TextView) findViewById(R.id.textatchmenticon);
        texttitle = (TextView) findViewById(R.id.texttitle);


        imagesworthybackOnClick();
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
        NoticData data = new Gson().fromJson(response, NoticData.class);
        gct.example.com.mountcarmelschool.models_staff_noticdata.Response response3 = data.getResponse();
        for (Notice notic : response3.getNotice()) {
            texttitle.setText(notic.getTitle());
            textatchmentOnclick(notic.getAttachment_name());
            textatchmenticonOnclick(notic.getAttachment_name());
        }
    }

    private void textatchmenticonOnclick(final String attachment_name) {
        textatchmenticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attachment_name != null) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(attachment_name));
                    startActivity(i);
                }
            }
        });
    }

    private void textatchmentOnclick(final String attachment_name) {
        textatchment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attachment_name != null) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(attachment_name));
                    startActivity(i);
                }
            }
        });
    }


    private void imagesworthybackOnClick() {
        imagesnoticback.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
    }
}

