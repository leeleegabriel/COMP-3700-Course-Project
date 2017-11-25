import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
   private LoginScreen loginScreen;
   private DataAdapter dataAdapter;

   public LoginController(LoginScreen loginScreen, DataAdapter dataAdapter) {
      this.loginScreen = loginScreen;
      this.dataAdapter = dataAdapter;
      this.loginScreen.getBtnLogin().addActionListener(this);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == loginScreen.getBtnLogin()) {
         String name = loginScreen.getEmployeeName().getText().trim();
         String pass = loginScreen.getPass().getText().trim();
         User user = dataAdapter.getUser(name, pass);
         //^^ this
         if (user == null) {
            JOptionPane.showMessageDialog(null, "Invalid Username/password");
         } 
         else {
            this.loginScreen.setVisible(false);
            Application.getInstance().SetUser(user);
            Application.getInstance().SetImage(user);
            if(user.getJob().equals("Manager")) {
               Application.getInstance().getManagerScreen().setVisible(true);
            } 
            else {
               Application.getInstance().getMainScreen().setVisible(true);
            }
            //Application.getInstance().setCurrentUser(user);
         }
      }
   }
}