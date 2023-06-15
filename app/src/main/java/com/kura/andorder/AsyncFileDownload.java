package com.kura.andorder;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * ファイルをWebからダウンロードするクラス
 * @since ver293
 */
public class AsyncFileDownload extends AsyncTask<String, Void, Boolean> {

    public Activity owner;
    private String urlString;
    private File outputPath;
    private String appName;
    private File appPath;

    private URL url;
    private URLConnection urlConnection;
    private InputStream inputStream;
    private FileOutputStream fileOutputStream;
    private byte[] buffer = new byte[1024];

    private ZipInputStream zipInputStream = null;
    private ZipEntry zipEntry = null;

	private logExtend _glog = new logExtend();

    /**
     * ファイルをWebからダウンロードするクラス
     * @param activity MainActivity
     * @param url ダウンロード先のURL
     * @param path ファイルの保存先ディレクトリ
     * @param app アプリ名称
     * @return
     * @since ver293
     */
    public AsyncFileDownload(Activity activity, String url, File path, String app) {
        owner = activity;
        urlString = url;
        outputPath = path;
        appName = app;
    }

    /**
     * ファイルをWebからダウンロードする非同期メソッド
     * @param
     * @return
     * @since ver293
     */
    @Override
    protected Boolean doInBackground(String... url) {
        try {
            connect();
        } catch (IOException e) {
            _glog.LogJson(owner,"【" + appName + "】Error getClientHtml ConnectError " + e.toString());
        }

        if (zipInputStream != null) {
            try {
                appPath = new File(outputPath + File.separator + appName);
                if (appPath.exists()) {
                    appPath.delete();
                }
                appPath.mkdir();

                while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                    File newfile = new File(outputPath + File.separator + zipEntry.getName());
                    new File(newfile.getParent()).mkdirs();
                    fileOutputStream = new FileOutputStream(newfile.getParent() + File.separator + newfile.getName());

                    int len;
                    while ((len = zipInputStream.read(buffer)) != -1) {
                        if (zipEntry.isDirectory()) {
                            // ディレクトリのみのエントリーがあった場合に問題が発生するためスキップ
                            continue;
                        }
                        fileOutputStream.write(buffer, 0, len);
                    }
                }

                zipInputStream.closeEntry();

            } catch(IOException e) {
                _glog.LogJson(owner,"【" + appName + "】Error getClientHtml " + e.toString());
                return false;
            }

        } else {
            _glog.LogJson(owner,"【" + appName + "】Error getClientHtml bufferedInputStream is null");
            return false;
        }

        try {
            fileOutputStream.close();
            fileOutputStream = null;
        } catch (IOException e) {
            _glog.LogJson(owner,"【" + appName + "】Error getClientHtml CloseError " + e.toString());
        }

        return true;
    }

    /**
     * ファイルのダウンロードメソッド
     * @param
     * @return
     * @since ver293
     */
    private void connect() throws IOException {
        url = new URL(urlString);
        urlConnection = url.openConnection();
        urlConnection.setReadTimeout(30000);
        urlConnection.setConnectTimeout(30000);
        inputStream = urlConnection.getInputStream();
        zipInputStream = new ZipInputStream(inputStream);
    }
}
