package com.martin.common.base.network;

/**
 * 网络类型
 */

public class NetWorkState {

    private static NetWorkState instance = null;
    public boolean isEnablaWifi = false;
    public boolean isWifi = false;
    public boolean isMobile = false;
    public boolean isConnected = false;

    private NetWorkState() {
    }

    public static NetWorkState getInstance() {
        if (instance == null) {
            instance = new NetWorkState();
        }
        return instance;
    }
}
