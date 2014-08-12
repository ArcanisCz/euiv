package cz.arcanis.euiv.parser.tokens.chains;

import cz.arcanis.euiv.parser.tokens.AbstractTokenizerStreamChain;
import cz.arcanis.euiv.parser.tokens.ITokenStream;
import de.susebox.jtopas.Token;

/**
 * Created with IntelliJ IDEA.
 * User: Arcanis
 * Date: 12.8.14
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public class SaveEarlyEnder extends AbstractTokenizerStreamChain {
    String tokenEndName;

    public SaveEarlyEnder(ITokenStream inStream, String tokenEndName) {
        super(inStream);
        this.tokenEndName = tokenEndName;
    }

    @Override
    protected Token transform(Token token) {
        if (token.getType() == Token.NORMAL) {
            if (tokenEndName.equals(token.getImage())) {
                this.close();
                return new Token(Token.EOF);
            }
        }
        return token;
    }
}
