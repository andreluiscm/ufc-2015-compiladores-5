public class Main {
   
   public static void main(String [] args) {
      try {
         new MiniJavaParser(System.in).Start();
         System.out.println("Sintatic analysis successfull");
      }
      catch (ParseException e) {
         System.out.println("Error : \n"+ e.toString());
      }
   }
}
