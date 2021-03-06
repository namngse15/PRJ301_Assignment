<%-- 
    Document   : productDetail
    Created on : Oct 31, 2021, 10:16:28 PM
    Author     : tenhik
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Product Detail</title>
        <link rel="icon" href="assets/favicon.ico" type="image/x-icon">
        <!--css file-->
        <link rel="stylesheet" href="assets/style.css">     
        <!-- boostrap -->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <!-- font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>
    <style>
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            display: none;
        }
    </style>
    <jsp:useBean id="userInfo" scope="page" class="dal.AccountDAO"></jsp:useBean>
    <jsp:useBean id="reviewInfo" scope="page" class="dal.ReviewDAO"></jsp:useBean>                  
        <body>
        <jsp:include page="template/header.jsp"/>
        <div class="header-block">
        </div>
        <div class="container">
            <!--product info-->
            <div class="heading-section">
                <h2>Chi tiết sản phẩm</h2>
            </div>
            <div class="row">
                <!--product image-->
                <div class="col-md-6 product-img">
                    <div class="product-img-border">
                        <div class="item-big">
                            <c:forEach var="i" items="${product.listImage}">
                                <img id="frame" src="${i.img1}" />
                            </c:forEach>  
                        </div>
                    </div>
                    <div id="thumb" class="owl-carousel product-thumb display-flex">
                        <c:forEach var="i" items="${product.listImage}">
                            <div class="item ">
                                <img src="${i.img1}" onclick="chooseImg(this)" />
                            </div>
                            <c:if test="${i.img2!=null}">
                                <div class="item">
                                    <img src="${i.img2}" onclick="chooseImg(this)" />
                                </div>
                            </c:if> 
                        </c:forEach>
                    </div>
                </div> 
                <!--end product image-->
                <!--product side form-->
                <div class="col-md-6">
                    <div class="product-detail">
                        <div class="product-info">
                            <div class="product-name">${product.name}</div>
                            <!--product review score-->
                            <div class="reviews-counter">
                                <c:set var = "average" scope = "page" 
                                       value = "${reviewInfo.getAverageProduct(product.id)}"/>
                                <div class="rate">
                                    <c:if test="${average>=5}"> 
                                        <input type="radio" id="star5" name="rate" value="5"/>
                                        <label for="star5" title="text">5 stars</label>
                                    </c:if>
                                    <c:if test="${average>=4}"> 
                                        <input type="radio" id="star4" name="rate" value="4"/>
                                        <label for="star4" title="text">4 stars</label>
                                    </c:if>
                                    <c:if test="${average>=3}"> 
                                        <input type="radio" id="star3" name="rate" value="3"/>
                                        <label for="star3" title="text">3 stars</label>
                                    </c:if>
                                    <c:if test="${average>=2}"> 
                                        <input type="radio" id="star2" name="rate" value="2"/>
                                        <label for="star2" title="text">2 stars</label>
                                    </c:if>
                                    <c:if test="${average>=1}"> 
                                        <input type="radio" id="star1" name="rate" value="1"/>
                                        <label for="star1" title="text">1 star</label>
                                    </c:if>
                                </div>
                                <span>${reviewInfo.getNumberReviewByProduct(product.id)} Phản hồi</span>
                            </div>
                            <!-- end product review score-->
                        </div>
                        <!--product price and color-->
                        <div class="product-price-discount"><span>Giá: <fmt:formatNumber value = "${product.price}" type = "currency"/></span></div>
                        <div class="row combobox">
                            <!--add to cart-->
                            <form action="add-to-cart" method="post">
                                <!--color-->
                                <div class="col-lg-7">
                                    <label for="color">Màu sản phẩm</label>
                                    <div class="product-choose"> 
                                        <select id="color" name="color" class="form-control form-control-lg p-0 pl-3 text-uppercase">
                                            <c:forTokens items="${product.color}" delims="," var="o" varStatus="status">
                                                <c:if test="${status.index==0}">
                                                    <option class="text-uppercase" value="${o}">${o}</option>
                                                </c:if>
                                                <c:if test="${status.index==1}">
                                                    <option class="text-uppercase" value="${o}">${o}</option>
                                                </c:if>
                                                <c:if test="${status.index==2}">
                                                    <option class="text-uppercase" value="${o}">${o}</option>
                                                </c:if>
                                            </c:forTokens>
                                        </select>
                                    </div> 
                                </div>
                                <!-- end color-->
                                <div class="col-lg-7"></div>
                                <!--quantity-->
                                <div class="product-count col-lg-6">
                                    <label for="size">Số lượng: </label>
                                    <c:if test="${edit==1}">
                                        <div class="product-choose"> 
                                            <div class="display-flex">
                                                <div class="qtyminus"style="pointer-events: none;">-</div>
                                                <input type="number" name="quantity" value="${quantity}" min="1" class="qty" disabled="disabled">
                                                <input type="text" name="productId" value="${product.id}" hidden>
                                                <div class="qtyplus" style="pointer-events: none;">+</div>
                                                <input type="text" name="edit" value="${edit}" hidden>
                                                <input type="text" name="colorEdit" value="${color}" hidden>
                                            </div>
                                        </div>
                                        <div class="product-choose-submit">
                                            <button type="submit" class="round-black-btn mb-5">Thay đổi màu</button>
                                        </div>
                                    </c:if>
                                    <c:if test="${edit!=1}"> 
                                        <div class="product-choose"> 
                                            <div class="display-flex">
                                                <div class="qtyminus">-</div>
                                                <input type="number" name="quantity" value="1" min="1" class="qty">
                                                <input type="text" name="productId" value="${product.id}" hidden>
                                                <div class="qtyplus">+</div>
                                            </div>
                                        </div>
                                        <c:if test="${product.quantity>0}">         
                                            <div class="product-choose-submit">
                                                <button type="submit" class="round-black-btn mb-5">Thêm hàng</button>
                                            </div>
                                        </c:if>
                                    </c:if>
                                </div>
                                <!-- end quantity-->
                            </form>
                            <!--end add to cart-->
                        </div>
                    </div>
                </div> 
                <!-- end product side form-->
                <!--product related-->
                <div class="col-md-12 mt-5">
                    <div class="row"> 
                        <c:forEach items="${listProductRelated}" var="o">
                            <c:if test="${product.id == o.id}">
                                <div class="col-lg-6 relate-product checked">
                                    <form action="detail" method="get">
                                        <input type="hidden" name="productId" value="${o.id}">
                                        <button type="submit"> 
                                            <div class="relate-product-img">
                                                <c:forEach items="${o.listImage}" var="i"> 
                                                    <img src="${i.img1}" alt="">
                                                </c:forEach>
                                            </div>
                                            <div class="relate-product-content">
                                                <div class="relate-des"><label>Name:</label><span>${o.name}</span></div>
                                                <div class="relate-des"><label>CPU:</label><span>${o.processor}</span></div>
                                                <div class="relate-des"><label>RAM:</label><span>${o.ram}</span></div>
                                                <div class="relate-des"><label>HDD:</label><span>${o.harddrive}</span></div>
                                            </div>
                                        </button>
                                    </form>
                                </div>
                            </c:if>
                            <c:if test="${product.id != o.id}">
                                <div class="col-lg-6 relate-product">
                                    <form action="detail" method="get">
                                        <input type="hidden" name="productId" value="${o.id}">
                                        <button type="submit"> 
                                            <div class="relate-product-img">
                                                <c:forEach items="${o.listImage}" var="i"> 
                                                    <img src="${i.img1}" alt="">
                                                </c:forEach>
                                            </div>
                                            <div class="relate-product-content">
                                                <div class="relate-des"><label>Name:</label><span>${o.name}</span></div>
                                                <div class="relate-des"><label>CPU:</label><span>${o.processor}</span></div>
                                                <div class="relate-des"><label>RAM:</label><span>${o.ram}</span></div>
                                                <div class="relate-des"><label>HDD:</label><span>${o.harddrive}</span></div>
                                            </div>
                                        </button>
                                    </form>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <!--end product related-->
                <!--product detai des and review-->
                <div class="col-md-12">
                    <div class="product-descrption">
                        <!--tablist-->
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link acitve" id="description-tab" data-toggle="tab" href="#description">Mô tả</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="review-tab" data-toggle="tab" href="#review">Phản hồi (${reviewInfo.getNumberReviewByProduct(product.id)})</a>
                            </li>
                        </ul>
                        <!--end tablist-->
                        <div class="tab-content" id="myTabContent">
                            <!--description-->
                                <div class="tab-pane fade in acitve" id="description">
                                    <h3>Mô tả chi tiết sản phẩm</h3>
                                    <ul>
                                        <li>Operating system: <span>${product.os}</span></li>
                                        <li>Display: <span>${product.display}</span></li>
                                        <li>Processor: <span>${product.processor}</span></li>
                                        <li>Ram: <span>${product.ram}</span></li>
                                        <li>Gpu: <span>${product.gpu}</span></li>
                                        <li>Hard drive: <span>${product.harddrive}</span></li>
                                        <li>Port: <span>${product.port}</span></li>
                                        <li>Battery: <span>${product.battery}</span></li>
                                    </ul>
                                </div>
                                <!--end description-->
                                <!--review-->
                                <div class="tab-pane fade" id="review">
                                    <div class="review-heading">PHẢN HỒI</div>
                                    <!--no review-->                                
                                    <c:if test="${reviewInfo.getNumberReviewByProduct(product.id) == 0}">
                                        <p class="mb-20">Chưa có phản hồi nào.</p>
                                    </c:if>
                                    <!--review > 0 --> 
                                    <c:if test="${reviewInfo.getNumberReviewByProduct(product.id) > 0}">
                                        <c:forEach var="o" items="${reviewInfo.getAllReviewByProductId(product.id)}">
                                            <div class="user-rating">
                                                <div class="rating-content">
                                                    <p class="text-capitalize">${userInfo.getAccountById(o.accountId).name}</p>

                                                    <!--user rating-->
                                                    <div class="rate">
                                                        <c:if test="${reviewInfo.getSumReviewByProduct(product.id,o.accountId)>=5}"> 
                                                            <input type="radio" id="star5" name="rate" value="5"/>
                                                            <label for="star5" title="text">5 stars</label>
                                                        </c:if>
                                                        <c:if test="${reviewInfo.getSumReviewByProduct(product.id,o.accountId)>=4}"> 

                                                            <input type="radio" id="star4" name="rate" value="4"/>
                                                            <label for="star4" title="text">4 stars</label>
                                                        </c:if>
                                                        <c:if test="${reviewInfo.getSumReviewByProduct(product.id,o.accountId)>=3}"> 

                                                            <input type="radio" id="star3" name="rate" value="3"/>
                                                            <label for="star3" title="text">3 stars</label>
                                                        </c:if>
                                                        <c:if test="${reviewInfo.getSumReviewByProduct(product.id,o.accountId)>=2}"> 

                                                            <input type="radio" id="star2" name="rate" value="2"/>
                                                            <label for="star2" title="text">2 stars</label>
                                                        </c:if>
                                                        <c:if test="${reviewInfo.getSumReviewByProduct(product.id,o.accountId)>=1}"> 

                                                            <input type="radio" id="star1" name="rate" value="1"/>
                                                            <label for="star1" title="text">1 star</label>
                                                        </c:if>
                                                    </div>
                                                    <!--end user rating-->
                                                    <!--user mess-->
                                                    <p>${o.message}</p>
                                                    <!--end user mess-->
                                                </div>  
                                                <!--user rating edit-->
                                                <div class="rating-edit">
                                                    <c:if test="${o.accountId == currentLogin.id}">
                                                        <c:if test="${checkUpdate==null}">    
                                                            <a href="editreview?id=${o.id}&productId=${product.id}"><i class="far fa-edit"></i></a> 
                                                            </c:if>
                                                            <c:if test="${checkUpdate==true}">    
                                                            <a href="editreview?id=${o.id}&productId=${product.id}"><i class="far fa-edit"></i>Edited</a> 
                                                        </c:if>
                                                        <a href="#" onclick="deleteReview(${o.id}, '${product.id}')"><i class="far fa-trash-alt"></i></a>
                                                        </c:if> 
                                                </div>
                                                <!--end user rating edit-->                                            
                                            </div>
                                        </c:forEach>  
                                    </c:if>
                                    <!--user review form-->
                                    <form class="review-form" action="review" method="post">
                                        <div class="form-group">
                                            <label>Đánh giá</label>
                                            <div class="reviews-counter">
                                                <div class="rating">
                                                    <label>
                                                        <input type="radio" name="stars" value="1" />
                                                        <span class="icon">★</span>
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="stars" value="2" />
                                                        <span class="icon">★</span>
                                                        <span class="icon">★</span>
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="stars" value="3" />
                                                        <span class="icon">★</span>
                                                        <span class="icon">★</span>
                                                        <span class="icon">★</span>   
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="stars" value="4" />
                                                        <span class="icon">★</span>
                                                        <span class="icon">★</span>
                                                        <span class="icon">★</span>
                                                        <span class="icon">★</span>
                                                    </label>
                                                    <label>
                                                        <input type="radio" name="stars" value="5" />
                                                        <span class="icon">★</span>
                                                        <span class="icon">★</span>
                                                        <span class="icon">★</span>
                                                        <span class="icon">★</span>
                                                        <span class="icon">★</span>
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group mt-5">
                                            <label>Đánh giá bằng lời</label>
                                            <input type="text" name="productId" value="${product.id}" hidden>
                                            <input type="text" name="id" value="${id}" hidden>
                                            <textarea name="reviewmess" class="form-control" rows="10"></textarea>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <input type="text" name="" class="form-control" placeholder="Name"
                                                           value="${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).name}" required>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <input type="text" name="" class="form-control" placeholder="Email" 
                                                           value="${userInfo.getAccountByUsernameAndPassword(currentLogin.username,currentLogin.password).email}" required>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="round-black-btn mb-5" type="submit">Lưu phản hồi</button>
                                    </form>
                                    <!--end user review form-->
                                </div>
                                <!--end review-->
                            </div>
                        </div>
                    </div>
                    <!-- end product detai des and review-->
                </div>
                <!--product info-->
            </div> 
            <!--modal change review-->
            <c:if test="${openModal == 1}">
                <div class="modal-nofi" id="modal-notifi">
                    <div class="modal-nofi-overlay"></div>
                    <div class="modal-edit-body">
                        <div class="modal-edit-header">
                            <h3>Thay đổi phản hồi</h3>
                        </div>
                        <div class="modal-edit-form">
                            <form class="review-form" action="editreview" method="post">
                                <div class="form-group">
                                    <label>Đánh giá</label>
                                    <div class="reviews-counter">
                                        <div class="rating">
                                            <label>
                                                <input type="radio" name="stars" value="1" />
                                                <span class="icon">★</span>
                                            </label>
                                            <label>
                                                <input type="radio" name="stars" value="2" />
                                                <span class="icon">★</span>
                                                <span class="icon">★</span>
                                            </label>
                                            <label>
                                                <input type="radio" name="stars" value="3" />
                                                <span class="icon">★</span>
                                                <span class="icon">★</span>
                                                <span class="icon">★</span>   
                                            </label>
                                            <label>
                                                <input type="radio" name="stars" value="4" />
                                                <span class="icon">★</span>
                                                <span class="icon">★</span>
                                                <span class="icon">★</span>
                                                <span class="icon">★</span>
                                            </label>
                                            <label>
                                                <input type="radio" name="stars" value="5" />
                                                <span class="icon">★</span>
                                                <span class="icon">★</span>
                                                <span class="icon">★</span>
                                                <span class="icon">★</span>
                                                <span class="icon">★</span>
                                            </label>
                                        </div>
                                    </div>
                                </div> 
                                <div class="form-group mt-5">
                                    <label>Đánh giá bằng lời</label>
                                    <input type="text" name="id" value="${id}" hidden>
                                    <input type="text" name="productId" value="${product.id}" hidden>
                                    <textarea name="reviewmess" class="form-control" rows="10"></textarea>
                                </div>
                                <div class="modal-change-button">
                                    <button class="btn" type="submit">Lưu</button>
                                    <button class="btn" type="button" onclick="closeModal()">Hủy</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if> 
            <!--modal change review-->
            <!--modal add to cart success-->
            <c:forEach items="${sessionScope.listCarts}" var="i">
                <c:if test="${i.productId == product.id&&check==1}">
                    <div onclick="closeModal()" class="modal-nofi" id="modal-notifi">
                        <div class="modal-nofi-overlay"></div>
                        <div class="modal-nofi-body">
                            <div class="modal-nofi-inner">
                                <i class="far fa-check-circle"></i>
                                <p>Thêm vào giỏ hàng thành công </p>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <!--end modal add to cart success-->
            <!--modal review fail-->
            <c:if test="${checkReviewExist==1}">
                <div onclick="closeModal()" class="modal-nofi" id="modal-notifi">
                    <div class="modal-nofi-overlay"></div>
                    <div class="modal-nofi-body">
                        <div class="modal-nofi-fail">
                            <i class="far fa-times-circle"></i>
                            <p>XIn lỗi ..! Bạn đã đánh giá sản phẩm này </p>
                        </div>
                    </div>
                </div>
            </c:if>  
            <c:if test="${checkReview==false &&checkReviewExist==0}">
                <div onclick="closeModal()" class="modal-nofi" id="modal-notifi">
                    <div class="modal-nofi-overlay"></div>
                    <div class="modal-nofi-body">
                        <div class="modal-nofi-fail">
                            <i class="far fa-times-circle"></i>
                            <p>Lưu phản hồi thất bại</p>
                        </div>
                    </div>
                </div>
            </c:if>  
            <!-- end modal review fail-->
            <jsp:include page="template/footer.jsp"/>
    </body>
    <script>

        //        var closeModalTime = setInterval(function () {
        //            document.getElementById("modal-notifi").style.display = "none";
        //            clearInterval(closeModalTime);
        //        }, 5 * 1000);
        function closeModal() {
            document.getElementById("modal-notifi").style.display = "none";
        }
        function deleteReview(id, productId) {
            var option = confirm('Bạn có muốn xóa phản hổi của bạn ?');
            if (option === true) {
                window.location.href = 'removereview?id=' + id + '&productId=' + productId;
            }
        }
        $(':radio').change(function () {
            console.log('New star rating: ' + this.value);
        }
        );
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
    <script src="assets/main.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>
