<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="findprofiles" method="post">
	<input type="text" name="fname">
	<input type="submit">
</form>

<h1>User List</h1>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of users</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${userList}">
				<tr>
					<td><c:out value="${user.user_id}" /></td>
					<td><c:out value="${user.fname}" /></td>
					<td><c:out value="${user.lname}" /></td>
					<td><c:out value="${user.age}" /></td>
					<td><a
						href="/SkillSetProjectFrontend/edit?user_id=<c:out value='${user.user_id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/SkillSetProjectFrontend/delete?user_id=<c:out value='${user.user_id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>