<%-- 
    Document   : adminProduct
    Created on : Nov 23, 2021, 3:12:15 PM
    Author     : tenhik
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Laptop Store</title>
        <!--css file-->
        <link rel="stylesheet" href="assets/admin.css">
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
    <jsp:useBean id="countOrders" scope="page" class="dal.OrdersDAO"></jsp:useBean>  
    <jsp:useBean id="orderStatus" scope="page" class="dal.OrderStatusDAO"></jsp:useBean> 
    <jsp:useBean id="getAccount" scope="page" class="dal.AccountDAO"></jsp:useBean>   
        <body onunload="">
        <jsp:include page="adminHeader.jsp"/>
        <div class="header-block">
        </div>
        <jsp:include page="adminSidemenu.jsp"/>
        <!--start content--> 
        <div class="content-wrapper">
            <!--all user and order--> 
            <c:if test="${accountView == null}"> 
                <div class="title" style="margin-left:1rem;">
                    <h2>All users's informations</h2>
                </div>
                <!--user table--> 
                <div class="table-responsive-lg table-product">
                    <table class="table table-striped table-hover" id="dataTable">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>                               
                                <th scope="col">Username</th>                        
                                <th scope="col">Email</th>
                                <th scope="col">Status</th>
                                <th scope="col">View Detail</th>
                                <th scope="col">Order</th>
                                <th scope="col">Total</th>                             
                                <th scope="col">Remove user(0 order)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listUsers}" var="o">  
                                <tr>
                                    <th scope="row">${o.id}</th>                               
                                    <td>${o.username}</td>
                                    <td>${o.email}</td>
                                    <c:if test="${o.status==1}">
                                        <td>Online</td>
                                    </c:if>
                                    <c:if test="${o.status==0}">
                                        <td>Offline</td>
                                    </c:if>
                                    <td><a href="admin-user?viewAccount=true&account=${o.id}">View details</a></td>
                                    <c:if test="${countOrders.getCountTotalOrderByAccount(o.id)>1}">    
                                        <td>${countOrders.getCountTotalOrderByAccount(o.id)} Orders</td>
                                    </c:if> 
                                    <c:if test="${countOrders.getCountTotalOrderByAccount(o.id)<=1}">    
                                        <td>${countOrders.getCountTotalOrderByAccount(o.id)} Order</td>
                                    </c:if> 
                                    <td><fmt:formatNumber value = "${countOrders.getTotalOrderPriceByAccount(o.id)}" type = "currency"/></td>
                                    <td>
                                        <c:if test="${countOrders.getTotalOrderByAccountandStatus(o.id)==countOrders.getCountTotalOrderByAccount(o.id)}">    
                                            <a href="#" onclick="removeAccount(${o.id})">Remove</a>
                                        </c:if>

                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!--end user table--> 
                <div class="row" style="margin: 5rem 0;">
                    <div class="col-sm-3 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Number of user: ${countusers} </h5>
                                <p class="card-text">Date: ${localDate}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <c:if test="${orderView == null}">            
                    <div class="title" style="margin-left:1rem;">
                        <h2>All users's orders</h2>
                    </div>
                    <!--all user's orders table-->             
                    <div class="table-responsive-lg table-product">
                        <table class="table table-striped table-hover" id="dataTable2" >
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Status</th>
                                    <th>Edit</th>
                                    <th scope="col">Address</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Order date</th>
                                    <th scope="col">Note</th>       
                                    <th>View Details</th>
                                    <th scope="col">Phone</th>
                                    <th scope="col">Email</th>
                                    <th>Remove orders (Canceled/Received)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listOrders}" var="o">  
                                    <tr>
                                        <th scope="row">${o.id}</th>
                                        <td>${orderStatus.getOrderStatusById(o.statusId).name}</td>                                       
                                        <td>
                                            <c:if test="${o.statusId != 6}" >
                                                <form action="admin-user">
                                                    <input type="hidden" name="orderId" value="${o.id}"/>
                                                    <select name="editStatus" id="myselect" onchange="this.form.submit()">
                                                        <option>Request</option>
                                                        <c:forEach items="${listStatus}" var="s">
                                                            <option value="${s.id}">${s.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </form>
                                            </c:if>
                                        </td>
                                        <td class="text-capitalize">${o.address}</td>
                                        <td><fmt:formatNumber value = "${o.total}" type = "currency"/></td>
                                        <td><fmt:formatDate pattern="dd-MM-yyyy" value = "${o.date}" /></td>
                                        <td>${o.note}</td>
                                        <td><a href="admin-user?viewOrder=true&orderId=${o.id}&total=${o.total}">View details</a></td>
                                        <td>${o.phone}</td>
                                        <td>${o.email}</td>
                                        <td>
                                            <c:if test="${o.statusId ==4||o.statusId== 6}">
                                                <a href="#" onclick="removeOrder(${o.id},${o.statusId})">Remove</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!--end all user's orders table--> 
                    <div class="row" style="margin: 5rem 0;">
                        <div class="col-sm-3 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Number of orders: ${countOrders.countTotalOrder}</h5>
                                    <p class="card-text">Date: ${localDate}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <!--all user's orders detail-->             
                <c:if test="${orderView != null}">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Order details</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-user"><i class=" fa-2x fas fa-arrow-left"></i></a>
                    </div>
                    <div class="table-responsive-lg table-product">
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
                                        <td><fmt:formatNumber value = "${od.productPrice}" type = "currency"/></td>
                                        <td>${od.productQuantity}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row" style="margin: 5rem 0;">
                        <div class="col-sm-3 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Order ${orderId} Total: <fmt:formatNumber value = "${total}" type = "currency"/></h5>
                                    <p class="card-text">Date: ${localDate}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <!-- end all user's orders detail-->   
            </c:if>
            <!--end all user and order--> 
            <!--one account and order table--> 
            <c:if test="${accountView != null && account != null}"> 
                <div class="title" style="display:flex;margin-left:1rem;">
                    <h2 class="text-capitalize">${account.name}</h2>
                    <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-user"><i class=" fa-2x fas fa-arrow-left"></i></a>
                </div> 
                <!--one account--> 
                <div class="table-responsive-lg table-product">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">FullName</th>
                                <th scope="col">Date of birth</th>
                                <th scope="col">Phone</th>                                
                                <th scope="col">Address</th> 
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">${account.id}</th>
                                <td class="text-capitalize">${account.name}</td>
                                <td><fmt:formatDate pattern="dd-MM-yyyy" value = "${account.dob}" /></td>
                                <td>${account.phone}</td>
                                <td class="text-capitalize">${account.address}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!--end one account-->             
                <div class="row" style="margin: 5rem 0;">
                    <div class="col-sm-4 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${account.name}'s orders: ${countOrders.getCountTotalOrderByAccount(account.id)} </h5>
                                <p class="card-text">Date: ${localDate}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <!--one account's orders--> 
                <c:if test="${orderView == null}">
                    <div class="title" style="margin-left:1rem;">
                        <h2 class="text-capitalize">${account.name}'s orders</h2>
                    </div> 
                    <div class="table-responsive-lg table-product">
                        <table class="table table-striped table-hover" id="dataTable">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Total price</th>
                                    <th scope="col">Order date</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Notes</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${listOrdersUser}" var="o">
                                    <tr>
                                        <th scope="row">${o.id}</th>
                                        <td><fmt:formatNumber value = "${o.total}" type = "currency"/></td>
                                        <td><fmt:formatDate pattern="dd-MM-yyyy" value = "${o.date}" /></td>
                                        <td>${orderStatus.getOrderStatusById(o.statusId).name}</td>
                                        <td>${o.note}</td>
                                        <td><a href="admin-user?viewAccount=true&account=${account.id}&viewOrder=true&orderId=${o.id}&total=${o.total}">View Details</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row" style="margin: 5rem 0;">
                        <div class="col-sm-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${account.name}'s total price: <fmt:formatNumber value = "${countOrders.getTotalOrderPriceByAccount(account.id)}" type = "currency"/></h5>
                                    <p class="card-text">Date: ${localDate}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${orderView != null&&listOrDetails != null}">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2 class="text-capitalize">${account.name}'s orders</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-user?viewAccount=true&account=${account.id}"><i class=" fa-2x fas fa-arrow-left"></i></a>
                    </div> 
                    <div class="table-responsive-lg table-product">
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
                                        <td><fmt:formatNumber value = "${od.productPrice}" type = "currency"/></td>
                                        <td>${od.productQuantity}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row" style="margin: 5rem 0;">
                        <div class="col-sm-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${account.name}'s total: <fmt:formatNumber value = "${total}" type = "currency"/></h5>
                                    <p class="card-text">Date: ${localDate}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <!--end one account's orders--> 
            </c:if>
            <!-- end one account and order table-->    
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
            <!-- end footer -->           
        </div>  
        <script>
            $(document).ready(function () {
                $('#dataTable,#dataTable2').DataTable();
            });
            function removeAccount(id) {
                var option = confirm('Are you sure to delete account: ' + id);
                if (option === true) {
                    window.location.href = 'admin-user?removeAccount=' + id;
                }
            }
            function removeOrder(id, statusId) {
                var option = confirm('Are you sure to delete order: ' + id);
                if (option === true) {
                    window.location.href = 'admin-user?removeOrder=' + id + '&statusId=' + statusId;
                }
            }
        </script>
        <script>
            // keep scroll position when reload
            document.addEventListener("DOMContentLoaded", function (event) {
                var scrollpos = localStorage.getItem('scrollpos');
                if (scrollpos)
                    window.scrollTo(0, scrollpos);
            });
            window.onbeforeunload = function (e) {
                localStorage.setItem('scrollpos', window.scrollY);
            };
        </script>
        <script src="assets/admin.js"></script>
    </body>
</html>