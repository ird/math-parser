package parse;

import java.util.LinkedList;
import java.util.List;

public class Parser {
    LinkedList<Token> tokens;
    Token lookahead;
    public void parse(LinkedList<Token> tokens) {
        this.tokens = (LinkedList<Token>) tokens.clone();
        lookahead = this.tokens.getFirst();
        expression();
        if(lookahead.token != Token.EPSILON) {
            throw new ParserException("Unexpected token; " + lookahead);
        }
    }
    public void nextToken() {
        tokens.pop();
        if(tokens.isEmpty()){
            lookahead = new Token(Token.EPSILON, "");
        } else {
            lookahead = tokens.getFirst();
        }
    }
    public void expression() {
        // rule: expression -> signed_term sum_op
        signedTerm();
        sumOp();
    }
    public void signedTerm() {
        // signed_term -> PLUSMINUS term
        // signed_term -> term
        if(lookahead.token == Token.PLUSMINUS) {
            nextToken();
        }
        term();
    }
    public void sumOp() {
        // sum_op -> PLUSMINUS term sum_op
        // sum_op -> EPSILON
        if(lookahead.token == Token.PLUSMINUS) {
            nextToken();
            term();
            sumOp();
        }
    }
    public void term() {
        // term -> factor termOp
        factor();
        termOp();
    }
    public void signedFactor() {
        // signed_factor -> PLUSMINUS factor
        // signed_factor -> factor
        // TODO
    }
    public void termOp() {
        // term_op -> MULTDIV factor term_op
        // term_op -> EPSILON
        if(lookahead.token == Token.MULTDIV) {
            nextToken();
            signedFactor();
            termOp();
        }
    }
}
