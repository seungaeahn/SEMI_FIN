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
                       
                        <!--검색결과 올라가는 곳-->

                        <%
	                        SearchDAO searchDAO = new SearchDAO();
	                        ArrayList<User_info_VO> userList = searchDAO.getSearchUsers(request.getParameter("searchText"));
	                                              		
	                        //검색결과 없을경우 보일 창
	                        if(userList.size() == 0){
                        %>
								<img src="img/텅.png" style="margin-top:100px; width:250px;">
								<script>
									alert("검색 결과가 존재하지 않습니다");
								</script>
						<%
							}
						                        	
							for(User_info_VO u : userList) {
						%>       
								<div class="window" style="width: 500px; height:150px; margin-left: 350px; margin-bottom: 10px;">
									<div class="title-bar">
										<div class="title-bar-text">
											user_info
										</div>
										<div class="title-bar-controls">
											<button aria-label="Minimize"></button>
											<button aria-label="Maximize"></button>
											<button aria-label="Close"></button>
										</div>
									</div>
									<div style="float: left; margin-left: 15px; width:100px; height: 100px; margin-top: 10px; background-color: white; box-shadow:inset;">
										<%
											if(u.getProfile_img()==null){
										%>
												<img src="img/알계.jpg" alt="<%=u.getUser_nickname()%>님의 프로필 사진" style="height: 100%;">
										<%
											} else {
										%>
												<img src="<%=u.getProfile_img()%>" alt="<%=u.getUser_nickname()%>님의 프로필 사진" style="height: 100%;">
										<%
											}
										%>
									</div>
									<div style="float:right; width: 50%; text-align: left;">
										<p style="margin-top:20px; width:200px; height: 30px;">id : <%=u.getUser_id()%></p>
										<p style="margin-top:-10px;">nickname : <%=u.getUser_nickname()%></p>
										<form action="InsertFollowlist" method="post">
											<input type="hidden" name="follower_id" value="<%=(String)session.getAttribute("user_id")%>">
											<input type="hidden" name="following_id" value="<%=u.getUser_id()%>">
											<button type="submit" style="margin-top: 5px;">팔로우</button>
										</form>
										<!-- 타인의 마이페이지 양식 만들어지면 구현 예정 -->
										<!-- <button style="margin-top: 5px;">사용자 홈 방문</button> -->
									</div>
	                    		</div>
						<%
							}
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