<!-- 以下は不要であれば削除すること

##  CHANGELOGの原則
- 機械的に生成するのではなく人間の手で書く
- 各セクションへのリンクが容易にできる
- 1つのバージョンごとに1つのサブセクションを作る
- リリースは新しいものが上に来るようにする
- 日付のフォーマットは YYYY-MM-DD で書く
- Semantic Versioning に従うかは明示的に表明する
- 各バージョンのセクションは次の原則に従うべき
- 上記のフォーマットの日付を付与する

以下のようにグループ分けして表記する
- Added 新機能
- Changed 既存機能の変更
- Deprecated 将来的に削除される機能
- Removed 削除された機能
- Fixed バグフィックス
- Security 脆弱性修正のためユーザーにアップデートを促す場合
-->

// TODO NTTデーター関西のスマホでチェック機能にマージさせる事

ver 414
- Added 新機能
  200円皿警告機能追加　全店適用

ver 334
 無添くら対応検証

ver 319
 ver297(NTT製自動会計、アンケート、QRコード表示機能)のマージ
  →各機能の追加、各機能のONOFFコマンドの追加、zipインストールコマンド追加、起動時・Tickerループ内にzip取得処理を追加
  
  自動会計ONコマンド
  echo 8011,602,0,0,0,tableNum, | nc 192.168.11.1 11111

  自動会計OFFコマンド
  echo 8011,603,0,0,0,tableNum, | nc 192.168.11.1 11111
  
  アンケートONコマンド
  echo 8011,600,0,0,0,tableNum, | nc 192.168.11.1 11111
  
  アンケートOFFコマンド
  echo 8011,601,0,0,0,tableNum, | nc 192.168.11.1 11111
  
  QRコードONコマンド
  echo 8011,981,0,0,0,tableNum, | nc 192.168.11.1 11111
  
  QRコードOFFコマンド
  echo 8011,982,0,0,0,tableNum, | nc 192.168.11.1 11111
  
  zipインストールコマンド
  echo 8011,779,0,0,0,tableNum, | nc 192.168.11.1 11111
 
 bt_main11をQRコード表示ボタンに変更
 くら製会計ONコマンド受信時、NTT製自動会計をOFFにするように変更
 同様にNTT製自動会計ON時、くら製会計はOFFにするように変更
 getSFlagsState2の送信フラグを自動会計、QRコード、アンケートの使用フラグに変更
 自動会計・アンケートをデフォルト設定でOFFに変更
 flashing画面表示部分をコメントアウト・コマンド101受信時、QRコード画面を最初に表示するように変更
 help画面で、二重になっているボタンの背景が残る不具合の修正

ver318
//短いタッチは処理せずに捨てるsettingを追加
 private int gTOUCH_TIME_THRESHOLD_CNT;
 private int TOUCH_TIME_THRESHOLD = 10;
 private int TOUCH_TIME_THRESHOLD_IMG = 10;
 private int TOUCH_TIME_THRESHOLD_CALL = 10;
 private int TOUCH_TIME_THRESHOLD_KAIKEI = 10;
 private int TOUCH_TIME_THRESHOLD_RIREKI = 10;

ver317
flashViewの画像をupload.zipから取得するように変更
 /upload/flashView.jpg

 OpningMovが存在しない場合のバグに対応

 OpningMovのclose時間はtMOV2_backで客席端末をgrep




ver316
opning mov
firest order
udpの定期ステータースの内容を変更


ver311~
udpの定期ステータースの内容を変更

ver300~
起動時の音量調整

setup時に音量MAXに設定

無料ガリ初回オーダー機能
仕様

firstFreeOrderUseFlagがONの場合に実施
案内時に無料ガリの必要、不要を問い合わせ
注文した場合は、無料ガリとしてオーダーされる。
無料ガリと、有料ガリは事前に商品登録が必要。またタッチパネル側にも無料ガリ商品コード設定
　コードの登録が必要です。（下記参照）

設定

## [ver300] - 2020-0523

無料ガリ注文機能ON
echo '8010,520,1,1,1,9,' | nc 192.168.11.1 11111


無料ガリ注文機能OFF
echo '8010,521,1,1,1,9,' | nc 192.168.11.1 11111


無料ガリ商品コード設定
echo '8010,522,itemCode,0,0,0,' | nc 192.168.11.1 11111


無料ガリORDER OPEN（test用）
echo '8011,522,0,0,0,tableNum,' | nc 192.168.11.1 11111


無料ガリORDER CLOSE（test用）
echo '8011,523,0,0,0,tableNum,' | nc 192.168.11.1 11111


## [ver292] - 2020-03023



## [ver285] - 2020-02-04
充電監視機能

## [ver278] - 2020-01-16
浅草ROX向けレイアウト変更
特殊ゲーム機能の実装（未検証）

## [ver261] - 2019-12-16
780のudpのコマンドで案内有効を実施した際、自動会計有効のフラグが立ってしまうバグを修正

## [ver260] - 2019-12-15
webView起動時にコネクトできない場合にreloadするように変更

## [ver257] - 2019-11-11
強制注文制限上書き機能の追加

## [ver255] - 2019-10-11
android studio ver 3.2.1に対応

## [ver245] - 2019-06-14
- スマホオーダー対応で指定の動画を再生し、ビックラポンを再生できるように対応
   //add 20190614 スマホオーダー対応　ゲーム
        if(_gCurrentGame == 0 && _gSmartGameCount > 0) {
            if (FLAG_GAME_TEST_ON == 2) {
                _gCurrentGame = 2;//win
            } else {
                _gCurrentGame = 1;//loose
            }
            _gSmartGameCount--;//プールしているゲームしているゲームを減らす。
            _gDrawGameFlag = F_VIEW_STATE_OPEN_CMD;
        }
   





## [ver239] - 2019-06-07
- Changed 開発環境をandroid2.2からandroid3.2に変更
