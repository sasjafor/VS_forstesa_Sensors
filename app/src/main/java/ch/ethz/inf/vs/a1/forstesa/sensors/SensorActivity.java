package ch.ethz.inf.vs.a1.forstesa.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import static ch.ethz.inf.vs.a1.forstesa.sensors.MainActivity.sens_man;

public class SensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sens_name = getIntent().getExtras().getString("sensor_name");
        List<Sensor> sens_list = sens_man.getSensorList(Sensor.TYPE_ALL);
        for(Sensor i : sens_list){
            if(i.getName() == sens_name) sens = i;
        }
        text = (TextView) findViewById(R.id.text_view);
        text.setText(sens_name + "\n");
    }

    private class MySensorEventListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            for (int k = 0; k < SensorTypesImpl.getNumberValues(sensorEvent.sensor.getType()));
            text.append(sensorEvent.);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    private TextView text;
    private String sens_name;
    private MySensorEventListener msel = new MySensorEventListener();
    private Sensor sens;
}
