package com.kura.andorder;

import android.util.Log;

/**
 * Created by kura on 2016/03/09.
 */
public class rirekiInfo {

    //private String TAG = "USER LOG";
    private  logExtend _glog=null;

    private final int MAX_RIREKI = 100;
    private final String CHECK_TBUF = "00:00:00";
    private int _Rcheak[] = new int[MAX_RIREKI];
    private int _RnetaId[] = new int[MAX_RIREKI];
    //int _Rname[MAX_RIREKI];
    private String _Rtime[] = new String[MAX_RIREKI];
    private String _Rsara[] = new String[MAX_RIREKI];
    private int _Rwasabi[] = new int[MAX_RIREKI];
    private int _RdataCount;
    private int _Rpage=0;

    private String RCHEAK_STR[] = new String[2];
    private String RWASABI_STR[] = new String[2];


    //add 20171109 option選択商品
    private int _Ropt[] = new int[MAX_RIREKI];

    /**
     * コンストラクタ
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    public rirekiInfo(logExtend obj){
        this.clearRdata();
        this._glog=obj;
    }
    /**
     * Get
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    public int getRirekiPage() {
        return _Rpage;
    }

    public int  getRirekidataCount(){
        return _RdataCount;
    }

    public int getNetaId(int val) {
        return _RnetaId[val];
    }

    public int getRcheak(int val) {
        return _Rcheak[val];
    }
    public String getRcheakStr(int val) {

        if(_Rcheak[val]==0 ||_Rcheak[val]==1){
            return RCHEAK_STR[_Rcheak[val]];
        }else{
            return "";
        }

    }

    public int getRnetaId(int val) {
        return _RnetaId[val];
    }

    public String _Rtime(int val) {
        return _Rtime[val];
    }

    public String getRsara(int val) {
        return _Rsara[val];
    }

    public int getRwasabi(int val) {
        return _Rwasabi[val];
    }

    public int getRopt(int val) {
        return _Ropt[val];
    }

    public  String  getRCHEAK_STR(int val) {
        if(val == 1 || val ==0){
            return getRCHEAK_STR(val);
        }else{
            return "";
        }
    }

    public  String getRWASABI_STR(int val) {
        if(val == 1 || val ==0){
            return getRWASABI_STR(val);
        }else{
            return "";
        }
    }



    /**
     *
     * @author ys
     *@since 20160304
     *@version 20160304
     */

    public void clearRdata(){
        int i=0;
        for(i=0;i<MAX_RIREKI;i++){
            _Rcheak[i] = 0;
            _RnetaId[i]= 0;
            _Rtime[i]= "";
            _Rsara[i]= "";
            _Rwasabi[i]=0;

            _Ropt[i]=0;
        }
    }
    /**
     *
     * @author ys
     *@since 20160304
     *@version 20160304
     */
    public void set_STRING(){

   /* RCHEAK_STR[0] = @"未";
    RCHEAK_STR[1] = @"済";
    RWASABI_STR[0] =@"無";
    RWASABI_STR[1] =@"有";*/
        //外国語時の対応
        RCHEAK_STR[0] = " ";
        RCHEAK_STR[1] = "○";
        RWASABI_STR[0] ="　";
        RWASABI_STR[1] ="○";
    }
    /**
     *
     * @author ys
     *@since 20160304
     *@version 20160304
     */
    public void RpageChange(int vector) {

        int maxPage = _RdataCount / 10;
        int buf = _Rpage;

        if (vector == 0) {
            buf--;
            if (buf >= 0) {
                _Rpage = buf;
            }
        } else {
            buf++;
            if (buf <= maxPage) {
                _Rpage = buf;
            }
        }
    }

    /**
     *解析
     * @author ys
     *@since 20160304
     *@version 20160304
     */
    public void analizeNetaInfoLine(String line){

        // _glog.log("analizeNetaInfoLine called >>"+line);

        String arr[] = null;
        if(line == null) {
            _glog.log("ERR: analizeNetaInfoLine  data null");
            return;
        }
        if (line.indexOf(",") == -1) {// 文字列に区切り文字が入っていない場合はスキップする。
            _glog.log("ERR: analizeNetaInfoLine  data err");
            return;
        }
        arr = line.split(",");
        int  count = arr.length;
        if(count < 10){
            _glog.log("ERR: analizeNetaInfoLine  data short:" + line);
            return;
        }

        try {

            // _RnetaId[_RdataCount] = Integer.parseInt(arr[5]);
            _RnetaId[_RdataCount] = this.ParseInt(arr[5]);
            _Rsara[_RdataCount] = arr[6];
            //_Rwasabi[_RdataCount] = Integer.parseInt(arr[7]);
            _Rwasabi[_RdataCount] =  this.ParseInt(arr[7]);


            //add 20171109 for opt
            _Ropt[_RdataCount] =_Rwasabi[_RdataCount] / 100;


            String arrTbuf[] = null;
            if ( arr[8] == null){
                _Rcheak[_RdataCount] = 0;
                _Rtime[_RdataCount] = "";
                return;
            }
            if (arr[8].indexOf(" ") == -1) { // 文字列に区切り文字が入っていない場合はスキップする。
                _Rcheak[_RdataCount] = 0;
                _Rtime[_RdataCount] = "";
                return;
            }
            arrTbuf = arr[8].split(" ");
            _Rtime[_RdataCount] =  arrTbuf[1]; // スペースで分解


            if(_Rtime[_RdataCount].equals(CHECK_TBUF)){// 到着済の商品か確認（※確認ボタンでの運用のケースでは到着済を確認する必要があるが未実装）
                _Rcheak[_RdataCount] = 0;
                _Rtime[_RdataCount] = "";
            }else{
                _Rcheak[_RdataCount] = 1;
            }

            // _glog.log("===========================>> " + _RnetaId[_RdataCount]  );

        }catch (Exception e){
            _glog.log("ERR:analizeNetaInfoLine"+e.toString());
        }

    }

    /**
     *履歴情報のセット
     * @author ys
     *@since 20160304
     *@version 20160304
     */
    public void setRirekiInfo(String str){

        _glog.log("setRirekiInfo called:"+str);
        String arr[] = null;
        int i=0;
        if (str == null) {
            _glog.log("ERR: setRirekiInfo  data null");
            return;
        }
        if (str.indexOf("\n") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
            _glog.log("ERR: setRirekiInfo  data err");
            return;
        }
        _RdataCount = 0;
        _Rpage = 0;
        clearRdata();
        set_STRING();
        arr =str.split("\n");

        for (i = 0; i < arr.length; i++) {
            try {
                analizeNetaInfoLine(arr[i]);
                _RdataCount++;
                if (_RdataCount == MAX_RIREKI) {
                    return;
                }
            }catch (Exception e){
                _glog.log("ERR:setRirekiInfo:" + e.toString());
                return;
            }
        }



    }


    /****************************************************************
     * util
     *****************************************************************/
//Integer.parseIntのラッパー
    private int ParseInt(String s){
        int i =0;
        //if(s != null && s != ""){
        if(s !=null && !s.equals("")){
            i = Integer.parseInt(s);
        }
        return i;
    }














}
