package com.example.wifi;

import androidx.annotation.Nullable;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class Wifi_info extends AppCompatActivity {

    private WifiManager mainWifi;
    private WifiReceiver receiverWifi;
    private Button btnRefresh;
    private Button graf;
   float[] Channel_tab = new float [14];
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


        for(int i=0; i<14; i++)
        {
            Channel_tab[i] =0;
        }

    }

    public void GrafWifi(View view) {
        Log.i("CHANNELS_tab", "channel1 = " + Channel_tab[0]);
        Log.i("CHANNELS_tab", "channel2 = " + Channel_tab[1]);
        Log.i("CHANNELS_tab", "channel3 = " + Channel_tab[2]);
        Log.i("CHANNELS_tab", "channel4 = " + Channel_tab[3]);
        Log.i("CHANNELS_tab", "channel5 = " + Channel_tab[4]);
        Log.i("CHANNELS_tab", "channel6 = " + Channel_tab[5]);
        Log.i("CHANNELS_tab", "channel7 = " + Channel_tab[6]);
        Log.i("CHANNELS_tab", "channel8 = " + Channel_tab[7]);
        Log.i("CHANNELS_tab", "channel9 = " + Channel_tab[8]);
        Log.i("CHANNELS_tab", "channel0 = " + Channel_tab[9]);
        Log.i("CHANNELS_tab", "channel11 = " + Channel_tab[10]);
        Log.i("CHANNELS_tab", "channel12 = " + Channel_tab[11]);
        Log.i("CHANNELS_tab", "channel13 = " + Channel_tab[12]);
        Log.i("CHANNELS_tab", "channel14 = " + Channel_tab[13]);


        Intent intent = new Intent(this, Graf.class);
        startActivityForResult(intent, RESULT_OK);
    }

    public void Update_values()
    {

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("RESULT", " INN");
        if(resultCode == RESULT_OK)
        {
           float tab[] = data.getExtras().getFloatArray("TAB_VALUES");
           for(int i=0; i<14; i++)
           {
               Log.i("RESULT", " OKKK");
               Channel_tab[i] += tab[i];
           }
 ///NIE DZIALA COS DODAWNIE, NIE ODPALA TEJ FUNKCJI NA RAZIE DUNNO WHY
        }
    }


}