package com.kura.andorder;

import android.util.Log;

/**
 * Created by kura on 2016/03/07.
 */
public class orderHeightInfo {

    // private String TAG = "USER LOG";
    private final int MAX_ORDERINFO_BUFFER= 5;//１回の到着数がこの数を上回るとバグる。
    private final int MAX_ARAIVEINFO_BUFFER = 5;//１回の到着数がこの数を上回るとバグる。
    private int _orderKousokuId[]=new int[MAX_ORDERINFO_BUFFER];
    private int  _orderKousokuNetaId[]=new int[MAX_ORDERINFO_BUFFER];
    private int _orderKousokuSara[]=new int[MAX_ORDERINFO_BUFFER];

    private int  _dispTime;
    private  logExtend _glog=null;
    public orderHeightInfo(logExtend obj){
        this._glog=obj;
    }

    //20180616 分轄更新のbuf
    private int MAX_DEVIDE_BUF_COUNT=10;
    private int _DevidBuf_Count[];
    private int _DevidBuf_netaID[];
    private int _DevidBuf_UnitCount[];

    private int _orderKousokCount[]=new int[MAX_ORDERINFO_BUFFER];
    private int _orderKousokuUnitCount[]=new int[MAX_ORDERINFO_BUFFER];


    // TODO　add 20211229 高速レーンの商品が重なった時に再表示する確認をする
    private int _ReDrawFlag = 0;
    //====================================
//20180616
//分割order処理
//netaIDのみのチェックなので、特殊な注文では整合性がなくなるかもしれない。
//====================================
    public void initDevidBuf(){
        _DevidBuf_netaID=new int[MAX_DEVIDE_BUF_COUNT];//何分轄するか
        _DevidBuf_Count=new int[MAX_DEVIDE_BUF_COUNT];//何分轄するか
        _DevidBuf_UnitCount=new int[MAX_DEVIDE_BUF_COUNT];//注文合計数
    }

    public void clearDevidBuf(){
        int i=0;
        for(i=0;i<MAX_DEVIDE_BUF_COUNT;i++){
            _DevidBuf_Count[i]=0;
            _DevidBuf_netaID[i]=0;
            _DevidBuf_UnitCount[i]=0;
        }

    }

    public void setDevidBuf(int netaid,int unitcount){
        int i=0;

        for(i=0;i<MAX_DEVIDE_BUF_COUNT;i++){
            if( _DevidBuf_netaID[i]==0){
                _DevidBuf_netaID[i]=netaid;
                _DevidBuf_Count[i]=0;
                _DevidBuf_UnitCount[i]=unitcount;
                _glog.log("TES->setDevidBuf called:"+i);
                _glog.log("TES->netaid:"+netaid);
                _glog.log("TES->unitcount:"+unitcount);
                return;
            }
        }
    }

    public int checkDevidBuf(int netaID){
        int i=0;

        //_glog.log("TES->checkDevidBuf netaID:"+netaID);

        if(netaID ==0){
            return -1;
        }
        for(i=0;i<MAX_DEVIDE_BUF_COUNT;i++){
            if( _DevidBuf_netaID[i]==netaID){
                _glog.log("TES->checkDevidBuf i:"+i);
                return i;
            }
        }
        return -1;
    }

    public int calDevidBuf(int i,int sara){
        int ret=0;
        _DevidBuf_Count[i]=_DevidBuf_Count[i]+sara;
        ret=_DevidBuf_Count[i];

        _glog.log("TES->calDevidBuf sara:"+sara);
        _glog.log("TES->calDevidBuf _DevidBuf_Count:"+_DevidBuf_Count[i]);
        _glog.log("TES->calDevidBuf ret:"+ret);
        _glog.log("TES->calDevidBuf UnitCount:"+_DevidBuf_UnitCount[i]);



        if(_DevidBuf_Count[i] >= _DevidBuf_UnitCount[i]){
            _DevidBuf_Count[i]=0;
            _DevidBuf_netaID[i]=0;
            _DevidBuf_UnitCount[i]=0;
        }
        return ret;
    }


    //====================================

    //====================================
    public void clearOrder(){
        int i = 0;

        // TODO　add 20211229 高速レーンの商品が重なった時に再表示する確認をする
        if(_ReDrawFlag != 0){
            _glog.log("★★高速レーンの再描画が実行されていないため、クリアーしない" );
            return;
        }
        for(i=0;i<MAX_ORDERINFO_BUFFER;i++){
            _orderKousokuId[i] =0;
            _orderKousokuNetaId[i] =0;
            _orderKousokuSara[i] =0;

            //20180616 分轄更新のbuf
            _orderKousokuUnitCount[i]=0;
        }
    }
//====================================

    //====================================
    public void setOrder(String data){

        String arr[];
        int i= 0;
        int key=0;

        _glog.log("setOrder>" + data);
        if(data.indexOf(",") == -1 || data == null) {//文字列に区切り文字が入っていない場合はスキップする。
            _glog.log("ERR:setOrder err data");
            return;
        }
        arr=data.split(",");
        if(arr.length < 12){
            _glog.log("ERR:setOrder , short");
            return;
        }

        // TODO　add 20211229 高速レーンの商品が重なった時に再表示する確認をする
        // 高速レーンの描画を伸ばした事で描画中に次の描画情報が来ると処理されないという問題が生じている
        // 高速レーンのアイテムが既にある場合はデーターをクリアして再描画のフラグを立てる
        if(_orderKousokuId[0] != 0){
            _glog.log("★★高速レーンのアイテムが存在するため、再描画のフラグをたてる" );
            this.clearOrder();
            this.setReDrawFlag(1);
        }
        // TODO　add 20211229 高速レーンの商品が重なった時に再表示する確認をする


        // _dispTime=[[ar objectAtIndex:0] intValue]*2;//<change:20150515>ver2.8 del
        _orderKousokuId[0] =Integer.parseInt(arr[1]);
        _orderKousokuNetaId[0] =Integer.parseInt(arr[2]);
        _orderKousokuSara[0] =Integer.parseInt(arr[3]);

        //20180616 分轄更新のbuf
        key=checkDevidBuf(_orderKousokuNetaId[0]);
        if(key != -1){
            _orderKousokuUnitCount[0]=_DevidBuf_UnitCount[key];
            _orderKousokCount[0]=calDevidBuf(key,_orderKousokuSara[0]);
            _glog.log("TES 0->"+_orderKousokCount[0] + "/" + _orderKousokuUnitCount[0] );
        }else{
            _orderKousokuUnitCount[0]=0;
            _orderKousokCount[0]=0;
        }

        _orderKousokuId[1] =Integer.parseInt(arr[4]);
        _orderKousokuNetaId[1] =Integer.parseInt(arr[5]);
        _orderKousokuSara[1] = Integer.parseInt(arr[6]);

        //20180616 分轄更新のbuf
        key=checkDevidBuf(_orderKousokuNetaId[1]);
        if(key != -1){
            _orderKousokuUnitCount[1]=_DevidBuf_UnitCount[key];
            _orderKousokCount[1]=calDevidBuf(key,_orderKousokuSara[0]);
            _glog.log("TES 1->"+_orderKousokCount[1] + "/" + _orderKousokuUnitCount[1] );
        }else{
            _orderKousokuUnitCount[1]=0;
            _orderKousokCount[1]=0;
        }

        _orderKousokuId[2] =Integer.parseInt(arr[7]);
        _orderKousokuNetaId[2] =Integer.parseInt(arr[8]);
        _orderKousokuSara[2] = Integer.parseInt(arr[9]);

        //20180616 分轄更新のbuf
        key=checkDevidBuf(_orderKousokuNetaId[2]);
        if(key != -1){
            _orderKousokuUnitCount[2]=_DevidBuf_UnitCount[key];
            _orderKousokCount[2]=calDevidBuf(key,_orderKousokuSara[0]);
            _glog.log("TES 2->"+_orderKousokCount[2] + "/" + _orderKousokuUnitCount[2] );
        }else{
            _orderKousokuUnitCount[2]=0;
            _orderKousokCount[2]=0;
        }

        _orderKousokuId[3] =Integer.parseInt(arr[10]);
        _orderKousokuNetaId[3] = Integer.parseInt(arr[11]);
        _orderKousokuSara[3] = Integer.parseInt(arr[12]);

        //20180616 分轄更新のbuf
        key=checkDevidBuf(_orderKousokuNetaId[3]);
        if(key != -1){
            _orderKousokuUnitCount[3]=_DevidBuf_UnitCount[key];
            _orderKousokCount[3]=calDevidBuf(key,_orderKousokuSara[0]);
            _glog.log("TES 3->"+_orderKousokCount[3] + "/" + _orderKousokuUnitCount[3] );
        }else{
            _orderKousokuUnitCount[3]=0;
            _orderKousokCount[3]=0;
        }
    }

    public int doOrder(){
        if(_orderKousokuId[0] != 0){
            return 1;
        }else{
            return 0;
        }
    }

    // TODO　add 20211229 高速レーンの商品が重なった時に再表示する確認をする
    // 高速レーンの描画を伸ばした事で描画中に次の描画情報が来ると処理されないという問題が生じている
    // 高速レーンの描画中、この処理をフラグを確認し、１なら再描画する
    public int getReDrawFlag(){
        if(_ReDrawFlag == 1){
            _ReDrawFlag = 0;
            return 1;
        }else{
            return 0;
        }
    }
    public void setReDrawFlag(int value){
      if(value == 1 || value == 0){
          _ReDrawFlag = value;
      }else{
          _ReDrawFlag = 0;
      }
    }

    // TODO　add 20211229 高速レーンの商品が重なった時に再表示する確認をする

//====================================

    //====================================
/*-(int)getDispTime{//<change:20150515>ver2.8 del
    return _dispTime;
}*/
    public int tgetCurretOrderId(int x){
        return  _orderKousokuId[x];

    }
    public int getCurretNetaId(int x){
        return  _orderKousokuNetaId[x];
    }
    // public int getCurretSara(int x) { //change 20180616
    public String getCurretSara(int x) {
        if (_orderKousokuUnitCount[x] != 0 && _orderKousokCount[x] != 0) {
            return _orderKousokCount[x]+"/"+_orderKousokuUnitCount[x];
        } else {
            return String.valueOf(_orderKousokuSara[x]);
        }
    }




}
