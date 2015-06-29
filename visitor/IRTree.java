package visitor;

import java.util.*;
import syntaxtree.*;
import symbol.*;
import Translate.*;
import Tree.*;
import Frame.*;

public class IRTree implements IRvisitor
{
	private Frame currentFrame;
	private Frag initial;
	private Frag frags;
	private ClassTable currentClass;
	private MethodTable currentMethod;
	private ProgramTable pt;
	
	public IRTree (ProgramTable pt, Frame f)
	{
		this.pt = pt;
		frags = new Frag(null);
		initial = frags;
		currentFrame = f;
	}
	
	public void procEntryExit (Translate.Exp body)
	{
		ProcFrag f = new ProcFrag(body, frame);
		frags.addNext(f);
		frags = frags.getNext();
	}
	
	public Frag getResults()
	{
		return frags;
	}
	
	// MainClass m;
	// ClassDeclList cl;
	public Translate.Exp visit(Program n) {
		n.m.accept(this);
		for ( int i = 0; i < n.cl.size(); i++ ) {
			n.cl.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i1,i2;
	// Statement s;
	public Translate.Exp visit(MainClass n) {
		n.i1.accept(this);
		currentClass = pt.getClass(Symbol.symbol(n.i1.toString()));
		n.i2.accept(this);
		Symbol sy = Symbol.symbol(n.i2.toString());
		currentMethod = currentClass.getMethod(sy);
		Frame mFrame = currentFrame(sy,new BoolList(false,null));
		currentFrame = mFrame;
		Translate.Exp se = n.s.accept(this);
		Translate.Exp retExp = new CONST(0);
		Translate.Exp body = new MOVE( new Tree.TEMP(currentFrame.RV()) , new Tree.ESEQ( se , retExp ) );
		procEntryExit(body);
		return null;
	}

	// Identifier i;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Translate.Exp visit(ClassDeclSimple n) {
		n.i.accept(this);
		currentClass = pt.getClass(Symbol.symbol(n.i.toString()));
		currentMethod = null;
		for ( int i = 0; i < n.vl.size(); i++ ) {
			n.vl.elementAt(i).accept(this);
		}
		for ( int i = 0; i < n.ml.size(); i++ ) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i;
	// Identifier j;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Translate.Exp visit(ClassDeclExtends n) {
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
		return null;
	}

	// Type t;
	// Identifier i;
	//NOTHING TO DO HERE!!!
	public Translate.Exp visit(VarDecl n) {
		return null;
	}

	// Type t;
	// Identifier i;
	// FormalList fl;
	// VarDeclList vl;
	// StatementList sl;
	// Translate.Exp e;
	public Translate.Exp visit(MethodDecl n) {
		n.t.accept(this);
		currentMethod = currentClass.getMethod(Symbol.symbol(n.i.toString()));
		BoolList b = new BoolList(false, null);
		BoolList init = b;
		for ( int i = 0; i < n.fl.size(); i++ ) {
			n.fl.elementAt(i).accept(this);
			b.tail = new BoolList(false,null);
			b = b.tail;
		}
		b = init;
		Frame newf = currentFrame(Symbol.symbol(n.i.toString()),b);
		Frame oldf = currentFrame;
		currentFrame = newf;
		for ( int i = 0; i < n.vl.size(); i++ ) {
			n.vl.elementAt(i).accept(this);
		}
		Translate.Exp retExp = null;
		Translate.Exp body = null;
		if (!n.sl.size())
		{
			retExp = n.e.accept(this);
			body = new MOVE( new Tree.TEMP(currentFrame.RV()) , new Tree.ESEQ( null , retExp ) );
		}
		else
		{
			body = n.sl.elementAt(0).accept(this);
			for ( int i = 1; i < n.sl.size(); i++ ) {
				body = new SEQ(body, n.sl.elementAt(i).accept(this));
			}
			retExp = n.e.accept(this);
			body = new MOVE( new Tree.TEMP(currentFrame.RV()) , new Tree.ESEQ( body , retExp ) );
		}
		procEntryExit(body);
		return null;
	}

	// Type t;
	// Identifier i;
	//NOTHING TO DO HERE!!!
	public Translate.Exp visit(Formal n) {
		return null;
	}

	//NOTHING TO DO HERE!!!
	public Translate.Exp visit(IntArrayType n) {
		return null;
	}

	//NOTHING TO DO HERE!!!
	public Translate.Exp visit(BooleanType n) {
		return null;
	}

	//NOTHING TO DO HERE!!!
	public Translate.Exp visit(IntegerType n) {
		return null;
	}
	
	//NOTHING TO DO HERE!!!
	// String s;
	public Translate.Exp visit(IdentifierType n) {
		return null;
	}

	// StatementList sl;
	public Translate.Exp visit(Block n) {
		Stm actual,next;
		SEQ seq;
		actual = n.sl.elementAt(0).accept(this);
                for ( int i = 1; i < n.sl.size(); i++ ) 
                {
                	next = n.sl.elementAt(i).accept(this);
                	seq = new SEQ(seq,next);
                }

                return (Translate.Exp)seq;
	}

	// Translate.Exp e;
	// Statement s1,s2;
	public Translate.Exp visit(If n) {
		Translate.Exp exp = n.e.accept(this);          
                Translate.Exp if1 = n.s1.accept(this);
                Translate.Exp if2 = n.s2.accept(this);
                Label fal = new Label();
                Label tru = new Label();
                Label end = new Label();
                
                return new SEQ(new Tree.CJUMP(Tree.CJUMP.EQ, new CONST(1), exp, tru, fal),
                       new SEQ( new Tree.LABEL(tru), 
                                new SEQ ( if1,
                                        new SEQ ( new JUMP(end),
                                                new SEQ ( new Tree.LABEL(fal),
                                                        new SEQ (if2, 
                                                                new Tree.LABEL(end)
                                                                )))))); 	
	}

	// Translate.Exp e;
	// Statement s;
	public Translate.Exp visit(While n) {
                n.e.accept(this);               
                Translate.Exp exp = n.e.accept(this);
                Translate.Exp b = n.s.accept(this);
                Label loop = new Label();
                Label fim = new Label();
                Label inicio = new Label();          
                return new SEQ(new Tree.LABEL(inicio), 
                                 new SEQ(new Tree.CJUMP(Tree.CJUMP.EQ, new CONST(1), exp, loop, fim), 
                                                new SEQ(new Tree.LABEL(loop),
                                                                new SEQ(b,new SEQ(new JUMP(inicio), 
                                                                                                new Tree.LABEL(fim))))));
	}

	// Translate.Exp e;
	public Type visit(Translate.Print n) {
		Translate.Exp exp = n.e.accept(this);
		List<Translate.Exp> printarg = new LinkedList<Translate.Exp>();
		printarg.add(exp.unEx());
		return new Translate.Exp( frame.externalCall("printint",args) );
	}

	// Identifier i;
	// Translate.Exp e;
	public Translate.Exp visit(Assign n) {
		return new MOVE(new Tree.TEMP(n.i.toString()),n.e.accept(this));
	}

	// Identifier i;
	// Translate.Exp e1,e2;
	public Translate.Exp visit(ArrayAssign n) {
		return new MOVE( new Tree.BINOP(Tree.BINOP.PLUS, new Tree.MEM(new Tree.TEMP(n.i.toString())), n.e1.accept(this)),n.e2.accept(this) );
	}

	// Translate.Exp e1,e2;
	public Translate.Exp visit(And n) {
		Translate.Exp and1 = n.e1.accept(this);
                Translate.Exp and2 = n.e2.accept(this);               

                return new Tree.BINOP(Tree.BINOP.AND,and1,and2);
	}

	// Translate.Exp e1,e2;
	public Translate.Exp visit(LessThan n) {
                Translate.Exp lt1 = n.e1.accept(this);
                Translate.Exp lt2 = n.e2.accept(this);
                Tree.TEMP r = new Tree.TEMP(new temp.Temp());
                Label tru = new temp.Label();
                Label fal = new temp.Label();
                Label fim = new temp.Label();             
                return new Tree.ESEQ(                                
                                new SEQ(new tree.Tree.CJUMP(Tree.CJUMP.LT,lt1, lt2, tru, fal),                        
                                                new SEQ(new SEQ(new Tree.LABEL(tru),new SEQ(new MOVE(r, new CONST(1)),
                                                new JUMP(fim))),new SEQ(new Tree.LABEL(fal),
                                                                new SEQ(new MOVE(r, new CONST(0)),
                                                                new SEQ(new tree.JUMP(fim),new Tree.LABEL(fim)))))), r);  
	}

	// Translate.Exp e1,e2;
	public Translate.Exp visit(Plus n) {
                Translate.Exp plus1 = n.e1.accept(this);
                Translate.Exp plus2 = n.e2.accept(this);

                return new Tree.BINOP(Tree.BINOP.PLUS, plus1, plus2);
	}

	// Translate.Exp e1,e2;
	public Translate.Exp visit(Minus n) {
                Translate.Exp minus1 = n.e1.accept(this);
                Translate.Exp minus2 = n.e1.accept(this);

                return new Tree.BINOP(Tree.BINOP.MINUS, minus1, minus2);
	}

	// Translate.Exp e1,e2;
	public Translate.Exp visit(Times n) {
                Translate.Exp times1 = n.e1.accept(this);
                Translate.Exp times2 = n.e1.accept(this);

                return new Tree.BINOP(Tree.BINOP.MUL, times1, times2);
	}

	// Translate.Exp e1,e2;
	public Translate.Exp visit(ArrayLookup n) {
		Translate.Exp mem1 = n.e1.accept(this);
		Translate.Exp mem2 = n.e2.accept(this);
                return new Tree.MEM( new Tree.BINOP(Tree.BINOP.PLUS, new Tree.MEM(mem1), new Tree.MEM(mem2)));
	}

	// Translate.Exp e;
	public Translate.Exp visit(ArrayLength n) {
		//FAZER
	}

	// Translate.Exp e;
	// Identifier i;
	// ExpList el;
	public Translate.Exp visit(Call n) {
		ExpList expList = null;
		Translate.Exp node;
		for ( int i = 0; i < n.el.size(); i++ ) {
			node.el.elementAt(i).accept(this);
			expList = new ExpList(node, expList);
		}
		return Tree.CALL(new Tree.NAME(new Label(n.i.toString())), expList);
	}

	// int i;
	public Translate.Exp visit(IntegerLiteral n) {
		return new Ex(new CONST(n.i));
	}

	public Translate.Exp visit(True n) {
		return new Ex(new CONST(1));
	}

	public Translate.Exp visit(False n) {
		return new Ex(new CONST(0));
	}

	// String s;
	public Translate.Exp visit(IdentifierExp n) {
		return new Tree.TEMP(n.s);
	}

	public Translate.Exp visit(This n) {
		return Tree.TEMP("this");
	}

	// Translate.Exp e;
	public Translate.Exp visit(NewArray n) {
		return new Tree.TEMP(new Temp());
	}

	// Identifier i;
	public Translate.Exp visit(NewObject n) {
		return new Tree.TEMP(new Temp());
	}

	// Translate.Exp e;
	public Translate.Exp visit(Not n) {
		Translate.Exp element = n.e.accept(this);
		Translate.Exp not = new Tree.BINOP(Tree.BINOP.Minus, new CONST(1), element);
		return not;
	}

	// String s;
	public Translate.Exp visit(Identifier n) {
		return new Tree.TEMP(n.s);
	}
	
}
