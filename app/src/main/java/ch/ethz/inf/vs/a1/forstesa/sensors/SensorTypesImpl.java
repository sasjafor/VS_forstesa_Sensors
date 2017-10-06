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

    public int getNumberValues(int sensorType){
        switch (sensorType) {
            case 1:
                return 3;
            case 2:
                return 3;
            case 4:
                return 3;
            case 5:
                return 1;
            case 6:
                return 1;
            case 9:
                return 3;
            case 10:
                return 3;
            case 11:
                return 3;
            case 12:
                return 1;
            case 13:
                return 1;
            default:
                return 0;
        }
    }

    public String getUnitString(int sensorType){
        switch (sensorType){
            case 1:
                return "m/s^2";
            case 2:
                return "microT";
            case 4:
                return "rad/s";
            case 5:
                return "lx";
            case 6:
                return "hPa";
            case 9:
                return "m/s^2";
            case 10:
                return "m/s^2";
            case 11:
                return "no unit";
            case 12:
                return "%";
            case 13:
                return "°C";
            default:
                return "no unit";
        }
    }
}
