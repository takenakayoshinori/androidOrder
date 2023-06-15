package com.kura.andorder;

import android.util.Log;


public class kaikeiInfo {

    private String TAG = "USER LOG";
    private int KAIKEI_ALERT_FLAG = 0;
    private final String CHECK_TBUF = "00:00:00";

    private  logExtend _glog=null;

    // private final int MAX_KAIKEI_RIREKI_COUNT = 30; kaikeicatを

    // TODO kaikeicatを100まで対応させる 20211222
    // private final int MAX_KAIKEI_RIREKI_COUNT = 60;
    private final int MAX_KAIKEI_RIREKI_COUNT = 100;
    private String kaikeiRirekiSara[] = new String[MAX_KAIKEI_RIREKI_COUNT];
    private String kaikeiRirekiCat[] = new String[MAX_KAIKEI_RIREKI_COUNT];
    private String kaikeiRirekiName[] = new String[MAX_KAIKEI_RIREKI_COUNT];

    //add 20180828 経費算定テスト用
    private int kaikeiRirekiPrice[] = new int[MAX_KAIKEI_RIREKI_COUNT];

    private int kaikeiCount = 0;
    private int kaikeiShowCnt = 0;

    //from Transfer
    // private final int MAX_T_RIREKI = 80;
    private final int MAX_T_RIREKI = 100;
    private int _TnetaId[] = new int[MAX_T_RIREKI];
    private int _Tcheak[] = new int[MAX_T_RIREKI];
    private int _Tsara[] = new int[MAX_T_RIREKI];
    private String _Ttime[] = new String[MAX_T_RIREKI];
    private String _TnetaName[] = new String[MAX_T_RIREKI];
    private int _Tseizou[] = new int[MAX_T_RIREKI];
    private int _TkaikeiCat[] = new int[MAX_T_RIREKI];
    private int _TdataCount = 0;
    //add 20171109 オプション選択表示
    private int _Topt[] = new int[MAX_T_RIREKI];

    //add 20180901 会計区分毎の計算結果を変数に持たせる。
    private int _CalCount[]=new int[MAX_KAIKEI_RIREKI_COUNT];
    private int _CalCountSara=0;
    private StringBuilder _CalCountSaraStr;//100yen 皿内訳
    private StringBuilder _CalCountSaraStr2;//200yen 皿内訳
    //---------------------------------------
    public kaikeiInfo(logExtend obj){
        this.clearRireki();
        _glog=obj;
    }

    public String getkaikeiRirekiSara(int x){
        if(x>=0 && x<MAX_KAIKEI_RIREKI_COUNT){
            if(kaikeiRirekiSara[x] == null){
                return "";
            }else {
                return kaikeiRirekiSara[x];
            }
        }else{
            return "";
        }
    }

    public String getkaikeiRirekiCat(int x){
        if(x>=0 && x<MAX_KAIKEI_RIREKI_COUNT){
            if(kaikeiRirekiCat[x] == null){
                return "";
            }else {
                return kaikeiRirekiCat[x];
            }
        }else{
            return "";
        }
    }

    //add 20180829
    public int getkaikeiRirekiPrice(int x){
        if(x>=0 && x<MAX_KAIKEI_RIREKI_COUNT){
            return kaikeiRirekiPrice[x];
        }else{
            return  0;
        }
    }


    //=======================================
//<change:20150306> 会計前に皿の投入を促す表示
//=======================================
    public int setKaikeiAlertFlag(int val) {
        KAIKEI_ALERT_FLAG = val;
        return KAIKEI_ALERT_FLAG;
    }
//=======================================

    //=======================================
    public void SetKaikeiRireki(String str) {

        kaikeiShowCnt =0;//add 20140901
        clearRireki();
        analizeKaikeiInfo(str);

    }
    //=======================================
//add 2014 0829会計画面が起動していた時間に応じて、戻るボタンの挙動をかえる。
//=======================================
  /*  private void kaikeiShowCntUp() {
        kaikeiShowCnt++;
    }*/

  /*  public int getkaikeiShowCnt() {
        return kaikeiShowCnt;
    }*/

    //=======================================
//clearRireki
//2014 0712
//=======================================
    public void clearRireki() {
        int i;
        for (i = 0; i < MAX_KAIKEI_RIREKI_COUNT; i++) {
            kaikeiRirekiSara[i] = null;
            kaikeiRirekiName[i] = null;
            kaikeiRirekiCat[i] = null;

            kaikeiRirekiPrice[i]=0;
        }
    }

    //==================================================
//解析
//up 20150526
//==================================================
    private void analizeKaikeiInfo(String str) {

        Log.i(TAG, "==>"+str);
        int i = 0;
        String arr[] = null;
        arr = str.split(":");
        int count = arr.length;

        if (count == 0) {
            kaikeiCount = 0;
            return;
        } else if (count > MAX_KAIKEI_RIREKI_COUNT) {
            count = MAX_KAIKEI_RIREKI_COUNT;
        }
        String arr2[] = null;
        for (i = 0; i < count; i++) {
            arr2 = arr[i].split(",");
            if (arr2.length == 3) {
                kaikeiRirekiName[i] = arr2[2];
                kaikeiRirekiSara[i] = arr2[1];
                kaikeiRirekiCat[i] = arr2[0];
            }
            if (arr2.length == 4) {
                kaikeiRirekiName[i] = arr2[2];
                kaikeiRirekiSara[i] = arr2[1];
                kaikeiRirekiCat[i] = arr2[0];
                //add 20180828 金額情報の追加 エラーを吸収するラッパー
                kaikeiRirekiPrice[i]=ParseInt(arr2[3]);
            }
        }
        kaikeiCount = count - 1;
        arr = null;
        arr2 = null;
    }


// FROM transfer
    //=======================================

    //=======================================
    public void clearTdata() {
        int i;
        for (i = 0; i < MAX_T_RIREKI; i++) {
            _TnetaId[i] = 0;
            _Tcheak[i] = 0;
            _Tsara[i] = 0;
            _Tseizou[i] = 0;
            _Ttime[i] = null;
            _TnetaName[i] = null;
            _TdataCount = 0;

            _TkaikeiCat[i] = 0;

            //add 20171106 オプション表示用
            // _ToptCode[i]  = 0;
            _Topt[i]  = 0;
        }
    }

    //================================================
//あおいそ時 表示用2
//change 20171109 オプション選択表示対応
//20150523
//==================================================
    public String GetRirekiStrLocal3(netaInfo _netaInfo,optInfo _optInfo) {
        int i = 0;
        int cal[] = new int[MAX_KAIKEI_RIREKI_COUNT];//add 20180419
        StringBuffer buf[] = new StringBuffer[MAX_KAIKEI_RIREKI_COUNT];//add 20180419
        StringBuffer sb = new StringBuffer("");
        int optCode=0;
        String optStr="";

        _glog.log("GetRirekiStrLocal3 called");

        for (i = 0; i < MAX_KAIKEI_RIREKI_COUNT; i++) {//add 20180419
            cal[i] = 0;
            buf[i] = new StringBuffer("");
        }
        for (i = 0; i < MAX_T_RIREKI; i++) {
            if (_TnetaId[i] == 0) {
                break;
            } else {
                if(_Topt[i]==0){
                    _TnetaName[i] = _netaInfo.getNameFromNetaId(_TnetaId[i]);
                }else {
                    optCode = _Topt[i] / 100;
                    if(_optInfo !=null){
                        int check=_optInfo.serchID(optCode);
                        if(check != -1){
                            optStr=_optInfo.getNameFromToppingId(optCode)+"/";
                        }
                    }
                    _TnetaName[i] =optStr + _netaInfo.getNameFromNetaId(_TnetaId[i]);
                }
                _TkaikeiCat[i] = _netaInfo.getKaikeiCatFromNetaId(_TnetaId[i]);
                if (_TkaikeiCat[i] > 0 && _TkaikeiCat[i] <= MAX_KAIKEI_RIREKI_COUNT && _Tcheak[i] == 1) {
                    cal[_TkaikeiCat[i]] = cal[_TkaikeiCat[i]] + _Tsara[i];
                    buf[_TkaikeiCat[i]].append(_TnetaName[i] + "(" + _Tsara[i] + ")");
                    //add 20180901
                }
            }
        }
        for (i = 0; i <MAX_KAIKEI_RIREKI_COUNT; i++) {//add 20180419
            if (cal[i] != 0) {
                sb.append(_netaInfo.getTkaikeiCatInfo(i)+","+ cal[i] +","+ buf[i]+":");
                _CalCount[i]=cal[i];
            }
            buf[i] = null;
        }
        return sb.toString();
    }
    //================================================
//あおいそ時 表示用2
//change 20180829 金額表記対応
//================================================
    public  int getCalCountSara (){
        return _CalCountSara;
    }
    public  void clearCalCountSara (){
        _CalCountSara=0;
    }
    public  int getCalCount (int x){
        if (x > 0 && x <= MAX_KAIKEI_RIREKI_COUNT) {
            return _CalCount[x];
        }else{
            return 0;
        }
    }



//================================================
//あおいそ時 表示用2
//change 20180829 金額表記対応
//================================================
    public String GetRirekiStrLocal4(netaInfo _netaInfo,optInfo _optInfo) {
        int _price=0;
        int i = 0;
        int cal[] = new int[MAX_KAIKEI_RIREKI_COUNT];//add 20180419
        StringBuffer buf[] = new StringBuffer[MAX_KAIKEI_RIREKI_COUNT];//add 20180419
        StringBuffer sb = new StringBuffer("");
        int optCode=0;
        String optStr="";
        _glog.log("GetRirekiStrLocal4 called");

        //saraの内訳をクリア
        _CalCountSaraStr=new StringBuilder();
        _CalCountSaraStr2=new StringBuilder();
        _CalCountSara=0;


        for (i = 0; i < MAX_KAIKEI_RIREKI_COUNT; i++) {//add 20180419
            cal[i] = 0;
            buf[i] = new StringBuffer("");
        }
        for (i = 0; i < MAX_T_RIREKI; i++) {
            if (_TnetaId[i] == 0) {
                break;
            } else {
                if(_Topt[i]==0){
                    _TnetaName[i] = _netaInfo.getNameFromNetaId(_TnetaId[i]);
                }else {
                    optCode = _Topt[i] / 100;
                    if(_optInfo !=null){
                        int check=_optInfo.serchID(optCode);
                        if(check != -1){
                            optStr=_optInfo.getNameFromToppingId(optCode)+"/";
                        }
                    }
                    _TnetaName[i] =optStr + _netaInfo.getNameFromNetaId(_TnetaId[i]);
                }
                _TkaikeiCat[i] = _netaInfo.getKaikeiCatFromNetaId(_TnetaId[i]);
                // if (_TkaikeiCat[i] > 0 && _TkaikeiCat[i] <= MAX_KAIKEI_RIREKI_COUNT && _Tcheak[i] == 1) { //add 20180419
                if (_TkaikeiCat[i] > 0 && _TkaikeiCat[i] <= MAX_KAIKEI_RIREKI_COUNT && _Tcheak[i] == 1) { //change 20180901
                    cal[_TkaikeiCat[i]] = cal[_TkaikeiCat[i]] + _Tsara[i];
                    buf[_TkaikeiCat[i]].append(_TnetaName[i] + "(" + _Tsara[i] + ")");
                }
                //100yen sara
                if (_TkaikeiCat[i] ==0 && _Tcheak[i] == 1) { //add 20180419
                    _CalCountSara = _CalCountSara + _Tsara[i];
                    _CalCountSaraStr.append(_TnetaName[i] + "(" + _Tsara[i] + ")");
                }
                //200yen sara
                if (_TkaikeiCat[i] ==1 && _Tcheak[i] == 1) { //add 20180419
                    _CalCountSara = _CalCountSara + _Tsara[i]*2;
                    _CalCountSaraStr2.append(_TnetaName[i] + "(" + _Tsara[i] + ")");
                }
            }
        }
        _glog.log("皿計:"+_CalCountSara + " 100:"+ _CalCountSaraStr.toString() + " 200:" +_CalCountSaraStr2.toString() );
        for (i = 0; i <MAX_KAIKEI_RIREKI_COUNT; i++) {//add 20180419
            //if (cal[i] != 0 ) {
            if (cal[i] != 0 && i != 0) {
                _price = _netaInfo.getTkaikeiCatPrice(i);
                _glog.log("GetRirekiStrLocal4:"+_netaInfo.getTkaikeiCatInfo(i)+" 個数:"+cal[i] +" 単価:"+_price + " 合計:" + (cal[i]*_price));
                //sb.append(_netaInfo.getTkaikeiCatInfo(i) + "," + cal[i] + "," + buf[i] + ":");
                sb.append(_netaInfo.getTkaikeiCatInfo(i) + "," + cal[i] + "," + buf[i] +","+ (cal[i]*_price)+":");
            }
            buf[i] = null;
        }
        return sb.toString();
    }

    //============================================
//解析
//============================================
    public void analizeNetaInfoLine(String line){

        _glog.log("analizeNetaInfoLine  called");
        String arr[] = null;
        //int _opt=0;

        if(line==null) {
            // Log.i(TAG, "ERR: kaikei_analizeNetaInfoLine  data null");
            _glog.log("ERR: kaikei_analizeNetaInfoLine  data null");
            return;
        }
        if (line.indexOf(",") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
            _glog.log( "ERR: kaikei_analizeNetaInfoLine  data err");
            return;
        }
        _glog.log("line:"+line);
        arr =line.split(",");
        int  count = arr.length;
        if(count < 10){
            //Log.i(TAG, "ERR: kaikei_analizeNetaInfoLine  data short:"+line);
            _glog.log( "ERR: kaikei_analizeNetaInfoLine  data short");
            return;
        }

        try {
            _TnetaId[_TdataCount] = Integer.parseInt(arr[5]);
            _Tsara[_TdataCount] =Integer.parseInt(arr[6]);
            _Topt[_TdataCount]  = Integer.parseInt(arr[7]);

            String arrTbuf[] = null;
            if ( arr[8]==null){
                _Tcheak[_TdataCount] = 0;
                _Ttime[_TdataCount] = "";
                return;
            }
            if (arr[8].indexOf(" ") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
                _Tcheak[_TdataCount] = 0;
                _Ttime[_TdataCount] = "";
                return;
            }
            arrTbuf = arr[8].split(" ");
            _Ttime[_TdataCount] =  arrTbuf[1];

            if(_Ttime[_TdataCount].equals(CHECK_TBUF)){//到着済の商品か確認（※確認ボタンでの運用のケースでは到着済を確認する必要があるが未実装）
                _Tcheak[_TdataCount] = 0;
                _Ttime[_TdataCount] = "";
            }else{
                _Tcheak[_TdataCount] = 1;
            }
        }catch (Exception e){
            Log.i(TAG, "ERR:analizeNetaInfoLine"+e.toString());
        }
    }

    //Integer.parseIntのラッパー
   /* private float ParseFloat(String s){
        float f =0;
        if(s != null && s != ""){
            try {
                f = Float.parseFloat(s);
            }catch (Exception e){
                _glog.log("ERR:ParseFloat:" + e.toString());
            }
        }
        return f;
    }*/
    //Integer.parseIntのラッパー
    private int ParseInt(String s){
        int i =0;
        // if(s != null && s != ""){
        if(s !=null && !s.equals("")){
            i = Integer.parseInt(s);
        }
        return i;
    }
    /**
     * 会計用履歴情報のセット
     *
     * @since 20160304
     */
    public void setTRirekiInfo(String str) {
        //Log.i(TAG, "会計画面用履歴:"+ str);
        Log.i(TAG, "会計画面用履歴 called");
        String arr[] = null;
        this.clearTdata();
        //add 2016 0510

        int i = 0;
        if (str == null) {
            Log.i(TAG, "ERR:setTRirekiInfo   data null");
            return;
        }
        //-------------------------------------------------
        if (str.indexOf("\n") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
            Log.i(TAG, "ERR: setRirekiInfo  data err:" + str);
            if (str.indexOf(",") == -1) {
                return;
            }else{
                arr=new String[1];
                arr[0]=str;
            }
        }else{
            arr = str.split("\n");
        }
        //-------------------------------------------------
        for (i = 0; i < arr.length; i++) {
            try {
                analizeNetaInfoLine(arr[i]);
                _TdataCount++;
                if (_TdataCount == MAX_T_RIREKI) {
                    return;
                }
            }catch (Exception e) {
                Log.i(TAG, "ERR:setRirekiInfo:"+e.toString());
                return;
            }

        }
    }
}








