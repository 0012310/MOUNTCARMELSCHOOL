package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import java.util.List;
import java.util.Map;

import gct.example.com.mountcarmelschool.adapter.AttendenceAprilAdapter;
import gct.example.com.mountcarmelschool.adapter.AttendenceAuguestAdapter;
import gct.example.com.mountcarmelschool.adapter.AttendenceDecemberAdapter;
import gct.example.com.mountcarmelschool.adapter.AttendenceFebrauryAdapter;
import gct.example.com.mountcarmelschool.adapter.AttendenceJanuaryAdapter;
import gct.example.com.mountcarmelschool.adapter.AttendenceJulyAdapter;
import gct.example.com.mountcarmelschool.adapter.AttendenceJuneAdapter;
import gct.example.com.mountcarmelschool.adapter.AttendenceMarchAdapter;
import gct.example.com.mountcarmelschool.adapter.AttendenceMay1Adapter;


import gct.example.com.mountcarmelschool.adapter.AttendenceNovemberAdapter;
import gct.example.com.mountcarmelschool.adapter.AttendenceOctoberAdapter;
import gct.example.com.mountcarmelschool.adapter.AttendenceSeptemberAdapter;

import gct.example.com.mountcarmelschool.classes.AttendanceJanuaryForList;
import gct.example.com.mountcarmelschool.classes.AttendenceAprilForList;
import gct.example.com.mountcarmelschool.classes.AttendenceAuguestForList;
import gct.example.com.mountcarmelschool.classes.AttendenceDecemberForList;

import gct.example.com.mountcarmelschool.classes.AttendenceFebrurayForList;
import gct.example.com.mountcarmelschool.classes.AttendenceJulyForList;
import gct.example.com.mountcarmelschool.classes.AttendenceJuneForList;
import gct.example.com.mountcarmelschool.classes.AttendenceMayForList;
import gct.example.com.mountcarmelschool.classes.AttendenceNovemberForList;
import gct.example.com.mountcarmelschool.classes.AttendenceOctoberlForList;
import gct.example.com.mountcarmelschool.classes.AttendenceSeptemberForList;
import gct.example.com.mountcarmelschool.classes.AttentdanceFebrauryForList;
import gct.example.com.mountcarmelschool.classes.AttentdanceMarchForList;
import gct.example.com.mountcarmelschool.model_attendance_data.Apr;
import gct.example.com.mountcarmelschool.model_attendance_data.Attendence;
import gct.example.com.mountcarmelschool.model_attendance_data.AttendenceCelenderData;
import gct.example.com.mountcarmelschool.model_attendance_data.Aug;
import gct.example.com.mountcarmelschool.model_attendance_data.Dec;
import gct.example.com.mountcarmelschool.model_attendance_data.Feb;
import gct.example.com.mountcarmelschool.model_attendance_data.Jan;
import gct.example.com.mountcarmelschool.model_attendance_data.Jul;
import gct.example.com.mountcarmelschool.model_attendance_data.Jun;
import gct.example.com.mountcarmelschool.model_attendance_data.Mar;
import gct.example.com.mountcarmelschool.model_attendance_data.May;
import gct.example.com.mountcarmelschool.model_attendance_data.Nov;
import gct.example.com.mountcarmelschool.model_attendance_data.Oct;
import gct.example.com.mountcarmelschool.model_attendance_data.Sep;




public class Attendance extends AppCompatActivity {

    Context context;
    String url_data;
    TextView textView;
    private List<Bean> listitems;
    private CalendarAdapter adapter;
    private RecyclerView recyclerView;
    TextView Present, Holidays, absent;
    ImageView imageattendence;
    RecyclerView recyclerviewApr, recyclerviewMay, recyclerviewJune, recyclerviewJuly, recyclerviewAug, recyclerviewSep, recyclerviewOct, recyclerviewNov, recyclerviewDec,recyclerviewJanuary,recyclerviewFebraury,recyclerviewMarch;
    LinearLayout linearParentMarch,linearParentFebraury,linearParentJanuary,linearParentDecember,linearParentNovember,
            linearParentOctober,linearParentSeptember,linearParentAugust,linearParentJuly,linearParentJune,linearParentMay,linearParentApril;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        context = Attendance.this;
        listitems = new ArrayList<>();

        imageattendence = (ImageView) findViewById(R.id.imageattendence);
        imageattendenceOnclick();

        Present = (TextView) findViewById(R.id.Present);
        Holidays = (TextView) findViewById(R.id.Holidays);
        absent = (TextView) findViewById(R.id.absent);


        recyclerviewApr = (RecyclerView) findViewById(R.id.recyclerviewApr);
        recyclerviewMay = (RecyclerView) findViewById(R.id.recyclerviewMay);

        recyclerviewJune = (RecyclerView) findViewById(R.id.recyclerviewJune);

        recyclerviewJuly = (RecyclerView) findViewById(R.id.recyclerviewJuly);
        recyclerviewAug = (RecyclerView) findViewById(R.id.recyclerviewAug);

        recyclerviewSep = (RecyclerView) findViewById(R.id.recyclerviewSep);
        recyclerviewOct = (RecyclerView) findViewById(R.id.recyclerviewOct);

        recyclerviewNov = (RecyclerView) findViewById(R.id.recyclerviewNov);
        recyclerviewDec = (RecyclerView) findViewById(R.id.recyclerviewDec);
        recyclerviewJanuary= (RecyclerView) findViewById(R.id.recyclerviewJanuary);
        recyclerviewFebraury= (RecyclerView) findViewById(R.id.recyclerviewFebraury);
        recyclerviewMarch= (RecyclerView) findViewById(R.id.recyclerviewMarch);

        if (null != getIntent().getStringExtra("email_for_attendence")) {
            String email = getIntent().getStringExtra("email_for_attendence");
            url_data = "http://infoes.in/sunil/mcsd/user/attendance?e_mail=" + email;
            Log.e("url_data", url_data);
            loadRecyclerViewDatas(url_data, email);
        }
    }

    private void loadRecyclerViewDatas(String URL, final String email) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("response_", response);
                //   Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
                parse_attendence(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "error: " + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("e_mail", email);
                return params;
            }
        };
        queue.add(strReq);
    }

    private void parse_attendence(String response) {
        ArrayList<AttentdanceMarchForList> attentdanceMarchForLists = new ArrayList<>();
        ArrayList<AttentdanceFebrauryForList> attendanceFebrauryForLists=new ArrayList<>();
        ArrayList<AttendanceJanuaryForList> attendenceJanuaryForLists = new ArrayList<>();
        ArrayList<AttendenceDecemberForList> attendenceDecemberForLists = new ArrayList<>();
        ArrayList<AttendenceNovemberForList> attendenceNovemberForLists = new ArrayList<>();
        ArrayList<AttendenceOctoberlForList> attendenceOctoberlForLists = new ArrayList<>();
        ArrayList<AttendenceSeptemberForList> attendenceSeptemberForLists = new ArrayList<>();
        ArrayList<AttendenceAuguestForList> attendenceAuguestForLists = new ArrayList<>();
        ArrayList<AttendenceJulyForList> attendenceJulyForLists = new ArrayList<>();
        ArrayList<AttendenceJuneForList> attendenceJuneForLists = new ArrayList<>();
        ArrayList<AttendenceMayForList> attendenceMayForLists = new ArrayList<>();
        ArrayList<AttendenceAprilForList> attendenceAprilForLists = new ArrayList<>();


        AttendenceCelenderData data = new Gson().fromJson(response, AttendenceCelenderData.class);

        if (data.getStatus().equals("true")) {
            gct.example.com.mountcarmelschool.model_attendance_data.Response response1 = data.getResponse();
            gct.example.com.mountcarmelschool.model_attendance_data.Attendence attendence = response1.getAttendence();

            Present.setText(response1.getTotal_Present());
            Holidays.setText(response1.getTotal_Holiday());
            absent.setText(response1.getTotal_Absent());


            for (Mar mar :attendence.getMar()) {
                AttentdanceMarchForList  attentdanceMarchForList = new AttentdanceMarchForList();
                attentdanceMarchForList.setAtt_date(mar.getAtt_date());
                attentdanceMarchForList.setPresent_status(mar.getStatus());
                attentdanceMarchForLists.add(attentdanceMarchForList);

            }



            for (Feb feb :attendence.getFeb()) {
                AttentdanceFebrauryForList attendanceFebrauryForList=new AttentdanceFebrauryForList();
                attendanceFebrauryForList.setAtt_date(feb.getAtt_date());
                attendanceFebrauryForList.setPresent_status(feb.getStatus());
                attendanceFebrauryForLists.add(attendanceFebrauryForList);




            }

            for (Jan jan :attendence.getJan()) {
                AttendanceJanuaryForList attendenceJanuaryForList = new AttendanceJanuaryForList();
                attendenceJanuaryForList.setAtt_date(jan.getAtt_date());
                attendenceJanuaryForList.setPresent_status(jan.getStatus());
                attendenceJanuaryForLists.add(attendenceJanuaryForList);


            }


            for (Dec dec : attendence.getDec()) {
                AttendenceDecemberForList attendenceDecemberForList = new AttendenceDecemberForList();
                attendenceDecemberForList.setAtt_date(dec.getAtt_date());
                attendenceDecemberForList.setPresent_status(dec.getStatus());
                attendenceDecemberForLists.add(attendenceDecemberForList);

            }
            for (Nov nov : attendence.getNov()) {
                AttendenceNovemberForList attendenceNovemberForList = new AttendenceNovemberForList();
                attendenceNovemberForList.setAtt_date(nov.getAtt_date());
                attendenceNovemberForList.setPresent_status(nov.getStatus());
                attendenceNovemberForLists.add(attendenceNovemberForList);
            }
            for (Oct oct : attendence.getOct()) {
                AttendenceOctoberlForList attendenceOctoberForList = new AttendenceOctoberlForList();
                attendenceOctoberForList.setAtt_date(oct.getAtt_date());
                attendenceOctoberForList.setPresent_status(oct.getStatus());
                attendenceOctoberlForLists.add(attendenceOctoberForList);
            }
            for (Sep sep : attendence.getSep()) {
                AttendenceSeptemberForList attendenceSeptemberForList = new AttendenceSeptemberForList();
                attendenceSeptemberForList.setAtt_date(sep.getAtt_date());
                attendenceSeptemberForList.setPresent_status(sep.getStatus());
                attendenceSeptemberForLists.add(attendenceSeptemberForList);
            }
            for (Aug aug : attendence.getAug()) {
                AttendenceAuguestForList attendenceAuguestForList = new AttendenceAuguestForList();
                attendenceAuguestForList.setAtt_date(aug.getAtt_date());
                attendenceAuguestForList.setPresent_status(aug.getStatus());
                attendenceAuguestForLists.add(attendenceAuguestForList);
            }
            for (Jul july : attendence.getJul()) {
                AttendenceJulyForList attendenceJulyForList = new AttendenceJulyForList();
                attendenceJulyForList.setAtt_date(july.getAtt_date());
                attendenceJulyForList.setPresent_status(july.getStatus());
                attendenceJulyForLists.add(attendenceJulyForList);
            }
            for (Jun june : attendence.getJun()) {
                AttendenceJuneForList attendenceJuneForList = new AttendenceJuneForList();
                attendenceJuneForList.setAtt_date(june.getAtt_date());
                attendenceJuneForList.setPresent_status(june.getStatus());
                attendenceJuneForLists.add(attendenceJuneForList);
            }
            for (May may : attendence.getMay()) {
                AttendenceMayForList attendenceMayForList = new AttendenceMayForList();
                attendenceMayForList.setAtt_date(may.getAtt_date());
                attendenceMayForList.setPresent_status(may.getStatus());
                attendenceMayForLists.add(attendenceMayForList);
            }
            for (Apr april : attendence.getApr()) {
                AttendenceAprilForList attendenceAprilForList = new AttendenceAprilForList();
                attendenceAprilForList.setAtt_date(april.getAtt_date());
                attendenceAprilForList.setPresent_status(april.getStatus());
                attendenceAprilForLists.add(attendenceAprilForList);
            }

            AttendenceMarchAdapter attendenceMarchAdapter = new AttendenceMarchAdapter(attentdanceMarchForLists,context);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewMarch.setLayoutManager(gridLayoutManager);
            recyclerviewMarch.setAdapter(attendenceMarchAdapter);







            AttendenceFebrauryAdapter attendenceFebrauryAdapter = new AttendenceFebrauryAdapter(attendanceFebrauryForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewFebraury.setLayoutManager(gridLayoutManager);
            recyclerviewFebraury.setAdapter(attendenceFebrauryAdapter);


            AttendenceJanuaryAdapter attendenceJanuaryAdapter = new AttendenceJanuaryAdapter(attendenceJanuaryForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewJanuary.setLayoutManager(gridLayoutManager);
            recyclerviewJanuary.setAdapter(attendenceJanuaryAdapter);


            AttendenceDecemberAdapter attendenceDecemberAdapter = new AttendenceDecemberAdapter(attendenceDecemberForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewDec.setLayoutManager(gridLayoutManager);
            recyclerviewDec.setAdapter(attendenceDecemberAdapter);

            AttendenceNovemberAdapter attendenceNovemberAdapter = new AttendenceNovemberAdapter(attendenceNovemberForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewNov.setLayoutManager(gridLayoutManager);
            recyclerviewNov.setAdapter(attendenceNovemberAdapter);


            AttendenceOctoberAdapter attendenceOctoberAdapter = new AttendenceOctoberAdapter(attendenceOctoberlForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewOct.setLayoutManager(gridLayoutManager);
            recyclerviewOct.setAdapter(attendenceOctoberAdapter);


            AttendenceSeptemberAdapter attendenceSeptemberAdapter = new AttendenceSeptemberAdapter(attendenceSeptemberForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewSep.setLayoutManager(gridLayoutManager);
            recyclerviewSep.setAdapter(attendenceSeptemberAdapter);


            AttendenceAuguestAdapter attendenceAuguestAdapter = new AttendenceAuguestAdapter(attendenceAuguestForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewAug.setLayoutManager(gridLayoutManager);
            recyclerviewAug.setAdapter(attendenceAuguestAdapter);

            AttendenceJulyAdapter attendenceJulyAdapter = new AttendenceJulyAdapter(attendenceJulyForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewJuly.setLayoutManager(gridLayoutManager);
            recyclerviewJuly.setAdapter(attendenceJulyAdapter);


            AttendenceJuneAdapter attendenceJuneAdapter = new AttendenceJuneAdapter(attendenceJuneForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewJune.setLayoutManager(gridLayoutManager);
            recyclerviewJune.setAdapter(attendenceJuneAdapter);


            AttendenceMay1Adapter attendenceMay1Adapter = new AttendenceMay1Adapter(attendenceMayForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewMay.setLayoutManager(gridLayoutManager);
            recyclerviewMay.setAdapter(attendenceMay1Adapter);

            AttendenceAprilAdapter attendenceAprilAdapter = new AttendenceAprilAdapter(attendenceAprilForLists, context);
            gridLayoutManager = new GridLayoutManager(getApplicationContext(), 7);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerviewApr.setLayoutManager(gridLayoutManager);
            recyclerviewApr.setAdapter(attendenceAprilAdapter);

        }
    }


    private void imageattendenceOnclick() {
        imageattendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}


