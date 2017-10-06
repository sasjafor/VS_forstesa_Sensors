package ch.ethz.inf.vs.a1.forstesa.sensors;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sascha on 06.10.17.
 */

public class GraphContainerImpl {

    GraphContainerImpl(GraphView graph_view, String unit, int num_vals){
        graph = graph_view;
        series = new LineGraphSeries[num_vals];
        for (int k = 0; k < num_vals; k++) {
            LineGraphSeries<DataPoint> ser1 = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(1,50),
                    new DataPoint(2,30),
                    new DataPoint(3,40)
            });
            ser1.draw(graph,new Canvas(),false);
            Paint paint = new Paint();
            int[] rgb = col(k);
            paint.setARGB(1,rgb[0],rgb[1],rgb[2]);
            ser1.setCustomPaint(paint);
            series[k] = ser1;
            graph.addSeries(series[k]);
        }
    }

    void addValues(double xIndex, float[] values){
        for (int k = 0; k < values.length; k++) {
            //series[k].appendData(new DataPoint(xIndex,values[k]),false,100);
            //System.out.println("DEBUG: "+series[k]);
        }
        //System.out.println("DEBUG: "+graph);
    }

    float[][] getValues(){
        List<Series> list = graph.getSeries();
        float[][] res = new float[100][list.size()];
        for (int k = 0; k < list.size(); k++){
            //list.get(k).getValues()
        }
        return null;
    }

    private int[] col(int k){
        switch (k){
            case 0:
                return new int[]{255,0,0};
            case 1:
                return new int[]{0,255,0};
            case 2:
                return new int[]{0,0,255};
            default:
                return new int[]{0,0,0};
        }
    }

    private GraphView graph;
    private LineGraphSeries<DataPoint>[] series;
}
