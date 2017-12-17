import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;


public class ProfileScreen extends JFrame {
   private JButton btnPicture = new JButton("Edit Picture");
   private JButton btnPassword = new JButton("Edit Password");
   private User user;

   public ProfileScreen() {
      this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
      this.setSize(500, 400);
   
      JLabel title = new JLabel("Store Management System");
      title.setFont(new Font("Sans Serif", Font.BOLD, 24));
      JPanel panelTitle = new JPanel();
      panelTitle.add(title);
      this.getContentPane().add(panelTitle); 
      
      btnPicture.setPreferredSize(new Dimension(150, 50));
      btnPassword.setPreferredSize(new Dimension(150, 50));
      JPanel panelButton = new JPanel();
      panelButton.add(btnPicture);
      panelButton.add(btnPassword);
      this.getContentPane().add(panelButton);
      
      btnPassword.addActionListener(
         new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
               Application.getInstance().getPasswordScreen().setVisible(true);            }
         });
         
      btnPicture.addActionListener(
         new ActionListener() { // when controller is simple, we can declare it on the fly
            public void actionPerformed(ActionEvent e) {
               JFileChooser chooser = new JFileChooser();
               FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
               chooser.setFileFilter(filter);
               int returnVal = chooser.showOpenDialog(null);
               if(returnVal == JFileChooser.APPROVE_OPTION) {
                  System.out.println("You chose to open this file: " +
                     chooser.getSelectedFile().getName()); 
                  File selectedfile = chooser.getSelectedFile(); 
                  String filePath = selectedfile.getAbsolutePath();
                  File source = new File(filePath);
                  File dest = new File(System.getProperty("user.dir") + "/image/" + user.getName() + ".png");
                  try
                  {
                     //Files.copy(filePath,dest.toPath(),REPLACE_EXISTING);
                  
                     FileInputStream inStream = new FileInputStream(source);
                     FileOutputStream outStream = new FileOutputStream(dest);
                     byte[] buffer = new byte[1024];
                     int length;
                     while ((length = inStream.read(buffer)) > 0){
                        outStream.write(buffer, 0, length);
                     }
                     if (inStream != null)inStream.close();
                     if (outStream != null)outStream.close();
                     System.out.println("File Copied..");
                     
                     Application.getInstance().UpdateImage(user);     
                  }
                  catch (IOException ex) {
                     System.out.println(ex);
                  }        
               }  
               else {
                  JOptionPane.showMessageDialog(null, "File Failed to Load");
               }
            }      
         });
   }
   
   private JLabel img_label = new JLabel();
   public void setImage(User user) {
      String file_name = "image/" + user.getName() + ".png";
      File tmpDir = new File(file_name);
      if(!tmpDir.exists()) {
         file_name = "image/default.png";
      }
      
      ImageIcon image = new ImageIcon(file_name);
      img_label.setIcon(image);
      //img_panel.add(img_label, BorderLayout.CENTER);
      this.add(img_label);
   }
   
   
   //TODO MAKE THIS SHIT WORK 
   public void UpdateImage(User user) {
      String file_name = "image/" + user.getName() + ".png";
      ImageIcon image = new ImageIcon(file_name);
      img_label.setIcon(image);
      //this.getContentPane().remove(img_label);
      this.getContentPane().repaint();
      img_label.repaint();
      //img_label.setIcon(image);
   }
   
   public User getUser() {
      return user;
   }
   
   public void setUser(User user_in) {
      user = user_in;
   }
}