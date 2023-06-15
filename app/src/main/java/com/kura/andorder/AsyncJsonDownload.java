package com.kura.andorder;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ファイルをWebからダウンロードするクラス
 * @since ver293
 */
public class AsyncJsonDownload extends AsyncTask<String, Void, JSONObject> {

    private String urlString;
    private JSONObject requestJson;

    private URL url;
    private HttpURLConnection urlConnection;

    /**
     * ファイルをWebからダウンロードするクラス
     * @param url ダウンロード先のURL
     * @param json POｓTするJson
     * @return
     * @since ver293
     */
    public AsyncJsonDownload(String url,JSONObject json) {
        urlString = url;
        requestJson = json;
    }

    /**
     * ファイルをWebからダウンロードする非同期メソッド
     * @param
     * @return
     * @since ver293
     */
    @Override
    protected JSONObject doInBackground(String... url) {
        InputStream stream = null;
        try{
            connect();
            OutputStream outputStream = urlConnection.getOutputStream();
            PrintStream ps = new PrintStream(urlConnection.getOutputStream());
            ps.print(requestJson.toString());
            ps.close();
            outputStream.close();

            if(urlConnection.getResponseCode()!=200){
                throw new Exception("エラーレスポンスが返ってきました");
            }
            stream = urlConnection.getInputStream();
            StringBuffer sb = new StringBuffer();
            String line = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            Log.d("responceJson(string)",sb.toString());
            return new JSONObject(sb.toString());

        }catch(Exception e) {
            Log.d("getJson", "ConnectError:" + e.toString());
        }finally {
            if(stream != null){
                try {
                    stream.close();
                }catch(Exception e){
                    Log.d("getJson", "ConnectError:" + e.toString());
                }
            }
            if(urlConnection!=null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    /**
     * ファイルのダウンロードメソッド
     * @param
     * @return
     * @since ver293
     */
    private void connect() throws Exception {
        url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout(5000);
        urlConnection.setConnectTimeout(5000);
        //ヘッダーにContent-Typeを設定する
        urlConnection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
        //HTTPのメソッドをPOSTに設定する。
        urlConnection.setRequestMethod("POST");
        //リクエストのボディ送信を許可する
        urlConnection.setDoOutput(true);
        //レスポンスのボディ受信を許可する
        urlConnection.setDoInput(true);
        urlConnection.connect();
    }
}