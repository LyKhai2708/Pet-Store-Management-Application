/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author 84896
 */
public class Detail_Invoice {
    private String Invoice_ID, Product_ID, Pet_ID;
    private int Amount;
    private float Price;

    public Detail_Invoice() {
    }

    public Detail_Invoice(String Invoice_ID, String Product_ID, String Pet_ID, int Amount, float Price) {
        this.Invoice_ID = Invoice_ID;
        this.Product_ID = Product_ID;
        this.Pet_ID = Pet_ID;
        this.Amount = Amount;
        this.Price = Price;
    }

    public String getInvoice_ID() {
        return Invoice_ID;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public String getPet_ID() {
        return Pet_ID;
    }

    public int getAmount() {
        return Amount;
    }

    public float getPrice() {
        return Price;
    }

    public void setInvoice_ID(String Invoice_ID) {
        this.Invoice_ID = Invoice_ID;
    }

    public void setProduct_ID(String Product_ID) {
        this.Product_ID = Product_ID;
    }

    public void setPet_ID(String Pet_ID) {
        this.Pet_ID = Pet_ID;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }
    
}
