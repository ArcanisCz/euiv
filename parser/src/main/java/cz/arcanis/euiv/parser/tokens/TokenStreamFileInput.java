package cz.arcanis.euiv.parser.tokens;

import de.susebox.jtopas.*;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Arcanis
 * Date: 12.8.14
 * Time: 22:10
 * To change this template use File | Settings | File Templates.
 */
public class TokenStreamFileInput implements ITokenStream {

    Tokenizer tokenizer;
    private boolean isClosed = false;

    public TokenStreamFileInput(File file) throws IOException {

        TokenizerProperties props = new StandardTokenizerProperties();

        props.addPattern("\"[0-9]{4}\\.[0-9]{1,2}\\.[0-9]{1,2}\"", "date");
        props.addPattern("[0-9]{4}\\.[0-9]{1,2}\\.[0-9]{1,2}", "date");
        props.addPattern("[0-9]+\\.[0-9]{3,5}", "number");
        props.addPattern("\\-\\-\\-");
        props.addString("\"", "\"", "\\");

        tokenizer = new StandardTokenizer(props);
        TokenizerSource source = new ReaderSource(file);
        tokenizer.setSource(source);
    }

    @Override
    public Token next() {
        try {
            if (!this.isClosed()) {
                Token t = tokenizer.nextToken();
                if (!tokenizer.hasMoreToken()) {
                    this.close();
                }
                return t;
            } else {
                return null;
            }
        } catch (TokenizerException ex) {
            return new Token(Token.EOF);
        }
    }

    @Override
    public boolean isClosed() {
        return this.isClosed;
    }

    @Override
    public void close() {
        tokenizer.close();
        this.isClosed = true;
    }

    //    public Token getNext() {
//        if (!this.isEnded()) {
//            Token token = this.getNextInternal();
//            if (token == null || token.getType() == Token.EOF) {
//                setEnded();
//            }
//            return token;
//        }
//        return null;
//    }

}
