package gct.example.com.mountcarmelschool.model_rahul;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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

import java.util.ArrayList;

import gct.example.com.mountcarmelschool.R;
import gct.example.com.mountcarmelschool.gallary.NewEventDetial;
import me.relex.circleindicator.CircleIndicator;

import static android.view.View.GONE;

public class ImageShow extends AppCompatActivity {

    String cid, desc;
    ImageView image, imagegallery2;
    ImageView imgfull;
    TextView description;
    ScrollView scrollImageShow;
    LinearLayout linearhalf;
    private static ViewPager mPager;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        context = ImageShow.this;
        imgfull = (ImageView) findViewById(R.id.imgfull);
        linearhalf = (LinearLayout) findViewById(R.id.linearhalf);
        scrollImageShow = (ScrollView) findViewById(R.id.scrollImageShow);
        Intent i = this.getIntent();
        String fieldUrl = i.getStringExtra("FieldUrl");
        if (fieldUrl!=null){
            Toast.makeText(context, ""+fieldUrl, Toast.LENGTH_SHORT).show();
            callImageRequest(fieldUrl);
        }



        cid = i.getExtras().getString("imageURL");
        desc = i.getExtras().getString("des");
        imagegallery2 = (ImageView) findViewById(R.id.imagegallery2);
        imagegallery2Onclick();
        Log.e("url", cid);
        image = (ImageView) findViewById(R.id.image);
        description = (TextView) findViewById(R.id.decption);
        description.setText(desc);
        //Picasso.with(this).load(cid).resize(500, 400).into(image);
        imgfull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearhalf.setVisibility(GONE);
                Picasso.with(context).load(cid).resize(750, 700).into(imgfull);

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
                            //fieldList = new ArrayList<>();
                            int i=0;
                            while (i<mainobj.length()){
                                JSONObject finalobject = mainobj.getJSONObject(i);
                                String caseId = finalobject.getString("imgid");
                                String CCNNo = (finalobject.getString("image"));
                                String Claim = (finalobject.getString("description"));
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
        /*for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);*/
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
            Glide.with(context).load(images.get(position)).into(myImage);

            view.addView(myImageLayout, 0);
            return myImageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }

}
