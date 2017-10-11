package ch.ethz.inf.vs.a1.forstesa.sensors;

import android.graphics.Color;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GraphContainerImpl implements GraphContainer{

    GraphContainerImpl(GraphView graph_view, String unit, int num_vals){
        graph = graph_view;
        n_vals = num_vals;
        GridLabelRenderer gridLabel = graph_view.getGridLabelRenderer();
        gridLabel.setVerticalAxisTitle(unit);
        gridLabel.setHorizontalAxisTitle("timestamp");
    }

    public void addValues(double xIndex, float[] values){
        if (series == null){
            n_vals = (n_vals == 0) ? values.length : n_vals;
            series = new LineGraphSeries[n_vals];
            for (int k = 0; k < n_vals; k++) {
                series[k] = new LineGraphSeries<>();
                series[k].setColor(col(k));
                graph.addSeries(series[k]);
            }
        }
        if (values.length != n_vals){
            throw new IllegalArgumentException();
        }
        for (int k = 0; k < n_vals; k++) {
            series[k].appendData(new DataPoint(xIndex,values[k]),true,100);
        }
    }

    public float[][] getValues(){
        List<Series> list = graph.getSeries();
        ArrayList<ArrayList> vals = new ArrayList<>();

        for (int k = 0; k < list.size(); k++){
            vals.add(new ArrayList());
            Series ser = list.get(k);
            double last_x = ser.getHighestValueX();
            Iterator it = ser.getValues(last_x-100,last_x);

            while (it.hasNext()){
                DataPoint dp = (DataPoint) it.next();
                float val = (float) dp.getY();
                vals.get(k).add(val);
            }
        }

        int s = 0;
        if (!vals.isEmpty()) s = vals.get(0).size();
        float[][] res = new float[s][list.size()];

        for (int i = 0; i < s; i++){
            for (int j = 0; j < list.size(); j++){
                res[i][j] = (float) vals.get(j).get(i);
            }
        }

        return res;
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
    private LineGraphSeries<DataPoint>[] series;
    private int n_vals;
}
