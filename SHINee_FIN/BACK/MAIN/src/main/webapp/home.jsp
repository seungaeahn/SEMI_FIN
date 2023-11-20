<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<!-- 로그아웃 상태에서 처음 접속 화면 -->
		<title>SHINee Music Home</title>
		<link rel="stylesheet" type="text/css" href="css/login.css">
        <link rel="stylesheet" type="text/css" href="css/all.css">
	</head>
	<body>
		<%
			//user_id 값이 존재할 경우
			if(session.getAttribute("user_id") != null){
				response.sendRedirect("MyPlaylist.jsp");
			} else {
				//로그아웃 상태에서 보일 페이지 내용
		%>
	        <div class="container" style="margin-top:30px;">
	            <aside>
	                <button id="gotoMyPageButton" style="margin-top: 250px;">
	                    <img src="img/mypage.png" style="width:85px; padding:0;" alt="myPage Icon">
	                    <p><strong>mypage</strong></p>
	                </button>
	                <button id="gotoMyPlaylistButton" style="margin-top: 50px;">
	                    <img src="img/lookaround.png" style="width:85px; padding:0;" alt="lookaround Icon">
	                    <p><strong>my<br>Playlist</strong></p>
	                </button>
	                <button id="gotoSearchButton" style="margin-top: 50px;">
	                    <img src="img/pixel_search.png" style="width:85px; padding:0;" alt="Search Icon">
	                    <p><strong>search</strong></p>
	                </button>
	            </aside>
	            <section>
	                <div class="bluetop" style="margin-top:20px; width:1200px;">
	                    <p style="margin-top:5px">Update My Info</p>
	                </div>
	                <div style="width:1200px; height:30px; background-color: gray;">
	                </div>
	                <article>
	                    <div style="text-align: center; margin-top:8%">
	                        <img src="img/dino.gif" alt="샤이니뮤직 홈" style="width:300px;">
	                    </div>
	                    <div style="text-align: center; margin-top: 20px;">
	                        <h3>로그인 후 이용해주세요</h3><br>
	                        <button id="gotoLoginButton" style="width: 200px; padding: 10px; background-color: blue; color:white;">로그인 하기</button><br>
	                        <button id="joinButton" style="width: 200px; padding: 10px; margin-top: 5px;">회원가입 하기</button>
	                    </div>
	                </article>
	            </section>
	        </div><!--container-->			
		<%
			} //else (로그아웃 상태에서 보여질 창)
		%>
		<script>
			//창 이동 함수
			
			//로그인하기 버튼 누르면 로그인 창으로 이동
			document.getElementById("gotoLoginButton").addEventListener("click",()=>{
				window.location.href = "login.jsp";
			});
			
			//검색버튼 누르면 검색 창으로 이동 (기본 음악검색)
			document.getElementById("gotoSearchButton").addEventListener("click",()=>{
				window.location.href = "music_search.jsp";
			});
			
			//마이페이지 버튼 누르면 이동
			document.getElementById("gotoMyPageButton").addEventListener("click",()=>{
				window.location.href = "MyPage.jsp";
			});
			
			//마이플레이리스트 버튼
			document.getElementById("gotoMyPlaylistButton").addEventListener("click",()=>{
				window.location.href = "MyPlaylist.jsp";
			});
		</script>
	</body>
</html>