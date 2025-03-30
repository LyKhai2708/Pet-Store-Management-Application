/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import java.util.ArrayList;
public interface DAOInterface<T> {
    public ArrayList<T> getAll();
    public ArrayList<T> getbyID(String ID);
    public int insert (T o);
    public int delete (String id);
    public int update (T o);
    public ArrayList<T> search(String Condition,String things);
}
