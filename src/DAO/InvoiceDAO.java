/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.Connect.Conn;
import Model.Invoice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author 84896
 */
public class InvoiceDAO  implements DAOInterface<Invoice>{

 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    @Override
    public ArrayList<Invoice> getAll() {
       ArrayList<Invoice> list = new ArrayList();
            Statement stm = null;
            ResultSet rs = null;
        try{
        Connection c = Conn();
        String sql = "select * from Invoice";
        stm = c.createStatement();
        rs = stm.executeQuery(sql);
        list.clear();
        while(rs.next()){
            String Invoice_ID = rs.getString("Invoice_ID");
            String Staff_ID = rs.getString("Staff_ID");
            String CustomerCCCD = rs.getString("CustomerCCCD");
            Date SellDay = rs.getDate("SellDay");
            Float Total_Price = rs.getFloat("Total_Price");
            Invoice i = new Invoice(Invoice_ID,Staff_ID, CustomerCCCD, SellDay, Total_Price );
            list.add(i);
        }
        }catch(Exception e) {
        System.out.println("Cannot connect" + e);
        }
        return list;
    }

    @Override
    public ArrayList<Invoice> getbyID(String ID) {
        PreparedStatement stmt = null;
        ArrayList<Invoice> list = new ArrayList();
        try{
            Connection conn = Connect.Conn();
            stmt = conn.prepareStatement("SELECT * FROM Invoice where Invoice_ID = ?");
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            String Invoice_ID = rs.getString("Invoice_ID");
            String CustomerCCCD = rs.getString("CustomerCCCD");
            String Staff_ID = rs.getString("Staff_ID");
            Date SellDay = rs.getDate("SellDay");
            Float Total_Price = rs.getFloat("Total_Price");
            Invoice i = new Invoice(Invoice_ID,Staff_ID, CustomerCCCD, SellDay, Total_Price);
            list.add(i);
            }
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return list; 
    }

    @Override
    public int insert(Invoice o) {
        PreparedStatement stmt = null;
          int result = 0;//result == 1 => thanh cong // result == 0 => insert that bai
        try{
        Connection conn = Conn(); //Ket noi
        stmt = conn.prepareStatement("INSERT INTO Invoice values(?, ?, ?,?, ?)"); //chuan bi cau lenh sql
        stmt.setString(1,o.getInvoiceID());
        stmt.setString(2, o.getStaffID());
        stmt.setString(3,o.getCustomerCCCD());
        stmt.setDate(4,new java.sql.Date(o.getSellDay().getTime()));
        stmt.setFloat(5, o.getTotal_Price());
        result = stmt.executeUpdate(); //thuc thi cau lenh luu ket qua vao result
        }catch(Exception e){
            System.out.println("Cannot connect" + e);
        }finally{
            if (stmt != null) {
                try {
                stmt.close();
                   }catch (SQLException sqlEx) { } //exception handling code
                stmt = null;
        }
        }
       return result;
    }

    @Override
    public int delete(String id) {
        PreparedStatement stmt = null;
        int result = 0;//result == 1 => thanh cong // result == 0 => that bai
        try{
            Connection conn = Conn();
            stmt = conn.prepareStatement("DELETE FROM Invoice where Invoice_ID = ?");
            stmt.setString(1,id);
            result = stmt.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return result;
    }    

    @Override
    public int update(Invoice o) {
        PreparedStatement st = null;
        int result = 0; //result == 1 => thanh cong // result == 0 =>  that bai
        try{
            Connection conn = Connect.Conn();
            st = conn.prepareStatement("UPDATE Invoice \n" +
                                        "Set Staff_ID = ?,\n" +
                                        "    CustomerCCCD = ?,\n" +
                                        "    SellDay = ?,\n" +
                                        "    Total_Price= ?\n" +
                                        "where Invoice_ID = ?");
            st.setString(1,o.getStaffID());
            st.setString(2, o.getCustomerCCCD());
            st.setDate(3, new  java.sql.Date(o.getSellDay().getTime()));
            st.setFloat(4, o.getTotal_Price());
            st.setString(5, o.getInvoiceID());
            result = st.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return result;   
 }

    @Override
    public ArrayList<Invoice> search(String Condition, String things) {
        ArrayList<Invoice> list = new ArrayList();
        Statement st = null;
        try{
        Connection conn = Conn();
        st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select * from Invoice where " + Condition +" LIKE '%"+ things +"%'");
        while(rs.next()) {
            String Invoice_ID = rs.getString("Invoice_ID");
            String CustomerCCCD = rs.getString("CustomerCCCD");
            String Staff_ID = rs.getString("Staff_ID");
            Date SellDay = rs.getDate("SellDay");
            Float Total_Price = rs.getFloat("Total_Price");
            Invoice i = new Invoice(Invoice_ID,Staff_ID, CustomerCCCD, SellDay, Total_Price);
            list.add(i);
        }    
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return list;
    }
    public ArrayList<Invoice> findbyDate(Date Start, Date Finish){
        PreparedStatement stmt = null;
        ArrayList<Invoice> list = new ArrayList();
        try{
            Connection conn = Connect.Conn();
            stmt = conn.prepareStatement("SELECT * FROM Invoice where SellDay BETWEEN ? and ?");
            stmt.setDate(1, new java.sql.Date(Start.getTime()));
            stmt.setDate(2, new java.sql.Date(Finish.getTime()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            String Invoice_ID = rs.getString("Invoice_ID");
            String CustomerCCCD = rs.getString("CustomerCCCD");
            String Staff_ID = rs.getString("Staff_ID");
            Date SellDay = rs.getDate("SellDay");
            Float Total_Price = rs.getFloat("Total_Price");
            Invoice i = new Invoice(Invoice_ID,Staff_ID, CustomerCCCD, SellDay, Total_Price);
            list.add(i);
            }
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return list;
    }
}
