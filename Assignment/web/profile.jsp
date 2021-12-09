<%-- 
    Document   : profile
    Created on : Nov 9, 2021, 10:12:38 AM
    Author     : tenhik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Profile</title>
        <!--css file-->
        <link rel="stylesheet" href="assets/style.css">
        <link rel="icon" href="assets/favicon.ico" type="image/x-icon">
        <!-- boostrap -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <!--datatable-->
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>
        <!-- font awesome -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    </head>

    <body>

        <jsp:useBean id="orderStatus" scope="page" class="dal.OrderStatusDAO"></jsp:useBean>
        <jsp:useBean id="userInfo" scope="page" class="dal.AccountDAO"></jsp:useBean>
        <jsp:include page="template/header.jsp"/>
        <div class="header-block">
        </div>
        <div class="container-fluid">
            <div class="row">               
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                        <span class="font-weight-bold">${currentLogin.username}</span>
                        <span class="text-black-50">${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).email}</span>
                        <span> </span>
                    </div>
                    <ul class="profile-menu align-items-center text-center">
                        <li><a href="javascript:;" onclick="openMenu1()">Account</a></li>
                        <li><a href="javascript:;" onclick="openMenu2()">View order</a></li>
                        <li><a href="javascript:;" onclick="openMenu3()">Change password</a></li>
                            <c:if test="${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).status == 1}">
                            <li><img src="https://img.icons8.com/doodle/48/000000/online--v1.png"/></li>
                            </c:if> 
                    </ul>    
                </div>
                <div class="col-md-5 border-right tab-content">
                    <!--change user info-->
                    <div id="menu1">
                        <c:if test="${not empty accountView}">
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
                                                <input type="password" class="form-control" name="pass" placeholder="Enter password......." value="">
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
                        </c:if>
                    </div> 
                    <!--end change user info-->
                    <!--user order-->
                    <div id="menu2">
                        <c:if test="${orderView != null &&listOrDetails == null}">
                            <div class="d-flex justify-content-between align-items-center mb-3 profile-title">
                                <p class="text-right">View order</p>
                            </div>
                            <div class="table-responsive-lg">
                                <table class="table table-striped table-hover" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Total price</th>
                                            <th scope="col">Order date</th>
                                            <th scope="col">Status</th>
                                            <th scope="col">Received</th>
                                            <th scope="col">Notes</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listOrders}" var="o">
                                            <tr>
                                                <th scope="row">${o.id}</th>
                                                <td><fmt:formatNumber value = "${o.total}" type = "currency"/></td>
                                                <td><fmt:formatDate pattern="dd-MM-yyyy" value = "${o.date}"/></td>
                                                <td>${orderStatus.getOrderStatusById(o.statusId).name}</td>
                                                <td>
                                                    <c:if test="${o.statusId == 3}">
                                                        <form action="profile">
                                                            <input type="hidden" name="viewOrder" value="true"/>
                                                            <input type="hidden" name="orderId" value="${o.id}"/>
                                                            <select name="editStatus" id="myselect" onchange="this.form.submit()">
                                                                <option>${orderStatus.getOrderStatusById(o.statusId).name}</option>
                                                                <c:forEach items="${listStatus}" var="s">
                                                                    <option value="${s.id}">${s.name}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </form>
                                                    </c:if> 
                                                </td>
                                                <td>${o.note}</td>
                                                <td><a href="detailOrders?viewOrder=true&orderId=${o.id}">View Details</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                        <!--user order detail-->
                        <c:if test="${orderView != null&&listOrDetails != null}">
                            <div class="d-flex justify-content-between align-items-center mb-3 profile-title">
                                <p class="text-right">View Detail </p>
                            </div>
                            <div class="table-responsive-lg">
                                <table class="table table-striped table-hover" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th scope="col">Product ID</th>
                                            <th scope="col">Product Name</th>
                                            <th scope="col">Product Color</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Quantity</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listOrDetails}" var="od"> 
                                            <tr>
                                                <td>${od.productId}</td>
                                                <td>${od.productName}</td>
                                                <td>${od.productColor}</td>
                                                <td>${od.productPrice}</td>
                                                <td>${od.productQuantity}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                        <!-- end user order detail-->
                    </div>
                    <!-- end user order-->
                    <!--change password form-->
                    <div id="menu3">
                        <div class="d-flex justify-content-between align-items-center mb-3 profile-title">
                            <p class="text-right">Passwords Settings</p>
                        </div>
                        <div class="mt-3 profile-input" style="margin: 0 5rem">
                            <form action="changePassword" method="post">
                                <div class="col-md-12 mt-3">
                                    <label class="labels">Enter old password</label>
                                    <input type="password" class="form-control" name="oldPass" placeholder="Password"/>
                                </div>
                                <div class="col-md-12 mt-3">
                                    <label class="labels">Enter new password</label>
                                    <input type="password" class="form-control" name="newPass" placeholder="Password" onChange="checkPass()"/>
                                </div>
                                <div class="col-md-12 mt-3">
                                    <label class="labels">Re-enter new password</label>
                                    <input type="password" class="form-control" name="confirm" placeholder="Password" onChange="checkPass()"/>
                                </div>
                                <div class="mt-5 text-center profile-save">
                                    <button class="btn" type="submit">Save Changes</button>
                                </div>
                            </form> 
                        </div>
                    </div>
                    <!--end change password form-->
                </div>
                <!--update user avatar-->  
                <div class="col-md-4 edit-image-title">
                    <div class="d-flex justify-content-between"><p>Edit Profile Avatar</p></div><br>
                    <div class="p-3 py-5 edit-image">
                        <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                        </div>
                        <input type="file" name="uploadfile" id="img" style="display:none;"/>
                        <label for="img">Upload avatar</label>
                    </div>
                </div>
                <!--end update user avatar-->  
            </div>
            <!--modal update user info-->     
            <c:if test="${checkAccount==true}">
                <div onclick="closeModal()" class="modal-nofi" id="modal-edit">
                    <div class="modal-nofi-overlay"></div>
                    <div class="modal-nofi-body">
                        <div class="modal-nofi-inner">
                            <i class="far fa-check-circle"></i>
                            <p>Change info successfully </p>
                        </div>
                    </div>
                </div>
            </c:if> 
            <c:if test="${checkPass==true}">
                <div onclick="closeModal()" class="modal-nofi" id="modal-edit">
                    <div class="modal-nofi-overlay"></div>
                    <div class="modal-nofi-body">
                        <div class="modal-nofi-inner">
                            <i class="far fa-check-circle"></i>
                            <p>Change password successfully </p>
                        </div>
                    </div>
                </div>
            </c:if> 
            <c:if test="${checkPass==false}">
                <div onclick="closeModal()" class="modal-nofi" id="modal-edit">
                    <div class="modal-nofi-overlay"></div>
                    <div class="modal-nofi-body">
                        <div class="modal-nofi-fail">
                            <i class="far fa-times-circle"></i>
                            <p>Change password failed </p>
                        </div>
                    </div>
                </div>
            </c:if> 
            <c:if test="${checkPass==true}">
                <div class="modal-nofi" id="modal-edit">
                    <div class="modal-nofi-overlay"></div>
                    <div class="modal-change-body">
                        <div class="modal-nofi-inner">
                            <i class="far fa-check-circle"></i>
                            <p>Change password successfully </p>
                        </div>
                        <a href="#" onclick="reDirect()">Close</a>
                    </div>
                </div>
            </c:if>
        </div>
        <!-- end modal update user info-->  
        <jsp:include page="template/footer.jsp"/> 
        <script>
            function checkPass() {
                const oldPassword = document.querySelector('input[name=oldPass]');
                const password = document.querySelector('input[name=newPass]');
                const confirm = document.querySelector('input[name=confirm]');
                var regex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
                if (oldPassword.value === password.value) {
                    password.setCustomValidity('New password same the old password');
                } else {
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
            }
            function openChangeInfo() {
                document.getElementById("modal-change").style.display = "flex";
            }
            function closeChangeInfo() {
                document.getElementById("modal-change").style.display = "none";
            }
            function closeModal() {
                document.getElementById("modal-edit").style.display = "none";
            }
            function reDirect() {
                document.getElementById("modal-edit").style.display = "none";
                window.location.href = "login.jsp";
            }
            function openMenu1() {
                document.getElementById("menu1").style.display = "block";
                window.location.href = "profile?viewAccount=true";
                document.getElementById("menu2").style.display = "none";
                document.getElementById("menu3").style.display = "none";
            }
            function openMenu2() {
                document.getElementById("menu2").style.display = "block";
                window.location.href = "profile?viewOrder=true";
                document.getElementById("menu1").style.display = "none";
                document.getElementById("menu3").style.display = "none";
            }
            function openMenu3() {
                document.getElementById("menu3").style.display = "block";
                document.getElementById("menu1").style.display = "none";
                document.getElementById("menu2").style.display = "none";
            }

        </script>
        <script>
            $(document).ready(function () {
                $('#dataTable').DataTable();
            });
        </script>
        <script src="assets/main.js"></script>
    </body>
</html>
