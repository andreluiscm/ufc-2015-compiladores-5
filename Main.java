import java.io.IOException;

import reader.ReadFile;

import syntaxtree.*;
import visitor.*;
import reader.*;

public class Main {
   
   public static void main(String [] args) throws IOException {
      try {
    	  String input = new ReadFile().getContent("t.txt");
    	  Program p = new MiniJavaParser(new java.io.StringReader(input)).Prog();
    	  System.out.println("Sintatic analysis successfull");
    	  p.accept(new PrettyPrintVisitor());
      }
      catch (ParseException e) {
         System.out.println("Error : \n"+ e.toString());
      }
   }
}
