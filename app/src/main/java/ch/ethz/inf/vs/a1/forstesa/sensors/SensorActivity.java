package ch.ethz.inf.vs.a1.forstesa.sensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

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

        
    }

    private String sens_name;
    private SensorManager sens_man;
    private Sensor sens;
}
