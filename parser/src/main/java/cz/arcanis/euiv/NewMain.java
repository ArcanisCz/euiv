/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.arcanis.euiv;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

/**
 * @author Arcanis
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        File savegame = new File("../src/main/resources/france1.eu4");


        String str = getJsonFromFile("../src/main/resources/france.eu4");
        FileWriter f = new FileWriter("../src/main/resources/out.json");
        f.write(str);
        f.close();

    }

    public static String getJsonFromFile(String file) throws Exception {
        File savegame = new File(file);

        Map doc = Parser.getMapTree(savegame);

        String str = doc.toString();
        FileWriter f = new FileWriter(new File("./out.txt"));
        f.write(str);
        f.close();
        str = str.replace("=", ":");
        return str;
    }

}
