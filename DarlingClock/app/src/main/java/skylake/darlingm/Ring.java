package skylake.darlingm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.security.Provider;

/**
 * Created by YIN on 3/16/2016.
 */

public class Ring extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;
    public IBinder onBind(Intent intenr) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId){

        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        String state = intent.getExtras().getString("extra");

        Log.e("Ring state: extra is ", state);
        assert state != null;
        switch(state){
            case "alarmOn":startId = 1; break;
            case "alarmOff":startId = 0; Log.e("state ID is ", state); break;
            default:startId = 0; break;
        }

        if(!this.isRunning && startId == 1){
            Log.e("no music ","and you want start");
            media_song = MediaPlayer.create(this, R.raw.say);
            media_song.start();
            this.isRunning = true;
            this.startId = 0;
        }

        else if(this.isRunning && startId == 0){
            Log.e("music ", "you want to end");
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            this.startId = 0;
        }

        else if(!this.isRunning && startId == 0){
            Log.e("no music", "want to end");
            this.isRunning = false;
            this.startId = 0;
        }

        else if(this.isRunning && startId == 1){
            Log.e("music", "want to start");
            this.isRunning = true;
            this.startId = 1;
        }

        else{
            Log.e("else ", "you may reach this");
        }

        return START_NOT_STICKY;
    }

    public void onDestroy(){
        Log.e("on Destory called", "ta da");
        super.onDestroy();
        this.isRunning = false;
    }
}
