package com.example.wifi;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
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
    float channelCount1 = 0;
    float channelCount2 = 0;
    float channelCount3 = 0;
    float channelCount4 = 0;
    float channelCount5 = 0;
    float channelCount6 = 0;
    float channelCount7 = 0;
    float channelCount8 = 0;
    float channelCount9 = 0;
    float channelCount10 = 0;
    float channelCount11 = 0;
    float channelCount12 = 0;
    float channelCount13 = 0;
    float channelCount14 = 0;

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
                float tmp = (float) (-100.0/ wifiList1.get(i).level);
                channelCount1+= tmp;
                channelCount2+=tmp;
                channelCount3+=0.3*tmp;
                //Log.i("CHANNELS", "tmp = " + tmp);
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 2) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount1+= tmp;
                channelCount2+= tmp;
                channelCount3+= tmp;
                channelCount4+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 3) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount1+= 0.3* tmp;
                channelCount2+= tmp;
                channelCount3+= tmp;
                channelCount4+=  tmp;
                channelCount5+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 4) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount2+= 0.3* tmp;
                channelCount3+= tmp;
                channelCount4+= tmp;
                channelCount5+=  tmp;
                channelCount6+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 5) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount3+= 0.3* tmp;
                channelCount4+= tmp;
                channelCount5+= tmp;
                channelCount6+=  tmp;
                channelCount7+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 6) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount4+= 0.3* tmp;
                channelCount5+= tmp;
                channelCount6+= tmp;
                channelCount7+=  tmp;
                channelCount8+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 7) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount5+= 0.3* tmp;
                channelCount6+= tmp;
                channelCount7+= tmp;
                channelCount8+=  tmp;
                channelCount9+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 8) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount6+= 0.3* tmp;
                channelCount7+= tmp;
                channelCount8+= tmp;
                channelCount9+=  tmp;
                channelCount10+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 9) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount7+= 0.3* tmp;
                channelCount8+= tmp;
                channelCount9+= tmp;
                channelCount10+=  tmp;
                channelCount11+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 10) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount8+= 0.3* tmp;
                channelCount9+= tmp;
                channelCount10+= tmp;
                channelCount11+=  tmp;
                channelCount12+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 11) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount9+= 0.3* tmp;
                channelCount10+= tmp;
                channelCount11+= tmp;
                channelCount12+=  tmp;
                channelCount13+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 12) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount10+= 0.3* tmp;
                channelCount11+= tmp;
                channelCount12+= tmp;
                channelCount13+=  tmp;
                channelCount14+= 0.3* tmp;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 13) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount11+= 0.3* tmp;
                channelCount12+= tmp;
                channelCount13+= tmp;
                channelCount14+=  tmp;

            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 14) {
                float tmp = (float) (-200.0/ wifiList1.get(i).level);
                channelCount12+= 0.3* tmp;
                channelCount13+= tmp;
                channelCount14+= tmp;

            }
        }

        Log.i("CHANNELS", "channel1 = " + channelCount1);
        Log.i("CHANNELS", "channel2 = " + channelCount2);
        Log.i("CHANNELS", "channel3 = " + channelCount3);
        Log.i("CHANNELS", "channel4 = " + channelCount4);
        Log.i("CHANNELS", "channel5 = " + channelCount5);
        Log.i("CHANNELS", "channel6 = " + channelCount6);
        Log.i("CHANNELS", "channel7 = " + channelCount7);
        Log.i("CHANNELS", "channel8 = " + channelCount8);
        Log.i("CHANNELS", "channel9 = " + channelCount9);
        Log.i("CHANNELS", "channel0 = " + channelCount10);
        Log.i("CHANNELS", "channel11 = " + channelCount11);
        Log.i("CHANNELS", "channel12 = " + channelCount12);
        Log.i("CHANNELS", "channel13 = " + channelCount13);
        Log.i("CHANNELS", "channel14 = " + channelCount14);

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

    public void getValuesOfChannels(View view)
    {
        float tab[] = new float[14];

        tab[0] = channelCount1;
        tab[1] = channelCount2;
        tab[2] = channelCount3;
        tab[3] = channelCount4;
        tab[4] = channelCount5;
        tab[5] = channelCount6;
        tab[6] = channelCount7;
        tab[7] = channelCount8;
        tab[8] = channelCount9;
        tab[9] = channelCount10;
        tab[10] = channelCount11;
        tab[11] = channelCount12;
        tab[12] = channelCount13;
        tab[13] = channelCount14;


        Intent intent = new Intent();
        intent.putExtra("TAB_VALUES", tab);
        setResult(RESULT_OK, intent);

        Log.i("Result", "send");
        finish();
    }


    int ieee80211_frequency_to_channel(int freq) {
        if (freq == 2484)
            return 14;

        if (freq < 2484)
            return (freq - 2407) / 5;

        return freq / 5 - 1000;
    }
}