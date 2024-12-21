<%-- 
    Document   : myCart
    Created on : Dec 20, 2024, 1:02:05 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <% 
            String productIdString = request.getAttribute("productId").toString();
            int productId = Integer.parseInt(productIdString); 
        
        %>
        
        <input type="text" placeholder="Email" name="email" value="${productId}">
        
        <h1>Hello World!</h1>
    </body>
</html>
