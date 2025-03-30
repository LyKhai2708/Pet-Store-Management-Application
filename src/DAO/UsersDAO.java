/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.Connect.Conn;
import java.util.ArrayList;
import java.sql.*;
import Model.Users;
/**
 *
 * @author 84896
 */
public class UsersDAO implements DAOInterface<Users> {

    @Override
    public ArrayList<Users> getAll() {
       Statement st = null;
       ResultSet rs = null;
       ArrayList<Users> list = new ArrayList();
        try{
            Connection conn = Connect.Conn();
            st = conn.createStatement();
            rs = st.executeQuery("select * from Staff_Login");
            while(rs.next()){
               Users u = new Users(rs.getString("Staff_id"),
                                   rs.getString("username"),
                                   rs.getString("pass"),
                                   rs.getInt("role_id"));
               list.add(u);
            }
        }catch(Exception e){
            System.out.println("Cannot connect:" + e);
        }
        return list;
    }

    @Override
    public ArrayList<Users> getbyID(String ID) {
        PreparedStatement stmt = null;
        ArrayList<Users> list = new ArrayList();
        try{
            Connection conn = Conn();
            stmt = conn.prepareStatement("SELECT * FROM Staff_Login where Staff_id = ?");
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            Users u = new Users(rs.getString("Staff_id"),
                                   rs.getString("username"),
                                   rs.getString("pass"),
                                   rs.getInt("role_id"));
            list.add(u);
            }
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return list;
    }

    @Override
    public int insert(Users o) {
        PreparedStatement st = null;
        int result = 0;
        try{
            Connection conn = Connect.Conn();
            String sql = "insert into Staff_Login values (?,?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1,o.getId());
            st.setString(2,o.getUsername());
            st.setString(3, o.getPassword());
            st.setInt(4, o.getRole_id());
            result = st.executeUpdate();
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
            Connection conn = Connect.Conn();
            stmt = conn.prepareStatement("DELETE FROM Staff_Login where staff_id = ?");
            stmt.setString(1,id);
            result = stmt.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return result;
    }

    @Override
    public int update(Users o) {
        PreparedStatement st = null;
        int result = 0; //result == 1 => thanh cong // result == 0 =>  that bai
        try{
            Connection conn = Connect.Conn();
            st = conn.prepareStatement("UPDATE Staff_Login \n" +
                                        "Set username = ?,\n" +
                                        "    pass = ?\n" +
                                        "Where Staff_id = ?");
            st.setString(1, o.getUsername());
            st.setString(2, o.getPassword());
            st.setString(3, o.getId());
            result = st.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return result;
    }

    @Override
    public ArrayList<Users> search(String condition, String things) {
        ArrayList<Users> list = new ArrayList();
        Statement stmt =  null;
        try{
            Connection conn = Connect.Conn();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Staff_Login where " + condition +" LIKE '%"+ things +"%'");
            while(rs.next()){
               Users u = new Users(
               rs.getString("Staff_id"),
               rs.getString("username"),
               rs.getString("pass"),
               rs.getInt("role_id")
               );
               list.add(u);
            }
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return list;
    }
    public Users Login(String username, String password){
        PreparedStatement st = null;
        Users user = null;
        try{
            Connection conn = Connect.Conn();
            String sql = "select * from Staff_Login where username = ? and pass = ?";
            st = conn.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                user = new Users(rs.getString("Staff_id"),
                                 rs.getString("username"),
                                 rs.getString("pass"),
                                 rs.getInt("role_id"));
            }
        }catch(Exception e){
            System.out.println("Cannot connect" + e);
        }
        return user;
    }
}
