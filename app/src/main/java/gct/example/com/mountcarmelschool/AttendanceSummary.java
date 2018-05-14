package gct.example.com.mountcarmelschool;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.AttendanceSummaryforlist;
import gct.example.com.mountcarmelschool.classes.helper;

import static gct.example.com.mountcarmelschool.Main2Activity.email;
import static gct.example.com.mountcarmelschool.Main2Activity.types;

public class AttendanceSummary extends AppCompatActivity {


    Context context;
    ImageView imagesummeryback;
    String string;
    public String str;
    android.support.v7.app.AlertDialog scheduleDialog;
    private static ProgressDialog progressDialog;
    public GregorianCalendar month, itemmonth;// calendar instances.
    public CalendarASummaeryAdapter adapter;// adapter instance
    public Handler handler;// for grabbing some event values for showing the dot
    // marker.
    public ArrayList<String> items; // container to store calendar items which
    // needs showing the event marker
    public ArrayList<String> messagess;
    public ArrayList<String> Id;
    String catalog_outdated = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_summary);
        context = AttendanceSummary.this;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        findViewById(R.id.imagesummeryback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Locale.setDefault(Locale.US);
        month = (GregorianCalendar) GregorianCalendar.getInstance();
        itemmonth = (GregorianCalendar) month.clone();

        items = new ArrayList<String>();
        messagess = new ArrayList<>();
        Id = new ArrayList<>();
        adapter = new CalendarASummaeryAdapter(this, month);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(adapter);

        handler = new Handler();
        handler.post(calendarUpdater);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

        RelativeLayout previous = (RelativeLayout) findViewById(R.id.previous);

        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar();
            }
        });

        RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();

            }
        });


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                ((CalendarASummaeryAdapter) parent.getAdapter()).setSelected(v);
                String selectedGridDate = CalendarASummaeryAdapter.dayString
                        .get(position);
                String[] separatedTime = selectedGridDate.split("-");
                String gridvalueString = separatedTime[2].replaceFirst("^0*", "");
                int gridvalue = Integer.parseInt(gridvalueString);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date strDate = null;


                Log.d("texst", String.valueOf((position)));
                if ((gridvalue > 10) && (position < 8)) {


                    setPreviousMonth();
                    refreshCalendar();
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar();
                }
                ((CalendarASummaeryAdapter) parent.getAdapter()).setSelected(v);

                if (TextUtils.isEmpty(helper.catalog_outdated)) {
                    //region "Admin and Staff"

                    if (types.equalsIgnoreCase("ADMIN")) {


                    } else if (types.equalsIgnoreCase("STAFF")) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.size() > 0) {
                                if (selectedGridDate.contains(list.get(i).getAttendance_date())) {

                                    //   if (selectedGridDate.contains(list.get(i).getDate())) {
                                    requestToGetAbsendDate(list.get(i).getAttendance_date());
                                    // String str = (list.get(i).getAbsent_data());
                                    ///   Log.d("abc", str);

                                 /* Intent intent = new Intent(AttendanceSummary.this,ASummary.class);
                                 startActivity(intent);*/
                                    break;
                                }
                            }
                        }

                    }
                }

            }
        });
    }

    private void ShowMessage(String date,ArrayList<String> studentLists) {
        android.support.v7.app.AlertDialog scheduleDialog;
        LayoutInflater inflater = (LayoutInflater) AttendanceSummary.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        android.support.v7.app.AlertDialog.Builder builderAlert = new android.support.v7.app.AlertDialog.Builder(AttendanceSummary.this);
        final View alert_view = inflater.inflate(R.layout.cal_dialog_absent_student, null);
        final TextView TextvieweventMessage = (TextView) alert_view.findViewById(R.id.TextvieweventMessage);
        ListView lvAbsentStudent = (ListView) alert_view.findViewById(R.id.lvAbsentStudent);
        TextvieweventMessage.setMovementMethod(new ScrollingMovementMethod());

        String str1 = (""+date);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        Date date1 = null;
        try {
            date1 = (Date)formatter.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("DD-MM-yyyy");
        String finalString = newFormat.format(date1);
        TextvieweventMessage.setText(finalString);
        builderAlert.setView(alert_view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AttendanceSummary.this, android.R.layout.simple_list_item_1, studentLists);
        lvAbsentStudent.setAdapter(arrayAdapter);
        builderAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderAlert.setCancelable(false);
        scheduleDialog = builderAlert.create();
        builderAlert.show();

    }

    private void requestToGetAbsendDate(final String date) {
        int count=0;
        Log.d("count",""+count++);
        Log.e("abchdg", email);
        Log.e("abchdg", date);
        final ArrayList<String> studentList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://infoes.in/sunil/mcsd/user/absentstudentlist",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("absres", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("response");
                            JSONArray jsonArray = object.getJSONArray("Absent_student_List");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                studentList.add(obj.getString("firstname") + "\t" + obj.getString("adm_no"));

                            }
                            ShowMessage(date,studentList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("login_type", "" + email);
                params.put("absent_date", ""+date);
                return params;
            }
        };
        getStringRequest(context, stringRequest);
    }


    protected void setNextMonth() {
        if (month.get(GregorianCalendar.MONTH) == month.getActualMaximum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) + 1),
                    month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) + 1);
        }

    }

    protected void setPreviousMonth() {
        if (month.get(GregorianCalendar.MONTH) == month.getActualMinimum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }

    }


    public void refreshCalendar() {
        TextView title = (TextView) findViewById(R.id.title);

        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some calendar items
        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }


    ArrayList<AttendanceSummaryforlist> list = new ArrayList<>();
    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            items.clear();

            // Print dates of the current week

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String itemvalue;
            //  here  was 7  where is 1

            for (int i = 0; i < 1; i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(GregorianCalendar.DATE, 1);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://infoes.in/sunil/mcsd/user/absentdate",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("abchdg", response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONObject object = jsonObject.getJSONObject("response");
                                    JSONArray jsonArray = object.getJSONArray("Absent_date");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject obj = jsonArray.getJSONObject(i);
                                        String data = obj.getString("attendance_date");
                                        Log.e("wkjsfdjxkesj" + i, data);
                                        items.add(data);
                                        AttendanceSummaryforlist forList = new AttendanceSummaryforlist();
                                        forList.setAttendance_date(data);

                                        list.add(forList);
                                    }
                                    adapter.setItems(items);
                                    adapter.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("login_type", "" + email);
                        //    Log.e("abcD", "" + email);

                        return params;
                    }
                };
                getStringRequest(context, stringRequest);
            }
        }
    };

    private void getStringRequest(Context context, StringRequest stringRequest) {
        int socketTimeout = 50000;//10 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        stringRequest.setShouldCache(true);
        requestQueue.add(stringRequest);
    }
}
