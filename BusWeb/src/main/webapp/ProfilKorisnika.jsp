<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/css/Tema.css" type="text/css">

<title>Profil Korisnika</title>
</head>
<body>
<body>
	<div class="Box">
		<img id="logo" src="resources/images/Logo.jpg">
		<%@ include file="/resources/templates/menu.jsp" %>
		<div class="contain">
			<br></br>
			<h2>Vaš profil:</h2>
			<img src="resources/images/profile.png">

			<p>
				<strong>Ime:</strong> <label for="ime">${korisnik.ime}</label>
			</p>
			<p>
				<strong>Prezime: </strong><label for="prezime">${korisnik.prezime}</label>
			</p>
 			<p> 
 				<strong>Broj rezervacija: </strong><label for="brKarata">${korisnik.brrezervacija}</label> 
 			</p> 					
			<div class="rezervacija2"> 
			<marquee>
				<p><strong> NA SVAKU TREĆU REZERVACIJU, POPUST OD 10% NA CENU KARTE! ! ! </strong>
				<img src="resources/images/as-bus.jpg"></p>
			</marquee>
			</div>
			<br></br>
			
		</div>
	</div>
<%@ include file="/resources/templates/footer.jsp" %>