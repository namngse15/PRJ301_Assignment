<%-- 
    Document   : pagging
    Created on : Nov 3, 2021, 2:34:00 PM
    Author     : tenhik
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="pagging" aria-label="Page navigation example">
    <!--pagging without filter product-->
    <c:if test="${priceId==null&&cateId==null&&brandId==null&&displaySize==null&&processId==null&&text==null}">
        <c:if test="${totalPage>0}">
            <form action="home" method="get">
                <c:if test="${back>0}">
                    <button type="submit" name="pageIndex" value="${back}">Previous</button>
                </c:if>  
                <c:forEach begin="1" end="${totalPage}" step="1" var="i">
                    <c:if test="${i==pageIndex}">
                        <button type="submit" class="active-true" name="pageIndex" value="${i}">${i}</button>
                    </c:if>
                    <c:if test="${i!=pageIndex}">
                        <button type="submit" class="active-false" name="pageIndex" value="${i}">${i}</button>
                    </c:if>
                </c:forEach>
                <c:if test="${next<totalPage+1}">
                    <button type="submit" name="pageIndex" value="${next}">Next</button>
                </c:if>
                <c:if test="${sortId!=null}">
                    <input type="hidden" name="sortId" value="${sortId}"/>
                </c:if>
            </form>
        </c:if>
    </c:if>
    <!--pagging with filter product-->
    <c:if test="${priceId!=null||cateId!=null||brandId!=null||displaySize!=null||processId!=null}">    
        <c:if test="${totalPage>0}">
            <form action="filterproduct" method="get">
                <!--pagging-->
                <c:if test="${back>0}">
                    <button type="submit" name="pageIndex" value="${back}">Previous</button>
                </c:if>  
                <c:forEach begin="1" end="${totalPage}" step="1" var="i">
                    <c:if test="${i==pageIndex}">
                        <button type="submit" class="active-true" name="pageIndex" value="${i}">${i}</button>
                    </c:if>
                    <c:if test="${i!=pageIndex}">
                        <button type="submit" class="active-false" name="pageIndex" value="${i}">${i}</button>
                    </c:if>
                </c:forEach>
                <c:if test="${next<totalPage+1}">
                    <button type="submit" name="pageIndex" value="${next}">Next</button>
                </c:if>
                <!--category-->
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
                <!--sort-->
                <c:if test="${sortId!=null}">
                    <input type="hidden" name="sortId" value="${sortId}"/>
                </c:if>
            </form>
        </c:if>
    </c:if>
    <!--pagging with searching-->
    <c:if test="${text!=null}">
        <c:if test="${totalPage>0}">
            <form action="search" method="get">
                <c:if test="${back>0}">
                    <button type="submit" name="pageIndex" value="${back}">Previous</button>
                </c:if>  
                <c:forEach begin="1" end="${totalPage}" step="1" var="i">
                    <c:if test="${i==pageIndex}">
                        <button type="submit" class="active-true" name="pageIndex" value="${i}">${i}</button>
                    </c:if>
                    <c:if test="${i!=pageIndex}">
                        <button type="submit" class="active-false" name="pageIndex" value="${i}">${i}</button>
                    </c:if>
                </c:forEach>
                <c:if test="${next<totalPage+1}">
                    <button type="submit" name="pageIndex" value="${next}">Next</button>
                </c:if>
                <c:if test="${sortId!=null}">
                    <input type="hidden" name="sortId" value="${sortId}"/>
                </c:if>
            </form>
        </c:if>  
    </c:if>
</nav>
