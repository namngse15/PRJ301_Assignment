<%-- 
    Document   : adminCategory
    Created on : Dec 10, 2021, 3:01:51 PM
    Author     : tenhik
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Categories</title>
        <link rel="icon" href="assets/favicon.ico" type="image/x-icon">
        <!--css file-->
        <link rel="stylesheet" href="assets/admin.css">
        <!-- boostrap -->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <!--datatable-->
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>
        <!-- font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>
    <jsp:useBean id="getCategory" scope="page" class="dal.CategoryDAO"></jsp:useBean>  
    <jsp:useBean id="getProduct" scope="page" class="dal.ProductDAO"></jsp:useBean>  
        <body>
        <jsp:include page="adminTemplate/adminHeader.jsp"/>
        <div class="header-block">
        </div>
        <jsp:include page="adminTemplate/adminSidemenu.jsp"/>
        <div class="content-wrapper">
            <div class="row" style="margin: 5rem 0;">
                <!--table brand--> 
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Brand</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-category?addCategory=true&cateType=brand"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable4">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Brand</th>
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
                                        <td>${getProduct.getCountTotalProductByBrand(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByBrand(o.id) == 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id},'brand')">Remove</a>
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
                <!--table price-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Price</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-category?addCategory=true&cateType=price"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable5">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Price</th>
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
                                        <td>${getProduct.getCountTotalProductByPrice(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByPrice(o.id)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id},'price')">Remove</a>
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
            </div>
            <div class="row" style="margin: 5rem 0;">
                <!--table category-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Category</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-category?addCategory=true&cateType=category"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable3">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Category</th>
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
                                        <td>${getProduct.getCountTotalProductByCate(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByCate(o.id)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id},'category')">Remove</a>
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
                <!--table display-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Display</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-category?addCategory=true&cateType=display"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable2">
                            <thead>
                                <tr>
                                    <th scope="col">Display Size</th>
                                    <th>Items</th>
                                    <th>Remove(0 item)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${getCategory.allDisplaySize}" var="o">  
                                    <tr>
                                        <td>${o.size} Inch</td>
                                        <td>${getProduct.getCountTotalProductByDisplay(o.size)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByDisplay(o.size)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.size},'display')">Remove</a>                                                
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
            </div>
            <div class="row" style="margin: 5rem 0;">
                <!--table hdd-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Hard Drive</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-category?addCategory=true&cateType=hdd"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable1">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">HDD size</th>
                                    <th>Items</th>
                                    <th>Remove(0 item)</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${getCategory.allHdd}" var="o">  
                                    <tr>
                                        <th scope="row">${o.id}</th>
                                        <td>${o.name}</td>
                                        <td>${getProduct.getCountTotalProductByHdd(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByHdd(o.id)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id},'hdd')">Remove</a>
                                            </c:if>
                                        </td>
                                        <td><a href="admin-category?editCategory=true&hddId=${o.id}">Edit</a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Number of Hard Drive: ${getCategory.countHdd} </h5>
                            <p class="card-text">Date: ${localDate}</p>
                        </div>
                    </div> 
                </div>
                <!--table ram-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's RAM</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-category?addCategory=true&cateType=ram"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable1">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Ram size</th>
                                    <th>Items</th>
                                    <th>Remove(0 item)</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${getCategory.allRam}" var="o">  
                                    <tr>
                                        <th scope="row">${o.id}</th>
                                        <td>${o.name}</td>
                                        <td>${getProduct.getCountTotalProductByRam(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByRam(o.id)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id},'ram')">Remove</a>
                                            </c:if>
                                        </td>
                                        <td><a href="admin-category?editCategory=true&ramId=${o.id}">Edit</a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Number of ram: ${getCategory.countRam} </h5>
                            <p class="card-text">Date: ${localDate}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin: 5rem 0;">
                <!--table gpu-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's GPU</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-category?addCategory=true&cateType=gpu"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable1">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">GPU</th>
                                    <th>Items</th>
                                    <th>Remove(0 item)</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${getCategory.allGpu}" var="o">  
                                    <tr>
                                        <th scope="row">${o.id}</th>
                                        <td>${o.name}</td>
                                        <td>${getProduct.getCountTotalProductByGpu(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByGpu(o.id)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id},'gpu')">Remove</a>
                                            </c:if>
                                        </td>
                                        <td><a href="admin-category?editCategory=true&gpuId=${o.id}">Edit</a></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Number of GPU: ${getCategory.countGpu} </h5>
                            <p class="card-text">Date: ${localDate}</p>
                        </div>
                    </div>
                </div>
                <!--table processor-->
                <div class="col-sm-6 mb-3">
                    <div class="title" style="display:flex;margin-left:1rem;">
                        <h2>Product's Processor</h2>
                        <a style="margin-left: 1rem;margin-top:2.2rem;" href="admin-category?addCategory=true&cateType=processor"><i class=" fa-2x fas fa-plus"></i></a>
                    </div>
                    <div class="table-responsive-lg">
                        <table class="table table-striped table-hover" id="dataTable1">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Processor</th>
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
                                        <td>${getProduct.getCountTotalProductByProcessor(o.id)}</td>
                                        <td>
                                            <c:if test="${getProduct.getCountTotalProductByProcessor(o.id)== 0}"> 
                                                <a href="javascript:;" onclick="removeCate(${o.id},'processor')">Remove</a>
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
            </div>
        </div>
        <!-- footer -->
        <jsp:include page="adminTemplate/adminFooter.jsp"/>
        <!--modal add category-->
        <c:if test="${viewAddCategory!= null}">
        <div class="modal-nofi" id="modal-add-category">
            <div class="modal-nofi-overlay"></div>
            <div class="modal-add modal-dialog-scrollable">
                <form action="admin-cate-crud" method="get">
                    <h5 class="modal-add-title">Add ${addType}</h5>
                    <input hidden name="add" value="true">
                    <input hidden name="addType" value="${addType}">
                    <div class="modal-add-full mb-3">
                        <label class="text-capitalize">${addType}:</label>
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
        </c:if>
        <!--modal edit category-->      
        <c:if test="${viewEditCategory!= null}">            
            <div class="modal-nofi" id="modal-edit-category">
                <div class="modal-nofi-overlay"></div>
                <div class="modal-add modal-dialog-scrollable">
                    <form action="admin-cate-crud" method="post">
                        <h5 class="modal-add-title">Edit ${cate}</h5>
                        <input hidden name="edit" value="true">
                        <input hidden name="editType" value="${cate}">
                        <div class="modal-add-flex mb-3">
                            <label>ID:</label>
                            <input class="input-2" name="cateEditId" value="${id}" readonly>
                            <label>Category:</label>
                            <input class="input-7" name="cateEditName" type="text" placeholder="Enter name" value="${name}" required>
                        </div>
                        <!--button check out-->
                        <div class="modal-footer">
                            <button type="button" onclick="closeModal()" class="btn-close-modal" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn-save-modal">Edit</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <script>
            $(document).ready(function () {
                $('#dataTable1,#dataTable2,#dataTable3,#dataTable4,#dataTable5,#dataTable6').DataTable();
            });

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
