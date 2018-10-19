<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kalkulator bankowy</title>
</head>
<body>
<form action="kalkulator" method="post">
<label>Wielkość kredytu:	<p><input type="number" id="wK" name="wKred" required="required"/></label></p>
		Ilość rat:			<p><input type="number" id="iR" name="iRat" required="required"/></label></p>
		Oprocentowanie:		<p><input type="number" id="oP" name="oProc" step="any" required="required"/></label>%</p>
		
		Opłata stała:		<p><input type="number" id="oS" name="oStala" required="required"/></label></p>
		Wybierz rodzaj raty: <p><input type="radio" name="rRaty" value="rStala" checked> Stala<p>
 						 <input type="radio" name="rRaty" value="rMalej"> Malejaca
 		
<p><input type="submit" value="Oblicz!"/></form></p>
</body>
</html>