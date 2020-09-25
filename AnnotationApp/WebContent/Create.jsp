<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<div style = 'display:flex; align-items:center; justify-content:center; widht:500px'>
	<form action = 'Create'  method ='post'>
		Anotação: <input style =' width: 100%;clear: both;' type = 'text' name = 'note' /><br/>
		Tipo: <select name = 'type'><option>insper</option><option>projeto pessoal</option><option>trabalho</option></select><br/>
		Data de criação: <input  style =' width: 100%;clear: both;' type = 'text' name = 'creation' /><br/>
		Deadline: <input  style =' width: 100%;clear: both;' type = 'text' name = 'deadline' /><br/>
		<input type="hidden" id="username" name="username" value="${username}">
		<input type = 'submit' value = 'Gravar' /><br/>
	</form>
</div>
</body>
</html>


