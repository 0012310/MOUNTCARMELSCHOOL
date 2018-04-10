package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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

import gct.example.com.mountcarmelschool.adapter.StaffBdAdapter;
import gct.example.com.mountcarmelschool.adapter.WishesListAdapter;
import gct.example.com.mountcarmelschool.classes.BirtdayWishesForList;
import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;
import gct.example.com.mountcarmelschool.classes.StaffBdForList;
import gct.example.com.mountcarmelschool.model.login_data.InvalidUserData;
import gct.example.com.mountcarmelschool.model_wishinglist_data.WishingList;
import gct.example.com.mountcarmelschool.models_staff_birthday.BirthdatDetails;

public class Message_Wished extends AppCompatActivity {
    Context context;
    ImageView imageswishesdYouback;
    TextView textmessagewish, textmessageReply;

    String e_mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message__wished);
        context=Message_Wished.this;
        imageswishesdYouback= (ImageView) findViewById(R.id.imageswishesdYouback);


        String dob = getIntent().getStringExtra("dob");

        String message = getIntent().getStringExtra("message");

        String reply= getIntent().getStringExtra("reply");




        LocalSharedPreferences.getWishMessage(context);

        LocalSharedPreferences.getReplyMessage(context);



        textmessagewish= (TextView) findViewById(R.id.textmessagewish);
        textmessagewish.setText(message);

        textmessageReply= (TextView) findViewById(R.id.textmessageReply);

        textmessageReply.setText(reply);





        imageswishesdYouback.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}
