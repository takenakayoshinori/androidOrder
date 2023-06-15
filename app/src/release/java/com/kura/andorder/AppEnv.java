package com.kura.andorder;

/**
 * 環境固有設定 - リリースビルド
 *
 * 下記ファイルとの整合性を維持してください
 * app/src/debug/java/com/kura/andorder/AppEnv.java
 */
public class AppEnv {
    private AppEnv() {};

    /** スマホ会計アプリ識別名 **/
    public static final String SP_CHECK_APP_NAME = "check";

    /** スマホ会計コンテンツZIP **/
    public static final String SP_CHECK_ZIP_URL = "http://192.168.11.5:13000/client/check.zip";
    /** スマホ会計URL**/
    public static final String SP_CHECK_API_SERVER_URL = "http://192.168.11.5:13000/selfcheck";
    /** スマホ会計APIのparamsの値**/
    public static final String SP_CHECK_INITPAGE_VERSION = "v1";
    public static final String SP_CHECK_CANCEL_VERSION = "v1";
    public static final String SP_CHECK_CALL_VERSION = "v1";
    /** スマホ会計APIのbodyの値**/
    public static final String ANDORDER_TERMINAL_KIND_CODE = "\"02\"";

    /**スマホ会計APIのレスポンスの値**/
    public static final int CHECK_STATUS_OK = 0;
    public static final int CHECK_STATUS_SPCHECK_NOT_AVAILABLE = 1;
    public static final int CHECK_STATUS_CHECKING = 2;
    public static final int CHECK_STATUS_END = 9;
    public static final int RESULT_SECCEED = 0;
    public static final int RESULT_FAILED = 1;

}
