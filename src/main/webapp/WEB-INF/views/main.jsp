<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"	isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
    request.setCharacterEncoding("UTF-8");
%>

<%--<div id="ad_main_banner">--%>
<%--    <ul class="bjqs">--%>
<%--        <li><img width="100%" height="145" src="${contextPath}/resources/image/main_banner01.jpg"></li>--%>
<%--        <li><img width="100%" height="145" src="${contextPath}/resources/image/main_banner02.jpg"></li>--%>
<%--        <li><img width="100%" height="145" src="${contextPath}/resources/image/main_banner03.jpg"></li>--%>
<%--    </ul>--%>
<%--</div>--%>

<div id="mainContent">
    <div class="main_slider">
        <img class="main_slide" src="${contextPath}/resources/image/mainbannerposter1.jpg" style="display: block;">
        <img class="main_slide" src="${contextPath}/resources/image/mainbannerposter2.jpg">
        <img class="main_slide" src="${contextPath}/resources/image/mainbannerposter3.jpg">
        <img class="main_slide" src="${contextPath}/resources/image/mainsample4.jpg">
        <img class="main_slide" src="${contextPath}/resources/image/mainsample5.jpg">
    </div>
<div class="main_book">
    <c:set  var="goods_count" value="0" />
    <h3>BEST</h3>
    <c:forEach var="item" items="${goodsMap.best}">
        <c:set  var="goods_count" value="${goods_count+1 }" />
        <div class="book">
            <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
                <img class="link"  src="${contextPath}/resources/image/1px.gif">
            </a>
            <img width="121" height="154"
                 src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">

            <div class="title">${item.goods_title }</div>
            <div class="price">
                <fmt:formatNumber  value="${item.goods_price}" type="number" var="goods_price" />
                    ${goods_price}원
            </div>
        </div>
        <c:if test="${goods_count==15   }">
            <div class="book">
                <font size=20> <a href="#">more</a></font>
            </div>
        </c:if>
    </c:forEach>
</div>
<div class="clear"></div>
<div id="ad_sub_banner">
    <img width="100%" height="117" src="${contextPath}/resources/image/sub_banner01.jpg">
</div>
<div class="main_book" >
    <c:set  var="goods_count" value="0" />
    <h3>NEW</h3>
    <c:forEach var="item" items="${goodsMap.new}" >
        <c:set  var="goods_count" value="${goods_count+1 }" />
        <div class="book">
            <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
                <img class="link"  src="${contextPath}/resources/image/1px.gif">
            </a>
            <img width="121" height="154"
                 src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
            <div class="title">${item.goods_title }</div>
            <div class="price">
                <fmt:formatNumber  value="${item.goods_price}" type="number" var="goods_price" />
                    ${goods_price}원
            </div>
        </div>
        <c:if test="${goods_count==15   }">
            <div class="book">
                <font size=20> <a href="#">more</a></font>
            </div>
        </c:if>
    </c:forEach>
</div>

<div class="clear"></div>
<div id="ad_sub_banner">
    <img width="100%" height="117" src="${contextPath}/resources/image/sub_banner02.jpg">
</div>


<div class="main_book" >
    <c:set  var="goods_count" value="0" />
    <h3>스테디셀러</h3>
    <c:forEach var="item" items="${goodsMap.steady}" >
        <c:set  var="goods_count" value="${goods_count+1 }" />
        <div class="book">
            <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
                <img class="link"  src="${contextPath}/resources/image/1px.gif">
            </a>
            <img width="121" height="154"
                 src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
            <div class="title">${item.goods_title }</div>
            <div class="price">
                <fmt:formatNumber  value="${item.goods_price}" type="number" var="goods_price" />
                    ${goods_price}원
            </div>
        </div>
        <c:if test="${goods_count==15   }">
            <div class="book">
                <font size=20> <a href="#">more</a></font>
            </div>
        </c:if>
    </c:forEach>
</div>
</div>


