<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.dto.DictionDTO"%>
<%@page import="com.dto.PageDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Inner Page - Bethany Bootstrap Template</title>
<meta content="" name="descriptison">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/venobox/venobox.css" rel="stylesheet">
<link href="assets/vendor/owl.carousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="assets/vendor/aos/aos.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">
</head>

<body>

	<jsp:include page="../common/header.jsp" flush="ture"></jsp:include>
	
	
	
	<main id="main"> <!-- ======= Breadcrumbs ======= -->
	<section class="breadcrumbs" id="Breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2></h2>
				<ol>
					<li><a href="<c:url value='/' />">Home</a></li>
					<li>Inner Page</li>
				</ol>
			</div>

		</div>
	</section>

	<section class="inner-page">
		<div class="content">
			<div class="container" style="text-align: center; font-size: 22px;">
				[검색할 단어를 입력하세요 ]</div>
			<form action="DInitial"
				style="width: 400px; height: 30px; margin: auto; padding-left: 50px;">
				<input type="text" name="searchValue" id="searchValue"
					class="searchValue" style="width: 80%; display: float;"> <input
					type="submit" value="검색" class="submit" style="height: 30px;">
			</form>
			
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td height="10" align="center" style="font-size: 22px;"><a
						href="Order?initial=ga">ㄱ</a> <a
						href="Order?initial=na">ㄴ</a> <a
						href="Order?initial=da">ㄷ</a> <a
						href="Order?initial=ra">ㄹ</a> <a
						href="Order?initial=ma">ㅁ</a> <a
						href="Order?initial=ba">ㅂ</a> <a
						href="Order?initial=sa">ㅅ</a> <a
						href="Order?initial=aa">ㅇ</a> <a
						href="Order?initial=ja">ㅈ</a> <a
						href="Order?initial=cha">ㅊ</a> <a
						href="Order?initial=ka">ㅋ</a> <a
						href="Order?initial=ta">ㅌ</a> <a
						href="Order?initial=pa">ㅍ</a> <a
						href="Order?initial=ha">ㅎ</a></td>
				</tr>
				<tr>
					<td>
						<table align="center" width="40%" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td height="5"></td>
							</tr>
							<tr>
								<td height="1" colspan="8" bgcolor="CECECE"></td>
							</tr>
							<td>
							<c:forEach var="xx" items="${list }" varStatus="status">
						
								<table>
									<tr>
										<td align="left"><strong>${xx.hNm}</strong> [${xx.cNm}
											${xx.eNm}]</td>
									</tr>

									<tr>
										<td height="10">
									</tr>

									<tr>
										<td class="explain" align="left">${xx.explain}</td>
									</tr>
									<tr>
										<td style='padding: 10px;'></td>
									</tr>
								</table>
								
							</c:forEach>
						
					<tr class="paging" style="text-align: center; font-size: 22px; padding-bottom: 30px;">

							<td>
								<c:set var="curPage" value="${curPage }" /> 
								<c:set var="perPage" value="${perPage }" />
								<c:set var="totalCount" value="${totalCount }" />
								<c:set var="TotalPage" value="${totalCount/perPage }" />
								<c:set var="totalPage" value="${TotalPage+(1-(TotalPage%1))%1 }" /> <!-- 올림  계산-->
								<c:set var="PageBlock" value="10" /> 
								<c:set var="PrevBlock" value="${ ((curPage - 1) / PageBlock) * PageBlock}" />
								<c:set var="nextBlock" value="${PrevBlock+PageBlock+1 }" />
								<fmt:parseNumber var="NextBlock" type="number" value="${nextBlock-1}" />							
									
								<!--########## 이전 페이지 링크 출력 ############ -->
						     	 <c:if test="${ curPage > 10 }">
									<a href="DInitial?searchValue=${searchValue}&curPage=${ curPage - 10}">[이전 페이지 ]</a>
				 		    	</c:if> 
									
								<!--########## 페이지 카운터 출력 ############ -->
								<c:forEach var="counter" begin="${curPage}" end="${nextBlock-1}">
																													
										<c:if test="${ counter <= totalPage }">
											<c:choose>
												<c:when test="${ counter == curPage }">
													<a href="DInitial?searchValue=${searchValue}&curPage=${counter }">[${counter }]</a>
												</c:when>

												<c:when test="${ counter != curPage }">
													<a href="DInitial?searchValue=${searchValue}&curPage=${counter }">[${counter }]</a>
												</c:when> 
											</c:choose>
										</c:if>
									</c:forEach> 
						
									
								<!--########## 다음 페이지 링크 출력 ############ -->
								 <c:if test="${ curPage < totalPage  }">
										<a href="DInitial?searchValue=${searchValue}&curPage=${ curPage + 10}">[다음 페이지]</a>
								</c:if>
							</td>
						</tr> <!-- end paging -->
						${totalPage }
								</td>
						
					</table><!-- 93td --> 
				</tr><!-- 92 tr -->
			</table><!-- 73 table -->
		</div>

	</section>
	</main>

	<!-- End #main -->

	<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>

	<a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/jquery.easing/jquery.easing.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>
	<script src="assets/vendor/waypoints/jquery.waypoints.min.js"></script>
	<script src="assets/vendor/counterup/counterup.min.js"></script>
	<script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="assets/vendor/venobox/venobox.min.js"></script>
	<script src="assets/vendor/owl.carousel/owl.carousel.min.js"></script>
	<script src="assets/vendor/aos/aos.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

</body>
</html>
