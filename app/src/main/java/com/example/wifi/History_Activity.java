package com.example.wifi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class History_Activity extends AppCompatActivity {

    public static float[] Channel_tab = new float [14];
    public static int[] Channel_count = new int[14];
    public final String Channel_STRING = "CHANNEL_RATIO_DATA";
    public final String ChannelShared = "ChannelShared";
    private ListView list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getValues();
/*
        list = (ListView) findViewById(R.id.history_list);
        ArrayList<String> surveyList = new ArrayList<String>();

        Log.i("Size", " " + SurveyList.ITEMS.size());
        for (int i =0; i<SurveyList.ITEMS.size(); i ++)
        {
            int[] tab2 = new int[14];
            int[] tab3 = new int[14];
            SurveyList.Survey tmp = SurveyList.ITEMS.get(i);
            tab2 = tmp.getValue();
            tab3 = tmp.getValue();

            int channel1 =0, channel2=0, channel3=0;
            Arrays.sort(tab2);
            for(int j=0; j<14; j++)
            {
                    if(tab2[j] == tab3[j] )
                    {
                        if(channel1 ==0)
                        channel1 = j;
                        else if(channel2 ==0)
                            channel2 = j;
                        else if (channel3 ==0)
                            channel3 = j;

                    }
            }
String string_tmp = "test";
            //String string_tmp = "Survey " + i +
              //      "\nBest Channels: " + channel1 + ", " + channel2 + ", " + channel3 +
               //     "\n Values: \t" + tab2[0] + ", " + tab2[1] + ", " + tab2[2] ;

            surveyList.add(string_tmp);

            Log.i("String", "String added " + string_tmp + " " + tab2[0]);
        }

        adapter = new ArrayAdapter<String>(this, R.layout.dataset_survey, surveyList);
        list.setAdapter(adapter);
*/

        list = (ListView) findViewById(R.id.history_list);

        String cars[] = {"Mercedes", "Fiat", "Ferrari", "Aston Martin", "Lamborghini", "Skoda", "Volkswagen", "Audi", "Citroen"};

        ArrayList<String> carL = new ArrayList<String>();
        carL.addAll( Arrays.asList(cars) );

        adapter = new ArrayAdapter<String>(this, R.layout.dataset_survey, carL);

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
                        Toast.makeText(History_Activity.this, "Sieci_WiFi", Toast.LENGTH_SHORT).show();
                        Intent infoIntent = new Intent(History_Activity.this, Wifi_info.class);
                        History_Activity.this.startActivity(infoIntent);
                        break;
                    case R.id.action_grafy:
                        Toast.makeText(History_Activity.this, "Grafy", Toast.LENGTH_SHORT).show();
                        Intent grafIntent = new Intent(History_Activity.this, Graf_bars.class);
                        History_Activity.this.startActivity(grafIntent);
                        break;
                    case R.id.action_historia:
                        Toast.makeText(History_Activity.this, "Historia", Toast.LENGTH_SHORT).show();
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


    private void restoreChannels()
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



}