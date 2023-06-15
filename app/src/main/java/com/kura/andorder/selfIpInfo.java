package com.kura.andorder;

import android.util.Log;

//import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
//import java.util.Iterator;


public class selfIpInfo {
    private  logExtend _glog=null;

    /****************************************************************
     * コンストラクタ
     *****************************************************************/
    public selfIpInfo(logExtend obj){
        this._glog=obj;
    }
    /****************************************************************
     *
     *****************************************************************/
    public String getLocalIpAddress() {
        String ip = null;
        try{
            // インターフェースを列挙
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while ( (ip == null) && en.hasMoreElements() ) {
                // インターフェースオブジェクトを取得
                NetworkInterface ni = en.nextElement();
                // このインターフェースのアドレスを列挙
                Enumeration<InetAddress>  ei = ni.getInetAddresses();
                while (ei.hasMoreElements()) {
                    // アドレスを取得
                    InetAddress ia = ei.nextElement();
                    // IPv4でなければ無視
                    if ( !(ia instanceof java.net.Inet4Address) ) {
                        continue;
                    }
                    // ループバックアドレスは無視
                    if ( ia.isLoopbackAddress() ) {
                        continue;
                    }
                    // IPアドレスを取得
                    ip = ia.getHostAddress();
                    break;
                }
            }
        } catch (SocketException e){
            Log.e("ERR:SocketException: ", e.toString());
        } catch (Exception e) {
            Log.e("ERR:getLocalIpAddress: ", e.toString());
        }
        return ip;
    }
}
