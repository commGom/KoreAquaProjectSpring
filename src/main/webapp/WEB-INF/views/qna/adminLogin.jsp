<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String mesg = (String) request.getAttribute("mesg");
   	if( mesg != null) {
%>
	<script type="text/javascript">
		alert('<%= mesg %>');
	</script>
<%
	}
%>
<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>관리자 전용</title>
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
<link rel="stylesheet" type="text/css" href="${path}/css/qna.css">
</head>
<body>

	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>

	<main id="main">

		<!-- ======= Breadcrumbs ======= -->
		<section class="breadcrumbs" id="Breadcrumbs">
			<div class="container">

				<div class="d-flex justify-content-between align-items-center">
					<h2>관리자 전용</h2>
					<ol>
						<li><a href="index.jsp">Home</a></li>
						<li>관리자 로그인</li>
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
								<a href="<c:url value='admin'/>">관리자 전용</a>
							</nav>
						</div>
						<div class="three_quarter">
							<form action="admin/login" method="post">
								<ul>
									<li>ID: <input type="text" name="userid" id="userid" class="login"></li>
									<li>PW: <input type="password" name="passwd" id="passwd" class="login" maxlength="4"></li>
								</ul>
								<button type="submit" class="button">로그인</button>
							</form>
							<br>
							<br>
						</div>
					</section>
				</div>
			</div>
		</main>
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