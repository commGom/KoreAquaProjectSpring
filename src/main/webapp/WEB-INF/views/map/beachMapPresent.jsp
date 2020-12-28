<%@page import="com.dto.BeachDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	.black_bg {
		display: none;
		position: absolute;
		content: "";
		width: 100%;
		height: 100%;
		background-color: rgba(0, 0, 0, 0.5);
		top: 0;
		left: 0;
		z-index: 1;
	}
	

	
	/* 공통옵션 */
	ul li {list-style:none; }

	.fl {float:left; }
	.tc {text-align:center; }
	.table_t {
				margin:0;
				padding:0;
                width: 100%;
                display: table;
                border-collapse: collapse;
                table-layout:fixed;
                border: none;
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
            /*아래는 메뉴 영역을 구분하기 위한 옵션입니다.*/
            .boardD+.boardD{border-left:1px solid #69c}
	
	input{
		width: 150px;
		padding: .5em .5em;
		border: 1px solid #999;
		border-radius: 0px;
		-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
	
	}
	select {
		width: 180px;
		padding: .5em .5em;
		border: 1px solid #999;
		font-family: inherit;
		background: url('images/select_img.jpeg') no-repeat 95% 50%;
		border-radius: 0px;
		-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		}
	select::-ms-expand {
    	display: none;
		}
		
	#searchBtn {
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
	margin-right: -4px;
	border: 1px solid skyblue;
	background-color: rgba(0, 0, 0, 0);
	color: skyblue;
	padding: 5px;
	font-size: 15px;
		}



	#btnDiv button:hover {
	color: white;
	background-color: skyblue;
	

	}
	
	h3{
font-size: 20px;
font-weight: 250;
	}
</style>
</head>
<body>

	<table>
	<tr>
	<td rowspan="3">
		<div id="map" style="width:500px;height:450px;margin-left:150px""></div>
		<div>
		<br>
			<h3>해수욕장수질 정보</h3>
			<form name="searchForm" id="searchForm">
			<input type="hidden" name="pMENUID" value="147" /> <input
				type="hidden" name="ATTR_1" id="ATTR_1" value="" /> <input
				type="hidden" name="ATTR_2" id="ATTR_2" value="" /> <input
				type="hidden" name="ATTR_3" id="ATTR_3" value="" />
			<div class="condition">
			<br><div>지역검색</div>
				<!-- 시도 -->
		<div style="float:left; margin-right:10px; margin-bottom:10px" >
		<select id="sidoNm" class="W150">
		<option value="" selected disabled hidden>도를 선택해주세요</option>
		<option name="sidoNm" value="강원">강원</option>
		<option name="sidoNm" value="경남">경남</option>
		<option name="sidoNm" value="경북">경북</option>
		<option name="sidoNm" value="부산">부산</option>
		<option name="sidoNm" value="울산">울산</option>
		<option name="sidoNm" value="인천">인천</option>
		<option name="sidoNm" value="전남">전남</option>
		<option name="sidoNm" value="전북">전북</option>
		<option name="sidoNm" value="제주">제주</option>
		<option name="sidoNm" value="충남">충남</option>
		</select>
		</div>

		<input type="text" id="staNm" name="staNm" style="width:45%; text-align:center;">
		<div id="btnDiv" style="float:right; margin-left:10px;"><button id="searchBtn" title="조 회">조 회</button></div>
		</form>
		</div>

			<nav class="table_t">
			
			<ul class="board">
			<li id="boardD">지역명</li>
			<li id="boardD">해 변<br></li>
			<li id="boardD">대장균</li>
			<li id="boardD">장구균 </li>
			<li id="boardD">적합여부</li>
			<li id="boardD">검사년도</li>
			<li id="boardD">길찾기</li>
			</ul>
			<ul class="board">
			<li id="boardD"><c:out value="${bdto.sidoNm}"/></li>
			<li id="boardD"><c:out value="${bdto.staNm}"/></li>
			<li id="boardD"><c:out value="${bdto.res1}"/></li>
			<li id="boardD"><c:out value="${bdto.res2}"/></li>
			<li id="boardD"><c:out value="${bdto.resYn}"/></li>
			<li id="boardD"><c:out value="${bdto.resYear}"/></li>
			<li id="boardD"><a href="https://map.kakao.com/link/to/<c:out value="${bdto.staNm}"/>,<c:out value="${bdto.lat}"/>,<c:out value="${bdto.lon}"/>" style="color:blue"><c:out value="${loc}"/></a></li>
			</ul>	
			</nav>
		</td>
	</tr>
	<tr>
	<td>
		<div id="staNm"></div>	
	</td>
	</tr>
</table>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=71d27f8c1b01c15ea6c3c1cb51315cad"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	
	var markers = [];
	// 마커를 생성하고 지도위에 표시하는 함수입니다

    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표 
        level : 14 // 지도의 확대 레벨 
    });
$("#sidoNm").on("change", function() {
	console.log($("#sidoNm").val());
	$.ajax({
		type : "GET",
		data : {
			sidoNm : $("#sidoNm").val()
		},
		url : 'BeachInfo',
		dataType : 'json',
		success : function(data) {
				var s= "";
				$("#staNm").empty();
			
				console.log("data => " + data);
				var lat;
				var lon;
			for(var i =0; i<data.length; i++){
				lat=data[0].lat;
				lon=data[0].lon
			}
			changeMapLoc(lat,lon);
			for(var i =0; i<data.length; i++){
				addMarker(new kakao.maps.LatLng(data[i].lat, data[i].lon),data[i].staNm,data[i].lat, data[i].lon,data[0].lat,data[0].lon); //
				s = "<br><li href='#' name=gugunNm>"+data[i].staNm+"&nbsp&nbsp&nbsp"+data[i].resNum+"&nbsp&nbsp&nbsp"+data[i].resLoc+"&nbsp&nbsp&nbsp"+data[i].resYn+"&nbsp&nbsp&nbsp"+data[i].resDate;
				console.log(data[i].staNm);
				console.log(data[i])
//				$("#staNm").append(s);
			
			}

			
			 // 지도에 표시된 마커 객체를 가지고 있을 배열입니다
			},
			error : (xhr, status, data)=>{
				console.log("error");
			}
});
});



	function changeMapLoc(lat,lon){
			map=new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
	        center : new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표 
	        level : 11 // 지도의 확대 레벨 
	    });
		 setMarkers(map);   
	}	
	 // 지도에 표시된 마커 객체를 가지고 있을 배열입니다

	function addMarker(position,staNm,lat,lng,lat0,lon0) {

	    var marker = new kakao.maps.Marker({
	        position: position
	    });

 	    // 마커가 지도 위에 표시되도록 설정합니다
	    marker.setMap(map);
	    // 생성된 마커를 배열에 추가합니다
	    markers.push(marker); 
		console.log("클릭이벤트적용할 marker값들"+marker);
	/* 	var map=new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
	        center : new kakao.maps.LatLng(lat0, lon0), // 지도의 중심좌표 
	        level : 11 // 지도의 확대 레벨 
	   
	    }); */
		
		 setMarkers(map);  
		 
			console.log("marker갯수:"+markers.length);
			for (var n = 0; n < markers.length; n ++) {
			    // 마커에 표시할 인포윈도우를 생성합니다 
			    var infowindow = new kakao.maps.InfoWindow({
			        content: '<div style="padding:5px;"><a href="Beach?staNm='+staNm+'" style="color:blue">'+staNm+'</a></div>', // 인포윈도우에 표시할 내용
			        removable : true
			    });
				kakao.maps.event.addListener(marker, 'click', makeClickListener(map, marker, infowindow));
				
		};

		
	}	
	
		
	  function makeClickListener(map, marker, infowindow) {
		    return function() {
		        infowindow.open(map, marker);
		    	console.log("click이벤트 실행중");
		    };
		}

	// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
	function setMarkers(map) {
	    for (var i = 0; i < markers.length; i++) {
	        markers[i].setMap(map);
	    }            
	}
	
	
	
	//조회 버튼 클릭 시 시도, 시군, 동 값 유효성 검사 후 DayDataServlet으로 이동
	$("#searchBtn").on("click", function () {
		
		var staNm =$("#staNm").val().trim();
		
		
		$("form").attr("action","Beach?staNm="+staNm);						
	});
</script>
</body>
</html>