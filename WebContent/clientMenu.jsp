<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
		<link rel="stylesheet"
			href="/YourPhone/Bootstrap/css/bootstrap.css">
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
                        <li class="nav-item"><a class="nav-link" href="#showAccount-section">Show Account</a></li>
                        <li class="nav-item"><a class="nav-link" href="#showHistoryLog-section">Show History Log</a></li>
                        <li class="nav-item"><a class="nav-link" href="#payAccount-section">Pay Account</a></li>               
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
							Welcome client <i><%=session.getAttribute("loginNameDB")%> </i><i><%=session.getAttribute("loginSurnameDB")%> </i></h3>
						<p style="color:#0059b3;"><b><%=request.getAttribute("message")%></b></p>		
					</div>
					<div class="form-top-right">
						<i class="fa fa-user"></i>
					</div>
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
						<h3>Your Account</h3>
					</div>
				</div>
				<div class="form-bottom">
					<% ArrayList<String> x = (ArrayList<String>) session.getAttribute("accountList");%>					
						<% if (!(x.isEmpty())) { %>
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
								<td><%=x.get(2)%>&euro;</td>
							</tr>	
							<tr>
								<th border="1"><b>Call Duration</b></th>
								<td><%=x.get(3)%></td>
							</tr>
							<tr>
								<th border="1"><b>Debt</b></th>
								<td><%=x.get(4)%>&euro;</td>
							</tr>
					</table>
					<%}%>					
				</div>
			</div>
		</div>
	</div>
	</section>
   
	<section id="showHistoryLog-section">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>Your History Log</h3>
					</div>
				</div>
				<div class="form-bottom">
				<% ArrayList<String> y = (ArrayList<String>) session.getAttribute("historyLogList");%>
						<% if (!(y.isEmpty())) { %>
							<table class="table">
								<tr>
									<th border="1"><b>Number of Call</b></th>
									<th border="1"><b>Dialed Number</b></th>
									<th border="1"><b>Call duration</b></th>
								</tr>	
								<% for (int i = 0; i <  y.size(); i +=3) { %>	
								<tr>
									<td><%=y.get(i)%></td>
									<td><%=y.get(i+1)%></td>
									<td><%=y.get(i+2)%></td>
								</tr>	
								<%}%>
						</table>
					<%}%>									
				</div>
			</div>
		</div>
	</div>
	</section>

		<section id="payAccount-section">
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3>Pay Account</h3>
					</div>
				</div>
				<div class="form-bottom">
					<p>Your account debt is: <%=session.getAttribute("debtAccount")%>&euro;</p>
					<form method="POST" action="/YourPhone/ClientServlet" class="login-form">
						<button type="submit" class="btn">Pay account debt</button>						
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>
        <!-- Javascript -->	
	<script src="/YourPhone/Bootstrap/jquery-1.11.1.min.js"></script>
	<script src="/YourPhone/Bootstrap/js/bootstrap.min.js"></script>
	<script src="/YourPhone/Bootstrap/jquery.backstretch.min.js"></script>
	<script src="/YourPhone/Bootstrap/scripts.js"></script>
</body>
</html>