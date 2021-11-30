<%-- 
    Document   : category
    Created on : Oct 30, 2021, 11:11:13 AM
    Author     : tenhik
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="CategoryDAO" class="dal.CategoryDAO"></jsp:useBean>
    <div class="col-xl-3">
        <div class="row">
            <div class="col-12 col-xl-12 sidebar">
                <div class="sidebar-border">
                    <div class="sidebar-header text-uppercase font-weight-bold h4">
                        <i class="fas fa-list-alt fa-1x ml-1 mr-1"></i>
                        Categories
                    </div>
                </div>  
                <div class="sidebar-border">
                    <ul class="sidebar-list">
                        <li class='submenu'>
                            <div class="submenu-header"><span>Category</span></div>
                        <c:forEach items="${CategoryDAO.allCategory}" var="o">
                            <input type="radio" name="" value="${o.id}">
                            <label for="${o.id}">${o.name}</label>
                        </c:forEach>
                    </li> 
                    <!--                    <li class='sub-menu'>
                                            <button class="cateBtn"><span>Brand</span></button>
                                            <ul>
                    <c:forEach items="${CategoryDAO.allBrand}" var="o">
                        <li><a class="${brandId== o.id ? "acitve":""}" href='filterproduct?brandId=${o.id}'></i>${o.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </li>     
            <li class='sub-menu'>
                <button class="cateBtn"><span>Display Size</span></button>
                <ul>
                    <c:forEach items="${CategoryDAO.allDisplaySize}" var="o">  
                        <li><a href='filterproduct?displaySize=${o.size}'></i>${o.size}</a></li>
                    </c:forEach>
        </ul>
    </li>  
    <li class='sub-menu'>
        <button class="cateBtn"><span>Processor</span></button>
        <ul>
                    <c:forEach items="${CategoryDAO.allProcessor}" var="o">  
                        <li><a href='filterproduct?processId=${o.id}'></i>${o.name}</a></li>                               
                    </c:forEach>
        </ul>
    </li> 
    <li class='sub-menu'>
        <button class="cateBtn"><span>Price</span></button>
        <ul>
                    <c:forEach items="${CategoryDAO.allPrice}" var="o">  
                        <li><a href='filterproduct?priceId=${o.id}'></i>${o.amount}</a></li>
                    </c:forEach>
        </ul>
    </li> -->
                </ul>
            </div> 
            <div class="sidebar-footer"></div>
        </div>
    </div>
</div>