package com.kura.andorder;

//import android.util.Log;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by kura on 2016/03/01.
 * 192.168.11.155　及び 192.168.11.3の設定情報を保持する。
 */
public class settingInfo {

    //from 192.168.11.155
    //private final String _TAG = "USER_LOG";
    private  logExtend _glog=null;

    //private final int CLEAR_RESET_FLAG_COUNT= 20;
    private final int CLEAR_WIN_FLAG__COUNT= 20;

    private final int SARA_TO_GAME_UNIT= 5;//default set

    private int _gSaraCnt=0;
    private int _gKagiState=0;//鍵リセット信号のステータス
    private int _gGgameEnd=0;//ゲーム終了数
    private int _gWinFlag=0;//ゲームの当たりを上位PCに伝えるためのフラグ

    private int _gGameWinRate=0;
    private int _gSaraToGameUnit=0;//皿何枚でゲームスタートするか
    private int _gScreenOnCount=0;//スクリーンセーバの起動時間
    private int _gGameWinResetMode=0;//ゲームの当たりを積算するかのモード　０：しない　１：する
    private int _gGameTypeMode=0;//
    private int _gGameWinResetCount=0;//ゲームの当たりを積算し、当たったらリセットする変数
    private int _gGameWinPlayCount=0;//びっくらぽん出力の遅延カウン
    private int _gOrderWaitTime=0;
    private int _gServerTimeH=0;
    private int _gServerTimeM=0;
    private int _gServerTimeW=0;
    private int _gWinCntAll=0;

    private int _gCmd=0;
    private int _gopt1=0;
    private int _gopt2=0;
    private int _gopt3=0;
    private int _gcheakRetWinFlag_cnt=0;



    //add 20200110 特殊game用の処理
    private int _gGameWinArra[];



    /**
     * コンストラクタ
     */
    public settingInfo(logExtend obj){
        //初期値
        _gScreenOnCount =5;
        _gSaraToGameUnit = 5;
        _gGameWinRate = 6;
        //add 20160524
        _gOrderWaitTime=15;

        this._glog=obj;
        setGameWinArrayDefault();
    }

    /*=====================================================
     up:2014 0512
     aothor:ys
     content:setKaikeiResetFlag
     ====================================================*/
    public int getCmd(){
        return _gCmd;
    }
    public int getOpt1(){
        return _gopt1;
    }
    public int getOpt2(){
        return _gopt2;
    }
    public int getOpt3(){
        return _gopt3;
    }



    /*=====================================================
     up:20161228
     aothor:ys
     content:
     ====================================================*/
    int getServerTimeH() {
        //_gServerTimeH;
        return _gServerTimeH;
    }
    int getServerTimeW() {
        //_gServerTimeH;
        return _gServerTimeW;
    }




    /*=====================================================
     up:2014 0718
     aothor:ys
     content:ゲームの当たりを積算し、当たり積算をクリアする。
     積算クリアモードのケースで使用する。
     ====================================================*/
    public void CleargameWinRisetCount(){
        _gGameWinResetCount =0;
    }
    /*=====================================================
     up:2014 0512
     aothor:ys
     content:set
     ====================================================*/
    public void ClearIOgame_End(){
        _gGgameEnd =0;
        _gWinCntAll=0;
        //add 2014 0718 当たり積算クリアモードの場合は積算をクリアする。
        if(_gGameWinResetMode ==1){//change 2014 0809
            _gGameWinResetCount =0;
        }
    }
    /*=====================================================
     up:2014 0512
     aothor:ys
     content:setKaikeiResetFlag

     ====================================================*/
    public void setWinFlag(int val){
        //NSLog(@"setWinFlag %d",val);
        if(val == 2){
            _gWinFlag=1;
        }else if(val == 0){
            _gWinFlag=0;//clear
        }
    }
    /*=====================================================
     up:2014 0512
     aothor:ys
     content:当たり確立変更

     ====================================================*/
    public void setGameWinRaito(int val){
        // NSLog(@"setGameWinRaito %d",val);
        if(val <= 0 ){

        }else{
            _gGameWinRate=val;
        }
    }
    /*=====================================================
     up:2014 0512
     aothor:ys
     content:
     ゲーム数は上位PCに伝達し、返信されたゲーム数の方が多い場合は、
     異常と考え、ゲーム数を補正する形を検討。

     ====================================================*/
    public void cheakGame(int val){
        // NSLog(@"cheakGame >> %d",val);
        if(_gGgameEnd < val){//返信されたゲーム数の方が多い場合は、異常と考え、ゲーム数を補正する。
            _gGgameEnd= val;
        }
    }
    /*=====================================================
     up:2014 0512
     aothor:ys
     content:winflag チェック

     ====================================================*/
    public void cheakRetWinFlag(int val){

        if(val == 1){//win信号が入った
            if(_gWinFlag ==1){//winフラグがonなら落とす。
                setWinFlag(0);
                _gcheakRetWinFlag_cnt=0;
                return;
            }else{//winフラグがonではないケースでリセット信号が入るのはおかしい
                _gcheakRetWinFlag_cnt=0;
            }
        }
        if(_gWinFlag ==1){
            _gcheakRetWinFlag_cnt++;//一定時間フラグクリアが来ない場合は強制的にクリア
            if(_gcheakRetWinFlag_cnt > CLEAR_WIN_FLAG__COUNT){//change 2014 0504
                setWinFlag(0);
                _gcheakRetWinFlag_cnt=0;
            }
        }
    }

    /****************************************************************
     getter
     *****************************************************************/
    public int GetSaraCnt(){
        return _gSaraCnt;
    }

    public int GetKagiState(){
        return  _gKagiState;
    }
    public int GetGgameEnd(){
        return  _gGgameEnd;
    }
    public int GetWinFlag(){
        return  _gWinFlag;
    }
    public int GetGameWinRate(){
        return  _gGameWinRate;
    }
    public int GetSaraToGameUnit(){
        return  _gSaraToGameUnit;
    }
    public int GetScreenOnCount(){
        return  _gScreenOnCount;
    }
    public int GetGameWinResetMode(){
        return  _gGameWinResetMode;
    }
    public int GetGameTypeMode(){
        return  _gGameTypeMode;
    }
    public int GetGameWinResetCount(){
        return  _gGameWinResetCount;
    }
    public int GetGameWinPlayCount(){
        //0以下は異常値なので、０以下が設定されているなら強制的に０を返す。
        if(_gGameWinPlayCount<0){
            return 0;
        }
        return  _gGameWinPlayCount;
    }
    public int GetOrderWaitTime(){
        return  _gOrderWaitTime;
    }
    public int GeServerTimeH(){
        return  _gServerTimeH;
    }
    public int GetServerTimeM(){
        return  _gServerTimeM;
    }
    public int GetServerTimeW(){
        return  _gServerTimeW;
    }


    public int GetWinCntAll(){
        return  _gWinCntAll;
    }



    /****************************************************************

     *****************************************************************/
    private int RangeCheck(String str,int H,int L) {
        int x=Integer.parseInt(str);
        if(x<=H && x>=L){
            return x;
        }else{
            return -1;
        }
    }
    /****************************************************************
     from udp
     *****************************************************************/
    public boolean setIoInfo(String src){

        if(src == null){
            _glog.log("ERRsetIoInfo:null" );
            //return 0;
            return false;
        }
        String str=new String(src.trim());
        int x=0;
        String arr[];
        int count;
        int sara=0;
        try {
            arr=str.split(",");
            count = arr.length;
            if (count < 28) {
                _glog.log("ERRsetIoInfo:count short" +count );
                //return -1;
                return false;
            }
            sara=Integer.parseInt(arr[3]);//皿カウント
            if(_gSaraCnt>sara){
                _glog.log("皿カウントおかしい 今:" +_gSaraCnt);
                _glog.log("皿カウントおかしい　入力値:" +sara);
            }
            _gSaraCnt=sara;//皿カウント
            _gKagiState=Integer.parseInt(arr[4]);//かぎ状態
            x=RangeCheck(arr[5], 100, 1);
            if(x!=-1){
                _gGameWinRate=x;//何回目のゲームであたりか
            }
            x=RangeCheck(arr[6],100,1);
            if(x!=-1){
                _gSaraToGameUnit=x;//何皿でゲームがはじまるか
            }
            x=RangeCheck(arr[7],100,1);
            if(x!=-1){
                _gScreenOnCount=x;//
            }
            x=RangeCheck(arr[8],21,0);
            if(x == 0){
                _gGameWinResetMode=0;
                _gGameTypeMode=0;
            }
            //===============================================
            //add 20170222 アメリカ対応用に修正 コメントアウト
           /* if(x!=-1){
                _gGameWinResetMode=x;//
            }*/
            if(x == 1 ){
                _gGameWinResetMode =1;
                _gGameTypeMode=0;
            }
            if(x == 10){
                _gGameWinResetMode =0;
                _gGameTypeMode=1;
            }
            if(x == 11){
                _gGameWinResetMode =1;
                _gGameTypeMode=1;
            }
            if(x == 20){
                _gGameWinResetMode =0;
                _gGameTypeMode=2;
            }
            if(x == 21){
                _gGameWinResetMode =1;
                _gGameTypeMode=2;
            }
            x=RangeCheck(arr[9],250,0);
            if(x!=-1){
                _gGameWinPlayCount=x;//びっくらぽん出力タイミング
            }
            //arr[10]リザーブ

            //arr[11]ゲーム数ー＞チェック
            x=RangeCheck(arr[11],100,0);
            if(x!=-1) {
                this.cheakGame(x);
            }
            //arr[12]あたり信号リセットフラグー＞チェック処理
            x=RangeCheck(arr[12],1,0);
            if(x!=-1){
                this.cheakRetWinFlag(x);
            }
            //arr[13]未定義　品切れ通知実装？
            //arr[14]未定義　orderstop 未実装？
            x=RangeCheck(arr[15],100,2);
            if(x!=-1){
                _gOrderWaitTime=x;//注文到着時間目安
            }else{
                _gOrderWaitTime=15;//デフォルト
            }
            x=RangeCheck(arr[16],24,0);
            if(x!=-1){
                _gServerTimeH=x;//
            }
            x=RangeCheck(arr[17],60,0);
            if(x!=-1){
                _gServerTimeM=x;//何回目のゲームであたりか
            }
            x=RangeCheck(arr[18],6,0);
            if(x!=-1){
                _gServerTimeW=x;//
            }




            _gCmd=Integer.parseInt(arr[24]);
            _gopt1=Integer.parseInt(arr[25]);
            _gopt2=Integer.parseInt(arr[26]);
            _gopt3=Integer.parseInt(arr[27]);
        }catch (Exception e){
            _glog.log("ERR:setIoInfo" + e.toString());
        }
        //  printTest();
        //return 1;
        return true;
    }

    /*=====================================================
     up:2014 0512
     aothor:ys
     content:当たり判定を前の席からの累積で判定
     ====================================================*/
    public int gameCheak2(){
        /*  _glog.log( "SaraCnt>"+_gSaraCnt);
          _glog.log( "SaraToGameUnit>"+_gSaraToGameUnit);
          _glog.log( "GgameEnd>"+_gGgameEnd);*/
        if(_gSaraToGameUnit == 0 || _gGameWinRate == 0){
            return 0;
        }
        if(_gSaraCnt ==0){
            return 0;
        }

        int gamecnt = (_gSaraCnt/_gSaraToGameUnit)-(_gGgameEnd);

        if(gamecnt <= 0){//ゲーム無し
            return 0;
        }else{//ゲーム有り
            _gGgameEnd++;
            _gGameWinResetCount++;

            if(_gGameWinResetCount >= _gGameWinRate){
                _gGameWinResetCount=0;
                _gWinCntAll++;

                return 2;//当たり
            }else{
                return 1;//ハズレ
            }

        }
    }

    /*=====================================================
  up:20200109
  aothor:ys
  content:当たり判定を配列で指定
  ====================================================*/
    public int gameCheakwithArray(){
        /*  _glog.log( "SaraCnt>"+_gSaraCnt);
          _glog.log( "SaraToGameUnit>"+_gSaraToGameUnit);
          _glog.log( "GgameEnd>"+_gGgameEnd);*/
        if(_gSaraToGameUnit == 0 || _gGameWinRate == 0){
            return 0;
        }
        if(_gSaraCnt ==0){
            return 0;
        }
        int gamecnt = (_gSaraCnt/_gSaraToGameUnit)-(_gGgameEnd);

        _glog.log( "gameCheakwithArray gamecnt:" + gamecnt);

        if(gamecnt <= 0){//ゲーム無し
            return 0;
        }else{//ゲーム有り
            _gGgameEnd++;
            //配列を越えた場合は外れとする
            if(_gGameWinArra.length <= gamecnt){
                return 1;//ハズレ
            }
            if(_gGameWinArra[gamecnt-1] == 2){
                _glog.log( "gameCheakwithArray win");
                return 2;
            }else{
                _glog.log( "gameCheakwithArray loose");
                return 1;//ハズレ
            }
        }

    }



    /****************************************************************
     print test
     *****************************************************************/
    public void printTest(){
        _glog.log(">"
                + " 皿:" + _gSaraCnt
                + " かぎ:" + _gKagiState
                + " ゲーム数:" + _gGgameEnd
                + " あたりﾌﾗｸﾞ:" + _gWinFlag
                + " あたり確立:" + _gGameWinRate
                + " ゲーム/皿数:" + _gSaraToGameUnit
                + " ｽｸﾘｰﾝｾｰﾊﾞｰ:" + _gScreenOnCount
                + " ｹﾞｰﾑﾘｾｯﾄﾓｰﾄﾞ:" + _gGameWinResetMode
                + " ｹﾞｰﾑﾀｲﾌﾟ:" + _gGameTypeMode
                + " winResetCount:" + _gGameWinResetCount
                + " ﾋﾞｯｸﾗﾎﾟﾝﾀｲﾐﾝｸﾞ:" + _gGameWinPlayCount
                + "待ち時間:" + _gOrderWaitTime
                + " 時:" + _gServerTimeH
                + " 分:" + _gServerTimeM
                + " 週:" + _gServerTimeW
                + " WinCntAll:" + _gWinCntAll
                + " CMD:" + _gCmd
                + " OPT1:" + _gopt1
                + " OPT2:" + _gopt2
                + " OPT3:" +  _gopt3);
    }
    /****************************************************************
     GetOrderTimeStamp
     *****************************************************************/
    public int GetOrderTimeStamp(){
        int ret=0;
        String str= String.format(Locale.JAPAN,"%02d%02d%02d", _gServerTimeW, _gServerTimeH, _gServerTimeM);
        // _glog.log( " GetOrderTimeStamp:"+str);
        ret=Integer.parseInt(str);
        return ret;
    }



//=============================================================
    //up:20200110
    //特殊ゲーム用default
//==============================================================
   private void setGameWinArrayDefault() {
        _gGameWinArra=new int[100];
        for(int i=0;i<100;i++){
            if(i % 6 == 0) {
                _gGameWinArra[i] = 2;
            }else{
                _gGameWinArra[i] = 1;
            }
        }
    }


//=============================================================
    //up:20200110
    //特殊ゲーム用設定
//==============================================================
    public void setGameWinArray(File f) {

        setGameWinArrayDefault();
        FileReader filereader = null;
        try {
                File file = new File( f.toString() + "/upload/gameWinArray.csv");
                if(!file.exists()){
                    _glog.log("ERR:setGameWinArray not exit file");
                    //default set
                    return;
                }
                filereader = new FileReader(file);
                int ch;//読み込み文字
                int i=0;
                while((ch=filereader.read())!=-1){//一文字づつ読み込み
                    if(ch == '2'){
                        _gGameWinArra[i]=2;
                    }else{
                        _gGameWinArra[i]=1;
                    }
                    i++;
                    if( i >= 100){
                        break;
                    }
                }
                _glog.log("setGameWinArray:"+ Arrays.toString(_gGameWinArra));
            }catch (Exception e){
            _glog.log("ERR:setGameWinArray:" + e.toString());
        }finally {
            if(filereader != null) {
                try {
                    filereader.close();
                }catch (IOException e){
                    _glog.log("ERR:setGameWinArray:" + e.toString());
                }
            }
        }
    }



}
