/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author ckahn
 */
public class SharePurchase {
    final String orderNum;
    final String brokerRef;
    final String shareType;
    final int quantity;
    final float unitPrice;
    
    public SharePurchase(String orderNum, String brokerRef, String shareType, int quantity, float unitPrice) {
        this.orderNum = orderNum;
        this.brokerRef = brokerRef;
        this.shareType = shareType;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
