package com.kura.andorder;

import android.util.Log;

/**
 * Created by kura on 2016/03/07.
 */
public class orderUnderInfo {

    // private String TAG = "USER LOG";
    private final int MAX_ORDERINFO_BUFFER= 30;//１回の到着数がこの数を上回るとバグる。
    private final int  MAX_ARAIVEINFO_BUFFER= 30;//１回の到着数がこの数を上回るとバグる。

    // private final int  TICK_TO_SECOND= 2;//tickの周期を０．５Mにした場合
    private final int  TICK_TO_SECOND= 1;//呼び出し側で1秒で呼ぶように変更

    private int _orderId[] = new int[MAX_ORDERINFO_BUFFER];
    private int _orderNetaId[] = new int[MAX_ORDERINFO_BUFFER];
    private int _orderSara[] = new int[MAX_ORDERINFO_BUFFER];
    private int _orderWasabi[] = new int[MAX_ORDERINFO_BUFFER];
    private int _orderCnt[] = new int[MAX_ORDERINFO_BUFFER];

    private int _orderAriveId[] = new int[MAX_ORDERINFO_BUFFER];
    private int _orderAriveNetaId[] = new int[MAX_ORDERINFO_BUFFER];
    private int _orderAriveSara[] = new int[MAX_ORDERINFO_BUFFER];
    //int _orderWasabi[MAX_ORDERINFO_BUFFER];
    private int _orderAriveCnt[] = new int[MAX_ORDERINFO_BUFFER];

    private  logExtend _glog=null;

    // TODO　add 20210817 原宿店屋台対応 屋台村対応商品情報を追加するために使用
    // private int _addFoodStandFlag[] = new int[MAX_ORDERINFO_BUFFER];



    public orderUnderInfo(logExtend obj) {
        this._glog=obj;
    }


// TODO　add 20210817 原宿店屋台対応 屋台村対応商品情報を追加するために使用
//　========================================================================
//　既存の処理の変更範囲を限定するため、後から屋台村対応商品の情報を追加
//　========================================================================
    public void setFoodStanddispCount (int x, int dispCount) {
        _glog.log("setFoodStanddispCount x:"+ x + " disp:" +  dispCount);
        // _addFoodStandFlag[x] = dispCount;
        _orderAriveCnt[x] = dispCount;
    }

// TODO　add 20210817 原宿店屋台対応 屋台村対応商品情報を追加するために使用
//  20210817　このボタンは屋台対応の場合のみ表示される。　１　～　４までの情報を強制的にクリア
    public void clearFoodStanddispCount () {
    _glog.log("clearFoodStanddispCount called");

      for(int i = 0; i < 4 ; i++) {
          _glog.log("->> num:" + i + " id:" + _orderAriveId[0] );
          if(_orderAriveId[0] != 0 ) {
              _glog.log("clear num:" + i + " id:" + _orderAriveId[0] );
              _orderAriveCnt[0] = 1;
              this.doAriveorder();
          } else {
              return;
          }
      }
      // ４つ以上注文表示がはいっていた場合は到着画面が再表示するように強制的にorderのプールに戻す。
      for(int i = 0; i < 4 ; i++) {
          if(_orderAriveId[i] != 0 ) {
              _glog.log("強制敵にオーダーカウント付与" +
                      " num:" + i + " id:" + _orderAriveId[i] );
              _orderId[i] = _orderAriveId[i];
              _orderNetaId[i] =  _orderAriveNetaId[i];
              _orderSara[i] = _orderAriveSara[i];
              _orderCnt[i] = 5;

               _orderAriveId[i] = 0;
               _orderAriveNetaId[i]= 0;
               _orderAriveSara[i]= 0;
              _orderAriveCnt[i]= 0;
          }
      }
    }
//  20210817　このボタンは屋台対応の場合のみ表示される。　１　～　４までの情報を強制的にクリア
// TODO　add 20210817 原宿店屋台対応 屋台村対応商品情報を追加するために使用

    //====================================
//下のレーンから流れてくる注文品を登録
//配列の要素一つ前まで使用可とする。
//====================================
    public void setOrder (String data,settingInfoFromServer siF){

        _glog.log(" setOrder called>"+ data);

        String arr[];
        if(data.indexOf(",") == -1 || data == null) {//文字列に区切り文字が入っていない場合はスキップする。
            _glog.log("ERR:setOrder err data");
            return;
        }
        int i=0;
        arr=data.split(",");
        if(arr.length < 5){
            _glog.log("ERR:setOrder , short");
            return;
        }

        int orderId =Integer.parseInt(arr[1]);//オーダーコード
        int netaId = Integer.parseInt(arr[2]);//ネタのコード
        int sara = Integer.parseInt(arr[3]);//皿数
        int monitorId = Integer.parseInt(arr[4]);//確認ボタンを押した端末のid
        int wasabi = Integer.parseInt(arr[5]);//わさび
        int cnt=0;//到着までのカウントを初期化

      /*   _glog.log( "orderId>"+ orderId);
         _glog.log( "netaId>"+ netaId);
         _glog.log( "sara>"+ sara);
         _glog.log( "monitorId>"+monitorId);
         _glog.log( "wasabi>"+wasabi);*/

        //ショートカットの状態と確認ボタンを押した端末のidから到着時間を計算(秒）
        cnt= siF.calArriveTime(monitorId);

        for(i=0;i<MAX_ORDERINFO_BUFFER;i++){
            if(_orderId[i]==0){
                _orderId[i] = orderId;
                _orderNetaId[i] = netaId;
                _orderSara[i] = sara;
                _orderWasabi[i] = wasabi;
                _orderCnt[i] = cnt * TICK_TO_SECOND;//秒で登録

                // TODO　add 20210817 原宿店屋台対応 初期値０で登録
                // _addFoodStandFlag[i] = 0;
                return;
            }
        }
        _orderId[MAX_ORDERINFO_BUFFER-2]=0;
        _orderNetaId[MAX_ORDERINFO_BUFFER-2]=0;
        _orderSara[MAX_ORDERINFO_BUFFER-2]=0;
        _orderWasabi[MAX_ORDERINFO_BUFFER-2] = 0;
        _orderCnt[MAX_ORDERINFO_BUFFER-2]=0;
    }


    //====================================
//下のレーンから流れてくる注文品の削除ボタンに対応
//配列の要素一つ前まで使用可とする。
//====================================
    public void deleteOrder(String data,settingInfoFromServer siF){

        int i=0;
        int i2=0;

        String arr[];
        if(data.indexOf(",") == -1 || data == null) {//文字列に区切り文字が入っていない場合はスキップする。
            _glog.log("ERR:setOrder err data");
            return;
        }
        arr=data.split(",");
        if(arr.length < 5){
            _glog.log("ERR:setOrder , short");
            return;
        }

        int orderId =Integer.parseInt(arr[1]);//オーダーコード
        for(i=0;i<MAX_ORDERINFO_BUFFER;i++){
            if(_orderId[i]==orderId){
                _orderId[i] = 0;
                _orderNetaId[i] = 0;
                _orderSara[i]  = 0;
                _orderWasabi[i] = 0;
                _orderCnt[i] = 0;
                for(i2=i;i2<MAX_ORDERINFO_BUFFER;i2++){
                    //NSLog(@"loop i(%d):i2(%d)",i,i2);
                    _orderId[i2]=_orderId[i2+1];
                    _orderNetaId[i2] = _orderNetaId[i2+1];
                    _orderSara[i2] = _orderSara[i2+1];
                    _orderWasabi[i2] = _orderWasabi[i+1];
                    _orderCnt[i2] = _orderCnt[i2+1] ; //for test
                    if(_orderId[i2+1] ==0){
                        // NSLog(@"brak :%d",i2);
                        _orderId[i2] = 0;
                        _orderNetaId[i2] = 0;
                        _orderSara[i2] = 0;
                        _orderWasabi[i2] = 0;
                        _orderCnt[i2] =0;
                        break;
                    }
                }
            }
        }
        _orderId[MAX_ORDERINFO_BUFFER-2]=0;
        _orderNetaId[MAX_ORDERINFO_BUFFER-2]=0;
        _orderSara[MAX_ORDERINFO_BUFFER-2]=0;
        _orderWasabi[MAX_ORDERINFO_BUFFER-2] = 0;
        _orderCnt[MAX_ORDERINFO_BUFFER-2]=0;
    }
    //====================================
//
//====================================
    public int doOrder(){

        int i=0;
        for(i=0;i<MAX_ORDERINFO_BUFFER-1;i++){
            if(_orderId[i]!=0){
                _orderCnt[i]--;
                _glog.log(i + ">doOrder : " + _orderCnt[i]);
            }
        }
        //-------count が０になったら注文到着画面フラグをセットし、バッファをつめる
        if(_orderCnt[0]<=0 && _orderId[0] != 0 ){
            // [self setAriveorder:_orderId[0] NetaId:_orderNetaId[0] Sara:_orderSara[0]];
            //  [self setAriveorder:_orderId[0] NetaId:_orderNetaId[0] Sara:_orderSara[0]];
            setAriveorder( _orderId[0],_orderNetaId[0] ,_orderSara[0]);
            _orderId[0]=0;
            _orderNetaId[0] = 0;
            _orderSara[0] = 0;
            _orderWasabi[0] = 0;
            _orderCnt[0] = 0; //for test
            for(i=0;i<MAX_ORDERINFO_BUFFER;i++){
                _orderId[i]=_orderId[i+1];
                _orderNetaId[i] = _orderNetaId[i+1];
                _orderSara[i] = _orderSara[i+1];
                _orderWasabi[i] = _orderWasabi[i+1];
                _orderCnt[i] = _orderCnt[i+1] ; //for test
                if( _orderId[i+1] ==0){
                    break;
                }
            }
            _orderId[i+1]=0;
            _orderNetaId[i+1]=0;
            _orderSara[i+1]=0;
            _orderWasabi[i+1] = 0;
            _orderCnt[i+1]=0;
            return 1;
        }
        return 0;
    }
    //====================================
//到着
//====================================
    //   -(void)setAriveorder:(int)orderId NetaId:(int)netaId Sara:(int)sara{
    private void setAriveorder(int orderId,int netaId ,int sara){
        int i;
        for(i=0;i<MAX_ARAIVEINFO_BUFFER;i++){
            if(_orderAriveId[i]==0){
                _orderAriveId[i] = orderId;
                _orderAriveNetaId[i] = netaId;
                _orderAriveSara[i] = sara;
                _orderAriveCnt[i] = 19; //到着後の表示時間

                // TODO　add 20210817 原宿店屋台対応 setAriveorderで４件以上プールされるケースは屋台村のみなので、設定時間を伸ばしておく
                if(i > 3) {
                    _orderAriveCnt[i] = 1200;
                }
                // TODO　add 20210817 原宿店屋台対応 setAriveorderで４件以上プールされるケースは屋台村のみなので、設定時間を伸ばしておく
                return;
            }
        }
    }
//====================================

    //====================================
    public void doAriveorder(){
        int i;
        for(i=0;i<MAX_ARAIVEINFO_BUFFER;i++){
            if(_orderAriveId[i]!=0){
                _orderAriveCnt[i]--;

                _glog.log(i + ">doAriveorder : " + _orderAriveCnt[i]);

            }
        }
        //-------count が０になったらバッファをつめる
        if(_orderAriveCnt[0]<=0 && _orderAriveId[0] != 0 ){

            _glog.log("doAriveorder END: "+_orderAriveId[0]);


            _orderAriveId[0]=0;
            _orderAriveNetaId[0] =0;
            _orderAriveSara[0] =0;
            //_orderWasabi[i] = wasabi;
            _orderAriveCnt[0] =0;

            for(i=0;i<MAX_ARAIVEINFO_BUFFER;i++){
                _orderAriveId[i]=_orderAriveId[i+1];
                _orderAriveNetaId[i]=_orderAriveNetaId[i+1];
                _orderAriveSara[i]=_orderAriveSara[i+1];
                _orderAriveCnt[i]=_orderAriveCnt[i+1];//for test
                if(_orderAriveId[i+1]==0){
                    break;
                }
            }
            _orderAriveId[i+1]=0;
            _orderAriveNetaId[i+1]=0;
            _orderAriveSara[i+1]=0;
            //_orderWasabi[i] = wasabi;
            _orderAriveCnt[i+1]=0;
        }
    }
    //====================================

    //====================================
    public void cleanAriveorder() {

        _glog.log("cleanAriveorder called");

        int i;
        for(i=0;i<MAX_ARAIVEINFO_BUFFER;i++){
            _orderAriveId[i]=0;
            _orderAriveNetaId[i]=0;
            _orderAriveSara[i]=0;
            //_orderWasabi[i] = wasabi;
            _orderAriveCnt[i]=0;
        }
    }
//====================================

    //====================================
    public int getTestCount(){
        return   _orderCnt[0];
    }
    public int getCurretOrderId(int x){
        return  _orderAriveId[x];
    }
    public int getCurretNetaId(int x){
        return  _orderAriveNetaId[x];
    }
    public int getCurretSara(int x){
        return  _orderAriveSara[x];
    }



}

