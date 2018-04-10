package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;

public class Reply extends AppCompatActivity {

    Context context;
    ImageView imageReplydback;
    EditText editTextReply;
    Button buttonrReplySubmit;
    final String URL_MESS = "http://infoes.in/sunil/mcsd/user/replybirthday";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        context = Reply.this;
        imageReplydback = (ImageView) findViewById(R.id.imageReplydback);
        editTextReply = (EditText) findViewById(R.id.editTextReply);
        buttonrReplySubmit = (Button) findViewById(R.id.buttonrReplySubmit);

        imageReplydback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        buttonrReplySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reciever_id = getIntent().getStringExtra("staff_id");

                if (!TextUtils.isEmpty(editTextReply.getText().toString())) {
                    sendStringReq(URL_MESS, LocalSharedPreferences.getUserEmail(context), editTextReply.getText().toString(), reciever_id);


                    LocalSharedPreferences.saveReplyMessage(context,editTextReply.getText().toString());
                    LocalSharedPreferences.getReplyMessage(context);

                    Log.d("your_wish",LocalSharedPreferences.getReplyMessage(context));

                }
            }
        });

    }
    private void sendStringReq(String url, final String sender_email, final String staff_id, final String reply) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("sendDataResult555", "" + response);

                    //    Toast.makeText(context, response+"", Toast.LENGTH_SHORT).show();

                        Intent i =new Intent(Reply.this,StaffBd.class);
                        startActivity(i);

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
                params.put("sender_id", sender_email);
                params.put("reply",staff_id);
                params.put("reciever_id",reply);

                Log.d("sender_email",""+LocalSharedPreferences.getUserEmail(context));
                Log.d("staff_id",""+staff_id);
                Log.d("Msg",""+editTextReply.getText().toString());

                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }



}

