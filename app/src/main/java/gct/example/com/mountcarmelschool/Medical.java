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
import gct.example.com.mountcarmelschool.modal_medical.medical_data.MedicalData;
import gct.example.com.mountcarmelschool.model.login_data.InvalidUserData;


/**
 * A simple {@link Fragment} subclass.
 */
public class Medical extends Fragment {
    RequestQueue requestQueue;
    Context context;
    TextView textbgroup, textallergies, textphysical, textTeeth, textWeight, textvisionL, textvisionR, textHygine, textoral, textheight, textNoab, textviewname ;


    public Medical() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical, container, false);
        context = container.getContext();

        textbgroup = (TextView) view.findViewById(R.id.textbgroup);
        textallergies = (TextView) view.findViewById(R.id.textallergies);
        textphysical = (TextView) view.findViewById(R.id.textphysical);
        textTeeth = (TextView) view.findViewById(R.id.textTeeth);
        textWeight = (TextView) view.findViewById(R.id.textWeight);
        textheight = (TextView) view.findViewById(R.id.textheight);

        textvisionL = (TextView) view.findViewById(R.id.textvisionL);
        textvisionR = (TextView) view.findViewById(R.id.textvisionR);
        textHygine = (TextView) view.findViewById(R.id.textHygine);
        textoral = (TextView) view.findViewById(R.id.textoral);
        textNoab = (TextView) view.findViewById(R.id.textNoab);





        String e_mail = CommonMethods.getPreference(context, "e_mail");
        //String e_mail = "abc@gmail.com";
        String url = "http://infoes.in/sunil/mcsd/user/medicalProfile?e_mail=" + e_mail;
        // Toast.makeText(context, "e_mail in general" + e_mail, Toast.LENGTH_SHORT).show();
        Log.d("e_mail general", e_mail);
        getStringReq(url, e_mail);


        return view;
    }

    private void getStringReq(String URL, final String email) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest strReq = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseMedical", response);
                getMedicalData(response);
                //  Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
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

    private void getMedicalData(String response) {
        MedicalData data = new Gson().fromJson(response, MedicalData.class);
        if (data.getStatus().equals("true")) {
            gct.example.com.mountcarmelschool.modal_medical.medical_data.Response response1 = data.getResponse();
            for (gct.example.com.mountcarmelschool.modal_medical.medical_data.Medical medical : response1.getMedical()) {
                //   Toast.makeText(context, "medical" + medical.getAdm_no(), Toast.LENGTH_SHORT).show();

                textbgroup.setText(medical.getBlood_group());
                textallergies.setText(medical.getAllergies());
                textphysical.setText(medical.getPsychological_ailment());
                textTeeth.setText(medical.getTeeth());
                textWeight.setText(medical.getWeight());
                textheight.setText(medical.getHeight());
                textvisionL.setText(medical.getVision_left());
                textvisionR.setText(medical.getVision_right());
                textHygine.setText(medical.getHygiene());
                textoral.setText(medical.getOral());
                textNoab.setText(medical.getSpecific_ailments());


            }
        } else if (data.getStatus().equals("false")) {
            InvalidUserData invalidUserData = new Gson().fromJson(response, InvalidUserData.class);
            Toast.makeText(context, "" + invalidUserData.getError(), Toast.LENGTH_SHORT).show();
        }
    }
}
