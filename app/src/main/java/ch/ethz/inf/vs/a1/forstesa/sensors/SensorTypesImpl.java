package ch.ethz.inf.vs.a1.forstesa.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by sascha on 05.10.17.
 */

public class SensorTypesImpl implements SensorTypes {

    /*public int getNumberValues(int sensorType){
        Sensor sens = SensorManager.
        MainActivity.sens_man.registerListener(msel,sens,0);
        MainActivity.sens_man.unregisterListener(msel);
        return n;
    }

    public String getUnitString(int sensorType){
        Sensor sens = MainActivity.sens_man.getDefaultSensor(sensorType);
        MainActivity.sens_man.registerListener(msel,sens,0);
        MainActivity.sens_man.unregisterListener(msel);
        return "Hello";
    }

    private class MySensorEventListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            n = sensorEvent.values.length;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    private int n;
    private MySensorEventListener msel = new MySensorEventListener();*/

    public int getNumberValues(int sensorType){
        switch (sensorType) {
            case 1:
                return 3;
            case 13:
                return 1;
            default:
                return 0;
        }
    }

    public String getUnitString(int sensorType){
        return "hello";
    }
}
