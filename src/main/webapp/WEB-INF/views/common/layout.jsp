<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <link href="${contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" media="screen">
    <link href="${contextPath}/resources/css/basic-jquery-slider.css" rel="stylesheet" type="text/css" media="screen">
    <link href="${contextPath}/resources/css/mobile.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed&family=Nanum+Gothic&display=swap" rel="stylesheet">
    <script src="${contextPath}/resources/jquery/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/jquery/jquery.easing.1.3.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/jquery/stickysidebar.jquery.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/jquery/basic-jquery-slider.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/jquery/tabs.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/jquery/carousel.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/jquery/mainslide.js" type="text/javascript"></script>
    <script>
        // 슬라이드
        $(document).ready(function() {
            $('#ad_main_banner').bjqs({
                'width' : 1332.09,
                'height' : 145,
                'showMarkers' : true,
                'showControls' : false,
                'centerMarkers' : false
            });
        });
        // 스티키
        $(function() {
            $("#sticky").stickySidebar({
                timer : 100,
                easing : "easeInBounce"
            });
        });
    </script>
    <title><tiles:insertAttribute name="title" /></title>

</head>
<body>
<div id="outer_wrap">
    <header>
        <tiles:insertAttribute name="header" />
    </header>
    <div id="wrap">

        <div class="clear"></div>
        <aside>
            <tiles:insertAttribute name="side" />
        </aside>
        <article>
            <tiles:insertAttribute name="body" />
        </article>
        <div class="clear"></div>
        <div id="quickMenu">
            <tiles:insertAttribute name="quickMenu" />
        </div>
    </div>
    <div id="ftwrap">
    <footer>
        <tiles:insertAttribute name="footer" />
    </footer>
    </div>
</div>
</body>
</html>
