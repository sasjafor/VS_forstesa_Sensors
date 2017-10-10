package ch.ethz.inf.vs.a1.forstesa.sensors;

import android.graphics.Color;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.List;

public class GraphContainerImpl implements GraphContainer{

    GraphContainerImpl(GraphView graph_view, String unit, int num_vals){
        graph = graph_view;
        n_vals = num_vals;
        System.out.println("DEBUG: n_vals="+n_vals);
        series = new LineGraphSeries[n_vals];
        for (int k = 0; k < n_vals; k++) {
            series[k] = new LineGraphSeries<>();
            series[k].setColor(col(k));
            graph.addSeries(series[k]);
        }
    }

    public void addValues(double xIndex, float[] values){
        for (int k = 0; k < n_vals; k++) {
            series[k].appendData(new DataPoint(xIndex,values[k]),true,100);
        }
    }

    public float[][] getValues(){
        List<Series> list = graph.getSeries();
        float[][] res = new float[100][list.size()];
        for (int k = 0; k < list.size(); k++){
            //list.get(k).getValues()
        }
        return null;
    }

    private static int col(int k){
        switch (k){
            case 0:
                return Color.RED;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.BLUE;
            default:
                return Color.BLACK;
        }
    }

    private GraphView graph;
    private LineGraphSeries ser0,ser1,ser2;
    private LineGraphSeries[] series;
    private int n_vals;
}
