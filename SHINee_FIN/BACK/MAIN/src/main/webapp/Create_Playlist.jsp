<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "shinee.dao.MyPage_DAO" %>
<%@ page import = "shinee.vo.User_info_VO" %>
<!DOCTYPE html>
<html>
    <head>
        <title>SHINee Music_create</title>
        <link rel="stylesheet" href="css/all.css">
        <link rel="stylesheet" href="css/login.css">
        
    </head>
    <body>
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
            	<%
	            	// 회원 정보 가져오기!!!!! 
	                //String = nickname값과 id값을 가지고 오겠다.
	                //String userIdValue = request.getParameter("user_id");
	                String id = (String) session.getAttribute("user_id");
	                MyPage_DAO mypageDAO = new MyPage_DAO();
	                User_info_VO user_info = mypageDAO.getMember(id);
            	%>
                <div class="bluetop" style="margin-top:20px; width:1200px;">
                    <p style="margin-top:5px;" ><img src="./img/Save.png" style="height: 28px; float: left; margin-top: -8px; "><strong>My_PlayList</strong></p>
                </div>
                <div style="width:1200px; height:30px; background-color: gray;">
                <button id="new"><a href="MyPlaylist.jsp">Back To PlayList</a></button>
                
                </div>
              
                <article id="create">
                  
	                <div style="text-align: center; margin-top: 5%;">
	           			<form action="UploadServlet" method="post" enctype="multipart/form-data"> 
	           				<input type="hidden" name="user_idd" value="<%=user_info.getUser_id()%>"> 
	                        <label for = "PlaylistID"></label>
	                        <label for = "title" style="font-size: 20px;">PlayList_Name : </label> 
	                        <input type="text" name = "title" required>
	                        <br><br><br><br><br>
	                        <p style="text-align: center;">플레이리스트 대표사진 </p>
	                        
	                       
	                        <label for ="image">이미지 : </label>
							<input type="file" name="image" id="image" required><br>
	                       
	                        
	                        <br>
	                        <button id = "saveButton" type = "submit" style="margin-top: 20%;">생성하기</button>  
	                   	</form>
	                </div>
                </article>
            
            </section>

        </div><!--container-->
        <script>
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
            
            //생성하기 버튼 스크립트
            document.getElementById("saveButton").addEventListener("click", function(){
                const playlistContent = document.getElementById("ListPost").value;
                if(playlistContent.trim() !==""){
                    const timestamp=new Date().getTime();
                    const ListPost ={
                        id: timestamp,
                        content: playlistContent
                    };
                    const savedPosts = JSON.parse(localStorage.getItem("ListPosts"))||[];
                    savedPosts.push(ListPost);
                    localStorage.setItem("ListPosts", JSON.stringify(savedPosts));

                    alert("플레이리스트가 생성되었습니다.");

                    window.location.href="MyPlaylist.jsp";
                }else{
                    alert("플레이리스트 내용을 다시 입력해주세요.");
                }
            });
            
        </script>
    </body>
</html>