package symbol;

import java.util.LinkedHashMap;
import syntaxtree.*;

public class MethodTable 
{
	private String Name;
	private Type t;
	private LinkedHashMap<Symbol, Type> varTable;
	private LinkedHashMap<Symbol, Type> paramTable;
	public MethodTable(String name, Type t)
	{
		this.t = t;
		Name = name;
		varTable = new LinkedHashMap<Symbol, Type>();
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
	public boolean addParam(Symbol s, Type t)
	{
		if (!paramTable.containsKey(s))
		{
			paramTable.put(s,t);
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
		Set<Symbol> keys2 = paramTable.keySet();
		System.out.print("(");
		for (Symbol i : keys2)
			System.out.print(i.toString()+", ");
		System.out.print(") {\n");
		Set<Symbol> keys = varTable.keySet();
		for (Symbol i : keys)
			System.out.println("\t\t"+i.toString());
	}
	public Type getVar(Symbol s)
	{
		if (varTable.contains(s))
			return varTable.get(s);
		return null;
	}
	public Type getParam(Symbol s)
	{
		if (paramTable.contains(s))
			return paramTable.get(s);
		return null;
	}
} 
