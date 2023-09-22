<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                
                	<form:form modelAttribute="memberVO" method="post">
                		<div class="form-group">
                			<form:label path="username">Username</form:label>						    
						    <form:input path="username" cssClass="form-control" id="username"/>
						    <form:errors  path="username"></form:errors><!-- 에러 메세지를 여기에 찍기 위해 -->
						    <!-- 어디다 넣을 것인가? 멤버VO 셋터의 이름, name속성이 없어도 됨 -->
					    </div>
					    <div class="form-group">
                			<form:label path="password">Password</form:label>						    
						    <form:password path="password" cssClass="form-control" id="password"/>
					    	<form:errors path="password"></form:errors>
					    </div>
					  
					  <button type="submit" class="btn btn-primary">Submit</button>
                	
                	</form:form>
                </div>
            </div>
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
        </div>
    </div>
<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>