package com.kura.andorder;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kura on 2016/05/16.
 * 管理系のCMDを受ける
 * 管理系を意図するので、アクセス後シングルスレッド
 */
public class threadRevCmdManager implements Runnable {
    private  logExtend _glog=null;
    private final int Port = 10001;
    private Socket _gsock;
    private ServerSocket _gservsock;

    private int _gLogSendFlag;
    private int _gLoopEndFlag;

    private OutputStream out = null;


    public threadRevCmdManager(logExtend obj){
        _glog = obj;
    }
    /********************************************************

     **************************************************************/
    private int SendSockData(String cmd) {
        byte[] buf;
        // OutputStream out = null;

        try {
            out = new DataOutputStream(_gsock.getOutputStream());
            buf = cmd.getBytes("UTF-8");
            out.write(buf);
            out.flush();
        } catch (UnsupportedEncodingException e) {
            _glog.log("ERR:SendSockData UnsupportedEncodingException:" + e.toString());
            return -2;
        } catch (Exception e) {
            _glog.log("ERR:SendSockData Exception:" + e.toString());
            return -2;
        }
        return 0;
    }
    /********************************************************

     *********************************************************/
    private  void checkSendLog(){
        String sendStr;
        int ret=0;

        //-------------------------
        if (_gLogSendFlag == 1) {//ログリアルタイム転送
            sendStr = _glog.GetLogBufStr();
            if (sendStr != null) {
                ret = this.SendSockData(sendStr);
            }
            if (ret < 0) {
                _glog.log("err:SendSockData ret:" + ret);
                _gLoopEndFlag = 1;
            }
        }
        //-------------------------
    }


    /********************************************************
     コマンド取得
     *********************************************************/
    private String GetData() {
        InputStream in = null;
        BufferedInputStream buff_in = null;
        byte[] byteBuffer = new byte[255];
        int recvMsgSize = 0;
        String revStr;
        String sendStr;
        int cmd = 0;
        int ret = 0;

//-----------------------------
        _glog.log("GetData called");
        try {
            in = new DataInputStream(_gsock.getInputStream());
            buff_in = new BufferedInputStream(in);
            _glog.log("Acsess from:" + _gsock.getInetAddress());
            while (true) {
                //sleep
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {//sleep

                }
                //check flag
                if (_gLoopEndFlag == 1) {
                    _glog.log("_gLoopEndFlag break loop:");
                    _gLoopEndFlag=0;
                    break;
                }
                checkSendLog();
                //------------------get rev data
                try {
                    if (in.available() > 0) {//available実装調査。この使い方だめ？
                        // in.available();//nonblock
                        recvMsgSize = in.read(byteBuffer);
                        revStr = new String(byteBuffer);
                        _glog.log("revStr:" + revStr);
                        //-------------------get cmd
                        if ((byteBuffer[4]) == ':') {
                            cmd = (byteBuffer[0] - 48) * 1000 + (byteBuffer[1] - 48) * 100 + (byteBuffer[2] - 48) * 10 + (byteBuffer[3] - 48);
                            switch (cmd) {
                                case 1000:
                                    _gLogSendFlag = 1;
                                    _glog.log("***cmd:1000 リアルタイムログ転送　開始***");
                                    break;
                                case 1001:
                                    // _glog.log("cmd:" + 1001);
                                    _gLogSendFlag = 0;
                                    _glog.log("***cmd:1000 リアルタイムログ転送　終了***");
                                    break;
                                case 9999:
                                    _gLoopEndFlag = 1;
                                    _glog.log("***cmd:9999 コネクション切断　***");
                                    // _glog.log("cmd:" + 9999);
                                    break;
                            }
                        }

                    }
                } catch (IOException e2) {
                    _glog.log("err: GetData in.available:" + e2.toString());
                }
            }
            //-------------------end stream
            if(buff_in !=null) {
                buff_in.close();
            }
            if(in !=null) {
                in.close();
            }
            if(out !=null) {
                out.close();
            }
            return new String(byteBuffer);
        } catch (IOException e) {
            _glog.log("err: GetData:" + e.toString());
            try {
                //20181115 commnet out
                //buff_in.close();
                //in.close();

                //change 20181115
                try {
                    if(buff_in !=null) {
                        buff_in.close();
                    }
                    if(in !=null) {
                        in.close();
                    }
                }catch (NullPointerException ep){
                    _glog.log("err:close:" + ep.toString());
                }
                //change 20181115
            } catch (IOException e2) {
                _glog.log("err: GetData2:" + e2.toString());
            }
            return null;
        }
    }

    /********************************************************
     author:ys
     content:analyse
     up:20160229
     *********************************************************/
    private void closeAccseptSock(){
        try{
            _gsock.close();
        }   catch (IOException e) {
            //_gsock.close();
        }
    }
    /********************************************************
     author:ys
     content:analyse
     up:20160229
     *********************************************************/
    private void closeServerSock(){
        try{
            _gservsock.close();
        }   catch (IOException e) {

        }
    }

    /********************************************************
     author:ys
     content:analyse
     up:20160229
     ***********************************************************/
    @Override
    public void run() {
        int i=0;
        String str=null;

        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {//sleep
                _glog.log("threadRevManager: ");
            }
            try {
                _gservsock  = new ServerSocket();
                _gservsock.setSoTimeout(10000);
                _gservsock.setReuseAddress(true);//以前の接続がタイムアウト状態でもソケットをバインドする設定
                _gservsock.bind(new InetSocketAddress(Port));
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {//sleep
                        _glog.log("threadWaitRevCmd InterruptedException ");
                    }
                    _gsock =  _gservsock.accept();
                    _glog.log("ANDORDER MANAGER IPPUT COMMAND >>");
                    str = GetData();
                    _glog.log("ANDORDER MANAGER IPPUT COMMAND END>>");
                    this.closeAccseptSock();
                }
            } catch (IOException e) {
                // _glog.log("err:threadRevManager:" + e.toString());
            }
            this.closeServerSock();
        }
    }
}
