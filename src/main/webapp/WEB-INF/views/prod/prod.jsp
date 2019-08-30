<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
<script>
	$(document).ready(function(){
		$('#prodLgu').on('change',function(){
			console.log('prodLgu');
			$('#frm').submit();
			
		});
	})

</script>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>사용자 리스트</title>
	
	<!-- css, js -->
	<%@include file="/WEB-INF/views/common/basicLib.jsp" %>
</head>

<body>

	<!-- header -->
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	
	<div class="container-fluid">
		<div class="row">
			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp" %>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="row">
				<div class="col-sm-8 blog-main">
					<h2 class="sub-header">사용자</h2>
						<form  id="frm" action="${cp }/prod/prodList" method="post">
							<select id="prodLgu" name="prodLgu">
								<option value = "all" <c:if test="${prodLgu == 'all' }">selected</c:if>>전체리스트</option>
								<option value = "P101" <c:if test="${prodLgu == 'P101' }">selected</c:if>>P101</option>
								<option value = "P102" <c:if test="${prodLgu == 'P102' }">selected</c:if>>P102</option>
								<option value = "P201" <c:if test="${prodLgu == 'P201' }">selected</c:if>>P201</option>
								<option value = "P202" <c:if test="${prodLgu == 'P202' }">selected</c:if>>P202</option>
								<option value = "P301" <c:if test="${prodLgu == 'P301' }">selected</c:if>>P301</option>
								<option value = "P302" <c:if test="${prodLgu == 'P302' }">selected</c:if>>P302</option>
							</select>
						</form>
					<div class="table-responsive">
						
						<table class="table table-striped">
							<tr>
								<th>prod_id</th>
								<th>prod_name</th>
								<th>prod_lgu</th>
							</tr>
							
						
							<c:forEach items="${prodList }" var="prodList">
								<tr>
									<td>${prodList.prod_id }</td>
									<td>${prodList.prod_name }</td>
									<td>${prodList.prod_lgu }</td>
									<td></td>
								</tr>
							
							</c:forEach>
							
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>