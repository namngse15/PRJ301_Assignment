<%-- 
    Document   : adminSidemenu
    Created on : Nov 29, 2021, 11:40:03 PM
    Author     : tenhik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="wrapper">
    <div class="col-12 col-lg-12 sidebar">
        <div class="sidebar-header text-uppercase font-weight-bold h4">
            <i class="fas fa-address-card fa-1x ml-1 mr-1"></i>
            <span>Laptop Manager</span>
        </div>
        <ul class="sidebar-list">
            <li class="sidebar-items" ><button id="admin-btn" onclick="toggleAdministrator()"><i class="fas fa-user-shield mr-3" ></i><span>Admin</span></button>
            </li>
            <li class="sidebar-items"><button id="user-btn"onclick="toggleUser()"><i class="fas fa-users mr-3"></i><span>User</span></button></li>
            <li class="sidebar-items"><button id="product-btn"onclick="toggleProduct()"><i class="fas fa-laptop mr-3"></i><span>Product</span></button></li>
        </ul>
        <div class="sidebar-footer"></div>
    </div>
</div>