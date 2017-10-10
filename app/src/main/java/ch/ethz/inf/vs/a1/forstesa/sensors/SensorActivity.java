package ch.ethz.inf.vs.a1.forstesa.sensors;

import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;

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
        //System.out.println("DEBUG: sens_name="+sens_name);
        List<Sensor> sens_list = sens_man.getSensorList(Sensor.TYPE_ALL);
        for(Sensor i : sens_list){
            if(i.getName().equals(sens_name)) sens = i;
            //System.out.println("DEBUG: i.name="+i.getName());
        }
        sens_man.registerListener(this, sens, SensorManager.SENSOR_DELAY_NORMAL);
        //System.out.println("DEBUG: "+sens);
        text = (TextView) findViewById(R.id.text_view);
        text.setText(sens_name + "\n");

        int n_vals = sti.getNumberValues(sens.getType());

        GraphView graph = (GraphView) findViewById(R.id.graph);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(100);

        graph_container = new GraphContainerImpl(graph,sti.getUnitString(sens.getType()),n_vals);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //System.out.println("DEBUG: onSensorChanged");

        float[] vals = sensorEvent.values.clone();
        long time = sensorEvent.timestamp;
        //System.out.println("DEBUG: vals=" + vals);
        String str = "";
        int stype = sensorEvent.sensor.getType();
        int num_vals = sti.getNumberValues(stype);
        int t = (num_vals == 0) ? vals.length : num_vals;
        for (int k = 0; k < t; k++){
            str = str + "SensorValue" + k + "= " + vals[k] + " " + sti.getUnitString(stype) + "\n";
            //System.out.println("DEBUG: k=" + k);
        }
        //System.out.println("DEBUG: str="+str);
        text.setText(sens_name + "\n" + str);

        time = (time/100000000);
        graph_container.addValues(time, vals);
        //System.out.println("DEBUG: num_vals="+num_vals);
        //System.out.println("DEBUG: time="+time);
    }

    public GraphContainerImpl getGraphContainer(){
        return graph_container;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_sensor);

    }*/

    private TextView text;
    private GraphContainerImpl graph_container;
    private String sens_name;
    private Sensor sens;
    private SensorTypesImpl sti = new SensorTypesImpl();
}
