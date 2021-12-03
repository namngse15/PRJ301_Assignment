<%-- 
    Document   : admin
    Created on : Oct 30, 2021, 10:17:05 AM
    Author     : tenhik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Document</title>
        <!--css file-->
        <link rel="stylesheet" href="assets/admin.css">
        <!-- boostrap -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <!-- font awesome -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    </head>

    <body>
        <jsp:include page="adminHeader.jsp"/>
        <div class="header-block">
        </div>
        <jsp:include page="adminSidemenu.jsp"/>
        <div class="content-wrapper">
            <div class="row">  
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                        <span class="font-weight-bold">${currentLogin.username}</span>
                        <span class="text-black-50">${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).email}</span>
                        <span> </span>
                    </div>
                </div>
                <div class="col-md-5 border-right tab-content"> 
                    <div class="d-flex justify-content-between align-items-center mb-3 profile-title">
                        <p class="text-right">Profile Settings</p>
                    </div>
                    <div class="mt-3 profile-input">
                        <!--change info form-->
                        <form action="changeInfo" method="post">
                            <div class="col-md-12 mt-3">
                                <label class="labels">Full name</label>
                                <input name="fullName" type="text" class="form-control text-uppercase" 
                                       placeholder="Enter your name......." value="${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).name}"></div>
                            <div for="email" class="col-md-12 mt-3">
                                <label class="labels">Email</label>
                                <input name="email" type="email" class="form-control" 
                                       placeholder="Enter your email......." value="${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).email}">
                            </div>
                            <div class="col-md-12 mt-3">
                                <label class="labels">Phone</label>
                                <input name="phone" type="text" class="form-control"
                                       placeholder="Enter your phone......." value="${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).phone}">
                            </div>
                            <div class="col-md-12 mt-3">
                                <label class="labels">Address</label>
                                <input name="address" type="text" class="form-control text-capitalize" 
                                       placeholder="Enter your address......." value="${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).address}">
                            </div>
                            <div class="col-md-12 mt-3">
                                <label class="labels">Date of Birth</label>
                                <input name="date" type="date" class="form-control" 
                                       placeholder="Enter your birth......." value="${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).dob}">

                            </div>
                            <div class="mt-5 text-center profile-save">
                                <button class="btn" type="button" onclick="openChangeInfo()">Save Change</button>
                            </div>
                            <!--enter password when change info-->
                            <div class="modal-nofi" id="modal-change">
                                <div class="modal-nofi-overlay"></div>
                                <div class="modal-change-body">
                                    <div class="modal-change-input">
                                        <p>Enter password: </p>
                                        <input type="password" class="form-control" name="oldPass" placeholder="Enter password......." value="">
                                    </div>
                                    <div class="modal-change-button">
                                        <button class="btn" type="submit">Save Changes</button>
                                        <button class="btn" type="button" onclick="closeChangeInfo()">Cancel</button>
                                    </div>
                                </div>
                            </div>
                            <!-- end enter password when change info-->
                        </form> 
                        <!--end change info form-->
                    </div>
                </div>
                <div class="col-md-4 edit-image-title">
                    <div class="d-flex justify-content-between"><p>Edit Profile Avatar</p></div><br>
                    <div class="p-3 py-5 edit-image">
                        <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                        </div>
                        <input type="file" name="uploadfile" id="img" style="display:none;"/>
                        <label for="img">Upload avatar</label>
                    </div>
                </div>
                <div class="header-block">
                </div>
            </div> 
            <!-- footer -->
            <footer class="pt-4 my-md-5 pt-md-5 border-top container">
                <div class="row">
                    <div class="col-12 col-md">
                        <i class="fas fa-laptop fa-3x mb-3 ml-3"></i>
                        <small class="d-block mb-3 text-muted">© 2017-2018</small>
                    </div>
                    <div class="col-6 col-md">
                        <h5 class="font-weight-bold ">Features</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Home</a></li>
                            <li><a class="text-muted" href="#">Product</a></li>
                            <li><a class="text-muted" href="#">Contact</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md">
                        <h5 class="font-weight-bold ">User</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Profile</a></li>
                            <li><a class="text-muted" href="#">Sign in</a></li>
                            <li><a class="text-muted" href="#">Log in</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md">
                        <h5 class="font-weight-bold ">About</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Team</a></li>
                            <li><a class="text-muted" href="#">Locations</a></li>
                            <li><a class="text-muted" href="#">Privacy</a></li>
                            <li><a class="text-muted" href="#">Terms</a></li>
                        </ul>
                    </div>
                </div>
            </footer>
        </div>
        <script>
            function openChangeInfo() {
                document.getElementById("modal-change").style.display = "flex";
            }
            function closeChangeInfo() {
                document.getElementById("modal-change").style.display = "none";
            }
        </script>
        <script src="assets/admin.js"></script>

    </body>

</html>