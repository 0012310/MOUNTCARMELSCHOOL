package gct.example.com.mountcarmelschool;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class SIntellect extends AppCompatActivity {
    ImageView imagesintellectyback;
    Button buttonSIntellectSubmit;
    EditText editTextSIntellect;
    String edt_valueI,scanner_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintellect);



        if (null!= getIntent().getStringExtra("scanner_resultIntellect")){
            scanner_res = getIntent().getStringExtra("scanner_resultIntellect");
           // Toast.makeText(this, ""+scanner_res, Toast.LENGTH_SHORT).show();
            Log.d("scanner_resultIntellect",scanner_res);

        }
        imagesintellectyback= (ImageView) findViewById(R.id.imagesintellectyback);
        imagesintellectybackOnclick();
        editTextSIntellect= (EditText) findViewById(R.id.editTextSIntellect);
        findViewById(R.id.buttonSIntellectSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_valueI = editTextSIntellect.getText().toString().trim();
                new SendRequest().execute();
                Intent i = new Intent(SIntellect.this,Main2Activity.class);
                startActivity(i);

            }
        });

    }

    public class SendRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL("http://infoes.in/sunil/mcsd/user/givewise");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("t_mail",Main2Activity.email);
                postDataParams.put("adm_no",scanner_res);
                postDataParams.put("type","I");
                postDataParams.put("remark",edt_valueI);
                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
           // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            startActivity(new Intent(SIntellect.this,Swise.class));
            Log.e("params",result);

        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    private void imagesintellectybackOnclick() {
        imagesintellectyback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}