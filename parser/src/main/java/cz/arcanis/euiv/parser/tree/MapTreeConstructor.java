package cz.arcanis.euiv.parser.tree;

import cz.arcanis.euiv.parser.tokens.ITokenStream;
import de.susebox.jtopas.Token;
import org.apache.commons.collections4.map.LinkedMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Arcanis
 * Date: 13.8.14
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public class MapTreeConstructor {
    public static Map<String, Object> createTree(ITokenStream stream) throws ParserConfigurationException {

        Deque<LinkedMap<String, Object>> stack = new LinkedList<>();

        LinkedMap<String, Object> root = new LinkedMap<>();
        LinkedMap<String, Object> current = root;


        while (!stream.isClosed()) {
            Token token = stream.next();
//            System.out.println("token1 " + token);
            if (token.getType() == Token.EOF) {
                //nothing
            } else if ("=".equals(token.getImage())) {
                Token token_value = stream.next();
//                System.out.println("token2 " + token_value);
                if ("{".equals(token_value.getImage())) {
                    String key = current.lastKey();
                    LinkedMap<String, Object> newCurrent = new LinkedMap<>();
                    current.put(key, newCurrent);
                    stack.push(current);
                    current = newCurrent;
//                    System.out.println(stack + " " + current);
                } else if ("}".equals(token_value.getImage())) {
                    current = stack.poll();
//                    System.out.println(stack + " " + current);
                } else {
                    String key = current.lastKey();
                    current.put(key, trimDoubleQuotes(token_value.getImage()));
                }
            } else if ("{".equals(token.getImage())) {
                String key = current.lastKey();
                LinkedMap<String, Object> newCurrent = new LinkedMap<>();
                current.put(key, newCurrent);
                stack.push(current);
                current = newCurrent;
//                System.out.println(stack + " " + current);
            } else if ("}".equals(token.getImage())) {
                current = stack.poll();
//                System.out.println(stack + " " + current);
            } else {
                current.put(trimDoubleQuotes(token.getImage()), null);
            }
//            DomTreeConstructor.prettyPrint(doc);
        }

        return root;
    }

    public static String trimDoubleQuotes(String text) {
        if (text != null) {
            int textLength = text.length();

            if (textLength >= 2 && text.charAt(0) == '"' && text.charAt(textLength - 1) == '"') {
                //return text.substring(1, textLength - 1);
                return text;
            } else {
                return "\"" + text + "\"";
            }
        }
        return null;

    }
}