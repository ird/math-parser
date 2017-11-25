package parse;

import java.util.LinkedList;

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
    private void nextToken() {
        tokens.pop();
        if(tokens.isEmpty()){
            lookahead = new Token(Token.EPSILON, "");
        } else {
            lookahead = tokens.getFirst();
        }
    }
    private void expression() {
        // rule: expression -> signed_term sum_op
        signedTerm();
        sumOp();
    }
    private void signedTerm() {
        // signed_term -> PLUSMINUS term
        // signed_term -> term
        if(lookahead.token == Token.PLUSMINUS) {
            nextToken();
        }
        term();
    }
    private void sumOp() {
        // sum_op -> PLUSMINUS term sum_op
        // sum_op -> EPSILON
        if(lookahead.token == Token.PLUSMINUS) {
            nextToken();
            term();
            sumOp();
        }
    }
    private void term() {
        // term -> factor termOp
        // only the subsequent terms can be proceeded by a PLUSMINUS
        factor();
        termOp();
    }
    private void termOp() {
        // term_op -> MULTDIV signed_factor term_op
        // term_op -> EPSILON
        if(lookahead.token == Token.MULTDIV) {
            nextToken();
            signedFactor();
            termOp();
        }
    }
    private void factor() {
        // factor -> argument factor_op
        argument();
        factorOp();

    }
    private void signedFactor() {
        // signed_factor -> PLUSMINUS factor
        // signed_factor -> factor
        if(lookahead.token == Token.PLUSMINUS) {
            nextToken();
        }
        factor();
    }
    private void argument() {
        // argument -> FUNCTION argument
        // argument -> OPENBRACKET expression CLOSEBRACKET
        // argument -> value

        switch (lookahead.token) {
            case Token.FUNCTION:
                nextToken();
                argument();
                break;
            case Token.OPENBRACKET:
                nextToken();
                expression();
                if(lookahead.token != Token.CLOSEBRACKET) {
                    throw new ParserException("')' not found: " + lookahead.seq);
                }
                nextToken();
                break;
            default:
                value();
        }
    }
    private void factorOp() {
        // factor_op -> RAISED signed_factor
        // factor_op -> EPSILON
        if(lookahead.token == Token.RAISED) {
            nextToken();
            signedFactor();
        }
    }
    private void value() {
        // value -> number
        // value -> variable
        switch (lookahead.token) {
            case Token.NUMBER:
                nextToken();
                break;
            case Token.VAR:
                nextToken();
                break;
            default:
                throw new ParserException("Unknown symbol: " + lookahead.seq);
        }
    }
}
