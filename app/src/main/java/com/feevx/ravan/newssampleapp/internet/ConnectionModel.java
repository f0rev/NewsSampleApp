package com.feevx.ravan.newssampleapp.internet;

public class ConnectionModel {

    private int mType;
    private boolean mIsConnected;

    public ConnectionModel(int type, boolean isConnected) {
        mType = type;
        mIsConnected = isConnected;
    }

    public int getType() {
        return mType;
    }

    public boolean getIsConnected() {
        return mIsConnected;
    }

}
