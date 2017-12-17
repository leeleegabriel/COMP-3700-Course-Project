import javax.swing.*;
import java.awt.*;

public class PasswordScreen extends JFrame {
   private JTextField OldPass = new JTextField(30);
   private JTextField NewPass = new JTextField(30);
   private JTextField ConfirmPass = new JTextField(30);
   private JButton btnSave = new JButton("Save password");
   private User user;
   
   public JButton getBtnSave() {
      return btnSave;
   }

   public JTextField getNewPass() {
      return NewPass;
   }

   public JTextField getConfirmPass() {
      return ConfirmPass;
   }
   
   public JTextField getOldPass() {
      return OldPass;
   }
   
   public PasswordScreen() {
      this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
      this.setSize(400, 400);
      
      JLabel title = new JLabel("Store Management System");
      title.setFont(new Font("Sans Serif", Font.BOLD, 24));
      JPanel panelTitle = new JPanel();
      panelTitle.add(title);
      this.getContentPane().add(panelTitle); 
      
      
      JPanel panel= new JPanel();
      JLabel labelold = new JLabel("Enter Old Password:");
      panel.add(labelold);
      panel.add(OldPass);
      JLabel labelnew = new JLabel("Enter New Password:");
      panel.add(labelnew);
      panel.add(NewPass);
      JLabel labelconfirm = new JLabel("Confirm Password:");
      panel.add(labelconfirm);
      panel.add(ConfirmPass);
      panel.add(btnSave);
      this.getContentPane().add(panel);
   }
   
   
   public User getUser() {
      return user;
   }
   
   public void setUser(User user_in) {
      user = user_in;
   }

}
