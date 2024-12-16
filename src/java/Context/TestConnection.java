/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Context;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        
        try{
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if(con!=null){
                System.out.println("Connection success");
            }else{
                System.out.println("Connection failed");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
