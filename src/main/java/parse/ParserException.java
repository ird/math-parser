package parser;

public class ParserException extends RuntimeException {
    public ParserException() { super(); }
    public ParserException(String msg) { super(msg); }
    public ParserException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public ParserException(Throwable cause) { super(cause); }
}
