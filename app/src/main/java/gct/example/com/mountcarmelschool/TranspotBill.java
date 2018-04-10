package gct.example.com.mountcarmelschool;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.model_Feebill_data.FeeBillData;
import gct.example.com.mountcarmelschool.model_Transportbill_data.Transportbill;

public class TranspotBill extends AppCompatActivity {
    ImageView imageviewback;
    Button download;
    Context context;
    WebView webViewTransportFeeBill;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transpotbill);
        context = TranspotBill.this;
        progressDialog = new ProgressDialog(TranspotBill.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        String e_mail = CommonMethods.getPreference(context, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/transport_bill?e_mail=" + e_mail;

        Log.d("e_mail Feebill", e_mail);
        getStringReq(url, e_mail);


        imageviewback = (ImageView) findViewById(R.id.imageviewback);
        imageviewbackOnclick();
        webViewTransportFeeBill = (WebView) findViewById(R.id.webViewTransportFeeBill);

        webViewTransportFeeBill.getSettings().setJavaScriptEnabled(true);
        webViewTransportFeeBill.getSettings().setJavaScriptEnabled(true);
        webViewTransportFeeBill.setVerticalScrollBarEnabled(true);
        webViewTransportFeeBill.setHorizontalScrollBarEnabled(true);
        webViewTransportFeeBill.loadUrl("http://pay.mountcarmeldelhi.com/test_tr/pay/invoice.php?data=58be4d3737d362936bf45557~12171946~Nursery~MCSANKIDS~2017-2018~FOURTH~app&p=d");


        webViewTransportFeeBill.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);

            }
        });


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
        Transportbill data = new Gson().fromJson(response, Transportbill.class);
        if (data.getStatus().equals("true")) {
            for (gct.example.com.mountcarmelschool.model_Transportbill_data.Response response1 : data.getResponse()) {
                String receipt_url = response1.getTransport_Bill();

                webViewTransportFeeBill.getSettings().setJavaScriptEnabled(true);
                webViewTransportFeeBill.setWebViewClient(new WebViewClient());
                webViewTransportFeeBill.getSettings().setJavaScriptEnabled(true);
                webViewTransportFeeBill.setVerticalScrollBarEnabled(true);
                webViewTransportFeeBill.setHorizontalScrollBarEnabled(true);
                webViewTransportFeeBill.loadUrl("http://pay.mountcarmeldelhi.com/test_tr/pay/invoice.php?data=C86dbNQPzwsqMpPpq~11153466~III~MCSAN~2017-18~FOURTH~app&p=d");


                webViewTransportFeeBill.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageCommitVisible(WebView view, String url) {
                        super.onPageCommitVisible(view, url);
                        progressDialog.dismiss();
                    }
                });

                //      webViewTransportFeeBill.loadUrl("" + receipt_url);
                Log.d("abc", receipt_url);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (webViewTransportFeeBill.canGoBack()) {
            webViewTransportFeeBill.goBack();
            Snackbar.make(webViewTransportFeeBill, "Go to back history", Snackbar.LENGTH_SHORT).show();
        } else {
            finish();
        }

    }

    private void imageviewbackOnclick() {
        imageviewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}

