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

public class SensorActivity extends AppCompatActivity implements SensorEventListener{

    protected void onResume() {
        super.onResume();
        sens_man.registerListener(this, sens, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sens_man.unregisterListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sens_name = getIntent().getExtras().getString("sensor_name");
        //System.out.println("DEBUG: sens_name"+sens_name);
        List<Sensor> sens_list = sens_man.getSensorList(Sensor.TYPE_ALL);
        for(Sensor i : sens_list){
            if(i.getName().equals(sens_name)) sens = i;
            //System.out.println("DEBUG: i.name="+i.getName());
        }
        //System.out.println("DEBUG: "+sens);
        text = (TextView) findViewById(R.id.text_view);
        text.setText(sens_name + "\n");

        sens_man.registerListener(this, sens, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //System.out.println("DEBUG: onSensorChanged");
        SensorTypesImpl sti = new SensorTypesImpl();
        float[] vals = sensorEvent.values.clone();
        //System.out.println("DEBUG: vals=" + vals);
        String str = "";
        for (int k = 0; k < sti.getNumberValues(sensorEvent.sensor.getType()); k++){
            str.concat("SensorValue" + k + vals[k] + "\n");
            //System.out.println("DEBUG: k=" + k);
        }
        text.setText(sens_name + "\n" + str);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private TextView text;
    private String sens_name;
    //private MySensorEventListener msel = new MySensorEventListener();
    private Sensor sens;
}
