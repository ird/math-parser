import java.util.regex.Pattern;

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

    private LinkedList<TokenInfo> tokenInfos;

    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>();
    }

    public void add(String regex, int token) {
        tokenInfos.add(
            new TokenInfo(Pattern.compile("^("+regex+")"), token));
    }
}
