/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.arcanis.euiv.parser;

import de.susebox.jtopas.Token;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Arcanis
 */
public class TreeConstructor {

    public static Document createTree(File file) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element root = doc.createElement("root");
        doc.appendChild(root);

        Node current = root;
        TokenizerSectionFilter tokenizer = new TokenizerSectionFilter(new TokenizerWrapper(file));

        while (!tokenizer.isEnded()) {
            Token token = tokenizer.getNext();
//            System.out.println("token " + token);           
            if (token.getType() == Token.EOF) {
            } else if ("=".equals(token.getImage())) {
                Token token_value = tokenizer.getNext();
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
                    Element element = doc.createElement("dummy");
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
//            TreeConstructor.prettyPrint(doc);
        }

        return doc;
    }

    public static final void prettyPrint(Document xml) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new FileWriter(new File("src/main/resources/out.xml"));
        tf.transform(new DOMSource(xml), new StreamResult(out));
    }

    public static String trimDoubleQuotes(String text) {
        int textLength = text.length();

        if (textLength >= 2 && text.charAt(0) == '"' && text.charAt(textLength - 1) == '"') {
            return text.substring(1, textLength - 1);
        }

        return text;
    }

}
