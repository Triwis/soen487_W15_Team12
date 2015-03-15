package business;

/**
 *
 * @author ckahn
 */
public class ShareOrder {
    final String orderNum;
    final String brokerRef;
    final Share share;
    final int quantity;
    final float unitPrice;
    
    public ShareOrder(String orderNum, String brokerRef, Share share, int quantity, float unitPrice) {
        this.orderNum = orderNum;
        this.brokerRef = brokerRef;
        this.share = share;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
