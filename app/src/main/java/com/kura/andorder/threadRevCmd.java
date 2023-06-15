package com.kura.andorder;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;


/**
 * Created by kura on 2016/02/20.
 */
public class threadRevCmd implements Runnable {

    //ver 80 20170601
    private int _gHELP_PAGE_OPEN_FLAG = 0;//help 表示
    private int _gHELP_PAGE_CLOSE_FLAG = 0;//help 非表示


    // private String _TAG = "USER_LOG";
    private logExtend _glog = null;

    private final int Port = 10000;

    //private変数
    private int _gNETA_UPDATE = 0;//neta更新要求
    private int _gTAG_UPDATE = 0;//neta更新要求

    private int _gTEST_PAGE_OPEN_FLAG = 0;//
    private int _gTEST_PAGE_CLOSE_FLAG = 0;//

    // private int _gKOUSOKU_UPDATE = 0;//
    private int _gKOUSOKU_RESERVE_ON = 0;
    private int _gAPP_UPDATE = 0;//app更新要求
    private int _gFLAG_THREAD_RUN_END = 0;
    private int _gFLAG_CMD_REBOOT = 0;

    //private Socket _gsock;
    private InputStream _gin;//バイトデーター出力用
    private int _gstate;

    private settingInfo _gSettingInfo = null;
    private settingInfoFromServer _gSettingInfoFromServer = null;
    private orderUnderInfo _gOrderUnderInfo = null;
    private orderHeightInfo _gOrderHeightInfo = null;
    private threadFtpClient _gthreadFtpClient = null;
    private threadSendCmd _gthreadSendCmd = null;
    private threadWaitRevCmd _gthreadWaitRevCmd = null;

    private MainActivity _gMainActivity = null;


    //後付セッティング  初期値有
    private int _gkaikei_alert_mode = 0;
    private int _gReserv_mode_mode = 0;
    private int _gIo_use_mode = 0;//多言語化に対応するか
    private int _gkousoku_arraive_wait = 6;
    private int _gkousoku_arraive_show_count = 16;

    private int _gTouchState_Start0n = 0;
    //ver83 _gTouchState_Start0FFのステータスを設ける。
    private int _gTouchState_Start0FF = 0;
    private int _gTouchState_Custom_1 = 0;
    private int _gTouchState_Custom_2 = 0;
    private int _gLogLevel = 2;
    private int _gLogErrLevel = 2;


    //add for test 20161103
    private int _gFLAG_CMD_EXIT_APP = 0;
    //add for test 20161104
    private int _gFLAG_CMD_DesablePkg = 0;
    //add for test 20171201 音声操作用
    private int _gFLAG_CMD_Sound_inform_Receive = 0;
    private int _gFLAG_CMD_Sound_inform_Send =0;
    private int _gFLAG_CMD_Sound_Screen = 0;
    private int _gFLAG_CMD_Sound_Order_Receive = 0;
    private int _gFLAG_CMD_Sound_Order_Send = 0;
    private int _gFLAG_CMD_Sound_Order_Results_Receive = 0;
    private int _gFLAG_CMD_Sound_Order_Results_Send = 0;
    private int _gFLAG_CMD_Sound_Order_Results_Screen = 0;








    // private settingInfoFromServer _gSettingInfoFromServer = null;

    /********************************************************
     * コンストラクタ
     *********************************************************/
    public threadRevCmd(logExtend obj) {
        this._glog = obj;
    }

    /********************************************************
     *
     *********************************************************/
/*public void setAcceptSock(Socket sock) {
        _gsock=sock;

}*/
    public void SetMainActivity(MainActivity obj) {
        _gMainActivity = obj;
    }

    public void SET_threadWaitRevCmd(threadWaitRevCmd obj) {
        _gthreadWaitRevCmd = obj;
    }

    public void SET_threadSendCmd(threadSendCmd obj) {
        _gthreadSendCmd = obj;
    }

    public void SET_threadFtpClient(threadFtpClient obj) {
        _gthreadFtpClient = obj;
    }

    public void SET_SettingInfo(settingInfo obj) {
        _gSettingInfo = obj;
    }

    public void SET_settingInfoFromServer(settingInfoFromServer obj) {
        _gSettingInfoFromServer = obj;
    }

    public void SET_OrderUnderInfo(orderUnderInfo obj) {
        _gOrderUnderInfo = obj;
    }

    public void SET_gOrderHeightInfo(orderHeightInfo obj) {
        _gOrderHeightInfo = obj;
    }

    /*!#########################################################
    //getter
    #########################################################*/
    //add for test 20161107
    public int GET_DesablePkg() {
        if (_gFLAG_CMD_DesablePkg == 1) {
            _gFLAG_CMD_DesablePkg = 0;
            return 1;
        } else {
            return 0;
        }
    }


    //add for test 20161103
    public int GET_EXIT_APP() {
        if( _gFLAG_CMD_EXIT_APP==1){
            _gFLAG_CMD_EXIT_APP=0;
            return 1;
        }else{
            return 0;
        }
    }



    public int GET_NETA_UPDATE() {
       /* if( _gNETA_UPDATE==1){
            _gNETA_UPDATE=0;
            return 1;
        }else{
            return 0;
        }*/
        if( _gNETA_UPDATE > 0){
            _gNETA_UPDATE--;
            return 1;
        }else{
            return 0;
        }
    }
    public int GET_TAG_UPDATE (){
        if( _gTAG_UPDATE==1){
            _gTAG_UPDATE=0;
            return 1;
        }else{
            return 0;
        }
    }

    public int GET_APP_UPDATE (){
        if( _gAPP_UPDATE==1){
            _gAPP_UPDATE=0;
            return 1;
        }else{
            return 0;
        }
    }
    public int GET_TEST_PAGE_CLOSE_FLAG () {
        if(_gTEST_PAGE_CLOSE_FLAG==1) {
            _gTEST_PAGE_CLOSE_FLAG=0;
            return 1;
        }else{
            return 0;
        }
    }
    public int GET_TEST_PAGE_OPEN_FLAG () {
        if(_gTEST_PAGE_OPEN_FLAG==1) {
            _gTEST_PAGE_OPEN_FLAG=0;
            return 1;
        }else{
            return 0;
        }
    }
    public int GET_REBOOT_FLAG (){
        if(_gFLAG_CMD_REBOOT==1){
            _gFLAG_CMD_REBOOT=0;
            return 1;
        }else{
            return 0;
        }
    }

    public int GET_KOUSOKU_RESERVE_ON (){
        if(_gKOUSOKU_RESERVE_ON==1){
            _gKOUSOKU_RESERVE_ON=0;
            return 1;
        }else{
            return 0;
        }
    }

    //ver80 20170601
    public int GET_HELP_PAGE_CLOSE_FLAG () {
        if(_gHELP_PAGE_CLOSE_FLAG==1) {
            _gHELP_PAGE_CLOSE_FLAG=0;
            return 1;
        }else{
            return 0;
        }
    }
    public int GET_HELP_PAGE_OPEN_FLAG () {
        if(_gHELP_PAGE_OPEN_FLAG==1) {
            _gHELP_PAGE_OPEN_FLAG=0;
            return 1;
        }else{
            return 0;
        }
    }
    //ver83 20170601
    //TCP_IP コマンドで_gTouchState_Start0nを受けた場合
    public int GET_TOUCHSTATE_STRAT_ON() {
        if(_gTouchState_Start0n==1) {
            _gTouchState_Start0n=0;
            return 1;
        }else{
            return 0;
        }
    }
    public int GET_TOUCHSTATE_STRAT_OFF() {
        if( _gTouchState_Start0FF==1) {
            _gTouchState_Start0FF=0;
            _gTouchState_Custom_1=0;
            _gTouchState_Custom_2=0;
            return 1;
        }else{
            return 0;
        }
    }
    //cat1 -> 大人の人数　cat2 -> 子供の人数
    public int GET_TOUCHSTATE_COUSTOM(int cat) {
        if(cat==1) {
            return _gTouchState_Custom_1;
        }else{
            return _gTouchState_Custom_2;
        }
    }



    /****************************************************************
     author:ys
     content:analyse
     up:20160229
     ****************************************************************/
    private void analysis(String data) {

        //_glog.log("analysis:"+ data);

        int comannd = 0;
        if(data == null){
            return;
        }
        if(data.indexOf(":") == -1) {
            _glog.log("ERR:analysis" );
            return;
        }
        String arr[]=data.split(":");

        try {
            comannd=Integer.parseInt(arr[0]);
        }catch (Exception e){
            _glog.log("ERR:analysis" + e.toString() );
            return;
        }


        switch(comannd) {
            case 890:
                _glog.log("テストページOPEN >>890");
                _gTEST_PAGE_OPEN_FLAG = 1;
                break;
            case 891:
                _glog.log("テストページCLOSE >>891");
                _gTEST_PAGE_CLOSE_FLAG = 1;
                break;


            //ver80 20170601==
            case 973:
                _glog.log("HELP画面表示 >>973");
                _gHELP_PAGE_OPEN_FLAG = 1;
                break;
            case 974:
                _glog.log("HELP画面表示 >>974");
                _gHELP_PAGE_CLOSE_FLAG = 1;
                break;
            //ver80 20170601==

            //change 20160608 off pageを増やすテスト　サーバー側の仕様で改行がはいるので単純に下記コードではだめ
            /*case 1019:
                _glog.log("change 20160608 off page >>1019 :" + arr.length);
                if(arr.length>1) {
                    _gSettingInfoFromServer.setLimitoffpage(arr[1]);
                }
            break;
            //change 20160608 off pageを増やすテスト*/

            case 99990:
                _glog.log("TH_DO >>99990 update app");
                _gAPP_UPDATE = 1;
                break;

            case 99998:
                _glog.log("TH_DO >>99998 exit app");
                _gFLAG_CMD_EXIT_APP=1;
                break;

            case 99997:
                _glog.log("TH_DO >>99997 DesablePkg");
                _gFLAG_CMD_DesablePkg=1;
                break;

            case 99999:
                _glog.log("TH_DO >>99999 reboot");
                _gFLAG_CMD_REBOOT=1;
                break;
            case 10999://add 2013 0920  高速レーンの特急ボタンが押された。
                _glog.log("TH_DO >>10999 特急ボタンが押された");
                if (data.indexOf(",") != -1) {//文字列に区切り文字が入っていない場合はスキップする。
                    _gOrderHeightInfo.setOrder(arr[1].trim());
                }
                break;
            case 10998://add 2013 0920  高速レーン逆転ボタンがおされた。
                _glog.log("TH_DO >>10999 高速レーン逆転");
                _gKOUSOKU_RESERVE_ON = 1;
                break;
            case 101001://TAG
                _glog.log("TH_DO >>101001 TAG-update");
                _gTAG_UPDATE = 1;
                break;
            case 101002://画像変更要求無しでネタ情報を更新
                _glog.log("TH_DO >>101002 ネタ情報を更新");
                //_gNETA_UPDATE=1;
                _gNETA_UPDATE++;
                break;
		/*case 101017:
			Log.i(TAG,"TH_DO >>101017  Bmp Change" );
			break;
		case 101018:
			Log.i(TAG,"TH_DO >>101018   dataUPLoad" );
			if(CLIENT.appstate.getUPdateDataFlag()==0){
				CLIENT.appstate.setUPdateDataFlag(1);//データー更新中フラグをセットする。
			}
			break;
		case 101019://
			Log.i(TAG,"TH_DO >>101019   EXIT dataUPLoad" );
			CLIENT.appstate.setUPdateDataFlag(0);//データー更新中フラグを解除する。
			break;*/
            case 101004://注文拒否フラグをセット
                _glog.log("TH_DO >>101004  order-stop");
                _gSettingInfoFromServer.setOrderStop(1);
                break;
            //----------------------------------------------------------------
            case 101005://注文拒否フラグを解除
                _glog.log("TH_DO >>101005　order-stop-releases");
                _gSettingInfoFromServer.setOrderStop(0);
                break;
            //----------------------------------------------------------------
           /* case 101007://order-limit-cheak
                _glog.log("TH_DO >>101007  order-limit-cheak" );

                break;
            //----------------------------------------------------------------
            case 101008://order-wait-time
                _glog.log("TH_DO >>101008  order-wait-time" ); //信号が着たら目安時間を更新
                break;*/
            //----------------------------------------------------------------
            case 101009://send_time
                _glog.log("TH_DO >>101009  send_time");
                _gthreadSendCmd.setReqSendTime();

                break;
            //----------------------------------------------------------------
            case 101010:
                _glog.log("TH_DO >>101010  offset-time-update");
                _gthreadSendCmd.setFlagReqOffsetTime();
                //   CLIENT.settinginfo.SetOffset();
                break;
            //----------------------------------------------------------------
            case 101013:
                _glog.log("TH_DO >>101013　settingdata-update:");
                _gthreadSendCmd.setFlagReqSetting();

                //  CLIENT.settinginfo.SetSetting();
                break;
            //-----------------------------------------------------------------
            case 101014:
                _glog.log("TH_DO >>101014　混雑画面表示指示:");//混雑画面表示指示
                //  CLIENT.settinginfo.SetOrderStopFlag3(1);//注文拒否フラグをセット
                _gSettingInfoFromServer.setOrderStop_SYSTEM_ORDER_OVER(1);
                break;
            //-------------------------------------------------------------------
            case 101015:
                _glog.log("TH_DO >>101015 混雑画面解除処理:");//混雑画面解除処理
                // CLIENT.settinginfo.SetOrderStopFlag3(0);//注文拒否フラグをセット
                _gSettingInfoFromServer.setOrderStop_SYSTEM_ORDER_OVER(0);
                break;
            //---------------------------------------------------------------
            case 101011://確認ボタンが押された通知
                //_glog.log("TH_DO >>101011 確認ボタン押された:" + data.trim());
                _glog.log("TH_DO >>101011 確認ボタン押された:" + data);
                if (data.indexOf(",") != -1) {//文字列に区切り文字が入っていない場合はスキップする。
                    _gOrderUnderInfo.setOrder(data, _gSettingInfoFromServer);
                }
                break;
            //--------------------------------------------------
            case 101012://復帰、削除ボタンが押された通知   ⇒　　復帰処理は注文商品を表示させないおうにするための処理に変更。
                if (data.indexOf(",") != -1) {//文字列に区切り文字が入っていない場合はスキップする。
                    _gOrderUnderInfo.deleteOrder(data, _gSettingInfoFromServer);
                }
                break;
            //------------------------------------------------------
            case 101016://
                _glog.log("TH_DO >>101016　settingdata-update");
                _gthreadSendCmd.setFlagReqOffsetPage();
                break;
            //------------------------------------------------------
            case 109001://HTTP経由の画像ファイル転送　要実装
                //Log.i(TAG,"TH_DO >>109001 FROM dataSERVER UPLoad");
              /*  if(CLIENT.appstate.getUPdateDataFlag()==0){
                    CLIENT.appstate.setUPdateDataFlag(1);//データー更新中フラグをセットする。
                }
                if(data.indexOf(",") != -1){//文字列に区切り文字が入っていない場合はスキップする。
                    //YsHttp yshttp = new YsHttp(data);
                    new YsHttp(data);
                }
                CLIENT.appstate.setUPdateDataFlag(0);
                //yshttp.getContent(data.split(",")[0]);*/
                break;
            case 109002://HTTP経由のCSVファイル転送　要実装
                break;
            //------------------------------------------------
            case 109007://
                //Log.i(TAG,"TH_DO >>109007 netaDataSingleReset");
                break;
            //-----------------------------------------------------
            case 109008://ネタ情報リロード
                //Log.i(TAG,"TH_DO >>109008 netaDataAllReset");
                //CLIENT.netainfo.ClearNetaInfo();//データーを削除
                /*if(CLIENT.appstate.getUPdateDataFlag()==0){
                    CLIENT.appstate.setUPdateDataFlag(1);//データー更新中フラグをセットする。
                }
                CLIENT.netainfo.SetNetaData(1);//画像変更要求を実施してネタ変更
                CLIENT.appstate.setUPdateDataFlag(0);*/
                break;
            //------------------------------------------------
	    /*case 109009://データー更新中フラグを解除する。
	        //Log.i(TAG,"TH_DO >>109009 EXIT dataUPLoad");
	        //CLIENT.appstate.setUPdateDataFlag(0);
		break;*/
//-------------------------------------------------------------
//add 2013 0920 FTPによるネタデーターの一括更新用
//-------------------------------------------------------------
            case 109020://
                _glog.log("TH_DO >>109020 ftp受信指示:" + data);
                if(arr.length >1) {
                    _gthreadFtpClient.SetFtpFlag(arr[1]);
                }
                break;
//-------------------------------------------------------------:
            case 109301:
                //Log.i(TAG,"TH_DO >>109301 sound1");
                // new YsSound("sound1");
                break;
//------------------------------------------------------------
            case 109302://
                //Log.i(TAG,"TH_DO >>109302 sound2");
                //new YsSound("sound2");
                break;
            case 109303://
                //Log.i(TAG,"TH_DO >>109303 sound2");
                // new YsSound("oaiso");
                break;

            case 900010://滞在SET
                _glog.log("TH_DO >>900010 滞在SET:" + data);
                //ver83 20170614 大人のお客さんの場合にゲームスキップを問い合わせ
                if(arr.length >2) {
                    if(Integer.parseInt(arr[0]) > 0 && Integer.parseInt(arr[0]) <=6 &&
                            Integer.parseInt(arr[1]) > 0 && Integer.parseInt(arr[1])<=6){
                        _gTouchState_Custom_1 = Integer.parseInt(arr[0]);
                        _gTouchState_Custom_2 = Integer.parseInt(arr[1]);
                        _gTouchState_Start0n = 1;
                        _glog.log("set adult:" + _gTouchState_Custom_1 );
                        _glog.log("set child:" + _gTouchState_Custom_2 );
                        break;
                    }
                }
                _glog.log("人数セット登録失敗:" + data);
                //ver83 20170614
                break;
            case 900011://滞在時間クリア <- 20180816 この機能はudp経由で実施するよう改める
                _glog.log("TH_DO >>900011 滞在時間クリア:" + data);
                _gTouchState_Start0FF = 1;
                _gTouchState_Custom_1 = 0;
                _gTouchState_Custom_2 = 0;
                break;
            case 900101://_gkaikei_alert_mode=1
                _gkaikei_alert_mode=1;
                break;
            case 900102://_gkaikei_alert_mode=0
                _gkaikei_alert_mode=0;
                break;
            case 900103://_gIo_use_mode=0
                _gIo_use_mode=0;
                break;
            case 900104://_gIo_use_mode=1
                _gIo_use_mode=1;
                break;
           /* case 900105://_gIo_use_mode=2
                _gIo_use_mode=2;
                break;*/
        }
        //-----------------------------------------------
    }

    /********************************************************
     コマンド取得
     change20160703
     _gsockを使用しない。
     *********************************************************/
    private String GetData2(int sockNum) {
        InputStream in=null;
        BufferedInputStream buff_in=null;
        byte[] byteBuffer = new byte[255];
//-----------------------------
        //soketがnullでないかもう１回チェック
        if(_gthreadWaitRevCmd.getSock2(sockNum)==null){
            _glog.log("err:GetData2 null");
            return null;
        }
        // _glog.log("GetData() called ");
        try {
            in = new DataInputStream(_gthreadWaitRevCmd.getSock2(sockNum).getInputStream());
            buff_in = new BufferedInputStream(in);
            //getData start
            //_glog.log("getData start");
            try {
                while((buff_in.read(byteBuffer)) != -1) {
                }
            } catch (IOException e) {
                _glog.log("err: GetData2 A:"+e.toString());
                //close
                try {
                    buff_in.close();
                    in.close();
                } catch (IOException e2) {
                    _glog.log("err: GetData2 B:"+e.toString());
                }
                //close end
                return null;
            }
            //getData end
            buff_in.close();
            in.close();
        } catch (IOException e) {
            _glog.log("err: GetData2 C:"+e.toString());
            //close
            try {
                if(buff_in!=null) {
                    buff_in.close();
                }
                if(in!=null) {
                    in.close();
                }
            } catch (IOException e2) {
                _glog.log("err: GetData2 D:"+e.toString());
            }
            //close end
            return null;
        }
        //-----------------------------
        return new String(byteBuffer);
    }

    /********************************************************
     author:ys
     content:analyse
     up:20160229
     **************************************************************/
    private void revCmd2(){

        String revb=null;
        int sockNum=-1;

        while(true){
            //sleep
            try {
                Thread.sleep(5);
            }catch(InterruptedException e) {//sleep
                _glog.log("revCmd InterruptedException ");
            }
            //NUM check
            //_gsock=_gthreadWaitRevCmd.getSock();
            sockNum=_gthreadWaitRevCmd.cheackSock();
            //-1でない場合はアクセスがあったと判断する。
            if(sockNum >=0){
                revb= GetData2(sockNum);
                if(revb != null){
                    _glog.log( "revCmd:"+revb);
                    //コマンド解析
                    analysis(revb);
                }
                _gthreadWaitRevCmd.closeWaitSock2(sockNum);
            }

        }

    }

    /********************************************************
     　RUN実行
     **********************************************************/
    @Override
    public void run() {
        this.revCmd2();
    }
}

