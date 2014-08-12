package cz.arcanis.euiv.parser.tokens.chains;

import cz.arcanis.euiv.parser.tokens.AbstractTokenizerStreamChain;
import cz.arcanis.euiv.parser.tokens.ITokenStream;
import de.susebox.jtopas.Token;

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

    public OnlySomeTopLevelSections(ITokenStream inStream) {
        super(inStream);
        this.fillBlacklist();
    }

    private void fillBlacklist() {
        blacklist.add("trade");
        blacklist.add("rebel_faction");
        blacklist.add("papacy");
        blacklist.add("fired_events");
        blacklist.add("provinces");
//        blacklist.add("countries");
        blacklist.add("active_advisors");
        blacklist.add("diplomacy");
        blacklist.add("combat");
//        blacklist.add("active_war");

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
            if (token.getType() == Token.NORMAL) {
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
