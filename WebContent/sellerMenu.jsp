<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
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
					<li class="nav-item"><a class="nav-link" href="#createCustomer-section">Create Customer</a></li>
					<li class="nav-item"><a class="nav-link" href="#createAccount-section">Create Customer Account</a></li>
					<li class="nav-item"><a class="nav-link" href="#showAccount-section">Show Customer Account</a></li>
					<li class="nav-item"><a class="nav-link" href="#changeProgram-section">Change Customer Program</a></li>
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
						<h3>Welcome seller <i><%=session.getAttribute("loginNameDB")%></i> <i> <%=session.getAttribute("loginSurnameDB")%></i></h3>
						<p style="color:#0059b3;"><b><%= request.getAttribute("message") %></b></p>
					</div>
					
					<div class="form-top-right">
						<i class="fa fa-user"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<section id="createCustomer-section">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>Create Customer</h3>
						<p>Please, fill the following form:</p>
					</div>
					<div class="form-top-right">
						<i class="fa fa-user-plus"></i>
					</div>
				</div>
				<div class="form-bottom">
					<form method="post" action="/YourPhone/SellerServlet"
						class="login-form">
						<input type="hidden" name="requestType" value="Insert" />
						<div class="form-group">
							<input
								type="text" name="clientName" placeholder="Enter name..."
								class="form-username form-control" >
						</div>
						<div class="form-group">
							
							<input type="text" name="clientSurname"
								placeholder="Enter surname..."
								class="form-username form-control" >
						</div>
						<div class="form-group">
							<input type="text" name="clientUsername"
								placeholder="Enter username..."
								class="form-username form-control" >
						</div>
						<div class="form-group">
							<input type="password" name="clientPassword"
								placeholder="Enter password..."
								class="form-password form-control" id="form-password">
						</div>
						<button type="submit" class="btn">Create Customer</button>						
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>
	<section id="createAccount-section">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>Create Customer Account</h3>
						<p>Please, fill the following form:</p>
					</div>
					<div class="form-top-right">
						<i class="fa fa-user-plus"></i>
					</div>
				</div>
				<div class="form-bottom">
					<form method="post" action="/YourPhone/SellerServlet"
						class="login-form">
						<input type="hidden" name="requestType" value="Insert" />
						<div class="form-group">
							<input
								type="text" name="username"
								placeholder="Enter username..."
								class="form-username form-control" >
						</div>				
						<div class="form-group">
							<div>
								<select name="nameProgram" id="basic1"
									class="selectpicker show-tick form-control"
									style="height: 50px; max-width: 505px; font-size: 16px;">
									
									 <%ArrayList<String> programs = (ArrayList<String>) request.getAttribute("availablePrograms");%>									
									<% for (int i = 0; i <  programs.size(); i +=2) { %>
 									<option value="<%=programs.get(i)%>" style="height: 50px; max-width: 505px;"><%=programs.get(i)%></option> 
									<% }%>
									
								</select>
							</div>
						</div>	
						<button type="submit" class="btn">Create Customer Account</button>						
						<div class="form-group">
							<label class="sr-only" for="form-username">Phone Number</label> 													
							<%if ((request.getAttribute("phoneNumber")==null)||(request.getAttribute("phoneNumber").equals(0))) {%>
								<b>Please fill the above,in order a new phone number to be created</b>
							<%  } else {%>
								<b>New client Phone number :<i><%=request.getAttribute("phoneNumber")%></i></b>
							<% } %>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>

	<section id="showAccount-section">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>Show Customer Account</h3>
						<p>Please fill the customer username:</p>
					</div>
				</div>
				<div class="form-bottom">
					<form method="get" action="/YourPhone/SellerServlet"
						class="login-form">
						<div class="form-group">
							<input type="text" name="usernameShowAccount"
								placeholder="Enter username..."
								class="form-username form-control" >
						</div>
						<button type="submit" class="btn">Show Account</button>
					</form>
					<% ArrayList<String> x = (ArrayList<String>) request.getAttribute("accountList");%>
						<% if (x!=null) { %>
						<table class="table">
							<tr>
								<th border="1"><b>Program</b></th>
								<td><%=x.get(0)%></td>
							</tr>
							<tr>
								<th border="1"><b>Phone Number</b></th>
								<td><%=x.get(1)%></td>
							</tr>					
							<tr>
								<th border="1"><b>Charge per second</b></th>
								<td><%=x.get(2)%></td>
							</tr>	
							<tr>
								<th border="1"><b>Call Duration</b></th>
								<td><%=x.get(3)%></td>
							</tr>
							<tr>
								<th border="1"><b>Debt</b></th>
								<td><%=x.get(4)%></td>
							</tr>
					</table>
					<%}%>					
				</div>
			</div>
		</div>
	</div>
	</section>

	<section id="changeProgram-section">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>Change Customer Program</h3>
						<p>Please fill the customer username:</p>
					</div>
				</div>
				<div class="form-bottom">
					<form method="get" action="/YourPhone/SellerServlet"
						class="login-form">
						<input type="hidden" name="requestType" value="Insert" />
						<div class="form-group">
							<input type="text" name="usernameChangeProgram"
								placeholder="Enter username..."
								class="form-username form-control" >
						</div>
						<button type="submit" class="btn">Search Customer</button>
					</form>
					<br><br>					
					<form method="post" action="/YourPhone/SellerServlet"
						class="login-form">
						<div class="form-group">
							<div>
								<select name="nameProgram" id="basic"
									class="selectpicker show-tick form-control"
									style="height: 50px; max-width: 505px; font-size: 16px;">
									<% ArrayList<String> y = (ArrayList<String>) request.getAttribute("programList");%>
									<% if (y!=null) { %>
										<% for (int i3 = 0; i3 < y.size(); i3 += 1) { %>
												<option value="<%=y.get(i3)%>" style="height: 50px; max-width: 505px;"><%=y.get(i3)%></option>
										<%	} %>
									<%	} %>
								</select>
							</div>
						</div>
						<button type="submit" class="btn">Change Customer Program</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>

	<!--End About Section -->
	<!-- Javascript -->
	<script src="/YourPhone/Bootstrap/js/bootstrap.min.js"></script>
	<script src="/YourPhone/Bootstrap/jquery-1.11.1.min.js"></script>
	<script src="/YourPhone/Bootstrap/jquery.backstretch.min.js"></script>
	<script src="/YourPhone/Bootstrap/scripts.js"></script>
</body>
</html>