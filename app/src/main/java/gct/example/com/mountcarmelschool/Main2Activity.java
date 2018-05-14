package gct.example.com.mountcarmelschool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import gct.example.com.mountcarmelschool.classes.CommonMethods;
import gct.example.com.mountcarmelschool.classes.LocalSharedPreferences;
import gct.example.com.mountcarmelschool.classes.LoginDataForList;
import gct.example.com.mountcarmelschool.gallary.NewGalleryActivity;
import gct.example.com.mountcarmelschool.main_notice_data.Main_notice_data;
import gct.example.com.mountcarmelschool.main_notice_data.Notice;
import gct.example.com.mountcarmelschool.model.login_data.LoginData;
import gct.example.com.mountcarmelschool.model_assessment_data.Assessment;
import gct.example.com.mountcarmelschool.model_assessment_data.AssessmentData;
import gct.example.com.mountcarmelschool.model_rahul.SessionManager;
import gct.example.com.mountcarmelschool.models_staff_noticdata.NoticData;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static gct.example.com.mountcarmelschool.R.id.SAttendance;
import static gct.example.com.mountcarmelschool.R.id.myassignment;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    CardView card1Main2;
    String email_ids;
    String url_name = "";
    RequestQueue requestQueue;
    Context context;
    String e_mail;
    TextView textatchmenticon;
    TextView textatchment;
    TextView texttitle;
    ImageView imageVieStaffBuilding;
    TextView textTitle, textlink;
    LinearLayout linearLayoutStaffNotice;
    LoginDataForList loginDataForList = new LoginDataForList();
    public static String url_link, types, email, img, names;
    LinearLayout linearLayoutProfile, linearnoticforstudent;
     TextView text_name, statusprofile;
    CircleImageView image_viewprofile;
    String URL_LOGIN = "http://infoes.in/sunil/mcsd/user/login";
    SessionManager sessionManager;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 101;
    String sec="";
    LinearLayout linearLayoutStudentfnotic;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        context = Main2Activity.this;
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();
        email = user.get(SessionManager.KEY_EMAIL);
        getStringReq1(URL_LOGIN, email);





        Log.d("email22",""+email);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

        //String e_mail = CommonMethods.getPreference(context, "email_token");
        String e_mail = LocalSharedPreferences.getUserEmail(context);
        String url = "http://infoes.in/sunil/mcsd/user/updatedeviceid?e_mail=" + e_mail+"&device_id="+LocalSharedPreferences.getToken(context);
        Log.d("email_token", e_mail);
        Log.d("token_url", url);
       getStringReqSend(url, e_mail);

    }

    private void getStringReqSend(String URL, final String email) {
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest strReq = new StringRequest(Request.Method.PUT, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseToken", response);
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
               /*params.put("e_mail", ""+email);
               Log.d("emailId",email);
               params.put("device_id",""+LocalSharedPreferences.getToken(context)*/
                return params;
            }
        };
        queue.add(strReq);
    }


    private void getDetailsDataStaff(String response) {
        NoticData data = new Gson().fromJson(response, NoticData.class);
        if (data.getStatus().equals("true")) {
            gct.example.com.mountcarmelschool.models_staff_noticdata.Response response3 = data.getResponse();
        for (gct.example.com.mountcarmelschool.models_staff_noticdata.Notice notic : response3.getNotice()) {
            texttitle.setText(notic.getTitle());
            textatchmentOnclick(notic.getAttachment_name());



        }
        } else if (data.getStatus().equals("false"));



    }



    private void textatchmentOnclick(final String attachment_name) {
        textatchment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attachment_name != null) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(attachment_name));
                    startActivity(i);
                }
            }
        });
    }


    private void getStringReq(String url, final String e_mail) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responseStr: ", response);
                        getDetailsData(response);
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
                params.put("e_mail", e_mail);
                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }

    private void getStringReqStaff(String url, final String e_mail) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
                        Log.d("response: ", response);
                        getDetailsDataStaff(response);
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
                params.put("e_mail", e_mail);
                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }

    private void getDetailsData(String response) {
        Main_notice_data data = new Gson().fromJson(response, Main_notice_data.class);
        if (data.getStatus().equals("true")) {
            gct.example.com.mountcarmelschool.main_notice_data.Response response1 = data.getResponse();
            for (Notice notice : response1.getNotice()) {
                textTitle.setText(notice.getTitle());
                textlinkOnClick(notice.getAttachment_name());
            }
        } else if (data.getStatus().equals("false")) {
           card1Main2.setVisibility(View.GONE);


            /*Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new R
            esultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    startActivity(new Intent(Main2Activity.this, Login.class));
                }
            });*/

        }
    }

    private void textlinkOnClick(final String attachment_name) {
        textlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (attachment_name != null) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(attachment_name));
                    startActivity(i);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);


            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);



        } else {


            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            super.onBackPressed();



        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {

            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    sessionManager.logoutUser();
                }
            });
            finish();
        }
        return true;
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.attendance) {
            Intent intent = new Intent(Main2Activity.this, Attendance.class);
            intent.putExtra("email_for_attendence", e_mail);
            startActivity(intent);
        } else if (id == myassignment) {
            openPdf();
            //startActivity(new Intent(Main2Activity.this, AssessmentRecord.class));
          //  Toast.makeText(context, "herre want pdf ", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.wise) {
            Intent intent = new Intent(Main2Activity.this, Wise.class);
            intent.putExtra("wise_email", "");
            startActivity(intent);
        } else if (id == R.id.gallary) {
            startActivity(new Intent(Main2Activity.this, NewGalleryActivity.class));
        } else if (id == R.id.feebill) {
            startActivity(new Intent(Main2Activity.this, FeeBills.class));
        } else if (id == R.id.transportbill) {
            startActivity(new Intent(Main2Activity.this, TranspotBill.class));
        } else if (id == R.id.feereceipt) {
            startActivity(new Intent(Main2Activity.this, FeeReceipts.class));
        } else if (id == R.id.transportreceipt) {
            startActivity(new Intent(Main2Activity.this, TransportReceipt.class));

        } else if (id == R.id.Schoolcalendar) {
            //Toast.makeText(context, "hii marks", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main2Activity.this, School_Calendar.class));

        } else if (id == R.id.SSWise) {

            startActivity(new Intent(Main2Activity.this, Swise.class));


        } else if (id == R.id.SAttendance) {
            //  Toast.makeText(context, "hii attendance", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main2Activity.this, MultiSelectSportsActivity.class));

            Intent i =new Intent(Main2Activity.this,MultiSelectSportsActivity.class);
            i.putExtra("sec",sec);
            startActivity(i);


            //  startActivity(new Intent(Main2Activity.this, NewAttendance.class));

        }else if(id==R.id.SAttendanceSummary) {
            startActivity(new Intent(Main2Activity.this, AttendanceSummary.class));


        }else if (id == R.id.sms) {

            startActivity(new Intent(Main2Activity.this, Sms.class));

        } else if (id == R.id.staffbirthday) {

            startActivity(new Intent(Main2Activity.this, StaffBd.class));
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }


    /// for Assesment Record
    private void openPdf() {
        String e_mail1 = CommonMethods.getPreference(this, "e_mail");
        String url = "http://infoes.in/sunil/mcsd/user/assessment?e_mail=" + e_mail1;
        Log.d("e_mail assessment", "" + e_mail1);
        getStringReqForPdf(url, "" + e_mail1);
    }

    private void getStringReqForPdf(String url, final String e_mail) {

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(context, "" + response, Toast.LENGTH_SHORT).show();
                        Log.d("response: ", response);
                        getDetailsDataForPdf(response);
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
                params.put("e_mail", e_mail);
                return params;
            }
        };
        CommonMethods.callWebserviceForResponse(stringRequest, context);
    }

    private void getDetailsDataForPdf(String response) {
        AssessmentData data = new Gson().fromJson(response, AssessmentData.class);
         if(data.getStatus().equals("true")){
            gct.example.com.mountcarmelschool.model_assessment_data.Response response1 = data.getResponse();
            for (Assessment assessment : response1.getAssessment()) {
                assessment_details(assessment.getAssessment_Record());
            }
        }else
        {
            Toast.makeText(context, "Sorry No Assessment", Toast.LENGTH_SHORT).show();
        }
    }

    private void assessment_details(String assessment_record) {
        if (assessment_record != null) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(assessment_record));
            startActivity(i);
        }
    }

    private void getStringReq1(String URL, final String email) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("response", response);
                try {
                    getLoginData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(Login.this, "" + response, Toast.LENGTH_SHORT).show();
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
                params.put("email", email);

                return params;
            }
        };
        queue.add(strReq);
    }


    private void getLoginData(String response) throws JSONException {
        final LoginData data = new Gson().fromJson(response, LoginData.class);
        if (data.getStatus().equals("true")) {
            for (gct.example.com.mountcarmelschool.model.login_data.Response response1 : data.getResponse()) {
                LoginDataForList loginDataForList = new LoginDataForList();
                loginDataForList.setEmail(response1.getEmail());
                loginDataForList.setId(response1.getId());
                loginDataForList.setType(response1.getType());
                loginDataForList.setSchoolcode(response1.getSchoolcode());
                loginDataForList.setTokengoogle(response1.getTokengoogle());
                loginDataForList.setTimestamp(response1.getTimestamp());
                loginDataForList.setName(response1.getName());
                loginDataForList.setImg(response1.getImg());
                sec=response1.getSection();
                if (response1.getType() != null && response1.getEmail() != null) {
                    goTOMain2(response1.getEmail(), loginDataForList.getName(), loginDataForList.getType(), loginDataForList.getImg());
                }
            }
        } else if (data.getStatus().equals("false")) {

            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    //Toast.makeText(context, "status\t"+data.getStatus(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Main2Activity.this, Login.class));
                }
            });
        }
    }


    private void goTOMain2(String e_mail, String name_url, String type, String img) {
        CommonMethods.setPreference(context, "e_mail", e_mail);
        types = type;

        this.e_mail = e_mail;
        url_name = name_url;
        this.img = img;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        card1Main2 = (CardView) findViewById(R.id.card1Main2);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textlink = (TextView) findViewById(R.id.textlink);
        textatchmenticon = (TextView) findViewById(R.id.textatchmenticon);
        textatchment = (TextView) findViewById(R.id.textatchment);
        texttitle = (TextView) findViewById(R.id.texttitle);
      //  imageVieStaffBuilding = (ImageView) findViewById(R.id.imageVieStaffBuilding);
        linearLayoutStaffNotice = (LinearLayout) findViewById(R.id.linearLayoutStaffNotice);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        //String e_mail = getIntent().getStringExtra("e_mail");
        String e_mail1 = LocalSharedPreferences.getUserEmail(context);
                //getIntent().getStringExtra("e_mail");

        url_name = getIntent().getStringExtra("url_name");

        Log.d("url_name11", "" + getIntent().getStringExtra("url_names"));

        //email_ids = e_mail;

        text_name = (TextView) headerview.findViewById(R.id.text_name);
        image_viewprofile = (CircleImageView) headerview.findViewById(R.id.image_viewprofile);
        linearnoticforstudent = (LinearLayout) findViewById(R.id.linearnoticforstudent);
        linearLayoutProfile = (LinearLayout) headerview.findViewById(R.id.linearLayoutProfile);
        statusprofile= (TextView) findViewById(R.id.statusprofile);

        String url = "http://infoes.in/sunil/mcsd/user/notice?e_mail=" + e_mail1;
        Log.d("e_mail_notices", "" + url + "  " + e_mail1);

        if (types.equalsIgnoreCase("STUDENT")) {
            getStringReq(url, e_mail1);
            linearLayoutStaffNotice.setVisibility(View.GONE);
            card1Main2.setVisibility(View.VISIBLE);
        } else if (types.equalsIgnoreCase("STAFF")) {
            String url_staff = "http://infoes.in/sunil/mcsd/user/staffNotice?e_mail=" + email;
//            Log.d("staff_data", e_mail1);
            getStringReqStaff(url_staff, email);
            card1Main2.setVisibility(View.GONE);
            linearLayoutStaffNotice.setVisibility(View.VISIBLE);
            navigationView.getMenu().findItem(R.id.sms).setVisible(false);


            navigationView.getMenu().findItem(R.id.attendance).setVisible(false);
            navigationView.getMenu().findItem(R.id.myassignment).setVisible(false);
            navigationView.getMenu().findItem(R.id.wise).setVisible(false);
            navigationView.getMenu().findItem(R.id.gallary).setVisible(false);
            navigationView.getMenu().findItem(R.id.feebill).setVisible(false);
            navigationView.getMenu().findItem(R.id.transportbill).setVisible(false);
            navigationView.getMenu().findItem(R.id.feereceipt).setVisible(false);
            navigationView.getMenu().findItem(R.id.transportreceipt).setVisible(false);





        } else if (types.equalsIgnoreCase("ADMIN")) {

            String url_staff = "http://infoes.in/sunil/mcsd/user/staffNotice?e_mail=" + e_mail1;
            Log.d("staff_data", e_mail1);
            getStringReqStaff(url_staff, email);
            card1Main2.setVisibility(View.GONE);
            linearLayoutStaffNotice.setVisibility(View.VISIBLE);
            navigationView.getMenu().findItem(R.id.sms).setVisible(true);

            navigationView.getMenu().findItem(R.id.attendance).setVisible(false);
            navigationView.getMenu().findItem(R.id.myassignment).setVisible(false);
            navigationView.getMenu().findItem(R.id.wise).setVisible(false);
            navigationView.getMenu().findItem(R.id.gallary).setVisible(false);
            navigationView.getMenu().findItem(R.id.feebill).setVisible(false);
            navigationView.getMenu().findItem(R.id.transportbill).setVisible(false);
            navigationView.getMenu().findItem(R.id.feereceipt).setVisible(false);
            navigationView.getMenu().findItem(R.id.transportreceipt).setVisible(false);
            navigationView.getMenu().findItem(R.id.SAttendanceSummary).setVisible(false);


        }

        text_name.setText("" + LocalSharedPreferences.getUserName(context));

      //  statusprofile.setText(""+LocalSharedPreferences.getUserEmail(context));


        if (img != null) {
            Glide.with(this).load(img).into(image_viewprofile);
        }


  //    setTitle(" Hello " + LocalSharedPreferences.getUserName(context) );

       String str = LocalSharedPreferences.getUserName(context);

        String[] splitStr = str.split("\\s+");
        String name = splitStr[0];
        String s1 = name.substring(0, 1).toUpperCase();
        String nameCapitalized = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        setTitle(Html.fromHtml("<medium>Hello! "+nameCapitalized +" </medium>"));

        card1Main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        final String finalE_mail = e_mail1;
        linearLayoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != finalE_mail) {
                    if (types.equalsIgnoreCase("STUDENT")) {
                        Intent intent = new Intent(Main2Activity.this, ViewPagerActivity.class);
                        intent.putExtra("e_mail", finalE_mail);
                        startActivity(intent);
                    } else if (types.equalsIgnoreCase("STAFF")) {
                        Intent i = new Intent(Main2Activity.this, StaffPagerActivity.class);
                        i.putExtra("e_mail", finalE_mail);
                        startActivity(i);

                    } else if (types.equalsIgnoreCase("ADMIN")) {
                        Intent i = new Intent(Main2Activity.this, StaffPagerActivity.class);
                        i.putExtra("e_mail", finalE_mail);
                        startActivity(i);

                    }
                }
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        if (types != null) {
            if (types.equalsIgnoreCase("STUDENT")) {

                navigationView.getMenu().findItem(R.id.attendance).setVisible(true);
                navigationView.getMenu().findItem(R.id.myassignment).setVisible(true);
                navigationView.getMenu().findItem(R.id.wise).setVisible(true);
                navigationView.getMenu().findItem(R.id.gallary).setVisible(true);
                navigationView.getMenu().findItem(R.id.transportbill).setVisible(true);
                navigationView.getMenu().findItem(R.id.feereceipt).setVisible(true);
                navigationView.getMenu().findItem(R.id.transportreceipt).setVisible(true);
                navigationView.getMenu().findItem(R.id.feebill).setVisible(true);

                navigationView.getMenu().findItem(R.id.Schoolcalendar).setVisible(true);
                navigationView.getMenu().findItem(R.id.SSWise).setVisible(false);
                navigationView.getMenu().findItem(R.id.SAttendance).setVisible(false);
                navigationView.getMenu().findItem(R.id.sms).setVisible(false);
                navigationView.getMenu().findItem(R.id.staffbirthday).setVisible(false);
                navigationView.getMenu().findItem(R.id.SAttendanceSummary).setVisible(false);


            }
        } else if (types.equalsIgnoreCase("STAFF")) {
            navigationView.getMenu().findItem(R.id.attendance).setVisible(false);
            navigationView.getMenu().findItem(R.id.myassignment).setVisible(false);
            navigationView.getMenu().findItem(R.id.wise).setVisible(false);
            navigationView.getMenu().findItem(R.id.gallary).setVisible(false);
            navigationView.getMenu().findItem(R.id.feebill).setVisible(false);
            navigationView.getMenu().findItem(R.id.transportbill).setVisible(false);
            navigationView.getMenu().findItem(R.id.feereceipt).setVisible(false);
            navigationView.getMenu().findItem(R.id.transportreceipt).setVisible(false);

            navigationView.getMenu().findItem(R.id.sms).setVisible(false);


            navigationView.getMenu().findItem(R.id.Schoolcalendar).setVisible(true);
            navigationView.getMenu().findItem(R.id.SAttendance).setVisible(true);
            navigationView.getMenu().findItem(R.id.SSWise).setVisible(true);
            navigationView.getMenu().findItem(R.id.staffbirthday).setVisible(true);
            navigationView.getMenu().findItem(R.id.SAttendanceSummary).setVisible(true);

        }
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}

