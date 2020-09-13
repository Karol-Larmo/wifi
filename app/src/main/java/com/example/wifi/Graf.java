package com.example.wifi;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wifi.permission.PermissionService;

import java.util.ArrayList;
import java.util.List;

public class Graf extends AppCompatActivity {
    private WifiManager mainWifi;
    ListView lvWifiDetails1;
    ListAdapter adapter1;
    List wifiList1;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graf);
        lvWifiDetails1 = (ListView) findViewById(R.id.lvWifiDetails1);
        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //Graff();
        ArrayList<Integer> connections = new ArrayList<>();

        List<ScanResult> wifiList1;
        wifiList1 = mainWifi.getScanResults();
        for (int i = 0; i < wifiList1.size(); i++) {
            connections.add(wifiList1.get(i).frequency);
        }
        for (int i = 0; i < wifiList1.size(); i++) {

            if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 1) {
                channelCount1++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 2) {
                channelCount2++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 3) {
                channelCount3++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 4) {
                channelCount4++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 5) {
                channelCount5++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 6) {
                channelCount6++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 7) {
                channelCount7++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 8) {
                channelCount8++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 9) {
                channelCount9++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 10) {
                channelCount10++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 11) {
                channelCount11++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 12) {
                channelCount12++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 13) {
                channelCount13++;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 14) {
                channelCount14++;
            }
        }
    }

    private void setAdapter() {
        adapter1 = new ListAdapter(getApplicationContext(), wifiList1);
        lvWifiDetails1.setAdapter(adapter1);
    }

    private void scanWifiList() {
        mainWifi.startScan();
        wifiList1 = mainWifi.getScanResults();

        setAdapter();

    }

    int ieee80211_frequency_to_channel(int freq) {
        if (freq == 2484)
            return 14;

        if (freq < 2484)
            return (freq - 2407) / 5;

        return freq / 5 - 1000;
    }
}