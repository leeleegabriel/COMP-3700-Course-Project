import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
   private JTextField Name = new JTextField(30);
   private JTextField Pass = new JTextField(30);
   private JButton btnLogin = new JButton("Login");

   public JButton getBtnLogin() {
      return btnLogin;
   }

   public JTextField getPass() {
      return Pass;
   }

   public JTextField getEmployeeName() {
      return Name;
   }

   public LoginScreen() {
      this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(400, 300);
   
      JLabel title = new JLabel("Store Management System");
      title.setFont(new Font("Sans Serif", Font.BOLD, 24));
      JPanel panelTitle = new JPanel();
      panelTitle.add(title);
      this.getContentPane().add(panelTitle); 
   
      JPanel panel = new JPanel();
      JLabel labelName = new JLabel("Employee Name:");
      panel.add(labelName);
      panel.add(Name);
      JLabel labelPass = new JLabel("Password:");
      panel.add(labelPass);
      panel.add(Pass);
      panel.add(btnLogin);
      this.getContentPane().add(panel);
   
      /**
      JPanel panelPass = new JPanel();
      JLabel labelPass = new JLabel("Password:");
      panelPass.add(labelPass);
      panelPass.add(Pass);
      this.getContentPane().add(panelPass); */
      
      //btnLogin.setPreferredSize(new Dimension(150, 50));
      //this.getContentPane().add(btnLogin);
   }
}