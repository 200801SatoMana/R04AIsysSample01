<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>感情分析</title>
</head>
<%
Object o = request.getAttribute("string");
String string = (String) o;

Object s = request.getAttribute("sentiment");
String sentiment = (String) s;

Object ng = request.getAttribute("negative");
String negative = (String) ng;

Object ne = request.getAttribute("neutral");
String neutral = (String) ne;

Object po = request.getAttribute("positive");
String positive = (String) po;
	%>
<body>

<h1>CognitiveService(Sentiment)を使うサイト</h1>
<h2>元の文章</h2>
<h3><%=string %></h3>

<h2>結果</h2>
<h3><%=sentiment %></h3>
<h3>内訳</h3>
<h4>negative：<%= negative%></h4>
<h4>		neutral：<%= neutral %></h4>
<h4>		positive：<%=positive %></h4>
</body>
</html>