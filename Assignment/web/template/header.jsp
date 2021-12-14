<%-- 
    Document   : header.jsp
    Created on : Oct 30, 2021, 11:00:59 AM
    Author     : tenhik
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="header navbar-light ">
    <!--logo-->
    <a href="home" class="logo text-black h3 font-weight-bold"> <i class="fas fa-laptop"></i> LAPTOP </a>
    <!--search-->
    <div class="search-border">
        <form action="search" method="post" class="">
            <div class="search-form">
                <input  value="${text}" type="search" name="text" id="search-box" placeholder="Tìm kiếm sản phẩm ở đây ...">
                <label for="search-box" class="fas fa-search"></label>
            </div> 
        </form>
    </div>        
    <!--fucntion-icon-->
    <div class="search-border">
        <nav class="icons">
            <a href="home" class="fas fa-home" id="menu-btn"></a>
            <a class="fas fa-search" id="search-btn"></a>
            <c:if test="${listCarts==null||listCarts.size()==0 }">
                <a href="view-empty-cart" class="fas fa-shopping-cart"></a>  
            </c:if>
            <c:if test="${listCarts.size()>0}">
                <a href="view-cart" class="fas fa-shopping-cart"></a>
                <span class="bg-warning" style="padding:0.2rem;border-radius:3rem;margin-left:-1rem;position:fixed; top:18px; ">${listCarts.size()}</span>
            </c:if> 
            <a id="account-btn" class="fas fa-user"></a>
        </nav>
    </div>

    <!-- account -->
    <nav class="account-menu bg-dark">
        <c:if test="${currentLogin.name != null||account != null}">           
            <a href="profile?viewAccount=true" class="text-light"> <i class="far fa-user-circle"></i> ${sessionScope.currentLogin.name}</a>
        </c:if> 
        <c:if test="${currentLogin.name == null&&currentLogin!=null}">           
            <a href="profile?viewAccount=true" class="text-light"> <i class="far fa-user-circle"></i> ${sessionScope.currentLogin.username}</a>
        </c:if> 
        <c:if test="${currentLogin == null}">
            <a href="login" class="text-light"><i class="fas fa-sign-in-alt"></i> Đăng nhập</a>
        </c:if> 
        <a href="logout" class="text-light"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a>
    </nav>
</header>
