package com.kura.andorder;


import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * JavascriptInterfaceを実行するクラス
 * @since ver293
 */
public class JSObject {
    private MainActivity main;
    private WebView wv;

    /**
     * ファイルをWebからダウンロードするクラス
     * @param wv WebViewオブジェクト
     * @param main MainActivity
     * @return
     * @since ver293
     */
    public JSObject(WebView wv, MainActivity main) {
        this.wv = wv;
        this.main = main;
    }

    /**
     * アンケート、自動会計のキャンセルメソッド
     * @since ver293
     */
    @JavascriptInterface
    public void cancel() {
        main.removeViewCancel();
    }

    /**
     * アンケートの回答メソッド
     * @since ver293
     */
    @JavascriptInterface
    public void surveyDone() {
        main.removeViewSurveyDone();
    }

    /**
     * QRコード表示中止メソッド
     */
    @JavascriptInterface
    public void removeQR() {
        main.removeViewQrCode();
    }
    @JavascriptInterface
	public void callDisp() {
        main.callViewDisp();
    }
    @JavascriptInterface
    public void setCall(){ main.setCallFlag();}
}
