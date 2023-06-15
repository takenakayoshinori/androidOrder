package com.kura.andorder;
import android.media.AudioManager;
import android.media.MediaPlayer;

//2016/11/10 soundpoolを使わないように変更
//soundpoolはシステムのヒープ領域を使用するため、使用をやめる。
public class soundManager implements Runnable {
    private  logExtend _glog=null;
    private String _gHomeDir;

    //change20190518 sound max 変更
    // TODO add s20210829  ADD SOUND
    // private int MAX_SOUND=20;
    private int MAX_SOUND = 26;

    private String SoundPath[] = new String[MAX_SOUND];
    private int SoundOnFlag[] = new int[MAX_SOUND];

    //add 20191011 soundの中断機能検討
    private MediaPlayer gMpCurrent=null;
    private int gCurrentSoundState=0;

    /*private String SoundPath[] = new String[6];
    private int SoundOnFlag[] = new int[6];*/

    public soundManager(logExtend obj){
        this._glog=obj;
        _glog.log("soundManager called");
    }
    /**
     *
     */
    public void  setHomeDir(String buf) {
        _gHomeDir = buf;
        setSound();
        _glog.log("setHomeDir called:" + _gHomeDir );
    }
    /**
     *　サウンド登録
     */
    public void setSound(){
        SoundPath[0] = _gHomeDir + "wave1.wav";
        SoundPath[1] = _gHomeDir + "wave2.wav";
        SoundPath[2] = _gHomeDir + "wave3.wav";
        SoundPath[3] = _gHomeDir + "wave4.wav";
        SoundPath[4] = _gHomeDir + "arive1.wav";
        SoundPath[5] = _gHomeDir + "arive2.wav";

        //add 20190518
        SoundPath[6] = _gHomeDir + "extra1.wav";
        SoundPath[7] = _gHomeDir + "extra2.wav";
        SoundPath[8] = _gHomeDir + "extra3.wav";
        SoundPath[9] = _gHomeDir + "extra4.wav";

        //add 201905605
        SoundPath[10] = _gHomeDir + "extra5.wav";
        SoundPath[11] = _gHomeDir + "extra6.wav";
        SoundPath[12] = _gHomeDir + "extra7.wav";
        SoundPath[13] = _gHomeDir + "extra8.wav";
        SoundPath[14] = _gHomeDir + "extra9.wav";
        SoundPath[15] = _gHomeDir + "extra10.wav";
        SoundPath[16] = _gHomeDir + "extra11.wav";
        SoundPath[17] = _gHomeDir + "extra12.wav";
        SoundPath[18] = _gHomeDir + "extra13.wav";
        SoundPath[19] = _gHomeDir + "extra14.wav";

        // TODO add s20210829  ADD SOUND
        // TODO add 20210913 get QRINFO  extra13.wav
        SoundPath[20] = _gHomeDir + "extra15.wav";
        SoundPath[21] = _gHomeDir + "extra16.wav";
        SoundPath[22] = _gHomeDir + "extra17.wav";
        SoundPath[23] = _gHomeDir + "extra18.wav";
        SoundPath[24] = _gHomeDir + "extra19.wav";
        SoundPath[25] = _gHomeDir + "extra20.wav";

    }
    /**
     * 音を鳴らす
     */
    public void PlaySound(int val,int loop){
       /* if(val >=0 && val<6) {
            SoundOnFlag[val] = 1;
        }*/
        if(val >=0 && val < MAX_SOUND) {
            SoundOnFlag[val] = 1;
        }
    }


    /**
     * 音を鳴らす
     */
  public void PlaySoundtread(int val,int loop){
        _glog.log("PlaySound called");
         //MediaPlayer mp=new MediaPlayer();

      if(gMpCurrent != null) {
          try {
              // _glog.log("--------->gMpCurrent:" + gMpCurrent.isPlaying());
              if(gMpCurrent.isPlaying()==true){
                  gMpCurrent.stop();
                  gMpCurrent.reset();
                  gMpCurrent.release();
                  gMpCurrent=null;
                  // _glog.log("--------->gMpCurrent:stop");
              }
          }catch (Exception e){
              //_glog.log("gMpCurrent:" + e.toString());
          }
      }

         gMpCurrent=new MediaPlayer();
         //gCurrentSoundState=1;
        try {
            gMpCurrent.setAudioStreamType(AudioManager.STREAM_MUSIC);
            _glog.log("PlaySound:" + SoundPath[val]);
            gMpCurrent.setDataSource(SoundPath[val]);
           // _glog.log("PlaySound 2");

            gMpCurrent.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //_glog.log("PlaySound satart");
                    mp.start();
                }
            });
            //20190111
            gMpCurrent.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    _glog.log("setOnErrorListener called");
                    return false;
                }
            });

            // 終了を検知するリスナー
            gMpCurrent.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    _glog.log("setOnCompletionListener called");
                    mp.stop();
                    mp.reset();
                    mp.release();
                   // gCurrentSoundState=0;
                }
            });
            // _glog.log("PlaySound 3");
            gMpCurrent.prepareAsync();

        }catch (Exception e){
            _glog.log("ERR:PlaySound"+e.toString());
        }
    }



  /*  public void PlaySoundtread(int val,int loop){
        //_gsoundPool.play (SoundId[val],1.0f,1.0f,0,loop,1.0f);

        _glog.log("PlaySound called");
        MediaPlayer mp=new MediaPlayer();
        try {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            _glog.log("PlaySound 1");
            mp.setDataSource(SoundPath[val]);
            _glog.log("PlaySound 2");

            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    _glog.log("PlaySound satart");
                    mp.start();
                }
            });
            //20190111
            mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    _glog.log("setOnErrorListener called");
                    return false;
                }
            });

            // 終了を検知するリスナー
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    _glog.log("setOnCompletionListener called");
                    mp.stop();
                    mp.reset();
                    mp.release();
                }
            });
            _glog.log("PlaySound 3");
            mp.prepareAsync();

        }catch (Exception e){
            _glog.log("ERR:PlaySound"+e.toString());
        }
    }*/
    /**
     /********************************************************
     　RUN実行
     **************************************************************/
    @Override
    public void run() {
        int i=0;
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {//sleep

            }


            for (i = 0; i < MAX_SOUND; i++) {
                if (SoundOnFlag[i] == 1) {
                    SoundOnFlag[i]=0;
                    PlaySoundtread(i, 1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {//sleep
                    }

                }
            }
        }
    }


}
