<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN FORM</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- <link src="/bootstrap/bootstrap/css/bootstrap.min.css"
	rel="stylesheet"> -->
</head>
<body>
	<div class="container">
		<h1>PLease Login</h1>

		<div class="card">
			<div class="card-body">
				${SPRING_SECURITY_LAST_EXCEPTION.message}
				<form action="login" method="post">

					<div class="form-group row">
						<label for="userName" class="col-sm-2 col-form-label">User
							Name</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="username"
								placeholder="Enter user name">
						</div>
					</div>

					<div class="form-group row">
						<label for="password" class="col-sm-2 col-form-label">Password
						</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" name="password"
								placeholder="Enter password">
						</div>
					</div>

					<div class="dropdown">

						<label for="password" class="col-sm-2 col-form-label">Select
							Role </label>
						<div class="col-sm-7">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								SELECT <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
							<c:forEach items="${displayRolesList}" var ="role">
							<li>${role}</li>
							</c:forEach>
							</ul>
							
							
							<c:forEach items="${displayRolesList}" var ="role">
							<c:out value="${role}"/> 
							</c:forEach>
						

						</div>
					</div>

					<button type="submit" name="submit" class="btn btn-primary">Submit</button>

				</form>
			</div>
		</div>
	</div>
</body>
</html>