import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;


public class ReportController implements ActionListener {
   private ReportScreen view;
   private PaymentScreen payment;
   private DataAdapter dataAdapter; // to save and load product
   private Order order = null;
   private double TaxRate = 1.08;
   private int count = 0;
  

   public ReportController(ReportScreen view, PaymentScreen payment, DataAdapter dataAdapter) {
      this.dataAdapter = dataAdapter;
      this.view = view;
      this.payment = payment;
   
      view.getBtnGenerate().addActionListener(this);
   
      order = new Order();
      
   }


   public void actionPerformed(ActionEvent e) {
    if (e.getSource() == view.getBtnGenerate()) {
             generateReport();
    }

   }

   private void reset() {
      payment.setVisible(false);
      view.setVisible(false);
      order = new Order();
      this.view.getLabTotal().setText("Total: " + 0.0);
      view.resetTable();
   }

   private void generateReport() {
            
      //Gather orders
      ArrayList<Order> orders = new ArrayList();
      Order result;
      int i = 0;
      
      result = dataAdapter.loadOrder(i);
      
      while(result != null) {
         orders.add(result);
         result = dataAdapter.loadOrder(i);
         i++;
      }
      
      //Calculate Numbers
      int[] quantities = new int[1000];
      double[] costs = new double[1000];
      
      List<OrderLine> orderLineList = null;
      OrderLine line = null;
      
      for(int j = 0; j < orders.size(); j++) {
         orderLineList = orders.get(j).getLines();
         for(int k = 0; k < orderLineList.size(); k++) {
            line = orderLineList.get(k);
            quantities[line.getProductID()] += line.getQuantity();
            costs[line.getProductID()] += line.getCost();
         }
      }
      
      String[] row = new String[3];
      
      for(int l = 0; l < quantities.length; l++) {
         if(quantities[l] != 0) {
            row[0] = Integer.toString(l);
            row[1] = Integer.toString(quantities[l]);
            row[2] = Double.toString(costs[l]);
            this.view.addRow(row);
         }
      }
      
      
      
      
      
      
   }

}