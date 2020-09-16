package com.example.wifi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.view.MenuItem;
import com.example.wifi.SurveyList;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Wifi_info extends AppCompatActivity {


    private WifiManager mainWifi;
    private WifiReceiver receiverWifi;
    private Button btnRefresh;
    private Button graf;
    public static float[] Channel_tab = new float [14];
    public static int[] Channel_count = new int[14];
    public int COUNT = 1;
    ListAdapter adapter;
    ListView lvWifiDetails;
    List wifiList;
    String BSSID="";



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_info);


        lvWifiDetails = (ListView) findViewById(R.id.lvWifiDetails);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        graf = (Button) findViewById(R.id.graf);
        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        WifiInfo info = null;
        if (mainWifi != null) {
            info = mainWifi.getConnectionInfo();
        }
         BSSID = info != null ? info.getBSSID() : null;

        Log.i("SSID", BSSID);

        receiverWifi = new WifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        scanWifiList();



        for(int i=0; i<14; i++)
        {
            Channel_tab[i] =0;
            Channel_count[i] = 0;
            Log.i("test", " test create");
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

/*
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(Wifi_info.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(Wifi_info.this, MainActivity.class);
                        Wifi_info.this.startActivity(homeIntent);
                        break;
                    case R.id.action_sieci_WiFi:
                        Toast.makeText(Wifi_info.this, "Sieci_WiFi", Toast.LENGTH_SHORT).show();
                        Intent infoIntent = new Intent(Wifi_info.this, Wifi_info.class);
                        Wifi_info.this.startActivity(infoIntent);
                        break;
                    case R.id.action_grafy:
                        Toast.makeText(Wifi_info.this, "Grafy", Toast.LENGTH_SHORT).show();
                        Intent grafIntent = new Intent(Wifi_info.this, Graf.class);
                        Wifi_info.this.startActivity(grafIntent);
                        break;
                    case R.id.action_historia:
                        Toast.makeText(Wifi_info.this, "Historia", Toast.LENGTH_SHORT).show();
                        Intent historyIntent = new Intent(Wifi_info.this, History_Activity.class);
                        Wifi_info.this.startActivity(historyIntent);
                        break;
                }
                return true;
            }
        }); */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void Channel_Ratio_10s(View view)
    {
    for(int i=0; i<10; i++)
    {
        Update_values();
        Log.i("test", "asd petla chuj mi na dupe");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    int[] tab = new int[14];
        for(int i=0; i<Channel_tab.length; i++)
        {
            tab[i] = Math.round(Channel_tab[i]);
        }
    SurveyList.Survey survey= new SurveyList.Survey(String.valueOf(COUNT), tab);
    SurveyList.addItem(survey);
    COUNT++;

    Intent data = new Intent(this, Channel_ratio.class);
    ArrayList<String> channels_string = new ArrayList<>();
    String tmp;
        for(int i=0; i<14; i++)
        {
            tmp = "Channel_"+ i +" = " + Channel_tab[i];
            channels_string.add(tmp);
        }


    data.putStringArrayListExtra("TAB_VALUES", channels_string);
    startActivity(data);

    }

    public void Test_channels(View view)
    {

        Channel_Ratio_10s(view);

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



    int ieee80211_frequency_to_channel(int freq) {
        if (freq == 2484)
            return 14;

        if (freq < 2484)
            return (freq - 2407) / 5;

        return freq / 5 - 1000;
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
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Result", " INN");
        if(resultCode == RESULT_OK)
        {
            float tab[] = data.getExtras().getFloatArray("TAB_VALUES");
            for(int i=0; i<14; i++)
            {
                Log.i("Result", " OKKK");
                Channel_tab[i] += tab[i];
            }
            ///NIE DZIALA COS DODAWNIE, NIE ODPALA TEJ FUNKCJI NA RAZIE DUNNO WHY
        }
    } */

    public void SCAN_WIFI_LIST(View view) {
        scanWifiList();
    }


    public void RESTART_COUNT()
    {
        for(int i=0; i<14; i++)
        {
            Channel_tab[i] =0;
            Channel_count[i] = 0;

        }
    }




    public void Update_values() {
        List<ScanResult> wifiList1;
        wifiList1 = mainWifi.getScanResults();

        for (int i = 0; i < wifiList1.size(); i++) {
            if(wifiList1.get(i).BSSID.equals(BSSID))
            {
                //do nothing
            }
            else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 1) {
                float tmp = (float) (-100.0 / wifiList1.get(i).level);
                Channel_tab[0] += tmp;
                Channel_tab[1]  += tmp;
                Channel_tab[2]  += 0.3 * tmp;
                //Log.i("CHANNELS", "tmp = " + tmp);
                Channel_count[0] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 2) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[0] += tmp;
                Channel_tab[1] += tmp;
                Channel_tab[2] += tmp;
                Channel_tab[3] += 0.3 * tmp;
                Channel_count[1] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 3) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[0] += 0.3 * tmp;
                Channel_tab[1] += tmp;
                Channel_tab[2] += tmp;
                Channel_tab[3] += tmp;
                Channel_tab[4] += 0.3 * tmp;
                Channel_count[2] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 4) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[1] += 0.3 * tmp;
                Channel_tab[2] += tmp;
                Channel_tab[3] += tmp;
                Channel_tab[4] += tmp;
                Channel_tab[5] += 0.3 * tmp;
                Channel_count[3] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 5) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[2] += 0.3 * tmp;
                Channel_tab[3] += tmp;
                Channel_tab[4] += tmp;
                Channel_tab[5] += tmp;
                Channel_tab[6] += 0.3 * tmp;
                Channel_count[4] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 6) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[3] += 0.3 * tmp;
                Channel_tab[4] += tmp;
                Channel_tab[5] += tmp;
                Channel_tab[6] += tmp;
                Channel_tab[7] += 0.3 * tmp;
                Channel_count[5] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 7) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[4] += 0.3 * tmp;
                Channel_tab[5] += tmp;
                Channel_tab[6] += tmp;
                Channel_tab[7] += tmp;
                Channel_tab[8] += 0.3 * tmp;
                Channel_count[6] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 8) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[5] += 0.3 * tmp;
                Channel_tab[6] += tmp;
                Channel_tab[7] += tmp;
                Channel_tab[8] += tmp;
                Channel_tab[9] += 0.3 * tmp;
                Channel_count[7] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 9) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[6] += 0.3 * tmp;
                Channel_tab[7] += tmp;
                Channel_tab[8] += tmp;
                Channel_tab[9] += tmp;
                Channel_tab[10] += 0.3 * tmp;
                Channel_count[8] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 10) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[7] += 0.3 * tmp;
                Channel_tab[8] += tmp;
                Channel_tab[9] += tmp;
                Channel_tab[10] += tmp;
                Channel_tab[11] += 0.3 * tmp;
                Channel_count[9] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 11) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[8] += 0.3 * tmp;
                Channel_tab[9] += tmp;
                Channel_tab[10] += tmp;
                Channel_tab[11] += tmp;
                Channel_tab[12] += 0.3 * tmp;
                Channel_count[10] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 12) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[9] += 0.3 * tmp;
                Channel_tab[10] += tmp;
                Channel_tab[11] += tmp;
                Channel_tab[12] += tmp;
                Channel_tab[13] += 0.3 * tmp;
                Channel_count[11] +=1;
            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 13) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[10] += 0.3 * tmp;
                Channel_tab[11] += tmp;
                Channel_tab[12] += tmp;
                Channel_tab[13] += tmp;
                Channel_count[12] +=1;

            } else if (ieee80211_frequency_to_channel(wifiList1.get(i).frequency) == 14) {
                float tmp = (float) (-200.0 / wifiList1.get(i).level);
                Channel_tab[11] += 0.3 * tmp;
                Channel_tab[12] += tmp;
                Channel_tab[13] += tmp;
                Channel_count[13] +=1;

            }
        }
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
        scanWifiList();
    }

    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
        }
    }

}