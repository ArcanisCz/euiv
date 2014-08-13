package cz.arcanis.euiv.parser.tokens.chains;

import cz.arcanis.euiv.parser.tokens.AbstractTokenizerStreamChain;
import cz.arcanis.euiv.parser.tokens.ITokenStream;
import de.susebox.jtopas.Token;

/**
 * Created with IntelliJ IDEA.
 * User: Arcanis
 * Date: 13.8.14
 * Time: 1:12
 * To change this template use File | Settings | File Templates.
 */
public class OnlyTopLevels extends AbstractTokenizerStreamChain {
    private final int maxLevel;
    private int depth = 0;

    public OnlyTopLevels(ITokenStream inStream, int maxLevel) {
        super(inStream);
        this.maxLevel = maxLevel;
    }

    @Override
    protected Token transform(Token token) {
        if (token == null) {
            this.close();
            return new Token(Token.EOF);
        }

        if ("{".equals(token.getImage())) {
            depth++;
        } else if ("}".equals(token.getImage())) {
            depth--;
            if (depth + 1 == maxLevel) {
                return new Token(Token.NORMAL, "{}");
            }
        }

        if (depth >= maxLevel) {
            return null;
        } else {
            return token;
        }
    }
}
