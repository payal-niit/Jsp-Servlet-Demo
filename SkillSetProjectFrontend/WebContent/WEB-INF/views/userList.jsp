<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>users Store Application</title>
</head>
<body>
	<h1>Add new User</h1>
	<h1><a href="login">Login</a></h1>
	<c:if test="${user != null}">
		<form action="update" method="post">
	</c:if>
	<c:if test="${user == null}">
		<form action="insert" method="post">
	</c:if>
	<table border="1" cellpadding="5">
		<caption>
			<h2>
				<c:if test="${user != null}">
                        Edit user
                    </c:if>
				<c:if test="${user == null}">
                        Add New user
                    </c:if>
			</h2>
		</caption>
		<c:if test="${user != null}">
			<input type="hidden" name="id"
				value="<c:out value='${user.user_id}' />" r/>
		</c:if>
		<tr>
			<th>User Id:</th>
			<td><input type="text" name="user_id"
				value="<c:out value='${user.user_id}' />" required="required"/>
			</td>
		</tr>
		<tr>
			<th>First Name:</th>
			<td><input type="text" name="fname"
				value="<c:out value='${user.fname}' />" required="required"/></td>
		</tr>
		<tr>
			<th>Last Name:</th>
			<td><input type="text" name="lname"
				value="<c:out value='${user.lname}' />" required="required"/></td>
		</tr>
		<tr>
			<th>Age:</th>
			<td><input type="text" name="age"
				value="<c:out value='${user.age}' />" required="required"/></td>
		</tr>
		<tr>
			<th>Email ID:</th>
			<td><input type="text" name="email_id"
				value="<c:out value='${user.email_id}' />" required="required"/>
			</td>
		</tr>
		<tr>
			<th>Phone number:</th>
			<td><input type="text" name="phone_number"
				value="<c:out value='${user.phone_number}' />" required="required"/>
			</td>
			<td>Message - ${m}</td>
		</tr>

		<tr>
			<th>Password:</th>
			<td><input type="password" name="password"
				value="<c:out value='${user.password}' />" required="required"/>
			</td>
		</tr>
		<tr>
			<th>User type:</th>
			<td>
			<input type="radio" name="user_type" value="HR"> HR
			<input type="radio" name="user_type" value="Project Manager"> Project Manager
			<input type="radio" name="user_type" value="Tech Mentor"> Tech Mentor
			
			</td>
		</tr>

		<tr>
			<td colspan="2" align="center"><input type="submit" value="Save" />
			</td>
		</tr>
	</table>
	</form>
	<%-- <h1>User List</h1>
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
		</table> --%>
	</div>
</body>
</html>