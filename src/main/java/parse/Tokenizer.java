package parse;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.LinkedList;

public class Tokenizer {
    private class TokenInfo {
        public final Pattern regex;
        public final int token;

        public TokenInfo(Pattern regex, int token) {
            super();
            this.regex = regex;
            this.token = token;
        }
    }
    public class Token {
        public final String seq;
        public final int token;

        public Token(int token, String seq) {
            super();
            this.token = token;
            this.seq = seq;
        }
    }
    private LinkedList<TokenInfo> tokenInfos;
    public LinkedList<Token> tokens;
    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>();
        tokens = new LinkedList<Token>();
    }
    public void add(String regex, int token) {
        tokenInfos.add(
            new TokenInfo(Pattern.compile("^("+regex+")"), token));
    }
    public void tokenize(String str) {
        String s = new String(str);
        s = s.trim();
        tokens.clear();
        while(!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String token = s.substring(m.start(), m.end()).trim();
                    tokens.add(new Token(info.token, token));
                    s = m.replaceFirst("").trim();
                    break;
                }
            }
            if(!match) {
                throw new ParserException("Unexpected char in input: " + s);
            }
        }

    }
    public static void main(String[] args) {
        Tokenizer t = new Tokenizer();
        t.add("sin|cos|exp|ln|sqrt", 1); // functions
        t.add("\\(", 2);
        t.add("\\)", 3);
        t.add("[+-]", 4);
        t.add("[*/]", 5);
        t.add("\\^", 6);
        t.add("[0-9]+", 7);
        t.add("[a-zA-Z][a-zA-Z0-9_]*", 8); // var
        try {
            t.tokenize(" sin(x) * (1 + var_12) ");
            for (Tokenizer.Token tok : t.tokens) {
                System.out.println("" + tok.token + " " + tok.seq);
            }
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }
    }
}
