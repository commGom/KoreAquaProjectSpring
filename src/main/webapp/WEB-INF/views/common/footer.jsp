<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta property="og:url"           content="http://192.168.0.12:8095/KoreAquaProject/index.jsp" />
<meta property="og:type"          content="website" />
<meta property="og:title"         content="KoreAqua" />
<meta property="og:description"   content="수질조회 사이트 " />
<meta property="og:image"         content="https://images.pexels.com/photos/1346155/pexels-photo-1346155.jpeg?cs=srgb&dl=pexels-lisa-fotios-1346155.jpg&fm=jpg" />

<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script  src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type='text/javascript'>
//페이스북 링크 전달 (!!! 고정아이피 사용이 필요해, 현재는 작동X. 추후 호스팅 후 변경 예정!!!)
function sharefacebook(url) {  
    window.open("http://www.facebook.com/sharer/sharer.php?u=" + encodeURIComponent(url), "_blank", "width=600,height=400");  
}

//트위터 링크 전달
 function sharetwitter() {  
        //window.open("https://twitter.com/intent/tweet?text=" + text + "&url=" + url);  
        
        var content = "KoreAqua(수질조회페이지)";
        var link = "http://localhost:8073/KoreAquaProject/index.jsp";
        var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";
        var wp = window.open("http://twitter.com/share?url=" + encodeURIComponent(link) + "&text=" + encodeURIComponent(content), 'twitter', popOption); 
        if ( wp ) {
          wp.focus();
        }
    }  
   
    
//카카오 링크 전달
function sendLink(){
  //<![CDATA[
    // // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('9b86803bb8bb0ea0ae4f38646fba7209');
    // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
    Kakao.Link.createDefaultButton({
      container: '#kakao-link-btn',
      objectType: 'location',
       address: 'KoreAqua',
      addressTitle: 'KoreAqua', 
      content: {
        title: 'KoreAqua',
        description: '수질 조회 사이트',
        imageUrl: 'https://images.pexels.com/photos/1346155/pexels-photo-1346155.jpeg?cs=srgb&dl=pexels-lisa-fotios-1346155.jpg&fm=jpg',
        link: {
          mobileWebUrl: 'http://localhost:8073/KoreAquaProject/index.jsp',
          webUrl: 'http://localhost:8073/KoreAquaProject/index.jsp'
        }
      },
      social: {
        likeCount: 286,
        commentCount: 45,
        sharedCount: 845
      },
      buttons: [
        {
          title: '웹으로 보기',
          link: {
            mobileWebUrl: 'http://localhost:8073/KoreAquaProject/index.jsp',
            webUrl: 'http://localhost:8073/KoreAquaProject/index.jsp'
          }
        }
      ]
    });
}

//subscribe
$(document).ready(function(){
$(".btemail").click(function(){
	var useremail = $(".useremail").val();
	var x = "@";
	var bool=true;
	
	if(bool){
		$.ajax({
			url:"subscribemail",
			/* url:"<c:url value='/subscribemail'/>", */
			type:"post",
			dataType:"text",
			data:{"useremail":useremail},
			success: function(result){
				if(result.search(x)>0 ){
				
				alert("구독 성공하였습니다.");
				console.log(result);
				bool=false;}
				if (result.search(x)== -1){
					alert("구독 실패하였습니다. 올바른 이메일을 작성해주세요. ");
				}
			},
			error:function(xhr, status, error){
				alert("구독 실패하였습니다. 올바른 이메일을 작성해주세요. ");
				
			}
		});//end ajax
	}
	
});

});
</script>
</head>
<body>
<!-- ======= Footer ======= -->
  <footer id="footer">

    <div class="footer-top">
      <div class="container">
        <div class="row">

          <div class="col-lg-3 col-md-6 footer-contact">
            <h3>KoreAqua</h3>
            <p>
              환경부에서 제공하는 정보를 기반으로   <br>
              수질 정보 검색 서비스, 물과 관련된 여행정보와<br>
              용어의 정확한 의미가 담긴 사전 <br>
              서비스를 제공합니다.<br>
              <br>
              <strong>Github:</strong> https://github.com/velSeon/<br>&emsp;&emsp;&emsp;&nbsp;&nbsp;KoreAuaProjectSpring<br>
              <strong>Email:</strong> KoreAqua2020@gmail.com<br>
            </p>
          </div>

          <div class="col-lg-2 col-md-6 footer-links">
            <h4>Our service</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="index.jsp">Home</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="DaySearch.jsp">수돗물 수질 조회</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="MineralSWMark.jsjp">약수터 수질 조회</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="beachMapPresent.jsp">약수터 수질 조회</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="TravelServlet">물과 여행 정보</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="${pageContext.request.contextPath}/qna">Q & A</a></li>
            </ul>
          </div>

          <div class="col-lg-3 col-md-6 footer-links">
            <h4>자세한 정보가 궁금하다면?</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="https://ilovewater.or.kr/" target="_blank">물사랑누리집</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="http://www.wamis.go.kr/" target="_blank">국가수자원관리종합정보시스템</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="https://www.nier.go.kr/" target="_blank">국립환경과학원</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="https://www.kwwa.or.kr/" target="_blank">한국상하수도협회</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="http://water.nier.go.kr/" target="_blank">물환경정보시스템</a></li>
            </ul>
          </div>

          <div class="col-lg-4 col-md-6 footer-newsletter">
            <h4>Join Our Newsletter</h4>
            <p>수질에 대한 정보를 이메일로 받아보고 싶다면 구독을 눌러주세요!</p>
            <div class="newsletter-sub" >
            
             <input type="email"  name="useremail" id="useremail" class="useremail">           
              <input type="button" name="btemail" class="btemail" id="btemail" value="Subscribe">    
                            
             </div>
          </div>
          
        </div>
      </div>
    </div>

    <div class="container d-md-flex py-4">

      <div class="mr-md-auto text-center text-md-left">
        <div class="copyright">
          &copy; Copyright <strong><span>Bethany</span></strong>. All Rights Reserved
        </div>
        <div class="credits">
         
          Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
        </div>
      </div>
      <div class="social-links text-center text-md-right pt-3 pt-md-0">
        <a href="javascript:sharetwitter()" class="twitter"><i class="bx bxl-twitter"></i></a>
        <a href="javascript:sharefacebook('http://192.168.0.12:8073/KoreAquaProject/index.jsp')"  class="facebook"><i class="bx bxl-facebook"></i></a>
        <a href="javascript:sendLink()" id="kakao-link-btn"><i class="bx bxl-messenger"></i></a>
        <a href="https://github.com/velSeon/KoreAquaProject" class="github"><i class="bx bxl-github"></i></a>
        <!-- <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a> -->
      </div>
    </div>
  </footer>
  <!-- End Footer -->
  <a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>
  
</body>
</html>