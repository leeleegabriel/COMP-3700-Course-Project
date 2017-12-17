import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.DriverManager;

public class ProxyDataAdapter implements IDataAccess {
   String hostName;
   int portNumber;

   private DataAdapter getLocalDataAdapter() {
      if (localDataAdapter == null) {
         try {
            localDataAdapter = new DataAdapter(DriverManager.getConnection("jdbc:sqlite:localstore.db"));
         } catch (Exception ex) {
            ex.printStackTrace();
         }
      }
      return localDataAdapter;
   }

   private RemoteDataAdapter getRemoteDataAdapter() {
      if (remoteDataAdapter == null)
         System.out.println("Need access to remote data server!!!!");
      System.out.println("Attemping to connect to host " + hostName + " on port " + portNumber);
   
      remoteDataAdapter = new RemoteDataAdapter(hostName, portNumber);
      return remoteDataAdapter;
   }

   DataAdapter localDataAdapter = null;
   RemoteDataAdapter remoteDataAdapter = null;

   public ProxyDataAdapter(String hostName, int portNumber) {
      this.hostName = hostName;
      this.portNumber = portNumber;
   }


   public Product loadProduct(int id) {
      Product product;
      product = getRemoteDataAdapter().loadProduct(id);
      if (product != null)
         getRemoteDataAdapter().saveProduct(product);
      return product;
   }

   public boolean saveProduct(Product product) {
      return getRemoteDataAdapter().saveProduct(product);
   }
   
   public boolean UpdateUser(String newpass, User user) {
      user.setPass(newpass);
      return getRemoteDataAdapter().UpdateUser(user);
   }
   
   public boolean saveUser(User user) {
      return getRemoteDataAdapter().saveUser(user);
   }
   
   public int getRow() {
      return getRemoteDataAdapter().getRow();
   }
   
   public Order loadOrder(int id) {
      return getRemoteDataAdapter().loadOrder(id);
   }
   
   public boolean saveOrder(Order order) {
      return getRemoteDataAdapter().saveOrder(order);
   }
   
   public User getUser(String username, String password) {
      return getRemoteDataAdapter().getUser(username);
   }
   
   public void close() {
      getRemoteDataAdapter().close();
   }
   
}
