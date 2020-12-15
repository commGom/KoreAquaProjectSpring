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
  //]]>
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
              <strong>Github:</strong> https://github.com/velSeon/KoreAqua<br>
              <strong>Email:</strong> KoreAqua@gmail.com<br>
            </p>
          </div>

          <div class="col-lg-2 col-md-6 footer-links">
            <h4>Useful Links</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="index.jsp">Home</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="DaySearch.jsp">일별 수질 조회</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="MineralSWMark.jsjp">약수터 수질 조회</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="beachMapPresent.jsp">약수터 수질 조회</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="TravelServlet">물과 여행 정보</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Q & A</a></li>
            </ul>
          </div>

          <!-- <div class="col-lg-3 col-md-6 footer-links">
            <h4>Our Services</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Web Design</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Web Development</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Product Management</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Marketing</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Graphic Design</a></li>
            </ul>
          </div> -->

          <div class="col-lg-4 col-md-6 footer-newsletter">
            <h4>Join Our Newsletter</h4>
            <p>수질에 대한 정보를 받아보고 싶다면 구독해주세요!</p>
            <form action="" method="post">
              <input type="email" name="email"><input type="submit" value="Subscribe">
            </form>
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