package com.martin.common.base.network;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.martin.common.base.utils.ApiUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 网络状态工具类
 */

public class NetWorkUtil {

    /**
     * 判断是否是联网状态
     *
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (ApiUtil.isSupportApi(Build.VERSION_CODES.LOLLIPOP)) {
                Network[] infos = cm.getAllNetworks();
                NetworkInfo networkInfo;
                for (Network network : infos) {
                    networkInfo = cm.getNetworkInfo(network);
                    if (NetworkInfo.State.CONNECTED.equals(networkInfo.getState())) {
                        return true;
                    }
                }
            } else {
                NetworkInfo[] infos = cm.getAllNetworkInfo();
                if (infos != null) {
                    for (NetworkInfo networkInfo : infos) {
                        if (NetworkInfo.State.CONNECTED.equals(networkInfo.getState())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断当前是否是wifi网络
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前是否是3G移动网络
     *
     * @param context
     * @return
     */
    public static boolean is3G(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前是否是2G移动网络
     *
     * @param context
     * @return
     */
    public static boolean is2G(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && (networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE
                    || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS
                    || networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_CDMA)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断wifi是否打开
     *
     * @param context
     * @return
     */

    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        if (cm != null) {
            return cm.getActiveNetworkInfo().getState() != null
                    && cm.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED;
        } else if (tm != null) {
            return tm.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS;
        }
        return false;

    }

    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */

    public static boolean isNetWorkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     */

    public static boolean isMobileNetAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            //获取NetworkInfo对象
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            //判断NetworkInfo对象是否为空 并且类型是否为MOBILE
            if (networkInfo != null && ConnectivityManager.TYPE_MOBILE == networkInfo.getType()) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取当前网络类型信息
     *
     * @param context
     * @return
     */
    public static int getConnectedNetType(Context context) {


        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            //获取NetworkInfo对象
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            //判断NetworkInfo对象是否为空 并且类型是否为MOBILE
            if (networkInfo != null) {
                return networkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * 获取当前的网络状态 ：没有网络-0：WIFI网络1：4G网络-4：3G网络-3：2G网络-2
     * 自定义
     *
     * @param context
     * @return
     */
    public static int getAPNType(Context context) {

        int netType = 0;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null || cm.getActiveNetworkInfo() == null) {
            return netType;
        }
        int type = cm.getActiveNetworkInfo().getType();
        if (type == ConnectivityManager.TYPE_WIFI) {
            netType = 1;
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            int subType = cm.getActiveNetworkInfo().getSubtype();
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (subType == TelephonyManager.NETWORK_TYPE_LTE && !tm.isNetworkRoaming()) {
                netType = 4;
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                    || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0 && !tm.isNetworkRoaming()) {
                netType = 3;
            } else if (subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE
                    || subType == TelephonyManager.NETWORK_TYPE_CDMA
                    && !tm.isNetworkRoaming()) {
                netType = 2;
            } else {
                netType = 2;
            }
        }

        return netType;
    }

    /**
     * 判断手机GPS是否开启
     *
     * @param context
     * @return
     */
    public static boolean isGpsOpen(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return manager != null && manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 获取本机ip地址
     *
     * @return
     */
    public static String getHostIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();

                for (Enumeration<InetAddress> ipAddr = intf.getInetAddresses(); ipAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取本机串号imei
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getDeviceId() : null;
    }

    /**
     * 判断是否有外网连接（普通方法不能判断外网的网络是否连接，比如连接上局域网）
     *
     * @return
     */
    public static final boolean ping() {
        String result = null;
        String ip = "www.baidu.com";
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader in = null;
        try {
            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);
            // 读取ping的内容
            inputStream = p.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            in = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String content = "";
            while ((content = in.readLine()) != null) {
                buffer.append(content);
            }
            Log.d("------ping-----", "result content : " + buffer.toString());

            // ping的状态
            int status = p.waitFor();
            if (status == 0) {
                result = "success";
                return true;
            } else {
                result = "failed";
            }


        } catch (IOException e) {
            result = "IOException";
        } catch (InterruptedException e) {
            result = "InterruptedException";
        } finally {
            Log.d("----result---", "result = " + result);
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

}