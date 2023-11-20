<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    request.setCharacterEncoding("UTF-8");
    %>
    
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "shinee.dao.SearchDAO" %>
<%@ page import = "shinee.vo.User_info_VO" %>

<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
        <title>SHINee Music</title>
        <link rel="stylesheet" type="text/css" href="css/search.css">
        <link rel="stylesheet" type="text/css" href="css/all.css">
    </head>
    <body>
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
                    <p style="margin-top:5px">Search</p>
                </div>
                <!-- 여기서부터 검색창 페이지 변경 -->
                <!-- 검색 카테고리 선택 버튼 -->
                <div style="width:1200px; height:30px; background-color: gray;">
                    <button id="music_searchButton"><strong>music</strong></button>
                    <button id="playlist_searchButton"><strong>playlist</strong></button>
                    <button id="user_searchButton"><strong>user</strong></button>
                </div>
                <article>
                    <div style="margin-top: 60px; text-align: center;">
                        <img src="img/샤이니로고.png" alt="샤이니뮤직로고" style="width:150px;">
                        
                       	<form method="post" name="search" action="user_search_result.jsp">
			                <input type="text"
			                    placeholder="사용자 검색" id= "searchText" name="searchText" minlength="2" maxlength="100">
			                <button type="submit">검색</button>
	                  	</form>
	                  	
                        <div class="division-line"></div>
                       
                    </div>
                </article>
            </section>
        </div><!--container-->
        
        <script>
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
			
			//검색 카테고리 이동 버튼
			document.getElementById("music_searchButton").addEventListener("click",()=>{
				window.location.href = "music_search.jsp";
			});
			
			document.getElementById("playlist_searchButton").addEventListener("click",()=>{
				window.location.href = "playlist_search.jsp";
			});
			
			document.getElementById("user_searchButton").addEventListener("click",()=>{
				window.location.href = "user_search.jsp";
			});
        </script>
    </body>
</html>