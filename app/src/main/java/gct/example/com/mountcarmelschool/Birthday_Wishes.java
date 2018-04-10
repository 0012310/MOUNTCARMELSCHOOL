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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.adapter.SAttendanceAdapter;
import gct.example.com.mountcarmelschool.adapter.StaffBdAdapter;
import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;
import gct.example.com.mountcarmelschool.classes.SAttandanceForList;
import gct.example.com.mountcarmelschool.classes.StaffBdForList;

import static android.R.attr.data;

public class Birthday_Wishes extends AppCompatActivity {
     ImageView  imageswishesback;
     Context context;
    Button buttonWishesSubmit;
    StaffBdAdapter staffBdAdapter;
    EditText editTextWishes;

    final String URL_MSG = "http://infoes.in/sunil/mcsd/user/wish_staff_dob";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday__wishes);
        context = Birthday_Wishes.this;
        editTextWishes= (EditText) findViewById(R.id.editTextWishes);
        imageswishesback = (ImageView) findViewById(R.id.imageswishesback);
        imageswishesback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonWishesSubmit = (Button) findViewById(R.id.buttonWishesSubmit);
        buttonWishesSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = getIntent().getStringExtra("status");
                String dob = getIntent().getStringExtra("dob");
                String staff = getIntent().getStringExtra("staff_id");




                if (!TextUtils.isEmpty(editTextWishes.getText().toString())){
                    sendStringReq(URL_MSG,LocalSharedPreferences.getUserEmail(context),staff,editTextWishes.getText().toString(),dob);

                    LocalSharedPreferences.saveWishMessage(context,editTextWishes.getText().toString());


                    LocalSharedPreferences.getWishMessage(context);
                    Log.d("your_wish",LocalSharedPreferences.getWishMessage(context));

                }



            }
        });
    }

    private void sendStringReq(String url, final String sender_email , final String staff_id ,final String message,final String dob ) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("sendDataResult555", "" + response);

                        Toast.makeText(context, response+"", Toast.LENGTH_SHORT).show();

                        Intent i =new Intent(Birthday_Wishes.this,Main2Activity.class);
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
                params.put("sender_email", sender_email);
                params.put("staff_id",staff_id);
                params.put("message",message);
                params.put("dob",dob);

              Log.d("sender_email",""+sender_email);
                Log.d("staff_id",""+staff_id);
                Log.d("wishes",""+message);
                Log.d("bd",""+dob);


                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }



}
