<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                	<h1>Info Page</h1>
                	<div class="row">
                	<sec:authorize access="isAuthenticated()">
                	<sec:authentication property="principal" var="user"/>
                	<h3>${user.username}</h3>
                	<h3>${user.email}</h3>
                	</sec:authorize>
                	</div>
                </div>
            </div>
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
        </div>
    </div>
<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>