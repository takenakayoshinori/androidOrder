package com.kura.andorder;

//import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class localStr {
    private static int MAX_LOCAL_STR_COUNT=200;
    private  logExtend _glog=null;
    private static int _LocalKey;
    // private static final int LOCAL_MAX_CATEGORY= 3;
    private static final int LOCAL_MAX_CATEGORY= 6;//for ver75
    private static int _LocalMax;//何種類の言語に対応するか
    private File _gFileDir;

    private static final String BR = System.getProperty("line.separator");
    private  static String[]_localStrTag = new String[MAX_LOCAL_STR_COUNT];
    private  static String[]_localStr= new String[MAX_LOCAL_STR_COUNT];



    public  localStr(File f,logExtend obj){
        this._gFileDir=f;
        _LocalKey=0;//母国語設定
        _LocalMax =  LOCAL_MAX_CATEGORY;//何種類の言語に対応するか
        _glog=obj;
    }

    //add 20170428 ver75
    public  localStr(int langCount,File f,logExtend obj){
        this._gFileDir=f;
        _LocalKey=0;//母国語設定
        if(langCount>0 && langCount<=LOCAL_MAX_CATEGORY) {
            _LocalMax = langCount;//何種類の言語に対応するか
        }else{
            _LocalMax = LOCAL_MAX_CATEGORY;//何種類の言語に対応するか
        }
        _glog=obj;
    }

/*=====================================================
up:2017 1103
aothor:ys
content:

====================================================*







/*=====================================================
up:2014 0512
aothor:ys
content:ローカライズ用のキーをゲット
====================================================*/

    //add 20170428 ver75
    public int setLcalMax(int langCount) {
        if (langCount > 0 && langCount <= LOCAL_MAX_CATEGORY) {
            _LocalMax = langCount;//何種類の言語に対応するか
        } else {
            _LocalMax = LOCAL_MAX_CATEGORY;//何種類の言語に対応するか
        }
        return  _LocalMax;
    }

    public int getLcalMax(){
        return  _LocalMax;
    }

    /*=====================================================
    up:2014 0512
    aothor:ys
    content:ローカライズ用のキーをゲット
    ====================================================*/
    public int getLcalKey(){
        return  _LocalKey;
    }
    /*====================================================
     up:2014 0512
     aothor:ys
     content:ローカライズ用のキーをセット
     ====================================================*/
    public void setLcalKey(int key){
        _LocalKey=key;
    }

    private int serchTag(String tag){
        int i=0;
        for(i=0;i<MAX_LOCAL_STR_COUNT;i++){
            if(tag.equals(_localStrTag[i])){//true
           /* _glog.log( "タグあったよ"+_localStrTag[i]);
            _glog.log( ">>"+i);*/
                return i;
            }
        }
        return -1;
    }
    /*====================================================
     up:2014 0512
     aothor:ys
     content:
    //ローカライズ用の文字列を返す。
    //（戻り値で文字列操作するケースでの処理落ち対策のため、nilのケースでも空白文字列を返す。）
     ====================================================*/
    public String LoStr(String tag){
        int x=0;
        x=serchTag(tag);
        if(x<0){
            return "";
        }else{
            return _localStr[x];
        }
    }
    /*=======================================
     up:2014 0512
     aothor:ys
     content:
    =========================================*/
    public void setLocalStrData(String fileName){




        int x=0;
        String arr[] = null;
        File file = new File(_gFileDir + "/" + fileName);
        String str = "";
        FileReader filereader = null;
        BufferedReader br = null;

        try {
            filereader = new FileReader(file);
            br = new BufferedReader(filereader);
            str = "";
            while (str != null) {
                str = br.readLine();//次の１行を読む
                if(str == null){
                /*if(br!=null) {
                    br.close();
                }
                if(filereader != null) {
                    filereader.close();
                }*/
                    break;
                }

                //  if (str.indexOf(",") != -1 ) {//文字列に区切り文字が入っていない場合はスキップする。
                if(str.contains(",")){//warning 内容に修正
                    //   if(str.charAt(0) =='#') {//先頭が'#'の場合はコメントとみなして無視
                    //   }else{
                    if(str.charAt(0) !='#') {//先頭が'#'の場合はコメントとみなして無視
                        arr = str.split(",");
                        if(arr.length >= 2+_LocalMax) {
                            x = Integer.parseInt(arr[0]);
                            if(x<0 || x>=MAX_LOCAL_STR_COUNT){
                                _glog.log("ERR:range:" + x);
                            }else {
                                _localStrTag[x]=new String(arr[1]);
                                //  if(arr[_LocalKey+2].indexOf("@")!= -1 ){
                                if(arr[_LocalKey+2].contains("@")){
                                    String temp=new String(arr[_LocalKey + 2].replaceAll("@@",BR));
                                    _localStr[x] = new String(temp.replaceAll("@",BR));
                                }else {
                                    _localStr[x] = new String(arr[_LocalKey + 2]);
                                }
                            }
                        }
                    }
                }
            }
            //br.close();
            //filereader.close();
   /* } catch (FileNotFoundException e) {
        _glog.log("ERR:setLocalStrData:" + e.toString());*/
        } catch (IOException e) {
            _glog.log("ERR:setLocalStrData:" + e.toString());
    /*} catch (Exception e) {
        _glog.log("ERR:setLocalStrData:" + e.toString());*/
        }finally {
            if(br != null) {
                try {
                    br.close();
                }catch (IOException e){
                    _glog.log("ERR:setTkaikeiCatInfo:" + e.toString());
                }
            }
            if(filereader != null) {
                try {
                    filereader.close();
                }catch (IOException e){
                    _glog.log("ERR:setTkaikeiCatInfo:" + e.toString());
                }
            }


        }
    }
    /*====================================================
 up:20160509
 aothor:ys
 content:ユニット名の多言語版を返す。
/
 ====================================================*/
    public String getUnitNameLocal(int x){
        // String str;
        switch (x){
            case 0:
                return this.LoStr("COMMON_UNIT_NAME1");
            case 1:
                return this.LoStr("COMMON_UNIT_NAME2");
            case 2:
                return this.LoStr("COMMON_UNIT_NAME3");
            case 3:
                return this.LoStr("COMMON_UNIT_NAME4");
        }
        return "";
    }

/****************************************************************
 *deleate 20181122
 *****************************************************************/
//Integer.parseIntのラッパー
    /*private int ParseInt(String s){
        int i =0;
        //if(s != null && s != ""){
        if(s !=null && !s.equals("")){
                i = Integer.parseInt(s);
        }
        return i;
    }*/
/****************************************************************
 *deleate 20181122
 ****************************************************************/
   /* private static String[] tokenToArgs(String value, String delimiter) {
        List<String> list = tokenToList(value, delimiter);
        return list.toArray(new String[list.size()]);
    }*/
    /****************************************************************
     *
     *****************************************************************/
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
