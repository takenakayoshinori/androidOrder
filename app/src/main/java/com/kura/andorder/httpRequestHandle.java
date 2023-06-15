package com.kura.andorder;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class httpRequestHandle implements Runnable {
    public Activity owner;
    private  logExtend _glog = null;

    private String gpostHost = "";
    private int gpostPort = 80; // tfm_node_app -> 5182
    private String gencoding = "utf-8";
    private String gendPoint = "";
    private Map<String, String> gheaders; // HTTPヘッダ(指定したければ)
    private String gjsonString = "";


    /**
     * コンストラクタ
     */
    public httpRequestHandle(logExtend obj){
        this._glog = obj;

        // とりあえず固定で
        gheaders = new HashMap<String, String>();
        gheaders.put("Content-Type", "application/json");
    }

    public void setPostRequest(String host, int port, String endPoint, String jsonString){
        gpostPort = port;
        gpostHost = host;
        gendPoint = endPoint;
        gjsonString = jsonString;
    }

    // public String post(String endpoint, String encoding, Map<String, String> headers, String jsonString) throws IOException {
    public String post() throws IOException {

        _glog.log("post called:" + gpostHost + " port:" + gpostPort + " msg:" + gjsonString);

        final int TIMEOUT_MILLIS = 1000 * 10;// タイムアウトミリ秒：0は無限
        final StringBuffer sb = new StringBuffer("");
        HttpURLConnection httpConn = null;
        BufferedReader br = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
           // URL url = new URL(endpoint);
            URL url = new URL("http", gpostHost , gpostPort, gendPoint );
            _glog.log("post url:" + url.toString());

            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setConnectTimeout(TIMEOUT_MILLIS);// 接続にかかる時間
            httpConn.setReadTimeout(TIMEOUT_MILLIS);// データの読み込みにかかる時間
            httpConn.setRequestMethod("POST");// HTTPメソッド
            httpConn.setUseCaches(false);// キャッシュ利用
            httpConn.setDoOutput(true);// リクエストのボディの送信を許可(GETのときはfalse,POSTのときはtrueにする)
            httpConn.setDoInput(true);// レスポンスのボディの受信を許可

            if (gheaders != null) {
                for (String key : gheaders.keySet()) {
                    httpConn.setRequestProperty(key, gheaders.get(key));// HTTPヘッダをセット
                }
            }
            OutputStream os = httpConn.getOutputStream();
            final boolean autoFlash = true;
            PrintStream ps = new PrintStream(os, autoFlash, gencoding);
            ps.print(gjsonString);
            ps.close();

            _glog.log("jsonString:" + gjsonString);

            final int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                is = httpConn.getInputStream();
                isr = new InputStreamReader(is, gencoding);
                br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                // If responseCode is not HTTP_OK
                _glog.log("res:" + responseCode);
            }

        } catch (IOException e) {
            throw e;
        } finally {
            // fortify safeかつJava1.6 compliantなclose処理
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
        return sb.toString();
    }

    @Override
    public void run() {
        try {
            this.post();
        }catch( IOException e){
            _glog.log("480:http post test:" + e);
        }

        /*
        // String postJson = "[{\"message\":{\"number\":\"2\",\"value\":\"Hello\"}}]";
        String postJson = "{\"number\":\"2\"}";
        Map<String, String> headers; // HTTPヘッダ(指定したければ)
        headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        try {
            this.post("/kura/api/:version/setting/", "utf-8", headers, postJson);
        }catch( IOException e){
            _glog.log("480:http post test:" + e);
        }
         */
    }
}
