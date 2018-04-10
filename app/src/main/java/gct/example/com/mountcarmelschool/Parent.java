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
import gct.example.com.mountcarmelschool.model.login_data.InvalidUserData;
import gct.example.com.mountcarmelschool.model_parent.Father;
import gct.example.com.mountcarmelschool.model_parent.Mother;
import gct.example.com.mountcarmelschool.model_parent.ParentDetailsData;


public class Parent extends Fragment {

    RequestQueue requestQueue;
    Context context;
    TextView textFatherName, textfqualification, textfoccupation, textforg, textfded, textfpan, textfofficeadd, textfofficeno, textfemail, textfmob;
    TextView textMotherrName, textMqul, textMocu, textMorg, textMdeg, textMPan, textMOffAdd, textMOffNo, textMEmail, textMMOb;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_parent, container, false);
        context = container.getContext();

        textFatherName = (TextView) v.findViewById(R.id.textFatherName);
        textfqualification = (TextView) v.findViewById(R.id.textfqualification);
        textfoccupation = (TextView) v.findViewById(R.id.textfoccupation);
        textforg = (TextView) v.findViewById(R.id.textforg);
        textfded = (TextView) v.findViewById(R.id.textfded);
        textfpan = (TextView) v.findViewById(R.id.textfpan);
        textfofficeadd = (TextView) v.findViewById(R.id.textfofficeadd);
        textfofficeno = (TextView) v.findViewById(R.id.textfofficeno);
        textfemail = (TextView) v.findViewById(R.id.textfemail);
        textfmob = (TextView) v.findViewById(R.id.textfmob);


        textMotherrName = (TextView) v.findViewById(R.id.textMotherrName);
        textMqul = (TextView) v.findViewById(R.id.textMqul);
        textMocu = (TextView) v.findViewById(R.id.textMocu);

        textMorg = (TextView) v.findViewById(R.id.textMorg);
        textMdeg = (TextView) v.findViewById(R.id.textMdeg);
        textMPan = (TextView) v.findViewById(R.id.textMPan);
        textMOffAdd = (TextView) v.findViewById(R.id.textMOffAdd);
        textMOffNo = (TextView) v.findViewById(R.id.textMOffNo);
        textMEmail = (TextView) v.findViewById(R.id.textMEmail);
        textMMOb = (TextView) v.findViewById(R.id.textMMOb);


        String e_mail = CommonMethods.getPreference(context, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/parentProfile?e_mail=" + e_mail;
        //  Toast.makeText(context, "e_mail_in_parent" + e_mail, Toast.LENGTH_SHORT).show();
        Log.d("e_mail parent", e_mail);
        getStringReq(url, e_mail);
        return v;
    }


    private void getStringReq(String URL, final String email) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest strReq = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseParent", response);
                getDetailsData(response);
                //  Toast.makeText(context, "" + response, Toast.LENGTH_LONG).show();
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
                params.put("email", email);
                return params;
            }
        };
        queue.add(strReq);
    }

    private void getDetailsData(String response) {
        ParentDetailsData data = new Gson().fromJson(response, ParentDetailsData.class);
        if (data.getStatus().equals("true")) {
           gct.example.com.mountcarmelschool.model_parent.Response response1 = data.getResponse();

            for (Father father : response1.getFather()) {

                textFatherName.setText(father.getG_name());
                textfqualification.setText(father.getG_quali());
                textforg.setText(father.getG_org());
                textfoccupation.setText(father.getG_occp());
                textfpan.setText(father.getG_pan());
                textfofficeadd.setText(father.getG_office_add());
                textfofficeno.setText(father.getG_office_ph());

                textfemail.setText(father.getG_mail());
                textfmob.setText(father.getG_mob());
                textfded.setText(father.getG_desig());
            }
            for (Mother mother : response1.getMother()) {
                textMotherrName.setText(mother.getG_name());
                textMqul.setText(mother.getG_quali());
                textMocu.setText(mother.getG_occp());
                textMorg.setText(mother.getG_org());
                textMdeg.setText(mother.getG_desig());
                textMPan.setText(mother.getG_pan());
                textMOffAdd.setText(mother.getG_office_add());
                textMOffNo.setText(mother.getG_office_ph());
                textMEmail.setText(mother.getG_mail());
                textMMOb.setText(mother.getG_mob());
            }
        } else if (data.getStatus().equals("false")) {
            InvalidUserData invalidUserData = new Gson().fromJson(response, InvalidUserData.class);
            Toast.makeText(context, "" + invalidUserData.getError(), Toast.LENGTH_SHORT).show();
        }
    }
}




