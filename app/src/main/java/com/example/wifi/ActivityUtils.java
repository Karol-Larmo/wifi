/*
 * WiFiAnalyzer
 * Copyright (C) 2019  VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.example.wifi;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.wifi.util.IntentUtils;


import static android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS;
import static android.provider.Settings.Panel.ACTION_WIFI;

public class ActivityUtils {
    private ActivityUtils() {
        throw new IllegalStateException("Utility class");
    }

    static void setActionBarOptions(ActionBar actionBar) {
        if (actionBar == null) {
            return;
        }
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }



 /*

    @TargetApi(Build.VERSION_CODES.Q)
    public static void startWiFiSettings() {
        MainActivity mainActivity = MainContext.INSTANCE.getMainActivity();
        Intent intent = IntentUtils.makeIntent(ACTION_WIFI);
        mainActivity.startActivityForResult(intent, 0);
    }
*/
    static void startLocationSettings() {
        MainActivity mainActivity = MainContext.INSTANCE.getMainActivity();
        Intent intent = IntentUtils.makeIntent(ACTION_LOCATION_SOURCE_SETTINGS);
        mainActivity.startActivity(intent);
    }
/*
    static class WiFiBandToggle implements View.OnClickListener {
        private final MainActivity mainActivity;

        WiFiBandToggle(@NonNull MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onClick(View view) {
            if (mainActivity.getCurrentNavigationMenu().isWiFiBandSwitchable()) {
                MainContext.INSTANCE.getSettings().toggleWiFiBand();
            }
        }
    }
*/
}
