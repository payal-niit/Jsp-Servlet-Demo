<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${message}
<form action="validateuser" method="post">
	<input type="text" name="username" />
	<input type="password" name="password" />
	<input type="submit" />
</form>

 <c:if test="${not empty loginError}">
    <script>
    window.addEventListener("load",function(){
         alert("${loginError}");
    }
    </script>
</c:if>
</body>
</html>