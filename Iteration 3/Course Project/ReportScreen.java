import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportScreen extends JFrame {

   private DefaultTableModel items = new DefaultTableModel(); // store information for the table!

   private JButton btnGenerate = new JButton("Generate Report");

   private JTable tblItems = new JTable(items);
   private JLabel labTotal = new JLabel("Total Revenue: ");

   public ReportScreen() {
   
      this.setTitle("Business Report");
      this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
      this.setSize(500, 700);
   
   
      items.addColumn("Product ID");
      items.addColumn("Quantity Sold");
      items.addColumn("Total");
   
      JPanel panelOrder = new JPanel();
      panelOrder.setPreferredSize(new Dimension(400, 450));
      panelOrder.setLayout(new BoxLayout(panelOrder, BoxLayout.PAGE_AXIS));
      tblItems.setBounds(0, 0, 400, 350);
      panelOrder.add(tblItems.getTableHeader());
      panelOrder.add(tblItems);
      panelOrder.add(labTotal);
      tblItems.setFillsViewportHeight(true);
      this.getContentPane().add(panelOrder);
      
   
      JPanel panelButton = new JPanel();
      panelButton.setPreferredSize(new Dimension(400, 100));
      panelButton.add(btnGenerate);
      this.getContentPane().add(panelButton);
   
   }

   public JButton getBtnGenerate() {
      return btnGenerate;
   }


   public JLabel getLabTotal() {
      return labTotal;
   }

   public void addRow(Object[] row) {
      items.addRow(row);              // add a row to list of item!
      items.fireTableDataChanged();
   }
   
   public void resetTable() {
      items.setRowCount(0);
   }
   
   
}
