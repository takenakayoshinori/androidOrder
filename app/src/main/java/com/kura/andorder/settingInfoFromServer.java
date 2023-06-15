package com.kura.andorder;

//import android.util.Log;
//import java.util.Arrays;

public class settingInfoFromServer {

    // private String TAG = "USER LOG";
    private  logExtend _glog=null;

    private final String crlf = System.getProperty("line.separator");
    private final int ORDER_CATEGORY_CHANGE_TIME = 17;//17時昼と夜間の設定を区分する。

    private int[] shortcatstate = new int[8];//0.1:A 2.3:B 4.5:C 6.7:D
    private int[] soutatutime = new int[4];//0 通常　１　ｼｮｰﾄｶｯﾄ１　２ｼｮｰﾄｶｯﾄ２　３送達ﾚｰﾝ
    private int[] chuumonseigen = new int[4];//0:平日昼　１：平日夜　２：休日昼　３：休日夜
    private int OrderCatState;//0 平日午前　１平日午後　２祝日午前　３祝日午後

    private int[] soutatuoffset = new int[20];//change 2013 1108 モニタ増設に対応　0~3までの配列に何が入っている確認！！
    private int kyouseikyujitu = 0;
    private int kyouseiwasabi = 0;
    private int lenecategory = 0;

    //private int[] limitoffpage = new int[4];//注文制限の制約を受けないページ
//change 20160608  注文制限の制約を受けないページ 全ページを対象にするように変更
    private int[] limitoffpage = new int[8];//注文制限の制約を受けないページ

    //order stop state
    private int OrderStop_SystemOver_Flag;//0 注文可能状態　 1注文数MAXの場合 注文件数が１００件近くになると発行される。
    private int OrderStop_Refuse_Flag;//0 注文可能状態　1注文拒否状態　メインでｽﾄｯﾌﾟしている場合。ワンショットなので、udpに以降させる事
    private int OrderLimitFlag;//０制限無し　１注文制限　端末毎の注文数が設定を超えた場合（※:limit off pageは注文可能）

    private int OrderWaitAboutTime;//注文待ち時間目安
    private int tableno=0;

    //add 20170918 ver90 for debug
    private int _gCURRENT_ORDER=0;

    /**
     * コンストラクタ
     */
    public settingInfoFromServer(logExtend obj) {
        initSettting();
        this._glog=obj;
    }


    public int getCURRENT_ORDER(){
        return  _gCURRENT_ORDER;
    }

    public int get_chuumonseigen(int i) {
        if(i >= 0 && i<4){
            return chuumonseigen[i];
        }else{
            return -1;
        }
    }

    public int getCURRENT_chuumonseigen() {
        int ret=0;

        if(OrderCatState>=0 &&OrderCatState<4 ) {
            ret=chuumonseigen[OrderCatState];
        }else{
            ret=1;
        }
        return ret;
    }

    /**
     *
     */
    public int setOrderStop_Refuse(int val){
        if(val==0 || val==1) {
            OrderStop_Refuse_Flag=val;
        }
        return OrderStop_Refuse_Flag;
    }
    /**
     *
     */
   /* public int setOrderStop_SystemOver(int val){
        if(val==0 || val==1) {
            OrderStop_SystemOver_Flag=val;
        }
        return OrderStop_SystemOver_Flag;
    }*/

    /**
     *
     */
    private void initSettting() {
        int i = 0;
        kyouseikyujitu = 0;
        kyouseiwasabi = 0;
        OrderLimitFlag = 0;
        OrderStop_Refuse_Flag = 0;
        OrderStop_SystemOver_Flag = 0;
        lenecategory = 1;

        for (i = 0; i < 4; i++) {
            soutatutime[i] = 0;
            chuumonseigen[i] = 0;
        }
        for (i = 0; i < 8; i++) {
            shortcatstate[i] = 0;
        }
        for (i = 0; i < 20; i++) {
            soutatuoffset[i] = 0;
        }
    }
    /**
     * GET
     */
   /* public int getFoceHolidaySet(){
        return kyouseikyujitu;
    }*/
    /**
     * GET
     */
    /*public int getFoceWasabi(){
        return kyouseiwasabi;
    }*/
    /**
     * GET
     */
   /* public int getOrderWaitAboutTime(){
        return OrderWaitAboutTime;
    }*/
    /**
     * GET
     */
   /* public int getLeanCategory(){
        return lenecategory;
    }*/
    /**
     * GET
     */
    public int getOrderStop(){
        return OrderStop_Refuse_Flag;
    }
    /**
     * GET
     */
    public int getOrderStop_SYSTEM_OVER(){
        return OrderStop_SystemOver_Flag;
    }

    /**
     * 注文の区分をOrderCatStateに設定
     * 0 平日午前　１平日午後　２祝日午前　３祝日午後
     *
     */
    public void setOrderCatStat(int hour, int week) {
        int hour_flag = 0;
        int week_flag = 0;

        if (hour >= ORDER_CATEGORY_CHANGE_TIME) {
            hour_flag = 1;
        }
        if (week == 0 || week == 6 || kyouseikyujitu == 1) {
            //week_flag = 1;
            week_flag = 2;
        }
        OrderCatState = hour_flag + week_flag;
        //_glog.log("setOrderCatStat OrderCatState:" + OrderCatState);

    }



    /**
     * 注文制限にかかるケースで使用
     * 注文OFFページか確認し、小さい順にソートし、指定の番号を返す
     *
     */
    public int getOrderOffPage_ByOrder(int val) {
        int i=0;
        int j=0;
        int ch=0;
        int page[]=new int[8];
        //0クリア
        for(j=0;j<8;j++) {
            page[j] = 0;
        }
        //範囲チェック
        // if(val < 1 || val >4){
        if(val < 1 || val >8){
            return 0;
        }
        //ソート
        for(i=0;i<20;i++){
            for(j=0;j<8;j++){
                if(limitoffpage[j] ==i+1){
                    page[ch] = i+1;
                    ch++;
                }
            }
        }
        //すべて未設定の場合は99をかえす。
        if(ch == 0){
            return 99;
        }
        return page[val-1];
    }

    /**
     * 注文制限にかかるケースで使用
     * 注文OFFページか確認し、OFFページでない場合は表示の文言を作成
     * 多言語化未対応
     *
     */
  /*  public String getOrderOffPage() {
        String str = null;
        return str;
    }*/

    /**
     * 注文制限にかかるケースで使用
     * 注文OFFページか確認し、リターン
     * 多言語化未対応
     *　注文OFF対象ページを８に増加
     * 20180620->もう一度注文制限を確認する処理を入れてみる
     */
    //add 20180620 もう一度注文制限を確認する処理を入れてみる
    public int getOrderLimitFlag(){
        return OrderLimitFlag;
    }
    //add 20180620 もう一度注文制限を確認する処理を入れてみる
    public int getOrderStop_ORDERMAX(int currentPage) {
        int i = 0;
        // int page[] = new int[4];
        int cnt = 0;
        //_glog.log("getOrderStop_ORDERMAX called page>"+currentPage);


        //add 20180620 もう一度注文制限を確認する処理を入れてみる
        if(chuumonseigen[OrderCatState] == 0){//注文回数制限が０の場合は、注文制限無しと判断する。
            OrderLimitFlag= 0;
        }else if(chuumonseigen[OrderCatState] > _gCURRENT_ORDER){
            OrderLimitFlag= 0;
        }else{
            OrderLimitFlag= 1;
        }
        //add 20180620 もう一度注文制限を確認する処理を入れてみる

        if (OrderLimitFlag== 0) {
            return 0;
        } else {
            for (i = 0; i < 8; i++) {
                if (limitoffpage[i] == currentPage) {
                    _glog.log("limitoffpage >" +limitoffpage[i]);
                    return 0;
                }
            }
        }
        return 1;
    }
              /*  int i = 0;
        int page[] = new int[4];
        int cnt = 0;

        _glog.log("getOrderStop_ORDERMAX called page>"+currentPage);

       if (OrderLimitFlag== 0) {
            return 0;
        } else {
            for (i = 0; i < 4; i++) {
                _glog.log("limitoffpage>" +limitoffpage[i]);

                if (limitoffpage[i] == currentPage) {
                    return 0;
                }
            }
        }
        return 1;
    }*/
    /**
     * table_num
     */
    public void setTableNum(int num){
        tableno=num;
    }

    /**
     *
     */
    public void setSettingData(String data){

        //_glog.log("setSettingData called >"+data);
        if(data == null){
            _glog.log("ERR:setSettingData err null");
            return;
        }

        if(data.indexOf(",") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
            _glog.log("ERR:setSettingData err data");
            return;
        }
        int i=0;
        String arr[]=data.split(",");

        //  _glog.log("arr count >"+arr.length);

        if(arr.length < 31){
            _glog.log("ERR:setSettingData err length");
            return;
        }

        try {
            kyouseikyujitu = Integer.parseInt(arr[8]);
            for (i = 0; i < 8; i++) {
                shortcatstate[i] = Integer.parseInt(arr[i + 14]);
            }
            kyouseiwasabi = Integer.parseInt(arr[26]);

            for (i = 0; i < 4; i++) {
                chuumonseigen[i] = Integer.parseInt(arr[i + 27]);
            }

            //add 20161228
            //setOrderCatStat();

        }catch (Exception e){
            _glog.log("ERR:setSettingData" + e.toString());
        }

    }
    /**
     * 注文品到着時間目安
     */
    public void SetOrderWaitAboutTime(String data){
        int i = 0;
        //if(data != null && data != ""){
        if(data != null && !data.equals("")){
            data = data.trim();//split(",")で受けれない？？？？？
            i = Integer.parseInt(data);
        }
        OrderWaitAboutTime = i;
    }
    /**
     * @since 20160304
     */

    public void setAriveNeedTime(String data) {
        int i=0;

        _glog.log("setAriveNeedTime called:"+data);

        if(data == null){
            _glog.log("ERR:setAriveNeedTim err null");
            return;
        }
        if(data.indexOf(",") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
            _glog.log("ERR:setAriveNeedTim err data" + data);
            return;
        }
        String arr[] = data.split(",");
        if(arr.length < 4){
            _glog.log("ERR:setAriveNeedTim err data" + data);
            return;
        }
        try {
            for (i = 0; i < 4; i++) {
                soutatutime[i] = Integer.parseInt(arr[i+1]);
            }
        }catch (Exception e){
            _glog.log("ERR:setAriveNeedTim" + e.toString());
        }
    }
    /**
     *
     *注文制限状態なら注文制限ﾌﾗｸﾞをONする。
     */
    public void setOrderStop_ORDERMAX(String data){
        int x = 0;
        if(data == null){
            _glog.log("ERR:setOrderStop_ORDERMAX null data");
            return;
        }
        if(data.indexOf(",") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
            _glog.log("ERR:setOrderStop_ORDERMAX err data");
            return;
        }

        try{
            data = data.trim();
            x = Integer.parseInt(data.split(",")[0]);

            _gCURRENT_ORDER=x;

            if(chuumonseigen[OrderCatState] == 0){//注文回数制限が０の場合は、注文制限無しと判断する。
                OrderLimitFlag= 0;
                return;
            }else if(chuumonseigen[OrderCatState] > x){ //20180610 修正
                OrderLimitFlag= 0;
            }else{
                OrderLimitFlag= 1;
            }

            /*_glog.log( "L: x :"+ x );
            _glog.log("L:OrderCatState:"+ OrderCatState);
            _glog.log("L: chuumonseigen:" + chuumonseigen[OrderCatState]);
            _glog.log("L: OrderLimitFlag:" + OrderLimitFlag);*/

        }catch(NullPointerException e){
            _glog.log("ERR:setOrderStop_ORDERMAX" + e.toString());
        }catch(Exception e){
            _glog.log("ERR:setOrderStop_ORDERMAX" + e.toString());
        }
    }
    /**
     *注文OFFページの設定
     *元データーに改行コードがはいっているので注意
     */
    public void setLimitoffpage(String data) {

        String arr[] = null;
        int i=0;

        if(data == null){
            _glog.log("ERR: setLimitoffpage  data null");
            return;
        }

        _glog.log("setLimitoffpage:"+data);

        if (data.indexOf(",") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
            _glog.log("ERR: setLimitoffpage  data err");
            return;
        }
        arr = data.split(",\n");
        _glog.log( "arr"+ arr.length);

        try {
            if (arr.length > 3) {
                limitoffpage[0] = Integer.parseInt(arr[0]);
                limitoffpage[1] = Integer.parseInt(arr[1]);
                limitoffpage[2] = Integer.parseInt(arr[2]);
                limitoffpage[3] = Integer.parseInt(arr[3]);
            }

            // change20160608 注文制限ページを８ページに拡張
            if (arr.length > 7) {
                limitoffpage[4] = Integer.parseInt(arr[4]);
                limitoffpage[5] = Integer.parseInt(arr[5]);
                limitoffpage[6] = Integer.parseInt(arr[6]);
                limitoffpage[7] = Integer.parseInt(arr[7]);
            }

            for(i=0;i<8;i++){
                _glog.log(""+Integer.parseInt(arr[i]));
                _glog.log("limitoffpage:" + limitoffpage[i]);
            }

            //test
            //getOrderOffPage_ByOrder(1);
            //test
        } catch (Exception e) {
            _glog.log("ERR: setLimitoffpage" + e.toString());
        }
    }
    /**
     *
     */
    public void setClientOffsetTime(String data){

        if(data == null){
            _glog.log("ERR: setClientOffsetTime data null");
            return;
        }

        try {
            if (data.indexOf(",") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
                _glog.log("ERR: setClientOffsetTime  data err");
                return;
            }
            int i = 0;
            String arr[]=data.split(",");
            if(arr.length < 20){
                _glog.log("ERR: setClientOffsetTime arr short");
                return;
            }
            lenecategory = Integer.parseInt(data.split(",")[0]);
            for (i = 1; i < 20; i++) {
                soutatuoffset[i - 1] = Integer.parseInt(data.split(",")[i]);
            }
        }catch (Exception e){
            _glog.log("ERR: setClientOffsetTime"+e.toString());
        }
    }
    /**
     *　オーダーストップ信号を受けた場合に発行される。
     */
    public void setOrderStop(int val){
        if(val==1 || val ==0){
            OrderStop_Refuse_Flag=val;
        }
    }
    /**
     * 注文件数が１００件近くになった場合に発行される
     */
    public void setOrderStop_SYSTEM_ORDER_OVER(int val){
        if(val==1 || val ==0){
            OrderStop_SystemOver_Flag=val;
        }
    }
    /**
     * オフセット時間の計算
     */
    public int calArriveTime(int offset){
        int c = (lenecategory-1)*2;//該当配列の場所を検索
        int key = 0;
        int soutatuTime = 0;

        /*_glog.log("calArriveTime offset:"+offset);
        _glog.log("calArriveTime lenecategory:"+lenecategory);
        _glog.log("calArriveTime c:"+c);*/

        try{
            if(shortcatstate[c]==0 && shortcatstate[c+1]==0){//両方開いている
                key = 0;
            }else if(shortcatstate[c+1]==1){
                key =2;
            }else if(shortcatstate[c]==1){
                key = 1;
            }

            //_glog.log("calArriveTime  key:"+ key);

            if(offset ==21){
                offset =0;
            }
            if(offset > 80 && offset < 90){//add 20131108追加のモニタの番号が８０番台から。
                offset = offset - 70;
            }
            offset += 4;//offsetの持ち方は暫定的。サーバー処理を含めた修正を検討の事！！0~3までの配列に何が入っている確認！！

            soutatuTime = soutatutime[key] + soutatuoffset[offset];
            _glog.log("[BASE TIME:" + soutatutime[key] + "OFFSET TIME :" + soutatuoffset[offset] + "SOUTATU TIME :" + soutatuTime + "]\n");
        }catch(ArrayIndexOutOfBoundsException e){//配列の範囲外の場合
            _glog.log("ERR:calArriveTimec=" + c + " key = " + key + "offset =" + offset + e.toString());
        }catch (Exception e){
            _glog.log("ERR:calArriveTime" + e.toString());
        }
        return soutatuTime;
    }
    /**
     * print
     */
    public String setingprint(){
        int i = 0;
        StringBuffer sb= new StringBuffer();

        sb.append("強制休日:"+kyouseikyujitu);
        sb.append(crlf);
        sb.append("強制わさび"+ OrderLimitFlag);
        sb.append(crlf);
        sb.append("レーンｶﾃｺﾞﾘｰ"+lenecategory);
        sb.append(crlf);
        sb.append("注文区分:"+ OrderCatState);
        sb.append(crlf);
        sb.append("ｵｰﾀﾞｰｽﾄｯﾌﾟ（強制ボタン）"+OrderStop_Refuse_Flag );
        sb.append(crlf);
        sb.append("ｵｰﾀﾞｰｽﾄｯﾌﾟ（注文制限）"+OrderLimitFlag );
        sb.append(crlf);
        sb.append("ｵｰﾀﾞｰｽﾄｯﾌﾟ（システム）"+OrderStop_SystemOver_Flag);
        sb.append(crlf);
        for (i = 0; i < 4; i++) {
            sb.append("注文制限["+i+"]"+chuumonseigen[i]);
            sb.append(crlf);
        }
        for (i = 0; i < 4; i++) {
            sb.append("到着時間["+i+"]"+soutatutime[i]);
            sb.append(crlf);
        }
        for (i = 0; i < 8; i++) {
            sb.append("注文可能ページ["+i+"]"+limitoffpage[i]);
            sb.append(crlf);
        }
        return sb.toString();
    }



    /*
     * 20191111
     * 注文制限を強制的に上書き
     * 坂本MGから要望の注文数に応じて動的に制限数を送信する実験で使用

     */
    public void FoceSet_chuumonseigen(int val) {
        for(int i=0;i<4;i++){
            chuumonseigen[i]=val;
        }
    }


}



