package Controller;

import DAL.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        User user = userDAO.checkUserExist(email);
        
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com$")){
            request.setAttribute("notification", "Email is in invalid format");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; 
        }

        boolean isPasswordValid = checkPassword(password);
        if (!isPasswordValid){
            request.setAttribute("notification", "Password must contain at least 8 characters, including lower case letters, upper case letters, numbers and special characters");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; 
        } 
            
        boolean isConfirmPasswordMatch = checkConfirmPassword(confirmPassword, password); 
        if (!isConfirmPasswordMatch){
            request.setAttribute("notification", "Confirm password must match the registered password");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        boolean isFullnameValid = checkFullname(fullName);
        if (!isFullnameValid){
            request.setAttribute("notification", "Fullname must contain letters only.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        boolean isPhoneNumberValid = checkPhoneNumber(phoneNumber);
        if (!isPhoneNumberValid){
            request.setAttribute("notification", "Phone number is invalid");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        boolean checkPhoneNumberLength = checkPhoneNumberLength(phoneNumber);
        if (!checkPhoneNumberLength) {
            request.setAttribute("notification", "Phone number must contain 10 numeric characters");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // check if email has existed
        if (user.getUsername() == null) {
            int registerResult = userDAO.register(username, email, password, fullName, phoneNumber, address, "Customer");

            if (registerResult > 0) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("notification", "smt went wrong");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return; 
            }
        } else {
            request.setAttribute("notification", "Email has already existed. Enter another one.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; 
        }
    }

    // check password's validity
    public boolean checkPassword(String password) {  
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.{8,}).*$")) {
            return false;
        }

        return true;
    }
    
    // check whether confirm password matches password
    public boolean checkConfirmPassword(String confirmPassword, String password) {
        if (!confirmPassword.matches(password)) {
            return false;
        }
        
        return true; 
    }
    
    // check if full name contains numbers
    public boolean checkFullname(String fullName){
        if (fullName.matches(".*\\d.*")) {
            // .*\\d.*
            /* 
                .*: Bất kỳ ký tự nào, xuất hiện 0 hoặc nhiều lần.
                \\d: Đại diện cho một chữ số (0-9).
                => Kết hợp này kiểm tra nếu trong chuỗi có ít nhất một chữ số.
             */
            return false; 
        }
        
        return true;
    }
    
    // check phone number's validity
    public boolean checkPhoneNumber(String phoneNumber){
        if (!phoneNumber.matches("[0-9]*")) { // check if number contains letters
            return false; 
        } 
        
        return true; 
    }
    
    // check phone number's length
    public boolean checkPhoneNumberLength(String phoneNumber){
        if (phoneNumber.length() != 10) { 
            return false; 
        } 
        
        return true; 
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
