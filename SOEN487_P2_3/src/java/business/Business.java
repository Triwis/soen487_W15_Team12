/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import utils.xmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ckahn
 */
public class Business {
    String symbol;
    File shares;
    File stockIssues; 
    
    /**
     * Constructor
     * 
     * @param sharesPath
     * @param stockIssuesPath 
     */
    public Business(String symbol, String sharesPath, String stockIssuesPath) {
        this.symbol = symbol;
        this.shares = new File(sharesPath);
        this.stockIssues = new File(stockIssuesPath);
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    /**
     * Issue new shares.
     * 
     * @param sp
     * @return 
     */
    public boolean issueShares(SharePurchase sp) throws TransformerException {
        Share share = getShareInfo(sp.shareType);
        if (sp.unitPrice >= share.unitPrice) {
            int requested = sp.quantity;
            while (requested > 0) {
                if (requested < 100) {
                    this.authorizeShare(sp.shareType, requested);
                    requested = 0;
                }
                else {
                    this.authorizeShare(sp.shareType, 100);
                    requested -= 100;
                }
            }
            
            Document issuesDoc = xmlUtils.getXMLDocumentFromFile(this.stockIssues);
            Element newOrder = issuesDoc.createElement("order");
            newOrder.setAttribute("brokerRef", sp.brokerRef);
            newOrder.setAttribute("shareType", sp.shareType);
            newOrder.setAttribute("quantity", String.valueOf(sp.quantity));
            newOrder.setAttribute("unitPrice", String.valueOf(sp.unitPrice));
            newOrder.setAttribute("paid", "false");
            issuesDoc.appendChild(newOrder);
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(this.stockIssues);
            Source input = new DOMSource(issuesDoc);

            transformer.transform(input, output);
            
            return true;
        }
        return false;
    }
    
    /**
     * Gets the information for a type of share, for this business.
     * 
     * @param aShareType the type of share to get info for
     * @return a Share
     */
    public Share getShareInfo(String aShareType) {
        if (aShareType == "common" ||
            aShareType == "preferred" ||
            aShareType == "convertible"
        ) {
            Document sharesDoc = xmlUtils.getXMLDocumentFromFile(this.shares);
            NodeList nodeList = sharesDoc.getElementsByTagName(aShareType);
            if (nodeList.getLength() != 1) {
                return null;
            }
            Node shareNode = nodeList.item(0);
            NodeList children = shareNode.getChildNodes();
            if (children.getLength() != 2) {
                return null;
            }
            String businessSymbol = "";
            float unitPrice = 0.0F;
            
            List<Node> nodes = new ArrayList<Node>();
            nodes.add(children.item(0));
            nodes.add(children.item(1));
            
            for (Node node : nodes) {
                if (node.getLocalName().equals("unitPrice")) {
                    Float.parseFloat(node.getTextContent());
                }
                else if (node.getLocalName().equals("businessSymbol")) {
                    businessSymbol = node.getTextContent();
                }
            }
            
            if (businessSymbol.isEmpty()) return null;
            
            return new Share(businessSymbol, aShareType, unitPrice);
        }
        return null;
    }
    
    /**
     * Receive payment for an existing order
     * 
     * @param orderNum
     * @param totalPrice
     * @return 
     */
    public boolean receivePayment(String orderNum, float totalPrice) throws TransformerException {
        Document issuesDoc = xmlUtils.getXMLDocumentFromFile(this.stockIssues);
        Element order = issuesDoc.getElementById(orderNum);
        
        if (order == null) return false;
        
        boolean isPaid = Boolean.parseBoolean(order.getAttribute("paid"));
        
        if (isPaid == true) return false;
        
        float unitPrice = Float.parseFloat(order.getAttribute("unitPrice"));
        int quantity = Integer.parseInt(order.getAttribute("quantity"));
        float total = quantity * unitPrice;
        
        if (Math.abs(totalPrice - total) > 0.0000001F) return false;
         
        order.setAttribute("paid", "true");
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(this.stockIssues);
        Source input = new DOMSource(issuesDoc);

        transformer.transform(input, output);
        
        return false;
    }
    
    /**
     * Authorizes a number of shares to be created
     * 
     * @param shareType
     * @param quantity
     * @return 
     */
    private boolean authorizeShare(String shareType, int quantity) {
        if (quantity > 100 ||
            (shareType != "common" &&
             shareType != "preferred" &&
             shareType != "convertible")
        ) {
            return false;
        }
        return true;
    }
}
    
