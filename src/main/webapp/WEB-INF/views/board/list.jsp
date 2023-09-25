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
												<td><a href="./detail?boardNo=${vo.boardNo}">${vo.boardTitle}</a></td>
												<td>${vo.boardWriter}</td>
												<td>${vo.boardDate}</td>
												<td>${vo.boardHit}</td>
											</tr>
										</c:forEach>
									</tbody>
	                			</table>
	                			<div class="row">
	                				<div class="col-sm-12 col-md-5">
	                				</div>
	                				<div class="col-sm-12 col-md-7">
	                				</div>
	                			</div>
	                		</div>
	                		<sec:authorize access="hasRole('ADMIN')">
								<a href="./add" class="btn btn-primary btn-icon-split">
		                            <span class="icon text-white-50">
		                            	<i class="fas fa-flag"></i>
		                            </span>
		                            <span class="text">글작성</span>
		                        </a>
		                    </sec:authorize>
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