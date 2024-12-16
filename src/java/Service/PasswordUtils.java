/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.security.SecureRandom; 

/**
 *
 * @author Asus
 */
public class PasswordUtils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGHTH = 8;
    
    public static String generateRandomPassword(){
        SecureRandom random = new SecureRandom(); 
        StringBuilder password = new StringBuilder(PASSWORD_LENGHTH); 
        
        for (int i = 0; i < PASSWORD_LENGHTH; i++){
            int index = random.nextInt(CHARACTERS.length()); // sinh số ngẫu nhiên trong khoảng 0 đến PASSWORD_LENGTH - 1
            password.append(CHARACTERS.charAt(index)); 
        }
        
        return password.toString(); // chuyển đối tượng StringBuilder thành String
    }
}
