<%@page import="com.dto.QnaDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="">

<head>


<title>Q & A - 글쓰기</title>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript">

	$(function() {
		$("#saveBtn").on("click", function() {
			var email = $("#email").val();
			if(!$("#title").val()){
				event.preventDefault();
				alert("제목을 입력해 주세요.");
				$("#title").focus();
				
			} else if(!$("#author").val()){
				event.preventDefault();
				alert("작성자명을 입력해 주세요.");
				$("#author").focus();
			
			} else if(!email){
				event.preventDefault();
				alert("이메일을 입력해 주세요.");
				$("#email").focus();
				
			} else if($("#passwd").val().length != 4){
				event.preventDefault();
				alert("4자리 비밀번호를 입력해 주세요.");
				$("#passwd").focus();
				
			} else if(!$("#content").val()){
				event.preventDefault();
				alert("내용을 입력해 주세요.");
				$("#content").focus();
			} else {
				if(!checkEmail(email)){ // 이메일 형식 체크
					event.preventDefault();
					alert("이메일 형식이 잘못되었습니다.");
					$("#email").focus();
				}
			}
			
		})
	})
	
	function checkEmail(m) {
		var regEmail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if(!regEmail.test(m)){
			return false;
		} else {
			return true;
		}
	}

</script>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<meta content="" name="descriptison">
<meta content="" name="keywords">

<!-- Favicons -->
<c:set var="path" value="${pageContext.request.contextPath}"/>

<link href="${path}/assets/img/favicon.png" rel="icon">
<link href="${path}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->

<link href="${path}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${path}/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
<link href="${path}/assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="${path}/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="${path}/assets/vendor/venobox/venobox.css" rel="stylesheet">
<link href="${path}/assets/vendor/owl.carousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="${path}/assets/vendor/aos/aos.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="${path}/assets/css/style.css" rel="stylesheet">
<link href="${path}/css/subscribe_css.css" rel="stylesheet" >
<link rel="stylesheet" type="text/css" href="${path}/css/qna.css">

</head>
<body>

	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>

	<!-- ======= Breadcrumbs ======= -->
	<section class="breadcrumbs" id="Breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>Q & A 글쓰기</h2>
				<ol>
					<li><a href="<c:url value='/' />">Home</a></li>
					<li>Q & A</li>
				</ol>
			</div>
		</div>
	</section>
	<!-- End Breadcrumbs -->

	<main id="main hoc contain">
		<div class="wrapper row3">
			<div class="hoc contain clear">
				<section class="inner-page">
				
					<div class="sidebar one_quarter first">
						<nav class="sdb_holder">
							<a href="${pageContext.request.contextPath}/qna">Q & A</a>
						</nav>
					</div>

					<div class=" three_quarter">
						<form action="${pageContext.request.contextPath}/qna/write" method="post" enctype="multipart/form-data">
							<nav class="table_t">
								<ul class="board">
									<li id="boardD_t">제목</li>
									<li id="boardD_w"><input type="text" id="title" name="title" placeholder="글 제목"></li>
								</ul>
								<ul class="board">
									<li id="boardD_t">작성자</li>
									<li id="boardD_w"><input type="text" id="author" name="author" maxlength="30"></li>
								</ul>
								<ul class="board">
									<li id="boardD_t">이메일</li>
									<li id="boardD_w"><input type="text" id="email" name="email" maxlength="30"></li>
								</ul>
								<ul class="board">
									<li id="boardD_t">비밀번호</li>
									<li id="boardD_w"><input type="password" id="passwd" name="passwd" maxlength="4"></li>
								</ul>
								<ul class="board">
									<li id="boardD_t">내용</li>
									<li id="boardD_w"><textarea name="content" id="content" rows="20" placeholder="글 내용"></textarea></li>
								</ul>
								<ul class="board">
									<li id="boardD_t">파일</li>
									<li id="boardD_w"><input type="file" id="uploadFile" name="uploadFile"></li>
								</ul>
							</nav>
							<br>
							<div id="btnRight">
								<button class="button" id="saveBtn">저장</button>
							</div>
						</form>
							<div id="btnRight">
								<button class="button" id="cancleBtn" onclick="location.href='${pageContext.request.contextPath}/qna'">취소</button>
							</div>
				</section>
			</div>
		</div>
	</main>
	<!-- End #main -->

	<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>

	<a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

	<!-- Vendor JS Files -->
	<script src="${path}/assets/vendor/jquery/jquery.min.js"></script>
	<script src="${path}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${path}/assets/vendor/jquery.easing/jquery.easing.min.js"></script>
	<script src="${path}/assets/vendor/php-email-form/validate.js"></script>
	<script src="${path}/assets/vendor/waypoints/jquery.waypoints.min.js"></script>
	<script src="${path}/assets/vendor/counterup/counterup.min.js"></script>
	<script src="${path}/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="${path}/assets/vendor/venobox/venobox.min.js"></script>
	<script src="${path}/assets/vendor/owl.carousel/owl.carousel.min.js"></script>
	<script src="${path}/assets/vendor/aos/aos.js"></script>

	<!-- Template Main JS File -->
	<script src="${path}/assets/js/main.js"></script>

</body>
</html>