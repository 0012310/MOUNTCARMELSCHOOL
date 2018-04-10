package gct.example.com.mountcarmelschool;



import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import java.util.ArrayList;

import gct.example.com.mountcarmelschool.classes.CommonMethods;

public class Splash extends AppCompatActivity {

    private boolean mIsBackButtonPressed;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if (!mIsBackButtonPressed) {
                    checkPermissions();
                }
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        mIsBackButtonPressed = true;
        Splash.this.finish();
        super.onBackPressed();
    }

    //region "Permissions"
    private static final int REQUEST_Permission = 0;
    private String[] permissionsArray = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    public void checkPermissions() {
        ArrayList<String> askedPermissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, permissionsArray[0]) != PackageManager.PERMISSION_GRANTED) {
            askedPermissionList.add(permissionsArray[0]);
        }
        if (ContextCompat.checkSelfPermission(this, permissionsArray[1]) != PackageManager.PERMISSION_GRANTED) {
            askedPermissionList.add(permissionsArray[1]);
        }
        if (ContextCompat.checkSelfPermission(this, permissionsArray[2]) != PackageManager.PERMISSION_GRANTED) {
            askedPermissionList.add(permissionsArray[2]);
        }
        if (askedPermissionList.size() > 0) {
            //Need to ask permissions
            String[] permissionToBeAsked = askedPermissionList.toArray(new String[0]);
            loadPermissions(permissionToBeAsked, REQUEST_Permission);
        } else {

            Intent intent = new Intent(Splash.this, Main2Activity.class);
            Splash.this.startActivity(intent);
            finish();
        }
    }

    private void loadPermissions(String[] perm, int requestCode) {
        ActivityCompat.requestPermissions(this, perm, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        String permissionThatsNotPermittiedBYuser = "";
        switch (requestCode) {
            case REQUEST_Permission: {
                int count = 0;
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result == 0) {
                        } else {
                            permissionThatsNotPermittiedBYuser = permissionThatsNotPermittiedBYuser + permissions[count];
                        }
                        count++;
                    }
                }

                if (permissionThatsNotPermittiedBYuser.isEmpty()) {
                    finish();

                    Intent intent = new Intent(Splash.this, Main2Activity.class);
                    Splash.this.startActivity(intent);
                    //settingsRequest();
                } else {
                    boolean showAgainPermissionDialog = true;
                    if (ContextCompat.checkSelfPermission(this, permissionsArray[0]) != PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(Splash.this, permissionsArray[0])) {
                            showAgainPermissionDialog = false;
                        }
                    } else if (ContextCompat.checkSelfPermission(this, permissionsArray[1]) != PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(Splash.this, permissionsArray[1])) {
                            showAgainPermissionDialog = false;
                        }
                    } else if (ContextCompat.checkSelfPermission(this, permissionsArray[2]) != PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(Splash.this, permissionsArray[2])) {
                            showAgainPermissionDialog = false;
                        }
                    }

                    if (showAgainPermissionDialog) {
                        showMessageDialogAndRestartActivity("This permission required by the app.Please provide the permission.");
                    } else {
                        showMessageDialogThatYouCanEnablePermissionsFromSettings("Please enable permissions for the app from phone settings.");
                    }
                }
                return;
            }

        }

    }

    private void showMessageDialogAndRestartActivity(String message) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title("Oops!")
                .theme(Theme.LIGHT)
                .content(message)
                .positiveText("OK")
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        //finish();
                        CommonMethods.reloadActivity(Splash.this);
                    }
                });

        MaterialDialog dialog = builder.build();
        dialog.show();
    }

    private void showMessageDialogThatYouCanEnablePermissionsFromSettings(String message) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title("Oops!")
                .theme(Theme.LIGHT)
                .content(message)
                .positiveText("OK")
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        finish();
                    }
                });

        MaterialDialog dialog = builder.build();
        dialog.show();
    }
}