<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                font-family: Arial, Helvetica, sans-serif;
            }
            form {
                border: 3px solid #f1f1f1;
            }

            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }

            button {
                background-color: #04AA6D;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }

            button:hover {
                opacity: 0.8;
            }

            .cancelbtn {
                width: auto;
                padding: 10px 18px;
                background-color: #f44336;
            }

            .imgcontainer {
                text-align: center;
                margin: 24px 0 12px 0;
            }

            img.avatar {
                width: 40%;
                border-radius: 50%;
            }

            .container {
                padding: 16px;
            }

            span.psw {
                float: right;
                padding-top: 16px;
            }

            /* Change styles for span and cancel button on extra small screens */
            @media screen and (max-width: 300px) {
                span.psw {
                    display: block;
                    float: none;
                }
                .cancelbtn {
                    width: 100%;
                }
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">My E-Commerce</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="about.jsp">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="register.jsp">Register</a>
                    </li>
            </div>
        </nav>

        <div class="imgcontainer">
            <img 
                src="https://yt3.googleusercontent.com/ytc/AIdro_kt-sUf4kFDrZ4iaFcyK4EHwVz-jlvQBwjZSA6hQ9ogPEg=s900-c-k-c0x00ffffff-no-rj" alt="Avatar" class="avatar"
                style="width: 400px; height: 400px;"
                >
        </div>
        
        <% String notification = (String) request.getAttribute("notification");%>
        <c:if test="${notification!=null}">
            <strong style="color: red; text-align: center; display: block;">${notification}</strong>
        </c:if>
    
        <form action="resetPassword" method="POST">
            <div class="container">
                <% 
                    HttpSession userSession = request.getSession();
                    String email = (String) userSession.getAttribute("email");
                %>
                <input type="hidden" placeholder="Email" name="email" value="${email}">
                
                <label for="currentPassword"><b>Current Password</b></label>
                <input type="password" placeholder="Enter Password" name="currentPassword" required>

                <label for="newPassword"><b>New Password</b></label>
                <input type="password" placeholder="Enter New Password" name="newPassword" required>
                
                <label for="confirmPassword"><b>Confirm Password</b></label>
                <input type="password" placeholder="Enter Confirm Password" name="confirmPassword" required>
                
                <button type="submit">Create new password</button>
            </div>
        </form>
        
        <% 
            HttpSession httpSession = request.getSession(); 
            String passwordNotification = (String) httpSession.getAttribute("passwordNotification");
        %>
        <c:if test="${passwordNotification!=null}">
            <strong style="color: red; text-align: center; display: block;">${passwordNotification}</strong>
        </c:if>
        
        <% 
            // Xóa thông báo lỗi sau khi đã hiển thị
            httpSession.removeAttribute("passwordNotification"); 
        %>
                
    </body>
</html>

<script>
    function redirectToRegister() {
        window.location.href = "register.jsp";
    }
</script>
