package ch.ethz.inf.vs.a1.forstesa.sensors;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sens_man = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // List of Sensors Available
        final List<Sensor> sens_list = sens_man.getSensorList(Sensor.TYPE_ALL);
        final ArrayList<String> sens_names = new ArrayList<>();

        for (Sensor i : sens_list){
            sens_names.add(i.getName());
        }

        lv = (ListView) findViewById(R.id.list_view);
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sens_names);
        lv.setAdapter(adapter);
        lv.setEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(MainActivity.this, SensorActivity.class);
                myIntent.putExtra("sensor_name",sens_list.get(position).getName());
                startActivity(myIntent);
            }
        });
    }

    private ListView lv;
    private SensorManager sens_man;
}
