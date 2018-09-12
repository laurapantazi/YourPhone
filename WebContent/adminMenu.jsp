<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"%>
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
	<!--Start Navigation -->
	<header>
        <div class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">YourPhone</a>
        <button class="navbar-toggler" type="button"data-toggle="collapse" 
        data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" 
        aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                   <ul class="navbar-nav"> 
                    <li class="nav-item"><a class="nav-link" href="#home">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#createUser-section">Create User</a></li>
					<li class="nav-item"><a class="nav-link" href="#createProgram-section">Create Program</a></li>
					<li class="nav-item"><a class="nav-link" href="#showUsers-section">Users</a></li>
					<li class="nav-item"><a class="nav-link" href="#showPrograms-section">Programs</a></li>
					<li class="nav-item"><a class="nav-link" href="index.jsp">Log Out</a></li>                    
                        
                    </ul>
                </div>
        </div>
        </header>
	<!--End Navigation -->
	<!-- Top content -->
	<section id="home" class="mt-5">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>
							Welcome admin <i><%=session.getAttribute("loginNameDB")%><i> </i> <%=session.getAttribute("loginSurnameDB")%></i></h3>
						<p style="color:#0059b3;"><b><%=request.getAttribute("message")%></b></p>
					</div>
					<div class="form-top-right">
						<!--  εικονίδιο-->
						<i class="fa fa-user"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<section id="createUser-section">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>Create User</h3>
						<p>Please fill the following form:</p>
					</div>
					<div class="form-top-right">
						<!--  εικονίδιο-->
						<i class="fa fa-user-plus"></i>
					</div>
				</div>
				<div class="form-bottom">
					<form method="post" action="/YourPhone/AdminServlet"
						class="login-form">
						<input type="hidden" name="requestType" value="Insert" />
						<div class="form-group">
							<input
								type="text" name="name" placeholder="Enter name..."
								class="form-username form-control" >
						</div>
						<div class="form-group">
							
							<input type="text" name="surname"
								placeholder="Enter surname..."
								class="form-username form-control" >
						</div>
						<div class="form-group">
							<input type="text" name="username"
								placeholder="Enter username..."
								class="form-username form-control">
						</div>
						<div class="form-group">
							<input type="password" name="password"
								placeholder="Enter password..."
								class="form-password form-control" id="form-password">
						</div>

						<div class="form-group">
							<div>
								<select name="role" id="basic"
									class="selectpicker show-tick form-control"
									style="height: 50px; max-width: 505px; font-size: 16px;">
									<option value="client" style="height: 50px; max-width: 505px;">Client</option>
									<option value="seller" style="height: 50px; max-width: 505px;">Seller</option>
									<option value="admin" style="height: 50px; max-width: 505px;">Administrator</option>
								</select>
							</div>
						</div>
						<button type="submit" class="btn">Create User</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>

	<section id="createProgram-section">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>Create Program</h3>
						<p>Please fill the following form:</p>
					</div>
					<div class="form-top-right">
						<i class="fa fa-database"></i>
					</div>
				</div>
				<div class="form-bottom">
					<form method="post" action="/YourPhone/AdminServlet"
						class="login-form">
						<input type="hidden" name="requestType" value="Insert" />
						<div class="form-group">
							<input type="text" name="nameProgram"
								placeholder="Enter program name..."
								class="form-username form-control" >
						</div>
						<div class="form-group">
							 <input type="text" name="chargeSeconds"
								placeholder="Enter call charge per seconds..."
								class="form-username form-control">
						</div>
						<button type="submit" class="btn">Create Program</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>
	
	<section id="showUsers-section">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>List of all Users</h3>
					</div>
				</div>
				<div class="form-bottom">
					<% ArrayList<String> u = (ArrayList<String>) session.getAttribute("allUsers");%>
						<% if (!(u.isEmpty())) { %>
							<table class="table">
								<tr>
									<th border="1"><b>Name</b></th>
									<th border="1"><b>Surname</b></th>
									<th border="1"><b>Username</b></th>
									<th border="1"><b>Role</b></th>								
								</tr>	
								<% for (int i = 0; i <  u.size(); i +=4) { %>	
								<tr>
									<td><%=u.get(i)%></td>
									<td><%=u.get(i+1)%></td>
									<td><%=u.get(i+2)%></td>
									<td><%=u.get(i+3)%></td>
								</tr>	
								<%}%>
						</table>
					<%}%>					
				</div>
			</div>
		</div>
	</div>
	</section>
	
	<section id="showPrograms-section">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>List of all Programs</h3>
					</div>
				</div>
				<div class="form-bottom">	
					<% ArrayList<String> p = (ArrayList<String>) session.getAttribute("allPrograms");%>
						<% if (!(p.isEmpty())) { %>
							<table class="table">
								<tr>
									<th border="1"><b>Name Program</b></th>
									<th border="1"><b>Charge per seconds</b></th>
								</tr>	
								<% for (int i = 0; i <  p.size(); i +=2) { %>	
								<tr>
									<td><%=p.get(i)%></td>
									<td><%=p.get(i+1)%></td>
								</tr>	
								<%}%>
						</table>
					<%}%>											
				</div>
			</div>
		</div>
	</div>
	</section>
	<!--End About Section -->

	<!-- Javascript -->
	<script src="/YourPhone/Bootstrap/jquery-1.11.1.min.js"></script>
	<script src="/YourPhone/Bootstrap/js/bootstrap.min.js"></script>
	<script src="/YourPhone/Bootstrap/jquery.backstretch.min.js"></script>
	<script src="/YourPhone/Bootstrap/scripts.js"></script>
</body>
</html>