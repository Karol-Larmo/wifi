package com.example.wifi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SurveyList {

    public static final List<Survey> ITEMS = new ArrayList<Survey>();
    public static final HashMap<String, Survey> ITEMS_MAP = new HashMap<String, Survey>();

    public static void addItem(Survey survey)
    {
        ITEMS.add(survey);
        ITEMS_MAP.put(survey.id, survey);
    }

    public static void clearList()
    {
        ITEMS.clear();
        ITEMS_MAP.clear();
    }








    public static class Survey {
        public String id;
        public static int[] Channels_ratio = new int[14];


        public Survey()
        {

        }

        public Survey(String id, int[] tab)
        {
            this.id =id;
            this.Channels_ratio = tab;
        }

        public Survey(String id, float[] tab)
        {
            this.id =id;
            for(int i=0; i<14; i++)
            {
                Channels_ratio[i] = Math.round(tab[i]);
            }
        }

        public void putValue(int[] tab)
        {
            Channels_ratio = tab;
        }

        public int[] getValue ()
        {
            return Channels_ratio;
        }

    }

}
