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
public class PetProduct {
    private String Product_ID;
    private String Product_Name;
    private float Price_issue,Price_sell;
    private int Stock;
    private Date Receipt_Day;

    public PetProduct() {
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public float getPrice_issue() {
        return Price_issue;
    }

    public float getPrice_sell() {
        return Price_sell;
    }

    public int getStock() {
        return Stock;
    }

    public Date getReceipt_Day() {
        return Receipt_Day;
    }

    public void setProduct_ID(String Product_ID) {
        this.Product_ID = Product_ID;
    }

    public void setProduct_Name(String Product_Name) {
        this.Product_Name = Product_Name;
    }

    public void setPrice_issue(float Price_issue) {
        this.Price_issue = Price_issue;
    }

    public void setPrice_sell(float Price_sell) {
        this.Price_sell = Price_sell;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public void setReceipt_Day(Date Receipt_Day) {
        this.Receipt_Day = Receipt_Day;
    }
    
}

    
