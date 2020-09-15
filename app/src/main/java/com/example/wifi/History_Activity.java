package com.example.wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class History_Activity extends AppCompatActivity {

    public static float[] Channel_tab = new float [14];
    public static int[] Channel_count = new int[14];
    public final String Channel_STRING = "CHANNEL_RATIO_DATA";
    public final String ChannelShared = "ChannelShared";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getValues();

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
        editor.putInt(Channel_STRING, Channel_tab.length);

        for(int i=0; i<Channel_tab.length; i++)
        {

            editor.putInt(ID + i, (int) Channel_tab[i]);

        }
        editor.apply();
    }


    private void restoreChannels()
    {
        SharedPreferences channel = getSharedPreferences(ChannelShared, MODE_PRIVATE);
        int numOfChannelsSeries = channel.getInt(Channel_STRING, 0);
        if(numOfChannelsSeries != 0)
        {
            for(int i=0; i<14; i++)
            {
                Channel_tab[i] =0;
                Channel_count[i] = 0;

            }
        }

        String ID = "Channel_";


        for (int i=0; i<numOfChannelsSeries; i++)
        {
            int value = channel.getInt(ID +i, 0);
            Channel_count[i] = value;

        }
    }


}