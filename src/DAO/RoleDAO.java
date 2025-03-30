/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Role;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author 84896
 */
public class RoleDAO implements DAOInterface<Role> {

    @Override
    public ArrayList<Role> getAll() {
        ArrayList list = new ArrayList();
        Statement st = null;
        ResultSet rs = null;
        try{
            Connect c = new Connect();
            Connection conn = c.Conn();
            String sql = "SELECT * FROM Role";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            list.clear();
            while(rs.next()){
                Role o = new Role(rs.getInt("ROLE_ID"), 
                                  rs.getString("ROLE_NAME"));
                list.add(o);
            }
        }catch(Exception e){
            System.out.println("Cannot connect "+ e);
        }
        return list;
    }


    @Override
    public int insert(Role o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Role o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public ArrayList<Role> search(String Condition, String things) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Role> getbyID(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
