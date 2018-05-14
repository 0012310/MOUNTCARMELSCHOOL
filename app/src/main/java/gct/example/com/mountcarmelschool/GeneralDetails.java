//package gct.example.com.mountcarmelschool;
package gct.example.com.mountcarmelschool;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.general_staff_details_data.General;
import gct.example.com.mountcarmelschool.general_staff_details_data.GeneralStaffDetailsData;
import gct.example.com.mountcarmelschool.general_staff_details_data.Other;

import static gct.example.com.mountcarmelschool.Login.email;


/**
 * A simple {@link Fragment} subclass.
 */

public class GeneralDetails extends Fragment {


    RequestQueue requestQueue;
    TextView textStaffName, textStaffLName, textStafEmail, textStafMob, textStafAddress, textStafCity, textStaffCountry, textStafPin, textStafPh;
    Context context;


    public GeneralDetails() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general_details, container, false);
        context = container.getContext();

        textStaffName = (TextView) view.findViewById(R.id.textStaffName);
        textStaffLName = (TextView) view.findViewById(R.id.textStaffLName);
        textStafEmail = (TextView) view.findViewById(R.id.textStafEmail);
        textStafMob = (TextView) view.findViewById(R.id.textStafMob);
        textStafAddress = (TextView) view.findViewById(R.id.textStafAddress);
        textStafCity = (TextView) view.findViewById(R.id.textStafCity);
        textStaffCountry = (TextView) view.findViewById(R.id.textStaffCountry);
        textStafPin = (TextView) view.findViewById(R.id.textStafPin);
        textStafPh = (TextView) view.findViewById(R.id.textStafPh);

        String e_mail = CommonMethods.getPreference(context, "e_mail");
        //http://infoes.in/sunil/mcsd/user/staffProfile?e_mail=anupamakumar@mountcarmeldelhi.com
        String url = "http://infoes.in/sunil/mcsd/user/staffProfile?e_mail=" + e_mail;
        Log.d("e_mailstaffgeneral", e_mail);
        getStringReq(url, e_mail);


        return view;
    }

    private void getStringReq(String url, final String e_mail) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest strReq = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responsestaffGeneral", response);
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
            for (General general : response1.getGeneral()) {
                textStaffName.setText(general.getStaff_fname());
                textStaffLName.setText(general.getStaff_lname());
                textStafEmail.setText(general.getE_mail());
                textStafMob.setText(general.getStaff_mob());
                textStafAddress.setText(general.getStaff_add());
                textStafCity.setText(general.getCity());
                textStafPin.setText(general.getPincode());
                textStafPh.setText(general.getStaff_ph());
                textStaffCountry.setText(general.getCountry());
            }
        }
        else
        {
            Toast.makeText(context, "Sorry no data ", Toast.LENGTH_SHORT).show();
        }


       /* for (Other other : response1.getOther()) {

        }*/
    }
}