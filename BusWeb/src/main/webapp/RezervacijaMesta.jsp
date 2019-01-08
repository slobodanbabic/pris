<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="resources/css/Tema.css" type="text/css">
	<title>Online rezervacija</title>
</head>
<body>
	<jsp:useBean class="customBeans.SveRazliciteLinije" id="razliciteLinije" scope="request" />
	<div class="Box">
		<img id="logo" src="resources/images/Logo.jpg">
		<%@ include file="/resources/templates/menu.jsp" %>
		<br></br>
		<h2 align="center">ONLINE REZERVACIJA AUTOBUSKE KARTE</h2>
		<div class="rezervacija1">
			<form action="RezervacijaMestaServlet" method="get">
				<table width="500">
					<tr>
						<td>Destinacija:</td>
						<td><select name="idGrad">
								<c:forEach items="${razliciteLinije.getSveRazliciteLinije()}" var="l">
									<option value="${l.grad.idgrad}">${l.grad.naziv}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>	
						<td><label>Datum polaska:</label></td>
						<td><input id="datePick" type="date" name="datumPolaska" /></td>							
						
					</tr>
				</table>
				
				<input type="submit" value="Pretraži"> 
			</form>
			<br/><br/>
			<form action=RezervisiKartuServlet method="get">
				<c:if test="${empty trazeniPolasci}">
					<p style="color:red">${porukaNemaPol}</p>
				</c:if>
				<c:if test="${!empty trazeniPolasci}">
					<table border="1">
						<tr>
							<th>Destinacija</th>
							<th>Prevoznik</th>
							<th>Vreme polaska</th>
							<th>Broj preostalih karata</th>
							<th>Cena karte</th>							
							<th>Broj karata</th>
							<th>Potvrda</th>
						</tr>
						<c:forEach items="${trazeniPolasci}" var="trazeniPol">
							<input type="hidden" name="idPolaska" value="${trazeniPol.idpolaska}"/>
							<input type="hidden" name="cenaKarte" value="${trazeniPol.cenaKarte}"/>
						<tr>						
							<td>${trazeniPol.linija.grad.naziv}</td>
							<td>${trazeniPol.prevoznik.naziv}</td>
							<td>${trazeniPol.vremepolaska}</td>
							<td>${trazeniPol.prevoznik.brmesta - trazeniPol.brprodatihkarata}</td>
							<td>${trazeniPol.cenaKarte}</td> 							
							<td><input type="text" name="brKarata"></td>
							<td><input type="submit" value="Rezerviši"></td>							
						</tr>
						</c:forEach>
					</table>					
				</c:if>
			</form>
			<br>
			${poruka}
			<br>
			${popust}
			<br/><br/><br/>	
			<div class="rezervacija2">

				<strong><marquee>Na svaku treću rezervaciju,
						POPUST OD 10% na cenu karte! ! !</marquee></strong>
			</div>
		</div>
	</div>	
<%@ include file="/resources/templates/footer.jsp" %>