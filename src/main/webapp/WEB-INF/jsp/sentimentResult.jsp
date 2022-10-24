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
	%>
<body>

<h1>CognitiveService(Sentiment)を使うサイト</h1>
<h2>元の文章</h2>
<h3><%=string %></h3>

<h2>結果</h2>
<h3><%=sentiment %></h3>
</body>
</html>