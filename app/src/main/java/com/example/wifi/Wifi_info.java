package com.example.wifi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



public class Wifi_info extends AppCompatActivity {

    private WifiManager mainWifi;
    private WifiReceiver receiverWifi;
    private Button btnRefresh;
    private Button graf;
    int channelCount1 = 0;
    int channelCount2 = 0;
    int channelCount3 = 0;
    int channelCount4 = 0;
    int channelCount5 = 0;
    int channelCount6 = 0;
    int channelCount7 = 0;
    int channelCount8 = 0;
    int channelCount9 = 0;
    int channelCount10 = 0;
    int channelCount11 = 0;
    int channelCount12 = 0;
    int channelCount13 = 0;
    int channelCount14 = 0;
    ListAdapter adapter;
    ListView lvWifiDetails;
    List wifiList;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_info);


        lvWifiDetails = (ListView) findViewById(R.id.lvWifiDetails);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        graf = (Button) findViewById(R.id.graf);
        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        receiverWifi = new WifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        scanWifiList();
        btnRefresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                scanWifiList();

            }
        });



    }

    public void GrafWifi(View view) {
        Intent intent = new Intent(this, Graf.class);
        startActivity(intent);
    }


    private void setAdapter() {
        adapter = new ListAdapter(getApplicationContext(), wifiList);
        lvWifiDetails.setAdapter(adapter);
    }
    private void scanWifiList() {
        mainWifi.startScan();
        wifiList = mainWifi.getScanResults();

        setAdapter();

    }

    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
        }
    }


}