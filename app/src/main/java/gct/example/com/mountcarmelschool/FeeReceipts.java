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
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.model.login_data.InvalidUserData;
import gct.example.com.mountcarmelschool.model_Feebill_data.FeeBillData;
import gct.example.com.mountcarmelschool.model_feeReceipt_data.FeeReceiptData;

public class FeeReceipts extends AppCompatActivity {
    Context context;
    ImageView imageFeeReceipt;
    WebView webViewFeeReceipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_receipt);
        context=FeeReceipts.this;
        imageFeeReceipt= (ImageView) findViewById(R.id.imageFeeReceipt);
        imageView1BackOnClick();


        webViewFeeReceipt= (WebView) findViewById(R.id.webViewFeeReceipt);


        String e_mail= CommonMethods.getPreference(context,"e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/fee_recipt?e_mail=" + e_mail;
      //  Toast.makeText(context,"e_mail in feereceipt"+e_mail,Toast.LENGTH_SHORT).show();
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
              //  Toast.makeText(context, "" + response, Toast.LENGTH_LONG).show();
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
        FeeReceiptData data = new Gson().fromJson(response, FeeReceiptData.class);
        if (data.getStatus().equals("true")) {
            for (gct.example.com.mountcarmelschool.model_feeReceipt_data.Response response1 : data.getResponse()) {
                String url = response1.getFee_Bill();
              //  Log.d("url_fill", "" + url);
                String receipt_url = response1.getFee_Bill();
                webViewFeeReceipt.getSettings().setJavaScriptEnabled(true);

                webViewFeeReceipt.setWebViewClient(new WebViewClient());
                webViewFeeReceipt.getSettings().setJavaScriptEnabled(true);
                webViewFeeReceipt.setVerticalScrollBarEnabled(true);
                webViewFeeReceipt.setHorizontalScrollBarEnabled(true);

             //   webViewFeeReceipt.loadUrl("http://www.keyboardlabs.com");


                webViewFeeReceipt.loadUrl(""+receipt_url);


            }
        } else if (data.getStatus().equals("false")) {
            InvalidUserData invalidUserData = new Gson().fromJson(response, InvalidUserData.class);
          //  Toast.makeText(context, "" + invalidUserData.getError(), Toast.LENGTH_SHORT).show();
        }


    }













    private void imageView1BackOnClick() {
        imageFeeReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });


    }

}
