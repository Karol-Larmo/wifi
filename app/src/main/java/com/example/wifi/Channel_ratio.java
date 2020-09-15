package com.example.wifi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Channel_ratio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_ratio);
        Show_Channel();
    }


    public void Show_Channel()
    {
        TextView chan1 = findViewById(R.id.channel_1);
        TextView chan2 = findViewById(R.id.channel_2);
        TextView chan3 = findViewById(R.id.channel_3);
        TextView chan4 = findViewById(R.id.channel_4);
        TextView chan5 = findViewById(R.id.channel_5);
        TextView chan6 = findViewById(R.id.channel_6);
        TextView chan7 = findViewById(R.id.channel_7);
        TextView chan8 = findViewById(R.id.channel_8);
        TextView chan9 = findViewById(R.id.channel_9);
        TextView chan10 = findViewById(R.id.channel_10);
        TextView chan11 = findViewById(R.id.channel_11);
        TextView chan12 = findViewById(R.id.channel_12);
        TextView chan13 = findViewById(R.id.channel_13);
        TextView chan14 = findViewById(R.id.channel_14);



        float[] tab_f = Wifi_info.Channel_tab;
        int[] tab =  new int[tab_f.length];
        int[] tab_c = Wifi_info.Channel_count;
        for(int i=0; i<tab_f.length; i++)
        {
            tab[i] = Math.round(tab_f[i]);
            tab_c[i] /= 10;
        }

        String[] tmp = new String[14];
        for(int i=0; i<14; i++)
        {
             tmp[i] = "\t\tChannel_"+ (i+1) +" = " + tab[i] + "\t\t\t\t\t\t\t\t Device: " + tab_c[i];
        }
        chan1.setText(tmp[0]);
        chan2.setText(tmp[1]);
        chan3.setText(tmp[2]);
        chan4.setText(tmp[3]);
        chan5.setText(tmp[4]);
        chan6.setText(tmp[5]);
        chan7.setText(tmp[6]);
        chan8.setText(tmp[7]);
        chan9.setText(tmp[8]);
        chan10.setText(tmp[9]);
        chan11.setText(tmp[10]);
        chan12.setText(tmp[11]);
        chan13.setText(tmp[12]);
        chan14.setText(tmp[13]);


    }
}