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

        // 셀렉트의 경우
        // ShopSelector.addEventListener("change", (e) => {
        //     ProductSortUp()
        // });

        function ProductSortUp(){
            var sort_option=$('#sort_options').val();
            var category = '${param.category}';

            console.log(sort_option);

            $.ajax({
                type : "post",
                async : false, //false인 경우 동기식으로 처리한다.
                url : "${contextPath}/goods/categoryGoodsList.do",
                data : {
                    sort_option : sort_option,
                    category : category
                },

                success : function(data, textStatus) {
                    //alert(data);
                },
                error : function(data, textStatus) {
                    alert("에러가 발생했습니다."+data);
                },
                complete : function(data, textStatus) {
                    //alert("작업을완료 했습니다");

                }
            }); //end ajax
        }


        // // 셀렉트의 경우
        // ShopSelector.addEventListener("change", (e) => {
        //     if (e.target.value == "낮은 가격순") {
        //         ProductSortUp();
        //     } else if (e.target.value == "높은 가격순") {
        //         ProductSortDown();
        //     } else if (e.target.value == "가나다순") {
        //         ProductABC();
        //     } else if (e.target.value == "기본정렬") {
        //         ProductBasic();
        //     } else {
        //         ProductExchange();
        //     }
        // });




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

    <h1>${param.category}</h1>


    <div class="main_book">
        <c:set var="goods_count" value="0"/>
        <%--    <h3>IT/인터넷</h3>--%>
        <c:forEach var="item" items="${goodsMap.goodsList }">
            <c:set var="goods_count" value="${goods_count+1 }"/>
            <div class="book">
                <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
                    <img class="link" src="${contextPath}/resources/image/1px.gif">
                </a>
                <img width="121" height="154"
                     src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">

                <div class="title">${item.goods_title }</div>
                <div class="price">
                    <fmt:formatNumber value="${item.goods_price}" type="number" var="goods_price"/>
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

</body>
</html>
