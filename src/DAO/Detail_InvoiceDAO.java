
package DAO;

import static DAO.Connect.Conn;
import Model.Detail_Invoice;
import Model.Invoice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
        
import java.util.ArrayList;
import java.util.Date;
public class Detail_InvoiceDAO implements DAOInterface<Detail_Invoice> {

    @Override
    public ArrayList<Detail_Invoice> getAll() {
        ArrayList<Detail_Invoice> list = new ArrayList();
            Statement stm = null;
            ResultSet rs = null;
        try{
        Connection c = Conn();
        String sql = "select * from Detail_Invoice";
        stm = c.createStatement();
        rs = stm.executeQuery(sql);
        list.clear();
        while(rs.next()){
            String Invoice_ID = rs.getString("Invoice_ID");
            String Product_ID = rs.getString("Product_ID");
            String Pet_ID = rs.getString("Pet_ID");
            int Amount = rs.getInt("Amount");
            Float Price = rs.getFloat("Price");
            Detail_Invoice di = new Detail_Invoice(Invoice_ID, Product_ID, Pet_ID, Amount, Price );
            list.add(di);
        }
        }catch(Exception e) {
        System.out.println("Cannot connect" + e);
        }
        return list;   
    }

    @Override
    public ArrayList<Detail_Invoice> getbyID(String ID) {
        PreparedStatement stmt = null;
        ArrayList<Detail_Invoice> list = new ArrayList();
        try{
            Connection conn = Connect.Conn();
            stmt = conn.prepareStatement("SELECT * FROM Detail_Invoice where Invoice_ID = ?");
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            String Invoice_ID = rs.getString("Invoice_ID");
            String Product_ID = rs.getString("Product_ID");
            String Pet_ID = rs.getString("Pet_ID");
            int Amount = rs.getInt("Amount");
            Float Price = rs.getFloat("Price");
            Detail_Invoice di = new Detail_Invoice(Invoice_ID, Product_ID, Pet_ID, Amount, Price);
            list.add(di);
            }
            
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return list;  
    }    

    @Override
    public int insert(Detail_Invoice o) {
    PreparedStatement stmt = null;
          int result = 0;//result == 1 => thanh cong // result == 0 => insert that bai
        try{
        Connection conn = Conn(); //Ket noi
        stmt = conn.prepareStatement("INSERT INTO Detail_Invoice values(?, ?, ?, ?, ?)"); //chuan bi cau lenh sql
        stmt.setString(1,o.getInvoice_ID());
        stmt.setString(2,o.getProduct_ID());
        stmt.setString(3, o.getPet_ID());
        stmt.setInt(4,o.getAmount());
        stmt.setFloat(5, o.getPrice());
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
            stmt = conn.prepareStatement("DELETE FROM Detail_Invoice where Invoice_ID = ?");
            stmt.setString(1,id);
            result = stmt.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return result;
    }

    @Override
    public int update(Detail_Invoice o) {
        PreparedStatement st = null;
        int result = 0; //result == 1 => thanh cong // result == 0 =>  that bai
        try{
            Connection conn = Connect.Conn();
            st = conn.prepareStatement("UPDATE Detail_Invoice \n" +
                                        "Set Product_ID = ?,\n" +
                                        "    Pet_ID = ?,\n" +
                                        "    Amount = ?,\n" +
                                        "    Price= ?\n" +
                                        "where Invoice_ID = ?");
            st.setString(1, o.getProduct_ID());
            st.setString(2, o.getPet_ID());
            st.setInt(3, o.getAmount());
            st.setFloat(4, o.getPrice());
            st.setString(5, o.getInvoice_ID());
            result = st.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return result;    }

    @Override
    public ArrayList<Detail_Invoice> search(String Condition, String things) {
        ArrayList<Detail_Invoice> list = new ArrayList();
        Statement st = null;
        try{
        Connection conn = Conn();
        st = conn.createStatement();
        ResultSet rs = st.executeQuery("Select * from Detail_Invoice where " + Condition +" LIKE '%"+ things +"%'");
        while(rs.next()) {
            String Invoice_ID = rs.getString("Invoice_ID");
            String Product_ID = rs.getString("Product_ID");
            String Pet_ID = rs.getString("Pet_ID");      
            int Amount = rs.getInt("Amount");
            Float Price = rs.getFloat("Price");
            Detail_Invoice di = new Detail_Invoice(Invoice_ID, Product_ID, Pet_ID, Amount, Price);
            list.add(di);
        }    
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return list;   
    }
    
}
