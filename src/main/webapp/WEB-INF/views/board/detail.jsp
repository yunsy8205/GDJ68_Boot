<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/layout/headCSS.jsp"></c:import>
</head>
<body id="page-top">
	<!-- Page Wrapper -->
    <div id="wrapper">
    	<c:import url="/WEB-INF/views/layout/sidebar.jsp"></c:import>
    	<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
        	<!-- Main Content -->
            <div id="content">
            	<c:import url="/WEB-INF/views/layout/topbar.jsp"></c:import>
            	<!-- Begin Page Content -->
                <div class="container-fluid">
                <!-- 페이지 실제 내용 -->
	                <h1>Detail Page</h1>
                	<div class="row">
		               	<div>${boardVO.boardNo}</div>
						<div>${boardVO.boardTitle}</div>
						<div>${boardVO.boardWriter}</div>
						<div>${boardVO.boardDate}</div>
						<div>${boardVO.boardHit}</div>
                	</div>
                	
                	<div class="row">
                		<c:forEach items="${boardVO.list}" var="f">
                			<img alt="" src="../files/${board}/${f.fileName}">
                			<a href="./fileDown?fileNo=${f.fileNo}">${f.oriName}</a>
                		</c:forEach>
                	</div>
                </div>
            </div>
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
        </div>
    </div>
<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>