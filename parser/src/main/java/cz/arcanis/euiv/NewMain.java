/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.arcanis.euiv;

import cz.arcanis.euiv.parser.TokenizerWrapper;
import cz.arcanis.euiv.parser.TreeConstructor;
import de.susebox.jtopas.Token;
import java.io.File;
import org.w3c.dom.Document;

/**
 *
 * @author Arcanis
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        File savegame = new File("./src/main/resources/pokus.eu4");
//        System.out.println(savegame.getAbsolutePath());

//        File savegame = new File("pokus.eu4");
        Document doc =  TreeConstructor.createTree(savegame);
        TreeConstructor.prettyPrint(doc);
//

    }

}
