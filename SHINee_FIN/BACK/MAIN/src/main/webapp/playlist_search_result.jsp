<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%
    request.setCharacterEncoding("UTF-8");
%>
    
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "shinee.dao.SearchDAO" %>
<%@ page import = "shinee.vo.Music_info_VO" %>
<%@ page import = "shinee.vo.Playlist_info_VO" %>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
        <title>SHINee Music</title>
        <link rel="stylesheet" type="text/css" href="css/search.css">
        <link rel="stylesheet" type="text/css" href="css/all.css">
        <style>
        	#info_box1{
        		background-color:white; 
        		margin-top:10px; 
        		padding-right: 20px; 
        		width:200px; 
        		height: 22px; 
        		border: 2px inset lightgray;
        	}
        	#info_box{
        		background-color:white; 
        		margin-top:15px; 
        		padding-right: 20px; 
        		width:200px; 
        		height: 22px; 
        		border: 2px inset lightgray;
        	}
        </style>
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
                        
                       	<form method="post" name="search" action="playlist_search_result.jsp">
			                <input type="text"
			                    placeholder="플레이리스트 검색" id= "searchText" name="searchText" maxlength="100">
			                <button type="submit">검색</button>
	                  	</form>
	                  	
                        <div class="division-line"></div>
                       
                        <!--검색결과 올라가는 곳-->

                        <%
	                        SearchDAO searchDAO = new SearchDAO();
	                        ArrayList<Playlist_info_VO> playlistList = searchDAO.getSearchPlaylist(request.getParameter("searchText"));
	                                              		
	                        //검색결과 없을경우 보일 창
	                        if(playlistList.size() == 0){
                        %>
								<img src="img/텅.png" style="margin-top:100px; width:250px;">
								<script>
									alert("검색 결과가 존재하지 않습니다");
								</script>
						<%
							}
						                        	
							for(Playlist_info_VO p : playlistList) {
						%>           	       
								<div class="window" style="width: 500px; height:150px; margin-left: 350px; margin-bottom: 10px;">
									<div class="title-bar">
										<div class="title-bar-text">
											playlist_info
										</div>
										<div class="title-bar-controls">
											<button aria-label="Minimize"></button>
											<button aria-label="Maximize"></button>
											<button aria-label="Close"></button>
										</div>
									</div>
									<div style="float: left; margin-left: 15px; width:100px; height: 100px; margin-top: 10px; background-color: white; box-shadow:inset;">
										<img src="<%=p.getImage()%>" style="height: 100px;">
									</div>
									<div style="float:right; width: 50%; text-align: left;">
										<div id="info_box1"><p style="margin-top:0px; margin-left:2px;">제목 : <%=p.getPlaylist_name()%></p></div>
										<div id="info_box"><p style="margin-top:3px; margin-left:2px;"> User : <%=p.getUser_id()%></p></div>
										<!-- 자세히 보기 버튼 누르면 해당 사용자의 플레이리스트 구경 가능 -->
										<form action="playListDetail.jsp">
											<input type="hidden" name="playlistId" value="<%=p.getPlaylist_id()%>">
											<button type="submit" style="margin-top: 20px;">자세히 보기</button>
										</form>
										<!-- add to mine 버튼 누르면 나의 플레이리스트에 추가 가능 (기능 구현 예정) -->
										<!-- <button style="margin-left: 10px;">add to mine</button> -->
									</div>
	                    		</div><!-- window -->
						<%
							} //for(Playlist_info_VO p : playlistList)
						%>
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