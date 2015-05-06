package symbol;

import java.util.LinkedHashMap;
import syntaxtree.*;

public class ClassTable 
{
	private String Name;
	private LinkedHashMap<Symbol, Type> varTable;
	private LinkedHashMap<Symbol, MethodTable> methodTable;
	public ClassTable(String name)
	{
		Name = name;
		varTable = new LinkedHashMap<Symbol, Type>();
		methodTable = new LinkedHashMap<Symbol, MethodTable>();
	}
	public boolean addVar(Symbol s, Type t)
	{
		if (!varTable.containsKey(s))
		{
			varTable.put(s,t);
			return true;
		}
		return false;
	}
	public boolean addMethod(Symbol s, MethodTable t)
	{
		if (!methodTable.containsKey(s))
		{
			methodTable.put(s,t);
			return true;
		}
		return false;
	}
	public String toString()
	{
		return Name;
	}
	public void PrettyPrint()
	{
		Set<Symbol> keys = varTable.keySet();
		for (Symbol i : keys)
			System.out.println(i.toString());
		Set<Symbol> keys2 = methodTable.keySet();
		for (Symbol i : keys2)
		{
			System.out.println("\tMethod "+i.toString());
			classTable.get(i).PrettyPrint();
			System.out.println("\t}");
		}
	}
	public MethodTable getMethod(Symbol s)
	{
		if (methodTable.contains(s))
			return methodTable.get(s);
		return null;
	}
	public Type getVar(Symbol s)
	{
		if (varTable.contains(s))
			return varTable.get(s);
		return null;
	}
} 
