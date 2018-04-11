package gct.example.com.mountcarmelschool;

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
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.modal_medical.medical_data.MedicalData;
import gct.example.com.mountcarmelschool.model.login_data.InvalidUserData;
import gct.example.com.mountcarmelschool.model_Feebill_data.FeeBillData;
import gct.example.com.mountcarmelschool.model_transportReceipt_data.TranspostreceiptData;


import static android.R.attr.data;

public class FeeBills extends AppCompatActivity {
    ImageView imageFeebillback;
    Context context;
    RequestQueue requestQueue;
    WebView webViewFeeBill;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_bill);
        context = FeeBills.this;
        webViewFeeBill= (WebView) findViewById(R.id.webViewFeeBill);

        progressDialog = new ProgressDialog(FeeBills.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        imageFeebillback= (ImageView) findViewById(R.id.imageFeebillback);


        String e_mail = CommonMethods.getPreference(context, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/fee" +"_bill?e_mail=" + e_mail;

        Log.d("e_mail Feebill", e_mail);
        getStringReq(url, e_mail);

        imageFeebillbackOnClick();

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
        FeeBillData data = new Gson().fromJson(response, FeeBillData.class);
        if (data.getStatus().equals("true")) {
            for (gct.example.com.mountcarmelschool.model_Feebill_data.Response response1 : data.getResponse()) {
                String receipt_url = response1.getFee_Bill();
                webViewFeeBill.getSettings().setJavaScriptEnabled(true);


                webViewFeeBill.setWebViewClient(new WebViewClient());
                webViewFeeBill.getSettings().setJavaScriptEnabled(true);
                webViewFeeBill.setVerticalScrollBarEnabled(true);
                webViewFeeBill.setHorizontalScrollBarEnabled(true);
                webViewFeeBill.loadUrl("http://pay.mountcarmeldelhi.com/test/pay/invoice.php?data=C86dbNQPzwsqMpPpq~11153466~III~MCSAN~2017-18~FOURTH~app&p=d");

                webViewFeeBill.setWebViewClient(new WebViewClient() {

                    @Override
                    public void onPageCommitVisible(WebView view, String url) {
                        super.onPageCommitVisible(view, url);
                        progressDialog.dismiss();
                    }
                });



            //   webViewFeeBill.loadUrl(""+receipt_url);
                Log.d("abc",receipt_url);
            }
        }
    }

 @Override
    public void onBackPressed() {


        if (webViewFeeBill.canGoBack()) {
            // If web view have back history, then go to the web view back history
            webViewFeeBill.goBack();
            Snackbar.make(webViewFeeBill, "Go to back history", Snackbar.LENGTH_SHORT).show();
        } else {
            // Ask the user to exit the app or stay in here
            finish();

        }

    }




    private void imageFeebillbackOnClick() {
        imageFeebillback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (webViewFeeBill.canGoBack()) {
                    // If web view have back history, then go to the web view back history
                    webViewFeeBill.goBack();
                    Snackbar.make(webViewFeeBill, "Go to back history", Snackbar.LENGTH_SHORT).show();
                } else {
                    // Ask the user to exit the app or stay in here
                    finish();

                }

            }
        });


    }


}
