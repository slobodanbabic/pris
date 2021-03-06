<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Profil Radnika</title>
<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Expires" CONTENT="-1">
<link href="resources/css/Tema.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="Box">
		<header>
			<img id="logo" src="resources/images/Logo.jpg">
		</header>
		<%@ include file="/resources/templates/menu.jsp" %>
		<br></br>
		<div class="rezervacija1">
			<p>
				<strong>Ime:</strong> <label for="ime">${radnik.ime}</label>
			</p>
			<p>
				<strong>Prezime: </strong><label for="prezime">${radnik.prezime}</label>
			</p>
		</div>
		<div class="rezervacija2">
			<h3 align="justify">Broj prodatih karata: ${prodaja.brprodatih}</h3>
			<form action="DnevniProfitRadnikaServlet" method="get">
				<input type="submit" value="Ostvareni dnevni profit">
			</form>
			<h3 align="justify">${porukaProfit}</h3>
			
			<form action="ReportServlet" method="get" target="_blank">
				<input type=hidden name="dnevniProfit" value="dnevniProfit">
				<input type="submit" value="Dnevni profit izvestaj">
			</form>	
			<form action="ReportServlet" method="get" target="_blank">
				<input type=hidden name="ocenePrevoznika" value="ocenePrevoznika">
				<input type="submit" value="Najbolje ocenjeni prevoznici izvestaj">
			</form>				
			</div>
			<br></br>
			<marquee><img src="resources/images/karta.jpg"></marquee>
	</div>
<%@ include file="/resources/templates/footer.jsp" %>