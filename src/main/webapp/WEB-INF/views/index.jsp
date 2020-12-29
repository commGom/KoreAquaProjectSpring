<%@page import="com.dto.QnaDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.dto.PageQnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>KoreAqua</title>
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
  
  <style type="text/css">
  
  #qnatest3{
   position: relative;
  float: left;
   padding-right: 30px;
    padding-left: 15px;
  }
  
  #qnatest2{
  position: relative;
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
   
    }
    
    .qnatest{
    padding-right: 15px;
    padding-left: 15px;
     
    float: right;
    
   
    -ms-flex-align: stretch!important;
    align-items: stretch!important;
	box-sizing: border-box;
    }
    ul li {list-style:none;
     }
    
    .table_t {
				text-align: center;
				margin:0;
				padding:0;
                width: 100%;
                display: table;
                border-collapse: collapse;
                table-layout:fixed;
                border: none;
                width:"500";
     heigh:"315";
                
            }
            .table_t .board {
                display: table-row;
            }
            .table_t #boardD  {
                display: table-cell;
                text-align:center;
                padding: 5px 0;
                 
                /*아래는 메뉴 영역을 구분하기 위한 옵션입니다.*/
                border-top:1px solid #69c;
                border-bottom:1px solid #69c;
            }
            .table_t #boardD_t {
		display: table-cell;
		background-color: #e6f9ff;
		text-align : center;
		font-weight: bold;
		padding: 5px 0;
		/*아래는 메뉴 영역을 구분하기 위한 옵션입니다.*/
		border-top: 1px solid #69c;
		border-bottom: 1px solid #69c;
		text-align: center;
	}
            /*아래는 메뉴 영역을 구분하기 위한 옵션입니다.*/
            .boardD+.boardD{border-left:1px solid #69c}
            
  </style>
  <meta content="" name="descriptison">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" >
  <link href="assets/vendor/icofont/icofont.min.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/venobox/venobox.css" rel="stylesheet" >
  <link href="assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="css/style.css" rel="stylesheet" >
  <link href="css/subscribe_css.css" rel="stylesheet" >
 

</head>

<body>

<jsp:include page="common/header.jsp"></jsp:include> 

  <section id="hero" >
    <jsp:include page="sildeshow/slideshow.jsp"></jsp:include>
    
  </section><!-- End Hero -->

  <main id="main">
<br>
    <!-- ======= Why Us Section ======= -->
    <section id="why-us" class="why-us">
      <div class="container">

        <div class="row">
          <div class="col-lg-4 d-flex align-items-stretch" data-aos="fade-right">
            <div class="content">
              <h3>더 자세한 정보 <br>
              알아보세요!</h3>
              <p>
               이미지 로고 클릭시 해당 기관 홈페이지로 이동합니다.
              </p>
              
            </div>
          </div>
          <div class="col-lg-8 d-flex align-items-stretch">
            <div class="icon-boxes d-flex flex-column justify-content-center">
              <div class="row">
                <div class="col-xl-4 d-flex align-items-stretch" data-aos="zoom-in" data-aos-delay="100">
                  <div class="icon-box mt-4 mt-xl-0">
                    <i ><a href="http://me.go.kr/home/web/main.do"> <img src="images/icon1.png" width="45" height="45"></a> </i>
                    <h4>환경부</h4>
                    <p>환경부는 자연환경, 생활환경의 보전, 환경오염방지, 수자원의 보전·이용, 개발에 관한 사무를 관장하는 대한민국의 중앙행정기관입니다.</p>
                  </div>
                </div>
                <div class="col-xl-4 d-flex align-items-stretch" data-aos="zoom-in" data-aos-delay="200">
                  <div class="icon-box mt-4 mt-xl-0">
                    <i ><a href="https://www.keco.or.kr/kr/main/index.do"> <img src="images/icon2.png" width="45" height="45"></a></i>
                    <h4>한국환경공단</h4>
                    <p>환경오염방지, 환경개선 및 자원순환을 촉진하는 사업을 추진하는 대한민국 환경부 산하 위탁집행형 준정부기관입니다.</p>
                  </div>
                </div>
                <div class="col-xl-4 d-flex align-items-stretch" data-aos="zoom-in" data-aos-delay="300">
                  <div class="icon-box mt-4 mt-xl-0">
                     <i ><a href="https://www.kwater.or.kr/main.do?s_mid=1"> <img src="images/icon3.png" width="90" height="45"></a></i>
                    <h4>한국수자원공사</h4>
                    <p>수자원을 종합적으로 개발, 관리하여 생활용수 등의 공급을 원활하게 하고 수질을 개선하는 대한민국의 공기업입니다.</p>
                  </div>
                </div>
              </div>
            </div><!-- End .content-->
          </div>
        </div>

<br>
<br>

<div class="row" >
<div id="qnatest">
<iframe id="qnatest3"  width="530" height="315" src="https://www.youtube.com/embed/bgsMD8utZPQ" frameborder="0" 
allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
allowfullscreen></iframe>
</div>
<div class="qnatest2" id="qnatest" style="width:500px; height:315px; float:left;margin-left:10px;" >
<h3  >Q&A</h3>

<nav class="table_t">
							<ul class="board">
								
								<li id="boardD_t">제목</li>
								<li id="boardD_t">작성일</li>
								
							</ul>
							
							<c:forEach var="x" items="${list}" end="4">
							
							<ul class="board">
								
								<li id="boardD" style="text-align: left"><c:forEach begin="1" end="${x.repIndent }"><%= "&nbsp;&nbsp;" %></c:forEach>
									<a href="${pageContext.request.contextPath}/qna/num/${x.num}"><strong>${x.title }</strong></a></li>
									<li id="boardD">${x.writeday }</li>
								
							</ul>
							
							</c:forEach>
							
							
							</nav>
</div>
						

</div>
<br>


      </div>
    </section><!-- End Why Us Section -->




  </main><!-- End #main -->

<jsp:include page="common/footer.jsp"></jsp:include> 

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
  <script src="js/main.js"></script>

</body>

</html>
