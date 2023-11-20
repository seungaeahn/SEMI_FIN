<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import ="shinee.vo.Playlist_info_VO" %>
<%@page import = "shinee.dao.MyPlayList_DAO" %>
<%@ page import = "shinee.dao.SearchDAO" %>
<%@ page import = "shinee.vo.Music_info_VO" %>
<%@ page import = "shinee.vo.Playlist_info_VO" %>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>my Playlist</title>
		<link rel="stylesheet" type="text/css" href="css/all.css">
		<link rel="stylesheet" type="text/css" href="css/login.css">
	</head>
	
	<body>
		<%
			//user_id 값이 존재하지 않을 경우 로그인 페이지로 돌아갑니다.
			if(session.getAttribute("user_id") == null){
				%>
					<script>
						alert("로그인이 필요한 서비스입니다.");
						location.href="login.jsp";
					</script>
				<%
			} else {
				//user_id값이 존재한다면
		%>
			<div class="container" style="margin-top:30px;">
	            <aside>
	                <button id="gotoMyPageButton" style="margin-top: 250px;">
	                    <img src="./img/mypage.png" style="width:85px; padding:0;" alt="myPage Icon">
	                    <p><strong>mypage</strong></p>
	                </button>
	                <button id="gotoMyPlaylistButton" style="margin-top: 50px;">
	                    <img src="./img/lookaround.png" style="width:85px; padding:0;" alt="Search Icon">
	                    <p><strong>My<br>PlayList</strong></p>
	                </button>
	                <button id="gotoSearchButton" style="margin-top: 50px;">
	                    <img src="./img/pixel_search.png" style="width:85px; padding:0;" alt="lookaround Icon">
	                    <p><strong>Search</strong></p>
	                </button>
	            </aside>
	            <section>
	                <div class="bluetop" style="margin-top:20px; width:1200px;">
	                    <p style="margin-top:5px;" ><img src="./img/Save.png" style="height: 28px; float: left; margin-top: -8px; "><strong>My_PlayList</strong></p>
	                </div>
	                <div style="width:1200px; height:30px; background-color: gray;">
	                	<button id="new"><a href="Create_Playlist.jsp">NEW</a></button>
					</div>
	                <article id="articleP">
	                	
	                	<div style="text-align: center; margin-top: 0%;">
	                	</div>
	         
		                <table>
							<tr>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</table>
					<%
						MyPlayList_DAO playlistDAO = new MyPlayList_DAO();
						String user_id = (String)session.getAttribute("user_id");
						List<Playlist_info_VO> playlists = playlistDAO.getAllPlaylists(user_id);
								
						for(Playlist_info_VO p : playlists){
					%>
							<form action="DeleteServlet" method="post">
							<tr>
							<div class="window" style="width: 600px; height:200px; float: left; margin-left: 20%; margin-bottom: 5px; margin-top:10px;">
							<div class="title-bar">
							<div class="title-bar-text" style="margin-left:5px;">
							 MY_PlayList_♡ 
							<td><%=p.getPlaylist_id()%></td>
							</div>
							<div class="title-bar-controls">
							<input type="hidden" name="playlistId" value="<%= p.getPlaylist_id() %>">
							<button type="submit" class="btn btn-default pull right">X</button>
													
													
							</div></div>
				
							<td></td>
			
							<td><a href="playListDetail.jsp?playlistId=<%=p.getPlaylist_id()%>"><img src="<%=p.getImage()%>"style="width : 150px; height: 150px; margin-left:10px; margin-top:10px; border: 3px inset lightgray;"></a></td>
							
							<td>
							<div style="float:right; width: 300px; text-align: left; margin-right:100px; ">
							<div style="background-color:white; margin-top:20px; padding-right: 20px; width:300px; height: 22px; border: 2px outset lightgray;">
							<p style="margin-left: 30px; margin-top:1px;"><%=p.getPlaylist_name()%></p></div></div>
							</td>
							
							</tr><br>
							</form>
							</div><!-- container -->
							
					<%
						} //for(Playlist_info_VO p : playlists)
					%>
	                	</article>
	                </section>
	                <%
						}//else
	                %>

		<script>
            const List = document.getElementById("List");
            const savedPosts = JSON.parse(localStorage.getItem("ListPosts")) || [];

            savedPosts.forEach(post => {
                const listItem = document.createElement("li");
                listItem.textContent = post.content;
                List.appendChild(listItem);
  
            });
          
            //===================== 창 이동 함수 =====================
            	
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
			
			//메뉴바(?) new버튼 누르면 플레이리스트 생성창으로 이동
			document.getElementById("new").addEventListener("click",function(){
                window.location.href="Create_Playlist.jsp";
            });
			
		</script>
       
	</body>
</html>