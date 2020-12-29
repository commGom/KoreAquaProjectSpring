<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Q & A - 답글 쓰기</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">
	function onDownload(num, e) {
		e.preventDefault();
		location.href = "${pageContext.request.contextPath}/qna/download/num/" + num;
	}
</script>

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
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>

	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>


		<!-- ======= Breadcrumbs ======= -->
		<section class="breadcrumbs" id="Breadcrumbs">
			<div class="container">

				<div class="d-flex justify-content-between align-items-center">
					<h2>Q & A 게시판</h2>
					<h3>답글 작성 화면</h3>
					<ol>
						<li><a href="index.jsp">Home</a></li>
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
							<a href="<c:url value='qna' />">Q & A</a>
						</nav>
					</div>
					<div class=" three_quarter">
			<form action="${pageContext.request.contextPath}/qna/reply" method="post" enctype="multipart/form-data">
				<input type="hidden" name="num" value="${replyUI.num}">
				<input type="hidden" name="repRoot" value="${replyUI.repRoot}">
				<input type="hidden" name="repStep" value="${replyUI.repStep}">
				<input type="hidden" name="repIndent" value="${replyUI.repIndent}">
				<nav class="table_t">
					<ul class="board">
						<li id="boardD_t">원글 번호</li>
						<li id="boardD_w">&nbsp;&nbsp;${replyUI.num}</li>
					</ul>
					<ul class="board">
						<li id="boardD_t">조회수</li>
						<li id="boardD_w">&nbsp;&nbsp;${replyUI.readCnt}</li>
					</ul>
					<ul class="board">
						<li id="boardD_t">제목</li>
						<li id="boardD_w"><input type="text" name="title" value="Re: ${replyUI.title}"></li>
					</ul>
					<ul class="board">
						<li id="boardD_t">작성자</li>
						<li id="boardD_w"><input type="text" name="author" value="관리자"></li>
					</ul>
					<ul class="board">
						<li id="boardD_t">내용</li>
						<li id="boardD_w"><textarea name="content" rows="20">${replyUI.content}</textarea></li>
					</ul>
					<ul class="board">
						<li id="boardD_t">파일</li>
						<li id="boardD_w"><input type="file" id="uploadFile" name="uploadFile"></li>
					</ul>
				</nav>
				<br>
				<div id="btnRight">
					<button class="button">답글 등록</button>
				</div>
			</form>
				<div id="btnRight">
					<button class="button" onclick="location.href='${pageContext.request.contextPath}/qna'">목록</button>
				</div>
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