package Model;

public class User {
    private int user_id;
    private String username;
    private String email;
    private String password;
    private String full_name;
    private String phone_number; 
    private String address; 
    private String role; 

    public User() {
    }

    public User(int user_id, String username, String email, String password, String full_name, String phone_number, String address, String role) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.address = address;
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
    
    
}
