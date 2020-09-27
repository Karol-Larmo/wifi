package com.example.wifi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class History_Activity extends AppCompatActivity {

    public static float[] Channel_tab = new float [14];
    public static int[] Channel_count = new int[14];
    public final String Channel_STRING = "CHANNEL_RATIO_DATA";
    public final String ChannelShared = "ChannelShared";
    private ListView list;
    private ArrayAdapter<String> adapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        

        getValues();

        list = (ListView) findViewById(R.id.history_list);
        ArrayList<String> surveyList = new ArrayList<String>();

        Log.i("Size", " " + SurveyList.ITEMS.size());
        for (int i =0; i<SurveyList.ITEMS.size(); i ++)
        {
            SurveyList.Survey tmp = SurveyList.ITEMS.get(i);
            int[] tab2 = tmp.getValue();
            int[][] tab3 = new int [14][2];
            //Log.i("Tab", "Tab2 = " + tab2[0] + " ... " + tab2[1] + " ... " + tab2[2] + " ... " + tab2[3] + " ... " + tab2[4] + " ... " + tab2[5] + " ... " + tab2[6] + " ... " + tab2[7] + " ... " + tab2[8] + " ... " + tab2[9] + " ... ");

            for(int j=0; j<14; j++) {
                tab3[j][1] = j+1;
                tab3[j][0] = tab2[j];
            }



            Log.i("Tab", "Tab3 =  " + tab3[0][0] + " c... " + tab3[0][1] + " ... "+ tab3[1][0] + " ...c " + tab3[1][1] +" ... " + tab3[2][0] + " ...c " + tab3[2][1] + " ... "+ tab3[3][0] + " ... c" + tab3[3][1] + " ... " + tab3[4][0] + " ...c " + tab3[4][1] + " ... ");

            Arrays.sort(tab3, (a,b) -> Integer.compare(a[0],b[0]));

            Log.i("Tab", "Tab2 = " + tab2[0] + " ... " + tab2[1] + " ... " + tab2[2] + " ... " + tab2[3] + " ... " + tab2[4] + " ... " + tab2[5] + " ... " + tab2[6] + " ... " + tab2[7] + " ... " + tab2[8] + " ... " + tab2[9] + " ... ");
            Log.i("Tab", "Tab3 =  " + tab3[0][0] + " c... " + tab3[0][1] + " ... "+ tab3[1][0] + " ...c " + tab3[1][1] +" ... " + tab3[2][0] + " ...c " + tab3[2][1] + " ... "+ tab3[3][0] + " ... c" + tab3[3][1] + " ... " + tab3[4][0] + " ...c " + tab3[4][1] + " ... ");

            // String string_tmp = "test";
            String string_tmp = "\nSurvey " + (i+1) +
                    "\nBest Channels: " + tab3[0][1] + ", " + tab3[1][1] + ", " + tab3[2][1] +
                    "\nValues: \t" + tab3[0][0] + ", " + tab3[1][0] + ", " + tab3[2][0]  ;

            surveyList.add(string_tmp);

            Log.i("String", "String added " + string_tmp + " " );
        }

        adapter = new ArrayAdapter<String>(this, R.layout.row, surveyList);
        list.setAdapter(adapter);




        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigationH);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(History_Activity.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(History_Activity.this, MainActivity.class);
                        History_Activity.this.startActivity(homeIntent);
                        break;
                    case R.id.action_sieci_WiFi:
                        Toast.makeText(History_Activity.this, "Networks", Toast.LENGTH_SHORT).show();
                        Intent infoIntent = new Intent(History_Activity.this, Wifi_info.class);
                        History_Activity.this.startActivity(infoIntent);
                        break;
                    case R.id.action_grafy:
                        Toast.makeText(History_Activity.this, "Graphs", Toast.LENGTH_SHORT).show();
                        Intent grafIntent = new Intent(History_Activity.this, Graf_bars.class);
                        History_Activity.this.startActivity(grafIntent);
                        break;
                    case R.id.action_historia:
                        Toast.makeText(History_Activity.this, "History", Toast.LENGTH_SHORT).show();
                       // Intent historyIntent = new Intent(History_Activity.this, History_Activity.class);
                        //History_Activity.this.startActivity(historyIntent);
                        break;
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.action_historia);

    }



    public void getValues()
    {
        for(int i=0; i<14; i++)
        {
            Channel_tab[i] =Wifi_info.Channel_tab[i];
            Channel_count[i] = Wifi_info.Channel_count[i];

        }
    }


    public void saveChannels(View viw)
    {
        saveChannels();
    }


    private void saveChannels()
    {
        SharedPreferences channels = getSharedPreferences(ChannelShared, MODE_PRIVATE);
        SharedPreferences.Editor editor = channels.edit();

        String ID = "Channel_";


        editor.clear();
        editor.putInt(Channel_STRING, SurveyList.ITEMS.size());
        for (int j=0; j<SurveyList.ITEMS.size(); j++)
        {
            for(int i=0; i<Channel_tab.length; i++)
            {

                editor.putInt(ID + i, (int) Channel_tab[i]);

            }
        }

        editor.apply();
    }




    public void restoreChannels()
    {
        SharedPreferences channel = getSharedPreferences(ChannelShared, MODE_PRIVATE);
        int numOfChannelsSeries = channel.getInt(Channel_STRING, 0);
        int count = 0;
        if(numOfChannelsSeries != 0)
        {
            for(int i=0; i<14; i++)
            {
                Channel_tab[i] =0;
                Channel_count[i] = 0;

            }
        }

        String ID = "Channel_";

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


    public void clearList(View view) {
        SurveyList.clearList();
        saveChannels();
        Intent historyIntent = new Intent(History_Activity.this, History_Activity.class);
       startActivity(historyIntent);
    }
}