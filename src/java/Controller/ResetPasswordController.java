/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class ResetPasswordController extends HttpServlet {

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
            out.println("<title>Servlet ResetPasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
     
        HttpSession session = request.getSession(); 
        
        String foundCurrentPassword = "";  
        
        String email = (String) session.getAttribute("email");
        
        String currentPassword = request.getParameter("currentPassword"); 
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        foundCurrentPassword = userDAO.checkCorrespondingPassword(email); 
        
        
        if (!currentPassword.matches(foundCurrentPassword)){
            session.setAttribute("passwordNotification", "The current password is not correct. Check your email again.");
            response.sendRedirect("resetPassword.jsp");
            return; 
        }
        
        boolean isNewPasswordValid = checkPassword(newPassword);
        if (!isNewPasswordValid){
            session.setAttribute("passwordNotification", "Password must contain at least 8 characters, including lower case letters, upper case letters, numbers and special characters");
            response.sendRedirect("resetPassword.jsp");
            return; 
        } 
        
        boolean isConfirmPasswordMatch = checkConfirmPassword(confirmPassword, newPassword); 
        if (!isConfirmPasswordMatch){
            session.setAttribute("passwordNotification", "Confirm password must match the reset password");
            response.sendRedirect("resetPassword.jsp");
            return;
        }
        
        int resetPasswordResult = userDAO.resetPassword(email, newPassword); // Lưu mật khẩu mới vào CSDL 
        if (resetPasswordResult > 0){
            session.setAttribute("passwordNotification", "Reset password successfully. Navigate to login page to sign in");
            response.sendRedirect("resetPassword.jsp");
            return;
        } else {
            session.setAttribute("passwordNotification", "Something wrong");
            request.getRequestDispatcher("resetPassword.jsp");
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
