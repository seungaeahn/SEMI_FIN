<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shinee.vo.User_info_VO" %>
<%@ page import="shinee.dao.MyPage_DAO" %>
<%@ page import="shinee.vo.Follow_list_VO" %>
<%@ page import="shinee.dao.FollowBlob_DAO" %> 
<%@ page import="shinee.dao.MyPlayList_DAO" %>
<%@ page import="shinee.vo.Playlist_info_VO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <title>SHINee Music</title>
        <style>
		    .col-md-3, .col-md-6 {
			background: transparent;
			border-style: solid;
		    border-color: #DAF8FF;
			padding: 40px;
			border-radius: 5px;
			box-shadow: 0.5px 0.5px 2px 1px #eee;
			text-align: left;
			margin-left: 20px;
			margin-right: 20px;
			margin-bottom: 40px;
		    }
		    .white-box {
		    display:inline;
		    }
		    .playlists {
		    }
        </style>
        <link rel="stylesheet" href="css/all.css">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/myPage.css">
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
			
			
			<div style="margin: 0 auto; text-align: left;">
	        <section>
	        
	            <div class="bluetop" style="margin-top:20px; width:1200px;  margin: 0 auto;">
	                <p style="margin-top:5px;" ><img src="img/smile.png" style="height: 50px; float: left; margin-top: -17px; "><strong>My Page</strong></p>
	            </div>
	            <div style="width:1200px; height:30px; background-color: gray;">
	                
	                <article>
	                    <div class="d-flex">
	                        <div class="flex-shrink-0">
	                        <%                  
			                	// 회원 정보 가져오기!!!!! 
			                    String id = (String) session.getAttribute("user_id");
			                  
			                    MyPage_DAO mypageDAO = new MyPage_DAO();
			                    User_info_VO userinfo = mypageDAO.getMember(id);
			                %>
	                            
	                            <br>
	                            <br>
	                            <br>
	                            <%
	                            	if(userinfo.getProfile_img()==null){
	                            %> 		
	                            		<img class="profile" src="img/알계.jpg" style="width: 200px; height: 200px;">
	                            <%
	                            	} else {
	                            %>
	                            		<img class="profile" src="<%=userinfo.getProfile_img()%>" style="width: 200px; height: 200px;">
	                            <%
	                            	}
	                            %>
	                             
	                          
	                        </div>
	  
	                        <div class="flex-grow-1 ms-3">
								<h4><%= userinfo.getUser_nickname() %></h4>
								<h4><%= userinfo.getUser_id() %></h4>
								<h4><%= userinfo.getEmail() %></h4>
	                      
								<button style="margin-top: 10px; background-color: transparent"  class="btn btn-primary" onclick="location.href='Modify_user_info.jsp'">
				                	<img src="img/pencil.png" style="width:40px; padding:0;" alt="Modify Icon">
				             	</button>
								<br>
								<%
						 			//만약 mno값이 존재할 경우 빈(null)값이 아닐경우
						 			if(session.getAttribute("user_id") != null){
						 		%>
						 				<button id="logoutButton" style="margin-top: 10px; background-color: transparent" onclick="location.href='logout.jsp'">
					         				<img src="img/로그아웃.png" style="width:85px; padding:0;" alt="Logout Icon" >
					     				</button>
						 		<%
						 			}//if(session.getAttribute("user_id") != null)
								%>
					        <br>
				            <br>
				            <br>
				            <form action="DeleteUserInfoServlet" method="post" id="deleteForm">
						    <input type="hidden" name="user_id" value="<%= userinfo.getUser_id() %>">
						    <button type="button" class="btn btn-default pull right" onclick="confirmAndSubmit()">SHINee Music 탈퇴하기</button>
							</form>
							
							<script>
								function confirmAndSubmit() {
								    if (confirm('정말 Shinee Nusic을 탈퇴하시겠습니까?!!')) {
								        // 사용자가 확인을 누르면 폼을 제출한다.
								        document.getElementById('deleteForm').submit();
								    } else {
								        // 사용자가 취소를 누른 경우 아무것도 하지 않는다.
								    }
								}
							</script>
	                        </div>
	                        
	                    </div> 
	                    <br><br><br><br>
	             <div class="col-md-6">
	            <div class="white-box">
	              <div class="mb-5">
	             <span style="display: flex; align-items: center;">
				    <img class="profile" src="img/headset.gif" style="width: 60px; height: 60px;">
				    <h3 style="margin-left: 10px; margin-top: 0; margin-bottom: 0;">My playlist</h3>
				</span>
				<br>
	                <%
		                String id_2 = (String) session.getAttribute("user_id");
		 				   //회원의 플레이 리스트 가져오기!!!!
		                MyPlayList_DAO playlistDAO = new MyPlayList_DAO();
						List<Playlist_info_VO> playlists = playlistDAO.getAllPlaylists(id_2);
				
						for(Playlist_info_VO p : playlists){
	                	%>
			                <div style="display: inline-block; margin: 10px;">
			                    <a href="playListDetail.jsp?playlistId=<%=p.getPlaylist_id()%>"><img class="profile" src="<%= p.getImage() %>" style="width: 180px; height: 180px;"></a>
			                    <div><p><strong><%=p.getPlaylist_name()%></strong></p></div>
			                    
			                </div>
	                
	                	<%
	                	}//for(Playlist_info_VO p : playlists)
	                %>
	              </div>
	            </div>
	          </div>

	          <br>
	                            
	          <div class="col-md-6">
	            <div class="white-box">
	              <div class="mb-5">
	              <span style="display: flex; align-items: center;">
	              <img class="profile" src="img/blueheart.gif" style="width: 60px; height: 60px;">   
	                <h3 style="margin-left: 10px; margin-top: 0; margin-bottom: 0">Followings</h3>
	                </span>
	                <br>
	                <%
		             	//Blob 땜에 만든 followBlobDAO도 가져오기
		                FollowBlob_DAO fbDAO = new FollowBlob_DAO();
		                List<Follow_list_VO> followlists = fbDAO.getAllFollowings(id);
	               
	                	for(Follow_list_VO f : followlists) {
	            		%>
			                <div style="display: inline-block; margin: 10px;">
			                	<%
			                		if(f.getProfile_img()==null){
			                	%>
			                			<img class="profile" src="img/알계.jpg" style="width: 200px; height: 200px;">
			                	<%
			                		} else {
			                	%>
					                    <img class="profile" src="<%= f.getProfile_img() %>" style="width: 200px; height: 200px;">
					            <%
			                		}
					            %>
					            <div><p><strong><%= f.getUser_nickname() %></strong></p></div>
					            <div><p><strong><%= f.getFollowing_id() %></strong></p></div>
			                </div>
	            		<%
	                	}//for(Follow_list_VO f : followlists)
	            	%>
	              						</div>
	            					</div>
	          					</div>
	
	                		</article>
	           			</div>
	            
	        		</section>
	  
	        	</div>
	
	
	          </div><!-- div:container -->
	          <%
					}//else
	          %>
	          
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
          </script>
    </body>
</html>