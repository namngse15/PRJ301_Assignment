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
        <title>Administrator's Products</title>
        <!--css file-->
        <link rel="stylesheet" href="assets/admin.css">
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
    <jsp:useBean id="getCategory" scope="page" class="dal.CategoryDAO"></jsp:useBean>  
    <jsp:useBean id="getProduct" scope="page" class="dal.ProductDAO"></jsp:useBean>  
    <jsp:useBean id="getReview" scope="page" class="dal.ReviewDAO"></jsp:useBean> 
    <jsp:useBean id="getNumber" scope="page" class="dal.OrdersDAO"></jsp:useBean> 
        <body>
        <jsp:include page="adminTemplate/adminHeader.jsp"/>
        <div class="header-block">
        </div>
        <jsp:include page="adminTemplate/adminSidemenu.jsp"/>
        <div class="content-wrapper">
            <!--table product-->
            <c:if test="${viewProductDetail == null&&viewProductReview == null}">
                <div class="title" style="display:flex;margin-left:1rem;">
                    <h2>Product's Informations</h2>
                    <a style="margin-left: 1rem;margin-top:2.2rem;" href="#" onclick="addProduct()"><i class=" fa-2x fas fa-plus"></i></a>
                </div>
                <div class="table-responsive-lg table-product">
                    <table class="table table-striped table-hover" id="dataTable">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Category</th>
                                <th scope="col">Brand</th>        
                                <th scope="col">Color</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Price</th>   
                                <th scope="col">Rate</th>
                                <th>Remove product(Order cancel/receive/No order)</th>
                                <th>Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listProducts}" var="o">  
                                <tr>
                                    <th scope="row">${o.id}</th>
                                    <td><a href="admin-product?viewProductDetail=true&productId=${o.id}&price=${o.price}&quantity=${o.quantity}">${o.name}</a></td>
                                    <td>${getCategory.getCategoryById(o.cateId)}</td>
                                    <td>${getCategory.getBrandById(o.brandId)}</td>
                                    <td>${o.color}</td>
                                    <td>${o.quantity}</td>
                                    <td><fmt:formatNumber value = "${o.price}" type = "currency"/> </td>
                                    <c:if test="${o.rate>0}">
                                        <td>${o.rate} <a href="admin-product?viewProductReview=true&productId=${o.id}">(View)</a></td>
                                    </c:if>
                                    <c:if test="${o.rate==0}">
                                        <td>${o.rate}</td>
                                    </c:if>
                                    <td>
                                        <c:if test="${getNumber.getTotalOrderByProductandStatus(o.id)==0}">
                                            <a href="#" onclick="removeProduct('${o.id}')">Remove</a>
                                        </c:if>
                                    </td>
                                    <td><a href="admin-product?editProduct=true&viewProductDetail=true&productId=${o.id}&price=${o.price}&quantity=${o.quantity}">Edit</a></td>
                                </tr>                              
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row" style="margin: 5rem 0;">
                    <div class="col-sm-4 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Number of product: ${countproduct}</h5>
                                <p class="card-text">Date: ${localDate}</p>
                            </div>
                        </div>
                    </div>
                </div>         
            </c:if>
            <c:if test="${viewProductReview != null}">
                <div class="title" style="display:flex;margin-left:1rem;">
                    <h2>Product's Review</h2>
                    <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-listProduct"><i class=" fa-2x fas fa-arrow-left"></i></a>
                </div>
                <div class="table-responsive-lg table-product">
                    <table class="table table-striped table-hover table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Rate</th>
                                <th scope="col">Message</th>
                                <th scope="col">Email</th>        
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${listReviews}" var="o">  
                                <tr>
                                    <th scope="row">${o.id}</th>
                                    <td>${o.rate}</td>                                   
                                    <td>${o.message}</td>
                                    <td>${o.email}</td>
                                    <td><a href="#" onclick="removeReiew()">Remove</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <!--end table product-->
            <!--table product detail-->
            <c:if test="${viewProductDetail != null}">
                <div class="title" style="display:flex;margin-left:1rem;">
                    <h2>Product's Informations</h2>
                    <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-listProduct"><i class=" fa-2x fas fa-arrow-left"></i></a>
                </div>
                <div class="table-responsive-lg table-product">
                    <table class="table table-striped table-hover table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Image</th>
                                <th scope="col">Display</th>        
                                <th scope="col">OS</th>
                                <th scope="col">CPU</th>
                                <th scope="col">GPU</th>   
                                <th scope="col">Ram</th>
                                <th scope="col">HDD</th>
                                <th scope="col">Port</th>
                                <th scope="col">Battery</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">${product.id}</th>
                                <td>${product.name}</td>                                   
                                <td>
                                    <c:forEach var="i" items="${product.listImage}">
                                        <img style="width:80%" src="${i.img1}"/>
                                    </c:forEach>
                                </td>
                                <td>${product.display}</td>
                                <td>${product.os}</td>
                                <td>${product.processor}</td>
                                <td>${product.gpu}</td>
                                <td>${product.ram}</td>
                                <td>${product.harddrive}</td>
                                <td>${product.port}</td>
                                <td>${product.battery}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="row" style="margin: 5rem 0;">
                    <div class="col-sm-4 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Product price: <fmt:formatNumber value = "${price}" type = "currency"/> </h5>
                                <p class="card-text">Date: ${localDate}</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Product quantity: ${quantity} </h5>
                                <p class="card-text">Date: ${localDate}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <!-- end table product detail-->
            <div class="row" style="margin: 5rem 0;">
                <!--table brand--> 
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Brand</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="javascript:;" onclick="addCategory('brand')"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable4">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Brand</th>
                                    <th>View products</th>
                                    <th>Items</th>
                                    <th>Remove(0 item)</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${getCategory.allBrand}" var="o">  
                                    <tr>
                                        <th scope="row">${o.id}</th>
                                        <td>${o.name}</td>
                                        <td><a href="admin-product?viewProductByBrand=true&brandId=${o.id}">View Products</a></td>
                                        <td>${getProduct.getCountTotalProductByBrand(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByBrand(o.id) == 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id}, 0)">Remove</a>
                                            </c:if>
                                        </td>
                                        <td><a href="admin-category?editCategory=true&brandId=${o.id}">Edit</a></td>                                       
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Number of brand: ${getCategory.countBrand}</h5>
                            <p class="card-text">Date: ${localDate}</p>
                        </div>
                    </div>
                </div>
                <!--end table brand-->
                <!--table price-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Price</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="javascript:;" onclick="addCategory('price')"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable5">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Price</th>
                                    <th>View</th>
                                    <th>Items</th>
                                    <th>Remove(0 item)</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${getCategory.allPrice}" var="o">  
                                    <tr>
                                        <th scope="row">${o.id}</th>
                                        <td>${o.amount}</td>
                                        <td><a href="admin-product?viewProductByPrice=true&priceId=${o.id}">View</a></td>
                                        <td>${getProduct.getCountTotalProductByPrice(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByPrice(o.id)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id}, 1)">Remove</a>
                                            </c:if>
                                        </td>
                                        <td><a href="admin-category?editCategory=true&priceId=${o.id}">Edit</a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Number of type: ${getCategory.countPrice}</h5>
                            <p class="card-text">Date: ${localDate}</p>
                        </div>
                    </div>
                </div>                      
                <!--end table price-->   
                <!--view table product by brand and price-->
                <!--product by brand-->
                <c:if test="${viewProductBrand != null}"> 
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>List Products by Brand: ${countProductByCate} items</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-listProduct"><i class="fa-2x fas fa-times"></i></i></a>
                    </div>
                    <div class="col-sm-12 mb-3">
                        <div class="table-responsive-lg">
                            <table class="table table-striped table-hover" id="dataTable6">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Display</th>        
                                        <th scope="col">OS</th>
                                        <th scope="col">CPU</th>
                                        <th scope="col">GPU</th>   
                                        <th scope="col">Ram</th>
                                        <th scope="col">HDD</th>
                                        <th scope="col">Port</th>
                                        <th scope="col">Battery</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${listProductByCate}">
                                        <tr>
                                            <th scope="row">${o.id}</th>
                                            <td>${o.name}</td>                                   
                                            <td>
                                                <c:forEach var="i" items="${o.listImage}">
                                                    <img style="width:80%" src="${i.img1}"/>
                                                </c:forEach>
                                            </td>
                                            <td>${o.display}</td>
                                            <td>${o.os}</td>
                                            <td>${o.processDetail}</td>
                                            <td>${o.gpu}</td>
                                            <td>${o.ram}</td>
                                            <td>${o.harddrive}</td>
                                            <td>${o.port}</td>
                                            <td>${o.battery}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
                <!--product by price-->
                <c:if test="${viewProductPrice != null}"> 
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>List Products by Price: ${countProductByCate} items</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-listProduct"><i class="fa-2x fas fa-times"></i></i></a>
                    </div>
                    <div class="col-sm-12 mb-3">
                        <div class="table-responsive-lg">
                            <table class="table table-striped table-hover" id="dataTable6">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Display</th>        
                                        <th scope="col">OS</th>
                                        <th scope="col">CPU</th>
                                        <th scope="col">GPU</th>   
                                        <th scope="col">Ram</th>
                                        <th scope="col">HDD</th>
                                        <th scope="col">Port</th>
                                        <th scope="col">Battery</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${listProductByCate}"> 
                                        <tr>
                                            <th scope="row">${o.id}</th>
                                            <td>${o.name}</td>                                   
                                            <td>
                                                <c:forEach var="i" items="${o.listImage}">
                                                    <img style="width:80%" src="${i.img1}"/>
                                                </c:forEach>
                                            </td>
                                            <td>${o.display}</td>
                                            <td>${o.os}</td>
                                            <td>${o.processDetail}</td>
                                            <td>${o.gpu}</td>
                                            <td>${o.ram}</td>
                                            <td>${o.harddrive}</td>
                                            <td>${o.port}</td>
                                            <td>${o.battery}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
                <!--end view table product by brand and price-->
            </div>

            <div class="row" style="margin: 5rem 0;">
                <!--table category-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Category</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="javascript:;" onclick="addCategory('category')"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable3">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Category</th>
                                    <th>View products</th>
                                    <th>Items</th>
                                    <th>Remove (0 item)</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${getCategory.allCategory}" var="o">  
                                    <tr>
                                        <th scope="row">${o.id}</th>
                                        <td>${o.name}</td>
                                        <td><a href="admin-product?viewProductByCate=true&cateId=${o.id}">View Products</a></td>
                                        <td>${getProduct.getCountTotalProductByCate(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByCate(o.id)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id}, 2)">Remove</a>
                                            </c:if>
                                        </td>
                                        <td><a href="admin-category?editCategory=true&cateId=${o.id}">Edit</a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div> 
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Number of category: ${getCategory.countCategory}</h5>
                            <p class="card-text">Date: ${localDate}</p>
                        </div>
                    </div>
                </div>
                <!--end table category-->
                <!--table display-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Display</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="javascript:;" onclick="addCategory('display')"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable2">
                            <thead>
                                <tr>
                                    <th scope="col">Display Size</th>
                                    <th>View products</th>
                                    <th>Items</th>
                                    <th>Remove(0 item)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${getCategory.allDisplaySize}" var="o">  
                                    <tr>
                                        <td>${o.size} Inch</td>
                                        <td><a href="admin-product?viewProductByDisplay=true&displaySize=${o.size}">View Products</a></td>
                                        <td>${getProduct.getCountTotalProductByDisplay(o.size)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByDisplay(o.size)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.size}, 3)">Remove</a>                                                
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Number of type: ${getCategory.countDisplay} </h5>
                            <p class="card-text">Date: ${localDate}</p>
                        </div>
                    </div>
                </div>
                <!--end table display-->
                <!--table product by cate and display-->
                <!--product by cate-->
                <c:if test="${viewProductCate != null}"> 
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>List Products by Category: ${countProductByCate} items</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-listProduct"><i class="fa-2x fas fa-times"></i></i></a>
                    </div>
                    <div class="col-sm-12 mb-3">
                        <div class="table-responsive-lg">
                            <table class="table table-striped table-hover" id="dataTable6">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Display</th>        
                                        <th scope="col">OS</th>
                                        <th scope="col">CPU</th>
                                        <th scope="col">GPU</th>   
                                        <th scope="col">Ram</th>
                                        <th scope="col">HDD</th>
                                        <th scope="col">Port</th>
                                        <th scope="col">Battery</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${listProductByCate}"> 
                                        <tr>
                                            <th scope="row">${o.id}</th>
                                            <td>${o.name}</td>                                   
                                            <td>
                                                <c:forEach var="i" items="${o.listImage}">
                                                    <img style="width:80%" src="${i.img1}"/>
                                                </c:forEach>
                                            </td>
                                            <td>${o.display}</td>
                                            <td>${o.os}</td>
                                            <td>${o.processDetail}</td>
                                            <td>${o.gpu}</td>
                                            <td>${o.ram}</td>
                                            <td>${o.harddrive}</td>
                                            <td>${o.port}</td>
                                            <td>${o.battery}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
                <!--product by display-->
                <c:if test="${viewProductDisplay != null}"> 
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>List Products by Display Size: ${countProductByCate} items</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-listProduct"><i class="fa-2x fas fa-times"></i></i></a>
                    </div>
                    <div class="col-sm-12 mb-3">
                        <div class="table-responsive-lg">
                            <table class="table table-striped table-hover" id="dataTable6">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Display</th>        
                                        <th scope="col">OS</th>
                                        <th scope="col">CPU</th>
                                        <th scope="col">GPU</th>   
                                        <th scope="col">Ram</th>
                                        <th scope="col">HDD</th>
                                        <th scope="col">Port</th>
                                        <th scope="col">Battery</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${listProductByCate}"> 
                                        <tr>
                                            <th scope="row">${o.id}</th>
                                            <td>${o.name}</td>                                   
                                            <td>
                                                <c:forEach var="i" items="${o.listImage}">
                                                    <img style="width:80%" src="${i.img1}"/>
                                                </c:forEach>
                                            </td>
                                            <td>${o.display}</td>
                                            <td>${o.os}</td>
                                            <td>${o.processDetail}</td>
                                            <td>${o.gpu}</td>
                                            <td>${o.ram}</td>
                                            <td>${o.harddrive}</td>
                                            <td>${o.port}</td>
                                            <td>${o.battery}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="row" style="margin: 5rem 0;">
                <!--table processor-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Processor</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="javascript:;" onclick="addCategory('processor')"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable1">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Processor</th>
                                    <th>View products</th>
                                    <th>Items</th>
                                    <th>Remove(0 item)</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${getCategory.allProcessor}" var="o">  
                                    <tr>
                                        <th scope="row">${o.id}</th>
                                        <td>${o.name}</td>
                                        <td><a href="admin-product?viewProductByProcess=true&processId=${o.id}">View Products</a></td>
                                        <td>${getProduct.getCountTotalProductByProcessor(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByProcessor(o.id)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id}, 4)">Remove</a>
                                            </c:if>
                                        </td>
                                        <td><a href="admin-category?editCategory=true&processorId=${o.id}">Edit</a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Number of processor: ${getCategory.countProcessor} </h5>
                            <p class="card-text">Date: ${localDate}</p>
                        </div>
                    </div>
                </div>
                <!--end table processor-->
                <!--table product by processor-->
                <c:if test="${viewProductProcess != null}"> 

                    <div class="col-sm-12 mb-3">
                        <div class="title" style="display:flex;margin-left:1rem;">
                            <h2>List Products by Processor: ${countProductByCate} items</h2>
                            <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-listProduct"><i class="fa-2x fas fa-times"></i></i></a>
                        </div>
                        <div class="table-responsive-lg">
                            <table class="table table-striped table-hover" id="dataTable6">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Display</th>        
                                        <th scope="col">OS</th>
                                        <th scope="col">CPU</th>
                                        <th scope="col">GPU</th>   
                                        <th scope="col">Ram</th>
                                        <th scope="col">HDD</th>
                                        <th scope="col">Port</th>
                                        <th scope="col">Battery</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${listProductByCate}"> 
                                        <tr>
                                            <th scope="row">${o.id}</th>
                                            <td>${o.name}</td>                                   
                                            <td>
                                                <c:forEach var="i" items="${o.listImage}">
                                                    <img style="width:80%" src="${i.img1}"/>
                                                </c:forEach>
                                            </td>
                                            <td>${o.display}</td>
                                            <td>${o.os}</td>
                                            <td>${o.processDetail}</td>
                                            <td>${o.gpu}</td>
                                            <td>${o.ram}</td>
                                            <td>${o.harddrive}</td>
                                            <td>${o.port}</td>
                                            <td>${o.battery}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
                <!--end table product by processor-->
            </div>
            <!-- footer -->
            <jsp:include page="adminTemplate/adminFooter.jsp"/>
        </div> 
        <!--modal add product-->                
        <div class="modal-nofi" id="modal-product">
            <div class="modal-nofi-overlay"></div>
            <div class="modal-add modal-dialog-scrollable">
                <form action="admin-crud" method="post">
                    <h5 class="modal-add-title">Add product</h5>
                    <div class="modal-add-full border-bottom">
                    </div>
                    <div class="modal-checkout-body mt-3">
                        <input name="add" value="true" hidden>
                        <div class="modal-upper">
                            <div class="modal-add-full mt-3 mb-3">
                                <label>Image 1</label>                              
                                <input  class="mb-3" name="productImg1" type="url" placeholder="https://example.com/users/" required>
                                <label>Image 2</label>
                                <input  name="productImg2" type="url" placeholder="https://example.com/users/">
                            </div>
                            <div class="modal-add-full border-bottom">
                            </div>
                            <div class="modal-add-flex mt-3">
                                <label>Product Info:</label>
                            </div>
                            <div class="modal-add-flex ">
                                <label>ID:</label>
                                <input class="" name="productId" type="text" placeholder="Enter product id" required>
                                <label>Name:</label>
                                <input class="" name="productName" type="text" placeholder="Enter product name" required>
                            </div>
                            <div class="modal-add-flex">
                                <label style="margin-left:1.4rem">Brand:</label>
                                <select name="brandId" class="choose-big" required>
                                    <option>Choose</option>
                                    <c:forEach items="${getCategory.allBrand}" var="o">  
                                        <option value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                                <label>Category:</label>
                                <select name="cateId" class="choose-mid" required>
                                    <option>Choose</option>
                                    <c:forEach items="${getCategory.allCategory}" var="o">  
                                        <option value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="modal-add-flex">
                                <label>Price:</label>
                                <input class="input-3" name="productPrice" type="number" placeholder="Enter price" required>
                                <label>Quantity:</label>
                                <input class="input-2" name="productQuan" type="text" placeholder="Enter quantity" required>
                                <label>OS:</label>
                                <input class="input-2" name="productOs" type="text" placeholder="Enter os" required>
                            </div>
                            <div class="modal-add-flex">
                                <label style="margin-left:1.4rem">Amount:</label>
                                <select name="priceId" class="choose-mid-2" required>
                                    <option>Choose</option>
                                    <c:forEach items="${getCategory.allPrice}" var="o">  
                                        <option value="${o.id}">${o.amount}</option>
                                    </c:forEach>
                                </select>
                                <label>OS:</label>
                                <select name="osId" class="choose-big" required>
                                    <option>Choose</option>
                                    <c:forEach items="${getCategory.allOs}" var="o">  
                                        <option value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="modal-add-half mt-3 mb-3">
                                <label>Port:</label>
                                <input  name="productPort" type="text" placeholder="Enter product port" required>
                            </div>
                            <div class="modal-add-full border-bottom">
                            </div>
                            <div class="modal-add-flex">
                                <label style="margin-left:1.4rem">Display size:</label>
                                <select  name="displaySize" required>
                                    <option>Choose</option>
                                    <c:forEach items="${getCategory.allDisplaySize}" var="o">  
                                        <option value="${o.size}">${o.size}</option>
                                    </c:forEach>
                                </select>
                                <label>Processor(CPU):</label>
                                <select name="processorId" class="choose-big" required>
                                    <option>Choose</option>
                                    <c:forEach items="${getCategory.allProcessor}" var="o">  
                                        <option value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="modal-add-flex mb-1">
                                <label>Display:</label>
                                <input class="input-2" name="productDisplay" type="text" placeholder="Enter display detail" required>
                                <label>CPU:</label>
                                <input class="input-7" name="productCpu" type="text" placeholder="Enter processor detail" required>
                            </div>
                            <div class="modal-add-flex">
                                <label style="margin-left:1.4rem">RAM:</label>
                                <select name="ramId" required>
                                    <option>Ram</option>
                                    <c:forEach items="${getCategory.allRam}" var="o">  
                                        <option value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                                <label>GPU:</label>
                                <select name="gpuId" required>
                                    <option>Gpu</option>
                                    <c:forEach items="${getCategory.allGpu}" var="o">  
                                        <option value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                                <label>HDD:</label>
                                <select name="hddId" class="choose-mid-2" required>
                                    <option>Hdd</option>
                                    <c:forEach items="${getCategory.allHdd}" var="o">  
                                        <option value="${o.id}">${o.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="modal-add-flex mb-3">
                                <label>RAM:</label>
                                <input class="input-2" name="productRam" type="text" placeholder="Enter ram detail"required>
                                <label>GPU:</label>
                                <input class="input-8" name="productGpu" type="text" placeholder="Enter gpu detail" required>
                            </div>
                            <div class="modal-add-flex mb-3">
                                <label>HDD:</label>
                                <input class="input-3" name="productHd" type="text" placeholder="Enter hard drive" required>
                                <label>Battery:</label>
                                <input class="input-1" name="productBattery" type="text" placeholder="Enter battery" required>
                                <label>Color:</label>
                                <input class="input-3" name="productColor" type="text" placeholder="Enter color" required>
                            </div>
                            <div class="modal-add-half mt-3 mb-3">
                                <label>Notes:</label>
                                <input  name="note" type="text" placeholder="Note something here">
                            </div>
                        </div>
                    </div>
                    <!--button check out-->
                    <div class="modal-footer">
                        <button type="button" onclick="closeModal()" class="btn-close-modal" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn-save-modal">Add</button>
                    </div>
                </form>
            </div>
        </div>
        <!--modal edit product-->
        <c:if test="${viewEditProduct == true}">
            <div class="modal-nofi" id="modal-edit-product">
                <div class="modal-nofi-overlay"></div>
                <div class="modal-add modal-dialog-scrollable">
                    <form action="admin-crud" method="post">
                        <h5 class="modal-add-title">Update product</h5>
                        <div class="modal-add-full border-bottom">
                        </div>
                        <div class="modal-checkout-body mt-3">
                            <input name="edit" value="true" hidden>
                            <div class="modal-upper">
                                <div class="modal-add-full mt-3 mb-3">
                                    <c:forEach items="${product.listImage}" var="i">
                                        <label>Image 1</label>                              
                                        <input  class="mb-3" name="productImg1" value="${i.img1}" type="url" placeholder="https://example.com/users/" required>
                                        <label>Image 2</label>
                                        <input  name="productImg2" type="url" value="${i.img2}" placeholder="https://example.com/users/">
                                    </c:forEach>
                                </div>
                                <div class="modal-add-full border-bottom">
                                </div>
                                <div class="modal-add-flex mt-3">
                                    <label>Product Info:</label>
                                </div>
                                <div class="modal-add-flex ">
                                    <label>ID:</label>
                                    <input class="" name="productId" type="text" value="${product.id}" placeholder="Enter product id" required>
                                    <label>Name:</label>
                                    <input class="" name="productName" type="text" value="${product.name}" placeholder="Enter product name" required>
                                </div>
                                <div class="modal-add-flex">
                                    <label style="margin-left:1.4rem">Brand:</label>
                                    <select name="brandId" class="choose-big" required>
                                        <option value="${product.brandId}">${getCategory.getBrandById(product.brandId)}</option>
                                        <c:forEach items="${getCategory.allBrand}" var="o">  
                                            <c:if test="${product.brandId != o.id}">
                                                <option value="${o.id}">${o.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <label>Category:</label>
                                    <select name="cateId" class="choose-mid" required>
                                        <option value="${product.cateId}">${getCategory.getCategoryById(product.cateId)}</option>
                                        <c:forEach items="${getCategory.allCategory}" var="o"> 
                                            <c:if test="${product.cateId != o.id}">
                                                <option value="${o.id}">${o.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="modal-add-flex">
                                    <label>Price:</label>
                                    <input class="input-3" name="productPrice" type="number" value="${product.price}" placeholder="Enter price" required>
                                    <label>Quantity:</label>
                                    <input class="input-2" name="productQuan" type="text" value="${product.quantity}" placeholder="Enter quantity" required>
                                    <label>OS:</label>
                                    <input class="input-2" name="productOs" type="text" value="${product.os}" placeholder="Enter os" required>
                                </div>
                                <div class="modal-add-flex">
                                    <label style="margin-left:1.4rem">Amount:</label>
                                    <select name="priceId" class="choose-mid-2" required>
                                        <option value="${product.priceId}">${getCategory.getPriceById(product.priceId)}</option>
                                        <c:forEach items="${getCategory.allPrice}" var="o">  
                                            <c:if test="${product.priceId != o.id}">
                                                <option value="${o.id}">${o.amount}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <label>OS:</label>
                                    <select name="osId" class="choose-big" required>
                                        <option value="${product.osId}">${getCategory.getOsById(product.osId)}</option>
                                        <c:forEach items="${getCategory.allOs}" var="o">  
                                            <c:if test="${product.osId != o.id}">
                                                <option value="${o.id}">${o.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="modal-add-half mt-3 mb-3">
                                    <label>Port:</label>
                                    <input  name="productPort" type="text" value="${product.port}" placeholder="Enter product port" required>
                                </div>
                                <div class="modal-add-full border-bottom">
                                </div>
                                <div class="modal-add-flex">
                                    <label style="margin-left:1.4rem">Display size:</label>
                                    <select  name="displaySize" required>
                                        <option value="${product.displayId}">${product.displayId}</option>
                                        <c:forEach items="${getCategory.allDisplaySize}" var="o">  
                                            <c:if test="${product.displayId != o.size}">
                                                <option value="${o.size}">${o.size}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <label>Processor(CPU):</label>
                                    <select name="processorId" class="choose-big" required>
                                        <option value="${product.processorId}">${getCategory.getProcessorById(product.processorId)}</option>
                                        <c:forEach items="${getCategory.allProcessor}" var="o">  
                                            <c:if test="${product.processorId != o.id}">
                                                <option value="${o.id}">${o.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="modal-add-flex mb-1">
                                    <label>Display:</label>
                                    <input class="input-2" name="productDisplay" type="text" value="${product.display}" placeholder="Enter display detail" required>
                                    <label>CPU:</label>
                                    <input class="input-7" name="productCpu" type="text" value="${product.processor}" placeholder="Enter processor detail" required>
                                </div>
                                <div class="modal-add-flex">
                                    <label style="margin-left:1.4rem">RAM:</label>
                                    <select name="ramId" required>
                                        <option value="${product.ramId}">${getCategory.getRamById(product.ramId)}</option>
                                        <c:forEach items="${getCategory.allRam}" var="o">  
                                            <c:if test="${product.ramId != o.id}">
                                                <option value="${o.id}">${o.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <label>GPU:</label>
                                    <select name="gpuId" class="choose-mid" required>
                                        <option value="${product.gpuId}">${getCategory.getGpuById(product.gpuId)}</option>
                                        <c:forEach items="${getCategory.allGpu}" var="o">  
                                            <c:if test="${product.gpuId != o.id}">
                                                <option value="${o.id}">${o.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <label>HDD:</label>
                                    <select name="hddId" required>
                                        <option value="${product.harddriveId}">${getCategory.getHddById(product.harddriveId)}</option>
                                        <c:forEach items="${getCategory.allHdd}" var="o">  
                                            <c:if test="${product.harddriveId != o.id}">
                                                <option value="${o.id}">${o.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="modal-add-flex mb-3">
                                    <label>RAM:</label>
                                    <input class="input-2" name="productRam" type="text" value="${product.ram}" placeholder="Enter ram detail"required>
                                    <label>GPU:</label>
                                    <input class="input-8" name="productGpu" type="text" value="${product.gpu}" placeholder="Enter gpu detail" required>
                                </div>
                                <div class="modal-add-flex mb-3">
                                    <label>HDD:</label>
                                    <input class="input-3" name="productHd" type="text" value="${product.harddrive}" placeholder="Enter hard drive" required>
                                    <label>Battery:</label>
                                    <input class="input-1" name="productBattery" type="text" value="${product.battery}" placeholder="Enter battery" required>
                                    <label>Color:</label>
                                    <input class="input-3" name="productColor" type="text" value="${product.color}" placeholder="Enter color" required>
                                </div>
                                <div class="modal-add-half mt-3 mb-3">
                                    <label>Notes:</label>
                                    <input  name="note" type="text" placeholder="Note something here">
                                </div>
                            </div>
                        </div>
                        <!--button check out-->
                        <div class="modal-footer">
                            <button type="button" onclick="closeModal()" class="btn-close-modal" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn-save-modal">Update</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <!--modal add category-->
        <!--brand-->
        <div class="modal-nofi" id="modal-brand">
            <div class="modal-nofi-overlay"></div>
            <div class="modal-add modal-dialog-scrollable">
                <form action="admin-cate-crud" method="post">
                    <h5 class="modal-add-title">Add brand</h5>
                    <input hidden name="add" value="true">
                    <input hidden name="category" value="0">
                    <div class="modal-add-full mb-3">
                        <label>Brand:</label>
                        <input class="input-8" name="cateName" type="text" placeholder="Enter name" required>
                    </div>
                    <!--button check out-->
                    <div class="modal-footer">
                        <button type="button" onclick="closeModal()" class="btn-close-modal" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn-save-modal">Add</button>
                    </div>
                </form>
            </div>
        </div>
        <!--price-->
        <div class="modal-nofi" id="modal-amount">
            <div class="modal-nofi-overlay"></div>
            <div class="modal-add modal-dialog-scrollable">
                <form action="admin-cate-crud" method="post">
                    <h5 class="modal-add-title">Add amount</h5>
                    <input hidden name="add" value="true">
                    <input hidden name="category" value="1">
                    <div class="modal-add-full mb-3">
                        <label>Amount:</label>
                        <input class="input-8" name="cateName" type="text" placeholder="Enter name" required>
                    </div>
                    <!--button check out-->
                    <div class="modal-footer">
                        <button type="button" onclick="closeModal()" class="btn-close-modal" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn-save-modal">Add</button>
                    </div>
                </form>
            </div>
        </div>
        <!--display-->
        <div class="modal-nofi" id="modal-display">
            <div class="modal-nofi-overlay"></div>
            <div class="modal-add modal-dialog-scrollable">
                <form action="admin-cate-crud" method="post">
                    <h5 class="modal-add-title">Add display size</h5>
                    <input hidden name="add" value="true">
                    <input hidden name="category" value="2">
                    <div class="modal-add-full mb-3">
                        <label>Display:</label>
                        <input class="input-8" name="display" type="number" placeholder="Enter name" required>
                    </div>
                    <!--button check out-->
                    <div class="modal-footer">
                        <button type="button" onclick="closeModal()" class="btn-close-modal" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn-save-modal">Add</button>
                    </div>
                </form>
            </div>
        </div>
        <!--category-->
        <div class="modal-nofi" id="modal-category">
            <div class="modal-nofi-overlay"></div>
            <div class="modal-add modal-dialog-scrollable">
                <form action="admin-cate-crud" method="post">
                    <h5 class="modal-add-title">Add category</h5>
                    <input hidden name="add" value="true">
                    <input hidden name="category" value="3">
                    <div class="modal-add-full mb-3">
                        <label>Category:</label>
                        <input class="input-8" name="cateName" type="text" placeholder="Enter name" required>
                    </div>
                    <!--button check out-->
                    <div class="modal-footer">
                        <button type="button" onclick="closeModal()" class="btn-close-modal" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn-save-modal">Add</button>
                    </div>
                </form>
            </div>
        </div>
        <!--processor-->
        <div class="modal-nofi" id="modal-processor">
            <div class="modal-nofi-overlay"></div>
            <div class="modal-add modal-dialog-scrollable">
                <form action="admin-cate-crud" method="post">
                    <h5 class="modal-add-title">Add processor</h5>
                    <input hidden name="add" value="true">
                    <input hidden name="category" value="4">
                    <div class="modal-add-full mb-3">
                        <label>Processor:</label>
                        <input class="input-8" name="cateName" type="text" placeholder="Enter name" required>
                    </div>
                    <!--button check out-->
                    <div class="modal-footer">
                        <button type="button" onclick="closeModal()" class="btn-close-modal" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn-save-modal">Add</button>
                    </div>
                </form>
            </div>
        </div>
        <!--modal edit category-->      
        <c:if test="${viewEditCategory!= null}">            
            <div class="modal-nofi" id="modal-edit-category">
                <div class="modal-nofi-overlay"></div>
                <div class="modal-add modal-dialog-scrollable">
                    <form action="admin-cate-crud" method="post">
                        <h5 class="modal-add-title">Edit category</h5>
                        <input hidden name="edit" value="true">
                        <input hidden name="editType" value="${cate}">
                        <div class="modal-add-flex mb-3">
                            <label>ID:</label>
                            <input class="input-2" name="cateId" type="number" placeholder="Enter id" value="${id}" required>
                            <label>Category:</label>
                            <input class="input-7" name="cateName" type="text" placeholder="Enter name" value="${name}" required>
                        </div>
                        <!--button check out-->
                        <div class="modal-footer">
                            <button type="button" onclick="closeModal()" class="btn-close-modal" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn-save-modal">Add</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <script>
            $(document).ready(function () {
                $('#dataTable,#dataTable1,#dataTable2,#dataTable3,#dataTable4,#dataTable5,#dataTable6').DataTable();
            });
            function removeProduct(productId) {
                var option = confirm('Are you sure to delete this product');
                if (option === true) {
                    window.location.href = 'admin-crud?removeProductById=' + productId;
                }
            }
            function addProduct() {
                document.getElementById("modal-product").style.display = "flex";
            }

            function addCategory(value) {
                if (value === 'category') {
                    document.getElementById("modal-category").style.display = "flex";
                } else if (value === 'brand') {
                    document.getElementById("modal-brand").style.display = "flex";
                } else if (value === 'price') {
                    document.getElementById("modal-amount").style.display = "flex";
                } else if (value === 'display') {
                    document.getElementById("modal-display").style.display = "flex";
                } else {
                    document.getElementById("modal-processor").style.display = "flex";
                }
            }
            function closeModal() {
                var x = document.querySelectorAll(".modal-nofi");
                for (var i = 0; i < x.length; i++) {
                    if (x[i].style.display !== "none") {
                        x[i].style.display = "none";
                    }
                }
            }
            function removeCate(cateId, value) {
                var option = confirm('Are you sure to delete this product');
                if (option === true) {
                    window.location.href = 'admin-cate-crud?removeCateById=' + cateId + '&removeType=' + value;
                }
            }
        </script>
        <script>
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