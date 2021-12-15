<%-- 
    Document   : login
    Created on : Oct 28, 2021, 11:31:32 PM
    Author     : tenhik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" 
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="icon" href="assets/favicon.ico" type="image/x-icon">
        <!-- boostrap -->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <!--css file-->
        <link rel="stylesheet" href="assets/authentication.css">
        <!-- font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <% Cookie[] cookies = request.getCookies();%>
    </head>

    <body>
        <%
            String username = "";
            String password = "";

            if (cookies != null) {
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("username")) {
                        username = cooky.getValue();
                    }
                    if (cooky.getName().equals("password")) {
                        password = cooky.getValue();
                    }
                }
            }
        %>
        <div class="form-modal">
            <div class="form-toggle">
                <button id="login-toggle" onclick="toggleLogin()">Đăng nhập</button>
                <button id="signup-toggle" onclick="toggleSignup()">Đăng kí</button>
            </div>
            <div class="login-block"></div>

            <div id="login-form">
                <form action="login" method="post">
                    <input type="hidden" name="loginAction" value="true">
                    <input type="text"  name="username" value="<%=username%>" placeholder="Tên đăng nhập" required/>
                    <input type="password" name="password" value="<%=password%>" placeholder="Mật khẩu" required/>
                    <input type="checkbox" name ="remember" value="remember"/> Remember me <br/>
                    <button type="submit" class="btn login" value="Login">Đăng nhập</button>
                    <c:if test="${statement != null}">
                        <div class="notification">
                            <p class="">${statement}</p>
                        </div>
                    </c:if>
                    <hr />
                </form>
            </div>

            <div id="signup-form">
                <form action="create-account" method="post">
                    <input type="email" name="email" placeholder="Nhập email" value="${email}"required/>
                    <input type="text" name="user" placeholder="Nhập tên tài khoản" onChange="checkUser()" value="${user}"required/>
                    <input type="password" name="pass" placeholder="Nhập mật khẩu" onChange="checkPass()" value="${pass}" required/>
                    <input hidden />
                    <input type="password" name="confirm" placeholder="Nhập lại mật khẩu" onChange="checkPass()" required/>
                    <button type="submit" class="btn signup">Tạo tài khoản</button>
                    <hr />
                </form>
            </div>
        </div>
        <c:if test="${checkUser==2}">
            <div onclick="closeModal()" class="modal-nofi" id="modal-edit">
                <div class="modal-nofi-overlay"></div>
                <div class="modal-nofi-body">
                    <div class="modal-nofi-fail">
                        <i class="far fa-times-circle"></i>
                        <p>Tài khoản đã tồn tại</p>
                    </div>
                </div>
            </div>
        </c:if> 
        <c:if test="${checkUser==3}">
            <div onclick="closeModal()" class="modal-nofi" id="modal-edit">
                <div class="modal-nofi-overlay"></div>
                <div class="modal-nofi-body">
                    <div class="modal-nofi-fail">
                        <i class="far fa-times-circle"></i>
                        <p>Email đã được đăng kí</p>
                    </div>
                </div>
            </div>
        </c:if> 
    </body>
    <script>
        function checkUser() {
            var username = document.querySelector('input[name=user]');
            var regex = /[0-9a-zA-Z]{6,}/;
            if (username.value.match(regex)) {
                username.setCustomValidity('');
            } else {
                username.setCustomValidity('Tên đăng nhập phải chứa chữ và số và trên 6 kí tự');
            }
        }
        
        function checkPass() {
            var password = document.querySelector('input[name=pass]');
            var regexP = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
            if (password.value.match(regexP)) {
                password.setCustomValidity('');
                var confirm = document.querySelector('input[name=confirm]');
                if (confirm.value === password.value) {
                    confirm.setCustomValidity('');
                } else {
                    confirm.setCustomValidity('Mật khẩu không khớp');
                }
            } else {
                password.setCustomValidity('Mật khẩu có ít nhất 1 số, chữ hoa, thường, kí tự đặc biệt và trên 8 kí tự');
            }
        }
        
        //login-signup
        function toggleSignup() {
            document.getElementById("login-form").style.display = "none";
            document.getElementById("login-toggle").style.boxShadow = "inset 0.2rem 0.2rem 0.5rem #c8d0e7, inset -0.2rem -0.2rem 0.5rem #FFFFFF";
            document.getElementById("signup-form").style.display = "block";
            document.getElementById("signup-toggle").style.boxShadow = "0.3rem 0.3rem 0.6rem #c8d0e7,-0.2rem -0.2rem 0.5rem #FFFFFF";
        }
        function toggleLogin() {
            document.getElementById("signup-form").style.display = "none";
            document.getElementById("login-toggle").style.boxShadow = "0.3rem 0.3rem 0.6rem #c8d0e7,-0.2rem -0.2rem 0.5rem #FFFFFF";
            document.getElementById("login-form").style.display = "block";
            document.getElementById("signup-toggle").style.boxShadow = "inset 0.2rem 0.2rem 0.5rem #c8d0e7, inset -0.2rem -0.2rem 0.5rem #FFFFFF";
        }

        function closeModal() {
            document.getElementById("modal-edit").style.display = "none";
        }
    </script>
</html>
