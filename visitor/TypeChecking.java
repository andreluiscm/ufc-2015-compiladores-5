package visitor;

import syntaxtree.*;

public class TypeChecking
{

	private ClassTable currentClass;
	private MethodTable currentMethod;
	private ProgramTable pt;
	private ErrorMsg error;
	
	TypeChecking (ProgramTable pt)
	{
		this.pt = pt;
	}
	
	// MainClass m;
	// ClassDeclList cl;
	public void visit(Program n) {
		error = new ErrorMsg();
		n.m.accept(this);
		for ( int i = 0; i < n.cl.size(); i++ ) {
			n.cl.elementAt(i).accept(this);
		}
	}

	// Identifier i1,i2;
	// Statement s;
	public void visit(MainClass n) {
		n.i1.accept(this);
		currentClass = pt.getClass(Symbol.symbol(n.i1.toString()));
		n.i2.accept(this);
		currentMethod = currentClass.getMethod(Symbol.symbol(n.i2.toString()));
		n.s.accept(this);
	}

	// Identifier i;
	// VarDeclList vl;
	// MethodDeclList ml;
	public void visit(ClassDeclSimple n) {
		n.i.accept(this);
		currentClass = pt.getClass(Symbol.symbol(n.i.toString()));
		currentMethod = null;
		for ( int i = 0; i < n.vl.size(); i++ ) {
			n.vl.elementAt(i).accept(this);
		}
		for ( int i = 0; i < n.ml.size(); i++ ) {
			n.ml.elementAt(i).accept(this);
		}
	}

	// Identifier i;
	// Identifier j;
	// VarDeclList vl;
	// MethodDeclList ml;
	public void visit(ClassDeclExtends n) {
		n.i.accept(this);
		currentClass = pt.getClass(Symbol.symbol(n.i.toString()));
		n.j.accept(this);
		currentMethod = null;
		for ( int i = 0; i < n.vl.size(); i++ ) {
			n.vl.elementAt(i).accept(this);
		}
		for ( int i = 0; i < n.ml.size(); i++ ) {
			n.ml.elementAt(i).accept(this);
		}
	}

	// Type t;
	// Identifier i;
	public void visit(VarDecl n) {
		n.t.accept(this);
		n.i.accept(this);
	}

	// Type t;
	// Identifier i;
	// FormalList fl;
	// VarDeclList vl;
	// StatementList sl;
	// Exp e;
	public void visit(MethodDecl n) {
		n.t.accept(this);
		n.i.accept(this);
		currentMethod = currentClass.getMethod(Symbol.symbol(n.i2.toString()));
		for ( int i = 0; i < n.fl.size(); i++ ) {
			n.fl.elementAt(i).accept(this);
		}
		for ( int i = 0; i < n.vl.size(); i++ ) {
			n.vl.elementAt(i).accept(this);
		}
		for ( int i = 0; i < n.sl.size(); i++ ) {
			n.sl.elementAt(i).accept(this);
		}
		Type t = n.e.accept(this);
		if (t.toString.equals(n.t.toString()))
			error.complain("Return expression does not match with the return type of method "+currentMethod.toString());
	}

	// Type t;
	// Identifier i;
	public void visit(Formal n) {
		n.t.accept(this);
		n.i.accept(this);
	}

	public void visit(IntArrayType n) {
	}

	public void visit(BooleanType n) {
	}

	public void visit(IntegerType n) {
	}

	// String s;
	public void visit(IdentifierType n) {
	}

	// StatementList sl;
	public void visit(Block n) {
		for ( int i = 0; i < n.sl.size(); i++ ) {
			n.sl.elementAt(i).accept(this);
		}
	}

	// Exp e;
	// Statement s1,s2;
	public void visit(If n) {
		if (! (n.e.accept(this) instanceof BooleanType))
			error.complain("If statement condition must be boolean");
		n.s1.accept(this);
		n.s2.accept(this);
	}

	// Exp e;
	// Statement s;
	public void visit(While n) {
		if (! (n.e.accept(this) instanceof BooleanType))
			error.complain("While statement condition must be boolean");
		n.s.accept(this);
	}

	// Exp e;
	public void visit(Print n) {
		n.e.accept(this);
	}

	// Identifier i;
	// Exp e;
	public void visit(Assign n) {
		Type var = whatType(n.i.toString());
		Type exp = n.e.accept(this);
		if (var instanceof IdentifierType) //Class
		{
			ClassTable c = pt.getClass(Symbol.symbol(((IdentifierType) var).toString()));
			if (c == null || !c.toString().equals(exp.toString()))
				error.complain("Assign types not maching");
		}
		else
		{
			if (var == null || exp == null || !var.toString().equals(exp.toString()))
				error.complain("Assign types not maching");
		}
	}

	// Identifier i;
	// Exp e1,e2;
	public void visit(ArrayAssign n) {
		Type var = whatType(n.i.toString());
		if (var == null)
			error.complain("Array Type not declared");
		else if (!(var instanceof IntegerType))
			error.complain("Array must be integer");
		if (! (n.e1.accept(this) instanceof IntegerType))
			error.complain("Iterator of Array must be integer");
		if (! (n.e2.accept(this) instanceof IntegerType))
			error.complain("Right side expression of Array Assign must be integer");
	}

	// Exp e1,e2;
	public Type visit(And n) {
		if (! (n.e1.accept(this) instanceof BooleanType))
			error.complain("Left side of operator && must be boolean");
		if (! (n.e2.accept(this) instanceof BooleanType))
			error.complain("Right side of operator && must be boolean");
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(LessThan n) {
		if (! (n.e1.accept(this) instanceof IntegerType))
			error.complain("Left side of operator < must be integer");
		if (! (n.e2.accept(this) instanceof IntegerType))
			error.complain("Right side of operator < must be integer");
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(Plus n) {
		if (! (n.e1.accept(this) instanceof IntegerType))
			error.complain("Left side of operator + must be integer");
		if (! (n.e2.accept(this) instanceof IntegerType))
			error.complain("Right side of operator + must be integer");
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Minus n) {
		if (! (n.e1.accept(this) instanceof IntegerType))
			error.complain("Left side of operator - must be integer");
		if (! (n.e2.accept(this) instanceof IntegerType))
			error.complain("Right side of operator - must be integer");
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Times n) {
		if (! (n.e1.accept(this) instanceof IntegerType))
			error.complain("Left side of operator * must be integer");
		if (! (n.e2.accept(this) instanceof IntegerType))
			error.complain("Right side of operator * must be integer");
		return new IntegerType();
	}

	// Exp e1,e2;
	public void visit(ArrayLookup n) {
		if (! (n.e1.accept(this) instanceof IntegerType))
			error.complain("Array must be integer");
		if (! (n.e2.accept(this) instanceof IntegerType))
			error.complain("Iterator of Array must be integer");
		return new IntegerType();
	}

	// Exp e;
	public void visit(ArrayLength n) {
		if (! (n.e.accept(this) instanceof IntegerType))
			error.complain("Array Length must be integer");
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public void visit(Call n) {
		n.e.accept(this);
		n.i.accept(this);
		for ( int i = 0; i < n.el.size(); i++ ) {
			n.el.elementAt(i).accept(this);
		}
	}

	// int i;
	public Type visit(IntegerLiteral n) {
		return new IntegerLiteral();
	}

	public Type visit(True n) {
		return new BooleanType();
	}

	public Type visit(False n) {
		return new BooleanType();
	}

	// String s;
	public Type visit(IdentifierExp n) {
		Type t = whatType(n.s);
		if (t == null)
			error.complain("Identifier not found");
		return t;
	}

	public Type visit(This n) {
		if (currentClass == null)
			error.complain("Class Environment not found");
		return new IdentifierType(currentClass.toString());
	}

	// Exp e;
	public Type visit(NewArray n) {
		if (! (n.e.accept(this) instanceof IntegerType))
			error.complain("Array size must be integer");
		return new IntArrayType();
	}

	// Identifier i;
	public Type visit(NewObject n) {
		ClassTable c = pt.getClass(n.i.toString());
		if (c == null)
			error.complain("Class used in new object not found");
		return IdentifierType(c.toString());
	}

	// Exp e;
	public Type visit(Not n) {
		n.e.accept(this);
		return new BooleanType();
	}

	// String s;
	public Type visit(Identifier n) {
		return whatType(n.s);
	}
	
	public void PrettyPrint()
	{
		pt.PrettyPrint();
	}
	public Type whatType(String id)
	{
		if ((currentMethod != null) && (currentMethod.getVar(Symbol.symbol(id)) != null))
			return currentMethod.getVar(Symbol.symbol(id));
		if ((currentMethod != null) && (currentMethod.getParam(Symbol.symbol(id)) != null))
			return currentMethod.getParam(Symbol.symbol(id));
		if ((currentClass != null) && (currentClass.getVar(Symbol.symbol(id)) != null))
			return currentClass.getVar(Symbol.symbol(id));
		if (pt.getClass(Symbol.symbol(id)) != null)
			return new IdentifierType(id);
		return null;
	}
}
