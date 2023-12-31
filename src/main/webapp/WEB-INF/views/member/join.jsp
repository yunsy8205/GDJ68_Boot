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
                	<!-- form에 form이 그냥 form태그 대체 -->
                	<form:form modelAttribute="memberVO" method="post" enctype="multipart/form-data">
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
					    <div class="form-group">
                			<form:label path="passwordCheck">PasswordCheck</form:label>						    
						    <form:password path="passwordCheck" cssClass="form-control" id="passwordCheck"/>
					    	<form:errors path="passwordCheck"></form:errors>
					    </div>
					    <div class="form-group">
                			<form:label path="name">Name</form:label>						    
						    <form:input path="name" cssClass="form-control" id="name"/>
					    	<form:errors path="name"></form:errors>
					    </div>
					    <div class="form-group">
                			<form:label path="email">Email</form:label>						    
						    <form:input path="email" cssClass="form-control" id="email"/>
					    	<form:errors path="email"></form:errors>
					    </div>
					    <div class="form-group">
                			<form:label path="birth">Birth</form:label>						    
						    <form:input path="birth" cssClass="form-control" id="birth"/>
					    	<form:errors path="birth"></form:errors>
					    </div>
					    <div class="form-group">
					    <label for="photo">Photo</label>
					    <input type="file" class="form-control" id="photo" name="photo" aria-describedby="photo">
					    <small id="photoHelp" class="form-text text-muted">We'll never share your email with anyone else.</samll>
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