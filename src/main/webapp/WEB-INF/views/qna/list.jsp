<%@ page import="java.util.List"%>
<%@ page import="com.dto.QnaDTO"%>
<%@ page import="com.dto.PageQnaDTO"%>
<%@ page import="com.dto.AdminDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	PageQnaDTO pDTO = (PageQnaDTO)request.getAttribute("page");
	List<QnaDTO> list = pDTO.getList();
	String searchName = (String)request.getAttribute("searchName");
	String searchValue = (String)request.getAttribute("searchValue");
	
	int curPage = pDTO.getCurPage();
	int perPage = pDTO.getPerPage(); // 한 페이지당 게시글 개수 : 10개
	int pageBlock = 10; // 한 화면에 10개의 페이지 번호 보여주기
	int totalCount = pDTO.getTotalCount(); // 레코드 개수
	
	int totalPage = totalCount / perPage; // 한 화면에 보여줄 페이지 번호 개수
	
	if (totalCount % perPage != 0) totalPage++;
	
	int endNo = perPage * curPage;
	int startNo = endNo - perPage;
	int prevBlock = (int)Math.floor((curPage - 1) / pageBlock) * pageBlock;
	int nextBlock = prevBlock + pageBlock + 1;

	String mesg = (String) request.getAttribute("mesg"); // "로그인 성공" mesg
	AdminDTO login = (AdminDTO) session.getAttribute("adminLogin"); // 로그인 여부 체크
	System.out.println("login은? " + login);
	System.out.println("mesg는? " + mesg);
	
	if( mesg != null){
%>
	<script type="text/javascript">
		alert("<%=mesg%>");
	</script>
<%
	}
%>

<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Template Main CSS File -->
<c:set var="path" value="${pageContext.request.contextPath}"/>

<link href="${path}/assets/css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${path}/css/qna.css">

</head>
<body>

	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>

	<!-- ======= Breadcrumbs ======= -->
	<section class="breadcrumbs" id="Breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>Q & A 게시판</h2>
				<ol>
					<li><a href="<c:url value='/' />">Home</a></li>
					<li>Q & A</li>
				</ol>
			</div>
		</div>
	</section>
	<!-- End Breadcrumbs -->

	<!-- 전체 페이지 포함 -->
	<main id="main hoc contain">
		<div class="wrapper row3">
			<div class="hoc contain clear">
				<section class="inner-page">
				
					<div class="sidebar one_quarter first">
						<nav class="sdb_holder">
							<a href="<c:url value='qna' />">Q & A</a>
						</nav>
					</div>
					<div class="three_quarter">

						<h3>KoreAqua 이용과 관련해 궁금한 점이나 의견을 제시하는 공간입니다.</h3>

						<br> <br>
						<form action="qna">
							검색 조건 <select name="searchName" id="searchName">
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="author">작성자</option>
							</select> &nbsp;&nbsp;&nbsp;검색어
								<input type="text" name="searchValue" id="searchValue">&nbsp;&nbsp;<button id="searchBtn" class="button">검색</button>
						</form>
						<br>
						<nav class="table_t">
							<ul class="board">
								<li id="boardD_t">글번호</li>
								<li id="boardD_t">제목</li>
								<li id="boardD_t">작성자</li>
								<li id="boardD_t">작성일</li>
								<li id="boardD_t">조회수</li>
							</ul>
							<%
				if (list.size() == 0){
			%>
							</nav>
							<br>
							<h3 align="center">검색 결과가 없습니다.</h3>
							<%
				} else {
					for (int i = 1; i <= list.size(); i++) {
					QnaDTO qDTO = list.get(i - 1);
					int num = qDTO.getNum();
					int repIndent = qDTO.getRepIndent();
					String title = qDTO.getTitle();
					String author = qDTO.getAuthor();
					String writeday = qDTO.getWriteday();
					int readCnt = qDTO.getReadCnt();
			%>
							<ul class="board">
								<li id="boardD"><%= num %></li>
								<li id="boardD" style="text-align: left;"><c:forEach begin="1" end="<%= repIndent %>"><%= "&nbsp;&nbsp;" %></c:forEach>
									<a href="${pageContext.request.contextPath}/qna/num/<%= num %>"><strong><%= title %></strong></a></li>
								<li id="boardD"><%= author %></li>
								<li id="boardD"><%= writeday %></li>
								<li id="boardD"><%= readCnt %></li>
							</ul>

							<%
					} // end for
			%>
							<!-- 페이징 처리 부분 -->
							<br>
							<br>
							<ul>
								<div id="divPaging">

									<%
						if(prevBlock > 0) {
					%>
									<div id="edge">
										<a href="${pageContext.request.contextPath}/qna?searchValue=<%=searchValue%>&searchName=<%=searchName%>&curPage=<%= curPage - 1 %>">[이전페이지]</a>
									</div>
									<%
						}
					%>
									<%
					for(int i = 1 + prevBlock; i < nextBlock && i <= totalPage; i++){
						if(curPage == i){
					%>
									<div>
										<font size=4 color="red"><strong><%= i %></strong></font>
									</div>
									<%
						} else {
					%>
									<div>
										<a href="${pageContext.request.contextPath}/qna?searchValue=<%=searchValue%>&searchName=<%=searchName%>&curPage=<%= i %>"><%= i %></a>
									</div>
									<%
						}
					}
				%>

									<%
				 	if (totalPage >= nextBlock) {
				%>
									<div id="edge">
										<a href="${pageContext.request.contextPath}/qna?searchValue=<%=searchValue%>&searchName=<%=searchName%>&curPage=<%= nextBlock %>">[다음 페이지]</a>
									</div>
									<%
				 	}
				}
			 	%>
								</div>
							</ul>
						</nav>
						<div id="btnRight">
							<button class="button" onclick="location.href='${pageContext.request.contextPath}/qna/write'">글쓰기</button>
						</div>
						<br> <br>
						<%
			if (login != null) {
		%>
						<div>
							<button class="button" onclick="location.href='${pageContext.request.contextPath}/admin/logout'">로그아웃</button>
						</div>
						<%
			}
		%>
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