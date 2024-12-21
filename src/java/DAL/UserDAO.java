package DAL;

import Context.DBContext;
import Model.User;
import java.lang.System.Logger;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends DBContext {

    public ArrayList<User> getUser() {
        ArrayList<User> user = new ArrayList<>();
        
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            
            String sqlQuery = "select * from Users";
            PreparedStatement stm = con.prepareStatement(sqlQuery);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                user.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    
    public User checkUserExist(String email){
        User user = new User(); 
        
        try {
            DBContext db = new DBContext(); 
            Connection con = db.getConnection(); 
            
            String sqlQuery = "select * from Users where email = '" + email + "'";
            PreparedStatement stm = con.prepareStatement(sqlQuery);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                user.setUser_id(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setFull_name(rs.getString(5));
                user.setPhone_number(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setRole(rs.getString(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return user; 
    }
    
    public int register (String username, String email, String password, String fullName, String phoneNumber, String address, String role){
        try {
            DBContext db = new DBContext(); 
            Connection con = db.getConnection(); 
            
            String sqlQuery = "insert into Users values (?, ?, ?, ?, ?, ?, ?)"; 
            PreparedStatement stm = con.prepareStatement(sqlQuery);
            
            stm.setString(1, username);
            stm.setString(2, email);
            stm.setString(3, password);
            stm.setString(4, fullName);
            stm.setString(5, phoneNumber);
            stm.setString(6, address);
            stm.setString(7, role); 
            
            int rowsInserted = stm.executeUpdate(); 
            
            if (rowsInserted > 0){
                return rowsInserted;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return 0; 
    }
    
    public String checkCorrespondingPassword(String email){
        String foundPassword = ""; 
        
        try {
            DBContext db = new DBContext(); 
            Connection con = db.getConnection(); 
            
            String sqlQuery = "select password from Users where email = '" + email + "'";
            PreparedStatement stm = con.prepareStatement(sqlQuery);
            ResultSet rs = stm.executeQuery();

            if (rs.next()){
                foundPassword = rs.getString("password"); 
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return foundPassword; 
    }
    
    public int resetPassword(String email, String newPassword){
        try {
            DBContext db = new DBContext(); 
            Connection con = db.getConnection(); 
            
            String sqlQuery = "UPDATE Users SET Password = ? WHERE Email = ?"; 
            PreparedStatement stm = con.prepareStatement(sqlQuery);
            
            stm.setString(1, newPassword);
            stm.setString(2, email);
            
            int rowsUpdated = stm.executeUpdate(); 
            
            if (rowsUpdated > 0){
                return rowsUpdated; 
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return 0; 
    }
    
    public int findUserId(String email){
        int userId = 0; 
        
        try {
            DBContext db = new DBContext(); 
            Connection con = db.getConnection(); 
            
            String sqlQuery = "select user_id from Users where email = '" + email + "'";
            PreparedStatement stm = con.prepareStatement(sqlQuery);
            ResultSet rs = stm.executeQuery();

            if (rs.next()){
                userId = rs.getInt("user_id"); 
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return userId; 
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = userDAO.getUser();
        
        int userId = userDAO.findUserId("vietchhe170297@fpt.edu.vn"); 
        System.out.println("userId found: " + userId);

//        int result = userDAO.resetPassword("john.doe@example.com", "Abc@12345");
//        System.out.println("Result of register: " + result);
    
    }
}
