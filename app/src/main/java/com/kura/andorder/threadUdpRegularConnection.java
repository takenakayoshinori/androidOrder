package com.kura.andorder;

//import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class threadUdpRegularConnection implements Runnable {
    // private String _TAG = "USER_LOG";
    private  logExtend _glog=null;

    private int _gTableNum=0;

    private int _gFlagRunEnd = 0;
    private int _gStopSendFlag=0;
    private int _gSendOnFlag=0;

    private String _gSendStr=null;
    private String _gRevStr=null;

    //add 20160523
    private  final int NETWORK_ERR_COUNT_RWQ=100;//エラーを通知する閾値
    private int _gNetWorkErrCount=0;//ネットワークエラーが連続して続いた場合


    /********************************************************
     コンストラクタ
     *********************************************************/
    public threadUdpRegularConnection(logExtend obj){
        this._glog=obj;
    }
    /****************************************************************
     *sendするレギュラーデーター
     *****************************************************************/
//------------------------------------------------------------------------
//setter
//----------------------------------------------------------------------------
    public void setSendOnFlag() {
        _gSendOnFlag=1;
    }
    public void setFlagRunEnd() {
        _gFlagRunEnd= 1;
    }
    public void setStopSendFlag(int val) {
        if(val==1) {
            _gStopSendFlag = 1;
        }else{
            _gStopSendFlag = 0;
        }
    }
    public  int getStopSendFlag() {
        if(_gStopSendFlag==1) {
            return 1;
        }else{
            return 0;
        }
    }
    /* public void setSendStr(String str) {
          _gSendStr = new String(str);
      }*/
    //change 20181128
    public boolean setSendStr(String str) {
        if(str==null) {
            return false;
        }else{
            _gSendStr = str;
            return true;
        }
    }

    public void setTableNum(int val) {
        _gTableNum= val;
    }

    /****************************************************************
     *sgetter
     *****************************************************************/
    public String getRevStr() {
        return _gRevStr;
    }
    /****************************************************************
     *sgetter
     *****************************************************************/
    public   boolean  getNetWorkErr() {
        if(_gNetWorkErrCount>NETWORK_ERR_COUNT_RWQ){
            _gNetWorkErrCount=0;
            return  true;
        }else {
            return false;
        }
    }

    //------------------------------------------------------------------------
//UDPレギュラー通信
//----------------------------------------------------------------------------
    private void regularUdp() {

        if(_gTableNum==0){
            //_glog.log("ERR:regularUdp _gTableNum:");
            return;
        }
        final int port = 19999+ _gTableNum-1;//
        final String addr = "192.168.11.155";
        int _skipOnceFlag=0;
        DatagramSocket sock = null;
        DatagramPacket packetSend = null;
        DatagramPacket packetRev = null;

        byte buf[];
        byte revBuf[] = new byte[500];

        while (true) {
            try {
                Thread.sleep(20);
            }catch(InterruptedException e) {//sleep

            }
            if (_gStopSendFlag != 1 && _gSendOnFlag==1) {
                try {
                    sock=null;
                    _gRevStr=null;
                    if(_skipOnceFlag ==0) {
                        InetAddress i_addr = InetAddress.getByName(addr);
                        sock = new DatagramSocket();
                        buf = _gSendStr.getBytes();
                        packetSend = new DatagramPacket(buf, buf.length, i_addr, port);
                        sock.send(packetSend);
                        revBuf = new byte[125];
                        packetRev = new DatagramPacket(revBuf, revBuf.length);
                        sock.setSoTimeout(200);
                        sock.receive(packetRev);
                        _gRevStr = new String(packetRev.getData(), 0, packetRev.getLength());
                        sock.close();
                        if(_gNetWorkErrCount>0){
                            _gNetWorkErrCount--;
                        }
                    }else{
                        _skipOnceFlag=0;
                    }
                    _gSendOnFlag=0;
                    if(_gFlagRunEnd ==1){
                        _gFlagRunEnd=0;
                        break;
                    }

                }catch(SecurityException e){
                    _gNetWorkErrCount++;
                    _glog.logE("ERR:regularUdp SecurityException count:" +_gNetWorkErrCount+" str:"+ e.toString());
                    if(sock != null) {
                        sock.close();
                    }
                }catch(SocketException e){
                    //add 20160523
                    _gNetWorkErrCount++;
                    _glog.log("ERR:regularUdp SocketException count:" +_gNetWorkErrCount+" str:"+  e.toString());
                    if(sock != null) {
                        sock.close();
                    }
                }catch(UnknownHostException e){
                    _gNetWorkErrCount++;
                    _glog.logE("ERR:ローカルホストアドレスが取得できません count:" +_gNetWorkErrCount+" str:"+  e.toString());
                    if(sock != null) {
                        sock.close();
                    }
                }catch(java.io.IOException e){
                    _gNetWorkErrCount++;
                    _glog.logE("ERR:regularUdp IOException count:" +_gNetWorkErrCount+" str:"+  e.toString());
                    _skipOnceFlag=1;
                    if(sock != null) {
                        sock.close();
                    }
                }catch(java.lang.NullPointerException e){
                    _glog.logE("ERR:NullPointer:" + e.toString());
                    if(sock != null) {
                        sock.close();
                    }
                }catch (Exception e) {
                    _glog.logE("ERR:Exception:" + e.toString());
                    if(sock != null) {
                        sock.close();
                    }
                }
            }
        }
    }

    /********************************************************
     　RUN実行
     **************************************************************/
    @Override
    public void run() {
        this.regularUdp();
    }
}

