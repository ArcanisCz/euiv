package cz.arcanis.euiv.parser.tokens;

import de.susebox.jtopas.Token;

/**
 * Created with IntelliJ IDEA.
 * User: Arcanis
 * Date: 12.8.14
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractTokenizerStreamChain implements ITokenStream {
    ITokenStream inStream;

    public AbstractTokenizerStreamChain(ITokenStream inStream) {
        this.inStream = inStream;
    }

    @Override
    public Token next() {
        if (!inStream.isClosed()) {
            Token token = transform(inStream.next());
            while (token == null) {
                if (!inStream.isClosed()) {
                    token = transform(inStream.next());
                } else {
                    return null;
                }
            }
            return token;
        }
        return null;
    }

    protected abstract Token transform(Token token);

    @Override
    public boolean isClosed() {
        return inStream.isClosed();
    }

    @Override
    public void close() {
        inStream.close();
    }
}
