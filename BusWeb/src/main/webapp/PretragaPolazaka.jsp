<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="resources/css/Tema.css" type="text/css">
	<title>Prodaja karata</title>
</head>
<body>
	<jsp:useBean class="customBeans.SviRazlicitiPolasci" id="razlicitiGradoviUPolasku" scope="request" />
	<div class="Box">
		<header> <img id="logo" src="resources/images/Logo.jpg">
		</header>
		<%@ include file="/resources/templates/menu.jsp" %>
		<div class="pretraga">
			<br/>
			<h2 class="sansserif">Pronađite odgovarajući autobus</h2>
			<form method="get" action="PretragaServlet">
				<table class="pretragaPolazaka">
					<tr>
						<td align="right">Destinacija: </td>
						<td>
							<select name="destinacija">
								<c:forEach items="${razlicitiGradoviUPolasku.sviRazlicitiPolasci}" var="grad">
									<option value="${grad.idgrad}">${grad.naziv}</option>								
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">Datum polaska</td>
						<td><input id="datePick" type="date" name="datumPolaska"/>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="Potvrdi"/>
						</td>

					</tr>
				</table>
			</form>
		</div>		
		<div>			
			<div>
				<c:if test="${!empty polasci}">
					<h1>Rezultat pretrage</h1>
					<table class="table">
						<tr class="tr">
							<th class="th">Polazak</th>
							<th class="th">Destinacija</th>
							<th class="th">Vreme polaska</th>
							<th class="th">Cena</th>
							<th class="th">Slobodnih mesta</th>
							<th class="th">Prevoznik</th>							
						</tr>
						<c:forEach items="${polasci}" var="polazak">							
							<tr class="tr">
								<td class="td">Novi Sad</td>								
								<td class="td">${polazak.linija.grad.naziv}</td>
								<td class="td">${datum} ${polazak.vremepolaska}</td>
								<td class="td">${polazak.cenaKarte}</td>	
								<td class="td">${polazak.prevoznik.brmesta - polazak.brprodatihkarata}</td>
								<td class="td">${polazak.prevoznik.naziv}</td>								
							</tr>
						</c:forEach>
					</table>
				</c:if>
				${porukaPretraga}
			</div>
		</div>
	</div>
<%@ include file="/resources/templates/footer.jsp" %>