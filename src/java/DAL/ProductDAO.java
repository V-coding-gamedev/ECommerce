package DAL;

import Context.DBContext;
import Model.Product;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import DAL.ProductDAO; 

public class ProductDAO extends DBContext{
    
    public ArrayList<Product> getProduct(){
        ArrayList<Product> product = new ArrayList<>(); 
        
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            
            String sqlQuery = "select * from Products"; 
            PreparedStatement stm = con.prepareStatement(sqlQuery);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()){
                product.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), 
                                        rs.getDouble(4), rs.getInt(5), rs.getInt(6), 
                                        rs.getString(7), rs.getTimestamp(8).toLocalDateTime(), rs.getTimestamp(9).toLocalDateTime())); 
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
    
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO(); 
        ArrayList<Product> products = productDAO.getProduct();
        
        for (Product product : products) {
            System.out.println(product.getProduct_id()+ " - " + product.getName()); // Ensure the User class has a proper toString() method
        }
        
    }
}
