package ch.ethz.inf.vs.a1.forstesa.sensors;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;

import java.util.List;

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

        int sens_type = 0;
        sens_man = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sens_list = sens_man.getSensorList(Sensor.TYPE_ALL);
        Bundle extras = getIntent().getExtras();

        int n_vals = sti.getNumberValues(sens_type);

        if (extras != null) {
            sens_name = getIntent().getExtras().getString("sensor_name");
        }

        for(Sensor i : sens_list){
            if(i.getName().equals(sens_name)) sens = i;
        }

        if (sens != null){
            sens_type = sens.getType();
        }

        sens_man.registerListener(this, sens, SensorManager.SENSOR_DELAY_NORMAL);

        text = (TextView) findViewById(R.id.text_view);
        Resources res = getResources();
        text.setText(res.getString(R.string.sens_name,sens_name));

        GraphView graph = (GraphView) findViewById(R.id.graph);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(100);

        graph_container = new GraphContainerImpl(graph,sti.getUnitString(sens_type),n_vals);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (t0 == 0){
            t0 = sensorEvent.timestamp;
        }

        float[] vals = sensorEvent.values.clone();
        long time = (sensorEvent.timestamp - t0) / 100000000;
        String str = "";
        int stype = sensorEvent.sensor.getType();
        int n_vals = sti.getNumberValues(stype);
        n_vals = (n_vals == 0) ? vals.length : n_vals;
        float[] vals1 = new float[n_vals];
        Resources res = getResources();

        for (int k = 0; k < n_vals; k++){
            String unit = sti.getUnitString(stype);
            str = str + res.getString(R.string.values,k,vals[k],unit);
            vals1[k] = vals[k];
        }

        String txt = res.getString(R.string.sens_name,sens_name) + str;
        text.setText(txt);

        graph_container.addValues(time, vals1);
    }

    public GraphContainerImpl getGraphContainer(){
        return graph_container;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private long t0 = 0;
    private TextView text;
    private GraphContainerImpl graph_container;
    private String sens_name;
    private Sensor sens;
    private SensorManager sens_man;
    private SensorTypesImpl sti = new SensorTypesImpl();
}
