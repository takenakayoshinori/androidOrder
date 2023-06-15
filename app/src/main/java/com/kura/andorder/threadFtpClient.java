package com.kura.andorder;

//import android.util.Log;

import java.io.File;


public class threadFtpClient implements Runnable {
    ftpClient _ftpClient;
    //private String _TAG = "USER_LOG";
    private  logExtend _glog=null;

    // private File _gFtpVer[]=new int[6];
    private File _gHomeDir;
    private int _gFtpVer[]=new int[6];
    private int _gFtpVerBuf[]=new int[6];
    private int _gFtpStartOnFlag[]=new int[6];
    private int _gFtpProsesOnFlag[]=new int[6];
    private int _gFtpEndOnFlag[]=new int[6];

    // TODO add 20210913 get QRINFO
    private int _gFtpStartQrOnFlag = 0;
    private String QrFileName = "";
    // TODO add 20210913 get QRINFO



    private final String _gFtpName[]={"upload.zip",
            "sound.zip","douga.zip","douga_win.zip",
            "douga_lose.zip","douga_screen.zip"};
    /********************************************************
     コンストラクタ
     *********************************************************/
    //   public threadFtpClient(File f,String f_name) {
    public threadFtpClient(File f,logExtend obj){
        this._glog=obj;
        _glog.log("threadFtpClient called");
        ClearVer();
        //  _ftpClient=new ftpClient(f);
        _ftpClient=new ftpClient(f,_glog);
        _gHomeDir=f;
    }

    // TODO add 20210913 get QRINFO
    public threadFtpClient(File f,logExtend obj, int table){
        this._glog=obj;
        _glog.log("threadFtpClient called");
        ClearVer();
        //  _ftpClient=new ftpClient(f);
        _ftpClient=new ftpClient(f,_glog);
        _gHomeDir=f;
        this.QrFileName = "qr_info_" + table + ".png";
    }




    // TODO add 20210913 get QRINFO

    /**
     * FTPでのデーター取得
     * vsftpdの処理依存。転用不可
     */
   /* public int getFtpProcess(){
        if(_ftpClient!=null) {
            return _ftpClient.getFtpProcess();
        }else{
            return -1;
        }
    }*/
    public String getFtpProsessStr(){
        if(_ftpClient!=null) {
            return _ftpClient.getFtpProsessStr();
        }else{
            return "";
        }
    }


    /**
     * FTPのバージョンをクリア
     *０のときは無視
     */
    private void ClearVer() {
        //int i=0;
        for(int i=0;i<6;i++){
            _gFtpVer[i] = 0;
            _gFtpStartOnFlag[i] = 0;
            _gFtpVerBuf[i] = 0;
        }

    }
    /**
     *
     */
    public int checkEndFlag(int x) {
        int ret=0;
        if (x < 0 && x > 6) {
            return -1;
        }
        if(_gFtpEndOnFlag[x] ==1 ){
            _glog.log("checkEndFlag OM:"+ x);
            ret=_gFtpVerBuf[x];
            _gFtpVerBuf[x]=0;
            _gFtpEndOnFlag[x]=0;
            return ret;
        }
        return 0;
    }

    /**
     * メイン側のヴァージョンとこのクラスのバージョンを比較
     * 違ったらFTP更新ﾌﾗｸﾞを立てる
     * 連続して更新しないように一度比べたらバージョンは０にリセットする
     * FTPが成功したらバージョンを上書きするので、バッファに残す
     */
    public int CompareVer(int x, int val) {
        if (x < 0 && x > 6) {
            return -1;
        }
        for(int i=0;i<6;i++){
            if(_gFtpProsesOnFlag[i] == 1){
                _glog.log(i + " >wait ftp CompareVer:" + _gFtpProsesOnFlag[i]);
                return 0;
            }
        }
        if(_gFtpVer[x] != val && _gFtpVer[x] != 0 ){
            _gFtpStartOnFlag[x] = 1;
            _gFtpVerBuf[x] = _gFtpVer[x];
            _gFtpVer[x] = 0;
            return 1;
        }else{
            return 0;
        }
    }






    // TODO add 20210913 get QRINFO
    public void SetQrFtpFlag(){
        if( _gFtpStartQrOnFlag == 0 ) {
            _glog.log("--->> SetQrFtpFlag called");
            _gFtpStartQrOnFlag = 1;
        }
    }
    // TODO add 20210913 get QRINFO


    /**
     * 受信データーを解析し、FTPのバージョンをセット。
     *メイン側のループでCompareVerを呼び出し、差異があれば
     * FTPを実施する
     */
    public void SetFtpFlag(String data){
        String arr[];
        int type=0;
        int ver=0;

        // _glog.log("SetFtpFlag data 1:" + data);
        try {
            if (data.indexOf(",") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
                _glog.log( "data:" + data);
                return;
            }
            arr = data.split(",");
            // _glog.log("SetFtpFlag data 2:"+arr.length);
            if(arr.length<2){
                return;
            }
            //_glog.log("SetFtpFlag data 3");
            type=Integer.parseInt(arr[0]);
            ver=Integer.parseInt(arr[1]);
            _glog.log( "type:"+ type);
            _glog.log( "ver:"+ver);

            if(type>=0 && type<6){
                _gFtpVer[type] = ver;
            }
      /*  = data.trim();
        i = Integer.parseInt(data);*/
        }catch (Exception e){
            _glog.log("ERR:SetFtpFlag:"+e.toString());
        }
    }
    /**
     * FTP更新のﾌﾗｸﾞが足っていたらFTPを実施
     * 失敗した場合はエラーコードをバッファに入れる。
     */
    public void ftpGetStart() {

        int ret=-1;
        //int i=0;

        _glog.log("ftpGetStart() start");

        for(int i=0;i<6;i++){
            if(_gFtpStartOnFlag[i]==1){
                _glog.log("ftpGetStart() ON:"+_gFtpName[i]);
                _gFtpStartOnFlag[i]=0;
                _gFtpProsesOnFlag[i]=1;
                ret=_ftpClient.ftpGet(_gFtpName[i],_gHomeDir.toString()+"/"+_gFtpName[i]);
                _gFtpProsesOnFlag[i]=0;
                _gFtpEndOnFlag[i]=1;
                if(ret <0){//失敗のとき
                    _gFtpVerBuf[i]=9999;
                }
                _glog.log("ftpGetStart() END:"+ ret);
                return;
            }
        }

        // TODO add 20210913 get QRINFO
        if( _gFtpStartQrOnFlag == 1 ) {
            _glog.log("--->> ftpGetQrStart called:" + this.QrFileName);
            // String f_name = "qr_info_2.png";
            ret=_ftpClient.ftpGet("/home/kura/TOUCH_QR_INFO/"+ this.QrFileName,
                    _gHomeDir.toString() + "/" + "qr_info.png");
        }
        // TODO add 20210913 get QRINFO
    }


    // TODO add 20220321 #36 ゲームチケットのQRの処理修正 QRの画像を消去　本来ここでやるのはおかしいがやっつけで
    public void deleteQrFile() {
        File outFile = new File(_gHomeDir,"qr_info.png");
        _glog.log("deleteSendFile :" + outFile.toString() );
        if (outFile.exists()) {
            if (outFile.isFile()) {
                _glog.log("outFile delete :" + outFile.toString());
                outFile.delete();
                outFile=null;
            }
        }
    }

    // TODO add 20220321 #36 ゲームチケットのQRの処理修正 QRの画像を消去　本来ここでやるのはおかしいがやっつけで
    public boolean CheckQrFile() {
        File outFile = new File(_gHomeDir,"qr_info.png");
        _glog.log("CheckQrFile:" + outFile.toString() );
        if (outFile.exists()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     /********************************************************
     　RUN実行
     **************************************************************/
    @Override
    public void run() {
        _glog.log("threadFtpClient run start");
        ftpGetStart();
    }

}
