package com.example.wifi;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graf_bars extends AppCompatActivity {

    private WifiManager mainWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graf_bars);

        mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> wifiList1;
        wifiList1 = mainWifi.getScanResults();

        float[] tab = Wifi_info.Channel_tab;
        float[] tab1 = Wifi_info.Channel_tab;
        float min = tab1[0];

/*
        int channelMinNumber = 0;
        for(int i = 0; i<wifiList1.size();i++) {
            if(tab1[i] < min) {
                min = tab1[i];
                channelMinNumber = i + 1;
            }
        }

 */
        BarChart barChart = (BarChart) findViewById(R.id.barchart);

        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(tab[0], 0));
        entries.add(new BarEntry(tab[1], 1));
        entries.add(new BarEntry(tab[2], 2));
        entries.add(new BarEntry(tab[3], 3));
        entries.add(new BarEntry(tab[4], 4));
        entries.add(new BarEntry(tab[5], 5));
        entries.add(new BarEntry(tab[6], 6));
        entries.add(new BarEntry(tab[7], 7));
        entries.add(new BarEntry(tab[8], 8));
        entries.add(new BarEntry(tab[9], 9));
        entries.add(new BarEntry(tab[10], 10));
        entries.add(new BarEntry(tab[11], 11));
        entries.add(new BarEntry(tab[12], 12));
        entries.add(new BarEntry(tab[13], 13));


        BarDataSet bardataset = new BarDataSet(entries, "Channels");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");
        labels.add("7");
        labels.add("8");
        labels.add("9");
        labels.add("10");
        labels.add("11");
        labels.add("12");
        labels.add("13");
        labels.add("14");

        BarData data = new BarData(labels, bardataset);
/*
        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("1");
        xAxisLabel.add("2");
        xAxisLabel.add("3");
        xAxisLabel.add("4");
        xAxisLabel.add("5");
        xAxisLabel.add("6");
        xAxisLabel.add("7");
        xAxisLabel.add("8");
        xAxisLabel.add("9");
        xAxisLabel.add("10");
        xAxisLabel.add("11");
        xAxisLabel.add("12");
        xAxisLabel.add("13");
        xAxisLabel.add("14");
*/

        int[] tmpColors = new int[14];

        for(int i = 0; i < 14; i++) {
            if (tab[i]<30) {
                tmpColors[i] = getResources().getColor(R.color.dark_green);
            } else if (tab[i]>=30 && tab[i]<60) {
                tmpColors[i] = getResources().getColor(R.color.blue);//Color.rgb(247, 207, 19);
            } else if (tab[i]>=60) {
                tmpColors[i] = getResources().getColor(R.color.red);//Color.rgb(199, 0, 15);
            }
        }


        bardataset.setColors(tmpColors);
        barChart.animateY(5000);

        barChart.setData(data); // set the data and list of labels into chart
        barChart.setDescription(""); //+ channelMinNumber);  // set the description




        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        tab = null;
        tmpColors = null;
        wifiList1.clear();
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
}
