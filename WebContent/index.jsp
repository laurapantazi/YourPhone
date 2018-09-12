<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="/YourPhone/Bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/YourPhone/Bootstrap/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/YourPhone/Bootstrap/form-elements.css">
<link rel="stylesheet" href="/YourPhone/Bootstrap/style.css">
<link rel="shortcut icon" href="/YourPhone/Bootstrap/ico/favicon.png">
<title>yourphone</title>
</head>
<body>
	<!-- Top content -->
	<div class="top-content">
		<p>
			Telephone Company <i>yourphone</i>
		</p>
		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>Welcome User</h3>
								<p>Please, fill the following form:</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-user"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form method="post" action="/YourPhone/LoginServlet"
								class="login-form">
								<input type="hidden" name="requestType" value="Insert" />
								<div class="form-group">
									<input type="text" name="username" placeholder="Enter username..."
										class="form-username form-control" id="form-username" >
								</div>
								<div class="form-group">
										<input type="password" name="password"
										placeholder="Enter password..."
										class="form-password form-control" id="form-password">
								</div>
								<button type="submit" class="btn">Log In</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Javascript -->
	<script src="/YourPhone/Bootstrap/js/bootstrap.min.js"></script>
	<script src="/YourPhone/Bootstrap/jquery-1.11.1.min.js"></script>
	<script src="/YourPhone/Bootstrap/jquery.backstretch.min.js"></script>
	<script src="/YourPhone/Bootstrap/scripts.js"></script>
</body>
</html>