package com.kura.andorder;

import java.io.File;
//import android.util.Log;

public class threadPutFile implements Runnable {
    ftpClient _ftpClient;
    //private String _TAG = "USER_LOG";
    private  logExtend _glog=null;
    private File _gHomeDir;
    private String sorceFileName=null;
    private String sendFileName=null;
    private String localFileName=null;

    private int _gDelSendFileFlag=0;

    /********************************************************
     コンストラクタ
     *********************************************************/
    public threadPutFile(int tableNum,File f,logExtend obj,String sendfile){
        this._glog=obj;
        _ftpClient=new ftpClient(f,_glog);
        _gHomeDir=f;

        sorceFileName=sendfile;
        sendFileName=tableNum+"_"+sendfile;
        localFileName=f+"/"+sendfile;
        _glog.log("threadPutFile sendFileName:" +sendFileName);
        _glog.log("threadPutFile localFileName:" +localFileName);
    }

    /**
     * FTP更新のﾌﾗｸﾞが足っていたらFTPを実施
     * 失敗した場合はエラーコードをバッファに入れる。
     */
    public void ftpPutStart() {
        _glog.log("ftpGetStart() start");
        _ftpClient.ftpPut(sendFileName,localFileName);
        return;
    }

//============================================

//============================================
    public void setDelSendFileFlag(int val){
        _gDelSendFileFlag = 1;
    }

//============================================

//============================================
    public void deleteSendFile() {
        //_glog.log("deleteSendFile" + _gHomeDir);
        //_glog.log("deleteSendFile" + localFileName);
        File outFile = new File(_gHomeDir,sorceFileName);
        _glog.log("deleteSendFile :" + outFile.toString() );

        //すでに存在している場合に消去する？
        //if (outFile.exists() == true) {
        if (outFile.exists()) {
            if (outFile.isFile()) {
                _glog.log("outFile delete :" + outFile.toString());
                outFile.delete();
                outFile=null;
            }
        }
        // return;
    }
    /**
     /********************************************************
     　RUN実行
     **************************************************************/
    @Override
    public void run() {
        //_glog.log("threadFtpClient run start");
        this.ftpPutStart();
        //_glog.log("threadFtpClient run  _gDelSendFileFlag: " +_gDelSendFileFlag );
        if(_gDelSendFileFlag == 1){
            this.deleteSendFile();
            _gDelSendFileFlag=0;
        }
    }

}

