/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.Connect.Conn;
import Model.Staff;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 84896
 */
public class StaffDAO implements DAOInterface<Staff> {

    @Override
    public ArrayList<Staff> getAll() {
        ArrayList<Staff> list = new ArrayList<Staff>();
            Statement stm = null;
            ResultSet rs = null;
        try{
        Connection c = Connect.Conn();
        String sql = "select * from Staff";
        stm = c.createStatement();
        rs = stm.executeQuery(sql);
        list.clear();
        while(rs.next()){
            String id = rs.getString("Staff_id");
            String name = rs.getString("Staff_name");
            String nation = rs.getString("Nation");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String socialmedia = rs.getString("Staff_Media");   
            Staff st = new Staff(id, name, nation, phone, email, socialmedia);
            list.add(st);
        }
        }catch(Exception e) {
        System.out.println("Cannot connect" + e);
        }
        return list;
    }

    @Override
    public int insert(Staff o) {
          PreparedStatement stmt = null;
          int result = 0;//result == 1 => thanh cong // result == 0 => insert that bai
        try{
        Connection conn = Connect.Conn(); //Ket noi
        stmt = conn.prepareStatement("INSERT INTO Staff values(?, ?, ?, ?, ?, ?)"); //chuan bi cau lenh sql
        stmt.setString(1,o.getId());
        stmt.setString(2,o.getName());
        stmt.setString(3,o.getNation());
        stmt.setString(4,o.getPhone());
        stmt.setString(5,o.getEmail());
        stmt.setString(6,o.getSocialmedia());
        result = stmt.executeUpdate(); //thuc thi cau lenh luu ket qua vao result
        }catch(Exception e){
            System.out.println("Cannot connect" + e);
        }
       return result; 
    }

    @Override
    public int delete(String id) {
        PreparedStatement stmt = null;
        int result = 0;//result == 1 => thanh cong // result == 0 => that bai
        try{
            Connection conn = Conn();
            stmt = conn.prepareStatement("DELETE FROM Staff where staff_id = ?");
            stmt.setString(1,id);
            result = stmt.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return result;
    }
    @Override
    public int update(Staff o) {
        PreparedStatement st = null;
        int result = 0; //result == 1 => thanh cong // result == 0 =>  that bai
        try{
            Connection conn = Connect.Conn();
            st = conn.prepareStatement("UPDATE Staff \n" +
                                        "Set Staff_name = ?,\n" +
                                        "    Nation = ?,\n" +
                                        "    Phone = ?,\n" +
                                        "    Email = ?,\n" +
                                        "    Staff_Media = ?\n" +
                                        "Where Staff_id = ?");
            st.setString(1, o.getName());
            st.setString(2, o.getNation());
            st.setString(3, o.getPhone());
            st.setString(4, o.getEmail());
            st.setString(5, o.getSocialmedia());
            st.setString(6, o.getId());
            result = st.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return result;
    }

    @Override
    public ArrayList<Staff> getbyID(String ID) {
        PreparedStatement stmt = null;
        ArrayList<Staff> list = new ArrayList();
        try{
            Connection conn = Connect.Conn();
            stmt = conn.prepareStatement("SELECT * FROM Staff where staff_id = ?");
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            String id = rs.getString("Staff_id");
            String name = rs.getString("Staff_name");
            String nation = rs.getString("Nation");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            String socialmedia = rs.getString("Staff_Media");   
            Staff st = new Staff(id, name, nation, phone, email, socialmedia);
            list.add(st);
            }
            
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return list;
    }

    @Override
    public ArrayList<Staff> search(String condition, String things) {
        ArrayList<Staff> list = new ArrayList();
        Statement stmt =  null;
        try{
            Connection conn = Connect.Conn();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Staff where " + condition +" LIKE '%"+ things +"%'");
            while(rs.next()){
               Staff st = new Staff(
               rs.getString("Staff_id"),
               rs.getString("Staff_name"),
               rs.getString("Nation"),
               rs.getString("phone"),
               rs.getString("email"),
               rs.getString("Staff_Media")
               );
               list.add(st);
            }
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return list;
    }
    
}
