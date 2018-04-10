package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Swise extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ImageView imageswiseback;

    Context context;
    private ZXingScannerView zXingScannerView;
    String scan_res;
    boolean flag1 = false;
    boolean flag2,flag3,flag4 = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swise);
        context = Swise.this;
        imageswiseback = (ImageView) findViewById(R.id.imageswiseback);
        imageswisebackOnclick();




    }


    public void SimageWorthy(View view) {
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
        flag2 = true;





    }


    public void SimageIntellect(View view) {
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
        flag3 =true;



    }

    public void SimageStature(View view) {
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
        flag1 = true;

    }

    public void SimageEmotion(View view) {
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();

        flag4=true;




    }


    private void imageswisebackOnclick() {
        imageswiseback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //onBackPressed();
             //    finish();
                Intent i = new Intent(Swise.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void handleResult(Result result) {
      //  Toast.makeText(getApplicationContext(), result.getText(), Toast.LENGTH_SHORT).show();
        zXingScannerView.resumeCameraPreview(this);
        Log.d("scanner_result : ", "" + result.toString());
        scan_res = result.toString();
        int len = scan_res.length();
        String fin_str = scan_res.substring(len-8,len);

        if (flag1){
            Intent intent = new Intent(context, SStature.class);
            intent.putExtra("scanner_resultStaure", "" +fin_str);
            startActivity(intent);
            flag1 = false;
        }
        if (flag2){
            Intent intent = new Intent(context, SWorthy.class);
            intent.putExtra("scanner_resultWorthy", "" + fin_str);
            startActivity(intent);
            flag2=false;
        }
        if (flag3){

            Intent intent = new Intent(context, SIntellect.class);
            intent.putExtra("scanner_resultIntellect", "" + fin_str);
            startActivity(intent);
            flag3=false;
        }

        if (flag4){
            Intent intent = new Intent(context, SEmotion.class);
            intent.putExtra("scanner_resultEmotion", "" + fin_str);
            startActivity(intent);
            flag4=false;
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
     //   zXingScannerView.stopCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
      //  zXingScannerView.stopCamera();
    }

    @Override
    public void onBackPressed() {
    // super.onBackPressed();
      //startActivity(new Intent(Swise.this,Main2Activity.class ));
      finish();
    }
}
