package com.kura.andorder;

// 20220323 #39 ゲーム情報を保持するだけのクラス
public class saveGameInfo {

    private  logExtend _glog = null;
    private int _gGameCount = 0;
    private int _gGameWinCount = 0;

    private int _gGameCountSmart = 0;
    private int _gGameWinCountSmart = 0;

    /**
     * コンストラクタ
     */
    public saveGameInfo(logExtend obj){
        this._glog = obj;
    }

    /**
    * 通常のゲーム情報を加算 type:2 win  else: loose
    */
    public void IncGameInfo(int type){
      if(type == 2){
          _gGameWinCount++;
      }
        _gGameWinCount++;
    }

    /**
     * スマホのゲーム情報を加算 type:2 win  else: loose
     */
     public void setGameSmartInfo(int type){
         if(type == 2){
             _gGameWinCountSmart++;
         }
         _gGameWinCountSmart++;
    }

    /**
     * ゲームの合計数を返却
     */
    public int getGameWinSum(){
        return   _gGameWinCount +   _gGameWinCountSmart;
    }

    public void clearGameInfo(){
        _gGameCount = 0;
        _gGameWinCount = 0;
        _gGameCountSmart = 0;
        _gGameWinCountSmart = 0;
    }


}