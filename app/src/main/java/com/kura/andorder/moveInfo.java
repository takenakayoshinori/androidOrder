package com.kura.andorder;

import android.util.Log;

import java.io.File;

/**
 * Created by kura on 2016/03/14.
 */
public class moveInfo {
    private final int GAME_WIN=2;
    private final int GAME_LOOSE=1;
    private final String GAME_WIN_STATE_NAME="win";
    private final String GAME_LOOSE_STATE_NAME="los";

    //  private final  String _TAG = "USER_LOG";
    private int  MAX_MOVE_GAME=10;//win lose　各１０パターン
    private String _currentGamePath=null;

    private int  _gWinDougaPlayCount=0;
    private int  _gLooseDougaPlayCount=0;

    private String _gHomeDir;

    private  logExtend _glog=null;

    /**
     * moveInfo
     */
    public  moveInfo(logExtend obj){
        _glog=obj;
    }
    /**
     * moveInfo
     */
    public void  setHomeDir(String buf) {
        _gHomeDir = buf;
    }
    public void  ClearPlayCount() {
        _gLooseDougaPlayCount=0;
        _gWinDougaPlayCount=0;
    }
    /**
     * moveInfo
     */
    public int get_gLooseDougaPlayCount() {
        return _gLooseDougaPlayCount;
    }
    public int get_gWinDougaPlayCount(){
        return _gWinDougaPlayCount;
    }


    /**
     * add 20190319
     * スマホオーダーで指定番号でゲームを再生する機能で使用する
     */
    public int set_LooseDougaPlayCount(int cnt) {
        String cat_name = _gHomeDir + GAME_LOOSE_STATE_NAME;
        String f_name = cat_name + "_" + cnt + "_Game.mp4";
        File  gameFile = new File(f_name);
        //dougaファイルが存在するしたら動画番号を変更。無ければ変更しない。
        if (gameFile.exists()) {
            _gLooseDougaPlayCount=cnt;
        }
        _glog.log("set_gLooseDougaPlayCount called cnt:" +cnt+ " loosecnt:"+_gLooseDougaPlayCount);
        return _gLooseDougaPlayCount;
    }

    public int set_WinDougaPlayCount(int cnt){
        String cat_name = _gHomeDir + GAME_WIN_STATE_NAME;
        String f_name = cat_name + "_" + cnt + "_Game.mp4";
        File  gameFile = new File(f_name);
        //dougaファイルが存在するしたら動画番号を変更。無ければ変更しない。
        if (gameFile.exists()) {
            _gWinDougaPlayCount=cnt;
        }
        _glog.log("set_gWinDougaPlayCount called cnt:" +cnt+ " wincnt:"+_gWinDougaPlayCount);
        return _gWinDougaPlayCount;
    }



    /**
     *  動画の名称を確認
     */
    public String get_GameDougaName(int state){
        String f_name="";
        String cat_name;
        int cnt=0;
        int i=0;
        int cc=0;
        File gameFile;

        try {
            if (state == GAME_WIN) {//win
                cat_name = _gHomeDir + GAME_WIN_STATE_NAME;
                cnt = _gWinDougaPlayCount;
            } else {//loose
                cat_name = _gHomeDir + GAME_LOOSE_STATE_NAME;
                cnt = _gLooseDougaPlayCount;
            }

            _glog.log("CAT:" + cat_name);

            cc = cnt;
            for (i = cnt; i < 20 + cnt + 1; i++) {
                f_name = cat_name + "_" + cc + "_Game.mp4";
                //_glog.log("get_GameDougaName:" + f_name);
                gameFile = new File(f_name);
                if (gameFile.exists() == true) {
                    _glog.log("ゲームファイル発見:" + f_name);
                    break;
                } else {
                    cc++;
                    if (cc > 20) {
                        cc = 1;
                    }
                }
            }
            //add for test 20160603
            if(i == 20 + cnt + 1){
                _glog.log("ゲームファイル無かった:" + f_name);
                f_name = cat_name + "_" + 1 + "_Game.mp4";
            }
        }catch (Exception e){
            _glog.log("ERR get_GameDougaName:" + e.toString());
        }
        // ファイルが存在しない場合はデフォルトファイルの名称を返す？

        cc++;
        if (cc > 20) {
            cc = 1;
        }
        if (state == GAME_WIN) {//win
            _gWinDougaPlayCount = cc;
        } else {//loose
            _gLooseDougaPlayCount = cc;
        }
        return f_name;
    }

    /**
     * 再生するｽｸﾘｰﾝｾｰﾊﾞｰの名前を取得
     */
    public String getScreenMoveStr(int cnt){

        String f_name="";
        File gameFile;
        f_name =_gHomeDir + "Screen0" + cnt + ".mp4";
        _glog.log("getScreenMoveStr dir >" +_gHomeDir);

        gameFile = new File(f_name);
        try{
            if (gameFile.exists() == true) {
                // _glog.log("ファイル発見:" + f_name);
                return  f_name;
            }
            //change 20200707
            // f_name ="Screen01.mp4";
            f_name =_gHomeDir + "Screen0" + 1 + ".mp4";
            gameFile = new File(f_name);
            if (gameFile.exists() == true) {
                // _glog.log("ファイル発見:" + f_name);
                return  f_name;
            } else {
                _glog.log("getScreenMoveStr ｽｸﾘｰﾝｾｰﾊﾞｰ動画がない");
                return null;
            }

        }catch (Exception e){
            _glog.log("ERR get_GameDougaName:" + e.toString());
        }
        return  null;
    }
    /**
     * 再生するCMｰの名前を取得
     */
    public String getScreenMoveStrCm(int cnt){
        String f_name="";
        File gameFile;
        f_name =_gHomeDir + "Cm_" + cnt + ".mp4";
        _glog.log(" getScreenMoveStrCm dir >" +_gHomeDir);

        gameFile = new File(f_name);
        try{
            if (gameFile.exists() == true) {
                _glog.log("ファイル発見:" + f_name);
                return  f_name;
            }

            f_name ="Cm_1.mp4";
            gameFile = new File(f_name);
            if (gameFile.exists() == true) {
                _glog.log("ファイル発見:" + f_name);
                return  f_name;
            } else {
                _glog.log("CM動画がない"+ f_name );
                return null;
            }

        }catch (Exception e){
            _glog.log("ERR getScreenMoveStrCm:" + e.toString());
        }
        return  null;
    }

    /**
     * 再生するｽｸﾘｰﾝｾｰﾊﾞｰの名前を取得
     */
    public String getOpenningMoveName(int cnt){
        String f_name="";
        File gameFile;
        f_name =_gHomeDir + "Open_" + cnt + ".mp4";
        gameFile = new File(f_name);
        try{
            if (gameFile.exists() == true) {
                return  f_name;
            }

            //f_name ="Screen01.mp4";
            f_name =_gHomeDir + "Screen0" + 1 + ".mp4";
            gameFile = new File(f_name);
            if (gameFile.exists() == true) {
                return  f_name;
            } else {
                _glog.log("getOpenningMoveName 動画がない");
                return null;
            }

        }catch (Exception e){
            _glog.log("ERR getOpenningMoveName:" + e.toString());
        }
        return  null;
    }

/*
public int setCurrentGame(int state,int cnt){
        int i;
        String cat;
        //Boolean res;

        if(state == 2){
        cat ="win";
        }else if(state == 1){
        cat ="los";
        }else{
        _currentGamePath = null;
        }

        for(i=cnt;i<MAX_MOVE_GAME+cnt+1;i++){
        //_currentGamePath =[FileUtil filePathEithNamge:[NSString stringWithFormat:@"douga/%@_%d_Game.mp4",cat,cnt]];
       // res=[FileUtil fileExistsAtPath:_currentGamePath];
        //NSLog(@"i%d,cnt%d  moviePath %@",i,cnt, _currentGamePath );
        }
//---ファイルが存在しない場合はデフォルとゲーム
       // _currentGamePath  =[[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"%@_%d_Game",cat,0] ofType:@"mp4"];
        return 0;

        }*/
}
