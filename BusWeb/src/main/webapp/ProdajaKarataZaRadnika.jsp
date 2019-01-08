<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="resources/css/Tema.css" type="text/css">
	<title>Prodaja karata za radnika</title>
</head>
<body>
	<jsp:useBean class="customBeans.SviRazlicitiPolasci" id="razlicitipolasci" scope="session" />
	<div class="Box">
		<img id="logo" src="resources/images/Logo.jpg">
		<%@ include file="/resources/templates/menu.jsp" %>
		<br></br>
		<h2 align="center">PRODAJA KARATA</h2>
		<div class="rezervacija2">
			<form action="ProdajaKarataZaRadnikaServlet" method="get">
				<table>
					<tr>
						<td><h3><label for="polazak">Odaberite polazak: </label></td></h3>
						<td><select name="polazak">
								<c:forEach items="${razlicitipolasci.getSviRazlicitiPolasci()}" var="p">
									<option value="${p.idgrad}">${p.naziv}</option>
								</c:forEach>
						</select></td>
						<div class="datum">
							<td><label>Datum polaska:</label></td>
							<td><input id="datePick" type="date" name="datumPolaska" />							
						</div>
					</tr>		
				</table>				
				<input type="submit" value="Potvrdi"> 
			</form>
			<br/><br/>			
		</div>
		<form action=ProdajaKarataZaRadnikaServlet method="post">
			<c:if test="${empty trazeniPolasci}">
				<p style="color: red">${porukaNemaPol}</p>
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
						<input type="hidden" name="idPolaska" value="${trazeniPol.idpolaska}" />
						<tr>							
							<td>${trazeniPol.linija.grad.naziv}</td>
							<td>${trazeniPol.prevoznik.naziv}</td>
							<td>${datum} ${trazeniPol.vremepolaska}</td>
							<td>${trazeniPol.prevoznik.brmesta - trazeniPol.brprodatihkarata}</td>
							<td>${trazeniPol.cenaKarte}</td>
							<td><input type="text" name="brKarata"></td>
							<td><input type="submit" value="Prodaj"></td>
						
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</form>
		${poruka }
<%@ include file="/resources/templates/footer.jsp" %>