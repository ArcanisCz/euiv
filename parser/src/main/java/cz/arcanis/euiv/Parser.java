package cz.arcanis.euiv;

import cz.arcanis.euiv.parser.tree.DomTreeConstructor;
import cz.arcanis.euiv.parser.tokens.TokenStreamFileInput;
import cz.arcanis.euiv.parser.tokens.chains.OnlySomeTopLevelSections;
import cz.arcanis.euiv.parser.tokens.chains.OnlyTopLevels;
import cz.arcanis.euiv.parser.tokens.chains.SaveEarlyEnder;
import cz.arcanis.euiv.parser.tree.MapTreeConstructor;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Arcanis
 * Date: 13.8.14
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public class Parser {

    public static Document getDomTree(File save) throws IOException, ParserConfigurationException {
        String[] blacklist = new String[]{"trade", "rebel_faction", "papacy", "fired_events", "provinces", "active_advisors", "diplomacy", "combat"};
        String hardStop = "previous_war";
        int maxDepth = 3;


        TokenStreamFileInput tokenizer = new TokenStreamFileInput(save);
        SaveEarlyEnder filter = new SaveEarlyEnder(tokenizer, hardStop);
        OnlySomeTopLevelSections filter1 = new OnlySomeTopLevelSections(filter, blacklist);
        OnlyTopLevels filter2 = new OnlyTopLevels(filter1, maxDepth);
        Document doc = DomTreeConstructor.createTree(filter2);
        return doc;
    }

    public static Map getMapTree(File save) throws IOException, ParserConfigurationException {
        String[] blacklist = new String[]{"trade", "rebel_faction", "papacy", "fired_events", "provinces", "active_advisors", "diplomacy", "combat"};
        String hardStop = "previous_war";
        int maxDepth = 3;

        TokenStreamFileInput tokenizer = new TokenStreamFileInput(save);
        SaveEarlyEnder filter = new SaveEarlyEnder(tokenizer, hardStop);
        OnlySomeTopLevelSections filter1 = new OnlySomeTopLevelSections(filter, blacklist);
        OnlyTopLevels filter2 = new OnlyTopLevels(filter1, maxDepth);
        Map doc = MapTreeConstructor.createTree(filter2);
        return doc;
    }

    public static final void saveTreeToFile(Document doc, File file) throws TransformerException, IOException {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new FileWriter(file);
        tf.transform(new DOMSource(doc), new StreamResult(out));
    }
}
