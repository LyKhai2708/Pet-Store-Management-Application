
package DAO;

import static DAO.Connect.Conn;
import Model.Pet;
import java.util.ArrayList;
import java.sql.*;

public class PetDAO implements DAOInterface<Pet> {

    @Override
    public ArrayList<Pet> getAll() {
        ArrayList<Pet> list = new ArrayList<Pet>();
            Statement stm = null;
            ResultSet rs = null;
        try{
        Connection c = Conn();
        String sql = "select * from Pet";
        stm = c.createStatement();
        rs = stm.executeQuery(sql);
        list.clear();
        while(rs.next()){
            String ID = rs.getString("Pet_ID");
            String Pet_Type = rs.getString("Pet_Type");
            String Pet_Breed = rs.getString("Pet_Breed");
            String Pet_Name = rs.getString("Pet_Name");
            String  Color = rs.getString("Color");
            int Age = rs.getInt("Age");
            float Weight = rs.getFloat("Weight");
            String Picture = rs.getString("Picture");
            String Gender = rs.getString("Gender");
            String Shots = rs.getString("Shots");
            String Flush = rs.getString("Flush");
            String Heath = rs.getString("Health");
            float Price = rs.getFloat("Price");
            Pet p = new Pet(ID, Pet_Type,Pet_Breed, Pet_Name, Color, Age , Weight, Picture, Gender, Shots, Flush, Heath, Price);
            list.add(p);
        }
        }catch(Exception e) {
        System.out.println("Cannot connect" + e);
        }
        return list;
    }

    @Override
    public ArrayList<Pet> getbyID(String ID) {
        PreparedStatement stmt = null;
        ArrayList<Pet> list = new ArrayList();
        try{
            Connection conn = Conn();
            stmt = conn.prepareStatement("SELECT * FROM Pet where Pet_ID = ?");
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            String Pet_ID = rs.getString("Pet_ID");
            String Pet_Type = rs.getString("Pet_Type");
            String Pet_Breed = rs.getString("Pet_Breed");
            String Pet_Name = rs.getString("Pet_Name");
            String  Color = rs.getString("Color");
            int Age = rs.getInt("Age");
            float Weight = rs.getFloat("Weight");
            String Picture = rs.getString("Picture");
            String Gender = rs.getString("Gender");
            String Shots = rs.getString("Shots");
            String Flush = rs.getString("Flush");
            String Heath = rs.getString("Health");
            float Price = rs.getFloat("Price");
            Pet p = new Pet(Pet_ID, Pet_Type,Pet_Breed,Pet_Name, Color, Age , Weight, Picture, Gender, Shots, Flush, Heath, Price);
            list.add(p);
            }
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return list;
    }

    @Override
    public int insert(Pet o) {
       PreparedStatement stmt = null;
          int result = 0;//result == 1 => thanh cong // result == 0 => insert that bai
        try{
        Connection conn = Conn(); //Ket noi
        stmt = conn.prepareStatement("INSERT INTO Pet values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); //chuan bi cau lenh sql
        stmt.setString(1, o.getPet_ID());
        stmt.setString(2, o.getPet_Type());
        stmt.setString(3, o.getPet_Breed());
        stmt.setString(4, o.getPet_Name());
        stmt.setString(5,o.getColor());
        stmt.setInt(6, o.getAge());
        stmt.setFloat(7, o.getWeight());
        stmt.setString(8, o.getPicture());
        stmt.setString(9,o.getGender());
        stmt.setString(10, o.getShots());
        stmt.setString(11, o.getFlush());
        stmt.setString(12, o.getHeath());
        stmt.setFloat(13,o.getPrice());
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
            stmt = conn.prepareStatement("DELETE FROM Pet where Pet_ID = ?");
            stmt.setString(1,id);
            result = stmt.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot connect: "+ e);
        }
        return result;
    }

    @Override
    public int update(Pet o) {
        PreparedStatement st = null;
        int result = 0; //result == 1 => thanh cong // result == 0 =>  that bai
        try{
            Connection conn = Conn();
            st = conn.prepareStatement("UPDATE Pet \n" +
                                        "Set Pet_Type = ?,\n" +
                                        "    Pet_Breed = ?,\n" +
                                        "    Pet_Name = ?,\n" +
                                        "    Color = ?,\n" +
                                        "    Age = ?,\n" +
                                        "    Weight = ?,\n" +
                                        "    Picture = ?,\n" +
                                        "    Gender = ?,\n" +
                                        "    Shots = ?,\n" +
                                        "    Flush = ?,\n" +
                                        "    Health= ?,\n" +
                                        "    Price = ?\n" +
                                        "Where Pet_ID = ?");
            st.setString(1, o.getPet_Type());
            st.setString(2, o.getPet_Breed());
            st.setString(3, o.getPet_Name());
            st.setString(4, o.getColor());
            st.setInt(5, o.getAge());
            st.setFloat(6, o.getWeight());
            st.setString(7, o.getPicture());
            st.setString(8, o.getGender()); 
            st.setString(9, o.getShots());
            st.setString(10, o.getFlush());
            st.setString(11, o.getHeath());
            st.setFloat(12, o.getPrice());
            st.setString(13, o.getPet_ID());
            result = st.executeUpdate();
        }catch(Exception e){
            System.out.println("Cannot Connect" + e);
        }
        return result;
    }

    @Override
    public ArrayList<Pet> search(String Condition, String things) {
        ArrayList<Pet> list = new ArrayList<Pet>();
            Statement stm = null;
        try{
        Connection c = Conn();
        stm = c.createStatement();
        ResultSet rs = stm.executeQuery("Select * from Pet where " + Condition +" LIKE '%"+ things +"%'");
        list.clear();
        while(rs.next()){
            String ID = rs.getString("Pet_ID");
            String Pet_Type = rs.getString("Pet_Type");
            String Pet_Breed = rs.getString("Pet_Breed");
            String Pet_Name = rs.getString("Pet_Name");
            String  Color = rs.getString("Color");
            int Age = rs.getInt("Age");
            float Weight = rs.getFloat("Weight");
            String Picture = rs.getString("Picture");
            String Gender = rs.getString("Gender");
            String Shots = rs.getString("Shots");
            String Flush = rs.getString("Flush");
            String Heath = rs.getString("Health");
            float Price = rs.getFloat("Price");
            Pet p = new Pet(ID, Pet_Type,Pet_Breed, Pet_Name, Color, Age , Weight, Picture, Gender, Shots, Flush, Heath, Price);
            list.add(p);
        }
        }catch(Exception e) {
        System.out.println("Cannot connect" + e);
        }
        return list;
    }
}
