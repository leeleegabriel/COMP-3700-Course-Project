import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentScreen extends JFrame {

    private JButton pay = new JButton("Customer did pay");
    private JButton nopay = new JButton("Customer did not pay");
    
    private Order order = null;

    public PaymentScreen() {
        this.setTitle("Payment");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);

        pay.setPreferredSize(new Dimension(240, 50));
        nopay.setPreferredSize(new Dimension(240, 50));


        JLabel title = new JLabel("Payment Menu");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);
        this.getContentPane().add(panelTitle);

        JPanel panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(500, 200));
        panelButton.add(pay);
        panelButton.add(nopay);
        this.getContentPane().add(panelButton);

/**
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getCheckoutScreen().setVisible(true);            }
        });


        nopay.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                Application.getInstance().getProductView().setVisible(true);
            }
        });
*/
    }
    
    public JButton getBtnpay() {
        return pay;
    }

    public JButton getBtnnopay() {
        return nopay;
    }


   public void setOrder(Order orderin) {
      order = orderin;
   }
}
