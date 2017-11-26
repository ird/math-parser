package parse;

import java.util.LinkedList;

public class MathParser {
    public static void main(String[] args) {
        Tokenizer t = new Tokenizer();
        t.add("sin|cos|exp|ln|sqrt", Token.FUNCTION); // functions
        t.add("\\(", Token.OPENBRACKET);
        t.add("\\)", Token.CLOSEBRACKET);
        t.add("[+-]", Token.PLUSMINUS);
        t.add("[*/]", Token.MULTDIV);
        t.add("\\^", Token.RAISED);
        t.add("[0-9]+", Token.NUMBER);
        t.add("[a-zA-Z][a-zA-Z0-9_]*", Token.VAR); // var
        LinkedList<Token> tokens = new LinkedList<parse.Token>();
        try {
            t.tokenize(" sin(x) *(1 + var) ");
            for (Tokenizer.Token tok : t.tokens) {
                System.out.println("" + tok.token + " " + tok.seq);
                tokens.add(new parse.Token(tok.token, tok.seq));
            }
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

        Parser p = new Parser();
        p.parse(tokens);
    }
}
