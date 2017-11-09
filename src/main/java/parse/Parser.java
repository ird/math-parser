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
    public void nextToken() {
        tokes.pop();
        if(tokens.isEmpty()){
            lookahead = new Token(Token.EPSILON, "");
        } else {
            lookahead = token.getFirst();
        }
    }
    public void expression() {
        // rule: expression :- signed_term sum_op
        signedTerm();
        sumOp();
    }
    public void signedTerm() {
        if(lookahead.token == Token.PLUSMINUS) {
            nextToken();
        }
        term();
    }
    public void sumOp() {
        if(lookahed.token == Token.PLUSMINUS) {
            nextToken();
            term();
            sumOp();
        }
    }
}
