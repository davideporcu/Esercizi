package davideporcu.broadcastflags;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int flagPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton view = findViewById(R.id.radio_flag_cancel_current);
        view.setChecked(true);
        flagPendingIntent = PendingIntent.FLAG_CANCEL_CURRENT;


    }

    public void startAlert(View view) {
        EditText text = findViewById(R.id.time);
        int delay = 0;
        try {
            delay = Integer.parseInt(text.getText().toString());

            Intent intent = new Intent(this, MyBroadcastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1234567890, intent, flagPendingIntent);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (delay * 1000), pendingIntent);
            Toast.makeText(this, "... waiting " + delay + " seconds", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Inserire un numero valido", Toast.LENGTH_SHORT).show();
        }

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_flag_cancel_current:
                if (checked)
                    flagPendingIntent = PendingIntent.FLAG_CANCEL_CURRENT;
                break;
            case R.id.radio_flag_immutable:
                if (checked)
                    flagPendingIntent = PendingIntent.FLAG_IMMUTABLE;
                break;
            case R.id.radio_flag_no_create:
                if (checked)
                    flagPendingIntent = PendingIntent.FLAG_NO_CREATE;
                break;
            case R.id.radio_flag_one_shot:
                if (checked)
                    flagPendingIntent = PendingIntent.FLAG_ONE_SHOT;
                break;
            case R.id.radio_flag_update_current:
                if (checked)
                    flagPendingIntent = PendingIntent.FLAG_UPDATE_CURRENT;
                break;
        }
    }
}
