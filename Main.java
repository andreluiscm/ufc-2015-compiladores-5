import java.io.IOException;

import reader.ReadFile;

import flow_graph.AssemFlowGraph;
import reg_alloc.InterferenceGraph;
import reg_alloc.Liveness;
import instr.sel.*;


import syntaxtree.*;
import visitor.*;
import reader.*;

public class Main {
   
   public static void main(String [] args) throws IOException {
      try {
    	  String input = new ReadFile().getContent(args[0]);
    	  Program p = new MiniJavaParser(new java.io.StringReader(input)).Prog();
    	  System.out.println(">Sintatic analysis successfull");
    	  p.accept(new PrettyPrintVisitor());
    	  System.out.println(">Semantic analysis successfull");
    	  SymbolTable s = new SymbolTable();
    	  p.accept(s);
    	  if (s.hasError())
    	  	System.out.println("Error creating Symbol Table. Abort.");
    	  else
    	  {
	    	  System.out.println(">Symbol Table created successfull");
	    	  s.PrettyPrint();
	    	  TypeChecking tc = new TypeChecking(s.getProgramTable());
	    	  p.accept(tc);
	    	  if (tc.hasError())
	    	  	System.out.println("Error checking types. Abort.");
	    	  else
	    	  	System.out.println(">Types checked sucessfully");
	  }
	  /* NO TEST!
	  IRTree t;
	  p.accept(t);*/
      }
      catch (ParseException e) {
         System.out.println("Error : \n"+ e.toString());
      }
	  
	  // InstrList inst;
	 //---------Seleção de instruções -----
	 //  
	 //...
	 //---------Grafo de fluxo
	 //	AssemFlowGraph graph = new AssemFlowGraph(inst);
	 //
	 // --------Analise de longevidade
	 //
	 // Liveness liveness = new Liveness(graph);
	 // --------Grafo de interferência
	 //
	 // InterferenceGraph inGraph = new InterferenceGraph(liveness);
   }
}
