<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <script>
        const ShopSelector = document.querySelector("#sort_options");

        function ProductSortUp(){
            var sort_option=$('#sort_options').val();
            var category = '${param.goods_sort}';


            <%--//console.log(sort_option);--%>
            <%--// 선택된 값을 URL에 추가하여 새로운 URL 만들기--%>
            <%--var newUrl = "${contextPath}/goods/categoryGoodsList.do?category="+category+"&sort_option=" + sort_option;--%>

            <%--// 새로운 URL로 페이지 이동--%>
            <%--window.location.href = newUrl;--%>

            $.ajax({
                type : "post",
                async : false, //false인 경우 동기식으로 처리한다.
                url : "${contextPath}/goods/categoryGoodsList.do",
                data : {
                    sort_option : sort_option,
                    category : category
                },

                success : function(data, textStatus) {
                    //console.log(data)
                    var $specificElement = $(data).find(".category_shop");
                    console.log($specificElement)

                    $(".category_shop").html($specificElement);

                    $(".title").css({
                        'color': 'rgb(102, 102, 102)',
                        'font-family': '"Dotum", Arial, serif',
                        'font-size': '13px',
                        'text-align': 'center',
                        'vertical-align': 'middle',
                        'margin': '0px',
                        'padding': '2px 0px',
                        'font-weight': 'bold'
                    });

                    $(".price").css({
                        'color': 'rgb(102, 102, 102)',
                        'font-family': '"Dotum", Arial, serif',
                        'font-size': '13px',
                        'text-align': 'center',
                        'vertical-align': 'middle',
                        'margin': '0px',
                        'padding': '2px 0px',
                    });

                    $(".book a").css({
                        'font-size': '13px',
                        'text-align': 'center',
                        'vertical-align': 'middle',
                        'margin': '5px 0px 0px',
                        'padding': '0px',
                        'width': '188px',
                        'height': '230px',
                        'border-image': 'none',
                        'border': 'currentColor',
                        'display': 'block',
                        'position': 'absolute',
                        'z-index': '2'
                    });
                },
                error : function(data, textStatus) {
                    alert("에러가 발생했습니다."+data);
                },
                complete : function(data, textStatus) {
                    //alert("작업을완료 했습니다");

                }
            }); //end ajax
        }


    </script>
</head>
<body>

<div id="categoryGoodsList">
    <%--상품정렬 드롭다운--%>
    <div id="goodsSort">
        <section class="sort_selector">
            <select name="sort_options" id="sort_options" onchange="ProductSortUp()">
                <option value="기본 정렬">기본 정렬</option>
                <option value="가나다순">가나다순</option>
                <option value="낮은 가격순">낮은 가격순</option>
                <option value="높은 가격순">높은 가격순</option>
                <%--                <option>신상품순</option>--%>
            </select>
        </section>
    </div>

<%--    <h1>${param.category}</h1>--%>


    <div class="category_shop">
        <c:set var="goods_count" value="0"/>
        <%--    <h3>IT/인터넷</h3>--%>
        <c:forEach var="item" items="${goodsMap.goodsList }">
            <c:set var="goods_count" value="${goods_count+1 }"/>
            <div class="maingoods">
                <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
                    <img class="link" src="${contextPath}/resources/image/1px.gif">
                </a>
                <img
                     src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">

                <div class="title">${item.goods_title }</div>
                <div class="price">
                    <fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price"/>
                        ${goods_price}원
                </div>
            </div>
            <c:if test="${goods_count==15   }">
                <div class="maingoods">
                    <font size=20> <a href="#">more</a></font>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>

</body>
</html>
