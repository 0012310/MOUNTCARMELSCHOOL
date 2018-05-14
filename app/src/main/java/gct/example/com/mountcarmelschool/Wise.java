package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Wise extends AppCompatActivity {
    Context context;
    ImageView      imageWorthy, imageIntellect,  imageStature, imageEmotion, imageWiseback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wise);
        context=Wise.this;



        imageWiseback= (ImageView) findViewById(R.id.imageWiseback);

        String wise_email = getIntent().getStringExtra("wise_email");

       // Toast.makeText(context, "wise_email"+wise_email, Toast.LENGTH_SHORT).show();
        imageWorthy= (ImageView) findViewById(R.id.imageWorthy);

        imageIntellect= (ImageView) findViewById(R.id.imageIntellect);

        imageStature= (ImageView) findViewById(R.id.imageStature);
        imageEmotion= (ImageView) findViewById(R.id.imageEmotion);
        imageEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Emotion.class));
            }
        });

        imageStature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Stature.class));
            }
        });


        imageIntellect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Intellect.class));
            }
        });

        imageWorthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Worthy.class));
            }
        });
        imageView1BackOnClick();
    }

    private void imageView1BackOnClick() {
        imageWiseback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  startActivity(new Intent(context, Main2Activity.class));
              // onBackPressed();
               finish();



            }
        });


    }

}
