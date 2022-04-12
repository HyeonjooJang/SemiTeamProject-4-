<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="url" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- CSS -->
<link rel="stylesheet" href="${url }/css/map_style.css">
<style>
#top {
	display: none;
}

.reviewList {
	overflow: hidden;
	list-style-type: none;
	font-size: 14px;
	color: #000;
	letter-spacing: -1px;
	margin: 0;
	list-style: none;
	overflow: hidden;
	position: relative;
	min-height: 52px;
	padding: 17px 0 18px;
	border-top: 1px solid #f2f2f2;
	border-bottin: 1px solid #ddd;
}
</style>
<!-- 카카오 api 라이브러리  -->
<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=096ec0036610b77d5b4e1aa8571cbb1e&libraries=services,clusterer,drawing"></script>
<title>map메인화면</title>
</head>
<body id="body-pd">
<<<<<<< HEAD
	<div class="l-navbar" id="navbar">
		<nav class="nav">
			<div>
				<div class="nav__brand">
					<ion-icon name="menu-outline" class="nav__toggle" id="nav-toggle"></ion-icon>
					<a href="#" class="nav__logo"></a>
				</div>

				<div class="nav__list">
					<a href="#" class="nav__link active"> <ion-icon
							name="home-outline" class="nav__icon"></ion-icon> <span
						class="nav_name">지도홈</span>
					</a> <a href="${url}/map/review" class="nav__link"> <ion-icon
							name="chatbubbles-outline" class="nav__icon"></ion-icon> <span
						class="nav_name">리뷰페이지</span>
					</a>
				</div>
				<a href="#" class="nav__link"> <ion-icon name="log-out-outline"
						class="nav__icon"></ion-icon> <span class="nav_name">이 페이지
						나가기</span>
				</a>
			</div>
		</nav>
	</div>

	<div class="map_wrap" style="position: relative;">
		<div style="z-index: 9">
			<form class="searching" onsubmit="searchPlaces(); return false;">
				<input type="text" name="query" placeholder="우리집 주변의 ${menu}"
					id="keyword">
				<button class="search-btn">검색</button>
			</form>
		</div>
		<div id="map"
			style="width: 100%; height: 100%; position: fixed; left: 0; top: 0; margin: 0 auto; z-index: 1"></div>
		<div id="menu_wrap" class="bg_white">
			<div class="option"></div>
			<ul id="placesList"></ul>
			<div id="pagination"></div>
		</div>
		<div id="review"
			style="margin: 2px; border: solid #20B2AA; float: left; display: block; width: 450px; height: 100%; position: relative; background-color: white; z-index: 1;">

			<hr />
			<div id="reviewcomment">
				<h5 style="height: 23px; font-size: 20px; line-height: 24px; text-align: center;">리뷰작성</h5>
				<div class="evaluation">
					<form id="evaluation" method="post" action="./save">
						<fieldset>
							<input type="radio" name="rating" value="5" id="rate1"><label
								for="rate1">⭐</label> <input type="radio" name="rating"
								value="4" id="rate2"><label for="rate2">⭐</label> <input
								type="radio" name="rating" value="3" id="rate3"><label
								for="rate3">⭐</label> <input type="radio" name="rating"
								value="2" id="rate4"><label for="rate4">⭐</label> <input
								type="radio" name="rating" value="1" id="rate5"><label
								for="rate5">⭐</label>
						</fieldset>
					</form>
					<div style="text-align: center;">
						<form method='post' id="repviewFrm">
							<textarea name="coment" id='coment'
								style="width: 100%; height: 80px;"></textarea>
							<input type="submit" value="리뷰 등록"/>
							
						</form>
					</div>
=======
   <div class="l-navbar" id="navbar">
      <nav class="nav">
         <div>
            <div class="nav__brand">
               <ion-icon name="menu-outline" class="nav__toggle" id="nav-toggle"></ion-icon>
               <a href="#" class="nav__logo"></a>
            </div>

            <div class="nav__list">
               <a href="#" class="nav__link active"> <ion-icon
                     name="home-outline" class="nav__icon"></ion-icon> <span
                  class="nav_name">지도홈</span>
               </a> <a href="${url}/map/review" class="nav__link"> <ion-icon
                     name="chatbubbles-outline" class="nav__icon"></ion-icon> <span
                  class="nav_name">리뷰페이지</span>
               </a>               
            </div>
            <a href="#" class="nav__link"> <ion-icon name="log-out-outline"
                  class="nav__icon"></ion-icon> <span class="nav_name">이 페이지
                  나가기</span>
            </a>
         </div>
      </nav>
   </div>
  
   <div class="map_wrap" style="position:relative;" >
    <div style="z-index:9">
      <form class="searching" onsubmit="searchPlaces(); return false;">
         <input type="text" name="query" placeholder="우리집 주변의 ${menu}" id="keyword">
         <button class="search-btn">검색</button>
      </form>
   </div>
   <div id="map" style="width: 100%; height: 100%; position: fixed; left:0;top:0;margin:0 auto;z-index:1"></div>
      <div id="menu_wrap" class="bg_white">
         <div class="option"></div>
         <ul id="placesList"></ul>
         <div id="pagination"></div>         
      </div>
   <div id="review" style="border:solid #20B2AA;display:none;float:left;width:300px;position:relative;z-index:1; height:100%;background-color: white;">                  
       <ul>
          <li>이미지 </li>
          <li>제목</li>
          <li>내용 </li>
            <li>작성자 | 작성일자</li>                  
         </ul>
         <ul>
            <li>이미지 </li>
            <li>제목</li>
            <li>내용 </li>
            <li>작성자 | 작성일자</li>                  
         </ul>
         <ul>
            <li>이미지 </li>
            <li>제목</li>
            <li>내용 </li>
            <li>작성자 | 작성일자</li>                  
         </ul>
         <ul>
            <li>이미지 </li>
            <li>제목</li>
            <li>내용 </li>
            <li>작성자 | 작성일자</li>                  
         </ul>
         <ul>
            <li>이미지 </li>
            <li>제목</li>
            <li>내용 </li>
            <li>작성자 | 작성일자</li>                  
         </ul>
         <hr/>
         <div id="reviewcomment">
			<h5 style="height: 23px; font-size:17px;line-height:24px; text-align: center;">리뷰 작성</h5>
			<div class="evaluation">
				<form id="evaluation" method="post" action="./save">
					<fieldset>
						<input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
						<input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
						<input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
						<input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
						<input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
					</fieldset>
				</form>
				<div style="text-align:center;">
					<form method='post' id="repviewFrm">
						<textarea name="coment" id='coment' style="width:100%; height:50px;"></textarea>
						<input type="submit" value="리뷰 등록";/>
					</form>
				</div>
			</div>      
      	</div>      
   </div>
   </div>
   
   <script>
      // 마커를 담을 배열입니다
      var markers = [];
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
      mapOption = {
         center : new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
         level : 3
      // 지도의 확대 레벨
      };
      // 지도를 생성합니다    
      var map = new kakao.maps.Map(mapContainer, mapOption);
      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places();
      var searchOption = {
         location : new kakao.maps.LatLng(37.564968, 126.939909),
         radius : 5000,
         size : 10
      };
      // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
         zIndex : 1
      });
      // 키워드로 장소를 검색합니다
      searchPlaces();
   
      // 키워드 검색을 요청하는 함수입니다
      function searchPlaces() {
         navigator.geolocation.getCurrentPosition(function(position) {
            var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
            var locPosition = {
               location : new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
               radius : 5000,
               size : 10
            };
            // 마커와 인포윈도우를 표시합니다        
            var keyword = document.getElementById('keyword').value;
            // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
            ps.keywordSearch('${menu}', placesSearchCB, locPosition);
         });
      }
      // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
      function placesSearchCB(data, status, pagination) {
         if (status === kakao.maps.services.Status.OK) {
            // 정상적으로 검색이 완료됐으면
            // 검색 목록과 마커를 표출합니다
            displayPlaces(data);
            // 페이지 번호를 표출합니다
            displayPagination(pagination);
         } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            alert('검색 결과가 존재하지 않습니다.');
            return;
         } else if (status === kakao.maps.services.Status.ERROR) {
            alert('검색 결과 중 오류가 발생했습니다.');
            return;
         }
      }
      // 검색 결과 목록과 마커를 표출하는 함수입니다
      function displayPlaces(places) {
         var listEl = document.getElementById('placesList'), menuEl = document
               .getElementById('menu_wrap'), fragment = document
               .createDocumentFragment(), bounds = new kakao.maps.LatLngBounds(), listStr = '';
         // 검색 결과 목록에 추가된 항목들을 제거합니다
         removeAllChildNods(listEl);
         // 지도에 표시되고 있는 마커를 제거합니다
         removeMarker();
         for (var i = 0; i < places.length; i++) {
            // 마커를 생성하고 지도에 표시합니다
            var placePosition = new kakao.maps.LatLng(places[i].y,
                  places[i].x), marker = addMarker(placePosition, i), itemEl = getListItem(
                  i, places[i]); // 검색 결과 항목 Element를 생성합니다
            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            bounds.extend(placePosition);
            // 마커와 검색결과 항목에 mouseover 했을때
            // 해당 장소에 인포윈도우에 장소명을 표시합니다
            // mouseout 했을 때는 인포윈도우를 닫습니다
            (function(marker, title) {
               kakao.maps.event.addListener(marker, 'mouseover',
                     function() {
                        displayInfowindow(marker, title);
                     });
               kakao.maps.event.addListener(marker, 'mouseout',
                     function() {
                        infowindow.close();
                     });
               itemEl.onmouseover = function() {
                  displayInfowindow(marker, title);
               };
               itemEl.onmouseout = function() {
                  infowindow.close();
               };
            })(marker, places[i].place_name);
            fragment.appendChild(itemEl);
         }
         // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
         listEl.appendChild(fragment);
         menuEl.scrollTop = 0;
         // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
         map.setBounds(bounds);
      }
      // 검색결과 항목을 Element로 반환하는 함수입니다
      // 음식점 이름 클릭시 마커로 이동하는것도 좋을듯?
      // 마커 클릭시 마커 모양 변화 주기?
      function getListItem(index, places) {
         var el = document.createElement('li'), itemStr = '<span class="markerbg marker_'
               + (index + 1)
               + '"></span>'
               + '<div class="info">'
               + '   <h5>' + places.place_name + '</h5>'  //음식점과 마커 링크 하기               
               + '<div> 4.3 | ' + '<input type="button" value="리뷰" onclick="toggleDiv()">'+ "123"+ "건" + '</input>' + '</div>';   //DB에서 별점평균, 리뷰 수 가져오기 + 별모양 css 만들기 
         if (places.road_address_name) {
            itemStr += '    <span>' + places.road_address_name + '</span>'
                  + '   <span class="jibun gray">' + places.address_name
                  + '</span>';
         } else {
            itemStr += '    <span>' + places.address_name + '</span>';
         }
         itemStr += '  <span class="tel">' + places.phone + '</span>'
               + '</div>';
         el.innerHTML = itemStr;
         el.className = 'item';
         return el;
      }   
      
      function toggleDiv() {
           const div = document.getElementById('review');
           
           if(div.style.display === 'block')  {
             div.style.display = 'none';
           }else {
             div.style.display = 'block';
           }
         }       
      
      // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
      function addMarker(position, idx, title) {
         var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
         imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
         imgOptions = {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset : new kakao.maps.Point(13, 37)
         // 마커 좌표에 일치시킬 이미지 내에서의 좌표
         }, markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
               imgOptions), marker = new kakao.maps.Marker({
            position : position, // 마커의 위치
            image : markerImage
         });
         marker.setMap(map); // 지도 위에 마커를 표출합니다
         markers.push(marker); // 배열에 생성된 마커를 추가합니다
         return marker;
      }
      // 지도 위에 표시되고 있는 마커를 모두 제거합니다
      function removeMarker() {
         for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
         }
         markers = [];
      }
      // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
      function displayPagination(pagination) {
         var paginationEl = document.getElementById('pagination'), fragment = document
               .createDocumentFragment(), i;
         // 기존에 추가된 페이지번호를 삭제합니다
         while (paginationEl.hasChildNodes()) {
            paginationEl.removeChild(paginationEl.lastChild);
         }
         for (i = 1; i <= pagination.last; i++) {
            var el = document.createElement('a');
            el.href = "#";
            el.innerHTML = i;
            if (i === pagination.current) {
               el.className = 'on';
            } else {
               el.onclick = (function(i) {
                  return function() {
                     pagination.gotoPage(i);
                  }
               })(i);
            }
            fragment.appendChild(el);
         }
         paginationEl.appendChild(fragment);
      }
      // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
      // 인포윈도우에 장소명을 표시합니다
      function displayInfowindow(marker, title) {
         var content = '<div style="padding:5px;z-index:1;">' + title
               + '</div>';
         infowindow.setContent(content);
         infowindow.open(map, marker);
      }
      // 검색결과 목록의 자식 Element를 제거하는 함수입니다
      function removeAllChildNods(el) {
         while (el.hasChildNodes()) {
            el.removeChild(el.lastChild);
         }
      }
   </script>
>>>>>>> 0605d22f4076ad5c585a690f66443ff201cd4fb1


					<table cellspacing="0" class="reviewList">
						<caption class="blind">리뷰 목록으로 별점, 이미지, 내용, 작성자, 작성일자
							정보를 제공</caption>
						
						<thead>
							<tr>
								<th>이미지</th>
								<th>내용</th>
								<th><span>작성자·작성일자</span></th>
							</tr>
						</thead>
						<tbody>

							<tr>
								<td class="lst_reviewimg">${vo.reviewimg }음식사진.jpg</td>
								<td class="lst_star">${vo.star}☆☆☆☆</td>
								<td class="lst_content">
									<br>최고의 떡볶이 ${vo.content }</td>
								<td class="lst_userid">이정은${vo.userid }<br>22.04.12${vo.writedate}</td>
							</tr>
							<tr>
								<td class="lst_reviewimg">${vo.reviewimg }음식사진.jpg</td>
								<td class="lst_star">${vo.star}☆☆</td>
								<td class="lst_content">
									<br>맛있는 아이스크림!${vo.content } </td>
								<td class="lst_userid">김이박${vo.userid }<br>22.03.08${vo.writedate}</td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
		</div>

		<script>
			// 마커를 담을 배열입니다
			var markers = [];
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
				level : 3
			// 지도의 확대 레벨
			};
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption);
			// 장소 검색 객체를 생성합니다
			var ps = new kakao.maps.services.Places();
			var searchOption = {
				location : new kakao.maps.LatLng(37.564968, 126.939909),
				radius : 5000,
				size : 10
			};
			// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
			var infowindow = new kakao.maps.InfoWindow({
				zIndex : 1
			});
			// 키워드로 장소를 검색합니다
			searchPlaces();

			// 키워드 검색을 요청하는 함수입니다
			function searchPlaces() {
				navigator.geolocation.getCurrentPosition(function(position) {
					var lat = position.coords.latitude, // 위도
					lon = position.coords.longitude; // 경도
					var locPosition = {
						location : new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
						radius : 5000,
						size : 10
					};
					// 마커와 인포윈도우를 표시합니다        
					var keyword = document.getElementById('keyword').value;
					// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
					ps.keywordSearch('${menu}', placesSearchCB, locPosition);
				});
			}
			// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
			function placesSearchCB(data, status, pagination) {
				if (status === kakao.maps.services.Status.OK) {
					// 정상적으로 검색이 완료됐으면
					// 검색 목록과 마커를 표출합니다
					displayPlaces(data);
					// 페이지 번호를 표출합니다
					displayPagination(pagination);
				} else if (status === kakao.maps.services.Status.ZERO_RESULT) {
					alert('검색 결과가 존재하지 않습니다.');
					return;
				} else if (status === kakao.maps.services.Status.ERROR) {
					alert('검색 결과 중 오류가 발생했습니다.');
					return;
				}
			}
			// 검색 결과 목록과 마커를 표출하는 함수입니다
			function displayPlaces(places) {
				var listEl = document.getElementById('placesList'), menuEl = document
						.getElementById('menu_wrap'), fragment = document
						.createDocumentFragment(), bounds = new kakao.maps.LatLngBounds(), listStr = '';
				// 검색 결과 목록에 추가된 항목들을 제거합니다
				removeAllChildNods(listEl);
				// 지도에 표시되고 있는 마커를 제거합니다
				removeMarker();
				for (var i = 0; i < places.length; i++) {
					// 마커를 생성하고 지도에 표시합니다
					var placePosition = new kakao.maps.LatLng(places[i].y,
							places[i].x), marker = addMarker(placePosition, i), itemEl = getListItem(
							i, places[i]); // 검색 결과 항목 Element를 생성합니다
					// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
					// LatLngBounds 객체에 좌표를 추가합니다
					bounds.extend(placePosition);
					// 마커와 검색결과 항목에 mouseover 했을때
					// 해당 장소에 인포윈도우에 장소명을 표시합니다
					// mouseout 했을 때는 인포윈도우를 닫습니다
					(function(marker, title) {
						kakao.maps.event.addListener(marker, 'mouseover',
								function() {
									displayInfowindow(marker, title);
								});
						kakao.maps.event.addListener(marker, 'mouseout',
								function() {
									infowindow.close();
								});
						itemEl.onmouseover = function() {
							displayInfowindow(marker, title);
						};
						itemEl.onmouseout = function() {
							infowindow.close();
						};
					})(marker, places[i].place_name);
					fragment.appendChild(itemEl);
				}
				// 검색결과 항목들을 검색결과 목록 Element에 추가합니다
				listEl.appendChild(fragment);
				menuEl.scrollTop = 0;
				// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
				map.setBounds(bounds);
			}
			// 검색결과 항목을 Element로 반환하는 함수입니다
			// 음식점 이름 클릭시 마커로 이동하는것도 좋을듯?
			// 마커 클릭시 마커 모양 변화 주기?
			function getListItem(index, places) {
				var el = document.createElement('li'), itemStr = '<span class="markerbg marker_'
						+ (index + 1)
						+ '"></span>'
						+ '<div class="info">'
						+ '   <h5>'
						+ places.place_name
						+ '</h5>' //음식점과 마커 링크 하기               
						+ '<div> 4.3 | '
						+ '<input type="button" value="리뷰" onclick="toggleDiv()">'
						+ "123" + "건" + '</input>' + '</div>'; //DB에서 별점평균, 리뷰 수 가져오기 + 별모양 css 만들기 
				if (places.road_address_name) {
					itemStr += '    <span>' + places.road_address_name
							+ '</span>' + '   <span class="jibun gray">'
							+ places.address_name + '</span>';
				} else {
					itemStr += '    <span>' + places.address_name + '</span>';
				}
				itemStr += '  <span class="tel">' + places.phone + '</span>'
						+ '</div>';
				el.innerHTML = itemStr;
				el.className = 'item';
				return el;
			}

			function toggleDiv() {

				const div = document.getElementById('review');
				if (div.style.display === 'block') {
					div.style.display = 'none';
				} else {
					div.style.display = 'block';
				}
			}

			// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
			function addMarker(position, idx, title) {
				var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
				imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
				imgOptions = {
					spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
					spriteOrigin : new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
					offset : new kakao.maps.Point(13, 37)
				// 마커 좌표에 일치시킬 이미지 내에서의 좌표
				}, markerImage = new kakao.maps.MarkerImage(imageSrc,
						imageSize, imgOptions), marker = new kakao.maps.Marker(
						{
							position : position, // 마커의 위치
							image : markerImage
						});
				marker.setMap(map); // 지도 위에 마커를 표출합니다
				markers.push(marker); // 배열에 생성된 마커를 추가합니다
				return marker;
			}
			// 지도 위에 표시되고 있는 마커를 모두 제거합니다
			function removeMarker() {
				for (var i = 0; i < markers.length; i++) {
					markers[i].setMap(null);
				}
				markers = [];
			}
			// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
			function displayPagination(pagination) {
				var paginationEl = document.getElementById('pagination'), fragment = document
						.createDocumentFragment(), i;
				// 기존에 추가된 페이지번호를 삭제합니다
				while (paginationEl.hasChildNodes()) {
					paginationEl.removeChild(paginationEl.lastChild);
				}
				for (i = 1; i <= pagination.last; i++) {
					var el = document.createElement('a');
					el.href = "#";
					el.innerHTML = i;
					if (i === pagination.current) {
						el.className = 'on';
					} else {
						el.onclick = (function(i) {
							return function() {
								pagination.gotoPage(i);
							}
						})(i);
					}
					fragment.appendChild(el);
				}
				paginationEl.appendChild(fragment);
			}
			// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
			// 인포윈도우에 장소명을 표시합니다
			function displayInfowindow(marker, title) {
				var content = '<div style="padding:5px;z-index:1;">' + title
						+ '</div>';
				infowindow.setContent(content);
				infowindow.open(map, marker);
			}
			// 검색결과 목록의 자식 Element를 제거하는 함수입니다
			function removeAllChildNods(el) {
				while (el.hasChildNodes()) {
					el.removeChild(el.lastChild);
				}
			}
		</script>


		<!-- IONICONS -->
		<script src="https://unpkg.com/ionicons@5.2.3/dist/ionicons.js"></script>
		<!-- JS -->
		<script src="../js/map.js"></script>
</body>
</html>