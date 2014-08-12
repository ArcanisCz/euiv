/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.arcanis.euiv.parser;

import de.susebox.jtopas.ReaderSource;
import de.susebox.jtopas.StandardTokenizer;
import de.susebox.jtopas.StandardTokenizerProperties;
import de.susebox.jtopas.Token;
import de.susebox.jtopas.Tokenizer;
import de.susebox.jtopas.TokenizerException;
import de.susebox.jtopas.TokenizerProperties;
import de.susebox.jtopas.TokenizerSource;
import java.io.File;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arcanis
 */
public class TokenizerWrapper {

    private boolean isEnded = false;
    private Token current = null;
    Tokenizer tokenizer;

    public TokenizerWrapper(File file) throws IOException {

        TokenizerProperties props = new StandardTokenizerProperties();

        props.addString("\"", "\"", "\\");
        props.addPattern("[0-9]{4}\\.[0-9]{1,2}\\.[0-9]{1,2}", "date");
        props.addPattern("[0-9]+\\.[0-9]{3}", "number");

        tokenizer = new StandardTokenizer(props);
        TokenizerSource source = new ReaderSource(file);
        tokenizer.setSource(source);
        setCurrent(getNextInternal());
    }

    public Token getNext() throws IOException {
        Token current1 = getCurrent();
        setCurrent(getNextInternal());
        return current1;
    }

    public Token getCurrent() {
        return this.current;
    }

    public boolean isEnded() {
        return this.isEnded;
    }

    protected void setEnded() {
        this.isEnded = true;
    }

    private void setCurrent(Token current) {
        this.current = current;
    }

    private Token getNextInternal() {
        try {
//            int count = 0;
//            int length = 0;

            Token token = tokenizer.nextToken();
            if (token.getType() == Token.EOF) {
                setEnded();
            } 
//            else if ("{".equals(token.getImage())) {
//                count++;
//                while (count > 0) {
//                    token = tokenizer.nextToken();
//                    if ("{".equals(token.getImage())) {
//                        count++;
//                    } else if ("}".equals(token.getImage())) {
//                        count--;
//                    } else {
//                        length++;
//                    }
//                }
//                token = new Token(Token.NORMAL, "dummy{" + length + "}");
//            }
            return token;
        } catch (TokenizerException ex) {
            setEnded();
            return new Token(Token.EOF);
        }

    }

}
