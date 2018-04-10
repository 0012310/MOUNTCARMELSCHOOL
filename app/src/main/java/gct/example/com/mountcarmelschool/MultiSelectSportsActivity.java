package gct.example.com.mountcarmelschool;

import android.app.ActionBar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;
import gct.example.com.mountcarmelschool.model_attendacestudentlist.Attendancelist;

public class MultiSelectSportsActivity extends AppCompatActivity implements ActionMode.Callback {

    RequestQueue requestQueue;
    RecyclerView recyclerView;
    List<MyData> lists = new ArrayList<>();
    private ActionMode actionMode;
    private boolean isMultiSelect = true;
    boolean flag = true;
    //i created List of int type to store id of data, you can create custom class type data according to your need.
    private List<Integer> selectedIds = new ArrayList<>();
    private MyAdapter1 adapter;
    Context context;
    String Standard,section;
    String titleMenu;
    String from_umpire_id, from_coach_id, player_sports_id, from_sports_circle_id, from_sports_team_id, from_sports_tournament_id, from_sports_event_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_select_sports);
        context = MultiSelectSportsActivity.this;
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);

        }

        from_umpire_id = getIntent().getStringExtra("explore_umpire_filter");
        from_coach_id = getIntent().getStringExtra("explore_coach_filter");

        player_sports_id = getIntent().getStringExtra("player_sports_id");
        from_sports_circle_id = getIntent().getStringExtra("circle_sports_id");
        from_sports_team_id = getIntent().getStringExtra("team_sports_id");
        from_sports_tournament_id = getIntent().getStringExtra("nearby_tournament_filter");
        from_sports_event_id = getIntent().getStringExtra("nearby_event_filter");
        recyclerView = (RecyclerView) findViewById(R.id.widget_list);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isMultiSelect) {
                    multiSelect(position);
                }
                if (!isMultiSelect) {
                    selectedIds = new ArrayList<>();
                    isMultiSelect = true;

                    if (actionMode == null) {
                        actionMode = startActionMode(MultiSelectSportsActivity.this); //show ActionMode.
                    }
                    multiSelect(position);
                }


            }


            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));


       /* String e_mail1 = "karmveersingh2016@gmail.com";
        String url = "http://infoes.in/sunil/mcsd/user/studentlist?t_mail=" + e_mail1;

        getStringReq(url, "" + e_mail1);
*/
        String e_mail1 = CommonMethods.getPreference(this, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/studentlist?t_mail=" + e_mail1;
        Log.d("e_mail ", "" + e_mail1);
        getStringReq(url, "" + e_mail1);

    }

    private void getStringReq(String url, final String e_mail) {

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("user_emails", e_mail);
                        Log.d("response11: ", response);
                        getDetailsData(response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject json2 = jsonObject.getJSONObject("response");

                            Standard = (String) json2.get("Standard");
                            section = json2.getString("Section_abc");
                            titleMenu = Standard+section;
                            Toast.makeText(MultiSelectSportsActivity.this,Standard+section,Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                params.put("t_mail", e_mail);
                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }

    private void getDetailsData(String response) {
        Attendancelist data = new Gson().fromJson(response, Attendancelist.class);
        gct.example.com.mountcarmelschool.model_attendacestudentlist.Response response1 = data.getResponse();
        int i = 1;

        if (response1!=null){
            for (gct.example.com.mountcarmelschool.model_attendacestudentlist.List list : response1.getList()) {


                MyData myData = new MyData(i, list.getFirstname(), list.getAdm_no());
                lists.add(myData);

                i++;
            }
        }else
            Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();

        if (lists.size() > 0) {
            adapter = new MyAdapter1(MultiSelectSportsActivity.this, lists);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerView.setAdapter(adapter);
            if (flag) {
                for (int a = 1; a <= lists.size(); a++) {
                    selectedIds.add(a);
                    adapter.setSelectedIds(selectedIds);
                }
                if (actionMode == null) {
                    actionMode = startActionMode(MultiSelectSportsActivity.this); //show ActionMode.
                }
                if (selectedIds.size() > 0)
                    actionMode.setTitle(String.valueOf(selectedIds.size()));//show selected item count on action mode.
                    //actionMode.setCustomView();

                else {
                    actionMode.setTitle(""); //remove item count from action mode.
                    //actionMode.finish(); //hide action mode.
                }
                flag = false;
            }
        }
    }

    private void multiSelect(int position) {
        MyData data = adapter.getItem(position);
        if (data != null) {
            if (actionMode != null) {
                if (selectedIds.contains(data.getId()))
                    selectedIds.remove(Integer.valueOf(data.getId()));
                else
                    selectedIds.add(data.getId());

                if (selectedIds.size() > 0)
                    actionMode.setTitle(String.valueOf(selectedIds.size()+"\t\t\t"+Standard+section)); //show selected item count on action mode.
                else {
                    actionMode.setTitle("");

                    //remove item count from action mode.
                    //actionMode.finish(); //hide action mode.
                }
                adapter.setSelectedIds(selectedIds);
            }
        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_select, menu);
        return true;
    }

    /*@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (titleMenu!=null){
            menu.findItem(R.id.action_Title).setTitle(titleMenu);

        }
      //  menu.findItem(R.id.action_Title).setTitle(Standard+section);
        return super.onPrepareOptionsMenu(menu);
    }*/

    @Override
    public void onBackPressed() {

      MultiSelectSportsActivity.this.finish();
        super.onBackPressed();

  //    calender_alert_dialog();

    }




    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_delete:

                //just to show selected items.
                StringBuilder stringBuilder = new StringBuilder();
                if (lists.size() > 0) {
                    for (MyData data : lists) {
                        if (selectedIds.contains(data.getId()))
                            stringBuilder.append(data.getadm_no()).append(",");
                    }
                    if (!TextUtils.isEmpty(stringBuilder.toString())) {
                        callApi(stringBuilder.toString());
                    }
                    Intent intent=new Intent(MultiSelectSportsActivity.this,Main2Activity.class);
                    startActivity(intent);
                    return true;
                }
        }
        return false;
    }

    private void callApi(String adm_ids) {
        Toast.makeText(context, "" + adm_ids.substring(0,adm_ids.length()), Toast.LENGTH_SHORT).show();
        Log.d("callApi: ","" + adm_ids.substring(0,adm_ids.length()));
        String e_mail1 = CommonMethods.getPreference(this, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/studentlist?t_mail=" + e_mail1;
        sendStringReq("http://infoes.in/sunil/mcsd/user/make_att", adm_ids.substring(0,adm_ids.length()-1));
    }

    private void sendStringReq(String url, final String adm_no) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("sendDataResult", "" + response);
                        //Toast.makeText(context, "Attendance Updated.", Toast.LENGTH_SHORT).show();
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
                params.put("adm_no", adm_no);
                Log.d("adm_no_tosend",""+adm_no);
                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        if (actionMode != null) {
            actionMode.setTitle("");
        }
        actionMode = null;
        isMultiSelect = false;
        selectedIds = new ArrayList<>();
        adapter.setSelectedIds(new ArrayList<Integer>());
    }
}