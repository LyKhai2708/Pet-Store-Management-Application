/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
public class Connect {
    public static Connection Conn() {
        Connection c = null;
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms?" + "user=root&password=1234");
        System.out.println("Connected");
        }catch (Exception e) {
            System.out.println("Cannot connect" + e);
        }
        return c;
    }
}
