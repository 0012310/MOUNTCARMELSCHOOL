package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.main_notice_data.Notice;
import gct.example.com.mountcarmelschool.model_Feebill_data.FeeBillData;
import gct.example.com.mountcarmelschool.model_transportReceipt_data.TranspostreceiptData;

public class
TransportReceipt extends AppCompatActivity {

    ImageView imageTransportReceipt;
    Context context;

    WebView webViewTransportReceipt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_receipt);
        context = TransportReceipt.this;
        webViewTransportReceipt = (WebView) findViewById(R.id.webViewTransportReceipt);
        imageTransportReceipt = (ImageView) findViewById(R.id.imageTransportReceipt);
        imageView1BackOnClick();


        String e_mail = CommonMethods.getPreference(context, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/transport_recipt?e_mail=" + e_mail;
      //  Toast.makeText(context, "e_mail in feereceipt" + e_mail, Toast.LENGTH_SHORT).show();
        Log.d("e_mail Feebill", e_mail);
        getStringReq(url, e_mail);
    }

    private void getStringReq(String URL, final String email) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest strReq = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responsefeereceipt", response);
                getDetailsData(response);
             //   Toast.makeText(context, "" + response, Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
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

    private void getDetailsData(String response) {
        TranspostreceiptData data = new Gson().fromJson(response, TranspostreceiptData.class);
        if (data.getStatus().equals("true")) {
            for (gct.example.com.mountcarmelschool.model_transportReceipt_data.Response response1 : data.getResponse()) {
                String receipt_url = response1.getFee_Bill();

                webViewTransportReceipt.getSettings().setJavaScriptEnabled(true);

                webViewTransportReceipt.setWebViewClient(new WebViewClient());
                webViewTransportReceipt.getSettings().setJavaScriptEnabled(true);
                webViewTransportReceipt.setVerticalScrollBarEnabled(true);
                webViewTransportReceipt.setHorizontalScrollBarEnabled(true);
                webViewTransportReceipt.loadUrl("http://www.keyboardlabs.com");

                webViewTransportReceipt.loadUrl(""+receipt_url);
            }
        }
    }

    private void imageView1BackOnClick() {
        imageTransportReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
