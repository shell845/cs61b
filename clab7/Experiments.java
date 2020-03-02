import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST<Integer> tree = new BST<>();
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        for (int i = 0; i < 5000; i++) {
            tree.add(r.nextInt());
            xValues.add(i);
            yValues.add(tree.avgDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("BST average depth", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        BST<Integer> tree = new BST<>();
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        // Initial the tree
        for (int i = 0; i < 1000; i++) {
            tree.add(r.nextInt(1000));
        }
        double startingDepth = tree.avgDepth();

        // Delete and insert for M times
        for (int j = 0; j < 500; j++) {
            int keyD = r.nextInt(1000);
            while (!tree.contains((keyD))) {
                keyD = r.nextInt(1000);
            }
            tree.deleteTakingSuccessor(keyD);

            int keyI = r.nextInt(1000);
            tree.add(keyI);

            xValues.add(j);
            yValues.add(tree.avgDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Knott’s experiment", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        BST<Integer> tree = new BST<>();
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();

        // Initial the tree
        for (int i = 0; i < 1000; i++) {
            tree.add(r.nextInt(1000));
        }
        double startingDepth = tree.avgDepth();

        // Delete and insert for M times
        for (int j = 0; j < 1000; j++) {
            int keyD = r.nextInt(1000);
            while (!tree.contains((keyD))) {
                keyD = r.nextInt(1000);
            }
            tree.deleteTakingRandom(keyD);

            int keyI = r.nextInt(1000);
            tree.add(keyI);

            xValues.add(j);
            yValues.add(tree.avgDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Knott’s experiment", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment3();
    }
}
