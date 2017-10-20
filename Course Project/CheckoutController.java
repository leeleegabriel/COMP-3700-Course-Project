import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;

public class CheckoutController implements ActionListener {
    private CheckoutScreen view;
    private PaymentScreen payment;
    private DataAdapter dataAdapter; // to save and load product
    private Order order = null;
    private double TaxRate = 1.08;
    private int count = 0;
   

    public CheckoutController(CheckoutScreen view, PaymentScreen payment, DataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
        this.view = view;
        this.payment = payment;

        view.getBtnAdd().addActionListener(this);
        view.getBtnPay().addActionListener(this);
        
        payment.getBtnnopay().addActionListener(this);
        payment.getBtnpay().addActionListener(this);

        order = new Order();

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnAdd()){
            //createOrder();
            addProduct();
        }
        else if (e.getSource() == view.getBtnPay() && order.getLines().size() > 0){
            payment.setOrder(order);
            payment.setVisible(true);
        }
        else if (e.getSource() == payment.getBtnpay()){
            JOptionPane.showMessageDialog(null, "Order added to DB, Returning to main menu");
            dataAdapter.saveOrder(order);
            reset();
        }
        else if (e.getSource() == payment.getBtnnopay()){
            JOptionPane.showMessageDialog(null, "Customer did not pay, Returning to main menu");
            reset();
        }
        else {
            JOptionPane.showMessageDialog(null, "Order is empty! Please Add Products.");
        }
    }

    private void reset() {
         payment.setVisible(false);
         view.setVisible(false);
         order = new Order();
         this.view.getLabTotal().setText("Total: " + 0.0);
         view.resetTable();
    }

    private void addProduct() {
        String id = JOptionPane.showInputDialog("Enter ProductID: ");
        Product product = dataAdapter.loadProduct(Integer.parseInt(id));
        if (product == null) {
            JOptionPane.showMessageDialog(null, "This product does not exist!");
            return;
        }

        double quantity = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter quantity: "));

        if (quantity < 0 || quantity > product.getQuantity()) {
            JOptionPane.showMessageDialog(null, "This quantity is not valid!");
            return;
        }
        
        try {
            String CustomerName = view.getTxtCustomerName().getText();        
            order.setCustomerName(CustomerName);
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please provide a Customer Name!");
            return;
        }
        
        int OrderID = dataAdapter.getRow();
        if (OrderID == -1) {
            JOptionPane.showMessageDialog(null, "Invalid OrderID");
            return;
        }
        
        order.setOrderID(OrderID);
        
        OrderLine line = new OrderLine();
        line.setOrderID(this.order.getOrderID());
        line.setProductID(product.getProductID());
        line.setQuantity(quantity);
        line.setCost(quantity * product.getPrice());

        product.setQuantity(product.getQuantity() - quantity); // update new quantity!!
        dataAdapter.saveProduct(product); // and store this product back right away!!!

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        order.setDate(date);
        
        order.getLines().add(line);
        order.setTotalCost(order.getTotalCost() + line.getCost());
        order.setTotalTax(order.getTotalCost() * TaxRate);

        Object[] row = new Object[5];
        row[0] = line.getProductID();
        row[1] = product.getName();
        row[2] = product.getPrice();
        row[3] = line.getQuantity();
        row[4] = line.getCost();

        this.view.addRow(row);
        this.view.getLabTotal().setText("Total: " + order.getTotalCost());
        this.view.invalidate();
    }

}