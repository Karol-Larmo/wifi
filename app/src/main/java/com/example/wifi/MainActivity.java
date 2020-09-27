package com.example.wifi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.wifi.permission.PermissionService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    private String currentCountryCode;
    private PermissionService permissionService;
    public final String Channel_STRING = "CHANNEL_RATIO_DATA";
    public final String ChannelShared = "ChannelShared";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionService = new PermissionService(this);
        permissionService.check();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        //Intent homeIntent = new Intent(MainActivity.this, MainActivity.class);
                        //MainActivity.this.startActivity(homeIntent);
                        break;
                    case R.id.action_sieci_WiFi:
                        Toast.makeText(MainActivity.this, "Networks", Toast.LENGTH_SHORT).show();
                        Intent infoIntent = new Intent(MainActivity.this, Wifi_info.class);
                        startActivity(infoIntent);
                        break;
                    case R.id.action_grafy:
                        Toast.makeText(MainActivity.this, "Graphs", Toast.LENGTH_SHORT).show();
                        Intent grafIntent = new Intent(MainActivity.this, Graf_bars.class);
                        startActivity(grafIntent);
                        break;
                    case R.id.action_historia:
                        Toast.makeText(MainActivity.this, "History", Toast.LENGTH_SHORT).show();
                        Intent historyIntent = new Intent(MainActivity.this, History_Activity.class);
                        startActivity(historyIntent);
                        break;
                }
                return true;
            }
        });

        if(SurveyList.ITEMS.size() == 0)
        restoreChannels();
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

    public void restoreChannels()
    {
        SharedPreferences channel = getSharedPreferences(ChannelShared, MODE_PRIVATE);
        int numOfChannelsSeries = channel.getInt(Channel_STRING, 0);
        int count = 0;
        int[] Channel_tab = new int [14];
        if(numOfChannelsSeries != 0)
        {

        }
        Log.i("PRZYWROCONO ", " = " + numOfChannelsSeries);
        String ID = "Channel_";
        SurveyList.clearList();
        for (int j=0; j<numOfChannelsSeries; j++)
        {
            for (int i=0; i<14; i++)
            {
                int value = channel.getInt(ID +i, 0);
                Channel_tab[i] = value;
            }
            SurveyList.Survey survey= new SurveyList.Survey(String.valueOf(j),Channel_tab);
            SurveyList.addItem(survey);
        }
    }

}