import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController implements ActionListener {
    private UserView userView;
    private DataAdapter dataAdapter; // to save and load user information

    public UserController(UserView userView, DataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
        this.userView = userView;

        //userView.getBtnLoad().addActionListener(this);
        userView.getBtnSave().addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
         /*
        if (e.getSource() == userView.getBtnLoad())
            loadUser();
            System.out.println("User can't be loaded");
        */
        if (e.getSource() == userView.getBtnSave())
            saveUser();
    }

    private void saveUser() {
        String userName = userView.getTxtUserName().getText().trim();

        if (userName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid user name! Please provide a non-empty user name!");
            return;
        }
        
        String userPass = userView.getTxtUserPass().getText().trim();

        if (userName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid user pass! Please provide a non-empty user password!");
            return;
        }
        
        String userJob = userView.getTxtUserJob().getText().trim();

        if (userName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Invalid user job! Please provide a non-empty job title!");
            return;
        }
        
        

        // Done all validations! Make an object for this user!

        User user = new User();
        user.setName(userName);
        user.setPass(userPass);
        user.setJob(userJob);

        // Store the user to the database

        dataAdapter.saveUser(user);
    }
      /*
    private void loadUser() {
        String name = "";
        try {
            name = userView.getTxtUserName().getText().trim();
            
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid user name! Please provide a valid user name!");
            return;
        }

        User user = dataAdapter.loadUser(name);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "This username does not exist in the database!");
            return;
        }

        userView.getTxtUserName().setText(user.getName());
        userView.getTxtUserName().setText(user.getPass());
        userView.getTxtUserName().setText(user.getJob());
    }
    */


}