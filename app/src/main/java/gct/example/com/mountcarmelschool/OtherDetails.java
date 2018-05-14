package gct.example.com.mountcarmelschool;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.general_staff_details_data.GeneralStaffDetailsData;
import gct.example.com.mountcarmelschool.general_staff_details_data.Other;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherDetails extends Fragment {
    TextView textdateofbirth,textjoining,textqualification,textdepartment,textStafMob,textstaffroom;
    RequestQueue requestQueue;  Context context;



    public OtherDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other_details, container, false);
        context = container.getContext();


        textdateofbirth = (TextView) view.findViewById(R.id.textdateofbirth);
        textjoining = (TextView) view.findViewById(R.id.textjoining);
        textqualification = (TextView) view.findViewById(R.id.textqualification);
        textStafMob= (TextView) view.findViewById(R.id.textStafMob);
        textdepartment = (TextView) view.findViewById(R.id.textdepartment);
        textstaffroom = (TextView) view.findViewById(R.id.textstaffroom);
        String e_mail = CommonMethods.getPreference(context, "e_mail");
        //http://infoes.in/sunil/mcsd/user/staffProfile?e_mail=anupamakumar@mountcarmeldelhi.com
        String url = "http://infoes.in/sunil/mcsd/user/staffProfile?e_mail=" + e_mail;
        Log.d("e_mailstaffother", e_mail);
        getStringReq(url, e_mail);
        return view;
    }


    private void getStringReq(String url, final String e_mail) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest strReq = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responsestaffOther", response);
                getDetailsData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d("", "Error: " + volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", e_mail);
                return params;
            }
        };
        queue.add(strReq);
    }


    private void getDetailsData(String response) {
        GeneralStaffDetailsData data = new Gson().fromJson(response, GeneralStaffDetailsData.class);
        if (data.getStatus().equals("true")) {

            gct.example.com.mountcarmelschool.general_staff_details_data.Response response1 = data.getResponse();
            for (Other other : response1.getOther()) {

                // textdateofbirth.setText(other.getDob());

                String str =(other.getDob());
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
                Date date = null;
                try {
                    date = (Date)formatter.parse(str);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat newFormat = new SimpleDateFormat("DD-MM-yyyy");
                String finalString = newFormat.format(date);
                textdateofbirth.setText(finalString);

                // textjoining.setText(other.getDate_of_joining());

                String str1 = (other.getDate_of_joining());
                DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-DD");
                Date date1 = null;
                try {
                    date1 = (Date)formatter1.parse(str1);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat newFormat1 = new SimpleDateFormat("MM-DD-yyyy");
                String finalString1 = newFormat1.format(date1);

                textjoining.setText(finalString1);


                textqualification.setText(other.getQualification());
                textStafMob.setText(other.getStaff_mob());
                textdepartment.setText(other.getDepartment());
                textstaffroom.setText(other.getRoom_alloted());

            }
        }
        else
        {
            Toast.makeText(context, "Sorry please ", Toast.LENGTH_SHORT).show();
        }
    }
}