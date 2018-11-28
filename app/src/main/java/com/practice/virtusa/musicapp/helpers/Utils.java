package com.practice.virtusa.musicapp.helpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.practice.virtusa.musicapp.app_start.MusicApp;

public class Utils {
    public static final class AppPackageManager
    {
        public static String getMetaDataOrDefault(String key, String defaultValue) {
            String result;
            try {
                Context context = MusicApp.getInstance().getBaseContext();
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                Bundle bundle = appInfo.metaData;
                result =  bundle.getString(key).trim();
            } catch (Exception ex) {
                result = defaultValue;
            }
            return result;
        }
    }
    public static final class StringUtil {
        public static String toTitleCase(String s) {
            final String ACTIONABLE_DELIMITERS = " '-/";

            StringBuilder sb = new StringBuilder();

            boolean capNext = true;
            for (char c : s.toCharArray()) {
                c = (capNext)
                        ? Character.toUpperCase(c)
                        : Character.toLowerCase(c);
                sb.append(c);
                capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0);
            }
            return sb.toString();
        }
    }

}

