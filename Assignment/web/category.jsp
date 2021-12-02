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
                <!--Category-->
                <div class="sidebar-border">
                    <ul class="sidebar-list">
                        <li class='submenu'>
                            <div class="submenu-header"><span>Category</span></div>
                            <form action="filterproduct" method="get">                     
                                <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                            <c:forEach items="${CategoryDAO.allCategory}" var="o">
                                <div class="radiobtn"> 
                                    <c:if test="${cateId == o.id}">
                                        <input  class="state" type="radio" name="cateId" id="${o.name}" checked="checked" value="${o.id}"  onclick="this.form.submit();">
                                        <label  class="label" for="${o.name}">
                                            <div class="indicator"></div>
                                        </label>
                                    </c:if>
                                    <c:if test="${cateId != o.id}">
                                        <input class="state" type="radio" name="cateId" id="${o.name}" value="${o.id}"  onclick="this.form.submit();">
                                        <label class="label" for="${o.name}">
                                            <div class="indicator"></div>
                                        </label>
                                    </c:if>
                                </div>
                                <p>${o.name}</p>
                            </c:forEach>
                            <c:if test="${brandId!=null}">
                                <input type="hidden" name="brandId" value="${brandId}"/>
                            </c:if>
                            <c:if test="${displaySize!=null}">
                                <input type="hidden" name="displaySize" value="${displaySize}"/>
                            </c:if>
                            <c:if test="${processId!=null}">
                                <input type="hidden" name="processId" value="${processId}"/>
                            </c:if>
                            <c:if test="${priceId!=null}">
                                <input type="hidden" name="priceId" value="${priceId}"/>
                            </c:if>
                        </form>
                    </li> 
                    <!--Brand-->
                    <li class='submenu'>
                        <div class="submenu-header"><span>Brand</span></div>
                        <form action="filterproduct" method="get">                                               
                            <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                            <c:if test="${cateId!=null}">
                                <input type="hidden" name="cateId" value="${cateId}"/>
                            </c:if>    
                            <c:forEach items="${CategoryDAO.allBrand}" var="o">
                                <div class="radiobtn"> 
                                    <c:if test="${brandId == o.id}">
                                        <input class="state" type="radio" name="brandId" value="${o.id}" id="${o.name}" checked="checked" onclick="this.form.submit();">
                                        <label class="label" for="${o.name}">
                                            <div class="indicator"></div>
                                        </label>
                                    </c:if>
                                    <c:if test="${brandId != o.id}">
                                        <input class="state" type="radio" name="brandId" value="${o.id}" id="${o.name}" onclick="this.form.submit();">
                                        <label  class="label" for="${o.name}">
                                            <div class="indicator"></div>
                                        </label>
                                    </c:if>
                                </div>
                                <p>${o.name}</p>
                            </c:forEach>
                            <c:if test="${displaySize!=null}">
                                <input type="hidden" name="displaySize" value="${displaySize}"/>
                            </c:if>
                            <c:if test="${processId!=null}">
                                <input type="hidden" name="processId" value="${processId}"/>
                            </c:if>
                            <c:if test="${priceId!=null}">
                                <input type="hidden" name="priceId" value="${priceId}"/>
                            </c:if>
                        </form>
                    </li>     
                    <!--Display size-->
                    <li class='submenu'>
                        <div class="submenu-header"><span>Display Size</span></div>
                        <form action="filterproduct" method="get">                                               
                            <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                            <c:if test="${cateId!=null}">
                                <input type="hidden" name="cateId" value="${cateId}"/>
                            </c:if>    
                            <c:if test="${brandId!=null}">
                                <input type="hidden" name="brandId" value="${brandId}"/>
                            </c:if>
                            <c:forEach items="${CategoryDAO.allDisplaySize}" var="o"> 
                                <div class="radiobtn">  
                                    <c:if test="${displaySize == o.size}">
                                        <input class="state" type="radio" name="displaySize" value="${o.size}" id="${o.size}" checked="checked" onclick="this.form.submit();">
                                        <label  class="label" for="${o.size}">
                                            <div class="indicator"></div>
                                        </label>    
                                    </c:if>
                                    <c:if test="${displaySize != o.size}">
                                        <input class="state" type="radio" name="displaySize" value="${o.size}" id="${o.size}" onclick="this.form.submit();">
                                        <label  class="label" for="${o.size}">
                                            <div class="indicator"></div>
                                        </label>    
                                    </c:if>
                                </div>
                                <p>${o.size}</p>
                            </c:forEach>
                            <c:if test="${processId!=null}">
                                <input type="hidden" name="processId" value="${processId}"/>
                            </c:if>
                            <c:if test="${priceId!=null}">
                                <input type="hidden" name="priceId" value="${priceId}"/>
                            </c:if>
                        </form>
                    </li>  
                    <!--Processor-->
                    <li class='submenu'>
                        <div class="submenu-header"><span>Processor</span></div>
                        <form action="filterproduct" method="get">                                               
                            <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                            <c:if test="${cateId!=null}">
                                <input type="hidden" name="cateId" value="${cateId}"/>
                            </c:if>    
                            <c:if test="${brandId!=null}">
                                <input type="hidden" name="brandId" value="${brandId}"/>
                            </c:if>
                            <c:if test="${displaySize!=null}">
                                <input type="hidden" name="displaySize" value="${displaySize}"/>
                            </c:if>
                            <c:forEach items="${CategoryDAO.allProcessor}" var="o">  
                                <div class="radiobtn">  
                                    <c:if test="${processId == o.id}">
                                        <input class="state" type="radio" name="processId" value="${o.id}" id="${o.name}" checked="checked" onclick="this.form.submit();">
                                        <label  class="label" for="${o.name}">
                                            <div class="indicator"></div>
                                        </label>
                                    </c:if>
                                    <c:if test="${processId != o.id}">
                                        <input class="state" type="radio" name="processId" value="${o.id}" id="${o.name}" onclick="this.form.submit();">
                                        <label  class="label" for="${o.name}">
                                            <div class="indicator"></div>
                                        </label>
                                    </c:if>
                                </div>
                                <p>${o.name}</p>
                            </c:forEach>
                            <c:if test="${priceId!=null}">
                                <input type="hidden" name="priceId" value="${priceId}"/>
                            </c:if>
                        </form>
                    </li> 
                    <!--Price amount-->
                    <li class='submenu'>
                        <div class="submenu-header"><span>Price</span></div>
                        <form action="filterproduct" method="get">                                               
                            <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                            <c:if test="${cateId!=null}">
                                <input type="hidden" name="cateId" value="${cateId}"/>
                            </c:if>    
                            <c:if test="${brandId!=null}">
                                <input type="hidden" name="brandId" value="${brandId}"/>
                            </c:if>
                            <c:if test="${displaySize!=null}">
                                <input type="hidden" name="displaySize" value="${displaySize}"/>
                            </c:if>
                            <c:if test="${processId!=null}">
                                <input type="hidden" name="processId" value="${processId}"/>
                            </c:if>
                            <c:forEach items="${CategoryDAO.allPrice}" var="o"> 
                                <div class="radiobtn">  
                                    <c:if test="${priceId == o.id}">
                                        <input class="state" type="radio" name="priceId" value="${o.id}" id="${o.id}" checked="checked" onclick="this.form.submit();">
                                        <label  class="label" for="${o.id}">
                                            <div class="indicator"></div>
                                        </label>
                                    </c:if>
                                    <c:if test="${priceId != o.id}">
                                        <input class="state" type="radio" name="priceId" value="${o.id}" id="${o.id}" onclick="this.form.submit();">
                                        <label  class="label" for="${o.id}">
                                            <div class="indicator"></div>
                                        </label>
                                    </c:if>
                                </div>
                                <p>${o.amount}</p>
                            </c:forEach>
                        </form>
                    </li> 
                </ul>
            </div> 
            <div class="sidebar-footer"></div>
        </div>
    </div>
</div>