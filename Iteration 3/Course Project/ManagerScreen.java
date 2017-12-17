import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ManagerScreen extends JFrame {

   private JButton btnCheckout = new JButton("Checkout");
   private JButton btnManage   = new JButton("Manage Product");
   private JButton btnProfile = new JButton("Manage Profile");
   private JButton btnUsers = new JButton("Manage Users");
   private JButton btnReport = new JButton("Business Report");
   private User user;
   private JLabel img_label;
   private JPanel img_panel;
   private ImageIcon image;

   public ManagerScreen() {
      this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(900, 900);
   
      btnManage.setPreferredSize(new Dimension(150, 50));
      btnUsers.setPreferredSize(new Dimension(150, 50));
      btnCheckout.setPreferredSize(new Dimension(150, 50));
      btnProfile.setPreferredSize(new Dimension(150, 50));
      btnReport.setPreferredSize(new Dimension(150, 50));
   
      JLabel title = new JLabel("Store Management System: Manager Screen");
      title.setFont(new Font("Sans Serif", Font.BOLD, 24));
      JPanel panelTitle = new JPanel();
      panelTitle.add(title);
      this.getContentPane().add(panelTitle);
   
      JPanel panelButton = new JPanel();
      panelButton.add(btnCheckout);
      panelButton.add(btnManage);
      panelButton.add(btnUsers);
      panelButton.add(btnProfile);
      panelButton.add(btnReport);

      this.getContentPane().add(panelButton);
   
      btnCheckout.addActionListener(
         new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
               Application.getInstance().getCheckoutScreen().setVisible(true);            }
         });
   
   
      btnManage.addActionListener(
         new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
               Application.getInstance().getProductView().setVisible(true);
            }
         });
         
      btnUsers.addActionListener(
         new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
               Application.getInstance().getUserView().setVisible(true);
            }
         });   
       
      btnProfile.addActionListener(
         new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
               Application.getInstance().getProfileScreen().setVisible(true);
            }
         });
      btnReport.addActionListener(
         new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
               Application.getInstance().getReportScreen().setVisible(true);
            }
         });
   }
   
   public void setImage(User user) {
      String file_name = "image/" + user.getName() + ".png";
      File tmpDir = new File(file_name);
      if(!tmpDir.exists()) {
         file_name = "image/default.png";
      }
      ImageIcon image = new ImageIcon(file_name);
      JLabel label = new JLabel("", image, JLabel.CENTER);
      JPanel panel = new JPanel(new BorderLayout());
      panel.add( label, BorderLayout.CENTER );
      this.getContentPane().add(panel);
   }
   
   public void UpdateImage(User user) {
      this.getContentPane().remove(img_panel); 
      String file_name = "image/" + user.getName() + ".png";
      image = new ImageIcon(file_name);
      //img_label = new JLabel("", image, JLabel.CENTER);
      //img_panel = new JPanel(new BorderLayout());
      //img_panel.add(img_label, BorderLayout.CENTER);
      //this.getContentPane().add(img_panel);
   }
   
   public User getUser() {
      return user;
   }
   
   public void setUser(User user_in) {
      user = user_in;
   }

}
