<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="resources/css/Tema.css" type="text/css">
	<title>Registracija radnika</title>
</head>

<body>
	<div class="Box">
		<img id="logo" src="resources/images/Logo.jpg">
		<%@ include file="/resources/templates/menu.jsp" %>
		<div class="contain">
			<br></br>
			<h2>Registruj radnika:</h2>
			<form action="RegistracijaServlet" method="post">				
				<input type="hidden" name="radnik" value="radnik">
				<table>
					<tr>
						<td><p>Ime radnika: *</p></td>
						<td><input type="text" name="ime"></td>
					</tr>
					<tr>
						<td><p>Prezime rednika: *</p></td>
						<td><input type="text" name="prezime"></td>
					</tr>
					<tr>
						<td><p>Korisničko ime: *</p></td>
						<td><input type="text" name="korisnicko_ime"></td>
					</tr>
					<tr>
						<td><p>Lozinka: *</p></td>
						<td><input type="password" name="lozinka"></td>
					</tr>
					<tr>
						<td><p>Ponovi lozinku: *</p></td>
						<td><input type="password" name="ponLozinku"></td>
					</tr>

					<tr>
						
						<td><input type="submit" name="submit" value="Dodaj"></td>
					</tr>
				</table>
				<br></br>
				<marquee>
					<img src="resources/images/as-bus.jpg">
				</marquee>
			</form>
		</div>		
		</div>
		${porukaReg}
<%@ include file="/resources/templates/footer.jsp" %>