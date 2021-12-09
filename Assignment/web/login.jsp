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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="icon" href="assets/favicon.ico" type="image/x-icon">
        <!-- boostrap -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <!--css file-->
        <link rel="stylesheet" href="assets/authentication.css">
        <!-- font awesome -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
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
                <button id="login-toggle" onclick="toggleLogin()">log in</button>
                <button id="signup-toggle" onclick="toggleSignup()">sign up</button>
            </div>
            <div class="login-block"></div>

            <div id="login-form">
                <form action="login" method="post">
                    <input type="hidden" name="loginAction" value="true">
                    <div class="input-border">
                        <input type="text"  name="username" value="<%=username%>" placeholder="Enter email or username" required/>
                    </div> 
                    <div class="input-border">
                        <input type="password" name="password" value="<%=password%>" placeholder="Enter password" required/>
                    </div>
                    <input type="checkbox" name ="remember" value="remember"/> Remember me <br/>
                    <button type="submit" class="btn login" value="Login">login</button>
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
                    <input type="email" name="email" placeholder="Enter your email" value="${email}"required/>
                    <input type="text" name="user" placeholder="Enter your username" onChange="checkUser()" value="${user}"required/>
                    <input type="password" name="pass" placeholder="Create password" onChange="checkPass()" value="${pass}" required/>
                    <input hidden />
                    <input type="password" name="confirm" placeholder="Re-enter password" onChange="checkPass()" required/>
                    <button type="submit" class="btn signup">create account</button>
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
                        <p>Account already exists</p>
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
                        <p>Email already registered </p>
                    </div>
                </div>
            </div>
        </c:if> 
        <c:if test="${checkInsert==false}">
            <div onclick="closeModal()" class="modal-nofi" id="modal-edit">
                <div class="modal-nofi-overlay"></div>
                <div class="modal-nofi-body">
                    <div class="modal-nofi-fail">
                        <i class="far fa-times-circle"></i>
                        <p>Cannot register your account </p>
                    </div>
                </div>
            </div>
        </c:if>     
    </body>
    <script>
        function checkUser() {
            const username = document.querySelector('input[name=user]');
            var regex = /[0-9a-zA-Z]{6,}/;
            if (username.value.match(regex)) {
                username.setCustomValidity('');
            } else {
                username.setCustomValidity('Username mustcontain number and alphabet');
            }
        }

        function checkPass() {
            const password = document.querySelector('input[name=pass]');
            const confirm = document.querySelector('input[name=confirm]');
            var regex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
            if (!regex.test(password.value)) {
                password.setCustomValidity('Password must contains at least one number,uppercase and lowercase,special character and above 8 characters');
            } else {
                if (confirm.value === password.value) {
                    confirm.setCustomValidity('');
                } else {
                    confirm.setCustomValidity('Passwords do not match');
                }
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
