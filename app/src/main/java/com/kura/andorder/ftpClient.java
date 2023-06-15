package com.kura.andorder;
import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
//import java.util.zip.ZipException;
import java.util.zip.ZipFile;
//import java.util.zip.ZipInputStream;
import java.io.*;



/**
 * FTPクライアント用の処理
 * 動作実績はcentos6のvsftpd.vsftpd.confの設定はgatePCの設定を参照のこと
 * 機能はダウンロードに限定
 */
public class ftpClient {
    static final int ftp_PORT = 21;
    static final String CRLF = "\r\n";
    private final String host_addr="192.168.11.1";
    private File _gbaseFile=null;
    private int _gFtpProcess;
    private String _gFtpProsessStr=null;
    private  logExtend _glog=null;

    /**
     * コンストラクタ
     */
    public ftpClient(File f,logExtend obj) {
        _gbaseFile=f;
        _glog=obj;
    }
    /**
     * getDataSize
     * vsftpdの処理依存。転用不可
     */
    public int getDataSize(String str) {
        String buf = "";
        String buf2 = "";
        int res=0;

        try {
            int index = str.indexOf("(");
            if (index != 0) {
                buf = str.substring(index + 1);
            } else {
                return -1;
            }

            index = buf.indexOf(" ");
            if (index != 0) {
                buf2 = buf.substring(0, index);//以降を除外
            } else {
                return -1;
            }

            _glog.log( "getDataSize called>" + buf2);
            res = Integer.parseInt(buf2);
        }catch (Exception e){
            _glog.log( "ERR:getDataSize" + e.toString());
        }

        return res;
    }

    /**
     * FTP転送のデーター転送で使用するポート番号の取得
     * vsftpdの処理依存。転用不可
     */
    public int getDataPort(String str) {
        String buf = "";
        String buf2 = "";
        String[] array;
        try {
            int index = str.indexOf("(");//（以前を除外
            if (index != 0) {
                buf = str.substring(index + 1);
            } else {
                return -1;
            }
            index = buf.indexOf(")");
            if (index != 0) {
                buf2 = buf.substring(0, index);//)以降を除外
            } else {
                return -1;
            }
            array = buf2.split(",");
            if (array.length == 6) {
                int h = Integer.parseInt(array[4]);
                int l = Integer.parseInt(array[5]);
                return (h * 256 + l);
            }
        }catch (Exception e){
            _glog.log( "ERR:getDataPor:" + e.toString());
        }
        return -1;
    }


    /**
     * FTPでのデーター取得
     * vsftpdの処理依存。転用不可
     */
    public int getFtpProcess(){
        return  _gFtpProcess;
    }
    public String getFtpProsessStr(){
        return _gFtpProsessStr;
    }
    /**
     * FTPでのデーター取得
     * vsftpdの処理依存。転用不可
     *@return filure:-1 success:data size
     */
    public int ftpGet(String getFileName,String outFileName) {

        Socket sock=null;
        int dataSize = 0;
        BufferedInputStream in=null;
        BufferedOutputStream out=null;
        Socket sock_data=null;
        BufferedInputStream in_data=null;
        byte[] read_buf = new byte[1000];
        String sendData = "";
        byte[] send_buf = null;

        _glog.log( "get:"+getFileName);
        _glog.log( "out:"+outFileName);
        _gFtpProcess=0;
        int data_port = 0;
        try {
            sock = new Socket(host_addr, ftp_PORT);
            in = new BufferedInputStream(sock.getInputStream());
            out = new BufferedOutputStream(sock.getOutputStream());

            in.read(read_buf);
            _gFtpProcess=0;
            _gFtpProsessStr="acsess ftp server";

            sendData = "USER kura" + CRLF;
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();

            in.read(read_buf);
            // _glog.log( "2:" + new String(read_buf));

            sendData = "PASS sys001" + CRLF;
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();
            in.read(read_buf);
            _glog.log( "3:" + new String(read_buf));

            sendData = "TYPE I" + CRLF;//バイナリーモード
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();
            Arrays.fill(read_buf, (byte) 0);
            in.read(read_buf);
            _glog.log( "TYPE I:" + new String(read_buf));


            sendData = "PASV" + CRLF;
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();
            Arrays.fill(read_buf, (byte) 0);
            in.read(read_buf);
            data_port = this.getDataPort(new String(read_buf));
            _glog.log( ">>PASV GET_PORT:" + data_port);


            //----------------------------add 2013 1127
            sock_data = new Socket(host_addr, data_port);
            in_data = new BufferedInputStream(sock_data.getInputStream());


            sendData = "RETR "+ getFileName + CRLF;//change:20160222
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();
            Arrays.fill(read_buf, (byte) 0);
            in.read(read_buf);
            dataSize = this.getDataSize(new String(read_buf));
            _glog.log( "RETR:" + dataSize);
            _gFtpProcess=0;
            _gFtpProsessStr="data size:"+dataSize/1024+"kbyte";

            //データー取得開智
            int k = 0;
            int cnt = 0;
            FileOutputStream getfile = new FileOutputStream(outFileName);
            while (true) {
                k = in_data.read(read_buf);
                cnt = cnt + k;
                _gFtpProsessStr=cnt/1024+"/"+dataSize/1024+"kbyte";
                if (k == -1) {
                    break;
                }
                getfile.write(read_buf, 0, k);
            }

            //QUITの送信を追加
            sendData = "QUIT" + CRLF;
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();
            out.close();

            in_data.close();
            getfile.close();

            //closeを追加。エラー処理の場合のcloseも必要か？
            sock.close();
            sock_data.close();

            //データーサイズチェック
            if (cnt + 1 == dataSize) {//データーサイズが同じ場合
            } else {
                _gFtpProcess=-1;
                _gFtpProsessStr="ERR :get data\n size not much";
            }

            if(getFileName.indexOf(".zip") == -1 ){
                return dataSize;
            }else {
                _gFtpProcess=0;
                _gFtpProsessStr="unzip process";

                File  zipfile = new File(outFileName);
                String str[]=outFileName.split(".zip");
                File  zipOut= new File(str[0]);

                //add 20161120
                ForceDelDir(zipOut);
                //add 20161120


                unzip(zipfile,zipOut);
                zipfile.delete();//add 20160222
                _gFtpProcess=100;
            }
        } catch (UnknownHostException e) {
            _glog.log( "ftpGet ERR:UnknownHostException:" + e.toString());
            e.printStackTrace();
            _gFtpProcess=-1;
            _gFtpProsessStr="ERR :"+ e.toString();
            return -1;
        }catch(java.lang.NullPointerException e){
            _glog.log( "ftpGet ERR:NullPointerException:" + e.toString());
            e.printStackTrace();
            _gFtpProcess=-1;
            _gFtpProsessStr="ERR :"+ e.toString();
            return -1;
        } catch (IOException e) {
            _glog.log( "ftpGet ERR:IOException:" + e.toString());
            e.printStackTrace();
            _gFtpProcess=-1;
            _gFtpProsessStr="ERR :"+ e.toString();
            return -1;
        } catch (Exception e) {
            _glog.log( "ftpGet ERR:Exception:" + e.toString());
            e.printStackTrace();
            _gFtpProcess=-1;
            _gFtpProsessStr="ERR :"+ e.toString();
            return -1;
        }
        return dataSize;
    }

    /**
     * zip 解凍
     * vsftpdの処理依存。転用不可
     */

    public void unzip(File file,File OutFile) throws IOException {

        _glog.log( "unzip called> "+ file);
        _glog.log( "unzip called? "+ OutFile);
        String fileName = file.getName();
        // Zip ファイルから ZipEntry を一つずつ取り出し、ファイルに保存していく。
        ZipFile zipFile = new ZipFile(file);
        _glog.log( "zipFile  "+zipFile);

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        _glog.log("entries :" + entries.toString()  );
        while (entries.hasMoreElements()) {
            ZipEntry ze = entries.nextElement();

            _glog.log("entries :" + ze.toString() );
            // 出力先ファイル
            // File outFile = new File(OutFile, ze.getName());
            File outFile = new File(_gbaseFile, ze.getName());
            _glog.log("outFile :" + outFile.toString() );

            //すでに存在している場合に消去する？
            if (outFile.exists() == true) {
                if (outFile.isFile()) {
                    _glog.log("outFile delete :" + outFile.toString() );
                    outFile.delete();
                }
            }
            //すでに存在している場合に消去する？

            if (ze.isDirectory()) {
                // ZipEntry がディレクトリの場合はディレクトリを作成。
                outFile.mkdirs();
            } else {
                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                try {
                    // ZipFile から 対象ZipEntry の InputStream を取り出す。
                    InputStream is = zipFile.getInputStream(ze);
                    // 効率よく読み込むため BufferedInputStream でラップする。
                    bis = new BufferedInputStream(is);

                    if (!outFile.getParentFile().exists()) {
                        // 出力先ファイルの保存先ディレクトリが存在しない場合は、
                        // ディレクトリを作成しておく。
                        outFile.getParentFile().mkdirs();
                    }
                    // 出力先 OutputStream を作成。
                    bos = new BufferedOutputStream(new FileOutputStream(outFile));

                    // 入力ストリームから読み込み、出力ストリームへ書き込む。
                    int ava;
                    while ((ava = bis.available()) > 0) {
                        byte[] bs = new byte[ava];
                        // 入力
                        bis.read(bs);

                        // 出力
                        bos.write(bs);
                    }
                } catch (FileNotFoundException e) {
                    throw e;
                } catch (IOException e) {
                    throw e;
                } finally {
                    try {
                        if (bis != null)
                            // ストリームは必ず close する。
                            bis.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (bos != null)
                            // ストリームは必ず close する。
                            bos.close();

                    } catch (IOException e) {
                    }
                }
            }
        }
    }


    //=================================================
//ディテクトリ強制削除（再帰的に全て削除する。）
//=================================================
    static private void ForceDelDir(File f) {

        if (f.exists() == false) {
            return;
        }
        if (f.isFile()) {
            f.delete();
        }
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (int i = 0; i < files.length; i++) {
                ForceDelDir(files[i]);
            }
            f.delete();
        }
    }


    //=========================================================
    //
    //=========================================================
    public int ftpPut(String sendFileName,String localFileName) {

        int ret = 0;
        int dataSize = 0;

        Socket sock=null;//for FTP cmd connection
        BufferedInputStream in=null;//for FTP cmd connection IN
        BufferedOutputStream out=null;//for FTP cmd connection OUT
        Socket sock_data=null;//for FTP data connection
        BufferedOutputStream out_data=null;//FTPへのアプトプット用ストリーム
        FileInputStream fis=null;//転送するファイルの読み込みストリーム
        try {
            fis = new FileInputStream(localFileName);
        }catch (FileNotFoundException e) {
            return -1;
        }catch (SecurityException e){
            return -1;
        }

        byte[] read_buf = new byte[1000];
        String sendData = "";
        byte[] send_buf = null;


        _glog.log( "send:"+sendFileName);
        _gFtpProcess=0;

        int data_port = 0;
        try {
            sock = new Socket(host_addr, ftp_PORT);
            in = new BufferedInputStream(sock.getInputStream());
            out = new BufferedOutputStream(sock.getOutputStream());

            in.read(read_buf);
            _gFtpProcess=0;
            _gFtpProsessStr="acsess ftp server";
            sendData = "USER kura" + CRLF;
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();

            in.read(read_buf);

            sendData = "PASS sys001" + CRLF;
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();
            in.read(read_buf);
            _glog.log( "3:" + new String(read_buf));

            sendData = "TYPE I" + CRLF;//バイナリーモード
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();
            Arrays.fill(read_buf, (byte) 0);
            in.read(read_buf);
            _glog.log( "TYPE I:" + new String(read_buf));


            sendData = "PASV" + CRLF;
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();
            Arrays.fill(read_buf, (byte) 0);
            in.read(read_buf);
            data_port = this.getDataPort(new String(read_buf));
            _glog.log( ">>PASV GET_PORT:" + data_port);

            sock_data = new Socket(host_addr, data_port);
            out_data = new BufferedOutputStream(sock_data.getOutputStream());

            //=================ファイルの内容を読み込んで、転送する処理を書く？
            sendData = "STOR "+ sendFileName + CRLF;//change:20160222
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();
            Arrays.fill(read_buf, (byte) 0);
            int n = 0;
            //fileを読み込んで転送
            while ((n =fis.read(read_buf))>0) {
                out_data.write(read_buf,0,n);
            }
            //QUITの送信
            sendData = "QUIT" + CRLF;
            send_buf = sendData.getBytes();
            out.write(send_buf);
            out.flush();

            //20181115 finallyブロックで閉じるように変更
           /* out.close();
            out_data.close();
            fis.close();
            sock.close();
            sock_data.close();*/

            _gFtpProcess=100;

        } catch (UnknownHostException e) {
            _glog.log( "ftpGet ERR:UnknownHostException:" + e.toString());
            e.printStackTrace();
            _gFtpProcess=-1;
            _gFtpProsessStr="ERR :"+ e.toString();
            return -1;
        }catch(java.lang.NullPointerException e){
            _glog.log( "ftpGet ERR:NullPointerException:" + e.toString());
            e.printStackTrace();
            _gFtpProcess=-1;
            _gFtpProsessStr="ERR :"+ e.toString();
            return -1;
        } catch (IOException e) {
            _glog.log( "ftpGet ERR:IOException:" + e.toString());
            e.printStackTrace();
            _gFtpProcess=-1;
            _gFtpProsessStr="ERR :"+ e.toString();
            return -1;
        } catch (Exception e) {
            _glog.log( "ftpGet ERR:Exception:" + e.toString());
            e.printStackTrace();
            _gFtpProcess=-1;
            _gFtpProsessStr="ERR :"+ e.toString();
            return -1;
        }finally {
            try {
                if(out != null) {
                    out.close();
                }
                if(out_data != null) {
                    out_data.close();
                }
                if(sock != null) {
                    sock.close();
                }
                if(sock_data != null) {
                    sock_data.close();
                }
                fis.close();
            }catch (NullPointerException e) {
                _glog.log( "ERR:Exception:" + e.toString());
            }catch (IOException e){
                _glog.log( "ERR:Exception:" + e.toString());
            }

        }
        return dataSize;
    }











}



