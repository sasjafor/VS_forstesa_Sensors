package ch.ethz.inf.vs.a1.forstesa.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sens_man = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // List of Sensors Available
        List<Sensor> sens_list = sens_man.getSensorList(Sensor.TYPE_ALL);
        final ArrayList<String> sens_names = new ArrayList<String>();
        for (Sensor i : sens_list){
            sens_names.add(i.getName());
            //System.out.println("DEBUG: "+i.getName());
        }
        lv = (ListView) findViewById(R.id.list_view);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sens_names);
        lv.setAdapter(adapter);
        lv.setEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                //Sensor sens = ;

                // Show Alert
                Toast.makeText(getApplicationContext(), "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG).show();

            }
        });
    }

    private ListView lv;
    private SensorManager sens_man;
}
