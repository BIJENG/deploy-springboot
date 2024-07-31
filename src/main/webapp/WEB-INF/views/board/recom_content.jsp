<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page trimDirectiveWhitespaces="true"%> <%@ page
import="java.util.List" %> <%@ page import="com.example.myboard.vo.WineVO" %>
<%@ page import="com.example.myboard.vo.UserVO" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>와인 상세 정보</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      crossorigin="anonymous"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Noto+Sans+KR:wght@100..900&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" type="text/css" href="/css-bj/recom_content.css" />
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ab3471774620655a66deb82dddd44631&libraries=services,clusterer,drawing"
    ></script>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/user/navbar.jsp" %> <%@ include
    file="/WEB-INF/views/chat.jsp" %>
    <br />

    <div class="container wine_explain">
      <section class="recom_section0">
        <div class="section0_image">
          <img src="${wine.url}" alt="와인 이미지" />
        </div>
        <div class="section0_text">
          와인명
          <p class="text_font">${wine.name}</p>
          <br />
          원산지
          <p class="text_font">${wine.origin}</p>
          <br />
          알콜 도수
          <p class="text_font">${wine.proof}</p>
          <br />
          특성 <% WineVO wine = (WineVO) request.getAttribute("wine"); %>
          <div class="wine_char">
            <% if (wine.acidity == 1) { %>
            <div class="wine_property" style="background-color: #ff6666">
              신선한 뒷맛
            </div>
            <% } %> <% if (wine.balanced == 1) { %>
            <div class="wine_property" style="background-color: #66cc99">
              균형 잡힌 맛
            </div>
            <% } %> <% if (wine.body == 1) { %>
            <div class="wine_property" style="background-color: #3399ff">
              몸잡힌 맛
            </div>
            <% } %> <% if (wine.bouquet == 1) { %>
            <div class="wine_property" style="background-color: #ffcc66">
              풍미
            </div>
            <% } %> <% if (wine.buttery == 1) { %>
            <div class="wine_property" style="background-color: #ff9966">
              버터풍미
            </div>
            <% } %> <% if (wine.citrus == 1) { %>
            <div class="wine_property" style="background-color: #ff99ff">
              과일향
            </div>
            <% } %> <% if (wine.dry == 1) { %>
            <div class="wine_property" style="background-color: #ffcc99">
              드라이한 맛
            </div>
            <% } %> <% if (wine.earthy == 1) { %>
            <div class="wine_property" style="background-color: #996633">
              대지의 향
            </div>
            <% } %> <% if (wine.easy == 1) { %>
            <div class="wine_property" style="background-color: #ff9999">
              부드러운 입안 느낌
            </div>
            <% } %> <% if (wine.finish == 1) { %>
            <div class="wine_property" style="background-color: #ff6666">
              끝맛
            </div>
            <% } %> <% if (wine.flat == 1) { %>
            <div class="wine_property" style="background-color: #cccccc">
              단조한 맛
            </div>
            <% } %> <% if (wine.fruity == 1) { %>
            <div class="wine_property" style="background-color: #ffccff">
              과일의 향
            </div>
            <% } %> <% if (wine.green == 1) { %>
            <div class="wine_property" style="background-color: #66cc99">
              신선한 느낌
            </div>
            <% } %> <% if (wine.mouthfeel == 1) { %>
            <div class="wine_property" style="background-color: #ff99cc">
              입안의 감촉
            </div>
            <% } %> <% if (wine.spicy == 1) { %>
            <div class="wine_property" style="background-color: #ff9966">
              향이 나는 향신료
            </div>
            <% } %> <% if (wine.structure == 1) { %>
            <div class="wine_property" style="background-color: #6699ff">
              맛의 조화
            </div>
            <% } %> <% if (wine.tannins == 1) { %>
            <div class="wine_property" style="background-color: #996633">
              거친 맛
            </div>
            <% } %> <% if (wine.tobacco == 1) { %>
            <div class="wine_property" style="background-color: #996633">
              담배 향
            </div>
            <% } %> <% if (wine.vanilla == 1) { %>
            <div class="wine_property" style="background-color: #ffcc99">
              바닐라 향
            </div>
            <% } %>
          </div>
        </div>
      </section>

      <section class="recom_section1">
        <p class="section1-font">
          <span class="wineName">${wine.name}</span> 의 판매처 알아보기
        </p>
        <div id="map" style="width: 80%; height: 400px"></div>
        <ul id="wineShopList" class="wineShopListStyle"></ul>
        <!-- 와인샵 목록을 표시할 요소 -->
      </section>

      <section class="recom_section2">
        <div class="commentBox">
          <div class="comment-count">
            댓글 <span id="commentCount" class="comment-count-style"></span>
          </div>
        </div>
        <div>
          <form id="frmNewComment" class="mb-3">
            <input type="hidden" id="wineNo" name="wineNo" value="${wine.no}" />
            <div class="section2_comment">
              <textarea
                id="txtComment"
                name="commentContent"
                class="comment_css"
                placeholder="${wine.name}에 대한 솔직한 의견을 달아주세요"
                cols="120"
                rows="1"
                required
              ></textarea>
              <% UserVO userObject = (UserVO) session.getAttribute("USER");
              String userIdentifier = (userObject != null) ? userObject.getId()
              : null; %> <% if (userObject != null) { %>
              <button type="button" class="btn btn-secondary" id="btnComment">
                댓글달기
              </button>
              <% } else { %>
              <button type="button" class="btn btn-secondary" id="btnComment">
                로그인이 필요합니다.
              </button>
              <% } %>
            </div>
          </form>

          <!-- 댓글 목록 표시할 곳 -->
          <div class="section2_board">
            <ul id="comment-list" class="list-group"></ul>
          </div>
        </div>
      </section>
    </div>

    <script>
      const mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
          level: 6, // 지도의 확대 레벨
        };

      let map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성
      let infowindow = null; // 현재 열려 있는 인포윈도우를 저장할 변수
      let prevClickedMarker = null; // 이전에 클릭한 마커를 저장할 변수
      let ps = new kakao.maps.services.Places();

      shopMarkerImage = new kakao.maps.MarkerImage(
        "https://cdn-icons-png.flaticon.com/512/4698/4698562.png", // 와인샵 마커 이미지 경로
        new kakao.maps.Size(40, 40) // 마커 이미지의 크기
      );

      const myMarkerImageSrc =
        "https://cdn-icons-png.flaticon.com/512/6986/6986345.png"; // 새 마커 이미지 경로
      const myMarkerImageSize = new kakao.maps.Size(65, 65); // 새 마커 이미지 크기
      const myMarkerImage = new kakao.maps.MarkerImage(
        myMarkerImageSrc,
        myMarkerImageSize
      );

      // HTML5의 geolocation으로 사용할 수 있는지 확인
      if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옴
        navigator.geolocation.getCurrentPosition(function (position) {
          (lat = position.coords.latitude), // 위도
            (lon = position.coords.longitude); // 경도

          // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성
          (locPosition = new kakao.maps.LatLng(lat, lon)),
            (message = "<div>현재 위치</div>");

          // 마커와 인포윈도우를 표시
          displayMarker(locPosition, message, null);
          searchNearbyWineShops(locPosition); // 주변 와인샵 검색
        });
      } else {
        // GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정
        (locPosition = new kakao.maps.LatLng(33.450701, 126.570667)),
          (message = "geolocation을 사용할수 없어요..");

        displayMarker(locPosition, message, null);
      }

      // 지도에 마커와 인포윈도우를 표시하는 함수
      function displayMarker(locPosition, message, markerImage) {
        // 마커를 생성
        markerOptions = {
          map: map,
          position: locPosition,
          image: myMarkerImage,
        };
        if (markerImage) {
          markerOptions.image = markerImage; // 마커 이미지 설정
        }
        marker = new kakao.maps.Marker(markerOptions);

        (iwContent = message), // 인포윈도우에 표시할 내용
          (iwRemoveable = true);

        // 인포윈도우를 생성
        infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
          removable: iwRemoveable,
        });

        // 인포윈도우를 마커위에 표시
        infowindow.open(map, marker);

        // 지도 중심좌표를 접속위치로 변경
        map.setCenter(locPosition);
      }

      // 주변 와인샵 검색 함수
      function searchNearbyWineShops(locPosition) {
        ps.keywordSearch(
          "와인샵",
          function (data, status, pagination) {
            if (status === kakao.maps.services.Status.OK) {
              for (i = 0; i < data.length; i++) {
                displayPlaceMarker(data[i], shopMarkerImage, i);
              }
            }
          },
          {
            location: locPosition,
            radius: 5000, // 검색 반경 설정 (5km)
          }
        );
      }

      // 장소에 마커를 표시하는 함수
      function displayPlaceMarker(place, markerImage, index) {
        const marker = new kakao.maps.Marker({
          map: map,
          position: new kakao.maps.LatLng(place.y, place.x),
          image: markerImage, // 마커 이미지 설정
        });

        // 마커 클릭 이벤트 리스너
        kakao.maps.event.addListener(marker, "click", function () {
          // 이전에 클릭한 마커가 있고, 현재 클릭한 마커와 같은 경우에는 인포윈도우를 닫습니다.
          if (prevClickedMarker && prevClickedMarker === marker) {
            infowindow.close();
            prevClickedMarker = null;
            return;
          } // 이전에 클릭한 마커가 있으면 그 마커의 인포윈도우를 닫습니다.
          if (prevClickedMarker) {
            infowindow.close();
          }

          // 현재 클릭한 마커에 대한 인포윈도우를 열고, 이전에 클릭한 마커를 현재 클릭한 마커로 설정합니다.
          const content =
            '<div style="padding:5px;font-size:12px;">' +
            place.place_name +
            "</div>";
          infowindow.setContent(content);
          infowindow.open(map, marker);
          prevClickedMarker = marker;
        });

        // 마커 호버 이벤트 리스너
        kakao.maps.event.addListener(marker, "mouseover", function () {
          // 마우스를 가져다대면 인포윈도우를 표시
          infowindow.setContent(place.place_name);
          infowindow.open(map, marker);
        });

        // 마커 아웃 이벤트 리스너
        kakao.maps.event.addListener(marker, "mouseout", function () {
          // 마우스를 벗어나면 인포윈도우 닫기
          infowindow.close();
        });

        const shopList = document.getElementById("wineShopList");
        if (shopList) {
          // 새로운 리스트 아이템 생성
          const listItem = document.createElement("li");
          listItem.id = "shopItem-" + index; // 고유한 id 추가
          listItem.dataset.lat = place.y; // 와인샵 위도
          listItem.dataset.lng = place.x; // 와인샵 경도

          // 와인샵 이름 추가
          const nameElement = document.createElement("span");
          nameElement.textContent = place.place_name;
          nameElement.classList.add("wine-name"); // 클래스 추가
          listItem.appendChild(nameElement);

          // 와인샵 주소 추가
          if (place.road_address_name) {
            const addressElement = document.createElement("span");
            addressElement.textContent = place.road_address_name;
            addressElement.classList.add("wine-address"); // 클래스 추가
            listItem.appendChild(addressElement);
          } else if (place.address_name) {
            const addressElement = document.createElement("span");
            addressElement.textContent = place.address_name;
            addressElement.classList.add("wine-address"); // 클래스 추가
            listItem.appendChild(addressElement);
          }

          // 와인샵 번호 추가
          if (place.phone) {
            const phoneElement = document.createElement("span");
            phoneElement.textContent = place.phone;
            phoneElement.classList.add("wine-phone"); // 클래스 추가
            listItem.appendChild(phoneElement);
          }

          // 생성한 리스트 아이템을 리스트에 추가
          shopList.appendChild(listItem);

          // 리스트 아이템에 마우스 오버 이벤트 추가
          listItem.addEventListener("mouseover", function () {
            const lat = parseFloat(this.dataset.lat);
            const lng = parseFloat(this.dataset.lng);
            const locPosition = new kakao.maps.LatLng(lat, lng);
            const message = "<div>" + place.place_name + "</div>";

            // 지도를 해당 위치로 이동시키고 인포윈도우 표시
            displayMarker(locPosition, message, markerImage);
          });

          listItem.addEventListener("mouseout", function () {
            // 마우스를 벗어나면 인포윈도우 닫기
            infowindow.close();
          });
        }
      }
    </script>

    <script>
      (() => {
          const $frmNewComment = document.querySelector("#frmNewComment");
          const $btnComment = document.querySelector("#btnComment");
          const $txtComment = document.querySelector("#txtComment");
          const $commentBoard = document.querySelector(".section1_board");
          const $commentCount = document.getElementById("commentCount");

          function redirectToLogin() {
              window.location.href = "/user/login"; // 로그인 페이지로 이동
          }

          function loadCommentsWithAjax() {
              const wineNo = document.querySelector("#wineNo").value;

              fetch("/board/CommentlistProcess2", {
                  method: "POST",
                  headers: {
                      "Content-Type": "application/json",
                  },
                  body: JSON.stringify({ no: wineNo }),
              })
                  .then((response) => {
                      if (!response.ok) {
                          throw new Error("네트워크 응답에 문제가 있습니다.");
                      }
                      return response.json();
                  })
                  .then((comments) => {
                      displayComments(comments);
                      updateCommentCount(comments.length);
                  })
                  .catch((error) => {
                      console.error("댓글을 가져오지 못했습니다.", error);
                  });
          }

          function updateCommentCount(count) {
              // 댓글 개수 업데이트
              $commentCount.textContent = count; // 댓글 개수를 받아온 값으로 업데이트
          }

          document.addEventListener("DOMContentLoaded", function () {
              loadCommentsWithAjax();
          });

          function displayComments(comments) {
              const commentList = document.getElementById("comment-list");
              if (!commentList) {
                  console.error("Cannot find comment list container.");
                  return;
              }
              commentList.innerHTML = "";

              comments.forEach((comment) => {
                  const userId = comment.userid || "알 수 없는 사용자";
                  const content = comment.commentcontent || "내용 없음";
                  const regDate = comment.regdate || "날짜 없음";

                  const listItem = document.createElement("li");
                  listItem.className =
                      "list-group-item d-flex justify-content-between align-items-start";

                  listItem.innerHTML =
                      "<div>" +
                      "<strong>" + userId + "</strong>" +
                      "<p>" + content + "</p>" +
                      "</div>" +
                      '<small class="text-muted">' + regDate + "</small>";

                  commentList.appendChild(listItem);
              });
          }

          if ($btnComment) {
              $btnComment.addEventListener("click", () => {
                  const comment = $txtComment.value.trim();

                  if (comment === "") {
                      alert("댓글을 입력해주세요.");
                      return;
                  }

                  // 로그인 상태 확인
                  const userLoggedIn = <%= session.getAttribute("USER") != null %>;

                  if (userLoggedIn) {
                      // 로그인 상태인 경우
                      const formData = new FormData($frmNewComment);

                      fetch("/board/commentInsertProcess2", {
                          method: "POST",
                          body: formData,
                      })
                          .then((response) => {
                              if (response.ok) {
                                  loadCommentsWithAjax();
                                  $txtComment.value = "";
                              } else {
                                  alert("댓글 저장에 실패하였습니다.");
                              }
                          })
                          .catch((error) => {
                              console.error("댓글 저장 중 오류 발생:", error);
                              alert("댓글 저장 중 오류가 발생하였습니다.");
                          });
                  } else {
                      // 로그인 상태가 아닌 경우
                      const confirmed = confirm("댓글을 작성하려면 로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?");
                      if (confirmed) {
                          redirectToLogin(); // 로그인 페이지로 이동
                      }
                  }
              });
          }
      })();
    </script>
  </body>
</html>
