package parser;

public class Token {
    public static final int EPSILON = 0;
    public static final int PLUSMINUS = 1;
    public static final int MULTDIV = 2;
    public static final int RAISED = 3;
    public static final int FUNCTION = 4;
    public static final int OPENBRACKET = 5;
    public static final int CLOSEBRACKET = 6;
    public static final int NUMBER = 7;
    public static final int VAR = 8;

    public final int token;
    public final String seq;
    public Token(int token, String seq) {
        super();
        this.token = token;
        this.seq = seq;
    }
}
