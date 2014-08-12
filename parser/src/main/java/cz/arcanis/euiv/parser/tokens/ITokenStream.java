package cz.arcanis.euiv.parser.tokens;

import de.susebox.jtopas.Token;

/**
 * Created with IntelliJ IDEA.
 * User: Arcanis
 * Date: 12.8.14
 * Time: 23:17
 * To change this template use File | Settings | File Templates.
 */
public interface ITokenStream {

    public Token next();

    public boolean isClosed();

    public void close();
}
