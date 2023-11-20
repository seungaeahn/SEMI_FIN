<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%
    request.setCharacterEncoding("UTF-8");
    %>
    
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "shinee.dao.SearchDAO" %>
<%@ page import = "shinee.dao.Insert_music_toPlaylist_DAO" %>
<%@ page import = "shinee.vo.Music_info_VO" %>
<%@ page import = "shinee.vo.Playlist_info_VO" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>insert Music</title>
		<link rel="stylesheet" type="text/css" href="css/search.css">
        <link rel="stylesheet" type="text/css" href="css/all.css">
	</head>
	<body>
	
		<!-- 내 플레이리스트가 뜰 곳 -->
		<div>
			<%
			Insert_music_toPlaylist_DAO insertDAO = new Insert_music_toPlaylist_DAO();
					    String session_user_id = (String)session.getAttribute("user_id");
						ArrayList<Playlist_info_VO> myplaylists = insertDAO.getAllPlaylist(session_user_id);
							                        
						//로그인 정보가 없을시
						if(session_user_id == null){
			%>
				<p>로그인 후 이용해주세요</p>
			<%
			} else {
			%>
				<p>나의 플레이리스트</p>
				<select name="playlistFiled">	
			<%
				for(Playlist_info_VO p : myplaylists) {
				%>
						<option value=<%=p.getPlaylist_name()%>><%=p.getPlaylist_name()%></option>
				<%
					}; //for
				%>
				</select>
				<button>저장</button>
			<%
				} //else
			%>
		</div>

	</body>
</html>