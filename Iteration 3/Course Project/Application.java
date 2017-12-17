import java.sql.*;

public class Application {

   private String hostName = "127.0.0.1";
   private int portNumber = 8888;
      
   private static Application instance;   // Singleton pattern

   public static Application getInstance() {
      if (instance == null) {
         instance = new Application();
      }
      return instance;
   }
   // Main components of this application

   private Connection connection;

   public Connection getConnection() {
      return connection;
   }
   
   private LoginScreen loginScreen = new LoginScreen();

   private MainScreen mainScreen = new MainScreen(); 
   
   private ManagerScreen managerScreen = new ManagerScreen();
   
   private ProfileScreen profileScreen = new ProfileScreen();
   
   private PasswordScreen passwordScreen = new PasswordScreen();
   
   private CheckoutScreen checkoutScreen = new CheckoutScreen();
   
   private PaymentScreen paymentScreen = new PaymentScreen();

   private ProductView productView;

   private UserView userView = new UserView();
   
   private ReportScreen reportScreen = new ReportScreen();
  
   //private ProductController productController;
   
   private UserController userController;
   
   private ReportController reportController;
      
   private CheckoutController checkoutController;
   
   private LoginController loginController;
   
   private PasswordController passwordController;
   
   private ProxyDataAdapter dataAdapter;
           
   public LoginScreen getLoginScreen() {
      return loginScreen;
   }

   public MainScreen getMainScreen() {
      return mainScreen;
   }
   
   public ManagerScreen getManagerScreen() {
      return managerScreen;
   }
   
   public ProfileScreen getProfileScreen() {
      return profileScreen;
   }
   
   public PasswordScreen getPasswordScreen() {
      return passwordScreen;
   }

   public ProductView getProductView() {
      return productView;
   }

   public UserView getUserView() {
      return userView;
   }
   
   public ReportScreen getReportScreen() {
      return reportScreen;
   }

   public CheckoutScreen getCheckoutScreen() {
      return checkoutScreen;
   }
   
   public PaymentScreen getPaymentScreen() {
      return paymentScreen;
   }
   
   public LoginController getLoginController() {
      return loginController;
   }

   // public ProductController getProductController() {
//       return productController;
//    }

   public UserController getUserController() {
      return userController;
   }
   
   public ReportController getReportController() {
      return reportController;
   }

   public CheckoutController getCheckoutController() {
      return checkoutController;
   }

   public ProxyDataAdapter getDataAdapter() {
      return dataAdapter;
   }
   
   public void SetUser(User user) {
      Application.getInstance().getMainScreen().setUser(user);
      Application.getInstance().getManagerScreen().setUser(user);
      Application.getInstance().getProfileScreen().setUser(user);
      Application.getInstance().getPasswordScreen().setUser(user);
   }
   
   public void SetImage(User user) {
      Application.getInstance().getMainScreen().setImage(user);
      Application.getInstance().getManagerScreen().setImage(user);
      Application.getInstance().getProfileScreen().setImage(user);
   }
   
   public void UpdateImage(User user) {
      Application.getInstance().getProfileScreen().UpdateImage(user);
      //Application.getInstance().getMainScreen().UpdateImage(user);
      //Application.getInstance().getManagerScreen().UpdateImage(user);
   }

   private void initializeProductDB(Statement stmt) throws SQLException {
      stmt.execute("create table Product (ProductID int PRIMARY KEY, ProductName char(30), Price double, Quantity double)");
   }

   private void initializeOrderDB(Statement stmt) throws SQLException {
      stmt.execute("create table \"Order\" (OrderID int PRIMARY KEY, OrderDate date, Customer char(30), TotalCost double, TotalTax double)");
   }

   private Application() {
      /*
      try {
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:store.db");
         Statement stmt = connection.createStatement();
         if (!stmt.executeQuery("select * from product").next()) // product table do not exist
            initializeProductDB(stmt);
      }
      catch (ClassNotFoundException ex) {
         System.out.println("SQLite is not installed. System exits with error!");
         System.exit(1);
      }
      
      catch (SQLException ex) {
         System.out.println("SQLite database is not ready. System exits with error!" + ex.getMessage());
         System.exit(2);
      }
      */
      try {
         productView = new ProductView();

      }
      catch(Exception e)
      {
      
      }
   
      dataAdapter = new ProxyDataAdapter(hostName, portNumber);
   
      //productController = new ProductController(productView, dataAdapter);
   
      checkoutController = new CheckoutController(checkoutScreen, paymentScreen, dataAdapter);
      
      loginController = new LoginController(loginScreen, dataAdapter);
      
      reportController = new ReportController(reportScreen, paymentScreen, dataAdapter);
      
      passwordController = new PasswordController(passwordScreen, dataAdapter);
      
      userController = new UserController(userView, dataAdapter);
   }


   public static void main(String[] args) {
      Application.getInstance().getLoginScreen().setVisible(true);
   }
}
