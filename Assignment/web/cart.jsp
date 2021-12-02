<%-- 
    Document   : cart
    Created on : Nov 3, 2021, 7:42:18 PM
    Author     : tenhik
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Add to cart</title>
        <!--css file-->
        <link rel="stylesheet" href="assets/style.css">
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
        <jsp:include page="template/header.jsp"/>
        <div class="header-block">
        </div>
        <div class="container">
            <!--cart item-->
            <c:if test="${not empty listCarts}">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="mb-3">
                            <div class="pt-4 wish-list">
                                <h5 class="mb-4">Cart (<span>${listCarts.size()}</span> items)</h5>
                                <c:forEach items="${sessionScope.listCarts}" var="i">
                                    <!--product information-->
                                    <div class="row mb-4">
                                        <div class="col-md-5 col-lg-3 col-xl-3">
                                            <div class="view zoom overlay z-depth-1 rounded mb-3 mb-md-0">
                                                <c:forEach var="o" items="${i.listImage}"> 
                                                    <img class="img-fluid w-100"
                                                         src="${o.img1}" alt="Sample">
                                                </c:forEach> 
                                            </div>
                                        </div>
                                        <div class="col-md-7 col-lg-9 col-xl-9">
                                            <div>
                                                <div class="d-flex justify-content-between">
                                                    <div>
                                                        <h5>${i.name}</h5                                               

                                                        <p class="mb-2 text-muted text-uppercase small">SKU: ${i.productId}</p>

                                                        <p class="mb-2 text-muted text-uppercase small">Color: ${i.color}</p>
                                                        <a href="detail?productId=${i.productId}&color=${i.color}&quanity=${i.quantity}&edit=${1}" class="text-uppercase text-muted small" style="color: #000"><i class="fas fa-edit"></i>Edit this color</a>
                                                    </div>
                                                    <div>
                                                        <div class="input-group w-50 input-amount" style="float:right; display:flex">
                                                            <a href="reloadquantity?subQty=plus&qty=${i.quantity}&productId=${i.productId}&color=${i.color}" class="qutyplus" style="text-decoration: none">+</a>
                                                            <input type="text" disabled="disabled" class="form-control input-number text-center rounded quty" value="${i.quantity}">
                                                            <c:if  test="${i.quantity > 1}">
                                                                <a href="reloadquantity?subQty=minus&qty=${i.quantity}&productId=${i.productId}&color=${i.color}" class="qutyminus"style="text-decoration: none">-</a>
                                                            </c:if>
                                                            <c:if  test="${i.quantity == 1}">
                                                                <a href="#" onclick="showConfirm('${i.productId}')" class="qutyminus"style="text-decoration: none">-</a>
                                                            </c:if>

                                                        </div>
                                                        <small id="passwordHelpBlock" class="form-text text-muted text-center">
                                                            (Note, ${i.quantity} piece)
                                                        </small>
                                                    </div>
                                                </div>
                                                <div class="d-flex justify-content-between align-items-center mt-3 edit-function">
                                                    <div class="remove-cart" style="border-radius:1rem;padding: 0.5rem;" >
                                                        <a href="#" onclick="showMess('${i.productId}')" class="text-uppercase mr-3" style="color: #000;text-decoration: none;">
                                                            <i class="fas fa-trash-alt mr-1"></i> Remove item </a>
                                                    </div>
                                                    <p class="mb-0"><span><strong id="summary"><fmt:formatNumber value = "${i.price}" type = "currency"/></strong></span></p class="mb-0">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--end product information-->
                                </c:forEach>
                                <hr class="mb-4">
                                <p class="text-danger mb-0"><i class="fas fa-info-circle mr-1"></i> Do not delay the purchase, adding
                                    items to your cart does not mean booking them.</p>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="pt-4">
                                <h5 class="mb-4">Expected shipping delivery</h5>
                                <p class="mb-0"> Thu., 12.03. - Mon., 16.03.</p>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="pt-4">

                                <h5 class="mb-4">We accept</h5>

                                <img class="mr-2" width="45px"
                                     src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
                                     alt="Visa">
                                <img class="mr-2" width="45px"
                                     src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
                                     alt="American Express">
                                <img class="mr-2" width="45px"
                                     src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
                                     alt="Mastercard">
                                <img class="mr-2" width="45px"
                                     src="https://mdbootstrap.com/wp-content/plugins/woocommerce/includes/gateways/paypal/assets/images/paypal.png"
                                     alt="PayPal acceptance mark">
                            </div>
                        </div>
                    </div>
                    <!--total price form-->
                    <div class="col-lg-4">
                        <div class="mb-3">
                            <div class="pt-4 addtocart-blue">

                                <h5 class="mb-3 font-weight-bold">The total amount of</h5>

                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                                        Temporary amount
                                        <span><fmt:formatNumber value = "${totalPrice}" type = "currency"/></span>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                        <div>
                                            <strong>The total amount of</strong>

                                            <p class="mb-0">(Not including shipping fee)</p>                                          
                                        </div>
                                        <span><strong><fmt:formatNumber value = "${totalPrice}" type = "currency"/></strong></span>
                                    </li>
                                </ul>
                                <c:if test="${currentLogin != null}">
                                    <a  href="#" onclick="openCheckout(${totalPrice})" class="cartBtn btn-block"style="text-decoration: none"> 
                                        <span class="text-uppercase font-weight-bold" >go to checkout</span></a>
                                    </c:if>
                                    <c:if test="${currentLogin == null}">
                                    <a  href="login.jsp" class="cartBtn btn-block"style="text-decoration: none"> 
                                        <span class="text-uppercase font-weight-bold" >go to checkout</span></a>
                                    </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if> 
            <!--list cart empty-->
            <c:if test="${viewCart == 'empty'}">
                <div class="empty-cart">
                </div>
                <p class="text-center text-uppercase font-weight-bold">Cart is empty!!</p>
                <div class="empty-cart-home">
                    <a href="home"class=" text-uppercase font-weight-bold" style="text-decoration:none ">Home</a>
                </div>
            </c:if>
        </div>
        <!--modal check out product-->
        <div class="modal-nofi" id="modal-checkout">
            <div class="modal-nofi-overlay"></div>
            <div class="modal-checkout modal-dialog-scrollable">
                <form action="payment" method="post">
                    <h5 class="modal-checkout-title">Check out</h5>
                    <div class="modal-checkout-body">
                        <div class="modal-upper">
                            <div class="modal-checkout-product border-bottom">
                                <p>Cart:</p>
                                <c:forEach items="${sessionScope.listCarts}" var="i">
                                    <div class="modal-product">
                                        <c:forEach var="o" items="${i.listImage}">
                                            <img src="${o.img1}">
                                        </c:forEach>
                                        <div class="modal-product-des">
                                            <p>${i.name}</p>
                                            <p>Price: <fmt:formatNumber value = "${i.price}" type = "currency"/></p>  
                                            <p>Quanity: ${i.quantity}</p>
                                            <p>Color: ${i.color}</p>  
                                        </div>
                                    </div>
                                </c:forEach>
                            </div> 
                            <div class="modal-checkout-info"> 
                                <h4>Information</h4>
                                <div class="modal-checkout-flex">
                                    <input type="text" class="" placeholder="Enter full name" value="${currentLogin.name}" required>
                                    <input  name="phone" type="text" placeholder="Enter phone" value="${currentLogin.phone}"required>
                                </div>
                                <div class="modal-checkout-full border-bottom">
                                    <input type="email" placeholder="Enter email" value="${currentLogin.email}"required>  
                                    <input  name="address" type="text" placeholder="Enter address"  required>
                                    <input name ="totalPrice" value = "${totalPrice}"  hidden>
                                    <textarea name="note" placeholder="Notes here..."></textarea>
                                </div>   

                                <h4>Total price: <fmt:formatNumber value = "${totalPrice}" type = "currency"/></h4>
                            </div>
                        </div>
                    </div>
                    <!--button check out-->
                    <div class="modal-footer">
                        <button type="button" onclick="closeCheckout()" class="btn-close-modal" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn-save-modal">Check out</button>
                    </div>
                </form>
            </div>
        </div>
        <!--modal edit color success-->
        <c:if test="${edit==1}">
            <div onclick="closeModal()" class="modal-nofi" id="modal-edit">
                <div class="modal-nofi-overlay"></div>
                <div class="modal-nofi-body">
                    <div class="modal-nofi-inner">
                        <i class="far fa-check-circle"></i>
                        <p>Edit successfully </p>
                    </div>
                </div>
            </div>
        </c:if>      
        <!--modal edit checkout success-->
        <c:if test="${checkOdetail==true}">
            <div onclick="closeModal()" class="modal-nofi" id="modal-edit">
                <div class="modal-nofi-overlay"></div>
                <div class="modal-nofi-body">
                    <div class="modal-nofi-inner">
                        <i class="far fa-check-circle"></i>
                        <p>Check out successfully </p>
                    </div>
                </div>
            </div>
        </c:if>   

        <jsp:include page="template/footer.jsp"/>
        <script>
//            var closeModalTime = setInterval(function () {
//                document.getElementById("modal-edit").style.display = "none";
//                clearInterval(closeModalTime);
//            }, 5 * 1000);

            function closeModal() {
                document.getElementById("modal-edit").style.display = "none";
            }
            function closeCheckout() {
                document.getElementById("modal-checkout").style.display = "none";
            }
            function openCheckout() {
                document.getElementById("modal-checkout").style.display = "flex";
            }
            function showMess(id) {
                var option = confirm('Are you sure to remove this item');
                if (option === true) {
                    window.location.href = 'removeProductInCart?productId=' + id;
                }
            }

            function showConfirm(productId) {
                var option = confirm('Are you sure to delete this item');
                if (option === true) {
                    window.location.href = 'removeProductInCart?productId=' + productId;
                }
            }
        </script>
        <script src="assets/main.js"></script>
    </body>
</html>
