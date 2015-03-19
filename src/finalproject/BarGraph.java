/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import databasedriver.connectWithDB;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Ashay
 */
public class BarGraph extends javax.swing.JPanel {
    connectWithDB db = new connectWithDB();
    Connection conn = db.getConnect(); 
    /**
     * Creates new form BarGraph
     * @throws java.sql.SQLException
     */
    public BarGraph() throws SQLException {
        initComponents();
        DefaultCategoryDataset my_bar_chart_dataset = new DefaultCategoryDataset();
                Statement stmt = conn.createStatement();
                try {
                    JFreeChart BarChartObject;
            try (ResultSet query_set = stmt.executeQuery("Select ID_DATE, SUM(TOTAL_CALORIES) from Orders group by ID_DATE")) {
                while (query_set.next()) {
                    String date = query_set.getString("ID_DATE");
                    System.out.println(date);
                    int calories = Integer.parseInt(query_set.getString("SUM(TOTAL_CALORIES)"));
                    System.out.println(calories);
                    my_bar_chart_dataset.addValue(calories,"Calories",date);
                }
                BarChartObject = ChartFactory.createBarChart("Subject Vs Marks - Bar Chart","Subject","Marks",my_bar_chart_dataset,PlotOrientation.VERTICAL,true,true,false);
            }
                stmt.close(); 
                conn.close();
                int width=640; /* Width of the image */
                int height=480; /* Height of the image */                
                File BarChart=new File("output_chart.png");              
                ChartUtilities.saveChartAsPNG(BarChart,BarChartObject,width,height); 
                }
                 catch (SQLException | NumberFormatException | IOException i)
                 {
            System.out.println(i);
                 }
   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}