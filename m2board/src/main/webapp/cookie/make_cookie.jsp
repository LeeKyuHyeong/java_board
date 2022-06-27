<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	Cookie cookie = new Cookie("id", URLEncoder.encode("유기형","UTF-8"));
	cookie.setMaxAge(10);
	response.addCookie(cookie);
	response.sendRedirect("using_cookie.jsp");
%>