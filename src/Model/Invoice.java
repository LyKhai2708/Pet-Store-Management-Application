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
public class Invoice {

    private String Invoice_ID, CustomerCCCD ,StaffID;
    private Date SellDay;
    private float Total_Price;

    public Invoice() {
    }

    public Invoice(String InvoiceID,String StaffID, String CustomerCCCD, Date SellDay,  float Total_Price) {
        this.Invoice_ID = InvoiceID;
        this.CustomerCCCD = CustomerCCCD;
        this.StaffID = StaffID;
        this.SellDay = SellDay;
        this.Total_Price = Total_Price;
    }

    public String getInvoiceID() {
        return Invoice_ID;
    }

    public String getCustomerCCCD() {
        return CustomerCCCD;
    }

    public Date getSellDay() {
        return SellDay;
    }

    public float getTotal_Price() {
        return Total_Price;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }
    

    public void setInvoiceID(String InvoiceID) {
        this.Invoice_ID = InvoiceID;
    }

    public void setCustomerCCCD(String CustomerCCCD) {
        this.CustomerCCCD = CustomerCCCD;
    }

    public void setSellDay(Date SellDay) {
        this.SellDay = SellDay;
    }

    public void setTotal_Price(float Total_Price) {
        this.Total_Price = Total_Price;
    }

    
}

