<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>文件上传入门案例</h1>
	<form action="http://localhost:8091/file" method="post" enctype="multipart/form-data">
		文件名称:<input name="fileImage" type="file"/><br>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>