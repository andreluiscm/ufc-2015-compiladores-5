package flow_graph;

import java.util.Hashtable;

import inst.sel.Instr;
import inst.sel.InstrList;
import inst.sel.assem.MOVE;
import graph.Node;
import Temp.Label;
import Temp.LabelList;
import Temp.Temp;
import Temp.TempList;
import util.List;

public class AssemFlowGraph extends FlowGraph
{
    private Hashtable<Node, Instr> map;
    private Hashtable<Instr, Node> revMap;
    Hashtable <Label, Instr> map1;
    Hashtable <Instr, Label> revMap1;
        
    public AssemFlowGraph(InstrList list)
    {
        super();
        
        map = new Hashtable<Node, Instr>();
        revMap = new Hashtable<Instr, Node>();
        map1 = new Hashtable<Label, Instr>();
        revMap1 = new Hashtable<Instr,Label>();
        
        buildGraph(list);
    }
        
   
    public Instr instr(Node node)
    {
        return map.get(node);
    }
    
    public Node node(Instr instr)
    {
        return revMap.get(instr);
    }

    public TempList def(Node node)
    {
        Instr i = map.get(node);
        
        if ( i == null )
            return null;
        
        return i.def();
    }

    public TempList use(Node node)
    {
        Instr i = map.get(node);
        
        if ( i == null )
            return null;
        
        return i.use();
    }

    public boolean isMove(Node node)
    {
        Instr i = map.get(node);
        
        if ( i == null )
            return false;
        
        return i instanceof MOVE;
    }

private void buildGraph(InstrList ilist){ 
        // construcao do gr�fico e dos nos
        Instr aux_label = null, aux_branch = null;
        for( InstrList a = ilist ; a != null; a = (InstrList) a.tail ){
        	if ( a.head instanceof instructionSelection.Assem.LABEL ){
            		aux_label = a.head;
        	}else{
        		Node n = this.newNode();
        		map.put(n, a.head);            
        		revMap.put(a.head, n);     
        		if(aux_label!=null){ 
        			map1.put(((instructionSelection.Assem.LABEL)aux_label).label, a.head );
        			revMap1.put( a.head, ((instructionSelection.Assem.LABEL)aux_label).label);
        			aux_label = null;
        		}
            }
        }
        for ( InstrList aux = ilist; aux != null; aux = (InstrList) aux.tail ){
        	if(!(aux.head instanceof instructionSelection.Assem.LABEL)){
        		LabelList jmps = aux.head.jumps; 
        		
	            if ( jmps == null ){
	                if (aux.tail != null){
	                	if(!(aux.tail.head instanceof instructionSelection.Assem.LABEL)) this.addEdge(revMap.get(aux.head), revMap.get(aux.tail.head));
	                	else  this.addEdge(revMap.get(aux.head), revMap.get(map1.get(((instructionSelection.Assem.LABEL)aux.tail.head).label)));
	                } 
	            }
	            else{	         
	                for ( LabelList a = jmps; a != null; a = (LabelList) a.tail ){
	                	
	                	if(revMap1.contains(a.head)){
	                		this.addEdge(revMap.get(aux.head), revMap.get(map1.get(a.head)));	                		
	                	}
	                }
	            }
	            
	            if(aux_branch!=null){
	            	this.addEdge(revMap.get(aux_branch), revMap.get(aux.head));
	            	aux_branch = null;
	            }
	            
	            if(aux.head.branch) aux_branch = aux.head;
	            
        	}
        }        
    }
}
