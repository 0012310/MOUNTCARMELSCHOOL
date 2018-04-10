package gct.example.com.mountcarmelschool.gallary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import gct.example.com.mountcarmelschool.R;

public class NewEventDetial extends AppCompatActivity {
    ImageView imgvie;
    String cid,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neweventdetails);
        imgvie= (ImageView) findViewById(R.id.imgvie);
        Intent i=this.getIntent();
    //
        //    cid=i.getExtras().getString("imageURL");
        desc = i.getExtras().getString("des");
        Picasso.with(this).load(cid).resize(500,600).into(imgvie);
        imgvie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
