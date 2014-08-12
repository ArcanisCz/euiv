/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.arcanis.euiv;

import java.io.File;

import cz.arcanis.euiv.parser.TreeConstructor;
import cz.arcanis.euiv.parser.tokens.chains.SaveEarlyEnder;
import cz.arcanis.euiv.parser.tokens.TokenStreamFileInput;
import de.susebox.jtopas.Token;
import org.w3c.dom.Document;


/**
 * @author Arcanis
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        File savegame = new File("../src/main/resources/pokus.eu4");

        TokenStreamFileInput tokenizer = new TokenStreamFileInput(savegame);
//        SaveEarlyEnder filter = new SaveEarlyEnder(tokenizer, "used_colonial_names");

//        while (!filter.isClosed()) {
//            Token t = filter.next();
//            System.out.println(t + " " + t.getCompanion());
//        }


        Document doc = TreeConstructor.createTree(tokenizer);
        TreeConstructor.prettyPrint(doc);


    }

}
