package gct.example.com.mountcarmelschool;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gct.example.com.mountcarmelschool.adapter.CustomAdapter;
import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.classes.CustomForList;
import gct.example.com.mountcarmelschool.model.login_data.InvalidUserData;
import gct.example.com.mountcarmelschool.model_sibling_data.sibling_details_data.SiblingDetailsData;
import gct.example.com.mountcarmelschool.models.general_details_data.GeneralDetailsData;

/**
 * A simple {@link Fragment} subclass.
 */

public class Sibling extends Fragment {
    RequestQueue requestQueue;
    RecyclerView recyclerViewSibling;
    Context context;
    private ArrayList<CustomForList> customForLists = new ArrayList<>();

    public Sibling() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sibling, container, false);
        context = container.getContext();
        recyclerViewSibling = (RecyclerView) view.findViewById(R.id.recyclerViewSibling);


        String e_mail = CommonMethods.getPreference(context, "e_mail");
        Log.d("e_mailsibling", e_mail);
        String url = "http://infoes.in/sunil/mcsd/user/siblingProfile?e_mail=" + e_mail;
        // Toast.makeText(context, "e_mail in sibling" + e_mail, Toast.LENGTH_SHORT).show();
        getStringReq(url, e_mail);
        return view;
    }

    private void getStringReq(String URL, final String email) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest strReq = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseSibling", response);
                getSiblingData(response);
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

    private void getSiblingData(String response) {
        SiblingDetailsData data1 = new Gson().fromJson(response, SiblingDetailsData.class);
        if (data1.getStatus().equals("true")) {
            gct.example.com.mountcarmelschool.model_sibling_data.sibling_details_data.Response response1 = data1.getResponse();
            if (data1.getStatus().equals("true")) {
                for (gct.example.com.mountcarmelschool.model_sibling_data.sibling_details_data.Sibling sibling : response1.getSibling()) {
                    CustomForList customForList = new CustomForList();
                    customForList.setAdm_no(sibling.getAdm_no());
                    customForList.setSname(sibling.getSname());
                    customForList.setSbranch(sibling.getSbranch());
                    customForList.setSadm_no(sibling.getSadm_no());
                    customForLists.add(customForList);
                }
                CustomAdapter customAdapter = new CustomAdapter(customForLists, context, R.layout.custom_layout);
                recyclerViewSibling.setLayoutManager(new LinearLayoutManager(context));
                recyclerViewSibling.setAdapter(customAdapter);
            }
        }else if (data1.getStatus().equals("false")) {
            InvalidUserData invalidUserData = new Gson().fromJson(response, InvalidUserData.class);
            Toast.makeText(context, "" + invalidUserData.getError(), Toast.LENGTH_SHORT).show();
        }
    }
}
