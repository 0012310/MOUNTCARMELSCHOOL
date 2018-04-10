package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.adapter.StaffBdAdapter;
import gct.example.com.mountcarmelschool.adapter.WishesListAdapter;
import gct.example.com.mountcarmelschool.classes.BirtdayWishesForList;
import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;
import gct.example.com.mountcarmelschool.classes.StaffBdForList;
import gct.example.com.mountcarmelschool.general_staff_details_data.General;
import gct.example.com.mountcarmelschool.model.login_data.InvalidUserData;
import gct.example.com.mountcarmelschool.model_wishinglist_data.WishingList;
import gct.example.com.mountcarmelschool.models_staff_birthday.BirthdatDetails;
import gct.example.com.mountcarmelschool.models_staff_noticdata.NoticData;
import gct.example.com.mountcarmelschool.models_staff_noticdata.Notice;

import static android.R.attr.data;
import static android.R.attr.logo;
import static android.R.id.message;

public class StaffBd extends AppCompatActivity {

    Context context;
    ImageView imagebdback,imgcake;
    TextView textfirstnameofstaff, textlasttnameofstaff,textdateofbirth,textviewwishDetails;
    String e_mail;
    RecyclerView staffbirtdayrecycler,  staffbirtdayrecyclerWishesDetail;
    StaffBdAdapter staffBdAdapter;
    WishesListAdapter wishesListAdapter;



    private ArrayList<StaffBdForList> staffBdForListArrayList = new ArrayList<>();

    private ArrayList<BirtdayWishesForList> birtdayWishesForListArrayList=new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_bd);
        context = StaffBd.this;

        staffBdForListArrayList = new ArrayList<>();
        birtdayWishesForListArrayList=new ArrayList<>();

        staffbirtdayrecycler = (RecyclerView) findViewById(R.id.staffbirtdayrecycler);
        staffbirtdayrecyclerWishesDetail= (RecyclerView) findViewById(R.id.staffbirtdayrecyclerWishesDetail);

        imagebdback = (ImageView) findViewById(R.id.imagebdback);
        textdateofbirth = (TextView) findViewById(R.id.textdateofbirth);
        textfirstnameofstaff = (TextView) findViewById(R.id.textfirstnameofstaff);
        textlasttnameofstaff = (TextView) findViewById(R.id.textlasttnameofstaff);


        textviewwishDetails= (TextView) findViewById(R.id.textviewwishDetails);





        imgcake = (ImageView) findViewById(R.id.imgcake);





        imagebdbackOnclick();
        String e_maill = CommonMethods.getPreference(this, "e_mail");

        String url = "http://infoes.in/sunil/mcsd/user/getstaffbirthday?e_mail=" + e_maill;

        Log.d("e_mail bd", "" + e_maill);
        getStringReq(url, "" + e_maill);


        String urlWish = "http://infoes.in/sunil/mcsd/user/getbirthdaywish?e_mail=" + e_maill;
        getStringReqW(urlWish,""+e_maill);















    }
    ///// here coming birtday details

    private void getStringReq(String url, final String e_mail) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
                        Log.d("response_staffbday", response);
                        Log.d("loginemail",e_mail);
                        getDetailsData(response);
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
                params.put("e_mail", e_mail);
                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }

    private void getDetailsData(String response) {
        BirthdatDetails data = new Gson().fromJson(response, BirthdatDetails.class);
        if (data.getStatus().equals("true")) {
            gct.example.com.mountcarmelschool.models_staff_birthday.Response response2 = data.getResponse();
            for (gct.example.com.mountcarmelschool.models_staff_birthday.General general : response2.getGeneral()) {

                StaffBdForList staffBdForList = new StaffBdForList();
                staffBdForList.setStaff_fname(general.getStaff_fname());
                staffBdForList.setStaff_lname(general.getStaff_lname());
                staffBdForList.setDob(general.getDob());
                staffBdForList.setE_mail(general.getE_mail());
                staffBdForList.setStaff_mob(general.getStaff_mob());
                staffBdForList.setStatus(general.getStatus());
                staffBdForList.setStaff_id(general.getStaff_id());
                staffBdForList.setImg(general.getImg());

                staffBdForList.setMsg(general.getMsg());
                staffBdForList.setReply(general.getReply());

                staffBdForListArrayList.add(staffBdForList);
            }
        } else if (data.getStatus().equals("false")) {
            InvalidUserData invalidUserData = new Gson().fromJson(response, InvalidUserData.class);
            Toast.makeText(context, "" + invalidUserData.getError(), Toast.LENGTH_SHORT).show();
        }
       // Log.d("reply_",""+new StaffBdForList().getReply());

        staffBdAdapter = new StaffBdAdapter(staffBdForListArrayList, context);
        staffbirtdayrecycler.setLayoutManager(new LinearLayoutManager(this));
        staffbirtdayrecycler.setAdapter(staffBdAdapter);
    }




    //////////////   here coming wishes

    private void getStringReqW(String url, final String e_mail) {


        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
                       Log.d("response_wishes", response);
                       Log.d("login",e_mail);
                       getDetailsDataW(response);
                      //  Toast.makeText(context, response+ "", Toast.LENGTH_SHORT).show();
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
                params.put("e_mail", e_mail);
                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }

    private void getDetailsDataW(String response) {
        WishingList data = new Gson().fromJson(response, WishingList.class);
        if (data.getStatus().equals("true")) {
            gct.example.com.mountcarmelschool.model_wishinglist_data.Response response2 = data.getResponse();
            for (gct.example.com.mountcarmelschool.model_wishinglist_data.General general : response2.getGeneral()) {
                BirtdayWishesForList birtdayWishesForList = new BirtdayWishesForList();

                birtdayWishesForList.setTeacher_name(general.getTeacher_name());
                birtdayWishesForList.setMessage(general.getMessage());
                birtdayWishesForList.setStaff_id(general.getStaff_id());
                birtdayWishesForList.setStatus(general.getStatus());



                birtdayWishesForListArrayList.add(birtdayWishesForList);




            }
        } else if (data.getStatus().equals("false")) {
            InvalidUserData invalidUserData = new Gson().fromJson(response, InvalidUserData.class);
            Toast.makeText(context, "" + invalidUserData.getError(), Toast.LENGTH_SHORT).show();
        }
        wishesListAdapter = new WishesListAdapter(birtdayWishesForListArrayList, context);
        staffbirtdayrecyclerWishesDetail.setLayoutManager(new LinearLayoutManager(this));
        staffbirtdayrecyclerWishesDetail.setAdapter(wishesListAdapter);
    }



    private void imagebdbackOnclick() {
        imagebdback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
