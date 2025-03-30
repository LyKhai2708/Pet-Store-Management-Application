/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import static DAO.Connect.Conn;
import Model.Customers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author 84896
 */
public class CustomersDAO implements DAOInterface<Customers> {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    @Override
    public ArrayList<Customers> getAll() {
        ArrayList<Customers> list = new ArrayList();
            Statement stm = null;
            ResultSet rs = null;
        try{
        Connection c = Conn();
        String sql = "select * from Customer";
        stm = c.createStatement();
        rs = stm.executeQuery(sql);
        list.clear();
        while(rs.next()){
            String CCCD = rs.getString("CustomerCCCD");
            String name = rs.getString("Customername");
            Date birth = rs.getDate("DateOfBirth");
            String phone = rs.getString("Cus_Phone");
            String email = rs.getString("Email");
            String Address = rs.getString("Address");
            String socialmedia = rs.getString("SocialMedia");
            String Gender = rs.getString("Gender");
            String Nation = rs.getString("Nation");
            Customers cus = new Customers(CCCD, name, phone, email,Address, socialmedia,Gender, Nation,birth);
            list.add(cus);
        }
        }catch(Exception e) {
        System.out.println("Cannot connect" + e);
        }
        return list;
    }

    @Override
    public ArrayList<Customers> getbyID(String ID) {
        PreparedStatement stmt = null;
        ArrayList<Customers> list = new ArrayList();
        try{
            Connection conn = Connect.Conn();
            stmt = conn.prepareStatement("SELECT * FROM Customer where CustomerCCCD = ?");
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            String CCCD = rs.getString("CustomerCCCD");
            String name = rs.getString("CustomerName");
            String phone = rs.getString("Cus_Phone");
            String email = rs.getString("Email");
            String Address = rs.getString("Address");
            String socialmedia = rs.getString("SocialMedia");   
            String Gender = rs.getString("Gender");
            String nation = rs.getString("Nation");
            Date birth = rs.getDate("DateOfBirth");
            Customers cus = new Customers(CCCD, name, phone, email,Address, socialmedia,Gender, nation,birth);
            list.add(cus);
            }
            
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return list;
    }
    @Override
    public int delete(String id) {
        PreparedStatement stmt = null;
        int result = 0;//result == 1 => thanh cong // result == 0 => that bai
        try{
            Connection conn = Conn();
            stmt = conn.prepareStatement("DELETE FROM Customer where CustomersCCCD = ?");
            stmt.setString(1,id);
            result = stmt.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return result;
    }

    @Override
    public ArrayList<Customers> search(String Condition, String things) {
        ArrayList<Customers> list = new ArrayList();
        Statement st = null;
        try{
        Connection conn = Conn();
        st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select * from Customer where " + Condition +" LIKE '%"+ things +"%'");
        while(rs.next()) {
            String CCCD = rs.getString("CustomerCCCD");
            String name = rs.getString("Customername");
            Date birth = rs.getDate("DateOfBirth");
            String phone = rs.getString("Cus_Phone");
            String email = rs.getString("Email");
            String Address = rs.getString("Address");
            String socialmedia = rs.getString("SocialMedia");
            String Gender = rs.getString("Gender");
            String Nation = rs.getString("Nation");
            Customers cus = new Customers(CCCD, name, phone, email,Address, socialmedia,Gender, Nation,birth);
            list.add(cus);
        }    
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return list;
    }

    @Override
    public int insert(Customers o) {
        PreparedStatement stmt = null;
          int result = 0;//result == 1 => thanh cong // result == 0 => insert that bai
        try{
        Connection conn = Conn(); //Ket noi
        stmt = conn.prepareStatement("INSERT INTO Customer values(?, ?, ?, ?, ?, ?, ?, ?, ?)"); //chuan bi cau lenh sql
        stmt.setString(1,o.getCustomerCCCD());
        stmt.setString(2,o.getCustomerName());
        stmt.setDate(3,new java.sql.Date(o.getDateOfBirth().getTime()));
        stmt.setString(4,o.getCus_Phone());
        stmt.setString(5,o.getEmail());
        stmt.setString(6,o.getAddress());
        stmt.setString(7,o.getSocialMedia());
        stmt.setString(8, o.getGender());
        stmt.setString(9, o.getNation());
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
    public int update(Customers o) {
        PreparedStatement st = null;
        int result = 0; //result == 1 => thanh cong // result == 0 =>  that bai
        try{
            Connection conn = Connect.Conn();
            st = conn.prepareStatement("UPDATE Customer \n" +
                                        "Set CustomerName = ?,\n" +
                                        "    Cus_Phone = ?,\n" +
                                        "    Email = ?,\n" +
                                        "    Address = ?,\n" +
                                        "    SocialMedia = ?,\n" +
                                        "    Gender = ?,\n" +
                                        "    Nation = ?,\n" +
                                        "    DateOfBirth = ?\n" +
                                        "Where CustomerCCCD = ?");
            st.setString(1, o.getCustomerName());
            st.setString(2, o.getCus_Phone());
            st.setString(3, o.getEmail());
            st.setString(4, o.getAddress());
            st.setString(5, o.getSocialMedia());
            st.setString(6, o.getGender());
            st.setString(7, o.getNation());
            st.setDate(8, new  java.sql.Date(o.getDateOfBirth().getTime()));
            st.setString(9, o.getCustomerCCCD());
            result = st.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return result;
    }
    
}
