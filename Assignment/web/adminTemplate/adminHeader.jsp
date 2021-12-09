<%-- 
    Document   : adminHeader
    Created on : Nov 29, 2021, 11:28:01 PM
    Author     : tenhik
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="header">
    <!--logo-->
    <a href="admin" class="logo text-black h3 font-weight-bold"> <i class="fas fa-laptop"></i> laptop </a>
    <!--fucntion-icon-->
    <div class="search-border">
        <nav class="icons">
            <a href="home" class="fas fa-home" id="menu-btn"></a>
            <a id="account-btn" class="fas fa-user"></a>
        </nav>
    </div>

    <!-- account -->
    <nav class="account-menu bg-dark">
        <c:if test="${adminLogin.name != null||account != null}">           
            <a href="admin" class="text-light text-capitalize"> <i class="far fa-user-circle"></i> ${sessionScope.adminLogin.name} (Admin)</a>
        </c:if> 
        <c:if test="${adminLogin == null}">
            <a href="login" class="text-light"><i class="fas fa-sign-in-alt"></i> Log in</a>
        </c:if> 
        <a href="logout" class="text-light"><i class="fas fa-sign-out-alt"></i> Log Out</a>
    </nav>
</header>
