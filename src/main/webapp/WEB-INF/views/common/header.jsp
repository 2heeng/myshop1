<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"
         isELIgnored="false"
%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />

<script type="text/javascript">
    var loopSearch=true;
    function keywordSearch(){
        if(loopSearch==false){
            return; }

        var value=document.frmSearch.searchWord.value;
        $.ajax({
            type : "get",
            async : true, //false인 경우 동기식으로 처리한다.
            url : "${contextPath}/goods/keywordSearch.do",
            data : {keyword:value},
            success : function(data, textStatus) {
                var jsonInfo = JSON.parse(data);
                displayResult(jsonInfo);
            },
            error : function(data, textStatus) {
                alert("에러가 발생했습니다."+data);
            },
            complete : function(data, textStatus) {
                //alert("작업을완료 했습니다");

            }
        }); //end ajax
    }

    function displayResult(jsonInfo){
        var count = jsonInfo.keyword.length;
        if(count > 0) {
            var html = '';
            for(var i in jsonInfo.keyword){
                html += "<a href=\"javascript:select('"+jsonInfo.keyword[i]+"')\">"+jsonInfo.keyword[i]+"</a><br/>";
            }
            var listView = document.getElementById("suggestList");
            listView.innerHTML = html;
            show('suggest');
        }else{
            hide('suggest');
        }
    }

    function select(selectedKeyword) {
        document.frmSearch.searchWord.value=selectedKeyword;
        loopSearch = false;
        hide('suggest');
    }

    function show(elementId) {
        var element = document.getElementById(elementId);
        if(element) {
            element.style.display = 'block';
        }
    }

    function hide(elementId){
        var element = document.getElementById(elementId);
        if(element){
            element.style.display = 'none';
        }
    }

</script>
<body>
<div id="head_banner">
    여기는 배너입니다.여기 중앙에 아름드리 로고가 들어갈겁니다.
</div>
<%--<div id="logo">--%>
<%--    <a href="${contextPath}/main/main.do">--%>
<%--        <img width="176" height="80" alt="booktopia" src="${contextPath}/resources/image/Booktopia_Logo.jpg">--%>
<%--    </a>--%>
<%--</div>--%>
<div id="head_link">
    <ul>
        <c:choose>
            <c:when test="${isLogOn==true and not empty memberInfo }">
                <li><a href="${contextPath}/member/logout.do">LOGOUT</a></li>
                <li><a href="${contextPath}/mypage/myPageMain.do">MYPAGE</a></li>
                <li><a href="${contextPath}/cart/myCartList.do">CART</a></li>
                <li><a href="#">주문배송</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${contextPath}/member/loginForm.do">LOGIN</a></li>
                <li><a href="${contextPath}/member/memberForm.do">JOIN</a></li>
            </c:otherwise>
        </c:choose>
        <li><a href="#">CS</a></li>
        <c:if test="${isLogOn==true and memberInfo.member_id =='admin' }">
            <li class="no_line"><a href="${contextPath}/admin/goods/adminGoodsMain.do">ADMIN</a></li>
        </c:if>
    </ul>
    <div id="search" >
        <form name="frmSearch" action="${contextPath}/goods/searchGoods.do" >
            <input name="searchWord" class="main_input" type="text"  onKeyUp="keywordSearch()">
            <input type="image" src="${contextPath}/resources/image/free-icon-magnifier-2311526.png" alt="Submit" width="19" height="19">
        </form>
    </div>
</div>
<br>

<div id="suggest">
    <div id="suggestList"></div>
</div>
</body>
</html>