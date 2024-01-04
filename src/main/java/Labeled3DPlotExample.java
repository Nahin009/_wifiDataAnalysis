//import com.panayotis.gnuplot.JavaPlot;
//import com.panayotis.gnuplot.plot.AbstractPlot;
//import com.panayotis.gnuplot.plot.DataSetPlot;
//import com.panayotis.gnuplot.style.ObjectStyle;
//import com.panayotis.gnuplot.style.Style;
//
//public class Labeled3DPlotExample {
//
//    public static void main(String[] args) {
//        // Sample data
//        double[] xData = {1, 2, 3, 4, 5};
//        double[] yData = {2, 4, 1, 5, 3};
//        double[] zData = {3, 1, 2, 4, 5};
//        String[] labels = {"A", "B", "C", "D", "E"};
//
//        // Create a JavaPlot instance
//        JavaPlot plot = new JavaPlot();
//
//        // Create a 3D data set plot
//        AbstractPlot dataSetPlot = new DataSetPlot(new double[][]{xData, yData, zData});
//
//        // Set plot style
//        plot.addPlot(dataSetPlot);
//
//        // Add labels to specific points in the XY plane
//        for (int i = 0; i < xData.length; i++) {
//            ObjectStyle labelStyle = new ObjectStyle();
//            labelStyle.setStyle(Style.TEXT);
//            labelStyle.setPointLabel(labels[i]);
//            plot.addLabel(xData[i], yData[i], labelStyle);
//        }
//
//        // Set axis labels
//        plot.getAxis("x").setLabel("X Axis");
//        plot.getAxis("y").setLabel("Y Axis");
//        plot.getAxis("z").setLabel("Z Axis");
//
//        // Display the plot
//        plot.plot();
//    }
//}
