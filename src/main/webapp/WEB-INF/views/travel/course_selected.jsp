<%@page import="com.dto.CourseDTO"%>
<%@page import="com.dto.CoursePageDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>물과 여행 정보</title>
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
<!-- Template innerPage CSS File -->
<link href="css/travel.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../common/header.jsp" flush="ture"></jsp:include>
	<main class="main">
	<!-- ======= Breadcrumbs ======= -->
		<section class="breadcrumbs">
			<div class="container">
				<div class="d-flex justify-content-between align-items-center">
					<h2></h2>
					<ol>
						<li><a href="<c:url value='/' />">Home</a></li>
						<li>물과 여행 정보</li>
						<li>강 따라</li>
					</ol>
				</div>
			</div>
		</section>
		<!-- End Breadcrumbs -->
		<!-- ======= MainPage ======= -->	
		<section class="MainPage"> 
		<div id="recommended">추천코스</div>
		<p class="course_explain">강을 위주로 한 여행코스를 소개합니다.
			<strong style="color:#0569b4">권역을 선택하시면 추천 여행코스를 보실 수</strong>  있습니다.
		</p>
		<!-- content -->
		<div class="content">
			<ul class="content_map">				
				<li class="Map">  
					<div class="map_fig"> 
						<img src="images/map/비활성화.JPG" id="map" usemap="#map01" style="border:2px solid #eeeeee">
						<map name="map01" id="map01">  
							<area shape="rect" coords="215,110,140,75" alt="한강권역" 
								href="Course_Selected?course=ha"></area>
							<area shape="rect" coords="93,213,167,242" alt="금강권역"   
								href="Course_Selected?course=gu" /></area>
							<area shape="rect" coords="200,225,287,253" alt="낙동강권역"  
								href="Course_Selected?course=nd" /></area>
							<area shape="rect" coords="35,335,123,370" alt="영산강권역"
								href="Course_Selected?course=ys" /></area>
							<area shape="rect" coords="95,305,185,335" alt="섬진강권역"
								href="Course_Selected?course=sj" /></area>
						</map>  
					</div>  
				</li>   
			</ul> 
				</div> <!-- end content -->
		<!-- course -->
		<div id="main_course"> 
			<h5 class="course_subtit">추천코스</h5>				
			<c:forEach var="xx" items="${list }" varStatus="status">
				<c:if test="${xx.rImage != null}">
					<ul class="travel_thum"> 
						<li><img src="images/ha/${xx.rImage }.JPG"> 
							<strong>${xx.title}</strong>
							<p>${xx.course}</p> 
						</li>
					</ul> 
				</c:if>
			</c:forEach>
		</div> <!-- end course -->
		</section> <!-- End MainPage -->
		<ul>
			<li class="page">
				<c:set var="curPage" value="${curPage }" /> 
				<c:set var="perPage" value="${perPage }" /> 
				<c:set var="totalCount" value="${totalCount }" />
				<c:if test="totalCount % perPage != 0">
					<c:set var="totalCount" value="${totalCount+1 }"/>
				</c:if>
				<c:set var="TotalPage" value="${totalCount/perPage }" />
				<c:set var="totalPage" value="${TotalPage+(1-(TotalPage%1))%1}" />
				<fmt:parseNumber var="totalPage" type="number" value="${totalPage}" />
				<c:set var="PageBlock" value="10" /> 
				<c:set var="PrevBlock" value="${ ((curPage - 1) / PageBlock) * PageBlock}" />
				<c:set var="nextBlock" value="${PrevBlock+PageBlock+1 }" />
				<fmt:parseNumber var="nextBlock" type="number" value="${nextBlock}" />
									
				<!--########## 이전 페이지 링크 출력 ############ -->
				<c:if test="${ curPage > 2 }">
					<a href="Course_Selected?course=${course }&curPage=${ 1 }">[1 페이지로 ]</a>&nbsp;
				</c:if> 
				<c:if test="${ curPage > 10 }">
					<a href="Course_Selected?course=${course }&curPage=${ curPage - 10 }">[이전 10 페이지 ]</a>&nbsp;
				</c:if> 
				<c:if test="${ curPage > 1 }">
					<a href="Course_Selected?course=${course }&curPage=${ curPage - 1 }">[이전 페이지 ]</a>&nbsp;
				 </c:if> 
				<!--########## 페이지 출력 ############ --> 
				<c:forEach var="counter" begin="${curPage}" end="${nextBlock-1}">
					<c:set var="counter" value="${counter-4 }"/>
					<c:if test="${5-curPage>0 }">
						<c:set var="counter" value="${5-curPage+counter }"/>
					</c:if>
					<c:if test="${totalPage < 5 }"> 
						<c:set var="counter" value="${counter - curPage + 4}"/>
					</c:if>	
					<c:if test="${totalPage - curPage < 5}">
						<c:set var="counter" value="${counter - 4 + totalPage - curPage }" />
					</c:if>										
					<c:if test="${ counter <= totalPage }"> 
						<c:choose>
						<c:when test="${ counter == curPage }">
							<a class="curPage" href="Course_Selected?course=${course }&curPage=${ curPage }">[${counter}]</a>&nbsp;
						</c:when>
						<c:when test="${ counter != curPage }">
							<a href="Course_Selected?course=${course }&curPage=${counter}">[${counter}]</a>&nbsp;
						</c:when> 
						</c:choose>
					</c:if>
				</c:forEach> 
									
				<!--########## 다음 10 페이지 링크 출력 ############ -->
				<c:if test="${ curPage < totalPage  }">
					<a href="Course_Selected?course=${course }&curPage=${ curPage+1  }">[다음 페이지]</a>&nbsp;
				<c:if test="${ totalPage > 10  }">
					<a href="Course_Selected?course=${course }&curPage=${ curPage+10  }">[다음 10페이지]</a>&nbsp;
				</c:if>
					<a href="Course_Selected?course=${course }&curPage=${ totalPage  }">[마지막 페이지]</a>
				</c:if>
			</li> 
		</ul> <!-- end paging -->
	</main>
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
