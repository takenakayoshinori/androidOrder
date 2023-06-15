package com.kura.andorder;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

public class threadPutLogJson implements Runnable {
    ftpClient _ftpClient;
    private logExtend _glog=null;
    private File _gHomeDir;
    private String sorceFileName=null;
    private String sendFileName=null;
    private String localFileName=null;
    private int _gDelSendFileFlag=0;
    private int _gTable;

    /********************************************************
     コンストラクタ
     *********************************************************/
    public threadPutLogJson (int table,File f,logExtend obj){
        this._glog=obj;
        _ftpClient=new ftpClient(f,_glog);
        _gHomeDir=f;
        sorceFileName="LogJson.txt";
        localFileName=f+"/LogJson.txt";
        _gTable=table;
    }
    /**
     * FTP更新のﾌﾗｸﾞが足っていたらFTPを実施
     * 失敗した場合はエラーコードをバッファに入れる。
     */
    public void ftpPutStart() {
        int ret=-1;
        int i=0;
        // StringBuffer _sb =new StringBuffer("");
        StringBuilder _sb = new StringBuilder("");
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        _sb.append(String.format(Locale.JAPAN,"%04d",calendar.get(Calendar.YEAR)));
        _sb.append(String.format(Locale.JAPAN,"%02d",calendar.get(Calendar.MONTH)+1));
        _sb.append(String.format(Locale.JAPAN,"%02d",calendar.get(Calendar.DAY_OF_MONTH)));

        //本日の日付のログ名にする
        sendFileName="TOUCH_LOG/log_AndOrder_"+_gTable+"_"+_sb.toString()+".txt";
        ret=_ftpClient.ftpPut(sendFileName,localFileName);
        _glog.log("threadPutLogJson filename:" +sendFileName +" ret:"+ret);
        // return;
    }
//============================================

    //============================================
    public void setDelSendFileFlag(int val){
        _gDelSendFileFlag = val;
    }
//============================================

    //============================================
    public void deleteSendFile() {
        File outFile = new File(_gHomeDir,sorceFileName);
        _glog.log("deleteSendFile :" + outFile.toString() );
        //すでに存在している場合に消去する
        //if (outFile.exists() == true) {
        if (outFile.exists()) {
            if (outFile.isFile()) {
                outFile.delete();
            }
        }
        //outFile=null;
        return;
    }
    /**
     /********************************************************
     　RUN実行
     *********************************************************/
    @Override
    public void run() {
        _glog.log("threadPutLogJson del:" + _gDelSendFileFlag);
        ftpPutStart();
        if(_gDelSendFileFlag == 1){
            this.deleteSendFile();
            _gDelSendFileFlag=0;
        }
    }

}
