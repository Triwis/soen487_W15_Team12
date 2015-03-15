/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Louis-Maxime
 */
public class xmlUtils {
    public static Document getDocumentFromPath(String filePath) {
        File file = new File(filePath);
        String s = "";
        Document xmlDoc = null;
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = docBuilder.parse(file);
        }
        catch (ParserConfigurationException e) {e.getMessage();}
        catch (SAXException e) {s = e.getMessage();}
        catch (IOException e) {s=e.getMessage();}
        finally {
            return xmlDoc;
        }
    }
    
    public static List instantiateRandomPrice(Document xmlDoc, String path) {
        int x = 0;
        //goThroughDocument(xmlDoc.getDocumentElement());
        /*Element root = xmlDoc.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); ++i) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element)nodes.item(i);
                if (e.getNodeName().equals("price")) {
                    e.setTextContent("100");
                }
            }
        }*/
        List<String> list = new ArrayList<String>();
        NodeList stocks = xmlDoc.getElementsByTagName("stock");
        for (int i = 0; i < stocks.getLength(); ++i) {
            NodeList stockChild = stocks.item(i).getChildNodes();
            for (int j = 0; j < stockChild.getLength(); ++j) {
                Node n = stockChild.item(j);
                //list.add(n.getNodeName());
                if ("price".equals(n.getNodeName())) {
                    Random rand = new Random();
                    int value = rand.nextInt(721)+1;
                    n.setTextContent(Integer.toString(value));
                }
            }
        }
        Document newXML = null;
        //File file = new File("")
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(xmlDoc);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        }
        catch (Exception e) {}
        finally {
            return list;
        }
    }
    
    private static void goThroughDocument(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("price")) {
            Random rand = new Random();
            int value = rand.nextInt(501)+1;
            node.setTextContent(Integer.toString(value));
        }
        
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                goThroughDocument(currentNode);
            }
        }
    }
}
