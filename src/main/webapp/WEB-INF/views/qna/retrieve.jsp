<%@page import="com.dto.QnaDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dto.AdminDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	AdminDTO login = (AdminDTO) session.getAttribute("adminLogin");
	QnaDTO qDTO = (QnaDTO) request.getAttribute("retrieve");
	System.out.println("jsp의 qDTO === " + qDTO);
	String title = qDTO.getTitle();
	String fileName = qDTO.getFileName();
%>

<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Q & A - 글 상세보기</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript">

	$(function() {
		
		// 삭제 버튼 눌렀을 때 Modal 띄우기
		$("#deleteBtn").on("click", function (e) {
			e.preventDefault();
			document.querySelector(".delete_modal_wrap").style.display ='block';
			document.querySelector("#deleteModal").style.display ='block';
	    	document.querySelector('.black_bg').style.display ='block';
		});
		
		// Modal의 x 버튼 눌렀을 때 닫기
		$('.update_modal_close').on("click", function () {
	    	document.querySelector(".update_modal_wrap").style.display ='none';
	    	document.querySelector('.black_bg').style.display ='none';
		});
		
		// Modal의 x 버튼 눌렀을 때 닫기
		$('.delete_modal_close').on("click", function () {
	    	document.querySelector(".delete_modal_wrap").style.display ='none';
	    	document.querySelector('.black_bg').style.display ='none';
		});
	
	});
		
		// 수정용 password check
		// 비밀번호 4자리 체크 후 PasswordCheck 비동기 처리
		// password 일치 시 제목, 내용 textarea 활성화
		function updatePwCheck(e, f){
			var passwd = document.getElementById("updatePasswd");
			$("#updateCheckBtn").on("click", function() {
				if(passwd.value.length != 4){
					e.preventDefault();
					alert("글 작성 시 입력한 비밀번호를 입력해 주세요.");
				} else {
					$.ajax({
						type: "post",
						url: "${pageContext.request.contextPath}/qna/pw-check",
						dataType: "text",
						data:{
							passwd: $("#updatePasswd").val(),
							num: $("#num").val()
						},
						success: function(data, status, xhr) {
							console.log("data = ", data);
							if(data == "match"){
								$("#title").removeAttr("disabled");
								$("#content").removeAttr("disabled");
								$("#uploadFile").removeAttr("disabled");
								document.querySelector(".update_modal_wrap").style.display = "none";
						    	document.querySelector('.black_bg').style.display = "none";
								if(fileName.value != null){
									document.querySelector('.deleteFile').style.display ="block";
								}
							} else {
								e.preventDefault();
								alert("비밀번호가 일치하지 않습니다.");
							}
						},
						error: function(xhr, status, e) {
							console.log(e);
						}
					});//end ajax
					return false;
				}// end else
			})//end function
		}//end updatePwCheck

		// 수정 기능
		function updateEvent(e, f){
			e.preventDefault();
			alert("수정되었습니다");
			f.action = "${pageContext.request.contextPath}/qna/update";
			f.submit();
		}//end updateEvent
		
		function updateBtnEvent(e, f) {
			if ($("#content").attr("disabled") == "disabled"){ // 처음 수정 버튼 눌렀을 때 -> updatePwCheck 호출 
				e.preventDefault();
				document.querySelector(".update_modal_wrap").style.display ='block';
				document.querySelector("#updateModal").style.display ='block';
		    	document.querySelector('.black_bg').style.display ='block';
		    	updatePwCheck(e, f);
			} else { // updatePwCheck 한 후 수정 버튼을 눌렀을 때 -> update
				e.preventDefault();
				updateEvent(e, f);
			}
		} // end updateBtnEvent
		
		// 삭제 기능
		function deleteEvent(e, f){
			var passwd = document.getElementById("deletePasswd");
			if(passwd.value.length != 4){
				e.preventDefault();
				alert("글 작성 시 입력한 비밀번호를 입력해 주세요.");
			} else {
				$.ajax({
					type: "post",
					url: "${pageContext.request.contextPath}/qna/pw-check",
					dataType: "text",
					data:{
						passwd: $("#deletePasswd").val(),
						num: $("#num").val()
					},
					success: function(data, status, xhr) {
						console.log("data = ", data);
						if(data == "match"){
							alert("삭제되었습니다.");
							f.action = "${pageContext.request.contextPath}/qna/delete";
							f.submit();
						} else {
							e.preventDefault();
							alert("비밀번호가 일치하지 않습니다.");
						}
					},
					error: function(xhr, status, e) {
						console.log(e);
					}
				});//end ajax
			}//end else
		}//end deleteEvent
		
		// 관리자 로그인 - 수정
		function adminUpdateEvent(e, f){
			if ($("#content").attr("disabled") == "disabled"){
				e.preventDefault();
				$("#title").removeAttr("disabled");
				$("#content").removeAttr("disabled");
				$("#uploadFile").removeAttr("disabled");
				if(fileName.value != null){
					document.querySelector('.deleteFile').style.display ="block";
				}
			} else { // 수정 처리
				e.preventDefault();
				updateEvent(e, f);
			}
		}//end function
		
		// 관리자 로그인 - 삭제
		function adminDeleteEvent(e, f){
			e.preventDefault();
			alert("삭제되었습니다.");
			f.action = "${pageContext.request.contextPath}/qna/delete";
			f.submit();
		}//end function
		
		// 파일 다운로드
		function onDownload(num, e) {
			e.preventDefault();
			location.href = "${pageContext.request.contextPath}/qna/download/num/" + num;
		}
		
		// 파일 삭제
		function fileDelete(num, e) {
			e.preventDefault();
			//location.href = "${pageContext.request.contextPath}/qna/file-delete/num/" + num;
			$.ajax({
				type: "post",
				url: "${pageContext.request.contextPath}/qna/file-delete",
				dataType: "text",
				data:{
					num: num
				},
				success: function(data, status, xhr) {
					console.log(data);
					document.querySelector("#attachedFile").style.display = "none";
					//document.querySelector("#attachedFile").load(location.href + "#attachedFile");
				},
				error: function(xhr, status, e) {
					console.log(e);
				}
			})
		}
		
</script>

<meta content="" name="descriptison">
<meta content="" name="keywords">

<!-- Favicons -->
<c:set var="path" value="${pageContext.request.contextPath}"/>

<link href="${path}/assets/img/favicon.png" rel="icon">
<link href="${path}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="${path}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${path}/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
<link href="${path}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
<link href="${path}/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="${path}/assets/vendor/venobox/venobox.css" rel="stylesheet">
<link href="${path}/assets/vendor/owl.carousel/assets/owl.carousel.min.css"	rel="stylesheet">
<link href="${path}/assets/vendor/aos/aos.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="${path}/assets/css/style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${path}/css/qna.css">

</head>
<body>

	<jsp:include page="../common/header.jsp" flush="true"></jsp:include>

		<!-- ======= Breadcrumbs ======= -->
		<section class="breadcrumbs" id="Breadcrumbs">
			<div class="container">

				<div class="d-flex justify-content-between align-items-center">
					<h2>게시글 상세 보기</h2>
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
	        <ul>
	          <li><a href="${pageContext.request.contextPath}/qna">Q & A</a></li>
	        </ul>
	      </nav>
	    </div>
	    
	<div class=" three_quarter">
		<form name="retForm" method="post" enctype="multipart/form-data">
		<!-- <input type="hidden" name="_method" value="DELETE"/> -->
		<input type="hidden" id="num" name="num" value="${retrieve.num}">
		<input type="hidden" id="fileName" name="fileName" value="${retrieve.fileName}">
			<nav class="table_t">
				<ul class="board">
		        	<li id="boardD_t">글번호</li>
	        		<li id="boardD_r">${retrieve.num}</li>
	        		<li id="boardD_t">조회수</li>
	                <li id="boardD_r">${retrieve.readCnt}</li>
		        </ul>
				<ul class="board">
					<li id="boardD_t">작성자</li>
					<li id="boardD_r" id="author" name="author">${retrieve.author}</li>
					<li id="boardD_t">작성일</li>
		            <li id="boardD_r">${retrieve.writeday}</li>
	            </ul>
            </nav>
            <nav class="table_t">
		    	<ul class="board">
		    		<li id="boardD_t_c">제목</li>
		            <li id="boardD_t_m">
		            	<textarea name="title" id="title" disabled>${retrieve.title}</textarea>
		            </li>
				</ul>
	            <ul class="board">
	                <li id="boardD_t_c">내용</li>
	                <li id="boardD_t_m" colspan="3">
	                	<textarea name="content" id="content" rows="20" disabled>${retrieve.content}</textarea>
		            </li>
            	</ul>
           	</nav>
           	<nav class="table_t">
		        <ul class="board">
		        	<li id="boardD_t">첨부파일</li>
		            <li id="boardD_r">
		            	<div id="attachedFile">
			            	<span><a href="#" id="downloadBtn" onclick="onDownload('${retrieve.num}', event)" style="float: left; width: 80%;">${retrieve.fileName}</a></span>
						<% if(fileName != null) { %> <!-- 파일이 있을 경우에만 x버튼 노출 -->
			            	<span class="deleteFile">
			            		<span id="deleteFile" onclick="fileDelete('${retrieve.num}', event) "style="float: right; width: 20%;">&times;</span>
			            	</span>
		            	<% } %>
			            	
		            	</div>
		            </li>
			        <li id="boardD_t" class="fileUpload">파일</li>
			        <li id="boardD_r" class="fileUpload" id="fileUpload"><input type="file" id="uploadFile" name="uploadFile" style="width: 240px" disabled></li>
		        </ul>
	        </nav>
			<br>
		<%
		
			// 관리자 로그인한 경우 pw 입력 없이 수정, 삭제 가능한 event 처리
			if(login != null) {
		%>	<div id="btnRight">
				<button class="button" id="adminUpdateBtn" onclick="adminUpdateEvent(event, retForm)">수정 </button>
				<button class="button" id="adminDeleteBtn" onclick="adminDeleteEvent(event, retForm)">삭제 </button>
			</div>
		<% 
			} else if(!title.equals("삭제된 게시글입니다.")) { // 삭제된 게시물이 아닌 경우에만 수정, 삭제 버튼 보이게 하기
		%>	<div id="btnRight">
				<button class="button" id="updateBtn" onclick="updateBtnEvent(event, retForm)">수정</button>
				<button class="button" id="deleteBtn">삭제</button>
			</div>
		<%
			} // end if
		%>
			
			<!-- 모달창 부분 -->
			<div class="black_bg"></div>
			<div class="update_modal_wrap">
				<div class="update_modal_close">
					<a href="#">close</a>
				</div>
				<div class="update_modal_wrap" id="updateModal">
					<div id="modal_text">
					<h5>&nbsp;글 작성 시 입력한 4자리 비밀번호를 입력하세요.</h5><br>
					<input type="password" id="updatePasswd" name="passwd" maxlength="4"><br>
					</div>
					<div id="btnRight">
					<button class="button" id="updateCheckBtn">확인</button>
					</div>
				</div>
			</div>
			<div class="delete_modal_wrap">
				<div class="delete_modal_close">
					<a href="#">close</a>
				</div>
				<div class="delete_modal_wrap" id="deleteModal">
					<div id="modal_text">
					<h5>&nbsp;글 작성 시 입력한 4자리 비밀번호를 입력하세요.</h5><br>
					<input type="password" id="deletePasswd" name="passwd" maxlength="4"><br>
					</div>
					<div id="btnRight">
					<button class="button" id="deleteCheckBtn" onclick="deleteEvent(event, retForm)">확인</button>
					</div>
				</div>
			</div>
		</form>
		
		<div id="btnRight">
			<button class="button" id="listBtn" onclick="location.href='${pageContext.request.contextPath}/qna'">목록</button>
		</div>
		<%
			// 로그인한 경우 답글 쓰기 버튼 보이게
			if(login != null) {
		%>	<div id="btnRight">
			<button class="button" id="replyBtn" onclick="location.href='${pageContext.request.contextPath}/qna/reply/num/${retrieve.num}'">답글 쓰기</button>
			</div><br>
		<%
			}// end if
		%>	
		<%-- <%@ include file="../include_body.jspf" %> --%>
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