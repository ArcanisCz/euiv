package cz.arcanis.euiv.parser.tokens.chains;

import cz.arcanis.euiv.parser.tokens.AbstractTokenizerStreamChain;
import cz.arcanis.euiv.parser.tokens.ITokenStream;
import de.susebox.jtopas.Token;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Arcanis
 * Date: 13.8.14
 * Time: 0:12
 * To change this template use File | Settings | File Templates.
 */
public class OnlySomeTopLevelSections extends AbstractTokenizerStreamChain {
    Set<String> blacklist = new HashSet<>();
    private int depth = 0;
    private String currentSection = "";

    public OnlySomeTopLevelSections(ITokenStream inStream, String[] blacklist) {
        super(inStream);
        this.blacklist.addAll(Arrays.asList(blacklist));
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
        }

        if (depth == 0) {
            if (token.getType() == Token.NORMAL || token.getType() == Token.PATTERN) {
                currentSection = token.getImage();
            }
        }

        if (!blacklist.contains(currentSection)) {
            return token;
        } else {
            return null;
        }
    }
}
