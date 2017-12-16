import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordController implements ActionListener {
   private PasswordScreen passwordScreen;
   private DataAdapter dataAdapter;

   public PasswordController(PasswordScreen passwordScreen, DataAdapter dataAdapter) {
      this.passwordScreen = passwordScreen;
      this.dataAdapter = dataAdapter;
      this.passwordScreen.getBtnSave().addActionListener(this);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == passwordScreen.getBtnSave()) {
         User user = passwordScreen.getUser();
         String oldpass = passwordScreen.getOldPass().getText().trim();
         String newpass = passwordScreen.getNewPass().getText().trim();
         String confirmpass = passwordScreen.getConfirmPass().getText().trim();
         if (!user.getPass().equals(oldpass)) {
            JOptionPane.showMessageDialog(null, "Incorrect Old Password");
         }
         else if (!newpass.equals(confirmpass)) {
            JOptionPane.showMessageDialog(null, "New Passwords do not match");
            System.out.println(newpass + "  "+ confirmpass);
         } 
         else if (oldpass.equals(newpass)) {
            JOptionPane.showMessageDialog(null, "New Password is the Same as the Old Password");
         } 
         else {
            dataAdapter.UpdateUser(newpass, user);
            user.setPass(newpass);
            //Application.getInstance().setCurrentUser(user);
         }
      }
   }
}