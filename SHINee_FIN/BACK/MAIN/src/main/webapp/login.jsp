<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log in</title>
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link rel="stylesheet" type="text/css" href="css/all.css">
        <script>
        	function displayLoginFail(){
				alert("일치하는 회원정보가 없습니다");
			}
        </script>
	</head>
	<body>
        <div class="container" style="margin-top:30px;">
            <aside>
                <button id="gotoMyPageButton" style="margin-top: 250px;">
                    <img src="img/mypage.png" style="width:85px; padding:0;" alt="myPage Icon">
                    <p><strong>mypage</strong></p>
                </button>
                <button style="margin-top: 50px;">
                    <img id="gotoMyPlaylistButton" src="img/lookaround.png" style="width:85px; padding:0;" alt="lookaround Icon">
                    <p><strong>my<br>Playlist</strong></p>
                </button>
                <button id="gotoSearchButton" style="margin-top: 50px;">
                    <img src="img/pixel_search.png" style="width:85px; padding:0;" alt="Search Icon">
                    <p><strong>search</strong></p>
                </button>
            </aside>
            <section>
                <div class="bluetop" style="margin-top:20px; width:1200px;">
                    <p style="margin-top:5px">log in</p>
                </div>
                <div style="width:1200px; height:30px; background-color: gray;">
                </div>
                <article>
                    <div style="text-align: center; margin-top: 20%;">
                        <h3>Log in</h3><br>
                        <form action="LoginServlet" method="post">
	                        <input type="text" id="user_id" name="user_id" placeholder="ID" style="width:200px; height: 30px; margin-bottom:10px; border-style:none;"><br>
	                        <input type="password" id="user_password" name="user_password" placeholder="user_password" style="width:200px; height: 30px; margin-bottom:15px; border-style:none;"><br>
	                    	<input type="submit" value="로그인" style="background-color:blue; color:white; width:80px; height: 30px; border-style:none;">
                    	</form><br>
                    		<a href="Register_user.jsp" style="font-size:xsmall; margin-top:10px;">계정이 없으신가요?</a> <!-- 회원가입 페이지로 이동 -->
                    </div>
                </article>
            </section>
        </div><!--container-->
        <%
			//로그인이 실패했을 상황 가정 (로그인 실패시 보일 함수 호출)
			String loginError = (String)request.getAttribute("loginError");
		
			if(loginError != null){
		%>
			<script>
				displayLoginFail();
			</script>
		<%
			} //if(loginError != null)
		%>
		
			<script>
				//창 이동 함수
				
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