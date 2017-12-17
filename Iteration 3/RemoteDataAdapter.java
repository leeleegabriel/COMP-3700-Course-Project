import com.google.gson.Gson;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RemoteDataAdapter implements IDataAccess {

   private PrintWriter outStream;
   private BufferedReader inStream;
   private Socket socket;
   private Gson gson = new Gson();

   public RemoteDataAdapter(String hostName, int portNumber) {
      try {
         socket = new Socket(hostName, portNumber);
         outStream = new PrintWriter(socket.getOutputStream(), true);
         inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public Product loadProduct(int id) {
      try {
         ClientRequest request = new ClientRequest("GET Product", String.valueOf(id));
         outStream.println(gson.toJson(request));
         String serverAnswer = inStream.readLine();
         return gson.fromJson(serverAnswer, Product.class);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }


   public boolean saveProduct(Product product) {
      try {
         ClientRequest request = new ClientRequest("PUT Product", gson.toJson(product));
         outStream.println(gson.toJson(request));
      
         String serverAnswer = inStream.readLine();
         if (serverAnswer.equals("ERROR"))
            return false;
         else
            return true;
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return false;
   }
   
   public boolean UpdateUser(User user) {
      try {
         ClientRequest request = new ClientRequest("PUT UserUpdate", gson.toJson(user));
         outStream.println(gson.toJson(request));
         
         String serverAnswer = inStream.readLine();
         if (serverAnswer.equals("ERROR"))
            return false;
         else
            return true;
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      
      return false;
   }
   
   public boolean saveUser(User user) {
      try {
         ClientRequest request = new ClientRequest("PUT User", gson.toJson(user));
         outStream.println(gson.toJson(request));
         
         String serverAnswer = inStream.readLine();
         if (serverAnswer.equals("ERROR"))
            return false;
         else
            return true;
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      
      return false;
   }
   
   public int getRow() {
      try {
         ClientRequest request = new ClientRequest("GET Row", gson.toJson(""));
         outStream.println(gson.toJson(request));
         String serverAnswer = inStream.readLine();
         return Integer.parseInt(serverAnswer);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      
      return -999999;
   }
   
   public Order loadOrder(int id) {
      try {
         ClientRequest request = new ClientRequest("GET Order", gson.toJson(id));
         outStream.println(gson.toJson(request));
         String serverAnswer = inStream.readLine();
         return gson.fromJson(serverAnswer, Order.class);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      
      return null;
   }
   
   public boolean saveOrder(Order order) {
      try {
         ClientRequest request = new ClientRequest("PUT Order", gson.toJson(order));
         outStream.println(gson.toJson(request));
         
         String serverAnswer = inStream.readLine();
         if (serverAnswer.equals("ERROR"))
            return false;
         else
            return true;
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      
      return false;
   }
   
   public User getUser(String username) {
      try {
         ClientRequest request = new ClientRequest("GET User", gson.toJson(username));
         outStream.println(gson.toJson(request));
         String serverAnswer = inStream.readLine();
         return gson.fromJson(serverAnswer, User.class);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      
      return null;
   
   }

}
