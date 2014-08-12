/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.arcanis.euiv;

import java.io.File;

import cz.arcanis.euiv.parser.dom.TreeConstructor;
import cz.arcanis.euiv.parser.tokens.chains.OnlySomeTopLevelSections;
import cz.arcanis.euiv.parser.tokens.chains.OnlyTopLevels;
import cz.arcanis.euiv.parser.tokens.chains.SaveEarlyEnder;
import cz.arcanis.euiv.parser.tokens.TokenStreamFileInput;
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
        File savegame = new File("../src/main/resources/france.eu4");

        TokenStreamFileInput tokenizer = new TokenStreamFileInput(savegame);
        SaveEarlyEnder filter = new SaveEarlyEnder(tokenizer, "previous_war");
        OnlySomeTopLevelSections filter1 = new OnlySomeTopLevelSections(filter);
        OnlyTopLevels filter2 = new OnlyTopLevels(filter1);

//        int count = 0;
//        while (!filter2.isClosed()) {
//            Token t = filter2.next();
//            System.out.println(t + " " + t.getCompanion());
//            count++;
//        }
//        System.out.println("Tokens: " + count);


        Document doc = TreeConstructor.createTree(filter2);
        TreeConstructor.prettyPrint(doc);


    }

}
