package com.kura.andorder;

//import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class threadGetFile implements Runnable {
    //private String TAG = "USER LOG";
    private  logExtend _glog=null;
    private ftpClient _ftpClient=null;

    private OutputStream out;//バイトデーター出力用
    private InputStream in;//バイトデーター出力用
    private Socket sock;
    //private final int TIMEOUT = 3000;
    // TODO add 20211018 APP SERVERのエミュレート対応
    // final private int PORT = 10000;
    // final private String ADDR = "192.168.11.3";
    private int PORT = 10000;
    private String ADDR = "192.168.11.3";
    // TODO add 20211018 APP SERVERのエミュレート対応
    // private int State;

    private File _gSaveDir=null;
    // private String _TAG = "USER_LOG";

    private int _gGetNetaInfo_Flag=0;//ネタデーター取得要求
    private int _gUpdateNetaInfo_Flag=0;//ネタデーター取得済み

    private int _gGetTagInfo_Flag=0;
    private int _gUpdateTagInfo_Flag=0;

    private int _gGetLocalStr_Flag=0;
    private int _gUpdateLocalStr_Flag=0;
    private int _gGetLocalNetaName_Flag=0;
    private int _gUpdateLocalNetaName_Flag=0;
    /********************************************************
     コンストラクタ
     *********************************************************/
    public threadGetFile(File f,logExtend obj){
        _gSaveDir = f;
        this._glog = obj;
        _glog.log("threadGetFile called" + _gSaveDir);
        _ftpClient = new ftpClient(f,_glog);
    }

    // TODO add 20211018 APP SERVERのエミュレート対応 addr　と　portを可変にする
    public threadGetFile(File f,logExtend obj, String addr, Integer port){
        _gSaveDir = f;
        this._glog = obj;
        _glog.log("threadGetFile called" + _gSaveDir);
        _ftpClient = new ftpClient(f,_glog);
        this.ADDR  = addr;
        this.PORT = port;
    }
    // TODO add 20211018 APP SERVERのエミュレート対応 addr　と　portを可変にする
    /********************************************************
     　ソケットを確立し、ストリームを作成する。
     **************************************************************/
    public int OpenScoket(){

        try {
            sock = new Socket();
            sock.setSoTimeout(10000);

            //_glog.log( "OpenScoket called");

            sock.connect(new InetSocketAddress(ADDR, PORT));
            //sock.connect(new InetSocketAddress(ADDR,PORT),TIMEOUT);
            out = new DataOutputStream(sock.getOutputStream());
            in = new DataInputStream(sock.getInputStream());

        } catch (SocketTimeoutException e){
            _glog.log("ERR:OpenScoket SocketTimeoutException called" + e.toString());
            return -1;
        } catch (IOException e) {
            _glog.log("ERR:OpenScoket IOException called" + e.toString());
            return -1;
        }catch (Exception e) {
            _glog.log("ERR:OpenScoket Exception called" + e.toString());
            return -1;
        }
        return 1;
    }
    /********************************************************
     　ソケットを確立し、ストリームを作成する。
     20160625 timeoutを長くする
     **************************************************************/
    public int OpenScoket2(){

        try {
            sock = new Socket();
            sock.setSoTimeout(30000);
            sock.connect(new InetSocketAddress(ADDR, PORT));
            out = new DataOutputStream(sock.getOutputStream());
            in = new DataInputStream(sock.getInputStream());

        } catch (SocketTimeoutException e){
            _glog.log("ERR:OpenScoket SocketTimeoutException called" + e.toString());
            return -1;
        } catch (IOException e) {
            _glog.log("ERR:OpenScoket IOException called" + e.toString());
            return -1;
        }catch (Exception e) {
            _glog.log("ERR:OpenScoket Exception called" + e.toString());
            return -1;
        }
        return 1;
    }
    /********************************************************
     setter
     **************************************************************/


    public  void setGetNetaInfo_Flag(){
        _gGetNetaInfo_Flag =1;
    }
    public  void setGetTagInfo_Flag(){
        _gGetTagInfo_Flag =1;
    }

    public  void setGetLocalStr_Flag(){
        _gGetLocalStr_Flag =1;
    }
    public  void setGetLocalNetaName_Flag(){
        _gGetLocalNetaName_Flag =1;
    }



    /********************************************************
     getter
     **************************************************************/
    public  int getUpdateLocalStr_Flag(){
        int ret=0;
        if(_gUpdateLocalStr_Flag ==1) {
            _gUpdateLocalStr_Flag=0;
            ret=1;
        }else if(_gUpdateLocalStr_Flag ==-1){
            _gUpdateLocalStr_Flag=0;
            ret=-1;
        }else{
            ret=0;
        }
        return  ret;
    }
    public  int getUpdateLocalNetaName_Flag(){
        int ret=0;
        if(_gUpdateLocalNetaName_Flag ==1) {
            _gUpdateLocalNetaName_Flag=0;
            ret=1;
        }else if(_gUpdateLocalNetaName_Flag ==-1){
            _gUpdateLocalNetaName_Flag=0;
            ret=-1;
        }else{
            _gUpdateLocalNetaName_Flag=0;
            ret=0;
        }
        return  ret;
    }


    public  int getUpdateNetaInfo_Flag(){
        int ret=0;
        if(_gUpdateNetaInfo_Flag ==1) {
            _gUpdateNetaInfo_Flag=0;
            ret=1;
        }else{
            ret=0;
        }
        return  ret;
    }
    //--------------------------------------
    public  int getUpdateTagInfo_Flag(){
        int ret=0;
        if(_gUpdateTagInfo_Flag ==1) {
            _gUpdateTagInfo_Flag=0;
            ret=1;
        }else{
            ret=0;
        }
        return  ret;
    }

    //********************************************************
//ディレクトリ操作
    // ********************************************************

    private int fileRename(int d_size,String str_src,String dst_str) {
        int res = 0;
        File f_name_dst = null;
        File f_name_src = null;

        try {
            f_name_dst = new File(dst_str);
            f_name_src = new File(str_src);

            if (d_size < 10) {//短すぎるデーターは異常と判断して処理しない
                f_name_src.delete();
                _glog.log("ERR:fileRename short" + d_size);
                return -1;
            }
            if (f_name_dst.exists()) {
                f_name_dst.delete();
            }
            f_name_src.renameTo(f_name_dst);
            return 0;
        }catch (Exception e) {
            _glog.log("ERR:fileRename Exception called" + e.toString());
            return -1;
        }
    }
    /********************************************************
     //ファイル書き出し
     //一度データーを文字列に受けるべきか？

     **************************************************************/
    public int GetFile(String filepath){

        int d_size=0;
        int c=0;
        int ret=-1;

        BufferedOutputStream buff_out = null;
        BufferedInputStream buff_in = new BufferedInputStream(in);
        String tempStr=filepath + "_temp";

        try {
            //  buff_out = new BufferedOutputStream(new FileOutputStream(filepath));
            buff_out = new BufferedOutputStream(new FileOutputStream(tempStr));
            while((c=buff_in.read()) != -1) {//次のbyteをintがたで返してくる。
                buff_out.write(c);
                d_size++;
            }
            buff_out.close();
            buff_out = null;
            buff_in.close();

            ret= fileRename(d_size, tempStr,filepath);
            return ret;

        } catch (FileNotFoundException e) {
            _glog.log("ERR:GetFile FileNotFoundException called" + e.toString());
        }catch (IOException e) {
            _glog.log("ERR:GetFile IOException called" + e.toString());
        }catch (Exception e) {
            _glog.log("ERR:GetFile Exception called" + e.toString());
        }
        return ret;
    }
    /********************************************************
     　ソケットを終了
     **************************************************************/
    //20181115 NTT 指摘箇所を修正
    public int CloseSocket(){
        try {
            if(out != null) {
                out.close();
            }
            if( in != null) {
                in.close();
            }
            if( sock != null) {
                sock.close();
            }
        } catch (IOException e) {
            _glog.log("ERR:CloseSocket IOException called" + e.toString());
            return -1;
        }catch (Exception e) {
            _glog.log("ERR:CloseSocket Exception called" + e.toString());
            return -1;
        }
        return 0;
    }
    /********************************************************
     　RUN実行
     **************************************************************/
    private int SendSockData(String cmd){
        byte[] buf;
        try {
            buf = cmd.getBytes("UTF-8");
            out.write(buf);
            out.flush();
        } catch (UnsupportedEncodingException e) {
            _glog.log("ERR:SendSockData UnsupportedEncodingException called" + e.toString());
            return -2;
        }catch (Exception e) {
            _glog.log("ERR:SendSockData Exception called" + e.toString());
            return -2;
        }
        return 0;
    }
    /********************************************************

     ************************************************************/
    private int getTag(){
        int ret=-1;
        try {
            ret=this.OpenScoket();
            if(ret >= 0) {
                ret=this.SendSockData("001001:");
                if(ret >= 0) {
                    ret= this.GetFile(_gSaveDir + "/taginfo.csv");
                }
            }
            CloseSocket();
        }catch (Exception e){
            _glog.log("ERR:getTag Exception called" + e.toString());
        }
        return ret;
    }
    /********************************************************
     //test 20160624 タイムアウトの時間を長くしてみる。
     ************************************************************/
    private int getNeta(){
        int ret=-1;
        try {
            //ret = this.OpenScoket();
            ret = this.OpenScoket2();//test 20160624 タイムアウトの時間を長くしてみる。
            if (ret >= 0) {
                ret = this.SendSockData("001002:");
                if (ret >= 0) {
                    ret= this.GetFile(_gSaveDir + "/netainfo.csv");
                }
            }
            CloseSocket();
        }catch (Exception e){
            _glog.log("ERR:getNeta Exception called" + e.toString());
        }
        return ret;
    }
    /********************************************************
     　LOOP
     ************************************************************/
    private void DoLoop() {
        int ret=0;

        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {//sleep
            }
            // _glog.log( "A>" + _gGetNetaInfo_Flag + " B>" + _gGetTagInfo_Flag);
            if (_gGetNetaInfo_Flag == 1) {
                //_glog.log("ねたデーター取得要求");
                if (this.getNeta() >= 0) {
                    _gGetNetaInfo_Flag = 0;
                    _gUpdateNetaInfo_Flag = 1;
                } else {
                    _gGetNetaInfo_Flag = 0;//1回であきれめていいの？
                    _glog.log("ねたデーター取得要求失敗");
                }
            }
            if (_gGetTagInfo_Flag == 1) {
                this.getTag();
                _gGetTagInfo_Flag = 0;
                _gUpdateTagInfo_Flag = 1;
            }

            //add 20160521
            if (_gGetLocalStr_Flag ==1){
                _glog.log("_gGetLocalStr_Flag ON");
                //_glog.log(_gSaveDir.toString()+"/upload/"+"LocalStr.csv");
                ret= _ftpClient.ftpGet("LocalStr.csv",_gSaveDir.toString()+"/upload/"+"LocalStr.csv");
                //_glog.log("_gGetLocalStr_Flag  データー取得　ret:"+ret);
                if(ret==-1){
                    _glog.log("LocalStr取得要求失敗");
                    _gGetLocalStr_Flag = 0;
                    _gUpdateLocalStr_Flag = -1;
                }else {
                    _glog.log("LocalStr取得要求成功");
                    _gGetLocalStr_Flag = 0;
                    _gUpdateLocalStr_Flag = 1;
                }
            }
            //add 20160521
            if (_gGetLocalNetaName_Flag ==1){
                _glog.log("__gGetLocalNetaName_Flag ON");
                ret= _ftpClient.ftpGet("LocalNetaName.csv",_gSaveDir.toString()+"/upload/"+"LocalNetaName.csv");
                //_glog.log(_gSaveDir.toString()+"/upload/"+"LocalNetaName.csv");
                if(ret==-1){
                    _gGetLocalNetaName_Flag = 0;
                    _gUpdateLocalNetaName_Flag = -1;
                }else {
                    _gGetLocalNetaName_Flag = 0;
                    _gUpdateLocalNetaName_Flag = 1;
                }
            }


        }
    }

    /********************************************************
     　RUN実行
     ************************************************************/
    @Override
    public void run () {
        //int ret = 0;
        DoLoop();

    }
}
