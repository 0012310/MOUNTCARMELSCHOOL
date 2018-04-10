package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Sms extends AppCompatActivity {
   Context context;
    ImageView imagesmsback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        context=Sms.this;
        imagesmsback= (ImageView) findViewById(R.id.imagesmsback);
        imagesmsbackOnclick();

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
