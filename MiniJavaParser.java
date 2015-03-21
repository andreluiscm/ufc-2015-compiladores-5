/* Generated By:JavaCC: Do not edit this line. MiniJavaParser.java */
  public class MiniJavaParser implements MiniJavaParserConstants {

// The following is a simple grammar that will allow you
// to test the generated lexer.
  static final public void Goal() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAREN:
      case RPAREN:
      case LCOUCH:
      case RCOUCH:
      case VIRG:
      case DOT:
      case EQ:
      case LBRACE:
      case RBRACE:
      case SEMICOLON:
      case LESS:
      case NOT:
      case PLUS:
      case MINUS:
      case STAR:
      case AND:
      case IF:
      case ELSE:
      case WHILE:
      case NEW:
      case TRUE:
      case FALSE:
      case LENGTH:
      case THIS:
      case VOID:
      case PRINT:
      case BOOLEAN:
      case INTEGER:
      case STRING:
      case STATIC:
      case EXTENDS:
      case RETURN:
      case CLASS:
      case PUBLIC:
      case MAIN:
      case IDENTIFIER:
      case INTEGER_LITERAL:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      MiniJavaToken();
    }
    jj_consume_token(0);
  }

  static final public void MiniJavaToken() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CLASS:
      jj_consume_token(CLASS);
      break;
    case IDENTIFIER:
      jj_consume_token(IDENTIFIER);
      break;
    case LBRACE:
      jj_consume_token(LBRACE);
      break;
    case PUBLIC:
      jj_consume_token(PUBLIC);
      break;
    case STATIC:
      jj_consume_token(STATIC);
      break;
    case VOID:
      jj_consume_token(VOID);
      break;
    case MAIN:
      jj_consume_token(MAIN);
      break;
    case LPAREN:
      jj_consume_token(LPAREN);
      break;
    case STRING:
      jj_consume_token(STRING);
      break;
    case LCOUCH:
      jj_consume_token(LCOUCH);
      break;
    case RCOUCH:
      jj_consume_token(RCOUCH);
      break;
    case RPAREN:
      jj_consume_token(RPAREN);
      break;
    case RBRACE:
      jj_consume_token(RBRACE);
      break;
    case EXTENDS:
      jj_consume_token(EXTENDS);
      break;
    case SEMICOLON:
      jj_consume_token(SEMICOLON);
      break;
    case RETURN:
      jj_consume_token(RETURN);
      break;
    case VIRG:
      jj_consume_token(VIRG);
      break;
    case INTEGER:
      jj_consume_token(INTEGER);
      break;
    case BOOLEAN:
      jj_consume_token(BOOLEAN);
      break;
    case EQ:
      jj_consume_token(EQ);
      break;
    case IF:
      jj_consume_token(IF);
      break;
    case ELSE:
      jj_consume_token(ELSE);
      break;
    case WHILE:
      jj_consume_token(WHILE);
      break;
    case PRINT:
      jj_consume_token(PRINT);
      break;
    case AND:
      jj_consume_token(AND);
      break;
    case LESS:
      jj_consume_token(LESS);
      break;
    case PLUS:
      jj_consume_token(PLUS);
      break;
    case MINUS:
      jj_consume_token(MINUS);
      break;
    case STAR:
      jj_consume_token(STAR);
      break;
    case DOT:
      jj_consume_token(DOT);
      break;
    case LENGTH:
      jj_consume_token(LENGTH);
      break;
    case INTEGER_LITERAL:
      jj_consume_token(INTEGER_LITERAL);
      break;
    case TRUE:
      jj_consume_token(TRUE);
      break;
    case FALSE:
      jj_consume_token(FALSE);
      break;
    case THIS:
      jj_consume_token(THIS);
      break;
    case NEW:
      jj_consume_token(NEW);
      break;
    case NOT:
      jj_consume_token(NOT);
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public MiniJavaParserTokenManager token_source;
  static JavaCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[2];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xfffffffe,0xfffffffe,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x9f,0x9f,};
   }

  /** Constructor with InputStream. */
  public MiniJavaParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MiniJavaParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MiniJavaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public MiniJavaParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new MiniJavaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public MiniJavaParser(MiniJavaParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(MiniJavaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[45];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 2; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 45; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

                               }
