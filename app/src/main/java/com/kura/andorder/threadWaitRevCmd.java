package com.kura.andorder;

//import android.util.Log;

import java.io.IOException;
//import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class threadWaitRevCmd implements Runnable {
    // private String _TAG = "USER_LOG";
    private  logExtend _glog=null;
    private final int Port = 10000;
    //SOCK_MAX
    private final int SOCK_MAX = 10;
    private Socket[] _gsock;

    public  threadWaitRevCmd(logExtend obj){
        // int i=0;
        //_gsock=new  Socket[5];
        _gsock=new  Socket[ SOCK_MAX];
        this._glog=obj;
        for(int i=0;i< SOCK_MAX;i++) {
            _gsock[i]=null;
        }
    }

    public Socket getSock2(int sockNum){
        if(sockNum<0 && sockNum<SOCK_MAX){
            _glog.log("err getSock2: 配列範囲外");
            return null;
        }
        if(_gsock[sockNum]!=null) {
            return _gsock[sockNum];
        }
        return null;
    }
   /* public Socket  getSock(){
        int i=0;
        Socket socket=null;
        for(i=0;i< SOCK_MAX;i++) {
            if(_gsock[i] != null){
                _glog.log("getSock:" + i+ _gsock.toString());
                socket=_gsock[i];
                return socket;
            }
        }
        return null;
    }*/
    /********************************************************
     author:ys
     content:analyse
     up:20160703
     socketがnullでないかチェック。これだと順番通りに裁かない可能性があるため
     運用上問題があれば、受けた順に処理するようにする必要がる?
     全てからの場合はnullを返す。
     ***********************************************************/
    int cheackSock(){
        // int i=0;
        // Socket socket=null;
        for(int i=0;i< SOCK_MAX;i++) {
            if(_gsock[i] != null){
                return i;
            }
        }
        return -1;
    }


    public void closeWaitSock2(int sockNum){


        //配列範囲のチェック
        if(sockNum<0 && sockNum<SOCK_MAX){
            _glog.log("err closeSock: 配列範囲外");
        }
        //nullでなかったらクローズする。
        if(_gsock[sockNum] != null) {
            try {
                _gsock[sockNum].close();
                _glog.log("closeSock:" + sockNum);
                _gsock[sockNum] = null;
            } catch (IOException e) {
                _glog.log("err closeSock:" + sockNum);
                _gsock[sockNum] = null;
            }
        }
        return;
    }
   /* public void closeWaitSock(){
        int i=0;
        //Socket socket=null;
        for(i=0;i< SOCK_MAX;i++) {
            if(_gsock[i] != null){
                try{
                    _gsock[i].close();
                    _glog.log("closeSock:"+i);
                    _gsock[i]=null;
                }   catch (IOException e) {
                    _glog.log("err closeSock:"+i);
                    _gsock[i]=null;
                }
                return ;
            }
        }
        return;
    }*/
    /********************************************************
     author:ys
     content:analyse
     up:20160229
     ***********************************************************/
    @Override
    public void run() {
        //int i=0;
        try {
            //sever Sock Set
            ServerSocket servsock  = new ServerSocket();
            servsock.setReuseAddress(true);//以前の接続がタイムアウト状態でもソケットをバインドする設定
            servsock.bind(new InetSocketAddress(Port));

            while(true){
                //NULLなソケットでaccept()
                for(int i=0;i< SOCK_MAX;i++){
                    if(_gsock[i] == null){
                        // _glog.log("accept START" + i);
                        try {//accept try
                            _gsock[i] = servsock.accept();
                        }catch(IOException e) {// accept err catch
                            _glog.log("accept servsock.accept():err"+e.toString());
                            if(_gsock[i]  != null){
                                try {// accept close
                                    _gsock[i].close();
                                    _gsock[i]=null;
                                }catch(IOException e2) {//sleep
                                    _glog.log("accept servsock.accept():err"+e2.toString());
                                    _gsock[i]=null;
                                }
                            }
                        }

                    }
                }
                //sleep
                try {
                    Thread.sleep(5);
                }catch(InterruptedException e) {//sleep
                    _glog.log("threadWaitRevCmd InterruptedException ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            _glog.log("err:threadWaitRevCmd" + e.toString());
        }
    }


}
