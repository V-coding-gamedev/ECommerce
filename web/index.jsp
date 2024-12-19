<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="DAL.ProductDAO"%>
<%@ page import="Model.*" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My E-Commerce</title>
        <!-- Add Bootstrap CSS for better styling -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <style>
            .card-title {
                /*                max-height: 3rem; */
                /*                overflow: hidden;  Ẩn phần nội dung tràn ra */
                /*                text-overflow: ellipsis;  Thêm dấu "..." cho nội dung bị cắt */
                /*                white-space: nowrap;  Đảm bảo không xuống dòng */
            }

            .card {
                /*                width: 18rem;   Đảm bảo các card có cùng chiều rộng */
                /*                margin-left: 1000px;*/
            }

            .card-body {
                display: flex;  /* Kích hoạt Flexbox */
                flex-direction: column;  /* Đặt các phần tử xếp theo cột */
                align-items: center; /* Căn giữa theo chiều ngang */
                justify-content: center; /* Căn giữa theo chiều dọc */
                text-align: center /* Căn giữa nội dung văn bản (nếu cần) */
            }

            body {
                overflow-x: hidden; /* vô hiệu hóa cuộn ngang */
            }
        </style>
    </head>
    
    <body>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">My E-Commerce</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="about.jsp">About</a>
                    </li>
                    <%-- Kiểm tra trạng thái đăng nhập --%>
                    <%
                        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
                        if (isLoggedIn != null && isLoggedIn) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="myProfile.jsp">My Profile</a>
                    </li>
                    
                    <form action="logout" method="GET">
                        <li class="nav-item">
                            <a class="nav-link">Logout</a>
                        </li>
                    </form>
                    
                    <% } else { %>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Login</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="register.jsp">Register</a>
                    </li>
                    <% } %>

                 </ul>
            </div>
        </nav>

        <%
            ProductDAO productDAO = new ProductDAO();
            ArrayList<Product> products = productDAO.getProduct();

        %>

        <div class="row" style="margin-left: 5px; margin-right: 5px; margin-top: 10px">   
            <% for (Product product : products) {%>
            <div class="col-lg-3">
                <div class="card w-100" style="width: 200rem; margin-bottom: 10px;">
                    <img src="<%= product.getImage_url()%>" style="max-width: 600px; max-height: 200px; object-fit: contain; margin-top: 10px">
                    <div class="card-body">
                        <h5 class="card-title"><%= product.getName()%></h5>
                        <h6 class="price">Price: $<%= product.getPrice()%></h6>
                        <!--                        <div class="mt-3 d-flex justify-content-between">-->

                        <!--                        tạo margin top là 1.5 rem-->
                        <!--                        d:flex; để các phần tử nằm ngang-->
                        <!--                        
                                                    justify-content-between: sắp xếp các phần tử con sao cho:
                                                        phần tử đầu tiên nằm sát bên trái, 
                                                        phần tử cuối nằm sát bên phải, 
                                                        khoảng trống giữa sẽ được phân bổ đều 
                        -->
                        <div class="mt-3"> 
                            <a href="#" class="btn btn-primary">Add to Cart</a>
                            <a href="#" class="btn btn-primary">Buy Now</a>                
                        </div>
                    </div>
                </div>
            </div>
            <% }%>
        </div>

        <!-- Add Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>

<script>
    function logout(){
        localStorage.removeItem('isLoggedIn');
        sessionStorage.removeItem('isLoggedIn'); 
        sessionStorage.clear(); 
        
        window.location.href= "index.jsp"; 
    }
</script>
