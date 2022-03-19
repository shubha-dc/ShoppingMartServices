<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Form</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>User Register Form:</h1>
		<div class="card">
			<div class="card-body">
				<form action="/register" method="post">

					<div class="form-group row">
						<label for="firstName" class="col-sm-2 col-form-label">First
							Name</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="firstName"
								placeholder="Enter first name">
						</div>
					</div>

					<div class="form-group row">
						<label for="lastName" class="col-sm-2 col-form-label">Last
							Name</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="lastName"
								placeholder="Enter last name">
						</div>
					</div>

					<div class=" form-group row">
						<label for="lastName" class="col-sm-2 col-form-label">User
							Name</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="username"
								placeholder="Enter user name">
						</div>
					</div>

					<div class="form-group row">
						<label for="lastName" class="col-sm-2 col-form-label">Passwrod</label>
						<div class="col-sm-7">
							<input type="password" class="form-control" name="password"
								placeholder="Enter Password">
						</div>
					</div>

					<div class="form-group row">
						<label for="lastName" class="col-sm-2 col-form-label">Address</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="address"
								placeholder="Enter Address">
						</div>
					</div>

					<div class="form-group row">
						<label for="contact" class="col-sm-2 col-form-label">Contact
							No</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="contact"
								placeholder="Enter Contact Address">
						</div>
					</div>

					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>