<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shinee.vo.User_info_VO" %>
<%@ page import="shinee.dao.MyPage_DAO" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <title>SHINee Music회원정보수정 페이지</title>
        <link rel="stylesheet" type="text/css" href="css/CreateAccountPage.css">

    </head>
    <body>
        <div class="pageColor">
            <div class="TabColor"><img src="images/mypage.png" class="TabiconImage"><strong class="TabTextStyle">Modify My Info</strong> 
                <button id="pageCloseButton" class="pageCloseButton" onclick="closeButton()"><Strong class="pageCloseButtonText">X</Strong></button>
            </div>          
             
                <form action="ModifyServlet" method="post" id="createAccount" enctype="multipart/form-data">
                <%
               
                  //String = id값을 가지고 오겠다.
                  
                  //String userIdValue = request.getParameter("user_id");
                  String id = (String) session.getAttribute("user_id");
                  MyPage_DAO mypageDAO = new MyPage_DAO();
                  User_info_VO userinfo = mypageDAO.getMember(id);
                  
                   %>
                <div id="divid">
                    <label for="user_id">
                        <img class="iconImg" src="images/user.png">
                    </label>
                    <input name="user_id" type="text" value="<%= userinfo.getUser_id() %>" readonly="readonly">
                   
                    <p id="ruleid"></p>
                </div>
                <div>
                    <label for="user_name">
                        <img class="iconImg" src="images/name.png">
                    </label>
                    <input name="user_name" type="text" value= "<%= userinfo.getUser_name() %>">
                    <p id="rulename"></p>
                </div>
                <div>
                    <label for="user_password">
                        <img class="iconImg" src="images/password.png">
                    </label>
                    <input name="user_password" type="password"  oninput="pwCheck()"  value="<%=userinfo.getUser_password() %>">
                    <p id="rulepm"></p>
                </div>
                <div>
                    <label for="checkpw">
                       <img class="iconImg" src="images/passwordcheck.png">
                    </label>
                    <input id="checkpw" type="password" oninput="pwCheck()" placeholder="비밀번호 확인">
                    <span id="checkpm"></span>
                </div>
                <br>
                <div>
                    <label for="user_nickname">
                        <img class="iconImg" src="images/profile.png">
                    </label>
                    <input name="user_nickname"  type="text" value="<%=userinfo.getUser_nickname()%>">
                    <p id="rulenickname"></p>
                </div>
                
            
                <div>
                    <div id="profileContainer">
                        <!-- 프로필 사진을 표시할 컨테이너 -->
                        <img id="uploadProfile">
                    </div>
                    <label for="profileInput">
                    <input type="file" id="profileInput" name="profileInput" accept="image/jpg" style="margin: 0 0 0 6%;">
                    </label>
                    <button id="profileUploadButton" type="button">프로필 등록</button>
                </div>
                <br>
                <div>
                    <label for="email">
                        <img class="iconImg" src="images/email.png">
                    </label>
                    <input name="email" type="text" value="<%=userinfo.getEmail() %>">
                    <p id="ruleemail"></p>
                </div>
                
                <div>
                    <label for="phone_number">
                        <img class="iconImg" src="images/phone.png">
                    </label>
                    <input name="phone_number" type="text" value="<%=userinfo.getPhone_number() %>">
                    <p id="rulephoneNumber"></p>
                </div>
                <button id="createUserButton" type="submit"  >정보수정</button>
                </form>
            </div>
        <script src="https://code.jquery.com/jquery-3.7.1.js" 
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" 
        crossorigin="anonymous"></script>
        <script src="js/CreateAccountPage.js">

        </script>
    </body>
    </html>