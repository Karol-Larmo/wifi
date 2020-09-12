package com.example.wifi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.wifi.permission.PermissionService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    private String currentCountryCode;
    private PermissionService permissionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionService = new PermissionService(this);
        permissionService.check();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!permissionService.isGranted(requestCode, grantResults)) {
            finish();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (permissionService.isPermissionGranted()) {
            if (!permissionService.isSystemEnabled()) {
                ActivityUtils.startLocationSettings();
            }
            //MainContext.INSTANCE.getScannerService().resume();
        }

    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public void GoToWifi(View view) {
        Intent intent = new Intent(this, Wifi_info.class);
        startActivity(intent);

    }
}