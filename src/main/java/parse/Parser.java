package parser;

public class Parser {
    LinkedList<Token> tokens;
    Token lookahead;
    public void parse(List<Token> tokens) {
        this.tokens = tokens.clone();
        lookahead = this.tokens.getFirst();
        expression();
        if(lookahead.token != Token.EPSILON) {
            throw new ParserException("Unexpected token; " + lookahead);
        }
    }
    public void expression() {

    }
}
