/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.arcanis.euiv.parser;

import de.susebox.jtopas.Token;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Arcanis
 */
public class TokenizerSectionFilter {

    private final TokenizerWrapper tokenizer;

    private static final Set<String> blacklist = new HashSet<>(Arrays.asList(
            new String[]{"trade","rebel_faction","papacy","fired_events","provinces","countries","active_advisors","diplomacy","combat","active_war"}
    ));
    private String currentSection = "";
    private static final String hardStop = "previous_war";

    public TokenizerSectionFilter(TokenizerWrapper tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Token getNext() throws IOException {
        int count = 0;
        int length = 0;

        Token token = tokenizer.getNext();
        if (token.getType() == Token.NORMAL) {
            if (token.getImage().equals(hardStop)) {
                tokenizer.setEnded();
                token = new Token(Token.EOF);
            }
            currentSection = token.getImage();
        } else if ("{".equals(token.getImage()) && blacklist.contains(currentSection)) {
            count++;
            while (count > 0) {
                token = tokenizer.getNext();
                if (null != token.getImage()) {
                    switch (token.getImage()) {
                        case "{":
                            count++;
                            break;
                        case "}":
                            count--;
                            break;
                        default:
                            length++;
                            break;
                    }
                }
            }
            token = new Token(Token.NORMAL, "dummy{" + length + "}");
        }
        return token;
    }

    public Token getCurrent() {
        return tokenizer.getCurrent();
    }

    public boolean isEnded() {
        return tokenizer.isEnded();
    }
}
