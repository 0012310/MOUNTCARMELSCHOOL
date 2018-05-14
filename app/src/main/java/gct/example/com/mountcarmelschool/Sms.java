package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;

public class Sms extends AppCompatActivity {
   Context context;
    ImageView imagesmsback;
    EditText edittext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        context=Sms.this;
        imagesmsback= (ImageView) findViewById(R.id.imagesmsback);
        imagesmsbackOnclick();
        edittext= (EditText) findViewById(R.id.edittext);
      // edittext.setText(LocalSharedPreferences.getToken(context));

      //  edittext.setText("" + LocalSharedPreferences.getToken(context));



    }

    private void imagesmsbackOnclick() {
        imagesmsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
