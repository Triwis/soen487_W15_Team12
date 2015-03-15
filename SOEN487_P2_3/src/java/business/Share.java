package business;

/**
 *
 * @author ckahn
 */
public class Share {
    final public String businessSymbol;
    final public String shareType;
    final public float unitPrice;
    
    // some boilerplate
    
    public Share(String businessSymbol, String shareType, float unitPrice) {
        this.businessSymbol = businessSymbol;
        this.shareType = shareType;
        this.unitPrice = unitPrice;
    }
}
