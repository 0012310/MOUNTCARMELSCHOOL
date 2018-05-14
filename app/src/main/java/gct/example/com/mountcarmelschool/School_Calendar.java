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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import gct.example.com.mountcarmelschool.adapter.CustomSpinnerAdapter;
import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.classes.SchoolCadenderForList;
import gct.example.com.mountcarmelschool.classes.SchoolCalenderForList;
import gct.example.com.mountcarmelschool.classes.helper;

import static gct.example.com.mountcarmelschool.Main2Activity.types;

public class School_Calendar extends AppCompatActivity {


    Context context;
    ImageView imgbtnschoolback;
    String string;
    public String str;
    android.support.v7.app.AlertDialog scheduleDialog;
    private static ProgressDialog progressDialog;
    public GregorianCalendar month, itemmonth;// calendar instances.
    public CalendarAdapter2 adapter;// adapter instance
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
        setContentView(R.layout.activity_school__calendar);
        context = School_Calendar.this;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        findViewById(R.id.imgbtnschoolback).setOnClickListener(new View.OnClickListener() {
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
        adapter = new CalendarAdapter2(this, month);

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

                ((CalendarAdapter2) parent.getAdapter()).setSelected(v);
                String selectedGridDate = CalendarAdapter2.dayString
                        .get(position);
                String[] separatedTime = selectedGridDate.split("-");
                String gridvalueString = separatedTime[2].replaceFirst("^0*", ""); // taking last part of date. ie; 2 from 2012-12-02.
                int gridvalue = Integer.parseInt(gridvalueString);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date strDate = null;
                /*try {
                    strDate = sdf.parse(selectedGridDate);

                    if (new Date() == strDate) {
                        helper.catalog_outdated = "1";
                    } else if (new Date().after(strDate)) {
                        helper.catalog_outdated = "1";
                        Log.d("current_date", "" + catalog_outdated);
                    } else {
                        helper.catalog_outdated = "";
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }*/

                Log.d("texst", String.valueOf((position)));
                if ((gridvalue > 10) && (position < 8)) {


                    setPreviousMonth();
                    refreshCalendar();
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar();
                }
                ((CalendarAdapter2) parent.getAdapter()).setSelected(v);

                if (TextUtils.isEmpty(helper.catalog_outdated)) {
                    //region "Admin and Staff"

                    if (types.equalsIgnoreCase("ADMIN")) {
                        Log.d("type", types);

                        showToast(selectedGridDate);

                    } else if (types.equalsIgnoreCase("STAFF")) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.size() > 0) {
                                if (selectedGridDate.contains(list.get(i).getDate())) {

                                    ShowMessage(list.get(i).getMessage());
                                    String str = (list.get(i).getMessage());
                                    Log.d("abc", str);

                                    break;
                                }
                            }
                        }

                    } else {

                        for (int i = 0; i < list.size(); i++) {
                            if (list.size() > 0) {
                                if (selectedGridDate.contains(list.get(i).getDate())) {
                                    //  Toast.makeText(context, ""+list.get(i).getMessage(), Toast.LENGTH_SHORT).show();
                                    EventStudent(list.get(i).getMessage());
                                    String str = (list.get(i).getMessage());
                                    Log.d("abc", str);

                                    break;

                                }
                            }
                        }
                    }
                }

            }


//>>>>>>>>>>>>>>>>>>>>>>>>>>>> this for student

            private void ShowMessage(String string) {

                android.support.v7.app.AlertDialog scheduleDialog;
                LayoutInflater inflater = (LayoutInflater) School_Calendar.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                android.support.v7.app.AlertDialog.Builder builderAlert = new android.support.v7.app.AlertDialog.Builder(School_Calendar.this);
                final View alert_view = inflater.inflate(R.layout.cal_dialog_message, null);
                final TextView TextvieweventMessage = (TextView) alert_view.findViewById(R.id.TextvieweventMessage);
                TextvieweventMessage.setMovementMethod(new ScrollingMovementMethod());
                TextvieweventMessage.setText(string);
                builderAlert.setView(alert_view);
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
        });

    }
//>>>>>>>>>>>>>>>>>>>>>>>>>>>> this for staff
    private void EventStudent(String string) {
        android.support.v7.app.AlertDialog scheduleDialog;
        LayoutInflater inflater = (LayoutInflater) School_Calendar.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        android.support.v7.app.AlertDialog.Builder builderAlert = new android.support.v7.app.AlertDialog.Builder(School_Calendar.this);
        final View alert_view = inflater.inflate(R.layout.cal_dialoge_student, null);
        final TextView TextvieweventMessageStudent = (TextView) alert_view.findViewById(R.id.TextvieweventMessageStudent);
        TextvieweventMessageStudent.setMovementMethod(new ScrollingMovementMethod());
        TextvieweventMessageStudent.setText(string);
        builderAlert.setView(alert_view);
        builderAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
       /* builderAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });*/


        builderAlert.setCancelable(false);
        scheduleDialog = builderAlert.create();
        builderAlert.show();


    }


    private void insertStringReq(String url, final String action, final String message, final String date) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://infoes.in/sunil/mcsd/user/schoolCalender",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("insert_response: ", response);
                        //  getDetailsData(response);
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
                params.put("action", action);
                params.put("date", date);
                params.put("message", message);
                params.put("user_type",spinner_val);
                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
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

    String spinner_val = "";

    protected void showToast(final String string) {
        final boolean[] update = {false};
        final boolean[] delete = {false};
        LayoutInflater inflater = (LayoutInflater) School_Calendar.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        android.support.v7.app.AlertDialog.Builder builderAlert = new android.support.v7.app.AlertDialog.Builder(School_Calendar.this);
        final View alert_view = inflater.inflate(R.layout.cal_dialog, null);
        Spinner spinnerSelectType = (Spinner) alert_view.findViewById(R.id.spinnerSelectType);
        ImageView iVUpdate = (ImageView) alert_view.findViewById(R.id.iVUpdate);
        ImageView iVDelete = (ImageView) alert_view.findViewById(R.id.iVDelete);
        final EditText editTextCalData = (EditText) alert_view.findViewById(R.id.editTextCalData);
        final EditText editTextDo = (EditText) alert_view.findViewById(R.id.editTextDo);


        final ArrayList<String> chooseDurationList = new ArrayList<String>();

        chooseDurationList.add("Student");
        chooseDurationList.add("Staff");
        chooseDurationList.add("Both");
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(School_Calendar.this, chooseDurationList);
        spinnerSelectType.setAdapter(customSpinnerAdapter);

        spinnerSelectType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        spinner_val = "student";
                        break;
                    case 1:
                        spinner_val = "staff";
                        break;
                    case 2:
                        spinner_val = "All";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(context, "plz sellect somthing ", Toast.LENGTH_SHORT).show();

            }
        });


        builderAlert.setView(alert_view);
        boolean found = false;
        Iterator<String> iter = items.iterator();
        String curItem = "";
        int pos = 0;
        ArrayList<SchoolCalenderForList> schoolCalenderForLists = new ArrayList<>();

        while (iter.hasNext() == true) {
            pos = pos + 1;
            curItem = (String) iter.next();
            if (curItem.equals(string)) {
                // builderAlert.setMessage(messagess.get(pos-1));
                SchoolCalenderForList schoolCalenderForList = new SchoolCalenderForList();
                schoolCalenderForList.setId(Id.get(pos - 1));
                schoolCalenderForList.setMessage(messagess.get(pos - 1));
                schoolCalenderForLists.add(schoolCalenderForList);

                editTextCalData.setText(messagess.get(pos - 1));
                editTextDo.setText(Id.get(pos - 1));
                final int finalPos = pos;

                iVDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteCalData(Id.get(finalPos - 1), messagess.get(finalPos - 1));
                        Log.d("delete_data", Id.get(finalPos - 1) + messagess.get(finalPos - 1));
                        progressDialog.show();
                    }
                });
                iVUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateCalData(Id.get(finalPos - 1), editTextCalData.getText().toString());
                        Log.d("update_data", Id.get(finalPos - 1) + editTextCalData.getText().toString());
                        progressDialog.show();
                    }
                });
                Log.d("abc", messagess.get(pos - 1));
                found = true;
                break;
            }
        }

        builderAlert.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (!TextUtils.isEmpty(editTextCalData.getText().toString())) {
                    insertStringReq("", "A", editTextCalData.getText().toString(), string);//spinner_val
                    finish();
                    startActivity(getIntent());
                }
                dialog.dismiss();
            }
        });


        builderAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        if (found == false) {
            pos = 0;
        }
        if (pos != 0) {
        }
        builderAlert.setCancelable(false);
        scheduleDialog = builderAlert.create();
        builderAlert.show();

    }

    //>>>>>>>>>>>.....  update
    private void updateCalData(final String id, final String message) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.POST, "http://infoes.in/sunil/mcsd/user/schoolCalender", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("responseUpdateDatas", response);
                //progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("success").equals("1") && jsonObject.getString("response").equals("true")) {
                        progressDialog.dismiss();
                        //startActivity(new Intent(School_Calendar.this, Main2Activity.class));
                        finish();
                        startActivity(getIntent());
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(School_Calendar.this, "" + response, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("email", "aabhakatre1910@mountcarmeldelhi.com");
                params.put("id", id);
                params.put("message", message);
                params.put("action", "U");
                //params.put("password", "abc@androidhive.info");
                Log.d("id_msg", "" + id + "\n" + message);
                return params;
            }
        };
        queue.add(strReq);
    }
//>>>>>>>>>>>.....  delte
    private void deleteCalData(final String id, final String message) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.POST, "http://infoes.in/sunil/mcsd/user/schoolCalender", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("responseDelData", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("success").equals("1") && jsonObject.getString("response").equals("true")) {
                        progressDialog.dismiss();
                        //startActivity(new Intent(School_Calendar.this, Main2Activity.class));
                        finish();
                        startActivity(getIntent());
                    } else {
                        Toast.makeText(School_Calendar.this, "" + response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
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
                //params.put("email", "aabhakatre1910@mountcarmeldelhi.com");
                params.put("id", id);
                params.put("message", message);
                params.put("action", "D");
                //params.put("password", "abc@androidhive.info");
                return params;
            }
        };
        queue.add(strReq);
    }

    public void refreshCalendar() {
        TextView title = (TextView) findViewById(R.id.title);

        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some calendar items
        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }


    ArrayList<SchoolCadenderForList> list = new ArrayList<>();
    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            items.clear();

            // Print dates of the current week

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String itemvalue;
            //  here  was 7  where is 1

            for (int i = 0; i <1; i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(GregorianCalendar.DATE, 1);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://infoes.in/sunil/mcsd/user/schoolCalender",
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject object = jsonObject.getJSONObject("response");
                            JSONArray jsonArray = object.getJSONArray("Calender_List");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                String date = obj.getString("date");
                                String message = obj.getString("message");
                                String id = obj.getString("id");

                                //   Toast.makeText(School_Calendar.this, date, Toast.LENGTH_LONG).show();

                                Log.e("date" + i, date);
                                items.add(date);
                                SchoolCadenderForList forList = new SchoolCadenderForList();
                                forList.setDate(date);


                                forList.setDate(date);
                                forList.setId(id);
                                Id.add(id);
                                forList.setMessage(message);
                                messagess.add(message);

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

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        if(types.equalsIgnoreCase("ADMIN")){
                            params.put("login_type","admin");
                        }if(types.equalsIgnoreCase("STAFF")){
                            params.put("login_type","staff");
                        }if(types.equalsIgnoreCase("STUDENT")){
                            params.put("login_type","student");
                        }
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(School_Calendar.this);
                requestQueue.add(stringRequest);
            }
        }
    };
}
