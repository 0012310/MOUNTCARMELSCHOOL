package gct.example.com.mountcarmelschool.model_rahul;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import gct.example.com.mountcarmelschool.R;
import gct.example.com.mountcarmelschool.gallary.NewEventDetial;
import me.relex.circleindicator.CircleIndicator;

import static android.view.View.GONE;
import static gct.example.com.mountcarmelschool.R.id.decption;

public class ImageShow extends AppCompatActivity {
    String cid, desc;
    ImageView image, imagegallery2;
    TextView description;
    LinearLayout linearhalf;
    private static ViewPager mPager;
    Context context;
    int img_pos;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        context = ImageShow.this;
        linearhalf = (LinearLayout) findViewById(R.id.linearhalf);
        textView1= (TextView) findViewById(R.id.textView1);
        if (getIntent().getStringExtra("eventname")!=null){
            textView1.setText(getIntent().getStringExtra("eventname"));
        }

        List<Field> listitems = new ArrayList<>();
        img_pos=getIntent().getIntExtra("img_pos",0);
        Intent i = this.getIntent();
        String fieldUrl = i.getStringExtra("imageURL");
        if (fieldUrl!=null){
        //    Toast.makeText(context, ""+fieldUrl, Toast.LENGTH_SHORT).show();
            callImageRequest(fieldUrl);
        }



        cid = i.getExtras().getString("imageURL");
        desc = i.getExtras().getString("des");
        imagegallery2 = (ImageView) findViewById(R.id.imagegallery2);
        imagegallery2Onclick();
        Log.e("url", cid);
        image = (ImageView) findViewById(R.id.image);
        description = (TextView) findViewById(decption);
        description.setMovementMethod(new ScrollingMovementMethod());
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show();
                linearhalf.setVisibility(GONE);
                //Picasso.with(context).load(cid).into(imgfull);
      //          Picasso.with(context).load(cid).resize(600, 200).centerInside().into(imgfull);
            }
        });

    }
    ArrayList<String> strings = new ArrayList<>();
    private void callImageRequest(String fieldUrl) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, fieldUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject parentobj = new JSONObject(response);
                            JSONArray mainobj = parentobj.getJSONArray("image");
                            StringBuffer sb = new StringBuffer();
                            ArrayList l1 = new ArrayList();
                            int i=0;
                            while (i<mainobj.length()){
                                JSONObject finalobject = mainobj.getJSONObject(i);
                                String caseId = finalobject.getString("imgid");
                                String CCNNo = (finalobject.getString("image"));
                                String Claim = (finalobject.getString("description"));
                                description.setText(Claim);
                                strings.add(CCNNo);
                                i++;
                            }
                            init(strings);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void imagegallery2Onclick() {
        imagegallery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void init(ArrayList<String> listOfImages) {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(context, listOfImages));
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
    }



    public class MyAdapter extends PagerAdapter {
        private ArrayList<String> images;
        private LayoutInflater inflater;
        private Context context;

        public MyAdapter(Context context, ArrayList<String> images) {
            this.context = context;
            this.images = images;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View myImageLayout = inflater.inflate(R.layout.slide, view, false);
            ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
            //myImage.setImageResource(images.get(position).getImage());
            final String img = images.get(position);
            Glide.with(context).load(images.get(position)).into(myImage);
            view.addView(myImageLayout, 0);
            myImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Dialog fullscreenDialog = new Dialog(context, R.style.DialogFullscreen);
                    fullscreenDialog.setContentView(R.layout.dialog_fullscreen);
                    ImageView img_full_screen_dialog = (ImageView) fullscreenDialog.findViewById(R.id.img_full_screen_dialog);
                    Glide.with(context).load(img).into(img_full_screen_dialog);
                    img_full_screen_dialog.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                           Toast.makeText(ImageShow.this, "hii", Toast.LENGTH_SHORT).show();
                     //    new DownloadImage().execute("http://www.mountcarmeldelhi.com/getphoto.php?staff_id=10&sch_code=MCSANKIDS");

                            return false;
                        }
                    });
                    ImageView imageneweventlistback = (ImageView) fullscreenDialog.findViewById(R.id.imageneweventlistback);
                    imageneweventlistback.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            fullscreenDialog.dismiss();
                        }
                    });
                    fullscreenDialog.show();
                }

            });
            return myImageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
    private void getImage(String imageUrl, int desiredWidth, int desiredHeight)
    {

    }

}


 /*class DownloadImage extends AsyncTask<String, Void, Bitmap> {

    private String TAG = "DownloadImage";

    private Bitmap downloadImageBitmap(String sUrl) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
            bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
            inputStream.close();
        } catch (Exception e) {
            Log.d(TAG, "Exception 1, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return downloadImageBitmap(params[0]);
    }

    protected void onPostExecute(Bitmap result) {
        //saveImage(getApplicationContext(), result, "my_image.png"); }

    }
}*/