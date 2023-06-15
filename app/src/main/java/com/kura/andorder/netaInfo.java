package com.kura.andorder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.opengl.Matrix;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class
netaInfo {
    private int PAGE_MAX;
    private int ORDER_MAX;

    // TODO 20210829 TAG_MAXを９に増やす
    // private int TAG_MAX = 7;cmd
    private int TAG_MAX = 9;
    private int COUNT;//ネタ情報の数
    private Bitmap neta0;//ネタ情報の数
    private final String[] UNIT = {"皿", "本", "個", "杯"};
    private File _gFileDir;
    private  logExtend _glog=null;

    private String[] mNITAGname = new String[TAG_MAX];//tag情報
    private int[] mNITAGpage = new int[TAG_MAX];
    private int[] mNIid;
    private String[] mNIname;
    private String[] mNImemo;
    private Bitmap[] mNIbmp;
    private int[] mNIpage;
    private int[] mNIorder;
    private int[] mNIPrice;
    private int[] mNIUnit;
    private String[] mBmpPath;
    private int[] mNIChumon;
    private int[] mNIZaiko;
    private int[] mNIWasabi;
    private int[] mNISelf;
    //ver77 品切れ時に品切れ表示文字を出す商品を設定
    private int[] mNISinagireDisp;
    //local str用
    private String[] mNIname_sub1;
    private String[] mNIname_sub2;
    private int[] mname_sparate;
    //xcode 版より移植
    private int _kaikeiCat[];//<change:20150522>
    private int _seizou[];//seizou 区分


    //---change 20180419 会計区分用---
    // TODO kaikeicatを100まで対応させる 20211222
    // private final int MAX_T_NAME = 60;
    private final int MAX_T_NAME = 100;

    private String _TkaikeiCatName[] = new String[MAX_T_NAME];

    //add 20180816 実験用に値段情報を追加 海外対応は別途検討とする。
    //private float _TkaikeiCatPrice[] = new float[MAX_T_NAME];
    private int _TkaikeiCatPrice[] = new int[MAX_T_NAME];
    //---change 20180419 会計区分用---

    //add 20171031 option対応用
    private int[] mNIOptSel;
    //up 20180222 大ネタ対応処理
    private int[] gPageSpSet;
    //add 20180612 分轄注文を実施。分割注文時２以上なら分轄して注文
    private int[] mDevidOrderOn;
    //add 20180615 for recommend
    private int RECOMMEND_MAX=20;
    private String[] gRecommendHtml;
    private int[] gRecommendNetaId;
    private int[] gRecommendJumpId;


    // TODO marge ２皿対応
    private int[] mDoublePlate;

    // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
    private int[] mFoodStand;
    // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
    private int[] mFoodStandItemType; // それ以外:0 ICE対象商品:1


    /****************************************************************
     * コンストラクタ
     *****************************************************************/
    public netaInfo(File f, int page_max, int order_max ,logExtend obj){
        this._glog=obj;
        this._gFileDir = f;
        this.PAGE_MAX = page_max;
        this.ORDER_MAX = order_max;
        this.COUNT = page_max * order_max;

        mNIname = new String[COUNT];
        mNImemo = new String[COUNT];
        mNIid = new int[COUNT];
        mNIpage = new int[COUNT];
        mNIorder = new int[COUNT];
        mNIPrice = new int[COUNT];
        mNIZaiko = new int[COUNT];
        mNIWasabi = new int[COUNT];
        mNISelf = new int[COUNT];
        mNIbmp = new Bitmap[COUNT];
        mNIUnit = new int[COUNT];
        mNIChumon = new int[COUNT];
        mBmpPath = new String[COUNT];

//xcode 版より移植
        _kaikeiCat = new int[COUNT];
        _seizou = new int[COUNT];


        mNIname_sub1 = new String[COUNT];
        mNIname_sub2 = new String[COUNT];
        mname_sparate = new int[COUNT];

        //ver77 品切れ時に品切れ表示文字を出す商品を設定
        mNISinagireDisp = new int[COUNT];
        //ver100 add20171001 order時 option機能 ---
        mNIOptSel= new int[COUNT];

        //up 20180222 大ネタ対応処理
        gPageSpSet= new int[this.PAGE_MAX];

        //add 20180612 分轄注文を実施。分轄単位
        mDevidOrderOn= new int[COUNT];

        // TODO marge ２皿対応
        mDoublePlate = new int[COUNT];

        // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
        mFoodStand = new int[COUNT];

        // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
        mFoodStandItemType  = new int[COUNT];
    }

    // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
    /**
     *  upload.zipの中にfoodstandNetaInfo.csvをセット
     *  対象商品をid, name, ice対象商品
     *
     */
    public void setmFoodStand() {
        _glog.log("原宿店屋台対応 setmFoodStand called");
        File subfile = new File(this._gFileDir.toString() + "/upload/foodstandNetaInfo.csv");
        try {
            int i = 0;
            int checkId = 0;
            int key = 0;
            String line = "";
            byte[] data= new byte[PAGE_MAX];
            FileInputStream in = new FileInputStream(subfile);
            InputStreamReader in2 = new InputStreamReader(in, "UTF8");
            BufferedReader br = new BufferedReader(in2);

            while ((line = br.readLine()) != null) {
                String[] st = tokenToArgs(line, ",");
                // _glog.log(">" + line + " size:" + st.length );
                if(line.contains(",")) {
                    // TODO　add 20210817 原宿店屋台対応 　ID照合
                    // if (st.length > 1) {
                     if (st.length > 3) {
                        checkId = Integer.parseInt(st[0]);
                         int itemType = Integer.parseInt(st[2]);
                         key = this.serchID( checkId );
                        // _glog.log("checkId:" +  checkId + " key:" + key);
                        // TODO　add 20210817 原宿店屋台対応 　ID照合
                        if(  key == -1){
                            _glog.log("notfound key:" +  checkId);
                        }else{
                            if (key >= 0 && key < COUNT) {
                                mFoodStand[key] = 1;
                                _glog.log("FoodStand:" + mNIid[key] + " name:" + mNIname[key] );

                                // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
                                if( itemType == 1 ) {
                                    mFoodStandItemType[key] = 1;
                                } else {
                                    mFoodStandItemType[key] = 0;
                                }
                                // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
                            }
                        }
                    }
                }
            }
            in.close();
            in2.close();
            br.close();
        }catch (FileNotFoundException e){
            _glog.log("ERR: setmFoodStand not found");
        } catch (Exception e) {
            _glog.log("setmFoodStand ERR !! Exception>>" + e.toString());
        }
    }
    // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討



//================================================================
    //up 20180222 大ネタ対応処理
//================================================================
    public int getPageSpInfo(int i){

        if(i<=0 && i<PAGE_MAX) {
            return gPageSpSet[i];
        }else{
            return 0;
        }
    }
    //================================================================
//Page情報のクリア
//================================================================
    private void clearPageSpInfo(){
        int i;
        for(i=0;i<PAGE_MAX;i++){
            gPageSpSet[i]=0;
        }
    }
    public void setPageSpInfo() {
        int i=0;
        int key=0;

        // _glog.log("setPageSpInfo called");

        File subfile = new File(this._gFileDir.toString() + "/upload/PageSet.csv");
        try {

            byte[] data= new byte[PAGE_MAX];
            FileInputStream in = new FileInputStream(subfile);
            in.read(data);

            /*
            for(i=0;i<PAGE_MAX;i++){
                if(data[i]==1){
                    _glog.log("setPageSpInfo:"+i +" data:"+data[i]);
                }
            }
            */
            in.close();
        }catch (FileNotFoundException e){
            _glog.log("ERR: setPageSpInfo not found");
        } catch (Exception e) {
            _glog.log("setPageSpInfo ERR !! Exception>>" + e.toString());
        }

    }

    /****************************************************************
     * get file
     *****************************************************************/
    private void CreateNetaFile() {
       /* YsSocket yscocket = new YsSocket();//カテゴリ
        if(yscocket.GetState() == 0){
            yscocket.CommandRequestFile("001002:", file.toString()+"/ini/netainfo.csv");
        }
        yscocket = null;*/
    }

    private void CreateTagFile() {

      /*  YsSocket yscocket = new YsSocket();//タグ情報　
        if(yscocket.GetState() == 0){
            yscocket.CommandRequestFile("001001:", file.toString()+"/ini/taginfo.csv");
        }
        yscocket = null;*/
    }

    /****************************************************************
     * ネタ情報の初期化
     *****************************************************************/
    public void ClearNetaInfo() {
        for (int i = 0; i < COUNT; i++) {
            mNIname[i] = "";
            mNImemo[i] = "";
            mNIid[i] = 0;
            mNIpage[i] = 0;
            mNIorder[i] = 0;
            mNIPrice[i] = 0;
            mNIZaiko[i] = 0;
            mNIWasabi[i] = 0;
            mNISelf[i] = 0;
            mNIUnit[i] = 0;
            mNIChumon[i] = 0;
            // mNIbmp[i] = null;//change 20160618画像の消去は画像更新前に
            _kaikeiCat[i] = 0;

            //ver77 品切れ時に品切れ表示文字を出す商品を設定
            mNISinagireDisp[i] = 0;
            mNIOptSel[i]=0;

            //add 20180612 分轄注文を実施。分割注文時２以上なら分轄して注文
            mDevidOrderOn[i]=0;

            // TODO marge ２皿対応
            mDoublePlate[i] = 0;

            // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
            mFoodStand[i] = 0;
            // return;
        }
    }
    /****************************************************************
     *add 20160618
     *****************************************************************/
    public void ClearNetaImg() {
        for (int i = 0; i < COUNT; i++) {
            //add 20161115 recycleを追加
            if(mNIbmp[i] != null) {
                try {
                    mNIbmp[i].recycle();
                }catch (Exception e){

                }
                mNIbmp[i] = null;
            }
        }
    }

    /****************************************************************
     *
     *****************************************************************/
    public int SerchNeta(int id) {
        for (int i = 0; i < this.PAGE_MAX * this.ORDER_MAX; i++) {
            if (id == this.mNIid[i]) {
                return i;
            }
        }
        return -1;
    }
//======================================================================
//add 20160623(from ver11)
//ネタの画像をセット
    //FTP更新に画像の同期が取れていない。ネタデーター取得後に画像を更新する必要がある。

    //=========================================================================
    public void setImg(){

      /*  int i=0;
        //NSLog(@"setImg called");//調査用
        //画像データー更新要求があった場合は画像も全て更新
        this.ClearNetaImg();
        for(i=0;i<COUNT;i++){
            mNIbmp[i] = Setbmp(mBmpPath[i]);
        }*/
    }

    //======================================================================
//test
//=========================================================================
    public void setPageImg(int page){
        int i=0;

        this.ClearNetaImg();

        for(i=0;i<12;i++){
            mNIbmp[page*12 + i] = Setbmp(mBmpPath[page*12 + i]);

        }
    }
    //======================================================================
//add 20161201 到着画面ように単独のネタ画像読み込み処理追加
//=========================================================================
    public void setSingleImg(int x){
        int i=0;
        mNIbmp[x] = Setbmp(mBmpPath[x]);
    }


//======================================================================
//add 2020 0707 flash用の画像読み込み　
//=========================================================================
    public Bitmap getFlashViewBmp(){
        String f_name = _gFileDir.toString() + "/upload/flashView.jpg";
        // String f_name = _gFileDir.toString() + "/" + mBmpPath[0];
        File subfile = new File(f_name);

        // _glog.log("getFlashViewBmp called 1"+f_name);//調査用

        if (!subfile.exists()) {
                return Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
        }

        // _glog.log("getFlashViewBmp called 2" +f_name);//調査用

        BitmapFactory.Options options = new BitmapFactory.Options();//この値をtrueにすると実際には画像を読み込まず、画像のサイズ情報だけを取得することができます。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(subfile.toString(), options);//
        options.inJustDecodeBounds = false;//今度は画像を読み込みたいのでfalseを指定
        Bitmap bmp = BitmapFactory.decodeFile(subfile.toString(), options);
        return bmp;
    }

    //======================================================================
//add 20180817 大ネタ表記用のイメージを取得

    //=========================================================================
    public Bitmap getBIGImg(int x){
        String f_name = _gFileDir.toString() + "/" + mBmpPath[x];

          // _glog.log("getBIGImg called 1"+f_name + "  " + mBmpPath[x]);//調査用

        File subfile = new File(f_name);
        if (!subfile.exists()) {
            subfile = new File(_gFileDir + "/img/menyu/" + "0000.jpg");
            if (!subfile.exists()) {
                return Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
            }
        }
        BitmapFactory.Options options = new BitmapFactory.Options();//この値をtrueにすると実際には画像を読み込まず、画像のサイズ情報だけを取得することができます。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(subfile.toString(), options);//上記のオプションがtrueのため実際の画像は読み込まれないです。
        //int scaleW = (options.outWidth / 100 + 1);
        //int scaleH = (options.outHeight / 50 + 1);
        // int scale = Math.max(scaleW, scaleH);//縮尺は整数値で、2なら画像の縦横のピクセル数を1/2にしたサイズ。3なら1/3にしたサイズで読み込まれます。
        options.inJustDecodeBounds = false;//今度は画像を読み込みたいのでfalseを指定
        //options.inSampleSize = scale;//先程計算した縮尺値を指定.これで指定した縮尺で画像を読み込めます。もちろん容量も小さくなるので扱いやすいです。
        Bitmap bmp = BitmapFactory.decodeFile(subfile.toString(), options);
        return bmp;
    }









    /****************************************************************
     *
     *****************************************************************/
    public int ChangeBmp(int id) {
       /* YsSocket yscocket = new YsSocket();
        if(yscocket.GetState() ==0){
            int c =SerchNeta(id);
            String f_name =file.toString()+"/"+ mBmpPath[c];
            yscocket.CommandRequestFile("001020:"+Integer.toString(id), f_name);
            yscocket = null;
            mNIbmp[c] = Setbmp(mBmpPath[c]);
            return 0;
        }*/
        return -1;
    }

    /****************************************************************
     *
     *****************************************************************/
    /*private Bitmap Setneta0() {//

        Bitmap bmp;
        File subfile = new File(_gFileDir + "/upload/menyu/" + "0000.jpg");

        if (!subfile.exists()) {
            _glog.log("Bitmap Setneta0()ERR not exists 0000.jpg");
            //bmp = Bitmap.createBitmap(280, 180, Bitmap.Config.ARGB_8888);
            bmp = Bitmap.createBitmap(50, 10, Bitmap.Config.ARGB_8888);
        } else {
            bmp = BitmapFactory.decodeFile(subfile.toString());
        }
        return bmp;
    }*/

    /****************************************************************
     *
     *****************************************************************/
    private Bitmap Setbmp(String path) {//
        String f_name = _gFileDir.toString() + "/" + path;
        File subfile = new File(f_name);
        if (!subfile.exists()) {
            subfile = new File(_gFileDir + "/img/menyu/" + "0000.jpg");
            //========================================
            if (!subfile.exists()) {
                //Bitmap bmp_0 = Bitmap.createBitmap(400, 240, Bitmap.Config.ARGB_8888);
                //return conposit(280, 180, bmp_0);
                //return conposit(250, 150, bmp_0);
                //return Bitmap.createBitmap(200, 100, Bitmap.Config.RGB_565);
                return Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
            }
            //========================================
        }
        BitmapFactory.Options options = new BitmapFactory.Options();//この値をtrueにすると実際には画像を読み込まず、画像のサイズ情報だけを取得することができます。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(subfile.toString(), options);//上記のオプションがtrueのため実際の画像は読み込まれないです。


        int scaleW = (options.outWidth / 280 + 1);
        int scaleH = (options.outHeight / 200 + 1);

        //int scaleW = (options.outWidth / 200 + 1);
        //int scaleH = (options.outHeight / 100 + 1);
        int scale = Math.max(scaleW, scaleH);//縮尺は整数値で、2なら画像の縦横のピクセル数を1/2にしたサイズ。3なら1/3にしたサイズで読み込まれます。
        options.inJustDecodeBounds = false;//今度は画像を読み込みたいのでfalseを指定
        options.inSampleSize = scale;//先程計算した縮尺値を指定.これで指定した縮尺で画像を読み込めます。もちろん容量も小さくなるので扱いやすいです。
        Bitmap bmp = BitmapFactory.decodeFile(subfile.toString(), options);
        //return conposit(280,180,bmp);
        return bmp;
    }

    /****************************************************************
     *
     *****************************************************************/
    private Bitmap conposit(int w, int h, Bitmap image) {
        Bitmap newBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(image, 0, 0, null);
        image.recycle();
        return newBitmap;
    }

    /****************************************************************
     * getter
     *****************************************************************/
    public String getTagname(int i) {
        return this.mNITAGname[i];
    }

    public int getTagpage(int i) {
        return this.mNITAGpage[i];
    }

    public int getId(int i) {
        return this.mNIid[i];
    }

    public String getName(int i) {
        //if (this.isItem(i) == false) {
        if (!this.isItem(i)) {
            return "";//品切れの場合は文字表記無し。
        }
        return this.mNIname[i];
    }

    // TODO 20220125 履歴の際は品切れでも名称を表記する。
    public String getAbsoluteName(int i) {
        return this.mNIname[i];
    }
    // TODO 20220125 履歴の際は品切れでも名称を表記する。


    public String getMemo(int i) {
        return this.mNImemo[i];
    }

    public int getZaiko(int i) {
        return this.mNIZaiko[i];
    }

    public int getSelf(int i) {
        return this.mNISelf[i];
    }

    public int getWasabi(int i) {
        return this.mNIWasabi[i];
    }

    public int getPrice(int i) {
        return this.mNIPrice[i];
    }

    //add 20180612 分轄注文を実施。
    public int getDevidOrderOn(int i) {
        return this.mDevidOrderOn[i];
    }

    /*public int getShariSel(int i) {
        return this.mNIShariSel[i];
    }*/

    public int getOptSel(int i) {
        return this.mNIOptSel[i];
    }



    //============================
//ver77 品切れ時に品切れ表示文字を出す商品を設定
    //============================
    public int getSinagireDisp(int i) {
        return this.mNISinagireDisp[i];
    }

    public Bitmap getBmpSinagireDisp(int i) {
        if (mNIbmp[i] == null) {
            return this.neta0;//デフォルト画像を返すように修正。
        }
        return this.mNIbmp[i];
    }
    public String getNameSinagireDisp(int i) {
        if (mNIname[i] == null) {
            return "";//品切れの場合は文字表記無し。
        }
        return mNIname[i];
    }


    public String getUnit(int i) {
        int cc = this.mNIUnit[i];
        String unit = UNIT[cc];
        return unit;
    }

    public int getUnitInt(int i) {
        return this.mNIUnit[i];
    }

    public Bitmap getBmp(int i) {
        //_glog.log("Bitmap getBmp" +i);
        if (mNIbmp[i] == null || this.isItem(i) == false) {
            return this.neta0;//デフォルト画像を返すように修正。
        }
        return this.mNIbmp[i];
    }


    //==============================================
//ネタidからネタ名称を返す
//==============================================
    public String getNameFromNetaId(int netaid) {
        int i = serchID(netaid);
        if (i == -1) {
            return "";
        }
        if (mNIname[i] == null) {
            return "NETA" + i;
        }
        return mNIname[i];
    }

    public int getSeizouFromNetaId(int netaid) {

        int i = serchID(netaid);
        if (i == -1) {
            return 0;
        }
        return _seizou[i];
    }

    //<change:20150222>
    public int getKaikeiCatFromNetaId(int netaid) {
        int i = serchID(netaid);
        if (i == -1) {
            return 0;
        }
        return _kaikeiCat[i];
    }

    /****************************************************************
     * check
     *****************************************************************/
    public boolean isItem(int i) {
        // _glog.log("isItem called <" +i + "> id:"+mNIid[i]+ "zaiko:"+mNIZaiko[i]);
        boolean ret = false;
        try {
            ret = mNIid[i] != 0 && mNIZaiko[i] == 1;
            return ret;
        }catch (Exception e){
            _glog.log("ERR: isItem");
            return ret;
        }
    }

    /****************************************************************
     * isPage
     *****************************************************************/

    public boolean isPage(int page) {//pageに掲載商品があるかをチェックする。

        //  _glog.log("isPage called " + page);

        for (int i = 0; i < ORDER_MAX; i++) {
            if (isItem(i + (page - 1) * ORDER_MAX)) {
                return true;//ページ内にidが０でなくて、在庫がある商品が存在したら真を返す。
            }
        }
        return false;
    }

    /****************************************************************
     * タグデーターのｾｯﾄ
     *****************************************************************/
    // public void settagdata() {
    // TODO 20211211 後方互換性tag数を確認して７～９で確認する。（7以下は7表示とする。）
    public int settagdata() {
        int i = 0;
        // TODO 20210829 TAG_MAXを９に増やす  データーの取得左記をupload配下にして、データー更新で設定する形に変更する。（メインモニタの操作は無効になる）
        // File subfile = new File(_gFileDir + "/taginfo.csv");
        File subfile = new File(_gFileDir + "/upload/taginfo.csv");
        try {
            FileInputStream in1 = new FileInputStream(subfile);
            InputStreamReader in2 = new InputStreamReader(in1, "UTF8");
            BufferedReader br = new BufferedReader(in2);
            String line = "";
            while ((line = br.readLine()) != null) {//
                // if (line.indexOf(",") != -1) {//文字列に区切り文字が入っていない場合はスキップする。
                if(line.contains(",")){
                    String[] st = tokenToArgs(line, ",");
                    mNITAGname[i] = st[1];

                    // TODO 20210829 TAG_MAXを９に増やす
                    // mNITAGpage[i] = Integer.parseInt(st[5]);

                    int setpage = this.ParseInt(st[5]);
                    _glog.log("settagdata i page:" + setpage + " defalut:" + mNITAGname[i]);
                    if( setpage > 0 && setpage <= PAGE_MAX) {
                        mNITAGpage[i] = Integer.parseInt(st[5]);
                    }else{
                        _glog.log("settagdata invaild page:" + setpage);
                    }
                    i++;
                }
            }
            in1.close();
            in2.close();
            br.close();
            return i;
        } catch (FileNotFoundException e) {
            _glog.log("ERR: <NetaInfo >not found");
            return i;
        } catch (IOException e) {
            _glog.log(": <NetaInfo> Set_Data()");
            return i;
        }
    }

    /****************************************************************
     * ネタデーターのｾｯﾄ
     *****************************************************************/
    public void setnetadata() {
        //public void setnetadata(int BmpFlag) {



        int i = 0;
        int key = 0;
        File subfile = new File(this._gFileDir.toString() + "/netainfo.csv");
        try {
            FileInputStream in1 = new FileInputStream(subfile);
            InputStreamReader in2 = new InputStreamReader(in1, "UTF8");
            BufferedReader br = new BufferedReader(in2);
            String line = "";
            int s_id = 0;
            int s_page = 1;
            int s_order = 1;
            int s_self = 0;
            int s_wasabi = 0;
            int s_zaiko = 0;
            int s_price = 0;
            int s_chuumon = 0;
            int s_unit = 0;

            int s_kaikeicat = 0;


            //ver77 品切れ時に品切れ表示文字を出す商品を設定
            int s_sinagiredisp=0;

            //ver101 add20171025 シャリ選択
            // int s_shariSel=0;
            int s_OptSel=0;

            //add 20180612 分轄注文を実施。分割注文時２以上なら分轄して注文
            int s_DevidOrderOn=0;
            // TODO marge ２皿対応
            int s_DoublePlate = 0;

            // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
            // int s_FoodStand = 0;

            String s_name = "";
            String s_memo = "";
            String s_path = "";


            //add 20160618
            this.ClearNetaInfo();//既存のデーターを消去してから
           /* if (BmpFlag == 1) {//画像データー更新要求があった場合は画像も全て更新
            this.ClearNetaImg();
            }*/
            //add 20160618


            while ((line = br.readLine()) != null) {
                //if (line.indexOf(",") != -1) {//文字列に区切り文字が入っていない場合はスキップする。
                if(line.contains(",")){
                    String[] st = tokenToArgs(line, ",");
                    //  _glog.log("-->>" + line.toString());
                    if (st.length > 17) {//もしデーター件数が少ない場合はスキップしてしまう。
                        s_id = Integer.parseInt(st[0]);
                        s_name = st[2];
                        s_memo = st[3];
                        s_page = Integer.parseInt(st[5]);
                        s_order = Integer.parseInt(st[6]);
                        s_chuumon = Integer.parseInt(st[8]);
                        s_unit = Integer.parseInt(st[9]);
                        s_self = Integer.parseInt(st[10]);
                        s_wasabi = Integer.parseInt(st[11]);
                        //_glog.log(key +":--------------"+s_price);
                        s_price = this.ParseInt(st[12]);
                        s_kaikeicat = Integer.parseInt(st[13]);
                        s_zaiko = Integer.parseInt(st[15]);


                        //add 20171103 wasibiの欄を使用。わさびは０固定
                        //add 20171103 option表示用のフラグ
                        s_OptSel =Integer.parseInt(st[11]);
                        s_wasabi =0;
                        //add 20171103 wasibiの欄を使用。わさびは０固定


                        // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
                        // TODO　どの欄を使うかは未定、下記は修理する事
                         // s_FoodStand = Integer.parseInt(st[14]);
                        // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討


                        //add 20180612 分轄注文を実施。分轄単位を指定
                        if (st.length > 25) {//後方互換を考慮しデーター件数がすくない場合は処理しない。
                            /*
                            s_DevidOrderOn = Integer.parseInt(st[26]);
                            if(s_DevidOrderOn>4){
                                s_DevidOrderOn=0;
                            }
                            */
                            // TODO marge ２皿対応
                            s_DoublePlate = Integer.parseInt(st[26]);
                        }else{
                            s_DevidOrderOn =0;
                            // TODO marge ２皿対応
                            s_DoublePlate = 0;
                        }
                        //add 20180612 分轄注文を実施。分轄単位を指定

                        //ver77 品切れ時に品切れ表示文字を出す商品を設定
                        //priceは現在使用していないので、実験ではここに値をセットして使用する
                        s_sinagiredisp=this.ParseInt(st[12]);

                        if (st[17] != null) {
                            s_path = st[17].replaceAll("img", "upload");
                            // _glog.log(" Path>>"+s_path);
                        }

                        key = (s_order - 1) + (s_page - 1) * ORDER_MAX;
                        if (key >= 0 && key < COUNT) {
                            mNIid[key] = s_id;
                            mNIname[key] = s_name;
                            mNImemo[key] = s_memo;
                            mNIpage[key] = s_page;
                            mNIorder[key] = s_order;
                            mBmpPath[key] = s_path;
                           /* if (BmpFlag == 1) {//画像データー更新要求があった場合は画像も全て更新
                                mNIbmp[key] = Setbmp(s_path);
                            }*/
                            mNISelf[key] = s_self;
                            mNIUnit[key] = s_unit;
                            mNIChumon[key] = s_chuumon;
                            // _glog.log(key +":"+mNIpage[key]+"//"+mNIorder[key]);
                            mNIPrice[key] = s_price;
                            mNIZaiko[key] = s_zaiko;
                            mNIWasabi[key] = s_wasabi;
                            _kaikeiCat[key] = s_kaikeicat;


                            //ver77 品切れ時に品切れ表示文字を出す商品を設定
                            mNISinagireDisp[key]=s_sinagiredisp;

                            //add 20171103 option表示用のフラグ
                            mNIOptSel[key]= s_OptSel;

                            //_glog.log(key+": <mNIOptSel> "+mNIOptSel[key]);
                            //add 20171103 option表示用のフラグ

                            //add 20180612 分轄注文を実施。分割注文時２以上なら分轄して注文
                            mDevidOrderOn[key]=s_DevidOrderOn;
                            // TODO marge ２皿対応
                            mDoublePlate[key] = s_DoublePlate;

                            // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
                            // mFoodStand[key] = s_FoodStand;
                            i++;
                        }
                    }
                }
            }
            in1.close();
            in2.close();
            br.close();

            // TODO　add 20210817 原宿店屋台対応
            this.setmFoodStand();


        } catch (FileNotFoundException e) {
            _glog.log("ERR: <NetaInfo>not found");
        } catch (IOException e) {
            _glog.log(": <NetaInfo> Set_Data()");
        } catch (NumberFormatException e) {
            _glog.log("SETNETAERR　!! NumberFormatException>>" + e.toString() + key + ":" + mNIid[key] + mNIname[key] + "<" + mNIZaiko[key] + ">");
        } catch (ArrayIndexOutOfBoundsException e) {
            _glog.log("setnetaERR !!ArrayIndexOutOfBoundsException>>" + e.toString());
        } catch (NullPointerException e) {
            _glog.log("setnetaERR !! setnetaERR>>" + e.toString());
        } catch (PatternSyntaxException e) {
            _glog.log("setnetaERR ! PatternSyntaxException>>" + e.toString());
        } catch (Exception e) {
            _glog.log("setnetaERR !! Exception>>" + e.toString());
        }

    }


    /****************************************************************
     *以下ipad版からの追加要素
     *  NETA_MAX=COUNT アンドロイド既存コードとの互換性のために設定
     *****************************************************************/
    /**
     * ID検索
     *
     * @author ys
     * @version 20160304
     * @since 20160304
     */
    public int serchID(int netaID) {
        int ret = -1;
        int i = 0;
        for (i = 0; i < COUNT; i++) {
            if (netaID == mNIid[i]) {
                return i;
            }
        }
        return ret;
    }

    /**
     */
//=======================================

//=======================================
 /*   public void printTkaikeiCatInfo() {
        int i = 0;
        for (i = 0; i < MAX_T_NAME; i++) {
            _glog.log("kaikeiCat:" + _TkaikeiCatName[i] +" price:" +_TkaikeiCatPrice[i]);
        }
    }*/
//=======================================

    //=======================================
    public void cleraTkaikeiCatInfo() {
        int i;
        for (i = 0; i < MAX_T_NAME; i++) {
            _TkaikeiCatName[i] = "cat" + i;

            //add 20180816
            _TkaikeiCatPrice[i]=0;
        }
    }

    // TODO marge ２皿対応
    public int getDoublePlateFlag(int key){
        _glog.log("★★getDoublePlateFlag  _kaikeiCat:" + mDoublePlate[key]);
        _glog.log("★★getDoublePlateFlag  _kaikeiCat:" +  _kaikeiCat[key]);

        if (key >= 0 && key < COUNT) {
            // if(mDoublePlate[key] == 200 || mDoublePlate[key] == 220 || mDoublePlate[key] == 242 ) {
<<<<<<< HEAD
            // TODO　20211229 ２００円皿登録方法の変更検討
            if(mDoublePlate[key] == 220 || mDoublePlate[key] == 242 || mDoublePlate[key] == 264 ) {
                return 1;
            }else{
                return 0;
=======
            // TODO　20220103 ２００円皿登録方法の変更検討
            if(_kaikeiCat[key] == 0) {
                // if (mDoublePlate[key] == 220 || mDoublePlate[key] == 242 || mDoublePlate[key] == 250 || mDoublePlate[key] == 264) {
                // TODO　20221 0228 １皿　１１５円対応（暫定）#32
                if (mDoublePlate[key] == 220 ||mDoublePlate[key] == 230 ||  mDoublePlate[key] == 242
                        || mDoublePlate[key] == 250 || mDoublePlate[key] == 264) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
               return 0;
>>>>>>> bdd96031fb7465e981103be81608750222a8869a
            }
        } else {
            return 0;
        }
    }
    // TODO marge ２皿対応

    // TODO 20210703  marge ２皿対応
    public int getDoublePlate(int key){
        if (key >= 0 && key < COUNT) {
            return  mDoublePlate[key];
        } else {
            return 0;
        }
    }
    // TODO 20210703  marge ２皿対応


    // TODO　add 20210817 原宿店屋台対応 　メッセージを点滅する形で検討
    public int getFoodStand(int key){
        if (key >= 0 && key < COUNT) {
            // TODO　add 20210817 debugで強制で１を返す　下記削除してコメントアウトを外すこと
            // return 1; // for debug
            return mFoodStand[key];
        } else {
           return 0;
        }
    }

    // TODO　add 20211027 原宿店屋台対応  ICE商品の区分
    public int getFoodStandItemType(int key){
        if (key >= 0 && key < COUNT) {
            return mFoodStandItemType[key];
        } else {
            return 0;
        }
    }
    // TODO　add 20211027 原宿店屋台対応  ICE商品の区分





    //=======================================
    //kaikei区分の名称を返す。
//=======================================
    public String getTkaikeiCatInfo(int x) {

        String str = null;
        if (x > 0 && x < MAX_T_NAME) {
            str = _TkaikeiCatName[x];
        }
        return str;
    }
    //=======================================
    //kaikei区分の金額を返す
//=======================================
    // public float getTkaikeiCatPrice(int x) {
    public int getTkaikeiCatPrice(int x) {
        // float f = 0;
        int val=0;
        if (x > 0 && x < MAX_T_NAME) {
            val = _TkaikeiCatPrice[x];
        }
        return val;
    }

    //=============================================================
    //up:20180816
    //会計区分情報のセット
    //実験用に金額情報を追加
    //==============================================================
    public void setTkaikeiCatInfo(String fileName) {
        int i = 0;
        int x = 0;
        String arr[] = null;
        FileReader filereader = null;
        BufferedReader br = null;

        // _glog.log( "setTkaikeiCatInfo called:" + fileName);
        cleraTkaikeiCatInfo();
        File file = new File(_gFileDir + "/" + fileName);
        String str = "";
        try {
            //FileReader filereader = new FileReader(file);
            //BufferedReader br = new BufferedReader(filereader);
            filereader = new FileReader(file);
            br = new BufferedReader(filereader);
            str = "";
            // while (str != null) {
            while (true) {//change 20181123
                str = br.readLine();//次の１行を読む
                if (str == null) {
                    br.close();
                    filereader.close();
                    //return;
                    break;
                }
                // if (str.indexOf(",") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
                if(str.contains(",")){
                    arr = str.split(",");
                    x = ParseInt(arr[0]);//区分番号
                    if (x > 0 && x < MAX_T_NAME) {
                        _TkaikeiCatName[x] = arr[1];
                        //add 20180816  値段情報を取得
                        if(arr.length > 2) {
                            //_TkaikeiCatPrice[x] = ParseFloat(arr[2]);
                            _TkaikeiCatPrice[x] = ParseInt(arr[2]);
                        }
                    }
                }
            }
       /* } catch (FileNotFoundException e) {
            _glog.log("ERR:setTkaikeiCatInfo:" + e.toString());*/
        } catch (IOException e) {
            _glog.log("ERR:setTkaikeiCatInfo:" + e.toString());
        } catch (Exception e) {
            _glog.log("ERR:setTkaikeiCatInfo:" + e.toString());
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

    /**************************************************************
     * local neta info
     *****************************************************************/
    public void clearStrSubInfo() {
        int i=0;
        for(i=0; i<COUNT; i++){
            mNIname_sub1[i] = "";
            mNIname_sub2[i] = "";
            mname_sparate[i]=0;
        }
    }
    //================================================================
//商品コードが重複しない事が前提
//up2014 1115 折り返しコード確認の処理を追加
//================================================================
    public void setLocalNetaName(String filename,localStr  local) {

        //int i = 0;
        int netaCode = 0;
        int order =0;
        String arr[] = null;
        String arr2[] = null;
        File file = new File(_gFileDir + "/" + filename);
        String str = "";
        String str2 = "";
        clearStrSubInfo();

        FileReader filereader = null;
        BufferedReader br = null;

        try {
            //if (file.exists()==false){
            if (!file.exists()){
                _glog.log("setLocalNetaName not exit fail:"+filename);
                return;
            }
            filereader = new FileReader(file);
            br = new BufferedReader(filereader);
            str = "";
            //一行読み込み
            // while (str != null) {
            while (true) {
                str = br.readLine();
                if (str == null) {
                    br.close();
                    filereader.close();
                    // return;
                    break;
                }
                // _glog.log( str);
                //-------------------------
                //if (str.indexOf(",") != -1 ) {//文字列に区切り文字が入っていない場合はスキップする。
                if(str.contains(",")){
                    arr = str.split(",");
                    if(arr.length >= local.getLcalMax()+2) {
                        netaCode = Integer.parseInt(arr[0]);
                        order=this.serchID(netaCode);

                        if(order>=0 && order < this.COUNT){
                            str2=arr[local.getLcalKey()+2];
                            // if(str2.indexOf("'")  != -1){
                            if(str2.contains("'")){
                                arr2 = str2.split("'");
                                mNIname_sub1[order] =  new String(arr2[1]);
                                mNIname_sub2[order] =  new String(arr2[0]);
                                mname_sparate[order]=1;
                            }
                            mNIname[order] =  new String(arr[local.getLcalKey()+2]);
                        }

                    }
                }

            }
        } catch (Exception e) {
            _glog.log("ERR:setLocalNetaName:" + e.toString());
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

    public int GetLocalNeta_sparate(int key) {
        if (key >= 0 && key < COUNT) {
            return mname_sparate[key];
        } else {
            return -1;
        }
    }
    public String GetLocalStrName1(int key){
        if(key>=0 && key < COUNT){
            return  mNIname_sub1[key];
        }else {
            return "";
        }
    }
    public String GetLocalStrName2(int key){
        if(key>=0 && key < COUNT){
            return  mNIname_sub2[key];
        }else {
            return "";
        }
    }



    //--- ver100 add20171001 order時 option機能---
  /*public int GetOrderOpt(int key){


      return 0;
  }*/

    //=============================================================
    //20180615
    //for recommend
    //itemID,jumpID,html_name,reserve1,reserve2,reserve3,,reserve4,reserve5,
    //itemIDが０の場合は処理しない。
    //jumpIDが０の場合はjumpしない
//==============================================================
    public void initRecommend(){
        int i=0;
        gRecommendHtml= new String[RECOMMEND_MAX];
        gRecommendNetaId = new int[RECOMMEND_MAX];
        gRecommendJumpId = new int[RECOMMEND_MAX];
    }

    public void ClearRecommend(){
        int i=0;
        for(i=0;i<RECOMMEND_MAX;i++) {
            gRecommendHtml[i]="";
            gRecommendNetaId[i]=0;
            gRecommendJumpId[i]=0;
        }
    }
    //見つかった　０以上　見つからなかった　−１
    public int serchRecommendNetaId(int srcId){
        int i=0;
        for(i=0;i<RECOMMEND_MAX;i++){
            if(srcId == gRecommendNetaId[i]){
                return i;
            }
        }
        return -1;
    }

    public int getRecommendJumpId(int i){
        if(i>=0 && i<RECOMMEND_MAX){
            return gRecommendJumpId[i];
        }
        return -1;
    }

    public int getRecommendNetaId(int i){
        if(i>=0 && i<RECOMMEND_MAX){
            return gRecommendNetaId[i];
        }
        return -1;
    }

    public String getRecommendHtml(int i){
        if(i>=0 && i<RECOMMEND_MAX){
            return gRecommendHtml[i];
        }
        return "";
    }

    /*public void printRecommend(){
        int i=0;
        for(i=0;i<RECOMMEND_MAX;i++){
            _glog.log("netaId:"+gRecommendNetaId[i]+" jumpId:"+gRecommendJumpId[i]+" html:"+gRecommendHtml[i]);
        }
    }*/


    public void setRecommendItem(String fileName) {
        int cc = 0;
        int x = 0;
        int arr_len=0;
        String arr[] = null;
        FileReader filereader = null;
        BufferedReader br = null;

        _glog.log( "setRecommendItem called:" + fileName);
        ClearRecommend();
        File file = new File(_gFileDir + "/" + fileName);
        String str = "";
        try {
            filereader = new FileReader(file);
            br = new BufferedReader(filereader);

            str = "";
            //while (str != null) {
            while (true) {
                str = br.readLine();//次の１行を読む
                //_glog.log( ">>" + str);
                if (str == null) {
                    // br.close();
                    // filereader.close();
                    //return;
                    break;
                }
                //if (str.indexOf(",") == -1) {//文字列に区切り文字が入っていない場合はスキップする。
                if(str.contains(",")){
                    arr = str.split(",");
                    arr_len=arr.length;
                    // _glog.log("arr_len:"+arr_len);
                    if(arr_len>3) {
                        gRecommendNetaId[cc] = ParseInt(arr[0]);
                        gRecommendJumpId[cc] = ParseInt(arr[1]);
                        gRecommendHtml[cc] = arr[2];
                        cc++;
                    }
                }
            }
            // br.close();
            // filereader.close();
        } catch (IOException e) {
            _glog.log("ERR:setTkaikeiCatInfo:" + e.toString());
      /*  } catch (Exception e) {
            _glog.log("ERR:setTkaikeiCatInfo:" + e.toString());
        } catch (FileNotFoundException e) {
            _glog.log("ERR:setTkaikeiCatInfo:" + e.toString());*/
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
        // printTkaikeiCatInfo();
    }

    /****************************************************************
     * util
     *****************************************************************/
//Integer.parseIntのラッパー
    // TODO 20210829 TAG_MAXを９に増やす
    private int ParseInt(String s){
        int i =0;
        //if(s != null && s != ""){
        if(s !=null && !s.equals("")){
            try {
                i = Integer.parseInt(s);
            }catch (NumberFormatException e){
                // 数字が含まれていない場合
                _glog.log("ParseInt:" + e);
                return -1;
            }
        }
        return i;
    }

    /*
    private int ParseInt(String s){
        int i =0;
        //if(s != null && s != ""){
        if(s !=null && !s.equals("")){
            i = Integer.parseInt(s);
        }
        return i;
    }
     */

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



    //  TODO add 20210913 get QRINFO
    public Bitmap getQrBmp(){
        String f_name = _gFileDir.toString() + "/qr_info.png";
        _glog.log("getQrBmp:" + f_name);
        File subfile = new File(f_name);
        if (!subfile.exists()) {
            _glog.log("getQrBmp not exits");
            return Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();//この値をtrueにすると実際には画像を読み込まず、画像のサイズ情報だけを取得することができます。
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(subfile.toString(), options);

        // int scaleW = (options.outWidth /  300 + 1);
        // int scaleH = (options.outHeight / 300 + 1);
        //  TODO add 20220318 押上店向けQRサイズ変更
        int scaleW = (options.outWidth /  230 + 1);
        int scaleH = (options.outHeight / 230 + 1);

        options.inJustDecodeBounds = false;//今度は画像を読み込みたいのでfalseを指定
        int scale = Math.max(scaleW, scaleH);//縮尺は整数値で、2なら画像の縦横のピクセル数を1/2にしたサイズ。3なら1/3にしたサイズで読み込まれます。
        options.inJustDecodeBounds = false;//今度は画像を読み込みたいのでfalseを指定
        options.inSampleSize = scale;//先程計算した縮尺値を指定.これで指定した縮尺で画像を読み込めます。もちろん容量も小さくなるので扱いやすいです。

        Bitmap bmp = BitmapFactory.decodeFile(subfile.toString(), options);
        return bmp;
    }
    //  TODO add 20210913 get QRINFO

    //  TODO add 20211028 FOR TEST リリース時コメントアウトする事
    public void testPrintPageItem(int page){
        if(this.PAGE_MAX > page && page >= 0){
            _glog.log("testPrintPageItem page:" + page);
            for(int i = 0; i < this.ORDER_MAX; i++) {
                int offset = i + (this.ORDER_MAX * (page - 1));
                _glog.log("id:" +  mNIid[offset] + " name:"+  mNIname[offset]  + " img:"+ mBmpPath[offset]);
            }
        }
    }

}


