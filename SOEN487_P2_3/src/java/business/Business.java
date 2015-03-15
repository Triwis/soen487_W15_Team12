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
    double price; 
    
    /**
     * Constructor
     * 
     * @param sharesPath
     * @param stockIssuesPath 
     */
    public Business(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
}
    