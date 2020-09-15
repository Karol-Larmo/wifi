package com.example.wifi;

import java.util.ArrayList;
import java.util.List;

public class SurveyList {

    public static final List<Survey> ITEMS = new ArrayList<Survey>();



    public static class Survey {
        public String id;
        public static int[] Channels_ratio;


        public Survey()
        {

        }

        public Survey(String id, int[] tab)
        {
            this.id =id;
            this.Channels_ratio = tab;
        }

    }

}
