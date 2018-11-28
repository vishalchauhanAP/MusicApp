package com.practice.virtusa.musicapp.common;

import com.practice.virtusa.musicapp.helpers.Utils;

public final class AppConfig {
    private static int serviceTimeOut;
    private static String serverAppUrl;
    private static String baseServiceHost;

    public static String getServerAppUrl() {
        return getbaseServiceHost();
    }

    public static void setServerAppUrl(String serverAppUrl) {
        AppConfig.serverAppUrl = serverAppUrl;
    }

    public static int getServiceTimeOut() {
        return Integer.parseInt(Utils.AppPackageManager.getMetaDataOrDefault("service_timeout_sec","40"));
    }

    public static void setServiceTimeOut(int serviceTimeOut) {
        AppConfig.serviceTimeOut = serviceTimeOut;
    }

    public static String getbaseServiceHost() {
        return Utils.AppPackageManager.getMetaDataOrDefault("base_service_host","https://jsonplaceholder.typicode.com/");
    }

    public static void setbaseServiceHost(String baseServiceHost) {
        AppConfig.baseServiceHost = baseServiceHost;
    }
}
