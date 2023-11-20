<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//세션을 없애서 로그아웃 처리
	session.invalidate();
	response.sendRedirect("login.jsp");
%>