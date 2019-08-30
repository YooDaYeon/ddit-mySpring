<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>사용자 등록</title>
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	$(document).ready(function(){
		
		var msg = '${msg}';
		if(msg != '')
			alert(msg);
		
		//주소찾기 버튼 클릭 이벤트 핸들러
		$("#addrSearchBtn").on("click",function(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		            //주소 input value에 설정 data.roadAddress
		            //우편번호 input value에 설정 data.zonecode
		            console.log
		            $("#addr1").val(data.roadAddress);
		            $("#zipcd").val(data.zonecode);
		            
		            
		            
		        }
		    }).open();
			
		});
		
		//사용자 등록 버튼 클릭 이벤트 핸들러
		$("#userRegBtn").on("click",function(){
			//유효성 체크
			//console.log("userRegBtn click");
			
			var userId = $(this).data("userId");
			//$(this).find(".userId").text();
			//여기까지 도달하면 유효성 검사 완료(submit)
			$("#frm").submit();
			
			
			
		});
		
		//개발용 데이터 초기화 함수 ===== 추후 지우기
		//dataInit();
		
		
	});
	
function dataInit(){
	$("#userId").val("userTest");
	$("#name").val("꾸꾸");
	$("#alias").val("과자");
	$("#addr1").val("주소1번");
	$("#addr2").val("주소2번");
	$("#zipcd").val("45467");
	$("#birth").val("2019-05-31");
	$("#pass").val("userTest1234");
	
	
}
	

</script>
</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@include file="/WEB-INF/views/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자 등록</h2>
						
						<form id="frm" class="form-horizontal" role="form" action="${cp}/user/form"
							method="post"
							enctype="multipart/form-data">
							
							<div class="form-group">
								<label for="filename" class="col-sm-2 control-label">사용자 사진</label>
								<div class="col-sm-10">
									<input type="file" class="form-control" id="filename" name="profile">
								</div>
							</div>
							
							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label">사용자 아이디</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="userId" name="userId" 
									value="${param.userId }" placeholder="아이디를 입력하세요.">
									
								</div>
								<div class="col-sm-10">
									<form:errors path="userVo.userId"/>
								</div>
							</div>
							
							<div class="form-group">
								<label for="pass" class="col-sm-2 control-label">사용자 비밀번호</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="pass" name="pass" 
									value="${param.pass }" placeholder="비밀번호를 입력하세요."
									>
								</div>
							</div>

							<div class="form-group">
								<label for="name" class="col-sm-2 control-label">사용자 이름</label>
								<div class="col-sm-10">
								<input type="text" class="form-control" id="name" name="name" 
								value="${param.name }"placeholder="이름을 입력하세요.">
								<form:errors path="userVo.name"></form:errors>
								</div>
							</div>
							
							<div class="form-group">
								<label for="alias" class="col-sm-2 control-label">별명</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="alias" name="alias" 
									value="${param.alias}" placeholder="사용자 별명을 입력하세요.">
								</div>
							</div>
							
							<div class="form-group">
								<label for="addr1" class="col-sm-2 control-label">주소</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="addr1" name="addr1" 
									value="${param.addr1 }" placeholder="주소를 입력하세요." readonly>
								</div>
								<div class="col-sm-2">
									<button type="button" id="addrSearchBtn" class="btn btn-default pull-right">주소검색</button>
								
								</div>
							</div>
							
							<div class="form-group">
								<label for="addr2" class="col-sm-2 control-label">상세주소</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소를 입력하세요.">
								</div>
							</div>
							
							<div class="form-group">
								<label for="zipcd" class="col-sm-2 control-label">우편번호</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="zipcd" name="zipcd" 
									value="${param.zipcd }" placeholder="우편번호를 입력하세요." readonly>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">생일</label>
								<div class="col-sm-10">
									<input type="date" class="form-control" id="birth" name="birth" placeholder="생일을 입력하세요.">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button id="userRegBtn" type="button" class="btn btn-default">사용자 등록</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>