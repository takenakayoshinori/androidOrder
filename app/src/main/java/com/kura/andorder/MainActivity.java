package com.kura.andorder;
;;;;
import android.app.Activity;
import android.app.AlarmManager;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import android.app.PendingIntent;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.*;
import java.util.ArrayList;
import java.util.Locale;

import android.view.WindowManager.LayoutParams;
import android.media.AudioManager;

import org.json.JSONObject;

//------------------------------------------------------------------------------
public class MainActivity extends Activity implements View.OnClickListener {
<<<<<<< HEAD
    private final String _gAppver = "566";
    private final String APP_UP_DATE = "20211229";
=======
    private final String _gAppver = "620";
    private final String APP_UP_DATE = "2022401";

    // TODO　20220108 ver 406 のマージ
    private String surveyBasePath = "survey";
    private String qrCodeBasePath = "qr_code";
    private String autoCheckBasePath = "auto_check";
    private String spSelfCheckBasePath = AppEnv.SP_CHECK_APP_NAME;
    private String surveyHtmlPath = "client/html/client_kaikei.html";
    private String autoCheckHtmlPath = "client/html/client_kaikei.html";
    private String qrCodeHtmlPath = "client/html/qrcode.html";
    private String spSelfCheckHtmlPath = "client/html/check.html";
    // TODO　20220108 ver 406 のマージ
>>>>>>> bdd96031fb7465e981103be81608750222a8869a

    //-----------------------定数の宣言---------------------
    private static final String BR = System.getProperty("line.separator");
    private int _gWIFI_CHANGE_OFF = 0;//for usa wifiの通信取得を抑制,有線の場合に設定する。
    private int _gSKIP_GAME_USE = 0;//change defalut 20180220
    private int _gLANG_MAX = 4;//change defalut 20180220

    private int _gSINAGIRE_DISP_ON = 0;//品切れ表示を使用するかのフラグ 初期値０にする事
    //HELP画面用のフラグ
    private View _gviewHELP = null;
    private int _gDrawHELPFlag = 0;
    private int _gviewHELPOnTimer = 0;
    private int _gHELP_STEP = 0;//helpの確認ステップ
    private TextView _gtxHELP_Message = null;

    //add 20190614 スマホオーダー対応　ゲーム
    private final int MAX_SMART_MOV_POOL = 20;
    private int _gSmartGameCount = 0;
    private int _gSmartGameMov[] = new int[MAX_SMART_MOV_POOL];
    private int _gSmartGameType[] = new int[MAX_SMART_MOV_POOL];
    private String _gCurrentSmartGameFile = "";
    ;
    //ヘルプのステップ
    private final int HELP_STEP_CLEAR = 0;
    private final int HELP_STEP_SENDKUN_MOV = 1;
    private final int HELP_STEP_GAME_SKIP_CHECK = 2;
    private final int HELP_STEP_GAME_SKIP_ON = 3;
    private final int HELP_STEP_GAME_SKIP_OFF = 4;
    private final int HELP_STEP_CLOSE_WAIT = 5;
    private String _gSmartMovName[] = new String[MAX_SMART_MOV_POOL];

    //ver338 WebViewコンテンツ状態
    private final int DOWNLOAD_FALED_DIRCTORY_EXIST = 0;
    private final int DOWNLOAD_FALED_DIRCTORY_NOTHING = 99;

    //add 20180503 QRcod　未使用
    /*
    private int _FLAG_CREATEQRCODE_USE_ON = 0;
    private int _gDrawCreateQRCodeFlag = 0;
    private View _gviewCreateQRCode = null;
    private int _gviewCreateQRCodeOnTimer = 0;
    */

    // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加
    // TODO add 20210913 get QRINFO　  activity_main.xmlにQR確認ようのボタンを追加
    private int _FLAG_LINKQRCODE_USE_ON = 0;
    private int _gDrawLinkQRCodeFlag = 0;
    private View _gviewLinkQRCode = null;
    private int _gviewLinkQRCodeOnTimer = 300;
    private int _gLinkQRMainViewButtonOnFlag = 0; // メイン画面からQRを確認できるようにするボタン
    // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加


    //add 20180415 無添くらモード
    private int _FLAG_MUTENKURA_MODE_ON = 0;
    private int _gKAIKEI_For_SaraButton_Count = 0;

    //<change20150530>test CM MOVE
    private int _FLAG_CM_MOVE_ON = 0;

    // ver83 add=================================
    private threadPutFile _gthreadPutFile = null;
    private Thread _gTHREAD_PUTFILE = null;

    private int _gSKIP_GAME_USE_ENABLE_FLAG = 1;//change default 20180220

    // ver86 add==========データー解析用
    private int _gTAG_CUSTOMOER_ID = 0;

    //----------------------------------------
    private final String SSID_NAME = "katch1";
    private final String SSID_NAME24 = "katch"; //add 20160705 For 2.4G
    private final String SSID_PASS = "sys00001";

    private final int F_VIEW_STATE_OPEN_CMD = 1;
    private final int F_VIEW_STATE_LIVE = 2;
    private final int F_VIEW_STATE_LIVE_ADD = 3;
    private final int F_VIEW_STATE_LIVE_WAIT = 6;//add 20160316 for move
    private final int F_VIEW_STATE_CLOSE_WAIT = 4;
    private final int F_VIEW_STATE_CLOSE_CMD = 5;
    private final int F_VIEW_STATE_ERR = 10;
    private final int F_VIEW_STATE_CLEAR = 0;

    private int _gCurrentPage = 1;
    // TODO merge PAGE MAX 30 20210706
    // private final int PAGE_MAX = 20;
    private final int PAGE_MAX = 30;
    private final int ORDER_MAX = 12;
    private int _gOrderItemKey = 0;//オーダーする商品のKEY

    private String _TAG = "USER_LOG";
    private Handler handler = new Handler();
    private Runnable runnable;

    //battery status
    private int _gBatteryLevel = 0;//充電レベル
    private int _gBatteryStatus = 0;//充電状態
    //=========wegit==========
    //--main button id
    //ver83 20170614 びっくらぽん設定変更用のボタンを追加
    private final int _gBtid[] = {R.id.bt_main1, R.id.bt_main2, R.id.bt_main3, R.id.bt_main4, R.id.bt_main5,
            R.id.bt_main6, R.id.bt_main7, R.id.bt_main8, R.id.bt_main9, R.id.bt_main10};
    //main  button
    private Button _gBt_main[] = new Button[_gBtid.length];

    //main neta img
    private final int mIBid[] = {R.id.netaImg1, R.id.netaImg2, R.id.netaImg3, R.id.netaImg4,
            R.id.netaImg5, R.id.netaImg6, R.id.netaImg7, R.id.netaImg8,
            R.id.netaImg9, R.id.netaImg10, R.id.netaImg11, R.id.netaImg12};
    //--main img button
    private ImageButton IB[] = new ImageButton[mIBid.length];

    //--neta name id
    private final int mNameA[] = {R.id.netaNameA1, R.id.netaNameA2, R.id.netaNameA3, R.id.netaNameA4,
            R.id.netaNameA5, R.id.netaNameA6, R.id.netaNameA7, R.id.netaNameA8,
            R.id.netaNameA9, R.id.netaNameA10, R.id.netaNameA11, R.id.netaNameA12};
    private final int mNameB[] = {R.id.netaNameB1, R.id.netaNameB2, R.id.netaNameB3, R.id.netaNameB4,
            R.id.netaNameB5, R.id.netaNameB6, R.id.netaNameB7, R.id.netaNameB8,
            R.id.netaNameB9, R.id.netaNameB10, R.id.netaNameB11, R.id.netaNameB12};

    //TODO 20211225 品切れ 指定商品の品切れ文字表示用
    /*
    private final int mSinagire[] = {R.id.netaSINAGIRE1, R.id.netaSINAGIRE2, R.id.netaSINAGIRE3, R.id.netaSINAGIRE4,
            R.id.netaSINAGIRE5, R.id.netaSINAGIRE6, R.id.netaSINAGIRE7, R.id.netaSINAGIRE8,
            R.id.netaSINAGIRE9, R.id.netaSINAGIRE10, R.id.netaSINAGIRE11, R.id.netaSINAGIRE12};
     */
    //TODO 20211225 品切れはレイアウト画面でコメントアウトしているので、使用する場合はコメントアウトを外す事。この機能は未検証
    // private TextView TSINAGIRE[] = new TextView[mSinagire.length];
    //TODO 20211225 品切れ 指定商品の品切れ文字表示用


    //--neta name textView
    private TextView TNAME_A[] = new TextView[mNameA.length];
    private TextView TNAME_B[] = new TextView[mNameB.length];

    //--tag buttton id

    // TODO 20210829 TAG_MAXを９に増やす
    /*
    private final int _gBtagid[] = {R.id.bt_tag1, R.id.bt_tag2, R.id.bt_tag3, R.id.bt_tag4,
            R.id.bt_tag5, R.id.bt_tag6, R.id.bt_tag7};
            */
    private final int _gBtagid[] = {R.id.bt_tag1, R.id.bt_tag2, R.id.bt_tag3, R.id.bt_tag4,
            R.id.bt_tag5, R.id.bt_tag6, R.id.bt_tag7, R.id.bt_tag8, R.id.bt_tag9};
    // TODO 20210829 TAG_MAXを９に増やす

    //--tag buttton id
    private Button _gBt_tag[] = new Button[_gBtagid.length];
    //--table back
    //private LinearLayout _gTableBack = null;
    private FrameLayout _gTableBack = null;
    //--MAIN PAGE
    private FrameLayout _gMAIN_FRAME = null;
    private TextView _gmT_tableNum;//mT_tableNum
    private TextView _gmT_AppVer;//mT_AppVer

    //--RIREKI
    private final int _gRIREKI_ARRIVE_ID[] = {R.id.RIREKI_ARRIVE1, R.id.RIREKI_ARRIVE2,
            R.id.RIREKI_ARRIVE3, R.id.RIREKI_ARRIVE4, R.id.RIREKI_ARRIVE5, R.id.RIREKI_ARRIVE6,
            R.id.RIREKI_ARRIVE7, R.id.RIREKI_ARRIVE8, R.id.RIREKI_ARRIVE9, R.id.RIREKI_ARRIVE10};
    private final int _gRIREKI_TIME_ID[] = {R.id.RIREKI_TIME1, R.id.RIREKI_TIME2,
            R.id.RIREKI_TIME3, R.id.RIREKI_TIME4, R.id.RIREKI_TIME5, R.id.RIREKI_TIME6,
            R.id.RIREKI_TIME7, R.id.RIREKI_TIME8, R.id.RIREKI_TIME9, R.id.RIREKI_TIME10};
    private final int _gRIREKI_NAME_ID[] = {R.id.RIREKI_NAME1, R.id.RIREKI_NAME2,
            R.id.RIREKI_NAME3, R.id.RIREKI_NAME4, R.id.RIREKI_NAME5, R.id.RIREKI_NAME6,
            R.id.RIREKI_NAME7, R.id.RIREKI_NAME8, R.id.RIREKI_NAME9, R.id.RIREKI_NAME10};
    private final int _gRIREKI_SARA_ID[] = {R.id.RIREKI_SARA1, R.id.RIREKI_SARA2,
            R.id.RIREKI_SARA3, R.id.RIREKI_SARA4, R.id.RIREKI_SARA5, R.id.RIREKI_SARA6,
            R.id.RIREKI_SARA7, R.id.RIREKI_SARA8, R.id.RIREKI_SARA9, R.id.RIREKI_SARA10};

    private TextView RIREKI_ARRIVE_TEXT[] = new TextView[10];
    private TextView RIREKI_TIME_TEXT[] = new TextView[10];
    private TextView RIREKI_NAME_TEXT[] = new TextView[10];
    private TextView RIREKI_SARA_TEXT[] = new TextView[10];

    private Button RIREKI_BUTTON_PRE = null;
    private Button RIREKI_BUTTON_NEXT = null;
    private Button RIREKI_BUTTON_BACK = null;

    //--KAIKEI
    private final int _gKAIKEI_CAT_NAME_ID[] = {R.id.KAIKEI_CAT_NAME1, R.id.KAIKEI_CAT_NAME2,
            R.id.KAIKEI_CAT_NAME3, R.id.KAIKEI_CAT_NAME4, R.id.KAIKEI_CAT_NAME5,
            R.id.KAIKEI_CAT_NAME6, R.id.KAIKEI_CAT_NAME7, R.id.KAIKEI_CAT_NAME8,
            R.id.KAIKEI_CAT_NAME9, R.id.KAIKEI_CAT_NAME10, R.id.KAIKEI_CAT_NAME11,
            R.id.KAIKEI_CAT_NAME12, R.id.KAIKEI_CAT_NAME13, R.id.KAIKEI_CAT_NAME14,
            R.id.KAIKEI_CAT_NAME15, R.id.KAIKEI_CAT_NAME16, R.id.KAIKEI_CAT_NAME17,
            R.id.KAIKEI_CAT_NAME18, R.id.KAIKEI_CAT_NAME19, R.id.KAIKEI_CAT_NAME20};
    private final int _gKAIKEI_CAT_COUNT_ID[] = {R.id.KAIKEI_CAT_COUNT1, R.id.KAIKEI_CAT_COUNT2,
            R.id.KAIKEI_CAT_COUNT3, R.id.KAIKEI_CAT_COUNT4, R.id.KAIKEI_CAT_COUNT5,
            R.id.KAIKEI_CAT_COUNT6, R.id.KAIKEI_CAT_COUNT7, R.id.KAIKEI_CAT_COUNT8,
            R.id.KAIKEI_CAT_COUNT9, R.id.KAIKEI_CAT_COUNT10, R.id.KAIKEI_CAT_COUNT11,
            R.id.KAIKEI_CAT_COUNT12, R.id.KAIKEI_CAT_COUNT13, R.id.KAIKEI_CAT_COUNT14,
            R.id.KAIKEI_CAT_COUNT15, R.id.KAIKEI_CAT_COUNT16, R.id.KAIKEI_CAT_COUNT17,
            R.id.KAIKEI_CAT_COUNT18, R.id.KAIKEI_CAT_COUNT19, R.id.KAIKEI_CAT_COUNT20};

    private TextView _gKAIKEI_CAT_NAME_TEXT[] = new TextView[20];
    private TextView _gKAIKEI_CAT_COUNT_TEXT[] = new TextView[20];
    // private TextView _gKAIKEI_SARA = null;

    // ナンバー表示
    private final int _gNUM_BUTTO_ID[] = {R.id.btNum0, R.id.btNum1, R.id.btNum2,

            R.id.btNum3, R.id.btNum4, R.id.btNum5,
            R.id.btNum6, R.id.btNum7, R.id.btNum8,
            R.id.btNum9};
    private Button _gbtNumStr;

    // TODO 20210829 TAG_MAXを９に増やす
    /*
    private final int _gTagColor[] = {Color.parseColor("#b0c4de"), Color.parseColor("#008080"), Color.parseColor("#e9967a"),
            Color.parseColor("#228b22"), Color.parseColor("#87cefa"), Color.parseColor("#eee8aa"), Color.parseColor("#ff6347")};
     */
    private final int _gTagColor[] = {Color.parseColor("#b0c4de"), Color.parseColor("#008080"), Color.parseColor("#e9967a"),
            Color.parseColor("#228b22"), Color.parseColor("#87cefa"), Color.parseColor("#eee8aa"), Color.parseColor("#ff6347"),
            Color.parseColor("#ffccff"),Color.parseColor("#cc66ff")};

    private int _gTimerCount = 0;
    //=========class==========
    private threadUdpRegularConnection _gthUdp = null;
    private threadRevCmd _gthRevCmd = null;
    private threadSendCmd _gthreadSendCmd = null;
    private threadFtpClient _gthreadFtpClient = null;
    private threadGetFile _gthreadGetFile = null;
    private netaInfo _gNetaInfo = null;

    private preferencesSave _gPreferencesSave = null;
    private settingInfo _gSettingInfo = null;
    private settingInfoFromServer _gSettingInfoFromServer = null;
    private orderUnderInfo _gOrderUnderInfo = null;
    private orderHeightInfo _gOrderHeightInfo = null;
    private rirekiInfo _gRirekiInfo = null;
    private kaikeiInfo _gKaikeiInfo = null;
    private moveInfo _gMoveInfo = null;

    private soundManager _gSoundManager = null;


    private threadWaitRevCmd _gthreadWaitRevCmd = null;
    private localStr _glocalStr = null;
    private logExtend _glog = null;
    private threadRevCmdManager _gthreadRevCmdManager = null;


    //flag
    private int _gTableDraw_Flag = 0;
    private int _gTableTag_Flag = 0;
    private int _gTableMain_Flag = 0;
    private int _gRirekiDraw_Flag = 0;
    //private int _gUpdateApp_Flag = 0;
    private int _gSETUP_Flag = 0;
    private int _gSETUP_COUNT = 0;

    //=========view & flag==========
    //高速レーンバック警告画面
    private int _gDrawKousokuBackFlag = 0;
    private View _gviewKousokuBack = null;
    private int _gviewKousokuBackOnTimer = 0;
    //かぎ異常警告画面
    private int _gDrawKeyAlertFlag = 0;
    private View _gviewKeyAlert = null;
    private int _gviewKeyAlertOnTimer = 0;

    //TOP画面
    private View _gviewTop = null;
    private int _gDrawTopFlag = 0;
    private int _gviewTopOnTimer = 0;

    //呼び出し画面
    private View _gviewCall = null;
    private int _gDrawCallFlag = 0;
    private int _gviewCallOnTimer = 0;

    //注文画面
    private View _gviewOrder = null;
    private int _gDrawOrderFlag = 0;
    private TextView _gOrderUnit = null;//
    private int _gDrawOrderUnitFlag = 0;
    private int _gOrderUnitCount = 1;
    private int _gviewOrderOnTimer = 0;

    private int _gORDER_CHECK_REFUSE_STATE = 0;//注文拒否の状態

    //注文画面self
    private View _gviewOrderSelf = null;
    private int _gDrawOrderSelfFlag = 0;
    // private int _gviewOrderSelfOnTimer = 0;

    //注文拒否メッセージ
    private View _gviewOrderRefuseMsg = null;
    private int _gDrawOrderRefuseMsgFlag = 0;
    private int _gviewOrderRefuseMsgOnTimer = 0;

    //注文拒否メッセージリミットページ
    private View _gviewOrderLmitMsg = null;
    private int _gDrawOrderLmitMsgFlag = 0;
    private int _gviewOrderLmitMsgOnTimer = 0;

    //会計画面
    private View _gviewKaikei = null;
    private int _gDrawKaikeiFlag = 0;

    //会計画面2 add 20180817
    private View _gviewKaikei2 = null;
    // private int _gDrawKaikei2Flag = 0;

    //自動会計画面 add 20200210
    private View _gviewSurvey = null;               // アンケート画面View
    private View _gviewAutoCheck = null;            // 自動会計画面View
    private int _gUSE_SURVEY_FLAG = 0;              // アンケート使用フラグ
    private int _gUSE_AUTO_CHECK_FLAG = 0;          // 自動会計使用フラグ
    private int _gSURVEY_DONE_FLAG = 0;             // アンケート回答済みフラグ
    private Date _gSeatingTime = null;              // アンケート、自動会計の開始時刻
    private String _gLangCode = null;               // アンケート、自動会計用言語コード
    private int _gDrawAutoCheckWebViewLoadFlag = 0; // アンケート、自動会計表示制御フラグ
    private int _gftpUpdateViewIndex = 0;           // アンケート、自動会計表示制御インデックス
    private String _gSurveyUrl = "192.168.11.5:11101";
    private String _gAutoCheckUrl = "192.168.11.5:11201";
    private int _gDrawSurveyFlag = 0;
    private WebView wv = null;
    private AsyncFileDownload asyncFileDownload;
    private boolean autoCheckIsInstalled = false;
    private boolean surveyIsInstalled = false;
    private int zipInstallCount = 0;//Tickerループ内でzipをインストールした回数
    private int zipInstallLimit = 3;//Tickerループ内で何回までzipインストールを試みるか

    //QRコード表示画面 add 20200701
    private View _gviewQrCode = null;
    private WebView qrWv = null;
    private int _gDrawQrCodeFlag = 0;
    private int _gQRCODE_DISP_FLAG = 0;
    private String _gQrCodeUrl = "192.168.11.5:12909";
    private int qrFromMain = 0;
    private boolean qrIsInstalled = false;

    //履歴画面
    private View _gviewRireki = null;
    private int _gDrawRirekiFlag = 0;
    //private int _gviewTestOnTimer = 0;
    private int _gTestCategoryCount = 0;
    private String _gTestCatStr = null;

    //test画面
    private View _gviewTest = null;
    private int _gDrawTestFlag = 0;
    private String _gWifiInfo = null;
    private int _gTestOpenCountButtonOn = 0;
    private int _gWifiTestOnFlag = 0;

    //データー更新画面
    private View _gviewUpdateAlert = null;
    private int _gDrawUpdateAlertFlag = 0;
    private int _gviewUpdateAlertOnTimer = 0;

    //動画用
    private View _gviewMov = null;
    private VideoView _gvideoView = null;
    //add 20181121
    private int _gPreparedMovFlag = 0;//動画再生準備ができたか

    //ｽｸﾘｰﾝｾｰﾊﾞｰ
    private int _gDrawMovFlag = 0;
    private int _gviewMovOnTimer = 0;

    //ゲーム
    private int _gDrawGameFlag = 0;
    private int _gviewGameOnTimer = 0;
    private int _gviewGamePuse = 0;
    private View _gviewMovForGame = null;//add 20160531 gameとｽｸﾘｰﾝｾｰﾊﾞｰで分ける
    private VideoView _gvideoViewForGame = null;//add 20160531 gameとｽｸﾘｰﾝｾｰﾊﾞｰで分ける
    //add 2016 1128 ゲーム中断処理用
    private int _gPousePosition = 0;

    //add 2017 0315 ゲームskip処理用
    private int _gsikpGameFlag = 0;

    //商品到着画面
    private View _garriveView = null;
    private int _gDrawArriveFlag = 0;
    private int _garriveViewOnTimer = 0;
    private TextView _gViewArriveText1 = null;
    private TextView _gViewArriveText2 = null;
    private TextView _gViewArriveText3 = null;
    private TextView _gViewArriveText4 = null;
    private TextView _gViewArriveUnitText1 = null;
    private TextView _gViewArriveUnitText2 = null;
    private TextView _gViewArriveUnitText3 = null;
    private TextView _gViewArriveUnitText4 = null;
    private ImageView _gViewArriveImage1 = null;
    private ImageView _gViewArriveImage2 = null;
    private ImageView _gViewArriveImage3 = null;
    private ImageView _gViewArriveImage4 = null;

    // TODO　add 20210817 原宿店屋台対応 確認画面で止められ間、上の到着が表示されない　下到着のviewを独立させる
    /*
    private FrameLayout _gViewArriveFoodStandLayout = null;
    private  Button _gViewArriverFoodStandBackBt = null;
    private  Button _gViewArriverFoodStandBackBtBack = null;
    private  TextView _gTextARRIVE_FOOD_STAND_ICE_MASK = null;
    */


    // TODO　add 20210817 原宿店屋台対応 確認画面で止められ間、上の到着が表示されない　下到着のviewを独立させる
    private View _garriveUnderView = null;
    private int _garriveUnderViewOnTimer = 0;
    private TextView _gViewArriveUnderText1 = null;
    private TextView _gViewArriveUnderText2 = null;
    private TextView _gViewArriveUnderText3 = null;
    private TextView _gViewArriveUnderText4 = null;
    private TextView _gViewArriveUnderUnitText1 = null;
    private TextView _gViewArriveUnderUnitText2 = null;
    private TextView _gViewArriveUnderUnitText3 = null;
    private TextView _gViewArriveUnderUnitText4 = null;
    private ImageView _gViewArriveUnderImage1 = null;
    private ImageView _gViewArriveUnderImage2 = null;
    private ImageView _gViewArriveUnderImage3 = null;
    private ImageView _gViewArriveUnderImage4 = null;
    private FrameLayout _gViewArriveUnderFoodStandLayout = null;
    private  Button _gViewArriverFoodUnderStandBackBt = null;
    private  Button _gViewArriverFoodUnderStandBackBtBack = null;
    private  TextView _gTextUnderARRIVE_FOOD_STAND_ICE_MASK = null;

    // TODO add 20211211 坂本MG表示変更要望のため暫定的に対応
    private int _garriveFoodStandDispCount = 0;

    // TODO　add 20210817 原宿店屋台対応 確認画面で止められ間、上の到着が表示されない　下到着のviewを独立させる





    // TODO marge ２皿対応
    // add 20210622 ２皿の場合の警告表示を使用するか
    private int _gDoublePlateAlret_USE = 0;
    // add 20210622 2皿商品が到着商品に存在するばあいは１にする
    private int _gDoublePlateAlretFlag = 0;
    // add 20210622 下記の時間になったら２皿警告画面を表示する
    private int _gDoublePlateAlretValue = 10;
    // TODO marge ２皿対応

    // TODO marge ２皿 firstOrder
    // add 20210622 2皿商品が到着商品を１回だけ表示させる場合に使用
    private int _gDoublePlateFist_USE = 0;
    private int _gDoublePlateFistOrderFlag = 0;
    //  TODO marge ２皿 firstOrder
    // TODO 20210703 表示方式を交互に変更
    private int _gDoublePlateOnCount = 10; // 商品到着画面を表示しているカウント
    private int _gDoublePlateOffCount = 6; // ２００円皿取得警告を表示しているカウント
    // TODO 20210703 表示方式を交互に変更


    //商品到着画面上
    //private View _garriveKousokuView = null;
    private int _gDrawArriveKousokuFlag = 0;
    private int _garriveViewKousokuWaitOnTimer = 0;
    private int _garriveViewKousokuOnTimer = 0;

    private View _gorderCheckView = null;
    private int _gDrawOrderCheckFlag = 0;
    private int _gorderCheckViewOnTimer = 0;

    private TextView _gViewMsgText = null;
    // private LinearLayout _gViewMsgLayout = null;


    //Num
    private View _gNumView = null;
    private int _gDrawNumFlag = 0;
    private int _gNumViewOnTimer = 0;
    private int _gNumViewVal = 0;

    //start
    private View _gstartView = null;
    private TextView _gtmTitle = null;
    private TextView _gtmMsg = null;

    //FTP
    private int _gDrawFtpUpdatetFlag = 0;
    private int _gviewFtpUpdatetOnTimer = 0;
    private String _gFfpCatStr = "";
    private View _gftpUpdateView = null;
    private TextView _gtvftpUpdateTitle = null;
    private TextView _gtvftpUpdateMsg = null;

    //=========setting==========
    private int _gTtableNum = 0;

    //=========ftp==========
    private int _gFtpVer[] = new int[6];
    //private String _gFtpCatStr;
    private int _gFtpMovSubVer = 0;


    //=========会計処理用==========
    private final int IO_RESET_STATE_ERR = 9;
    private final int IO_RESET_STATE_CLEAR = 0;//change 2014 0814
    private final int IO_RESET_STATE_WAIT = 2;
    private final int IO_RESET_STATE_CLEAR_CMD = 3;//change 2014 0814

    private int _g_IO_RESET_STATE_FLAG = 0;//キーリセットの状態
    private int _g_IO_RESET_STATE_CLEAR_CNT = 0;//あおいそ終了後のリセット通達をする間隔
    private int FLAG_IO_RESET_STATE_SARA_CLEAR = 0;
    private int FLAG_KAIKEI_ALERT_ON = 0;//アメリカ版で会計時に皿投入を促す画面を表示（未実装）
    private int _gKAIKEI_On_For_BackButton = 0;//会計ボタンの挙動制御用
    private int _gKAIKEI_For_BackButton_Count = 0;//会計ボタンを何回押したか

    //=========game==========
    //private final int GAME_WIN = 2;
    //private final int GAME_LOOSE = 1;
    private int _gCurrentGame;


    //add 2016 0624
    // private int _gCurrentGame_buf = 0;
    //add 2016 0624

    private int FLAG_GAME_TEST_ON = 0;

    private int _gGamePlayCount;
    private int _gGameSoundOn = 0;
    private int _gGamePlayTimeLength;

    //=========screen==========
    //private int _gScreenNotTouchCnt = 0;//タッチ操作をしていないカウント
    private int _gScreenOpenCntSetCnt = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰ起動開始時間
    private int _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰ起動までのカウント
    //private int _gCurrentScreen = 0;

    //描画更新確認用バッファ
    private int _gSaraCnt_Drawbuf = 0;
    private int _gWaitTime_Drawbuf = 0;

    //永続スレッド管理用
    private Thread _gTHREAD_UdpRegularConnection = null;
    private Thread _gTHREAD_RevCmd = null;
    private Thread _gTHREAD_SendCmd = null;
    private Thread _gTHREAD_FtpClient = null;
    private Thread _gTHREAD_WaitRevCmd = null;

    //add 20181215 soundをスレッド化する
    private Thread _gTHREAD_SoundManager = null;

    private Thread _gTHREAD_RevCmdManager = null;


    //UDP コマンド用
    private static int _gopt1_return = 0;
    private static int _gopt2_return = 0;
    private static int _gopt3_return = 0;
    private static int _gcmd_return = 0;
    private static int _gcmd = 0;
    private static int _gopt1 = 0;
    private static int _gopt2 = 0;
    private static int _gopt3 = 0;
    private static int _gpre_cmd;//前回のコマンド
    private static int _gpre_cmd_count;

    //後付セッティング  初期値有
    // private int _gOPT_OPT_TYPE=0;//選択した商品のオプションタイプ
    private int _gOPT_OPT_CODE = 0;//選択した商品のオプションコード
    private int _gOPT_SELECTED_OPT_CODE = 0;//選択したオプション
    //20180103
    private int _gOPT_OPT_CODE_1 = 0;
    private int _gOPT_OPT_CODE_2 = 0;
    private int _gOPT_OPT_FLAG_1 = 0;
    private int _gOPT_OPT_FLAG_2 = 0;

    private optInfo _goptInfo = null;
    // private int _gUSE_SELECT_OPT_FLAG = 0;
    private int _gUSE_SELECT_OPT_FLAG = 1;//change default 20180220
    private int _gkaikei_alert_mode = 0;
    private int _gReserv_mode_mode = 0;
    //private int _gIo_use_mode = 0;//多言語化に対応するか
    private int _gIo_use_mode = 1;//change default 20180220
    private int _gkousoku_arraive_wait = 6;
    private int _gkousoku_arraive_show_count = 16;
    private int _gTouchState_Start0n = 0;
    private int _gTouchState_Custom_1 = 0;
    private int _gTouchState_Custom_2 = 0;
    //for debug
    private int _gLogLevel = 2;
    private int _gLogErrLevel = 2;

    //add 20160705 For 2.4G
    private int _g24WIFI_USE = 0;
    //private int _gFORCERBOOT = 0;


    //for ファイルGetver 20160523
    private int _gVer_GetFile_LocalStr;
    private int _gVer_GetFile_LocalNetaName;
    private int _gVer_GetFile_KaikeiCat;
    private int _gVer_GetFile_LocalStr_Buf;
    private int _gVer_GetFile_LocalNetaName_Buf;
    private int _gVer_GetFile_KaikeiCat_Buf;

    //add 20161021
    private int _get_gORDER_CHECK_WAIT_TIME = 0;//注文のリターンが返ってくる時間


    //add 20180108 netimage　ボタン直後のページ遷移操作抑制
    private int _gNETAIMG_BOTTON_ON_COUNT;//
    // private final int NETAIMG_BOTTON_ON_COUNT_VAL = 5;
    //add 20161026 次へページの後一部の作業を制限
    //高浜店でのノイズ対策
    private int _gPAGE_CHANGE_BOTTON_ON_COUNT;//
    //  private final int PAGE_CHANGE_BOTTON_ON_COUNT_VAL = 5;
    //高浜店でのノイズ対策
    private int _gORDER_VIEW_BOTTON_ON_COUNT;//
    //  private final int ORDER_VIEW_BOTTON_ON_COUNT_VAL = 4;

    // add 20200822 ver320 ボタンのチャタ対策カウントを可変

    /*
    private final int TOUCH_TIME_THRESHOLD = 10;
    private final int TOUCH_TIME_THRESHOLD_IMG = 10;
    private final int TOUCH_TIME_THRESHOLD_CALL = 10;
    private final int TOUCH_TIME_THRESHOLD_KAIKEI = 10;
    private final int TOUCH_TIME_THRESHOLD_RIREKI = 10;
    */
    //add 20200718  短いタッチは処理せずに捨てる。
    private int gTOUCH_TIME_THRESHOLD_CNT; //短いタッチは処理せずに捨てるsetting
    private int TOUCH_TIME_THRESHOLD = 10;
    private int TOUCH_TIME_THRESHOLD_IMG = 10;
    private int TOUCH_TIME_THRESHOLD_CALL = 10;
    private int TOUCH_TIME_THRESHOLD_KAIKEI = 10;
    private int TOUCH_TIME_THRESHOLD_RIREKI = 10;


    private int _gCALLBACK_BOTTON_ON_COUNT;//呼び出し画面でバック
    private final int CALLBACK_BOTTON_ON_COUNT_VAL = 5;

    //add 20161031 鍵警告をしないフラグ。（起動時に設定。鍵リセットで有効にする。）
    private int _gKEY_ALERT_USE_FLAG = 0;

    //データー更新の連続が疑われるが、tiker 側でもチェックを入れる。
    private int _gVIEW_DATA_UPDATE_ON_COUNT_CHECK = 0;

    // 通信抑制20161108
    private int _gUDP_CONNECT_LIMMIT_FLAG = 0;
    private int _gDRAW_LIMMIT_FLAG = 0;


    //20171025 案内時に画面を光らせる
    private View _gviewFlashing = null;
    private int _gDrawFlashingFlag = 0;
    private int _gviewFlashingOnTimer = 0;
    private int _gUSE_FLASHING_FLAG = 0;

    //20171117 大ネタ商品対応 ->  20190529大ネタ商品RE対応

    private int _gUSE_BIGNETA_USE_FLAG = 0;
    private ImageButton IB_BIG = null;
    private TextView TNAME_BIG = null;
    private FrameLayout _gBIG_FRAME = null;

    //20190529 大ネタ対象
    private int _gUSE_BIGNETA_PAGE_FLAGS[] = new int[20];

    //20171117 大ネタ商品対応

    //20180610 add FLAG
    // private int _gUSE_SINAGIRE_RECOMMEND_ON_FLAG = 0; //品切れのレコメンド機能
    private int _gUSE_ORDER_DEVID_ON_FLAG = 0; //order分轄

    //分割オーダー用のバッファ 20180610
    private int _gDevidBuf_Count;//何分轄するか
    private int _gDevidBuf_netaID;
    private int _gDevidBuf_UnitCount;
    private int _gDevidBuf_SELECTED_OPT_CODE;

    //分割オーダー用のバッファ 20180610
    //add20180612 reccomend画面
    private View _gviewReccomendWeb = null;
    private int _gDrawReccomendWebFlag = 0;
    private int _gviewReccomendWebOnTimer = 0;
    private int _gRecommendKey = 0;
    //add20180622 log用の店舗コード
    private int _gMiseCode;
    private threadPutLogJson _gthreadPutLogJson;
    private Thread _gTHREAD_PUTLOGJSHON = null;


    private int _gUSE_AUTO_KAIKEI_FLAG = 0;
    //private int _gTatalSara = 0;//客席で取得した皿
    private int _gSeatGetSara = 0;//客席で取得した皿

    //20180901 操作案内画面
    private int _gUSE_SousaANNAI_FLAG = 0;
    private View _gviewSousaAnnai = null;
    private int _gDrawSousaAnnaiFlag = 0;
    private int _gviewSousaAnnaiOnTimer = 100;

    private WebView _gSousaAnnaiWeb = null;

    //add 20181027
    private WebView _gReccommendWeb = null;
    //add 20181029
    private int _gSousaAnnaiCat = 0;

    //20181128 ゲーム数保存
    private int _gUSE_GAME_END_SAVE = 1;

    //add 20181115 meminfo をデバック用に監視
    //private int _gUSE_DebugMemInfo;

    //20200110 特殊gameMode ON
    private int _gUSE_GAME_WITH_ARRAY_FLAG = 0;
    private int _gGAME_WITH_ARRAY[];

    //add 20200202 バッテリー監視
    private int _gUSE_BATTEYR_CHECK_FLAG = 0;
    // low battery
    private View _gviewLowBatteryAlert = null;
    private WebView _gLowBatteryAlertWeb = null;
    private int _gDrawLowBatteryAlertFlag = 0;
    private int _gviewLowBatteryAlertOnTimer = 0;
    //add20200204 一度起動したら一定時間開かないように抑制する
    private int _gviewLowBatteryAlertOffSetTimer = 0;
    private int _gviewLowBatteryAlertTHRESHOLDLevel = 50;


    // 202020200515 first free order
    // TODO 20211208 不要処理コメントアウト
    // private int _gUSE_FirstFreeOrder_FLAG = 0;
   // private View _gviewFirstFreeOrder = null;

    /*  TODO 20211208 不要処理コメントアウト
    private int _gDrawFirstFreeOrderFlag = 0;
    private int _gviewFirstFreeOrderOnTimer = 100;
    private int _gFirstFreeOrderCheckTimer = 10;
    private int _gFirstFreeOrder_itemCode = 0;// first free orderの商品Code
    private int _gFirstFreeOrderEndFlag = 0; // first free orderが終了したか
    private int _gFirstFreeOrderRetrun = 0; // first free orderの成否　カギリセットで解除
    private boolean _gFirstFreeOrderNoConfirm = false; // noの場合にもう１回確認
    */

    // 高速レーンバック到着
    private View _gviewOrderArriveAlert = null;
    private int _gDrawOrderArriveAlertFlag = 0;
    private int _gOrderArriveAlertOnTimer = 0;

    // 20200608 Opning MOVIE
    private int _gUSE_OpenningMove_FLAG = 0;
    private View _gviewOpenningMove = null;
    private int _gDrawOpenningMoveFlag = 0;
    private int _gOpenningMoveNumber = 0;
    private int _gOpenningMoveOnTimer = 0;
    private VideoView _gvideoViewOpenningMove = null;

    // 20200608
    private int _gApiVersion = 0;


    // add 20200830 ver322 ntt版アプリでhttpのコンテンツのバージョン管理用
    private int _gHttpQrVer = 0;
    private int _gHttpAutoCheckVer = 0;
    private int _gHttpSurveyVer = 0;

    // TODO　20220108 ver 406 のマージ
    private int _gHttpSpCheckVer = 0;

    // ver314 20201224 order second request
    // _gReserv_mode_mode
    private int _gUSE_SECOND_REQUEST = 1;
    private int _g_SECOND_REQUEST_ON = 0;
    private httpRequestHandle _ghttpRequestHandle = null;

    //add 20210222 ver335 自動会計、QRコードでのエラー時に表示する呼び出し画面の描画を管理
    private int _gDrawWvCallFlag = 0;

    // TODO　add 20210817 原宿店屋台対応
    private int _gUse_FoodStand = 0; // 屋台対応機能を有効 for debug
    private int _gFoodStandOnFlag = 0; // 屋台対応機能ON
    private int _gFoodStandAlertTimer = 300; // 屋台対応画面ONタイマー加算分:

    // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
    private int _gFoodStandTypeIceOnFlag = 0;
    private int _gFoodStandTypeIceOnCount = 0;

    // TODO 20211211 後方互換性tag数を確認して７～９で確認する。（7以下は7表示とする。）
    private int _gCuttentTagCount = 9;

    // TODO　20220108 ver 406 のマージ
    private View _gviewSpCheck = null;
    private WebView spCheckWv = null;
    private boolean spIsInstalled = false;
    private int _gUSE_SMARTPHONE_CHECK_FLAG = 0;
    private int F_SPCHECK_STATUS_NOT_CHECKING = 0;
    private int F_SPCHECK_STATUS_CHECKING = 1;
    private int F_SPCHECK_STATUS_CALLING = 2;
    private int spCheckStatus = F_SPCHECK_STATUS_NOT_CHECKING;
    // TODO　20220108 ver 406 のマージ

    // TODO　20220207 押上店向け、１F、２Fでコンテンツを分ける処理
    private int _gUSE_BIKKURAGYO_FLAG = 0;

    // TODO 20220305 #34 押上店向けー会計画面の前にQR読み込みの警告画面を出す
    private View _gviewDxGameKaikeiAlert = null;
    private int _gDrawDxGameKaikeiAlertFlag = 0;
    private int _gDxGameKaikeiAlertOnTimer = 0;
    private int _gDxGameKaikeiAlertCheckFlag = 0; // 会計チェック画面でチェック済か
    // TODO 20220305 #34 押上店向けー会計画面の前にQR読み込みの警告画面を出す

    // TODO 20220323 #39 当たり数の確認
    // private saveGameInfo _gSaveGameInfo = null;

    /**
     * 音量の取得と変更
     */
    public int get_soundVal() {
        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        // 現在の音量を取得する
        int _Volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        // _glog.log("get STREAM_MUSIC soundVal called" + _Volume);
        return _Volume;
    }

    public int set_soundVal(int val) {
        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        int _val = 15;
        switch (val) {
            // 現在の音量を取得する
            case 0:
                _val = 15;
                break;
            case 1:
                _val = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                break;
        }

        //add 20191211 onkyoで音が大きするという事例があったため、可変にしてみる
        if (val > 5 && val < 15) {
            _val = val;
        }

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, _val, 0);
        int _Volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        // _glog.log("get STREAM_MUSIC soundVal called val:" + _val);
        return _Volume;
    }


    public void setIo_use_mode(int val) {
        if (val == 0 || val == 1 || val == 2) {
            _gIo_use_mode = val;
        }
    }

    /* UP:20200515
     * FirstFreeOrder
     */
    /*  TODO 20211208 不要処理コメントアウト
    private void addViewFirstFreeOrder() {
        _glog.log("addFirstFreeOrder called");
        _gviewFirstFreeOrder = this.getLayoutInflater().inflate(R.layout.view_first_free_order, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewFirstFreeOrder);

        TextView tv = (TextView) findViewById(R.id.txFirstFreeOrderReturnMsg);
        tv.setVisibility(View.INVISIBLE);

        tv = (TextView) findViewById(R.id.txFirstFreeOrder_Confrim);
        tv.setVisibility(View.INVISIBLE);

        tv = (TextView) findViewById(R.id.txFirstFreeOrder_Message);
        tv.setVisibility(View.VISIBLE);

        FrameLayout fl = (FrameLayout) findViewById(R.id.frameFirstOrderDialog);
        fl.setBackgroundColor(Color.argb(0, 255, 255, 255));
        //todo 外国語対応
    }
   */


     /*  TODO 20211208 不要処理コメントアウト
    private void addViewFirstFreeOrderDraw() {
        // add20200601 本当にいらないか再確認する

        if (_gFirstFreeOrderNoConfirm == true) {
            FrameLayout fl = (FrameLayout) findViewById(R.id.frameFirstOrderDialog);
            fl.setBackgroundColor(Color.BLACK);

            TextView tv = (TextView) findViewById(R.id.txFirstFreeOrder_Confrim);
            tv.setVisibility(View.VISIBLE);

            tv = (TextView) findViewById(R.id.txFirstFreeOrder_Message);
            tv.setVisibility(View.INVISIBLE);
        }

        if (_gFirstFreeOrderEndFlag == 1) {
            TextView tv = (TextView) findViewById(R.id.txFirstFreeOrderReturnMsg);
            tv.setVisibility(View.VISIBLE);
            tv.setText("注文されたガリは" + BR + "上のレーンでお届けいたします。" + BR + "しばらくお待ち下さい。");
        }
        if (_gFirstFreeOrderRetrun == -1) {
            TextView tv = (TextView) findViewById(R.id.txFirstFreeOrderReturnMsg);
            tv.setVisibility(View.VISIBLE);
            tv.setText("ただいま注文が混み合っております。" + BR + "店員が参りますのでしばらくお待ち下さい。");
        }
    }
      */


    /* UP:20200203
     * 案内時に操作説明を流す。
     */
    private void addLowBatteryAlert() {

        _glog.log("addLowBatteryAlert PEN");
        _gviewLowBatteryAlert = this.getLayoutInflater().inflate(R.layout.view_alert, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewLowBatteryAlert);
        TextView tv = (TextView) findViewById(R.id.tvCALL_MSG);
        //tv.setText(_glocalStr.LoStr("V8_LB_HELP"));
        tv.setText("充電が少なくなっています。充電ドックに戻してご使用願います");
        tv.setBackgroundColor(Color.YELLOW);
        Button bt = (Button) findViewById(R.id.btCALL_BACK);
        bt.setText(_glocalStr.LoStr("V7_BT_BACK"));


        /* 2020 0204 expressの調査するが終わったらこっち使う
        String url="";
        url="http://192.168.11.1:5181/low-battery-alert.html";
        //url="http://192.168.11.1:5181/index.html";
        StringBuffer sf = new StringBuffer(url);
        sf.append("?table=");
        sf.append(_gTtableNum);
        sf.append("&inc=");
        sf.append(_gTimerCount);
        sf.append("&date=");
        sf.append(_gSettingInfo.GetOrderTimeStamp());
        sf.append("&batterylevel=");
        sf.append(_gBatteryLevel);
        _glog.log("addLowBatteryAlert called url:" + url);
        _gviewLowBatteryAlert = this.getLayoutInflater().inflate(R.layout.view_sousa_annai, (ViewGroup)null);
        _gMAIN_FRAME.addView(_gviewLowBatteryAlert);
        _gLowBatteryAlertWeb= (WebView) findViewById(R.id.SousaAnnaiWeb);
        //JavaScriptを有効にする
        _gLowBatteryAlertWeb.getSettings().setJavaScriptEnabled(true);
        _gLowBatteryAlertWeb.loadUrl(sf.toString());
        _gLowBatteryAlertWeb.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                _glog.log("onPageStarted called: url:"+url);
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:onReceivedError,PROC:addLowBatteryAlert");
                //操作案内画面をCLOSE
                _gDrawLowBatteryAlertFlag = F_VIEW_STATE_CLOSE_CMD;
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });*/
    }

    /* UP:20200201
     * 案内時に操作説明を流す。
     */
    private void addViewSousaAnnai() {
        // _glog.log("addViewSousaAnnai called:"+_gSousaAnnaiCat);
        String url = "";

        url = "http://192.168.11.1:11001/SousaAnnai/annai1.html";
        //url="http://192.168.11.1:5181/index.html";
        //ボタン操作の場合は０にしているため、ここで設定値に戻す。
        _gSousaAnnaiCat = _gPreferencesSave.getSOUSA_ANNAI_CAT();
        StringBuffer sf = new StringBuffer(url);
        sf.append("?table=");
        sf.append(_gTtableNum);
        sf.append("&inc=");
        sf.append(_gTimerCount);
        sf.append("&date=");
        sf.append(_gSettingInfo.GetOrderTimeStamp());

        //20190529 htmlの処理に依存してる。汎用性を持てるように修正したい。urlの設定を変更できるようにとか？
        switch (_gSousaAnnaiCat) {
            case 1:
                // url="http://192.168.11.1:11001/1/test.html";
                break;
            case 2:
                // url="http://192.168.11.1:11001/2/test.html";
                sf.append("#popup100");
                break;
            case 3:
                //  url="http://192.168.11.1:11001/3/test.html";
                sf.append("#popup111");
                break;
            default:
                // url="http://192.168.11.1:11001/1/test.html";
                break;
        }


        _gviewSousaAnnai = this.getLayoutInflater().inflate(R.layout.view_sousa_annai, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewSousaAnnai);

        // add 20200606 説明を見せるため最初は非表示とする
        /*
        Button bt = (Button) findViewById(R.id.bt_SousaAnnai_back);
        bt.setVisibility(View.INVISIBLE);`
        */

        //sf.append("#jquery");
        _gSousaAnnaiWeb = (WebView) findViewById(R.id.SousaAnnaiWeb);


        //change 20191209
        //JavaScriptを有効にする
        _gSousaAnnaiWeb.getSettings().setJavaScriptEnabled(true);

        //add 20181026
        //_gSousaAnnaiWeb.getSettings().setSaveFormData(false);

        //add 20181026
        _gSousaAnnaiWeb.loadUrl(sf.toString());

        //change 20191209 eventをoverride
        _gSousaAnnaiWeb.setWebViewClient(new WebViewClient() {

            /*public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //return true load with system-default-browser or other browsers, false with your webView
                _glog.log("shouldOverrideUrlLoading called: url:"+url);
                return false;
            }
           public void onLoadResource(WebView view, String url) {
               //_glog.log("onLoadResource called: url:"+url);
               super.onLoadResource(view, url);
           }*/

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                _glog.log("onPageStarted called: url:" + url);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //_glog.log("onReceivedError called:"+description + " url:"+failingUrl);
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:onReceivedError,PROC:addViewSousaAnnai");
                //操作案内画面をCLOSE
                _gDrawSousaAnnaiFlag = F_VIEW_STATE_CLOSE_CMD;
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }

    private void addViewSousaAnnaiDraw() {
        // add 20200606 5秒後からバック画面を
         /*
        if (_gviewSousaAnnaiOnTimer <= 90) {
            Button bt = (Button) findViewById(R.id.bt_SousaAnnai_back);
            bt.setVisibility(View.VISIBLE);
        }
        */
    }

    /**
     * HELP画面
     * 20200109 game skip機能に限定的する
     */
    private void addViewHELP() {
        _glog.log("addViewHELP() called");
        _gviewHELP = this.getLayoutInflater().inflate(R.layout.view_help, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewHELP);
        _gtxHELP_Message = (TextView) findViewById(R.id.txHELP_Message);
        _gtxHELP_Message.setText("");

        // _gtxHELP_Message.setText("いらっしゃいませ。当店のご利用は初めてですか？");
        Button bt = (Button) findViewById(R.id.btHELP_Case1);
        bt.setVisibility(View.INVISIBLE);
        bt = (Button) findViewById(R.id.btHELP_Case2);
        bt.setVisibility(View.INVISIBLE);
        bt = (Button) findViewById(R.id.btHELP_Case3);
        bt.setVisibility(View.INVISIBLE);
        bt = (Button) findViewById(R.id.btHELP_BACK);
        bt.setVisibility(View.INVISIBLE);
        bt = (Button) findViewById(R.id.btHELP_Ok);
        bt.setVisibility(View.VISIBLE);
        bt.setText("");
        bt = (Button) findViewById(R.id.btHELP_NO);
        bt.setVisibility(View.VISIBLE);
        bt.setText("");

        //topボタンをしようしない場合。
        _glog.log("_gIo_use_mode:" + _gIo_use_mode);
        if (_gIo_use_mode == 0) {
            bt = (Button) findViewById(R.id.btHELP_Lange);
            bt.setVisibility(View.INVISIBLE);
            bt = (Button) findViewById(R.id.btHELP_Lange_sub);
            bt.setVisibility(View.INVISIBLE);
        } else {
            bt = (Button) findViewById(R.id.btHELP_Lange);
            bt.setVisibility(View.VISIBLE);
            bt = (Button) findViewById(R.id.btHELP_Lange_sub);
            bt.setVisibility(View.VISIBLE);
        }


        //add 20190619 ビックラポン選択時に音声を追加
        if (HELP_STEP_GAME_SKIP_CHECK == _gHELP_STEP) {
            _gSoundManager.PlaySound(14, 1);//音声要確認
        }

    }

    //===========================================
    //
    //===========================================
    private void addViewHELPDraw() {

        // _glog.log( "addViewHELPDraw() called");
        Button btOk = (Button) findViewById(R.id.btHELP_Ok);
        btOk.setVisibility(View.VISIBLE);


        Button btNo = (Button) findViewById(R.id.btHELP_NO);
        btNo.setVisibility(View.VISIBLE);

        Button btLang = (Button) findViewById(R.id.btHELP_Lange);
        //btLang.setVisibility(View.VISIBLE);

        switch (_gHELP_STEP) {
            case HELP_STEP_SENDKUN_MOV:
                break;
            case HELP_STEP_GAME_SKIP_CHECK:

                //add 20190619 add sound

                if (_gSKIP_GAME_USE == 0) {//スキップ設定なし
                    btOk.setText(_glocalStr.LoStr("GAMESKIP_BUTTON_ON"));
                    btNo.setText(_glocalStr.LoStr("GAMESKIP_BUTTON_OFF"));
                    _gtxHELP_Message.setText(_glocalStr.LoStr("GAMESKIP_IN"));
                } else {//スキップ設定中
                    _gtxHELP_Message.setText(_glocalStr.LoStr("GAMESKIP_REIN"));
                    btOk.setText(_glocalStr.LoStr("GAMESKIP_BUTTON_ON"));
                    btNo.setText(_glocalStr.LoStr("GAMESKIP_BUTTON_OFF"));

                }
                break;
            case HELP_STEP_GAME_SKIP_ON:
                _gtxHELP_Message.setText(_glocalStr.LoStr("GAMESKIP_ON"));
                btOk.setVisibility(View.INVISIBLE);
                btNo.setVisibility(View.INVISIBLE);
                btLang.setVisibility(View.INVISIBLE);
                _gviewHELPOnTimer = 8;
                _gHELP_STEP = HELP_STEP_CLOSE_WAIT;
                break;
            case HELP_STEP_GAME_SKIP_OFF:
                _gtxHELP_Message.setText(_glocalStr.LoStr("GAMESKIP_OFF"));
                btOk.setVisibility(View.INVISIBLE);
                btNo.setVisibility(View.INVISIBLE);
                btLang.setVisibility(View.INVISIBLE);

                _gviewHELPOnTimer = 8;
                _gHELP_STEP = HELP_STEP_CLOSE_WAIT;
                break;
            case HELP_STEP_CLOSE_WAIT:

                btOk.setVisibility(View.INVISIBLE);
                btNo.setVisibility(View.INVISIBLE);
                btLang.setVisibility(View.INVISIBLE);

                if (_gviewHELPOnTimer < 3) {
                       /* _gtxHELP_Message.setText("ごゆっくり" +
                                System.getProperty ("line.separator")+
                                "お楽しみください。");*/
                }
                break;

        }

    }

    /**
     * FTP更新中画面
     */
    private void addViewFtpUpdate() {
        _gftpUpdateView = this.getLayoutInflater().inflate(R.layout.view_start, (ViewGroup) null);
        _gDrawTopFlag = F_VIEW_STATE_CLOSE_CMD;
        _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
        _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
        _gMAIN_FRAME.addView(_gftpUpdateView);
        _gtvftpUpdateTitle = (TextView) findViewById(R.id.tvStartTitle);
        _gtvftpUpdateMsg = (TextView) findViewById(R.id.tvSartMsg);
    }

    private void addViewFtpUpdateDraw() {
        _gtvftpUpdateTitle.setText("update_" + _gFfpCatStr);
        _gtvftpUpdateMsg.setText(_gthreadFtpClient.getFtpProsessStr());
    }

    /**
     * 数字入力画面1
     */
    private void addViewNum() {
        _gNumView = this.getLayoutInflater().inflate(R.layout.view_num, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gNumView);
        _gbtNumStr = (Button) findViewById(R.id.btNumStr);
        _gNumViewVal = 0;
    }

    private void CalViewNum(int num) {
        int x = 0;
        x = _gNumViewVal * 10 + num;
        if (x > 0 && x < 61) {
            _gNumViewVal = x;
        }
    }

    /**
     * データー更新アラート画面
     */
    private void addViewUpdateAlert() {
        //String f_dir = getApplicationContext().getFilesDir().toString();
        //_gviewUpdateAlert = this.getLayoutInflater().inflate(R.layout.view_msg, (ViewGroup)null);
        //Modify NDK 201912 Layout Change
        _gviewUpdateAlert = this.getLayoutInflater().inflate(R.layout.view_msg_update, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewUpdateAlert);
        _gViewMsgText = (TextView) findViewById(R.id.MSG_TEXT);
        //_gViewMsgLayout = (LinearLayout) findViewById(R.id.ID_VIEW_MSG);
        _gViewMsgText.setText(_glocalStr.LoStr("V8_LB_UPDATE"));
    }

    /**
     * 商品到着 下画面用
     */

    // TODO　add 20210817 原宿店屋台対応 確認画面で止められ間、上の到着が表示されない　下到着のviewを独立させる
     private void addViewUnderArrive() {
         _garriveUnderView = this.getLayoutInflater().inflate(R.layout.view_arrive_under, (ViewGroup) null);
         _gMAIN_FRAME.addView(_garriveUnderView);

         _gViewArriveUnderText1 = (TextView) findViewById(R.id.ARRIVE_NAME1_UNDER);
         _gViewArriveUnderText2 = (TextView) findViewById(R.id.ARRIVE_NAME2_UNDER);
         _gViewArriveUnderText3 = (TextView) findViewById(R.id.ARRIVE_NAME3_UNDER);
         _gViewArriveUnderText4 = (TextView) findViewById(R.id.ARRIVE_NAME4_UNDER);
         _gViewArriveUnderUnitText1 = (TextView) findViewById(R.id.ARRIVE_UNIT1_UNDER);
         _gViewArriveUnderUnitText2 = (TextView) findViewById(R.id.ARRIVE_UNIT2_UNDER);
         _gViewArriveUnderUnitText3 = (TextView) findViewById(R.id.ARRIVE_UNIT3_UNDER);
         _gViewArriveUnderUnitText4 = (TextView) findViewById(R.id.ARRIVE_UNIT4_UNDER);
         _gViewArriveUnderImage1 = (ImageView) findViewById(R.id.ARRIVE_IMG1_UNDER);
         _gViewArriveUnderImage2 = (ImageView) findViewById(R.id.ARRIVE_IMG2_UNDER);
         _gViewArriveUnderImage3 = (ImageView) findViewById(R.id.ARRIVE_IMG3_UNDER);
         _gViewArriveUnderImage4 = (ImageView) findViewById(R.id.ARRIVE_IMG4_UNDER);

         _gViewArriveUnderFoodStandLayout = (FrameLayout) findViewById(R.id.ARRIVE_FOOD_STAND_LAYOUT_UNDER);
         _gViewArriveUnderFoodStandLayout.setVisibility(View.GONE);
         _gViewArriverFoodUnderStandBackBt = (Button) findViewById(R.id.btArrive_BACK_btback_UNDER);
         _gViewArriverFoodUnderStandBackBt.setVisibility(View.INVISIBLE);
         _gViewArriverFoodUnderStandBackBtBack = (Button) findViewById(R.id.btArrive_BACK_UNDER);
         _gViewArriverFoodUnderStandBackBtBack.setVisibility(View.INVISIBLE);
         _gTextUnderARRIVE_FOOD_STAND_ICE_MASK = (TextView) findViewById(R.id.ARRIVE_FOOD_STAND_ICE_MASK_UNDER);
         _gTextUnderARRIVE_FOOD_STAND_ICE_MASK.setVisibility(View.VISIBLE);
     }
    // TODO　add 20210817 原宿店屋台対応 確認画面で止められ間、上の到着が表示されない　下到着のviewを独立させる


    private void addViewArrive() {
        _garriveView = this.getLayoutInflater().inflate(R.layout.view_arrive, (ViewGroup) null);
        _gMAIN_FRAME.addView(_garriveView);

        _gViewArriveText1 = (TextView) findViewById(R.id.ARRIVE_NAME1);
        _gViewArriveText2 = (TextView) findViewById(R.id.ARRIVE_NAME2);
        _gViewArriveText3 = (TextView) findViewById(R.id.ARRIVE_NAME3);
        _gViewArriveText4 = (TextView) findViewById(R.id.ARRIVE_NAME4);
        _gViewArriveUnitText1 = (TextView) findViewById(R.id.ARRIVE_UNIT1);
        _gViewArriveUnitText2 = (TextView) findViewById(R.id.ARRIVE_UNIT2);
        _gViewArriveUnitText3 = (TextView) findViewById(R.id.ARRIVE_UNIT3);
        _gViewArriveUnitText4 = (TextView) findViewById(R.id.ARRIVE_UNIT4);
        _gViewArriveImage1 = (ImageView) findViewById(R.id.ARRIVE_IMG1);
        _gViewArriveImage2 = (ImageView) findViewById(R.id.ARRIVE_IMG2);
        _gViewArriveImage3 = (ImageView) findViewById(R.id.ARRIVE_IMG3);
        _gViewArriveImage4 = (ImageView) findViewById(R.id.ARRIVE_IMG4);

        // TODO　add 20210817 原宿店屋台対応 屋台対応でしか使わないのでデフォルトは非表示
        /*
        _gViewArriveFoodStandLayout = (FrameLayout) findViewById(R.id.ARRIVE_FOOD_STAND_LAYOUT);
        _gViewArriveFoodStandLayout.setVisibility(View.GONE);
        _gViewArriverFoodStandBackBt = (Button) findViewById(R.id.btArrive_BACK_btback);
        _gViewArriverFoodStandBackBt.setVisibility(View.INVISIBLE);
        _gViewArriverFoodStandBackBtBack = (Button) findViewById(R.id.btArrive_BACK);
        _gViewArriverFoodStandBackBtBack.setVisibility(View.INVISIBLE);
        _gTextARRIVE_FOOD_STAND_ICE_MASK = (TextView) findViewById(R.id.ARRIVE_FOOD_STAND_ICE_MASK);
        _gTextARRIVE_FOOD_STAND_ICE_MASK.setVisibility(View.VISIBLE);
        */
    }

    /**
     * 商品到着下の場合のdraw
     *　2021 1119 高速レーンの場合の処理と分離させるため、viewを下で流した場合専用に用意
     */
    private void ViewArriveUnderDraw() {
        int netaId = 0;
        int x = 0;

        // TODO 20210914 下表示の際に画面がかくれてしまう 必要か要確認
        TextView tv2 = (TextView) findViewById(R.id.ARRIVE_DOUBLEPLATE_TEXT_UNDER);
        ImageView img2 = (ImageView) findViewById(R.id.ARRIVE_DOUBLEIMG_UNDER);
        tv2.setVisibility(View.GONE);
        img2.setVisibility(View.GONE);
        /*
        img2 = (ImageView) findViewById(R.id.imgArrive);
        img2.setVisibility(View.GONE);
        */
        // TODO 20210914 下表示の際に画面がかくれてしまう　必要か要確認


        // TODO　add 20210817 原宿店屋台対応
        int foodStandcheck = 0;
        // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
        int foodStanditmeType = 0;

        TextView tv = (TextView) findViewById(R.id.ARRIVE_TITLE_UNDER);
        tv.setText(_glocalStr.LoStr("V2_LB_TITLE_UNDER"));
        _gViewArriveUnderText1.setText("");
        _gViewArriveUnderText2.setText("");
        _gViewArriveUnderText3.setText("");
        _gViewArriveUnderText4.setText("");
        _gViewArriveUnderUnitText1.setText("");
        _gViewArriveUnderUnitText2.setText("");
        _gViewArriveUnderUnitText3.setText("");
        _gViewArriveUnderUnitText4.setText("");

        netaId = _gOrderUnderInfo.getCurretNetaId(0);
        if (netaId > 0) {
            x = _gNetaInfo.serchID(netaId);

            // TODO　add 20210817 原宿店屋台対応
            foodStandcheck = _gNetaInfo.getFoodStand(x);
            if(foodStandcheck > 0) {
                _gOrderUnderInfo.setFoodStanddispCount(0, _gFoodStandAlertTimer);

                // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
                foodStanditmeType = foodStanditmeType + _gNetaInfo.getFoodStandItemType(x);
            }
            // _glog.log("1 key:" + x +" foodStandcheck:" + foodStandcheck);

            if (x >= 0) {
                _gViewArriveUnderText1.setText(_gNetaInfo.getName(x));
                _gViewArriveUnderUnitText1.setText(_gOrderUnderInfo.getCurretSara(0) + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(x)));
                _gViewArriveUnderImage1.setVisibility(View.VISIBLE);

                //20161201 ネタ画像解放処理を入れているため、画像を読み直す必要がある。
                _gNetaInfo.setSingleImg(x);

                _gViewArriveUnderImage1.setImageBitmap(_gNetaInfo.getBmp(x));
            }
        } else {
            _gViewArriveUnderText1.setText("");
            _gViewArriveUnderUnitText1.setText("");
            _gViewArriveUnderImage1.setVisibility(View.INVISIBLE);
        }

        netaId = _gOrderUnderInfo.getCurretNetaId(1);
        if (netaId > 0) {
            x = _gNetaInfo.serchID(netaId);

            // TODO　add 20210817 原宿店屋台対応
            foodStandcheck = foodStandcheck + _gNetaInfo.getFoodStand(x);
            if(foodStandcheck > 0) {
                _gOrderUnderInfo.setFoodStanddispCount(1, _gFoodStandAlertTimer);

                // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
                foodStanditmeType = foodStanditmeType + _gNetaInfo.getFoodStandItemType(x);
            }
            // _glog.log("3 key:" + x +" foodStandcheck:" + foodStandcheck);

            if (x >= 0) {
                _gViewArriveUnderText2.setText(_gNetaInfo.getName(x));
                _gViewArriveUnderUnitText2.setText(_gOrderUnderInfo.getCurretSara(1) + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(x)));
                _gViewArriveUnderImage2.setVisibility(View.VISIBLE);

                //20161201 ネタ画像解放処理を入れているため、画像を読み直す必要がある。
                _gNetaInfo.setSingleImg(x);

                _gViewArriveUnderImage2.setImageBitmap(_gNetaInfo.getBmp(x));
            }
        } else {
            _gViewArriveUnderText2.setText("");
            _gViewArriveUnderUnitText2.setText("");
            _gViewArriveUnderImage2.setVisibility(View.INVISIBLE);
        }
        netaId = _gOrderUnderInfo.getCurretNetaId(2);
        if (netaId > 0) {
            x = _gNetaInfo.serchID(netaId);

            // TODO　add 20210817 原宿店屋台対応
            foodStandcheck = foodStandcheck + _gNetaInfo.getFoodStand(x);
            if(foodStandcheck > 0) {
                _gOrderUnderInfo.setFoodStanddispCount(2, _gFoodStandAlertTimer);

                // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
                foodStanditmeType = foodStanditmeType + _gNetaInfo.getFoodStandItemType(x);
            }
            // _glog.log("3 key:" + x +" foodStandcheck:" + foodStandcheck);

            if (x >= 0) {
                _gViewArriveUnderText3.setText(_gNetaInfo.getName(x));
                //_gViewArriveUnitText3.setText(_gOrderUnderInfo.getCurretSara(2) + _gNetaInfo.getUnit(x));
                _gViewArriveUnderUnitText3.setText(_gOrderUnderInfo.getCurretSara(2) + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(x)));
                _gViewArriveUnderImage3.setVisibility(View.VISIBLE);

                //20161201 ネタ画像解放処理を入れているため、画像を読み直す必要がある。
                _gNetaInfo.setSingleImg(x);

                _gViewArriveUnderImage3.setImageBitmap(_gNetaInfo.getBmp(x));
            }
        } else {
            _gViewArriveUnderText3.setText("");
            _gViewArriveUnderUnitText3.setText("");
            _gViewArriveUnderImage3.setVisibility(View.INVISIBLE);
        }

        netaId = _gOrderUnderInfo.getCurretNetaId(3);
        if (netaId > 0) {
            x = _gNetaInfo.serchID(netaId);

            // TODO　add 20210817 原宿店屋台対応
            foodStandcheck = foodStandcheck + _gNetaInfo.getFoodStand(x);
            if(foodStandcheck > 0) {
                _gOrderUnderInfo.setFoodStanddispCount(3, _gFoodStandAlertTimer);

                // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
                foodStanditmeType = foodStanditmeType + _gNetaInfo.getFoodStandItemType(x);
            }
            // _glog.log("4 key:" + x +" foodStandcheck:" + foodStandcheck);

            if (x >= 0) {
                _gViewArriveUnderText4.setText(_gNetaInfo.getName(x));
                _gViewArriveUnderUnitText4.setText(_gOrderUnderInfo.getCurretSara(3) + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(x)));
                _gViewArriveUnderImage4.setVisibility(View.VISIBLE);

                //20161201 ネタ画像解放処理を入れているため、画像を読み直す必要がある。
                _gNetaInfo.setSingleImg(x);

                _gViewArriveUnderImage4.setImageBitmap(_gNetaInfo.getBmp(x));
            }
        } else {
            _gViewArriveUnderText4.setText("");
            _gViewArriveUnderUnitText4.setText("");
            _gViewArriveUnderImage4.setVisibility(View.INVISIBLE);
        }


        // TODO　add 20210817 原宿店屋台対応
        // 下の場合は２皿警告は表示しない
        tv = (TextView) findViewById(R.id.ARRIVE_DOUBLEPLATE_TEXT_UNDER);
        ImageView img = (ImageView) findViewById(R.id.ARRIVE_DOUBLEIMG_UNDER);
        tv.setVisibility(View.GONE);
        img.setVisibility(View.GONE);

        if (foodStandcheck > 0) {
            _gFoodStandOnFlag = 1;
        }else{
            _gFoodStandOnFlag = 0;
        }
        if( _gUse_FoodStand == 1 && _gFoodStandOnFlag == 1) {
            // _glog.log("屋台対象！");
            tv = (TextView) findViewById(R.id.ARRIVE_TITLE_UNDER);
            // tv.setText(_glocalStr.LoStr("V2_LB_TITLE_UNDER"));
            // tv.setText("商品の準備が出来ました。スイーツ屋台までお越し願います。");
            tv.setText("スイーツ屋台までお越し願います。");
            tv.setBackgroundColor(Color.parseColor("#a52a2a"));

            // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
            if(foodStanditmeType > 0) {
                _gFoodStandTypeIceOnFlag = 1;
                // TODO 20211110 広報部よりレイアウトの修正指示があったため修正
                // tv = (TextView) findViewById(R.id.ARRIVE_FOOD_STAND_ICE_MASK);
                _gTextUnderARRIVE_FOOD_STAND_ICE_MASK.setVisibility(View.GONE);
            }else{
                _gFoodStandTypeIceOnFlag = 0;
                /*
                tv = (TextView) findViewById(R.id.ARRIVE_FOOD_STAND_MSG);
                tv.setText("");
                */
            }
            // TODO　add 20211027 原宿店屋台対応  ICE商品の区分


            // メッセージでな無く、画像を表示したいという要望があったためコメントアウト
           // tv = (TextView) findViewById(R.id.ARRIVE_FOOD_STAND_MSG);
           // tv.setText("商品ができあがりましたので屋台までお越しください。");
            _glog.log("ALL:" + x +" foodStandcheck:" + foodStandcheck + " foodStanditmeType:" + foodStanditmeType);

            /*
            Button bt = (Button) findViewById(R.id.btArrive_BACK_btback);
            bt.setVisibility(View.VISIBLE);
            bt = (Button) findViewById(R.id.btArrive_BACK);
            bt.setVisibility(View.VISIBLE);
            */
            _gViewArriverFoodUnderStandBackBt.setVisibility(View.VISIBLE);
            _gViewArriverFoodUnderStandBackBtBack.setVisibility(View.VISIBLE);


            // メッセージでな無く、画像を表示したいという要望があったため追加
           //  FrameLayout layout = (FrameLayout) findViewById(R.id.ARRIVE_FOOD_STAND_LAYOUT);
            _gViewArriveUnderFoodStandLayout.setVisibility(View.VISIBLE);
            /*
            tv = (TextView) findViewById(R.id.ARRIVE_FOOD_STAND_MSG);
            tv.setVisibility(View.VISIBLE);
            ImageView img3 = (ImageView) findViewById(R.id.imgArrive);
            img3.setVisibility(View.VISIBLE);
            */
        } else {
            // _glog.log("屋台対象ではない");
            tv = (TextView) findViewById(R.id.ARRIVE_TITLE_UNDER);
            tv.setText(_glocalStr.LoStr("V2_LB_TITLE_UNDER"));

            /*
            tv = (TextView) findViewById(R.id.ARRIVE_FOOD_STAND_MSG);
            tv.setVisibility(View.GONE);

            // メッセージでな無く、画像を表示したいという要望があったため追加
            ImageView img3 = (ImageView) findViewById(R.id.imgArrive);
            img3.setVisibility(View.GONE);
             */
            // FrameLayout layout = (FrameLayout) findViewById(R.id.ARRIVE_FOOD_STAND_LAYOUT);
            _gViewArriveUnderFoodStandLayout.setVisibility(View.GONE);
        }
        // TODO　add 20210817 原宿店屋台対応
    }

    /**
     * 商品到着上の場合のdraw
     */
    // TODO 20210703 表示方式を交互に変更 この関数は複数回呼ばれる可能性があるためステップを設けて処理をわける
       private void  ViewArriveKousokuDraw(int step) {
    // private void ViewArriveKousokuDraw() {
        int netaId = 0;
        int x = 0;

        // TODO marge ２皿対応  view_arrive.xmlもマージする事
        int doubelPlate = 0;
        _gDoublePlateAlretFlag = 0;

        // TODO 20210703 表示方式を交互に変更
         _gSoundManager.PlaySound(4, 1);

        TextView tv = (TextView) findViewById(R.id.ARRIVE_DOUBLEPLATE_TEXT);
        ImageView img = (ImageView) findViewById(R.id.ARRIVE_DOUBLEIMG);
        tv.setVisibility(View.GONE);
        img.setVisibility(View.GONE);

        tv = (TextView) findViewById(R.id.ARRIVE_TITLE);
        tv.setText(_glocalStr.LoStr("V2_LB_TITLE_HEIGHT"));
        _gViewArriveText1.setText("");
        _gViewArriveText2.setText("");
        _gViewArriveText3.setText("");
        _gViewArriveText4.setText("");
        _gViewArriveUnitText1.setText("");
        _gViewArriveUnitText2.setText("");
        _gViewArriveUnitText3.setText("");
        _gViewArriveUnitText4.setText("");

        netaId = _gOrderHeightInfo.getCurretNetaId(0);
        if (netaId > 0) {
            x = _gNetaInfo.serchID(netaId);
            if (x >= 0) {
                _gViewArriveText1.setText(_gNetaInfo.getName(x));
                _gViewArriveUnitText1.setText(_gOrderHeightInfo.getCurretSara(0) + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(x)));
                _gViewArriveImage1.setVisibility(View.VISIBLE);
                //20161201 ネタ画像解放処理を入れているため、画像を読み直す必要がある。
                _gNetaInfo.setSingleImg(x);
                _gViewArriveImage1.setImageBitmap(_gNetaInfo.getBmp(x));
                // add 20210621
                // doubelPlate = _gNetaInfo.getDoublePlate(x);
                if(_gDoublePlateAlretFlag != 1) {
                    _gDoublePlateAlretFlag = _gNetaInfo.getDoublePlateFlag(x);
                }

                // TODO 20210703 表示方式を交互に変更 この関数は複数回呼ばれる可能性があるためステップを設けて処理をわける
                if(step == 0) {
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Order,PROC:Arrive,OORDERNETAID:"
                            + netaId + ",SARA:" + _gOrderHeightInfo.getCurretSara(0)
                            + ",doubelPlate:" + _gNetaInfo.getDoublePlate(x)
                            + ",name:" + _gNetaInfo.getName(x));
                }
            }
        } else {
            _gViewArriveText1.setText("");
            _gViewArriveUnitText1.setText("");
            _gViewArriveImage1.setVisibility(View.INVISIBLE);
        }

        netaId = _gOrderHeightInfo.getCurretNetaId(1);
        if (netaId > 0) {
            x = _gNetaInfo.serchID(netaId);
            if (x >= 0) {
                _gViewArriveText2.setText(_gNetaInfo.getName(x));
                _gViewArriveUnitText2.setText(_gOrderHeightInfo.getCurretSara(1) + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(x)));
                _gViewArriveImage2.setVisibility(View.VISIBLE);
                //20161201 ネタ画像解放処理を入れているため、画像を読み直す必要がある。
                _gNetaInfo.setSingleImg(x);
                _gViewArriveImage2.setImageBitmap(_gNetaInfo.getBmp(x));
                // add 20210621
                // doubelPlate = _gNetaInfo.getDoublePlate(x);
                if(_gDoublePlateAlretFlag != 1) {
                    _gDoublePlateAlretFlag = _gNetaInfo.getDoublePlateFlag(x);
                }
                // TODO 20210703 表示方式を交互に変更 この関数は複数回呼ばれる可能性があるためステップを設けて処理をわける
                if(step == 0) {
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Order,PROC:Arrive,OORDERNETAID:"
                            + netaId + ",SARA:" + _gOrderHeightInfo.getCurretSara(1)
                            + ",doubelPlate:" + _gNetaInfo.getDoublePlate(x)
                            + ",name:" + _gNetaInfo.getName(x));
                }
            }
        } else {
            _gViewArriveText2.setText("");
            _gViewArriveUnitText2.setText("");
            _gViewArriveImage2.setVisibility(View.INVISIBLE);
        }

        netaId = _gOrderHeightInfo.getCurretNetaId(2);
        if (netaId > 0) {
            x = _gNetaInfo.serchID(netaId);
            if (x >= 0) {
                _gViewArriveText3.setText(_gNetaInfo.getName(x));
                _gViewArriveUnitText3.setText(_gOrderHeightInfo.getCurretSara(2) + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(x)));
                _gViewArriveImage3.setVisibility(View.VISIBLE);
                //20161201 ネタ画像解放処理を入れているため、画像を読み直す必要がある。
                _gNetaInfo.setSingleImg(x);
                _gViewArriveImage3.setImageBitmap(_gNetaInfo.getBmp(x));
                // doubelPlate = _gNetaInfo.getDoublePlate(x);
                if(_gDoublePlateAlretFlag != 1) {
                    _gDoublePlateAlretFlag = _gNetaInfo.getDoublePlateFlag(x);
                }
                // TODO 20210703 表示方式を交互に変更 この関数は複数回呼ばれる可能性があるためステップを設けて処理をわける
                if(step == 0) {
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Order,PROC:Arrive,OORDERNETAID:"
                            + netaId + ",SARA:" + _gOrderHeightInfo.getCurretSara(2)
                            + ",doubelPlate:" + _gNetaInfo.getDoublePlate(x)
                            + ",name:" + _gNetaInfo.getName(x));
                }
            }
        } else {
            _gViewArriveText3.setText("");
            _gViewArriveUnitText3.setText("");
            _gViewArriveImage3.setVisibility(View.INVISIBLE);
        }

        netaId = _gOrderHeightInfo.getCurretNetaId(3);
        if (netaId > 0) {
            x = _gNetaInfo.serchID(netaId);
            if (x >= 0) {
                _gViewArriveText4.setText(_gNetaInfo.getName(x));
                _gViewArriveUnitText4.setText(_gOrderHeightInfo.getCurretSara(3) + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(x)));
                _gViewArriveImage4.setVisibility(View.VISIBLE);

                //20161201 ネタ画像解放処理を入れているため、画像を読み直す必要がある。
                _gNetaInfo.setSingleImg(x);
                _gViewArriveImage4.setImageBitmap(_gNetaInfo.getBmp(x));
                // doubelPlate = _gNetaInfo.getDoublePlate(x);
                if(_gDoublePlateAlretFlag != 1) {
                    _gDoublePlateAlretFlag = _gNetaInfo.getDoublePlateFlag(x);
                }
                // TODO 20210703 表示方式を交互に変更 この関数は複数回呼ばれる可能性があるためステップを設けて処理をわける
                if(step == 0) {
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Order,PROC:Arrive,OORDERNETAID:"
                            + netaId + ",SARA:" + _gOrderHeightInfo.getCurretSara(3)
                            + ",doubelPlate:" + _gNetaInfo.getDoublePlate(x)
                            + ",name:" + _gNetaInfo.getName(x));
                }
            }
        } else {
            _gViewArriveText4.setText("");
            _gViewArriveUnitText4.setText("");
            _gViewArriveImage4.setVisibility(View.INVISIBLE);
        }
        // TODO marge ２皿対応  view_arrive.xmlもマージする事
    }
    // TODO 20210703 表示方式を交互に変更 この関数は複数回呼ばれる可能性があるためステップを設けて処理をわける

    /**
     * 商品到着上の場合のdraw
     */
    // TODO marge ２皿対応  view_arrive.xmlもマージする事
    private void ViewArriveKousokuDoubelPlateDraw() {
        // TODO marge ２皿 firstOrder
        // for debug
        _glog.log("ViewArriveKousokuDoubelPlateDraw called");
        _glog.log("_gDoublePlateFist_USE:" + _gDoublePlateFist_USE);
        _glog.log("_gDoublePlateFist_USE:" + _gDoublePlateFist_USE);
        _glog.log("_gDoublePlateFistOrderFlag:" + _gDoublePlateFistOrderFlag);
        _glog.log("_gDoublePlateAlretFlag:" + _gDoublePlateAlretFlag);
        // add 20210622 2皿商品が到着商品を１回だけ表示させる場合に使用




        // TODO 20210703 表示方式を交互に変更
        // この画面を２回呼び出すため、２回以降は表示しないように変更
        /*
        if(_gDoublePlateFist_USE ==1 ){
            if(_gDoublePlateFistOrderFlag == 1){
                return;
            } else {
                _gDoublePlateFistOrderFlag = 1;
            }
        }
        */
        if(_gDoublePlateFist_USE ==1 ){
            if(_gDoublePlateFistOrderFlag > 1){
                return;
            } else {
                _gDoublePlateFistOrderFlag++;
            }
        }
        // TODO 20210703 表示方式を交互に変更



        // TODO marge ２皿 firstOrder
        if(_gDoublePlateAlretFlag == 1){
            // _glog.log("ViewArriveKousokuDoubelPlateDraw called");
            TextView tv = (TextView) findViewById(R.id.ARRIVE_DOUBLEPLATE_TEXT);
            ImageView img = (ImageView) findViewById(R.id.ARRIVE_DOUBLEIMG);
            tv.setVisibility(View.VISIBLE);
            img.setVisibility(View.VISIBLE);
            _gSoundManager.PlaySound(13, 1);//音声追加
        }
    }
    // TODO marge ２皿対応  view_arrive.xmlもマージする事


    /**
     * key不正操作画面の表示
     */
    private void addViewKeyAlert() {
        _glog.log("キー不正操作画面OPEN");
        _gviewKeyAlert = this.getLayoutInflater().inflate(R.layout.view_alert, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewKeyAlert);
        TextView tv = (TextView) findViewById(R.id.tvCALL_MSG);
        tv.setText(_glocalStr.LoStr("V8_LB_HELP"));
        //tv.setBackgroundColor(Color.YELLOW);
        Button bt = (Button) findViewById(R.id.btCALL_BACK);
        bt.setText(_glocalStr.LoStr("V7_BT_BACK"));
    }

    /**
     * 高速バック画面の表示
     */
    private void addViewKousokuBackAlert() {
        // String f_dir = getApplicationContext().getFilesDir().toString();
        //_gviewKousokuBack = this.getLayoutInflater().inflate(R.layout.view_call, (ViewGroup)null);
        //Modify NDK 201912 Layout Change
        _gviewKousokuBack = this.getLayoutInflater().inflate(R.layout.view_call, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewKousokuBack);

        TextView tv = (TextView) findViewById(R.id.tvCALL_MSG);
        tv.setText(_glocalStr.LoStr("V8_LB_KOUSOKUBACK"));
        //tv.setBackgroundColor(Color.RED);
        Button bt = (Button) findViewById(R.id.btCALL_BACK);
        bt.setText(_glocalStr.LoStr("V7_BT_BACK"));
    }

    /**
     * ご注文商品が届いております ALRET
     * 20200605
     */
    private void addOrderArriveAlert() {
        _gviewOrderArriveAlert = this.getLayoutInflater().inflate(R.layout.view_alert, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewOrderArriveAlert);

        TextView tv = (TextView) findViewById(R.id.tvCALL_MSG);
        tv.setText("ご注文商品が届いております。　商品をレーンよりお取り下さい");
        //tv.setBackgroundColor(Color.RED);
        Button bt = (Button) findViewById(R.id.btCALL_BACK);
        bt.setText(_glocalStr.LoStr("V7_BT_BACK"));

        _gSoundManager.PlaySound(15, 1);
    }


    /**
     * ｵｰﾀﾞｰ時のメッセージ
     */
    private void addViewOrderCheck() {
        //_gorderCheckView = this.getLayoutInflater().inflate(R.layout.view_msg, (ViewGroup)null);
        _gorderCheckView = this.getLayoutInflater().inflate(R.layout.view_msg_order_comp, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gorderCheckView);
        _gViewMsgText = (TextView) findViewById(R.id.MSG_TEXT);

        // TODO　add 20210817 原宿店屋台 屋台の対象商品の場合は確認画面を変更する
        FrameLayout layout =  (FrameLayout) findViewById(R.id.foodstandlayout);
        layout.setVisibility(View.GONE);
        try {
            int foodStandcheck = _gNetaInfo.getFoodStand(_gOrderItemKey);
            if(foodStandcheck == 1){
                layout.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){

        }
        // TODO　add 20210817 原宿店屋台 屋台の対象商品の場合は確認画面を変更する
    }

    /**
     * ｵｰﾀﾞｰ時の注文制限メッセージ
     */
    private void addViewOrderLmit() {
        _gviewOrderLmitMsg = this.getLayoutInflater().inflate(R.layout.view_msg_limitpage, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewOrderLmitMsg);

        TextView title = (TextView) findViewById(R.id.MSG_LIMITPAGE_TITLE_TEXT);
        title.setText(_glocalStr.LoStr("V1_LB_MEG_PRE0"));

        TextView msg = (TextView) findViewById(R.id.MSG_LIMITPAGE_MSG_TEXT);
        msg.setText(_glocalStr.LoStr("EX_LIMITPAGE_MSG_TEXT"));

        Button bt_back = (Button) findViewById(R.id.btORDER_LIMIT_PAGE_BACK);
        bt_back.setText(_glocalStr.LoStr("V7_BT_BACK"));

        //対象を８ページMAXに増やす。
        int limitMaxItemNum = 8;
        Button bt[] = new Button[limitMaxItemNum];
        bt[0] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE1);
        bt[1] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE2);
        bt[2] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE3);
        bt[3] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE4);
        bt[4] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE5);
        bt[5] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE6);
        bt[6] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE7);
        bt[7] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE8);

        //added by ndk 20200120
        //ボタンの枠線を二重線とするため重ねているボタンも非表示とする必要があるためインスタンス化
        Button bt_sub[] = new Button[limitMaxItemNum];
        bt_sub[0] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE1_SUB);
        bt_sub[1] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE2_SUB);
        bt_sub[2] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE3_SUB);
        bt_sub[3] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE4_SUB);
        bt_sub[4] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE5_SUB);
        bt_sub[5] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE6_SUB);
        bt_sub[6] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE7_SUB);
        bt_sub[7] = (Button) findViewById(R.id.btORDER_LIMIT_PAGE8_SUB);

        int ZeroCount = 0;
        for (int i = 0; i < 8; i++) {
            int val = _gSettingInfoFromServer.getOrderOffPage_ByOrder(i + 1);
            if (val == 99) {//limit pagge 未設定
                bt[0].setVisibility(View.INVISIBLE);
                bt[1].setVisibility(View.INVISIBLE);
                bt[2].setVisibility(View.INVISIBLE);
                bt[3].setVisibility(View.INVISIBLE);
                bt[4].setVisibility(View.INVISIBLE);
                bt[5].setVisibility(View.INVISIBLE);
                bt[6].setVisibility(View.INVISIBLE);
                bt[7].setVisibility(View.INVISIBLE);

                //added by ndk 20200120
                //ボタンの枠線を二重線とするため重ねているボタンも非表示
                bt_sub[0].setVisibility(View.INVISIBLE);
                bt_sub[1].setVisibility(View.INVISIBLE);
                bt_sub[2].setVisibility(View.INVISIBLE);
                bt_sub[3].setVisibility(View.INVISIBLE);
                bt_sub[4].setVisibility(View.INVISIBLE);
                bt_sub[5].setVisibility(View.INVISIBLE);
                bt_sub[6].setVisibility(View.INVISIBLE);
                bt_sub[7].setVisibility(View.INVISIBLE);
                msg.setVisibility(View.INVISIBLE);
                break;
            }
            if (val == 0) {//limit pagge 未設定
                bt[i].setVisibility(View.INVISIBLE);
                //added by ndk 20200120
                //ボタンの枠線を二重線とするため重ねているボタンも非表示
                bt_sub[i].setVisibility(View.INVISIBLE);
                ZeroCount++;
            } else {
                // 2016 1021 外国語対応 LocalStrの変更が
                if (_glocalStr.LoStr("COMMON_PAGE").equals("")) {
                    bt[i].setText(val + "");
                } else {
                    bt[i].setText(_glocalStr.LoStr(val + "COMMON_PAGE"));
                }
            }
        }
    }

    /**
     * orderセルフ画面
     */
    private void addViewOrderSelf() {
        try {
            _gviewOrderSelf = this.getLayoutInflater().inflate(R.layout.view_order_self, (ViewGroup) null);
            _gMAIN_FRAME.addView(_gviewOrderSelf);
            TextView tv = (TextView) findViewById(R.id.ORDER_SELF_NAME);
            ImageView iv = (ImageView) findViewById(R.id.ORDER_SELF_IMG);
            tv.setText(_gNetaInfo.getName(_gOrderItemKey));
            iv.setImageBitmap(_gNetaInfo.getBmp(_gOrderItemKey));
            tv = (TextView) findViewById(R.id.ORDER_SELF_TITLE);
            tv.setText(_glocalStr.LoStr("EX_ORDER_SELF_TITLE"));
        } catch (Exception e) {
            _glog.logE("ERR:addViewOrderSelf " + e.toString());
        }
    }

    /**
     * Top画面
     */
    private void addViewTop() {
        _glog.log("addViewTop called");
        try {
            _gviewTop = this.getLayoutInflater().inflate(R.layout.view_top, (ViewGroup) null);
            _gMAIN_FRAME.addView(_gviewTop);
            this.addViewTopDraw();
        } catch (Exception e) {
            _glog.logE("ERR:addViewTop " + e.toString());
        }

    }

    /**
     * Top画面描画
     */
    private void addViewTopDraw() {
        try {
            //ver75　とりあえずmax6で
            Button bt1 = (Button) findViewById(R.id.btTopLangu1);
            bt1.setText(_glocalStr.LoStr("V5_BT_LANG1"));
            Button bt2 = (Button) findViewById(R.id.btTopLangu2);
            bt2.setText(_glocalStr.LoStr("V5_BT_LANG2"));
            Button bt3 = (Button) findViewById(R.id.btTopLangu3);
            bt3.setText(_glocalStr.LoStr("V5_BT_LANG3"));
            Button bt4 = (Button) findViewById(R.id.btTopLangu4);
            bt4.setText(_glocalStr.LoStr("V5_BT_LANG4"));
            Button bt5 = (Button) findViewById(R.id.btTopLangu5);
            bt5.setText(_glocalStr.LoStr("V5_BT_LANG5"));
            Button bt6 = (Button) findViewById(R.id.btTopLangu6);
            bt6.setText(_glocalStr.LoStr("V5_BT_LANG6"));

            //Added NDK 201912 Layout change
            //言語変更ボタンの二重枠線表現のためのオブジェクト。
            //非表示時に消す必要があるため、インスタンスを作成する。
            Button bt1_sub = (Button) findViewById(R.id.btTopLangu1_sub);
            Button bt2_sub = (Button) findViewById(R.id.btTopLangu2_sub);
            Button bt3_sub = (Button) findViewById(R.id.btTopLangu3_sub);
            Button bt4_sub = (Button) findViewById(R.id.btTopLangu4_sub);
            Button bt5_sub = (Button) findViewById(R.id.btTopLangu5_sub);
            Button bt6_sub = (Button) findViewById(R.id.btTopLangu6_sub);

            switch (_gLANG_MAX) {
                case 1:
                    bt1.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.GONE);
                    bt3.setVisibility(View.GONE);
                    bt4.setVisibility(View.GONE);
                    bt5.setVisibility(View.GONE);
                    bt6.setVisibility(View.GONE);
                    //Added NDK 201912 Layout change
                    bt1_sub.setVisibility(View.VISIBLE);
                    bt2_sub.setVisibility(View.GONE);
                    bt3_sub.setVisibility(View.GONE);
                    bt4_sub.setVisibility(View.GONE);
                    bt5_sub.setVisibility(View.GONE);
                    bt6_sub.setVisibility(View.GONE);
                    break;
                case 2:
                    bt1.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.VISIBLE);
                    bt3.setVisibility(View.GONE);
                    bt4.setVisibility(View.GONE);
                    bt5.setVisibility(View.GONE);
                    bt6.setVisibility(View.GONE);
                    //Added NDK 201912 Layout change
                    bt1_sub.setVisibility(View.VISIBLE);
                    bt2_sub.setVisibility(View.VISIBLE);
                    bt3_sub.setVisibility(View.GONE);
                    bt4_sub.setVisibility(View.GONE);
                    bt5_sub.setVisibility(View.GONE);
                    bt6_sub.setVisibility(View.GONE);
                    break;
                case 3:
                    bt1.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.VISIBLE);
                    bt3.setVisibility(View.VISIBLE);
                    bt4.setVisibility(View.GONE);
                    bt5.setVisibility(View.GONE);
                    bt6.setVisibility(View.GONE);
                    //Added NDK 201912 Layout change
                    bt1_sub.setVisibility(View.VISIBLE);
                    bt2_sub.setVisibility(View.VISIBLE);
                    bt3_sub.setVisibility(View.VISIBLE);
                    bt4_sub.setVisibility(View.GONE);
                    bt5_sub.setVisibility(View.GONE);
                    bt6_sub.setVisibility(View.GONE);
                    break;
                case 4:
                    bt1.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.VISIBLE);
                    bt3.setVisibility(View.VISIBLE);
                    bt4.setVisibility(View.VISIBLE);
                    bt5.setVisibility(View.GONE);
                    bt6.setVisibility(View.GONE);
                    //Added NDK 201912 Layout change
                    bt1_sub.setVisibility(View.VISIBLE);
                    bt2_sub.setVisibility(View.VISIBLE);
                    bt3_sub.setVisibility(View.VISIBLE);
                    bt4_sub.setVisibility(View.VISIBLE);
                    bt5_sub.setVisibility(View.GONE);
                    bt6_sub.setVisibility(View.GONE);
                    break;
                case 5:
                    bt1.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.VISIBLE);
                    bt3.setVisibility(View.VISIBLE);
                    bt4.setVisibility(View.VISIBLE);
                    bt5.setVisibility(View.VISIBLE);
                    bt6.setVisibility(View.GONE);
                    //Added NDK 201912 Layout change
                    bt1_sub.setVisibility(View.VISIBLE);
                    bt2_sub.setVisibility(View.VISIBLE);
                    bt3_sub.setVisibility(View.VISIBLE);
                    bt4_sub.setVisibility(View.VISIBLE);
                    bt5_sub.setVisibility(View.VISIBLE);
                    bt6_sub.setVisibility(View.GONE);
                    break;
                case 6:
                    bt1.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.VISIBLE);
                    bt3.setVisibility(View.VISIBLE);
                    bt4.setVisibility(View.VISIBLE);
                    bt5.setVisibility(View.VISIBLE);
                    bt6.setVisibility(View.VISIBLE);
                    //Added NDK 201912 Layout change
                    bt1_sub.setVisibility(View.VISIBLE);
                    bt2_sub.setVisibility(View.VISIBLE);
                    bt3_sub.setVisibility(View.VISIBLE);
                    bt4_sub.setVisibility(View.VISIBLE);
                    bt5_sub.setVisibility(View.VISIBLE);
                    bt6_sub.setVisibility(View.VISIBLE);
                    break;
            }

            TextView tv = (TextView) findViewById(R.id.tvTopTitle);
            tv.setText(_glocalStr.LoStr("V5_BT_TITLE1"));
            Button bt = (Button) findViewById(R.id.btTopBack);
            bt.setText(_glocalStr.LoStr("V5_BT_BACK"));
        } catch (Exception e) {
            _glog.logE("ERR:addViewTopDraw " + e.toString());
        }
    }

    /**
     * order拒否画面
     */
    private void addViewOrderRefuseMsg() {
        _glog.log("addViewOrderRefuseMsg called:");
        _gviewOrderRefuseMsg = this.getLayoutInflater().inflate(R.layout.view_msg, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewOrderRefuseMsg);
        _gViewMsgText = (TextView) findViewById(R.id.MSG_TEXT);
        // _gViewMsgLayout = (LinearLayout) findViewById(R.id.ID_VIEW_MSG);

        switch (_gORDER_CHECK_REFUSE_STATE) {
            case 98://注文停止
                _gViewMsgText.setText(_glocalStr.LoStr("V1_LB_MEG_REFUSAL"));
                break;
            case 99://注文混雑
                _gViewMsgText.setText(_glocalStr.LoStr("V1_LB_MEG_PRE"));
                break;
        }
    }

    /**
     * order画面
     */
    private void addViewOrder() {
        try {
            int check1 = _goptInfo.serchID(_gOPT_OPT_CODE_1);
            _gviewOrder = this.getLayoutInflater().inflate(R.layout.view_order2, (ViewGroup) null);
            _gMAIN_FRAME.addView(_gviewOrder);
            TextView tv = (TextView) findViewById(R.id.ORDER_NAME);
            ImageView iv = (ImageView) findViewById(R.id.ORDER_IMG);
            _gOrderUnit = (TextView) findViewById(R.id.ORDER_UNIT);
            tv.setText(_gNetaInfo.getName(_gOrderItemKey));

            // ver314 20201224 order second request
            Button btSecondRequest = (Button) findViewById(R.id.OrderSecondRequest);
            if (_gReserv_mode_mode == 1 && _gUSE_SECOND_REQUEST == 1) {
                btSecondRequest.setVisibility(View.VISIBLE);
            } else {
                btSecondRequest.setVisibility(View.GONE);
            }
            // ver314 20201224 order second request


            Button btOPT0 = (Button) findViewById(R.id.ORDER_OPT_0);
            Button btOPT1 = (Button) findViewById(R.id.ORDER_OPT_1);
            //トッピングはとりあえず２種に限定すらる。３種選択したいなら改めて検討が必要
            //  Button btOPT2=(Button) findViewById(R.id.ORDER_OPT_2);
            if (_gUSE_SELECT_OPT_FLAG == 1 && _gOPT_OPT_CODE != 0) {
                // _glog.log("addViewOrder   VISIBLE SHARI");
                btOPT0.setVisibility(View.VISIBLE);
                btOPT1.setVisibility(View.VISIBLE);
                btOPT0.setText(_goptInfo.getNameFromToppingId(_gOPT_OPT_CODE_1));
                btOPT1.setText(_goptInfo.getNameFromToppingId(_gOPT_OPT_CODE_2));
            }
            if (check1 == -1 || check1 == -2) {
                btOPT0.setVisibility(View.GONE);
                btOPT1.setVisibility(View.GONE);
                // btOPT1.setVisibility(View.VISIBLE);
            }
            //========================================
            _gOrderUnitCount = 1;
            iv.setImageBitmap(_gNetaInfo.getBmp(_gOrderItemKey));
            _gOrderUnit.setText(_gOrderUnitCount + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(_gOrderItemKey)));
            tv = (TextView) findViewById(R.id.ORDER_TITLE);
            tv.setText(_glocalStr.LoStr("V1_LB_TITLE"));

            Button bt;
            bt = (Button) findViewById(R.id.OrderOn);
            bt.setText(_glocalStr.LoStr("V1_BT_ORDER"));
            bt = (Button) findViewById(R.id.OrderBack);
            bt.setText(_glocalStr.LoStr("V1_BT_BACK"));
        } catch (Exception e) {
            _glog.logE("ERR:addViewOrder " + e.toString());
            _glog.log("ERR:addViewOrder " + e.toString());
            _gOrderUnitCount = 1;
        }
    }


    private void addViewOrderDraw() {
        Button btNUm1 = (Button) findViewById(R.id.ORDER_NUM1);
        Button btNUm2 = (Button) findViewById(R.id.ORDER_NUM2);
        Button btNUm3 = (Button) findViewById(R.id.ORDER_NUM3);
        Button btNUm4 = (Button) findViewById(R.id.ORDER_NUM4);
        TextView tv = (TextView) findViewById(R.id.ORDER_NAME);

        int _selOptType = 0;
        int check = _goptInfo.serchID(_gOPT_SELECTED_OPT_CODE);

        switch (_gOrderUnitCount) {//選択した数
            case 1:
                btNUm1.setBackgroundResource(R.drawable.fs_bt_order_sel_on);
                btNUm2.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                btNUm3.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                btNUm4.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                break;
            case 2:
                btNUm1.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                btNUm2.setBackgroundResource(R.drawable.fs_bt_order_sel_on);
                btNUm3.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                btNUm4.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                break;
            case 3:
                btNUm1.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                btNUm2.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                btNUm3.setBackgroundResource(R.drawable.fs_bt_order_sel_on);
                btNUm4.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                break;
            case 4:
                btNUm1.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                btNUm2.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                btNUm3.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                btNUm4.setBackgroundResource(R.drawable.fs_bt_order_sel_on);
                break;

        }
        //`外国語対応も必要
        //if(_gUSE_SELECT_SHARI_Flag ==1) {

        if (_gUSE_SELECT_OPT_FLAG == 1) {
            _selOptType = _gOPT_SELECTED_OPT_CODE % 10;//１桁目が選んだタイプ

            Button btOPT0 = (Button) findViewById(R.id.ORDER_OPT_0);
            Button btOPT1 = (Button) findViewById(R.id.ORDER_OPT_1);
            //Button btOPT2 = (Button) findViewById(R.id.ORDER_OPT_2);
            String _netaName = _gNetaInfo.getName(_gOrderItemKey);
            /*private int _gOPT_OPT_TYPE=0;//選択した商品のオプションタイプ
            private int _gOPT_OPT_CODE=0;//選択した商品のオプションコード
            private int _gOPT_SELECTED_OPT_CODE=0;//選択したオプション*/

            switch (_selOptType) {
               /* case 0:
                    tv.setText(_netaName);
                    btOPT0.setBackgroundResource(R.drawable.fs_bt_order_sel_on);
                    btOPT1.setBackgroundResource(R.drawable.fs_bt_order_sel_off);

                    btOPT2.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                    break;*/
                case 1:
                    //change 20190529 optは無条件でネタ名に付け足すよう変更
                    if (check != -1) {
                        tv.setText(_goptInfo.getNameFromToppingId(_gOPT_OPT_CODE_1) + BR + _netaName);
                    } else {
                        tv.setText(_netaName);
                    }
                    btOPT0.setBackgroundResource(R.drawable.fs_bt_order_sel_on);
                    btOPT1.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                    // btOPT2.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                    break;
                case 2:
                    if (check != -1) {
                        tv.setText(_goptInfo.getNameFromToppingId(_gOPT_OPT_CODE_2) + BR + _netaName);
                    } else {
                        tv.setText(_netaName);
                    }

                    btOPT0.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                    btOPT1.setBackgroundResource(R.drawable.fs_bt_order_sel_on);
                    //btOPT2.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                    break;
            }
              /*  case 3:
                    //tv.setText(_goptInfo.getNetaOptInfoStr(3,_gOPT_OPT_CODE)+"/"+_netaName);
                    tv.setText(_goptInfo.getNetaOptInfoStr(3,_gOPT_OPT_CODE)+BR+_netaName);
                    btOPT0.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                    btOPT1.setBackgroundResource(R.drawable.fs_bt_order_sel_off);

                    btOPT2.setBackgroundResource(R.drawable.fs_bt_order_sel_on);
                    break;
                default:
                    btOPT0.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                    btOPT1.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                    btOPT2.setBackgroundResource(R.drawable.fs_bt_order_sel_off);
                    break;*/
        }
    }

    /**
     * 動画用画面作成
     */
    // 20200608 Opning MOVIE
    private void addViewOpenningMov(String f_name) {

        // _glog.log("addViewOpenningMov 1!!");

        _gviewOpenningMove = this.getLayoutInflater().inflate(R.layout.view_mov2, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewOpenningMove);

        Button bt = (Button) findViewById(R.id.btMOV2_back);
        bt.setVisibility(View.GONE);
        // btOPT1.setVisibility(View.VISIBLE);

        _gvideoViewOpenningMove = (VideoView) findViewById(R.id.MOV2_VIEW);

        //  _glog.log("OpenningMove api:" +  _gApiVersion);
        // TODO onkyo8.0 OSむけ対応
        _gvideoViewOpenningMove.setZOrderMediaOverlay(true);
        _gvideoViewOpenningMove.setVideoPath(f_name);
         // TODO onkyo8.0 OSむけ対応
         _gvideoViewOpenningMove.setZOrderOnTop(true);

        // 再生準備完了時のリスナー　設定
        _gvideoViewOpenningMove.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mp != null) {
                    _gPreparedMovFlag = 1;
                }
            }
        });

        // _glog.log("addViewOpenningMov 2!!");
        _gvideoViewOpenningMove.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // _glog.log("setOnCompletionListener called api:" + _gApiVersion);
                if (_gvideoViewOpenningMove != null) {//add 20181207
                    // _glog.log("setOnCompletionListener A");
                    if (_gvideoViewOpenningMove.isPlaying()) {
                        //  _glog.log("setOnCompletionListener B");
                        _gvideoViewOpenningMove.stopPlayback();
                    }
                    // _glog.log("setOnCompletionListener C");
                    if (mp != null) {
                        mp.release();
                        mp = null;//add 20181206
                    }

                    // test
                    _gvideoViewOpenningMove = null;
                }
                _gPreparedMovFlag = 0;
                if (_gDrawOpenningMoveFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawOpenningMoveFlag = F_VIEW_STATE_CLOSE_CMD;
                    // _glog.log("setOnCompletionListener G");
                }
            }
        });
        // _glog.log("addViewOpenningMov 3!!");
    }


    private void addViewMov(String f_name) {

        _gviewMov = this.getLayoutInflater().inflate(R.layout.view_mov, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewMov);
        _gvideoView = (VideoView) findViewById(R.id.MOV_VIEW);

        // TODO onkyo8.0 OSむけ対応
        _gvideoView.setZOrderMediaOverlay(true);

        _gvideoView.setVideoPath(f_name);

        // TODO onkyo8.0 OSむけ対応
        _gvideoView.setZOrderOnTop(true);

        _gGamePlayTimeLength = 0;
        // 再生準備完了時のリスナー　設定
        _gvideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mp != null) {
                    _gPreparedMovFlag = 1;
                }

            }
        });
        _gvideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //_glog.log(" onCompletio:");
                //------add 20181121-

                // _glog.log("_gvideoView.setOnCompletionListener called");
                // _glog.LogJson(getApplicationContext(), "_gvideoView.setOnCompletionListener called");

                if (_gvideoView != null) {//add 20181207
                    if (_gvideoView.isPlaying()) {
                        _gvideoView.stopPlayback();

                        //_glog.log("_gvideoView stopPlayback");
                        //_glog.LogJson(getApplicationContext(), "_gvideoView stopPlayback");
                    }
                    if (mp != null) {
                        mp.release();
                        mp = null;//add 20181206

                        //_glog.log("mp=null");
                        //_glog.LogJson(getApplicationContext(), "mp=null");
                    }
                }
                _gPreparedMovFlag = 0;
                //-------add 20181121
                MoveEnd();
            }
        });
    }

    private void MoveEnd() {
        _glog.log("MoveEnd called");
        if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
            _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
        }
//del 20160531 ゲームとｽｸﾘｰﾝｾｰﾊﾞｰをわける
       /* if (_gDrawGameFlag != F_VIEW_STATE_CLEAR) {
            _gDrawGameFlag = F_VIEW_STATE_CLOSE_CMD;
        }*/
    }

    /**
     * 動画用画面作成
     */
    private void addViewMovGame(String f_name) {
        _glog.log(" =================addViewMovs called" + f_name);
        _gviewMovForGame = this.getLayoutInflater().inflate(R.layout.view_mov, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewMovForGame);
        _gvideoViewForGame = (VideoView) findViewById(R.id.MOV_VIEW);

        // TODO onkyo8.0 OSむけ対応
        _gvideoViewForGame.setZOrderMediaOverlay(true);

        _gvideoViewForGame.setVideoPath(f_name);

        // TODO onkyo8.0 OSむけ対応
        _gvideoViewForGame.setZOrderOnTop(true);


        //ver83 game skipの仕様をゲーム単体ではなく一括設定変更にする。
        _gGamePlayTimeLength = 0;
        // 再生準備完了時のリスナー　設定
        _gvideoViewForGame.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 再生時間を表示（単位ミリ秒）
                // int val = mp.getDuration();
                if (mp != null) {
                    _gGamePlayTimeLength = mp.getDuration();
                }
            }
        });
        _gvideoViewForGame.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //_glog.log(" onCompletio:");

                //------add 20181121-
                if (_gvideoViewForGame.isPlaying()) {
                    _gvideoViewForGame.stopPlayback();
                }
                if (mp != null) {
                    mp.release();
                }
                //-------add 20181121

                MoveEndGame();
            }
        });
    }

    private void MoveEndGame() {
        if (_gDrawGameFlag != F_VIEW_STATE_CLEAR) {
            _gDrawGameFlag = F_VIEW_STATE_CLOSE_CMD;
        }
    }

    /**
     * 20190615 スマホオーダー対応でAPIからの任意動画起動に対応させる
     * 画面をOPENする処理を関数にまとめる
     */
    void SmartGameOpen() {
        //for test
        int key = _gSmartGameMov[0];
        //_glog.log("------------------------SmartGameOpen key:" + key);
        _gCurrentSmartGameFile = getApplicationContext().getFilesDir().toString() + "/douga/" + _gSmartMovName[key];

        //_glog.log("------------------------SmartGameOpen name:" + _gCurrentSmartGameFile);

        //poolしている情報をoffset`
        for (int i = 0; i < MAX_SMART_MOV_POOL - 1; i++) {
            _gSmartGameType[i] = _gSmartGameType[i + 1];
            _gSmartGameMov[i] = _gSmartGameMov[i + 1];
        }
        _gSmartGameType[MAX_SMART_MOV_POOL - 1] = 0;
        _gSmartGameMov[MAX_SMART_MOV_POOL - 1] = 0;
        _gSmartGameCount--;//プールしているゲームしているゲームを減らす。

        //ｽｸﾘｰﾝｾｰﾊﾞｰ  close確認
        _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
        if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
            _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
        }
        //画面起動
        if (_gviewMov == null && _gviewMovForGame == null) {
            addViewMovGame(_gCurrentSmartGameFile);
            _gviewGameOnTimer = 100;
            _gPousePosition = 0;
            _gDrawGameFlag = F_VIEW_STATE_LIVE_WAIT;
        }
    }

    /**
     * 会計用画面2作成
     * 東貝塚店でしたレーンで皿を取った情報を加えてテーブル決裁する実験を予定
     */
    private void addViewKaikei2() {
        _glog.log("addViewKaikei2 called");
        _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:addViewKaikei2, UDPCODE:" + _gUSE_AUTO_KAIKEI_FLAG + ",DEBUG_TAG:NODE");

        _gviewKaikei2 = this.getLayoutInflater().inflate(R.layout.view_kaikei2, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewKaikei2);
        StringBuffer sf = new StringBuffer("http://192.168.11.1:11001/client_kaikei.html");


        sf.append("?table=");//後方互換のため
        sf.append(_gTtableNum);//後方互換のため
        sf.append("&inc=");
        sf.append(_gTimerCount);
        sf.append("&date=");
        sf.append(_gSettingInfo.GetOrderTimeStamp());
        //add 20190120 東貝塚実験用　アクセス時にお客様人数も含める
        sf.append("&customer_sum=");
        int _customer_sum = _gTouchState_Custom_1 + _gTouchState_Custom_2;
        sf.append(_customer_sum);

        //add 20200207
        sf.append("&seatNum=");
        sf.append(_gTtableNum);
        //add 20200207
        sf.append("&languege=");
        sf.append(_glocalStr.getLcalKey());
        sf.append("#jquery");


        WebView _web = (WebView) findViewById(R.id.kaikei_web);

        //dell 20191214
       /*_web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        _web.setWebViewClient(new WebViewClient());
        //JavaScriptを有効にする
        _web.getSettings().setJavaScriptEnabled(true);
        _web.loadUrl(sf.toString());*/

        //add 20191214
        //JavaScriptを有効にする
        _web.getSettings().setJavaScriptEnabled(true);
        _web.loadUrl(sf.toString());
        _web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //_glog.log("ViewKaikei2 onPageStarted called: url:"+url);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //_glog.log("ViewKaikei2 onReceivedError called:"+description + " url:"+failingUrl);
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:onReceivedError,PROC:addViewKaikei2");
                _gDrawKaikeiFlag = F_VIEW_STATE_CLOSE_CMD;
                super.onReceivedError(view, errorCode, description, failingUrl);
            }


        });

    }

    /**
     * 会計用画面作成
     */
    private void addViewKaikei() {
        //String f_dir = getApplicationContext().getFilesDir().toString();
        int i = 0;
        _gviewKaikei = this.getLayoutInflater().inflate(R.layout.view_kaikei, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewKaikei);

        for (i = 0; i < 20; i++) {
            _gKAIKEI_CAT_NAME_TEXT[i] = (TextView) findViewById(_gKAIKEI_CAT_NAME_ID[i]);
            _gKAIKEI_CAT_COUNT_TEXT[i] = (TextView) findViewById(_gKAIKEI_CAT_COUNT_ID[i]);
        }
        //会計画面は母国語表記を基本とする。バックボタンのみ外国語対応
        Button bt = (Button) findViewById(R.id.KAIKEI_BACK);
        bt.setText(_glocalStr.LoStr("V9_KAIKEI_BACK"));

        //add 20180415 for mutenkura
        TextView tv = (TextView) findViewById(R.id.KAIKEI_SARA);
        if (_FLAG_MUTENKURA_MODE_ON == 1) {
            tv.setBackgroundColor(Color.parseColor("#fcffc4"));
            // } else {
            // tv.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        //add 20181018 アメリカで使用するため
        tv = (TextView) findViewById(R.id.tvKaikeiTitle);
        tv.setText(_glocalStr.LoStr("V9_KAIKEI_TITLE"));

        tv = (TextView) findViewById(R.id.tvKaikeiWait);
        tv.setText(_glocalStr.LoStr("V9_KAIKEI_TITLE_SUB"));

        tv = (TextView) findViewById(R.id.tvKaikeiTitleSub1);
        tv.setText(_glocalStr.LoStr("V9_KAIKEI_TITLE_CAT1"));

    }

    /**
     * 会計用画面描画
     */

    private void addViewKaikeiDraw() {
        int i = 0;
        TextView tv = (TextView) findViewById(R.id.KAIKEI_SARA);
        TextView tv2 = (TextView) findViewById(R.id.tvKaikeiTitleSub1);
        //add 20180415 for mutenkura
        if (_FLAG_MUTENKURA_MODE_ON == 1) {
            tv.setText("");//無添くらは皿が無いので非表示
            tv2.setText("");//無添くらは皿が無いので非表示
        } else {
            tv.setText("" + _gSettingInfo.GetSaraCnt());
        }

        for (i = 0; i < 20; i++) {
            _gKAIKEI_CAT_NAME_TEXT[i].setText(_gKaikeiInfo.getkaikeiRirekiCat(i));
            _gKAIKEI_CAT_COUNT_TEXT[i].setText(_gKaikeiInfo.getkaikeiRirekiSara(i));
        }
    }

    /**
     * Rireki用画面作成
     */
    private void addViewRireki() {
        int i = 0;
        // String f_dir = getApplicationContext().getFilesDir().toString();
        _gviewRireki = this.getLayoutInflater().inflate(R.layout.view_rireki, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewRireki);

        RIREKI_BUTTON_PRE = (Button) findViewById(R.id.RIREKIpre);
        RIREKI_BUTTON_PRE.setText(_glocalStr.LoStr("V7_BT_NEXT"));
        RIREKI_BUTTON_NEXT = (Button) findViewById(R.id.RIREKInext);
        RIREKI_BUTTON_NEXT.setText(_glocalStr.LoStr("V7_BT_PRE"));
        RIREKI_BUTTON_BACK = (Button) findViewById(R.id.RIREKIback);
        RIREKI_BUTTON_BACK.setText(_glocalStr.LoStr("V7_BT_BACK"));

        TextView tv = (TextView) findViewById(R.id.RIREKI_ARRIVE);
        tv.setText(_glocalStr.LoStr("V7_LB_CHEAK"));
        tv = (TextView) findViewById(R.id.RIREKI_TIME);
        tv.setText(_glocalStr.LoStr("V7_LB_TIME"));
        tv = (TextView) findViewById(R.id.RIREKI_NAME);
        tv.setText(_glocalStr.LoStr("V7_LB_NAME"));
        tv = (TextView) findViewById(R.id.RIREKI_SARA);
        tv.setText(_glocalStr.LoStr("V7_LB_SARA"));

        for (i = 0; i < 10; i++) {
            RIREKI_ARRIVE_TEXT[i] = (TextView) findViewById(_gRIREKI_ARRIVE_ID[i]);
            RIREKI_TIME_TEXT[i] = (TextView) findViewById(_gRIREKI_TIME_ID[i]);
            RIREKI_NAME_TEXT[i] = (TextView) findViewById(_gRIREKI_NAME_ID[i]);
            RIREKI_SARA_TEXT[i] = (TextView) findViewById(_gRIREKI_SARA_ID[i]);
        }
    }

    /**
     * Rireki用画面作成
     */
    private void addViewRirekiDraw() {
        int i = 0;
        int netaOrder = 0;
        int key = 0;
        int opt = 0;
        // int optCode=0;
        // int optSelected=0;
        String optStr = "";

        if (_gRirekiInfo.getRirekidataCount() > 10) {

        } else {
            RIREKI_BUTTON_PRE.setVisibility(View.GONE);
            RIREKI_BUTTON_NEXT.setVisibility(View.GONE);
        }

        for (i = 0; i < 10; i++) {
            key = _gRirekiInfo.getRirekiPage() * 10 + i;

            if (_gRirekiInfo.getNetaId(i) == 0) {
                RIREKI_ARRIVE_TEXT[i].setText("");
                RIREKI_TIME_TEXT[i].setText("");
                RIREKI_NAME_TEXT[i].setText("");
                RIREKI_SARA_TEXT[i].setText("");
            } else {
                netaOrder = _gNetaInfo.serchID(_gRirekiInfo.getNetaId(key));
                RIREKI_ARRIVE_TEXT[i].setText(_gRirekiInfo.getRcheakStr(key));
                RIREKI_TIME_TEXT[i].setText(_gRirekiInfo._Rtime(key).toString());
                RIREKI_SARA_TEXT[i].setText(_gRirekiInfo.getRsara(key));
                //RIREKI_NAME_TEXT[i].setText(_gNetaInfo.getName(netaOrder));
                opt = _gRirekiInfo.getRopt(key);
                if (_gUSE_SELECT_OPT_FLAG == 0 || opt == 0) {
                    // TODO 20220125 履歴の際は品切れでも名称を表記する。
                    // RIREKI_NAME_TEXT[i].setText(_gNetaInfo.getName(netaOrder));
                    RIREKI_NAME_TEXT[i].setText(_gNetaInfo.getAbsoluteName(netaOrder));
                    // TODO 20220125 履歴の際は品切れでも名称を表記する。
                } else {
                    if (_goptInfo != null) {
                        int check = _goptInfo.serchID(opt);
                        if (check != -1) {
                            // TODO 20220125 履歴の際は品切れでも名称を表記する。
                            optStr = _goptInfo.getNameFromToppingId(opt) + "/";
                            // TODO 20220125 履歴の際は品切れでも名称を表記する。
                        }
                    }
                    // RIREKI_NAME_TEXT[i].setText(optStr + _gNetaInfo.getName(netaOrder));
                    // TODO 20220125 履歴の際は品切れでも名称を表記する。
                    // RIREKI_NAME_TEXT[i].setText(_gNetaInfo.getName(netaOrder));
                    RIREKI_NAME_TEXT[i].setText(optStr + _gNetaInfo.getAbsoluteName(netaOrder));
                    // TODO 20220125 履歴の際は品切れでも名称を表記する。
                }
            }
        }

    }

    /**
     * TEST表示用viewの作成
     */
    private void addViewTest() {
        // _glog.log("addViewTest  called");
        _gviewTest = this.getLayoutInflater().inflate(R.layout.view_test, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewTest);

        TextView tv = (TextView) findViewById(R.id.tvTesCon);
        tv.setText(_gTestCatStr);
        _gTestCategoryCount = 0;
        _gTestCatStr = _gPreferencesSave.PrintPreferencesSavet();

    }

    /**
     * TEST表示用viewの作成
     */
    private void addViewTestDraw() {
        TextView tv = (TextView) findViewById(R.id.tvTesCon);
        tv.setText(_gTestCatStr);
    }

    /**
     * 呼び出し表示用viewの作成
     */
    private void addViewCall() {
        //String f_dir = getApplicationContext().getFilesDir().toString();
        _gviewCall = this.getLayoutInflater().inflate(R.layout.view_call, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewCall);

        TextView tv = (TextView) findViewById(R.id.tvCALL_MSG);
        tv.setText(_glocalStr.LoStr("V8_LB_HELP"));
        Button bt = (Button) findViewById(R.id.btCALL_BACK);
        bt.setText(_glocalStr.LoStr("V7_BT_BACK"));
    }


    /**
     * FTP更新中画面
     */
    private void addViewFlashing() {
        _gviewFlashing = this.getLayoutInflater().inflate(R.layout.view_flashing, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewFlashing);

        // add 20200707 flash
        ImageView img = (ImageView) findViewById(R.id.btFlash_background);
        img.setImageBitmap(_gNetaInfo.getFlashViewBmp());
        // img.setImageBitmap(_gNetaInfo.getBIGImg(0));


        Button bt = (Button) findViewById(R.id.btFlash);
        bt.setText(_gTtableNum + "番席");


        //add 20181101 この機能はdemo段階　使用しない場合はこのボタンはなくす
        bt = (Button) findViewById(R.id.btFlash_bt_cat2);
        if (_gSousaAnnaiCat == 1) {
            bt.setVisibility(View.VISIBLE);
        } else {
            bt.setVisibility(View.INVISIBLE);
        }
    }

    private void addViewFlashingDraw() {

        if (_gviewFlashingOnTimer % 2 == 0) {
            _gviewFlashing.setBackgroundColor(Color.WHITE);
        } else {
            _gviewFlashing.setBackgroundColor(Color.YELLOW);
        }
    }


//!#########################################################
    //DRAW
    //描画関連の処理はここで一元管理すること。
    //ページ切り替えはaddviewで追加し、メインページは常に表示
    //addviewは複数しない事。
//#########################################################

    /**
     * ページを切り替えたときの処理
     */
    private void pageChange(int index, int vect) {
        int i = 0;
        int cc = index;
        for (i = 0; i < PAGE_MAX; i++) {
            if (_gNetaInfo.isPage(cc) == true) {
                break;
            } else {
                if (vect == 1) {
                    if (cc == PAGE_MAX) {
                        cc = 1;
                    } else {
                        cc++;
                    }
                } else {
                    if (cc == 1) {
                        cc = PAGE_MAX;
                    } else {
                        cc--;
                    }
                }
            }
        }
        //  if(cc !=_gCurrentPage) {
        _gCurrentPage = cc;
        _gTableDraw_Flag = 1;
        //  }
    }

    /**
     * MainPageの描画
     */
    void drawMainPage() {
        _gBt_main[6].setText("" + _gSettingInfo.GetSaraCnt());
        // _gSaraCnt_Drawbuf = _gSettingInfo.GetSaraCnt();
        // }
        if (_gWaitTime_Drawbuf != _gSettingInfo.GetOrderWaitTime()) {
            _gBt_main[7].setText("" + _gSettingInfo.GetOrderWaitTime());
            _gWaitTime_Drawbuf = _gSettingInfo.GetOrderWaitTime();
        }

        if (_gTableMain_Flag == 1) {
            //topボタンをしようしない場合。
            if (_gIo_use_mode == 0) {
                Button bt2 = (Button) findViewById(R.id.bt_main9);
                bt2.setVisibility(View.INVISIBLE);
            } else {//add 20160628使用する
                Button bt2 = (Button) findViewById(R.id.bt_main9);
                bt2.setVisibility(View.VISIBLE);
            }//add 20160628使用する
            _gmT_tableNum.setText("table:" + Integer.toString(_gTtableNum));
            _gmT_AppVer.setText("AppVer:" + _gAppver);

            Button bt = (Button) findViewById(R.id.bt_main1);
            bt.setText(_glocalStr.LoStr("V0_BTPRE"));
            bt = (Button) findViewById(R.id.bt_main2);
            bt.setAllCaps(false);
            bt.setText(_glocalStr.LoStr("V0_BT_NEXT"));
            bt = (Button) findViewById(R.id.bt_main3);
            bt.setAllCaps(false);
            bt.setText(_glocalStr.LoStr("V0_BT_RIREKI"));
            bt = (Button) findViewById(R.id.bt_main4);
            bt.setAllCaps(false);
            bt.setText(_glocalStr.LoStr("V0_BT_HELP"));
            bt = (Button) findViewById(R.id.bt_main5);
            bt.setAllCaps(false);
            bt.setText(_glocalStr.LoStr("V0_BT_KAIKEI"));
            TextView tv = (TextView) findViewById(R.id.mT_Menu);
            bt.setAllCaps(false);
            tv.setText(_glocalStr.LoStr("V0_BT_MENU"));
            tv = (TextView) findViewById(R.id.mT_Sara);
            tv.setText(_glocalStr.LoStr("V0_LB_SARACOUNT"));
            tv = (TextView) findViewById(R.id.mT_SaraUnit);
            tv.setText(_glocalStr.LoStr("V0_LB_SARACOUNT_UNIT"));
            tv = (TextView) findViewById(R.id.mT_Wait);
            tv.setText(_glocalStr.LoStr("V0_LB_WAIT"));
            tv = (TextView) findViewById(R.id.mT_WaitUnit);
            tv.setText(_glocalStr.LoStr("V0_LB_WAIT_UNIT"));


            bt = (Button) findViewById(R.id.bt_main10);
            if (_gSKIP_GAME_USE_ENABLE_FLAG == 1) {
                bt.setVisibility(View.VISIBLE);
            } else {
                bt.setVisibility(View.INVISIBLE);
            }
            if (_gSKIP_GAME_USE == 0) {
                bt.setText(_glocalStr.LoStr("GAMESKIP_MAIN_OFF"));
            } else {
                bt.setText(_glocalStr.LoStr("GAMESKIP_MAIN_ON"));
            }

            //ver139 add 20180418
            if (_FLAG_MUTENKURA_MODE_ON == 1) {
                tv = (TextView) findViewById(R.id.mT_Sara);
                tv.setVisibility(View.INVISIBLE);
                bt = (Button) findViewById(R.id.bt_main7);
                bt.setVisibility(View.INVISIBLE);
                tv = (TextView) findViewById(R.id.mT_SaraUnit);
                tv.setVisibility(View.INVISIBLE);
            } else {
                tv = (TextView) findViewById(R.id.mT_Sara);
                tv.setVisibility(View.VISIBLE);
                bt = (Button) findViewById(R.id.bt_main7);
                bt.setVisibility(View.VISIBLE);
                tv = (TextView) findViewById(R.id.mT_SaraUnit);
                tv.setVisibility(View.VISIBLE);
            }
            //add 20181101 操作案内機能の使用状況に応じて処理
            /*if (_gUSE_SousaANNAI_FLAG == 0) {
                bt = (Button) findViewById(R.id.bt_main12);
                bt.setVisibility(View.INVISIBLE);
            }else{
                bt = (Button) findViewById(R.id.bt_main12);
                bt.setVisibility(View.VISIBLE);
            }*/
            //add 20190316 操作案内機能は現在では使用指定ないので非表示としておく

            bt = (Button) findViewById(R.id.bt_main12);
            bt.setVisibility(View.INVISIBLE);
            _gTableMain_Flag = 0;

            //add 20200709
            bt = (Button) findViewById(R.id.bt_main11);
            bt.setText(_glocalStr.LoStr("V0_BT_SOWEB"));

        }
    }

    /**
     * タブの描画
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    void drawTag() {
        int i = 0;
        // TODO 20211211 後方互換性tag数を確認して７～９で確認する。（7以下は7表示とする。）
        /*
        for (i = 0; i < _gBtagid.length; i++) {
            _gBt_tag[i].setText(_glocalStr.LoStr("V0_BT_TAG" + (i + 1)));
        }
        */
        if(_gCuttentTagCount < _gBtagid.length  ){
            for (i = 0; i < _gBtagid.length; i++) {
                if( i <_gCuttentTagCount) {
                    _gBt_tag[i].setVisibility(View.VISIBLE);
                    _gBt_tag[i].setText(_glocalStr.LoStr("V0_BT_TAG" + (i + 1)));
                } else {
                    _gBt_tag[i].setVisibility(View.INVISIBLE);
                }
            }
        }else {
            for (i = 0; i < _gBtagid.length; i++) {
                _gBt_tag[i].setText(_glocalStr.LoStr("V0_BT_TAG" + (i + 1)));
            }
        }

    }

    /**
     * テーブルの描画
     */
    void drawTable() {
        int i = 0;
        int key = 0;
        //change from ver50 20161115 写真の一括読み込みはやめて、必要時に読み込む
        _gNetaInfo.setPageImg(_gCurrentPage - 1);

        _glog.log("drawTable called");

        for (i = 0; i < ORDER_MAX; i++) {
            key = i + (_gCurrentPage - 1) * ORDER_MAX;
            try {
                if (_gNetaInfo.isItem(key)) {
                    //TODO 20211225 品切れ 指定商品の品切れ文字表示用
                    // TSINAGIRE[i].setVisibility(View.INVISIBLE);

                    IB[i].setVisibility(View.VISIBLE);
                    IB[i].setClickable(true);
                    TNAME_A[i].setVisibility(View.VISIBLE);
                    IB[i].setImageBitmap(_gNetaInfo.getBmp(key));
                    if (_gNetaInfo.GetLocalNeta_sparate(key) == 1) {
                        TNAME_A[i].setText(_gNetaInfo.GetLocalStrName1(key));
                        TNAME_B[i].setVisibility(View.VISIBLE);
                        TNAME_B[i].setText(_gNetaInfo.GetLocalStrName2(key));
                    } else {
                        TNAME_A[i].setText(_gNetaInfo.getName(key));
                        TNAME_B[i].setVisibility(View.INVISIBLE);
                    }
                } else {//登録の無い商品を非表示
                    //TODO 20211225 品切れ 指定商品の品切れ文字表示用
                    /*
                    if (_gNetaInfo.getSinagireDisp(key) == 1 && _gSINAGIRE_DISP_ON == 1) {//品切れ時に品切れ文字を重ねる設定がある場合
                        // TSINAGIRE[i].setVisibility(View.VISIBLE);
                        IB[i].setImageBitmap(_gNetaInfo.getBmpSinagireDisp(key));
                        TNAME_A[i].setText(_gNetaInfo.getNameSinagireDisp(key));
                        IB[i].setVisibility(View.VISIBLE);
                        TNAME_A[i].setVisibility(View.VISIBLE);

                        if (_gNetaInfo.GetLocalNeta_sparate(key) == 1) {
                            TNAME_A[i].setText(_gNetaInfo.GetLocalStrName1(key));
                            TNAME_B[i].setVisibility(View.VISIBLE);
                            TNAME_B[i].setText(_gNetaInfo.GetLocalStrName2(key));
                        } else {
                            TNAME_A[i].setText(_gNetaInfo.getNameSinagireDisp(key));
                            TNAME_B[i].setVisibility(View.INVISIBLE);
                        }
                    //TODO 20211225 品切れ 指定商品の品切れ文字表示用
                    } else {//従来のネタ切れ表記
                    */
                    IB[i].setVisibility(View.INVISIBLE);
                    IB[i].setImageResource(R.drawable.kura_logo_mark_gray);
                    IB[i].setClickable(false);
                    TNAME_A[i].setVisibility(View.INVISIBLE);
                    TNAME_B[i].setVisibility(View.INVISIBLE);
                    // }

                }
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                _glog.log("key:" + key);
                _glog.log("drawTabl ArrayIndexOutOfBoundsException" + e);
            } catch (Exception e) {
                _glog.log("drawTabl Exception" + e);
            }
        }
        _gBt_main[5].setText(Integer.toString(_gCurrentPage));
        int t = 0;
        //_glog.log("_gBtagid.length:" + _gBtagid.length);
        for (i = 0; i < _gBtagid.length; i++) {
            if (_gNetaInfo.getTagpage(i) == _gCurrentPage) {
                t = i;
                break;
            }
            if (_gNetaInfo.getTagpage(i) > _gCurrentPage) {
                t = i - 1;
                break;
            }
        }
        if (i == _gBtagid.length) {
            t = i - 1;
        }
        for (i = 0; i < _gBtagid.length; i++) {
            _gBt_tag[i].setBackgroundResource(R.drawable.fs_bt_tag0);
        }
        //Modifed nttdk 20191231 layout change
//        if (t >= 0 && t < 7) {
//            _gTableBack.setBackgroundColor(_gTagColor[t]);
        _gBt_tag[t].setBackgroundColor(_gTagColor[t]);
//        }

        //===add 20180817 大ネタの表示処理===
        if (_gUSE_BIGNETA_USE_FLAG == 0) {//大ネタの表示を使用しない設定の場合は非表示
            IB_BIG.setVisibility(View.GONE);
            TNAME_BIG.setVisibility(View.GONE);
            _gBIG_FRAME.setVisibility(View.GONE);
            //_glog.log("--1  _gUSE_BIGNETA_USE_FLAG:" + _gUSE_BIGNETA_USE_FLAG);

        } else if (_gUSE_BIGNETA_USE_FLAG == 1) {//pageに対象商品がある場合
            if (_gUSE_BIGNETA_PAGE_FLAGS[_gCurrentPage - 1] == 0) {//大ネタ対象page?
                IB_BIG.setVisibility(View.GONE);
                TNAME_BIG.setVisibility(View.GONE);
                _gBIG_FRAME.setVisibility(View.GONE);
            } else {
                key = (_gCurrentPage - 1) * ORDER_MAX;//大ネタはページの先頭の商品とする
                if (_gNetaInfo.isItem(key)) {
                    _gBIG_FRAME.setVisibility(View.VISIBLE);
                    TNAME_BIG.setVisibility(View.VISIBLE);
                    IB_BIG.setVisibility(View.VISIBLE);
                    IB_BIG.setImageBitmap(_gNetaInfo.getBIGImg(key));
                    TNAME_BIG.setText(_gNetaInfo.getName(key));
                } else {//品切の場合は非表示
                    IB_BIG.setVisibility(View.GONE);
                    TNAME_BIG.setVisibility(View.GONE);
                    _gBIG_FRAME.setVisibility(View.GONE);
                }
            }
        }
        //===add 20180817 大ネタの表示処理===

        // TODO add 20210913 get QRINFO　メイン画面のボタンを非表示に設定
        Button _linkQRMainViewButton = (Button) findViewById(R.id.bt_main_linkqr);
        if( _gLinkQRMainViewButtonOnFlag == 1 && _FLAG_LINKQRCODE_USE_ON == 1 ) {
            _linkQRMainViewButton.setVisibility(View.VISIBLE);
        }else{
            _linkQRMainViewButton.setVisibility(View.GONE);
        }
        // TODO add 20210913 get QRINFO　メイン画面のボタンを非表示に設定
        return;
    }

    /**
     * draw
     * すべての描画はこの処理で管理すること
     * ページ切り替えのﾀｲﾐﾝｸﾞもこの処理で管理すること
     */
    private void draw() {
        int ret = 0;
        //----------main info draw----------
        // TODO marge os8対応 add 20210619 未検証：　os8で動画処理で処理がスキップする現象が見られる。動画処理画面ではテーブル描画の処理はしない形にしてテストする
        if(_gDrawMovFlag == F_VIEW_STATE_CLEAR &&
                _gDrawGameFlag == F_VIEW_STATE_CLEAR
            // _gDrawKaikeiFlag == F_VIEW_STATE_CLEAR
            // _gDrawCallFlag == F_VIEW_STATE_CLEAR &&

            // _gDrawOrderFlag == F_VIEW_STATE_CLEAR &&
            // _gDrawRirekiFlag == F_VIEW_STATE_CLEAR &&
            // _gDrawUpdateAlertFlag  == F_VIEW_STATE_CLEAR &&
            //  _gDrawArriveFlag  == F_VIEW_STATE_CLEAR &&
            //  _gDrawArriveKousokuFlag  == F_VIEW_STATE_CLEAR &&
            // _gDrawFtpUpdatetFlag == F_VIEW_STATE_CLEAR &&
            // _gDrawOpenningMoveFlag == F_VIEW_STATE_CLEAR
        ) {
            // _glog.log("----draw----" + _gTableDraw_Flag);
            drawMainPage();
            if (_gTableDraw_Flag == 1) { //neta draw
                drawTable();
                _gTableDraw_Flag = 0;
            }
            if (_gTableTag_Flag == 1) { //tag name draw
                drawTag();
                _gTableTag_Flag = 0;
            }
        }else{
            /* for debug
            _glog.log("_gDrawMovFlag:"+_gDrawMovFlag);
            _glog.log("_gDrawGameFlag:"+_gDrawGameFlag);
            _glog.log("_gDrawKaikeiFlag :"+_gDrawKaikeiFlag );
            _glog.log("_gDrawCallFlag:"+_gDrawCallFlag);
            _glog.log(" _gDrawOrderFlag:"+ _gDrawOrderFlag);
            _glog.log("_gDrawQrCodeFlag:"+_gDrawQrCodeFlag);
            _glog.log(" _gDrawRirekiFlag:"+_gDrawRirekiFlag);
            _glog.log("  _gDrawUpdateAlertFlag :"+ _gDrawUpdateAlertFlag );
            _glog.log("  _gDrawArriveFlag:"+ _gDrawArriveFlag );
            _glog.log("  _gDrawArriveKousokuFlag:"+ _gDrawArriveKousokuFlag );
            _glog.log("  _gDrawFtpUpdatetFlag:"+ _gDrawFtpUpdatetFlag );
            _glog.log("  _gDrawOpenningMoveFlag:"+ _gDrawOpenningMoveFlag );
            */
        }
        // TODO marge os8対応


        //WEB pageを表示するだけ
        if (_gDrawSousaAnnaiFlag == F_VIEW_STATE_OPEN_CMD &&
                //_gDrawFlashingFlag == FLAG_IO_RESET_STATE_SARA_CLEAR &&
                _gDrawQrCodeFlag == F_VIEW_STATE_CLEAR) {
            //_glog.log("_gDrawSousaAnnaiFlag = F_VIEW_STATE_OPEN_CMD called "+_gUSE_SousaANNAI_FLAG );
            if (_gUSE_SousaANNAI_FLAG == 0) {
                _gDrawSousaAnnaiFlag = F_VIEW_STATE_CLOSE_CMD;
            } else {
                if (_gviewSousaAnnai == null) {
                    _gDrawSousaAnnaiFlag = F_VIEW_STATE_LIVE;
                    addViewSousaAnnai();
                    _gviewSousaAnnaiOnTimer = 100;
                }
            }
        } else if (_gDrawSousaAnnaiFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            addViewSousaAnnaiDraw();
            _gviewSousaAnnaiOnTimer--;
            if (_gviewSousaAnnaiOnTimer <= 0) {
                _gviewSousaAnnaiOnTimer = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawSousaAnnaiFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewSousaAnnai != null) {
                _gSousaAnnaiWeb.stopLoading();
                _gSousaAnnaiWeb.clearCache(true);
                _gSousaAnnaiWeb.clearHistory();
                _gSousaAnnaiWeb.setWebChromeClient(null);
                _gSousaAnnaiWeb.setWebViewClient(null);
                _gSousaAnnaiWeb.destroy();
                _gSousaAnnaiWeb = null;
                _gMAIN_FRAME.removeView(_gviewSousaAnnai);
                _gviewSousaAnnai = null;
            }
            _gDrawSousaAnnaiFlag = F_VIEW_STATE_CLEAR;
        }
        //----------top---------
        if (_gDrawTopFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewTop == null) {
                _gDrawTopFlag = F_VIEW_STATE_LIVE;
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                addViewTop();
                _gviewTopOnTimer = 100;
            }
        } else if (_gDrawTopFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            addViewTopDraw();
            _gviewTopOnTimer--;
            if (_gviewTopOnTimer <= 0) {
                _gviewTopOnTimer = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawTopFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewTop != null) {
                _gMAIN_FRAME.removeView(_gviewTop);
                _gviewTop = null;
            }
            _gDrawTopFlag = F_VIEW_STATE_CLEAR;
        }
        //----------Rireki-------------
        if (_gDrawRirekiFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewRireki == null) {
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                addViewRireki();
                addViewRirekiDraw();
                _gDrawRirekiFlag = F_VIEW_STATE_LIVE;
            }
        } else if (_gDrawRirekiFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            if (_gRirekiDraw_Flag == 1) {
                _gRirekiDraw_Flag = 0;
                //処理速度が問題なら更新を検知させてドローする事
                // addViewRirekiDraw();
            }
            addViewRirekiDraw();
        } else if (_gDrawRirekiFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewRireki != null) {
                _gRirekiInfo.clearRdata();
                _gMAIN_FRAME.removeView(_gviewRireki);
                _gviewRireki = null;
            }
            _gDrawRirekiFlag = F_VIEW_STATE_CLEAR;
        }

        //----------call-------------
        if (_gDrawCallFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewCall == null) {
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                addViewCall();
                _gDrawCallFlag = F_VIEW_STATE_LIVE;
                _gviewCallOnTimer = 100;
            }
        } else if (_gDrawCallFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            if (_gviewCallOnTimer < 0) {
                _gDrawCallFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawCallFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewCall != null) {
                _gTableDraw_Flag = 1;
                _gMAIN_FRAME.removeView(_gviewCall);
                _gviewCall = null;
            }
            _gDrawCallFlag = F_VIEW_STATE_CLEAR;
            _gviewCallOnTimer = 0;
        }
        //----------order　refuse-------------
        if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewOrderRefuseMsg == null) {
                addViewOrderRefuseMsg();
                _gviewOrderRefuseMsgOnTimer = 15;
                _gDrawOrderRefuseMsgFlag = F_VIEW_STATE_LIVE;
            }
        } else if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _gviewOrderRefuseMsgOnTimer--;
            if (_gviewOrderRefuseMsgOnTimer <= 0) {
                _gDrawOrderRefuseMsgFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewOrderRefuseMsg != null) {
                _gMAIN_FRAME.removeView(_gviewOrderRefuseMsg);
                _gviewOrderRefuseMsg = null;
            }
            _gDrawOrderRefuseMsgFlag = F_VIEW_STATE_CLEAR;
            _gviewOrderRefuseMsgOnTimer = 0;
        }
        //----------order-----------
        if (_gDrawOrderFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewOrder == null) {
                ret = CheckOrderOn();
                //_glog.logOut(getApplicationContext(), "OrderPageCheck:"+ret +" SEIGEN:"+_gSettingInfoFromServer.getCURRENT_chuumonseigen()+ " ORDERD:"+_gSettingInfoFromServer.getCURRENT_ORDER()+" ID:"+_gTAG_CUSTOMOER_ID);
                if (ret == 0) {
                    addViewOrder();
                    _gDrawOrderFlag = F_VIEW_STATE_LIVE;
                    _gviewOrderOnTimer = 100;
                } else {
                    _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                    if (ret == 98 || ret == 99) {
                        //change 20161031
                        if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_CLEAR) {
                            _gDrawOrderRefuseMsgFlag = F_VIEW_STATE_OPEN_CMD;
                        }
                        //_gDrawOrderRefuseMsgFlag = F_VIEW_STATE_OPEN_CMD;
                    }
                    if (ret == 97) {
                        //change 20161031
                        if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_CLEAR) {
                            _gDrawOrderLmitMsgFlag = F_VIEW_STATE_OPEN_CMD;
                        }
                        //_gDrawOrderLmitMsgFlag = F_VIEW_STATE_OPEN_CMD;
                    }
                }
            }
        } else if (_gDrawOrderFlag == F_VIEW_STATE_LIVE) {
            //add 20170903 ver 90
            addViewOrderDraw();
            //add 20180625 orderMaxのチェックの追加
            ret = CheckOrderOn();
            if (ret != 0) {
                //_glog.logOut(getApplicationContext(), "OrderPageCheck_LIVE:"+ret +" SEIGEN:"+_gSettingInfoFromServer.getCURRENT_chuumonseigen()+ " ORDERD:"+_gSettingInfoFromServer.getCURRENT_ORDER()+" ID:"+_gTAG_CUSTOMOER_ID);
                _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                if (ret == 98 || ret == 99) {
                    if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_CLEAR) {
                        _gDrawOrderRefuseMsgFlag = F_VIEW_STATE_OPEN_CMD;
                    }
                }
                if (ret == 97) {
                    if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_CLEAR) {
                        _gDrawOrderLmitMsgFlag = F_VIEW_STATE_OPEN_CMD;
                    }
                }
            }
            //add 20180625 orderMaxのチェックの追加
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            if (_gDrawOrderUnitFlag == 1) {
                //_gOrderUnit.setText(_gOrderUnitCount + _gNetaInfo.getUnit(_gOrderItemKey));
                _gOrderUnit.setText(_gOrderUnitCount + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(_gOrderItemKey)));
                _gDrawOrderUnitFlag = 0;
                //_gOrderUnit.setText(_gOrderUnitCount + _glocalStr.getUnitNameLocal(_gNetaInfo.getUnitInt(_gOrderItemKey)));
            }
            _gviewOrderOnTimer--;
            if (_gviewOrderOnTimer <= 0) {
                _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawOrderFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewOrder != null) {
                try {
                    _gMAIN_FRAME.removeView(_gviewOrder);
                    _gviewOrder = null;
                } catch (Exception e) {
                    _glog.logE("ERR:_gviewOrder remove:" + e.toString());
                }
            }
            _gviewOrderOnTimer = 0;
            _gDrawOrderFlag = F_VIEW_STATE_CLEAR;
            //add 20171024 OPT clear
            // _gOPT_OPT_TYPE=0;
            _gOPT_OPT_CODE = 0;
            _gOPT_OPT_CODE_1 = 0;
            _gOPT_OPT_CODE_2 = 0;
            _gOPT_SELECTED_OPT_CODE = 0;
        }
        //----------order　check-------------
        if (_gDrawOrderCheckFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gorderCheckView == null) {
                addViewOrderCheck();
                _gorderCheckViewOnTimer = _get_gORDER_CHECK_WAIT_TIME;
                _gViewMsgText.setText("確認中");//LoalStr設定の事
                _gDrawOrderCheckFlag = F_VIEW_STATE_LIVE;
            }
        } else if (_gDrawOrderCheckFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            if (_gthreadSendCmd.GetOrderCmdReturnState() > 0) {

                // TODO　add 20210817 原宿店屋台対応
                _gFoodStandOnFlag = _gNetaInfo.getFoodStand(_gOrderItemKey);
                if (_gUse_FoodStand == 1 && _gFoodStandOnFlag == 1 ) {
                    _gViewMsgText.setText("こちらの商品は屋台でお作りいたします。　　　準備が出来ましたらお知らせ致します。");
                    _gDrawOrderCheckFlag = F_VIEW_STATE_CLOSE_WAIT;
                    _gorderCheckViewOnTimer = 16;//change 20160725
                    _gSoundManager.PlaySound(16, 1);//音声要確認
                }else {
                    _gViewMsgText.setText(_glocalStr.LoStr("V1_LB_MEG_OK"));
                    _gDrawOrderCheckFlag = F_VIEW_STATE_CLOSE_WAIT;
                    _gorderCheckViewOnTimer = 11;//change 20160725
                    _gSoundManager.PlaySound(2, 1);//音声要確認
                }
                // TODO　add 20210817 原宿店屋台対応

            }
            _gorderCheckViewOnTimer--;
            // if (_gorderCheckViewOnTimer <= 0 || _gthreadSendCmd.GetOrderCmdReturnState() < 0 ) {
            if (_gorderCheckViewOnTimer <= 0 || _gthreadSendCmd.GetOrderCmdReturnState() <= 0) {
                //20161023 マイナス値のリターンのみ失敗とみなす。（要確認）
                if (_gthreadSendCmd.GetOrderCmdReturnState() < 0) {//マイナス値の場合は失敗とする。
                    // _glog.log("注文失敗の表示");
                    _gorderCheckViewOnTimer = 8;
                    _gDrawOrderCheckFlag = F_VIEW_STATE_CLOSE_WAIT;
                    _gViewMsgText.setText(_glocalStr.LoStr("V1_LB_MEG_NOTCONECT"));
                    _gSoundManager.PlaySound(3, 1);//音声要確認

                    // TODO add 20220124 通信LOG強化
                    _glog.LogJson(getApplicationContext(), "LOGCAT:ERR,MSG:注文失敗画面を表示" );
                    // TODO add 20220124 通信LOG強化
                } else {
                    _gViewMsgText.setText(_glocalStr.LoStr("V1_LB_MEG_OK"));
                    _gDrawOrderCheckFlag = F_VIEW_STATE_CLOSE_WAIT;
                    _gorderCheckViewOnTimer = 11;//change 20160725
                    _gSoundManager.PlaySound(2, 1);//音声要確認

                    // 20201224 ~ ver314 second request
                    if (_g_SECOND_REQUEST_ON == 1) {
                        _gViewMsgText.setText("セカンドリクエストを通知しました");
                    } else if (_g_SECOND_REQUEST_ON == -1) {
                        _gViewMsgText.setText("セカンドリクエストを通知に失敗しました");
                    }
                    // 20201224 ~ ver314 second request
                }
                //20161023 マイナス値のリターンのみ失敗とみなす。（要確認）
                //*********************************************************
            }
        } else if (_gDrawOrderCheckFlag == F_VIEW_STATE_CLOSE_WAIT) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _gorderCheckViewOnTimer--;
            if (_gorderCheckViewOnTimer <= 0) {
                _gDrawOrderCheckFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawOrderCheckFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gorderCheckView != null) {
                _gMAIN_FRAME.removeView(_gorderCheckView);
            }
            _gorderCheckView = null;
            _gorderCheckViewOnTimer = 0;
            _gDrawOrderCheckFlag = F_VIEW_STATE_CLEAR;
        }
        //----------order self-------------
        if (_gDrawOrderSelfFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewOrderSelf == null) {
                addViewOrderSelf();
            }
        } else if (_gDrawOrderSelfFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット

        } else if (_gDrawOrderSelfFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewOrderSelf != null) {
                _gMAIN_FRAME.removeView(_gviewOrderSelf);
                _gviewOrderSelf = null;
            }
            _gDrawOrderSelfFlag = F_VIEW_STATE_CLEAR;
        }
        //----------OrderLmit page-------------
        if (_gDrawOrderLmitMsgFlag == F_VIEW_STATE_OPEN_CMD) {
            //_gviewOrderLmitMsgOnTimer = 20;
            _gviewOrderLmitMsgOnTimer = 500;//20181013 長時間自動OFFしないように設定
            if (_gviewOrderLmitMsg == null) {
                //add 20181210
                if (_gDrawArriveKousokuFlag != F_VIEW_STATE_CLEAR || _gDrawArriveFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
                } else {
                    _gDrawOrderLmitMsgFlag = F_VIEW_STATE_LIVE;
                    addViewOrderLmit();
                }
            }
        } else if (_gDrawOrderLmitMsgFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _gviewOrderLmitMsgOnTimer--;
            if (_gviewOrderLmitMsgOnTimer <= 0) {
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
            }
            //add 20181210
            if (_gDrawArriveKousokuFlag != F_VIEW_STATE_CLEAR || _gDrawArriveFlag != F_VIEW_STATE_CLEAR) {
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawOrderLmitMsgFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewOrderLmitMsg != null) {
                _gMAIN_FRAME.removeView(_gviewOrderLmitMsg);
                _gviewOrderLmitMsg = null;
            }
            _gviewOrderLmitMsgOnTimer = 0;
            _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLEAR;
        }

        //----------arrive under----上到着のケースとviewを分ける必要がある？
        // TODO　add 20210817 原宿店屋台対応 確認画面で止められ間、上の到着が表示されない　
        if (_gDrawArriveFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {//ｽｸﾘｰﾝｾｰﾊﾞｰオフ
                _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
            }
            if ( _garriveUnderView == null) {
            //if (_garriveView == null) {
                // _glog.log("★下　ON _gDrawArriveFlag:" + _gDrawArriveFlag + " cnt:" +  _garriveUnderViewOnTimer);
                addViewUnderArrive();
                // addViewArrive();
                ViewArriveUnderDraw();
                _gDrawArriveFlag = F_VIEW_STATE_LIVE;
                _garriveUnderViewOnTimer = _gkousoku_arraive_show_count;

                // TODO　add 20210817 原宿店屋台対応
                if (_gUse_FoodStand == 1 && _gFoodStandOnFlag == 1 ){
                    _glog.log("FOODSTAND　ON");
                    // TODO　屋台村対応音声
                    _gSoundManager.PlaySound(17, 1);
                    // 表示時間を延長
                    _garriveUnderViewOnTimer =  _garriveUnderViewOnTimer + _gFoodStandAlertTimer;
                } else {
                    // 通常到着音声
                    _gSoundManager.PlaySound(4, 1);
                }
                // TODO　add 20210817 原宿店屋台対応
            }
        } else if (_gDrawArriveFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _garriveUnderViewOnTimer--;
            // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
            _gOrderUnderInfo.doAriveorder();

            // _glog.log("★下　LIVE _gDrawArriveFlag:" + _gDrawArriveFlag + " cnt:" +  _garriveUnderViewOnTimer);

            if (_garriveUnderViewOnTimer == 0) {
                _gDrawArriveFlag = F_VIEW_STATE_CLOSE_CMD;
            }

            // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
            if (_gUse_FoodStand == 1 && _gFoodStandOnFlag == 1 ) {
                /*
                FrameLayout layout = (FrameLayout) findViewById(R.id.ARRIVE_FOOD_STAND_LAYOUT);
                layout.setVisibility(View.GONE);
                */
                _gViewArriveUnderFoodStandLayout.setVisibility(View.GONE);
                /*
                if (_garriveUnderViewOnTimer % 5 == 0) {
                    // _gViewArriveUnderFoodStandLayout.setVisibility(View.INVISIBLE);
                    _gViewArriveUnderFoodStandLayout.setVisibility(View.VISIBLE);
                } else {
                    // _gViewArriveUnderFoodStandLayout.setVisibility(View.VISIBLE);
                    _gViewArriveUnderFoodStandLayout.setVisibility(View.INVISIBLE);
                }
                */

                // TODO add 20211211 坂本MG表示変更要望のため暫定的に対応
                _garriveFoodStandDispCount++;
                if( _garriveFoodStandDispCount > 12){
                    _garriveFoodStandDispCount = 0;
                }
                if( _garriveFoodStandDispCount < 6) {
                    // _gViewArriveUnderFoodStandLayout.setVisibility(View.INVISIBLE);
                    _gViewArriveUnderFoodStandLayout.setVisibility(View.VISIBLE);
                } else {
                    // _gViewArriveUnderFoodStandLayout.setVisibility(View.VISIBLE);
                    _gViewArriveUnderFoodStandLayout.setVisibility(View.INVISIBLE);
                }
                // TODO add 20211211 坂本MG表示変更要望のため暫定的に対応

                /* 　相互に文言を換えるパターン
                if (_garriveViewOnTimer % 2 == 0 ) {
                    // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
                    tv = (TextView) findViewById(R.id.ARRIVE_TITLE);
                    if(_gFoodStandTypeIceOnFlag == 1) {
                        tv.setText("アイスはその場でお作りいたします！");
                        tv.setBackgroundColor(Color.parseColor("#0000cd"));
                    }
                } else {
                    tv = (TextView) findViewById(R.id.ARRIVE_TITLE);
                    tv.setText("スイーツ屋台までお越し願います。");
                    tv.setBackgroundColor(Color.parseColor("#a52a2a"));
                }
                */
                /*
                TextView tv = (TextView) findViewById(R.id.ARRIVE_FOOD_STAND_MSG);
                if(_gFoodStandTypeIceOnFlag == 1) {
                    tv.setText("アイスはその場でお作りいたします！");
                    // tv.setBackgroundColor(Color.parseColor("#0000cd"));
                } else {
                    tv.setText("");
                    // tv.setBackgroundColor(Color.parseColor("#a52a2a"));
                }
                 */
            }
            // TODO　add 20210817 原宿店屋台対応　メッセージを点滅する形で検討

        } else if (_gDrawArriveFlag == F_VIEW_STATE_LIVE_ADD) {
            // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
            if (_gUse_FoodStand == 1 && _gFoodStandOnFlag ==1 ){
                _glog.log("FOODSTAND　ON");
                // TODO　屋台村対応音声
                _gSoundManager.PlaySound(17, 1);
                // 表示時間を延長
                _garriveUnderViewOnTimer =  _garriveUnderViewOnTimer + _gFoodStandAlertTimer;
            } else {
                // 通常到着音声
                _gSoundManager.PlaySound(4, 1);
                _garriveUnderViewOnTimer = _gkousoku_arraive_show_count;
            }
            // _gSoundManager.PlaySound(4, 1);
            // _garriveViewOnTimer = _gkousoku_arraive_show_count;
            // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
            _gDrawArriveFlag = F_VIEW_STATE_LIVE;
            ViewArriveUnderDraw();
        } else if (_gDrawArriveFlag == F_VIEW_STATE_CLOSE_CMD) {
            // _glog.log("★下　close _gDrawArriveFlag:" + _gDrawArriveFlag + " cnt:" +  _garriveUnderViewOnTimer);
            /*
            if (_garriveView != null) {
                _gMAIN_FRAME.removeView(_garriveView);
                _garriveView = null;
            }
            */
            if (_garriveUnderView != null) {
                _gMAIN_FRAME.removeView(_garriveUnderView);
                _garriveUnderView = null;
            }


            // TODO　add 20210817 原宿店屋台対応 データーがプールされているケースを考慮し、処理を分岐
            // _gOrderUnderInfo.cleanAriveorder();
            if (_gUse_FoodStand == 1 && _gFoodStandOnFlag == 1 ){
                // _gOrderUnderInfo.clearFoodStanddispCount();
            }else{
                _gOrderUnderInfo.cleanAriveorder();
            }

            _gDrawArriveFlag = F_VIEW_STATE_CLEAR;
            // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
            _gFoodStandOnFlag = 0;
            // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
            _gFoodStandTypeIceOnFlag = 0;
            // TODO add 20211211 坂本MG表示変更要望のため暫定的に対応
            _garriveFoodStandDispCount = 0;
        }
        // TODO　add 20210817 原宿店屋台対応 確認画面で止められ間、上の到着が表示されない　

        //----------arrive Kousoku-------------
        // TODO　add 20210817 原宿店屋台対応 屋台対応 プログラムの配置場所を下到着の後に記載して優先順位を変更
        if (_gDrawArriveKousokuFlag == F_VIEW_STATE_OPEN_CMD) {
            //change 20190110
            if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
            }
            _garriveViewKousokuWaitOnTimer--;
            if (_garriveViewKousokuWaitOnTimer <= 0) {
                if (_garriveView == null) {
                    _glog.log("☆高速　ON _gDrawArriveFlag:" + _gDrawArriveFlag); //   _garriveUnderViewOnTimer
                    addViewArrive();
                    // TODO 20210703 表示方式を交互に変更 この関数は複数回呼ばれる可能性があるためステップを設けて処理をわける
                    // ViewArriveKousokuDraw();
                    ViewArriveKousokuDraw(0);
                    _garriveViewKousokuWaitOnTimer = 0;
                    // TODO 20210703 表示方式を交互に変更
                    // _garriveViewKousokuOnTimer = _gkousoku_arraive_show_count;
                    if(_gDoublePlateAlret_USE == 1) {
                        if( _gDoublePlateAlretFlag ==1) {
                            if(_gDoublePlateFist_USE ==1 && _gDoublePlateFistOrderFlag > 1 ){
                                _garriveViewKousokuOnTimer = _gkousoku_arraive_show_count;
                                // _glog.log("☆２００円２回目以降 _garriveViewKousokuOnTimer:"+_garriveViewKousokuOnTimer);
                                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:DoublePlateAlret,type:doubleused,count:" + _garriveViewKousokuOnTimer);

                            }else {
                                _garriveViewKousokuOnTimer = _gDoublePlateOffCount * 2 + _gDoublePlateOnCount * 2;
                                // _glog.log("☆長い _garriveViewKousokuOnTimer:" + _garriveViewKousokuOnTimer);
                                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:DoublePlateAlret,type:double,count:" + _garriveViewKousokuOnTimer);
                            }
                        } else {
                            _garriveViewKousokuOnTimer = _gkousoku_arraive_show_count;
                            // _glog.log("☆普通 _garriveViewKousokuOnTimer:"+_garriveViewKousokuOnTimer);
                            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:DoublePlateAlret,type:usual,count:" + _garriveViewKousokuOnTimer);
                        }
                    }else{
                        _garriveViewKousokuOnTimer = _gkousoku_arraive_show_count;
                    }
                    // TODO 20210703 表示方式を交互に変更
                    _gDrawArriveKousokuFlag = F_VIEW_STATE_LIVE;
                    // TODO 20210703 表示方式を交互に変更
                    // 音声を２回ならしたいたい。   ViewArriveKousokuDraw()で鳴らすように変更
                    // _gSoundManager.PlaySound(4, 1);
                }
            }
        } else if (_gDrawArriveKousokuFlag == F_VIEW_STATE_LIVE) {
            // TODO　add 20211229 高速レーンの商品が重なった時に再表示する確認をする
            // 高速レーンの描画を伸ばした事で描画中に次の描画情報が来ると処理されないという問題が生じている
            // 高速レーンの描画中、この処理をフラグを確認し、１なら再描画する
            if(_gOrderHeightInfo.getReDrawFlag() == 1){
                ViewArriveKousokuDraw(0);
                _garriveViewKousokuOnTimer = _gkousoku_arraive_show_count;
            }
            // TODO　add 20211229 高速レーンの商品が重なった時に再表示する確認をする

            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _garriveViewKousokuOnTimer--;

            // _glog.log("☆高速　LIVE _gDrawArriveFlag:" + _gDrawArriveFlag + " 下タイマー:" + _garriveUnderViewOnTimer); //
            // TODO marge ２皿対応
            if(_gDoublePlateAlret_USE == 1){
                // TODO 20210703 表示方式を交互に変更
                /*
                if(_garriveViewKousokuOnTimer  == _gDoublePlateAlretValue){
                    ViewArriveKousokuDoubelPlateDraw();
                }
                */
                // _glog.log("_garriveViewKousokuOnTimer:" + _garriveViewKousokuOnTimer);
                if(_garriveViewKousokuOnTimer == ( _gDoublePlateOffCount + _gDoublePlateOnCount  + _gDoublePlateOffCount )
                        && _gDoublePlateAlretFlag == 1){
                    if(_gDoublePlateFist_USE ==1 && _gDoublePlateFistOrderFlag > 1 ){
                        _glog.log("☆１回目:☆２００円２回目以降");
                    }else {
                        _glog.log("☆１回目:" + _garriveViewKousokuOnTimer);
                        ViewArriveKousokuDoubelPlateDraw();
                    }

                }
                if(_garriveViewKousokuOnTimer ==  ( _gDoublePlateOnCount  + _gDoublePlateOffCount )
                        && _gDoublePlateAlretFlag == 1){
                    if(_gDoublePlateFist_USE ==1 && _gDoublePlateFistOrderFlag > 1 ){
                        _glog.log("☆2回目:☆２００円２回目以降");
                    }else {
                        _glog.log("☆2回目:" + _garriveViewKousokuOnTimer);
                        ViewArriveKousokuDraw(1);
                    }
                }
                if(_garriveViewKousokuOnTimer  == _gDoublePlateOffCount
                        && _gDoublePlateAlretFlag == 1){
                    if(_gDoublePlateFist_USE ==1 && _gDoublePlateFistOrderFlag > 1 ){
                        _glog.log("☆3回目:☆２００円3回目以降");
                    }else {
                        _glog.log("☆3回目:" + _garriveViewKousokuOnTimer);
                        ViewArriveKousokuDoubelPlateDraw();
                    }
                }
                // TODO 20210703 表示方式を交互に変更
            }
            // TODO marge ２皿対応
            if (_garriveViewKousokuOnTimer <= 0) {
                _gDrawArriveKousokuFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawArriveKousokuFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_garriveView != null) {
                _gMAIN_FRAME.removeView(_garriveView);
                _garriveView = null;
            }
            _gOrderHeightInfo.clearOrder();
            _gDrawArriveKousokuFlag = F_VIEW_STATE_CLEAR;
            _garriveViewKousokuOnTimer = 0;
            _garriveViewKousokuWaitOnTimer = 0;

            // _glog.log("☆高速　OFF  下状態　_gDrawArriveFlag:" + _gDrawArriveFlag); //   _garriveUnderViewOnTimer
        }
        // TODO　add 20210817 原宿店屋台対応 屋台対応 プログラムの配置場所を下到着の後に記載して優先順位を変更

        //----------DrawUpdateAlertFlag-------------
        if (_gDrawUpdateAlertFlag == F_VIEW_STATE_OPEN_CMD && _gviewUpdateAlert == null) {
            if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {//ｽｸﾘｰﾝｾｰﾊﾞｰオフ
                _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
            }
            if (_gviewUpdateAlert == null) {
                addViewUpdateAlert();
                _gViewMsgText.setText(_glocalStr.LoStr("V8_LB_UPDATE"));
                _gDrawUpdateAlertFlag = F_VIEW_STATE_LIVE;
            }
        } else if (_gDrawUpdateAlertFlag == F_VIEW_STATE_LIVE) {
            if (_gviewUpdateAlertOnTimer > 0) {
                _gviewUpdateAlertOnTimer--;
                if (_gviewUpdateAlertOnTimer <= 0) {
                    _gDrawUpdateAlertFlag = F_VIEW_STATE_CLOSE_CMD;
                }
            }
        } else if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewUpdateAlert != null) {
                _gMAIN_FRAME.removeView(_gviewUpdateAlert);
                _gviewUpdateAlert = null;
            }
            _gviewUpdateAlertOnTimer = 0;
            _gDrawUpdateAlertFlag = F_VIEW_STATE_CLEAR;
        }

        /* アンケート画面を展開 */
        if (_gDrawSurveyFlag == F_VIEW_STATE_OPEN_CMD) {
            _gDrawSurveyFlag = F_VIEW_STATE_LIVE;
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:kaieflag_open, UDPCODE:" + _gUSE_SURVEY_FLAG + ",DEBUG_TAG:NODE");
            this.addViewSurvey();
            /* アンケート画面を表示中 */
        } else if (_gDrawSurveyFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
//            if (_gviewSurvey == null) {
//                _gDrawSurveyFlag = F_VIEW_STATE_CLOSE_CMD;
//            }
            /* アンケート画面を閉じる */
        } else if (_gDrawSurveyFlag == F_VIEW_STATE_CLOSE_CMD) {
            _gDrawSurveyFlag = F_VIEW_STATE_CLEAR;
            if (_gviewSurvey != null) {
                _gMAIN_FRAME.removeView(_gviewSurvey);
                _gviewSurvey = null;
                /* 引き続き自動会計画面を展開 */
                if (_gSURVEY_DONE_FLAG == 1) {
                    _gKEY_ALERT_USE_FLAG = 1;
                    _gTestOpenCountButtonOn = 0;
                    _gthreadSendCmd.setFlagReqKaikeiRirekiOn();
                    _gthreadSendCmd.setFlagCheackOn();//即時発行
                    _gDrawKaikeiFlag = F_VIEW_STATE_OPEN_CMD;
                } else {
                    wv = null;
                }
            }
        }

        /**
         * 押上店向け　会計時のQRコード確認画面
         */
         // TODO 20220305 #34 押上店向けー会計画面の前にQR読み込みの警告画面を出す
        if (_gDrawDxGameKaikeiAlertFlag == F_VIEW_STATE_OPEN_CMD) {
            // _glog.log("会計QRアラートOPEN");
            // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
            if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
            }
            if(_gviewDxGameKaikeiAlert == null) { // TODO 200328 会計アラートが2重に呼ばれている可能性がある。
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:_gDrawDxGameAlertOpen,MSG:会計前チケット確認OPEN, DEBUG_TAG:DxGame");
                _gDrawDxGameKaikeiAlertFlag = F_VIEW_STATE_LIVE;
                _gDxGameKaikeiAlertOnTimer = 300;
                this.addViewDxGameKaikeiAlert();
            } // TODO 200328 会計アラートが2重に呼ばれている可能性がある。
        } else if (_gDrawDxGameKaikeiAlertFlag  == F_VIEW_STATE_LIVE) {
            _glog.log("会計QRアラートLIVE" +   _gDxGameKaikeiAlertOnTimer );
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _gDxGameKaikeiAlertOnTimer--;
            // TODO 20220318　会計リセットでクリアすると無限ループしているパターンが見られる
            _gDxGameKaikeiAlertCheckFlag = 1;
            if(_gDxGameKaikeiAlertOnTimer <= 0){
                _gDrawDxGameKaikeiAlertFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawDxGameKaikeiAlertFlag  == F_VIEW_STATE_CLOSE_CMD)  {
            _glog.log("会計QRアラートCLOSE");
            if (_gviewDxGameKaikeiAlert!= null) {
                _gMAIN_FRAME.removeView(_gviewDxGameKaikeiAlert);
                _gviewDxGameKaikeiAlert = null;
            }
            _gDxGameKaikeiAlertOnTimer = 0;
            _gDrawDxGameKaikeiAlertFlag = F_VIEW_STATE_CLEAR;
            _gDrawKaikeiFlag = F_VIEW_STATE_OPEN_CMD;
            // TODO 20220318　会計リセットでクリアすると無限ループしているパターンが見られる。リセットの場合ここにこないケースもある
            // 会計画面を複数押す場合を考慮するならここでフラグを０にすべきか？
              _gDxGameKaikeiAlertCheckFlag = 1;
        }
        // TODO 20220305 #34 押上店向けー会計画面の前にQR読み込みの警告画面を出す
        /**
         * 会計画面を展開
         * 押上店のチケット確認用。会計画面の前にチケットが存在する場合は
         * チケットを確認してから会計画面に遷移させる
         */
         // _glog.log("会計！" +  "_gLinkQRMain" + _gLinkQRMainViewButtonOnFlag + "INKQRCODE_USE_ON " + _FLAG_LINKQRCODE_USE_ON + "KaikeiAlertCheck " + _gDxGameKaikeiAlertCheckFlag );


        // TODO 20220325 #43 会計ボタンを押した段階でチェックするように変更する
        // TODO 20220305 #34 押上店向けー会計画面の前にQR読み込みの警告画面を出す
        /*
        if( _gDrawKaikeiFlag == F_VIEW_STATE_OPEN_CMD
                && _gLinkQRMainViewButtonOnFlag == 1
                && _FLAG_LINKQRCODE_USE_ON == 1
                && _gDxGameKaikeiAlertCheckFlag ==0
        ) {
            // _glog.log("会計！" +  "_gLinkQRMainViewButtonOnFlag" + _gLinkQRMainViewButtonOnFlag);
            // _glog.log("会計！" +  "_FLAG_LINKQRCODE_USE_ON " + _FLAG_LINKQRCODE_USE_ON );
            // _glog.log("会計！" +  "_gDxGameKaikeiAlertCheckFlag " + _gDxGameKaikeiAlertCheckFlag );
            // QRの当たりコードを先に確認するため、一旦会計画面を開けない
            _gDrawKaikeiFlag = F_VIEW_STATE_CLEAR;
            // QR確認画面をあける
            _gDrawDxGameKaikeiAlertFlag = F_VIEW_STATE_OPEN_CMD;
            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:kaieflag_open, MSG:会計でQR確認画面を表示,DEBUG_TAG:DxGame");
        }
        */
        // TODO 20220305 #34 押上店向けー会計画面の前にQR読み込みの警告画面を出す

        if (_gDrawKaikeiFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewKaikei == null) {
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                if (_gDrawCallFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawCallFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                _gDrawKaikeiFlag = F_VIEW_STATE_LIVE;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:kaieflag_open, UDPCODE:" + _gUSE_AUTO_CHECK_FLAG + ",DEBUG_TAG:NODE");

                /**
                 * NTT自動会計画面を使用
                 */
                if (_gUSE_AUTO_CHECK_FLAG == 1) {
                    // this.addViewAutoCheck();
                    // TODO　20220108 ver 406 のマージ
                    if (spCheckStatus == F_SPCHECK_STATUS_CHECKING) {
                        this.addViewSpCheck();
                    } else if (spCheckStatus == F_SPCHECK_STATUS_CALLING) {
                        this.setCallFlag();
                        this.callViewDisp();
                    } else {
                        this.addViewAutoCheck();
                    }
                    // TODO　20220108 ver 406 のマージ
                    /**
                     * くら自動会計画面を使用
                     */
                } else if (_gUSE_AUTO_KAIKEI_FLAG == 1) {
                    this.addViewKaikei2();
                    /**
                     * 既存会計画面を使用
                     */
                } else {
                    this.addViewKaikei();
                    addViewKaikeiDraw();
                }
                //戻るボタン制御用
                _gKAIKEI_On_For_BackButton = 0;
                _gKAIKEI_For_BackButton_Count = 0;
            }
            /**
             * 会計画面を表示中
             */
        } else if (_gDrawKaikeiFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット

            if (_gUSE_AUTO_KAIKEI_FLAG == 1) {

            } else if (_gviewKaikei != null) {
                /**
                 * 既存会計画面の更新
                 */
                addViewKaikeiDraw();
                _gKAIKEI_On_For_BackButton++;//会計ボタンの挙動制御用
                if (_gDrawCallFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawCallFlag = F_VIEW_STATE_CLOSE_CMD;
                }
            }
            /**
             * 会計画面を閉じる
             */
        } else if (_gDrawKaikeiFlag == F_VIEW_STATE_CLOSE_CMD) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _gDrawKaikeiFlag = F_VIEW_STATE_CLEAR;
            /**
             * 自動会計画面を閉じる
             */
            if (_gviewAutoCheck != null) {
                _gMAIN_FRAME.removeView(_gviewAutoCheck);
                _gviewAutoCheck = null;
                wv = null;
            }
            // TODO　20220108 ver 406 のマージ
            /**
             * スマホ会計中画面を閉じる
             */
            if (_gviewSpCheck != null) {
                socketDisconnect();
                _gMAIN_FRAME.removeView(_gviewSpCheck);
                //_gDrawCallFlag = F_VIEW_STATE_CLEAR;
                _gviewSpCheck = null;
                spCheckWv = null;
                spCheckStatus = F_SPCHECK_STATUS_NOT_CHECKING;
            }
            // TODO　20220108 ver 406 のマージ

            /**
             * 既存会計画面を閉じる
             */
            if (_gUSE_AUTO_KAIKEI_FLAG == 1) {
                if (_gviewKaikei2 != null) {

                    //add2018 1215 キャッシュクリア
                    WebView _web = (WebView) findViewById(R.id.kaikei_web);
                    // _gMAIN_FRAME.removeView(_web);
                    // _web.onPause();
                    //_web.pauseTimers();
                    _web.stopLoading();
                    //_web.clearCache(true);
                    _web.clearHistory();
                    _web.removeAllViews();
                    _web.setWebChromeClient(null);
                    _web.setWebViewClient(null);
                    unregisterForContextMenu(_web);
                    _web.destroy();
                    _web = null;
                    //add2018 1215 キャッシュクリア

                    _gMAIN_FRAME.removeView(_gviewKaikei2);
                    _gviewKaikei2 = null;
                    _gKaikeiInfo.clearRireki();
                }
            } else {
                if (_gviewKaikei != null) {
                    _gMAIN_FRAME.removeView(_gviewKaikei);
                    _gviewKaikei = null;
                    _gKaikeiInfo.clearRireki();
                }
            }
            _gTableDraw_Flag = 1;
            //戻るボタン制御用
            _gKAIKEI_On_For_BackButton = 0;
            _gKAIKEI_For_BackButton_Count = 0;


            // TODO #41 チケット管理サーバーの会計前確認画面がもしのこっていたら消す。効くかわからないので、現象発生してもいみなければ消去の事
            if (_gviewDxGameKaikeiAlert != null && _FLAG_LINKQRCODE_USE_ON == 1) {
                _gMAIN_FRAME.removeView(_gviewDxGameKaikeiAlert);
                _gviewDxGameKaikeiAlert = null;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:kaieflag_closer, MSG:会計でQR確認画面を表示,DEBUG_TAG:DxGame");
            }
            // TODO #41 チケット管理サーバーの会計前確認画面がもしのこっていたら消す。効くかわからないので、現象発生してもいみなければ消去の事

            // TODO 20220305 #34 押上店向けー会計画面の前にQR読み込みの警告画面を出す
            // TODO 20220318　会計リセットでクリアすると無限ループしているパターンが見られる。案内時に０にするように変更する
            // gDxGameKaikeiAlertCheckFlag = 0;
        }

        //----------move-------------
        // 20200608 Opning MOVIE
        if (_gDrawOpenningMoveFlag == F_VIEW_STATE_OPEN_CMD &&
                _gUSE_OpenningMove_FLAG == 1 &&
                _gDrawQrCodeFlag == F_VIEW_STATE_CLEAR
            //        _gDrawFlashingFlag == F_VIEW_STATE_CLEAR
        ) {
            if (_gviewOpenningMove == null) {
                _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                    _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                }
                addViewOpenningMov(_gMoveInfo.getOpenningMoveName(_gOpenningMoveNumber));
                _gDrawOpenningMoveFlag = F_VIEW_STATE_LIVE;
                _gOpenningMoveOnTimer = 300;
            }
        } else if (_gDrawOpenningMoveFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            if (_gviewOpenningMove != null) {
                if (!_gvideoViewOpenningMove.isPlaying() && _gPreparedMovFlag == 1) {
                    _gvideoViewOpenningMove.start();
                    _gPreparedMovFlag = 0;
                    // _glog.log("OpenningMove start!!");
                }
            }
            _gOpenningMoveOnTimer--;
            // _glog.log("OpenningMove live:" +  _gOpenningMoveOnTimer);
            if (_gOpenningMoveOnTimer == 290) {
                //  _glog.log("OpenningMove api:" +  _gApiVersion);

                // TODO 20220223 案内動画でボタンが表示されない対策　この処理は上手く動いていない可能性があるためコメントアウト
                /*
                if (_gApiVersion < 21) {
                    // _glog.log("OpenningMove button on:" +  _gApiVersion);
                    Button bt = (Button) findViewById(R.id.btMOV2_back);
                    bt.setVisibility(View.VISIBLE);
                }
                */
                // TODO 20220223 案内動画でボタンが表示されない対策　
            }
            if (_gOpenningMoveOnTimer <= 0) {
                // _glog.log("__gOpenningMoveOnTimer:"+ _gOpenningMoveOnTimer );
                _gDrawOpenningMoveFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawOpenningMoveFlag == F_VIEW_STATE_CLOSE_CMD) {
            _glog.log("OpenningMove CLOSE");
            if (_gviewOpenningMove != null) {
                try {
                    _gDrawOpenningMoveFlag = F_VIEW_STATE_CLEAR;
                    _gOpenningMoveOnTimer = 0;
                    _gPreparedMovFlag = 0;
                    _gTableDraw_Flag = 1;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:viewOpenningMove_CLOSE");
                    _gvideoViewOpenningMove = null;
                    _gMAIN_FRAME.removeView(_gviewOpenningMove);
                    _gviewOpenningMove = null;
                } catch (Exception e) {
                    _glog.log("OpenningMove CLOSE err:" + e.toString());
                    _gMAIN_FRAME.removeView(_gviewOpenningMove);
                    _gviewOpenningMove = null;
                    _gDrawOpenningMoveFlag = F_VIEW_STATE_CLOSE_CMD;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:ERR,CMD:REMOVE_gviewOpenningMove:" + e.toString());
                }
            }
            _gDrawOpenningMoveFlag = F_VIEW_STATE_CLEAR;
        }

        // screen server
        if (_gDrawMovFlag == F_VIEW_STATE_OPEN_CMD &&
                _gDrawGameFlag == F_VIEW_STATE_CLEAR
                // TODO　add 20210817 原宿店屋台対応 確認画面で止められ間、上の到着が表示されない　
                && _garriveUnderView == null
                && _garriveView == null
                && _gviewOrderLmitMsg == null
                && _gviewOrderSelf == null
                && _gorderCheckView == null
                && _gviewKaikei == null
                && _gviewKousokuBack == null
                && _gviewKeyAlert == null) {

            if (_gviewMov == null) {
                _glog.log("_gDrawMovFlag 2 :" + _gDrawMovFlag);
                String f_name = "";
                // ver82 20170613 CMの場合の処理を追加
                if (_FLAG_CM_MOVE_ON == 0) { //Screen
                    f_name = _gMoveInfo.getScreenMoveStr(1);
                } else {// CM
                    f_name = _gMoveInfo.getScreenMoveStrCm(_FLAG_CM_MOVE_ON);
                    //CM終了後のpageのしていは？
                    _FLAG_CM_MOVE_ON = 0;
                }
                /*ｽｸﾘｰﾝｾｰﾊﾞｰがの動画番号が固定になっている修正検討の事
                String f_name = _gMoveInfo.getScreenMoveStr(1);*/
                if (f_name == null) {
                    Log.i(_TAG, "_gDrawMovFlag moveが無い");
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                } else {
                    addViewMov(f_name);
                    _gDrawMovFlag = F_VIEW_STATE_LIVE;
                    _gviewMovOnTimer = 300;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:MOVE_ON");
                }
            }
        } else if (_gDrawMovFlag == F_VIEW_STATE_LIVE) {
            //add 20181121 準備をから再生するするよう確認してに変更
            if (_gvideoView != null) {
                if (!_gvideoView.isPlaying() && _gPreparedMovFlag == 1) {
                    _gvideoView.start();
                    _gPreparedMovFlag = 0;
                }
            }
            _gviewMovOnTimer--;
            if (_gviewMovOnTimer <= 0) {
                _glog.log("_gviewMovOnTimer " + _gviewMovOnTimer);
                _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawMovFlag == F_VIEW_STATE_CLOSE_CMD) {
            _glog.log("A  gviewMov _gDrawMovFlag" + _gDrawMovFlag);
            if (_gviewMov != null) {
                try {
                    // _glog.log("_gviewMov: revome 1");
                    _gMAIN_FRAME.removeView(_gviewMov);
                    _gviewMov = null;
                    _gDrawMovFlag = F_VIEW_STATE_CLEAR;
                    _gviewMovOnTimer = 0;
                    _gPreparedMovFlag = 0;
                    _gTableDraw_Flag = 1;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:MOVE_REMOVE");
                    //_glog.LogJson(getApplicationContext(), "_gviewMov: revome 2");
                    //_gthreadGetFile.setGetNetaInfo_Flag();
                } catch (Exception e) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                    _glog.log("LOGCAT:ERR,CMD:REMOVE__gviewMov:" + e.toString());
                    _glog.LogJson(getApplicationContext(), "LOGCAT:ERR,CMD:REMOVE__gviewMov:" + e.toString());
                }
            } else {
                //add 20190110
                _gDrawMovFlag = F_VIEW_STATE_CLEAR;
                _glog.log("B  gviewMov _gDrawMovFlag" + _gDrawMovFlag);
                _gviewMovOnTimer = 0;
                _gPreparedMovFlag = 0;
            }
        }

        //----------game-------------
        //state game start ゲームの長さが取得できるまでは開始しない。
        if (_gDrawGameFlag == F_VIEW_STATE_OPEN_CMD) {

            // TODO 20211120 原宿店のスイーツ対応で到着画面が出ているとgameが開始出来ずに固まるので、到着画面をクローズする。
            if(_gDrawArriveFlag != F_VIEW_STATE_CLEAR){
                _gDrawArriveFlag = F_VIEW_STATE_CLOSE_CMD;
                return;
            }
            // TODO 20211120 原宿店のスイーツ対応で到着画面が出ているとgameが開始出来ずに固まるので、到着画面をクローズする。

            //add 2019 0614 スマホからのビックラポン再生は強制実行
            if (_gSmartGameCount > 0) {
                SmartGameOpen();
                // TODO ADD 20220324 ゲーム前に取得に変更してみる。
                if(_gthreadFtpClient.CheckQrFile() == false && _FLAG_LINKQRCODE_USE_ON == 1 ){
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:101,Msg:ゲーム前確認　Qrコード画像が存在しないため取得,TAG:DxGame,KaikeiAlertCheck:" + _gDxGameKaikeiAlertCheckFlag);
                    _gthreadFtpClient.SetQrFtpFlag();
                    Thread th = new Thread(_gthreadFtpClient);
                    th.start();
                }
                // TODO ADD 20220324 ゲーム前に取得に変更してみる。
            } else {
                //ver83 20170614 game skipがONの場合はgameを実施しない。
                if (_gSKIP_GAME_USE == 1 && _gSKIP_GAME_USE_ENABLE_FLAG == 1) {
                    _glog.log("ゲームスキップONのためゲームをキャンセル");
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Game,GAMESTATE:SKIP_GAME");
                    if (_gCurrentGame == 2) {
                        _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Game,GAMESTATE:SKIP_WIN");
                    }
                    _gCurrentGame = 0;
                    _gGameSoundOn = 0;
                    _gDrawGameFlag = F_VIEW_STATE_CLEAR;
                // TODO add 20210913 get QRINFO　  QRINFOが有効な場合はQR読み取り画面を表示
                } else if(_gDrawLinkQRCodeFlag !=F_VIEW_STATE_CLEAR ) {
                    // _glog.log("QR当たり画面表示中:" + _gviewLinkQRCodeOnTimer );
                 // TODO add 20210913 get QRINFO　  QRINFOが有効な場合はQR読み取り画面を表示
                } else {
                    _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                    if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                        _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                        _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                    }
                    if (_gviewMov == null && _gviewMovForGame == null) {
                        String f_name = _gMoveInfo.get_GameDougaName(_gCurrentGame);
                        _glog.log("ゲームStart" + f_name + "皿" + _gSettingInfo.GetSaraCnt());
                        addViewMovGame(f_name);
                        _gviewGameOnTimer = 100;
                        _gPousePosition = 0;
                        _gDrawGameFlag = F_VIEW_STATE_LIVE_WAIT;
                    }
                    // TODO ADD 20220324 ゲーム前に取得に変更してみる。
                    if(_gthreadFtpClient.CheckQrFile() == false && _FLAG_LINKQRCODE_USE_ON == 1 ){
                       _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:101,Msg:ゲーム前確認　Qrコード画像が存在しないため取得,TAG:DxGame,KaikeiAlertCheck:" + _gDxGameKaikeiAlertCheckFlag);
                       _gthreadFtpClient.SetQrFtpFlag();
                       Thread th = new Thread(_gthreadFtpClient);
                       th.start();
                    }
                    // TODO ADD 20220324 ゲーム前に取得に変更してみる。
                }
            }
        } else if (_gDrawGameFlag == F_VIEW_STATE_LIVE_WAIT) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            //一定時間動画の準備ができなかったらクローズさせる。
            _gviewGameOnTimer--;
            if (_gviewGameOnTimer <= 0) {
                _gDrawGameFlag = F_VIEW_STATE_CLOSE_CMD;
            }
            //動画の準備ができたら開始。
            if (_gGamePlayTimeLength > 0) {
                _glog.log("ゲーム準備OK:" + _gGamePlayTimeLength);
                _glog.log("_gCurrentGame:" + _gCurrentGame);
                _gDrawGameFlag = F_VIEW_STATE_LIVE;
                _gviewGameOnTimer = _gGamePlayTimeLength + 1000 * 2;//強制的に終了させる時間
                _gGamePlayCount = _gGamePlayTimeLength;//ゲームの再生時間を設定
                _gvideoViewForGame.start();
            }
            //state game live
        } else if (_gDrawGameFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            //３つの割り込み条件を設定。到着画面下、到着画面上、高速バック
            //いずれかに該当すれば、ゲームを中断させる。
            //バグ切り分けのため、中断処理は認識のみしてみる。　20160603
            if (_gDrawArriveFlag != F_VIEW_STATE_CLEAR || _gDrawArriveKousokuFlag != F_VIEW_STATE_CLEAR
                    || _gDrawKousokuBackFlag != F_VIEW_STATE_CLEAR || _gDrawUpdateAlertFlag != F_VIEW_STATE_CLEAR) {
                if (_gviewGamePuse == 0) {
                    // Log.i(_TAG, "ゲームを中断する処理書け");
                    _glog.log("ゲーム中断処理:");
                    _gviewGamePuse = 1;

                    //add 20161128 中断処理追加
                    if (_gvideoViewForGame != null) {
                        _gvideoViewForGame.pause();
                        _gPousePosition = _gvideoViewForGame.getCurrentPosition();

                        // TODO onkyo8.0 OSむけ対応
                        // _gvideoViewForGame.setZOrderMediaOverlay(true);
                        // TODO 20220131 ゲーム中に到着画面が来ると下になってしまう。上記の影響が考えられる
                        _gvideoViewForGame.setVisibility(View.INVISIBLE);
                    }
                }
            } else {
                if (_gviewGamePuse == 1) {
                    _glog.log("ゲーム中断解除:");
                    _gviewGamePuse = 0;
                    //add 20161128 中断処理追加
                    if (_gvideoViewForGame != null) {
                        _gvideoViewForGame.seekTo(_gPousePosition);
                        _gvideoViewForGame.start();
                        // TODO onkyo8.0 OSむけ対応
                        // _gvideoViewForGame.setZOrderMediaOverlay(true);
                        // TODO 20220131 ゲーム中に到着画面が来ると下になってしまう。上記の影響が考えられる
                        _gvideoViewForGame.setVisibility(View.VISIBLE);

                    }
                }
                //ゲームのカウントと、強制終了カウントを減らす。
                //描画loop 0.5秒単位なので、500づつ減らす
                _gGamePlayCount = _gGamePlayCount - 500;
                _gviewGameOnTimer = _gviewGameOnTimer - 500;
            }
            //ver79 add================================move skip あたり判定なし
            if (_gsikpGameFlag == 1) { //skip flag check
                _glog.log("_gsikpGameFlag=1");
                _gCurrentGame = 1;//強制的にはずれにする。
                _gviewGameOnTimer = 500;
                _gDrawGameFlag = F_VIEW_STATE_LIVE_ADD;
            }
            //ver79 end===============================move skip
            //ﾋﾞｯｸﾗﾎﾟﾝ出力指示確認　+200は念のためやや早めに出力指示
            if (_gGamePlayCount < _gSettingInfo.GetGameWinPlayCount() * 10 + 200) {
                //change 20160624 本来はここでやるべきだが連続でゲームするとスキップされるケースがある。
                // _gSettingInfo.setWinFlag(_gCurrentGame);//ﾋﾞｯｸﾗﾎﾟﾝ出力をセット
                //change 20160624 本来は上でやるべき。要調査
                _glog.log("ﾋﾞｯｸﾗﾎﾟﾝ出力したいところ :" + _gCurrentGame);
                _gDrawGameFlag = F_VIEW_STATE_LIVE_ADD;
            }
            //異常等により一定時間たったら強制的にクローズさせる。
            if (_gviewGameOnTimer <= 0) {
                _gDrawGameFlag = F_VIEW_STATE_CLOSE_CMD;
            }
            //state game live add
        } else if (_gDrawGameFlag == F_VIEW_STATE_LIVE_ADD) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            if (_gGamePlayCount <= 1500 && _gGameSoundOn == 0) {
                _gGameSoundOn = 1;
                //Log.i(_TAG, "音声を鳴らしたい場合はここで鳴らす");
            }
            _gviewGameOnTimer = _gviewGameOnTimer - 500;
            //異常等により一定時間たったら強制的にクローズさせる。
            if (_gviewGameOnTimer <= 0) {
                _gDrawGameFlag = F_VIEW_STATE_CLOSE_CMD;
            }

            //state game live close
        } else if (_gDrawGameFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewMovForGame != null) {
                try {
                    _glog.log("_gviewGameMov: revome1");
                    _glog.LogJson(getApplicationContext(), "_gviewGameMov: revome 1");
                    _gMAIN_FRAME.removeView(_gviewMovForGame);
                    _gviewMovForGame = null;
                       /* _gTableDraw_Flag = 1;
                        _gDrawGameFlag = F_VIEW_STATE_CLEAR;
                        _gSettingInfo.setWinFlag(_gCurrentGame);//ﾋﾞｯｸﾗﾎﾟﾝ出力をセット
                        if (_gCurrentGame == 2) {
                                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Game,GAMESTATE:WIN");
                        }
                        _gCurrentGame = 0;
                        _gGameSoundOn = 0;
                        _gGamePlayTimeLength = 0;
                        _gsikpGameFlag = 0;
                        _gPousePosition = 0;
                        _glog.log("ゲーム終了:皿" + _gSettingInfo.GetSaraCnt());*/
                } catch (Exception e) {
                    _glog.log("LOGCAT:ERR,CMD:REMOVE _gviewGameMov:" + e);
                    _glog.LogJson(getApplicationContext(), "LOGCAT:ERR,CMD:REMOVE__gviewGameMov:" + e);
                }
            } else {
                _gDrawGameFlag = F_VIEW_STATE_CLEAR;
                _gSettingInfo.setWinFlag(_gCurrentGame);//ﾋﾞｯｸﾗﾎﾟﾝ出力をセット
                if (_gCurrentGame == 2) {
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Game,GAMESTATE:WIN");
                    // TODO add 20210913 get QRINFO　  QRINFOが有効な場合はQR読み取り画面を表示
                    if( _FLAG_LINKQRCODE_USE_ON == 1 ) {
                        _gDrawLinkQRCodeFlag = F_VIEW_STATE_OPEN_CMD;
                        _gPreferencesSave.saveLINK_QR_MAIN_VIEW_BUTTON_ON_FLAG(1); // 再起動した際に保持出来るように
                        _gLinkQRMainViewButtonOnFlag = 1; // メイン画面にQR画面をひらくためのボタンを表示する設定をＯＮにする
                        _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:OpenQRInfo,GAMESTATE:WIN,TAG:DxGame");
                    }
                    // TODO add 20210913 get QRINFO　  QRINFOが有効な場合はQR読み取り画面を表示　
                }
                _gCurrentGame = 0;
                _gGameSoundOn = 0;
                _gGamePlayTimeLength = 0;
                _gsikpGameFlag = 0;
                _gPousePosition = 0;
                _gTableDraw_Flag = 1;
                _glog.log("ゲーム終了:皿" + _gSettingInfo.GetSaraCnt());
            }

        }
        //----------Num-------------
        if (_gDrawNumFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gNumView == null) {
                this.addViewNum();
                _gDrawNumFlag = F_VIEW_STATE_LIVE;
                _gNumViewOnTimer = 30;
            }
        } else if (_gDrawNumFlag == F_VIEW_STATE_LIVE) {
            _gNumViewOnTimer--;
            if (_gNumViewOnTimer <= 0) {
                _gNumViewOnTimer = F_VIEW_STATE_CLOSE_CMD;
            }
            _gbtNumStr.setText("" + _gNumViewVal);
        } else if (_gDrawNumFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gNumView != null) {
                _gMAIN_FRAME.removeView(_gNumView);
                _gNumView = null;
            }
            _gNumViewOnTimer = 0;
            _gDrawNumFlag = F_VIEW_STATE_CLEAR;
        }
        //----------高速レーンバック警告-------------
        if (_gDrawKousokuBackFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewKousokuBack == null) {
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                _gSoundManager.PlaySound(2, 1);//音声要確認
                addViewKousokuBackAlert();
                _gDrawKousokuBackFlag = F_VIEW_STATE_LIVE;
                _gviewKousokuBackOnTimer = 20;
            }
        } else if (_gDrawKousokuBackFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _gviewKousokuBackOnTimer--;
            if (_gviewKousokuBackOnTimer <= 0) {
                _gDrawKousokuBackFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawKousokuBackFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewKousokuBack != null) {
                _gMAIN_FRAME.removeView(_gviewKousokuBack);
                _gviewKousokuBack = null;
            }
            _gDrawKousokuBackFlag = F_VIEW_STATE_CLEAR;
            _gviewKousokuBackOnTimer = 0;
        }

        //----------add 20200203 LOW battery alert-------------
        if (_gDrawLowBatteryAlertFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewLowBatteryAlert == null && _gUSE_BATTEYR_CHECK_FLAG == 1 && _gviewLowBatteryAlertOffSetTimer == 0) {
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                this.addLowBatteryAlert();
                _gSoundManager.PlaySound(1, 1);
                _gDrawLowBatteryAlertFlag = F_VIEW_STATE_LIVE;
                _gviewLowBatteryAlertOnTimer = 30;
            }
        } else if (_gDrawLowBatteryAlertFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _gviewLowBatteryAlertOnTimer--;
            //_glog.log("====>> 2:"+ _gviewLowBatteryAlertOnTimer);
            if (_gviewLowBatteryAlertOnTimer <= 0) {
                _gDrawLowBatteryAlertFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawLowBatteryAlertFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewLowBatteryAlert != null) {
               /* 2020 0204 expressの調査するが終わったらこっち使う
                 _gLowBatteryAlertWeb.stopLoading();
                _gLowBatteryAlertWeb.clearCache(true);
                _gLowBatteryAlertWeb.clearHistory();
                _gLowBatteryAlertWeb.setWebChromeClient(null);
                _gLowBatteryAlertWeb.setWebViewClient(null);
                _gLowBatteryAlertWeb.destroy();
                _gLowBatteryAlertWeb=null;
                _gMAIN_FRAME.removeView(_gviewLowBatteryAlert );
                _gviewLowBatteryAlert  = null;*/
                _gMAIN_FRAME.removeView(_gviewLowBatteryAlert);
                _gviewLowBatteryAlert = null;
            }
            _gDrawLowBatteryAlertFlag = F_VIEW_STATE_CLEAR;
            _gviewLowBatteryAlertOnTimer = 0;
            //add 20200204 充電警告のOFFセット時間
            _gviewLowBatteryAlertOffSetTimer = 300;
        }


        //----------かぎリセット警告-------------
        if (_gDrawKeyAlertFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewKeyAlert == null && _gKEY_ALERT_USE_FLAG == 1) {
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                this.addViewKeyAlert();
                _gSoundManager.PlaySound(1, 1);
                _gDrawKeyAlertFlag = F_VIEW_STATE_LIVE;
                _gviewKeyAlertOnTimer = 20;
            }
        } else if (_gDrawKeyAlertFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            _gviewKeyAlertOnTimer--;
            if (_gviewKeyAlertOnTimer <= 0) {
                _gDrawKeyAlertFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawKeyAlertFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewKeyAlert != null) {
                _gMAIN_FRAME.removeView(_gviewKeyAlert);
                _gviewKeyAlert = null;
            }
            _gDrawKeyAlertFlag = F_VIEW_STATE_CLEAR;
            _gviewKeyAlertOnTimer = 0;
            _g_IO_RESET_STATE_FLAG = IO_RESET_STATE_CLEAR;
        }

        //----------FTP update------------
        if (_gDrawFtpUpdatetFlag == F_VIEW_STATE_OPEN_CMD
                && _gDrawGameFlag == F_VIEW_STATE_CLEAR) {
            if (_gftpUpdateView == null) {
                this.addViewFtpUpdate();
                _gDrawFtpUpdatetFlag = F_VIEW_STATE_LIVE;

                // 通信抑制20161108
                _gUDP_CONNECT_LIMMIT_FLAG = 1;
            }
        } else if (_gDrawFtpUpdatetFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            addViewFtpUpdateDraw();
            _gviewFtpUpdatetOnTimer--;
            if (_gviewFtpUpdatetOnTimer <= 0) {
                _gDrawFtpUpdatetFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawFtpUpdatetFlag == F_VIEW_STATE_CLOSE_CMD) {
            // _gdaialog_call.DissmisDig();
            if (_gftpUpdateView != null) {
                //20160622 add
                //_gNetaInfo.setImg();
                //20160622 add
                _gMAIN_FRAME.removeView(_gftpUpdateView);
                _gftpUpdateView = null;
                _gFfpCatStr = null;
            }
            //通信抑制20161108
            _gUDP_CONNECT_LIMMIT_FLAG = 0;
            _gDrawFtpUpdatetFlag = F_VIEW_STATE_CLEAR;
        }

        //help画面 => `20200109 gameskip機能に限定的する
        if (_gDrawHELPFlag == F_VIEW_STATE_OPEN_CMD &&
                //_gDrawFlashingFlag == F_VIEW_STATE_CLEAR &&
                _gDrawSousaAnnaiFlag == F_VIEW_STATE_CLEAR &&
                _gDrawQrCodeFlag == F_VIEW_STATE_CLEAR &&
                _gDrawOpenningMoveFlag == F_VIEW_STATE_CLEAR //add 20200608
        ) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット

            if (_gviewHELP == null) {
                this.addViewHELP();
                _gDrawHELPFlag = F_VIEW_STATE_LIVE;
                _gviewHELPOnTimer = 300;
            } else {
                //add 20170616 閉じなくなるエラーが発生していたので、追加
                _gDrawHELPFlag = F_VIEW_STATE_CLOSE_CMD;
                _glog.log("_gDrawHELPFlag open時の処理異常のためクローズ");
            }
        } else if (_gDrawHELPFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            //描画
            this.addViewHELPDraw();
            _gviewHELPOnTimer--;
            if (_gviewHELPOnTimer <= 0) {
                _gDrawHELPFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawHELPFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewHELP != null) {
                _gMAIN_FRAME.removeView(_gviewHELP);
                _gviewHELP = null;
            }
            _gviewHELPOnTimer = 0;
            _gDrawHELPFlag = F_VIEW_STATE_CLEAR;
        }
        //----------Test-------------
        if (_gDrawTestFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewTest == null) {
                this.addViewTest();
                _gDrawTestFlag = F_VIEW_STATE_LIVE;

            }
        } else if (_gDrawTestFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            addViewTestDraw();
        } else if (_gDrawTestFlag == F_VIEW_STATE_CLOSE_CMD) {

            // _glog.log("------->> gDrawTestClose  called");
            // _gdaialog_call.DissmisDig();
            if (_gviewTest != null) {
                _gMAIN_FRAME.removeView(_gviewTest);
                _gviewTest = null;
            }
            _gDrawTestFlag = F_VIEW_STATE_CLEAR;
        }

        //--------add 20171026 flashing--------
//        if (_gDrawFlashingFlag == F_VIEW_STATE_OPEN_CMD) {
//            if (_gUSE_FLASHING_FLAG == 0) {
//                _gDrawFlashingFlag = F_VIEW_STATE_CLOSE_CMD;
//            } else {
//                if (_gviewFlashing == null) {
//                    _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
//                    if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
//                        _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
//                        _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
//                    }
//                    this.addViewFlashing();
//                    _gDrawFlashingFlag = F_VIEW_STATE_LIVE;
//                    //_gviewFlashingOnTimer = 100;
//                    _gviewFlashingOnTimer = 200;//change 20181029
//                    _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
//                }
//            }
//        } else if (_gDrawFlashingFlag == F_VIEW_STATE_LIVE) {
//            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
//            addViewFlashingDraw();
//            _gviewFlashingOnTimer--;
//            if (_gviewFlashingOnTimer < 0) {
//                _gDrawFlashingFlag = F_VIEW_STATE_CLOSE_CMD;
//            }
//        } else if (_gDrawFlashingFlag == F_VIEW_STATE_CLOSE_CMD) {
//            if (_gviewFlashing != null) {
//                _gMAIN_FRAME.removeView(_gviewFlashing);
//                _gviewFlashing = null;
//            }
//            _gDrawFlashingFlag = F_VIEW_STATE_CLEAR;
//        }

        //--------add 202006005 oderArriveAlert--------
        if (_gDrawOrderArriveAlertFlag == F_VIEW_STATE_OPEN_CMD) {
            if (_gviewOrderArriveAlert == null) {
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                this.addOrderArriveAlert();
                _gDrawOrderArriveAlertFlag = F_VIEW_STATE_LIVE;
                _gOrderArriveAlertOnTimer = 100;//change 20181029
                _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            }
        } else if (_gDrawOrderArriveAlertFlag == F_VIEW_STATE_LIVE) {
            _gOrderArriveAlertOnTimer--;
            if (_gOrderArriveAlertOnTimer < 0) {
                _gDrawOrderArriveAlertFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawOrderArriveAlertFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewOrderArriveAlert != null) {
                _gMAIN_FRAME.removeView(_gviewOrderArriveAlert);
                _gviewOrderArriveAlert = null;
                _gOrderArriveAlertOnTimer = 0;
            }
        }

        //--------add 20200515 addFirstFreeOrder--------

        /*  TODO 20211208 不要処理コメントアウト
        if (_gDrawFirstFreeOrderFlag == F_VIEW_STATE_OPEN_CMD &&
                _gDrawSousaAnnaiFlag == F_VIEW_STATE_CLEAR &&
                //_gDrawFlashingFlag == F_VIEW_STATE_CLEAR &&
                _gDrawQrCodeFlag == F_VIEW_STATE_CLEAR &&
                _gDrawGameFlag == F_VIEW_STATE_CLEAR &&
                _gDrawHELPFlag == F_VIEW_STATE_CLEAR &&
                _gDrawOpenningMoveFlag == F_VIEW_STATE_CLEAR
        ) {
            if (_gUSE_FirstFreeOrder_FLAG == 0) {
                _gDrawFirstFreeOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                _glog.LogJson(getApplicationContext(), "_gDrawFirstFreeOrderFlag:OPEN_CMD,proc:not_use");
            } else {
                if (_gviewFirstFreeOrder == null) {
                    this.addViewFirstFreeOrder();
                    _gDrawFirstFreeOrderFlag = F_VIEW_STATE_LIVE;
                    _gFirstFreeOrderNoConfirm = false;
                    _gFirstFreeOrderEndFlag = 0;
                    _gFirstFreeOrderCheckTimer = 0;
                    _gFirstFreeOrderRetrun = 0;
                    _gviewFirstFreeOrderOnTimer = 100;//change 20181029
                    _gFirstFreeOrderCheckTimer = 0;
                    _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                    _glog.LogJson(getApplicationContext(), "_gDrawFirstFreeOrderFlag:OPEN_CMD,proc:addview");
                }
            }
        } else if (_gDrawFirstFreeOrderFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
            addViewFirstFreeOrderDraw();
            _gviewFirstFreeOrderOnTimer--;
            if (_gviewFirstFreeOrderOnTimer < 0) {
                _gDrawFirstFreeOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                _glog.LogJson(getApplicationContext(), "_gDrawFirstFreeOrderFlag:LIVE_CMD,proc:timerUp");
            }
            //order済みの場合
            if (_gFirstFreeOrderCheckTimer > 0) {
                _gFirstFreeOrderCheckTimer--;
                if (_gFirstFreeOrderCheckTimer == 0) {
                    if (_gthreadSendCmd.GetOrderCmdReturnState() < 0) {
                        _gFirstFreeOrderRetrun = -1;
                        //case miss
                        _glog.LogJson(getApplicationContext(), "_gDrawFirstFreeOrderFlag:LIVE_CMD,proc:orderMiss");
                    } else {
                        _gFirstFreeOrderEndFlag = 1;
                        //case sucsess
                        _glog.LogJson(getApplicationContext(), "_gDrawFirstFreeOrderFlag:LIVE_CMD,proc:orderOK");
                    }
                    _gDrawFirstFreeOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                }
            }
        } else if (_gDrawFirstFreeOrderFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewFirstFreeOrder != null) {
                _gMAIN_FRAME.removeView(_gviewFirstFreeOrder);
                _gviewFirstFreeOrder = null;
                _glog.LogJson(getApplicationContext(), "_gDrawFirstFreeOrderFlag:LIVE_CMD,proc:close");
                // orderに失敗していたら呼び出しページに飛ぶ
                if (_gFirstFreeOrderRetrun == -1) {
                    _gDrawCallFlag = F_VIEW_STATE_OPEN_CMD;
                }
                _gFirstFreeOrderRetrun = 0;
            }
            _gFirstFreeOrderNoConfirm = false;
            _gDrawFirstFreeOrderFlag = F_VIEW_STATE_CLEAR;
        }
        */


        if (_gDrawQrCodeFlag == F_VIEW_STATE_OPEN_CMD
            //&& _gDrawFlashingFlag == F_VIEW_STATE_CLEAR
        ) {
            if (_gQRCODE_DISP_FLAG == 0 ||
                    (_gHttpQrVer ==  DOWNLOAD_FALED_DIRCTORY_NOTHING && qrFromMain == 0)//zipがインストールされていない、かつメインメニュー以外からの遷移である
            ) {
                _gDrawQrCodeFlag = F_VIEW_STATE_CLOSE_CMD;
            } else {
                if (_gviewQrCode == null) {
                    this.addViewQrCode();
                    _gDrawQrCodeFlag = F_VIEW_STATE_LIVE;
                    _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                    // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                    if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                        _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                    }
                    // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                }
            }
        } else if (_gDrawQrCodeFlag == F_VIEW_STATE_LIVE) {
            _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
        } else if (_gDrawQrCodeFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewQrCode != null) {
                _gMAIN_FRAME.removeView(_gviewQrCode);
                _gviewQrCode = null;
            }
            _gDrawQrCodeFlag = F_VIEW_STATE_CLEAR;
        }

        if (_gDrawWvCallFlag == F_VIEW_STATE_OPEN_CMD) {
            //この画面を閉じる場合は、removeViewCancel()をWebView側から呼ぶことにしている
            drawCallViewDisp();
            _gDrawWvCallFlag = F_VIEW_STATE_CLEAR;
        }

        // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加
        if (_gDrawLinkQRCodeFlag == F_VIEW_STATE_OPEN_CMD) {
            _glog.LogJson(getApplicationContext(), "_gDrawLinkQRCodeFlag:OPEN_CMD,_FLAG_LINKQRCODE_USE_ON:" + _FLAG_LINKQRCODE_USE_ON);
            if(_FLAG_LINKQRCODE_USE_ON == 0) {
                // _FLAG_LINKQRCODE_USE_ON = F_VIEW_STATE_CLOSE_CMD; 間違い？
                _gDrawLinkQRCodeFlag = F_VIEW_STATE_CLOSE_CMD;
            }else {
                if (_gviewLinkQRCode == null) {
                     this.addViewLinkerQr();
                    _gDrawLinkQRCodeFlag = F_VIEW_STATE_LIVE;
                    _gviewLinkQRCodeOnTimer = 100;//change 20181029
                    _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                    // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                    if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                        _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                    }
                    // TODO 20220203 スクリーンセーバーがOPENしていたら閉じる
                }
            }
        } else if (_gDrawLinkQRCodeFlag  == F_VIEW_STATE_LIVE) {
             _gviewLinkQRCodeOnTimer--;
            if (_gviewLinkQRCodeOnTimer <= 0) {
                _gDrawLinkQRCodeFlag = F_VIEW_STATE_CLOSE_CMD;
            }
        } else if (_gDrawLinkQRCodeFlag == F_VIEW_STATE_CLOSE_CMD) {
            if (_gviewLinkQRCode  != null) {
                _gMAIN_FRAME.removeView(_gviewLinkQRCode);
                _gviewLinkQRCode  = null;
                _gviewLinkQRCodeOnTimer = 0;
                _gDrawLinkQRCodeFlag = F_VIEW_STATE_CLEAR;
            }
        }
        // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加

    }

    /**
     * init NetaTable
     * ネタテーブルの作成
     */
    private void initNetaTable() {
        int i = 0;
        //メイン画面のボタンにイベントつける
        for (i = 0; i < _gBtid.length; i++) {
            _gBt_main[i] = null;
            _gBt_main[i] = (Button) findViewById(_gBtid[i]);
            _gBt_main[i].setOnClickListener(this);
            //add 2016 1028 短すぎるイベントを取得
            if (i == 2) {//rireki
                try {
                    _gBt_main[i].setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == 1) {
                                int des = 0;
                                des = (int) (event.getEventTime() - event.getDownTime());
                                if (des < TOUCH_TIME_THRESHOLD_RIREKI) {
                                    Button bt = (Button) v;
                                    bt.clearFocus();
                                    _glog.log("onTouchEvent getSize :" + event.getSize());
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                } catch (Exception e) {
                    _glog.log("ERR:initNetaTable rireki" + e.toString());
                }
            } else if (i == 3) {//call
                try {
                    _gBt_main[i].setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == 1) {
                                int des = 0;
                                des = (int) (event.getEventTime() - event.getDownTime());
                                if (des < TOUCH_TIME_THRESHOLD_CALL) {
                                    Button bt = (Button) v;
                                    bt.clearFocus();
                                    _glog.log("onTouchEvent getSize :" + event.getSize());
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                } catch (Exception e) {
                    _glog.log("ERR:initNetaTable call" + e.toString());
                }
            } else if (i == 4) {//kaikei
                try {//
                    _gBt_main[i].setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == 1) {
                                int des = 0;
                                des = (int) (event.getEventTime() - event.getDownTime());
                                if (des < TOUCH_TIME_THRESHOLD_KAIKEI) {
                                    Button bt = (Button) v;
                                    bt.clearFocus();
                                    _glog.log("onTouchEvent getSize :" + event.getSize());
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                } catch (Exception e) {//
                    _glog.log("ERR:initNetaTable kaikei" + e.toString());
                }
            } else {
                try {
                    _gBt_main[i].setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == 1) {
                                int des = 0;
                                des = (int) (event.getEventTime() - event.getDownTime());
                                if (des < TOUCH_TIME_THRESHOLD) {
                                    Button bt = (Button) v;
                                    bt.clearFocus();
                                    _glog.log("onTouchEvent getSize :" + event.getSize());
                                    //_gMAIN_FRAME.requestFocus();
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                } catch (Exception e) {
                    _glog.log("ERR:initNetaTable else" + e.toString());
                }
            }
        }
        //メイン画面のnetaimageにイベントつける
        for (i = 0; i < mIBid.length; i++) {
            IB[i] = null;
            IB[i] = (ImageButton) findViewById(mIBid[i]);
            IB[i].setOnClickListener(this);
            //add 2016 1028 短すぎるイベントを取得実験
            IB[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == 1) {
                        int des = 0;
                        des = (int) (event.getEventTime() - event.getDownTime());
                        if (des < TOUCH_TIME_THRESHOLD_IMG) {
                            _glog.log("->img onTouch desert des:" + des);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        for (i = 0; i < mNameA.length; i++) {
            TNAME_A[i] = null;
            TNAME_A[i] = (TextView) findViewById(mNameA[i]);
        }
        for (i = 0; i < mNameB.length; i++) {
            TNAME_B[i] = null;
            TNAME_B[i] = (TextView) findViewById(mNameB[i]);
        }

        //TODO 20211225 品切れ 指定商品の品切れ文字表示用
        /*
        for (i = 0; i < TSINAGIRE.length; i++) {
            TSINAGIRE[i] = null;
            TSINAGIRE[i] = (TextView) findViewById(mSinagire[i]);
            TSINAGIRE[i].setOnClickListener(this);
        }
        */


        //tag
        for (i = 0; i < _gBtagid.length; i++) {
            _gBt_tag[i] = null;
            _gBt_tag[i] = (Button) findViewById(_gBtagid[i]);
            _gBt_tag[i].setOnClickListener(this);
        }
        //table back
        _gTableBack = null;
        _gTableBack = (FrameLayout) findViewById(R.id.Li_MainTableBack);
        //MAIN FRAME
        _gMAIN_FRAME = null;
        _gMAIN_FRAME = (FrameLayout) findViewById(R.id.MAIN_FRAME);
        _gviewMov = null;
        _gmT_tableNum = null;
        _gmT_tableNum = (TextView) findViewById(R.id.mT_tableNum);
        _gmT_AppVer = null;
        _gmT_AppVer = (TextView) findViewById(R.id.mT_AppVer);


        //add 230190529 大ネタ表示用
        IB_BIG = (ImageButton) findViewById(R.id.netaImgBIG);
        IB_BIG.setOnClickListener(this);
        TNAME_BIG = (TextView) findViewById(R.id.netaNameBIG);
        _gBIG_FRAME = (FrameLayout) findViewById(R.id.BIG_NETA_FRAME);
    }

    /**
     * onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Thread.UncaughtExceptionHandler savedUncaughtExceptionHandler =
                Thread.getDefaultUncaughtExceptionHandler();
        // キャッチされなかった例外発生時の処理を設定する
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            private volatile boolean mCrashing = false;

            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                try {
                    if (!mCrashing) {
                        mCrashing = true;
                    }
                } finally {
                    savedUncaughtExceptionHandler.uncaughtException(thread, ex);
                }
            }
        });
        //add 2016 0618 例外発生時
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);// ステータスバー削除
        Window window = getWindow();
        View view = window.getDecorView();
        setContentView(R.layout.activity_main);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        //通知領域の非表示
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        _gSETUP_Flag = 1;//セットアップ用ﾌﾗｸﾞON
        _gSETUP_COUNT = 0;

        //debug 20181115 デバック用メモリー使用状況
      /*  if(_gUSE_DebugMemInfo==1) {
            runtime = Runtime.getRuntime();
            activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            lowMemory = memoryInfo.lowMemory;
            availMem = memoryInfo.availMem;
        }*/
        //debug 20181115
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    /**
     * onDestroy()
     * LIFE CYCLE
     */
    protected void onDestroy() {
        if (_glog != null) {
            _glog.log("onDestroy called");
            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:onDestroy");
        }

        super.onDestroy();
        //バックグランドに入ったらアプリ終了をコール
        android.os.Process.killProcess(android.os.Process.myPid());
        //add 20160706
        finish();
    }

    /**
     * クリック時に呼ばれるメソッド
     */
    public void onClick(View view) {
        int i = 0;
        int viewID = view.getId();
        int tagNum = 0;

        int _netaID = 0;
        int _recommendID = 0;
        if (_gSETUP_Flag == 1) {
            _glog.log("onClick セットアップ中は処理しない。");
            return;
        }
        if (R.id.ID_MOV_VIEW == viewID) {
            if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                //add 20181205
                try {
                    _glog.log("_gvideoView stop button");
                    _glog.LogJson(getApplicationContext(), "_gvideoView:stop button");
                    if (_gvideoView != null) {
                        if (_gvideoView.isPlaying()) {
                            _gvideoView.stopPlayback();
                            // _gvideoView.
                        }
                        _gvideoView = null;//change 20160603
                    }
                } catch (Exception e) {
                    _glog.log("LOGCAT:ERR,CMD:_gvideoView stop button:" + e.toString());
                    _glog.LogJson(getApplicationContext(), "LOGCAT:ERR,CMD:_gvideoView stop button:" + e.toString());
                }
                _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
            }
            return;
        }

        for (i = 0; i < mIBid.length; i++) {
            //change 2018080817 大ネタ対応　大ネタはページの先頭固定なのでi=0になる
            if (mIBid[i] == viewID || R.id.netaImgBIG == viewID) {
                //if (mIBid[i] == viewID) {//20190110 大ネタ機能は一旦無くしているので、使用するばあいは再検討
                if (_gORDER_VIEW_BOTTON_ON_COUNT > 0) {
                    _glog.log("netaImg OrderBack ボタンON直後のためキャンセル");
                    break;
                }
                if (_gCALLBACK_BOTTON_ON_COUNT > 0) {
                    _glog.log("netaImg CALLBACK ボタンON直後のためキャンセル");
                    break;
                }
                if (_gPAGE_CHANGE_BOTTON_ON_COUNT > 0) {
                    _glog.log("pageを遷移させる ボタンON直後のためキャンセル");
                    break;
                }
                //add20180105 page遷移ボタン直後は禁止　

                _gKEY_ALERT_USE_FLAG = 1; //add 20161031
                //_gTOUCH_USE_ON_FLAG = 1; //タッチパネルを使用状態か？
                _gTestOpenCountButtonOn = 0;   //add 20161031

                if (_gDrawOrderFlag == 0) {
                    // _gNETAIMG_BOTTON_ON_COUNT = NETAIMG_BOTTON_ON_COUNT_VAL;
                    // add 20200822 ver320
                    _gNETAIMG_BOTTON_ON_COUNT = _gPreferencesSave.getIGNORE_BOTTON_ON_COUNT_VAL();

                    _gOrderItemKey = i + (_gCurrentPage - 1) * ORDER_MAX;
                    int _code = _gNetaInfo.getOptSel(_gOrderItemKey);
                    _gOPT_OPT_CODE = _code;
                    _gOPT_OPT_CODE_1 = _code * 10 + 1;
                    _gOPT_OPT_CODE_2 = _code * 10 + 2;
                    _gOPT_OPT_FLAG_1 = _goptInfo.getFlagFromToppingId(_gOPT_OPT_CODE_1);
                    _gOPT_OPT_FLAG_2 = _goptInfo.getFlagFromToppingId(_gOPT_OPT_CODE_2);
                    _gOPT_SELECTED_OPT_CODE = _gOPT_OPT_CODE_1;//初期値

                    //add 20180612 のレコマンド機能
                    /*if (_gUSE_SINAGIRE_RECOMMEND_ON_FLAG == 1) {
                        //netaIDとrecommand機能のIDが一致した場合商品
                        _netaID = _gNetaInfo.getId(_gOrderItemKey);
                        _gRecommendKey = _gNetaInfo.serchRecommendNetaId(_netaID);

                        //_glog.log("_gUSE_SINAGIRE_RECOMMEND_ON_FLAG:"+_netaID);
                        if (_gRecommendKey != -1) {//_gRecommendKeyが見つかった。
                            _gDrawReccomendWebFlag = F_VIEW_STATE_OPEN_CMD;
                            break;
                        }
                    }*/
                    //add 20180612 のレコマンド機能

                    //セルフ商品かチェック
                    if (_gNetaInfo.getSelf(_gOrderItemKey) == 0) {
                        _gthreadSendCmd.setFlagReqWaitOrderCount();//問い合わせﾌﾗｸﾞ
                        _gthreadSendCmd.setFlagCheackOn();//即時発行
                        //Log.i(_TAG, "netaImgm ON: " + _gOrderItemKey);
                        //_glog.logOut(getApplicationContext(), "IMG_ON_KEY:"+_gOrderItemKey+" PAGE:"+_gCurrentPage +" SEIGEN:"+_gSettingInfoFromServer.getCURRENT_chuumonseigen() +" ORDERD:"+_gSettingInfoFromServer.getCURRENT_ORDER() +" ID:"+_gTAG_CUSTOMOER_ID);
                        _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:BtNeta,ORDERNETAID:"
                                + _gNetaInfo.getId(_gOrderItemKey)
                                + ",PAGE:" + _gCurrentPage
                                + ",SEIGEN:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen()
                                + ",ORDERD:" + _gSettingInfoFromServer.getCURRENT_ORDER());
                        _gDrawOrderFlag = F_VIEW_STATE_OPEN_CMD;
                    } else {//セルフセルフ警告画面を表示する処理を入れること
                        _gDrawOrderSelfFlag = F_VIEW_STATE_OPEN_CMD;
                        //Log.i(_TAG, "セルフだよ　netaImgm ON: " + _gOrderItemKey);

                    }
                }
            }
        }

        //tagのクリック
        for (i = 0; i < _gBtagid.length; i++) {
            if (_gBtagid[i] == viewID) {
                //add 20180108
                if (_gNETAIMG_BOTTON_ON_COUNT > 0) {
                    _glog.log("tagボタン netaImg ボタンON直後のためキャンセル");
                    break;
                }
                //add 20180108
                _gTestOpenCountButtonOn = 0;
                tagNum = _gNetaInfo.getTagpage(i);
                // Log.i(_TAG, "==tagNum ON: " + tagNum);
                if (tagNum > 0 && tagNum <= PAGE_MAX) {
                    // _gPAGE_CHANGE_BOTTON_ON_COUNT = PAGE_CHANGE_BOTTON_ON_COUNT_VAL;
                    // add 20200822 ver320
                    _gPAGE_CHANGE_BOTTON_ON_COUNT = _gPreferencesSave.getIGNORE_BOTTON_ON_COUNT_VAL();
                    this.pageChange(tagNum, 1);
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:BtTag"
                            + ",PAGE:" + _gCurrentPage
                            + ",tagNum:" + tagNum);
                }
            }
        }

        //ボタン操作
        // Thread th = null;
        switch (viewID) {//前へボタン
            case R.id.bt_main1:
                if (_gNETAIMG_BOTTON_ON_COUNT > 0) {
                    _glog.log("前へボタン netaImg ボタンON直後のためキャンセル");
                    break;
                }
                //add 20161026  ノイズで勝手にボタンが押される対策
                // _gPAGE_CHANGE_BOTTON_ON_COUNT = PAGE_CHANGE_BOTTON_ON_COUNT_VAL;
                // add 20200822 ver320
                _gPAGE_CHANGE_BOTTON_ON_COUNT = _gPreferencesSave.getIGNORE_BOTTON_ON_COUNT_VAL();

                _gTestOpenCountButtonOn = 0;
                _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                if (_gCurrentPage == 1) {
                    this.pageChange(PAGE_MAX, 0);
                } else {
                    this.pageChange(_gCurrentPage - 1, 0);
                }
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:PrePage");
                break;

            case R.id.bt_main2://次へボタン
                if (_gNETAIMG_BOTTON_ON_COUNT > 0) {
                    _glog.log("次へボタン netaImg ボタンON直後のためキャンセル");
                    break;
                }
                //

                //add 20161026  ノイズで勝手にボタンが押される対策
                // _gPAGE_CHANGE_BOTTON_ON_COUNT = PAGE_CHANGE_BOTTON_ON_COUNT_VAL;
                // add 20200822 ver320
                _gPAGE_CHANGE_BOTTON_ON_COUNT = _gPreferencesSave.getIGNORE_BOTTON_ON_COUNT_VAL();

                _gTestOpenCountButtonOn = 0;
                _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                if (_gCurrentPage == PAGE_MAX) {
                    this.pageChange(1, 1);
                } else {
                    this.pageChange(_gCurrentPage + 1, 1);
                }
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:NextPage");
                break;

            case R.id.bt_main3://履歴

                //20161031
                if (_gDrawRirekiFlag != F_VIEW_STATE_CLEAR) {
                    _glog.log("onClick 履歴 ON break");
                    break;
                }
                _glog.log("onClick 履歴 ON");
                //add 20161026  ノイズで勝手にボタンが押される対策
                //次へ前へボタン直後の操作は無視する。
                if (_gPAGE_CHANGE_BOTTON_ON_COUNT > 0) {
                    _glog.log("onClick 履歴  ページ選択ボタン直後のためキャンセル");
                    break;
                }
                _gTestOpenCountButtonOn = 0;
                _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                _gthreadSendCmd.setFlagReqRireki();

                //change 20161026
                _gthreadSendCmd.setFlagCheackOn();//即時発行
                //change 20161026
                _gDrawRirekiFlag = F_VIEW_STATE_OPEN_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:Rireki");
                break;

            case R.id.bt_main4://呼び出し

                //20161031
                if (_gDrawCallFlag != F_VIEW_STATE_CLEAR) {
                    _glog.log("onClick 呼び出し ON break");
                    break;
                }

                //add 20161026  ノイズで勝手にボタンが押される対策
                //次へ前へボタン直後の操作は無視する。
                _glog.log("onClick 呼び出し ON");

                if (_gPAGE_CHANGE_BOTTON_ON_COUNT > 0) {
                    _glog.log("onClick 呼び出し ページ選択ボタン直後のためキャンセル");
                    break;
                }
                _gTestOpenCountButtonOn = 0;
                _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                _gDrawCallFlag = F_VIEW_STATE_OPEN_CMD;
                _gthreadGetFile.setGetNetaInfo_Flag();
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:Call");
                break;

            case R.id.bt_main5://会計

                // TODO 20220223 案内動画でボタンが表示されない対策　この処理は上手く動いていない可能性があるためコメントアウト


                if(_FLAG_LINKQRCODE_USE_ON  == 1 && _gLinkQRMainViewButtonOnFlag == 1){
                    _gDrawDxGameKaikeiAlertFlag = F_VIEW_STATE_OPEN_CMD;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:kaieflag_open, MSG:Dxの当たりがあるので、会計前QR確認画面を表示,DEBUG_TAG:DxGame");
                    break;
                }


                _gKEY_ALERT_USE_FLAG = 1;
                if (_gDrawKaikeiFlag != F_VIEW_STATE_CLEAR) {
                    _glog.log("onClick 会計 ON break");
                    break;
                }
                _glog.log("onClick 会計 ON");
                if (_gDrawCallFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawCallFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                if (_gPAGE_CHANGE_BOTTON_ON_COUNT > 0) {
                    _glog.log("onClick 会計 ページ選択ボタン直後のためキャンセル");
                    break;
                }
                /**
                 * アンケート画面が有効で未回答
                 */
                if (_gUSE_SURVEY_FLAG == 1 && _gSURVEY_DONE_FLAG == 0) {
                    _gDrawSurveyFlag = F_VIEW_STATE_OPEN_CMD;
                    /**
                     * 自動会計画面が有効
                     */
                } else {
                    _gKEY_ALERT_USE_FLAG = 1;
                    _gTestOpenCountButtonOn = 0;
                    _gthreadSendCmd.setFlagReqKaikeiRirekiOn();
                    _gthreadSendCmd.setFlagCheackOn();//即時発行
                    _gDrawKaikeiFlag = F_VIEW_STATE_OPEN_CMD;
                }
//                _gTestOpenCountButtonOn = 0;
//                _gthreadSendCmd.setFlagReqKaikeiRirekiOn();
//                _gthreadSendCmd.setFlagCheackOn();//即時発行
//                _gDrawKaikeiFlag = F_VIEW_STATE_OPEN_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:Kaikei");
                break;
            case R.id.bt_main9://top
                _gDrawTopFlag = F_VIEW_STATE_OPEN_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:Top");
                break;

            //add 20170614
            case R.id.bt_main10:
                _glog.log("ゲーム設定変更ボタンON");
                if (_gDrawHELPFlag == F_VIEW_STATE_CLEAR) {
                    _gHELP_STEP = HELP_STEP_GAME_SKIP_CHECK;
                    _gDrawHELPFlag = F_VIEW_STATE_OPEN_CMD;
                }
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:GameSkip");
                break;
            //add 20180502
            case R.id.bt_main11:
                qrFromMain = 1;
                _gDrawQrCodeFlag = F_VIEW_STATE_OPEN_CMD;
                break;
            case R.id.bt_main12:
                //add 20181101
                _glog.LogJson(getApplicationContext(), "CLOGCAT:DEBUG,MD:BtClick,BUTTON:SousaAnnaiOpen");
                _gSousaAnnaiCat = 0;//ボタン操作の場合は操作案内はデフォルトを表示する
                operate_sousaAnnai_open();
                break;

            case R.id.OrderOn:
                // 20201224 ~ ver314 second request
                _g_SECOND_REQUEST_ON = 0;

                //add 20161026  ノイズで勝手にボタンが押される対策
                if (_gORDER_VIEW_BOTTON_ON_COUNT > 0) {
                    _glog.log("onClick OrderOn ボタンON直後のためキャンセル");
                    break;
                }
                // _gORDER_VIEW_BOTTON_ON_COUNT = ORDER_VIEW_BOTTON_ON_COUNT_VAL;
                // add 20200822 ver320
                _gORDER_VIEW_BOTTON_ON_COUNT = _gPreferencesSave.getIGNORE_BOTTON_ON_COUNT_VAL();
                int ret = CheckOrderOn();
                if (ret == 98 || ret == 99) {
                    _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                    if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_CLEAR) {
                        _gDrawOrderRefuseMsgFlag = F_VIEW_STATE_OPEN_CMD;
                    }
                    //add 20180622
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Order,PROC:OrderRefuse,REFUSE_STATE:" + ret);
                    break;
                } else if (ret == 97) {
                    _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                    if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_CLEAR) {
                        _gDrawOrderLmitMsgFlag = F_VIEW_STATE_OPEN_CMD;
                    }
                    //add 20180622
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Order,PROC:OrderRefuse,REFUSE_STATE:" + ret);
                    break;
                }
                //order 状態再チェック
                //ver 91 change 20170918
                //add 20161026  ノイズで勝手にボタンが押される対策
                // Log.i(_TAG, "OrderOn ON 従業員端末の場合");=1;//for test
                _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                //_glog.log("OrderOn ON gReserv_mode_mode:" + _gReserv_mode_mode +"  _gUSE_ORDER_DEVID_ON_FLAG:" + _gUSE_ORDER_DEVID_ON_FLAG);
                if (_gReserv_mode_mode == 1) {//従業員端末の場合は下記の処理をテーブル番号入力後に実施
                    _glog.log("num open");
                    _gDrawNumFlag = F_VIEW_STATE_OPEN_CMD;
                } else {//普通の注文
                    if (_gthreadSendCmd != null) {
                        //add 20180612 分轄対象商品かを確認
                        int _devidOrderOn = _gNetaInfo.getDevidOrderOn(_gOrderItemKey);
                        int _a = 0;
                        if (_gUSE_ORDER_DEVID_ON_FLAG == 0 || _gOrderUnitCount < _devidOrderOn || _devidOrderOn == 0) {//add 20180610 order分轄機能は使わないかorder数が２以上（ベタ書きで大丈夫か？）
                            if (_gOPT_OPT_CODE != 0 && _gOPT_SELECTED_OPT_CODE != 0) {
                                _gthreadSendCmd.setOrderStr2(_gNetaInfo.getId(_gOrderItemKey), _gOrderUnitCount, 0, _gTtableNum, _gSettingInfo.GetOrderTimeStamp(), _gOPT_SELECTED_OPT_CODE);
                            } else {
                                _gthreadSendCmd.setOrderStr2(_gNetaInfo.getId(_gOrderItemKey), _gOrderUnitCount, 0, _gTtableNum, _gSettingInfo.GetOrderTimeStamp(), 0);
                            }
                            //_glog.logOut(getApplicationContext(), "OrderNetaId:" + _gNetaInfo.getId(_gOrderItemKey) + " Sara:" + _gOrderUnitCount + " SEIGEN:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen() + " ORDERD:" + _gSettingInfoFromServer.getCURRENT_ORDER() + " ORDER_MAX:"+_gSettingInfoFromServer.getOrderLimitFlag()+" ID:" + _gTAG_CUSTOMOER_ID);
                            _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Order,PROC:OrderOn,ORDERSTATE:Nomal:"
                                    + ",ORDERNETAID:" + _gNetaInfo.getId(_gOrderItemKey)
                                    + ",OPTCODE:" + _gOPT_SELECTED_OPT_CODE
                                    + ",SARA:" + _gOrderUnitCount
                                    + ",SEIGEN:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen()
                                    + ",ORDERD:" + _gSettingInfoFromServer.getCURRENT_ORDER()
                                    + ",ORDER_MAX:" + _gSettingInfoFromServer.getOrderLimitFlag());
                            _gthreadSendCmd.setFlagCheackOn();//即時発行
                        } else {//add 20180610 order分轄機能を使う。
                            //1 注文する商品が分轄対象確認 分轄対象の場合は注文情報を分割し、後で注文する情報をBUFに入れておく
                            //if(_gNetaInfo.getId(_gOrderItemKey)オーダー対象か確認){
                            // _glog.log("_gUSE_ORDER_DEVID_ON_FLAG:" + _gUSE_ORDER_DEVID_ON_FLAG + " OrderUnitCount:"+_gOrderUnitCount);
                            _gDevidBuf_netaID = _gNetaInfo.getId(_gOrderItemKey);
                            _gOrderHeightInfo.setDevidBuf(_gNetaInfo.getId(_gOrderItemKey), _gOrderUnitCount);
                            _a = _gOrderUnitCount % _devidOrderOn;//割り切れるか？
                            //_glog.log("_a:"+_a);
                            if (_a == 0) {
                                _gDevidBuf_Count = _gOrderUnitCount / _devidOrderOn;
                                _gOrderUnitCount = _devidOrderOn;//最少分割数で割り切れない場合は最初に最小単位で注文
                            } else {
                                _gDevidBuf_Count = (_gOrderUnitCount / _devidOrderOn) + 1;
                                _gOrderUnitCount = _a;//最少分割数で割り切れない場合は最初にあまりを注文
                            }
                            _gDevidBuf_UnitCount = _devidOrderOn;//最少分割数を指定
                            //分轄の１回目を注文
                            if (_gOPT_OPT_CODE != 0 && _gOPT_SELECTED_OPT_CODE != 0) {
                                _gthreadSendCmd.setOrderStr2(_gNetaInfo.getId(_gOrderItemKey), _gOrderUnitCount, 0, _gTtableNum, _gSettingInfo.GetOrderTimeStamp(), _gOPT_SELECTED_OPT_CODE);
                                //add 20180610 order分轄機能を使う。
                                _gDevidBuf_SELECTED_OPT_CODE = _gOPT_SELECTED_OPT_CODE;
                            } else {
                                _gthreadSendCmd.setOrderStr2(_gNetaInfo.getId(_gOrderItemKey), _gOrderUnitCount, 0, _gTtableNum, _gSettingInfo.GetOrderTimeStamp(), 0);
                                //add 20180610 order分轄機能を使う。
                                _gDevidBuf_SELECTED_OPT_CODE = 0;
                            }
                            _gDevidBuf_Count--;//分割注文カウントマイナス
                            //_glog.logOut(getApplicationContext(), "OrderNetaId:" + _gNetaInfo.getId(_gOrderItemKey) + " Sara:" + _gOrderUnitCount + " SEIGEN:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen() + " ORDERD:" + _gSettingInfoFromServer.getCURRENT_ORDER() +" ORDER_MAX:"+_gSettingInfoFromServer.getOrderLimitFlag()+ " ID:" + _gTAG_CUSTOMOER_ID +"devid:1");
                            _gthreadSendCmd.setFlagCheackOn();//即時発行*/
                            //_glog.log("OrderNetaId:" + _gNetaInfo.getId(_gOrderItemKey) + " Sara:" + _gOrderUnitCount + " SEIGEN:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen() + " ORDERD:" + _gSettingInfoFromServer.getCURRENT_ORDER() +" ORDER_MAX:"+_gSettingInfoFromServer.getOrderLimitFlag()+ " ID:" + _gTAG_CUSTOMOER_ID +"devid:"+_gDevidBuf_Count);
                            //_glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Order,PROC:OrderOn,OrderState:Devid_1,OrderNetaId:" + _gNetaInfo.getId(_gOrderItemKey) +",OPTCODE:"+_gOPT_SELECTED_OPT_CODE+ ",Sara:" + _gOrderUnitCount +",SEIGEN:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen() + ",ORDERD:" + _gSettingInfoFromServer.getCURRENT_ORDER()+",ORDER_MAX:"+_gSettingInfoFromServer.getOrderLimitFlag()+ ",DEVID:1");
                            _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Order,PROC:OrderOn,ORDERSTATE:Devid_" + _gDevidBuf_Count + 1
                                    + ",ORDERNETAID:" + _gNetaInfo.getId(_gOrderItemKey)
                                    + ",OPTCODE:" + _gOPT_SELECTED_OPT_CODE
                                    + ",SARA:" + _gOrderUnitCount
                                    + ",SEIGEN:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen()
                                    + ",ORDERD:" + _gSettingInfoFromServer.getCURRENT_ORDER()
                                    + ",ORDER_MAX:" + _gSettingInfoFromServer.getOrderLimitFlag());
                            //add 20180610 order分轄機能を使う。END
                        }
                    }
                    _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                    view.setEnabled(false);//back button gone
                    _gDrawOrderCheckFlag = F_VIEW_STATE_OPEN_CMD;
                    _gOPT_OPT_CODE = 0;//選択した商品のオプションコード
                    _gOPT_SELECTED_OPT_CODE = 0;//選択したオプション
                    _gOPT_OPT_CODE_1 = 0;
                    _gOPT_OPT_CODE_2 = 0;
                    _gOPT_OPT_FLAG_1 = 0;
                    _gOPT_OPT_FLAG_2 = 0;
                }
                break;
            case R.id.OrderBack:
                //add 20161026  ノイズで勝手にボタンが押される対策
                if (_gORDER_VIEW_BOTTON_ON_COUNT > 0) {
                    _glog.log("onClick OrderBack ボタンON直後のためキャンセル");
                    break;
                }
                _gOPT_OPT_CODE = 0;//選択した商品のオプションコード
                _gOPT_SELECTED_OPT_CODE = 0;//選択したオプション
                _gOPT_OPT_CODE_1 = 0;
                _gOPT_OPT_CODE_2 = 0;
                _gOPT_OPT_FLAG_1 = 0;
                _gOPT_OPT_FLAG_2 = 0;
                // _gORDER_VIEW_BOTTON_ON_COUNT = ORDER_VIEW_BOTTON_ON_COUNT_VAL;
                // add 20200822 ver320
                _gORDER_VIEW_BOTTON_ON_COUNT = _gPreferencesSave.getIGNORE_BOTTON_ON_COUNT_VAL();
                Button bt = (Button) findViewById(R.id.OrderOn);
                bt.setEnabled(false);
                //add 20161026  ノイズで勝手にボタンが押される対策
                _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                view.setEnabled(false);//back button gone
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:OrderBack");
                break;
            case R.id.ORDER_NUM1:
                _gOrderUnitCount = 1;
                _gDrawOrderUnitFlag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:ORDER_NUM1");
                break;
            case R.id.ORDER_NUM2:
                _gOrderUnitCount = 2;
                _gDrawOrderUnitFlag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:ORDER_NUM2");
                break;
            case R.id.ORDER_NUM3:
                _gOrderUnitCount = 3;
                _gDrawOrderUnitFlag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:ORDER_NUM3");
                break;
            case R.id.ORDER_NUM4:
                _gOrderUnitCount = 4;
                _gDrawOrderUnitFlag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:ORDER_NUM4");
                break;
            //add 20171105==========================
            case R.id.ORDER_OPT_0:
                _gOPT_SELECTED_OPT_CODE = _gOPT_OPT_CODE_1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:ORDER_OPT_0");
                break;
            case R.id.ORDER_OPT_1:
                _gOPT_SELECTED_OPT_CODE = _gOPT_OPT_CODE_2;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:ORDER_OPT_1");
                break;
            /*case R.id.ORDER_OPT_2://20180811 現在選択可能なのは２つだけ
                break;*/
            case R.id.RIREKIpre:
                _gRirekiInfo.RpageChange(0);
                _gRirekiDraw_Flag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:RIREKIpre");
                break;
            case R.id.RIREKInext:
                _gRirekiInfo.RpageChange(1);
                _gRirekiDraw_Flag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:RIREKInext");
                break;
            case R.id.RIREKIback:
                if (_gKAIKEI_On_For_BackButton < 100) {//一定時間以下ならすぐに戻る
                    _gDrawRirekiFlag = F_VIEW_STATE_CLOSE_CMD;
                    view.setEnabled(false);//back button gone
                } else {
                    if (_gKAIKEI_For_BackButton_Count >= 3) {//掃除を想定し、複数回タッチで戻るように
                        _gDrawRirekiFlag = F_VIEW_STATE_CLOSE_CMD;
                        view.setEnabled(false);//back button gone
                    } else {
                        _gKAIKEI_For_BackButton_Count++;
                    }
                }
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:RirekiBack");
                break;

            //add 20180415 for mutenkura
            case R.id.KAIKEI_SARA:
                _glog.log("KAIKEI_SARA CLICK:" + _gKAIKEI_For_SaraButton_Count);
                if (_FLAG_MUTENKURA_MODE_ON == 1) {
                    _gKAIKEI_For_SaraButton_Count++;
                    if (_gKAIKEI_For_SaraButton_Count >= 5) {
                        _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:KaikeiSara");
                        _gKAIKEI_For_SaraButton_Count = 0;
                        this.kaikeiRiset();
                        _g_IO_RESET_STATE_CLEAR_CNT = 10;
                        FLAG_IO_RESET_STATE_SARA_CLEAR = 1;
                        _gSoundManager.PlaySound(1, 1);
                        _gthreadSendCmd.setFlagReqKaikeiReset();
                        _g_IO_RESET_STATE_FLAG = IO_RESET_STATE_CLEAR;
                    }
                }
                break;
            //add 20180415 for mutenkura
            case R.id.KAIKEI_BACK:
                //add 20180415 for mutenkura
                _gKAIKEI_For_SaraButton_Count = 0;
                //add 20160524 一定時間たったら複数回押さないとバックしないように変更
                _glog.log("KAIKEI_BACK" + _gKAIKEI_On_For_BackButton);
                if (_gKAIKEI_On_For_BackButton < 100) {
                    _gDrawKaikeiFlag = F_VIEW_STATE_CLOSE_CMD;
                    view.setEnabled(false);//back button gone
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:KaikeiBack");
                } else {
                    _gKAIKEI_For_BackButton_Count++;
                    if (_gKAIKEI_For_BackButton_Count > 3) {
                        _gDrawKaikeiFlag = F_VIEW_STATE_CLOSE_CMD;
                        view.setEnabled(false);//back button gone
                        _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:KaikeiBack");
                    }
                }
                break;
            case R.id.btCALL_BACK:
                if (_gDrawCallFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawCallFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                if (_gDrawKeyAlertFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawKeyAlertFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                if (_gDrawKousokuBackFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawKousokuBackFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                // add 20200204 LOW battery alert
                if (_gDrawLowBatteryAlertFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawLowBatteryAlertFlag = F_VIEW_STATE_CLOSE_CMD;
                }

                // add 202006005 oderArriveAlert
                if (_gDrawOrderArriveAlertFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawOrderArriveAlertFlag = F_VIEW_STATE_CLOSE_CMD;
                }


                _gCALLBACK_BOTTON_ON_COUNT = CALLBACK_BOTTON_ON_COUNT_VAL;
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:CallBack");
                break;
            case R.id.OrderSelfBack:
                _gDrawOrderSelfFlag = F_VIEW_STATE_CLOSE_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:OrderSelfBack");
                break;
            //oreder limit msgt
            case R.id.btORDER_LIMIT_PAGE1:
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
                this.pageChange(_gSettingInfoFromServer.getOrderOffPage_ByOrder(1), 1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:OrderLimitPage,SELPAGE:" + _gSettingInfoFromServer.getOrderOffPage_ByOrder(1));
                break;
            case R.id.btORDER_LIMIT_PAGE2:
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
                this.pageChange(_gSettingInfoFromServer.getOrderOffPage_ByOrder(2), 1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:OrderLimitPage,SELPAGE:" + _gSettingInfoFromServer.getOrderOffPage_ByOrder(2));
                break;
            case R.id.btORDER_LIMIT_PAGE3:
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
                this.pageChange(_gSettingInfoFromServer.getOrderOffPage_ByOrder(3), 1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:OrderLimitPage,SELPAGE:" + _gSettingInfoFromServer.getOrderOffPage_ByOrder(3));
                break;
            case R.id.btORDER_LIMIT_PAGE4:
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
                this.pageChange(_gSettingInfoFromServer.getOrderOffPage_ByOrder(4), 1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:OrderLimitPage,SELPAGE:" + _gSettingInfoFromServer.getOrderOffPage_ByOrder(4));
                break;
            //change20160608 注文OFF対象のページを８ページに増やす。
            case R.id.btORDER_LIMIT_PAGE5:
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
                this.pageChange(_gSettingInfoFromServer.getOrderOffPage_ByOrder(5), 1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:OrderLimitPage,SELPAGE:" + _gSettingInfoFromServer.getOrderOffPage_ByOrder(5));
                break;
            case R.id.btORDER_LIMIT_PAGE6:
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
                this.pageChange(_gSettingInfoFromServer.getOrderOffPage_ByOrder(6), 1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:OrderLimitPage,SELPAGE:" + _gSettingInfoFromServer.getOrderOffPage_ByOrder(6));
                break;
            case R.id.btORDER_LIMIT_PAGE7:
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
                this.pageChange(_gSettingInfoFromServer.getOrderOffPage_ByOrder(7), 1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:OrderLimitPage,SELPAGE:" + _gSettingInfoFromServer.getOrderOffPage_ByOrder(7));
                break;
            case R.id.btORDER_LIMIT_PAGE8:
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
                this.pageChange(_gSettingInfoFromServer.getOrderOffPage_ByOrder(8), 1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:OrderLimitPage,SELPAGE:" + _gSettingInfoFromServer.getOrderOffPage_ByOrder(8));
                break;
            //change20160608
            case R.id.btORDER_LIMIT_PAGE_BACK:
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_CLOSE_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:OrderLimitPageBack");
                break;
            //TOP ボタンを押した場合
            case R.id.btTopLangu1://第1言語
                //_glog.log("btTopLangu1 click");
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:ChooseLangu SELLANG:0");
                changeLang(0);
                _gDrawTopFlag = F_VIEW_STATE_CLOSE_CMD;//add 20160629
                break;
            case R.id.btTopLangu2://第2言語
                //_glog.log("btTopLangu2 click");
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:ChooseLangu SELLANG:1");
                changeLang(1);
                _gDrawTopFlag = F_VIEW_STATE_CLOSE_CMD;//add 20160629
                break;
            case R.id.btTopLangu3://第3言語
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:ChooseLangu SELLANG:2");
                // _glog.log("btTopLangu3 click");
                changeLang(2);
                _gDrawTopFlag = F_VIEW_STATE_CLOSE_CMD;//add 20160629
                break;
            //ver 75
            case R.id.btTopLangu4://第4言語
                //_glog.log("btTopLangu4 click");
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:ChooseLangu SELLANG:3");
                changeLang(3);
                _gDrawTopFlag = F_VIEW_STATE_CLOSE_CMD;//add 20170422
                break;
            case R.id.btTopLangu5://第5言語
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:ChooseLangu SELLANG:4");
                //_glog.log("btTopLangu5 click");
                changeLang(4);
                _gDrawTopFlag = F_VIEW_STATE_CLOSE_CMD;//add 20170422
                break;
            case R.id.btTopLangu6://第6言語
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:ChooseLangu SELLANG:5");
                // _glog.log("btTopLangu6 click");
                //changeLang(5);
                _gDrawTopFlag = F_VIEW_STATE_CLOSE_CMD;//add 20170422
                break;
            case R.id.btTopBack:
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:TopBack");
                _gDrawTopFlag = F_VIEW_STATE_CLOSE_CMD;//add 20150422
                break;
            //test Page
            case R.id.btTesCat:
                _gTestCategoryCount++;
                switch (_gTestCategoryCount) {
                    case 1:
                        _gTestCatStr = _gPreferencesSave.PrintPreferencesSavet();
                        break;
                    case 2:

                        break;
                    default:
                        _gTestCategoryCount = 0;
                        break;
                }

                //add 20171026
            case R.id.btFlash:
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:Flash");
                _gDrawFlashingFlag = F_VIEW_STATE_CLOSE_CMD;
                break;
            case R.id.btTes1://win test
                _glog.log("btTes1:win test");
                FLAG_GAME_TEST_ON = 2;
                break;
            case R.id.btTes2://lose test
                _glog.log("btTes2:lose test");
                FLAG_GAME_TEST_ON = 1;
                break;
            case R.id.btTes4:
                _glog.log("btTes4:強制会計 BtOn");
                kaikeiRiset();
                _g_IO_RESET_STATE_CLEAR_CNT = 10;
                FLAG_IO_RESET_STATE_SARA_CLEAR = 1;
                break;
           /* case R.id.btTes5:
                _glog.log("btTes5:Reboot BtOn");
                this.Reboot();
                break;*/
            case R.id.btTesWifiOnOFF:
                _glog.log("btTesWifiOnOFF BtOn");
                break;

            case R.id.WifiTest:
                _gDrawNumFlag = F_VIEW_STATE_OPEN_CMD;
                _gWifiTestOnFlag = 1;//wifi用に強制的にtable番号をセットするため
                _glog.log("BtWifiTest BtOn");
                break;
            case R.id.btNumOk:    //num ボタンを押した場合
                if (_gDrawOrderFlag != F_VIEW_STATE_CLEAR) {
                    // 20201224 ~ ver311 second request
                    if (_g_SECOND_REQUEST_ON == 1) {
                        PostToAlertSystem(_gNumViewVal);
                        // _g_SECOND_REQUEST_ON = 0;
                    } else {
                        if (_gthreadSendCmd != null) {
                            if (_gOPT_OPT_CODE != 0 && _gOPT_SELECTED_OPT_CODE != 0) {
                                _gthreadSendCmd.setOrderStr2(_gNetaInfo.getId(_gOrderItemKey), _gOrderUnitCount, 0, _gNumViewVal, _gSettingInfo.GetOrderTimeStamp(), _gOPT_SELECTED_OPT_CODE);
                            } else {
                                _gthreadSendCmd.setOrderStr2(_gNetaInfo.getId(_gOrderItemKey), _gOrderUnitCount, 0, _gNumViewVal, _gSettingInfo.GetOrderTimeStamp(), 0);
                            }
                            _gthreadSendCmd.setFlagCheackOn();//即時発行
                        }
                    }
                    _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                    _gDrawOrderCheckFlag = F_VIEW_STATE_OPEN_CMD;
                }

                if (_gTestOpenCountButtonOn > 5) {
                    if (_gNumViewVal == 60) {
                        _glog.log("テスト画面手動OPEN");
                        _gDrawTestFlag = F_VIEW_STATE_OPEN_CMD;
                    }
                    _gTestOpenCountButtonOn = 0;
                }

                if (_gWifiTestOnFlag == 1) {
                    if (_gNumViewVal > 0 && _gNumViewVal <= 60) {
                        _gPreferencesSave.saveTableNumber(_gNumViewVal);
                        _gTtableNum = _gNumViewVal;
                        Nttest();
                        _glog.log("_gWifiTestOnFlag>" + _gWifiInfo);
                    }
                    _gWifiTestOnFlag = 0;
                }
                view.setEnabled(false);//back button gone
                _gDrawNumFlag = F_VIEW_STATE_CLOSE_CMD;
                break;
            case R.id.btNumClear:
                _gNumViewVal = 0;
                break;
            case R.id.btNumBack:
                _gNumViewVal = 0;
                _gDrawNumFlag = F_VIEW_STATE_CLOSE_CMD;
                break;

            //add 20180616 order recommend
            case R.id.bt_recm_jump:
                int _jumpId = _gNetaInfo.getRecommendJumpId(_gRecommendKey);
                operate_order_page(_jumpId, 1);
                //_glog.log("bt_recm_jump:"+_jumpId);
                _gDrawReccomendWebFlag = F_VIEW_STATE_CLOSE_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:RecommendJump");
                break;
            case R.id.bt_recm_back://add 20180616
                //_glog.log("bt_recm_back");
                _gDrawReccomendWebFlag = F_VIEW_STATE_CLOSE_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:RecommendBack");
                break;
            //add 20180616 order recommend

            //=================================ver80 HELP画面
            case R.id.btHELP_Lange:
                // _glog.log("btHELP_Lange click");
                _gDrawTopFlag = F_VIEW_STATE_OPEN_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:ChooseLang");
                break;
            case R.id.btHELP_Case1:
                _glog.log("btHELP_Case1 click");
                break;
            case R.id.btHELP_Case2:
                _glog.log("btHELP_Case2 click");
                break;
            case R.id.btHELP_Case3:
                _glog.log("btHELP_Case3 click");
                break;
            case R.id.btHELP_Ok:
                _glog.log("btHELP_Ok click");

                if (_gHELP_STEP == HELP_STEP_SENDKUN_MOV) {
                    _glog.log("鮮度君の動画を開始");
                    //_gDrawHELPFMovFlag = F_VIEW_STATE_OPEN_CMD;
                    _gHELP_STEP = HELP_STEP_GAME_SKIP_CHECK;
                    break;
                } else if (_gHELP_STEP == HELP_STEP_GAME_SKIP_CHECK) {
                    //_glog.log("ゲームスキップをONに設定");
                    _gHELP_STEP = HELP_STEP_GAME_SKIP_ON;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:GameSkipOn");
                    _gSKIP_GAME_USE = 1;
                    _gPreferencesSave.saveSKIP_GAME_USE(1);
                    _gTableMain_Flag = 1;
                    //ver83 20170614 =====game skipを押した場合の挙動=====
                    // TODO 20220321 #37 ゲームチケット取得に失敗しているケースがある #40 ２重案内で異常のケースが見られる
                    /*
                    if(_FLAG_LINKQRCODE_USE_ON == 1){
                        if(_gthreadFtpClient.CheckQrFile() == false){
                            _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:GAMEWIN,Msg:案内時にQrコード画像が存在しないため取得,TAG:DxGame");
                            _gthreadFtpClient.SetQrFtpFlag();
                            Thread th = new Thread(_gthreadFtpClient);
                            th.start();
                        }
                    }
                    */
                    // TODO 20220321 #37 ゲームチケット取得に失敗しているケースがある
                    break;
                }
                break;
            case R.id.btHELP_BACK:
                _glog.log("btHELP_BACK click");
                break;
            case R.id.btHELP_NO:
                _glog.log("btHELP_NO click");
                if (_gHELP_STEP == HELP_STEP_SENDKUN_MOV) {
                    _glog.log("鮮度君の動画をスキップ");
                    _gHELP_STEP = HELP_STEP_GAME_SKIP_CHECK;
                    break;
                } else if (_gHELP_STEP == HELP_STEP_GAME_SKIP_CHECK) {
                    _glog.log("ゲームスキップをOFF設定");
                    _gHELP_STEP = HELP_STEP_GAME_SKIP_OFF;
                    //ver83 20170614 =====game skipを押した場合の挙動=====
                    //_glog.log("_gSKIP_GAME_USE 使用しない");
                    //_glog.logOut(getApplicationContext(),"GAME_SKIP_OFF ID:"+_gTAG_CUSTOMOER_ID);
                    // _glog.LogJson(getApplicationContext(),"LOGCAT:SAVE,CMD:Game,GAMESTATE:SKIP_OFF");
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:BtClick,BUTTON:GameSkipOff");
                    _gSKIP_GAME_USE = 0;
                    _gPreferencesSave.saveSKIP_GAME_USE(0);
                    _gTableMain_Flag = 1;
                    //ver83 20170614 =====game skipを押した場合の挙動=====
                    break;
                }
                break;
           /* case R.id.ID_HLEPMOV_VIEW:
                _glog.log("help動画を停止");
                _gDrawHELPFMovFlag = F_VIEW_STATE_CLOSE_CMD;
                break;*/
            //add 20180901 操作案内操作
            case R.id.SousaAnnaiWeb:
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:SousaAnnaiWeb");
                _gDrawFlashingFlag = F_VIEW_STATE_CLOSE_CMD;
                break;
            case R.id.btFlash_bt_cat1:
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:btFlash_bt_cat1");
                _gDrawFlashingFlag = F_VIEW_STATE_CLOSE_CMD;

                //add 20181101 この機能はdemo段階　使用しない場合はこのボタンはなくす
                if (_gSousaAnnaiCat == 1) {
                    _gDrawSousaAnnaiFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                //add 20181101 この機能はdemo段階　使用しない場合はこのボタンはなくす
                break;
            case R.id.btFlash_bt_cat2:
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:btFlash_bt_cat2");
                _gDrawFlashingFlag = F_VIEW_STATE_CLOSE_CMD;
                //operate_sousaAnnai_open();
                break;
            case R.id.btFlash_background:
                //add 20190308
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:Flash");
                _gDrawFlashingFlag = F_VIEW_STATE_CLOSE_CMD;
                break;
            case R.id.bt_SousaAnnai_back:
                //_glog.log("help動画を停止");
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:sousa_annai_back");
                _gDrawSousaAnnaiFlag = F_VIEW_STATE_CLOSE_CMD;
                break;

            //202020200515 first free order
           /*  TODO 20211208 不要処理コメントアウト
            // case R.id.btFirstFreeOrder_Ok:
              //   bt = (Button) findViewById(R.id.btFirstFreeOrder_Ok);
             //    bt.setEnabled(false);
             //    bt = (Button) findViewById(R.id.btFirstFreeOrder_NO);
             //    bt.setEnabled(false);
              //   _gFirstFreeOrder_itemCode = _gPreferencesSave.getFIRST_FREE_ORDER_CODE();
             //    if (_gFirstFreeOrder_itemCode == 0) {
             //        _gFirstFreeOrderRetrun = -1;
            //    } else {
             //        _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:btFirstFreeOrder_Ok,item:" + _gFirstFreeOrder_itemCode);
             //        _gthreadSendCmd.setOrderStr2(_gFirstFreeOrder_itemCode, 1, 0, _gTtableNum, _gSettingInfo.GetOrderTimeStamp(), 0);
             //        _gthreadSendCmd.setFlagCheackOn();//即時発行*/
                    // _gFirstFreeOrderEndFlag = 1;
            //    }
                // _gFirstFreeOrderCheckTimer = 10;
            //   break;

            /*  TODO 20211208 不要処理コメントアウト
            case R.id.btFirstFreeOrder_NO:
                // add20200601 本当にいらないか再確認する
                if (_gFirstFreeOrderNoConfirm == true) {
                    _gDrawFirstFreeOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:btFirstFreeOrder_NO");
                    bt = (Button) findViewById(R.id.btFirstFreeOrder_NO);
                    bt.setEnabled(false);
                } else {
                    _gSoundManager.PlaySound(1, 1);//音声要確認
                    _gFirstFreeOrderNoConfirm = true;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:BtClick,BUTTON:btFirstFreeOrder_NO,pcoc:FirstFreeOrderNoConfirm");
                }
                break;
                */
            // 20200608 Opning MOVIE
            case R.id.btMOV2_back:
                bt = (Button) findViewById(R.id.btMOV2_back);
                bt.setEnabled(false);
                if (_gDrawOpenningMoveFlag != F_VIEW_STATE_CLEAR) {
                    try {
                        if (_gvideoViewOpenningMove != null) {
                            if (_gvideoViewOpenningMove.isPlaying()) {
                                _gvideoViewOpenningMove.stopPlayback();
                            }
                            _gvideoViewOpenningMove = null;//change 20160603
                        }
                        _glog.LogJson(getApplicationContext(), "openningMov:btMOV2_back,Count:" + (300 - _gOpenningMoveOnTimer));
                    } catch (Exception e) {
                        _glog.LogJson(getApplicationContext(), "LOGCAT:ERR,CMD:openningMov,msg:" + e.toString());
                    }
                    _gDrawOpenningMoveFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                break;

            // TODO 20220223 案内動画でボタンが表示されない対策　
            case R.id.ID_MOV2_VIEW:
                _glog.log("R.id.ID_MOV2_VIEW");
                if (_gDrawOpenningMoveFlag != F_VIEW_STATE_CLEAR) {
                    try {
                        if (_gvideoViewOpenningMove != null) {
                            if (_gvideoViewOpenningMove.isPlaying()) {
                                _gvideoViewOpenningMove.stopPlayback();
                            }
                            _gvideoViewOpenningMove = null;//change 20160603
                        }
                        _glog.LogJson(getApplicationContext(), "openningMov:MOV2_VIEW_CLICK,Count:" + (300 - _gOpenningMoveOnTimer));
                    } catch (Exception e) {
                        _glog.LogJson(getApplicationContext(), "LOGCAT:ERR,CMD:MOV2_VIEW_CLICK,msg:" + e.toString());
                    }
                    _gDrawOpenningMoveFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                break;
            // TODO 20220223 案内動画でボタンが表示されない対策　

            // 20201224 ~ ver314 second request
            case R.id.OrderSecondRequest:
                _glog.LogJson(getApplicationContext(), "LOGCAT:ERR,CMD:OrderSecondRequest");
                _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                _g_SECOND_REQUEST_ON = 1;
                _gDrawNumFlag = F_VIEW_STATE_OPEN_CMD;
                break;
            // 20201224 ~ ver311 second request

            // TODO　add 20210817 原宿店屋台対応
            case R.id.btArrive_BACK_UNDER:
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:btArrive_BACK");
                //  20210817　このボタンは屋台対応の場合のみ表示される。　１　～　４までの情報を強制的にクリア
                _gOrderUnderInfo.clearFoodStanddispCount();
                _gDrawArriveFlag = F_VIEW_STATE_CLOSE_CMD;
                break;
            // TODO　add 20210817 原宿店屋台対応
            // TODO add 20210913 QrInfo  View_linkqr.xmlを追加
            case R.id.btQrback:
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:btQrback");
                _gDrawLinkQRCodeFlag = F_VIEW_STATE_CLOSE_CMD;
                break;
            case R.id.bt_main_linkqr:
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:bt_main_linkqr");
                _gDrawLinkQRCodeFlag = F_VIEW_STATE_OPEN_CMD;
                break;
            // TODO add 20210913 QrInfo  View_linkqr.xmlを追加
            // TODO 20220305 #34 押上店向けー会計画面の前にQR読み込みの警告画面を出す
            case R.id.btdxalert:
                _gDrawDxGameKaikeiAlertFlag = F_VIEW_STATE_CLOSE_CMD;
                _gDxGameKaikeiAlertCheckFlag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:btdxalertOn,Msg:Qr確認画面の確認を押した,DEBUG_TAG:DxGame");
                break;
            // TODO 20220305 #34 押上店向けー会計画面の前にQR読み込みの警告画面を出す
        }

        //num数字ボタンを押した場合
        for (i = 0; i < _gNUM_BUTTO_ID.length; i++) {
            if (_gNUM_BUTTO_ID[i] == viewID) {
                this.CalViewNum(i);
                break;
            }
        }
    }

    /**
     * second recquest
     */
    private void PostToAlertSystem(int table) {
        String postJson = "";
        int _netaId = _gNetaInfo.getId(_gOrderItemKey);
        String _netaName = _gNetaInfo.getNameFromNetaId(_netaId);
        String _optName = _goptInfo.getNameFromToppingId(_gOPT_SELECTED_OPT_CODE);
        String _time = _glog.getTimeFormt();
        String _msg = "";

        String _host = "192.168.11.1";
        int _port = 5182;
        int _shop = _gPreferencesSave.getMISECODE();
        String _endPoint = "/kura/api/:version/alertSystem/";
        /* postJson = String.format("{\"neta_id\":\"%d\",\"neta_name\":\"%s\",\"option_name\":\"%s\",\"count\":\"%s\",\"table\":\"%d\"}",
                _netaId,_netaName,_optName,_gOrderUnitCount, _gNumViewVal);*/
        if (_gOPT_OPT_CODE != 0 && _gOPT_SELECTED_OPT_CODE != 0) {
            _optName = _goptInfo.getNameFromToppingId(_gOPT_SELECTED_OPT_CODE);
            _msg = String.format(" セカンド通知   %d番  %d個   %s<%s>", table, _gOrderUnitCount, _netaName, _optName);
        } else {
            _msg = String.format(" セカンド通知  %d番  %d個   %s", table, _gOrderUnitCount, _netaName);
        }
        postJson = String.format("{\"shopList\":[%d],\"message\":\"%s\",\"respBtn1\":\"%s\",\"respBtn2\":\"%s\",\"requestedAt\":\"%s\"}",
                _shop, _msg, "確認", "", _time);
        _glog.log("OrderSecondRequest:" + postJson);
        // _ghttpRequestHandle.setPostRequest("192.168.11.1", 5182, "/kura/api/:version/setting/", postJson);
        _ghttpRequestHandle.setPostRequest(_host, _port, _endPoint, postJson);
        Thread th = new Thread(_ghttpRequestHandle);
        th.start();
    }


    /**
     * 言語変更時の処理
     *
     * @param key 言語の番号
     */
    private void changeLang(int key) {
        _glocalStr.setLcalKey(key);
        _glocalStr.setLocalStrData("/upload/LocalStr.csv");
        _gTableDraw_Flag = 1;
        _gTableTag_Flag = 1;
        _gTableMain_Flag = 1;

        //_gIo_use_mode =0の場合はネタ名の多言語化は無視する。
        if (_gIo_use_mode != 0) {
            _gNetaInfo.setLocalNetaName("/upload/LocalNetaName.csv", _glocalStr);
        }
        if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLEAR) {
            _gDrawUpdateAlertFlag = F_VIEW_STATE_OPEN_CMD;
        }
        _gviewUpdateAlertOnTimer = 8;

        if (_goptInfo != null) {
            _goptInfo.setLocalKey(key);
            _goptInfo.setToppingInfo("/upload/toppinginfo.csv");
        }
        _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:ChangeLang,KEY:" + key);

        // 20200330_add
        switch (key) {
            case 0:
                _gLangCode = "ja";
                break;
            case 1:
                _gLangCode = "en";
                break;
            case 2:
                _gLangCode = "cn";
                break;
            case 3:
                _gLangCode = "ko";
                break;
            case 4:
                _gLangCode = null;
                break;
        }
    }

    /**
     * onPause
     */
    int CheckOrderOn() {
        //SYSTEM 制限で注文不可のケース
        if (_gSettingInfoFromServer.getOrderStop_SYSTEM_OVER() == 1) {
            Log.i(_TAG, "CheckOrderOn: OrderStop");
            _gORDER_CHECK_REFUSE_STATE = 99;
            return 99;
        }
        //ORDER STOPで注文不可のケース
        if (_gSettingInfoFromServer.getOrderStop() == 1) {
            _glog.log("CheckOrderOn: OrderStop");
            _gORDER_CHECK_REFUSE_STATE = 98;
            return 98;
        }
        //テーブル単位のorderMAXで注文不可のケース（制限回避ページは注文可能）
        if (_gSettingInfoFromServer.getOrderStop_ORDERMAX(_gCurrentPage) == 1) {
            _glog.log("CheckOrderOn: OrderStop_ORDERMAX");
            _gORDER_CHECK_REFUSE_STATE = 97;
            return 97;
        }
        return 0;
    }


    /**
     * onPause
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (_glog != null) {
            _glog.log("onPause called");
            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:onPause");
        }
        if (_gTHREAD_UdpRegularConnection != null) {
            if (_gTHREAD_UdpRegularConnection.isAlive() == true) {
                if (_glog != null) {
                    _glog.log("_gTHREAD_UdpRegularConnectionを終了させる。");
                }
            }
        }
        //change 20160706
        // finish();
    }

    /**
     * onStop
     */
    @Override
    public void onStop() {
        super.onStop();
        if (_glog != null) {
            _glog.log("onStop called アプリを終了させる。");
            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:onStop");
        }
        if (_gTHREAD_UdpRegularConnection != null) {
            if (_gTHREAD_UdpRegularConnection.isAlive() == true) {
                if (_glog != null) {
                    _glog.log("_gTHREAD_UdpRegularConnectionを終了させる。");
                }
            }
        }
        finish();
    }

    /**
     * 物理ボタンを押した場合
     */

    public boolean dispatchKeyEvent(KeyEvent event) {
        //_glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:DispatchKeyEvent,KEYCODE:" + event.getKeyCode());
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンが押された！
            _glog.log(" dispatchKeyEvent called アプリを終了させる。");
            if (_gTHREAD_UdpRegularConnection != null) {
                if (_gTHREAD_UdpRegularConnection.isAlive() == true) {
                    _glog.log("_gTHREAD_UdpRegularConnectionを終了させる。");
                }
                _gTHREAD_UdpRegularConnection = null;
            }
            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:DispatchKeyEvent,PROC:FinishApp");
            finish();
        }
        return super.dispatchKeyEvent(event);
    }

    /****************************************************************
     * //物理ボタンを押した場合
     *****************************************************************/
    public boolean onKeyUp(int iKeyCode, KeyEvent oKeyEvent) {

        if (oKeyEvent.getAction() == KeyEvent.ACTION_UP) {
        }
        return super.onKeyUp(iKeyCode, oKeyEvent);
    }

    /****************************************************************
     *
     *****************************************************************/
    private void checkNetaGet() {
        int flag = 0;
        if (_gthRevCmd != null) {
            flag = _gthRevCmd.GET_NETA_UPDATE();
        }
        if (flag == 1) {
            if (_gthreadGetFile != null) {
                _gthreadGetFile.setGetNetaInfo_Flag();
            }
        }
    }

    /**
     * SETUP
     * 起動時の処理を記述
     */
    private void checkTagGet() {
        int flag = 0;
        if (_gthRevCmd != null) {
            flag = _gthRevCmd.GET_TAG_UPDATE();
        }
        if (flag == 1) {
            if (_gthreadGetFile != null) {
                _gthreadGetFile.setGetTagInfo_Flag();
            }
        }
    }

    /**
     * SETUP
     * FTP check
     */
    int checkFtpUpdate() {

        int i = 0;
        int check = 0;
        for (i = 0; i < 6; i++) {
            if (_gthreadFtpClient != null) {
                check = _gthreadFtpClient.CompareVer(i, _gFtpVer[i]);
                // _glog.log("checkFtpUpdate:" + _gFtpVer[i] + " check:" +  check);
                if (check > 0) {
                    Thread th = new Thread(_gthreadFtpClient);
                    th.start();
                    switch (i) {
                        case 0:
                            //add 2016 0623
                            _gthreadGetFile.setGetNetaInfo_Flag();
                            _gthreadGetFile.setGetTagInfo_Flag();
                            //add 2016 0623
                            _gDrawFtpUpdatetFlag = F_VIEW_STATE_OPEN_CMD;
                            _gviewFtpUpdatetOnTimer = 100;
                            _gFfpCatStr = "image";
                            //_glog.log("FTP 画像更新開始");
                            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:FtpUpdate,PROC:FtpStart,FTPCAT:Img,FTPVER:" + _gFtpVer[0] + "_" + _gFtpVer[1] + "_" + _gFtpVer[2]);
                            return 11;
                        case 1:
                            _gDrawFtpUpdatetFlag = F_VIEW_STATE_OPEN_CMD;
                            _gviewFtpUpdatetOnTimer = 100;
                            _gFfpCatStr = "sound";
                            // _glog.log("FTP 音声更新開始");
                            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:FtpUpdate,PROC:FtpStart,FTPCAT:Sound,FTPVER:" + _gFtpVer[0] + "_" + _gFtpVer[1] + "_" + _gFtpVer[2]);
                            return 12;
                        case 2:
                            _gDrawFtpUpdatetFlag = F_VIEW_STATE_OPEN_CMD;
                            _gviewFtpUpdatetOnTimer = 500;
                            _gFfpCatStr = "movie";
                            //Log.i(_TAG, "FTP 動画更新開始");
                            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:FtpUpdate,PROC:FtpStart,FTPCAT:Mov,FTPVER:" + _gFtpVer[0] + "_" + _gFtpVer[1] + "_" + _gFtpVer[2]);
                            return 13;
                    }
                }

                check = _gthreadFtpClient.checkEndFlag(i);
                if (check > 0) {
                    _gFtpVer[i] = check;
                    //Log.i(_TAG, "checkFtpUpdate update<" + i + ">" + _gFtpVer[i]);
                    _gPreferencesSave.saveFtpver(i, _gFtpVer[i]);

                    switch (i) {
                        case 0:
                            //_glog.log("FTP 画像更新終了" + _gFtpVer[i]);
                            _gDrawFtpUpdatetFlag = F_VIEW_STATE_CLOSE_CMD;
                            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:FtpUpdate,PROC:FtpEnd,FTPCAT:Img,FTPVER:" + _gFtpVer[0] + "_" + _gFtpVer[1] + "_" + _gFtpVer[2]);
                            return 1;
                        case 1:
                            //_glog.log("FTP 音声更新終了" + _gFtpVer[i]);
                            _gDrawFtpUpdatetFlag = F_VIEW_STATE_CLOSE_CMD;
                            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:FtpUpdate,PROC:FtpEnd,FTPCAT:Sound,FTPVER:" + _gFtpVer[0] + "_" + _gFtpVer[1] + "_" + _gFtpVer[2]);
                            return 2;
                        case 2:
                            //_glog.log("FTP 動画更新終了" + _gFtpVer[i]);
                            _gDrawFtpUpdatetFlag = F_VIEW_STATE_CLOSE_CMD;
                            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:FtpUpdate,PROC:FtpEnd,FTPCAT:Mov,FTPVER:" + _gFtpVer[0] + "_" + _gFtpVer[1] + "_" + _gFtpVer[2]);
                            return 3;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * スマホオード用の動画ファイル名変換
     * 現状ベタ書きです。設定ファイルからの読み込みを検討したい
     */
    private void initSmartMovFileName() {
        _gSmartMovName[0] = "0_SmartWin1.mp4";
        _gSmartMovName[1] = "1_SmartWin2.mp4";
        _gSmartMovName[2] = "2_SmartWin3.mp4";
        _gSmartMovName[3] = "3_SmartWin4.mp4";
        _gSmartMovName[4] = "4_SmartWin5.mp4";
        _gSmartMovName[5] = "5_SmartLose1.mp4";
        _gSmartMovName[6] = "6_SmartLose2.mp4";
        _gSmartMovName[7] = "7_SmartLose3.mp4";
        _gSmartMovName[8] = "8_SmartLose4.mp4";
        _gSmartMovName[9] = "9_SmartLose5.mp4";
        _gSmartMovName[10] = "10_SmartEvent1.mp4";
        _gSmartMovName[11] = "11_SmartEvent2.mp4";
        _gSmartMovName[12] = "12_SmartEvent3.mp4";
        _gSmartMovName[13] = "13_SmartEvent4.mp4";
        _gSmartMovName[14] = "14_SmartEvent5.mp4";
        _gSmartMovName[15] = "15_SmartEvent6.mp4";
        _gSmartMovName[16] = "16_SmartEvent7.mp4";
        _gSmartMovName[17] = "17_SmartEvent8.mp4";
        _gSmartMovName[18] = "18_SmartEvent9.mp4";
        _gSmartMovName[19] = "19_SmartEvent10.mp4";
    }


    /**
     * SETUP
     * 起動時の処理を記述
     */
    public void setup() {
        _gSETUP_COUNT++;
        if (_gstartView != null) {
            _gtmMsg.setText("waiting for the start.........." + _gSETUP_COUNT + "%");
        }
        switch (_gSETUP_COUNT) {
            case 1:
                if (_glog == null) {           //ログラッパークラス作成
                    _glog = new logExtend();
                }
                _gApiVersion = Build.VERSION.SDK_INT;
                initNetaTable();
                //セットアップ画面表示
                _gstartView = this.getLayoutInflater().inflate(R.layout.view_start, (ViewGroup) null);
                _gMAIN_FRAME.addView(_gstartView);
                _gtmTitle = (TextView) findViewById(R.id.tvStartTitle);
                _gtmMsg = (TextView) findViewById(R.id.tvSartMsg);
                _gtmTitle.setText("AndOrder ver" + _gAppver + " " + _gApiVersion);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:StartApp,APPVER:" + _gAppver);
                break;
            case 2:
                //セッティングデーターロード
                if (_gPreferencesSave == null) {
                    _gPreferencesSave = new preferencesSave(getApplicationContext(), _glog);
                }
                this.LoadFtpver();
                this.LoadKaikei_alert_mode();
                this.LoadReserv_mode();
                this.LoadIo_use_mode();
                this.Loadkousoku_arraive_wait();
                this.Loadkousoku_arraive_show_count();

                _gWIFI_CHANGE_OFF = _gPreferencesSave.getWIFI_CHANGE_OFF();
                _gSKIP_GAME_USE = _gPreferencesSave.getSKIP_GAME_USE();
                _gLANG_MAX = _gPreferencesSave.getLANG_MAX();
                _gSINAGIRE_DISP_ON = _gPreferencesSave.getSINAGIRE_DISP_ON();


                //add 20180415 for mutenkura
                _FLAG_MUTENKURA_MODE_ON = _gPreferencesSave.get_FLAG_MUTENKURA_MODE_ON();
                _g24WIFI_USE = _gPreferencesSave.getWIFI_24_USE();
                _gLogLevel = _gPreferencesSave.getLogLevele();
                _gLogErrLevel = _gPreferencesSave.getLogErrLevele();
                _glog.setLogLevl(_gLogLevel);
                _glog.setLogErrLevl(_gLogErrLevel);
                _get_gORDER_CHECK_WAIT_TIME = _gPreferencesSave.getORDER_CHECK_WAIT_TIME();
                //add20180622
                _gMiseCode = _gPreferencesSave.getMISECODE();
                //add 20170921
                _gUSE_SELECT_OPT_FLAG = _gPreferencesSave.getUSE_SELECT_OPT_FLAG();
                this.setTableNum();
                break;
            case 3:
                _gTtableNum = _gPreferencesSave.getTableNumber();
                _glog.log(" _gTtableNum:" + _gTtableNum);
                break;
            case 4:
                //sound class
                if (_gSoundManager == null) {
                    _gSoundManager = new soundManager(_glog);
                }
                _gSoundManager.setHomeDir(getApplicationContext().getFilesDir().toString() + "/sound/");
                if (_gTHREAD_SoundManager != null) {
                    if (_gTHREAD_WaitRevCmd.isAlive() == false) {
                        _gTHREAD_SoundManager = new Thread(_gSoundManager);
                        _gTHREAD_SoundManager.start();
                    }
                } else {
                    _gTHREAD_SoundManager = new Thread(_gSoundManager);
                    _gTHREAD_SoundManager.start();
                }
                break;
            case 5:
                //多言語用クラス
                if (_glocalStr == null) {
                    _glocalStr = new localStr(_gLANG_MAX, getApplicationContext().getFilesDir(), _glog);
                }
                //言語データー読み込み
                _glocalStr.setLocalStrData("/upload/LocalStr.csv");
                break;
            case 6:
                //セット情報　for udp
                if (_gSettingInfo == null) {
                    _gSettingInfo = new settingInfo(_glog);
                    //add 20200110
                    _gSettingInfo.setGameWinArray(getApplicationContext().getFilesDir());
                }
                //確認ボタンでの到着商品管理用
                if (_gOrderUnderInfo == null) {
                    _gOrderUnderInfo = new orderUnderInfo(_glog);
                }
                //特急ボタンでの到着商品管理用
                if (_gOrderHeightInfo == null) {
                    _gOrderHeightInfo = new orderHeightInfo(_glog);
                    //add 20180616分割注文情報の保存用（到着時表示修正のため）
                    _gOrderHeightInfo.initDevidBuf();
                    _gOrderHeightInfo.clearDevidBuf();
                }
                //履歴管理用
                if (_gRirekiInfo == null) {
                    _gRirekiInfo = new rirekiInfo(_glog);
                }
                //会計管理用
                if (_gKaikeiInfo == null) {
                    _gKaikeiInfo = new kaikeiInfo(_glog);
                }
                break;
            case 7:
                //管理用
                if (_gMoveInfo == null) {
                    _gMoveInfo = new moveInfo(_glog);
                }
                _gMoveInfo.setHomeDir(getApplicationContext().getFilesDir().toString() + "/douga/");
                break;
            case 8:
                //FTP更新管理用
                if (_gthreadFtpClient == null) {
                    // TODO add 20210913 get QRINFO
                    // _gthreadFtpClient = new threadFtpClient(getApplicationContext().getFilesDir(), _glog);
                    _gthreadFtpClient = new threadFtpClient(getApplicationContext().getFilesDir(), _glog, _gTtableNum);
                    // TODO add 20210913 get QRINFO
                }
                break;
            case 9:
                //タッチサーバーから取得するセットデーター
                if (_gSettingInfoFromServer == null) {
                    _gSettingInfoFromServer = new settingInfoFromServer(_glog);
                }
                _gSettingInfoFromServer.setTableNum(_gTtableNum);
                break;
            case 10:
                //サーバーでwaitするスレッド立ち上げ
                if (_gthreadWaitRevCmd == null) {
                    _gthreadWaitRevCmd = new threadWaitRevCmd(_glog);
                }
                if (_gTHREAD_WaitRevCmd != null) {
                    if (_gTHREAD_WaitRevCmd.isAlive() == false) {
                        _gTHREAD_WaitRevCmd = new Thread(_gthreadWaitRevCmd);
                        _gTHREAD_WaitRevCmd.start();
                    }
                } else {
                    _gTHREAD_WaitRevCmd = new Thread(_gthreadWaitRevCmd);
                    _gTHREAD_WaitRevCmd.start();
                }
                break;
            case 11:
                //受信用データ解析用クラスを作成
                if (_gthRevCmd == null) {
                    _gthRevCmd = new threadRevCmd(_glog);
                }
                _gthRevCmd.SET_SettingInfo(_gSettingInfo);
                _gthRevCmd.SET_settingInfoFromServer(_gSettingInfoFromServer);
                _gthRevCmd.SET_OrderUnderInfo(_gOrderUnderInfo);
                _gthRevCmd.SET_gOrderHeightInfo(_gOrderHeightInfo);
                _gthRevCmd.SET_threadFtpClient(_gthreadFtpClient);
                _gthRevCmd.SET_threadWaitRevCmd(_gthreadWaitRevCmd);
                if (_gTHREAD_RevCmd != null) {
                    // if (_gTHREAD_RevCmd.isAlive() == false) {
                    if (!_gTHREAD_RevCmd.isAlive()) {
                        _gTHREAD_RevCmd = new Thread(_gthRevCmd);
                        _gTHREAD_RevCmd.start();
                    }
                } else {
                    _gTHREAD_RevCmd = new Thread(_gthRevCmd);
                    _gTHREAD_RevCmd.start();
                }
                break;
            case 12:
                //送信用クラスを作成
                if (_gthreadSendCmd == null) {
                    // TODO add 20211018 APP SERVERのエミュレート対応
                    // _gthreadSendCmd = new threadSendCmd(_glog);
                    // TODO add 20220124 通信ログ強化
                    // _gthreadSendCmd = new threadSendCmd(_glog, _gPreferencesSave.getAPP_SERVER_ADDR(), _gPreferencesSave.getAPP_SERVER_PORT());
                    _gthreadSendCmd = new threadSendCmd(this, _glog, _gPreferencesSave.getAPP_SERVER_ADDR(), _gPreferencesSave.getAPP_SERVER_PORT());
                    // TODO add 20211018 APP SERVERのエミュレート対応
                }
                _gthreadSendCmd.setSetTableNum(_gTtableNum);
                _gthreadSendCmd.SetsettingInfoFromServer(_gSettingInfoFromServer);
                _gthreadSendCmd.SetrirekiInfo(_gRirekiInfo);
                _gthreadSendCmd.SetkaikeiInfo(_gKaikeiInfo);
                _gthRevCmd.SET_threadSendCmd(_gthreadSendCmd);
                if (_gTHREAD_SendCmd != null) {
                    //if (_gTHREAD_RevCmd.isAlive() == false) {
                    if (!_gTHREAD_RevCmd.isAlive()) {
                        _gTHREAD_SendCmd = new Thread(_gthreadSendCmd);
                        _gTHREAD_SendCmd.start();
                    }
                } else {
                    _gTHREAD_SendCmd = new Thread(_gthreadSendCmd);
                    _gTHREAD_SendCmd.start();
                }
                break;
            case 13:
                //ファイル受け取り用クラスを作成（サーバー側実装依存）
                if (_gthreadGetFile == null) {
                    // TODO add 20211018 APP SERVERのエミュレート対応
                    // _gthreadGetFile = new threadGetFile(getApplicationContext().getFilesDir(), _glog);
                     _gthreadGetFile = new threadGetFile(
                             getApplicationContext().getFilesDir(),
                             _glog,
                             _gPreferencesSave.getAPP_SERVER_ADDR(),
                             _gPreferencesSave.getAPP_SERVER_PORT());
                    // TODO add 20211018 APP SERVERのエミュレート対応
                }
                if (_gTHREAD_FtpClient != null) {
                    //if (_gTHREAD_FtpClient.isAlive() == false) {
                    if (!_gTHREAD_FtpClient.isAlive()) {
                        _gTHREAD_FtpClient = new Thread(_gthreadGetFile);
                        _gTHREAD_FtpClient.start();
                    }
                } else {
                    _gTHREAD_FtpClient = new Thread(_gthreadGetFile);
                    _gTHREAD_FtpClient.start();
                }
                break;
            case 14://ネタ情報取得指示
                _gthreadGetFile.setGetNetaInfo_Flag();
                break;
            case 15: //tag情報取得指示
                _gthreadGetFile.setGetTagInfo_Flag();
                break;
            //30~40dirが存在しない場合の処理検討のこと
            case 16:
                if (_goptInfo == null) {
                    _goptInfo = new optInfo(getApplicationContext().getFilesDir(), _glog);
                    _goptInfo.setToppingInfo("/upload/toppinginfo.csv");
                }
                //ネタデーター管理用クラス生成
                if (_gNetaInfo == null) {
                    // TODO merge PAGE MAX 30 20210706
                    _gNetaInfo = new netaInfo(getApplicationContext().getFilesDir(), PAGE_MAX, ORDER_MAX, _glog);
                    // _gNetaInfo = new netaInfo(getApplicationContext().getFilesDir(), 20, 12, _glog);
                }
                // TODO 20211211 後方互換性tag数を確認して７～９で確認する。（7以下は7表示とする。）
                //
                // _gNetaInfo.settagdata();
                _gCuttentTagCount = _gNetaInfo.settagdata();
                _glog.log("★_tagcount★: " + String.valueOf(_gCuttentTagCount));
                _gNetaInfo.setnetadata();
                if (_gIo_use_mode != 0) { //_gIo_use_mode =0の場合はネタ名の多言語化は無視する。
                    _gNetaInfo.setLocalNetaName("/upload/LocalNetaName.csv", _glocalStr);
                }
                _gNetaInfo.setTkaikeiCatInfo("/upload/kaikeiCat.csv");
                _gthreadSendCmd.SetnetaInfo(_gNetaInfo);
                //add 20171109
                _gthreadSendCmd.SetoptInfo(_goptInfo);
                break;
            case 17://add 20180615 recommendItem
                if (_gNetaInfo != null) {
                    _gNetaInfo.initRecommend();
                    _gNetaInfo.setRecommendItem("/upload/recommendItem.csv");
                }
                break;
            case 18:
                //settingデーター取得要求
                _gthreadSendCmd.setFlagReqSetting();
                _gthreadSendCmd.setFlagCheackOn();//送信確認
                break;
            case 19:
                _gthreadSendCmd.setReqSendTime();
                _gthreadSendCmd.setFlagCheackOn();//送信確認
                break;
            case 20:
                _gthreadSendCmd.setFlagReqOffsetTime();
                _gthreadSendCmd.setFlagCheackOn();
                break;
            case 21:
                _gthreadSendCmd.setFlagReqOffsetPage();
                _gthreadSendCmd.setFlagCheackOn();
                break;
            case 22:
                //UDPレギュラー通信の作成
                if (_gthUdp == null) {
                    _gthUdp = new threadUdpRegularConnection(_glog);
                }
                if (_gTHREAD_UdpRegularConnection != null) {
                    // if (_gTHREAD_UdpRegularConnection.isAlive() == false) {
                    if (!_gTHREAD_UdpRegularConnection.isAlive()) {
                        _gTHREAD_UdpRegularConnection = new Thread(_gthUdp);
                        _gTHREAD_UdpRegularConnection.start();
                    }
                } else {
                    _gTHREAD_UdpRegularConnection = new Thread(_gthUdp);
                    _gTHREAD_UdpRegularConnection.start();
                }
                _gthUdp.setTableNum(_gTtableNum);
                break;
            case 23:
                if (_gthreadRevCmdManager == null) {
                    _gthreadRevCmdManager = new threadRevCmdManager(_glog);
                }
                if (_gTHREAD_RevCmdManager != null) {
                    // if (_gTHREAD_RevCmdManager.isAlive() == false) {
                    if (!_gTHREAD_RevCmdManager.isAlive()) {
                        _gTHREAD_RevCmdManager = new Thread(_gthreadRevCmdManager);
                        _gTHREAD_RevCmdManager.start();
                    }
                } else {
                    _gTHREAD_RevCmdManager = new Thread(_gthreadRevCmdManager);
                    _gTHREAD_RevCmdManager.start();
                }
                break;
            case 24://add 20180610
                /*  _FLAG_CREATEQRCODE_USE_ON = _gPreferencesSave.getUSE_ORDER_DEVID_ON_FLAG();
                //_gUSE_SINAGIRE_RECOMMEND_ON_FLAG = _gPreferencesSave.getUSE_SINAGIRE_RECOMMEND_ON_FLAG();
                _gUSE_ORDER_DEVID_ON_FLAG = _gPreferencesSave.getUSE_ORDER_DEVID_ON_FLAG();
                //_gUSE_LAYOUT_PORTRAIT_ON_FLAG = _gPreferencesSave.getUSE_LAYOUT_PORTRAIT_ON_FLAG();*/
               // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加
                _FLAG_LINKQRCODE_USE_ON = _gPreferencesSave.getUSE_LINKQRCODE();
                _gLinkQRMainViewButtonOnFlag = _gPreferencesSave.getLINK_QR_MAIN_VIEW_BUTTON_ON_FLAG();
                // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加

                _gUSE_AUTO_KAIKEI_FLAG = _gPreferencesSave.getUSE_AUTO_KAIKEI_FLAG();
                Log.d("debug", "_gUSE_AUTO_KAIKEI_FLAG : " + String.valueOf(_gUSE_AUTO_KAIKEI_FLAG));

                _gUSE_SURVEY_FLAG = _gPreferencesSave.getUSE_SURVEY_FLAG();
                Log.d("debug", "_gUSE_SURVEY_FLAG : " + String.valueOf(_gUSE_SURVEY_FLAG));

                _gUSE_AUTO_CHECK_FLAG = _gPreferencesSave.getUSE_AUTO_CHECK_FLAG();
                Log.d("debug", "_gUSE_AUTO_CHECK_FLAG : " + String.valueOf(_gUSE_AUTO_CHECK_FLAG));

                // TODO　20220108 ver 406 のマージ  不足分追加　20220131
                _gUSE_SMARTPHONE_CHECK_FLAG = _gPreferencesSave.getUSE_SMARTPHONE_CHECK_FLAG();
                Log.d("debug", "_gUSE_SMARTPHONE_CHECK_FLAG : " + String.valueOf(_gUSE_SMARTPHONE_CHECK_FLAG));
                // TODO　20220108 ver 406 のマージ  不足分追加　20220131

                _glog.setTableNum(_gTtableNum);
                _glog.setMiseCode(_gMiseCode);

                //add 20180901
                _gUSE_SousaANNAI_FLAG = _gPreferencesSave.getUSE_SOUSA_ANNAI_FLAG();

                _gUSE_GAME_END_SAVE = _gPreferencesSave.getUSE_GAME_END_CNT_SAVE();
                //add 20181128 game end をload する
                if (_gUSE_GAME_END_SAVE == 1) {
                    _gSettingInfo.cheakGame(_gPreferencesSave.getGAME_END_CNT_SAVE());
                }
                // TODO marge ２皿対応
                _gDoublePlateAlret_USE = _gPreferencesSave.getUSE_DOUBLE_PLATE_ALERT();

                // TODO marge ２皿 firstOrder
                _gDoublePlateFist_USE = _gPreferencesSave.getUSE_DOUBLE_PLATE_FIRST();

                // TODO　add 20210817 原宿店屋台対応
                // _gUse_FoodStand = 1; for debug
                _gUse_FoodStand = _gPreferencesSave.getUSE_FOOD_STAND();

                // TODO　add 20210817 原宿店屋台対応
                /*
                if(_gUse_FoodStand == 1 ){
                    _gNetaInfo.setmFoodStand();
                }
                */
                break;
            case 25:
                // ver314 20201224 order second request
                _gUSE_SECOND_REQUEST = _gPreferencesSave.getUSE_SECOND_REQUEST();

                TOUCH_TIME_THRESHOLD = gTOUCH_TIME_THRESHOLD_CNT;
                TOUCH_TIME_THRESHOLD_IMG = gTOUCH_TIME_THRESHOLD_CNT;
                TOUCH_TIME_THRESHOLD_CALL = gTOUCH_TIME_THRESHOLD_CNT;
                TOUCH_TIME_THRESHOLD_KAIKEI = gTOUCH_TIME_THRESHOLD_CNT;
                TOUCH_TIME_THRESHOLD_RIREKI = gTOUCH_TIME_THRESHOLD_CNT;

                // ver314 20201224 order second request
                // if(_gUSE_SECOND_REQUEST == 1 && _gReserv_mode_mode ==1 ) {
                if (_ghttpRequestHandle == null) {
                    _ghttpRequestHandle = new httpRequestHandle(_glog);
                }
                //  }
                break;
                // TODO 20211229 25%でとまるという問い合わせが多いので処理分散
            case 26:
                /**
                 * 中間サーバからサーベイ表示画面のコンテンツ取得
                 */
                getHttpZip(1,_glog.getDayFormt());
                break;
            case 28:
                /**
                 * 中間サーバから客席セルフチェック表示画面のコンテンツ取得
                 */
                getHttpZip(2,_glog.getDayFormt());
                break;
            case 30:
                getHttpZip(3,_glog.getDayFormt());
                break;
            case 34:
<<<<<<< HEAD
=======
                // TODO　20220108 ver 406 のマージ 中間サーバからスマホ会計連携画面のコンテンツ取得
                getHttpZip(4, _glog.getDayFormt());
                // TODO　20220108 ver 406 のマージ

>>>>>>> bdd96031fb7465e981103be81608750222a8869a
                initSmartMovFileName();
                break;
            case 35:
                 //add 20181101
                 _gSousaAnnaiCat = _gPreferencesSave.getSOUSA_ANNAI_CAT();

                //add 20200202 バッテリー監視 取得
                _gUSE_BATTEYR_CHECK_FLAG = _gPreferencesSave.getUSE_BATTEYR_CHECK_FLAG();
                if (_gUSE_BATTEYR_CHECK_FLAG == 1) {
                   this.checkBatteryState();
                }
               //add 20200708
               _gQRCODE_DISP_FLAG = _gPreferencesSave.getQRCODE_DISP_FLAG();
               if (_gQRCODE_DISP_FLAG == 1) {
                 findViewById(R.id.bt_main11).setVisibility(View.VISIBLE);
               } else {
                 findViewById(R.id.bt_main11).setVisibility(View.INVISIBLE);
               }
<<<<<<< HEAD
=======

                // TODO　20220207 押上店向け、１F、２Fでコンテンツを分ける処理
                _gUSE_BIKKURAGYO_FLAG = _gPreferencesSave.getUSE_BIKKURAGYO_FLAG();

               // TODO 20220323 #39 当たり数の確認
               // _gSaveGameInfo = new saveGameInfo(_glog);

>>>>>>> bdd96031fb7465e981103be81608750222a8869a
                break;
            case 36:
                _gSETUP_Flag = 0;
                _gTableDraw_Flag = 1;
                _gTableTag_Flag = 1;
                _gTableMain_Flag = 1;

                //add ver75
                changeLang(0);

                pageChange(1, 1);
                _gDrawUpdateAlertFlag = F_VIEW_STATE_CLOSE_CMD;
                //起動画面終了
                _gMAIN_FRAME.removeView(_gstartView);
                _gstartView = null;
                //add 20161031 鍵警告をしないフラグ。（起動時に設定。鍵リセットで有効にする。）
                _gKEY_ALERT_USE_FLAG = 0;
                //ver83 add20171614
                _gSKIP_GAME_USE = 0;
                _gPreferencesSave.saveSKIP_GAME_USE(0);
                //ver84 add20171619
                _gSKIP_GAME_USE_ENABLE_FLAG = _gPreferencesSave.getSKIP_GAME_USE_ENABLE_FLAG();
                //ver102 add20171026
                _gUSE_FLASHING_FLAG = _gPreferencesSave.get_FLASHING_USE_FLAG();
                //ver102 add20171117
                _gUSE_BIGNETA_USE_FLAG = _gPreferencesSave.getUSE_BIGNETA_USE_FLAG();
                //add 20190528

                //   TODO 2021010 未検証　要調査
                for (int cc = 0; cc < 20; cc++) {
                    _gUSE_BIGNETA_PAGE_FLAGS[cc] = _gPreferencesSave.getUSE_BIGNETA_PAGE_FLAGS(cc + 1);
                }
                //   TODO 2021010 未検証　要調査


                //   TODO 20211208 不要処理コメントアウト
                // _gUSE_FirstFreeOrder_FLAG = _gPreferencesSave.getUSE_FIRST_FREE_ORDER_FLAG();
                // _gFirstFreeOrder_itemCode = _gPreferencesSave.getFIRST_FREE_ORDER_CODE();


                //add 20200608 opning movie fucntion
                _gUSE_OpenningMove_FLAG = _gPreferencesSave.getUSE_OPENNINGMOVIE_FLAG();
                _gOpenningMoveNumber = _gPreferencesSave.getOPENNINGMOVIE_NUMVER();

                //add 20200523 sound max
                set_soundVal(0);
                break;
        }
    }

    /**
     * keyの状態を監視し、状態に応じて処理をする。
     */
    public void keyStateProc() {
        if (_gSettingInfo.GetKagiState() == 1) {//かぎON
            if (_gDrawKaikeiFlag == F_VIEW_STATE_CLEAR && _g_IO_RESET_STATE_FLAG == IO_RESET_STATE_CLEAR) {
                _g_IO_RESET_STATE_FLAG = IO_RESET_STATE_ERR; //不正警告画面を表示
                _gDrawKeyAlertFlag = F_VIEW_STATE_OPEN_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:keyStateProc,PROC:KeyErrOpen");
            } else {
                if (_g_IO_RESET_STATE_FLAG == IO_RESET_STATE_CLEAR) { //リセット処理
                    _g_IO_RESET_STATE_FLAG = IO_RESET_STATE_WAIT;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Reset,GAME:"
                            + _gSettingInfo.GetGgameEnd()
                            + ",GAMESKIP:" + _gsikpGameFlag
                            + ",SARA:" + _gSettingInfo.GetSaraCnt()
                            + ",LANG:" + _glocalStr.getLcalKey());
                    this.kaikeiRiset();
                    _g_IO_RESET_STATE_CLEAR_CNT = 10;
                    FLAG_IO_RESET_STATE_SARA_CLEAR = 1;
                    _glog.log("会計リセット処理 ゲームエンド:" + _gSettingInfo.GetGgameEnd());
                }
            }
        } else if (_gSettingInfo.GetKagiState() == 0) {//かぎoff
            if (_g_IO_RESET_STATE_FLAG == IO_RESET_STATE_ERR) {
                _g_IO_RESET_STATE_FLAG = IO_RESET_STATE_CLEAR;//不正警告クローズ
                _glog.log("キー不正警告画面表示をクローズ");
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:keyStateProc,PROC:KeyErrClose");
                _gDrawKeyAlertFlag = F_VIEW_STATE_CLOSE_CMD;
            }
            if (_g_IO_RESET_STATE_FLAG == IO_RESET_STATE_WAIT) {//清掃終了
                _g_IO_RESET_STATE_FLAG = IO_RESET_STATE_CLEAR;//ゲーム積算をクリア
                _gSettingInfo.ClearIOgame_End();
            }
        }
        //会計リセットの後の処理
        if (_g_IO_RESET_STATE_CLEAR_CNT > 0) {//上位サーバーへ一定時間通知し、クリアする
            _g_IO_RESET_STATE_FLAG = IO_RESET_STATE_CLEAR_CMD;
            _g_IO_RESET_STATE_CLEAR_CNT--;
            if (_g_IO_RESET_STATE_CLEAR_CNT <= 0) {
                _g_IO_RESET_STATE_FLAG = IO_RESET_STATE_WAIT;//ゲーム積算をクリアもう１回
                _gSettingInfo.ClearIOgame_End();
                _glog.log("会計リセットの後の処理 ゲームエンド:" + _gSettingInfo.GetGgameEnd());
            }
        }
        //皿カウントリセットの後に皿カウントが０クリアされているかチェック
        if (FLAG_IO_RESET_STATE_SARA_CLEAR > 0) {
            //count 15以上になったら強制的にクリアしてしまう。
            FLAG_IO_RESET_STATE_SARA_CLEAR++;
            if (FLAG_IO_RESET_STATE_SARA_CLEAR > 15) {
                FLAG_IO_RESET_STATE_SARA_CLEAR = 0;
                //add 20160603 ゲームカウントがクリアーされていないケースが見られる。
                _gSettingInfo.ClearIOgame_End();
                _glog.log("リセット後の皿カウントクリアが確認できていない。 ゲームエンド:" + _gSettingInfo.GetGgameEnd());
            }
            //リセット後の皿カウントクリアが確認。
            if (_gSettingInfo.GetSaraCnt() == 0) {
                FLAG_IO_RESET_STATE_SARA_CLEAR = 0;
                //add 20160603 ゲームカウントがクリアーされていないケースが見られる。
                _gSettingInfo.ClearIOgame_End();
                _glog.log("皿リセット確認 ゲームエンド:" + _gSettingInfo.GetGgameEnd());
                // Log.i(_TAG, "リセット後の皿カウントクリアが確認できた。");
            }
        }
    }

    /**
     * ｽｸﾘｰﾝｾｰﾊﾞｰ起動確認
     */
    void checkScreenOn() {
<<<<<<< HEAD

        // _glog.log("checkScreenOn called");
        // _glog.log("_gScreenOpenCntSetCnt:" + _gScreenOpenCntSetCnt);

        if (_gScreenOpenCntSetCnt == 0) {
            _gScreenOpenCntSetCnt = _gSettingInfo.GetScreenOnCount();
        }

       //  _glog.log("_gDrawMovFlag :" + _gDrawMovFlag);
       // _glog.log("_gScreenOpenCntSetCnt :" + _gScreenOpenCntSetCnt);

        if (_gDrawMovFlag == F_VIEW_STATE_CLEAR && _gScreenOpenCntSetCnt != 0) {
            _gPAGE_MOVE_OPEN_CNT++;

            // _glog.log("-> _gPAGE_MOVE_OPEN_CNT:" + _gPAGE_MOVE_OPEN_CNT);
            // _glog.log("->_gScreenOpenCntSetCnt :" + _gScreenOpenCntSetCnt);
=======
        if (_gScreenOpenCntSetCnt == 0) {
            _gScreenOpenCntSetCnt = _gSettingInfo.GetScreenOnCount();
        }
        if (_gDrawMovFlag == F_VIEW_STATE_CLEAR && _gScreenOpenCntSetCnt != 0) {
            _gPAGE_MOVE_OPEN_CNT++;
>>>>>>> bdd96031fb7465e981103be81608750222a8869a
            if (_gPAGE_MOVE_OPEN_CNT >= _gScreenOpenCntSetCnt) {//ｽｸﾘｰﾝｾｰﾊﾞｰを起動
                _gPAGE_MOVE_OPEN_CNT = 0;
                _gDrawMovFlag = F_VIEW_STATE_OPEN_CMD;
                _gScreenOpenCntSetCnt = _gSettingInfo.GetScreenOnCount();//ｽｸﾘｰﾝ起動時間を動的に反映
                // _glog.log("--> _gDrawMovFlag :" + F_VIEW_STATE_OPEN_CMD);
            }
        }
    }

    /**
     * change 20200110
     * 特殊gameMode ON
     * game起動確認
     */

    void checkGameOn2() {
        //add 20190614 スマホオーダー対応　ゲーム
        if (_gCurrentGame == 0 && _gSmartGameCount > 0) {
            if (_gSmartGameType[0] == 2) {// プールしているゲームは処理後ずらすので常に０を処理
                _gCurrentGame = 2;//win
                //_glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,PRC:checkGameOn2,MSG:スマホゲーム当たり");
            } else {
                _gCurrentGame = 1;//lose
               //  _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,PRC:checkGameOn2,MSG:スマホゲームはずれ");
            }
            _gDrawGameFlag = F_VIEW_STATE_OPEN_CMD;
        }

        // game test check
        if (_gCurrentGame == 0 && FLAG_GAME_TEST_ON > 0) {
            if (FLAG_GAME_TEST_ON == 2) {
                _gCurrentGame = 2;//win
            } else {
                _gCurrentGame = 1;//lose
            }
            FLAG_GAME_TEST_ON = 0;
            _gDrawGameFlag = F_VIEW_STATE_OPEN_CMD;
        }

        //game check
        if (_gCurrentGame == 0 && _gDrawGameFlag == F_VIEW_STATE_CLEAR
                && FLAG_IO_RESET_STATE_SARA_CLEAR == 0) {//皿カウントリセット待ちは処理しない

            if (_gPreferencesSave.getUSE_GAME_WITH_ARRAY_FLAG() == 1) {
                _gCurrentGame = _gSettingInfo.gameCheakwithArray();
            } else {
                _gCurrentGame = _gSettingInfo.gameCheak2();
            }


            if (_gCurrentGame > 0) {
                _gDrawGameFlag = F_VIEW_STATE_OPEN_CMD;
                //add 20181128 game end を保存する。
                if (_gUSE_GAME_END_SAVE == 1) {
                    //_glog.log("game save:"+_gSettingInfo.GetGgameEnd());
                    _gPreferencesSave.saveGAME_END_CNT_SAVE(_gSettingInfo.GetGgameEnd());
                }
                //_glog.log("ゲーム開始:" + _gCurrentGame+"皿:"+ _gSettingInfo.GetSaraCnt());
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:checkGameOn,PROC:StartGame,GAMESTATE:" + _gCurrentGame + ",SARA:" + _gSettingInfo.GetSaraCnt());
            }
        }


    }


    /**
     * game起動確認 TODO 20211120 処理削減検討:　使っていないようなのでコメントアウト
     */
    /*
    void checkGameOn() {
        //add 20190614 スマホオーダー対応　ゲーム
        if (_gCurrentGame == 0 && _gSmartGameCount > 0) {
            if (_gSmartGameType[0] == 2) {//プールしているゲームは処理後ずらすので常に０を処理
                _gCurrentGame = 2;//win
            } else {
                _gCurrentGame = 1;//lose
            }
            _gDrawGameFlag = F_VIEW_STATE_OPEN_CMD;
        }
        //add 20190614 スマホオーダー対応　ゲーム

        //game test check
        if (_gCurrentGame == 0 && FLAG_GAME_TEST_ON > 0) {
            if (FLAG_GAME_TEST_ON == 2) {
                _gCurrentGame = 2;//win
            } else {
                _gCurrentGame = 1;//lose
            }
            FLAG_GAME_TEST_ON = 0;
            _gDrawGameFlag = F_VIEW_STATE_OPEN_CMD;
        }
        //game check
        if (_gCurrentGame == 0 && _gDrawGameFlag == F_VIEW_STATE_CLEAR
                && FLAG_IO_RESET_STATE_SARA_CLEAR == 0) {//皿カウントリセット待ちは処理しない
            _gCurrentGame = _gSettingInfo.gameCheak2();
            if (_gCurrentGame > 0) {
                _gDrawGameFlag = F_VIEW_STATE_OPEN_CMD;

                //add 20181128 game end を保存する。
                if (_gUSE_GAME_END_SAVE == 1) {
                    //_glog.log("game save:"+_gSettingInfo.GetGgameEnd());
                    _gPreferencesSave.saveGAME_END_CNT_SAVE(_gSettingInfo.GetGgameEnd());
                }
                //_glog.log("ゲーム開始:" + _gCurrentGame+"皿:"+ _gSettingInfo.GetSaraCnt());
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:checkGameOn,PROC:StartGame,GAMESTATE:" + _gCurrentGame + ",SARA:" + _gSettingInfo.GetSaraCnt());
            }
        }
    }
    */


    /**
     * FTP更新指示をチェックし、更新があったら実行
     * 定期実行するタイマー０．２５秒単位
     */
    void chcekFtpState(int ret) {
        switch (ret) {
            case 1:
                if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLEAR) {
                    _gDrawUpdateAlertFlag = F_VIEW_STATE_OPEN_CMD;
                }
                _gviewUpdateAlertOnTimer = 50;
                _glog.log("FTP ねたデーター更新");
                if (_gNetaInfo == null) {
                    _gNetaInfo = new netaInfo(getApplicationContext().getFilesDir(), 20, 12, _glog);
                }
                //add 2016 0621
                _gNetaInfo.setTkaikeiCatInfo("/upload/kaikeiCat.csv");
                //多言語用クラス
                if (_glocalStr == null) {
                    //_glocalStr= new localStr(getApplicationContext().getFilesDir(),_glog);
                    _glocalStr = new localStr(_gLANG_MAX, getApplicationContext().getFilesDir(), _glog);
                }
                //言語データー読み込み
                _glocalStr.setLocalStrData("/upload/LocalStr.csv");
                _gNetaInfo.setnetadata();
                //add 2016 0621

                // TODO 20211211 後方互換性tag数を確認して７～９で確認する。（7以下は7表示とする。
                // _gNetaInfo.settagdata();
                _gCuttentTagCount = _gNetaInfo.settagdata();
                _gTableTag_Flag = 1;
                _glog.log("★_tagcount★: " + String.valueOf(_gCuttentTagCount));

                //add 20180317
                if (_goptInfo == null) {
                    _goptInfo = new optInfo(getApplicationContext().getFilesDir(), _glog);
                }
                _goptInfo.setToppingInfo("/upload/toppinginfo.csv");
                //add 20180317
                //add 2016 0623
                _gNetaInfo.setImg();
                changeLang(0);
                //add 2016 0623
                _gTableDraw_Flag = 1;
                break;
            case 2:
                if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLEAR) {
                    _gDrawUpdateAlertFlag = F_VIEW_STATE_OPEN_CMD;
                }
                _gviewUpdateAlertOnTimer = 50;
                _glog.log("FTP 音声データー更新");
                if (_gSoundManager == null) {
                    _gSoundManager = new soundManager(_glog);
                }
                _gSoundManager.setHomeDir(getApplicationContext().getFilesDir().toString() + "/sound/");
                break;
            case 3:

                break;
            case 11:
                if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLEAR) {
                    _gDrawUpdateAlertFlag = F_VIEW_STATE_OPEN_CMD;
                }
                _gviewUpdateAlertOnTimer = 15;
                break;
            case 12:
                if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLEAR) {
                    _gDrawUpdateAlertFlag = F_VIEW_STATE_OPEN_CMD;
                }
                _gviewUpdateAlertOnTimer = 15;
                break;
            case 13:
                if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLEAR) {
                    _gDrawUpdateAlertFlag = F_VIEW_STATE_OPEN_CMD;
                }
                _gviewUpdateAlertOnTimer = 15;
                break;
        }
    }

    /**
     * exitApp
     */
    void checkExitApp() {
        if (_gthRevCmd.GET_EXIT_APP() == 1) {
            if (_glog != null) {
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MERHOD:checkExitApp,PROC:finishApp");
            }
            if (_gTHREAD_UdpRegularConnection != null) {
                if (_gTHREAD_UdpRegularConnection.isAlive() == true) {
                    if (_glog != null) {
                        _glog.log("_gTHREAD_UdpRegularConnectionを終了させる。");
                    }
                }
            }
            finish();
        }
    }

    /**
     * Reboot
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    void checkGetLocalStrFile() {
        int ret = _gthreadGetFile.getUpdateLocalStr_Flag();
        if (ret == 1) {
            _gVer_GetFile_LocalStr = _gVer_GetFile_LocalStr_Buf;
            _gVer_GetFile_LocalStr_Buf = 0;
            _glog.log("checkGetLocalStrFile ON ver:" + _gVer_GetFile_LocalStr);
            //言語データー読み込み
            _glocalStr.setLocalStrData("/upload/LocalStr.csv");
            if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLEAR) {
                _gDrawUpdateAlertFlag = F_VIEW_STATE_OPEN_CMD;
            }
            _gviewUpdateAlertOnTimer = 15;
            _gTableDraw_Flag = 1;
            _gTableTag_Flag = 1;
            _gTableMain_Flag = 1;
        } else if (ret == -1) {
            _gVer_GetFile_LocalStr = 9999;
            _gVer_GetFile_LocalStr_Buf = 0;
            _glog.log("checkGetLocalStrFile ON 失敗 ver:" + _gVer_GetFile_LocalStr);
        }
    }

    /**
     * Reboot
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    void checkGetLocalNetaName() {
        int ret = _gthreadGetFile.getUpdateLocalNetaName_Flag();
        if (ret == 1) {
            _gVer_GetFile_LocalNetaName = _gVer_GetFile_LocalNetaName_Buf;
            _gVer_GetFile_LocalNetaName_Buf = 0;
            _glog.log("checkGetLocalNetaName ON ver:" + _gVer_GetFile_LocalNetaName);
            //言語データー読み込み
            if (_gIo_use_mode != 0) { //_gIo_use_mode =0の場合はネタ名の多言語化は無視する。
                _gNetaInfo.setLocalNetaName("/upload/LocalNetaName.csv", _glocalStr);
            }
            if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLEAR) {
                _gDrawUpdateAlertFlag = F_VIEW_STATE_OPEN_CMD;
            }
            _gviewUpdateAlertOnTimer = 15;
            _gTableDraw_Flag = 1;
            _gTableMain_Flag = 1;
        } else if (ret == -1) {
            _gVer_GetFile_LocalNetaName = 9999;
            _gVer_GetFile_LocalNetaName_Buf = 0;

            _glog.log("checkGetLocalNetaName ON  失敗 ver:" + _gVer_GetFile_LocalNetaName);
        }
    }


    /**
     * cheackservertime
     * サーバー時間の確認
     */
    private void checkSeverTime() {
       /* int t = _gSettingInfo.GetOrderTimeStamp();
        if (t == 0) {
            return;
        }
        t = t % 10000;
        if (_gSCHEDULE_WAIT_COUNT > 0) {
            _gSCHEDULE_WAIT_COUNT--;
            return;
        }*/
        //営業終了１
        /*if (t == _gSCHEDULE_1) {
            if (_gSCHEDULE_1_OFF == 0) {//add ver75 20170428
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:SchduleOn,SCHEDULE:1");
                _gUDP_CONNECT_LIMMIT_FLAG = 1;
                _gDRAW_LIMMIT_FLAG = 1;
                _gSCHEDULE_WAIT_COUNT = 30;
                kaikeiRiset();
                return;
            } else {
                _gSCHEDULE_WAIT_COUNT = 30;
                _glog.log("_gSCHEDULE1_OFFのため実行しない");
                return;
            }
        }*/
        //営業終了2
       /* if (t == _gSCHEDULE_2) {
            if (_gSCHEDULE_2_OFF == 0) {//add ver75 20170428
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:SchduleOn,SCHEDULE:2");
                _gUDP_CONNECT_LIMMIT_FLAG = 1;
                _gDRAW_LIMMIT_FLAG = 1;
                _gSCHEDULE_WAIT_COUNT = 10;
                veiwScreenBrightness(3);
                return;
            } else {
                _gSCHEDULE_WAIT_COUNT = 30;
                _glog.log("_gSCHEDULE2_OFFのため実行しない");
                return;
            }
        }*/

        //追加１
        /*if (t == _gSCHEDULE_3) {
            if (_gSCHEDULE_3_OFF == 0) {//add ver75 20170428
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:SchduleOn,SCHEDULE:3");
                _gUDP_CONNECT_LIMMIT_FLAG = 1;
                _gDRAW_LIMMIT_FLAG = 1;
                _gSCHEDULE_WAIT_COUNT = 30;
                kaikeiRiset();
                return;
            } else {
                _gSCHEDULE_WAIT_COUNT = 30;
                _glog.log("_gSCHEDULE3_OFFのため実行しない");
                return;
            }
        }*/
        //追加２
        /*if (t == _gSCHEDULE_4) {
            if (_gSCHEDULE_4_OFF == 0) {//add ver75 20170428
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:SchduleOn,SCHEDULE:4");
                _gUDP_CONNECT_LIMMIT_FLAG = 1;
                _gDRAW_LIMMIT_FLAG = 1;
                _gSCHEDULE_WAIT_COUNT = 10;
                veiwScreenBrightness(3);
                return;
            } else {
                _gSCHEDULE_WAIT_COUNT = 30;
                _glog.log("_gSCHEDULE4_OFFのため実行しない");
                return;
            }
        }*/


        //営業開始１
       /* if (t == _gSCHEDULE_5) {
            if (_gSCHEDULE_5_OFF == 0) {//add ver75 20170428
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,CMD:SchduleOn,SCHEDULE:5");
                _gUDP_CONNECT_LIMMIT_FLAG = 0;
                _gDRAW_LIMMIT_FLAG = 0;
                _gCurrentPage = 1;
                _gTableDraw_Flag = 1;
                _gSCHEDULE_WAIT_COUNT = 30;
                veiwScreenBrightness(2);
                return;
            } else {
                _gSCHEDULE_WAIT_COUNT = 30;
                _glog.log("_gSCHEDULE5_OFFのため実行しない");
                return;
            }
        }*/
        //営業開始2
       /* if (t == _gSCHEDULE_6) {
            if (_gSCHEDULE_6_OFF == 0) {//add ver75 20170428
                _glog.log("_gSCHEDULE_6 営業開始2 called");
                _gUDP_CONNECT_LIMMIT_FLAG = 0;
                _gDRAW_LIMMIT_FLAG = 0;
                _gCurrentPage = 1;
                _gTableDraw_Flag = 1;
                _gSCHEDULE_WAIT_COUNT = 10;
                veiwScreenBrightness(2);
                return;
            } else {
                _gSCHEDULE_WAIT_COUNT = 30;
                _glog.log("_gSCHEDULE6_OFFのため実行しない");
                return;
            }
        }*/

    }


    /**
     * ticker
     * 定期実行するタイマー０．２５秒単位
     */

    private void ticker() {
        String str = null;
        int ret = 0;
//0.25second
        //次へ前へボタン直後の操作は無視する。ノイズで勝手にボタンが押される対策
        if (_gPAGE_CHANGE_BOTTON_ON_COUNT > 0) {
            _gPAGE_CHANGE_BOTTON_ON_COUNT--;
        }
        if (_gORDER_VIEW_BOTTON_ON_COUNT > 0) {
            _gORDER_VIEW_BOTTON_ON_COUNT--;
        }
        if (_gCALLBACK_BOTTON_ON_COUNT > 0) {
            _gCALLBACK_BOTTON_ON_COUNT--;
        }
        if (_gNETAIMG_BOTTON_ON_COUNT > 0) {
            _gNETAIMG_BOTTON_ON_COUNT--;
        }


        //add 20190120 皿投入したら ｽｸﾘｰﾝｾｰﾊﾞｰが開いていたら閉じる
        if (_gSaraCnt_Drawbuf != _gSettingInfo.GetSaraCnt()) {
            if (_gDrawMovFlag != F_VIEW_STATE_CLEAR) {
                _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
                // _glog.log("皿投入で動画中断");
            }
            _gSaraCnt_Drawbuf = _gSettingInfo.GetSaraCnt();
        }
        //add 20190120 皿投入したら ｽｸﾘｰﾝｾｰﾊﾞｰが開いていたら閉じる

//0.5second
        if (_gTimerCount % 2 == 0) {
            // TODO 20211120 処理削減検討:　%3の処理に変更
            // checkGameOn2();//20200110 特殊ゲーム
            keyStateProc();
            if (_gthRevCmd.GET_KOUSOKU_RESERVE_ON() == 1) {
                _gDrawKousokuBackFlag = F_VIEW_STATE_OPEN_CMD;
            }
            // 20161108 制限時間中は処理を抑制
            if (_gUDP_CONNECT_LIMMIT_FLAG == 0) {
                if (_gthUdp.setSendStr(getSendRegularUdpStr())) {
                    _gthUdp.setSendOnFlag();//udp送信を指示
                    if (_gSettingInfo.setIoInfo(_gthUdp.getRevStr())) {
                        checkUdpCmd();
                    }
                } else {
                    _glog.LogJson(getApplicationContext(), "LOGCAT:ERR,CMD:SEND_UDP,PROC:SendStr_NULL");
                }
                /*str = getSendRegularUdpStr();//UDP送受信指示
                // エラー累積時の処理入れること
                if (str != null) {
                    _gthUdp.setSendStr(str);
                    _gthUdp.setSendOnFlag();//udp送信を指示
                    str = _gthUdp.getRevStr();
                    if (str != null) {
                        _gSettingInfo.setIoInfo(str);
                        checkUdpCmd();
                    }
                }*/
            }
            if (_gDRAW_LIMMIT_FLAG == 0) {
                draw();
            }
        }
        //=====================================
//0.75second
        if (_gTimerCount % 3 == 0) {
            if (_gthreadSendCmd.CheakFlagnOn() == 0) {//send待ちのデーター確認
                // TODO 20211120 処理削減検討:　%3の処理に変更
                checkGameOn2();//20200110 特殊ゲーム
                checkNetaGet();//netaデーターが取得されていたら読み込み
                checkTagGet();//
            } else {
                _gthreadSendCmd.setFlagCheackOn();//sendするデーターをチェック
            }

            //=====add 20180611 分割order対象がある場合=====
            // TODO 20211120 処理削減検討:　使用していない処理のためコメントアウト
            /*
            if (_gDevidBuf_netaID != 0 && _gUSE_ORDER_DEVID_ON_FLAG == 1 && _gDevidBuf_Count > 0) {
                if (_gDevidBuf_SELECTED_OPT_CODE != 0) {
                    _gthreadSendCmd.setOrderStr2(_gDevidBuf_netaID, _gDevidBuf_UnitCount, 0, _gTtableNum, _gSettingInfo.GetOrderTimeStamp(), _gDevidBuf_SELECTED_OPT_CODE);
                } else {
                    _gthreadSendCmd.setOrderStr2(_gDevidBuf_netaID, _gDevidBuf_UnitCount, 0, _gTtableNum, _gSettingInfo.GetOrderTimeStamp(), 0);
                }
                //_glog.logOut(getApplicationContext(), "OrderNetaId:" + _gDevidBuf_netaID + " Sara:" + _gDevidBuf_UnitCount + " SEIGEN:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen() + " ORDERD:" + _gSettingInfoFromServer.getCURRENT_ORDER() + " ID:" + _gTAG_CUSTOMOER_ID +"devid:2");
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:Order,PROC:OrderOn,ORDERSTATE:Devid_"
                        + _gDevidBuf_Count + ",ORDERNETAID:"
                        + _gNetaInfo.getId(_gOrderItemKey)
                        + ",OPTCODE:" + _gOPT_SELECTED_OPT_CODE
                        + ",SARA:" + _gOrderUnitCount
                        + ",SEIGEN:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen()
                        + ",ORDERD:" + _gSettingInfoFromServer.getCURRENT_ORDER()
                        + ",ORDER_MAX:" + _gSettingInfoFromServer.getOrderLimitFlag());
                //add 20180610 order分轄機能を使う。END
                //_glog.log( "OrderNetaId:" + _gDevidBuf_netaID + " Sara:" + _gDevidBuf_UnitCount + " SEIGEN:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen() + " ORDERD:" + _gSettingInfoFromServer.getCURRENT_ORDER() + " ID:" + _gTAG_CUSTOMOER_ID +"devid:"+_gDevidBuf_Count);
                _gthreadSendCmd.setFlagCheackOn();
                _gDevidBuf_Count--;
                if (_gDevidBuf_Count <= 0) {
                    _gDevidBuf_netaID = 0;
                    _gDevidBuf_UnitCount = 0;
                    _gDevidBuf_SELECTED_OPT_CODE = 0;
                }
            }
            */
            // TODO 20211120 処理削減検討:　使用していない処理のためコメントアウト
            //HELP page起動チェック
            if (_gthRevCmd != null) {
                ret = _gthRevCmd.GET_HELP_PAGE_OPEN_FLAG();
                if (ret == 1) {
                    _glog.log("_gDrawTestFlag open cmd");
                    if (_gHELP_STEP == HELP_STEP_CLEAR) {//ver85 フラグの現在の状況を
                        _gDrawHELPFlag = F_VIEW_STATE_OPEN_CMD;
                        _gHELP_STEP = HELP_STEP_SENDKUN_MOV;
                    }
                }
                ret = _gthRevCmd.GET_HELP_PAGE_CLOSE_FLAG();
                if (ret == 1) {
                    if (_gHELP_STEP == HELP_STEP_CLEAR) {//ver85 フラグの現在の状況を
                        _glog.log("_gDrawTestFlag close cmd");
                        _gDrawHELPFlag = F_VIEW_STATE_CLOSE_CMD;
                        _gHELP_STEP = HELP_STEP_CLEAR;
                    }
                }
            }
            // 20170613 CMの場合の処理を追加
            if (_gDrawMovFlag == F_VIEW_STATE_CLEAR && _FLAG_CM_MOVE_ON > 0) {
                _gDrawMovFlag = F_VIEW_STATE_OPEN_CMD;
                _glog.log("tikker cm open");
            }
        }
//1.00second
        if (_gTimerCount % 4 == 0) {
            //確認ボタンでの通達処理
            if (_gOrderUnderInfo.doOrder() == 1) {
                if (_gDrawArriveFlag == F_VIEW_STATE_LIVE) {//到着画面表示中
                    _gDrawArriveFlag = F_VIEW_STATE_LIVE_ADD;//商品追加
                } else {
                    _gDrawArriveFlag = F_VIEW_STATE_OPEN_CMD;
                }
            }
            //高速レーンでの処理
            if (_gOrderHeightInfo.doOrder() == 1 && _gDrawArriveKousokuFlag == F_VIEW_STATE_CLEAR) {
                _gDrawArriveKousokuFlag = F_VIEW_STATE_OPEN_CMD;
                _garriveViewKousokuWaitOnTimer = _gkousoku_arraive_wait;//可変にすること
            }
            //add 20161103 add log time
            if (_glog != null && _gSettingInfo != null) {
                _glog.setLogTime(_gSettingInfo.GetOrderTimeStamp());
            }

            //add 20200204 充電警告のOFFセット時間
            if (_gviewLowBatteryAlertOffSetTimer > 0) {
                _gviewLowBatteryAlertOffSetTimer--;
                if (_gviewLowBatteryAlertOffSetTimer <= 0) {
                    _gviewLowBatteryAlertOffSetTimer = 0;
                }
            }


            //=====for debug 20181115 アプリのメモリ情報を取得
           /* if(_gUSE_DebugMemInfo==1) {
                _glog.log("-----memory info----------");
                // アプリのメモリ情報を取得
                _glog.logOut(getApplicationContext(),"totalMemory[KB] = " + (int)(runtime.totalMemory()/1024));
                _glog.log("totalMemory[KB] = " + (int) (runtime.totalMemory() / 1024));
                // 現在使用しているメモリ
                _glog.logOut(getApplicationContext(), "usedMemory[KB] = " + (int)( (runtime.totalMemory() - runtime.freeMemory())/1024) );
                _glog.log("usedMemory[KB] = " + (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024));
                // Dalvikで使用できる最大メモリ
                _glog.logOut(getApplicationContext(), "maxMemory[KB] = " + (int)(runtime.maxMemory()/1024));
                _glog.log("maxMemory[KB] = " + (int) (runtime.maxMemory() / 1024));
                // 空きメモリ
                _glog.logOut(getApplicationContext(),"freeMemory[KB] = " + (int)(runtime.freeMemory()/1024));
                _glog.log("freeMemory[KB] = " + (int) (runtime.freeMemory() / 1024));
                //
                _glog.logOut(getApplicationContext(), "all Memory[MB] = " + String.valueOf(memoryInfo.availMem/1024/1024));
                _glog.log("all Memory[MB] = " + (int) (memoryInfo.availMem / 1024 / 1024));
                // low memory 状態になっていないか
                _glog.logOut(getApplicationContext(), "alive Memory = " + String.valueOf(lowMemory));
                _glog.log("alive Memory = " + String.valueOf(lowMemory));
            }*/
            //=====for debug 20181115ーー
            //20180815 後方互換性のため残すが下記処理はUDP経由のコマンドでの実行を想定するので不要。
            /*if (_gthRevCmd.GET_TOUCHSTATE_STRAT_ON() == 1) {
                _gTouchState_Start0n = 1;
                _gTouchState_Custom_1 = _gthRevCmd.GET_TOUCHSTATE_COUSTOM(0);
                _gTouchState_Custom_2 = _gthRevCmd.GET_TOUCHSTATE_COUSTOM(1);
                _glog.log("tikker 人数入力:ON");
                _glog.log("大人:" + _gTouchState_Custom_1);
                _glog.log("小人:" + _gTouchState_Custom_2);
            }*/
            //20180815 後方互換性のため残すが下記処理はUDP経由のコマンドでの実行を想定するので不要。
           /* if (_gthRevCmd.GET_TOUCHSTATE_STRAT_OFF() == 1) {
                _glog.log("tikker 人数入力:OFF");
                _gTouchState_Start0n = 0;
                _gTouchState_Custom_1 = 0;
                _gTouchState_Custom_2 = 0;
                if (_gDrawHELPFlag != F_VIEW_STATE_CLEAR) {
                    //確認ver85 フラグの現在の状況を
                    _gDrawHELPFlag = F_VIEW_STATE_CLOSE_CMD;
                }
            }*/
            //serverの時間を確認する処理を追加　20161108
            checkSeverTime();
        }
//1.25second
        if (_gTimerCount % 5 == 0) {//1.25
            if (_gthreadGetFile != null) {//ネタデーターをゲットしていたら適用
                if (_gthreadGetFile.getUpdateNetaInfo_Flag() == 1) {
                    if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLEAR) {
                        _gDrawUpdateAlertFlag = F_VIEW_STATE_OPEN_CMD;
                    }
                    _gviewUpdateAlertOnTimer = 8;
                    _gNetaInfo.setnetadata();//ねたデーター更新
                    if (_gIo_use_mode != 0) { //_gIo_use_mode =0の場合はネタ名の多言語化は無視する。
                        _gNetaInfo.setLocalNetaName("/upload/LocalNetaName.csv", _glocalStr);
                    }
                    _gTableDraw_Flag = 1;
                }
                if (_gthreadGetFile.getUpdateTagInfo_Flag() == 1) {
                    if (_gDrawUpdateAlertFlag == F_VIEW_STATE_CLEAR) {
                        _gDrawUpdateAlertFlag = F_VIEW_STATE_OPEN_CMD;
                    }
                    _gviewUpdateAlertOnTimer = 8;

                    // TODO 20211211 後方互換性tag数を確認して７～９で確認する。（7以下は7表示とする。
                    // _gNetaInfo.settagdata();
                    _gCuttentTagCount = _gNetaInfo.settagdata();
                    _gTableTag_Flag = 1;
                    _glog.log("★_tagcount★: " + String.valueOf(_gCuttentTagCount));

                    _gTableTag_Flag = 1;
                }
                this.checkGetLocalNetaName();//add 20160523
                this.checkGetLocalStrFile();//add 20160523
            }
            //add 20161115 add log taime
            //データー更新状態から解除出来ないバグが存在している。20161108
            //データー更新の連続が疑われるが、tiker 側でもチェックを入れる。
            if (_gviewUpdateAlert != null) {
                _gVIEW_DATA_UPDATE_ON_COUNT_CHECK++;
                if (_gVIEW_DATA_UPDATE_ON_COUNT_CHECK > 50) {
                    _gDrawUpdateAlertFlag = F_VIEW_STATE_CLOSE_CMD;
                    _glog.log("UPDATE 画面異常解除");
                }
            }
        }
//-
        if (_gTimerCount % 11 == 0) {
            //FTP更新のチェック
            ret = checkFtpUpdate();
            chcekFtpState(ret);
            //test page起動チェック
            if (_gthRevCmd != null) {
                ret = _gthRevCmd.GET_TEST_PAGE_OPEN_FLAG();
                if (ret == 1) {
                    _glog.log("_gDrawTestFlag open cmd");
                    _gDrawTestFlag = F_VIEW_STATE_OPEN_CMD;
                }
                ret = _gthRevCmd.GET_TEST_PAGE_CLOSE_FLAG();
                if (ret == 1) {
                    _glog.log("_gDrawTestFlag close cmd");
                    _gDrawTestFlag = F_VIEW_STATE_CLOSE_CMD;
                }
            }
        }
        //---10 second add 20161108
        if (_gTimerCount % 41 == 0) {
            if (_gUDP_CONNECT_LIMMIT_FLAG == 1) {
                str = getSendRegularUdpStr();//UDP送受信指示
                // エラー累積時の処理入れること
                if (str != null) {
                    _gthUdp.setSendStr(str);
                    _gthUdp.setSendOnFlag();//udp送信を指示
                    str = _gthUdp.getRevStr();
                    if (str != null) {
                        _gSettingInfo.setIoInfo(str);
                        checkUdpCmd();
                    }
                }
            }
            //20161108　制限時間中処理回数を減らす。
            if (_gDRAW_LIMMIT_FLAG == 1) {
                draw();
            }
        }
        //=====================================
//30second
        if (_gTimerCount % 121 == 0) {
            if (_gthUdp.getNetWorkErr() == true) {//通信エラーが一定回数たまっていたら
                _gthUdp.setStopSendFlag(1);
                _glog.log("getNetWorkErr true");
                if (_g24WIFI_USE == 1) {//2.4Gのwifiをしようする。
                    _glog.log("_g24WIFI_USE 1");
                    Nttest2();
                } else {
                    Nttest();
                }
            }
            if (_gthUdp.getStopSendFlag() == 1) {
                _glog.log("StopSendFlag 1");
                _gthUdp.setStopSendFlag(0);
            }
        }
//60second
        if (_gTimerCount % 241 == 0) {

            //ORDER STOP状態の場合 add 20161031
            if (_gSettingInfoFromServer.getOrderStop() == 1) {
                _glog.log("tiker: OrderStop");
                veiwScreenBrightness(3);
            } else {
                if (_gDRAW_LIMMIT_FLAG == 0) {//add 20161110
                    checkScreenOn();
                }
                _gTableMain_Flag = 1;
                veiwScreenBrightness(2);
            }
            this.checkExitApp();
            //add from 20161228 注文の制限の時間区分を確認する処理を追加
            this.checkOrderCatStat();

            //各種zipをインストール
            /**
             * 中間サーバからアンケート、自動会計画面、QRコード画面のコンテンツ取得
             */
            if (!surveyIsInstalled && _gUSE_SURVEY_FLAG == 1 && zipInstallCount < zipInstallLimit) {
                getHttpZip(1,_glog.getDayFormt());
//                String absolutePath = this.getFilesDir().getPath();
//                File outputPath = new File(absolutePath);
//                String download_file_url = "http://" + _gSurveyUrl + "/survey.zip";
//                String appName = "survey";
//                try {
//                    asyncFileDownload = new AsyncFileDownload(this, download_file_url, outputPath, appName);
//                    surveyIsInstalled = asyncFileDownload.execute().get();
//                    //surveyIsInstalled = new File(outputPath + File.separator + appName).exists();
//                } catch (Exception e) {
//                    _glog.LogJson(this, "Error Async_File_Download " + e.toString());
//                }
            }
            if (!autoCheckIsInstalled && _gUSE_AUTO_CHECK_FLAG == 1 && zipInstallCount < zipInstallLimit) {
                getHttpZip(2,_glog.getDayFormt());
//                String absolutePath = this.getFilesDir().getPath();
//                File outputPath = new File(absolutePath);
//                String download_file_url = "http://" + _gAutoCheckUrl + "/auto_check.zip";
//                String appName = "auto_check";
//                try {
//                    asyncFileDownload = new AsyncFileDownload(this, download_file_url, outputPath, appName);
//                    autoCheckIsInstalled = asyncFileDownload.execute().get();
//                    //autoCheckIsInstalled = new File(outputPath + File.separator + appName).exists();
//                } catch (Exception e) {
//                    _glog.LogJson(this, "Error Async_File_Download " + e.toString());
//                }
            }
            if (!qrIsInstalled && _gQRCODE_DISP_FLAG == 1 && zipInstallCount < zipInstallLimit) {
                getHttpZip(3,_glog.getDayFormt());
//                String absolutePath = this.getFilesDir().getPath();
//                File outputPath = new File(absolutePath);
//                String download_file_url = "http://" + _gQrCodeUrl + "/qr_code.zip";
//                String appName = "qr_code";
//                try {
//                    asyncFileDownload = new AsyncFileDownload(this, download_file_url, outputPath, appName);
//                    qrIsInstalled = asyncFileDownload.execute().get();
//                    //qrIsInstalled = new File(outputPath + File.separator + appName).exists();
//                } catch (Exception e) {
//                    _glog.LogJson(this, "Error Async_File_Download " + e.toString());
//                }
            }

            // TODO　20220108 ver 406 のマージ
            // 中間サーバからスマホ会計画面のコンテンツ取得
            if (!spIsInstalled && _gUSE_SMARTPHONE_CHECK_FLAG == 1 && zipInstallCount < zipInstallLimit) {
                getHttpZip(4, _glog.getDayFormt());
            }
            // TODO　20220108 ver 406 のマージ

            zipInstallCount++;
        }

        //120second
        //add 20200202 バッテリー監視 ON
        if (_gTimerCount % 481 == 0) {
            if (_gUSE_BATTEYR_CHECK_FLAG == 1) {
                this.checkBatteryState();
            }
        }

        //300second
        /*if (_gTimerCount % 1201 == 0) {
            //battery残量チェック
            this.checkBatteryState();
        }*/
//clear reset timer
        if (_gTimerCount > 10000) {
            _gTimerCount = 0;
        }

        /**
         * WebViewの読込み中画面を展開
         */
        if (_gDrawAutoCheckWebViewLoadFlag == F_VIEW_STATE_OPEN_CMD) {
            _gDrawAutoCheckWebViewLoadFlag = F_VIEW_STATE_LIVE;
            _gftpUpdateView = this.getLayoutInflater().inflate(R.layout.view_msg_update, (ViewGroup) null);
            _gMAIN_FRAME.addView(_gftpUpdateView);
            _gftpUpdateViewIndex = _gMAIN_FRAME.indexOfChild(_gftpUpdateView);
        }
        /**
         * WebViewの読込み中画面を閉じる
         */
        if (_gDrawAutoCheckWebViewLoadFlag == F_VIEW_STATE_CLOSE_CMD && _gftpUpdateViewIndex != 0) {
            _gMAIN_FRAME.removeView(_gftpUpdateView);
            _gftpUpdateView = null;
        }
    }

    //===========================================
    //add from 20161228 注文の制限の時間区分を確認する処理を追加
    //===========================================
    void checkOrderCatStat() {

        int h = _gSettingInfo.getServerTimeH();
        int w = _gSettingInfo.getServerTimeW();

        if (h >= 0 && h < 24) {
            if (w >= 0 && w < 7) {
                _gSettingInfoFromServer.setOrderCatStat(h, w);
            }
        }
    }

    /**
     * LIFE CYCLE
     * onStart
     */
    public void onStart() {
        super.onStart();
        if (runnable == null) {
            runnable = new Runnable() {//画面の定期更新処理
                @Override
                //TIMER
                public void run() {
                    _gTimerCount = _gTimerCount + 1;
                    if (_gSETUP_Flag == 1) {
                        setup();
                    } else {
                        ticker();
                    }
                    handler.postDelayed(runnable, 250);
                }
                // }
            };
            handler.postDelayed(runnable, 250);
        }
    }


/****************************************************************
 *regular
 *****************************************************************/
    /**
     * regular送信用 flags:
     * 20200830 ver 322
     * 1
     * _gBatteryLevel
     * _gBatteryStatus
     * _gUSE_SELECT_OPT_FLA
     * _gUSE_OpenningMove_FLAG
     */
    private String getSFlagsState0() {
        String str =
                String.valueOf(1) +
                        String.valueOf(_gBatteryLevel) +
                        String.valueOf(_gBatteryStatus) +
                        String.valueOf(_gUSE_SELECT_OPT_FLAG) +
                        String.valueOf(_gLANG_MAX);
        return str;
    }

    /**
     * regular送信用 flags:
     * 20200830 ver 322
     * 1
     * _gUSE_FLASHING_FLAG
     * _gSKIP_GAME_USE_ENABLE_FLAG
     * 0　　　　　　　　　　　　　　　　　　　　　　　 //　予備
     * _gUSE_OpenningMove_FLAG
     */
    private String getSFlagsState1() {
        String str =
                String.valueOf(1) +
                        String.valueOf(_gUSE_FLASHING_FLAG) +
                        String.valueOf(_gSKIP_GAME_USE_ENABLE_FLAG) +
                        String.valueOf(0) +
                        String.valueOf(_gUSE_OpenningMove_FLAG);
        return str;
    }

    /**
     * regular送信用 flags
     * 20200830 ver 322
     * 1
     * _gUSE_AUTO_KAIKEI_FLAG
     * _gUSE_AUTO_CHECK_FLAG
     * _gQRCODE_DISP_FLAG
     * _gUSE_SURVEY_FLAG
     */
    private String getSFlagsState2() {
        String str =
                String.valueOf(1) +
                        String.valueOf(_gUSE_AUTO_KAIKEI_FLAG) + //　自社製のセルフ会計
                        String.valueOf(_gUSE_AUTO_CHECK_FLAG) + //　NTT製のセルフ会計
                        String.valueOf(_gQRCODE_DISP_FLAG) +
                        String.valueOf(_gUSE_SURVEY_FLAG);
        return str;
    }

    /**
     * regular送信用 flags
     * 20200623
     * 1
     * _g24WIFI_USE
     * _gReserv_mode_mode
     * _gUSE_BATTEYR_CHECK_FLAG
     * _FLAG_MUTENKURA_MODE_ON
     */
    private String getSFlagsState3() {
        String str =
                String.valueOf(1) +
                        String.valueOf(_g24WIFI_USE) +
                        String.valueOf(_gReserv_mode_mode) +
                        String.valueOf(_gUSE_BATTEYR_CHECK_FLAG) +
                        String.valueOf(_FLAG_MUTENKURA_MODE_ON);
        return str;
    }

    /**
     * regular送信用のデーターをセット
     * 20200611
     * udp拡張情報
     */
    private String getSendRegularUdpStr() {
        // String str = null;
        int game_end = _gSettingInfo.GetGgameEnd();
        if (FLAG_IO_RESET_STATE_SARA_CLEAR > 0) {
            game_end = 0;

        }
        int send_kaikei_flag = _gDrawCallFlag == 9 ? 0 : _gDrawKaikeiFlag;
        try {
            String str = new String("999,"
                    + _gTimerCount + ","//incに変えること
                    + _gTtableNum + ","
                    + _gSettingInfo.GetSaraCnt() + ","//
                    + game_end + ","//
                    + _gSettingInfo.GetWinFlag() + ","//
                    + _g_IO_RESET_STATE_FLAG + ","//reset
                    + _gDrawCallFlag + ","//
                    + send_kaikei_flag + ","//
                    + _gSettingInfoFromServer.getOrderStop() + ","// orderstop
                    // +  _gSKIP_GAME_USE_ENABLE_FLAG+ ","// change 20200623
                    + _gSKIP_GAME_USE + ","// change 20200917 ver 326
                    + _gAppver + ","// appver
                    + _gApiVersion + ","// os ver
                    + _gHttpQrVer + ","// add 20200830 ver 322 ntt製アプリhttpコンテンツのバージョン（QR）
                    + _gHttpAutoCheckVer + ","// add 20200830 ver 322 ntt製アプリhttpコンテンツのバージョン（自動会計)
                    + _gSettingInfo.GetOrderTimeStamp() + ","//orderTime int
                    + _gSettingInfo.GetWinCntAll() + ","//
                    + _gFtpVer[0] + ","//img
                    + _gFtpVer[1] + ","//sound
                    + _gFtpVer[2] + ","//mov
                    + _gHttpSurveyVer + ","// add 20200830 ver 322 ntt製アプリhttpコンテンツのバージョン（アンケート）
                    + _gSettingInfo.GetGameWinRate() + ","// reserve
                    + getSFlagsState0() + ","// add 20200830 ver 322
                    + getSFlagsState1() + ","//add 20200623
                    + getSFlagsState2() + ","//add 20200623
                    + getSFlagsState3() + ","//add 20200623
                    + _gcmd_return + ","//
                    + _gopt1_return + ","//
                    + _gopt2_return + ","//
                    + _gopt3_return //change 20190319 ver223
                    //+ _gpre_cmd_count
            );
            return str;
        } catch (Exception e) {
            _glog.logE("ERR*getSendRegularUdpStr:" + e.toString());
        }
        return null;
    }

    //----------------------------------------------------------------------
    //メモリ使用量表示（デバック用）
//------------------------------------------------------------------------
   /* void sysInfoMemoryUse() {
        //メモリ情報の取得
        // システムで利用可能な空きメモリー
        ActivityManager am = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);

        // 利用可能なメモリーサイズ
        _glog.log("availMem:" + mi.availMem);

        // 利用可能なメモリーサイズが不足の時、解放するか判断する、しきい値(B)
        _glog.log("threshold:" + mi.threshold);

        // システムがメモリ不足と判断しているか
        _glog.log("lowMemory:" + mi.lowMemory);

        // 自プロセスが使用中のメモリー
        int[] pids = new int[1];
        pids[0] = android.os.Process.myPid();
        android.os.Debug.MemoryInfo[] dmi = am.getProcessMemoryInfo(pids);

        // 使用中のメモリーサイズ(KB)
        _glog.log("TotalPrivate:" + dmi[0].getTotalPrivateDirty());

        // プロセスの使用メモリ合計サイズ(KB)
        _glog.log("TotalPss:" + dmi[0].getTotalPss());

        // 共有メモリーの使用合計サイズ(KB)
        _glog.log("TotalShared:" + dmi[0].getTotalSharedDirty());
    }*/

 /*void clearUserData() {
  ActivityManager am = (ActivityManager)getSystemService(Activity.ACTIVITY_SERVICE);
  am.clearApplicationUserData();
 }*/


    /****************************************************************
     * UTIL
     *****************************************************************/
    /**
     * テーブル番号をセット
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    // private void getTableNum() {
    private void setTableNum() {
        String str = null;
        int x = 0;
        int y = 0;

        _glog.log("-getTableNum called-");

        try {
            selfIpInfo ob = new selfIpInfo(_glog);
            str = ob.getLocalIpAddress();
            if (str == null) {
                return;
            }
            ob = null;
            _glog.log("IP>" + str);
            x = str.lastIndexOf(".");
            y = Integer.parseInt(str.substring(x + 1));

            if (y > 10 && y < 70) {
                _gTtableNum = y - 10;
                _glog.log("tableNum>" + _gTtableNum);
                _gPreferencesSave.saveTableNumber(_gTtableNum);
                // _glog.log("tableNum2>" + _gTtableNum);
            } else {
                _glog.logE("ERR:getTableNum:range err IP" + str);
            }
        } catch (Exception e) {
            _glog.logE("ERR:getTableNum:" + e.toString());
        }
    }

    /**
     * 会計リセット処理
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    public void kaikeiRiset() {
        //皿リセット確認処理を検討の事
        //リセットの送信処理
        _gSoundManager.PlaySound(1, 1);
        _gCurrentGame = 0;//カレントゲームをクリア
        // _gSettingInfo.ClearIOgame_End();//ゲーム数積算をクリア
        socketDisconnect(); // 自動会計またはアンケートのソケットを閉じる
        _gDrawKaikeiFlag = F_VIEW_STATE_CLOSE_CMD;
        //Added by 20210426 カギ
        _gDrawCallFlag = F_VIEW_STATE_CLOSE_CMD;

        //TOP画面を使用する場合 <change:2015>初期画面ではトップ画面を表示させないように変更
       /* if( _g_Use_TOP_FLAG==1){
            //_FLAG_PAGE_TOP=SUB_VIEW_OPEN_CMD;//TOP画面表示
        }*/
        //スクリーンセーバーのプレイカウントをクリア
        // _SCREEN_PLAY_COUNT=0;


        //gameTypeModeが２の場合はゲームの動画再生カウントを０にリセット
        if (_gSettingInfo.GetGameTypeMode() == 2) {
            _gMoveInfo.ClearPlayCount();
        }


        //言語設定を変更している場合は０に変更
      /*  if([_localStr getLcalKey] !=0){
            //NSLog(@"Reset Language");
            [self changeLange:1];
        }*/
        //add 20160623
        changeLang(0);

        //1Pを選択
        pageChange(1, 1);
        //add ver83 20170614 ここでクリアーして問題ないか確認する事
        _gTouchState_Start0n = 0;
        _gTouchState_Custom_1 = 0;//大人の人数
        _gTouchState_Custom_2 = 0;//子供の人数*/
        _gSKIP_GAME_USE = 0;//game スキップの設定をクリアー
        _gPreferencesSave.saveSKIP_GAME_USE(0);
        //add ver83 20170614 ここでクリアーして問題ないか確認する事

        //スレッドの監視処理を検討すること
        _gSettingInfo.ClearIOgame_End();//ゲーム数積算をクリア

        //20160624 neta filne要求
        _gthreadGetFile.setGetNetaInfo_Flag();
        //add 20161031 この処理はダメ
        //_gKEY_ALERT_USE_FLAG=0;
        //タッチパネルを使用状態か？
        //_gTOUCH_USE_ON_FLAG = 0;
        //add 20161031

        //ver86 ログ解析用
        //_gTAG_CUSTOMOER_ID++;
        _gTAG_CUSTOMOER_ID = 0;//change 20180811
        _glog.clear_CusmorId();//change 20180811

        //add 20180616分割注文情報の保存用（到着時表示修正のため）
        _gOrderHeightInfo.clearDevidBuf();

        //add 20180627 ログをFTP転送する
        _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:kaikeiRiset");
        if (_gthreadPutLogJson == null) {
            _gthreadPutLogJson = new threadPutLogJson(_gTtableNum, getApplicationContext().getFilesDir(), _glog);
        }

        _gTHREAD_PUTLOGJSHON = new Thread(_gthreadPutLogJson);
        _gTHREAD_PUTLOGJSHON.start();
        _glog.clearLogOpt();

        //add 20181128 game end を保存する。
        if (_gUSE_GAME_END_SAVE == 1) {
            _gPreferencesSave.saveGAME_END_CNT_SAVE(0);
            _glog.log("game save:" + _gSettingInfo.GetGgameEnd());
        }
        /**
         * アンケート、自動会計用フラグの初期化
         */
        _gSeatingTime = null;
        _gSURVEY_DONE_FLAG = 0;

        // add 20200523 api versionが19 = 勝山製　sound max
        if (_gApiVersion < 21) {
            set_soundVal(0);
        }

        // TODO marge ２皿 firstOrder
        _gDoublePlateFistOrderFlag = 0;

       // TODO add 20210913 get QRINFO　メイン画面のボタンを非表示に設定
        _gLinkQRMainViewButtonOnFlag = 0;
        _gPreferencesSave.saveLINK_QR_MAIN_VIEW_BUTTON_ON_FLAG(0);

        // TODO add 20220321 #36 ゲームチケットのQRの処理修正 QRの画像を消去
        _gthreadFtpClient.deleteQrFile();
        // TODO 20220318　無限ループしているパターンが見られるためリセット時は確認済としてき、次の案内時にクリア
        _gDxGameKaikeiAlertCheckFlag = 1;


    }
/****************************************************************
 * 充電状態
 *****************************************************************/
    /**
     * 充電状態
     *
     * @author ys
     * @version 20160523
     * @since 20160523
     * BATTERY_STATUS_CHARGING = 2
     * BATTERY_STATUS_DISCHARGING = 3
     * BATTERY_STATUS_FULL = 5
     * BATTERY_STATUS_NOT_CHARGING = 4
     * BATTERY_STATUS_UNKNOWN = 1
     */
    private void checkBatteryState() {
        IntentFilter intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, intentfilter);
        try {
            _gBatteryLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            _gBatteryStatus = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

            /*int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryPct = _gBatteryLevel / (float)scale;*/
            _glog.log("checkBatteryState:" + _gBatteryStatus);
            _glog.log("_gBatteryLevel:" + _gBatteryLevel);
            // _glog.log("scale:"+ scale );
            // _glog.log("batteryPct:"+ batteryPct );

            if (_gviewLowBatteryAlertTHRESHOLDLevel > _gBatteryLevel) {
                // ver 330 バッテリー異常検知の条件がおかしいバグを修正
                if (_gBatteryStatus == BatteryManager.BATTERY_STATUS_DISCHARGING || _gBatteryStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    //if(_gBatteryStatus != BatteryManager.BATTERY_STATUS_CHARGING || _gBatteryStatus !=BatteryManager.BATTERY_STATUS_FULL ) {
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:LowBatteryON"
                            + ",Status:" + _gBatteryStatus + ",Level:" + _gBatteryLevel);
                    if (_gDrawLowBatteryAlertFlag == F_VIEW_STATE_CLEAR) {
                        _gDrawLowBatteryAlertFlag = F_VIEW_STATE_OPEN_CMD;
                    }
                }
            }

        } catch (NullPointerException e) {
            _glog.log("ERR:checkBatteryState:" + e.toString());
        }
    }

/****************************************************************
 * ネットワーク関連
 *****************************************************************/
    /**
     * ネットワークを確認し接続がなければ接続
     *
     * @author ys
     * @version 20160514
     * _g24WIFI_USE
     * @since 20160512
     */
    public void Nttest() {
        _glog.log("Nttest called");
        //add 20170428 ver75
        if (_gWIFI_CHANGE_OFF == 1) {
            _glog.log("_gWIFI_CHANGE_OFF=1:処理せずに戻る。");
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("->Network Test start\n");
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        boolean wifiEnabled = wifiManager.isWifiEnabled();
        if (!wifiEnabled) {//WIFIが使用可能かチェック
            wifiManager.setWifiEnabled(true);
            sb.append("->wifiEnable fales so  change to true\n");
        }
        wifiManager.startScan();
        try {
            WifiConfiguration conf = null;
            //String ssid2 = SSID_NAME;
            for (WifiConfiguration c0 : wifiManager.getConfiguredNetworks()) {
                if (c0.SSID.replace("\"", "").equals(SSID_NAME)) {
                    conf = c0;
                    sb.append("->SSID:" + conf.SSID.replace("\"", "") + "\n");
                    sb.append("->Ip:" + this.getIpAddress(wifiManager) + "\n");
                    _gWifiInfo = sb.toString();
                    break;
                }
            }
            if (conf != null) {
                wifiManager.enableNetwork(conf.networkId, true);
            } else {//ssidが見つからない場合は登録して再度チャレンジ
                sb.append("->SSID　not found so input ssid" + "\n");
                setSSID();
                for (WifiConfiguration c0 : wifiManager.getConfiguredNetworks()) {
                    if (c0.SSID.replace("\"", "").equals(SSID_NAME)) {
                        conf = c0;
                        wifiManager.enableNetwork(c0.networkId, true);
                        sb.append("->2st SSID:" + conf.SSID + "\n");
                        sb.append("->2st IP:" + this.getIpAddress(wifiManager) + "\n");
                        _gWifiInfo = sb.toString();
                        break;
                    }
                }
            }
            _gWifiInfo = sb.toString();
        } catch (Exception e) {
            sb.append("->ERR Nttest():" + e.toString() + "\n");
            _gWifiInfo = sb.toString();
        }
    }

    /**
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    public void setSSID() {
        try {
            _glog.log("SSIDの登録");
            //WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            String ssid = SSID_NAME;
            String newPassword = SSID_PASS;

            String tempIp = "";
            int t = 0;
            if (_gPreferencesSave != null) {
                t = _gPreferencesSave.getTableNumber();
                if (_gPreferencesSave.getTableNumber() == 0) {
                    tempIp = "192.168.11.99";
                } else {
                    tempIp = "192.168.11." + (t + 10);
                }
            } else {
                tempIp = "192.168.11.99";
            }
            _glog.log("tempIp:" + tempIp);

            WifiConfiguration config = new WifiConfiguration();
            config.SSID = "\"" + ssid + "\"";
            config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.preSharedKey = "\"" + newPassword + "\"";

            InetAddress inetAdd = InetAddress.getByName(tempIp);
            InetAddress inetGate = InetAddress.getByName("192.168.11.1");
            setIpAssignment("STATIC", config);
            setIpAddress(inetAdd, 24, config);
            setGateway(inetGate, config);
            setDNS(inetGate, config);


            int networkId = wifiManager.addNetwork(config); // 失敗した場合は-1となります
            if (networkId == -1) {
                _glog.logE("addNetworkに失敗");
            } else {
                _glog.logE("addNetworkに成功");
            }

            wifiManager.saveConfiguration();
            networkId = wifiManager.updateNetwork(config);
            if (networkId == -1) {
                _glog.logE("updateNetworkに失敗");
            } else {
                _glog.logE("updateNetworkに成功");
            }
        } catch (Exception e) {

        }
    }

    /**
     * DHCP / STATIC / NONE 設定
     *
     * @author ys
     * @version 20160304
     * @since 20160304≈
     */
    public void Nttest2() {
        _glog.log("Nttest2 called");
        //add 20170428 ver75
        if (_gWIFI_CHANGE_OFF == 1) {
            _glog.log("_gWIFI_CHANGE_OFF=1:処理せずに戻る。");
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("->Network Test start\n");
        //WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        boolean wifiEnabled = wifiManager.isWifiEnabled();
        if (!wifiEnabled) {//WIFIが使用可能かチェック
            wifiManager.setWifiEnabled(true);
            sb.append("->wifiEnable fales so  change to true\n");
        }
        wifiManager.startScan();
        try {
            WifiConfiguration conf = null;
            //String ssid2 = SSID_NAME;
            for (WifiConfiguration c0 : wifiManager.getConfiguredNetworks()) {
                if (c0.SSID.replace("\"", "").equals(SSID_NAME24)) {
                    conf = c0;
                    sb.append("->SSID:" + conf.SSID.replace("\"", "") + "\n");
                    sb.append("->Ip:" + this.getIpAddress(wifiManager) + "\n");
                    _gWifiInfo = sb.toString();
                    break;
                }
            }
            if (conf != null) {
                wifiManager.enableNetwork(conf.networkId, true);
            } else {//ssidが見つからない場合は登録して再度チャレンジ
                sb.append("->SSID　not found so input ssid" + "\n");
                //=======2.4g
                setSSID2();
                //=======
                for (WifiConfiguration c0 : wifiManager.getConfiguredNetworks()) {
                    if (c0.SSID.replace("\"", "").equals(SSID_NAME24)) {
                        conf = c0;
                        wifiManager.enableNetwork(c0.networkId, true);
                        sb.append("->2st SSID2:" + conf.SSID + "\n");
                        sb.append("->2st IP2:" + this.getIpAddress(wifiManager) + "\n");
                        _gWifiInfo = sb.toString();
                        break;
                    }
                }
            }
            _gWifiInfo = sb.toString();
        } catch (Exception e) {
            sb.append("->ERR Nttest():" + e.toString() + "\n");
            _gWifiInfo = sb.toString();
        }
    }

    /**
     * DHCP / STATIC / NONE 設定
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */

    public void setSSID2() {
        try {
            _glog.log("SSID2の登録");
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            //WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            String ssid = SSID_NAME24;
            String newPassword = SSID_PASS;

            String tempIp = "";
            int t = 0;
            if (_gPreferencesSave != null) {
                t = _gPreferencesSave.getTableNumber();
                if (_gPreferencesSave.getTableNumber() == 0) {
                    tempIp = "192.168.11.99";
                } else {
                    tempIp = "192.168.11." + (t + 10);
                }
            } else {
                tempIp = "192.168.11.99";
            }
            _glog.log("tempIp:" + tempIp);

            WifiConfiguration config = new WifiConfiguration();
            config.SSID = "\"" + ssid + "\"";
            config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.preSharedKey = "\"" + newPassword + "\"";

            InetAddress inetAdd = InetAddress.getByName(tempIp);
            InetAddress inetGate = InetAddress.getByName("192.168.11.1");
            setIpAssignment("STATIC", config);
            setIpAddress(inetAdd, 24, config);
            setGateway(inetGate, config);
            setDNS(inetGate, config);


            int networkId = wifiManager.addNetwork(config); // 失敗した場合は-1となります
            if (networkId == -1) {
                _glog.logE("addNetworkに失敗");
            } else {
                _glog.logE("addNetworkに成功");
            }

            wifiManager.saveConfiguration();
            networkId = wifiManager.updateNetwork(config);
            if (networkId == -1) {
                _glog.logE("updateNetworkに失敗");
            } else {
                _glog.logE("updateNetworkに成功");
            }
        } catch (Exception e) {

        }
    }


    /**
     * DHCP / STATIC / NONE 設定
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    private void setIpAssignment(String strIpAssign, WifiConfiguration wifiConf) {
        setEnumField(wifiConf, strIpAssign, "ipAssignment");
    }

    /**
     * IP Address設定
     * This is also known as the subnet mask in the context of IPv4 addresses.
     * Typical IPv4 values would be 8 (255.0.0.0), 16 (255.255.0.0) or 24 (255.255.255.0).
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    private void setIpAddress(InetAddress IpAddress, int nPrefixLength, WifiConfiguration wifiConf) {

        Object objLinkProperties = getField(wifiConf, "linkProperties");
        if (objLinkProperties == null) {
            return;
        }
        try {
            Class clsLinkAddress = Class.forName("android.net.LinkAddress");
            Constructor cnstLinkAddress
                    = clsLinkAddress.getConstructor(InetAddress.class, int.class);
            Object objLinkAddress = cnstLinkAddress.newInstance(IpAddress, nPrefixLength);
            ArrayList artkstLinkAddresses
                    = (ArrayList) getDeclaredField(objLinkProperties, "mLinkAddresses");

            if (artkstLinkAddresses != null) {
                artkstLinkAddresses.clear();
                artkstLinkAddresses.add(objLinkAddress);
            }
        } catch (NullPointerException e) {
            _glog.log("ERR setIpAddress:" + e.toString());
        } catch (Exception e) {
            _glog.log("ERR setIpAddress:" + e.toString());
        }
    }

    // Gateway設定
    private void setGateway(InetAddress inetAddr, WifiConfiguration wifiConf) {
        Object objLinkProperties = getField(wifiConf, "linkProperties");
        if (objLinkProperties == null) {
            return;
        }

        try {
            Class clsRouteInfo = Class.forName("android.net.RouteInfo");
            Constructor cnstRouteInfo = clsRouteInfo.getConstructor(InetAddress.class);
            Object objRouteInfo = cnstRouteInfo.newInstance(inetAddr);
            ArrayList arylstRoutes = (ArrayList) getDeclaredField(objLinkProperties, "mRoutes");
            arylstRoutes.clear();
            arylstRoutes.add(objRouteInfo);
        } catch (Exception e) {
            _glog.logE("ERR setGateway");
        }
    }

    //DNS設定
    private void setDNS(InetAddress inetAddr, WifiConfiguration wifiConf) {
        Object objLinkProperties = getField(wifiConf, "linkProperties");
        if (objLinkProperties == null) {
            return;
        }

        try {
            ArrayList<InetAddress> arylstDnses
                    = (ArrayList<InetAddress>) getDeclaredField(objLinkProperties, "mDnses");
            arylstDnses.clear();
            arylstDnses.add(inetAddr);
        } catch (Exception e) {
            _glog.logE("ERR setDNS");
        }
    }

    /**
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    private Object getField(Object object, String strName) {

        try {
            Field field = object.getClass().getField(strName);
            Object objField = field.get(object);
            return objField;
        } catch (Exception e) {
            _glog.logE("ERR getField ");
        }

        return null;
    }

    /**
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    private Object getDeclaredField(Object object, String strName) {
        try {
            Field field = object.getClass().getDeclaredField(strName);
            field.setAccessible(true);
            Object objField = field.get(object);
            return objField;
        } catch (Exception e) {
            _glog.logE("ERR getDeclaredField");
        }
        return null;
    }

    /**
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    private void setEnumField(Object object, String strValue, String strName) {

        try {
            Field field = object.getClass().getField(strName);
            field.set(object, Enum.valueOf((Class<Enum>) field.getType(), strValue));
        } catch (Exception e) {
            _glog.logE("ERR setEnumField");
        }
    }

    // Wi-Fi設定
    private void setWifiConfig(WifiConfiguration wifiConf) {
        // IP Assignment設定
        try {
            setIpAssignment("<DHCP / STATIC / NONE>", wifiConf);
            // IP Address設定
            setIpAddress(InetAddress.getByName("<IP Address>"), 24, wifiConf);
            // Gateway設定
            setGateway(InetAddress.getByName("<Gateway>"), wifiConf);
            // DNS設定
            setDNS(InetAddress.getByName("<DNS>"), wifiConf);
        } catch (Exception e) {
            _glog.logE("ERR  setWifiConfig");
        }
    }


    // IP Assignment設定取得
    private String getIpAssignment(WifiConfiguration wifiConf) {
        return getEnumField(wifiConf, "ipAssignment");
    }

    // IP Address取得
    private String getIpAddress(WifiManager wifiMng) {
        int nIPAddress = wifiMng.getConnectionInfo().getIpAddress();
        return ((nIPAddress >> 0) & 0xFF) + "." + ((nIPAddress >> 8) & 0xFF) + "."
                + ((nIPAddress >> 16) & 0xFF) + "." + ((nIPAddress >> 24) & 0xFF);
    }

    // Gateway取得
   /* private String getGateway(WifiConfiguration wifiConf) {

        _glog.log("getGateway called");
        Object objLinkProperties = getField(wifiConf, "linkProperties");
        _glog.log("getGateway:" + objLinkProperties.toString());
        if (objLinkProperties == null) {
            return "";
        }
        ArrayList arylstRoutes = (ArrayList) getDeclaredField(objLinkProperties, "mRoutes");

        if (arylstRoutes.size() == 0) {
            return "";
        }
        _glog.log("arylstRoutes:" + arylstRoutes.toString());
        Object objRouteInfo = arylstRoutes.get(0);
        _glog.log("objRouteInfo:" + arylstRoutes.toString());
        InetAddress inetAddressGateway = (InetAddress) getDeclaredField(objRouteInfo, "mGateway");
        _glog.log("inetAddressGateway:" + arylstRoutes.toString());

        if (inetAddressGateway == null) {
            return "";
        }
        byte aryGateway[] = inetAddressGateway.getAddress();
        String strGateway = "";
        for (int i = 0; i < 4; i++) {
            if (!strGateway.isEmpty()) strGateway += ".";
            if (aryGateway[i] >= 0) {
                strGateway += aryGateway[i];
            } else {
                strGateway += aryGateway[i] + 256;
            }
        }
        return strGateway;
    }*/

    // DNS取得
   /* private String getDNS(WifiConfiguration wifiConf) {
        Object objLinkProperties = getField(wifiConf, "linkProperties");

        _glog.log("getDNS called");
        if (objLinkProperties == null) {
            return "";
        }
        ArrayList<InetAddress> arylstDnses
                = (ArrayList<InetAddress>) getDeclaredField(objLinkProperties, "mDnses");

        if (arylstDnses.size() == 0) {
            _glog.log("getDNS size 0");
            return "";
        }


        byte aryDNS[] = arylstDnses.get(0).getAddress();
        String strDNS = "";
        for (int i = 0; i < 4; i++) {
            if (!strDNS.isEmpty()) strDNS += ".";
            if (aryDNS[i] >= 0) {
                strDNS += aryDNS[i];
            } else {
                strDNS += aryDNS[i] + 256;
            }
        }
        return strDNS;
    }*/

    // DNS取得
    private String getEnumField(Object object, String strName) {
        try {
            Field field = object.getClass().getField(strName);
            return field.get(object).toString();
        } catch (Exception e) {
            return "";
        }
    }

    // Wi-Fi設定取得
 /*   private void getWifiConfig (WifiConfiguration wifiConf) {
        // IP Assignment設定取得

        try {
            String strIpAssignment = getIpAssignment(wifiConf);
            // IP Address取得
            String strIpAddress = getIpAddress();
// Gateway取得
            String strGateway = getGateway(wifiConf);
// DNS取得
            String strDNS = getDNS(wifiConf);
        }catch (Exception e){

        }
    }*/
// ========================================================
    //
// ========================================================
   /* void Reboot() {
        _gthredadbexe.setFLAG_REBOOTP_ON(1);
    }*/

    /**
     * save
     *
     * @author ys
     * @version 20160304
     * @since 20160506
     */
    public void SaveFtpver() {
        int i = 0;
        for (i = 0; i < 6; i++) {
            _gPreferencesSave.saveFtpver(i, _gFtpVer[i]);
        }
        //_gPreferencesSave.saveCommitr();
    }

    /**
     * load
     *
     * @author ys
     * @version 20160304
     * @since 20160506
     */
    public void LoadFtpver() {
        int i = 0;
        for (i = 0; i < 6; i++) {
            _gFtpVer[i] = _gPreferencesSave.getFtpver(i);
            _glog.log("LoadFtpver:" + _gFtpVer[i]);
        }

    }

    /**
     * load
     *
     * @author ys
     * @version 20160304
     * @since 20160506
     */
    /*public void SaveKaikei_alert_mode() {
        _gPreferencesSave.saveKaikei_alert_mode(this._gkaikei_alert_mode);
        _gPreferencesSave.saveCommitr();
    }*/

    /**
     * load
     *
     * @author ys
     * @version 20160304
     * @since 20160506
     */
    public void LoadKaikei_alert_mode() {
        this._gkaikei_alert_mode = _gPreferencesSave.getKaikei_alert_mode();
    }

    /**
     * load
     *
     * @author ys
     * @version 20160304
     * @since 20160506
     */
    public void SaveReserv_mode() {
        _gPreferencesSave.saveKaikei_alert_mode(this._gReserv_mode_mode);
        // _gPreferencesSave.saveCommitr();
    }

    /**
     * load
     */
    public void LoadReserv_mode() {
        this._gReserv_mode_mode = _gPreferencesSave.getReserv_mode();
        _glog.log("LoadFtpver called:" + _gReserv_mode_mode);

    }

    /**
     * load
     */
    public void SaveIo_use_mode() {
        _gPreferencesSave.saveIo_use_mode(this._gIo_use_mode);
        //_gPreferencesSave.saveCommitr();
    }

    /**
     * load
     */
    public void LoadIo_use_mode() {
        this._gIo_use_mode = _gPreferencesSave.getIo_use_mode();
    }

    /**
     * load
     *
     * @author ys
     * @version 20160304
     * @since 20160506
     */
    public void Savekousoku_arraive_wait() {
        _gPreferencesSave.savekousoku_arraive_wait(this._gkousoku_arraive_wait);
        // _gPreferencesSave.saveCommitr();
    }

    /**
     * load
     *
     * @author ys
     * @version 20160304
     * @since 20160506
     */
    public void Loadkousoku_arraive_wait() {
        if (_gPreferencesSave.getkousoku_arraive_wait() < 0) {
            this._gkousoku_arraive_wait = 0;
        } else {
            this._gkousoku_arraive_wait = _gPreferencesSave.getkousoku_arraive_wait();
        }

    }

    /**
     * load
     *
     * @author ys
     * @version 20160304
     * @since 20160506
     */
    public void Savekousoku_arraive_show_coun() {
        _gPreferencesSave.savekousoku_arraive_show_count(this._gkousoku_arraive_show_count);
        // _gPreferencesSave.saveCommitr();
    }

    /**
     * load
     *
     * @author ys
     * @version 20160304
     * @since 20160506
     */
    public void Loadkousoku_arraive_show_count() {

        if (_gPreferencesSave.getkousoku_arraive_show_count() < 10) {
            this._gkousoku_arraive_show_count = 16;
        } else {
            this._gkousoku_arraive_show_count = _gPreferencesSave.getkousoku_arraive_show_count();
        }
    }

    /**
     * load
     *
     * @author ys
     * @version 20160304
     * @since 20160506
     */
    void restart(int period) {

        Context cnt = getApplicationContext();
        _glog.log("restart app" + period);
        // intent 設定で自分自身のクラスを設定
        Intent mainActivity = new Intent(cnt, MainActivity.class);
        // PendingIntent , ID=0
        //PendingIntent pendingIntent = PendingIntent.getActivity(cnt, 0, mainActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getActivity(cnt, 0, mainActivity, PendingIntent.FLAG_ONE_SHOT);
        // AlarmManager のインスタンス生成
        AlarmManager alarmManager = (AlarmManager) cnt.getSystemService(Context.ALARM_SERVICE);
        // １回のアラームを現在の時間からperiod（５秒）後に実行させる
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + period, pendingIntent);
    }

    /**
     * メモリが少なくなったらキャッシュをクリア
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        _glog.log("onLowMemory called ClearNetaImg exe");
        //sysInfoMemoryUse();
        //_gNetaInfo.ClearNetaImg();

    }

    /*=====================================================
 up:201500606
 aothor:ys
 content:UDP　help
 ====================================================*/
  /*  public String getUdpCmdHelpString() {
        StringBuilder sb = new StringBuilder("");

        sb.append("--UDP COMAND HELP--¥n");
        sb.append("1:show help¥n");
        sb.append("20: 注文拒否フラグをOn¥n");
        sb.append("32: game設定値をリターン ret opt1:winRate opt2:GameMode opt3:GameMode¥n");
        sb.append("33:スクリーンセーバー 設定値リターン ret opt1:ScreenOnCount¥n");
        sb.append("904: 注文拒否フラグをOn¥n");

        return sb.toString();
    }*/


    /*=====================================================
 up:201500606
 aothor:ys
 content:UDP　経由のコマンド処理
 ====================================================*/
    public int checkUdpCmd() {

        int cmd = _gSettingInfo.getCmd();
        int opt1 = _gSettingInfo.getOpt1();
        int opt2 = _gSettingInfo.getOpt2();
        int opt3 = _gSettingInfo.getOpt3();

        // _glog.log("cmd:" + cmd +"opt1:" + opt1+"opt2:" + opt2+"opt3:" + opt3);
        _gcmd_return = cmd;
        if (cmd == 0) {
            _gopt1_return = 0;
            _gopt2_return = 0;
            _gopt3_return = 0;
            _gpre_cmd = 0;
            _gpre_cmd_count = 0;
            return 0;
        }

        //同じコマンドは処理しない。
        if (cmd == _gpre_cmd) {
            _gpre_cmd_count++;
            if (_gpre_cmd_count > 4) {
                _gcmd_return = 0;
                _gopt1_return = 0;
                _gopt2_return = 0;
                _gopt3_return = 0;
                _gpre_cmd_count = 0;
            }
            return 0;
        }

        _glog.log("checkUdpCmd cmd:" + cmd);

        //コマンドに応じて処理実施
        switch (cmd) {
            case 20:// 注文拒否フラグをOn
                _gopt1_return = _gSettingInfoFromServer.setOrderStop_Refuse(1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:OrderRefuseOn,UDPCODE:" + cmd);
                break;
            case 22:// 注文拒否フラグをOFF
                _gopt1_return = _gSettingInfoFromServer.setOrderStop_Refuse(0);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:OrderRefuseOff,UDPCODE:" + cmd);
                break;
            case 32:
                _glog.log("UDP:game設定値確認(32)");
                _gopt1_return = _gSettingInfo.GetGameWinRate();//opt1=当り確率の設定値
                _gopt2_return = _gSettingInfo.GetGameWinResetMode();//opt2=ゲームリセットモードの設定値
                _gopt3_return = _gSettingInfo.GetGameTypeMode();// opt3=ゲームタイプの設定値
                break;
            case 33:
                _glog.log("UDP:スクリーンセーバー 設定値確認(33)");
                _gopt1_return = _gSettingInfo.GetScreenOnCount();
                break;
            case 34:
                _glog.log("UDP:WinPlayCount 設定値確認(34)");
                _gopt1_return = _gSettingInfo.GetGameWinPlayCount();
                break;
            case 80://cm TEST
                if (opt1 < 0 || opt1 > 6) {//cmは１?５とする。
                    _gopt1_return = -1;//err
                    return -1;
                } else {
                    if (_gDrawMovFlag != 0) {
                        _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                    }
                    _FLAG_CM_MOVE_ON = opt1;//cm 番号をセット
                    _gopt1_return = _FLAG_CM_MOVE_ON;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:CmOn,CMNUM:" + opt1 + ",CANGEPAGE:0,UDPCODE:" + cmd);
                }
                break;
            case 81://cm 拡張<change:20150918>
                if (opt1 < 0 || opt1 > 6) {//cmは１?５とする。
                    _gopt1_return = -1;//err
                    return -1;
                }
                if (opt2 < 0 || opt2 > 20) {//pageは２０まで
                    _gopt2_return = -1;//err
                    return -1;
                }

                // ver82 20170613 CMの場合の処理を追加
                if (_gDrawMovFlag != 0) {
                    _gDrawMovFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                _FLAG_CM_MOVE_ON = opt1;//cm 番号をセット
                _gopt1_return = _FLAG_CM_MOVE_ON;
                _gCurrentPage = opt2;
                _gopt2_return = _gCurrentPage;
                this.pageChange(_gCurrentPage, 0);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:CmOn,CMNUM:" + opt1 + ",CANGEPAGE:" + opt2 + ",UDPCODE:" + cmd);
                break;
            case 100://return
                _glog.log("UDP:RET_STAY_INFO");
                _gopt1_return = _gTouchState_Start0n;
                _gopt2_return = _gTouchState_Custom_1;
                _gopt3_return = _gTouchState_Custom_2;
                break;
            case 101://
                if (_gQRCODE_DISP_FLAG == 1) {
                    qrFromMain = 0;
                    _gDrawQrCodeFlag = F_VIEW_STATE_OPEN_CMD;
                }
                // 20200608 Opning MOVIE
                if (_gUSE_OpenningMove_FLAG == 1) {
                    _gDrawOpenningMoveFlag = F_VIEW_STATE_OPEN_CMD;
                }
                //add 20181101
                if (_gUSE_SousaANNAI_FLAG == 1) {
                    _gDrawSousaAnnaiFlag = F_VIEW_STATE_OPEN_CMD;
                }


                if (_gTouchState_Start0n == 0) {//すでにカウントをしている場合は無視
                    _gTouchState_Start0n = 1;
                } else {
                    break;
                }
                if (0 <= opt1 && 6 >= opt1) {
                    _gTouchState_Custom_1 = opt1;
                } else {
                }
                if (0 <= opt2 && 6 >= opt2) {
                    _gTouchState_Custom_2 = opt2;
                } else {
                }
                _gopt1_return = _gTouchState_Custom_1;
                _gopt2_return = _gTouchState_Custom_2;
                _gTAG_CUSTOMOER_ID = opt3;//20180811 touch_frp_managerから送信するIDに同期

                _glog.set_CusmorId(_gTAG_CUSTOMOER_ID);
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,METHOD:checkUdpCmd,CMD:table_in,PROC:Input"
                        + ",AD:" + _gTouchState_Custom_1
                        + ",CH:" + _gTouchState_Custom_2
                        + ",SKIP_GAME_USE_ENABLE:" + _gSKIP_GAME_USE_ENABLE_FLAG
                        + ",UDPCODE:" + cmd);

                //ver84 20170615
                if (_gSKIP_GAME_USE_ENABLE_FLAG == 0) {
                    _glog.log("_gSKIP_GAME_USE_ENABLE_FLAGが０のためゲームスキップを問い合わせはしない。");

                    // TODO 20211208 不要処理コメントアウト
                    // if (_gUSE_FirstFreeOrder_FLAG == 1) {
                    //    _gDrawFirstFreeOrderFlag = F_VIEW_STATE_OPEN_CMD;
                    // }
                    break;
                }
                //ver83 20170614 大人のお客さんの場合にゲームスキップを問い合わせ
                if (_gTouchState_Custom_1 > 0 && _gTouchState_Custom_2 == 0) {
                    _gHELP_STEP = HELP_STEP_GAME_SKIP_CHECK;
                    if (_gDrawHELPFlag == F_VIEW_STATE_CLEAR) {
                        _gDrawHELPFlag = F_VIEW_STATE_OPEN_CMD;
                        _glog.log("ゲームスキップを問い合わせ");
                    }
                }

                // TODO 20211208 不要処理コメントアウト
                // if (_gUSE_FirstFreeOrder_FLAG == 1) {
                //    _gDrawFirstFreeOrderFlag = F_VIEW_STATE_OPEN_CMD;
                // }

                // TODO 20220321 #37 ゲームチケット取得に失敗しているケースがある
                // チケット機能が有効でQRがない場合は端末側から取得する
                if(_FLAG_LINKQRCODE_USE_ON == 1){
                    // TODO 20220318　会計リセットでクリアすると無限ループしているパターンが見られる。案内時に０にするように変更する
                    _gDxGameKaikeiAlertCheckFlag = 0;
                    // TODO 20220323 一旦削除してしまう事とする。

                    // TODO add 20220321 #36 ゲームチケットのQRの処理修正 QRの画像を消去
                    _gthreadFtpClient.deleteQrFile();

                    // TODO ADD 20220324 ここでやると早すぎる？ 当たり前に取得に変更してみる。
                    /*
                     if(_gthreadFtpClient.CheckQrFile() == false){
                    _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:101,Msg:案内時にQrコード画像が存在しないため取得,TAG:DxGame,KaikeiAlertCheck:" + _gDxGameKaikeiAlertCheckFlag);
                    _gthreadFtpClient.SetQrFtpFlag();
                    Thread th = new Thread(_gthreadFtpClient);
                    th.start();
                      }
                    */
                    // TODO ADD 20220324 ここでやると早すぎる？　当たり前に取得に変更してみる。

                }

                // TODO 20220323 案内時の初期値としてビックらポン有効にする #38
                _gSKIP_GAME_USE = 0;
                _gPreferencesSave.saveSKIP_GAME_USE(0);

                break;
            case 102:// add20180811 消費皿数を通知する実験。以下実装する事
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:sheetSara,CMD:input,VALUE:" + opt1 + ",UDPCODE:" + cmd);
                _gSeatGetSara = opt1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,METHOD:checkUdpCmd,CMD:RevSeatSara"
                        + ",VALUE:" + _gSeatGetSara
                        + ",UDPCODE:" + cmd);
                break;

            case 103:// add20180919 滞在情報をクリアーする実験
                _gTouchState_Custom_1 = 0;
                _gTouchState_Custom_2 = 0;
                _gTAG_CUSTOMOER_ID = 0;
                _gTouchState_Start0n = 0;
                _glog.set_CusmorId(_gTAG_CUSTOMOER_ID);
                _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,METHOD:checkUdpCmd,CMD:table_in_reset,PROC:Input_RESET");
                break;

            case 700://
                if (_gthreadPutFile == null) {
                    _gthreadPutFile = new threadPutFile(_gTtableNum, getApplicationContext().getFilesDir(), _glog, "AndOrder_log.txt");
                }
                _gTHREAD_PUTFILE = new Thread(_gthreadPutFile);
                _gTHREAD_PUTFILE.start();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:UploadAndOrderlog,PROC:NotDelete" + ",UDPCODE:" + cmd);
                break;
            case 701://
                if (_gthreadPutFile == null) {
                    _gthreadPutFile = new threadPutFile(_gTtableNum, getApplicationContext().getFilesDir(), _glog, "AndOrder_log.txt");
                }
                _gthreadPutFile.setDelSendFileFlag(1);
                _gTHREAD_PUTFILE = new Thread(_gthreadPutFile);
                _gTHREAD_PUTFILE.start();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:UploadAndOrderlog,PROC:Delete,UDPCODE:" + cmd);
                break;
            case 702://
                _gopt1_return = get_soundVal();
                _glog.log("702:get_soundVal:" + opt1);
                break;
            case 703://
                set_soundVal(1);
                _gopt1_return = get_soundVal();
                _glog.log("703:set_soundVal:" + opt1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:set_soundVal,PROC:1,UDPCODE:" + cmd);
                break;

            case 704://
                set_soundVal(0);
                _gopt1_return = get_soundVal();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:set_soundVal,PROC:0,UDPCODE:" + cmd);
                break;

            //add 20191211 音量可変
            case 705://
                set_soundVal(opt1);
                _gopt1_return = get_soundVal();
                //_glog.log("704:set_soundVal:" + opt1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:set_soundVal,PROC:" + opt1 + ",UDPCODE:" + cmd);
                break;

            case 773:  // セカンドリクエスト通知機能　OFF
                _gPreferencesSave.saveUSE_SECOND_REQUEST(0);
                _gUSE_SECOND_REQUEST = 0;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SecondReqUseOn");
                break;
            case 774:  // セカンドリクエスト通知機能　ON
                _gPreferencesSave.saveUSE_SECOND_REQUEST(1);
                _gUSE_SECOND_REQUEST = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SecondReqUseOff");
                break;
            case 775:  // 自動会計画面のコンテンツ取得 ALL
                zipInstallCount = 0;
                getHttpZip(0, opt1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:GetHttpZip,PROC:ALL,ver:" + opt1 + ",UDPCODE:\"" + cmd);
                reopenHttpZipView();
                break;
            case 776:    // 自動会計画面のコンテンツ取得 QR
                zipInstallCount = 0;
                getHttpZip(1, opt1);
                // _gHttpQrVer = _glog.getDayFormt();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:GetHttpZip,PROC:qr,ver:" + opt1 + ",UDPCODE:\"" + cmd);
                reopenHttpZipView();
                break;
            case 777:   // 自動会計画面のコンテンツ取得 self
                zipInstallCount = 0;
                getHttpZip(2, opt1);
                // _gHttpAutoCheckVer = _glog.getDayFormt();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:GetHttpZip,PROC:AutoCheck,ver:" + opt1 + ",UDPCODE:\"" + cmd);
                reopenHttpZipView();
                break;
            case 778:  // 自動会計画面のコンテンツ取得 アンケート
                zipInstallCount = 0;
                getHttpZip(3, opt1);
                // _gHttpSurveyVer = opt1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:GetHttpZip,PROC:Survey,ver:" + opt1 + ",UDPCODE:\"" + cmd);
                reopenHttpZipView();
                break;
            case 779:
                //各種zipをインストール
                // TODO　20220108 ver 406 のマージ
                zipInstallCount = 0;
                getHttpZip(0, opt1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:GetHttpZip,PROC:ALL,ver:" + opt1 + ",UDPCODE:\"" + cmd);
                reopenHttpZipView();
                // TODO　20220108 ver 406 のマージ
                break;
            case 780: //
                _gPreferencesSave.saveUSE_SOUSA_ANNAI_FLAG(1);
                _gUSE_SousaANNAI_FLAG = _gPreferencesSave.getUSE_SOUSA_ANNAI_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:use_sousa_annai_on,PROC:0,UDPCODE:" + cmd);
                _gTableMain_Flag = 1;
                break;
            case 781: //
                _gPreferencesSave.saveUSE_SOUSA_ANNAI_FLAG(0);
                _gUSE_SousaANNAI_FLAG = _gPreferencesSave.getUSE_SOUSA_ANNAI_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:use_sousa_annai_off,PROC:0,UDPCODE:" + cmd);
                _gTableMain_Flag = 1;
                break;
            // TODO　20220108 ver 406 のマージ
            case 782:   // スマホ会計用コンテンツ取得
                zipInstallCount = 0;
                getHttpZip(4, opt1);
                // _gHttpAutoCheckVer = _glog.getDayFormt();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:GetHttpZip,PROC:SMARTPHONE_CHECK,ver:" + opt1 + ",UDPCODE:\"" + cmd);
                reopenHttpZipView();
                break;
            // TODO　20220108 ver 406 のマージ
            case 890://テスト画面ON
                _gDrawTestFlag = F_VIEW_STATE_OPEN_CMD;
                _gNetaInfo.setnetadata();
                break;
            case 891://テスト画面OFF
                _gDrawTestFlag = F_VIEW_STATE_CLOSE_CMD;
                break;
            //android 拡張
            case 892://kaikei_alert_mode
                if (opt1 == 1) {
                    _gkaikei_alert_mode = 1;
                    //save
                } else {
                    _gkaikei_alert_mode = 0;
                }
                _gPreferencesSave.saveKaikei_alert_mode(_gkaikei_alert_mode);
                _gopt1_return = _gkaikei_alert_mode;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:kaikeiAlertModeChange"
                        + ",VAL:" + _gkaikei_alert_mode
                        + ",UDPCODE:" + cmd);
                break;
            case 893:// RESERVE MODE
                if (opt1 == 1) {
                    _gReserv_mode_mode = 1;
                } else {
                    _gReserv_mode_mode = 0;
                }
                _gPreferencesSave.saveReserv_mode(_gReserv_mode_mode);
                _gopt1_return = _gReserv_mode_mode;
                //_glog.log("UDP:_gReserv_mode_mode" + _gReserv_mode_mode);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:ReserveModeChange"
                        + ",VALUE:" + _gReserv_mode_mode
                        + ",UDPCODE:" + cmd);
                break;
            case 894:// _gkousoku_arraive_show_count
                if (opt1 > 10 && opt1 < 30) {
                    _gkousoku_arraive_show_count = opt1;
                } else {
                    _gkousoku_arraive_show_count = 16;
                }
                _gPreferencesSave.savekousoku_arraive_show_count(_gkousoku_arraive_show_count);
                _gopt1_return = _gkousoku_arraive_show_count;
                //_glog.log("UDP:_gkousoku_arraive_show_count" + _gkousoku_arraive_show_count);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:KousokuArriveShowCountChange,VALUE:"
                        + ",VALUE:" + _gkousoku_arraive_show_count
                        + ",UDPCODE:" + cmd);
                break;
            case 895:// _gkousoku_arraive_wait

                if (opt1 >= 0 && opt1 < 30) {
                    _gkousoku_arraive_wait = opt1;
                    //save
                } else {
                    _gkousoku_arraive_wait = 6;
                }
                _gPreferencesSave.savekousoku_arraive_wait(_gkousoku_arraive_wait);
                _gopt1_return = _gkousoku_arraive_wait;
                // _glog.log("UDP:_gkousoku_arraive_wait" + _gkousoku_arraive_wait);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:KousokuArriveWaitChange,VALUE:"
                        + ",VALUE:" + _gkousoku_arraive_wait
                        + ",UDPCODE:" + cmd);
                break;
            case 897:// セッティング情報表示
                _glog.log(_gSettingInfoFromServer.setingprint());
                break;
            case 898:// セッティング情報表示
                _gSettingInfo.printTest();
                break;
            case 900:// _gLogLevel
                switch (opt1) {
                    case 0:
                        _gLogLevel = 0;
                        break;
                    case 1:
                        _gLogLevel = 1;
                        break;
                    case 2:
                        _gLogLevel = 2;
                        break;
                    default:
                        _gLogLevel = 0;
                        break;
                }
                _gPreferencesSave.saveLogLevele(_gLogLevel);
                _glog.setLogLevl(_gLogLevel);
                _gopt1_return = _gLogLevel;
                //_glog.log("UDP:_gLogLevel:" + _gLogLevel);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:LogLevelChange,VALUE:" + _gLogLevel + ",UDPCODE:" + cmd);
                break;
            case 901:// _gLogErrLevel
                switch (opt1) {
                    case 0:
                        _gLogErrLevel = 0;
                        break;
                    case 1:
                        _gLogErrLevel = 1;
                        break;
                    case 2:
                        _gLogErrLevel = 2;
                        break;
                    default:
                        _gLogErrLevel = 0;
                        break;
                }
                _gPreferencesSave.saveLogErrLevele(_gLogErrLevel);
                _glog.setLogErrLevl(_gLogErrLevel);
                _gopt1_return = _gLogErrLevel;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:LogErrLevelChange,VALUE:" + _gLogLevel + ",UDPCODE:" + cmd);
                break;

            case 902://top 画面使用設定
                _glog.log("UDP:_gIo_use_mode:" + _gIo_use_mode);
                this.setIo_use_mode(opt1);
                _gPreferencesSave.saveIo_use_mode(_gIo_use_mode);
                this.LoadIo_use_mode();
                _gopt1_return = _gIo_use_mode;
                //SaveIo_use_mode();

                if (1 <= opt2 && 6 >= opt2) {
                    _glog.log("使用言語数" + opt2);
                    _gPreferencesSave.saveLANG_MAX(opt2);
                    _gLANG_MAX = opt2;
                    _gopt2_return = _gLANG_MAX;
                    _glocalStr.setLcalMax(_gLANG_MAX);
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:UseLangMode,VALUE:"
                            + ",VALUE:" + _gIo_use_mode
                            + "LANGMAX:" + _gLANG_MAX
                            + ",UDPCODE:" + cmd);
                } else {
                    _glog.log("設定値は１〜６で設定してください。");
                }
                //add 20181127
                _gTableMain_Flag = 1;
                break;

            case 903:
                _glog.log("tableNum再取得");
                this.setTableNum();
                break;
            case 904:
                _g24WIFI_USE = 1;
                _gPreferencesSave.saveWIFI_24_USE(1);
                this.Nttest2();//ネットテストを試みる
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:2.4WifiChnage,VAL:1" + ",UDPCODE:" + cmd);
                break;
            case 905:
                _g24WIFI_USE = 0;
                _gPreferencesSave.saveWIFI_24_USE(0);
                this.Nttest();//ネットテストを試みる
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:2.4WifiChnage,VAL:0" + ",UDPCODE:" + cmd);
                break;

            case 906: // 短いタッチを無視する設定　20 ~ 50で設定
                if (20 <= opt1 && 50 >= opt1) {
                    _gPreferencesSave.saveORDER_CHECK_WAIT_TIME(opt1);
                    _get_gORDER_CHECK_WAIT_TIME = _gPreferencesSave.getORDER_CHECK_WAIT_TIME();
                    //_glog.log("order check time change >>" + _get_gORDER_CHECK_WAIT_TIME);
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:OrderCheckWaitChange"
                            + ",VALUE:" + _get_gORDER_CHECK_WAIT_TIME
                            + ",UDPCODE:" + cmd);
                } else {

                }
                break;
            case 907: // 鍵異常警告を有効　保持しないワンタイム設定
                _glog.log("key alert use");
                _gKEY_ALERT_USE_FLAG = 1;
                break;

            case 908: // 鍵異常警告を無効　ワ保持しないワンタイム設定
                _glog.log("key alert off");
                _gKEY_ALERT_USE_FLAG = 0;
                break;
            case 910: // Localstrのダウンロード。　未使用
                _glog.log("file get>>910 LocalStrダウンロード:" + opt1);
                _gthreadGetFile.setGetLocalStr_Flag();
                _gVer_GetFile_LocalStr_Buf = opt1;


                break;
            case 911:// LocalNetaNameのダウンロード。　未使用
                _glog.log("file get>>911　LocalNetaNameダウンロード:" + opt1);
                _gthreadGetFile.setGetLocalNetaName_Flag();
                _gVer_GetFile_LocalNetaName_Buf = opt1;
                break;

            case 943: // _gSKIP_GAME_USE有効にするかの切り替え:有効
                _gPreferencesSave.saveSKIP_GAME_USE_ENABLE_FLAG(1);
                _gSKIP_GAME_USE_ENABLE_FLAG = 1;
                _gopt1_return = _gSKIP_GAME_USE;
                _gopt2_return = _gSKIP_GAME_USE_ENABLE_FLAG;
                _gTableMain_Flag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:SKIP_GAME_USE_ENABLEChange,SKIP_GAME_USE_ENABLE:ON"
                        + ",VALUE:" + _gSKIP_GAME_USE_ENABLE_FLAG + ",UDPCODE:" + cmd);
                break;
            case 944:// _gSKIP_GAME_USE有効にするかの切り替え:無効
                //_glog.log("_gSKIP_GAME_USE有効にするかの切り替え:無効");
                _gPreferencesSave.saveSKIP_GAME_USE_ENABLE_FLAG(0);
                _gSKIP_GAME_USE_ENABLE_FLAG = 0;
                _gopt1_return = _gSKIP_GAME_USE;
                _gopt2_return = _gSKIP_GAME_USE_ENABLE_FLAG;
                _gTableMain_Flag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:SKIP_GAME_USE_ENABLEChange,SKIP_GAME_USE_ENABLE:OFF"
                        + ",VALUE:" + _gSKIP_GAME_USE_ENABLE_FLAG + ",UDPCODE:" + cmd);
                break;
            case 945:
                //_glog.log("_gSKIP_GAME_USE OFF");
                _gSKIP_GAME_USE = 0;
                _gPreferencesSave.saveSKIP_GAME_USE(0);
                _gopt1_return = _gSKIP_GAME_USE;
                _gopt2_return = _gSKIP_GAME_USE_ENABLE_FLAG;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:GameSkipUseChange"
                        + ",VALUE:" + _gSKIP_GAME_USE_ENABLE_FLAG
                        + ",UDPCODE:" + cmd);
                break;
            case 946:
                //_glog.log("_gSKIP_GAME_USE ON");
                _gSKIP_GAME_USE = 1;
                _gPreferencesSave.saveSKIP_GAME_USE(1);
                _gopt1_return = _gSKIP_GAME_USE;
                _gopt2_return = _gSKIP_GAME_USE_ENABLE_FLAG;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:GameSkipUseChange"
                        + ",VALUE:" + _gSKIP_GAME_USE_ENABLE_FLAG
                        + ",UDPCODE:" + cmd);
                break;
            case 947://　使用言語数　MAX=6に制限　増やしたかったら変更
                //_glog.log("使用言語数" + opt1);
                if (1 <= opt1 && 6 >= opt1) {
                    _gPreferencesSave.saveLANG_MAX(opt1);
                    _gLANG_MAX = opt1;
                    _glocalStr.setLcalMax(_gLANG_MAX);
                    _gopt1_return = _gLANG_MAX;
                } else {
                    _glog.log("設定値は１〜６で設定してください。");
                }
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:UseLangMode,VALUE:"
                        + ",VALUE:" + _gIo_use_mode
                        + "LANGMAX:" + _gLANG_MAX
                        + ",UDPCODE:" + cmd);
                break;
            //--------------------------------------------
            case 948://　WIFI設定変更を有効
                //_glog.log("wifi change state(add ver75)>>948 wifi change使用する");
                _gWIFI_CHANGE_OFF = 0;
                _gPreferencesSave.saveWIFI_CHANGE_OFF(0);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:WiFIChangeUse"
                        + ",VALUE:" + _gWIFI_CHANGE_OFF
                        + ",UDPCODE:" + cmd);
                break;
            case 949://　WIFI設定変更を無効
                //_glog.log("wifi change state(add ver75)>>949 wifi change使用しない。");
                _gWIFI_CHANGE_OFF = 1;
                _gPreferencesSave.saveWIFI_CHANGE_OFF(1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:WiFIChangeUse,VALUE:"
                        + ",VALUE:" + _gWIFI_CHANGE_OFF
                        + ",UDPCODE:" + cmd);
                break;

            case 950:
                _gopt1_return = _gVer_GetFile_LocalStr;
                _gopt2_return = _gVer_GetFile_LocalNetaName;
                _gopt3_return = _gVer_GetFile_KaikeiCat;
                break;
            //for test 20170915 ver91
            case 951://シャリ選択ON
                _gPreferencesSave.saveUSE_SELECT_OPT_FLAG(1);
                _gUSE_SELECT_OPT_FLAG = _gPreferencesSave.getUSE_SELECT_OPT_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:OptSelUse,VALUE:"
                        + ",VALUE:" + _gUSE_SELECT_OPT_FLAG
                        + ",UDPCODE:" + cmd);
                break;
            case 952://シャリ選択OFF
                _gPreferencesSave.saveUSE_SELECT_OPT_FLAG(0);
                _gUSE_SELECT_OPT_FLAG = _gPreferencesSave.getUSE_SELECT_OPT_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:OptSelUse"
                        + ",VALUE:" + _gUSE_SELECT_OPT_FLAG
                        + ",UDPCODE:" + cmd);

                break;
            //add 20180415 for mutenkura
            case 968:
                //_glog.log("969 called:無添加くらmode on" + opt1);
                _gPreferencesSave.saveFLAG_MUTENKURA_MODE_ON(1);
                _FLAG_MUTENKURA_MODE_ON = _gPreferencesSave.get_FLAG_MUTENKURA_MODE_ON();
                _gopt1_return = _FLAG_MUTENKURA_MODE_ON;
                _gTableMain_Flag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:MutenKuraModeUse"
                        + ",VALUE:" + _FLAG_MUTENKURA_MODE_ON
                        + ",UDPCODE:" + cmd);
                break;
            case 969:
                //_glog.log("969 called:無添加くらmode off" + opt1);
                _gPreferencesSave.saveFLAG_MUTENKURA_MODE_ON(0);
                _FLAG_MUTENKURA_MODE_ON = _gPreferencesSave.get_FLAG_MUTENKURA_MODE_ON();
                _gopt1_return = _FLAG_MUTENKURA_MODE_ON;
                _gTableMain_Flag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:MutenKuraModeUse"
                        + ",VALUE:" + _FLAG_MUTENKURA_MODE_ON
                        + ",UDPCODE:" + cmd);
                break;
            //add 20180415
            //for test 20161029
            case 970:
                _glog.log("970 called" + opt1);
                veiwScreenBrightness(opt1);
                //_gopt1_return=_gVer_GetFile_LocalStr;
                break;

            //ver77 品切れ表記時に品切れをオーバレイする設定を有効にする。
            case 971:
                _glog.log("971 called 品切れ表記有効");
                _gSINAGIRE_DISP_ON = 1;
                _gPreferencesSave.saveSINAGIRE_DISP_ON(1);
                _gTableDraw_Flag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SinagireDispUse"
                        + ",VALUE:" + _gSINAGIRE_DISP_ON
                        + ",UDPCODE:" + cmd);
                break;
            case 972:
                //_glog.log("971 called 品切れ表記無効");
                _gSINAGIRE_DISP_ON = 0;
                _gPreferencesSave.saveSINAGIRE_DISP_ON(0);
                _gTableDraw_Flag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SinagireDispUse"
                        + ",VALUE:" + _gSINAGIRE_DISP_ON
                        + ",UDPCODE:" + cmd);
                break;

            case 973:
                //_glog.log("973 called ヘルプ画面表示");
                if (_gDrawHELPFlag == F_VIEW_STATE_CLEAR) {
                    _gDrawHELPFlag = F_VIEW_STATE_OPEN_CMD;
                }
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:HelpOn,UDPCODE:" + cmd);
                break;
            case 974:
                //_glog.log("973 called ヘルプ画面非表示");
                if (_gDrawHELPFlag != F_VIEW_STATE_CLEAR) {
                    _gDrawHELPFlag = F_VIEW_STATE_CLOSE_CMD;
                }
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:HelpOff,UDPCODE:" + cmd);
                break;
            case 975:
                //_glog.log("975：案内時flash画面使用");
                _gUSE_FLASHING_FLAG = 1;
                _gPreferencesSave.saveFLASHING_USE_FLAG(1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:AnnaiFlashUse,VALUE:" + _gUSE_FLASHING_FLAG + ",UDPCODE:" + cmd);
                break;
            case 976:
                //_glog.log("975：案内時flash画面不使用");
                _gUSE_FLASHING_FLAG = 0;
                _gPreferencesSave.saveFLASHING_USE_FLAG(1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:AnnaiFlashUse,VALUE:" + _gUSE_FLASHING_FLAG + ",UDPCODE:" + cmd);
                break;

            //大ネタ商品対応
            case 977:
                _glog.log("977 大ネタ商品使用");
                _gUSE_BIGNETA_USE_FLAG = 1;
                _gPreferencesSave.saveBIGNETA_USE(1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:BigNetaUse,VALUE:" + _gUSE_BIGNETA_USE_FLAG + ",UDPCODE:" + cmd);
                _gTableDraw_Flag = 1;
                break;
            case 978:
                _glog.log("978 大ネタ商品不使用");
                _gUSE_BIGNETA_USE_FLAG = 0;
                //_gPreferencesSave.saveBIGNETA_USE(1);
                _gPreferencesSave.saveBIGNETA_USE(0);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:BigNetaUse,VALUE:" + _gUSE_BIGNETA_USE_FLAG + ",UDPCODE:" + cmd);
                _gTableDraw_Flag = 1;
                break;
            case 979:
                if (opt1 > 0 && opt1 <= 20) {
                    _glog.log("979 大ネタ ON PAGE:" + opt1);
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:BigUsePageON,VALUE:" + opt1 + ",UDPCODE:" + cmd);
                    _gPreferencesSave.saveUSE_BIGNETA_PAGE_FLAGS(1, opt1);
                    _gUSE_BIGNETA_PAGE_FLAGS[opt1 - 1] = 1;
                    _gTableDraw_Flag = 1;
                }
                break;
            case 980:
                if (opt1 > 0 && opt1 <= 20) {
                    _glog.log("979 大ネタ OFF PAGE:" + opt1);
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:BigUsePageOFF,VALUE:" + opt1 + ",UDPCODE:" + cmd);
                    _gPreferencesSave.saveUSE_BIGNETA_PAGE_FLAGS(0, opt1);
                    _gUSE_BIGNETA_PAGE_FLAGS[opt1 - 1] = 0;
                    _gTableDraw_Flag = 1;
                }
                break;

            //20200708　QRコード対応
            case 981:
                _gQRCODE_DISP_FLAG = 1;
                _gPreferencesSave.saveQRCODE_DISP_FLAG(1);
                findViewById(R.id.bt_main11).setVisibility(View.VISIBLE);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:QrCodeUse,VALUE:" + _gQRCODE_DISP_FLAG + ",UDPCODE:" + cmd);
                break;
            case 982:
                _gQRCODE_DISP_FLAG = 0;
                _gPreferencesSave.saveQRCODE_DISP_FLAG(0);
                findViewById(R.id.bt_main11).setVisibility(View.INVISIBLE);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:QrCodeUse,VALUE:" + _gQRCODE_DISP_FLAG + ",UDPCODE:" + cmd);
                break;
            //20171117 大ネタ商品対応
            /*case 980://
                _glog.log("-memInfo-");
                sysInfoMemoryUse();
                break;*/
            /*case 984://
                //_glog.log("-clear log-");
                _gthredadbexe.setFLAG_CLEAR_CACHE_ON(1);
                break;*/
            case 986: //add 20161115
                //_glog.log("-set img-");
                _gCurrentPage = 1;
                _gTableDraw_Flag = 1;
                break;
            /*case 987://
               _glog.log("---clear img---");
              _gNetaInfo.ClearNetaImg();
              break;*/
            case 998://
                _glog.log("restart");
                restart(20);
                break;
            /*case 999://
                _glog.log("reboot");
                //restart(5);
                // this.Reboot();
                // _gFORCERBOOT=1;
                break;*/
            case 123://
                _goptInfo.setToppingInfo("/upload/toppinginfo.csv");
                //for test
                break;
            case 124://
                //for test
                _gNetaInfo.setPageSpInfo();
                //for test
                break;
            case 125://
                _glog.log("sound test");
                _gSoundManager.PlaySound(opt1, 1);//音声要確認
                break;
            //---------------------------------
            //20180330 ver 300 拡張コマンド
            //---------------------------------
            case 301://
                _glog.log("301: ret getCURRENT_chuumonseigen()");
                if (_gSettingInfoFromServer != null) {
                    if (_gthreadSendCmd != null) {
                        _gthreadSendCmd.setFlagReqSetting();
                        _gthreadSendCmd.setFlagCheackOn();//送信確認
                    }
                    this.checkOrderCatStat();
                    _gopt1_return = _gSettingInfoFromServer.get_chuumonseigen(_gopt1);
                } else {
                    _gopt1_return = -1;
                }
                break;
            case 302://
                if (_gSettingInfoFromServer != null) {
                    _glog.log("302:gSet chuumon seigenn:" + _gSettingInfoFromServer.getCURRENT_chuumonseigen());
                    _glog.log("0:" + _gSettingInfoFromServer.get_chuumonseigen(0));
                    _glog.log("1:" + _gSettingInfoFromServer.get_chuumonseigen(1));
                    _glog.log("2:" + _gSettingInfoFromServer.get_chuumonseigen(2));
                    _glog.log("3:" + _gSettingInfoFromServer.get_chuumonseigen(3));
                    this.checkOrderCatStat();
                    _gopt1_return = _gSettingInfoFromServer.getCURRENT_chuumonseigen();
                    _gopt2_return = _gSettingInfoFromServer.get_chuumonseigen(0) * 1000 + _gSettingInfoFromServer.get_chuumonseigen(1) * 100 + _gSettingInfoFromServer.get_chuumonseigen(2) * 10 + _gSettingInfoFromServer.get_chuumonseigen(3);
                    // _gopt3_return = _gSettingInfoFromServer.get_chuumonseigen(2)*100+ _gSettingInfoFromServer.get_chuumonseigen(3);
                } else {
                    _gopt1_return = -1;
                }
                break;
            //---------------------------------
            //20180330 ver 131~ 外部操作用コマンド
            //---------------------------------
            case 400://
                _glog.log("401:order_page_on opt1:neta_code opt2:sara");
                _glog.log("402:order_on");
                _glog.log("403:order_on opt1:sara");
                _glog.log("404:order_close");
                _glog.log("405:pre page on");
                _glog.log("406:next page on");
                _glog.log("407:rireki page on");
                _glog.log("408:rireki page close");
                _glog.log("409:yobidasi page open");
                _glog.log("410:yobidasi page close");
                _glog.log("480:qrCreate ON");
                _glog.log("481:qrCreate OFF");
                _glog.log("482:sinagire recommend ON");
                _glog.log("483:sinagire recommend OFF");
                _glog.log("484:order devide ON");
                _glog.log("485:order devide  OFF");
                _glog.log("486:LAYOUT PORTRAIT ON");
                _glog.log("487:LAYOUT PORTRAIT   OFF");
                break;
            case 398://
                _glog.log("398:kaikei page open");
                _gopt1_return = operate_kaikei_open();
                break;
            case 399://
                _glog.log("399:kaikei page close");
                _gopt1_return = operate_kaikei_close();
                break;
            case 401://
                _glog.log("401:order_page_on opt1:neta_code opt2:sara");
                _gopt1_return = operate_order_page(opt1, opt2);
                operate_order_set_sara(opt2);
                _gopt2_return = opt1;
                _gopt3_return = opt2;
                break;
            case 402://
                _glog.log("402:order_on");
                _gopt1_return = operate_order_on();
                break;
            case 403://
                _glog.log("403:order_set_sara opt1:sara");
                _gopt1_return = operate_order_set_sara(opt1);
                break;
            case 404://
                _glog.log("404:order_close");
                operate_order_close();
                break;
            case 405://
                _glog.log("405:pre page on");
                operate_pre_page();
                _gopt1_return = _gCurrentPage;
                break;
            case 406://
                _glog.log("406:next page on");
                operate_next_page();
                _gopt1_return = _gCurrentPage;
                break;
            case 407://
                _glog.log("407:rireki page on");
                _gopt1_return = operate_rireki_open();
                break;
            case 408://
                _glog.log("408:rireki page close");
                _gopt1_return = operate_rireki_close();
                break;
            case 409://
                _glog.log("409:yobidasi page open");
                _gopt1_return = operate_yobidasi_open();
                break;
            case 410://
                _glog.log("410:yobidasi page close");
                _gopt1_return = operate_yobidasi_close();
                break;
            case 411://
                _glog.log("411:sousaAnnai page open");
                _gopt1_return = operate_sousaAnnai_open();
                break;
            case 412://
                _glog.log("412:sousaAnnai page close");
                _gopt1_return = operate_sousaAnnai_close();
                break;
            case 413://
                _glog.log("413:SousaAnnaiCat:" + _gSousaAnnaiCat);
                _gPreferencesSave.saveSOUSA_ANNAI_CAT(1);
                _gSousaAnnaiCat = _gPreferencesSave.getSOUSA_ANNAI_CAT();
                _gopt1_return = _gSousaAnnaiCat;
                break;
            case 414://
                _glog.log("414:SousaAnnaiCat:" + _gSousaAnnaiCat);
                _gPreferencesSave.saveSOUSA_ANNAI_CAT(2);
                _gSousaAnnaiCat = _gPreferencesSave.getSOUSA_ANNAI_CAT();
                _gopt1_return = _gSousaAnnaiCat;
                break;
            case 415://
                _glog.log("415:SousaAnnaiCat:" + _gSousaAnnaiCat);
                _gPreferencesSave.saveSOUSA_ANNAI_CAT(3);
                _gSousaAnnaiCat = _gPreferencesSave.getSOUSA_ANNAI_CAT();
                _gopt1_return = _gSousaAnnaiCat;
                break;

            //  TODO add 20211028 FOR TEST リリース時コメントアウトする事
            case 416:
                _gNetaInfo.testPrintPageItem(opt1);
                break;

                // TODO　20220108 ver 406 のマージ
            case 431: // スマホ会計開始
                // _glog.log("スマホ会計割り込み");
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MSG:スマホ会計表示コマンド,TAG:SMARTPHONE_CHECK,VALUE:" + opt1 + ",UDPCODE:" + cmd);
                if (_gUSE_SMARTPHONE_CHECK_FLAG == 0) {
                    _glog.log("スマホ会計フラグOFFの為、スマホ会計表示を中断");
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MSG:スマホ会計使用OFFのため中断,TAG:SMARTPHONE_CHECK");
                    break;
                }
                if (_gUSE_AUTO_CHECK_FLAG == 1) {
                    //_gDrawSpCheckFlag = F_VIEW_STATE_OPEN_CMD;
                    _gDrawKaikeiFlag = F_VIEW_STATE_OPEN_CMD;
                    //Ver403
                    if ( _gHttpSpCheckVer  == DOWNLOAD_FALED_DIRCTORY_NOTHING) {
                        //sp表示コンテンツが/data/data/com.kura.andorder/files/に存在しない場合
                        //スマホ側へ呼び出しを送信し、呼出し画面を表示
                        spCheckStatus = F_SPCHECK_STATUS_CALLING;
                        try {
                            JSONObject responseJson = doSpCheckCallApi();
                            if (responseJson == null || responseJson.getInt("resultCd") == AppEnv.RESULT_FAILED) {
                                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MSG:呼出状態の連携に失敗しました,TAG:SMARTPHONE_CHECK");
                                throw new Exception("呼出状態の連携に失敗しました");
                            }
                        } catch (Exception e) {
                            // Log.d("SMARTPHONE_SELF_CHECK", e.toString());
                            _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MSG:呼出状態の連携に失敗しました,TAG:SMARTPHONE_CHECK");
                        }
                    } else {
                        //sp表示コンテンツが/data/data/com.kura.andorder/files/に存在する場合は
                        //スマホ会計中画面を表示
                        spCheckStatus = F_SPCHECK_STATUS_CHECKING;
                        _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MSG:スマホ会計中画面表示,TAG:SMARTPHONE_CHECK");
                    }
                } else {
                    // _glog.log("ネイティブ会計画面表示");
                    //_gDrawSpCheckFlag = F_VIEW_STATE_CLOSE_CMD;
                    _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MSG:ネイティブ会計画面表示,TAG:SMARTPHONE_CHECK");
                    _gDrawKaikeiFlag = F_VIEW_STATE_OPEN_CMD;
                }
                break;
            // TODO　20220108 ver 406 のマージ
            case 432://スマホ会計中キャンセルコマンド
                Log.d("スマホ会計中", "キャンセルコマンド");
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,MSG:スマホ会計キャンセル,TAG:SMARTPHONE_CHECK");
                if (_gviewSpCheck != null) {
                    //スマホ会計中画面削除
                    //cancelSpCheck();
                    //_gDrawKaikeiFlag = F_VIEW_STATE_CLOSE_CMD;
                    removeViewCancel();
                } else {
                    _gKAIKEI_For_SaraButton_Count = 0;
                    _gDrawKaikeiFlag = F_VIEW_STATE_CLOSE_CMD;
                    Button bt = (Button) findViewById(R.id.KAIKEI_BACK);
                    if (bt != null) {
                        bt.setEnabled(false);
                    }
                    spCheckStatus = F_SPCHECK_STATUS_NOT_CHECKING;
                }
                break;
            // TODO　20220108 ver 406 のマージ
            case 433://スマホ会計中呼び出しコマンド
               //  Log.d("スマホ会計中", "呼出コマンド");
                //if(_gDrawSpCheckFlag == F_VIEW_STATE_CLEAR) {
                _gDrawKaikeiFlag = F_VIEW_STATE_OPEN_CMD;
                spCheckStatus = F_SPCHECK_STATUS_CALLING;
                //}
                break;
            // TODO　20220108 ver 406 のマージ

                /*
            case 480: // http test
                _glog.log("480:http post test");
                _ghttpRequestHandle.setPostRequest("192.168.20.150", 5182, "/kura/api/:version/setting/","{\"number\":\"3\"}");
                Thread th = new Thread(_ghttpRequestHandle);
                th.start();
                break;
                :/
                 */
            case 490:
                _glog.log("490:PUT LogJson not delete");
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:UploadAndOrderlog,PROC:NotDelete" + ",UDPCODE:" + cmd);
                if (_gthreadPutLogJson == null) {
                    _gthreadPutLogJson = new threadPutLogJson(_gTtableNum, getApplicationContext().getFilesDir(), _glog);
                }
                //_gthreadPutLogJson.setCustmorId(_glog.get_CusmorId());
                _gthreadPutLogJson.setDelSendFileFlag(0);
                _gTHREAD_PUTLOGJSHON = new Thread(_gthreadPutLogJson);
                _gTHREAD_PUTLOGJSHON.start();
                break;
            case 491://
                // _glog.log("491:PUT LogJson delete");
                if (_gthreadPutLogJson == null) {
                    _gthreadPutLogJson = new threadPutLogJson(_gTtableNum, getApplicationContext().getFilesDir(), _glog);
                }
                // _gthreadPutLogJson.setCustmorId(_glog.get_CusmorId());
                _gthreadPutLogJson.setDelSendFileFlag(1);
                _gTHREAD_PUTLOGJSHON = new Thread(_gthreadPutLogJson);
                _gTHREAD_PUTLOGJSHON.start();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:UploadAndOrderlog,PROC:Delete,UDPCODE:" + cmd);
                break;

            case 498:
                _gPreferencesSave.saveUSE_AUTO_KAIKEI_FLAG(1);
                _gPreferencesSave.saveUSE_AUTO_CHECK_FLAG(0);
                _gUSE_AUTO_KAIKEI_FLAG = 1;
                _gUSE_AUTO_CHECK_FLAG = 0;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_AUTO_KAIKEI_FLAG,PROC:ON,UDPCODE:" + cmd);
                break;
            case 499:
                _gPreferencesSave.saveUSE_AUTO_KAIKEI_FLAG(0);
                _gUSE_AUTO_KAIKEI_FLAG = 0;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_AUTO_KAIKEI_FLAG,PROC:OFF,UDPCODE:" + cmd);
                break;


            case 500://_gUSE_LAYOUT_PORTRAIT_ON_FLAG OFF
                SetTime(opt1, opt2, opt3);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SetTime,VALUE:" + opt1 + "_" + opt2 + "_" + opt3 + ",UDPCODE:" + cmd);
                break;
            case 501://_gUSE_LAYOUT_PORTRAIT_ON_FLAG OFF
                // _glog.log("501:MISE_CODE called:");
                _gPreferencesSave.saveMISECODE(opt1);
                _gMiseCode = _gPreferencesSave.getMISECODE();
                _gopt1_return = opt1;
                _glog.setMiseCode(_gMiseCode);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SetMiseCode,VALUE:" + _gMiseCode + ",UDPCODE:" + cmd);
                break;

            case 502:
                _gPreferencesSave.saveUSE_GAME_END_CNT_SAVE(1);
                _gUSE_GAME_END_SAVE = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_GAME_END_SAVE,PROC:ON,UDPCODE:" + cmd);
                break;
            case 503:
                _gPreferencesSave.saveUSE_GAME_END_CNT_SAVE(0);
                _gUSE_GAME_END_SAVE = 0;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_GAME_END_SAVE,PROC:OFF,UDPCODE:" + cmd);
                break;
           /* case 504:
                _gSoundManager.PlaySound(1, 1);
                break;
            case 505:
                _gSoundManager.PlaySound(3, 1);
                break;*/
            case 506://win test
                FLAG_GAME_TEST_ON = 2;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:PLAY_GAME,PROC:WIN,MOV_NUM:" + 0 + ",UDPCODE:" + cmd);
                break;
            case 507://lose test
                FLAG_GAME_TEST_ON = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:PLAY_GAME,PROC:LOOSE,MOV_NUM:" + 0 + ",UDPCODE:" + cmd);
                break;
            case 508://win test  番号指定
                if (1 <= opt1 && 20 >= opt1) {
                    _gMoveInfo.set_WinDougaPlayCount(opt1);
                    _gopt1_return = opt1;
                }
                FLAG_GAME_TEST_ON = 2;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:PLAY_GAME,PROC:WIN,MOV_NUM:" + 0 + ",UDPCODE:" + cmd);
                break;
            case 509://lose test　番号指定
                if (1 <= opt1 && 20 >= opt1) {
                    _gMoveInfo.set_LooseDougaPlayCount(opt1);
                    _gopt1_return = opt1;
                }
                FLAG_GAME_TEST_ON = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:PLAY_GAME,PROC:LOOSE,MOV_NUM:" + 0 + ",UDPCODE:" + cmd);
                break;

            case 510://gSmartGame WIN
                //poolがMAXを超えるばあいは処理しない
                if (_gSmartGameCount >= MAX_SMART_MOV_POOL - 1) {
                    break;
                }
                if (0 <= opt1 && 20 > opt1) {
                    _gSmartGameMov[_gSmartGameCount] = opt1;
                }
                if (opt2 == 1) {
                    _gSmartGameType[_gSmartGameCount] = 2;//あたり
                } else {
                    _gSmartGameType[_gSmartGameCount] = 1;//外れ
                }
                _gSmartGameCount++;
                _gopt1_return = opt1;
                _gopt2_return = opt2;
                _gopt3_return = _gSmartGameCount;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SET_martGameCount,PROC:" + opt2 + ",MOV_NUM:" + opt1 + ",UDPCODE:" + cmd);
                break;

            case 511://add 20191111 注文制限数の強制上書き
                _gopt1_return = opt1;
                _gSettingInfoFromServer.FoceSet_chuumonseigen(opt1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SET_FoceSetChuumonSeigen,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;

            case 512://add 20200120 特殊gameMode ON
                _gPreferencesSave.saveUSE_GAME_WITH_ARRAY_FLAG(1);
                _gUSE_GAME_WITH_ARRAY_FLAG = 1;
                _gopt1_return = _gPreferencesSave.getUSE_GAME_WITH_ARRAY_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SET_GameWithArrayON,opt1:" + opt1 + ",UDPCODE:" + cmd);
                break;

            case 513://add 20200120 特殊gameMode OFF
                _gPreferencesSave.saveUSE_GAME_WITH_ARRAY_FLAG(0);
                _gUSE_GAME_WITH_ARRAY_FLAG = 0;
                _gopt1_return = _gPreferencesSave.getUSE_GAME_WITH_ARRAY_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SET_GameWithArrayOFF,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;

            case 514://add 20200120 特殊gameMode 取得
                _gopt1_return = _gPreferencesSave.getUSE_GAME_WITH_ARRAY_FLAG();
                _gSettingInfo.setGameWinArray(getApplicationContext().getFilesDir());
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:GET_GameWithArrayOFF,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;


            case 515://add 20200202 バッテリー監視 ON
                _gPreferencesSave.saveUSE_BATTEYR_CHECK_FLAG(1);
                _gUSE_BATTEYR_CHECK_FLAG = 1;
                _gopt1_return = _gPreferencesSave.getUSE_BATTEYR_CHECK_FLAG();
                this.checkBatteryState();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SET_USE_BATTEYR_CHECK_FLAG_ON,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            case 516://add 20200202 バッテリー監視 OFF
                _gPreferencesSave.saveUSE_BATTEYR_CHECK_FLAG(0);
                _gUSE_BATTEYR_CHECK_FLAG = 0;
                _gopt1_return = _gPreferencesSave.getUSE_BATTEYR_CHECK_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SET_USE_BATTEYR_CHECK_FLAG_OFF,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            case 517://add 20200202 バッテリー監視 取得
                _gopt1_return = _gPreferencesSave.getUSE_BATTEYR_CHECK_FLAG();
                _gUSE_BATTEYR_CHECK_FLAG = _gPreferencesSave.getUSE_BATTEYR_CHECK_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:GET_USE_BATTEYR_CHECK_FLAG_OFF,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            case 518://add 20200202 バッテリー監視 取得
                _glog.log("バッテリー監視 OPEN:");
                _gDrawLowBatteryAlertFlag = F_VIEW_STATE_OPEN_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:LowBatteryAlert OPEN,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            case 519://add 20200202 バッテリー監視 取得
                _glog.log("バッテリー監視 CLOSE");
                _gDrawLowBatteryAlertFlag = F_VIEW_STATE_CLOSE_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:LowBatteryAlert CLOSE,VAL:" + opt1 + ",UDPCODE:" + cmd);

                // TODO DELETE 20211018 first free order機能の削除
                // 202020200515 first free order
                /*
            case 520://add 20200515 first free order USE ON
                _gPreferencesSave.saveUSE_FIRST_FREE_ORDER_FLAG(1);
                _gUSE_FirstFreeOrder_FLAG = 1;
                _gopt1_return = _gPreferencesSave.getUSE_FIRST_FREE_ORDER_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SET_USE_FirstFreeOrder_FLAG_ON,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            case 521://add 20200515 first free order USE OFF
                _gPreferencesSave.saveUSE_FIRST_FREE_ORDER_FLAG(0);
                _gUSE_FirstFreeOrder_FLAG = 0;
                _gopt1_return = _gPreferencesSave.getUSE_FIRST_FREE_ORDER_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SET_USE_FirstFreeOrder_FLAG_OFF,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            case 522://add 20200515 first free order item code
                _gPreferencesSave.saveFIRST_FREE_ORDER_CODE(opt1);
                _gFirstFreeOrder_itemCode = opt1;
                _gopt1_return = _gPreferencesSave.getFIRST_FREE_ORDER_CODE();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SET_FirstFreeOrder_itemCode,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            case 523://add 20200515 first free order item code
                _gDrawFirstFreeOrderFlag = F_VIEW_STATE_OPEN_CMD;
                break;
            case 524://add 20200515 first free order item code
                _gDrawFirstFreeOrderFlag = F_VIEW_STATE_CLOSE_CMD;
                break;
                 */
                //
            case 525:// アラート警告　ON
                _gDrawOrderArriveAlertFlag = F_VIEW_STATE_OPEN_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:OrderArriveAlert_Open,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            case 526:// アラート警告　OFF
                _gDrawOrderArriveAlertFlag = F_VIEW_STATE_CLOSE_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:rderArriveAlert_Close,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;

            // TODO DELETE 20211018 first free order機能の削除

            // opning movie fucntion
            case 527://add 20200608 opning movie fucntion
                _gDrawOpenningMoveFlag = F_VIEW_STATE_OPEN_CMD;
                break;
            case 528://add 20200608  opning movie fucntion
                _gDrawOpenningMoveFlag = F_VIEW_STATE_CLOSE_CMD;
                break;
            case 529://add 20200608  opning movie fucntion
                _gPreferencesSave.saveUSE_OPENNINGMOVIE_FLAG(1);
                _gUSE_OpenningMove_FLAG = 1;
                _gopt1_return = _gPreferencesSave.getUSE_OPENNINGMOVIE_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_OpenningMove_FLAG_ON,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            case 530://add 20200608  opning movie fucntion
                _gPreferencesSave.saveUSE_OPENNINGMOVIE_FLAG(0);
                _gUSE_OpenningMove_FLAG = 0;
                _gopt1_return = _gPreferencesSave.getUSE_OPENNINGMOVIE_FLAG();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_OpenningMove_FLAG_OFF,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            case 531://add 20200608  opning movie fucntion
                _gPreferencesSave.saveOPENNINGMOVIE_NUMVER(opt1);
                _gOpenningMoveNumber = _gPreferencesSave.getOPENNINGMOVIE_NUMVER();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SetOpenningMoveNumber,VAL:" + opt1 + ",UDPCODE:" + cmd);
                break;
            // TODO DELETE 20211018 first free order機能の削除
            /*
            case 532://add 20200623 first free order item code
                _gopt1_return = _gPreferencesSave.getFIRST_FREE_ORDER_CODE();
                break;
            */
            // TODO DELETE 20211018 first free order機能の削除

            case 533://add 20200718
                _gPreferencesSave.saveTOUCH_TIME_THRESHOLD_CNT(opt1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SetTOUCH_TIME_THRESHOLD_CNT,VAL:" + opt1 + ",UDPCODE:" + cmd);
                gTOUCH_TIME_THRESHOLD_CNT = _gPreferencesSave.getTOUCH_TIME_THRESHOLD_CNT();
                _gopt1_return = gTOUCH_TIME_THRESHOLD_CNT;

                TOUCH_TIME_THRESHOLD = gTOUCH_TIME_THRESHOLD_CNT;
                TOUCH_TIME_THRESHOLD_IMG = gTOUCH_TIME_THRESHOLD_CNT;
                TOUCH_TIME_THRESHOLD_CALL = gTOUCH_TIME_THRESHOLD_CNT;
                TOUCH_TIME_THRESHOLD_KAIKEI = gTOUCH_TIME_THRESHOLD_CNT;
                TOUCH_TIME_THRESHOLD_RIREKI = gTOUCH_TIME_THRESHOLD_CNT;
                break;

            case 534: // add 20200822 ver320 IGNORE_BOTTON_ON_COUNT_VAL
                _gPreferencesSave.saveIGNORE_BOTTON_ON_COUNT_VAL(opt1);
                _gopt1_return = _gPreferencesSave.getIGNORE_BOTTON_ON_COUNT_VAL();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SetIGNORE_BOTTON_ON_COUNT_VAL,VAL:" + opt1 + ",UDPCODE:" + cmd);
                // _gNETAIMG_BOTTON_ON_COUNT = _gPreferencesSave.getIGNORE_BOTTON_ON_COUNT_VAL();
                break;

            case 600:
                /**
                 * アンケート画面を有効する
                 */
                Log.d("debug", "SURVEY_ON");
                _gPreferencesSave.saveUSE_SURVEY_FLAG(1);
                _gUSE_SURVEY_FLAG = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_SURVEY_FLAG,PROC:ON,UDPCODE:" + cmd);
                break;
            case 601:
                /**
                 * アンケート画面を無効する
                 */
                Log.d("debug", "SURVEY_OFF");
                _gPreferencesSave.saveUSE_SURVEY_FLAG(0);
                _gUSE_SURVEY_FLAG = 0;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_SURVEY_FLAG,PROC:OFF,UDPCODE:" + cmd);
                break;
            case 602:
                /**
                 * 自動会計画面を有効する
                 */
                Log.d("debug", "AUTO_CHECK_ON");
                _gPreferencesSave.saveUSE_AUTO_KAIKEI_FLAG(0);
                _gPreferencesSave.saveUSE_AUTO_CHECK_FLAG(1);
                _gUSE_AUTO_KAIKEI_FLAG = 0;
                _gUSE_AUTO_CHECK_FLAG = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_AUTO_CHECK_FLAG,PROC:ON,UDPCODE:" + cmd);
                break;
            case 603:
                /**
                 * 自動会計画面を無効する
                 */
                Log.d("debug", "AUTO_CHECK_OFF");
                _gPreferencesSave.saveUSE_AUTO_CHECK_FLAG(0);
                _gUSE_AUTO_CHECK_FLAG = 0;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_AUTO_CHECK_FLAG,PROC:OFF,UDPCODE:" + cmd);
                break;
            case 605: // add 20201226 ver333 ~ 一括データー更新
                _glog.log("一括データー更新:" + opt1);
                String ftpdata = "";
                ftpdata = "0," + opt1;
                _gthreadFtpClient.SetFtpFlag(ftpdata);
                ftpdata = "1," + opt1;
                _gthreadFtpClient.SetFtpFlag(ftpdata);
                ftpdata = "2," + opt1;
                _gthreadFtpClient.SetFtpFlag(ftpdata);
                _gopt1_return = opt1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:UPDATE_ALL_FTP,VER:" + opt1);
                break;
            // TODO marge ２皿対応
            case 606: // add 2021 0625 ver 341 200円皿注文時警告機能　有効
                _gPreferencesSave.saveUSE_DOUBLE_PLATE_ALERT(1);
                _gDoublePlateAlret_USE = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_DOUBLE_PLATE_ALERT_ON");
                break;
            case 607: // add 2021 0625 ver 341 200円皿注文時警告機能　無効
                _gPreferencesSave.saveUSE_DOUBLE_PLATE_ALERT(0);
                _gDoublePlateAlret_USE = 0;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_DOUBLE_PLATE_ALERT_OFF");
                break;
            // TODO marge ２皿対応
            // TODO marge ２皿 firstOrder
            case 608: // add 2021 0627  ２００円注文警告を初回だけにする場合
                _gPreferencesSave.saveUSE_DOUBLE_PLATE_FIRST(1);
                _gDoublePlateFist_USE = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_DOUBLE_PLATE_FIRST_ON");
                break;
            case 609: // add 2021 0627   ２００円注文警告を毎回表示する場合
                _gPreferencesSave.saveUSE_DOUBLE_PLATE_FIRST(0);
                _gDoublePlateFist_USE = 0;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_DOUBLE_PLATE_FIRST_OFF");
                break;
            // TODO marge ２皿 firstOrder

            // TODO　add 20210817 原宿店屋台対応 　
            case 610: //
                _gPreferencesSave.saveUSE_FOOD_STAND(1);
                _gUse_FoodStand = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_FOOD_STAND_ON");
                break;
            case 611: // add 2021 0627  ２００円注文警告を初回だけにする場合
                _gPreferencesSave.saveUSE_FOOD_STAND(0);
                _gUse_FoodStand = 0;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:CMD:USE_FOOD_STAND_OFF");
                break;
            // TODO　add 20210817 原宿店屋台対応 　

            // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加
            case 612: // 有効切り替え
                //_gPreferencesSave.saveQRCODE_DISP_FLAG(1);
                //_FLAG_CREATEQRCODE_USE_ON  = 1;
                // _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:CREATEQRCODE_USE_ON");
                _gPreferencesSave.saveUSE_LINKQRCODE(1);
                _FLAG_LINKQRCODE_USE_ON = 1;
                _gTableDraw_Flag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:FLAG_LINKQRCODE_USE_ON,Msg:チケット管理システム機能有効,Tag:DxGame");
                break;
            case 613: // 無効に切り替え
                //_gPreferencesSave.saveQRCODE_DISP_FLAG(0);
               // _FLAG_CREATEQRCODE_USE_ON  = 0;
                // _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:CMD:CREATEQRCODE_USE_OFF");
                _gPreferencesSave.saveUSE_LINKQRCODE(0);
                _FLAG_LINKQRCODE_USE_ON = 0;
                _gTableDraw_Flag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:FLAG_LINKQRCODE_USE_OFF,Msg:チケット管理システム機能無効,Tag:DxGame");
                break;
            case 614: // メイン画面にQRボタン表示（　リセットしたらクリアされる　）
                _gLinkQRMainViewButtonOnFlag = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:bt_main_linkqrVISIBLE");
                break;
            case 615: // QRコードをサーバーからFTPで取得 押上店チケット管理システム要
                _gthreadFtpClient.SetQrFtpFlag();
                Thread th = new Thread(_gthreadFtpClient);
                th.start();
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:SetQrFtpFlagOn,Msg:Qrコード取得命令,Tag:DxGame");
                break;
            case 616: // QR　View OPEN
                _gDrawLinkQRCodeFlag = F_VIEW_STATE_OPEN_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:_gDrawLinkQRCodeFlagOpen");
                break;
            case 617: // QR　View CLOSE
                _gDrawLinkQRCodeFlag = F_VIEW_STATE_CLOSE_CMD;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:_gDrawLinkQRCodeFlagClose");
                break;
            // TODO add 20210913 get QRINFO　  view_linkqr.xmlを追加

            // TODO add 20211018 APP SERVERのエミュレート対応
            case 618: // APP SERVERのADDR設定
                if(opt1 == 1){
                    _gPreferencesSave.saveAPP_SERVER_ADDR("192.168.11.1");
                    _gPreferencesSave.saveAPP_SERVER_PORT(10000);
                    setup();
                }else if(opt1 == 2){
                    // _gPreferencesSave.saveAPP_SERVER_ADDR("192.168.11.1");
                    // _gPreferencesSave.saveAPP_SERVER_PORT(12345);
                }else{
                    _gPreferencesSave.saveAPP_SERVER_ADDR("192.168.11.3");
                    _gPreferencesSave.saveAPP_SERVER_PORT(10000);
                    setup();
                }
                _glog.LogJson(getApplicationContext(),
                        "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:setAppServer,ServerAddr:"
                                + _gPreferencesSave.getAPP_SERVER_ADDR() + ",ServerPort:" + _gPreferencesSave.getAPP_SERVER_PORT());
                break;
            // TODO add 20211018 APP SERVERのエミュレート対応

            // TODO　20220108 ver 406 のマージ ver 406の606は使用中のため619.620に変更している
            case 619:
                /**
                 * スマホ会計を有効にする
                 */
               //  Log.d("debug", "SMARTPHONE_CHECK_ON");
                _gPreferencesSave.saveUSE_SMARTPHONE_CHECK_FLAG(1);
                _gUSE_SMARTPHONE_CHECK_FLAG = 1;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_SMARTPHONE_CHECK_FLAG,PROC:ON,UDPCODE:" + cmd);
                break;
            case 620:
                /**
                 * スマホ会計を無効にする
                 */
                // Log.d("debug", "SMARTPHONE_CHECK_OFF");
                _gPreferencesSave.saveUSE_SMARTPHONE_CHECK_FLAG(0);
                _gUSE_SMARTPHONE_CHECK_FLAG = 0;
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_SMARTPHONE_CHECK_FLAG,PROC:OFF,UDPCODE:" + cmd);
                break;
            // TODO　20220108 ver 406 のマージ ver 406の606は使用中のため619.620に変更している

            // TODO　20220207 押上店向け、１F、２Fでコンテンツを分ける処理
            case 621: // ビックらぎょ　有効
                _gUSE_BIKKURAGYO_FLAG = 1;
                _gPreferencesSave.saveUSE_BIKKURAGYO_FLAG(1);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_BIKKURAGYO_FLAG,PROC:ON,Msg:ビックらぎょ有効,Tag:DxGame,UDPCODE:" + cmd);
                break;
            case 622: // ビックらぎょ　無効
                _gUSE_BIKKURAGYO_FLAG = 0;
                _gPreferencesSave.saveUSE_BIKKURAGYO_FLAG(0);
                _glog.LogJson(getApplicationContext(), "LOGCAT:DEBUG,METHOD:checkUdpCmd,CMD:USE_BIKKURAGYO_FLAG,PROC:OFF,Msg:ビックらぎょ無効,Tag:DxGame,UDPCODE:" + cmd);
                break;

        }
        _gpre_cmd = cmd;
        return 1;
    }

    /**
     * 各種zipをインストール NTT処理を分割
     *
     * @param type
     * @return
     */
    private int getHttpZip(int type, int ver) {
        String absolutePath = this.getFilesDir().getPath();
        File outputPath = new File(absolutePath);

        String download_file_url = "http://" + _gSurveyUrl + "/survey.zip";
        String appName = "survey";
        if (type == 0 || type == 1){
            try {
                asyncFileDownload = new AsyncFileDownload(this, download_file_url, outputPath, appName);
                surveyIsInstalled = asyncFileDownload.execute().get();
                if (!surveyIsInstalled) {
                    //Ver338 既に/data/data/com.kura.andorder/files/配下にフォルダがあった場合、
                    // 既にダウンロードされているため、ステータスは変更しない。
                    Boolean appDirectoryExists = new File(absolutePath + "/" + appName).exists();
                    if (appDirectoryExists) {
                        //Ver338
                        //ダウンロード失敗したがディレクトリが存在した場合は、
                        // 会計ボタン押下時に呼び出しにせず既存コンテンツを表示する。
                        _gHttpSurveyVer = DOWNLOAD_FALED_DIRCTORY_EXIST;
                    }else{
                        //Ver338
                        // フォルダが存在しなかった場合、会計会計押下時に呼び出し画面を表示する99を設定。
                        //zipInstallCount = 0;
                        _gHttpSurveyVer = DOWNLOAD_FALED_DIRCTORY_NOTHING;
                    }
                } else {
                    _gHttpSurveyVer = _glog.getDayFormt();
                }
            } catch (Exception e) {
                _glog.LogJson(this, "Error Async_File_Download " + e.toString());
            }
        }

        if (type == 0 || type == 2) {
            download_file_url = "http://" + _gAutoCheckUrl + "/auto_check.zip";
            appName = "auto_check";
            try {
                asyncFileDownload = new AsyncFileDownload(this, download_file_url, outputPath, appName);
                autoCheckIsInstalled = asyncFileDownload.execute().get();
                if (!autoCheckIsInstalled) {
                    //Ver338 既に/data/data/com.kura.andorder/files/配下にフォルダがあった場合、
                    // 既にダウンロードされているため、ステータスは変更しない。
                    Boolean appDirectoryExists = new File(absolutePath + "/" + appName).exists();
                    if (appDirectoryExists) {
                        //Ver338
                        //ダウンロード失敗したがディレクトリが存在した場合は、
                        // 会計ボタン押下時に呼び出しにせず既存コンテンツを表示する。
                        _gHttpAutoCheckVer = DOWNLOAD_FALED_DIRCTORY_EXIST;
                    }else{
                        //Ver338
                        // フォルダが存在しなかった場合、会計会計押下時に呼び出し画面を表示する99を設定。
                        //zipInstallCount = 0;
                        _gHttpAutoCheckVer = DOWNLOAD_FALED_DIRCTORY_NOTHING;
                    }
                } else {
                    _gHttpAutoCheckVer = _glog.getDayFormt();
                }
            } catch (Exception e) {
                _glog.LogJson(this, "Error Async_File_Download " + e.toString());
            }
        }

        if (type == 0 || type == 3) {
            download_file_url = "http://" + _gQrCodeUrl + "/qr_code.zip";
            appName = "qr_code";
            try {
                asyncFileDownload = new AsyncFileDownload(this, download_file_url, outputPath, appName);
                qrIsInstalled = asyncFileDownload.execute().get();
                if (!qrIsInstalled) {
                    //Ver338 既に/data/data/com.kura.andorder/files/配下にフォルダがあった場合、
                    // 既にダウンロードされているため、ステータスは変更しない。
                    Boolean appDirectoryExists = new File(absolutePath + "/" + appName).exists();
                    if (appDirectoryExists) {
                        //Ver338
                        //ダウンロード失敗したがディレクトリが存在した場合は、
                        // 会計ボタン押下時に呼び出しにせず既存コンテンツを表示する。
                        _gHttpQrVer = DOWNLOAD_FALED_DIRCTORY_EXIST;
                    }else{
                        //Ver338
                        // フォルダが存在しなかった場合、会計会計押下時に呼び出し画面を表示する99を設定。
                        //zipInstallCount = 0;
                        _gHttpQrVer = DOWNLOAD_FALED_DIRCTORY_NOTHING;
                    }
                } else {
                    _gHttpQrVer = _glog.getDayFormt();
                }
            } catch (Exception e) {
                _glog.LogJson(this, "Error Async_File_Download " + e.toString());
            }
        }

        // TODO　20220108 ver 406 のマージ
        if (type == 0 || type == 4) {
            download_file_url = AppEnv.SP_CHECK_ZIP_URL;
            try {
                asyncFileDownload = new AsyncFileDownload(this, download_file_url, outputPath, this.spSelfCheckBasePath);
                spIsInstalled = asyncFileDownload.execute().get();
                if (!spIsInstalled) {
                    //Ver403 既に/data/data/com.kura.andorder/files/配下にフォルダがあった場合、
                    // 既にダウンロードされているため、ステータスは変更しない。
                    Boolean appDirectoryExists = new File(absolutePath + File.separator + this.spSelfCheckBasePath + File.separator + this.spSelfCheckHtmlPath).exists();
                    if (appDirectoryExists) {
                        //Ver403
                        //ダウンロード失敗したがディレクトリが存在した場合は、
                        // 会計ボタン押下時に呼び出しにせず既存コンテンツを表示する。
                        _gHttpSpCheckVer = DOWNLOAD_FALED_DIRCTORY_EXIST;
                    }else{
                        //Ver403
                        // フォルダが存在しなかった場合、会計会計押下時に呼び出し画面を表示する99を設定。
                        //zipInstallCount = 0;
                        _gHttpSpCheckVer = DOWNLOAD_FALED_DIRCTORY_NOTHING;
                    }
                } else {
                    _gHttpSpCheckVer = _glog.getDayFormt();
                }
            } catch (Exception e) {
                _glog.LogJson(this, "Error Async_File_Download " + e.toString());
            }
            Log.d("spCheckIsInstalled", String.valueOf(spIsInstalled));
            Log.d("spCheckHttpVer", String.valueOf(_gHttpSpCheckVer));
        }
        // TODO　20220108 ver 406 のマージ
        return 0;
    }
    /**
     * zipインストールコマンド実行後、Viewを開きなおす処理
     *
     * @return
     */
    private void reopenHttpZipView(){
        /**
         * 取得したzipを開きなおす
         */
        if (_gviewSurvey != null) {
            _gMAIN_FRAME.removeView(_gviewSurvey);
            _gviewSurvey = null;
            _gDrawSurveyFlag = F_VIEW_STATE_OPEN_CMD;
        } else if (_gviewAutoCheck != null) {
            _gMAIN_FRAME.removeView(_gviewAutoCheck);
            _gviewAutoCheck = null;
            _gDrawKaikeiFlag = F_VIEW_STATE_OPEN_CMD;
        } else if (_gviewQrCode != null) {
            _gMAIN_FRAME.removeView(_gviewQrCode);
            _gviewQrCode = null;
            _gDrawQrCodeFlag = F_VIEW_STATE_OPEN_CMD;
         // TODO　20220108 ver 406 のマージ
        } else if (_gviewSpCheck != null) {
            _gMAIN_FRAME.removeView(_gviewSpCheck);
            _gviewSpCheck = null;
            spCheckStatus = F_SPCHECK_STATUS_CHECKING;
            _gDrawKaikeiFlag = F_VIEW_STATE_OPEN_CMD;
            //_gDrawSpCheckFlag = F_VIEW_STATE_OPEN_CMD;
        }
        // TODO　20220108 ver 406 のマージ


    }
    /*=====================================================
author:ys
up:20180330
tag:外部操作用
in: 0:とても暗い 1:暗い 2:MAX 3:blackout
out: 無し
memo:画面照度変更
=====================================================*/
    public void veiwScreenBrightness(int cat) {
        LayoutParams lp = getWindow().getAttributes();
        // 明るさを設定
        switch (cat) {
            case 0:
                lp.screenBrightness = 0.1F;
                // _glog.log("veiwLightChange -> 0.1");
                break;
            case 1:
                lp.screenBrightness = 0.5F;
                //  _glog.log("veiwLightChange -> 0.5");
                break;
            case 2:
                lp.screenBrightness = 1.0F;
                // _glog.log("veiwLightChange -> 1.0");
                break;
            case 3:
                lp.screenBrightness = 0F;
                //  _glog.log("veiwLightChange -> 0");
                break;
        }
        getWindow().setAttributes(lp);
    }


    /*=====================================================
author:ys
up:20180330
tag:外部操作用
in: netaID:netaid sara:皿数
out: sucsess:0 fail;-1
memo:netaIDと皿数を指定してオーダーページをオープンする。
オプションに対応していない。
=====================================================*/
    int operate_order_page(int netaID, int sara) {
        _glog.log("operate_order_page called:id " + netaID + " sara " + sara);
        int _key = _gNetaInfo.SerchNeta(netaID);
        int _page = 0;
        _glog.log("key :" + _key);
        //page change
        if (_key == -1) {
            return -1;
        } else if (_key == 0) {
            _page = 0;
        } else {
            _page = _key / 12;
        }
        _gOrderItemKey = _key;
        pageChange(_page - 1, 1);

        if (_gDrawOrderFlag == 0) {
            int _code = _gNetaInfo.getOptSel(_gOrderItemKey);
            _gOPT_OPT_CODE = _code;
            _gOPT_OPT_CODE_1 = _code * 10 + 1;
            _gOPT_OPT_CODE_2 = _code * 10 + 2;
            _gOPT_OPT_FLAG_1 = _goptInfo.getFlagFromToppingId(_gOPT_OPT_CODE_1);
            _gOPT_OPT_FLAG_2 = _goptInfo.getFlagFromToppingId(_gOPT_OPT_CODE_2);
            _gOPT_SELECTED_OPT_CODE = _gOPT_OPT_CODE_1;//初期値
            //セルフ商品かチェック
            if (_gNetaInfo.getSelf(_gOrderItemKey) == 0) {
                _gthreadSendCmd.setFlagReqWaitOrderCount();//問い合わせﾌﾗｸﾞ
                _gthreadSendCmd.setFlagCheackOn();//即時発行
                _gDrawOrderFlag = F_VIEW_STATE_OPEN_CMD;
            } else {//セルフセルフ警告画面を表示する処理を入れること
                _gDrawOrderSelfFlag = F_VIEW_STATE_OPEN_CMD;
            }
        }
        //page change
    /*if(_key==-1){
        return  -1;
    }else if(_key == 0) {
        _page=0;
    }else{
        _page=_key/12;
    }
    pageChange(_page-1,1);*/
        return 0;
    }

    /*=====================================================
author:ys
up:20180330
tag:外部操作用
in: netaID:netaid sara:皿数
out: sucsess:0 fail;-1
memo:orderを確定。オーダーページがopenしている場合のみ有効。
開いているページの商品情報でオーダーする。
=====================================================*/
    int operate_order_on() {
        _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
        //order pag　出ない場合はcloseする
        if (_gDrawOrderSelfFlag == F_VIEW_STATE_CLEAR) {
            _glog.log("'PROC':operate_order_on' 'state:'err' 'msg':'gDrawOrderSelfFlag is clear'");
            return -1;
        }
        int ret = CheckOrderOn(); //order 状態再チェック
        if (ret == 98 || ret == 99) {
            _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
            if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_CLEAR) {
                _gDrawOrderRefuseMsgFlag = F_VIEW_STATE_OPEN_CMD;
            }
        } else if (ret == 97) {
            _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
            if (_gDrawOrderRefuseMsgFlag == F_VIEW_STATE_CLEAR) {
                _gDrawOrderLmitMsgFlag = F_VIEW_STATE_OPEN_CMD;
            }
        }

        if (_gthreadSendCmd != null) {
            if (_gOPT_OPT_CODE != 0 && _gOPT_SELECTED_OPT_CODE != 0) {
                _gthreadSendCmd.setOrderStr2(_gNetaInfo.getId(_gOrderItemKey), _gOrderUnitCount, 0, _gTtableNum, _gSettingInfo.GetOrderTimeStamp(), _gOPT_SELECTED_OPT_CODE);
            } else {
                _gthreadSendCmd.setOrderStr2(_gNetaInfo.getId(_gOrderItemKey), _gOrderUnitCount, 0, _gTtableNum, _gSettingInfo.GetOrderTimeStamp(), 0);
            }
            _gthreadSendCmd.setFlagCheackOn();//即時発行
            _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
            _gDrawOrderCheckFlag = F_VIEW_STATE_OPEN_CMD;
            _gOPT_OPT_CODE = 0;//選択した商品のオプションコード
            _gOPT_SELECTED_OPT_CODE = 0;//選択したオプション
            _gOPT_OPT_CODE_1 = 0;
            _gOPT_OPT_CODE_2 = 0;
            _gOPT_OPT_FLAG_1 = 0;
            _gOPT_OPT_FLAG_2 = 0;
        } else {
            _glog.log("'PROC':operate_order_on' 'state:'err' 'msg':'_gthreadSendCmd=null'");
            return -1;
        }
        return 0;
    }

    /*=====================================================
author:ys
up:20180330
tag:外部操作用
in: sara:皿数
out: sucsess:0 fail;-1
memo:orderする皿数をセット。オーダーページがopenしている場合のみ有効。
=====================================================*/
    int operate_order_set_sara(int sara) {
        if (sara < 1 || sara > 4) {
            return -1;
        } else {
            _gOrderUnitCount = sara;
            _gDrawOrderUnitFlag = 1;
        }
        return 0;
    }

    /*=====================================================
author:ys
up:20180330
tag:外部操作用
in: 無し
out: sucsess:0 fail;-1
memo:order page close。オーダーページがopenしている場合のみ有効。
=====================================================*/
    int operate_order_close() {
        if (_gDrawOrderCheckFlag == F_VIEW_STATE_CLEAR) {
            return -1;
        }
        _gOPT_OPT_CODE = 0;//選択した商品のオプションコード
        _gOPT_SELECTED_OPT_CODE = 0;//選択したオプション
        _gOPT_OPT_CODE_1 = 0;
        _gOPT_OPT_CODE_2 = 0;
        _gOPT_OPT_FLAG_1 = 0;
        _gOPT_OPT_FLAG_2 = 0;
        Button bt = (Button) findViewById(R.id.OrderOn);
        bt.setEnabled(false);
        _gDrawOrderFlag = F_VIEW_STATE_CLOSE_CMD;
        return 0;
    }

    /*=====================================================
author:ys
up:20180330
tag:外部操作用
in: 無し
out: sucsess:0
memo:前へボタン
=====================================================*/
    int operate_pre_page() {
        _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
        if (_gCurrentPage == 1) {
            this.pageChange(PAGE_MAX, 0);
        } else {
            this.pageChange(_gCurrentPage - 1, 0);
        }
        return 0;
    }

    /*=====================================================
    author:ys
    up:20180330
    tag:外部操作用
    in: 無し
    out: sucsess:0
    memo:次へボタン
    =====================================================*/
    int operate_next_page() {
        _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
        if (_gCurrentPage == PAGE_MAX) {
            this.pageChange(1, 1);
        } else {
            this.pageChange(_gCurrentPage + 1, 1);
        }
        return 0;
    }

    /*=====================================================
author:ys
up:20180330
tag:外部操作用
in: 無し
out: sucsess:0
memo:履歴ボタン
=====================================================*/
    int operate_rireki_open() {
        if (_gDrawRirekiFlag != F_VIEW_STATE_CLEAR) {
            // _glog.log("onClick 履歴 ON break");
            return -1;
        }
        // _glog.log("onClick 履歴 ON");
        _gPAGE_MOVE_OPEN_CNT = 0;//ｽｸﾘｰﾝｾｰﾊﾞｰカウントリセット
        _gthreadSendCmd.setFlagReqRireki();
        _gthreadSendCmd.setFlagCheackOn();//即時発行
        _gDrawRirekiFlag = F_VIEW_STATE_OPEN_CMD;
        return 0;
    }

    /*=====================================================
author:ys
up:20180330
tag:外部操作用
in: 無し
out: sucsess:0 fail;-1
memo:履歴close
=====================================================*/
    int operate_rireki_close() {
        if (_gDrawRirekiFlag == F_VIEW_STATE_CLEAR) {
            return -1;
        } else {
            _gDrawRirekiFlag = F_VIEW_STATE_CLOSE_CMD;
        }
        return 0;
    }


    int operate_yobidasi_open() {
        if (_gDrawCallFlag != F_VIEW_STATE_CLEAR) {
            _glog.log("onClick 呼び出し ON break");
            return -1;
        }
        _gDrawCallFlag = F_VIEW_STATE_OPEN_CMD;
        _gthreadGetFile.setGetNetaInfo_Flag();
        return 0;
    }

    /*=====================================================
author:ys
up:20180330
tag:外部操作用
in: 無し
out: sucsess:0 fail;-1
memo:呼び出しclose
=====================================================*/
    int operate_yobidasi_close() {
        if (_gDrawCallFlag != F_VIEW_STATE_CLEAR) {
            _gDrawCallFlag = F_VIEW_STATE_CLOSE_CMD;
        }
        if (_gDrawKeyAlertFlag != F_VIEW_STATE_CLEAR) {
            _gDrawKeyAlertFlag = F_VIEW_STATE_CLOSE_CMD;
        }
        if (_gDrawKousokuBackFlag != F_VIEW_STATE_CLEAR) {
            _gDrawKousokuBackFlag = F_VIEW_STATE_CLOSE_CMD;
        }
        return 0;
    }

    /*=====================================================
author:ys
up:20180917
tag:外部操作用
in: 無し
out: sucsess:0 fail;-1
memo:呼び出しclose
=====================================================*/
    int operate_kaikei_close() {
        if (_gDrawKaikeiFlag != F_VIEW_STATE_CLEAR) {
            _gDrawKaikeiFlag = F_VIEW_STATE_CLOSE_CMD;
        }
        return 0;
    }

    /*=====================================================
        author:ys
        up:20180903
        tag:外部操作用
        in: 無し
        out: sucsess:0 fail;-1
        memo:呼び出しclose
    =====================================================*/
    int operate_sousaAnnai_open() {
        _glog.log("operate_sousaAnnai_open called");
        if (_gDrawSousaAnnaiFlag != F_VIEW_STATE_CLEAR) {
            _glog.log("operate_sousaAnnai_open break");
            return -1;
        }
        _gDrawSousaAnnaiFlag = F_VIEW_STATE_OPEN_CMD;
        return 0;
    }

    /*=====================================================
        author:ys
        up:20180903
        tag:外部操作用
        in: 無し
        out: sucsess:0 fail;-1
        memo:呼び出しclose
    =====================================================*/
    int operate_sousaAnnai_close() {
        if (_gDrawSousaAnnaiFlag != F_VIEW_STATE_CLEAR) {
            _gDrawSousaAnnaiFlag = F_VIEW_STATE_CLOSE_CMD;
        }
        return 0;
    }

    /*=====================================================
    author:ys
    up:20180330
    tag:外部操作用
    in: 無し
    out: sucsess:0 fail;-1
    memo:会計 open
    =====================================================*/
    int operate_kaikei_open() {
        _gKEY_ALERT_USE_FLAG = 1;
        if (_gDrawKaikeiFlag != F_VIEW_STATE_CLEAR) {
            return -1;
        }
        if (_gDrawCallFlag != F_VIEW_STATE_CLEAR) {
            _gDrawCallFlag = F_VIEW_STATE_CLOSE_CMD;
        }
        _gthreadSendCmd.setFlagReqKaikeiRirekiOn();
        _gthreadSendCmd.setFlagCheackOn();//即時発行
        _gDrawKaikeiFlag = F_VIEW_STATE_OPEN_CMD;
        return 0;

    }

    /*=====================================================
    author:ys
    up:20180620
    tag:操作ログの送信
    =====================================================*/

  /*  TODO 20211208 不要処理コメントアウト
    public void sendFtpOptLog() {
        _glog.log("sendFtpOptLog called");
        if (_gthreadPutFile == null) {
            _gthreadPutFile = new threadPutFile(_gTtableNum, getApplicationContext().getFilesDir(), _glog, "AndOrder_log.txt");
        }
        _gthreadPutFile.setDelSendFileFlag(1);
        _gTHREAD_PUTFILE = new Thread(_gthreadPutFile);
        _gTHREAD_PUTFILE.start();
    }
    */


    /*=====================================================
    author:ys
    up:20180620
    tag:時間合わせ用
    =====================================================*/
    int SetTime(int year, int moth_day, int time) {
        _glog.log("SetCalendar called");
        String str_h = String.format(Locale.JAPAN, "%04d%04d", year, moth_day);
        String str_l = String.format(Locale.JAPAN, "%06d", time);
        Pattern p_h = Pattern.compile("^[0-9]{8}$");
        Pattern p_l = Pattern.compile("^[0-9]{6}$");
        Matcher m_h = p_h.matcher(str_h);
        Matcher m_l = p_l.matcher(str_l);

        if (!m_h.find()) {//正規表現に合わない場合は抜ける
            _glog.log("err:SetCalendar date_h:" + str_h);
            return 0;
        }
        if (!m_l.find()) {//正規表現に合わない場合は抜ける
            _glog.log("err:SetCalendar date_l:" + str_l);
            return 0;
        }
        Runtime runtime = Runtime.getRuntime();
        Process proc = null;
        OutputStreamWriter osw = null;
        // String cmd = "date -s YYYYMMDD.hhmmss";
        String cmd = "date -s " + str_h + "." + str_l;
        _glog.log("SetCalendar cmd:" + cmd);
        try {
            proc = runtime.exec("su");
            osw = new OutputStreamWriter(proc.getOutputStream());
            osw.write(cmd);
            osw.flush();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * アンケート画面の生成メソッド
     *
     * @param
     * @return
     * @since ver319
     */
    private void addViewSurvey() {
        _gviewSurvey = this.getLayoutInflater().inflate(R.layout.view_auto_check, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewSurvey);

        wv = (WebView) findViewById(R.id.autoCheck);
        wv.setWebViewClient(new autoCheckWebView());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.addJavascriptInterface(new JSObject(wv, this), "android");

        final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        if (_gSeatingTime == null) {
            _gSeatingTime = new Date(System.currentTimeMillis());
        }

        String absolutePath = this.getFilesDir().getPath();
        StringBuffer sf = new StringBuffer("file://");
        sf.append(absolutePath);
        sf.append("/survey/client/html/client_kaikei.html");
        sf.append("?seatingTime=");
        sf.append(df.format(_gSeatingTime));
        sf.append("&lang=");
        sf.append(_gLangCode);
        wv.loadUrl(sf.toString());

//        StringBuffer sf = new StringBuffer("http://");
//        sf.append(_gSurveyUrl);
//        sf.append("/client_kaikei.html");
//        sf.append("?seatingTime=");
//        sf.append(df.format(seatingTime));
//        sf.append("&lang=");
//        sf.append(lang);
//        wv.loadUrl(sf.toString());
    }

    /**
     * 自動会計画面の生成メソッド
     *
     * @param
     * @return
     * @since ver319
     */
    private void addViewAutoCheck() {
        _gviewAutoCheck = this.getLayoutInflater().inflate(R.layout.view_auto_check, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewAutoCheck);

        wv = (WebView) findViewById(R.id.autoCheck);
        wv.setWebViewClient(new autoCheckWebView());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new JSObject(wv, this), "android");
//        wv.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onConsoleMessage(ConsoleMessage cm) {
//                Log.d("auto_check_condole", "[" + cm.messageLevel() + "] " + cm.message() + " - " + cm.sourceId() + "：" + cm.lineNumber() + "行目");
//                return true;
//            }
//        });

        final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        if (_gSeatingTime == null) {
            _gSeatingTime = new Date(System.currentTimeMillis());
        }

        String absolutePath = this.getFilesDir().getPath();
        File outputPath = new File(absolutePath);
        String appName = "auto_check";
        //Ver338 会計画面表示条件変更
        //if (autoCheckIsInstalled) {
        if (_gHttpAutoCheckVer != DOWNLOAD_FALED_DIRCTORY_NOTHING) {
            StringBuffer sf = new StringBuffer("file://");
            sf.append(absolutePath);
            sf.append("/auto_check/client/html/client_kaikei.html");
            sf.append("?seatingTime=");
            sf.append(df.format(_gSeatingTime));
            sf.append("&lang=");
            sf.append(_gLangCode);
            wv.clearCache(true);
            wv.loadUrl(sf.toString());
        } else {
            /* TODO
                おあいそ黄色だとハンディ会計ができないので、おあいそを赤点灯背景黄色にする必要がある。
                httpRequestでsetfunctionuseを呼び出す必要あり。
            */
            setCallFlag();
            callViewDisp();
        }

//        StringBuffer sf = new StringBuffer("http://");
//        sf.append(_gAutoCheckUrl);
//        sf.append("/client_kaikei.html");
//        sf.append("?seatingTime=");
//        sf.append(df.format(seatingTime));
//        sf.append("&lang=");
//        sf.append(lang);
//        wv.loadUrl(sf.toString());
    }

    /**
     * WebViewのsocketを閉じるメソッド
     *
     * @param
     * @return
     * @since ver319
     */
    public void socketDisconnect() {
        if (wv != null) {
            wv.loadUrl("javascript:socket.disconnect()");
        }
    }

    /**
     * アンケート、自動会計画面、QRコードのキャンセルメソッド
     *
     * @param
     * @return
     * @since ver319
     */
    public void removeViewCancel() {
        if (_gviewSurvey != null) {
            _gDrawSurveyFlag = F_VIEW_STATE_CLOSE_CMD;
        }
        if (_gviewAutoCheck != null) {
            _gDrawKaikeiFlag = F_VIEW_STATE_CLOSE_CMD;
        }
        if (_gviewQrCode != null) {
            _gDrawQrCodeFlag = F_VIEW_STATE_CLOSE_CMD;
        }

        if (_gviewSpCheck != null) {
            _gDrawKaikeiFlag = F_VIEW_STATE_CLOSE_CMD;
        }

        // TODO　20220108 ver 406 のマージ
        // if (_gDrawCallFlag == 9) _gDrawCallFlag = F_VIEW_STATE_CLEAR;

        // TODO　20220108 ver 406 のマージ
        //Ver403
        // 会計コンテンツが存在せず呼び出しになった場合、中間サーバへ接続できないため
        // ネイティブ側の呼び出し画面を表示する。
        // if (_gKaikeiCallFlag) _gKaikeiCallFlag = false;
        if (_gDrawCallFlag == 9) _gDrawCallFlag = F_VIEW_STATE_CLEAR;
        // TODO　20220108 ver 406 のマージ

    }

    /**
     * アンケートの回答メソッド
     *
     * @param
     * @return
     * @since ver319
     */
    public void removeViewSurveyDone() {
        _gDrawSurveyFlag = F_VIEW_STATE_CLOSE_CMD;
        _gSURVEY_DONE_FLAG = 1;
    }

    // TODO 20220305 #34 押上店向けー会計画面の前にQR読み込みの警告画面を出す
    public void addViewDxGameKaikeiAlert() {
        _gviewDxGameKaikeiAlert = this.getLayoutInflater().inflate(R.layout.view_dxgamekaikeialert, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewDxGameKaikeiAlert);
        ImageView img = ( ImageView ) findViewById(R.id.dxalertImgQr);
        img.setImageBitmap(_gNetaInfo.getQrBmp());
        _gSoundManager.PlaySound(18, 1); // 音声要確認
        _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,PROC:addViewDxGameKaikeiAlert,Msg:会計前のチケット確認画面を表示,TAG:DxGame");
    }


    // TODO add 20210913 QrInfo  View_linkqr.xmlを追加
    public void addViewLinkerQr() {
        _gviewLinkQRCode  = this.getLayoutInflater().inflate(R.layout.view_linkqr, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewLinkQRCode);

        // TODO add 20220321 #36 ゲームチケットのQRの処理修正 QRの有無を確認
        TextView tvQrErrBack = (TextView) findViewById(R.id.QrErrBack);
        TextView tvQrErrMsg = (TextView) findViewById(R.id.QrErrMsg);
        if(_gthreadFtpClient.CheckQrFile() == false){
            _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:addViewLinkerQr,Msg:Qrコード画像が存在しない,TAG:DxGame");
            tvQrErrBack.setVisibility(View.VISIBLE);
            tvQrErrMsg.setVisibility(View.VISIBLE);
        }else{
            // _glog.LogJson(getApplicationContext(), "LOGCAT:SAVE,CMD:addViewLinkerQr,Msg:Qrコード画像有,TAG:DxGame");
            tvQrErrBack.setVisibility(View.INVISIBLE);
            tvQrErrMsg.setVisibility(View.INVISIBLE);
        }
        // TODO add 20220321 #36 ゲームチケットのQRの処理修正 QRの有無を確認

        Button bt = (Button) findViewById(R.id.btQrback);
        // bt.setText(_glocalStr.LoStr("V7_BT_BACK"));

        // TODO 2022.0321 QRの処理修正　①存在チェックする事 !!
        ImageView img = ( ImageView ) findViewById(R.id.ImgQr);
        img.setImageBitmap(_gNetaInfo.getQrBmp());

        // TODO add 20210913 QrInfo 説明要動画表示処理
        // img = ( ImageView ) findViewById(R.id.imgQrHelp);
        // TODO add 20210913 QrInfo 音声処理
         _gSoundManager.PlaySound(18, 1);//音声要確認

        // TODO　20220207 押上店向け、１F、２Fでコンテンツを分ける処理
        if( _gUSE_BIKKURAGYO_FLAG == 1) {
            // ビックら魚のロゴを表示
            img = ( ImageView ) findViewById(R.id.log_gun);
            img.setVisibility(View.VISIBLE);
            img = ( ImageView ) findViewById(R.id.log_dx);
            // img.setX(150);
            // 真ん中のコンテンツ変更
            img = ( ImageView ) findViewById(R.id.imgQrHelp);
            img.setImageResource(R.drawable.linkqr_mount_2);

        } else {
            // ビックら魚のロゴを非表示
            img = ( ImageView ) findViewById(R.id.log_gun);
            img.setVisibility(View.GONE);
            img = ( ImageView ) findViewById(R.id.log_dx);
            // img.setX(0);
            // 真ん中のコンテンツ変更:
            img = ( ImageView ) findViewById(R.id.imgQrHelp);
            img.setImageResource(R.drawable.linkqr_mount_1);

        }
        // TODO　20220207 押上店向け、１F、２Fでコンテンツを分ける処理


    }
    // TODO add 20210913 QrInfo  View_linkqr.xmlを追加

    public void addViewQrCode() {
        // WebView呼び出し
         // TODO add 20210913 get QRINFO
        _gviewQrCode = this.getLayoutInflater().inflate(R.layout.view_qrcode, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewQrCode);
        qrWv = (WebView) findViewById(R.id.qrcode);
        qrWv.setWebViewClient(new qrCodeWebView());
        // WebView内のJavaScriptの実行を許可
        qrWv.getSettings().setJavaScriptEnabled(true);
        qrWv.addJavascriptInterface(new JSObject(qrWv, this), "android");
        String absolutePath = this.getFilesDir().getPath();
        File outputPath = new File(absolutePath);
        String appName = "qr_code";
        StringBuffer sf = new StringBuffer("file://");
        sf.append(absolutePath);
        sf.append("/qr_code/client/html/qrcode.html");
        if (_gHttpQrVer != DOWNLOAD_FALED_DIRCTORY_NOTHING) {
            try {
                //Qrコードを取得
                String url = "http://" + _gQrCodeUrl + "/api/setQrCode";
                JSONObject requestJson = new JSONObject("{seatNo:" + _gTtableNum + "}");
                AsyncJsonDownload asyncJsonDownload = new AsyncJsonDownload(url, requestJson);
                JSONObject responseJson = asyncJsonDownload.execute().get();
                if (responseJson == null) {
                    throw new Exception("Jsonファイルの取得に失敗しました");
                }
                //GET値を設定
                sf.append("?error=");
                sf.append(responseJson.getString("error"));
                sf.append("&data=");
                sf.append(responseJson.getString("data"));
                sf.append("&from=");
                sf.append(String.valueOf(qrFromMain));
                sf.append("&tableNum=");
                sf.append(String.valueOf(_gTtableNum));
                Log.d("QrCodeServerURL", sf.toString());
                qrWv.clearCache(true);
                qrWv.loadUrl(sf.toString());
            } catch (Exception e) {
                //ログ出力
                StringWriter sw = null;
                PrintWriter pw = null;
                sw = new StringWriter();
                pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String trace = sw.toString();
                _glog.log(trace);
                //GET値を設定
                sf.append("?error=9");
                sf.append("&data=Jsonファイルの取得に失敗しました");
                sf.append("&from=");
                sf.append(String.valueOf(qrFromMain));
                sf.append("&tableNum=");
                sf.append(String.valueOf(_gTtableNum));
                qrWv.loadUrl(sf.toString());
                Log.d("QrCodeServerURL", sf.toString());
            }
        } else if (qrFromMain == 1) {
            setCallFlag();
            callViewDisp();
        }
          // TODO add 20210913 get QRINFO
    }

    public void removeViewQrCode() {
        _gDrawQrCodeFlag = F_VIEW_STATE_CLOSE_CMD;
        Log.d("removeViewQrCode", "_gDrawQrCodeFlag=" + String.valueOf(_gDrawQrCodeFlag));
    }

    /**
     * WebViewClient継承クラス
     *
     * @since ver319
     */
    public class autoCheckWebView extends WebViewClient {
//        private ProgressDialog progress;

        public autoCheckWebView() {
            super();
//            progress = null;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            _gDrawAutoCheckWebViewLoadFlag = F_VIEW_STATE_OPEN_CMD;
        }

        public void onPageFinished(WebView view, String url) {
            _gDrawAutoCheckWebViewLoadFlag = F_VIEW_STATE_CLOSE_CMD;
        }
    }

    /**
     * WebViewClient継承クラス
     *
     * @since ver319
     */
    public class qrCodeWebView extends WebViewClient {
        public qrCodeWebView() {
            super();
        }
    }


    // TODO　20220108 ver 406 のマージ
    /**
     * スマホ会計 WebViewClent 継承クラス
     */
    public class spCheckWebView extends WebViewClient {
        public spCheckWebView() {
            super();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            _gDrawAutoCheckWebViewLoadFlag = F_VIEW_STATE_OPEN_CMD;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            _gDrawAutoCheckWebViewLoadFlag = F_VIEW_STATE_CLOSE_CMD;
//            view.evaluateJavascript("document.getElementById('txt-desc').innerHTML='" + _glocalStr.LoStr("SP_CHECK_DESC") + "'", null);
//            view.evaluateJavascript("document.getElementById('bt-back').innerHTML='" + _glocalStr.LoStr("SP_CHECK_BACK") + "'", null);
        }
    }




    // TODO　20220108 ver 406 のマージ

    /**
     * WebViewの店員呼出し画面を表示するメソッド
     *
     * @since ver319
     */
    /*
    public void drawCallViewDisp() {
        //String absolutePath = this.getFilesDir().getPath();
        StringBuffer sf = new StringBuffer("file:///android_asset/wv_call/html/wv_call.html");
        //sf.append(absolutePath);
        //sf.append("/android_asset/wv_call/html/wv_call.html");
        if (wv != null) {
            wv.loadUrl(sf.toString());
        } else if (qrWv != null) {
            qrWv.loadUrl(sf.toString());
        }
    }
    */
    // TODO　20220108 ver 406 のマージ
    public void drawCallViewDisp() {
        //String absolutePath = this.getFilesDir().getPath();
        StringBuffer sf = new StringBuffer("file:///android_asset/wv_call/html/wv_call.html");
        //sf.append(absolutePath);
        //sf.append("/android_asset/wv_call/html/wv_call.html");
        if (wv != null) {
            //自動会計・サーベイ時にサーバとの接続・コンテンツの読み込みに異常が生じた場合
            wv.loadUrl(sf.toString());
        } else if (qrWv != null) {
            //QR時にサーバとの接続・コンテンツの読み込みに異常が生じた場合
            qrWv.loadUrl(sf.toString());
        } else if (spCheckWv != null) {
            //スマホ会計中画面でソケットとの通信が切れた場合
            spCheckWv.loadUrl(sf.toString());
        } else if (spCheckStatus == F_SPCHECK_STATUS_CALLING) {
            //スマホ会計で初期情報取得失敗時した場合
            _gviewSpCheck = this.getLayoutInflater().inflate(R.layout.view_sp_check, (ViewGroup) null);
            _gMAIN_FRAME.addView(_gviewSpCheck);
            spCheckWv = (WebView) findViewById(R.id.sp_check);
            spCheckWv.setWebViewClient(new WebViewClient());
            spCheckWv.getSettings().setJavaScriptEnabled(true);
            spCheckWv.addJavascriptInterface(new JSObject(wv, this), "android");
            spCheckWv.loadUrl(sf.toString());
        }
    }
    // TODO　20220108 ver 406 のマージ
    /**
     * スマホ会計WebView呼び出し
     */
    private void addViewSpCheck() {
        _gviewSpCheck = this.getLayoutInflater().inflate(R.layout.view_sp_check, (ViewGroup) null);
        _gMAIN_FRAME.addView(_gviewSpCheck);

        spCheckWv = (WebView) findViewById(R.id.sp_check);
        spCheckWv.setWebViewClient(new spCheckWebView());
        spCheckWv.getSettings().setJavaScriptEnabled(true);
        spCheckWv.addJavascriptInterface(new JSObject(spCheckWv, this), "android");
        String absolutePath = this.getFilesDir().getPath();
        StringBuffer sf = new StringBuffer("file://");
        sf.append(absolutePath);
        sf.append("/" + AppEnv.SP_CHECK_APP_NAME + "/client/html/check.html");
        try {
            sf.append(String.format("?tableNum=%d", _gTtableNum));
            sf.append("&lang=");
            sf.append(_gLangCode);
            spCheckWv.clearCache(true);
            spCheckWv.loadUrl(sf.toString());

        } catch (Exception e) {
            StringWriter sw = null;
            PrintWriter pw = null;
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String trace = sw.toString();
            _glog.log(trace);
        }
    }

    // TODO　20220108 ver 406 のマージ
    /**
     * メインを除く全てのビューステートを一括変更する
     *
     * @param state
     */
    public void setAllViewStates(int state) {
        _gDrawHELPFlag = state;
        _gDrawKousokuBackFlag = state;
        _gDrawKeyAlertFlag = state;
        _gDrawTopFlag = state;
        _gDrawCallFlag = state;
        _gDrawOrderFlag = state;
        _gDrawOrderUnitFlag = state;
        _gDrawOrderSelfFlag = state;
        _gDrawOrderRefuseMsgFlag = state;
        _gDrawOrderLmitMsgFlag = state;
        _gDrawKaikeiFlag = state;
        _gDrawAutoCheckWebViewLoadFlag = state;
        _gDrawFtpUpdatetFlag = state;
        _gDrawSurveyFlag = state;
        _gDrawQrCodeFlag = state;
        _gDrawRirekiFlag = state;
        _gDrawTestFlag = state;
        _gDrawUpdateAlertFlag = state;
        _gDrawMovFlag = state;
        _gDrawGameFlag = state;
        _gDrawArriveFlag = state;
        _gDrawArriveKousokuFlag = state;
        _gDrawOrderCheckFlag = state;
        _gDrawNumFlag = state;
        _gDrawSousaAnnaiFlag = state;
        _gDrawLowBatteryAlertFlag = state;
       // _gDrawFirstFreeOrderFlag = state;
        _gDrawOrderArriveAlertFlag = state;
        _gDrawOpenningMoveFlag = state;
        //_gDrawSpCheckFlag = state;
    }
    // TODO　20220108 ver 406 のマージ
    /**
     * メインを除く全てのビューを閉じる
     */
    public void closeAllViews() {
        setAllViewStates(F_VIEW_STATE_CLOSE_CMD);
    }
    // TODO　20220108 ver 406 のマージ
    /**
     * スマホ会計キャンセル
     */
    public void cancelSpCheck() {
        socketDisconnect();
        closeAllViews();
    }
    // TODO　20220108 ver 406 のマージ

    public void callViewDisp() {
        //drawCallViewDispを実行するフラグを立てる
        _gDrawWvCallFlag = F_VIEW_STATE_OPEN_CMD;
    }
    // TODO　20220108 ver 406 のマージ
    public void setCallFlag() {
        _gDrawCallFlag = 9;
    }
    // TODO　20220108 ver 406 のマージ
    private JSONObject doSpCheckCallApi() {
        try {
            String uri = "/calltosp/" + AppEnv.SP_CHECK_CALL_VERSION + "/" + _gMiseCode + "/" + _gTtableNum;
            String url = AppEnv.SP_CHECK_API_SERVER_URL + uri;
            final DateFormat df = new SimpleDateFormat("\"yyyy-MM-dd HH:mm:ss\"");
            String requestedAt = df.format(new Date(System.currentTimeMillis()));

            JSONObject requestJson = new JSONObject("{" +
                    "\"terminalKindCd\":" + AppEnv.ANDORDER_TERMINAL_KIND_CODE +
                    ",\"requestedAt\":" + requestedAt +
                    ",\"reserve\":[]" +
                    "}");
            AsyncJsonDownload asyncJsonDownload = new AsyncJsonDownload(url, requestJson);
            return asyncJsonDownload.execute().get();
        } catch (Exception e) {
            Log.d("SMARTPHONE_SELF_CHECK", e.toString());
        }
        return null;
    }
    // TODO　20220108 ver 406 のマージ
}
