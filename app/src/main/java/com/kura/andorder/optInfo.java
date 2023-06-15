package com.kura.andorder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kura on 2017/11/03.
 */

public class optInfo {

    private  logExtend _glog=null;
    private File _gFileDir;
    private static final String BR = System.getProperty("line.separator");

    private static int MAX_OPT_COUNT=240;//OPTIONのMAX

    private static int _optType[];
    private static String _optTxt0[];//OPTION0 対応のテキスト
    private static String _optTxt1[];//OPTION1 対応のテキスト
    private static String _optTxt2[];//OPTION1 対応のテキスト



    //add 2180103 from xcode

    private final int TOPPING_LANG_CATEGORY = 10*2; //最大10カ国  //０：キー名　1:フラグ、2：日本語、3＝英語、4＝tw,5=？　　客席用１０言語の次に厨房用１０言語がセットされている
    //  private final int TOPPING_MAX= 100*2;   //トッピング種類　最大値
    private final int TOPPING_NETA_MAX= 240;
    private static int _id_topping [];   //id
    private static int _flag_topping [];   //表示フラグ。０＝表示しない　１＝表示する
    private static String _str_topping[];
    private static int _count_topping;

    private static int _LocalKey;
    //add 2180103 from xcode

    /****************************************************************
     * コンストラクタ
     *****************************************************************/
    public optInfo(File f,logExtend obj){
        this._gFileDir = f;
        _glog=obj;
        _LocalKey=0;
        this.initOptInfo();
        this.clearToppingInfo();
    }
    //==============================================
    //add 2180103 from xcode
//toppingidから名称を返す
//==============================================
    public String printOptInfo()
    {
        StringBuffer sb = new StringBuffer("");
        int i=0;
        //sb.append("---printOptInfo¥n---");
        for(i=0;i<MAX_OPT_COUNT;i++) {
            sb.append("id:"+_id_topping [i]);
            sb.append(" flag:"+_flag_topping [i]);
            sb.append(" str:"+_str_topping[i]);
            sb.append("\n");
        }
        return sb.toString();

    }

    /****************************************************************
     //add 2180103 from xcode
     *****************************************************************/
    public void setLocalKey(int key){
        if(key <0 || key >= 6){
            _glog.log("optInfo err:setLocalKey key:"+key);
        }else{
            _LocalKey=key;
        }
    }
    /****************************************************************
     //add 2180103 from xcode
     *****************************************************************/
    public int serchID(int id){

        int i;
        for(i=0;i<MAX_OPT_COUNT;i++){
            if(id == _id_topping[i]){
                return i;
            }
        }
        return -1;
    }
    //==============================================
    //add 2180103 from xcode
//toppingidから名称を返す
//==============================================
    public String getNameFromToppingId(int toppingid)
    {
        int i =serchID(toppingid);
        if(i==-1){
            return "err:" +toppingid;
        }
        return _str_topping[i];
    }
    //==============================================
    //add 2180103 from xcode
//toppingidから名称を返す
//==============================================
    public int getFlagFromToppingId(int toppingid)
    {
        int i =serchID(toppingid);
        if(i==-1){
            return -1;
        }
        return _flag_topping[i];
    }
    //================================================================
//add 2180103 from xcode
//ネタ情報のクリア
//================================================================
    public void clearToppingInfo(){
        int i;
        _count_topping=0;
        for(i=0;i<MAX_OPT_COUNT;i++){
            _id_topping[i]=0;
            _flag_topping[i]=0;
            _str_topping[i]="";
        }
    }
    //================================================================
//Topping情報のセット
//エラー処理確認の事
//================================================================
    public void setToppingInfo(String fname){
        File file = new File(_gFileDir + "/" + fname);

        String str = "";
        // String str2 = "";
        String arr[] ;
        int ar_cnt=0;

        _count_topping=0;
        _glog.log("setToppingInfo called:"+fname);
        try {
            if (file.exists()==false){
                _glog.log("err:setToppingInfo not exit fail:"+fname);
                return;
            }
            FileReader filereader = new FileReader(file);
            BufferedReader br = new BufferedReader(filereader);
            str = "";

            //一行読み込み
            while (str != null) {
                //次の１行を読む
                str = br.readLine();
                if (str == null) {
                    br.close();
                    filereader.close();
                    return;
                }
                //文字列に区切り文字が入っていない場合はスキップする。
                if (str.indexOf(",") != -1 ) {
                    //_glog.log("setToppingInfo:"+str);
                    arr= tokenToArgs(str, ",");
                    //arr = str.split(",");
                    ar_cnt=arr.length;
                    //_glog.log("arr_count:"+arr.length);

                    if(ar_cnt>=22){
                        _id_topping[_count_topping] = Integer.parseInt(arr[0]);//id
                        _flag_topping[_count_topping] = Integer.parseInt(arr[1]);//flag
                        _str_topping[_count_topping] =arr[2+_LocalKey];
                        _count_topping++;
                    }else{
                        _glog.log("err:setToppingInfo arry short:"+str);
                    }

                }
            }
        } catch (FileNotFoundException e) {
            _glog.log("ERR:setLocalNetaName:" + e.toString());
        } catch (IOException e) {
            _glog.log("ERR:setLocalNetaName:" + e.toString());
        } catch (Exception e) {
            _glog.log("ERR:setLocalNetaName:" + e.toString());
        }

    }



    /*=====================================================
    up:2017 1002
    ver 100
    aothor:ys
    content:neta opt info 追加。（シャリ選択、トッピング選択用）
    ====================================================*/
    public  void initOptInfo() {
        _glog.log("initOptInfo called");
        /*_optTxt0= new String[MAX_OPT_COUNT];
        _optTxt1= new String[MAX_OPT_COUNT];
        _optTxt2= new String[MAX_OPT_COUNT];
        _optType = new int[MAX_OPT_COUNT];*/

        _id_topping= new int[MAX_OPT_COUNT];
        _flag_topping = new int[MAX_OPT_COUNT];
        _str_topping = new String[MAX_OPT_COUNT];
    }

/*=====================================================
    up:2017 1002
    ver 100
    aothor:ys
    content:neta opt info 追加。（シャリ選択、トッピング選択用）
====================================================*/
/*public  int clearOptInfo(){
    int i=0;
    for(i=0;i<MAX_OPT_COUNT;i++){
        /*_optTxt0[i]="opt_0"+i;
        _optTxt1[i]="opt_1"+i;
        _optTxt2[i]=null;
        _optType[i]=0;
    }
    return 0;
}*/

    /*=====================================================
          up:2017 1002
          ver 100
           aothor:ys
          content:neta opt info 追加。（トッピング選択用）
    ====================================================*/
    public  int getNetaOptInfoType(int x){

        if(x>0  &&  x>=MAX_OPT_COUNT){
            _glog.log("ERR:getNetaOptInfoStr OVER MAX_OPT_COUNT");
            return 0;
        }
        return _optType[x];
    }
/*=====================================================
  up:2017 1002
  ver 100
   aothor:ys
  content:neta opt info 追加。（トッピング選択用）
====================================================*/
/*public  String getNetaOptInfoStr(int _cat,int x){

    //_glog.log("getNetaOptInfoStr cat:"+_cat +" code:" + x);
        if(x>0  &&  x>=MAX_OPT_COUNT){
            _glog.log("ERR:getNetaOptInfoStr OVER MAX_OPT_COUNT");
            return "";
        }

        if(_cat == 1){

            return _optTxt0[x];
        }else if(_cat == 2){
           // _glog.log("cat2 >"+_optTxt1[x]);
            return _optTxt1[x];
        }else if(_cat == 3) {
            return _optTxt2[x];
        }else{
            return "";
        }
}*/

/*=====================================================
    up:2017 1002
    ver 100
    aothor:ys
    content:neta opt info 追加。（シャリ選択、トッピング選択用）
====================================================*/
  /*  public  int setNetaOptInfo(){
        int i = 0;
        int cnt=0;
        int _cat=0;

        _glog.log("setNetaOptInfo called");

        File subfile = new File(_gFileDir + "/upload/OptInfo.csv");
        try {
            FileInputStream in1 = new FileInputStream(subfile);
            InputStreamReader in2 = new InputStreamReader(in1, "UTF8");
            BufferedReader br = new BufferedReader(in2);
            String line = "";
            while ((line = br.readLine()) != null) {//

                _glog.log("--------------------"+line);
                if (line.indexOf(",") != -1) {//文字列に区切り文字が入っていない場合はスキップする。
                    String[] st = tokenToArgs(line, ",");

                    cnt = st.length;
                    _glog.log("setNetaOptInfo cnt--->:"+cnt);
                    _glog.log("0 >>"+st[0]);
                    _glog.log("1 >>"+st[1]);
                    _glog.log("2 >>"+st[2]);
                    _glog.log("3 >>"+st[3]);
                    _cat = Integer.parseInt(st[0]);
                    i++;
                }
            }
            in1.close();
            in2.close();
            br.close();
        } catch (FileNotFoundException e) {
            _glog.log("setNetaOptInfo ERR:"+e.toString());
        } catch (IOException e) {
            _glog.log("setNetaOptInfo ERR:"+e.toString());
        }


        return 0;
    }*/

    /****************************************************************
     * util
     *****************************************************************/
//Integer.parseIntのラッパー
    private int ParseInt(String s){
        int i =0;
        // if(s != null && s != ""){
        if(s !=null && !s.equals("")){
            i = Integer.parseInt(s);
        }
        return i;
    }

    private static String[] tokenToArgs(String value, String delimiter) {
        List<String> list = tokenToList(value, delimiter);
        return list.toArray(new String[list.size()]);
    }

    private static List<String> tokenToList(String value, String delimiter) {
        // 分割した文字を格納する変数
        List<String> list = new ArrayList<String>();
        int i = 0;
        int j = value.indexOf(delimiter);
        for (int h = 0; j >= 0; h++) {
            list.add(value.substring(i, j));
            i = j + 1;
            j = value.indexOf(delimiter, i);
        }
        list.add(value.substring(i));
        return list;
    }







}
