<%-- 
    Document   : sort
    Created on : Nov 2, 2021, 12:31:55 PM
    Author     : tenhik
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${totalProduct != 0}">
    <!--sort without category-->
    <c:if test="${priceId==null&&cateId==null&&brandId==null&&displaySize==null&&processId==null&&text==null}">
        <div class="sortbar">
            <form action="home" method="get">
                <input type="hidden" name="pageIndex" value="${pageIndex}"/>
                <select name="sortId" class="sorting-list" onchange="this.form.submit()">
                    <option value="0" disabled selected>Sort by</option>
                    <option value="1" ${sortId == 1 ? "selected" : ""}>From A to Z</option>
                    <option value="2" ${sortId == 2 ? "selected" : ""}>From Z to A</option>
                    <option value="3" ${sortId == 3 ? "selected" : ""} >Lowest to Highest</option>
                    <option value="4" ${sortId == 4 ? "selected" : ""}>Highest to Lowest</option>
                </select>
            </form>
        </div>
    </c:if>
    <!--sort with category-->
    <c:if test="${priceId!=null||cateId!=null||brandId!=null||displaySize!=null||processId!=null}">     
        <div class="sortbar">
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
                <c:if test="${ramId!=null}">
                    <input type="hidden" name="ramId" value="${ramId}"/>
                </c:if>
                <c:if test="${hddId!=null}">
                    <input type="hidden" name="hddId" value="${hddId}"/>
                </c:if>
                <c:if test="${priceId!=null}">
                    <input type="hidden" name="priceId" value="${priceId}"/>
                </c:if>
                <select name="sortId" class="sorting-list" onchange="this.form.submit()">
                    <option value="0" disabled selected>Sort by</option>
                    <option value="1" ${sortId == 1 ? "selected" : ""}>From A to Z</option>
                    <option value="2" ${sortId == 2 ? "selected" : ""}>From Z to A</option>
                    <option value="3" ${sortId == 3 ? "selected" : ""} >Lowest to Highest</option>
                    <option value="4" ${sortId == 4 ? "selected" : ""}>Highest to Lowest</option>
                </select>
            </form>
        </div>
    </c:if>
    <!--sort with searching-->
    <c:if test="${text!=null}">
        <div class="sortbar">
            <form action="search" method="get">                            
                <input type="hidden" name="pageIndex" value="${pageIndex}"/>    
                <input type="hidden" name="text" value="${text}"/>
                <select name="sortId" class="sorting-list" onchange="this.form.submit()">
                    <option value="0" disabled selected>Sort by</option>
                    <option value="1" ${sortId == 1 ? "selected" : ""}>From A to Z</option>
                    <option value="2" ${sortId == 2 ? "selected" : ""}>From Z to A</option>
                    <option value="3" ${sortId == 3 ? "selected" : ""} >Lowest to Highest</option>
                    <option value="4" ${sortId == 4 ? "selected" : ""}>Highest to Lowest</option>
                </select>
            </form>
        </div>
    </c:if>
</c:if>
