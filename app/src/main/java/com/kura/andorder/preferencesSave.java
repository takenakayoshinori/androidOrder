package com.kura.andorder;

import android.content.Context;
import android.content.SharedPreferences;

//import java.io.File;

public class preferencesSave {

    private SharedPreferences _gpref;// プリファレンス
    private SharedPreferences.Editor _geditor; // プリファレンスに書き込むためのEditorオブジェクト

    //private String TAG = "USER LOG";
    private  logExtend _glog=null;
    private  static final  String TABLE_NUMBER="TABLE_NUMBER";
    private  static final String ftp_img ="ftp_img";
    private  static final String ftp_sound ="ftp_sound";
    private  static final String ftp_mov ="ftp_mov";
    private  static final String ftp_mov_win ="ftp_mov_win";
    private  static final String ftp_mov_loose ="ftp_mov_loose";
    private  static final String ftp_mov_screen ="ftp_mov_screen";

    private  static final String kaikei_alert_mode="kaikei_alert_mode";
    private  static final String reserv_mode="reserv_mode";
    private  static final String io_use_mode="io_use_mode";
    private  static final String kousoku_arraive_wait="kousoku_arraive_wait";
    private  static final String kousoku_arraive_show_count="kousoku_arraive_show_count";
    // static final String country_code="country_code";//未実装

    private   static final String LogErrLevel="LogErrLevel";
    private  static final String LogLevel="LogLevel";
    private  static final String WIFI_24_USE="WIFI_24_USE";
    //add 20161023
    private   static final String ORDER_CHECK_WAIT_TIME="ORDER_CHECK_WAIT_TIME";

    //add 20170428 ver75
    private  static final String WIFI_CHANGE_OFF="WIFI_CHANGE_OFF";
    private  static final String SKIP_GAME_USE="SKIP_GAME_USE";
    private   static final String LANG_MAX="LANG_MAX"; //maxを固定で６に設定　変更する場合はsaveの関数を修正する事。


    //add 20170503 ver77
    private  static final String SINAGIRE_DISP_ON="SINAGIRE_DISP_ON";


    private  static final String USE_SELECT_OPT_FLAG="USE_SELECT_OPT_Flag";
    private  static final String SKIP_GAME_USE_ENABLE_FLAG="SKIP_GAME_USE_ENABLE_FLAG";
    private  static final String USE_FLASHING_FLAG="USE_FLASHING_FLAG";
    private  static final String USE_BIGNETA_USE_FLAG="USE_BIGNETA_USE_FLAG";

    private  static final String MUTENKURA_MODE_ON="MUTENKURA_MODE_ON";
    private  static final String MISE_CODE="MISE_CODE";

    //add 20180817 下のレーンで取った鮮度君の情報を加え自動会計を目指す。
    private   static final String USE_AUTO_KAIKEI_FLAG="USE_AUTO_KAIKEI_FLAG";

    //add 20180901 操作案内を表示するか
    private  static final String USE_SOUSA_ANNAI_FLAG="USE_SOUSA_ANNAI_FLAG";

    //add 20181101 操作案内を表示するか
    private  static final String SOUSA_ANNAI_CAT="SOUSA_ANNAI_Cat";

    //add 2018 1128 game cntを保持する
    private  static final String USE_GAME_END_CNT_SAVE="USE_GAME_END_CNT_SAVE";
    private  static final String GAME_END_CNT_SAVE="GAME_END_CNT_SAVE";

    //20190529 大ネタ対象pageの配列
    private  static final String USE_BIGNETA_PAGE_FLAGS="USE_BIGNETA_PAGE_FLAGS";

    //20200110 特殊gameMode ON
    private  static final String USE_GAME_WITH_ARRAY_FLAG="USE_GAME_WITH_ARRAY_FLAG";


    //add 20200202 バッテリー監視 取得
    private  static final String USE_BATTEYR_CHECK_FLAG="USE_BATTEYR_CHECK_FLAG";

    // add 20200515 first free order
    private  static final String USE_FIRST_FREE_ORDER_FLAG= "USE_FIRST_FREE_ORDER_FLAG";

    // add 20200515 first free order item code
    private  static final String FIRST_FREE_ORDER_CODE= "IRST_FREE_ORDER_CODE";

    // add 20200608 opning movie fucntion
    private  static final String USE_OPENNINGMOVIE_FLAG= "OPENNINGMOVIE_FLAG";
    // add 20200608 opning movie fucntion
    private  static final String OPENNINGMOVIE_NUMVER= "OPENNINGMOVIE_NUMVER";

    //add 20200718  短いタッチは処理せずに捨てる。
    private  static final String TOUCH_TIME_THRESHOLD_CNT= "TOUCH_TIME_THRESHOLD_CNT";

    //add 20200225
    private static final String USE_SURVEY_FLAG = "USE_SURVEY_FLAG";
    private static final String USE_AUTO_CHECK_FLAG = "USE_AUTO_CHECK_FLAG";

    // add 20200707 QRコード使用
    private static final String QRCODE_DISP_FLAG = "QRCODE_DISP_FLAG";

    // add 20200822 ver320 touch後に無視するカウントの設定
    private static final String IGNORE_BOTTON_ON_COUNT_VAL = "IGNORE_BOTTON_ON_COUNT_VAL";

    // add 20201226 ver332 セカンドリクエスト通知機能　
    private static final String USE_SECOND_REQUEST = "USE_SECOND_REQUEST";

    //add 2021 0625 ver 341 200円皿注文時警告機能
    // TODO marge ２皿対応
    private static final String USE_DOUBLE_PLATE_ALERT = "USE_DOUBLE_PLATE_ALERT";

    // TODO marge ２皿 firstOrder
    private static final String USE_DOUBLE_PLATE_FIRST = "USE_DOUBLE_PLATE_FIRST";

    // TODO　add 20210817 原宿店屋台対応 　
    private static final String USE_FOOD_STAND = "USE_FOOD_STAND";

    // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加
    private static final String USE_LINKQRCODE = "USE_LINKQRCODE";
    private static final String LINK_QR_MAIN_VIEW_BUTTON_ON_FLAG = "LINK_QR_MAIN_VIEW_BUTTON_ON_FLAG";

    // TODO add 20211018 APP SERVERのエミュレート対応
    private static final String APP_SERVER_ADDR = "APP_SERVER_ADDR";
    private static final String APP_SERVER_PORT = "APP_SERVER_PORT";

    // TODO　20220108 ver 406 のマージ  不足分追加　20220131
    // add 20210317 スマホセルフチェック使用可否フラグ
    private static final String SMARTPHONE_CHECK_FLAG = "SMARTPHONE_CHECK_FLAG";

    // TODO　20220207 押上店向け、１F、２Fでコンテンツを分ける処理
    private static final String USE_BIKKURAGYO_FLAG = "USE_BIKKURAGYO_FLAG";


    /**
     * preferencesによるデーター保存
     * コンストラクタ　
     */
    public preferencesSave(Context context,logExtend obj){
        // プリファレンスの準備 //
        _gpref = context.getSharedPreferences( "pref", Context.MODE_PRIVATE );
        // プリファレンスに書き込むためのEditorオブジェクト取得 //
        _geditor = _gpref.edit();
        this._glog=obj;
    }

/*
    // "user_name" というキーで名前を登録
    editor.putString( "user_name", name );
    // "user_age" というキーで年齢を登録
    editor.putInt( "user_age", age );
    // 書き込みの確定（実際にファイルに書き込む）
    editor.apply();非同期
    editor.commit();同期

    */
    /**
     * データー保存実行
     */
    /*public void saveCommitr(){
        _geditor = _gpref.edit();
        _geditor.commit();
    }*/

    /**
     * 20181128
     * game cntを保持する
     */
    public int getUSE_GAME_END_CNT_SAVE() {
        return _gpref.getInt(USE_GAME_END_CNT_SAVE,1);
    }

    public void saveUSE_GAME_END_CNT_SAVE(int val) {
        if (val <0 || val >1) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(USE_GAME_END_CNT_SAVE,val);
        _geditor.apply();
    }

    public int getGAME_END_CNT_SAVE() {
        return _gpref.getInt(GAME_END_CNT_SAVE,0);
    }
    public void saveGAME_END_CNT_SAVE(int val) {
        if (val <0 || val >50) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(GAME_END_CNT_SAVE,val);
        _geditor.apply();
    }
    /**
     * FTPヴァージョン保存用
     */
    public void saveFtpver(int x,int val){
        String str=null;
        if(x<0 || x>5){
            return;
        }
        switch (x){
            case 0:
                str=ftp_img;
                break;
            case 1:
                str=ftp_sound;
                break;
            case 2:
                str=ftp_mov;
                break;
            case 3:
                str=ftp_mov_win;
                break;
            case 4:
                str=ftp_mov_loose;
                break;
            case 5:
                str=ftp_mov_screen;
                break;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(str, val);
        _geditor.apply();//20181128 appayに変更
    }
    /**
     * FTPヴァージョンGet
     */
    public int getFtpver(int x){
        String str=null;
        if(x<0 || x>5){
            return -1;
        }
        switch (x){
            case 0:
                str=ftp_img;
                break;
            case 1:
                str=ftp_sound;
                break;
            case 2:
                str=ftp_mov;
                break;
            case 3:
                str=ftp_mov_win;
                break;
            case 4:
                str=ftp_mov_loose;
                break;
            case 5:
                str=ftp_mov_screen;
                break;
        }
        // キーで保存されている値を読み出す
        return _gpref.getInt( str,0);
    }
    /**
     * LogLevel save
     */
    public void saveLogLevele(int val) {
        String str = null;
        if (val < 0 || val > 2) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(LogLevel,val);
        _geditor.apply();
    }
    /**
     * LogLevel get
     */
    public int getLogLevele() {
        return _gpref.getInt(LogLevel,0);
    }
    /**
     * LogLevel save
     */
    public void saveLogErrLevele(int val) {
        //String str = null;
        if (val < 0 || val > 2) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(LogErrLevel,val);
        _geditor.apply();
    }
    /**
     * LogLevel get
     */
    public int getLogErrLevele() {
        return _gpref.getInt(LogErrLevel,0);
    }
    /**
     * kaikei_alert_mode save
     */
    public void saveKaikei_alert_mode(int val) {
        String str = null;
        if (val < 0 || val > 1) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(kaikei_alert_mode,val);
        _geditor.apply();
    }
    /**
     * kaikei_alert_mode save
     */
    public int getKaikei_alert_mode() {
        return _gpref.getInt(kaikei_alert_mode,0);
    }
    /**
     * reserv_mode
     */
    public void saveReserv_mode(int val) {
        //String str = null;
        if (val < 0 || val > 1) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(reserv_mode,val);
        _geditor.apply();
    }
    /**
     * reserv_mode
     */
    public int getReserv_mode() {
        return _gpref.getInt(reserv_mode,0);
    }
    /**
     * io_use_mode
     */
    public void saveIo_use_mode(int val) {
        //String str = null;
        if (val < 0 || val > 1) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(io_use_mode,val);
        _geditor.apply();
    }
    /**
     * io_use_mode
     */
    public int getIo_use_mode() {
        //change 20190308 defaultで外国語を有効にする
        return _gpref.getInt(io_use_mode,1);
    }
    /**
     * kousoku_arraive_wait
     */
    public void savekousoku_arraive_wait(int val) {
        if (val < 0 || val > 30) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(kousoku_arraive_wait,val);
        _geditor.apply();
    }
    /**
     * io_use_mode
     */
    public int getkousoku_arraive_wait() {
        return _gpref.getInt(kousoku_arraive_wait,0);
    }
    /**
     * kousoku_arraive_wait
     */
    public void savekousoku_arraive_show_count(int val) {
        // String str = null;
        if (val < 0 || val > 100) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(kousoku_arraive_show_count,val);
        _geditor.apply();
    }

    /**-----------------------------------
     *change 20161023
     */
    public void saveORDER_CHECK_WAIT_TIME(int val){
        if (val < 0 || val > 50) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(ORDER_CHECK_WAIT_TIME,val);
        _geditor.apply();
    }
    public int getORDER_CHECK_WAIT_TIME() {
        int val= _gpref.getInt(ORDER_CHECK_WAIT_TIME,0);
        if (val < 0 || val > 50) {
            return 30; //return default
        }else {
            return val;
        }
    }


    //20171117 大ネタ商品対応
    public void saveBIGNETA_USE(int val) {
        String str = null;

        _glog.log("saveBIGNETA_USE >"+ val);

        if (val != 0 && val != 1) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(USE_BIGNETA_USE_FLAG,val);
        _geditor.apply();
    }
    public int getUSE_BIGNETA_USE_FLAG() {
        int val= _gpref.getInt(USE_BIGNETA_USE_FLAG,0);
        if (val == 0 || val == 1) {
            return val;
        }else {
            return 0; //return default
        }
    }

    //20171117 大ネタ商品対応

    /**
     * TableNumber
     */
    public void saveTableNumber(int val) {
        String str = null;

        _glog.log("saveTableNumber called2 >"+ val);

        if (val < 0 || val > 60) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(TABLE_NUMBER,val);
        _geditor.apply();
        _glog.log("saveTableNumber end2");
    }
    /**
     * TableNumber
     */
    public  int  getTableNumber() {
        return  _gpref.getInt(TABLE_NUMBER,0);
    }

    /**
     * io_use_mode
     */
    public int getkousoku_arraive_show_count() {
        return _gpref.getInt(kousoku_arraive_show_count,0);
    }


    /**
     *
     */
    public int getWIFI_24_USE() {
        return _gpref.getInt(WIFI_24_USE,0);
    }
    /**
     *
     */
    public void saveWIFI_24_USE(int val) {
        //String str = null;
        if (val <0 || val >1) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(WIFI_24_USE,val);
        _geditor.apply();
    }

    /**
     */
    public int getWIFI_CHANGE_OFF() {
        // TODO 20220203 押上店導入を踏まえWIFIの自動設定はデフォルトでOFFとする
        // return _gpref.getInt(WIFI_CHANGE_OFF,0);
        return _gpref.getInt(WIFI_CHANGE_OFF,1);
    }
    public void saveWIFI_CHANGE_OFF(int val) {
        // String str = null;
        if (val <0 || val >1) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(WIFI_CHANGE_OFF,val);
        //saveCommitr();
        _geditor.apply();
    }

    public int getSKIP_GAME_USE() {
        //change  defult 20190307
        return _gpref.getInt(SKIP_GAME_USE,1);
        //return _gpref.getInt(SKIP_GAME_USE,0);
    }

    public void saveSKIP_GAME_USE(int val) {
        // String str = null;
        if (val <0 || val >1) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(SKIP_GAME_USE,val);
        _geditor.apply();
    }

    //=add 20170619 ver84  // 20180220 `初期値を1に変更
    public int getSKIP_GAME_USE_ENABLE_FLAG() {
        return _gpref.getInt(SKIP_GAME_USE_ENABLE_FLAG, 1);
    }
    public void saveSKIP_GAME_USE_ENABLE_FLAG(int val) {
        //String str = null;
        if (val <0 || val >1) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(SKIP_GAME_USE_ENABLE_FLAG,val);
        _geditor.apply();
    }


    //初期値は３とする。 20180220 `初期値を４に変更
    public int getLANG_MAX() {
        //return _gpref.getInt(LANG_MAX,3);
        return _gpref.getInt(LANG_MAX,4);
    }
    //maxを６に設定
    public void saveLANG_MAX(int val) {
        String str = null;
        if (val <0 || val >6) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(LANG_MAX,val);
        _geditor.apply();
    }


    public int getSINAGIRE_DISP_ON() {
        return _gpref.getInt(SINAGIRE_DISP_ON,0);
    }
    public void saveSINAGIRE_DISP_ON(int val) {
        //String str = null;
        if (val <0 || val >1) {
            return;
        }
        _geditor = _gpref.edit();
        _geditor.putInt(SINAGIRE_DISP_ON,val);
        _geditor.apply();
    }

    //add20170920 ver91
    //シャリ選択のフラグ
    // public int getUSE_SELECT_SHARI_FLAG() {
    public int    getUSE_SELECT_OPT_FLAG(){
        return _gpref.getInt(USE_SELECT_OPT_FLAG,1);
    }

    // public void saveUSE_SELECT_SHARI_FLAG(int val) {
    public void saveUSE_SELECT_OPT_FLAG(int val) {
        String str = null;
        if (val ==0|| val ==1) {
            _geditor = _gpref.edit();
            _geditor.putInt(USE_SELECT_OPT_FLAG, val);
        }else{
            _geditor = _gpref.edit();
            _geditor.putInt(USE_SELECT_OPT_FLAG, val);
        }
        _geditor.apply();
    }

    //add20171027
    //USE_FLASHING_USE_FLAG
    public int get_FLASHING_USE_FLAG() {
        return _gpref.getInt(USE_FLASHING_FLAG,0);
    }
    public void saveFLASHING_USE_FLAG(int val) {
        // String str = null;
        if (val ==0|| val ==1) {
            _geditor = _gpref.edit();
            _geditor.putInt(USE_FLASHING_FLAG,val);
        }else{
            _geditor = _gpref.edit();
            _geditor.putInt(USE_FLASHING_FLAG,0);
        }
        _geditor.apply();
    }

    //add20180415
    public int get_FLAG_MUTENKURA_MODE_ON() {
        return _gpref.getInt(MUTENKURA_MODE_ON,0);
    }
    public void saveFLAG_MUTENKURA_MODE_ON(int val) {
        String str = null;
        if (val ==0|| val ==1) {
            _geditor = _gpref.edit();
            _geditor.putInt(MUTENKURA_MODE_ON,val);
        }else{
            _geditor = _gpref.edit();
            _geditor.putInt(MUTENKURA_MODE_ON,0);
        }
        _geditor.apply();
    }







    //20180622
    public int getMISECODE() {
        return _gpref.getInt(MISE_CODE,0);
    }
    public void saveMISECODE(int val) {
        // String str = null;
        _geditor = _gpref.edit();
        _geditor.putInt(MISE_CODE,val);
        _geditor.apply();
    }


    //20180622
    public int getUSE_AUTO_KAIKEI_FLAG() {
        return _gpref.getInt(USE_AUTO_KAIKEI_FLAG,0);
    }
    public void saveUSE_AUTO_KAIKEI_FLAG(int val) {
        _geditor = _gpref.edit();
        _geditor.putInt(USE_AUTO_KAIKEI_FLAG,val);
        //add 20190623
        _geditor.apply();
    }

	// add 20200225
    public int getUSE_SURVEY_FLAG() {
        return _gpref.getInt(USE_SURVEY_FLAG,0); //test 検証のためPreferenceに登録がない場合は使用としている
    }
    public void saveUSE_SURVEY_FLAG(int val) {
        _geditor = _gpref.edit();
        _geditor.putInt(USE_SURVEY_FLAG,val);
        _geditor.apply();
    }

    // add 20200225
    public int getUSE_AUTO_CHECK_FLAG() {
        return _gpref.getInt(USE_AUTO_CHECK_FLAG,0); //test 検証のためPreferenceに登録がない場合は使用としている
    }
    public void saveUSE_AUTO_CHECK_FLAG(int val) {
        _geditor = _gpref.edit();
        _geditor.putInt(USE_AUTO_CHECK_FLAG,val);
        _geditor.apply();
    }


    //20180622
    public int getUSE_SOUSA_ANNAI_FLAG() {
        return _gpref.getInt(USE_SOUSA_ANNAI_FLAG,0);
    }

    public void saveUSE_SOUSA_ANNAI_FLAG(int val) {
        _geditor = _gpref.edit();
        _geditor.putInt(USE_SOUSA_ANNAI_FLAG,val);
        _geditor.apply();
    }


    //20181101 操作案内のカテゴリー
    public int getSOUSA_ANNAI_CAT() {
        return _gpref.getInt(SOUSA_ANNAI_CAT,0);
    }
    public void saveSOUSA_ANNAI_CAT(int val) {
        _geditor = _gpref.edit();
        _geditor.putInt(SOUSA_ANNAI_CAT,val);
        _geditor.apply();
    }


    //20190528
    //大ネタ対応ページに対応するかの設定
    //page毎に０＝使用しない　１＝使用するとして　２０ページ分の設定を00000000000000000000の形の文字列で保存する
    public int getUSE_BIGNETA_PAGE_FLAGS(int page) {
        int _page=0;
        String _strArry=_gpref.getString(USE_BIGNETA_PAGE_FLAGS,"00000000000000000000");
        char ch=0;
        if(page<1 || page>20){
            return 0;
        }else{
            _page=page-1;
        }
        //_glog.log("sgetUSE_BIGNETA_PAGE_FLAGS called:"+_strArry);

        ch=_strArry.charAt(_page);
        if(ch=='1'){
            return 1;
        }else{
            return 0;
        }
    }
    public void saveUSE_BIGNETA_PAGE_FLAGS(int val,int page) {
        int _page=0;
        int _val=0;
        String _strArry=_gpref.getString(USE_BIGNETA_PAGE_FLAGS,"00000000000000000000");
        char ch='0';

        StringBuilder sb= new StringBuilder(_strArry);

        //_glog.log("saveUSE_BIGNETA_PAGE_FLAGS called:"+_strArry);

        if(page<1 || page>20){
            return;
        }else{
            _page=page-1;
        }
        if(val==1){
            ch='1';
        }else{
            ch='0';
        }
        sb.setCharAt(_page,ch);

        _geditor = _gpref.edit();
        _geditor.putString(USE_BIGNETA_PAGE_FLAGS,sb.toString());
        _geditor.apply();
    }



    //20200110 特殊gameMode ON
    public int getUSE_GAME_WITH_ARRAY_FLAG() {
        return _gpref.getInt(USE_GAME_WITH_ARRAY_FLAG,0);
    }
    public void saveUSE_GAME_WITH_ARRAY_FLAG(int val) {
        //String str = null;
        if (val ==0|| val ==1) {
            _geditor = _gpref.edit();
            _geditor.putInt(USE_GAME_WITH_ARRAY_FLAG,val);
        }else{
            _geditor = _gpref.edit();
            _geditor.putInt(USE_GAME_WITH_ARRAY_FLAG,0);
        }
        _geditor.apply();
    }

    //add 20200202 バッテリー監視 ON  -> change 20200908 ver325 default 1
    public int getUSE_BATTEYR_CHECK_FLAG() {
       // return _gpref.getInt(USE_BATTEYR_CHECK_FLAG,0);
        return _gpref.getInt(USE_BATTEYR_CHECK_FLAG,1);
    }
    public void saveUSE_BATTEYR_CHECK_FLAG(int val) {
        //String str = null;
        if (val ==0|| val ==1) {
            _geditor = _gpref.edit();
            _geditor.putInt(USE_BATTEYR_CHECK_FLAG,val);
        }else{
            _geditor = _gpref.edit();
            //add 20200202 バッテリー監視 ON  -> change 20200908 ver325 default 1
            _geditor.putInt(USE_BATTEYR_CHECK_FLAG,1);
        }
        _geditor.apply();
    }

    // add 20200515 first free order
    public int getUSE_FIRST_FREE_ORDER_FLAG() {
        return _gpref.getInt(USE_FIRST_FREE_ORDER_FLAG,0);
    }
    public void saveUSE_FIRST_FREE_ORDER_FLAG(int val) {
        if (val ==0|| val ==1) {
            _geditor = _gpref.edit();
            _geditor.putInt(USE_FIRST_FREE_ORDER_FLAG,val);
        }else{
            _geditor = _gpref.edit();
            _geditor.putInt(USE_FIRST_FREE_ORDER_FLAG,0);
        }
        _geditor.apply();
    }

    public int getFIRST_FREE_ORDER_CODE() {
        return _gpref.getInt(FIRST_FREE_ORDER_CODE,0);
    }
    public void saveFIRST_FREE_ORDER_CODE(int val) {
            _geditor = _gpref.edit();
            _geditor.putInt(FIRST_FREE_ORDER_CODE,val);
            _geditor.apply();
    }


    // add 20200608 opning movie fucntion
    public int getUSE_OPENNINGMOVIE_FLAG() {
        // TODO 20220228 #35 案内動画をデフォルトで有効にする
        // return _gpref.getInt(USE_OPENNINGMOVIE_FLAG,0);
        return _gpref.getInt(USE_OPENNINGMOVIE_FLAG,1);
    }
    public void saveUSE_OPENNINGMOVIE_FLAG(int val) {
        if (val ==0|| val ==1) {
            _geditor = _gpref.edit();
            _geditor.putInt(USE_OPENNINGMOVIE_FLAG,val);
        }else{
            _geditor = _gpref.edit();
            _geditor.putInt(USE_OPENNINGMOVIE_FLAG,0);
        }
        _geditor.apply();
    }

    public int getOPENNINGMOVIE_NUMVER() {
        return _gpref.getInt(OPENNINGMOVIE_NUMVER,1);
    }
    public void saveOPENNINGMOVIE_NUMVER(int val) {
        if (val >0 && val < 5 ) { //max5
            _geditor = _gpref.edit();
            _geditor.putInt(OPENNINGMOVIE_NUMVER, val);
            _geditor.apply();
        }
    }


   //add 20200718  短いタッチは処理せずに捨てる。
    public int getTOUCH_TIME_THRESHOLD_CNT() {
        return _gpref.getInt(TOUCH_TIME_THRESHOLD_CNT,20);
    }
    public void saveTOUCH_TIME_THRESHOLD_CNT(int val) {
        if (val >= 10 && val <= 80 ) {
            _geditor = _gpref.edit();
            _geditor.putInt(TOUCH_TIME_THRESHOLD_CNT, val);
            _geditor.apply();
        }
    }

    // add 20200713
    // USE_QR_CODE_FLAG
    public int getQRCODE_DISP_FLAG() {
        return _gpref.getInt(QRCODE_DISP_FLAG,1);
    }
    public void saveQRCODE_DISP_FLAG(int val) {
        _geditor = _gpref.edit();
        _geditor.putInt(QRCODE_DISP_FLAG,val);
        _geditor.apply();
    }

    // add 20200822 ver320
    // IGNORE_BOTTON_ON_COUNT_VAL
    public int getIGNORE_BOTTON_ON_COUNT_VAL() {
        return _gpref.getInt(IGNORE_BOTTON_ON_COUNT_VAL,2);
    }
    public void saveIGNORE_BOTTON_ON_COUNT_VAL(int val) {
        _geditor = _gpref.edit();
        if(val > 0 && val < 6 ) {
            _geditor.putInt(IGNORE_BOTTON_ON_COUNT_VAL,val);
        }else{
            _geditor.putInt(IGNORE_BOTTON_ON_COUNT_VAL,2);
        }
        _geditor.apply();
    }

    public int getUSE_SECOND_REQUEST() {
        return _gpref.getInt(USE_SECOND_REQUEST,0);
    }
    public void saveUSE_SECOND_REQUEST(int val) {
        _geditor = _gpref.edit();
        if(val == 0 || val == 1 ) {
            _geditor.putInt(USE_SECOND_REQUEST,val);
        }else{
            _geditor.putInt(USE_SECOND_REQUEST,0);
        }
        _geditor.apply();
    }

    // TODO marge ２皿対応
    public int getUSE_DOUBLE_PLATE_ALERT() {
        return _gpref.getInt(USE_DOUBLE_PLATE_ALERT,1);
    }
    public void saveUSE_DOUBLE_PLATE_ALERT(int val) {
        _geditor = _gpref.edit();
        if(val == 0 || val == 1 ) {
            _geditor.putInt(USE_DOUBLE_PLATE_ALERT, val);
        }else{
            _geditor.putInt(USE_DOUBLE_PLATE_ALERT,0);
        }
        _geditor.apply();
    }
    // TODO marge ２皿対応


    // TODO marge ２皿 firstOrder
    public int getUSE_DOUBLE_PLATE_FIRST () {
        return _gpref.getInt(USE_DOUBLE_PLATE_FIRST,1);
    }
    public void saveUSE_DOUBLE_PLATE_FIRST (int val) {
        _geditor = _gpref.edit();
        if(val == 0 || val == 1 ) {
            _geditor.putInt(USE_DOUBLE_PLATE_FIRST,val);
        }else{
            _geditor.putInt(USE_DOUBLE_PLATE_FIRST,0);
        }
        _geditor.apply();
    }
    // TODO marge ２皿 firstOrder

    // TODO　add 20210817 原宿店屋台対応 　defult = 1
    public int getUSE_FOOD_STAND () {
        return _gpref.getInt(USE_FOOD_STAND,1);
    }
    public void saveUSE_FOOD_STAND (int val) {
        _geditor = _gpref.edit();
        if(val == 0 || val == 1 ) {
            _geditor.putInt(USE_FOOD_STAND,val);
        }else{
            _geditor.putInt(USE_FOOD_STAND,0);
        }
        _geditor.apply();
    }
    // TODO　add 20210817 原宿店屋台対応 　


    // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加 　defult = 0
    // TODO add 20211129　default を０に変更
    public int getUSE_LINKQRCODE() {
        return _gpref.getInt(USE_LINKQRCODE,0);
    }
    public void saveUSE_LINKQRCODE (int val) {
        _geditor = _gpref.edit();
        if(val == 0 || val == 1 ) {
            _geditor.putInt(USE_LINKQRCODE, val);
        }else{
            _geditor.putInt(USE_LINKQRCODE,0);
        }
        _geditor.apply();
    }

    public int getLINK_QR_MAIN_VIEW_BUTTON_ON_FLAG () {
        return _gpref.getInt(LINK_QR_MAIN_VIEW_BUTTON_ON_FLAG,0);
    }
    public void saveLINK_QR_MAIN_VIEW_BUTTON_ON_FLAG  (int val) {
        _geditor = _gpref.edit();
        if(val == 0 || val == 1 ) {
            _geditor.putInt(LINK_QR_MAIN_VIEW_BUTTON_ON_FLAG, val);
        }else{
            _geditor.putInt(LINK_QR_MAIN_VIEW_BUTTON_ON_FLAG,0);
        }
        _geditor.apply();
    }




    // TODO add 20211018 APP SERVERのエミュレート対応
    public String getAPP_SERVER_ADDR () {
        return _gpref.getString(APP_SERVER_ADDR,"192.168.11.3");
    }

    // 呼び出し側でIPアドレスのデリゲートをする前提
    public void saveAPP_SERVER_ADDR  (String val) {
        _geditor = _gpref.edit();
        _geditor.putString(APP_SERVER_ADDR, val);
        _geditor.apply();
    }

    public int getAPP_SERVER_PORT () {
        return _gpref.getInt(APP_SERVER_PORT,10000);
    }
    public void saveAPP_SERVER_PORT  (int val) {
        _geditor = _gpref.edit();
        if(val == 0 || val == 1 ) {
            _geditor.putInt(APP_SERVER_PORT, val);
        }else{
            _geditor.putInt(APP_SERVER_PORT,10000);
        }
        _geditor.apply();
    }

    // TODO add 20211018 APP SERVERのエミュレート対応






    // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加


    // TODO　20220108 ver 406 のマージ  不足分追加　20220131
    //20210317 ver336
    //スマホセルフチェック使用可否フラグ
    public int getUSE_SMARTPHONE_CHECK_FLAG() {
        return _gpref.getInt(SMARTPHONE_CHECK_FLAG, 0);
    }

    public void saveUSE_SMARTPHONE_CHECK_FLAG(int val) {
        if (val == 0 || val == 1) {
            _geditor.putInt(SMARTPHONE_CHECK_FLAG, val);
        } else {
            _geditor.putInt(SMARTPHONE_CHECK_FLAG, 0);
        }
        _geditor.apply();
    }

    // TODO　20220207 押上店向け、１F、２Fでコンテンツを分ける処理
    public int getUSE_BIKKURAGYO_FLAG () {
        return _gpref.getInt(USE_BIKKURAGYO_FLAG,0);
    }
    public void saveUSE_BIKKURAGYO_FLAG  (int val) {
        _geditor = _gpref.edit();
        if(val == 0 || val == 1 ) {
            _geditor.putInt(USE_BIKKURAGYO_FLAG, val);
        }else{
            _geditor.putInt(USE_BIKKURAGYO_FLAG,0);
        }
        _geditor.apply();
    }

    /**
     * io_use_mode
     */
    public String PrintPreferencesSavet() {
        StringBuilder sb=new StringBuilder();
        sb.append("TABLE_NUMBER:");
        sb.append(_gpref.getInt(TABLE_NUMBER,0) + "\n");
        sb.append("ftp_img:");
        sb.append(_gpref.getInt(ftp_img,0) + "\n");
        sb.append("ftp_sound:");
        sb.append(_gpref.getInt(ftp_sound,0) + "\n");
        sb.append("ftp_mov:");
        sb.append(_gpref.getInt(ftp_mov,0) + "\n");
        sb.append("ftp_mov_win:");
        sb.append(_gpref.getInt(ftp_mov_win,0) + "\n");
        sb.append("ftp_mov_loose:");
        sb.append(_gpref.getInt(ftp_mov_loose,0) + "\n");
        sb.append("ftp_mov_screen:");
        sb.append(_gpref.getInt(ftp_mov_screen,0) + "\n");
        sb.append("kaikei_alert_mode:");
        sb.append(_gpref.getInt(kaikei_alert_mode,0) + "\n");
        sb.append("reserv_mode:");
        sb.append(_gpref.getInt(reserv_mode,0) + "\n");
        sb.append("io_use_mode:");
        sb.append(_gpref.getInt(io_use_mode,0) + "\n");
        sb.append("kousoku_arraive_wait:");
        sb.append(_gpref.getInt(kousoku_arraive_wait,0) + "\n");
        sb.append("kousoku_arraive_show_count:");
        sb.append(_gpref.getInt(kousoku_arraive_show_count,0) + "\n");
        sb.append("LogLevel:");
        sb.append(_gpref.getInt(LogLevel,0) + "\n");
        sb.append("LogErrLevel:");
        sb.append(_gpref.getInt(LogErrLevel,0) + "\n");
        sb.append("ORDER_CHECK_WAIT_TIME:");
        sb.append(_gpref.getInt( ORDER_CHECK_WAIT_TIME,0) + "\n");
        sb.append("USE_SELECT_OPT_Flag:");
        sb.append(_gpref.getInt(USE_SELECT_OPT_FLAG,0) + "\n");
        return sb.toString();
    }


}
