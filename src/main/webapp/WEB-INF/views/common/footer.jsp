<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<div id="footerInfoWrap" style="padding-left:200px">
   <ul>
       <li class="list" id="info">
        <em class="title">COMPANY INFO</em>
           <address class="subTxt">
               <p>회사명 : 아름드리 (AreumDuri)&nbsp;&nbsp; 대표 : 황이현 &nbsp;&nbsp;</p>
               <p>사업자번호 : 123-45-67890</p>
               <p>통신판매번호 : 2024-서울강남-1234호</p>
               <p>주소 : 서울시 강남구 선릉로 000, n층 아름드리 </p>
               <p>개인정보책임자 : 황이현</p>
               <p>E-mail : areumduri@gmail.com</p>
               <p class="copy">Copyright @ All Rights Reserved. design by yihyun</p>
           </address>
       </li>
       <li class="list" id="csCenter">
           <em class="title">CUSTOMER CENTER</em>
           <span class="csNumber">1234-5678</span>
           <p class="subTxt">MON - FRIDAY AM10 ~ PM 06<br>SAT,SUN,HOLIDAY OFF</p>
       </li>
       <li class="list" id="bank">
           <em class="title">BANK INFO</em>
           <p class="subTxt">국민 000-000-00-0000</p>
           <p class="subTxt">예금주 : 황이현(아름드리)</p>
       </li>
       <li class="list" id="csBtn">
           <em class="title">CLICK HERE!</em>
           <span class="iconBox">
               <p class="icon"><a href="#">NOTICE</a> </p>
                <p class="icon"><a href="#">CS CENTER</a> </p>
           </span>
           <span class="iconBox">
                <p class="icon"><a href="#">FnQ</a> </p>
                <p class="icon"><a href="#">Login</a> </p>
           </span>

       </li>
   </ul>
</div>
<div class="clear"></div>
<div id="footerNavWrap">
    <ul class="footerNav">
        <li><a href="#">ABOUT US</a></li>
        <li><a href="#">AGREEMENT</a></li>
        <li><a href="#">CHECKPRIVACY</a></li>
        <li><a href="#">MARKETING CENTER</a></li>
        <li class="no_line"><a href="#">SHOP GUIDE</a></li>
    </ul>
</div>

