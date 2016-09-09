package skylake.darlingm;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * Created by YIN on 3/16/2016.
 */
public class Clock_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Log.e("we are in the receiver.", "Yay!");
        String get_your_string = intent.getExtras().getString("extra");

        Log.e("what is the key?", get_your_string);

        Intent service_intent = new Intent(context, Ring.class);
        service_intent.putExtra("extra", get_your_string);
        context.startService(service_intent);

    }
}
