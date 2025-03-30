/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.Connect.Conn;
import Model.PetProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author 84896
 */
public class PetProductDAO implements DAOInterface<PetProduct> {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    @Override
    public ArrayList<PetProduct> getAll() {
       ArrayList<PetProduct> list = new ArrayList();
            Statement stm = null;
            ResultSet rs = null;
        try{
        Connection c = Conn();
        String sql = "select * from PetProduct";
        stm = c.createStatement();
        rs = stm.executeQuery(sql);
        list.clear();
        while(rs.next()){
            PetProduct pp = new PetProduct();
            pp.setProduct_ID(rs.getString("Product_ID"));
            pp.setProduct_Name(rs.getString("Product_Name"));
            pp.setPrice_issue(rs.getBigDecimal("Price_issue").floatValue());
            pp.setPrice_sell(rs.getBigDecimal("Price_sell").floatValue());
            pp.setStock(rs.getInt("Stock"));
            pp.setReceipt_Day(rs.getDate("Receipt_day"));
            list.add(pp);
        }
        }catch(Exception e) {
        System.out.println("Cannot connect" + e);
        }
        return list;
    }

    @Override
    public ArrayList<PetProduct> getbyID(String ID) {
       PreparedStatement stmt = null;
        ArrayList<PetProduct> list = new ArrayList();
        try{
            Connection conn = Connect.Conn();
            stmt = conn.prepareStatement("SELECT * FROM PetProduct where Product_ID = ?");
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            PetProduct pp = new PetProduct();
            pp.setProduct_ID(rs.getString("Product_ID"));
            pp.setProduct_Name(rs.getString("Product_Name"));
            pp.setPrice_issue(rs.getBigDecimal("Price_issue").floatValue());
            pp.setPrice_sell(rs.getBigDecimal("Price_sell").floatValue());
            pp.setStock(rs.getInt("Stock"));
            pp.setReceipt_Day(rs.getDate("Receipt_day"));
            list.add(pp);
            }
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return list;
    }

    @Override
    public int insert(PetProduct o) {
        PreparedStatement stmt = null;
          int result = 0;//result == 1 => thanh cong // result == 0 => insert that bai
        try{
        Connection conn = Conn(); //Ket noi
        stmt = conn.prepareStatement("INSERT INTO PetProduct values(?, ?, ?, ?, ?, ?)"); //chuan bi cau lenh sql
        stmt.setString(1,o.getProduct_ID());
        stmt.setString(2,o.getProduct_Name());
        stmt.setFloat(3,o.getPrice_issue());
        stmt.setFloat(4,o.getPrice_sell());
        stmt.setInt(5,o.getStock());
        stmt.setDate(6,new java.sql.Date(o.getReceipt_Day().getTime()));
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
            stmt = conn.prepareStatement("DELETE FROM PetProduct where Product_ID = ?");
            stmt.setString(1, id);
            result = stmt.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return result;
    }

    @Override
    public int update(PetProduct o) {
        PreparedStatement st = null;
        int result = 0; //result == 1 => thanh cong // result == 0 =>  that bai
        try{
            Connection conn = Connect.Conn();
            st = conn.prepareStatement("UPDATE PetProduct \n" +
                                        "Set Product_Name = ?,\n" +
                                        "    Price_issue = ?,\n" +
                                        "    Price_sell = ?,\n" +
                                        "    Stock= ?,\n" +
                                        "    Receipt_Day = ?\n" +
                                        "Where Product_ID = ?");
            st.setString(1, o.getProduct_Name());
            st.setFloat(2, o.getPrice_issue());
            st.setFloat(3, o.getPrice_sell());
            st.setInt(4, o.getStock());
            st.setDate(5, new java.sql.Date(o.getReceipt_Day().getTime()));
            st.setString(6, o.getProduct_ID());

            result = st.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return result;
    }

    @Override
    public ArrayList<PetProduct> search(String Condition, String things) {
        ArrayList<PetProduct> list = new ArrayList();
        Statement st = null;
        try{
        Connection conn = Conn();
        st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select * from PetProduct where " + Condition +" LIKE '%"+ things +"%'");
        while(rs.next()) {
            PetProduct pp = new PetProduct();
            pp.setProduct_ID(rs.getString("Product_ID"));
            pp.setProduct_Name(rs.getString("Product_Name"));
            pp.setPrice_issue(rs.getFloat("Price_issue"));
            pp.setPrice_sell(rs.getBigDecimal("Price_sell").floatValue());
            pp.setStock(rs.getInt("Stock"));
            pp.setReceipt_Day(rs.getDate("Receipt_day"));
            list.add(pp);    
        }    
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return list;
    }
    
}
