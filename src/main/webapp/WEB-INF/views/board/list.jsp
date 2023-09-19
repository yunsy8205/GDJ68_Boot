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
	                <h1>List Page</h1>
	                <div class="card shadow mb-4">
	                	<div class="card-body">
	                		<div class="table-responsive">
	                			<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th>No</th>
											<th>Title</th>
	                						<th>Writer</th>
	                						<th>Date</th>
	                						<th>Hit</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="vo">
											<tr>
												<td>${vo.boardNo}</td>
												<td>${vo.boardTitle}</td>
												<td>${vo.boardWriter}</td>
												<td>${vo.boardDate}</td>
												<td>${vo.boardHit}</td>
											</tr>
										</c:forEach>
									</tbody>
	                			</table>
	                		</div>
	                	</div>
	                </div>
                
                </div>
            </div>
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
        </div>
    </div>
<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>