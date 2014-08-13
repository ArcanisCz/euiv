/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.arcanis.euiv.data;

import java.io.File;

import cz.arcanis.euiv.Parser;
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

        Document doc = Parser.getDomTree(savegame);
        Parser.saveTreeToFile(doc, new File("../src/main/resources/out.xml"));
    }

}
