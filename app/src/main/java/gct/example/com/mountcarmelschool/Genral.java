package gct.example.com.mountcarmelschool;


import android.app.VoiceInteractor;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.models.general_details_data.General;
import gct.example.com.mountcarmelschool.models.general_details_data.GeneralDetailsData;
import gct.example.com.mountcarmelschool.models.general_details_data.GeneralErrorData;


/**
 * A simple {@link Fragment} subclass.
 */

public class Genral extends Fragment {


    RequestQueue requestQueue;
    Context context;
    public static String name;
    TextView text_addressL2, text_admission_No, text_doa, text_name, text_sex, text_nationality, text_dob, text_email, text_mobile_no, text_transport, text_addressL1, text_city, text_state, text_country, text_pin, text_phone_no;

    public Genral() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_genrel, container, false);
        context = container.getContext();

        text_name = (TextView) v.findViewById(R.id.text_name);
        text_admission_No = (TextView) v.findViewById(R.id.text_admission_No);
        text_doa = (TextView) v.findViewById(R.id.text_doa);
        text_sex = (TextView) v.findViewById(R.id.text_sex);
        text_nationality = (TextView) v.findViewById(R.id.text_nationality);
        text_dob = (TextView) v.findViewById(R.id.text_dob);
        text_email = (TextView) v.findViewById(R.id.text_email);
        text_mobile_no = (TextView) v.findViewById(R.id.text_mobile_no);
        text_transport = (TextView) v.findViewById(R.id.text_transport);
        text_addressL1 = (TextView) v.findViewById(R.id.text_addressL1);
        text_city = (TextView) v.findViewById(R.id.text_city);
        text_addressL2 = (TextView) v.findViewById(R.id.text_addressL2);
        text_state = (TextView) v.findViewById(R.id.text_state);
        text_country = (TextView) v.findViewById(R.id.text_country);
        text_pin = (TextView) v.findViewById(R.id.text_pin);
        text_phone_no = (TextView) v.findViewById(R.id.text_phone_no);

        String e_mail = CommonMethods.getPreference(context, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/generalProfile?e_mail=" + e_mail;
        Log.d("e_mail general", e_mail);
        getStringReq(url, e_mail);
        return v;
    }

    private void getStringReq(String URL, final String email) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest strReq = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseGeneral", response);
                getDetailsData(response);
               // Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                // params.put("id", "322");    //322
                params.put("email", email);
                //params.put("password", "abc@androidhive.info");
                return params;
            }
        };
        queue.add(strReq);
    }

    private void getDetailsData(String response) {
        GeneralDetailsData data = new Gson().fromJson(response, GeneralDetailsData.class);
        gct.example.com.mountcarmelschool.models.general_details_data.Response response1 = data.getResponse();
        if (data.getStatus().equals("true")) {
            for (General general : response1.getGeneral()) {
              //  Toast.makeText(context, "general" + general.getAdm_no(), Toast.LENGTH_SHORT).show();
                text_admission_No.setText(general.getAdm_no());
                text_doa.setText(general.getAd_date());
                text_name.setText(general.getFirstname());
                name=general.getFirstname();
                text_sex.setText(general.getSex());
                text_nationality.setText(general.getNation_type());
                text_dob.setText(general.getDob());
                text_email.setText(general.getE_mail());
                text_mobile_no.setText(general.getMobile_no_for_sms());
                text_transport.setText(general.getTransport());
                text_addressL1.setText(general.getAddress1());
                text_addressL2.setText(general.getAddress2());
                text_city.setText(general.getCity());
                text_state.setText(general.getState());
                text_country.setText(general.getCountry());
                text_pin.setText(general.getPin_code());
                text_phone_no.setText(general.getPhone_no());
            }
        } else if (data.getStatus().equals("false")) {
            GeneralErrorData errorData = new Gson().fromJson(response, GeneralErrorData.class);
            if (errorData.getStatus().equals("false")) {
                Toast.makeText(context, "Please Try Again after sometime.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}