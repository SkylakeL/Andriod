package skylake.darlingm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Clock extends AppCompatActivity {

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);
        update_text = (TextView) findViewById(R.id.update);
        final Calendar calendar = Calendar.getInstance();
        Button alarm_on = (Button) findViewById(R.id.alarm_on);
        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        final Intent myIntent = new Intent(this.context, Clock_Receiver.class);

        assert alarm_on != null;
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());
                int hour = alarm_timepicker.getHour();
                int min = alarm_timepicker.getMinute();
                String hourStr = String.valueOf(hour);
                String minStr = String.valueOf(min);

                if(hour > 12)
                    hourStr = String.valueOf(hour - 12);
                if(min < 10)
                    minStr = "0" + String.valueOf(min);

                set_alarm_text("Alarm On _ " + hourStr + " : " + minStr);
                myIntent.putExtra("extra", "alarmOn");

                pendingIntent = PendingIntent.getBroadcast(Clock.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        });

        assert alarm_off != null;
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_alarm_text("Alarm Off");
                alarm_manager.cancel(pendingIntent);
                myIntent.putExtra("extra", "alarmOff");
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void set_alarm_text(String str){
        update_text.setText(str);
    }
}
