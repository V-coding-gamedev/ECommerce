/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.*; 

public class ShoppingCartDAO {
    public ArrayList<ShoppingCart> getCart(){
        ArrayList<ShoppingCart> cart = new ArrayList<>(); 
        
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            
            String sqlQuery = "select * from Shopping_Cart"; 
            PreparedStatement stm = con.prepareStatement(sqlQuery);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()){
                cart.add(new ShoppingCart(rs.getInt(1), rs.getInt(2),
                                          rs.getInt(3), rs.getInt(4))); 
                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return cart;
    }
    
    public int addToCart(int user_id, int product_id, int quantity){
        try {
            DBContext db = new DBContext(); 
            Connection con = db.getConnection(); 
            
            String sqlQuery = "insert into Shopping_Cart values (?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sqlQuery);
            
            stm.setInt(1, user_id);
            stm.setInt(2, product_id);
            stm.setInt(3, quantity);
            
            int rowsInserted = stm.executeUpdate(); 
            
            if (rowsInserted > 0){
                return rowsInserted; 
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return 0; 
    }
    
    public static void main(String[] args) {
        ShoppingCartDAO shoppingCartDao = new ShoppingCartDAO();

        int result = shoppingCartDao.addToCart(10, 10, 10); 
        System.out.println("Result: " + result + "\n");
        
        ArrayList<ShoppingCart> cart = shoppingCartDao.getCart();

        for (ShoppingCart shoppingCart : cart) {
            System.out.println(shoppingCart.getCard_id() + " - " + shoppingCart.getProduct_id() + " - " + shoppingCart.getQuantity());
        }
    }
}
