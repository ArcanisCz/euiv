/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.arcanis.euiv.parser.tree;

import cz.arcanis.euiv.parser.tokens.ITokenStream;
import de.susebox.jtopas.Token;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Arcanis
 */
public class DomTreeConstructor {

    public static Document createTree(ITokenStream stream) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element root = doc.createElement("root");
        doc.appendChild(root);

        Node current = root;

        while (!stream.isClosed()) {
            Token token = stream.next();
//            System.out.println("token " + token);           
            if (token.getType() == Token.EOF) {
            } else if ("=".equals(token.getImage())) {
                Token token_value = stream.next();
//                System.out.println("value " + token_value);
                if ("{".equals(token_value.getImage())) {
                    current = current.getLastChild();
                } else if ("}".equals(token_value.getImage())) {
                    current = current.getParentNode();
                } else {
                    ((Element) current.getLastChild()).setAttribute("value", trimDoubleQuotes(token_value.getImage()));
                }
            } else if ("{".equals(token.getImage())) {
                if (current.getLastChild() == null) {
                    Element element = doc.createElement("asdasd");
                    current.appendChild(element);
                }
                current = current.getLastChild();
            } else if ("}".equals(token.getImage())) {
                current = current.getParentNode();
            } else {
                Element element = doc.createElement("item");
                element.setAttribute("name", trimDoubleQuotes(token.getImage()));
                current.appendChild(element);
            }
//            DomTreeConstructor.prettyPrint(doc);
        }

        return doc;
    }

    public static String trimDoubleQuotes(String text) {
        if (text != null) {
            int textLength = text.length();

            if (textLength >= 2 && text.charAt(0) == '"' && text.charAt(textLength - 1) == '"') {
                return text.substring(1, textLength - 1);
            }

            return text;
        }
        return null;

    }

}
