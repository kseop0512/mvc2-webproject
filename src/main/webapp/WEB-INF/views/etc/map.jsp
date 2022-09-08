<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MAP</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=b00v5gh0y0&submodules=geocoder"></script>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">지도 API</div>
		<h2>1. 다음 주소찾기 API</h2>
		<div class="address-wrap">
			<div style="display:flex;margin-bottom:20px;"> 
				<input type="text" name="postcode" id="postcode" class="input-form" readonly>
				<button class="btn bc1" onclick="searchAddr();" style="white-space:nowrap">주소찾기</button>
			</div>
			<input type="text" name="address" id="address" class="input-form" readonly><br>
			<input type="text" name="detailAddress" id="detailAddress" class="input-form"><br>
		</div>
		
		<hr>
		<h2>2. 네이버 지도 API</h2>
		<div id="map" style="width:100%;height:400px;"></div>
		<button onclick="showMap();" class="btn bc1">지도 옮기기</button>
	</div>
	
	<script>
	
		function searchAddr() {
			
			new daum.Postcode({
		        oncomplete: function(data) {
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		            $("#address").val(data.address);
		            $("#postcode").val(data.zonecode);
		            $("#detailAddress").focus();
		        }
		    }).open();
		}
		
		
		const map = new naver.maps.Map('map', {
			center: new naver.maps.LatLng(37.533837, 126.896836),
		    zoom : 15,
		    zoomControl : true,
		    zoomControlOptions : {
		    	position: naver.maps.Position.TOP_RIGHT,
				style : naver.maps.ZoomControlStyle.SMALL
		    }
			
		});
		
		const marker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(37.533837, 126.896836),
		    map: map
		});
		
		
		let contentString = [
			"<div class='iw_inner'>",
			"<h3>KH정보교육원</h3>",
			"<p>서울시 영등포구 선유동2로 57 이레빌딩 19F A강의장</p>",
			"</div>"
		].join("");
		
		let infoWindow = new naver.maps.InfoWindow();
		//마커 클릭이벤트 추가
		naver.maps.Event.addListener(marker, "click", function(e) {
			infoWindow = new naver.maps.InfoWindow({
				content : contentString
				
			});
			//생성된 infoWindow를 map의 marker위치에 생성
			infoWindow.open(map,marker);
		});
		
		//지도에 클릭이벤트 추가
		naver.maps.Event.addListener(map, "click", function(e) {
			console.dir(e);
			marker.setPosition(e.coord);
			if(infoWindow.getMap()) {//정보창이 지도위에 올라가 있으면
				infoWindow.close();
			}
			//위경도를 통해서 해당 위체의 주소를 알아내기
			naver.maps.Service.reverseGeocode({
				location : new naver.maps.LatLng(e.coord.lat(), e.coord.lng())
				
			}, function(status, response) {
				
				if (status !== naver.maps.Service.Status.OK) {
					return alert("주소를 찾을 수 없습니다.")
				}
				
				const address = response.result.items[1].address;
				contentString = [
					"<div class='iw_inner'>",
					"<p>" + address + "</p>",
					"</div>"
				].join("");
				
			})
		})
		
		
		function showMap() {
			const addr = $("#address").val();
			
			naver.maps.Service.geocode({
				address:addr
			}, function(status, response){
				if (status === naver.maps.Service.Status.ERROR) {
			        return alert("조회에러");
			    }
				
				console.log(response);
				const result = response.result;
				const items = result.items;
				const item = items[0].point;
	
				const latlng = new naver.maps.LatLng(item.y, item.x);
				map.setCenter(latlng);
				marker.setPosition(latlng);
			});
			
		}
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
	
</body>
</html>