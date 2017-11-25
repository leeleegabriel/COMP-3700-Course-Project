public class User {
   private String Name;
   private String Pass;
   private String Job;

   public void User(String Name_in, String Pass_in, String Job_in) {
      Name = Name_in;
      Pass = Pass_in;
      Job = Job_in;
   }
   
   public void User() {
      Name = "";
      Pass = "";
      Job = "";
   }
   
   public String getName() {
      return Name;
   }
   
   public void setName(String Name_in) {
      Name = Name_in;
   }

   public String getPass() {
      return Pass;
   }
   
   public void setPass(String Pass_in) {
      Pass = Pass_in;
   }
   
   public void setJob(String Job_in) {
      Job = Job_in;
   }

   public String getJob() {
      return Job;
   }

}