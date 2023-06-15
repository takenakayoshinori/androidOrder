package com.kura.andorder;
import android.content.Context;
//import android.provider.ContactsContract;
//import android.provider.Settings;
import android.util.Log;
import java.util.Locale;

//import java.io.File;


import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.util.Calendar;

public class logExtend {
    private final int MAX_LOG_LENGTH = 10000;
    private int LogLevel = 0;
    private int LogErrLevel = 0;
    private String _TAG = "KURA_D";
    private String _TAGE = "KURA_E";
    StringBuffer sb;
    StringBuffer sbErr;
    // private FileOutputStream _fileOutputstream;

    //add 20161103 add log taime
    private int _gT = 0;

    //add 20180620 for opt info
    // private String _gLogCusmorId="";//miseNUM_table_year_month_day_hour_min_sec
    private int _gLogCusmorId = 0;//miseNUM_table_year_month_day_hour_min_sec
    private int MAX_OPT_LOG_COUNT = 20;
    private int _gLogOptMiseCode = 0;
    private int _gLogOptTableNum = 0;
    private String[] _gLogOptTag;
    private String[] _gLogOptStr;

    //add 20161103 add log taime
    public void setLogTime(int t) {
        _gT = t;
    }
    //add 20180623 add log taime
    public void setTableNum(int val) {
        _gLogOptTableNum = val;
    }

    public void setMiseCode(int val) {
        _gLogOptMiseCode = val;
    }

//=================================
//コンストラクタ
//=================================
    public logExtend() {
        sb = new StringBuffer();
        sbErr = new StringBuffer();
        //add 20180622
        this.initLogOpt();

    }
    //=================================
// LogLevel0の場合はログのストリーミングはしない
//=================================
    public void setLogLevl(int val) {
        if (val == 0 || val == 1 || val == 2) {
            LogLevel = val;
        }
    }
    //=================================
//LogErrLevel0の場合はログのストリーミングはしない
//=================================
    public void setLogErrLevl(int val) {
        if (val == 0 || val == 1 || val == 2) {
            LogErrLevel = val;
        }
    }
    //=================================
//logのブッファを文字列に変換
//=================================
    public String GetLogBufStr() {
        String str = sb.toString();
        sb.delete(0, sb.length());
        if (str.equals("")) {
            return null;
        }

        return str;
    }
    //=================================
//logのブッファを文字列に変換
//=================================
    public String GetLogErrBufStr() {
        return sbErr.toString();
    }
    //=================================
//log出力
//=================================
    void log(String str) {
        switch (LogLevel) {
            case 0:
                break;
            case 1:
                sb.append(_gT + ">" + str + "\n");
                if (sb.length() > MAX_LOG_LENGTH) {
                    sb.delete(0, sb.length());
                }
                break;
            case 2:
                sb.append(_gT + ">" + str + "\n");
                if (sb.length() > MAX_LOG_LENGTH) {
                    sb.delete(0, sb.length());
                }
                Log.i(_TAG, _gT + ">" + str);
                break;
        }
    }
    //=================================
//err log出力
//=================================
    void logE(String str) {
        /*switch (LogErrLevel) {
            case 0:
                break;
            case 1:
                sbErr.append(str + "\n");
                if (sbErr.length() > MAX_LOG_LENGTH) {
                    sb.delete(0, sbErr.length());
                }
                break;
            case 2:
                sbErr.append(str + "\n");
                if (sbErr.length() > MAX_LOG_LENGTH) {
                    sbErr.delete(0, sbErr.length());
                }
                Log.i(_TAGE, str);
                break;
        }*/
    }

    //=================================
//ver 80 動作記録
    //log集約につき処理しない
//=================================
    void logOut(Context context, String str) {
      /*  StringBuffer _sb = new StringBuffer("");
        //ver83 20170614
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        _sb.append(calendar.get(Calendar.YEAR) + "_");
        _sb.append(calendar.get(Calendar.MONTH) + 1 + "_");
        _sb.append(calendar.get(Calendar.DAY_OF_MONTH) + "_");
        _sb.append(calendar.get(Calendar.HOUR_OF_DAY) + "_");
        _sb.append(calendar.get(Calendar.MINUTE) + "_");
        _sb.append(calendar.get(Calendar.SECOND) + "_");
        _sb.append(_gT + ":" + str + "\n");
        try {
            _fileOutputstream = context.openFileOutput("AndOrder_log.txt", context.MODE_APPEND);
            _fileOutputstream.write(_sb.toString().getBytes());
            _fileOutputstream.close();
            _sb = null;
        } catch (FileNotFoundException e) {
            this.log("err logOut" + e.toString());
        } catch (IOException e) {
            this.log("err logOut" + e.toString());
        }*/
    }
    //=================================
//20180620 操作ログ取得用
//=================================
    public int initLogOpt() {
        int i = 0;
        _gLogOptTag = new String[MAX_OPT_LOG_COUNT];
        _gLogOptStr = new String[MAX_OPT_LOG_COUNT];
        for (i = 0; i < MAX_OPT_LOG_COUNT; i++) {
            _gLogOptTag[i] = "";
            _gLogOptStr[i] = "";
        }
        return 0;
    }
    //=================================
//20180620 json形式でログを作製
//  String obj は tag1:val1,tag2:val2　の形式で渡す。
//=================================
    public int LogJson(Context context, String obj) {
        int i = 0;
        String[] arr = obj.split(",");
        String[] arr2;
        String str = "";
        FileOutputStream _fileOutputstream=null;

        this.clearLogOpt();
        for (i = 0; i < arr.length; i++) {
            arr2 = arr[i].split(":");
            if(arr2.length>1) {
                _gLogOptTag[i] = arr2[0];
                _gLogOptStr[i] = arr2[1];
            }else{
                this.log("err LogJson break:" + arr2.length);
                break;
            }
            if (i >= MAX_OPT_LOG_COUNT) {
                break;
            }
        }
        str = getLogOptStr();
        try {
            //ファイル書き込み
            _fileOutputstream = context.openFileOutput("LogJson.txt", Context.MODE_APPEND);
            if( _fileOutputstream != null) {
                _fileOutputstream.write(str.getBytes());
            }
        /*} catch (FileNotFoundException e) {
            this.log("err LogJson:" + e.toString());*/
        } catch (IOException e) {
            this.log("err LogJson:" + e.toString());
        }finally {
            try {
                _fileOutputstream.close();
            }catch (IOException e) {
            }
        }
        //this.log(str);
        return 0;
    }
    //=================================
//20180620 json形式でログを作製
//  String obj は tag1:val1,tag2:val2　の形式で渡す。
//=================================
    public int setLogOpt(String tag, String val) {
        int i = 0;
        for (i = 0; i < MAX_OPT_LOG_COUNT; i++) {
            if (_gLogOptTag[i].equals("")) {
                _gLogOptTag[i] = tag;
                _gLogOptStr[i] = val;
                // this.log(_gLogOptTag[i] + "," + _gLogOptStr[i] + "i:" + i);
                return 1;
            }
        }
        return -1;
    }
    //=================================
    //20180620
//=================================
    public int clearLogOpt() {
        int i = 0;
        for (i = 0; i < MAX_OPT_LOG_COUNT; i++) {
            _gLogOptTag[i] = "";
            _gLogOptStr[i] = "";
        }
        return 0;
    }

    //=================================
    //20180620
    //Customor IDは touch_ftp_managerから送信するように改め
//=================================
    public String getLogOptStr() {
        StringBuffer _sb = new StringBuffer("");
        int i = 0;

        _sb.append("{ \"log_time\":\"");
        _sb.append(getTimeFormt());
        _sb.append("\",\"mise_code\":\"");
        _sb.append(_gLogOptMiseCode);
        _sb.append("\",\"table\":\"");
        _sb.append(_gLogOptTableNum);
        _sb.append("\",\"t_id\":\"");
        _sb.append(_gLogCusmorId);
        _sb.append("\",");
        //this.log(sb.toString());
        for (i = 0; i < MAX_OPT_LOG_COUNT; i++) {
            if (!_gLogOptTag[i].equals("")) {
                _sb.append("\"");
                _sb.append(_gLogOptTag[i]);
                _sb.append("\":\"");
                _sb.append(_gLogOptStr[i]);
                _sb.append("\",");
            }
        }
        int index = _sb.lastIndexOf(",");
        _sb.deleteCharAt(index);
        _sb.append(" }");
        _sb.append(System.getProperty("line.separator"));
        return _sb.toString();
    }

    //=================================
    //20180620
//=================================
    public void clear_CusmorId(){
        _gLogCusmorId =0;
    }
    public void set_CusmorId(int _id){
        _gLogCusmorId = _id;
    }
    public int get_CusmorId(){
        return _gLogCusmorId;
    }


    public String getTimeFormt() {
        StringBuffer _sb =new StringBuffer("");
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        _sb.append(String.format(Locale.JAPAN,"%04d-",calendar.get(Calendar.YEAR)));
        _sb.append(String.format(Locale.JAPAN,"%02d-",calendar.get(Calendar.MONTH)+1));
        _sb.append(String.format(Locale.JAPAN,"%02d ",calendar.get(Calendar.DAY_OF_MONTH)));
        _sb.append(String.format(Locale.JAPAN,"%02d:",calendar.get(Calendar.HOUR_OF_DAY)));
        _sb.append(String.format(Locale.JAPAN,"%02d:",calendar.get(Calendar.MINUTE)));
        _sb.append(String.format(Locale.JAPAN,"%02d",calendar.get(Calendar.SECOND)));
        return _sb.toString();
    }

    // add 20201226
    public int getDayFormt() {
        StringBuffer _sb =new StringBuffer("");
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        _sb.append(String.format(Locale.JAPAN,"%02d",calendar.get(Calendar.MONTH)+1));
        _sb.append(String.format(Locale.JAPAN,"%02d ",calendar.get(Calendar.DAY_OF_MONTH)));
        int dayInt = 0;
        try{
            dayInt = Integer.parseInt(_sb.toString().trim(),10);
        }catch (Exception e){
            dayInt = 1;
        }
        return dayInt;
    }


}
