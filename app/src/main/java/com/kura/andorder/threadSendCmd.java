package com.kura.andorder;

import android.app.Activity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Locale;



public class threadSendCmd implements Runnable {
    private OutputStream out = null;//バイトデーター出力用
    private InputStream in = null;//バイトデーター出力用
    private Socket sock = null;

    // TODO add 20211018 APP SERVERのエミュレート対応
    // final private int PORT = 10000;
    // final private String ADDR = "192.168.11.3";
    private int PORT = 10000;
    private String ADDR = "192.168.11.3";
    // TODO add 20211018 APP SERVERのエミュレート対応

    final private int BUFSIZE = 10000;
    private int State = 0;
    private File _gSaveDir = null;
    private  logExtend _glog=null;
    private int _gFlagCheackOn = 0;
    private int _gFlagRunEnd = 0;
    private int _gTableNum = 0;
    private String _gStringTableNum = null;

    private int _gFlagReqSetting = 0;//001013
    private int _gFlagReqWaitTime = 0;//001008
    private int _gFlagReqSendTime = 0;//001010
    private int _gFlagReqOffsetTime = 0;//001011
    private int _gFlagReqOffsetPage = 0;//001019
    private int _gFlagReqWaitOrderCount = 0;//001015
    private int _gFlagReqServerTime = 0;//001998
    private int _gFlagReqHelpOn = 0;//001004
    private int _gFlagReqHelpOff = 0;//001005
    private int _gFlagReqKaikeiOn = 0;//001016
    private int _gFlagReqKaikeiOff = 0;//001017
    private int _gFlagReqKaikeiReset = 0;//001018

    private int  _gFlagReqRireki=0;//001009
    private int  _gFlagReqKaikeiRirekiOn=0;//001009


    private int _gFlagWaitOrder=0;
    private String _gOrderCmdStr=null;
    private int _gOrderCmdReturnState=0;

    private settingInfoFromServer _gSettingInfoFromServer = null;
    private rirekiInfo _gRirekiInfo = null;
    private kaikeiInfo  _gKaikeiInfo = null;
    private netaInfo _gNetaInfo = null;

    private optInfo _goptInfo =null;


    // TODO add 20220124 通信ログ強化
    public Activity _owner;
    /********************************************************
     * コンストラクタ
     ********************************************************/
    // TODO add 20220124 通信ログ強化
    public threadSendCmd(Activity owner, logExtend obj){
        this._glog=obj;
    }
    /*
    public threadSendCmd(logExtend obj){
        this._glog=obj;
    }
    */
    // TODO add 20211018 APP SERVERのエミュレート対応 addr　と　portを可変にする
    // TODO add 20220124 通信ログ強化
    public threadSendCmd(Activity owner, logExtend obj, String addr, Integer port){
        this._owner = owner;
        this._glog = obj;
        this.ADDR  = addr;
        this.PORT = port;
    }
    /*
    public threadSendCmd(logExtend obj, String addr, Integer port){
        this._glog=obj;
        this.ADDR  = addr;
        this.PORT = port;
    }
    */
    // TODO add 20211018 APP SERVERのエミュレート対応 addr　と　portを可変にする

    public void SetkaikeiInfo(kaikeiInfo obj) {
        _gKaikeiInfo = obj;
    }

    public void SetsettingInfoFromServer(settingInfoFromServer obj) {
        _gSettingInfoFromServer = obj;
    }
    public void SetrirekiInfo(rirekiInfo obj) {
        _gRirekiInfo = obj;
    }
    public void SetnetaInfo (netaInfo  obj) {
        _gNetaInfo  = obj;
    }
    public void SetoptInfo (optInfo  obj) {
        _goptInfo  = obj;
    }

    /********************************************************
     * 　ソケットを確立し、ストリームを作成する。
     **************************************************************/
    public int OpenScoket() {
        try {
            sock = new Socket();
            sock.setSoTimeout(1000*5);
            sock.connect(new InetSocketAddress(ADDR, PORT));
            out = new DataOutputStream(sock.getOutputStream());
            in = new DataInputStream(sock.getInputStream());

        } catch (SocketTimeoutException e) {
            _glog.log("ERR:OpenScoket SocketTimeoutException:" + e.toString());
            return -1;
        } catch (IOException e) {
            _glog.log("ERR:OpenScoket IOException:" + e.toString());
            return -1;
        } catch (Exception e) {
            _glog.log("ERR:OpenScoket Exception:" + e.toString());
            return -1;
        }
        return 1;
    }

    /********************************************************
     * setter
     **************************************************************/
    public void setFlagCheackOn() {
        _gFlagCheackOn = 1;
    }
    public void setFlagRunEnd() {
        _gFlagRunEnd = 1;
    }

    public void setSetTableNum(int num) {
        _gTableNum = num;
        _gStringTableNum = Integer.toString(num);
    }
    public void setFlagReqSetting(){
        _gFlagReqSetting=1;
    }
    public  void  setFlagReqWaitTime(){
        _gFlagReqWaitTime=1;
    }
    public  void  setReqSendTime(){
        _gFlagReqSendTime=1;
    }
    public  void  setFlagReqOffsetTime(){
        _gFlagReqOffsetTime=1;
    }
    public  void  setFlagReqOffsetPage(){
        _gFlagReqOffsetPage=1;
    }
    public  void  setFlagReqWaitOrderCount(){
        _gFlagReqWaitOrderCount=1;
    }
    public  void  setFlagReqServerTime(){
        _gFlagReqServerTime=1;
    }
    public  void  setFlagReqHelpOn(){
        _gFlagReqHelpOn=1;
    }
    public  void  setFlagReqHelpOff(){
        _gFlagReqHelpOff=1;
    }
    public  void  setFlagReqKaikeiOn(){
        _gFlagReqKaikeiOn=1;
    }
    public  void  setFlagReqKaikeiOff(){
        _gFlagReqKaikeiOff=1;
    }
    public  void  setFlagReqKaikeiReset(){
        _gFlagReqKaikeiReset=1;
    }
    public  void setFlagReqKaikeiRirekiOn(){
        _gFlagReqKaikeiRirekiOn=1;
    }

    public  void  setFlagReqRireki(){
        _glog.log("<<setFlagReqRireki called>>");
        _gFlagReqRireki=1;
    }
    /**
     * 通信異常を確認し、クリアーする。
     */
    public int  GetOrderCmdReturnState(){
        int ret = _gOrderCmdReturnState;
        _glog.log("GetOrderCmdReturnState　"+ ret);
        return ret;
    }

    /********************************************************

     ********************************************************/
    public int CheakFlagnOn() {
        int x=_gFlagReqSetting
                +_gFlagReqWaitTime
                + _gFlagReqSendTime
                +_gFlagReqOffsetTime
                +_gFlagReqOffsetPage
                +_gFlagReqWaitOrderCount
                +_gFlagReqServerTime
                +_gFlagReqHelpOn
                +_gFlagReqHelpOff
                +_gFlagReqKaikeiOn
                + _gFlagReqKaikeiOff
                + _gFlagReqKaikeiReset
                +_gFlagWaitOrder
                +_gFlagReqRireki
                +_gFlagReqKaikeiRirekiOn;

        if(x==0){
            return 0;
        }else{
            return 1;
        }
    }
    /**
     * オーダー用の送信分作成
     */
//20171025 シャリ選択の拡張情報追加
    public void setOrderStr2(int netaId,int sara,int wasabi,int table,int time,int opt){
        _gOrderCmdStr = String.format(Locale.JAPAN,"001003:%d,%d,%d,%d,%d,%d,%d,%d,%d",netaId,sara,wasabi,table,time,opt,0,0,0);
        _gFlagWaitOrder = 1;
        _glog.log("setOrderStr called order:" + _gOrderCmdStr + " ret:" + _gOrderCmdReturnState);
    }
    /**
     * send cmd
     */
    public void SendCmd() {
        String cmd=null;
        String retBuf=null;
        String buf=null;

        //order問い合わせは優先して確認
        if( _gFlagReqWaitOrderCount==1){
            // _glog.log("order問い合わせ 開始");
            cmd = "001015:".concat(_gStringTableNum);
            retBuf=CommandRequestData(cmd);
            // _glog.log("order問い合わせ回答"+retBuf);

            if(retBuf != null) {
                _glog.log("order問い合わせ>"+retBuf.trim());
                _gSettingInfoFromServer.setOrderStop_ORDERMAX(retBuf.trim());
            }else{
                _glog.log("ERR:order問い合わせ >NULL");
            }
            _gFlagReqWaitOrderCount=0;
            return;
        }
        //order 1003
        if( _gFlagWaitOrder == 1){
            //_glog.log("order 開始");
            _gOrderCmdReturnState = -1;//確認のため一度マイナス値にしておく
            _gOrderCmdReturnState =  SendSockData(_gOrderCmdStr);
            // _glog.log("order:" + _gOrderCmdStr + " ret:" + _gOrderCmdReturnState);
            // TODO add 20220124 通信LOG強化
            String tmpcmd = _gOrderCmdStr.replace(":","-");
            tmpcmd = tmpcmd.replace(",","_");
            // _glog.LogJson(_owner,"LOGCAT:DEBUG,METHOD:SendSockData,CMD:" + tmpcmd);
            _glog.LogJson(_owner, "LOGCAT:TRACE,TAG:orderproc,PROC:WaitOrder"  + ",FLAG:" + _gOrderCmdReturnState + ",CMD:" + tmpcmd );
            // TODO add 20220124 通信LOG強化
            _gFlagWaitOrder = 0;
            return;
        }
        //setting
        if(_gFlagReqSetting==1){
            // _glog.log(" SendCmd FlagReqSetting");
            // cmd="001013:".concat(_gStringTableNum);
            cmd="001013:";
            retBuf=CommandRequestData(cmd);

            if(retBuf != null) {
                // _glog.log(" FlagReqSetting>" + retBuf.trim());
                _gSettingInfoFromServer.setSettingData(retBuf.trim());
            }else{
                _glog.log("ERR:o FlagReqSetting >NULL");
            }
            _gFlagReqSetting=0;
            return;
        }

        //会計
        if( _gFlagReqKaikeiRirekiOn==1){
            cmd="001009:";

            retBuf=CommandRequestData(cmd);
            //_glog.log( "会計画面用履歴取得>" + retBuf);
            //if(retBuf != null && retBuf != ""  ) {
            if(retBuf != null &&  !retBuf.equals("")) {
                _gKaikeiInfo.setTRirekiInfo(retBuf);
                //buf =_gKaikeiInfo.GetRirekiStrLocal3(_gNetaInfo,_goptInfo);
                //change 20180829 金額表記対応検討
                buf =_gKaikeiInfo.GetRirekiStrLocal4(_gNetaInfo,_goptInfo);
                _gKaikeiInfo.SetKaikeiRireki(buf);
                buf = null;
                _gFlagReqKaikeiRirekiOn = 0;
            }else{
                _gFlagReqKaikeiRirekiOn = 0;
            }

            return;
        }

        if( _gFlagReqKaikeiOn==1){
            // _glog.log("<<FlagReqKaikeiOn>>");

            cmd="001016:".concat(_gStringTableNum);
            SendSockData(cmd);
            _gFlagReqKaikeiOn=0;
            return;
        }

        if( _gFlagReqWaitTime==1){

            _glog.log("<<FlagReqWaitTime ON>>");

            cmd="001008:";
            retBuf=CommandRequestData(cmd);

            if(retBuf != null) {
                // _glog.log("FlagReqWaitTime >" + retBuf.trim());
                _gSettingInfoFromServer.SetOrderWaitAboutTime(retBuf.trim());
            }else{
                _glog.log("ERR:FlagReqWaitTime >NULL");
            }

            _gFlagReqWaitTime=0;
            return;
        }

        if( _gFlagReqRireki==1){
            _glog.log("<<FlagReqRireki ON>>");
            cmd="001009:";
            //retBuf=CommandRequestData(cmd);
            //-----------------------------------------
            try {
                byte[] by = cmd.getBytes("UTF-8");
                byte[] byteBuffer = new byte[BUFSIZE];
                int c=0;
                int n=0;
                out.write(by);
                out.flush();
                while(true){
                    c=in.read();
                    if(c == -1){
                        break;
                    }
                    byteBuffer[n++] = (byte)c;
                }
                retBuf  =new String(byteBuffer,0,n+1);
            } catch (UnsupportedEncodingException e) {
                _glog.log("ERR:SendSockData UnsupportedEncodingException:" + e.toString());
            }catch (Exception e) {
                _glog.log("ERR:SendSockData Exception:" + e.toString());
            }
            //-----------------------------------------
            if(retBuf != null) {
                // _glog.log("_gFlagReqRireki >" + retBuf);
                //_gRirekiInfo.setRirekiInfo(retBuf.trim());
                _gRirekiInfo.setRirekiInfo(retBuf);
            }else{
                _glog.log("ERR:_gFlagReqRireki >NULL");
            }
            _gFlagReqRireki=0;
            return;
        }


        if( _gFlagReqSendTime==1){
            cmd="001010:";
            retBuf=CommandRequestData(cmd);
            if(retBuf != null) {
              //  _glog.log("_gFlagReqSendTime >" + retBuf.trim());
                _gSettingInfoFromServer.setAriveNeedTime(retBuf.trim());
            }else{
                _glog.log("ERR:_gFlagReqSendTime >NULL");
            }
            _gFlagReqSendTime = 0;
            return;
        }

        if( _gFlagReqOffsetTime==1){
            cmd="001011:";
            retBuf=CommandRequestData(cmd);

            if(retBuf != null) {
               // _glog.log("_gFlagReqOffsetTime >"+retBuf.trim());
                _gSettingInfoFromServer.setClientOffsetTime(retBuf.trim());
            }else{
                _glog.log("ERR:FlagReqOffsetTime >NULL");
            }

            _gFlagReqOffsetTime=0;
            return;
        }

        if( _gFlagReqOffsetPage==1){
            cmd="001019:";
            retBuf=CommandRequestData(cmd);

            if(retBuf != null) {
                // _glog.log("lagReqOffsetPage >"+retBuf.trim());
                _gSettingInfoFromServer.setLimitoffpage(retBuf);
            }else{
                _glog.log("ERR:lagReqOffsetPage >NULL");
            }

            //返信分に折り返しが入っている影響でtrim()して渡すとおかしくなる

            _gFlagReqOffsetPage=0;
            return;
        }
        if( _gFlagReqServerTime==1){
            _gFlagReqServerTime=0;
            return;
        }
        if( _gFlagReqHelpOn==1){
            cmd="001004:".concat(_gStringTableNum);
            SendSockData(cmd);
            _gFlagReqHelpOn=0;
            return;
        }
        if( _gFlagReqHelpOff==1){
            cmd="001005:".concat(_gStringTableNum);
            SendSockData(cmd);
            _gFlagReqHelpOff=0;
            return;
        }

        if( _gFlagReqKaikeiOff==1){
            cmd="001017:".concat(_gStringTableNum);
            SendSockData(cmd);
            _gFlagReqKaikeiOff=0;
            return;
        }
        if( _gFlagReqKaikeiReset==1){
            cmd="001018:".concat(_gStringTableNum);
            SendSockData(cmd);
            _gFlagReqKaikeiReset=0;
            return;
        }

    }
    /********************************************************
     　ソケットを終了
     ********************************************************/
    private int CloseSocket(){
        try {
            if(out != null) {
                out.close();
            }
            if(in != null){
                in.close();
            }
            if(sock !=null){
                sock.close();
            }
        } catch (IOException e) {
            _glog.log("ERR:CloseSocket IOException:" + e.toString());
            return -1;
        }catch (Exception e) {
            _glog.log("ERR:CloseSocket Exception:" + e.toString());
        }
        return 0;
    }
    /********************************************************

     **************************************************************/
    private int SendSockData(String cmd){
        byte[] buf;
        // TODO add 20220124 通信LOG強化
        String tmpcmd = cmd.replace(":","-");
        tmpcmd = tmpcmd.replace(",","_");
        _glog.LogJson(_owner,"LOGCAT:TRACE,METHOD:SendSockData,CMD:" + tmpcmd);
        // TODO add 20220124 通信LOG強化

        try {
            buf = cmd.getBytes("UTF-8");
            out.write(buf);
            out.flush();
            // _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:keyStateProc,PROC:KeyErrClose");

        } catch (UnsupportedEncodingException e) {
            // _glog.log("ERR:SendSockData UnsupportedEncodingException:" + e.toString());
            // TODO add 20220124 通信LOG強化
            _glog.LogJson(_owner,"LOGCAT:ERR,METHOD:SendSockData,MSG:"+ e.toString());
            return -2;
        }catch (Exception e) {
            // _glog.log("ERR:SendSockData Exception:" + e.toString());
            // TODO add 20220124 通信LOG強化
            _glog.LogJson(_owner,"LOGCAT:ERR,METHOD:SendSockData,MSG:" + e.toString());
            return -2;
        }
        return 1;
    }

    /********************************************************

     **************************************************************/
    private String GetData() {
        // BufferedInputStream buff_in = new BufferedInputStream(in);
        byte[] byteBuffer = new byte[BUFSIZE];
        String str=null;
        //StringBuffer sb=new StringBuffer("");
        int n=0;
        int c=0;

        try {
            while(true){
                c=in.read();
                if(c == -1){
                    break;
                }
                byteBuffer[n++] = (byte)c;
            }
            str= new String(byteBuffer);
            // _glog.log("***************************");
            // _glog.log("[ "+str +" ]");
            // _glog.log("***************************");
        } catch (IOException e) {
            // _glog.log("ERR:GetData:" + e.toString());
            _glog.LogJson(_owner,"LOGCAT:ERR,METHOD:GetData,MSG:" + e.toString());
            return null;
        }
        return str;
    }
    /********************************************************

     **************************************************************/
    private String CommandRequestData(String code){
        String str=null;
        try {
            SendSockData(code);
            str = GetData();
            CloseSocket();

            // TODO add 20220124 通信LOG強化

        }catch (Exception e){
           // _glog.log("ERR:CommandRequestData:" + e.toString());
            _glog.LogJson(_owner,"LOGCAT:ERR,METHOD:CommandRequestData,MSG:" + e.toString());
            return null;
        }
        return str;
    }
    /********************************************************
     　RUN LOOP
     ************************************************************/
    private void sendCmdLoop() {
        int ret=0;

        // TODO add 20220124 通信LOG強化
        int  WaitOrderErrCount = 0;
        // TODO add 20220124 通信LOG強化

        while (true) {
            try {
                Thread.sleep(20);
            }catch(InterruptedException e) {//sleep

            }
            // try {
            if (_gFlagCheackOn == 1 && CheakFlagnOn() > 0) {
                if (OpenScoket() > 0) {
                    _gOrderCmdReturnState = 0;
                    SendCmd();
                    // TODO add 20220124 通信LOG強化
                    WaitOrderErrCount = 0;
                    // TODO add 20220124 通信LOG強化
                }else{// openに失敗した場合はﾌﾗｸﾞを失敗にしておく
                    // _glog.LogJson(_owner,"LOGCAT:ERR,METHOD:sendCmdLoop,MSG:soketのOPENに失敗");
                    _gOrderCmdReturnState = -1;

                    // TODO add 20220124 通信LOG強化
                   if( _gFlagWaitOrder == 1 ){
                       _glog.LogJson(_owner,"LOGCAT:ERR,TAG:orderproc,MSG:注文時にsoketのOPENに失敗");
                       WaitOrderErrCount ++;
                       if( WaitOrderErrCount > 100) {
                           WaitOrderErrCount = 0;
                           _gFlagWaitOrder = 0;
                           _glog.LogJson(_owner,"LOGCAT:ERR,TAG:orderproc,MSG:注文フラグを強制クリア");
                       }
                   }
                    // TODO add 20220124 通信LOG強化
                }
                CloseSocket();
                _gFlagCheackOn =0;
            }
            if(_gFlagRunEnd ==1){
                _gFlagRunEnd=0;
                break;
            }
           /* }catch (Exception e){
                _glog.log("ERR:sendCmdLoop:" + e.toString());
            }*/
        }

    }
    /********************************************************
     　RUN実行
     ************************************************************/
    @Override
    public void run() {
        sendCmdLoop();
    }
}


