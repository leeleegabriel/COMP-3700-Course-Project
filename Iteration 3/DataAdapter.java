import java.sql.*;

public class DataAdapter implements IDataAccess {
   private Connection connection;

   public DataAdapter(Connection connection) {
      this.connection = connection;
   }
    
   public User getUser(String username, String password) {
      try {
         String query = "SELECT * FROM User WHERE Name = \"" + username + "\" AND Password = \"" + password + "\"";
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query);
         if (resultSet.next()) {
            User user = new User();
            user.setName(resultSet.getString(1));
            user.setPass(resultSet.getString(2));
            user.setJob(resultSet.getString(3));
            return user;
         }
      } 
      catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
      }
      return null;
   }
   
   public User getUser(String username) {
      try {
         String query = "SELECT * FROM User WHERE Name = \"" + username + "\"";
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query);
         if (resultSet.next()) {
            User user = new User();
            user.setName(resultSet.getString(1));
            user.setPass(resultSet.getString(2));
            user.setJob(resultSet.getString(3));
            return user;
         }
      } 
      catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
      }
      return null;
   }
   
   

   public void UpdateUser(String newpass, User user) {
      try {
         String query = "UPDATE User SET Password = \"" + newpass + "\" WHERE Password = \"" + user.getPass() + "\" AND Name = \"" + user.getName() + "\";";
         Statement statement = connection.createStatement();
         statement.executeUpdate(query);
         Application.getInstance().SetUser(user);
      }
      catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
      }
   }
   
   public void UpdateUser(User user) {
      try {
         String query = "UPDATE User SET Password = \"" + user.getPass() + "\" Name = \"" + user.getName() + "\";";
         Statement statement = connection.createStatement();
         statement.executeUpdate(query);
         Application.getInstance().SetUser(user);
      }
      catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
      }
   }

   @Override
    public Product loadProduct(int id) {
      try {
         String query = "SELECT * FROM Product WHERE ProductID = " + id;
      
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query);
         if (resultSet.next()) {
            Product product = new Product();
            product.setProductID(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setPrice(resultSet.getDouble(3));
            product.setQuantity(resultSet.getDouble(4));
            resultSet.close();
            statement.close();
         
            return product;
         }
      
      } catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
      }
      return null;
   }

   @Override
    public boolean saveProduct(Product product) {
      try {
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product WHERE ProductID = ?");
         statement.setInt(1, product.getProductID());
      
         ResultSet resultSet = statement.executeQuery();
      
         if (resultSet.next()) { // this product exists, update its fields
            statement = connection.prepareStatement("UPDATE Product SET Name = ?, Price = ?, Quantity = ? WHERE ProductID = ?");
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setDouble(3, product.getQuantity());
            statement.setInt(4, product.getProductID());
         }
         else { // this product does not exist, use insert into
            statement = connection.prepareStatement("INSERT INTO Product VALUES (?, ?, ?, ?)");
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setDouble(4, product.getQuantity());
            statement.setInt(1, product.getProductID());
         }
         statement.execute();
         resultSet.close();
         statement.close();
         return true;        // save successfully
      
      } catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
         return false; // cannot save!
      }
   }

   public Order loadOrder(int id) {
      try {
         Order order = null;
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Order\" WHERE OrderID = " + id);
      
         if (resultSet.next()) {
            order = new Order();
            order.setOrderID(resultSet.getInt("OrderID"));
            order.setCustomerName(resultSet.getString("Customer"));
            order.setTotalCost(resultSet.getDouble("TotalCost"));
            order.setDate(resultSet.getDate("OrderDate"));
            resultSet.close();
            statement.close();
         }
      
            // loading the order lines for this order
         resultSet = statement.executeQuery("SELECT * FROM OrderLine WHERE OrderID = " + id);
      
         while (resultSet.next()) {
            OrderLine line = new OrderLine();
            line.setOrderID(resultSet.getInt(1));
            line.setProductID(resultSet.getInt(2));
            line.setQuantity(resultSet.getDouble(3));
            line.setCost(resultSet.getDouble(4));
            order.addLine(line);
         }
      
         return order;
      
      } catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
         return null;
      }
   }

   public boolean saveOrder(Order order) {
      try {
         PreparedStatement statement = connection.prepareStatement("INSERT INTO \"Order\" VALUES (?, ?, ?, ?, ?)");
         statement.setInt(1, order.getOrderID());
         statement.setDate(2, order.getDate());
         statement.setString(3, order.getCustomerName());
         statement.setDouble(4, order.getTotalCost());
         statement.setDouble(5, order.getTotalTax());
      
         statement.execute();    // commit to the database;
         statement.close();
      
         statement = connection.prepareStatement("INSERT INTO OrderLine VALUES (?, ?, ?, ?)");
         for (OrderLine line: order.getLines()) { // store for each order line!
            statement.setInt(1, line.getOrderID());
            statement.setInt(2, line.getProductID());
            statement.setDouble(3, line.getQuantity());
            statement.setDouble(4, line.getCost());
         
            statement.execute();    // commit to the database;
         }
         statement.close();
         return true; // save successfully!
      }
      catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
         return false;
      }
   }

   public User loadUser(String username, String password) {
      try {
      
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE UserName = ? AND Password = ?");
         statement.setString(1, username);
         statement.setString(2, password);
         ResultSet resultSet = statement.executeQuery();
         if (resultSet.next()) {
            User user = new User();
                //user.setUserID(resultSet.getInt("UserID"));
            user.setName(resultSet.getString("UserName"));
            user.setPass(resultSet.getString("Password"));
                //user.setDisplayName(resultSet.getString("DisplayName"));
            user.setJob(resultSet.getString("Job"));
            resultSet.close();
            statement.close();
         
            return user;
         }
      
      } catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
      }
      return null;
   }
   public boolean saveUser(User user) {
      try {
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE Name = ?");
         statement.setString(1, user.getName());
      
         ResultSet resultSet = statement.executeQuery();
      
         if (resultSet.next()) { // this user exists, update its fields
            statement = connection.prepareStatement("UPDATE User SET Password = ?, Job = ? WHERE Name = ?");
            statement.setString(1, user.getPass());
            statement.setString(2, user.getJob());
            statement.setString(3, user.getName());
         
         }
         else { // this user does not exist, use insert into
            statement = connection.prepareStatement("INSERT INTO User VALUES (?, ?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getJob());
         }
         statement.execute();
         resultSet.close();
         statement.close();
         return true;        // save successfully
      }
      catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
         return false; // cannot save!
      }
   } 
   
   public int getRow() {
      try {
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT COUNT(ORDERID) FROM \"Order\"");
         int row = resultSet.getInt("Count(ORDERID)");
         resultSet.close();
         statement.close();
         //PreparedStatement statement = connection.prepareStatement("SELECT COUNT FROM \"Order\"");
         //statement.execute();    // commit to the database;
         //statement.close();    
         return row; // save successfully!
      }
      catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
         return -1;
      }
   }
}
